import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class getCoordinatesTest {
    @Test
    void test1(){
        getCoordinates x = new getCoordinates();
        int t = x.getcoordinates("tank1");
        assertEquals(28 , t);
    }
    @Test
    void test2(){
        getCoordinates x = new getCoordinates();
        int t = x.getcoordinates("tank2");
        assertEquals(16 , t);
    }
}