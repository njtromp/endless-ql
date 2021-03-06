export class UnsupportedTypeError implements Error {
  name = 'UnsupportedType';
  constructor(public message: string) { }
}

export class UnknownQuestionError implements Error {
  name = 'UnknownQuestion';
  constructor(public message: string) {}
}

export class TypeError implements Error {
  name = 'TypeError';
  constructor(public message: string) {}
}

export class DuplicateIdentifierError implements Error {
  name = 'DuplicateIdentifier';
  constructor(public message: string) {}
}

export class UnknownOperatorError implements Error {
  name = 'UnknownOperator';
  constructor(public message: string) {}
}

export class CircularDependencyError implements Error {
  name = 'CircularDependency';
  constructor(public message: string) {}
}
