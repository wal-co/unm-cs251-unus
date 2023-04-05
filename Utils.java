import java.util.ArrayList;
import java.util.List;

/**
 * Corey Walker
 * Generic method that creates an arrayList<></T> of an object
 */
public final class Utils {
    public static <T> List<T> repeat(int n, T item) {
        List<T> ls = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ls.add(item);
        }
        return ls;
    }
}
