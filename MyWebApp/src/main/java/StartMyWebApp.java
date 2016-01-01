import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.RolloverFileOutputStream;
import org.eclipse.jetty.webapp.WebAppContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;

public class StartMyWebApp
{
    public static void main(String[] args) throws Exception
    {
        Server server1 = new Server(8888);
        RolloverFileOutputStream os1 = new RolloverFileOutputStream("yyyy_mm_dd_mywebapp1.log", true);
        PrintStream logStream1 = new PrintStream(os1);
        System.setOut(logStream1);
        System.setErr(logStream1);

        String workingDir = System.getProperty("user.dir");
        String wardir = workingDir + "/MyWebApp/src/main/webapp/";

        WebAppContext context1 = new WebAppContext();

        context1.setResourceBase(wardir);
        context1.setParentLoaderPriority(true);
        server1.setHandler(context1);

        server1.start();

        Logger log1 = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);

        log1.info("**************** Embedded Jetty logging started *********************");

        for(int i = 0; i < 100 ; i++){

            log1.info(String.valueOf(i));
        }
        server1.join();
    }
}