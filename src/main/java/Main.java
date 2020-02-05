import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.xml.XmlPage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import util.ScrapeHelper;

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

        fetchRss();
        parseImageFromRss();
    }

    private static void fetchRss() {
        System.out.println("Fetching RSS...");
//        try {
//            ScrapeHelper scrapeHelper = new ScrapeHelper();
//            WebClient webClient = scrapeHelper.getWebClient();
//            XmlPage xmlPage = webClient.getPage("http://rss.cnn.com/rss/edition.rss");
//            FileWriter fw = new FileWriter("src/main/resources/b.rss");
//            fw.write(xmlPage.asXml().trim());
//            fw.close();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }

        try {
            BufferedInputStream in = new BufferedInputStream(new URL("http://rss.cnn.com/rss/edition.rss").openStream());
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/a.rss");
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void parseImageFromRss() {
        System.out.println("Parsing Image From RSS...");
        Instant start = Instant.now();
        int totalImages = 0;
        try {
            File file = new File("src/main/resources/edition.rss");
            if (file.exists()) {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);
                NodeList itemList = doc.getElementsByTagName("item");
                System.out.println();
                for (int i = 0; i < itemList.getLength(); i++) {
                    Element item = (Element) itemList.item(i);
                    NodeList mediaGroup = item.getElementsByTagName("media:group").item(0).getChildNodes();
                    for (int m = 0; m < mediaGroup.getLength(); m++) {
                        System.out.println(mediaGroup.item(m).getAttributes().item(3).getTextContent());
                        totalImages++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Instant end = Instant.now();
        Duration timeElapsed = Duration.between(start, end);
        System.out.println("Total Images: " + totalImages);
        System.out.println("Time taken: " + timeElapsed.toMillis() + " milliseconds");
    }

}
