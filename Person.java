package ca.concordia.coen346.fridgethreads;
import java.lang.Thread;
public class Person extends Thread{

    private Fridge fridge;
    private String name;
    

    private static boolean buyingMilk = true;
    public Person(Fridge fridge, String name){
        this.name = name;
        this.fridge = fridge;
        

    }

    public synchronized void buyMilk() throws Exception{
        
            if (!this.fridge.gotMilk()) {
                System.out.println(name + " buying milk...");
                this.fridge.putMilk();
                System.out.println(name + " bought milk...");
            }
        
    }

    public synchronized void run(){
        
        while(buyingMilk){

            if(this.fridge.gotMilk()){
                System.out.println(name + " drinking milk...");
                this.fridge.drinkMilk();
                System.out.println(name + " drank milk...");
            }
            this.fridge.milLock.lock();
            try {
                buyMilk();
            } catch (Exception e) {
                buyingMilk = false;
                System.out.println("Too much milk!");
            }
            this.fridge.milLock.unlock();
        }
    }
}
