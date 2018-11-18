package org.sopt.kclean.Model;

public class Trade {
   final public static int TRADE_TYPE = 0;
   final public static int DATE_TYPE =1;

    private  String  TradeLocation;
    private  String  time;
    private  String  money;
    private  int     isIncoming;

    private String  imageUri;


    private  String month;
    private  String day;
    private String date;

    private  int type;


    public Trade(String TradeLocation, String time, String money, int isIncoming, String month, String imageUri )
    {
        this.type = TRADE_TYPE;
        this.time = time;
        this.money = money;
        this.isIncoming = isIncoming;
        this.month = month;
        this.imageUri = imageUri;
    }
    public Trade( String month, String date ,String day) {
        this.type = DATE_TYPE;
        this.month = month;
        this.date = date;
        this.day = day;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTradeLocation() {
        return TradeLocation;
    }

    public void setTradeLocation(String tradeLocation) {
        TradeLocation = tradeLocation;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getIsIncoming() {
        return isIncoming;
    }

    public void setIsIncoming(int isIncoming) {
        this.isIncoming = isIncoming;
    }
}
