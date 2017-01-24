package za.co.entelect.bootcamp.twoface.squareeyes.services.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.order.Order;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.orders.OrdersRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.orders.OrdersRepositoryIMP;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.stock.StockRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.stock.StockRepositoryIMP;

import java.util.List;

/**
 * Created by sean.vienings on 2017/01/20.
 *
 */
public class ScheduleJob implements Job{


    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //System.out.println("SchedulJob executing");
        OrdersRepository scheduleOrderRepository = new OrdersRepositoryIMP();
        StockRepository scheduleStockRepository = new StockRepositoryIMP();

        List<Order> orderList;
        orderList = scheduleOrderRepository.findAll();
        //orderList = scheduleOrderRepository.search("DeliveryStatus","Delivered");
        for(Order order : orderList){
            if(order.getDeliveryStatus().equals("Pending"))
            {
                System.out.println("Found pending order");
                System.out.println(order.getIssue().getIssueTitle());
            }
        }
        //System.out.println("Schedule Job class");
    }
}
