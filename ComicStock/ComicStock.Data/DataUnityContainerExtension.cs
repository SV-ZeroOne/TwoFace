﻿using System;
using Microsoft.Practices.Unity;
using ComicStock.Data;
using ComicStock.Data.Interfaces;
using ComicStock.Data.Implementations;

namespace ComicStock.WebAPI
{
    internal class DataUnityContainerExtension : UnityContainerExtension
    {
        protected override void Initialize()
        {
            this.Container.RegisterType<SquareEyesContext>();
            this.Container.RegisterType<IssueInterface, IssuesRepo>(new HierarchicalLifetimeManager());
        }
    }
}