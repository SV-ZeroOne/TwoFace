using ComicStock.Data;
using Microsoft.Practices.Unity;
using System.Web.Http;
using Unity.WebApi;

namespace ComicStock.WebAPI
{
    public static class UnityConfig
    {
        public static void RegisterComponents()
        {
			var container = new UnityContainer();

            // register all your components with the container here
            // it is NOT necessary to register your controllers

            // e.g. container.RegisterType<ITestService, TestService>();
            container.AddExtension(new DataUnityContainerExtension());
            //container.AddExtension(new TestUnitiyContainerExtension());

            GlobalConfiguration.Configuration.DependencyResolver = new UnityDependencyResolver(container);
        }
    }
}