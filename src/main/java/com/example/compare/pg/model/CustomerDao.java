package com.example.compare.pg.model;

public class CustomerDao {

    private String firstname;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    String lastname;
    private String emailAddress;

    public CustomerDao(){}
    public CustomerDao(String firstname, String lastname, String emailAddress) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
    }

    public CustomerDao(Customer customer){
        this.firstname = customer.getFirstname();
        this.lastname = customer.getLastname();
        this.emailAddress = customer.getEmailAddress();
    }

    public CustomerDao(Object o) {
        if(o instanceof Customer )
        {
            Customer customer = (Customer) o;

            this.firstname = customer.getFirstname();
            this.lastname = customer.getLastname();
            this.emailAddress = customer.getEmailAddress();
        }
    }
}
