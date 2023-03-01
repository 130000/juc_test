package read_write_lock_test;

public class Main {
    public static void main(String[] args) {
        ReadWriteLockDemo readWriteLockDemo = new ReadWriteLockDemo();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                readWriteLockDemo.set((int)(Math.random() * 101));
            }
        },"writeThread").start();
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                readWriteLockDemo.get();
            }
        },"readThread").start();
    }
}
