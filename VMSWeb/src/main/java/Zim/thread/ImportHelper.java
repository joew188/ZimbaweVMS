package Zim.thread;



import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Laxton-Joe on 2017/6/22.
 */
public class ImportHelper {

    public static ConcurrentLinkedQueue<String> fileQueue;
//    public static ConcurrentLinkedQueue<String> matcherQueue;

    static {
        if (null == fileQueue) {
            fileQueue = new ConcurrentLinkedQueue<String>();
        }
//        if (null == matcherQueue) {
//            matcherQueue = new ConcurrentLinkedQueue<String>();
//        }

    }

}

