using System.IO;
using QuestionaireDomain.Entities.API.AstNodes;

namespace QuestionaireOrchestration.Visitors
{
    public interface IPrinter<T> where T : IAstNode
    {
        void Print(T node);
        TextWriter Writer { get; set; }
    }
}