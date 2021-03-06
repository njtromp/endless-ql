﻿using QLParser.AST;

namespace QLParser.AST.Nodes.ExpressionNodes
{
    public abstract class ExpressionNode : Node, IExpressionNode
    {
        public IExpressionNode Left { get; protected set; }
        public IExpressionNode Right { get; protected set; }

        public ExpressionNode(Location location, NodeType type) : base(location, type)
        {
        }

        public QValueType GetQValueType()
        {
            var leftSideType = Left.GetQValueType();
            var rightSideType = Right.GetQValueType();

            if (leftSideType == rightSideType)
                return leftSideType;
            else
                return QValueType.UNKNOWN;
        }
    }
}