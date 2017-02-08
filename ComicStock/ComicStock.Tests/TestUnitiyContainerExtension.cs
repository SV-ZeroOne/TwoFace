using Microsoft.Practices.Unity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ComicStock.Tests
{
    class TestUnitiyContainerExtension : UnityContainerExtension
    {
        protected override void Initialize()
        {
            this.Container.RegisterType<SquareEyesContext>();
            this.Container.RegisterType<IssueInterface, IssuesRepo>(new HierarchicalLifetimeManager());
        }
    }
}
