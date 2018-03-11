using System.Collections.Generic;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API.Output
{
    public interface IQuestionnaireOutputItem : IOutputItem
    {
        IList<Reference<IQuestionOutputItem>> Questions { get; set; }
    }
}