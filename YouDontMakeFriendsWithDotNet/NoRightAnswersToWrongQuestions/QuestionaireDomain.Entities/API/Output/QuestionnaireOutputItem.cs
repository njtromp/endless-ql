using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API.Output
{
    internal class QuestionnaireOutputItem : IQuestionnaireOutputItem
    {
        public QuestionnaireOutputItem(Guid id, string displayName)
        {
            Id = id;
            DisplayName = displayName;
        }

        public Guid Id { get; }
        public string DisplayName { get; }
        public IList<Reference<IQuestionOutputItem>> Questions { get; set; }
    }
}