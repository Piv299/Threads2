import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static java.lang.Thread.sleep;


public class Consumers implements Runnable {
    private static ExecutorService consExec;
    private DataQueue queue;

    public Consumers(DataQueue queue){
        consExec = Executors.newFixedThreadPool(2);
        this.queue = queue;
    }

    public void getElements(){

        while(true) {
            if(!queue.working)
                consExec.submit(() -> {
                    synchronized (queue) {
                        try{Thread.sleep(50);}
                        catch (InterruptedException e){}
                        queue.get();}
                    System.out.println("Consumer "+Thread.currentThread().getName()+" Queue elements size is: "
                            + queue.getSize());
                });

            try{sleep(100);}
            catch (InterruptedException e){};

        }


    }

    @Override
    public void run() {
        getElements();
    }

}
