﻿using System;
using QuestionaireDomain.Entities.API;
using QuestionaireDomain.Entities.API.AstNodes.Questionnaire;
using QuestionaireDomain.Entities.API.AstNodes.Relational;
using QuestionaireDomain.Entities.DomainObjects;
using QuestionnaireDomain.Logic.API;

namespace QuestionnaireDomain.Logic.Logic
{
    internal class QuestionnaireAstCreator : IQuestionnaireAstCreator
    {
        private readonly IAstTreeBuilder m_astTreeBuilder;

        public QuestionnaireAstCreator(IAstTreeBuilder astTreeBuilder)
        {
            m_astTreeBuilder = astTreeBuilder;
        }

        public Reference<IQuestionnaireRootNode> Create(string definition)
        {
            return m_astTreeBuilder.BuildForm(definition);
        }
    }
}