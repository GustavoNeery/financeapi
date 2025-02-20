package gustavoneery.financeapi.utils;
import java.lang.reflect.Field;

public class UpdateUtils {
    public static <T> void updateNonNullFields(T source, T target) {
        for (Field field : source.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(source);
                if (value != null) {
                    field.set(target, value);
                }
            } catch (IllegalAccessException ignored) {}
        }
    }
}
