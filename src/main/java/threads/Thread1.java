package threads;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.xml.XmlPage;
import service.ScrapeHelperService;

import java.io.FileWriter;
import java.util.Date;

public class Thread1 implements Runnable {
    private String name;
    private String sourceUrl;
    private String destinationPath;

    public Thread1(String name, String sourceUrl, String destinationPath) {
        this.name = name;
        this.sourceUrl = sourceUrl;
        this.destinationPath = destinationPath;
    }

    public String getName() {
        return name;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    public void run() {
        try {
            System.out.println("Doing a task during : " + name + " - Time - " + new Date());
            ScrapeHelperService scrapeHelperService = ScrapeHelperService.getInstance();
            scrapeHelperService.fetchRss(sourceUrl, destinationPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
