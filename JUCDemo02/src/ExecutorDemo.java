import java.util.concurrent.*;

/**
 * Create with IntelliJ IDEA。
 * User : Lyhang
 * Data : 2019-02-21
 * Time : 20:47
 **/
public class ExecutorDemo {
    public static void main(String[] args) {
        //线程池中的线程执行任务
        //ExecutorService es = Executors.newFixedThreadPool(5);
        //一个线程执行任务
        //ExecutorService es = Executors.newSingleThreadExecutor();
        //可扩容的线程池
        //ExecutorService es = Executors.newCachedThreadPool();
        //自定义线程池~
        ThreadPoolExecutor es = new ThreadPoolExecutor(
                1,5,1L,
                TimeUnit.SECONDS,new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy() );
        //AbortPolicy()   阻塞队列装不下，直接抛异常
        //CallerRunsPolicy() 阻塞队列装不下，把它抛给调用它来的线程去操作
        //DiscardOldestPolicy() 阻塞时间最长的，直接拒绝
        //DiscardPolicy() 线程干不了的直接抛弃掉
        for (int i = 0; i <10 ; i++) {
            es.execute(()->{
                System.out.println(Thread.currentThread().getName()+"执行任务");
            });

        }

        es.shutdown();

    }
}
