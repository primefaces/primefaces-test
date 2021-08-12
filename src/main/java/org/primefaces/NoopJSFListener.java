package org.primefaces;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class NoopJSFListener implements ServletContextListener {

    public NoopJSFListener() {
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // NOOP
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // NOOP
    }

}
