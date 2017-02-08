using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using ComicStock.WebAPI.Models;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace ComicStock.WebAPI.Controllers
{
    public class OrdersController : ApiController
    {
        private readonly OrderInterface orderRepo;
        private List<OrderDTO> newOrders;

        public OrdersController(OrderInterface orderRepo)
        {
            this.orderRepo = orderRepo;
            IEnumerable someOrders = orderRepo.GetAll();
            newOrders = new List<OrderDTO>();
            foreach (Order i in someOrders)
            {
                OrderDTO newOrder = new OrderDTO(i);
                newOrders.Add(newOrder);

            }
        }

        // GET api/orders
        public IEnumerable<OrderDTO> Get()
        {
            return newOrders;
        }

        // GET api/orders/id
        public OrderDTO Get(int id)
        {
            Order someOrder = orderRepo.GetById(id);
            if (someOrder == null)
            {
                throw new HttpResponseException(HttpStatusCode.NotFound);
            }
            OrderDTO someOrderDTO = new OrderDTO(someOrder);
            return someOrderDTO;
        }

        // Lamda Search Function
        // Need to figure out what property they might want to search orders for.
        public IList<OrderDTO> Get(string search)
        {
            return Get().Where(i => i.DeliveryStatus.Contains(search)).ToList<OrderDTO>();
        }

    }
}
