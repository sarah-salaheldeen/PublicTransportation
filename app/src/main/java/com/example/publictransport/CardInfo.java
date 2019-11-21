package com.example.publictransport;

public class CardInfo {

    private String mEstimatedTime;
    private String mNumberOfStations;
    private String mCost;
    private String mRoute;

    public CardInfo(String estimatedTime, String numberOfStations, String cost, String route){
        mEstimatedTime = estimatedTime;
        mNumberOfStations = numberOfStations;
        mCost = cost;
        mRoute = route;
    }

    public String getmEstimatedTime(){
        return mEstimatedTime;
    }

    public String getmNumberOfStations(){
        return mNumberOfStations;
    }

    public String getmCost(){
        return mCost;
    }

    public String getmRoute(){
        return mRoute;
    }

}
