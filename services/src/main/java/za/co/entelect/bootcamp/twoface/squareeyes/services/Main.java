package za.co.entelect.bootcamp.twoface.squareeyes.services;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import za.co.entelect.bootcamp.twoface.squareeyes.domain.stock.Stock;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.stock.StockRepository;
import za.co.entelect.bootcamp.twoface.squareeyes.persistence.relational.stock.RelationalStockRepository;

/**
 * Created by quinton.weenink on 2017/01/30.
 */
public class Main
{
    public static void main(String[] args)
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:za/co/entelect/bootcamp/twoface/squareeyes/services/root-context.xml");
        StockRepository sr = context.getBean(RelationalStockRepository.class);
        Stock stock = sr.find(30);
        System.out.println("Available qty: " + stock.getAvailableQty());
    }

}