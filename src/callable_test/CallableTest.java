package callable_test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Java 5.0 在 java.util.concurrent 提供了一个新的创建执行线程的方式：Callable 接口
 * Callable 接口类似于 Runnable，两者都是为那些其实例可能被另一个线程执行的类设计的。但是 Runnable 不会返回结果，并且无法抛出经过检查的异常。
 * Callable 需要依赖FutureTask ，FutureTask 也可以用作闭锁。
 */
public class CallableTest {
    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(() ->{
            int sum = 0;
            for (int i = 0; i < 100; i++) {
                sum += i;
            }
            return sum;
        });
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
    }
}
