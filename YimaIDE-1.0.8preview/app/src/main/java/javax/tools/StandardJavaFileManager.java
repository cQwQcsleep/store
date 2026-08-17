package javax.tools;

import defpackage.nrd;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import javax.tools.StandardJavaFileManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface StandardJavaFileManager extends JavaFileManager {

    public interface PathFactory {
        Path getPath(String str, String... strArr);
    }

    private static <T> Collection<T> asCollection(Iterable<T> iterable) {
        if (iterable instanceof Collection) {
            return (Collection) iterable;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        return arrayList;
    }

    private static Iterable<File> asFiles(final Iterable<? extends Path> iterable) {
        return new Iterable() { // from class: xld
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return StandardJavaFileManager.b(iterable);
            }
        };
    }

    private static Iterable<Path> asPaths(final Iterable<? extends File> iterable) {
        return new Iterable() { // from class: yld
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return StandardJavaFileManager.h3(iterable);
            }
        };
    }

    static /* synthetic */ Iterator b(Iterable iterable) {
        return new Iterator<File>(iterable) { // from class: javax.tools.StandardJavaFileManager.2
            final Iterator<? extends Path> iter;
            final /* synthetic */ Iterable val$paths;

            {
                this.val$paths = iterable;
                this.iter = iterable.iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.iter.hasNext();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.Iterator
            public File next() {
                Path next = this.iter.next();
                try {
                    return next.toFile();
                } catch (UnsupportedOperationException e) {
                    nrd.a(next.toString(), e);
                    return null;
                }
            }
        };
    }

    static /* synthetic */ Iterator h3(Iterable iterable) {
        return new Iterator<Path>(iterable) { // from class: javax.tools.StandardJavaFileManager.1
            final Iterator<? extends File> iter;
            final /* synthetic */ Iterable val$files;

            {
                this.val$files = iterable;
                this.iter = iterable.iterator();
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.iter.hasNext();
            }

            @Override // java.util.Iterator
            public Path next() {
                return this.iter.next().toPath();
            }
        };
    }

    default Path asPath(FileObject fileObject) {
        throw new UnsupportedOperationException();
    }

    Iterable<? extends JavaFileObject> getJavaFileObjects(File... fileArr);

    Iterable<? extends JavaFileObject> getJavaFileObjects(String... strArr);

    default Iterable<? extends JavaFileObject> getJavaFileObjects(Path... pathArr) {
        return getJavaFileObjectsFromPaths((Collection<? extends Path>) Arrays.asList(pathArr));
    }

    Iterable<? extends JavaFileObject> getJavaFileObjectsFromFiles(Iterable<? extends File> iterable);

    default Iterable<? extends JavaFileObject> getJavaFileObjectsFromPaths(Collection<? extends Path> collection) {
        return getJavaFileObjectsFromFiles(asFiles(collection));
    }

    Iterable<? extends JavaFileObject> getJavaFileObjectsFromStrings(Iterable<String> iterable);

    Iterable<? extends File> getLocation(JavaFileManager.Location location);

    default Iterable<? extends Path> getLocationAsPaths(JavaFileManager.Location location) {
        return asPaths(getLocation(location));
    }

    @Override // javax.tools.JavaFileManager
    boolean isSameFile(FileObject fileObject, FileObject fileObject2);

    void setLocation(JavaFileManager.Location location, Iterable<? extends File> iterable) throws IOException;

    default void setLocationForModule(JavaFileManager.Location location, String str, Collection<? extends Path> collection) throws IOException {
        throw new UnsupportedOperationException();
    }

    default void setLocationFromPaths(JavaFileManager.Location location, Collection<? extends Path> collection) throws IOException {
        setLocation(location, asFiles(collection));
    }

    default void setPathFactory(PathFactory pathFactory) {
    }

    @Deprecated
    default Iterable<? extends JavaFileObject> getJavaFileObjectsFromPaths(Iterable<? extends Path> iterable) {
        return getJavaFileObjectsFromPaths(asCollection(iterable));
    }
}
