package com.company.eems.util;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class to provide application-wide logger instances.
 */
public final class LoggerUtil {

    private LoggerUtil() {}

    /**
     * Returns a logger for the given class.
     *
     * @param clazz class for which logger is required
     * @return Logger instance
     */
    public static Logger getLogger(Class<?> clazz) {
        Logger logger = Logger.getLogger(clazz.getName());
        logger.setLevel(Level.ALL);
        return logger;
    }
}
