package za.co.entelect.bootcamp.twoface.squareeyes.web;

import za.co.entelect.bootcamp.twoface.squareeyes.services.payment.PaymentService;
import za.co.entelect.bootcamp.twoface.squareeyes.services.payment.SupplierPaymentDTO;

/**
 * Created by quinton.weenink on 2017/01/17.
 */
public class MockPaymentService implements PaymentService {
    public String makePayment(SupplierPaymentDTO supplierPayment) {
        System.out.println("Executed payment service");
        return "Success";
    }
}
