package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTest {

    @Test
    public void testDistance(){
        Point p1 = new Point(1,1);
        Point p2 = new Point(1, 1);

        double distance;
        double x = p2.x - p1.x;
        double y = p2.y - p1.y;
        distance = Math.sqrt(x*x - y*y);

        Assert.assertEquals(p1.distance(p2), distance);
    }
}
