package com.adlerd.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final int NONE = 0;
    private static final int INFO = 1;
    private static final int DEBUG = 2;
    private static final int WARN = 3;
    private static final int ERROR = 4;

    private static final PrintStream CONSOLE = System.out;
    private static final PrintStream CONSOLE_ERROR = System.err;

    private static boolean isLogOpen = false;
    private static int numInstances = 0;
    private static int logLevel = ERROR;
    private static PrintStream loggerStream;
    private static int logFileCount = 0;

    private Logger() {}

    public static void start() {
        start("log_file");
    }

    public static void start(String filename) {
        if (numInstances == 0) {
            File logFile = new File(getDate() + "_" + getFileTime() + "_" + filename + ".log");
            try {
                loggerStream = new PrintStream(logFile);
                SplitOutputStream outSplitOutputStream = new SplitOutputStream(System.out, loggerStream);
                SplitOutputStream errSplitOutputStream = new SplitOutputStream(System.err, loggerStream);
                System.setOut(new PrintStream(outSplitOutputStream));
                System.setErr(new PrintStream(errSplitOutputStream));
                isLogOpen = true;
                numInstances++;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            errorln("Logger has already been initialized");
        }
    }

    public static void close() {
        if (isLogOpen) {
            loggerStream.close();
            System.setOut(CONSOLE);
            System.setErr(CONSOLE_ERROR);
            isLogOpen = false;
            numInstances--;
        } else {
          errorln("Failed to close logger");
        }
    }

    public static void output(Object message) {
        if (logLevel >= NONE) {
            System.out.print(">>> " + message);
        }
    }

    public static void outputln(Object message) {
        if (logLevel >= NONE) {
            System.out.println(">>> " + message);
        }
    }

    public static void infoln(Object message) {
        if (logLevel >= INFO) {
            System.out.println("[ " + getDate() + " " + getTime() + " INFO ]: " + message);
        }
    }

    public static void debugln(Object message) {
        if (logLevel >= DEBUG) {
            System.out.println("[ " + getDate() + " " + getTime() + " DEBUG ]: " + message);
        }
    }

    public static void warnln(Object message) {
        if (logLevel >= WARN) {
            System.err.println("[ " + getDate() + " " + getTime() + " WARNING ]: " + message);
        }
    }

    public static void errorln(Object message) {
        errorln(message, false, -1);
    }

    public static void errorln(Object message, boolean shouldExit) {
        errorln(message, shouldExit, -1);
    }

    /**
     * Be careful with this since if you forget to set the appropriate log level your code program
     * might not exit properly
     * @param message
     * @param shouldExit
     * @param statusCode
     */
    public static void errorln(Object message, boolean shouldExit, int statusCode) {
        if (logLevel >= ERROR) {
            if (message instanceof Exception) {
                if (shouldExit) {
                    System.err.println("[ " + getDate() + " " + getTime() + " FATAL EXCEPTION]:");
                } else {
                    System.err.println("[ " + getDate() + " " + getTime() + " EXCEPTION]:");
                }
                ((Exception) message).printStackTrace();
            } else {
                System.err.println("[ " + getDate() + " " + getTime() + " ERROR ]: " + message);
            }
            if (shouldExit) {
                System.exit(statusCode);
            }
        }
    }

    private static String getFileTime() {
        String rawTime = getTime();
        StringBuilder time = new StringBuilder();
        for (char c : rawTime.toCharArray()) {
            if (c != ':') {
                time.append(c);
            }
        }

        return time.toString();
    }

    private static String getTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    private static String getDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static int getLogLevel() {
        return logLevel;
    }

    public static void setLogLevel(int logLevel) {
        if (logLevel <= ERROR && logLevel >= NONE) {
            Logger.logLevel = logLevel;
        } else {
            errorln("log level must be inputted as one of the following: \n"
                    + "\tNONE | INFO | DEBUG | WARNING | ERROR");
        }
    }

    public static boolean getIsLogOpen() {
        return isLogOpen;
    }

    public static void setIsLogOpen(boolean value) {
        isLogOpen = value;
    }
}