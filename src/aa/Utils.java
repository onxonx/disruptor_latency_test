package aa;

import com.sun.jna.LastErrorException;
import com.sun.jna.Library;
import com.sun.jna.Native;

import java.util.Arrays;

class Utils {
    static final long NANOSECOND = 1L;
    static final long MICROSECOND = 1_000L * NANOSECOND;
    static final long MILLISECOND = 1_000L * MICROSECOND;
    static final long SECOND = 1_000L * MILLISECOND;

    private static final LibC LibC_ = (LibC) Native.loadLibrary("c", LibC.class);
    private static final int PID_CURRENT_THREAD = 0;
    private static final int CPUSET_SIZE_BITS = 1024;
    private static final int CPUSET_SIZE_BYTES = CPUSET_SIZE_BITS / 8;
    private static final int CPUSET_SIZE_LONGS = CPUSET_SIZE_BITS / 64;

    interface LibC extends Library
    {
        int sched_setaffinity(int pid, int cpusetsize, long[] cpuset) throws LastErrorException;
    }

    static long now() {
        return System.nanoTime();
    }

    static void setCurrentThreadAffinity(long mask) {
        long[] cpuset = new long[CPUSET_SIZE_LONGS];
        try
        {
            Arrays.fill(cpuset, 0);
            cpuset[0] = mask;
            LibC_.sched_setaffinity(PID_CURRENT_THREAD, CPUSET_SIZE_BYTES, cpuset);
        }
        catch (LastErrorException e)
        {
            throw new IllegalArgumentException(
                    "Check that mask is appropriate for this host and CPU. mask=0b" + Long.toBinaryString(mask), e);
        }
    }
}
