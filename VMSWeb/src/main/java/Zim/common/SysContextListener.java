package Zim.common;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Enumeration;

/**
 * Created by Laxton-Joe on 2017/2/10.
 */
public class SysContextListener implements ServletContextListener {
    public void contextDestroyed(ServletContextEvent event) {

    }
    public void contextInitialized(ServletContextEvent event) {
        ServletContext servletContext = event.getServletContext();
        Enumeration en = servletContext.getInitParameterNames();
        while (en.hasMoreElements()) {
            String key = en.nextElement().toString();
            String value = servletContext.getInitParameter(key);
            SysConfigUtil.setSetting(key, value);
        }
    }
}
