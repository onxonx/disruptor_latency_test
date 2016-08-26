package aa;

import com.lmax.disruptor.EventPoller;

class ConsumerThread extends Thread implements EventPoller.Handler<DisruptorQueue.Cell> {
    private final DisruptorQueue queue_;

    private long nextReportTime_;
    private long min_;
    private long max_;
    private long sum_;
    private int count_;


    ConsumerThread(DisruptorQueue queue) {
        super("ConsumerThread");
        queue_ = queue;
    }
        
    @Override
    public void run() {
        Utils.setCurrentThreadAffinity(Main.CONSUMER_THREAD_AFFINITY_MASK);
        while (true) {
            act();
        }
    }

    private void act() {
        queue_.dispatchEvent(this);
    }

    @Override
    public boolean onEvent(DisruptorQueue.Cell cell, long sequence, boolean endOfBatch) throws Exception {
        long now = Utils.now();
        long diff = now - cell.getTime();

        if (isTimeForCollect(now)) {
            updateStats(diff);

            if (isTimeToReport(now))
                reportStats(now);
        }

        return false;
    }

    private void updateStats(long diff) {
        if (min_ == 0 || diff < min_)
            min_ = diff;

        if (max_ == 0 || diff > max_)
            max_ = diff;

        sum_ += diff;
        ++count_;
    }

    private void reportStats(long now) {

        System.out.printf(
                "%,d [%s]: count=%,d, min=%,d ns, avg=%,d ns, max=%,d ns\n",
                System.currentTimeMillis(),
                Thread.currentThread().getName(),
                count_,
                min_,
                sum_/count_,
                max_);
        min_ = max_ = sum_ = count_ = 0;
        nextReportTime_ = now + Main.CONSUMER_REPORT_INTERVAL;
    }

    private boolean isTimeForCollect(long now) {
        return nextReportTime_ - Main.CONSUMER_STATS_INTERVAL <= now;
    }

    private boolean isTimeToReport(long now) {
        return nextReportTime_ <= now;
    }
}
