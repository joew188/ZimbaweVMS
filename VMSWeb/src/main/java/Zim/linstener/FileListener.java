package Zim.linstener;

import Zim.task.ImportTask;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Laxton-Joe on 2017/2/10.
 */
public class FileListener extends FileAlterationListenerAdaptor {
    static final ExecutorService executorService = Executors.newCachedThreadPool();
    private static Logger logger = LoggerFactory.getLogger(FileListener.class);

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
        logger.error("File Created:" + file.getName());
        if (file.exists()) {
            if (file.canRead()) {
                ImportTask task = new ImportTask(file.getPath());
                executorService.submit(task);
                logger.error("Import Task Submit:" + file.getName());
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
