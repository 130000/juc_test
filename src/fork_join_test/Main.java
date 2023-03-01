package fork_join_test;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

/**
 * 采用 “工作窃取”模式（work-stealing）：
 * 当执行新的任务时它可以将其拆分分成更小的任务执行，并将小任务加到线程队列中，然后再从一个随机线程的队列中偷一个并把它放在自己的队列中。
 * 相对于一般的线程池实现，fork/join框架的优势体现在对其中包含的任务的处理方式上.
 * 在一般的线程池中，如果一个线程正在执行的任务由于某些原因无法继续运行，那么该线程会处于等待状态。
 * 而在fork/join框架实现中，如果某个子问题由于等待另外一个子问题的完成而无法继续运行。
 * 那么处理该子问题的线程会主动寻找其他尚未运行的子问题来执行.这种方式减少了线程的等待时间，提高了性能。
 * 参考：<a href="https://blog.csdn.net/weixin_42039228/article/details/123206215"></a>
 */
public class Main {
    public static void main(String[] args) {
        Instant start = Instant.now();
        var pool = new ForkJoinPool();
        var task = new ForkJoinDemo(0L, 50000000000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);
        Instant last = Instant.now();
        System.out.println("耗费时间："+ Duration.between(start,last).toMillis());//耗费时间：1089
        //-------------------------------------------------------------------------
        start = Instant.now();
        sum = LongStream.rangeClosed(0L,50000000000L).parallel().reduce(0L,Long::sum);
        System.out.println(sum);
        last = Instant.now();
        System.out.println("并行流耗费时间："+ Duration.between(start,last).toMillis());//并行流耗费时间：1189
        //--------------------------------------------------------------------------
        start = Instant.now();
        sum = LongStream.rangeClosed(0L,50000000000L).sequential().reduce(0L,Long::sum);
        System.out.println(sum);
        last = Instant.now();
        System.out.println("顺序流耗费时间："+ Duration.between(start,last).toMillis());//顺序流耗费时间：14242
    }
}
