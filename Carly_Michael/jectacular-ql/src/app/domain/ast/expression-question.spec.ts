import {Variable} from './expressions/variable';
import * as astMock from './ast-mock';
import {ExpressionQuestion} from './expression-question';
import {QuestionType} from './question-type';
import {CircularDependencyError} from '../errors';

describe('Expression question', () => {
  const variable = new Variable('question', astMock.emptyLoc);
  const question = new ExpressionQuestion('question', 'label', QuestionType.INT, variable, astMock.emptyLoc);

  it('should check for circular dependency', () => {
    expect(() => question.checkDependencies()).toThrow(jasmine.any(CircularDependencyError));
  });
});
