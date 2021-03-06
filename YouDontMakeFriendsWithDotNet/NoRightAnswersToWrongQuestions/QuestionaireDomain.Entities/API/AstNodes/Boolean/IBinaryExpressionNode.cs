﻿using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API.AstNodes.Boolean
{
    public interface IBinaryExpressionNode : IExpressionNode
    {
        Reference<IBooleanLogicNode> LeftExpression { get; }
        Reference<IBooleanLogicNode> RightExpression { get; }
    }
}