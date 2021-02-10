package com.navadhan.model;

public class SummaryData {
    public String report_pulling_date;
    public String applicant_name;
    public String is_ntc;
    public String score_value;
    public String number_of_account;
    public String number_of_account_overdue;
    public String number_of_account_active;
    public String number_of_account_active_overdue;
    public String number_of_account_with_alert;
    public String number_of_total_alert;
    public String inquiries_24_months;
    public String inquiries_3_months;
    public String new_account_24_months;
    public String new_account_3_months;
    public String new_account_amount_3_months;

    public SummaryData(String report_pulling_date, String applicant_name, String is_ntc, String score_value, String number_of_account, String number_of_account_overdue,
                       String number_of_account_active, String number_of_account_active_overdue,
                       String number_of_account_with_alert, String number_of_total_alert,
                       String inquiries_24_months, String inquiries_3_months, String new_account_24_months,
                       String new_account_3_months, String new_account_amount_3_months) {
        this.report_pulling_date = report_pulling_date;
        this.applicant_name = applicant_name;
        this.is_ntc = is_ntc;
        this.score_value = score_value;
        this.number_of_account = number_of_account;
        this.number_of_account_overdue = number_of_account_overdue;
        this.number_of_account_active = number_of_account_active;
        this.number_of_account_active_overdue = number_of_account_active_overdue;
        this.number_of_account_with_alert = number_of_account_with_alert;
        this.number_of_total_alert = number_of_total_alert;
        this.inquiries_24_months = inquiries_24_months;
        this.inquiries_3_months = inquiries_3_months;
        this.new_account_24_months = new_account_24_months;
        this.new_account_3_months = new_account_3_months;
        this.new_account_amount_3_months = new_account_amount_3_months;
    }

    public String getReport_pulling_date() {
        return report_pulling_date;
    }

    public void setReport_pulling_date(String report_pulling_date) {
        this.report_pulling_date = report_pulling_date;
    }

    public String getApplicant_name() {
        return applicant_name;
    }

    public void setApplicant_name(String applicant_name) {
        this.applicant_name = applicant_name;
    }

    public String getIs_ntc() {
        return is_ntc;
    }

    public void setIs_ntc(String is_ntc) {
        this.is_ntc = is_ntc;
    }

    public String getScore_value() {
        return score_value;
    }

    public void setScore_value(String score_value) {
        this.score_value = score_value;
    }

    public String getNumber_of_account() {
        return number_of_account;
    }

    public void setNumber_of_account(String number_of_account) {
        this.number_of_account = number_of_account;
    }

    public String getNumber_of_account_overdue() {
        return number_of_account_overdue;
    }

    public void setNumber_of_account_overdue(String number_of_account_overdue) {
        this.number_of_account_overdue = number_of_account_overdue;
    }

    public String getNumber_of_account_active() {
        return number_of_account_active;
    }

    public void setNumber_of_account_active(String number_of_account_active) {
        this.number_of_account_active = number_of_account_active;
    }

    public String getNumber_of_account_active_overdue() {
        return number_of_account_active_overdue;
    }

    public void setNumber_of_account_active_overdue(String number_of_account_active_overdue) {
        this.number_of_account_active_overdue = number_of_account_active_overdue;
    }

    public String getNumber_of_account_with_alert() {
        return number_of_account_with_alert;
    }

    public void setNumber_of_account_with_alert(String number_of_account_with_alert) {
        this.number_of_account_with_alert = number_of_account_with_alert;
    }

    public String getNumber_of_total_alert() {
        return number_of_total_alert;
    }

    public void setNumber_of_total_alert(String number_of_total_alert) {
        this.number_of_total_alert = number_of_total_alert;
    }

    public String getInquiries_24_months() {
        return inquiries_24_months;
    }

    public void setInquiries_24_months(String inquiries_24_months) {
        this.inquiries_24_months = inquiries_24_months;
    }

    public String getInquiries_3_months() {
        return inquiries_3_months;
    }

    public void setInquiries_3_months(String inquiries_3_months) {
        this.inquiries_3_months = inquiries_3_months;
    }

    public String getNew_account_24_months() {
        return new_account_24_months;
    }

    public void setNew_account_24_months(String new_account_24_months) {
        this.new_account_24_months = new_account_24_months;
    }

    public String getNew_account_3_months() {
        return new_account_3_months;
    }

    public void setNew_account_3_months(String new_account_3_months) {
        this.new_account_3_months = new_account_3_months;
    }

    public String getNew_account_amount_3_months() {
        return new_account_amount_3_months;
    }

    public void setNew_account_amount_3_months(String new_account_amount_3_months) {
        this.new_account_amount_3_months = new_account_amount_3_months;
    }
}
