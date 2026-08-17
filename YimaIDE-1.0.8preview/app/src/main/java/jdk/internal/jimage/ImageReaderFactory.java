package jdk.internal.jimage;

import com.sun.tools.javac.ConfigProvider;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class ImageReaderFactory {
    private static final Path BOOT_MODULES_JIMAGE;
    private static final String JAVA_HOME;
    private static Function<Path, ImageReader> OPENER;
    private static final Map<Path, ImageReader> readers;

    static {
        FileSystem fileSystem;
        try {
            String str = ConfigProvider.JAVA_HOME;
            String string = ConfigProvider.class.getDeclaredMethod("getJavaHome", null).invoke(null, null).toString();
            JAVA_HOME = string;
            if (ImageReaderFactory.class.getClassLoader() == null) {
                try {
                    fileSystem = (FileSystem) Class.forName("sun.nio.fs.DefaultFileSystemProvider").getMethod("theFileSystem", null).invoke(null, null);
                } catch (Exception e) {
                    throw new ExceptionInInitializerError(e);
                }
            } else {
                fileSystem = FileSystems.getDefault();
            }
            BOOT_MODULES_JIMAGE = fileSystem.getPath(string, "lib", "modules");
            readers = new ConcurrentHashMap();
            OPENER = new Function<Path, ImageReader>() { // from class: jdk.internal.jimage.ImageReaderFactory.1
                @Override // java.util.function.Function
                public ImageReader apply(Path path) {
                    try {
                        return ImageReader.open(path);
                    } catch (IOException e2) {
                        u8i.a(e2);
                        return null;
                    }
                }
            };
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            g3c.a("Unable to determine JAVA_HOME", e2);
        }
    }

    private ImageReaderFactory() {
    }

    public static ImageReader get(Path path) throws IOException {
        Objects.requireNonNull(path);
        try {
            return readers.computeIfAbsent(path, OPENER);
        } catch (UncheckedIOException e) {
            throw e.getCause();
        }
    }

    public static ImageReader getImageReader() {
        try {
            return get(BOOT_MODULES_JIMAGE);
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }
}
