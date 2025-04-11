package org.primefaces.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class NoopJSFListener implements ServletContextListener {

    public NoopJSFListener() {
    }

    public void contextInitialized(ServletContextEvent sce) {
        // NOOP
    }

    public void contextDestroyed(ServletContextEvent sce) {
        // NOOP
    }

}
