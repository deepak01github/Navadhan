package com.navadhan.model;

public class AlertDetailsData {

    public String status;
    public String applicant_name;
    public String account_type;
    public String disbursed_amount;
    public String credit_guarantor;
    public String disbursed_date;
    public String account_status;
    public String alert_description;
    public String color_indicator;



    public AlertDetailsData(String status, String applicant_name, String account_type, String disbursed_amount, String credit_guarantor, String disbursed_date, String account_status, String alert_description, String color_indicator) {
        this.status = status;
        this.applicant_name = applicant_name;
        this.account_type = account_type;
        this.disbursed_amount = disbursed_amount;
        this.credit_guarantor = credit_guarantor;
        this.disbursed_date = disbursed_date;
        this.account_status = account_status;
        this.alert_description = alert_description;
        this.color_indicator = color_indicator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplicant_name() {
        return applicant_name;
    }

    public void setApplicant_name(String applicant_name) {
        this.applicant_name = applicant_name;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public String getDisbursed_amount() {
        return disbursed_amount;
    }

    public void setDisbursed_amount(String disbursed_amount) {
        this.disbursed_amount = disbursed_amount;
    }

    public String getCredit_guarantor() {
        return credit_guarantor;
    }

    public void setCredit_guarantor(String credit_guarantor) {
        this.credit_guarantor = credit_guarantor;
    }

    public String getDisbursed_date() {
        return disbursed_date;
    }

    public void setDisbursed_date(String disbursed_date) {
        this.disbursed_date = disbursed_date;
    }

    public String getAccount_status() {
        return account_status;
    }

    public void setAccount_status(String account_status) {
        this.account_status = account_status;
    }

    public String getAlert_description() {
        return alert_description;
    }

    public void setAlert_description(String alert_description) {
        this.alert_description = alert_description;
    }

    public String getColor_indicator() {
        return color_indicator;
    }

    public void setColor_indicator(String color_indicator) {
        this.color_indicator = color_indicator;
    }
}
