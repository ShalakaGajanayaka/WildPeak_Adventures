/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class bookingItem {

    private String eid;
    private String eventname;
    private String qty;
    private String price;
    private String offer;
    private String date;


    public String getEid() {
        return eid;
    }


    public void setEid(String eid) {
        this.eid = eid;
    }


    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

 
    public String getQty() {
        return qty;
    }

 
    public void setQty(String qty) {
        this.qty = qty;
    }


    public String getPrice() {
        return price;
    }


    public void setPrice(String price) {
        this.price = price;
    }


    public String getOffer() {
        return offer;
    }

 
    public void setOffer(String offer) {
        this.offer = offer;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

}
