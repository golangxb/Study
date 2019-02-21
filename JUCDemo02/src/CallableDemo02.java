import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * Create with IntelliJ IDEA。
 * User : Lyhang
 * Data : 2019-02-21
 * Time : 20:36
 **/
public class CallableDemo02 {
    public static void main(String[] args) throws Exception{

        FutureTask<Integer> ft  = new FutureTask<Integer>(()->{
            TimeUnit.SECONDS.sleep(3);
            return 1024;
        });
        new Thread(ft,"A").start();
        //阻塞方法
        //System.out.println(ft.get());
        //轮询方法
        while (!ft.isDone()){
            System.out.println("wait...");
        }
        System.out.println("打印出来");




    }


}
