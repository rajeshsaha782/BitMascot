package threads;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.xml.XmlPage;
import service.ScrapeHelperService;

import java.io.FileWriter;
import java.util.Date;

public class FetchRss implements Runnable {
    private String name;

    public FetchRss(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void run() {
        try {
            System.out.println("Doing a task during : " + name + " - Time - " + new Date());
            ScrapeHelperService scrapeHelperService = ScrapeHelperService.getInstance();
            WebClient webClient = scrapeHelperService.getWebClient();
            XmlPage xmlPage = webClient.getPage("http://rss.cnn.com/rss/edition.rss");
            FileWriter fw = new FileWriter("src/main/resources/a.rss");
            fw.write(xmlPage.asXml());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
