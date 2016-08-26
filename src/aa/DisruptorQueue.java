package aa;

import com.lmax.disruptor.*;

class DisruptorQueue {
    private final RingBuffer<Cell> ringBuffer_;
    private final EventPoller<Cell> poller_;

    DisruptorQueue(final int queueSizePowerOfTwo)
    {
        ringBuffer_ = RingBuffer.createMultiProducer(
                Cell::new,
                queueSizePowerOfTwo,
                createStrategy());

        poller_ = ringBuffer_.newPoller();
        ringBuffer_.addGatingSequences(poller_.getSequence());
    }

    private static WaitStrategy createStrategy()
    {
        //return new BusySpinWaitStrategy();
        return new YieldingWaitStrategy();
    }

    void postEvent(long time) {
        long next = ringBuffer_.next();
        Cell cell = ringBuffer_.get(next);
        cell.setTime(time);
        ringBuffer_.publish(next);
    }

    void dispatchEvent(EventPoller.Handler<DisruptorQueue.Cell> handler) {
        try
        {
            poller_.poll(handler);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    static class Cell {
        private long time_;

        void setTime(long time) {
            time_ = time;
        }

        long getTime() {
            return time_;
        }
    }
}
