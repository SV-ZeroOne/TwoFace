package za.co.entelect.bootcamp.twoface.squareeyes.logging;

/**
 * Created by quinton.weenink on 2017/01/20.
 */
public class IssueNotFoundException extends Exception {
    @Override
    public String toString(){
        return "File is not found!";
    }
}
