import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import service.ScrapeHelperService;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;


/**
 * This code works in my PC
 * I am sure, it will work in your end too.
 * But, there's a saying, God knows everything.
 **/

public class Main {
    public static void main(String[] args) {
//        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//        Task task1 = new Task ("Demo Task 1");
//        Task task2 = new Task ("Demo Task 2");
//        System.out.println("The time is : " + new Date());
//
//        ScheduledFuture<?> result = executor.scheduleAtFixedRate(task1, 15, 15, TimeUnit.SECONDS);
//        ScheduledFuture<?> result2 = executor.scheduleAtFixedRate(task2, 20, 20, TimeUnit.SECONDS);
//
//        try {
//            TimeUnit.MINUTES.sleep(60);
//        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        executor.shutdown();

        ScrapeHelperService scrapeHelperService = ScrapeHelperService.getInstance();
        scrapeHelperService.fetchRss();
        scrapeHelperService.parseImageFromRss();
    }

}
