/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.administrator.customerManagement;

/**
 *
 * @author shalaka
 */
public class Customer {

    private String fname;
    private String lname;
    private String email;
    private String mobile;
    private String age;
    private String register_date;
    private String gender;
    private String customer_type;

    public Customer(String fname, String lname, String email, String mobile, String age, String register_date, String gender, String customer_type) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.mobile = mobile;
        this.age = age;
        this.register_date = register_date;
        this.gender = gender;
        this.customer_type = customer_type;
    }

    /**
     * @return the fname
     */
    public String getFname() {
        return fname;
    }

    /**
     * @param fname the fname to set
     */
    public void setFname(String fname) {
        this.fname = fname;
    }

    /**
     * @return the lname
     */
    public String getLname() {
        return lname;
    }

    /**
     * @param lname the lname to set
     */
    public void setLname(String lname) {
        this.lname = lname;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the age
     */
    public String getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     * @return the register_date
     */
    public String getRegister_date() {
        return register_date;
    }

    /**
     * @param register_date the register_date to set
     */
    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the customer_type
     */
    public String getCustomer_type() {
        return customer_type;
    }

    /**
     * @param customer_type the customer_type to set
     */
    public void setCustomer_type(String customer_type) {
        this.customer_type = customer_type;
    }
}
