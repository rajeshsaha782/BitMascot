import threads.Thread1;
import threads.Thread2;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        String sourceUrl = "http://rss.cnn.com/rss/edition.rss";
        String fetchDestinationPath = "d:\\a.rss";
        String imageDestinationPath = "d:\\a.rss";

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Thread1 task1 = new Thread1("Fetch", sourceUrl, fetchDestinationPath);
        Thread2 task2 = new Thread2("Image print", fetchDestinationPath, imageDestinationPath);
        System.out.println("The time is : " + new Date());

        /**
         * @executor.scheduleAtFixedRate
         * 1st param is the the task to execute periodically,
         * 2nd param is the delay of time until the first execution of the task,
         * 3rd param is the period between two executions,
         * and the last one is the time unit of the second and third parameters.
         **/
        ScheduledFuture<?> result = executor.scheduleAtFixedRate(task1, 0, 15, TimeUnit.SECONDS);
        ScheduledFuture<?> result2 = executor.scheduleAtFixedRate(task2, 0, 20, TimeUnit.SECONDS);

        // TO Fixed a Time
//        try {
//            TimeUnit.MINUTES.sleep(60);
//        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        executor.shutdown();
    }

}
