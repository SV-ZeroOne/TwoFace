package za.co.entelect.bootcamp.twoface.squareeyes.app;

public class App 
{
    public static final double CURRENT_RATE = 22.07;

    public static double convertRandToDollar(double value){
        return value / CURRENT_RATE;
    }

}
