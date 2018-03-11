using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API.Output;
using QuestionaireDomain.Entities.DomainObjects;
using QuestionnaireInfrastructure.API;

namespace QuestionaireDomain.Entities.API
{
    internal class OutputItemFactory : IOutputItemFactory
    {
        private readonly IIdMaker m_ids;
        private readonly IDomainItemRegistry m_registry;

        public OutputItemFactory(
            IIdMaker ids, 
            IDomainItemRegistry registry)
        {
            m_ids = ids;
            m_registry = registry;
        }

        public Reference<IQuestionnaireOutputItem> CreateQuestionnaireOutputItem(
            string displayName,
            List<Reference<IQuestionOutputItem>> questions)
        {
            var questionnaireOutputItem= new QuestionnaireOutputItem(
                m_ids.Next,
                displayName) {Questions = questions };

            return DomainItemRegistration<IQuestionnaireOutputItem>(
                questionnaireOutputItem);
        }

        public Reference<IQuestionOutputItem> CreateQuestionOutputItem(
            string text, 
            string value,
            Type type,
            bool isVisible, 
            bool isReadonly)
        {
            var questionOutputItem = new QuestionOutputItem(
                m_ids.Next,
                text,
                type,
                value,
                isVisible,
                isReadonly);

            return DomainItemRegistration<IQuestionOutputItem>(
                questionOutputItem);
        }

        private Reference<T> DomainItemRegistration<T>(T node) where T : IDomainItem
        {
            m_registry.Add(node);
            return new Reference<T>(node.Id);
        }
    }
}