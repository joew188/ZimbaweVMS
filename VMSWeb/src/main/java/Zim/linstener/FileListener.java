package Zim.linstener;

import Zim.common.SysConfigUtil;
import Zim.task.ApplicantRunnable;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Laxton-Joe on 2017/2/10.
 */
public class FileListener implements FileAlterationListener {
    static final ExecutorService threadPool = Executors.newFixedThreadPool(Integer.parseInt(SysConfigUtil.getSetting("import-thread-num")));

    @Override
    public void onStart(FileAlterationObserver observer) {

    }

    @Override
    public void onDirectoryCreate(File directory) {

    }

    @Override
    public void onDirectoryChange(File directory) {

    }

    @Override
    public void onDirectoryDelete(File directory) {

    }

    @Override
    public void onFileCreate(File file) {
        if (file.exists()) {
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(file);
                threadPool.execute(new ApplicantRunnable(fileInputStream));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            // threadPool.execute(new ApplicantRunnable(file));
        }
    }

    @Override
    public void onFileChange(File file) {

    }

    @Override
    public void onFileDelete(File file) {

    }

    @Override
    public void onStop(FileAlterationObserver observer) {

    }
}
