import BinaryOperator from "../BinaryOperator";
import ExpressionVisitor from "../../visitors/ExpressionVisitor";

export default class Division extends BinaryOperator {
  accept(visitor: ExpressionVisitor): any {
    return visitor.visitDivision(this);
  }
}