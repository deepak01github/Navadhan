package com.navadhan.model;

public class FamilyMemberData {
    public String family_member_id;
    public String family_member_name;
    public String family_relation_id;
    public String gender;
    public String mobile_no;
    public String aadhaar_no;
    public String pan;
    public String voter_id;
    public String family_member_number;
    public String date_of_birth;
    public String is_aadhaar_verified;
    public String is_mobile_aadhaar_linked;

    public FamilyMemberData(String family_member_id, String family_member_name, String family_relation_id, String gender, String mobile_no, String aadhaar_no, String pan, String voter_id, String family_member_number, String date_of_birth, String is_aadhaar_verified, String is_mobile_aadhaar_linked) {
        this.family_member_id = family_member_id;
        this.family_member_name = family_member_name;
        this.family_relation_id = family_relation_id;
        this.gender = gender;
        this.mobile_no = mobile_no;
        this.aadhaar_no = aadhaar_no;
        this.pan = pan;
        this.voter_id = voter_id;
        this.family_member_number = family_member_number;
        this.date_of_birth = date_of_birth;
        this.is_aadhaar_verified = is_aadhaar_verified;
        this.is_mobile_aadhaar_linked = is_mobile_aadhaar_linked;
    }

    public String getFamily_member_id() {
        return family_member_id;
    }

    public void setFamily_member_id(String family_member_id) {
        this.family_member_id = family_member_id;
    }

    public String getFamily_member_name() {
        return family_member_name;
    }

    public void setFamily_member_name(String family_member_name) {
        this.family_member_name = family_member_name;
    }

    public String getFamily_relation_id() {
        return family_relation_id;
    }

    public void setFamily_relation_id(String family_relation_id) {
        this.family_relation_id = family_relation_id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getAadhaar_no() {
        return aadhaar_no;
    }

    public void setAadhaar_no(String aadhaar_no) {
        this.aadhaar_no = aadhaar_no;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public String getVoter_id() {
        return voter_id;
    }

    public void setVoter_id(String voter_id) {
        this.voter_id = voter_id;
    }

    public String getFamily_member_number() {
        return family_member_number;
    }

    public void setFamily_member_number(String family_member_number) {
        this.family_member_number = family_member_number;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getIs_aadhaar_verified() {
        return is_aadhaar_verified;
    }

    public void setIs_aadhaar_verified(String is_aadhaar_verified) {
        this.is_aadhaar_verified = is_aadhaar_verified;
    }

    public String getIs_mobile_aadhaar_linked() {
        return is_mobile_aadhaar_linked;
    }

    public void setIs_mobile_aadhaar_linked(String is_mobile_aadhaar_linked) {
        this.is_mobile_aadhaar_linked = is_mobile_aadhaar_linked;
    }
}
