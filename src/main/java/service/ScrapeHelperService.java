package service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import util.Helper;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScrapeHelperService {

    private static ScrapeHelperService scrapeHelperServiceInstance = null;

    private ScrapeHelperService() {
    }

    public static ScrapeHelperService getInstance() {
        if (scrapeHelperServiceInstance == null)
            scrapeHelperServiceInstance = new ScrapeHelperService();
        return scrapeHelperServiceInstance;
    }

    public void fetchRss(String sourceUrl, String destinationPath) {
        Instant start = Instant.now();
        System.out.println("Fetching RSS..." + sourceUrl);
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
            BufferedInputStream in = new BufferedInputStream(new URL(sourceUrl).openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(destinationPath);
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("Fetch Time taken: " + timeElapsed.toMillis() + " milliseconds");
            System.out.println("Fetching RSS is done in " + destinationPath + "\n");
        }

    }

    public void parseImageFromRss(String sourcePath, String destinationPath) {
        System.out.println("Parsing Image From RSS...");
        Instant start = Instant.now();
        StringBuilder images = new StringBuilder();
        int totalImages = 0;
        try {
            File file = new File(sourcePath);
            if (file.exists()) {
                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);
                NodeList itemList = doc.getElementsByTagName("item");
                for (int i = 0; i < itemList.getLength(); i++) {
//                    System.out.println("item: " + i);
                    Element item = (Element) itemList.item(i);
                    NodeList mediaGroup = item.getElementsByTagName("media:group");
//                    NodeList mediaGroup = item.getElementsByTagName("media:group").item(0).getChildNodes();
                    if (mediaGroup.getLength() > 0) {
                        mediaGroup = item.getElementsByTagName("media:group").item(0).getChildNodes();
                        for (int m = 0; m < mediaGroup.getLength(); m++) {
//                            System.out.println(mediaGroup.item(m).getAttributes().item(3).getTextContent());
                            images.append(mediaGroup.item(m).getAttributes().item(3).getTextContent()).append("\n");
                            totalImages++;
                        }
                    }
                }
            } else {
                System.out.println(sourcePath + " File not found!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
//                System.out.println(images.toString());
                System.out.println("Updating image at " + new Date() + " in " + destinationPath);
                images.append("\n\nLast Updated: ").append(new Date().toString());
                Helper helper = Helper.getInstance();
                helper.fileWriter(destinationPath, images.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

            Instant end = Instant.now();
            Duration timeElapsed = Duration.between(start, end);
            System.out.println("Total Images: " + totalImages);
            System.out.println("Parsing Image Time taken: " + timeElapsed.toMillis() + " milliseconds");
            System.out.println("Parsing Image From RSS is done in "+ destinationPath + "\n");
        }
    }

    public WebClient getWebClient() throws Exception {
        WebClient webclient = new WebClient(BrowserVersion.CHROME);
        webclient.getOptions().setJavaScriptEnabled(true);
        webclient.getOptions().setCssEnabled(false);
        webclient.getOptions().setRedirectEnabled(true);
        webclient.setAjaxController(new NicelyResynchronizingAjaxController());
        webclient.getOptions().setThrowExceptionOnScriptError(false);
        webclient.getCookieManager().setCookiesEnabled(true);
        Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
        Logger.getLogger("org.apache.commons.httpclient").setLevel(Level.OFF);
        Logger.getLogger("log4j.rootLogger").setLevel(Level.OFF);
        Logger.getLogger("log4j.logger.main").setLevel(Level.OFF);
        return webclient;
    }
}
