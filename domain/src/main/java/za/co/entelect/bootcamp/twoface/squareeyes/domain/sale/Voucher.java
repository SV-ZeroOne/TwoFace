package za.co.entelect.bootcamp.twoface.squareeyes.domain.sale;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by quinton.weenink on 2017/01/31.
 */
@Entity
@Table(name="Vouchers")
public class Voucher {

    private int voucherID;
    private String voucherNumber;
    private Date dateIssued;
    private Date dateRedeemed;
    private BigDecimal voucherValue;

    public Voucher(){}

    public Voucher(String voucherNumber, Date dateIssued, Date dateRedeemed, BigDecimal voucherValue){
        this.voucherNumber = voucherNumber;
        this.dateIssued = dateIssued;
        this.dateRedeemed = dateRedeemed;
        this.voucherNumber = voucherNumber;
    }

    public int getVoucherID() {
        return voucherID;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }
    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public Date getDateIssued() {
        return dateIssued;
    }
    public void setDateIssued(Date dateIssued) {
        this.dateIssued = dateIssued;
    }

    public Date getDateRedeemed() {
        return dateRedeemed;
    }
    public void setDateRedeemed(Date dateRedeemed) {
        this.dateRedeemed = dateRedeemed;
    }

    public BigDecimal getVoucherValue() {
        return voucherValue;
    }
    public void setVoucherValue(BigDecimal voucherValue) {
        this.voucherValue = voucherValue;
    }
}
