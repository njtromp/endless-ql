using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IQuestionnaireModelCreator
    {
        bool Validate(Reference<IQuestionnaireRootNode> questionnaireRootNode);
        void Create(Reference<IQuestionnaireRootNode> questionnaireRootNode);
    }
}
