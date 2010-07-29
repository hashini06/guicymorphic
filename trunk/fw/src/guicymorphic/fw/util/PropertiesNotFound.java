package guicymorphic.fw.util;

/**
 * Thrown when finding properties fails.
 *
 * @author Alen Vrecko
 */
public class PropertiesNotFound extends RuntimeException {
    public PropertiesNotFound(String s, Exception e) {
        super(s,e);
    }
}
