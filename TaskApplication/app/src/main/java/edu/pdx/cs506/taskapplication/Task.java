package edu.pdx.cs506.taskapplication;

import java.util.Date;
import java.util.HashMap;

public class Task {

    private String title;
    private String detail;
    private String location;
    private Date date;

    public Task(){
        this.title = new String();
        this.detail = new String();
        this.location = new String();
        this.date = null;
    }

    public Task(String title, String detail, String location, Date date){
        this.title = title;
        this.detail = detail;
        this.location = location;
        this.date = date;
    }

    public void setTitle(String title){
        this.title = title;
    }


    public void setDetail(String detail){
        this.detail = detail;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDetail() {
        return detail;
    }

    public String getLocation() {
        return location;
    }

    public Date getDate() {
        return date;
    }

    public String printAll(){
        return "You have an event " + title + " on " + date + " at " + location + "\n" +
                "More information here: " + detail;
    }

    public String prettyPrint(){
        String s = "****************** " + title + " *******************" +
                "\nDate & Time: " + date +
                "\nLocation: " + location +
                "\nDetail: " + detail + "\n";
        return s;
    }

}
