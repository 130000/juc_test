package lock_condition_test;


public class Main {
    public static void main(String[] args) {
        LockConditionDemo lockConditionDemo = new LockConditionDemo();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lockConditionDemo.loopA();
            }
        },"t1").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lockConditionDemo.loopB();
            }
        },"t2").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                lockConditionDemo.loopC();
            }
        },"t3").start();
    }
}
