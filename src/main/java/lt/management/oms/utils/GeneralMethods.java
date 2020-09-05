package lt.management.oms.utils;

import com.google.common.base.Preconditions;

import java.io.File;

public class GeneralMethods {
    public static String getFileExtension(String fullName) {
        Preconditions.checkNotNull(fullName);
        String fileName = (new File(fullName)).getName();
        int dotIndex = fileName.lastIndexOf(46);
        return dotIndex == -1 ? "" : fileName.substring(dotIndex + 1);
    }
}
