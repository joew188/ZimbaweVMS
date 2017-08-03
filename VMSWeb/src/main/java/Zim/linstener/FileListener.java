package Zim.linstener;

import Zim.task.ImportTask;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Laxton-Joe on 2017/2/10.
 */
public class FileListener extends FileAlterationListenerAdaptor {
     static final ExecutorService executorService = Executors.newCachedThreadPool();

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
        super.onFileCreate(file);
        if (file.exists()) {
            if (file.canRead()) {
                ImportTask task = new ImportTask(file.getPath());
                executorService.submit(task);
            }
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
