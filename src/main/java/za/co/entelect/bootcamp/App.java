package za.co.entelect.bootcamp;

public class App 
{
    public static final double CURRENT_RATE = 22.07;

    public float convertRandToDollar(double value){
        return value * CURRENT_RATE / 100 ;
    }

}
