package volatile_test;

import java.util.concurrent.TimeUnit;
/**
 * 内存可见性 、volatile关键字
 * 1. 内存可见性
 * 内存可见性（Memory Visibility）是指当某个线程正在使用对象状态而另一个线程在同时修改该状态，需要确保当一个线程修改了对象状态后，其他线程能够看到发生的状态变化。
 * 可见性错误是指当读操作与写操作在不同的线程中执行时，我们无法确保执行读操作的线程能适时地看到其他线程写入的值，有时甚至是根本不可能的事情。
 * 我们可以通过同步来保证对象被安全地发布。除此之外我们也可以使用一种更加轻量级的 volatile变量。
 * 2. volatile 关键字
 * Java 提供了一种稍弱的同步机制，即 volatile 变量，用来确保将变量的更新操作通知到其他线程。可以将 volatile 看做一个轻量级的锁，但是又与锁有些不同：
 * <p>
 * 对于多线程，不是一种互斥关系
 * 不能保证变量状态的“原子性操作”
 */
public class ThreadDemo{
    private volatile boolean flag = true;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
        System.out.println("flag is "+flag);
    }
}
