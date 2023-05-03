package PA;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SumCalculator {
    public static int calculateSum(int number) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        int partSize = number / 5;
        int remainder = number % 5;

        int start = 1;
        int end = start + partSize - 1;

        AtomicInteger totalSum = new AtomicInteger();

        for (int i = 0; i < 5; i++) {
            if (remainder > 0) {
                end++;
                remainder--;
            }

            int finalStart = start;
            int finalEnd = end;

            executorService.execute(() -> {
                int sum = 0;

                for (int j = finalStart; j <= finalEnd; j++) {
                    sum += j;

                    // Simulate time-consuming calculation
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                synchronized (SumCalculator.class) {
                    totalSum.addAndGet(sum);
                }
            });

            start = end + 1;
            end = start + partSize - 1;
        }

        executorService.shutdown();
        executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        return totalSum.get();
    }
}
