package za.co.entelect.bootcamp.twoface.squareeyes.services.supplier;

/**
 * Created by mpho.mahase on 2017/01/16.
 */
public interface SupplierService {
    public String placeOrder(IssueOrderDTO issueOrder, String supplierRefNumber, int quantity);
}
