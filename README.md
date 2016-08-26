# disruptor_latency_test
Checking how fast events can pass between threads with help of disruptor ring buffer and poller

This test was built to support the question from https://github.com/LMAX-Exchange/disruptor/issues/171. Unfortunately or fortunately I don't observe such a big latency spikes that I see in our more complex program. I will continue searching for the difference.

OUTPUT from the latest run (08/26/2016):
<pre>
/Tools/Java/jdk1.8.0_102_x64/bin/java -verbose:gc -XX:+UnlockDiagnosticVMOptions -XX:GuaranteedSafepointInterval=300000 -XX:CompileThreshold=1 -XX:-TieredCompilation -XX:-PrintCompilation -XX:-PrintFlagsFinal -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCApplicationConcurrentTime -XX:PrintSafepointStatisticsCount=1 -XX:+PrintSafepointStatistics -XX:+UseLargePages -XX:LargePageSizeInBytes=2m -Didea.launcher.port=7534 -Didea.launcher.bin.path=/opt/idea-IC-145.1617.8/bin -Dfile.encoding=UTF-8 -classpath /Tools/Java/jdk1.8.0_102_x64/jre/lib/charsets.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/deploy.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/ext/cldrdata.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/ext/dnsns.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/ext/jaccess.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/ext/jfxrt.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/ext/localedata.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/ext/nashorn.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/ext/sunec.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/ext/sunjce_provider.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/ext/sunpkcs11.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/ext/zipfs.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/javaws.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/jce.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/jfr.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/jfxswt.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/jsse.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/management-agent.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/plugin.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/resources.jar:/Tools/Java/jdk1.8.0_102_x64/jre/lib/rt.jar:/home/sandman/alexmalko/disruptor_latency/out/production/disruptor_latency:/home/sandman/alexmalko/disruptor_latency/lib/disruptor-3.3.5.jar:/home/sandman/alexmalko/disruptor_latency/lib/jna-platform-4.0.0.jar:/home/sandman/alexmalko/disruptor_latency/lib/jna.jar:/opt/idea-IC-145.1617.8/lib/idea_rt.jar com.intellij.rt.execution.application.AppMain aa.Main
---####!!!! For better latencies make sure that these CPU cores are isolated: [
  100000000000,
  1000000000
}
Application time: 0.5010194 seconds
         vmop                    [threads: total initially_running wait_to_block]    [time: spin block sync cleanup vmop] page_trap_count
0.615: Deoptimize                       [      10          1              1    ]      [     7     0     8     0     0    ]  0   
Total time for which application threads were stopped: 0.0081256 seconds, Stopping threads took: 0.0080127 seconds
Application time: 0.0302245 seconds
         vmop                    [threads: total initially_running wait_to_block]    [time: spin block sync cleanup vmop] page_trap_count
0.653: Deoptimize                       [      10          0              0    ]      [     0     0     0     0     0    ]  0   
Total time for which application threads were stopped: 0.0000994 seconds, Stopping threads took: 0.0000190 seconds
Application time: 0.0017017 seconds
         vmop                    [threads: total initially_running wait_to_block]    [time: spin block sync cleanup vmop] page_trap_count
0.655: Deoptimize                       [      10          0              0    ]      [     0     0     0     0     0    ]  0   
Total time for which application threads were stopped: 0.0000562 seconds, Stopping threads took: 0.0000107 seconds
Application time: 0.0005263 seconds
         vmop                    [threads: total initially_running wait_to_block]    [time: spin block sync cleanup vmop] page_trap_count
0.655: Deoptimize                       [      10          1              0    ]      [     0     0     0     0     0    ]  0   
Total time for which application threads were stopped: 0.0000636 seconds, Stopping threads took: 0.0000141 seconds
Application time: 0.0631916 seconds
         vmop                    [threads: total initially_running wait_to_block]    [time: spin block sync cleanup vmop] page_trap_count
0.719: Deoptimize                       [      10          0              0    ]      [     0     0     0     0     0    ]  0   
Total time for which application threads were stopped: 0.0001265 seconds, Stopping threads took: 0.0000199 seconds
Application time: 0.0156430 seconds
         vmop                    [threads: total initially_running wait_to_block]    [time: spin block sync cleanup vmop] page_trap_count
0.734: Deoptimize                       [      10          0              0    ]      [     0     0     0     0     0    ]  0   
Total time for which application threads were stopped: 0.0000985 seconds, Stopping threads took: 0.0000150 seconds
1,472,238,406,376 [ConsumerThread]: count=1, min=1,330,300 ns, avg=1,330,300 ns, max=1,330,300 ns
1,472,238,408,376 [ConsumerThread]: count=997, min=20,775 ns, avg=26,525 ns, max=35,162 ns
Application time: 3.3844176 seconds
         vmop                    [threads: total initially_running wait_to_block]    [time: spin block sync cleanup vmop] page_trap_count
4.119: EnableBiasedLocking              [      10          2              2    ]      [     0     0     0     0     0    ]  2   
Total time for which application threads were stopped: 0.0002117 seconds, Stopping threads took: 0.0000478 seconds
1,472,238,410,376 [ConsumerThread]: count=1,299, min=150 ns, avg=210 ns, max=514 ns
1,472,238,412,377 [ConsumerThread]: count=1,001, min=156 ns, avg=217 ns, max=1,254 ns
1,472,238,414,377 [ConsumerThread]: count=1,001, min=161 ns, avg=227 ns, max=3,029 ns
1,472,238,416,377 [ConsumerThread]: count=1,001, min=162 ns, avg=248 ns, max=23,939 ns
1,472,238,418,377 [ConsumerThread]: count=1,001, min=155 ns, avg=224 ns, max=1,138 ns
1,472,238,420,377 [ConsumerThread]: count=1,300, min=164 ns, avg=265 ns, max=489 ns
1,472,238,422,377 [ConsumerThread]: count=1,001, min=162 ns, avg=217 ns, max=375 ns
1,472,238,424,378 [ConsumerThread]: count=1,300, min=154 ns, avg=284 ns, max=591 ns
1,472,238,426,378 [ConsumerThread]: count=1,001, min=157 ns, avg=214 ns, max=352 ns
1,472,238,428,378 [ConsumerThread]: count=1,001, min=168 ns, avg=227 ns, max=340 ns
1,472,238,430,378 [ConsumerThread]: count=1,300, min=145 ns, avg=218 ns, max=351 ns
1,472,238,432,378 [ConsumerThread]: count=1,001, min=166 ns, avg=227 ns, max=331 ns
1,472,238,434,378 [ConsumerThread]: count=1,300, min=149 ns, avg=250 ns, max=891 ns
1,472,238,436,378 [ConsumerThread]: count=1,001, min=160 ns, avg=219 ns, max=343 ns
1,472,238,438,379 [ConsumerThread]: count=1,001, min=159 ns, avg=215 ns, max=336 ns
1,472,238,440,379 [ConsumerThread]: count=1,001, min=161 ns, avg=217 ns, max=349 ns
1,472,238,442,379 [ConsumerThread]: count=1,001, min=161 ns, avg=228 ns, max=346 ns
1,472,238,444,379 [ConsumerThread]: count=1,300, min=156 ns, avg=261 ns, max=610 ns
1,472,238,446,379 [ConsumerThread]: count=1,001, min=154 ns, avg=224 ns, max=322 ns
1,472,238,448,379 [ConsumerThread]: count=1,001, min=162 ns, avg=219 ns, max=319 ns
1,472,238,450,379 [ConsumerThread]: count=1,001, min=155 ns, avg=218 ns, max=340 ns
1,472,238,452,380 [ConsumerThread]: count=1,001, min=160 ns, avg=215 ns, max=343 ns
1,472,238,454,380 [ConsumerThread]: count=1,300, min=157 ns, avg=254 ns, max=2,159 ns
1,472,238,456,380 [ConsumerThread]: count=1,001, min=161 ns, avg=226 ns, max=340 ns
1,472,238,458,380 [ConsumerThread]: count=1,300, min=144 ns, avg=215 ns, max=378 ns
1,472,238,460,380 [ConsumerThread]: count=1,001, min=155 ns, avg=221 ns, max=327 ns
1,472,238,462,380 [ConsumerThread]: count=1,001, min=161 ns, avg=219 ns, max=354 ns
1,472,238,464,381 [ConsumerThread]: count=1,300, min=140 ns, avg=217 ns, max=382 ns
1,472,238,466,381 [ConsumerThread]: count=1,001, min=158 ns, avg=220 ns, max=320 ns
1,472,238,468,381 [ConsumerThread]: count=1,300, min=152 ns, avg=317 ns, max=1,160 ns
1,472,238,470,381 [ConsumerThread]: count=1,001, min=161 ns, avg=224 ns, max=388 ns
1,472,238,472,381 [ConsumerThread]: count=1,001, min=166 ns, avg=221 ns, max=388 ns
1,472,238,474,381 [ConsumerThread]: count=1,001, min=154 ns, avg=223 ns, max=346 ns
1,472,238,476,381 [ConsumerThread]: count=1,001, min=161 ns, avg=219 ns, max=341 ns
1,472,238,478,382 [ConsumerThread]: count=1,300, min=148 ns, avg=207 ns, max=344 ns
1,472,238,480,382 [ConsumerThread]: count=1,001, min=160 ns, avg=362 ns, max=142,178 ns
1,472,238,482,382 [ConsumerThread]: count=1,001, min=160 ns, avg=225 ns, max=320 ns
1,472,238,484,382 [ConsumerThread]: count=1,001, min=169 ns, avg=224 ns, max=339 ns
1,472,238,486,382 [ConsumerThread]: count=1,001, min=163 ns, avg=222 ns, max=348 ns
1,472,238,488,382 [ConsumerThread]: count=1,300, min=141 ns, avg=226 ns, max=388 ns
1,472,238,490,382 [ConsumerThread]: count=1,001, min=162 ns, avg=219 ns, max=388 ns
1,472,238,492,383 [ConsumerThread]: count=1,300, min=154 ns, avg=241 ns, max=495 ns
1,472,238,494,383 [ConsumerThread]: count=1,001, min=166 ns, avg=230 ns, max=2,412 ns
1,472,238,496,383 [ConsumerThread]: count=1,001, min=163 ns, avg=224 ns, max=396 ns
1,472,238,498,383 [ConsumerThread]: count=1,300, min=140 ns, avg=230 ns, max=359 ns
1,472,238,500,383 [ConsumerThread]: count=1,001, min=160 ns, avg=219 ns, max=387 ns
1,472,238,502,383 [ConsumerThread]: count=1,300, min=147 ns, avg=222 ns, max=340 ns
1,472,238,504,384 [ConsumerThread]: count=1,001, min=162 ns, avg=218 ns, max=316 ns
1,472,238,506,384 [ConsumerThread]: count=1,001, min=156 ns, avg=215 ns, max=330 ns
1,472,238,508,384 [ConsumerThread]: count=1,001, min=168 ns, avg=226 ns, max=349 ns
1,472,238,510,384 [ConsumerThread]: count=1,001, min=164 ns, avg=225 ns, max=352 ns
1,472,238,512,384 [ConsumerThread]: count=1,300, min=146 ns, avg=266 ns, max=740 ns
1,472,238,514,384 [ConsumerThread]: count=1,001, min=161 ns, avg=223 ns, max=349 ns
1,472,238,516,384 [ConsumerThread]: count=1,001, min=160 ns, avg=219 ns, max=386 ns
1,472,238,518,385 [ConsumerThread]: count=1,001, min=163 ns, avg=219 ns, max=340 ns
1,472,238,520,385 [ConsumerThread]: count=1,001, min=158 ns, avg=225 ns, max=2,361 ns
1,472,238,522,385 [ConsumerThread]: count=1,300, min=157 ns, avg=237 ns, max=440 ns
1,472,238,524,385 [ConsumerThread]: count=1,001, min=169 ns, avg=222 ns, max=354 ns
1,472,238,526,385 [ConsumerThread]: count=1,300, min=148 ns, avg=238 ns, max=349 ns
1,472,238,528,385 [ConsumerThread]: count=1,001, min=161 ns, avg=225 ns, max=386 ns
1,472,238,530,385 [ConsumerThread]: count=1,001, min=164 ns, avg=217 ns, max=357 ns
1,472,238,532,386 [ConsumerThread]: count=1,300, min=147 ns, avg=208 ns, max=352 ns
1,472,238,534,386 [ConsumerThread]: count=1,001, min=162 ns, avg=226 ns, max=392 ns
1,472,238,536,386 [ConsumerThread]: count=1,300, min=159 ns, avg=244 ns, max=435 ns
1,472,238,538,386 [ConsumerThread]: count=1,001, min=163 ns, avg=225 ns, max=308 ns
1,472,238,540,386 [ConsumerThread]: count=1,001, min=166 ns, avg=221 ns, max=352 ns
1,472,238,542,386 [ConsumerThread]: count=1,001, min=161 ns, avg=219 ns, max=366 ns
1,472,238,544,387 [ConsumerThread]: count=1,001, min=163 ns, avg=220 ns, max=334 ns
1,472,238,546,387 [ConsumerThread]: count=1,300, min=146 ns, avg=217 ns, max=1,571 ns
1,472,238,548,387 [ConsumerThread]: count=1,001, min=157 ns, avg=229 ns, max=329 ns
1,472,238,550,387 [ConsumerThread]: count=1,001, min=157 ns, avg=226 ns, max=363 ns
1,472,238,552,387 [ConsumerThread]: count=1,001, min=155 ns, avg=223 ns, max=339 ns
1,472,238,554,387 [ConsumerThread]: count=1,001, min=166 ns, avg=219 ns, max=321 ns
Application time: 145.7208812 seconds
         vmop                    [threads: total initially_running wait_to_block]    [time: spin block sync cleanup vmop] page_trap_count
149.840: Exit                             [      10          2              3    ]      [     0     0     0     0   311    ]  2   

Polling page always armed
Deoptimize                         6
EnableBiasedLocking                1
Exit                               1
    0 VM operations coalesced during safepoint
Maximum sync time      8 ms
Maximum vm operation time (except for Exit VM operation)      0 ms

Process finished with exit code 130
</pre>
