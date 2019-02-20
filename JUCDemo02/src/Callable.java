import com.sun.org.apache.xalan.internal.utils.FeatureManager;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class Demo implements java.util.concurrent.Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        System.out.println("我是新的创建线程的方式");
        return 123;
    }

}

public class Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Demo());
        new Thread(futureTask,"A").start();
        System.out.println(futureTask.get());
    }

}
