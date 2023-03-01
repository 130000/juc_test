package cocurrent_hash_map_test;

import java.util.concurrent.CopyOnWriteArrayList;
/*
       Java 5.0 在 java.util.concurrent 包中提供了多种并发容器类来改进同步容器的性能。
       ConcurrentHashMap 同步容器类是Java 5 增加的一个线程安全的哈希表。对与多线程的操作，介于 HashMap 与 Hashtable 之间。
       内部采用“锁分段”机制替代 Hashtable 的独占锁。进而提高性能。此包还提供了设计用于多线程上下文中的 Collection
       实现：ConcurrentHashMap、ConcurrentSkipListMap、ConcurrentSkipListSet、CopyOnWriteArrayList 和CopyOnWriteArraySet。
       当期望许多线程访问一个给定 collection 时，ConcurrentHashMap 通常优于同步的 HashMap，ConcurrentSkipListMap
       通常优于同步的 TreeMap。当期望的读数和遍历远远大于列表的更新数时，CopyOnWriteArrayList 优于同步的 ArrayList。
*/
public class Main {
    public static void main(String[] args) {

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("AA");
        list.add("BB");
        list.add("CC");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (String s : list) {
                    System.out.println(s);
                    list.add("AA");
                }
            }).start();
        }
    }
}
