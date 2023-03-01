package fork_join_test;

import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo extends RecursiveTask<Long> {
    private final long start;
    private final long end;

    public ForkJoinDemo(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;
        long THRESHOLD = 10000L;
        if(length <= THRESHOLD){
            long sum = 0L;
            for (long i = start; i <= end ; i++) {
                sum += i;
            }
            return sum;
        } else {
            long middle = (start + end) / 2;
            //进行拆分，进入线程队列
            ForkJoinDemo left = new ForkJoinDemo(start,middle);
            left.fork();
            ForkJoinDemo right = new ForkJoinDemo(middle + 1,end);
            right.fork();
            return left.join() + right.join();
        }
    }
}
