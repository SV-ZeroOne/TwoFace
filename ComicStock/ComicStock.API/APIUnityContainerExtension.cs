using System;
using Microsoft.Practices.Unity;

namespace ComicStock.API
{
    public class APIUnityContainerExtension : UnityContainerExtension
    {
        protected override void Initialize()
        {
            this.Container.RegisterType<OrderServiceInterface, OrderService>(new HierarchicalLifetimeManager());
        }
    }
}