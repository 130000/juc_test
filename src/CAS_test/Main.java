package CAS_test;

/**
 * CAS (Compare-And-Swap) 是一种硬件对并发的支持，针对多处理器操作而设计的处理器中的一种特殊指令，用于管理对共享数据的并发访问。
 * CAS 是一种无锁的非阻塞算法的实现。
 * CAS 包含了 3 个操作数：需要读写的内存值 V、进行比较的值 A、拟写入的新值 B
 * 当且仅当 V 的值等于 A 时，CAS 通过原子方式用新值 B 来更新 V 的值，否则不会执行任何操作。
 * 参考： <a href="https://zhuanlan.zhihu.com/p/137261781"></a>
 *
 */
public class Main {
    public static void main(String[] args) {
        CAS cas = new CAS();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                int expectedValue = cas.getValue();
                boolean b = cas.compareAndSet(expectedValue, (int)(Math.random() * 101));
                System.out.println(b);
            },"thread---->" + i).start();

        }
    }
}
