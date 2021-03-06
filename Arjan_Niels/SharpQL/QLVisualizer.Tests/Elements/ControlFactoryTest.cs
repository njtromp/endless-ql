﻿using System.Windows.Forms;
using Microsoft.VisualStudio.TestTools.UnitTesting;
using QLVisualizer.Controllers;
using QLVisualizer.Controllers.Display;
using QLVisualizer.Factories;
using QLVisualizer.Style;
using QLVisualizer.Elements.Managers.LeafTypes;

namespace QLVisualizer.Tests.Elementss
{
    [TestClass]
    public class ControlFactoryTest : IElementFactoryTest
    {
        private ControlFactory _controlFactory;
        private ElementManagerController _widgetController;

        private IntQuestionManager _intWidget;
        private BoolQuestionManager _boolWidget;
        private StringQuestionManager _stringWidget;

        private WindowsStyleProperties _style;

        [TestInitialize]
        public void Initialize()
        {
            _style = new WindowsStyleProperties();
            _widgetController = new ElementManagerDisplayContollerWindows(null, 10);

            _controlFactory = new ControlFactory(_widgetController);
            _intWidget = new IntQuestionManager("a", "q1", null, _widgetController);
            _boolWidget = new BoolQuestionManager("b", "q2", null, _widgetController);
            _stringWidget = new StringQuestionManager("c", "q3", null, _widgetController);
        }

        #region Create
        [TestMethod]
        public void CreateBoolElementTest()
        {
            Control c = _controlFactory.CreateElement(_boolWidget, _style);

            // Should be one item in the control
            Assert.AreEqual(1, c.Controls.Count);

            // Item shoud be a textbox
            Assert.IsInstanceOfType(c.Controls[0], typeof(CheckBox));

            // Texts should match
            Assert.AreEqual(c.Controls[0].Text, _boolWidget.Text);
        }

        [TestMethod]
        public void CreateIntElementTest()
        {
            Control c = _controlFactory.CreateElement(_intWidget, _style);

            // Should be two items in the control
            Assert.AreEqual(2, c.Controls.Count);

            // A Label
            Assert.IsInstanceOfType(c.Controls[0], typeof(Label));

            // And a TextBox
            Assert.IsInstanceOfType(c.Controls[1], typeof(TextBox));

            // Texts should match
            Assert.AreEqual(c.Controls[0].Text, _intWidget.Text);
        }

        [TestMethod]
        public void CreateStringElementTest()
        {
            Control c = _controlFactory.CreateElement(_stringWidget, _style);

            // Should be two items in the control
            Assert.AreEqual(2, c.Controls.Count);

            // A Label
            Assert.IsInstanceOfType(c.Controls[0], typeof(Label));

            // And a TextBox
            Assert.IsInstanceOfType(c.Controls[1], typeof(TextBox));

            // Texts should match
            Assert.AreEqual(c.Controls[0].Text, _stringWidget.Text);
        }
        #endregion

        #region Update
        [TestMethod]
        public void UpdateBoolElementTest()
        {
            Control c = _controlFactory.CreateElement(_boolWidget, _style);

            // Control should contain a checkbox at 0
            Assert.IsInstanceOfType(c.Controls[0], typeof(CheckBox));

            // Check the checkbox
            ((CheckBox)c.Controls[0]).Checked = true;

            // Answer must now be updated
            Assert.IsTrue(_boolWidget.Answer.Value);
        }

        [TestMethod]
        public void UpdateIntElementTest()
        {
            Control c = _controlFactory.CreateElement(_intWidget, _style);

            // Control should contain a checkbox at 0
            Assert.IsInstanceOfType(c.Controls[1], typeof(TextBox));

            // Check the checkbox
            ((TextBox)c.Controls[1]).Text = 100.ToString();

            // Answer must now be updated
            Assert.AreEqual(100, _intWidget.Answer.Value);
        }

        [TestMethod]
        public void UpdateStringElementTest()
        {
            Control c = _controlFactory.CreateElement(_stringWidget, _style);

            // Control should contain a checkbox at 0
            Assert.IsInstanceOfType(c.Controls[1], typeof(TextBox));

            // Check the checkbox
            ((TextBox)c.Controls[1]).Text = "unittest";

            // Answer must now be updated
            Assert.AreEqual("unittest", _stringWidget.Answer.Value);
        }
        #endregion
    }
}
