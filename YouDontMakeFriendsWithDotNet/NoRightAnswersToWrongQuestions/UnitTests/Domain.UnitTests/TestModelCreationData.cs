using System;
using System.Collections;
using System.Globalization;
using NUnit.Framework;
using QuestionaireDomain.Entities.API;

namespace UnitTests.Domain.UnitTests
{
    public static class TestModelCreationData
    {
        private static readonly string NewLine = Environment.NewLine;

        public static IEnumerable SimpleQuestionnaireCases
        {
            get
            {
                var formTemplate = "form {0} {{ q: \"i\"  date}}";
                yield return new TestCaseData(
                    string.Format(formTemplate, "John"),
                    "John");
                yield return new TestCaseData(
                    string.Format(formTemplate, "TaxAuthorityForm"),
                    "TaxAuthorityForm");
            }
        }

        public static IEnumerable QuestionTypes
        {
            get
            {
                var formTemplate = "form TestForm {{ q: \"i\"  {0}}}";
                yield return new TestCaseData(
                    string.Format(formTemplate, "boolean"),
                    typeof(bool));

                yield return new TestCaseData(
                    string.Format(formTemplate, "string"),
                    typeof(string));

                yield return new TestCaseData(
                    string.Format(formTemplate, "decimal"),
                    typeof(decimal));

                yield return new TestCaseData(
                    string.Format(formTemplate, "integer"),
                    typeof(int));

                yield return new TestCaseData(
                    string.Format(formTemplate, "date"),
                    typeof(DateTime));
            }
        }

        public static IEnumerable DefaultQuestionValues
        {
            get
            {
                var formTemplate = "form TestForm {{ q: \"i\"  {0}}}";
                yield return new TestCaseData(
                    string.Format(formTemplate, "boolean"),
                    default(bool).ToString());

                //yield return new TestCaseData(
                //    string.Format(formTemplate, "string"),
                //    default(string));

                //yield return new TestCaseData(
                //    string.Format(formTemplate, "decimal"),
                //    default(decimal).ToString(CultureInfo.InvariantCulture));

                //yield return new TestCaseData(
                //    string.Format(formTemplate, "integer"),
                //    default(int).ToString(CultureInfo.InvariantCulture));

                //yield return new TestCaseData(
                //    string.Format(formTemplate, "date"),
                //    default(DateTime).ToString(CultureInfo.InvariantCulture));
            }
        }
    }
}