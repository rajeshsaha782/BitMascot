package threads;

import service.HelperService;

import java.util.Date;

public class Thread2 implements Runnable {
    private String name;
    private String sourcePath;
    private String destinationPath;

    public Thread2(String name, String sourcePath, String destinationPath) {
        this.name = name;
        this.sourcePath = sourcePath;
        this.destinationPath = destinationPath;
    }

    public String getName() {
        return name;
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public String getDestinationPath() {
        return destinationPath;
    }

    public void run() {
        try {
            System.out.println("Doing a task during : " + name + " - Time - " + new Date());
            HelperService helperService = HelperService.getInstance();
            helperService.parseImageFromRss(sourcePath, destinationPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
