package lock_condition_test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 2. Condition 控制线程通信
 * Condition 接口描述了可能会与锁有关联的条件变量。这些变量在用法上与使用 Object.wait 访问的隐式监视器类似，
 * 但提供了更强大的功能。需要特别指出的是，单个 Lock 可能与多个 Condition 对象关联。为
 * 了避免兼容性问题，Condition 方法的名称与对应的 Object 版本中的不同。
 * 在 Condition 对象中，与 wait、notify 和 notifyAll 方法对应的分别是await、signal 和 signalAll。
 * Condition 实例实质上被绑定到一个锁上。要为特定 Lock 实例获得Condition 实例，请使用其 newCondition() 方法。
 * 3. 线程按序交替
 * Lock和Condition结合应用以实现线程按序交替。

 */
public class LockConditionDemo {
    private int num = 1;
    private final Lock lock = new ReentrantLock();
    private final Condition condition1 = lock.newCondition();
    private final Condition condition2 = lock.newCondition();
    private final Condition condition3 = lock.newCondition();
    public void loopA(){
        lock.lock();
        try{
            if (num != 1)
                condition1.await();
            System.out.println(Thread.currentThread().getName());
            num = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }
    public void loopB(){
        lock.lock();
        try{
            if (num != 2)
                condition2.await();
            System.out.println(Thread.currentThread().getName());
            num = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }
    public void loopC(){
        lock.lock();
        try{
            if (num != 3)
                condition3.await();
            System.out.println(Thread.currentThread().getName());
            num = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }
}
