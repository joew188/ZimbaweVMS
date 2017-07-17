package Zim.linstener;

import Zim.common.SysConfigUtil;
import Zim.map.Record;
import Zim.thread.Consumer;
import Zim.thread.ImportHelper;
import Zim.thread.Matcher;
import Zim.thread.Producer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.*;

/**
 * Created by Laxton-Joe on 2017/6/22.
 */
public class ThreadWorkService {
    @PostConstruct
    public void init() {
        int importScanNum = Integer.parseInt(SysConfigUtil.getSetting("import-thread-num"));

        BlockingQueue<Record> buffer = new LinkedBlockingQueue<Record>(10);
        ConcurrentLinkedQueue<String> matcherQueue = new ConcurrentLinkedQueue<String>();
        Producer producer = new Producer(ImportHelper.fileQueue, buffer);
        Consumer consumer = new Consumer(buffer, matcherQueue);
        Matcher matcher = new Matcher(matcherQueue);
        for (int i = 0; i < importScanNum; i++) {
            Thread tProducer = new Thread(producer);
            tProducer.setDaemon(true);  //后台线程
            tProducer.start();
            Thread tConsumer = new Thread(consumer);
            tConsumer.setDaemon(true);  //后台线程
            tConsumer.start();
//            new Thread(producer).start();
//            new Thread(consumer).start();
        }
        new Thread(matcher).start();
    }

    @PreDestroy
    public void dostory() {
    }
}
