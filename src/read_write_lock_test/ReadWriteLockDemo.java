package read_write_lock_test;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    private int num = 0;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    public void get(){
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"----> "+ num);
        lock.readLock().unlock();
    }
    public void set(int num){
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"----> "+num);
        this.num = num;
        lock.writeLock().unlock();
    }
}
