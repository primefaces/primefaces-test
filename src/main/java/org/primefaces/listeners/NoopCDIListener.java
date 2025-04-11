package org.primefaces.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class NoopCDIListener implements ServletContextListener {

    public NoopCDIListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        // NOOP
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // NOOP
    }

}
