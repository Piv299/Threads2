import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class Producers implements Runnable {
    private Random random;
    private DataQueue queue;
    private ExecutorService prodExec;

    public Producers(DataQueue queue){
        prodExec = Executors.newFixedThreadPool(3);
        random = new Random();
        this.queue = queue;
    }

    public void addElements(){

        while(true){
            if(queue.working)
                prodExec.submit(() -> {
                    synchronized (queue){
                        try{
                            sleep(50);}
                        catch (InterruptedException e){}
                        queue.add(random.nextInt() % 100 + 1);}
                    System.out.println("Producer "+Thread.currentThread().getName()+" Queue elements size is: "
                            + queue.getSize());
                });

            try{sleep(100);}
            catch (InterruptedException e){};
        }
    }

    @Override
    public void run() {
        addElements();
    }

}