package fj.usp.tmms.service;

import fj.usp.tmms.model.CongestionLevel;

public final class TrafficRules {
    private TrafficRules(){}
    public static CongestionLevel classify(double speedKmh,double occupancyPercent){
        if(speedKmh < 10 || occupancyPercent >= 75) return CongestionLevel.RED;
        if(speedKmh < 30 || occupancyPercent >= 45) return CongestionLevel.YELLOW;
        return CongestionLevel.GREEN;
    }
    public static int greenSecondsForQueue(int queueLength){
        int value=15 + Math.max(0,queueLength)*2;
        return Math.max(15,Math.min(60,value));
    }
}
