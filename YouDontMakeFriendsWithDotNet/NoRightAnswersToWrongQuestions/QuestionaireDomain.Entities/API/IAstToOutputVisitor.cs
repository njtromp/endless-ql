using System.Collections.Generic;
using QuestionaireDomain.Entities.API.AstNodes;
using QuestionaireDomain.Entities.API.Output;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IAstToOutputVisitor { }

    public interface IAstToOutputVisitor<TAst, TOutput>
        where TAst : IAstNode
        where TOutput : IOutputItem
    {
        Reference<TOutput> Visit(Reference<TAst> node);
    }
}