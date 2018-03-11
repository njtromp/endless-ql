using System.Collections.Generic;
using System.Linq;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.API.Output;
using QuestionnaireDomain.Logic.API;
using QuestionnaireInfrastructure.API;

namespace QuestionaireOrchestration.API
{
    internal class QuestionnaireQueryService : IQuestionnaireQueryService
    {
        private readonly IQuestionnaireDefinitionLoader m_definitionLoader;
        private readonly IQuestionnaireAstCreator m_astCreator;
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly IQuestionnaireModelCreator m_modelCreator;

        public QuestionnaireQueryService(
            IQuestionnaireDefinitionLoader definitionLoader,
            IQuestionnaireAstCreator astCreator,
            IDomainItemLocator domainItemLocator,
            IQuestionnaireModelCreator modelCreator)
        {
            m_definitionLoader = definitionLoader;
            m_astCreator = astCreator;
            m_domainItemLocator = domainItemLocator;
            m_modelCreator = modelCreator;
        }

        public IEnumerable<ModelReference<IQuestionnaireOutputItem>> GetAll()
        {
            var existingQuestionnaires = m_domainItemLocator
                .GetAll<IQuestionnaireOutputItem>();

            if (!existingQuestionnaires.Any())
            {
                var examples = m_definitionLoader.Load();
                foreach (var example in examples)
                {
                    m_astCreator.Create(example);
                }

                var questionnaireRefs = m_domainItemLocator
                    .GetAllRefs<IQuestionnaireRootNode>();

                foreach (var questionnaireRootNode in questionnaireRefs)
                {
                    if (m_modelCreator.Validate(questionnaireRootNode))
                    {
                        m_modelCreator.Create(questionnaireRootNode);
                    }
                }

                existingQuestionnaires = m_domainItemLocator
                    .GetAll<IQuestionnaireOutputItem>();
            }

            return existingQuestionnaires
                .Select(x => x.ToModel<IQuestionnaireOutputItem>());
        }
    }
}