package com.shadow.okio.internal;

import com.shadow.kotlin.Lazy;
import com.shadow.kotlin.LazyKt;
import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import com.shadow.kotlin.text.StringsKt;
import com.shadow.okio.FileHandle;
import com.shadow.okio.FileMetadata;
import com.shadow.okio.FileSystem;
import com.shadow.okio.Okio;
import com.shadow.okio.Path;
import com.shadow.okio.Sink;
import com.shadow.okio.Source;
import core.pro.android.notify.h;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.JarURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class ResourceFileSystem extends FileSystem {
    private static final Companion Companion = new Companion(null);
    private static final Path ROOT = Path.Companion.get$default(Path.Companion, "/", false, 1, (Object) null);
    private final ClassLoader classLoader;
    private final Lazy roots$delegate;
    private final FileSystem systemFileSystem;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean keepPath(Path path) {
            return !StringsKt.f(path.name(), ".class", true);
        }

        public final Path getROOT() {
            return ResourceFileSystem.ROOT;
        }

        public final Path removeBase(Path path, Path path2) {
            CloseableKt.checkNotNullParameter(path, "<this>");
            CloseableKt.checkNotNullParameter(path2, "base");
            return getROOT().resolve(StringsKt.n(StringsKt.m(path.toString(), path2.toString()), '\\', '/'));
        }

        private Companion() {
        }
    }

    public /* synthetic */ ResourceFileSystem(ClassLoader classLoader, boolean z, FileSystem fileSystem, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(classLoader, z, (i & 4) != 0 ? FileSystem.SYSTEM : fileSystem);
    }

    private final Path canonicalizeInternal(Path path) {
        return ROOT.resolve(path, true);
    }

    private final List<Pair<FileSystem, Path>> getRoots() {
        return (List) this.roots$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Pair<FileSystem, Path>> toClasspathRoots(ClassLoader classLoader) throws IOException {
        Enumeration<URL> resources = classLoader.getResources("");
        CloseableKt.checkNotNullExpressionValue(resources, "getResources(...)");
        ArrayList<URL> list = Collections.list(resources);
        CloseableKt.checkNotNullExpressionValue(list, "list(...)");
        ArrayList arrayList = new ArrayList();
        for (URL url : list) {
            CloseableKt.checkNotNull(url);
            Pair<FileSystem, Path> fileRoot = toFileRoot(url);
            if (fileRoot != null) {
                arrayList.add(fileRoot);
            }
        }
        Enumeration<URL> resources2 = classLoader.getResources("META-INF/MANIFEST.MF");
        CloseableKt.checkNotNullExpressionValue(resources2, "getResources(...)");
        ArrayList<URL> list2 = Collections.list(resources2);
        CloseableKt.checkNotNullExpressionValue(list2, "list(...)");
        ArrayList arrayList2 = new ArrayList();
        for (URL url2 : list2) {
            CloseableKt.checkNotNull(url2);
            Pair<FileSystem, Path> jarRoot = toJarRoot(url2);
            if (jarRoot != null) {
                arrayList2.add(jarRoot);
            }
        }
        return CollectionsKt.e(arrayList, arrayList2);
    }

    private final Pair<FileSystem, Path> toFileRoot(URL url) {
        if (CloseableKt.areEqual(url.getProtocol(), "file")) {
            return new com.shadow.kotlin.Pair(this.systemFileSystem, Path.Companion.get$default(Path.Companion, new File(url.toURI()), false, 1, (Object) null));
        }
        return null;
    }

    private final Pair<FileSystem, Path> toJarRoot(URL url) {
        int iLastIndexOf;
        String string = url.toString();
        CloseableKt.checkNotNullExpressionValue(string, "toString(...)");
        if (!StringsKt.r(string, "jar:file:", false) || (iLastIndexOf = string.lastIndexOf("!", StringsKt.getLastIndex(string))) == -1) {
            return null;
        }
        Path.Companion companion = Path.Companion;
        String strSubstring = string.substring(4, iLastIndexOf);
        CloseableKt.checkNotNullExpressionValue(strSubstring, "substring(...)");
        return new com.shadow.kotlin.Pair(ZipFilesKt.openZip(Path.Companion.get$default(companion, new File(URI.create(strSubstring)), false, 1, (Object) null), this.systemFileSystem, new Function1<ZipEntry, Boolean>() { // from class: com.shadow.okio.internal.ResourceFileSystem$toJarRoot$zip$1
            public final Boolean invoke(ZipEntry zipEntry) {
                CloseableKt.checkNotNullParameter(zipEntry, "entry");
                return Boolean.valueOf(ResourceFileSystem.Companion.keepPath(zipEntry.getCanonicalPath()));
            }
        }), ROOT);
    }

    private final String toRelativePath(Path path) {
        return canonicalizeInternal(path).relativeTo(ROOT).toString();
    }

    @Override // com.shadow.okio.FileSystem
    public Sink appendingSink(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        throw new IOException(this + " is read-only");
    }

    @Override // com.shadow.okio.FileSystem
    public void atomicMove(Path path, Path path2) throws IOException {
        CloseableKt.checkNotNullParameter(path, "source");
        CloseableKt.checkNotNullParameter(path2, "target");
        throw new IOException(this + " is read-only");
    }

    @Override // com.shadow.okio.FileSystem
    public Path canonicalize(Path path) {
        CloseableKt.checkNotNullParameter(path, "path");
        return canonicalizeInternal(path);
    }

    @Override // com.shadow.okio.FileSystem
    public void createDirectory(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "dir");
        throw new IOException(this + " is read-only");
    }

    @Override // com.shadow.okio.FileSystem
    public void createSymlink(Path path, Path path2) throws IOException {
        CloseableKt.checkNotNullParameter(path, "source");
        CloseableKt.checkNotNullParameter(path2, "target");
        throw new IOException(this + " is read-only");
    }

    @Override // com.shadow.okio.FileSystem
    public void delete(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "path");
        throw new IOException(this + " is read-only");
    }

    @Override // com.shadow.okio.FileSystem
    public List<Path> list(Path path) throws FileNotFoundException {
        CloseableKt.checkNotNullParameter(path, "dir");
        String relativePath = toRelativePath(path);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        boolean z = false;
        for (com.shadow.kotlin.Pair pair : getRoots()) {
            FileSystem fileSystem = (FileSystem) pair.component1();
            Path path2 = (Path) pair.component2();
            try {
                List<Path> list = fileSystem.list(path2.resolve(relativePath));
                ArrayList arrayList = new ArrayList();
                for (Object obj : list) {
                    if (Companion.keepPath((Path) obj)) {
                        arrayList.add(obj);
                    }
                }
                ArrayList arrayList2 = new ArrayList(CollectionsKt.b(arrayList));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(Companion.removeBase((Path) it.next(), path2));
                }
                CollectionsKt.a(linkedHashSet, arrayList2);
                z = true;
            } catch (IOException unused) {
            }
        }
        if (z) {
            return CollectionsKt.g(linkedHashSet);
        }
        throw new FileNotFoundException(h.b(path, "file not found: "));
    }

    @Override // com.shadow.okio.FileSystem
    public List<Path> listOrNull(Path path) {
        CloseableKt.checkNotNullParameter(path, "dir");
        String relativePath = toRelativePath(path);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<Pair<FileSystem, Path>> it = getRoots().iterator();
        boolean z = false;
        while (true) {
            ArrayList arrayList = null;
            if (!it.hasNext()) {
                break;
            }
            com.shadow.kotlin.Pair next = it.next();
            FileSystem fileSystem = (FileSystem) next.component1();
            Path path2 = (Path) next.component2();
            List<Path> listListOrNull = fileSystem.listOrNull(path2.resolve(relativePath));
            if (listListOrNull != null) {
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : listListOrNull) {
                    if (Companion.keepPath((Path) obj)) {
                        arrayList2.add(obj);
                    }
                }
                ArrayList arrayList3 = new ArrayList(CollectionsKt.b(arrayList2));
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    arrayList3.add(Companion.removeBase((Path) it2.next(), path2));
                }
                arrayList = arrayList3;
            }
            if (arrayList != null) {
                CollectionsKt.a(linkedHashSet, arrayList);
                z = true;
            }
        }
        if (z) {
            return CollectionsKt.g(linkedHashSet);
        }
        return null;
    }

    @Override // com.shadow.okio.FileSystem
    public FileMetadata metadataOrNull(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "path");
        if (!Companion.keepPath(path)) {
            return null;
        }
        String relativePath = toRelativePath(path);
        for (com.shadow.kotlin.Pair pair : getRoots()) {
            FileMetadata fileMetadataMetadataOrNull = ((FileSystem) pair.component1()).metadataOrNull(((Path) pair.component2()).resolve(relativePath));
            if (fileMetadataMetadataOrNull != null) {
                return fileMetadataMetadataOrNull;
            }
        }
        return null;
    }

    @Override // com.shadow.okio.FileSystem
    public FileHandle openReadOnly(Path path) throws FileNotFoundException {
        CloseableKt.checkNotNullParameter(path, "file");
        if (!Companion.keepPath(path)) {
            throw new FileNotFoundException(h.b(path, "file not found: "));
        }
        String relativePath = toRelativePath(path);
        for (com.shadow.kotlin.Pair pair : getRoots()) {
            try {
                return ((FileSystem) pair.component1()).openReadOnly(((Path) pair.component2()).resolve(relativePath));
            } catch (FileNotFoundException unused) {
            }
        }
        throw new FileNotFoundException(h.b(path, "file not found: "));
    }

    @Override // com.shadow.okio.FileSystem
    public FileHandle openReadWrite(Path path, boolean z, boolean z2) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        throw new IOException("resources are not writable");
    }

    @Override // com.shadow.okio.FileSystem
    public Sink sink(Path path, boolean z) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        throw new IOException(this + " is read-only");
    }

    @Override // com.shadow.okio.FileSystem
    public Source source(Path path) throws IOException {
        CloseableKt.checkNotNullParameter(path, "file");
        if (!Companion.keepPath(path)) {
            throw new FileNotFoundException(h.b(path, "file not found: "));
        }
        Path path2 = ROOT;
        URL resource = this.classLoader.getResource(Path.resolve$default(path2, path, false, 2, (Object) null).relativeTo(path2).toString());
        if (resource == null) {
            throw new FileNotFoundException(h.b(path, "file not found: "));
        }
        URLConnection uRLConnectionOpenConnection = resource.openConnection();
        if (uRLConnectionOpenConnection instanceof JarURLConnection) {
            ((JarURLConnection) uRLConnectionOpenConnection).setUseCaches(false);
        }
        InputStream inputStream = uRLConnectionOpenConnection.getInputStream();
        CloseableKt.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
        return Okio.source(inputStream);
    }

    public ResourceFileSystem(ClassLoader classLoader, boolean z, FileSystem fileSystem) {
        CloseableKt.checkNotNullParameter(classLoader, "classLoader");
        CloseableKt.checkNotNullParameter(fileSystem, "systemFileSystem");
        this.classLoader = classLoader;
        this.systemFileSystem = fileSystem;
        this.roots$delegate = LazyKt.b(new Function0<List<? extends Pair<? extends FileSystem, ? extends Path>>>() { // from class: com.shadow.okio.internal.ResourceFileSystem$roots$2
            {
                super(0);
            }

            public final List<Pair<FileSystem, Path>> invoke() {
                ResourceFileSystem resourceFileSystem = this.this$0;
                return resourceFileSystem.toClasspathRoots(resourceFileSystem.classLoader);
            }
        });
        if (z) {
            getRoots().size();
        }
    }
}
