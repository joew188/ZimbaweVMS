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
    //  static MongoDBDaoImpl mongoDBDaoImpl = MongoDBDaoImpl.getMongoDBDaoImpl();

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
              //  ExportLog exportLog = SystemHelper.ImportInfo(file);
                ImportTask task = new ImportTask(file.getPath());

                executorService.submit(task);
            }
        }
    }
//    @Override
//    public void onFileCreate(File file) {
//        super.onFileCreate(file);
//        if (file.exists()) {
//
//            while (file.canRead()) {
//                ExportLog exportLog = SystemHelper.ImportInfo(file);
//                //   ImportHelper.fileQueue.add(file.getPath());
//                ImportTask task = new ImportTask(exportLog,file.getPath());
//                task.begin();
//                break;
//            }
////        if (file.exists()) {
////            while (file.canRead()) {
////
////                threadPool.submit(new Produce(file.getPath()));
////
////                break;
////            }
////        }
//        }
//    }


//    @Override
//    public void onFileCreate(File file) {
//        super.onFileCreate(file);
//        if (file.exists()) {
//            while (file.canRead()) {
//                Map<String, Object> map = new HashMap<String, Object>();
//                map.put("fileId", file.getPath());
//
//                mongoDBDaoImpl.addObject(map, "ApplicantImportLog");
////            ImportHelper.fileQueue.add(file.getPath());
//                threadPool.submit(new Produce(file.getPath(), ImportHelper.buffer));
//                threadPool.submit(new Consume(ImportHelper.buffer));
////            FileInputStream fileInputStream = null;
////            try {
////                fileInputStream = new FileInputStream(file);
////                threadPool.execute(new ApplicantRunnable(fileInputStream, file.getName()));
////            } catch (FileNotFoundException e) {
////
////                e.printStackTrace();
////            }
//            }
//        }
//    }

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
