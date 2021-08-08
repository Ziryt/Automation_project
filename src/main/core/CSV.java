package core;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class CSV {

    private static String getPath() {
        String classpath = null;
        boolean found = false;
        for (int i = 0; !found; i++) {
            if (Thread.currentThread().getStackTrace()[i].getClassName().matches(".+(\\.Page)\\w+$")) {
                classpath = Thread.currentThread().getStackTrace()[i].getClassName();
                found = true;
            }
        }
        return classpath;
    }

    private static String searchInFile(String place, String target) {
        String result = null;
        try {
            CSVReader reader = new CSVReader(new FileReader(place));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine[0].equals(target)) {
                    result = nextLine[1];
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    protected static String getLocator(String element) {
        return getData(element, null);
    }


    protected static String getData(String lf, String filename) {
        String classpath = getPath();
        String path;
        if (filename != null) {
            path = String.format("src/main/%s%s.csv", classpath.replace(".", "/").substring(0, classpath.lastIndexOf(".") + 1), filename);
            Log.log("Parsing " + path + " for " + lf + " data");
        } else {
            path = String.format("src/main/%s.def.csv", getPath().replace(".", "/"));
            Log.log(String.format("Parsing %s for %s locator", path, lf));
        }
        String data = searchInFile(path, lf);
        if (data != null)
            return data;
        else throw new java.lang.Error(String.format("\"%s\" was not found in %s \r\n", lf, path));
    }
}
