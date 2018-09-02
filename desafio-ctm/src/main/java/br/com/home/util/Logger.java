package br.com.home.util;

import br.com.home.ctm.enums.Level;

public class Logger {
    private static Logger logger = new Logger();

    private Logger() {}

    public static Logger getLogger() {
        return logger;
    }

    private void log(Level level, Object msg) {
        level.getStream().println("[" + level + "]: " + msg);
    }

    public void debug(Object msg) {
        log(Level.DEBUG, msg);
    }

    public void info(Object msg) {
        log(Level.INFO, msg);
    }

    public void warn(Object msg) {
        log(Level.WARN, msg);
    }

    public void error(Object msg) {
        log(Level.ERROR, msg);
    }

    public void fatal(Object msg) {
        log(Level.WARN, msg);
    }

}
