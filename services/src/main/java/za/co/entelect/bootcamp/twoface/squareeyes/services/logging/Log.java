package za.co.entelect.bootcamp.twoface.squareeyes.services.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.entelect.bootcamp.twoface.squareeyes.web.facade.SupplierOrderFacade;


/**
 * Created by mpho.mahase on 2017/01/19.
 */
public class Log {

    public static void main(String[] args){
        SupplierOrderFacade supplierOrderFacade = new SupplierOrderFacade();
        //supplierOrderFacade.placeOrder(0, 12);
        supplierOrderFacade.testDummyMethod(24, "Mpho Mahase");
    }

}
