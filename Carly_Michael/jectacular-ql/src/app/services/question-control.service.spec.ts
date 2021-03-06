import {inject, TestBed} from '@angular/core/testing';
import {QuestionControlService} from './question-control.service';
import {InputQuestion} from '../domain/angular-questions/input-question';
import {BooleanQuestion} from '../domain/angular-questions/boolean-question';

describe('The question control service', () => {
  let service: QuestionControlService;

  const questions = [
    new InputQuestion({
      key: 'intQuestion',
      label: 'intQuestion?',
      type: 'number',
      value: undefined,
      order: 0
    }),
    new InputQuestion({
      key: 'decimalQuestion',
      label: 'decimalQuestion?',
      type: 'number',
      value: undefined,
      order: 1
    }),
    new BooleanQuestion({
      key: 'booleanQuestion',
      label: 'booleanQuestion?',
      type: 'boolean',
      value: undefined,
      order: 2
    }),
    new InputQuestion({
      key: 'stringQuestion',
      label: 'stringQuestion?',
      type: 'text',
      value: '',
      order: 3
    }),
    new InputQuestion({
      key: 'dateQuestion',
      label: 'dateQuestion?',
      type: 'date',
      value: undefined,
      order: 4
    })
  ];

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [QuestionControlService]
    });
  });

  beforeEach(inject([QuestionControlService], (_service: QuestionControlService) => {
    service = _service;
  }));

  it('should return formControl elements for questions', () => {
    const result = service.toFormGroup(questions);
    expect(result.get('intQuestion')).toBeDefined();
    expect(result.get('decimalQuestion')).toBeDefined();
    expect(result.get('booleanQuestion')).toBeDefined();
    expect(result.get('stringQuestion')).toBeDefined();
    expect(result.get('stringQuestion').value).toBe('');
    expect(result.get('dateQuestion')).toBeDefined();
  });
});


