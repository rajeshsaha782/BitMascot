import service.ScrapeHelperService;
import threads.Thread1;
import threads.Thread2;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;


/**
 * This code works in my PC
 * I am sure, it will work in your end too.
 * But, there's a saying, God knows everything.
 **/

public class Main {
    public static void main(String[] args) {

        String sourceUrl = "http://rss.cnn.com/rss/edition.rss";
        String fetchDestinationPath = "E:/a.rss";
        String imageDestinationPath = "E:/b.txt";

        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        Thread1 task1 = new Thread1 ("Fetch", sourceUrl, fetchDestinationPath);
        Thread2 task2 = new Thread2 ("Image print", fetchDestinationPath, imageDestinationPath);
        System.out.println("The time is : " + new Date());

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

//        ScrapeHelperService scrapeHelperService = ScrapeHelperService.getInstance();
//        scrapeHelperService.fetchRss(sourceUrl, fetchDestinationPath);
//        scrapeHelperService.parseImageFromRss(fetchDestinationPath, imageDestinationPath);
    }

}
