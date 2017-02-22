using ComicStock.API;
using ComicStock.Data.Interfaces;
using ComicStock.Domain;
using ComicStock.WebAPI.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Web.Http;

namespace ComicStock.WebAPI.Controllers
{
    public class OrdersController : ApiController
    {
        private readonly OrderInterface orderRepo;
        private List<OrderDTO> newOrders;
        private readonly IOrderService orderService;

        public OrdersController(OrderInterface orderRepo, IOrderService orderService)
        {
            this.orderService = orderService;
            this.orderRepo = orderRepo;
            
        }

        public IEnumerable<OrderDTO> Get()
        {
            newOrders = new List<OrderDTO>();
            foreach (var order in orderRepo.GetAll())
            {
                newOrders.Add(convertObject(order));
            }
            return newOrders;
        }

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
            if (orderToDelete == null)
            {
                throw new HttpResponseException(HttpStatusCode.BadRequest);
            }
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

        private OrderDTO convertObject(Order order)
        {
            OrderDTO newOrder = new OrderDTO();
            newOrder.OrderID = order.OrderID;
            newOrder.OrderDate = order.OrderDate;
            newOrder.IssueID = order.IssueID;
            newOrder.SupplierID = order.SupplierID;
            newOrder.QtyOrdered = order.QtyOrdered;
            newOrder.ShipmentDate = order.ShipmentDate;
            newOrder.ShipmentRef = order.ShipmentRef;
            newOrder.Total = order.Total;
            newOrder.DeliveryStatus = order.DeliveryStatus;
            return newOrder;
        }

        [Route("api/Orders/GetPaged")]
        [HttpGet]
        public IHttpActionResult GetPaged(int pageNo, int pageSize)
        {

            int total = orderRepo.recordCount();

            IEnumerable<Order> orders = orderRepo.Paging(pageNo, pageSize);
            IList<OrderDTO> ordersDTO = new List<OrderDTO>();

            foreach (var item in orders)
            {
                ordersDTO.Add(convertObject(item));
            }

            return Ok(new PagedResult<OrderDTO>(ordersDTO, pageNo, pageSize, total));
        }

        [Route("api/Orders/GetSearchPaged")]
        [HttpGet]
        public IHttpActionResult GetSearchPaged(string searchKey, int pageNumber, int pageSize)
        {

            IEnumerable<Order> orders = orderRepo.Paging(pageNumber, pageSize, searchKey);
            IList<OrderDTO> ordersDTO = new List<OrderDTO>();
            int total = orders.Count();
            foreach (var item in orders)
            {
                ordersDTO.Add(convertObject(item));
            }

            return Ok(new PagedResult<OrderDTO>(ordersDTO, pageNumber, pageSize, total));
        }

    }
}
