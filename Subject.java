package com.project.pop.attendancemanagement;

/**
 * Created by Aniket on 4/12/2017.
 */

public class Subject {

    private String id;
    private String title;
    private int attended;
    private int missed;

    public Subject() {
    }

    public Subject(String id, String title,int attended,int missed) {
        this.id = id;
        this.title = title;
        this.attended = attended;
        this.missed = missed ;
    }

    public String getid() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getAttended() {
        return attended;
    }

    public int getMissed() {
        return missed;
    }


    public void setid(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAttended(int attended) {
        this.attended = attended;
    }

    public void setMissed(int missed) {
        this.missed = missed;
    }


}
