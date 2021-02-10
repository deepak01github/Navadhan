package com.navadhan.model;

public class ObligationDetailsData {
    private String total_disbursed;
    private String total_current_balance;
    private String total_installment;

    public ObligationDetailsData(String total_disbursed, String total_current_balance, String total_installment) {
        this.total_disbursed = total_disbursed;
        this.total_current_balance = total_current_balance;
        this.total_installment = total_installment;
    }

    public String getTotal_disbursed() {
        return total_disbursed;
    }

    public void setTotal_disbursed(String total_disbursed) {
        this.total_disbursed = total_disbursed;
    }

    public String getTotal_current_balance() {
        return total_current_balance;
    }

    public void setTotal_current_balance(String total_current_balance) {
        this.total_current_balance = total_current_balance;
    }

    public String getTotal_installment() {
        return total_installment;
    }

    public void setTotal_installment(String total_installment) {
        this.total_installment = total_installment;
    }
}
