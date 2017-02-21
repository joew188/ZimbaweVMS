package Zim.linstener;

import Zim.common.SysConfigUtil;

import java.util.Date;

/**
 * Created by Laxton-Joe on 2017/2/16.
 */
public class FileListenerRunnable implements Runnable {
    //  private static Logger logger = LoggerFactory.getLogger(FileListenerRunnable.class);
    @Override
    public void run() {
        try {
            long intervalScan = Long.parseLong(SysConfigUtil.getSetting("import-scan-interval"));
            FileMonitor m = new FileMonitor(intervalScan);
            m.monitor(SysConfigUtil.getSetting("import-scan-path"), new FileListener());
            m.start();
        } catch (Exception e) {
            // logger.error(new Date() + "xml文件监控服务错误：" + e.toString());
            System.out.println(new Date() + "xml文件监控服务错误：" + e.toString());
        }
    }
}
