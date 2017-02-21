package Zim.linstener;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * Created by Laxton-Joe on 2017/2/16.
 */
public class FileListenerService {

    @PostConstruct
    public void  init(){
        Thread t = new Thread(new FileListenerRunnable());
        t.setDaemon(true);  //后台线程
        t.start();

    }

    @PreDestroy
    public void  dostory(){
    }
}
