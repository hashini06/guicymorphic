package guicymorphic.fw.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: alen
 * Date: Feb 28, 2010
 * Time: 2:45:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Properties2 {


    private static final Logger log = LoggerFactory.getLogger(Properties2.class);


    public static Properties loadProperties(String name) {

        Properties properties = new Properties();

        final InputStream stream = Properties2.class.getResourceAsStream("/" + name);
        try {
            properties.load(stream);
        } catch (Exception e) {
            throw new PropertiesNotFound("Unable to find/load " + name + ".", e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    //cant do anything
                    log.warn("Exception encountered while closing stream after loading {}.", name);
                }
            }

        }

        return properties;
    }

    public static Properties loadProperties(String name, Class relativeTo) {
        Properties properties = new Properties();

        final InputStream stream = relativeTo.getResourceAsStream(name);
        try {
            properties.load(stream);
        } catch (Exception e) {
            throw new PropertiesNotFound("Unable to find/load " + name + " relative to " + relativeTo.getName() + ".", e);
        } finally {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    //cant do anything
                    log.warn("Exception encountered while closing stream after loading {}.", name);
                }
            }

        }

        return properties;
    }
}
