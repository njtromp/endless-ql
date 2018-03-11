using System;
using System.Collections.Generic;
using System.Linq;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.API.Output;
using QuestionaireDomain.Entities.DomainObjects;

namespace QuestionnaireDomain.Logic.Logic
{
    internal class BuildOutputVisitor : 
        IBuildOutputVisitor,
        IAstToOutputVisitor,
        IAstToOutputVisitor<IQuestionnaireRootNode, IQuestionnaireOutputItem>,
        IAstToOutputVisitor<IUserInputQuestionNode, IQuestionOutputItem>

    {
        private bool m_questionsCurrentlyVisible = true;
        private readonly IDomainItemLocator m_domainItemLocator;
        private readonly IOutputItemFactory m_outputItemFactory;


        public BuildOutputVisitor(
            IDomainItemLocator domainItemLocator,
            IOutputItemFactory outputItemFactory)
        {
            m_domainItemLocator = domainItemLocator;
            m_outputItemFactory = outputItemFactory;
        }

        public Reference<IQuestionnaireOutputItem> Build(
            Reference<IQuestionnaireRootNode> node)
        {
            dynamic d = node;
            return this.Visit(d);
        }

        public Reference<IQuestionnaireOutputItem> Visit(
            Reference<IQuestionnaireRootNode> questionnaireNode)
        {
            var node = questionnaireNode
                .ToDomainItem(m_domainItemLocator);

            var questions = new List<Reference<IQuestionOutputItem>>();
            foreach (var statement in node.Statements)
            {
                if (m_domainItemLocator.Exists<IUserInputQuestionNode>(statement.Id))
                {
                    var domainItem = m_domainItemLocator
                        .GetRef<IUserInputQuestionNode>(statement.Id);
                    var questionRef = Visit(domainItem);
                    questions.Add(questionRef);
                }
            }

            return m_outputItemFactory.CreateQuestionnaireOutputItem(
                node.QuestionnaireName,
                questions);
        }

        private string GetValue(Guid questionId)
        {
            return "NOT WORKING YET";
        }


        private Type GetQuestionType(Guid questionId)
        {
            return m_domainItemLocator
                .Get<IQuestionNode>(questionId)
                ?.QuestionType;
        }

        public Reference<IQuestionOutputItem> Visit(Reference<IUserInputQuestionNode> node)
        {
            var domainItem = node.ToDomainItem(m_domainItemLocator);
            return m_outputItemFactory.CreateQuestionOutputItem(
                domainItem.QuestionText,
                GetValue(node.Id),
                GetQuestionType(node.Id),
                m_questionsCurrentlyVisible,
                false);
        }
    }
}