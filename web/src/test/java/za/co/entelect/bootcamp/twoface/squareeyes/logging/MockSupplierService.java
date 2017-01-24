package za.co.entelect.bootcamp.twoface.squareeyes.logging;

import za.co.entelect.bootcamp.twoface.squareeyes.services.supplier.IssueOrderDTO;
import za.co.entelect.bootcamp.twoface.squareeyes.services.supplier.SupplierService;

/**
 * Created by quinton.weenink on 2017/01/17.
 */
public class MockSupplierService implements SupplierService{
    public String placeOrder(IssueOrderDTO issueOrder, String supplierRefNumber, int quantity) {
        System.out.println("Executed supplier service");
        return "Success";
    }
}
