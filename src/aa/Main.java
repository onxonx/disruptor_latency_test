package aa;

public class Main {
    static final int DISRUPTOR_QUEUE_SIZE = 0x200;

    static final long CONSUMER_THREAD_AFFINITY_MASK = 0b1000_0000_0000;
    static final long CONSUMER_REPORT_INTERVAL = 2 * Utils.SECOND;
    static final long CONSUMER_STATS_INTERVAL = Utils.SECOND;

    static final long PUBLISHER_THREAD_AFFINITY_MASK = 0b0010_0000_0000;
    static final long PUBLISHER_BURST_INTERVAL = (long)(3.4 * Utils.SECOND);
    static final long PUBLISHER_EVENTS_IN_BURST = 300;
    static final long PUBLISHER_EVENT_INTERVAL = Utils.MILLISECOND;


    public static void main(String[] args) {
        printNotice();
        startTest();
    }

    private static void startTest() {
        DisruptorQueue queue = new DisruptorQueue(DISRUPTOR_QUEUE_SIZE);
        new PublisherThread(queue).start();
        new ConsumerThread(queue).start();
    }

    private static void printNotice() {
        System.out.println("---####!!!! For better latencies make sure that these CPU cores are isolated: [");
        System.out.println("  " + Long.toBinaryString(CONSUMER_THREAD_AFFINITY_MASK) + ",");
        System.out.println("  " + Long.toBinaryString(PUBLISHER_THREAD_AFFINITY_MASK));
        System.out.println("}");
    }
}
