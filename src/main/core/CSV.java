package core;

import com.opencsv.CSVReader;
import web.Saucedemo.PageLogin.PageLogin;

import java.io.FileReader;
import java.io.IOException;

public class CSV {

    protected static String getLocator(String element) {
        String path = "src/main/" + Thread.currentThread().getStackTrace()[3].getClassName().replace(".", "/") + ".def.csv";
        String locator = null;
        Log.log("Parsing " + path + "for " + element + " locator");
        try {
            CSVReader reader = new CSVReader(new FileReader(path));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[0].equals(element)) {
                    locator = nextLine[1];
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (locator != null)
            return locator;
        else throw new java.lang.Error("element was not found");
    }


    protected static String getData(String name, String filename) {
        String classpath = Thread.currentThread().getStackTrace()[3].getClassName();
        String path = "src/main/" + classpath.replace(".", "/").substring(0, classpath.lastIndexOf(".") + 1) + filename + ".csv";
        String password = null;
        Log.log("Parsing " + path + "for " + name + " data");
        try {
            CSVReader reader = new CSVReader(new FileReader(path));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[0].equals(name)) {
                    password = nextLine[1];
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (password != null)
            return password;
        else throw new java.lang.Error("data was not found");
    }
}
