package Zim.linstener;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 * Created by Laxton-Joe on 2017/2/16.
 */
public class FileMonitor {
    FileAlterationMonitor monitor = null;

    public FileMonitor(long interval) throws Exception {
        monitor = new FileAlterationMonitor(interval);
    }

    public void monitor(String path, FileAlterationListener listener) {
        FileAlterationObserver observer = new FileAlterationObserver(new File(path),
                FileFilterUtils.and(
                        FileFilterUtils.suffixFileFilter(".exportlog")
                ));
        observer.addListener(listener);
        monitor.addObserver(observer);

    }

    public void stop() throws Exception {
        monitor.stop();
    }

    public void start() throws Exception {
        monitor.start();
    }
}
