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
        Server server = new Server(8888);
        RolloverFileOutputStream os = new RolloverFileOutputStream("yyyy_mm_dd_mywebapp.log", true);
        PrintStream logStream = new PrintStream(os);
        System.setOut(logStream);
        System.setErr(logStream);

        String wardir = "/home/hduser/IdeaProjects/JavaExamples/MyWebApp/src/main/webapp/";

        WebAppContext context = new WebAppContext();

        context.setResourceBase(wardir);
        context.setParentLoaderPriority(true);
        server.setHandler(context);

        server.start();

        Logger log = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);

        log.info("**************** Embedded Jetty logging started *********************");
        server.join();
    }
}