import TreeNode from "../../TreeNode";
import ExpressionVisitor from "../../visitors/ExpressionVisitor";
import Expression from "../Expression";

export default class NumberLiteral implements TreeNode, Expression {
  private value: number;

  constructor(value: number) {
    this.value = value;
  }

  accept(visitor: ExpressionVisitor): any {
    return visitor.visitLiteral(this);
  }

  getValue() {
    return this.value;
  }
}