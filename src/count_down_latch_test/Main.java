package count_down_latch_test;

import java.util.concurrent.CountDownLatch;
/**
 * Java 5.0 在 java.util.concurrent 包中提供了多种并发容器类来改进同步容器的性能。
 * CountDownLatch 是一个同步辅助类，在完成一组正在其他线程中执行的操作之前，它允许一个或多个线程一直等待。
 * 闭锁可以延迟线程的进度直到其到达终止状态，闭锁可以用来确保某些活动直到其他活动都完成才继续执行：
 * 确保某个计算在其需要的所有资源都被初始化之后才继续执行;
 * 确保某个服务在其依赖的所有其他服务都已经启动之后才启动;
 * 等待直到某个操作所有参与者都准备就绪再继续执行。
 */
public class Main {
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(5);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (int j = 0; j < 50000; j++) {
                    if(j % 2 == 0)
                        System.out.println(j);
                }
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间：" + (end - start) + "ms");
    }
}
