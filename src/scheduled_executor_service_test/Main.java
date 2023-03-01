package scheduled_executor_service_test;

import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        //一个 ExecutorService，可安排在给定的延迟后运行或定期执行的命令。
        var pool = Executors.newScheduledThreadPool(5);
        for (int i = 0; i < 5; i++) {
            var result = pool.schedule(() -> {
                int num = new Random().nextInt(100);
                System.out.println(Thread.currentThread().getName() + "----> "+num);
                return num;
            },1, TimeUnit.SECONDS);
            try {
                System.out.println(result.get());
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e.getMessage());
            }
        }
        pool.shutdown();
    }
}
