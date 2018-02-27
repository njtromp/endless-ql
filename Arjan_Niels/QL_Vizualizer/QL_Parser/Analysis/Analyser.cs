﻿using QL_Parser.Analysis.Semantic;
using QL_Parser.Analysis.Syntactic;
using QL_Parser.AST.Nodes;
using System.Collections.Generic;
using System.Linq;

namespace QL_Parser.Analysis
{
    public class Analyser
    {
        #region Singleton
        private static Analyser _instance;
        public static Analyser Instance
        {
            get
            {
                if (_instance == null)
                    _instance = new Analyser();

                return _instance;
            }
        }
        #endregion

        private List<IAnalyser> _analysers;
        private List<ParseMessage> _messages;

        private Analyser()
        {
            this._messages = new List<ParseMessage>();
            this._analysers = new List<IAnalyser>()
            {
                // Syntactic
                new QuestionHasNoChildrenValidator(),
                new SingleFormValidator(),

                // Semantic
                new DuplicateVariableAnalyser(),
                new OnlyInitialisedVarsAnalyser(),
                new BooleanStatementnalyser()
            };
        }

        public static bool Analyse(Node node)
        {
            var result = true;
            foreach (IAnalyser analyser in Instance._analysers)
                if (!analyser.Analyse(node) && result)
                    result = false;
            return result;
        }

        public static void AddMessage(string message, MessageType type)
        {
            Instance._messages.Add(new ParseMessage(message, type));
        }

        public static List<string> GetErrors()
        {
            return Instance._messages
                .Select(x => x.ToString())
                .ToList();
        }

        public static void Reset()
        {
            Instance._messages.Clear();
        }
    }
}