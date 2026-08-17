package nbjavac;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class FileSystemsWrapper {
    private static final Method newFS13;

    static {
        Method declaredMethod;
        try {
            declaredMethod = FileSystems.class.getDeclaredMethod("newFileSystem", Path.class, Map.class, ClassLoader.class);
        } catch (NoSuchMethodException unused) {
            declaredMethod = null;
        }
        newFS13 = declaredMethod;
    }

    public static FileSystem newFileSystem(Path path, Map<String, ?> map, ClassLoader classLoader) throws IOException {
        Method method = newFS13;
        if (method == null) {
            return FileSystems.newFileSystem(path, classLoader);
        }
        try {
            return (FileSystem) method.invoke(null, path, map, classLoader);
        } catch (IllegalAccessException unused) {
            g33.a();
            return null;
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause instanceof IOException) {
                throw ((IOException) cause);
            }
            rc6.a(e);
            return null;
        }
    }

    public static FileSystem newFileSystem(Path path, Map<String, ?> map) throws IOException {
        return newFileSystem(path, map, null);
    }
}
