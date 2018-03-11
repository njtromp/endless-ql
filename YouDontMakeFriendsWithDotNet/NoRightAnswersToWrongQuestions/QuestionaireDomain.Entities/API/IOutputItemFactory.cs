using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API.Output;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionaireDomain.Entities.API
{
    public interface IOutputItemFactory
    {
        Reference<IQuestionnaireOutputItem> CreateQuestionnaireOutputItem(string displayName,
            List<Reference<IQuestionOutputItem>> questions);

        Reference<IQuestionOutputItem> CreateQuestionOutputItem(
            string text,
            string value,
            Type type,
            bool isVisible,
            bool isReadonly);
   }
}
