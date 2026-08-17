package com.sun.tools.javac.file;

import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import com.sun.tools.javac.file.JavacFileManager;
import com.sun.tools.javac.file.RelativePath;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Options;
import defpackage.aca;
import defpackage.dm7;
import defpackage.s22;
import defpackage.u8i;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.ProviderNotFoundException;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.zip.ZipException;
import javax.lang.model.SourceVersion;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavacFileManager extends BaseFileManager implements StandardJavaFileManager {
    private static final boolean fileSystemIsCaseSensitive;
    private final Map<Path, Container> containers;
    private FSInfo fsInfo;
    private JRTIndex jrtIndex;
    private Map<JavaFileManager.Location, List<PathAndContainer>> nonIndexingContainersByLocation;
    private StandardJavaFileManager.PathFactory pathFactory;
    private Map<JavaFileManager.Location, Map<RelativePath.RelativeDirectory, List<PathAndContainer>>> pathsAndContainersByLocationAndRelativeDirectory;
    protected SortFiles sortFiles;
    protected boolean symbolFileEnabled;
    private static final Set<JavaFileObject.Kind> SOURCE_OR_CLASS = Set.of(JavaFileObject.Kind.SOURCE, JavaFileObject.Kind.CLASS);
    private static final Container MISSING_CONTAINER = new Container() { // from class: com.sun.tools.javac.file.JavacFileManager.1
        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public void close() throws IOException {
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public JavaFileObject getFileObject(Path path, RelativePath.RelativeFile relativeFile) throws IOException {
            return null;
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public Iterable<RelativePath.RelativeDirectory> indexedDirectories() {
            return com.sun.tools.javac.util.List.nil();
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public void list(Path path, RelativePath.RelativeDirectory relativeDirectory, Set<JavaFileObject.Kind> set, boolean z, ListBuffer<JavaFileObject> listBuffer) throws IOException {
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public boolean maintainsDirectoryIndex() {
            return false;
        }
    };
    private static final Set<FileVisitOption> NO_FILE_VISIT_OPTIONS = Collections.EMPTY_SET;
    private static final Set<FileVisitOption> FOLLOW_LINKS_OPTIONS = Set.of(FileVisitOption.FOLLOW_LINKS);

    public final class ArchiveContainer implements Container {
        private final Path archivePath;
        private final FileSystem fileSystem;
        private final Map<RelativePath.RelativeDirectory, Path> packages;

        public ArchiveContainer(Path path) throws IOException, ProviderNotFoundException {
            this.archivePath = path;
            if (JavacFileManager.this.multiReleaseValue == null || !path.toString().endsWith(".jar")) {
                this.fileSystem = FileSystems.newFileSystem(path, JavacFileManager.this.fsInfo.readOnlyJarFSEnv(null));
            } else {
                FileSystemProvider jarFSProvider = JavacFileManager.this.fsInfo.getJarFSProvider();
                Assert.checkNonNull(jarFSProvider, "should have been caught before!");
                try {
                    this.fileSystem = jarFSProvider.newFileSystem(path, JavacFileManager.this.fsInfo.readOnlyJarFSEnv(JavacFileManager.this.multiReleaseValue));
                } catch (ZipException e) {
                    StringBuilder sb = new StringBuilder("ZipException opening \"");
                    sb.append(path.getFileName());
                    String message = e.getMessage();
                    sb.append("\": ");
                    sb.append(message);
                    throw new IOException(sb.toString(), e);
                }
            }
            this.packages = new HashMap();
            for (final Path path2 : this.fileSystem.getRootDirectories()) {
                Files.walkFileTree(path2, JavacFileManager.NO_FILE_VISIT_OPTIONS, Integer.MAX_VALUE, new SimpleFileVisitor<Path>() { // from class: com.sun.tools.javac.file.JavacFileManager.ArchiveContainer.1
                    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                    public FileVisitResult preVisitDirectory(Path path3, BasicFileAttributes basicFileAttributes) {
                        if (!ArchiveContainer.this.isValid(path3.getFileName())) {
                            return FileVisitResult.SKIP_SUBTREE;
                        }
                        ArchiveContainer.this.packages.put(new RelativePath.RelativeDirectory(path2.relativize(path3).toString()), path3);
                        return FileVisitResult.CONTINUE;
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isValid(Path path) {
            if (path == null) {
                return true;
            }
            String string = path.toString();
            if (string.endsWith(PsuedoNames.PSEUDONAME_ROOT)) {
                string = string.substring(0, string.length() - 1);
            }
            return SourceVersion.isIdentifier(string);
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public void close() throws IOException {
            this.fileSystem.close();
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public JavaFileObject getFileObject(Path path, RelativePath.RelativeFile relativeFile) throws IOException {
            Path path2 = this.packages.get(relativeFile.dirname());
            if (path2 == null) {
                return null;
            }
            Path pathResolve = path2.resolve(relativeFile.basename());
            if (Files.exists(pathResolve, new LinkOption[0])) {
                return PathFileObject.forJarPath(JavacFileManager.this, pathResolve, path);
            }
            return null;
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public Iterable<RelativePath.RelativeDirectory> indexedDirectories() {
            return this.packages.keySet();
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public void list(Path path, RelativePath.RelativeDirectory relativeDirectory, final Set<JavaFileObject.Kind> set, boolean z, final ListBuffer<JavaFileObject> listBuffer) throws IOException {
            Path path2 = this.packages.get(relativeDirectory);
            if (path2 == null) {
                return;
            }
            Files.walkFileTree(path2, JavacFileManager.FOLLOW_LINKS_OPTIONS, z ? Integer.MAX_VALUE : 1, new SimpleFileVisitor<Path>() { // from class: com.sun.tools.javac.file.JavacFileManager.ArchiveContainer.2
                @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                public FileVisitResult preVisitDirectory(Path path3, BasicFileAttributes basicFileAttributes) {
                    return ArchiveContainer.this.isValid(path3.getFileName()) ? FileVisitResult.CONTINUE : FileVisitResult.SKIP_SUBTREE;
                }

                @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
                public FileVisitResult visitFile(Path path3, BasicFileAttributes basicFileAttributes) {
                    if (basicFileAttributes.isRegularFile() && set.contains(BaseFileManager.getKind(path3.getFileName().toString()))) {
                        ArchiveContainer archiveContainer = ArchiveContainer.this;
                        listBuffer.append(PathFileObject.forJarPath(JavacFileManager.this, path3, archiveContainer.archivePath));
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public boolean maintainsDirectoryIndex() {
            return true;
        }
    }

    public interface Container {
        void close() throws IOException;

        JavaFileObject getFileObject(Path path, RelativePath.RelativeFile relativeFile) throws IOException;

        Iterable<RelativePath.RelativeDirectory> indexedDirectories();

        void list(Path path, RelativePath.RelativeDirectory relativeDirectory, Set<JavaFileObject.Kind> set, boolean z, ListBuffer<JavaFileObject> listBuffer) throws IOException;

        boolean maintainsDirectoryIndex();
    }

    public final class DirectoryContainer implements Container {
        private final Path directory;

        public DirectoryContainer(Path path) {
            this.directory = path;
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public void close() throws IOException {
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public JavaFileObject getFileObject(Path path, RelativePath.RelativeFile relativeFile) throws IOException {
            try {
                Path pathResolveAgainst = relativeFile.resolveAgainst(path);
                if (!Files.exists(pathResolveAgainst, new LinkOption[0])) {
                    return null;
                }
                JavacFileManager javacFileManager = JavacFileManager.this;
                return PathFileObject.forSimplePath(javacFileManager, javacFileManager.fsInfo.getCanonicalFile(pathResolveAgainst), pathResolveAgainst);
            } catch (InvalidPathException unused) {
                return null;
            }
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public Iterable<RelativePath.RelativeDirectory> indexedDirectories() {
            return com.sun.tools.javac.util.List.nil();
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public void list(Path path, RelativePath.RelativeDirectory relativeDirectory, Set<JavaFileObject.Kind> set, boolean z, ListBuffer<JavaFileObject> listBuffer) throws IOException {
            DirectoryContainer directoryContainer;
            Path path2;
            Set<JavaFileObject.Kind> set2;
            boolean z2;
            ListBuffer<JavaFileObject> listBuffer2;
            try {
                Path pathResolveAgainst = relativeDirectory.resolveAgainst(path);
                if (Files.exists(pathResolveAgainst, new LinkOption[0]) && JavacFileManager.this.caseMapCheck(pathResolveAgainst, relativeDirectory)) {
                    Stream<Path> list = Files.list(pathResolveAgainst);
                    try {
                        SortFiles sortFiles = JavacFileManager.this.sortFiles;
                        List<Path> list2 = (sortFiles == null ? list : list.sorted(sortFiles)).toList();
                        if (list != null) {
                            list.close();
                        }
                        for (Path path3 : list2) {
                            String string = path3.getFileName().toString();
                            if (string.endsWith(PsuedoNames.PSEUDONAME_ROOT)) {
                                string = string.substring(0, string.length() - 1);
                            }
                            if (!Files.isDirectory(path3, new LinkOption[0])) {
                                directoryContainer = this;
                                path2 = path;
                                set2 = set;
                                z2 = z;
                                listBuffer2 = listBuffer;
                                if (JavacFileManager.this.isValidFile(string, set2)) {
                                    try {
                                        RelativePath.RelativeFile relativeFile = new RelativePath.RelativeFile(relativeDirectory, string);
                                        listBuffer2.append(PathFileObject.forDirectoryPath(JavacFileManager.this, relativeFile.resolveAgainst(directoryContainer.directory), path2, relativeFile));
                                    } catch (InvalidPathException e) {
                                        to0.a("error accessing directory ", directoryContainer.directory, e);
                                        return;
                                    }
                                } else {
                                    continue;
                                }
                            } else if (z && SourceVersion.isIdentifier(string)) {
                                directoryContainer = this;
                                path2 = path;
                                set2 = set;
                                z2 = z;
                                listBuffer2 = listBuffer;
                                directoryContainer.list(path2, new RelativePath.RelativeDirectory(relativeDirectory, string), set2, z2, listBuffer2);
                            } else {
                                directoryContainer = this;
                                path2 = path;
                                set2 = set;
                                z2 = z;
                                listBuffer2 = listBuffer;
                            }
                            this = directoryContainer;
                            path = path2;
                            set = set2;
                            z = z2;
                            listBuffer = listBuffer2;
                        }
                    } catch (Throwable th) {
                        if (list == null) {
                            throw th;
                        }
                        try {
                            list.close();
                            throw th;
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                            throw th;
                        }
                    }
                }
            } catch (IOException | InvalidPathException unused) {
            }
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public boolean maintainsDirectoryIndex() {
            return false;
        }
    }

    public final class JRTImageContainer implements Container {
        private JRTImageContainer() {
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public void close() throws IOException {
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public JavaFileObject getFileObject(Path path, RelativePath.RelativeFile relativeFile) throws Throwable {
            Path path2;
            JRTIndex.Entry entry = JavacFileManager.this.getJRTIndex().getEntry(relativeFile.dirname());
            if ((JavacFileManager.this.symbolFileEnabled && entry.ctSym.hidden) || (path2 = entry.files.get(relativeFile.basename())) == null) {
                return null;
            }
            return PathFileObject.forJRTPath(JavacFileManager.this, path2);
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public Iterable<RelativePath.RelativeDirectory> indexedDirectories() {
            return com.sun.tools.javac.util.List.nil();
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public void list(Path path, RelativePath.RelativeDirectory relativeDirectory, Set<JavaFileObject.Kind> set, boolean z, ListBuffer<JavaFileObject> listBuffer) throws Throwable {
            JRTImageContainer jRTImageContainer;
            Path path2;
            IOException iOException;
            try {
                JRTIndex.Entry entry = JavacFileManager.this.getJRTIndex().getEntry(relativeDirectory);
                if (JavacFileManager.this.symbolFileEnabled) {
                    try {
                        if (entry.ctSym.hidden) {
                            return;
                        }
                    } catch (IOException e) {
                        iOException = e;
                        jRTImageContainer = this;
                        path2 = path;
                    }
                }
                for (Path path3 : entry.files.values()) {
                    if (set.contains(BaseFileManager.getKind(path3))) {
                        listBuffer.append(PathFileObject.forJRTPath(JavacFileManager.this, path3));
                    }
                }
                if (z) {
                    Iterator<RelativePath.RelativeDirectory> it = entry.subdirs.iterator();
                    while (it.hasNext()) {
                        jRTImageContainer = this;
                        path2 = path;
                        Set<JavaFileObject.Kind> set2 = set;
                        boolean z2 = z;
                        ListBuffer<JavaFileObject> listBuffer2 = listBuffer;
                        try {
                            jRTImageContainer.list(path2, it.next(), set2, z2, listBuffer2);
                            this = jRTImageContainer;
                            path = path2;
                            set = set2;
                            z = z2;
                            listBuffer = listBuffer2;
                        } catch (IOException e2) {
                            e = e2;
                        }
                    }
                    return;
                }
                return;
            } catch (IOException e3) {
                e = e3;
                jRTImageContainer = this;
                path2 = path;
            }
            iOException = e;
            iOException.printStackTrace(System.err);
            JavacFileManager.this.log.error(CompilerProperties.Errors.ErrorReadingFile(path2, JavacFileManager.getMessage(iOException)));
        }

        @Override // com.sun.tools.javac.file.JavacFileManager.Container
        public boolean maintainsDirectoryIndex() {
            return false;
        }
    }

    static {
        fileSystemIsCaseSensitive = File.separatorChar == '/';
    }

    public JavacFileManager(Context context, boolean z, Charset charset) {
        super(charset);
        this.symbolFileEnabled = true;
        this.pathFactory = new dm7();
        this.pathsAndContainersByLocationAndRelativeDirectory = new HashMap();
        this.nonIndexingContainersByLocation = new HashMap();
        this.containers = new HashMap();
        if (z) {
            context.put((Class<JavacFileManager>) JavaFileManager.class, this);
        }
        setContext(context);
    }

    private static Iterable<File> asFiles(final Iterable<? extends Path> iterable) {
        if (iterable == null) {
            return null;
        }
        return new Iterable() { // from class: zl7
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return JavacFileManager.f(iterable);
            }
        };
    }

    private static Iterable<Path> asPaths(final Iterable<? extends File> iterable) {
        if (iterable == null) {
            return null;
        }
        return new Iterable() { // from class: cm7
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                return JavacFileManager.d(iterable);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean caseMapCheck(Path path, RelativePath relativePath) {
        if (fileSystemIsCaseSensitive) {
            return true;
        }
        try {
            String string = path.toRealPath(LinkOption.NOFOLLOW_LINKS).toString();
            char cCharAt = path.getFileSystem().getSeparator().charAt(0);
            char[] charArray = string.toCharArray();
            char[] charArray2 = relativePath.path.toCharArray();
            int length = charArray.length - 1;
            int length2 = charArray2.length - 1;
            while (length >= 0 && length2 >= 0) {
                while (length >= 0 && charArray[length] == cCharAt) {
                    length--;
                }
                while (length2 >= 0 && charArray2[length2] == '/') {
                    length2--;
                }
                if (length >= 0 && length2 >= 0) {
                    if (charArray[length] != charArray2[length2]) {
                        return false;
                    }
                    length--;
                    length2--;
                }
            }
            return length2 < 0;
        } catch (IOException unused) {
        }
    }

    private void checkModuleOrientedOrOutputLocation(JavaFileManager.Location location) {
        Objects.requireNonNull(location);
        if (location.isModuleOrientedLocation() || location.isOutputLocation()) {
            return;
        }
        z01.a("location is not an output location or a module-oriented location: ", location.getName());
    }

    private void checkNotModuleOrientedLocation(JavaFileManager.Location location) {
        Objects.requireNonNull(location);
        if (location.isModuleOrientedLocation()) {
            z01.a("location is module-oriented: ", location.getName());
        }
    }

    private void checkOutputLocation(JavaFileManager.Location location) {
        Objects.requireNonNull(location);
        if (location.isOutputLocation()) {
            return;
        }
        z01.a("location is not an output location: ", location.getName());
    }

    private void clearCachesForLocation(JavaFileManager.Location location) {
        BaseFileManager.nullCheck(location);
        this.pathsAndContainersByLocationAndRelativeDirectory.remove(location);
        this.nonIndexingContainersByLocation.remove(location);
    }

    public static /* synthetic */ Iterator d(Iterable iterable) {
        return new Iterator<Path>(iterable) { // from class: com.sun.tools.javac.file.JavacFileManager.2
            Iterator<? extends File> iter;
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

    public static /* synthetic */ JavaFileManager e(Context context) {
        return new JavacFileManager(context, true, null);
    }

    public static /* synthetic */ Iterator f(Iterable iterable) {
        return new Iterator<File>(iterable) { // from class: com.sun.tools.javac.file.JavacFileManager.3
            Iterator<? extends Path> iter;
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
                try {
                    return this.iter.next().toFile();
                } catch (UnsupportedOperationException e) {
                    e7f.a(e);
                    return null;
                }
            }
        };
    }

    private Path getClassOutDir() {
        return this.locations.getOutputLocation(StandardLocation.CLASS_OUTPUT);
    }

    private JavaFileObject getFileForOutput(JavaFileManager.Location location, RelativePath.RelativeFile relativeFile, FileObject fileObject) throws IOException {
        Path sourceOutDir;
        if (location == StandardLocation.CLASS_OUTPUT) {
            if (getClassOutDir() == null) {
                String strBasename = relativeFile.basename();
                if (fileObject instanceof PathFileObject) {
                    PathFileObject pathFileObject = (PathFileObject) fileObject;
                    if (!pathFileObject.isJarFile()) {
                        return pathFileObject.getSibling(strBasename);
                    }
                }
                Path path = getPath(strBasename, new String[0]);
                return PathFileObject.forSimplePath(this, this.fsInfo.getCanonicalFile(path), path);
            }
            sourceOutDir = getClassOutDir();
        } else if (location == StandardLocation.SOURCE_OUTPUT) {
            sourceOutDir = getSourceOutDir() != null ? getSourceOutDir() : getClassOutDir();
        } else {
            Iterator<T> it = this.locations.getLocation(location).iterator();
            sourceOutDir = it.hasNext() ? (Path) it.next() : null;
        }
        if (sourceOutDir == null) {
            try {
                sourceOutDir = getPath(System.getProperty("user.dir"), new String[0]);
            } catch (InvalidPathException e) {
                cia.a("bad filename ", relativeFile, e);
                return null;
            }
        }
        return PathFileObject.forDirectoryPath(this, relativeFile.resolveAgainst(this.fsInfo.getCanonicalFile(sourceOutDir)), sourceOutDir, relativeFile);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized JRTIndex getJRTIndex() {
        try {
            if (this.jrtIndex == null) {
                this.jrtIndex = JRTIndex.getSharedInstance();
            }
        } catch (Throwable th) {
            throw th;
        }
        return this.jrtIndex;
    }

    public static String getMessage(IOException iOException) {
        String localizedMessage = iOException.getLocalizedMessage();
        if (localizedMessage != null) {
            return localizedMessage;
        }
        String message = iOException.getMessage();
        return message != null ? message : iOException.toString();
    }

    private Path getPath(String str, String... strArr) {
        return this.pathFactory.getPath(str, strArr);
    }

    public static String getRelativeName(File file) {
        if (!file.isAbsolute()) {
            String strReplace = file.getPath().replace(File.separatorChar, '/');
            if (isRelativeUri(strReplace)) {
                return strReplace;
            }
        }
        aca.a("Invalid relative path: ", file);
        return null;
    }

    private Path getSourceOutDir() {
        return this.locations.getOutputLocation(StandardLocation.SOURCE_OUTPUT);
    }

    public static /* synthetic */ List i(List list, RelativePath.RelativeDirectory relativeDirectory) {
        return new ArrayList(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<RelativePath.RelativeDirectory, List<PathAndContainer>> indexPathsAndContainersByRelativeDirectory(JavaFileManager.Location location) {
        HashMap map = new HashMap();
        List<PathAndContainer> listPathsAndContainers = pathsAndContainers(location);
        final ArrayList arrayList = new ArrayList();
        for (PathAndContainer pathAndContainer : listPathsAndContainers) {
            if (!pathAndContainer.container.maintainsDirectoryIndex()) {
                arrayList.add(pathAndContainer);
            }
        }
        for (PathAndContainer pathAndContainer2 : listPathsAndContainers) {
            Container container = pathAndContainer2.container;
            if (container.maintainsDirectoryIndex()) {
                Iterator<RelativePath.RelativeDirectory> it = container.indexedDirectories().iterator();
                while (it.hasNext()) {
                    ((List) map.computeIfAbsent(it.next(), new Function() { // from class: am7
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return JavacFileManager.i(arrayList, (RelativePath.RelativeDirectory) obj);
                        }
                    })).add(pathAndContainer2);
                }
            }
        }
        this.nonIndexingContainersByLocation.put(location, arrayList);
        map.values().forEach(new Consumer() { // from class: bm7
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Collections.sort((List) obj);
            }
        });
        return map;
    }

    public static boolean isRelativeUri(URI uri) {
        if (uri.isAbsolute()) {
            return false;
        }
        String path = uri.normalize().getPath();
        return (path.length() == 0 || !path.equals(uri.getPath()) || path.startsWith(PsuedoNames.PSEUDONAME_ROOT) || path.startsWith("./") || path.startsWith("../")) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isValidFile(String str, Set<JavaFileObject.Kind> set) {
        return set.contains(BaseFileManager.getKind(str));
    }

    private static boolean isValidName(String str) {
        for (String str2 : str.split("\\.", -1)) {
            if (!SourceVersion.isIdentifier(str2)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$pathsAndContainers$1(JavaFileManager.Location location, RelativePath.RelativeDirectory relativeDirectory) {
        return this.nonIndexingContainersByLocation.get(location);
    }

    private List<PathAndContainer> pathsAndContainers(JavaFileManager.Location location) {
        Collection<? extends Path> locationAsPaths = getLocationAsPaths(location);
        if (locationAsPaths == null) {
            return com.sun.tools.javac.util.List.nil();
        }
        ArrayList arrayList = new ArrayList(locationAsPaths.size());
        for (Path path : locationAsPaths) {
            try {
                arrayList.add(new PathAndContainer(path, getContainer(path), arrayList.size()));
            } catch (IOException e) {
                u8i.a(e);
                return null;
            }
        }
        return arrayList;
    }

    public static void preRegister(Context context) {
        context.put(JavaFileManager.class, new Context.Factory() { // from class: yl7
            @Override // com.sun.tools.javac.util.Context.Factory
            public final Object make(Context context2) {
                return JavacFileManager.e(context2);
            }
        });
    }

    private static void printAscii(String str, Object... objArr) {
        String str2 = String.format(null, str, objArr);
        Charset charset = StandardCharsets.US_ASCII;
        System.out.println(new String(str2.getBytes(charset), charset));
    }

    public static void testName(String str, boolean z, boolean z2) {
        try {
            validatePackageName(str);
            if (!z) {
                throw new AssertionError("Invalid package name accepted: " + str);
            }
            printAscii("Valid package name: \"%s\"", str);
            try {
                validateClassName(str);
                if (z2) {
                    printAscii("Valid class name: \"%s\"", str);
                } else {
                    throw new AssertionError("Invalid class name accepted: " + str);
                }
            } catch (IllegalArgumentException unused) {
                if (z2) {
                    s22.a("Valid class name rejected: ", str);
                } else {
                    printAscii("Invalid class name: \"%s\"", str);
                }
            }
        } catch (IllegalArgumentException unused2) {
            if (z) {
                s22.a("Valid package name rejected: ", str);
                return;
            }
            printAscii("Invalid package name: \"%s\"", str);
        }
    }

    public static char[] toArray(CharBuffer charBuffer) {
        return charBuffer.hasArray() ? (char[]) charBuffer.compact().flip().array() : charBuffer.toString().toCharArray();
    }

    private static void validateClassName(String str) {
        if (isValidName(str)) {
            return;
        }
        aca.a("Invalid class name: ", str);
    }

    private static void validatePackageName(String str) {
        if (str.length() <= 0 || isValidName(str)) {
            return;
        }
        w01.a("Invalid packageName name: ".concat(str));
    }

    @Override // com.sun.tools.javac.file.BaseFileManager
    public void applyOptions(Options options) {
        super.applyOptions(options);
        this.symbolFileEnabled = !options.isSet("ignore.symbol.file");
        String str = options.get("sortFiles");
        if (str != null) {
            this.sortFiles = str.equals("reverse") ? SortFiles.REVERSE : SortFiles.FORWARD;
        }
    }

    @Override // javax.tools.StandardJavaFileManager
    public Path asPath(FileObject fileObject) {
        if (fileObject instanceof PathFileObject) {
            return ((PathFileObject) fileObject).path;
        }
        w01.a(fileObject.getName());
        return null;
    }

    @Override // javax.tools.JavaFileManager, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.deferredCloseTimeout > 0) {
            deferredClose();
            return;
        }
        this.locations.close();
        Iterator<Container> it = this.containers.values().iterator();
        while (it.hasNext()) {
            it.next().close();
        }
        this.containers.clear();
        this.pathsAndContainersByLocationAndRelativeDirectory.clear();
        this.nonIndexingContainersByLocation.clear();
        this.contentCache.clear();
        resetOutputFilesWritten();
    }

    @Override // javax.tools.JavaFileManager
    public boolean contains(JavaFileManager.Location location, FileObject fileObject) throws IOException {
        BaseFileManager.nullCheck(location);
        BaseFileManager.nullCheck(fileObject);
        return this.locations.contains(location, asPath(fileObject));
    }

    @Override // javax.tools.JavaFileManager, java.io.Flushable
    public void flush() {
        this.contentCache.clear();
        this.pathsAndContainersByLocationAndRelativeDirectory.clear();
        this.nonIndexingContainersByLocation.clear();
    }

    @Override // javax.tools.JavaFileManager
    public ClassLoader getClassLoader(JavaFileManager.Location location) {
        checkNotModuleOrientedLocation(location);
        Iterable<? extends File> location2 = getLocation(location);
        if (location2 == null) {
            return null;
        }
        ListBuffer listBuffer = new ListBuffer();
        Iterator<? extends File> it = location2.iterator();
        while (it.hasNext()) {
            try {
                listBuffer.append(it.next().toURI().toURL());
            } catch (MalformedURLException e) {
                x01.a(e);
                return null;
            }
        }
        return getClassLoader((URL[]) listBuffer.toArray(new URL[listBuffer.size()]));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public synchronized Container getContainer(Path path) throws IOException {
        Container container = this.containers.get(path);
        if (container != null) {
            return container;
        }
        BasicFileAttributes attributes = null;
        Object[] objArr = 0;
        if (this.fsInfo.isFile(path) && path.equals(Locations.thisSystemModules)) {
            Map<Path, Container> map = this.containers;
            JRTImageContainer jRTImageContainer = new JRTImageContainer();
            map.put(path, jRTImageContainer);
            return jRTImageContainer;
        }
        Path canonicalFile = this.fsInfo.getCanonicalFile(path);
        Container archiveContainer = this.containers.get(canonicalFile);
        if (archiveContainer != null) {
            this.containers.put(path, archiveContainer);
            return archiveContainer;
        }
        try {
            attributes = Files.readAttributes(canonicalFile, (Class<BasicFileAttributes>) BasicFileAttributes.class, new LinkOption[0]);
        } catch (IOException unused) {
            archiveContainer = MISSING_CONTAINER;
        }
        if (attributes != null) {
            if (attributes.isDirectory()) {
                archiveContainer = new DirectoryContainer(canonicalFile);
            } else {
                try {
                    archiveContainer = new ArchiveContainer(path);
                } catch (ProviderNotFoundException e) {
                    throw new IOException(e);
                }
            }
        }
        this.containers.put(canonicalFile, archiveContainer);
        this.containers.put(path, archiveContainer);
        return archiveContainer;
    }

    @Override // javax.tools.JavaFileManager
    public FileObject getFileForInput(JavaFileManager.Location location, String str, String str2) throws IOException {
        checkNotModuleOrientedLocation(location);
        BaseFileManager.nullCheck(str);
        if (isRelativeUri(str2)) {
            return getFileForInput(location, str.length() == 0 ? new RelativePath.RelativeFile(str2) : new RelativePath.RelativeFile(RelativePath.RelativeDirectory.forPackage(str), str2));
        }
        aca.a("Invalid relative name: ", str2);
        return null;
    }

    @Override // javax.tools.JavaFileManager
    public JavaFileObject getJavaFileForInput(JavaFileManager.Location location, String str, JavaFileObject.Kind kind) throws IOException {
        checkNotModuleOrientedLocation(location);
        BaseFileManager.nullCheck(str);
        BaseFileManager.nullCheck(kind);
        if (SOURCE_OR_CLASS.contains(kind)) {
            return getFileForInput(location, RelativePath.RelativeFile.forClass(str, kind));
        }
        aca.a("Invalid kind: ", kind);
        return null;
    }

    @Override // javax.tools.JavaFileManager
    public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, String str, JavaFileObject.Kind kind, FileObject fileObject) throws IOException {
        checkOutputLocation(location);
        BaseFileManager.nullCheck(str);
        BaseFileManager.nullCheck(kind);
        if (SOURCE_OR_CLASS.contains(kind)) {
            return getFileForOutput(location, RelativePath.RelativeFile.forClass(str, kind), fileObject);
        }
        aca.a("Invalid kind: ", kind);
        return null;
    }

    public JavaFileObject getJavaFileObject(Path path) {
        return getJavaFileObjects(path).iterator().next();
    }

    @Override // javax.tools.StandardJavaFileManager
    public Iterable<? extends JavaFileObject> getJavaFileObjects(String... strArr) {
        return getJavaFileObjectsFromStrings(Arrays.asList((String[]) BaseFileManager.nullCheck(strArr)));
    }

    @Override // javax.tools.StandardJavaFileManager
    public Iterable<? extends JavaFileObject> getJavaFileObjectsFromFiles(Iterable<? extends File> iterable) {
        ArrayList arrayList = iterable instanceof Collection ? new ArrayList(((Collection) iterable).size()) : new ArrayList();
        for (File file : iterable) {
            Objects.requireNonNull(file);
            Path path = file.toPath();
            arrayList.add(PathFileObject.forSimplePath(this, this.fsInfo.getCanonicalFile(path), path));
        }
        return arrayList;
    }

    @Override // javax.tools.StandardJavaFileManager
    public Iterable<? extends JavaFileObject> getJavaFileObjectsFromPaths(Collection<? extends Path> collection) {
        if (collection == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (Path path : collection) {
            arrayList.add(PathFileObject.forSimplePath(this, this.fsInfo.getCanonicalFile(path), path));
        }
        return arrayList;
    }

    @Override // javax.tools.StandardJavaFileManager
    public Iterable<? extends JavaFileObject> getJavaFileObjectsFromStrings(Iterable<String> iterable) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<String> it = iterable.iterator();
        while (it.hasNext()) {
            listBuffer.append(getPath((String) BaseFileManager.nullCheck(it.next()), new String[0]));
        }
        return getJavaFileObjectsFromPaths((Collection<? extends Path>) listBuffer.toList());
    }

    @Override // javax.tools.StandardJavaFileManager
    public Iterable<? extends File> getLocation(JavaFileManager.Location location) {
        BaseFileManager.nullCheck(location);
        return asFiles(this.locations.getLocation(location));
    }

    @Override // javax.tools.StandardJavaFileManager
    public Collection<? extends Path> getLocationAsPaths(JavaFileManager.Location location) {
        BaseFileManager.nullCheck(location);
        return this.locations.getLocation(location);
    }

    @Override // javax.tools.JavaFileManager
    public JavaFileManager.Location getLocationForModule(JavaFileManager.Location location, String str) throws IOException {
        checkModuleOrientedOrOutputLocation(location);
        BaseFileManager.nullCheck(str);
        if (location == StandardLocation.SOURCE_OUTPUT && getSourceOutDir() == null) {
            location = StandardLocation.CLASS_OUTPUT;
        }
        return this.locations.getLocationForModule(location, str);
    }

    @Override // javax.tools.JavaFileManager
    public <S> ServiceLoader<S> getServiceLoader(JavaFileManager.Location location, Class<S> cls) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // com.sun.tools.javac.file.BaseFileManager
    public boolean handleOption(Option option, String str) {
        if (BaseFileManager.javacFileManagerOptions.contains(option) && option != Option.MULTIRELEASE) {
            this.pathsAndContainersByLocationAndRelativeDirectory.clear();
            this.nonIndexingContainersByLocation.clear();
        }
        return super.handleOption(option, str);
    }

    public boolean hasExplicitLocation(JavaFileManager.Location location) {
        BaseFileManager.nullCheck(location);
        return this.locations.hasExplicitLocation(location);
    }

    @Override // javax.tools.JavaFileManager
    public boolean hasLocation(JavaFileManager.Location location) {
        BaseFileManager.nullCheck(location);
        return this.locations.hasLocation(location);
    }

    @Override // javax.tools.JavaFileManager
    public String inferBinaryName(JavaFileManager.Location location, JavaFileObject javaFileObject) {
        checkNotModuleOrientedLocation(location);
        Objects.requireNonNull(javaFileObject);
        Collection<? extends Path> locationAsPaths = getLocationAsPaths(location);
        if (locationAsPaths == null) {
            return null;
        }
        if (javaFileObject instanceof PathFileObject) {
            return ((PathFileObject) javaFileObject).inferBinaryName(locationAsPaths);
        }
        w01.a(javaFileObject.getClass().getName());
        return null;
    }

    @Override // javax.tools.JavaFileManager
    public String inferModuleName(JavaFileManager.Location location) {
        checkNotModuleOrientedLocation(location);
        return this.locations.inferModuleName(location);
    }

    @Override // javax.tools.JavaFileManager
    public boolean isSameFile(FileObject fileObject, FileObject fileObject2) {
        BaseFileManager.nullCheck(fileObject);
        BaseFileManager.nullCheck(fileObject2);
        if (fileObject instanceof PathFileObject) {
            PathFileObject pathFileObject = (PathFileObject) fileObject;
            if (fileObject2 instanceof PathFileObject) {
                return pathFileObject.isSameFile((PathFileObject) fileObject2);
            }
        }
        return fileObject.equals(fileObject2);
    }

    public boolean isSymbolFileEnabled() {
        return this.symbolFileEnabled;
    }

    @Override // javax.tools.JavaFileManager
    public Iterable<JavaFileObject> list(JavaFileManager.Location location, String str, Set<JavaFileObject.Kind> set, boolean z) throws IOException {
        checkNotModuleOrientedLocation(location);
        BaseFileManager.nullCheck(str);
        BaseFileManager.nullCheck((Collection) set);
        RelativePath.RelativeDirectory relativeDirectoryForPackage = RelativePath.RelativeDirectory.forPackage(str);
        ListBuffer<JavaFileObject> listBuffer = new ListBuffer<>();
        for (PathAndContainer pathAndContainer : pathsAndContainers(location, relativeDirectoryForPackage)) {
            pathAndContainer.container.list(pathAndContainer.path, relativeDirectoryForPackage, set, z, listBuffer);
        }
        return listBuffer.toList();
    }

    @Override // javax.tools.JavaFileManager
    public Iterable<Set<JavaFileManager.Location>> listLocationsForModules(JavaFileManager.Location location) throws IOException {
        checkModuleOrientedOrOutputLocation(location);
        return this.locations.listLocationsForModules(location);
    }

    @Override // com.sun.tools.javac.file.BaseFileManager
    public void setContext(Context context) {
        super.setContext(context);
        this.fsInfo = FSInfo.instance(context);
    }

    @Override // javax.tools.StandardJavaFileManager
    public void setLocation(JavaFileManager.Location location, Iterable<? extends File> iterable) throws IOException {
        BaseFileManager.nullCheck(location);
        this.locations.setLocation(location, asPaths(iterable));
        clearCachesForLocation(location);
    }

    @Override // javax.tools.StandardJavaFileManager
    public void setLocationForModule(JavaFileManager.Location location, String str, Collection<? extends Path> collection) throws IOException {
        BaseFileManager.nullCheck(location);
        checkModuleOrientedOrOutputLocation(location);
        this.locations.setLocationForModule(location, (String) BaseFileManager.nullCheck(str), BaseFileManager.nullCheck((Collection) collection));
        clearCachesForLocation(location);
    }

    @Override // javax.tools.StandardJavaFileManager
    public void setLocationFromPaths(JavaFileManager.Location location, Collection<? extends Path> collection) throws IOException {
        BaseFileManager.nullCheck(location);
        this.locations.setLocation(location, BaseFileManager.nullCheck((Collection) collection));
        clearCachesForLocation(location);
    }

    @Override // javax.tools.StandardJavaFileManager
    public void setPathFactory(StandardJavaFileManager.PathFactory pathFactory) {
        Objects.requireNonNull(pathFactory);
        this.pathFactory = pathFactory;
        this.locations.setPathFactory(pathFactory);
    }

    public void setSymbolFileEnabled(boolean z) {
        this.symbolFileEnabled = z;
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static abstract class SortFiles implements Comparator<Path> {
        private static final /* synthetic */ SortFiles[] $VALUES = $values();
        public static final SortFiles FORWARD;
        public static final SortFiles REVERSE;

        /* JADX INFO: renamed from: com.sun.tools.javac.file.JavacFileManager$SortFiles$1, reason: invalid class name */
        public final enum AnonymousClass1 extends SortFiles {
            private AnonymousClass1(String str, int i) {
                super(str, i);
            }

            @Override // java.util.Comparator
            public int compare(Path path, Path path2) {
                return path.getFileName().compareTo(path2.getFileName());
            }
        }

        /* JADX INFO: renamed from: com.sun.tools.javac.file.JavacFileManager$SortFiles$2, reason: invalid class name */
        public final enum AnonymousClass2 extends SortFiles {
            private AnonymousClass2(String str, int i) {
                super(str, i);
            }

            @Override // java.util.Comparator
            public int compare(Path path, Path path2) {
                return path2.getFileName().compareTo(path.getFileName());
            }
        }

        private static /* synthetic */ SortFiles[] $values() {
            return new SortFiles[]{FORWARD, REVERSE};
        }

        static {
            FORWARD = new AnonymousClass1("FORWARD", 0);
            REVERSE = new AnonymousClass2("REVERSE", 1);
        }

        public static SortFiles valueOf(String str) {
            return (SortFiles) Enum.valueOf(SortFiles.class, str);
        }

        public static SortFiles[] values() {
            return (SortFiles[]) $VALUES.clone();
        }

        private SortFiles(String str, int i) {
            super(str, i);
        }
    }

    public static class PathAndContainer implements Comparable<PathAndContainer> {
        private final Container container;
        private final int index;
        private final Path path;

        public PathAndContainer(Path path, Container container, int i) {
            this.path = path;
            this.container = container;
            this.index = i;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof PathAndContainer)) {
                return false;
            }
            PathAndContainer pathAndContainer = (PathAndContainer) obj;
            return this.path.equals(pathAndContainer.path) && this.container.equals(pathAndContainer.container) && this.index == pathAndContainer.index;
        }

        public int hashCode() {
            return Objects.hash(this.path, this.container, Integer.valueOf(this.index));
        }

        @Override // java.lang.Comparable
        public int compareTo(PathAndContainer pathAndContainer) {
            return this.index - pathAndContainer.index;
        }
    }

    @Override // javax.tools.StandardJavaFileManager
    public Iterable<? extends JavaFileObject> getJavaFileObjects(File... fileArr) {
        return getJavaFileObjectsFromFiles(Arrays.asList((File[]) BaseFileManager.nullCheck(fileArr)));
    }

    @Override // javax.tools.StandardJavaFileManager
    public Iterable<? extends JavaFileObject> getJavaFileObjects(Path... pathArr) {
        return getJavaFileObjectsFromPaths((Collection<? extends Path>) Arrays.asList((Path[]) BaseFileManager.nullCheck(pathArr)));
    }

    public JavaFileObject getJavaFileObject(String str) {
        return getJavaFileObjects(str).iterator().next();
    }

    @Override // javax.tools.JavaFileManager
    public JavaFileManager.Location getLocationForModule(JavaFileManager.Location location, JavaFileObject javaFileObject) throws IOException {
        checkModuleOrientedOrOutputLocation(location);
        if (!(javaFileObject instanceof PathFileObject)) {
            return null;
        }
        return this.locations.getLocationForModule(location, Locations.normalize(((PathFileObject) javaFileObject).path));
    }

    private JavaFileObject getFileForInput(JavaFileManager.Location location, RelativePath.RelativeFile relativeFile) throws IOException {
        Collection<? extends Path> locationAsPaths = getLocationAsPaths(location);
        if (locationAsPaths == null) {
            return null;
        }
        for (Path path : locationAsPaths) {
            JavaFileObject fileObject = getContainer(path).getFileObject(path, relativeFile);
            if (fileObject != null) {
                return fileObject;
            }
        }
        return null;
    }

    private List<PathAndContainer> pathsAndContainers(final JavaFileManager.Location location, RelativePath.RelativeDirectory relativeDirectory) throws IOException {
        try {
            return this.pathsAndContainersByLocationAndRelativeDirectory.computeIfAbsent(location, new Function() { // from class: em7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.indexPathsAndContainersByRelativeDirectory((JavaFileManager.Location) obj);
                }
            }).computeIfAbsent(relativeDirectory, new Function() { // from class: fm7
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.lambda$pathsAndContainers$1(location, (RelativePath.RelativeDirectory) obj);
                }
            });
        } catch (UncheckedIOException e) {
            throw e.getCause();
        }
    }

    public static boolean isRelativeUri(String str) {
        try {
            return isRelativeUri(new URI(str));
        } catch (URISyntaxException unused) {
            return false;
        }
    }

    @Override // javax.tools.JavaFileManager
    public FileObject getFileForOutput(JavaFileManager.Location location, String str, String str2, FileObject fileObject) throws IOException {
        RelativePath.RelativeFile relativeFile;
        checkOutputLocation(location);
        BaseFileManager.nullCheck(str);
        if (isRelativeUri(str2)) {
            if (str.length() == 0) {
                relativeFile = new RelativePath.RelativeFile(str2);
            } else {
                relativeFile = new RelativePath.RelativeFile(RelativePath.RelativeDirectory.forPackage(str), str2);
            }
            return getFileForOutput(location, relativeFile, fileObject);
        }
        aca.a("Invalid relative name: ", str2);
        return null;
    }

    public JavaFileObject getFileForOutput(String str, JavaFileObject.Kind kind, JavaFileObject javaFileObject) throws IOException {
        return getJavaFileForOutput(StandardLocation.CLASS_OUTPUT, str, kind, javaFileObject);
    }
}
