class TankAngle{
    int getAngleTheta(int radian){
        return 3*radian;
    }
    int getAngleRadian(int theta){
        return (100*theta)+1;
    }
}

class getCoordinates{
    int getcoordinates(String tank) {
        if (tank.equals("tank1")) {
            return 28;
        }
        else{
            return 16;
        }
    }
}
