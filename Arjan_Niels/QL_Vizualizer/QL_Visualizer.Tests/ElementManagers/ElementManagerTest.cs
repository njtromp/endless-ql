using Microsoft.VisualStudio.TestTools.UnitTesting;
using QL_Vizualizer.ElementManagers;

namespace QL_Visualizer.Tests.ElementManagers
{
    [TestClass]
    public abstract class ElementManagerTest<T> where T : ElementManager
    {
        protected T Widget;

        [TestMethod]
        public void ActiveTest()
        {
            Assert.IsTrue(Widget.Active);
        }
    }
}