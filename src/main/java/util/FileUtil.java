package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class FileUtil {

    public static String readFileInSameFolder() {
        try {
            String jarpath = System.getProperty("java.class.path");
            int firstIndex = jarpath.lastIndexOf(System.getProperty("path.separator")) + 1;
            int lastIndex = jarpath.lastIndexOf(File.separator) + 1;
            jarpath = jarpath.substring(firstIndex, lastIndex);

            File conffile =new File(jarpath + "conf.json");
            if(conffile.exists() != true) {
                return "false";
            }
            InputStreamReader confrd = new InputStreamReader (new FileInputStream(conffile),"UTF-8");
            BufferedReader confbf = new BufferedReader(confrd);
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while((ch = confbf.read()) != -1) {
                sb.append((char) ch);
            }

            return sb.toString();

        }catch (Exception e) {
            return "false";
        }
    }
}
