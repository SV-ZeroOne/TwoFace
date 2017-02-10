using System;
using Microsoft.Practices.Unity;

namespace ComicStock.API
{
    public class APIUnityContainerExtension : UnityContainerExtension
    {
        protected override void Initialize()
        {
            this.Container.RegisterType<IOrderService, OrderService>(new HierarchicalLifetimeManager());
            this.Container.RegisterType<IStockService, StockService>(new HierarchicalLifetimeManager());
        }
    }
}