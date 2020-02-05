package util;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ScrapeHelper {
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
