package volatile_test;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        new Thread(() -> {
            while(threadDemo.isFlag());
            System.out.println(Thread.currentThread().getName()+"结束");
        },"t1").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                threadDemo.setFlag(false);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        },"t2").start();

    }
}
