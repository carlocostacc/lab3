package ca.concordia.coen346.fridgethreads;
import java.util.concurrent.locks.*;
public class Fridge {

    private boolean milk;
    public ReentrantLock milLock;
    public Fridge(){
        this.milk = false;
        this.milLock = new ReentrantLock();
    }

    public boolean gotMilk(){
        return milk;
    }

    public void drinkMilk(){
        if(this.milk)
            this.milk = false;
    }

    public void putMilk() throws Exception{
        if(gotMilk()){
            throw new Exception(" Too much milk!");
        }
        this.milk = true;
    }


}
