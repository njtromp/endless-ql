﻿using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLParser.AST.Nodes;

namespace QLParser.Tests.AST
{
    [TestClass]
    public class FormNameTest : QLTest
    {
        private readonly string _simpleForm = "form SimpleForm { }";
        private readonly string _incorrectSimpleForm = "if SimpleForm { }";

        [TestMethod]
        public void SimpleFormNameTest()
        {
            FormNode form = QLParserHelper.Parse(_simpleForm);
            Assert.AreEqual("SimpleForm", form.FormName);
        }

        [TestMethod]
        public void IncorrectFormSyntexTest()
        {
            FormNode form = QLParserHelper.Parse(_incorrectSimpleForm);
            Assert.IsNull(form);
        }
    }
}