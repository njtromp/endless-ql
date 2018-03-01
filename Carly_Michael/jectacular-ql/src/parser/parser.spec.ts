import {parse} from './ql-parser';
import {Form, If, Question, QuestionType, Statement} from '../app/domain/ast/index';
import {gen, check, property, sample, sampleOne} from 'testcheck';
import * as mockInput from '../app/mock-input';

function questionTypeToString(type: QuestionType): string {
  switch (type) {
    case QuestionType.BOOLEAN: return 'boolean';
    case QuestionType.DATE: return 'date';
    case QuestionType.DECIMAL: return 'decimal';
    case QuestionType.MONEY: return 'money';
    case QuestionType.STRING: return 'string';
    case QuestionType.INT: return 'integer';
    default: console.log(`Unknown type ${type}`);
  }
}

function isValidAsciiString(str: string): boolean {
  return /^[\x00-\x7F]*$/.test(str);
}

function countMaxDepth(statements: Statement[]): number {
  let depth = 1;

  statements.forEach((statement) => {
    if (statement instanceof If) {
      depth += countMaxDepth((<If>statement).statements);
    }
  });

  return depth;
}

describe('Test functions tests', () => {
  it('Should correctly calculate max depth', () => {
    expect(countMaxDepth([])).toBe(1);

    const question: Question = new Question('name', 'label', QuestionType.BOOLEAN, null);
    expect(countMaxDepth([question])).toBe(1);

    const questionArray: Question[] = [];
    for (let i = 0; i < 256; i++) {
      questionArray.push(question);
    }
    expect(countMaxDepth(questionArray)).toBe(1);

    const ifStatement: If = new If('condition', [question], null);
    expect(countMaxDepth([ifStatement])).toBe(2);

    const ifStatement2: If = new If('condition', [ifStatement], null);
    expect(countMaxDepth([ifStatement2])).toBe(3);
  });

  it('Should correctly determine valid ascii strings', () => {
    check(property(gen.intWithin(0, 50), x => {
      const asciiStrings = sample(gen.alphaNumString.notEmpty(), x);

      asciiStrings.forEach(str => {
        expect(isValidAsciiString(str)).toBe(true);

        const invalidStr = str.split('').map(c => {
          return String.fromCharCode(c.charCodeAt(0) + 128);
        }).join('');

        expect(isValidAsciiString(invalidStr)).toBe(false);
      });
    }), {numTests: 100});

    let allValidAsciiCharacters = '';

    for (let i = 0; i < 128; i++) {
      allValidAsciiCharacters = allValidAsciiCharacters.concat(String.fromCharCode(i));
    }

    expect(isValidAsciiString(allValidAsciiCharacters)).toBe(true);
    expect(isValidAsciiString('á')).toBe(false);
    expect(isValidAsciiString('ß')).toBe(false);
    expect(isValidAsciiString('私')).toBe(false);
  });
});

describe('Generated forms', () => {
  it('Should parse correctly generated forms', () => {
    check(property(gen.intWithin(0, 50), x => {
      const formName = sampleOne(gen.alphaNumString.notEmpty());
      const questions = sample(gen.alphaNumString.notEmpty(), x * 2);
      const types = sample(gen.intWithin(QuestionType.INT, QuestionType.DATE), x);
      let form = `form ${formName} { \r\n`;

      for (let i = 0, n = 0; i < questions.length; i += 2, n++) {
        form += `${questions[i]}: "${questions[i + 1]}" ${questionTypeToString(types[n])}\r\n`;
      }

      form += ' }';

      const output = parse(form, {});
      expect(output).not.toBeNull();
      expect(output.name).toBe(formName);
      expect(output.statements.length).toBe(x);
    }), {numTests: 100});
  });

  it('Should parse nested ifs', () => {
    check(property(gen.intWithin(0, 256), x => {
      let form = `form form { \r\n`;

      form += `question: "question" integer\r\n`;
      for (let i = 0; i < x; i++) {
        form += `if (question) {\r\n`;
      }

      form += `nestedQuestion: "nestedQuestion" integer\r\n`;

      for (let i = 0; i < x; i++) {
        form += `}\r\n`;
      }

      form += ' }';

      const output: Form = parse(form, {});
      expect(output).not.toBeNull();
      expect(countMaxDepth(output.statements)).toBe(x + 1);
    }), {numTests: 100});
  });

  it('Should error on forms with incorrect question names', () => {
    check(property(gen.intWithin(0, 50), x => {
      const formName = sampleOne(gen.string);
      const questions = sample(gen.string, x * 2);
      let validForm = true;
      const types = sample(gen.intWithin(QuestionType.INT, QuestionType.DATE), x);

      if (!isValidAsciiString(formName)) {
        validForm = false;
      }

      questions.forEach(q => {
        if (!isValidAsciiString(q)) {
          validForm = false;
        }
      });

      let form = `form ${formName} { \r\n`;

      for (let i = 0, n = 0; i < questions.length; i += 2, n++) {
        form += `${questions[i]}: "${questions[i + 1]}" ${questionTypeToString(types[n])}\r\n`;
      }

      form += ' }';

      if (validForm) {
        const output = parse(form, {});
        expect(output).not.toBeNull();
        expect(output.name).toBe(formName.trim());
        expect(output.statements.length).toBe(x);
      } else {
        expect(() => parse(form, {})).toThrow();
      }
    }), {numTests: 100});
  });
});

describe('The parser', () => {
  it('Should parse simple form', () =>  {
    const output = parse(mockInput.simpleForm, {});
    expect(output).not.toBeNull();
    expect(output.name).toBe('form');
    expect(output.statements.length).toBe(1);
    expect(output.statements[0].name).toBe('question');
    expect(output.statements[0].label).toBe('Question?');
    expect(output.statements[0].type).toBe(QuestionType.BOOLEAN);
  });

  it('Should parse form only with certain characters', () => {
    expect(() => parse(mockInput.formWrongName, {})).toThrow();
    expect(() => parse(mockInput.formWrongQuestionName, {})).toThrow();
  });

  it('Should parse multiple questions', () => {
    const output = parse(mockInput.multipleQuestionForm, {});
    expect(output).not.toBeNull();
    expect(output.name).toBe('form');
    expect(output.statements.length).toBe(4);
    expect(output.statements[0].name).toBe('questionOne');
    expect(output.statements[0].type).toBe(QuestionType.BOOLEAN);
    expect(output.statements[0].label).toBe('Question1?');
    expect(output.statements[1].name).toBe('questionTwo');
    expect(output.statements[1].label).toBe('Question2?');
    expect(output.statements[1].type).toBe(QuestionType.STRING);
    expect(output.statements[2].name).toBe('questionThree');
    expect(output.statements[2].label).toBe('Question3?');
    expect(output.statements[2].type).toBe(QuestionType.DATE);
    expect(output.statements[3].name).toBe('questionFour');
    expect(output.statements[3].label).toBe('Question4?');
    expect(output.statements[3].type).toBe(QuestionType.MONEY);
  });

  it('should parse a form with an if statement', () => {
    const output = parse(mockInput.ifQuestionForm, {});
    expect(output).not.toBeNull();
    expect(output.name).toBe('form');
    expect(output.statements.length).toBe(2);
    expect(output.statements[0].name).toBe('question');
    expect(output.statements[0].label).toBe('Question?');
    expect(output.statements[0].type).toBe(QuestionType.BOOLEAN);

    const ifStatement = output.statements[1];
    expect(ifStatement.condition).toBe('question');
    expect(ifStatement.statements.length).toBe(1);
    expect(ifStatement.statements[0].name).toBe('questionIf');
    expect(ifStatement.statements[0].label).toBe('QuestionIf?');
    expect(ifStatement.statements[0].type).toBe(QuestionType.INT);
  });

  it('Should parse expression questions', () =>  {
    const output = parse(mockInput.expressionQuestionForm, {});
    expect(output).not.toBeNull();
    expect(output.name).toBe('form');
    expect(output.statements.length).toBe(2);
    expect(output.statements[0].name).toBe('question');
    expect(output.statements[0].label).toBe('Question?');
    expect(output.statements[0].type).toBe(QuestionType.INT);
    expect(output.statements[1].name).toBe('exprQuestion');
    expect(output.statements[1].label).toBe('Expression?');
    expect(output.statements[1].type).toBe(QuestionType.INT);
  });

  it('Should parse after incorrect input', () =>  {
    expect(() => parse(mockInput.formMissingQuestionName, {})).toThrow();
    const output = parse(mockInput.expressionQuestionForm, {});
    expect(output).not.toBeNull();
    expect(output.name).toBe('form');
    expect(output.statements.length).toBe(2);
    expect(output.statements[0].name).toBe('question');
    expect(output.statements[0].label).toBe('Question?');
    expect(output.statements[0].type).toBe(QuestionType.INT);
    expect(output.statements[1].name).toBe('exprQuestion');
    expect(output.statements[1].label).toBe('Expression?');
    expect(output.statements[1].type).toBe(QuestionType.INT);
  });

  it('should parse an expression with a variable', () => {
    const output = parse(mockInput.expressionVariableForm, {});
    expect(output).not.toBe(null);
  });

  it('should parse a form with comments', () => {
    const output = parse(mockInput.commentForm, {});
    expect(output).not.toBe(null);
  });
});
