package Zim.task;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created by Laxton-Joe on 2017/9/7.
 */
public class ImportExtFilter implements FilenameFilter {
    private String ext;

    public ImportExtFilter(String ext) {
        this.ext = ext;
    }

    public boolean accept(File dir, String name) {
        return (name.endsWith(ext));
    }
}
