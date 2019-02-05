package com.ahujafabrics.yarnit.Repository;

public class UserProfile {
    private String userName;
    private String address1;
    private String address2;
    private String city;
    private long phone;
    private String email;
    private String role;


    public UserProfile(String userName, String address1, String address2, String city,
                       long phone, String email, String role) {
        this.setUserName(userName);
        this.setAddress1(address1);
        this.setAddress2(address2);
        this.setCity(city);
        this.setPhone(phone);
        this.setEmail(email);
        this.setRole(role);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
