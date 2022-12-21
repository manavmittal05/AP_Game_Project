package com.mygdx.game;
import com.mygdx.game.Hill;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TankAngleTest {
    @Test
    void Test1(){
        TankAngle y = new TankAngle();
        int t = y.getAngleTheta(3);
        assertEquals(9,t);
    }
    @Test
    void Test2() {
        TankAngle y = new TankAngle();
        int t = y.getAngleTheta(4);
        assertEquals(12, t);
    }
    @Test
    void Test3() {
        TankAngle y = new TankAngle();
        int t = y.getAngleTheta(5);
        assertEquals(15, t);
    }
    @Test
    void Test4() {
        TankAngle y = new TankAngle();
        int t = y.getAngleRadian(5);
        assertEquals(501, t);
    }
    @Test
    void Test5() {
        TankAngle y = new TankAngle();
        int t = y.getAngleRadian(5);
        assertEquals(501, t);
    }
}