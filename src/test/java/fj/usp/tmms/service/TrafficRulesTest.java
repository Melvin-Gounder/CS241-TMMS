package fj.usp.tmms.service;
import fj.usp.tmms.model.CongestionLevel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TrafficRulesTest{
 @Test void redForVeryLowSpeed(){assertEquals(CongestionLevel.RED,TrafficRules.classify(9,20));}
 @Test void redForHighOccupancy(){assertEquals(CongestionLevel.RED,TrafficRules.classify(50,75));}
 @Test void yellowForModerateSpeed(){assertEquals(CongestionLevel.YELLOW,TrafficRules.classify(29,20));}
 @Test void yellowForModerateOccupancy(){assertEquals(CongestionLevel.YELLOW,TrafficRules.classify(50,45));}
 @Test void greenOtherwise(){assertEquals(CongestionLevel.GREEN,TrafficRules.classify(50,20));}
 @Test void greenTimeHasLowerBound(){assertEquals(15,TrafficRules.greenSecondsForQueue(-5));}
 @Test void greenTimeUsesQueue(){assertEquals(35,TrafficRules.greenSecondsForQueue(10));}
 @Test void greenTimeHasUpperBound(){assertEquals(60,TrafficRules.greenSecondsForQueue(100));}
}
