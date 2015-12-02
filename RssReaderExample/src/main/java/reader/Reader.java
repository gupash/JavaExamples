package reader;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;

/**
 * @author Ashish Gupta
 */

public class Reader {

    public static void main(String[] args) throws Exception {


        String url  = "http://www.business-standard.com/rss/beyond-business-104.rss";
        URLConnection urlConnection = new URL(url).openConnection();
        urlConnection.setRequestProperty("Cookie", "foo=bar");
        urlConnection.addRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");

        XmlReader reader = null;

        try {

            reader = new XmlReader(urlConnection);
            SyndFeed feed = new SyndFeedInput().build(reader);
            System.out.println("Feed Title: "+ feed.getTitle()  );
            System.out.println("Feed lastBuildDate : "+feed.getPublishedDate());
            System.out.println("Feed Description : "+feed.getDescription());

            for (Iterator i = feed.getEntries().iterator(); i.hasNext();) {
                SyndEntry entry = (SyndEntry) i.next();
                System.out.println(entry.getTitle());
            }
        } finally {
            if (reader != null)
                reader.close();
        }
    }
}