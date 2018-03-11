using System;
using System.Collections.Generic;

namespace QuestionaireDomain.Entities.API.Output
{
    internal class QuestionOutputItem : IQuestionOutputItem
    {
        public QuestionOutputItem(
            Guid id, 
            string questionText,
            Type questionType,
            string value,
            bool isVisible,
            bool isReadonly)
        {
            Id = id;
            DisplayName = questionText;
            QuestionText = questionText;
            Value = value;
            QuestionType = questionType;
            Visible = isVisible;
            ReadOnly = isReadonly;
        }

        public Guid Id { get; }
        public string DisplayName { get; }
        public string QuestionText { get; }
        public bool Visible { get; set; }
        public bool ReadOnly { get; }
        public string Value { get; set; }
        public Type QuestionType { get; }
    }
}
