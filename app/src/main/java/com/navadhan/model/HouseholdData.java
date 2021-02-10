package com.navadhan.model;

import java.io.Serializable;

public class HouseholdData implements Serializable {
public String household_name;
public String family_member_name;
public String mobile_no;
public String household_number;
public String household_id;

    public HouseholdData(String household_name, String family_member_name, String mobile_no, String household_number, String household_id) {
        this.household_name = household_name;
        this.family_member_name = family_member_name;
        this.mobile_no = mobile_no;
        this.household_number = household_number;
        this.household_id = household_id;
    }

    public String getHousehold_id() {
        return household_id;
    }

    public void setHousehold_id(String household_id) {
        this.household_id = household_id;
    }

    public String getHousehold_name() {
        return household_name;
    }

    public void setHousehold_name(String household_name) {
        this.household_name = household_name;
    }

    public String getFamily_member_name() {
        return family_member_name;
    }

    public void setFamily_member_name(String family_member_name) {
        this.family_member_name = family_member_name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getHousehold_number() {
        return household_number;
    }

    public void setHousehold_number(String household_number) {
        this.household_number = household_number;
    }
}
