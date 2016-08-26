package aa;

class PublisherThread extends Thread {
    private final DisruptorQueue queue_;
    private long nextEventTime_;
    private long nextBurstTime_;

    PublisherThread(DisruptorQueue queue) {
        super("PublisherThread");
        queue_ = queue;
    }
        
    @Override
    public void run() {
        Utils.setCurrentThreadAffinity(Main.PUBLISHER_THREAD_AFFINITY_MASK);
        while (true) {
            act();
        }
    }

    private void act() {
        long now = Utils.now();
        if (!isTimeForNextEvent(now))
            return;

        if (isTimeToBurst(now))
            burst();
        else
            postSingleEvent();
    }

    private void burst() {
        for (int i = 0; i < Main.PUBLISHER_EVENTS_IN_BURST; i++)
            postSingleEvent();
        nextBurstTime_ = Utils.now() + Main.PUBLISHER_BURST_INTERVAL;
    }

    private boolean isTimeToBurst(long now) {
        return nextBurstTime_ <= now;
    }

    private boolean isTimeForNextEvent(long now) {
        return nextEventTime_ <= now;
    }

    private void postSingleEvent() {
        long now = Utils.now();
        queue_.postEvent(Utils.now());
        nextEventTime_ = now + Main.PUBLISHER_EVENT_INTERVAL;
    }
}
