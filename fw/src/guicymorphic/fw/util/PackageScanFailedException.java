package guicymorphic.fw.util;

import java.io.IOException;

/**
 * Used when package scan fails.
 *
 * @author Alen Vrecko
 */
public class PackageScanFailedException extends RuntimeException {
    public PackageScanFailedException(String s, IOException e) {
        super(s,e);
    }
}
