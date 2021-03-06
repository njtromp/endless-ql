﻿using QLParser;
using QLParser.Analysis;
using QLParser.AST.Nodes;
using System;
using System.Text;

namespace QLParserTester
{
    class Program
    {
        static void Main(string[] args)
        {
            string[] lines = System.IO.File.ReadAllLines(@"..\..\..\QLParserTester\Questionnaire.gl");
            StringBuilder builder = new StringBuilder();
            foreach (string line in lines)
                builder.AppendLine(line);

            Console.WriteLine("Start parsing the QL");
            FormNode form = QLParserHelper.Parse(builder.ToString());
            PrintForm(form);

            Analyser.Analyse(form);
            var errors = Analyser.GetErrors();
            Console.WriteLine("\n\n---- Errors: {0} ----", errors.Count);
            foreach (string error in errors)
                Console.WriteLine(error);

            Console.ReadLine();
        }

        public static void PrintForm(FormNode form)
        {
            Console.WriteLine(form);
            foreach (Node section in form.Children)
                switch (section.GetNodeType())
                {
                    case NodeType.QUESTION:
                        PrintSection(section as QuestionNode);
                        break;
                    case NodeType.COMPUTED:
                        PrintSection(section as ComputedNode);
                        break;
                    case NodeType.CONDITIONAL:
                        PrintSection(section as ConditionalNode);
                        break;
                    default:
                        return;
                }
        }

        public static void PrintSection(QuestionNode question)
        {
            Console.WriteLine(question);
        }

        public static void PrintSection(ComputedNode computed)
        {
            Console.WriteLine(computed);
        }

        public static void PrintSection(ConditionalNode conditional)
        {
            Console.WriteLine("\n" + conditional);
            foreach (Node section in conditional.Children)
                if (section.GetType() == typeof(QuestionNode))
                    PrintSection(section as QuestionNode);
                else if (section.GetType() == typeof(ComputedNode))
                    PrintSection(section as ComputedNode);
                else
                    PrintSection(section as ConditionalNode);
        }
    }
}
