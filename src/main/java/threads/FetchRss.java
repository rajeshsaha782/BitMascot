package threads;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.xml.XmlPage;
import util.ScrapeHelper;

import java.io.FileWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            ScrapeHelper scrapeHelper = new ScrapeHelper();
            WebClient webClient = scrapeHelper.getWebClient();
            XmlPage xmlPage = webClient.getPage("http://rss.cnn.com/rss/edition.rss");
            FileWriter fw = new FileWriter("src/main/resources/a.rss");
            fw.write(xmlPage.asXml());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
