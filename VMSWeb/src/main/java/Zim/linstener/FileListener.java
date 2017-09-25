package Zim.linstener;



import Zim.task.NewImportDataTask;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 * Created by Laxton-Joe on 2017/2/10.
 */
public class FileListener extends FileAlterationListenerAdaptor {

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
                new Thread(new NewImportDataTask(file.getPath())).start();
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
