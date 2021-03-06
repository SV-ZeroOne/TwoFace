﻿using System;
using Microsoft.Practices.Unity;
using ComicStock.Data;
using ComicStock.Data.Interfaces;
using ComicStock.Data.Implementations;

namespace ComicStock.Data
{
    public class DataUnityContainerExtension : UnityContainerExtension
    {
        protected override void Initialize()
        {
            this.Container.RegisterType<SquareEyesContext>();
            this.Container.RegisterType<IssueInterface, IssuesRepo>(new HierarchicalLifetimeManager());
            this.Container.RegisterType<OrderInterface, OrderRepo>(new HierarchicalLifetimeManager());
            this.Container.RegisterType<SupplierInterface, SupplierRepo>(new HierarchicalLifetimeManager());
            this.Container.RegisterType<SupplierQuoteInterface, SupplierQuoteRepo>(new HierarchicalLifetimeManager());
            this.Container.RegisterType<SupplierPaymentInterface, SupplierPaymentRepo>(new HierarchicalLifetimeManager());
            this.Container.RegisterType<CreatorInterface, CreatorRepo>(new HierarchicalLifetimeManager());
            this.Container.RegisterType<VoucherInterface, VoucherRepo>(new HierarchicalLifetimeManager());
            this.Container.RegisterType<StockInterface, StockRepo>(new HierarchicalLifetimeManager());
            this.Container.RegisterType<SupplierQuoteInterface, SupplierQuoteRepo>(new HierarchicalLifetimeManager());
        }
    }
}