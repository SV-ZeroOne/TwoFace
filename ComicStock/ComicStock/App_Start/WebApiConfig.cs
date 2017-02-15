using System;
using System.Collections.Generic;
using System.Linq;
using System.Web.Http;
using System.Web.Http.Cors;

namespace ComicStock
{
    public static class WebApiConfig
    {
        public static void Register(HttpConfiguration config)
        {
            // Web API configuration and services

            //// Web API routes
            var cor = new EnableCorsAttribute("*", "*", "*");
            config.EnableCors(cor);

            config.MapHttpAttributeRoutes();

            //This solves the access-control-allow-origin error
            var cors = new EnableCorsAttribute("*", "*", "*");
            config.EnableCors(cors);


            config.Routes.MapHttpRoute(
                name: "DefaultApi",
                routeTemplate: "api/{controller}/{id}",
                defaults: new { id = RouteParameter.Optional }
            );
        }
    }
}
