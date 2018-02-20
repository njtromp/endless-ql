﻿using System;
using System.Collections.Generic;
using QuestionaireDomain.Entities.API;

namespace QuestionaireDomain.Entities.DomainObjects
{
    //public abstract class Element : AstNodeBase
    //{
    //}

    //public class Body : AstNodeBase
    //{
    //    private List<Element> m_body = new List<Element>();

    //    public List<Element> Body => m_body;

    //    public void AddElement(Element bodyElement)
    //    {
    //        m_body.Add(bodyElement);
    //    }
        
    //    public override void Accept(IAstVisitor visitor)
    //    {
    //        visitor.Visit(this);
    //    }
    //}

    

    //public class Questionnaire 
    //{
    //    private string m_name;
    //    private Body m_body;

    //    public Questionnaire(string id, Body body)
    //    {
    //        m_name = id;
    //        m_body = body;
    //    }

    //    public string Name => m_name;
    //    public Body Body => m_body;
    //}

    
    public class QuestionnaireAst : AstNodeBase, IQuestionnaireAst
    {
        public QuestionnaireAst()
        {
            Id = Guid.NewGuid();
            Questions = new List<IQuestionAst>();
        }

        public string FormName { get; set; }
        public Guid Id { get; }
        public IList<IQuestionAst> Questions { get; }
        public override void Accept(IAstVisitor visitor)
        {
            //ToDo: something here
        }
    }
}