import com.training.bar.Bar;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;

public class log {
    public static Logger log = Logger.getLogger(Bar.class);

    public static void main(String[] args) throws InterruptedException, InvocationTargetException
    {

        log.debug("Hello this is a debug message");
        log.info("Hello this is an info message");
    }
}
