package util;

import java.io.FileWriter;
import java.util.Date;

public class Helper {
    private static Helper helperInstance = null;

    private Helper() {
    }

    public static Helper getInstance() {
        if (helperInstance == null)
            helperInstance = new Helper();
        return helperInstance;
    }

    public void fileWriter(String filePath, String data) throws Exception{
        FileWriter fw = new FileWriter(filePath);
        fw.write(data);
        fw.close();
    }
}
