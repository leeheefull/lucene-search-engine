package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;

import static util.EngineConstants.Log.ERROR_LOG_PATH;
import static util.EngineConstants.Log.LINE_BREAK;

public class ExceptionLogging {
    private ExceptionLogging() {
    }

    public static void log(Exception e) {
        System.err.println(LINE_BREAK);
        System.err.println(new Date());
        e.printStackTrace(System.err);
        System.err.println(LINE_BREAK);
    }

    public static void log() throws FileNotFoundException {
        PrintStream log = new PrintStream(new FileOutputStream(ERROR_LOG_PATH, true));
        System.setErr(log);
    }
}
