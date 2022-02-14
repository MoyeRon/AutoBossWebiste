package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

public class ThreadUtils {
    private static final Logger LOGGER = LogManager.getLogger(ThreadUtils.class);

    public static void SafeSleeping(int seconds) {
        int randTime = new Random().nextInt() % (seconds * 200);
        try {
            Thread.sleep(seconds * 1000 + randTime);
        } catch (InterruptedException e) {
            LOGGER.error("safe sleep error", e);
        }

    }
}
