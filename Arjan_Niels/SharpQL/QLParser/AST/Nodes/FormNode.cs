﻿using QLParser.AST;

namespace QLParser.AST.Nodes
{
    public class FormNode : Node
    {
        public string FormName { get; set; }

        public FormNode(Location location, string formName) : base(location, NodeType.FORM)
        {
            this.FormName = formName;
        }

        public override string ToString()
        {
            return base.ToString() + string.Format("\t\t{0}", FormName);
        }
    }
}