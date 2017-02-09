using ComicStock.API;
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
        private readonly OrderServiceInterface orderService;

        public OrdersController(OrderInterface orderRepo, OrderServiceInterface orderService)
        {
            this.orderService = orderService;
            this.orderRepo = orderRepo;
            newOrders = new List<OrderDTO>();
            foreach (Order i in orderRepo.GetAll())
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

        [Route("api/Orders/PlaceOrder")]
        public void PlaceOrder(int issueID, Int16 quantity)
        {
            orderService.placeOrder(issueID, quantity);
        }

        public Order Post(OrderDTO orderDto)
        {
            if (orderDto == null)
            {
                return null;
                throw new HttpResponseException(HttpStatusCode.NotAcceptable);
            }
            Order newOrder = convertDTO(orderDto);
            orderRepo.Add(newOrder);
            return newOrder;
        }

        public OrderDTO Put(OrderDTO order)
        {
            //Need to have error handling!
            Order orderToGet = orderRepo.GetById(order.OrderID);
            var orderToUpdate = updateIssue(order, orderToGet);
            orderRepo.Update(orderToUpdate);
            return order;
        }

        private Order updateIssue(OrderDTO order, Order orderToUpdate)
        {
            orderToUpdate.OrderDate = order.OrderDate;
            orderToUpdate.QtyOrdered = order.QtyOrdered;
            orderToUpdate.ShipmentDate = order.ShipmentDate;
            orderToUpdate.ShipmentRef = order.ShipmentRef;
            orderToUpdate.Total = order.Total;
            //Might need to map more fields to update.
            return orderToUpdate;
        }

        public void Delete(OrderDTO order)
        {
            //Need to have error handling!
            var orderToDelete = orderRepo.GetById(order.OrderID);
            orderRepo.Delete(orderToDelete);
        }

        private Order convertDTO(OrderDTO orderDto)
        {
            Order newOrder = new Order();
            newOrder.OrderDate = orderDto.OrderDate;
            newOrder.QtyOrdered = orderDto.QtyOrdered;
            newOrder.ShipmentDate = orderDto.ShipmentDate;
            newOrder.ShipmentRef = orderDto.ShipmentRef;
            newOrder.Total = orderDto.Total;
            //Might need to map more fields.
            return newOrder;
        }

    }
}
