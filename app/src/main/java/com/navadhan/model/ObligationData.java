package com.navadhan.model;

public class ObligationData {
    public String credit_guarantor;
    public String ownership;
    public String applicant_name;
    public String account_type;
    public String disbursed_amount;
    public String current_balance;
    public String monthly_instalment;
    public String in_cb_report;
    public String is_dpd;
    public String days;
    public String start_month;
    public String end_month;

    public ObligationData(String credit_guarantor, String ownership, String applicant_name, String account_type,
                          String disbursed_amount, String current_balance, String monthly_instalment,
                          String in_cb_report, String is_dpd, String days, String start_month, String end_month) {
        this.credit_guarantor = credit_guarantor;
        this.ownership = ownership;
        this.applicant_name = applicant_name;
        this.account_type = account_type;
        this.disbursed_amount = disbursed_amount;
        this.current_balance = current_balance;
        this.monthly_instalment = monthly_instalment;
        this.in_cb_report = in_cb_report;
        this.is_dpd = is_dpd;
        this.days = days;
        this.start_month = start_month;
        this.end_month = end_month;
    }

    public String getCredit_guarantor() {
        return credit_guarantor;
    }

    public void setCredit_guarantor(String credit_guarantor) {
        this.credit_guarantor = credit_guarantor;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
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

    public String getCurrent_balance() {
        return current_balance;
    }

    public void setCurrent_balance(String current_balance) {
        this.current_balance = current_balance;
    }

    public String getMonthly_instalment() {
        return monthly_instalment;
    }

    public void setMonthly_instalment(String monthly_instalment) {
        this.monthly_instalment = monthly_instalment;
    }

    public String getIn_cb_report() {
        return in_cb_report;
    }

    public void setIn_cb_report(String in_cb_report) {
        this.in_cb_report = in_cb_report;
    }

    public String getIs_dpd() {
        return is_dpd;
    }

    public void setIs_dpd(String is_dpd) {
        this.is_dpd = is_dpd;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getStart_month() {
        return start_month;
    }

    public void setStart_month(String start_month) {
        this.start_month = start_month;
    }

    public String getEnd_month() {
        return end_month;
    }

    public void setEnd_month(String end_month) {
        this.end_month = end_month;
    }
}
