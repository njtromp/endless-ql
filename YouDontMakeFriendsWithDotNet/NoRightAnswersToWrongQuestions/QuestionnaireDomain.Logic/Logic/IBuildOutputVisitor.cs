using System;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.API.Output;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic.Logic
{
    public interface IBuildOutputVisitor
    {
        Reference<IQuestionnaireOutputItem> Build(Reference<IQuestionnaireRootNode> node);
    }
}