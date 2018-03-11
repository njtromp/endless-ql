using Microsoft.Extensions.DependencyInjection;
using QuestionaireDomain.Entities;
using QuestionaireDomain.Entities.API;
using QuestionnaireDomain.Logic.API;
using QuestionnaireDomain.Logic.Logic;
using QuestionnaireInfrastructure.API;

namespace QuestionnaireDomain.Logic
{
    public class DomainLogicModule : IHasRegistrations
    {
        public void RegisterDependencies(IServiceCollection appRegistration)
        {
            appRegistration.AddSingleton(typeof(IQuestionnaireModelCreator), typeof(QuestionnaireModelCreator));
            appRegistration.AddSingleton(typeof(IQuestionnaireAstCreator), typeof(QuestionnaireAstCreator));
            appRegistration.AddSingleton(typeof(IPredicateCreator), typeof(PredicateCreator));
            appRegistration.AddSingleton(typeof(IDomainItemLocator), typeof(DomainItemLocator));
            appRegistration.AddSingleton(typeof(IBuildOutputVisitor), typeof(BuildOutputVisitor));
        }
    }
}