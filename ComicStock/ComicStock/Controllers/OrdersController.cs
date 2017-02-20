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
        private readonly IOrderService orderService;
        private int totalRecords = 0;

        public OrdersController(OrderInterface orderRepo, IOrderService orderService)
        {
            this.orderService = orderService;
            this.orderRepo = orderRepo;
            newOrders = new List<OrderDTO>();
            foreach (Order i in orderRepo.GetAll())
            {
                OrderDTO newOrder = new OrderDTO(i);
                newOrders.Add(newOrder);
            }
            totalRecords = newOrders.Count();
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
        public IList<OrderDTO> Get(string search)
        {
            string searchString = search.ToLower();
            
            return Get().Where(i =>
            i.DeliveryStatus != null && i.DeliveryStatus.ToLower().Contains(searchString) ||
            i.OrderID.ToString().Contains(searchString) ||
            i.IssueID.ToString().Contains(searchString) ||
            i.ShipmentDate != null && i.ShipmentDate.ToString().Contains(searchString) ||
            i.OrderDate != null && i.OrderDate.ToString().Contains(searchString) ||
            i.SupplierID.ToString().Contains(searchString) ||
            i.ShipmentRef != null && i.ShipmentRef.ToLower().Contains(searchString) ||
            i.Total.ToString().Contains(search) ||
            i.QtyOrdered.ToString().Contains(search)
            ).ToList<OrderDTO>();
        }

        [Route("api/Orders/PlaceOrder")]
        public void PlaceOrder(int issueID, Int16 quantity, int supplierID)
        {
            orderService.placeOrder(issueID, quantity, supplierID);
        }

        [Route("api/Orders/MakePayment")]
        public void MakePayment(int orderID)
        {
            orderService.makePayment(orderID);
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
            Order orderToGet = orderRepo.GetById(order.OrderID);
            if (orderToGet == null)
            {
                return null;
                throw new HttpResponseException(HttpStatusCode.BadRequest);
            }
            var orderToUpdate = updateOrder(order, orderToGet);
            orderRepo.Update(orderToUpdate);
            return order;
        }

        private Order updateOrder(OrderDTO order, Order orderToUpdate)
        {
            orderToUpdate.IssueID = order.IssueID;
            orderToUpdate.SupplierID = order.SupplierID;
            orderToUpdate.OrderDate = order.OrderDate;
            orderToUpdate.QtyOrdered = order.QtyOrdered;
            orderToUpdate.ShipmentDate = order.ShipmentDate;
            orderToUpdate.ShipmentRef = order.ShipmentRef;
            orderToUpdate.Total = order.Total;
            orderToUpdate.DeliveryStatus = order.DeliveryStatus;
            orderToUpdate.IsDeleted = order.IsDeleted;
            return orderToUpdate;
        }

        public void Delete(int orderID)
        {
            var orderToDelete = orderRepo.GetById(orderID);
            orderRepo.Delete(orderToDelete);
        }

        private Order convertDTO(OrderDTO orderDto)
        {
            Order newOrder = new Order();
            newOrder.OrderDate = orderDto.OrderDate;
            newOrder.IssueID = orderDto.IssueID;
            newOrder.SupplierID = orderDto.SupplierID;
            newOrder.QtyOrdered = orderDto.QtyOrdered;
            newOrder.ShipmentDate = orderDto.ShipmentDate;
            newOrder.ShipmentRef = orderDto.ShipmentRef;
            newOrder.Total = orderDto.Total;
            newOrder.DeliveryStatus = orderDto.DeliveryStatus;
            return newOrder;
        }

        [Route("api/Orders/GetPaged")]
        [HttpGet]
        public IHttpActionResult GetPaged(int pageNo = 1, int pageSize = 10)
        {
            int skip = (pageNo - 1) * pageSize;

            int total = totalRecords;

            List<OrderDTO> orders = newOrders
                .OrderBy(c => c.OrderID)
                .Skip(skip)
                .Take(pageSize)
                .ToList();

            return Ok(new PagedResult<OrderDTO>(orders, pageNo, pageSize, total));
        }

        [Route("api/Orders/GetSearchPaged")]
        [HttpGet]
        public IHttpActionResult GetSearchPaged(string searchKey, int pageNumber)
        {
            if (searchKey != null)
            {
                string searchString = searchKey.ToLower();
                IEnumerable<OrderDTO> someOrders = Get().Where(i => i.DeliveryStatus.ToLower().Contains(searchString) ||
            i.OrderID.ToString().Contains(searchString) ||
            i.IssueID.ToString().Contains(searchString) ||
            i.ShipmentDate != null && i.ShipmentDate.ToString().Contains(searchString) ||
            i.OrderDate != null && i.OrderDate.ToString().Contains(searchString) ||
            i.SupplierID.ToString().Contains(searchString) ||
            i.ShipmentRef != null && i.ShipmentRef.ToLower().Contains(searchString) ||
            i.Total.ToString().Contains(searchString) ||
            i.QtyOrdered.ToString().Contains(searchString));
                int pageSize = 20;
                int skip = (pageNumber - 1) * pageSize;

                int total = someOrders.Count();

                List<OrderDTO> orders = someOrders
                    .OrderBy(c => c.OrderID)
                    .Skip(skip)
                    .Take(pageSize)
                    .ToList();

                return Ok(new PagedResult<OrderDTO>(orders, pageNumber, pageSize, total));
            }
            return null;
        }

    }
}
