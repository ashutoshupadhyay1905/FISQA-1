package main.apiUI.model;

public class CoinDesk {

    public Time time;
    public String disclaimer;
    public String chartName;
    public BPI bpi;

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
