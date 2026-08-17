package com.sun.tools.javac.file;

import com.sun.org.apache.bcel.internal.classfile.Module;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.tools.javac.ConfigProvider;
import com.sun.tools.javac.file.Locations;
import com.sun.tools.javac.jvm.ModuleNameReader;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.Iterators;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Pair;
import com.sun.tools.javac.util.StringUtils;
import defpackage.dm7;
import defpackage.nrd;
import defpackage.qwc;
import defpackage.u8i;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.ProviderNotFoundException;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.lang.model.SourceVersion;
import javax.tools.JavaFileManager;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import jdk.internal.jrtfs.JrtFileSystemProvider;
import nbjavac.JmodFileWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Locations {
    static final Path javaHome;
    static final Path thisSystemModules;
    private FSInfo fsInfo;
    Map<JavaFileManager.Location, LocationHandler> handlersForLocation;
    Map<Option, LocationHandler> handlersForOption;
    private Log log;
    private ModuleNameReader moduleNameReader;
    private StandardJavaFileManager.PathFactory pathFactory = new dm7();
    Map<Path, FileSystem> fileSystems = new LinkedHashMap();
    List<Closeable> closeables = new ArrayList();
    private String releaseVersion = null;

    /* JADX INFO: renamed from: com.sun.tools.javac.file.Locations$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$main$Option;

        static {
            int[] iArr = new int[Option.values().length];
            $SwitchMap$com$sun$tools$javac$main$Option = iArr;
            try {
                iArr[Option.XBOOTCLASSPATH.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$main$Option[Option.DJAVA_ENDORSED_DIRS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$main$Option[Option.DJAVA_EXT_DIRS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static abstract class BasicLocationHandler extends LocationHandler {
        boolean explicit;
        final JavaFileManager.Location location;
        final Set<Option> options;

        public BasicLocationHandler(JavaFileManager.Location location, Option... optionArr) {
            this.location = location;
            this.options = optionArr.length == 0 ? EnumSet.noneOf(Option.class) : EnumSet.copyOf((Collection) Arrays.asList(optionArr));
        }

        public Path checkDirectory(Path path) throws IOException {
            Objects.requireNonNull(path);
            if (Files.exists(path, new LinkOption[0])) {
                if (Files.isDirectory(path, new LinkOption[0])) {
                    return path;
                }
                b28.a(path, ": not a directory");
                return null;
            }
            throw new FileNotFoundException(path + ": does not exist");
        }

        public Path checkSingletonDirectory(Iterable<? extends Path> iterable) throws IOException {
            Iterator<? extends Path> it = iterable.iterator();
            if (!it.hasNext()) {
                w01.a("empty path for directory");
                return null;
            }
            Path next = it.next();
            if (it.hasNext()) {
                w01.a("path too long for directory");
                return null;
            }
            checkDirectory(next);
            return next;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public boolean isExplicit() {
            return this.explicit;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public void setPathsForModule(String str, Iterable<? extends Path> iterable) throws IOException {
            throw new UnsupportedOperationException("not supported for " + this.location);
        }
    }

    public class BootClassPathLocationHandler extends BasicLocationHandler {
        private boolean isDefault;
        final Map<Option, String> optionValues;
        private Collection<Path> searchPath;

        public BootClassPathLocationHandler() {
            super(StandardLocation.PLATFORM_CLASS_PATH, Option.BOOT_CLASS_PATH, Option.XBOOTCLASSPATH, Option.XBOOTCLASSPATH_PREPEND, Option.XBOOTCLASSPATH_APPEND, Option.ENDORSEDDIRS, Option.DJAVA_ENDORSED_DIRS, Option.EXTDIRS, Option.DJAVA_EXT_DIRS);
            this.optionValues = new EnumMap(Option.class);
        }

        private Option canonicalize(Option option) {
            int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$main$Option[option.ordinal()];
            if (i == 1) {
                return Option.BOOT_CLASS_PATH;
            }
            if (i != 2) {
                return i != 3 ? option : Option.EXTDIRS;
            }
            return Option.ENDORSEDDIRS;
        }

        private void lazy() {
            if (this.searchPath == null) {
                try {
                    this.searchPath = Collections.unmodifiableCollection(computePath());
                } catch (IOException e) {
                    u8i.a(e);
                }
            }
        }

        private Collection<Path> systemClasses() throws IOException {
            Path path = Locations.thisSystemModules;
            if (Files.isRegularFile(path, new LinkOption[0])) {
                return Collections.singleton(path);
            }
            Path pathResolve = Locations.javaHome.resolve("modules");
            if (!Files.isDirectory(pathResolve.resolve("java.base"), new LinkOption[0])) {
                return null;
            }
            Stream<Path> list = Files.list(pathResolve);
            try {
                Collection<Path> collection = (Collection) list.collect(Collectors.toList());
                list.close();
                return collection;
            } catch (Throwable th) {
                if (list != null) {
                    try {
                        list.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        public SearchPath computePath() throws IOException {
            SearchPath searchPath = new SearchPath();
            String str = this.optionValues.get(Option.BOOT_CLASS_PATH);
            String str2 = this.optionValues.get(Option.ENDORSEDDIRS);
            String str3 = this.optionValues.get(Option.EXTDIRS);
            String str4 = this.optionValues.get(Option.XBOOTCLASSPATH_PREPEND);
            String str5 = this.optionValues.get(Option.XBOOTCLASSPATH_APPEND);
            searchPath.addFiles(str4);
            boolean z = false;
            if (str2 != null) {
                searchPath.addDirectories(str2);
            } else {
                searchPath.addDirectories(System.getProperty("java.endorsed.dirs"), false);
            }
            if (str != null) {
                searchPath.addFiles(str);
            } else {
                Collection<Path> collectionSystemClasses = systemClasses();
                if (collectionSystemClasses != null) {
                    searchPath.addFiles((Iterable<? extends Path>) collectionSystemClasses, false);
                } else {
                    searchPath.addFiles(System.getProperty("sun.boot.class.path"), false);
                }
            }
            searchPath.addFiles(str5);
            if (str3 != null) {
                searchPath.addDirectories(str3);
            } else {
                Path pathResolve = Locations.javaHome.resolve("lib/jfxrt.jar");
                if (Files.exists(pathResolve, new LinkOption[0])) {
                    searchPath.lambda$addDirectory$0(pathResolve, false);
                }
                searchPath.addDirectories(System.getProperty("java.ext.dirs"), false);
            }
            if (str4 == null && str == null && str5 == null) {
                z = true;
            }
            this.isDefault = z;
            return searchPath;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public boolean contains(Path path) throws IOException {
            return Locations.this.contains(this.searchPath, path);
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public Collection<Path> getPaths() {
            lazy();
            return this.searchPath;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public boolean handleOption(Option option, String str) {
            if (!this.options.contains(option)) {
                return false;
            }
            this.explicit = true;
            Option optionCanonicalize = canonicalize(option);
            this.optionValues.put(optionCanonicalize, str);
            if (optionCanonicalize == Option.BOOT_CLASS_PATH) {
                this.optionValues.remove(Option.XBOOTCLASSPATH_PREPEND);
                this.optionValues.remove(Option.XBOOTCLASSPATH_APPEND);
            }
            this.searchPath = null;
            return true;
        }

        public boolean isDefault() {
            lazy();
            return this.isDefault;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public void setPaths(Iterable<? extends Path> iterable) {
            if (iterable == null) {
                this.searchPath = null;
                return;
            }
            this.isDefault = false;
            this.explicit = true;
            this.searchPath = Collections.unmodifiableCollection(new SearchPath().addFiles(iterable, false));
            this.optionValues.clear();
        }
    }

    public class ClassPathLocationHandler extends SimpleLocationHandler {
        public ClassPathLocationHandler() {
            super(StandardLocation.CLASS_PATH, Option.CLASS_PATH);
        }

        private void lazy() {
            if (this.searchPath == null) {
                setPaths(null);
            }
        }

        @Override // com.sun.tools.javac.file.Locations.SimpleLocationHandler
        public SearchPath computePath(String str) {
            if (str == null) {
                str = System.getProperty("env.class.path");
            }
            if (str == null && System.getProperty("application.home") == null) {
                str = System.getProperty("java.class.path");
            }
            if (str == null) {
                str = Constants.ATTRVAL_THIS;
            }
            return createPath().addFiles(str);
        }

        @Override // com.sun.tools.javac.file.Locations.SimpleLocationHandler
        public SearchPath createPath() {
            return new SearchPath().expandJarClassPaths(true).emptyPathDefault(Locations.this.getPath(Constants.ATTRVAL_THIS, new String[0]));
        }

        @Override // com.sun.tools.javac.file.Locations.SimpleLocationHandler, com.sun.tools.javac.file.Locations.LocationHandler
        public Collection<Path> getPaths() {
            lazy();
            return this.searchPath;
        }
    }

    public static abstract class LocationHandler {
        public abstract boolean contains(Path path) throws IOException;

        public JavaFileManager.Location getLocationForModule(String str) throws IOException {
            return null;
        }

        public abstract Collection<Path> getPaths();

        public abstract boolean handleOption(Option option, String str);

        public String inferModuleName() {
            return null;
        }

        public abstract boolean isExplicit();

        public boolean isSet() {
            return getPaths() != null;
        }

        public Iterable<Set<JavaFileManager.Location>> listLocationsForModules() throws IOException {
            return null;
        }

        public abstract void setPaths(Iterable<? extends Path> iterable) throws IOException;

        public abstract void setPathsForModule(String str, Iterable<? extends Path> iterable) throws IOException;

        public JavaFileManager.Location getLocationForModule(Path path) throws IOException {
            return null;
        }
    }

    public class ModuleLocationHandler extends LocationHandler implements JavaFileManager.Location {
        boolean explicit;
        private final String moduleName;
        private final String name;
        private final boolean output;
        private final LocationHandler parent;
        Collection<Path> searchPath;

        public ModuleLocationHandler(LocationHandler locationHandler, String str, String str2, Collection<Path> collection, boolean z) {
            this.parent = locationHandler;
            this.name = str;
            this.moduleName = str2;
            this.searchPath = collection;
            this.output = z;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public boolean contains(Path path) throws IOException {
            return Locations.this.contains(this.searchPath, path);
        }

        @Override // javax.tools.JavaFileManager.Location
        public String getName() {
            return this.name;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public Collection<Path> getPaths() {
            return Collections.unmodifiableCollection(this.searchPath);
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public boolean handleOption(Option option, String str) {
            throw new UnsupportedOperationException();
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public String inferModuleName() {
            return this.moduleName;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public boolean isExplicit() {
            return true;
        }

        @Override // javax.tools.JavaFileManager.Location
        public boolean isOutputLocation() {
            return this.output;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public void setPaths(Iterable<? extends Path> iterable) throws IOException {
            this.parent.setPathsForModule(this.moduleName, iterable);
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public void setPathsForModule(String str, Iterable<? extends Path> iterable) {
            throw new UnsupportedOperationException("not supported for " + this.name);
        }

        public String toString() {
            return this.name;
        }
    }

    public class SimpleLocationHandler extends BasicLocationHandler {
        protected Collection<Path> searchPath;

        public SimpleLocationHandler(JavaFileManager.Location location, Option... optionArr) {
            super(location, optionArr);
        }

        public SearchPath computePath(String str) {
            return createPath().addFiles(str);
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public boolean contains(Path path) throws IOException {
            return Locations.this.contains(this.searchPath, path);
        }

        public SearchPath createPath() {
            return new SearchPath();
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public Collection<Path> getPaths() {
            return this.searchPath;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public boolean handleOption(Option option, String str) {
            if (!this.options.contains(option)) {
                return false;
            }
            this.explicit = true;
            this.searchPath = str == null ? null : Collections.unmodifiableCollection(createPath().addFiles(str));
            return true;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public void setPaths(Iterable<? extends Path> iterable) {
            SearchPath searchPathAddFiles;
            if (iterable == null) {
                searchPathAddFiles = computePath(null);
            } else {
                this.explicit = true;
                searchPathAddFiles = createPath().addFiles(iterable);
            }
            this.searchPath = Collections.unmodifiableCollection(searchPathAddFiles);
        }
    }

    static {
        Path path = FileSystems.getDefault().getPath(ConfigProvider.getJavaHome(), new String[0]);
        javaHome = path;
        thisSystemModules = path.resolve("lib").resolve("modules");
    }

    public Locations() {
        initHandlers();
    }

    public static /* synthetic */ void a(ListBuffer listBuffer, Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            listBuffer.add(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:13:0x0045  */
    public boolean contains(Collection<Path> collection, Path path) throws IOException {
        Path path2;
        if (collection == null) {
            return false;
        }
        if (path.getFileSystem().provider() == this.fsInfo.getJarFSProvider()) {
            URI uri = path.toUri();
            if (uri.getScheme().equals("jar")) {
                String schemeSpecificPart = uri.getSchemeSpecificPart();
                int iLastIndexOf = schemeSpecificPart.lastIndexOf("!");
                if (!schemeSpecificPart.startsWith("file:") || iLastIndexOf <= 0) {
                    path2 = null;
                } else {
                    path2 = Paths.get(URI.create(schemeSpecificPart.substring(0, iLastIndexOf)));
                }
            } else {
                path2 = null;
            }
        } else {
            path2 = null;
        }
        Path pathNormalize = normalize(path);
        Iterator<Path> it = collection.iterator();
        while (it.hasNext()) {
            Path pathNormalize2 = normalize(it.next());
            if (pathNormalize2.getFileSystem() == pathNormalize.getFileSystem() && Files.isDirectory(pathNormalize2, new LinkOption[0]) && pathNormalize.startsWith(pathNormalize2)) {
                return true;
            }
            if (path2 != null && Files.isSameFile(path2, pathNormalize2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Iterable<Path> getPathEntries(String str, Path path) {
        ListBuffer listBuffer = new ListBuffer();
        for (String str2 : str.split(Pattern.quote(File.pathSeparator), -1)) {
            if (str2.length() != 0) {
                try {
                    listBuffer.add(getPath(str2, new String[0]));
                } catch (IllegalArgumentException unused) {
                    this.log.warning(CompilerProperties.LintWarnings.InvalidPath(str2));
                }
            } else if (path != null) {
                listBuffer.add(path);
            }
        }
        return listBuffer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isArchive(Path path) {
        String lowerCase = StringUtils.toLowerCase(path.getFileName().toString());
        if (this.fsInfo.isFile(path)) {
            return lowerCase.endsWith(".jar") || lowerCase.endsWith(".zip");
        }
        return false;
    }

    public static Path normalize(Path path) {
        try {
            return path.toRealPath(new LinkOption[0]);
        } catch (IOException unused) {
            return path.toAbsolutePath().normalize();
        }
    }

    public void close() throws IOException {
        final ListBuffer listBuffer = new ListBuffer();
        this.closeables.forEach(new Consumer() { // from class: pf9
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Locations.a(listBuffer, (Closeable) obj);
            }
        });
        if (listBuffer.nonEmpty()) {
            IOException iOException = new IOException();
            Iterator it = listBuffer.iterator();
            while (it.hasNext()) {
                iOException.addSuppressed((IOException) it.next());
            }
            throw iOException;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public LocationHandler getHandler(JavaFileManager.Location location) {
        Objects.requireNonNull(location);
        return location instanceof LocationHandler ? (LocationHandler) location : this.handlersForLocation.get(location);
    }

    public Collection<Path> getLocation(JavaFileManager.Location location) {
        LocationHandler handler = getHandler(location);
        if (handler == null) {
            return null;
        }
        return handler.getPaths();
    }

    public JavaFileManager.Location getLocationForModule(JavaFileManager.Location location, String str) throws IOException {
        LocationHandler handler = getHandler(location);
        if (handler == null) {
            return null;
        }
        return handler.getLocationForModule(str);
    }

    public Path getOutputLocation(JavaFileManager.Location location) {
        if (location.isOutputLocation()) {
            return ((OutputLocationHandler) getHandler(location)).outputDir;
        }
        j2d.a();
        return null;
    }

    public Path getPath(String str, String... strArr) {
        try {
            return this.pathFactory.getPath(str, strArr);
        } catch (InvalidPathException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public boolean handleOption(Option option, String str) {
        LocationHandler locationHandler = this.handlersForOption.get(option);
        if (locationHandler == null) {
            return false;
        }
        return locationHandler.handleOption(option, str);
    }

    public boolean hasExplicitLocation(JavaFileManager.Location location) {
        LocationHandler handler = getHandler(location);
        if (handler == null) {
            return false;
        }
        return handler.isExplicit();
    }

    public boolean hasLocation(JavaFileManager.Location location) {
        LocationHandler handler = getHandler(location);
        if (handler == null) {
            return false;
        }
        return handler.isSet();
    }

    public String inferModuleName(JavaFileManager.Location location) {
        LocationHandler handler = getHandler(location);
        if (handler == null) {
            return null;
        }
        return handler.inferModuleName();
    }

    public void initHandlers() {
        this.handlersForLocation = new HashMap();
        this.handlersForOption = new EnumMap(Option.class);
        BasicLocationHandler[] basicLocationHandlerArr = {new BootClassPathLocationHandler(), new ClassPathLocationHandler(), new SimpleLocationHandler(StandardLocation.SOURCE_PATH, Option.SOURCE_PATH), new SimpleLocationHandler(StandardLocation.ANNOTATION_PROCESSOR_PATH, Option.PROCESSOR_PATH), new SimpleLocationHandler(StandardLocation.ANNOTATION_PROCESSOR_MODULE_PATH, Option.PROCESSOR_MODULE_PATH), new OutputLocationHandler(StandardLocation.CLASS_OUTPUT, Option.D), new OutputLocationHandler(StandardLocation.SOURCE_OUTPUT, Option.S), new OutputLocationHandler(StandardLocation.NATIVE_HEADER_OUTPUT, Option.H), new ModuleSourcePathLocationHandler(), new PatchModulesLocationHandler(), new ModulePathLocationHandler(StandardLocation.UPGRADE_MODULE_PATH, Option.UPGRADE_MODULE_PATH), new ModulePathLocationHandler(StandardLocation.MODULE_PATH, Option.MODULE_PATH), new SystemModulesLocationHandler()};
        for (int i = 0; i < 13; i++) {
            BasicLocationHandler basicLocationHandler = basicLocationHandlerArr[i];
            this.handlersForLocation.put(basicLocationHandler.location, basicLocationHandler);
            Iterator<Option> it = basicLocationHandler.options.iterator();
            while (it.hasNext()) {
                this.handlersForOption.put(it.next(), basicLocationHandler);
            }
        }
    }

    public boolean isDefaultBootClassPath() {
        return ((BootClassPathLocationHandler) getHandler(StandardLocation.PLATFORM_CLASS_PATH)).isDefault();
    }

    public boolean isDefaultSystemModulesPath() {
        return !((SystemModulesLocationHandler) getHandler(StandardLocation.SYSTEM_MODULES)).isExplicit();
    }

    public Iterable<Set<JavaFileManager.Location>> listLocationsForModules(JavaFileManager.Location location) throws IOException {
        LocationHandler handler = getHandler(location);
        if (handler == null) {
            return null;
        }
        return handler.listLocationsForModules();
    }

    public void setLocation(JavaFileManager.Location location, Iterable<? extends Path> iterable) throws IOException {
        LocationHandler handler = getHandler(location);
        if (handler == null) {
            handler = location.isOutputLocation() ? new OutputLocationHandler(location, new Option[0]) : new SimpleLocationHandler(location, new Option[0]);
            this.handlersForLocation.put(location, handler);
        }
        handler.setPaths(iterable);
    }

    public void setLocationForModule(JavaFileManager.Location location, String str, Iterable<? extends Path> iterable) throws IOException {
        LocationHandler handler = getHandler(location);
        if (handler == null) {
            handler = location.isOutputLocation() ? new OutputLocationHandler(location, new Option[0]) : new ModulePathLocationHandler(location, new Option[0]);
            this.handlersForLocation.put(location, handler);
        }
        handler.setPathsForModule(str, iterable);
    }

    public void setMultiReleaseValue(String str) {
        this.releaseVersion = str;
    }

    public void setPathFactory(StandardJavaFileManager.PathFactory pathFactory) {
        this.pathFactory = pathFactory;
    }

    public void update(Log log, FSInfo fSInfo) {
        this.log = log;
        this.fsInfo = fSInfo;
    }

    public class PatchModulesLocationHandler extends BasicLocationHandler {
        private final ModuleTable moduleTable;

        public PatchModulesLocationHandler() {
            super(StandardLocation.PATCH_MODULE_PATH, Option.PATCH_MODULE);
            this.moduleTable = new ModuleTable();
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public boolean contains(Path path) throws IOException {
            return this.moduleTable.contains(path);
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public JavaFileManager.Location getLocationForModule(String str) throws IOException {
            return this.moduleTable.get(str);
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public Collection<Path> getPaths() {
            throw new UnsupportedOperationException();
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public boolean handleOption(Option option, String str) {
            PatchModulesLocationHandler patchModulesLocationHandler;
            if (!this.options.contains(option)) {
                return false;
            }
            this.explicit = true;
            this.moduleTable.clear();
            String[] strArrSplit = str.split("\u0000");
            int length = strArrSplit.length;
            int i = 0;
            while (i < length) {
                String str2 = strArrSplit[i];
                int iIndexOf = str2.indexOf(61);
                if (iIndexOf > 0) {
                    String strSubstring = str2.substring(0, iIndexOf);
                    patchModulesLocationHandler = this;
                    patchModulesLocationHandler.moduleTable.add(Locations.this.new ModuleLocationHandler(patchModulesLocationHandler, this.location.getName() + "[" + strSubstring + "]", strSubstring, new SearchPath().addFiles(str2.substring(iIndexOf + 1)), false));
                } else {
                    patchModulesLocationHandler = this;
                    Locations.this.log.error(CompilerProperties.Errors.LocnInvalidArgForXpatch(str));
                }
                i++;
                this = patchModulesLocationHandler;
            }
            return true;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public boolean isSet() {
            return !this.moduleTable.isEmpty();
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public Iterable<Set<JavaFileManager.Location>> listLocationsForModules() throws IOException {
            return Collections.singleton(this.moduleTable.locations());
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public void setPaths(Iterable<? extends Path> iterable) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // com.sun.tools.javac.file.Locations.BasicLocationHandler, com.sun.tools.javac.file.Locations.LocationHandler
        public void setPathsForModule(String str, Iterable<? extends Path> iterable) throws IOException {
            throw new UnsupportedOperationException();
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public JavaFileManager.Location getLocationForModule(Path path) throws IOException {
            return this.moduleTable.get(path);
        }
    }

    public class ModulePathLocationHandler extends SimpleLocationHandler {
        private ModuleTable moduleTable;

        public class ModulePathIterator implements Iterator<Set<JavaFileManager.Location>> {
            Iterator<Path> pathIter;
            int pathIndex = 0;
            Set<JavaFileManager.Location> next = null;

            public ModulePathIterator() {
                this.pathIter = ModulePathLocationHandler.this.searchPath.iterator();
            }

            private Pair<String, Path> inferModuleName(Path path) throws Throwable {
                String value;
                if (Files.isDirectory(path, new LinkOption[0])) {
                    if (Files.exists(path.resolve("module-info.class"), new LinkOption[0]) || Files.exists(path.resolve("module-info.sig"), new LinkOption[0])) {
                        String string = path.getFileName().toString();
                        if (SourceVersion.isName(string)) {
                            return new Pair<>(string, path);
                        }
                    }
                    return null;
                }
                if (!path.getFileName().toString().endsWith(".jar") || !Locations.this.fsInfo.exists(path)) {
                    if (path.getFileName().toString().endsWith(Module.EXTENSION)) {
                        try {
                            JmodFileWrapper.checkMagic(path);
                            if (Locations.this.fileSystems.get(path) == null) {
                                FileSystemProvider jarFSProvider = Locations.this.fsInfo.getJarFSProvider();
                                ModulePathLocationHandler modulePathLocationHandler = ModulePathLocationHandler.this;
                                if (jarFSProvider == null) {
                                    Locations.this.log.error(CompilerProperties.Errors.LocnCantReadFile(path));
                                    return null;
                                }
                                FileSystem fileSystemNewFileSystem = jarFSProvider.newFileSystem(path, Locations.this.fsInfo.readOnlyJarFSEnv(null));
                                try {
                                    String moduleName = readModuleName(fileSystemNewFileSystem.getPath("classes/module-info.class", new String[0]));
                                    Path path2 = fileSystemNewFileSystem.getPath("classes", new String[0]);
                                    Locations.this.fileSystems.put(path, fileSystemNewFileSystem);
                                    Locations.this.closeables.add(fileSystemNewFileSystem);
                                    try {
                                        return new Pair<>(moduleName, path2);
                                    } catch (Throwable th) {
                                        th = th;
                                        fileSystemNewFileSystem = null;
                                        if (fileSystemNewFileSystem != null) {
                                            fileSystemNewFileSystem.close();
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                }
                            }
                        } catch (ModuleNameReader.BadClassFile unused) {
                            Locations.this.log.error(CompilerProperties.Errors.LocnBadModuleInfo(path));
                        } catch (IOException unused2) {
                            Locations.this.log.error(CompilerProperties.Errors.LocnCantReadFile(path));
                            return null;
                        }
                    }
                    return null;
                }
                FileSystemProvider jarFSProvider2 = Locations.this.fsInfo.getJarFSProvider();
                ModulePathLocationHandler modulePathLocationHandler2 = ModulePathLocationHandler.this;
                if (jarFSProvider2 == null) {
                    Locations.this.log.error(CompilerProperties.Errors.NoZipfsForArchive(path));
                    return null;
                }
                try {
                    FileSystem fileSystemNewFileSystem2 = jarFSProvider2.newFileSystem(path, Locations.this.fsInfo.readOnlyJarFSEnv(Locations.this.releaseVersion));
                    try {
                        Path path3 = fileSystemNewFileSystem2.getPath("module-info.class", new String[0]);
                        if (Files.exists(path3, new LinkOption[0])) {
                            Pair<String, Path> pair = new Pair<>(readModuleName(path3), path);
                            fileSystemNewFileSystem2.close();
                            return pair;
                        }
                        Path path4 = fileSystemNewFileSystem2.getPath("META-INF/MANIFEST.MF", new String[0]);
                        if (Files.exists(path4, new LinkOption[0])) {
                            InputStream inputStreamNewInputStream = Files.newInputStream(path4, new OpenOption[0]);
                            try {
                                Attributes mainAttributes = new Manifest(inputStreamNewInputStream).getMainAttributes();
                                if (mainAttributes != null && (value = mainAttributes.getValue(new Attributes.Name("Automatic-Module-Name"))) != null) {
                                    if (ModulePathLocationHandler.this.isModuleName(value)) {
                                        Pair<String, Path> pair2 = new Pair<>(value, path);
                                        if (inputStreamNewInputStream != null) {
                                            inputStreamNewInputStream.close();
                                        }
                                        fileSystemNewFileSystem2.close();
                                        return pair2;
                                    }
                                    Locations.this.log.error(CompilerProperties.Errors.LocnCantGetModuleNameForJar(path));
                                    if (inputStreamNewInputStream != null) {
                                        inputStreamNewInputStream.close();
                                    }
                                    fileSystemNewFileSystem2.close();
                                    return null;
                                }
                                if (inputStreamNewInputStream != null) {
                                    inputStreamNewInputStream.close();
                                }
                            } catch (Throwable th3) {
                                if (inputStreamNewInputStream != null) {
                                    try {
                                        inputStreamNewInputStream.close();
                                    } catch (Throwable th4) {
                                        th3.addSuppressed(th4);
                                    }
                                }
                                throw th3;
                            }
                        }
                        fileSystemNewFileSystem2.close();
                        String string2 = path.getFileName().toString();
                        String strSubstring = string2.substring(0, string2.length() - 4);
                        Matcher matcher = Pattern.compile("-(\\d+(\\.|$))").matcher(strSubstring);
                        if (matcher.find()) {
                            strSubstring = strSubstring.substring(0, matcher.start());
                        }
                        String strReplaceAll = strSubstring.replaceAll("[^A-Za-z0-9]", Constants.ATTRVAL_THIS).replaceAll("(\\.)(\\1)+", Constants.ATTRVAL_THIS).replaceAll("^\\.", "").replaceAll("\\.$", "");
                        if (strReplaceAll.length() != 0) {
                            return new Pair<>(strReplaceAll, path);
                        }
                        Locations.this.log.error(CompilerProperties.Errors.LocnCantGetModuleNameForJar(path));
                        return null;
                    } catch (Throwable th5) {
                        if (fileSystemNewFileSystem2 != null) {
                            try {
                                fileSystemNewFileSystem2.close();
                            } catch (Throwable th6) {
                                th5.addSuppressed(th6);
                            }
                        }
                        throw th5;
                    }
                } catch (ModuleNameReader.BadClassFile unused3) {
                    Locations.this.log.error(CompilerProperties.Errors.LocnBadModuleInfo(path));
                    return null;
                } catch (IOException unused4) {
                    Locations.this.log.error(CompilerProperties.Errors.LocnCantReadFile(path));
                    return null;
                }
                Locations.this.log.error(CompilerProperties.Errors.LocnBadModuleInfo(path));
                return null;
            }

            private String readModuleName(Path path) throws ModuleNameReader.BadClassFile, IOException {
                if (Locations.this.moduleNameReader == null) {
                    Locations.this.moduleNameReader = new ModuleNameReader();
                }
                return Locations.this.moduleNameReader.readModuleName(path);
            }

            private Set<JavaFileManager.Location> scanDirectory(Path path) throws Throwable {
                Path next;
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                try {
                    DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path);
                    try {
                        Iterator<Path> it = directoryStreamNewDirectoryStream.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                next = null;
                                break;
                            }
                            next = it.next();
                            if (next.endsWith("module-info.class")) {
                                break;
                            }
                            linkedHashSet.add(next);
                        }
                        directoryStreamNewDirectoryStream.close();
                        if (next != null) {
                            try {
                                String moduleName = readModuleName(next);
                                String str = ModulePathLocationHandler.this.location.getName() + "[" + this.pathIndex + ":" + moduleName + "]";
                                ModulePathLocationHandler modulePathLocationHandler = ModulePathLocationHandler.this;
                                return Collections.singleton(Locations.this.new ModuleLocationHandler(modulePathLocationHandler, str, moduleName, Collections.singletonList(path), false));
                            } catch (ModuleNameReader.BadClassFile unused) {
                                Locations.this.log.error(CompilerProperties.Errors.LocnBadModuleInfo(path));
                                return Collections.EMPTY_SET;
                            } catch (IOException unused2) {
                                Locations.this.log.error(CompilerProperties.Errors.LocnCantReadFile(path));
                                return Collections.EMPTY_SET;
                            }
                        }
                        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
                        Iterator it2 = linkedHashSet.iterator();
                        int i = 0;
                        while (it2.hasNext()) {
                            Pair<String, Path> pairInferModuleName = inferModuleName((Path) it2.next());
                            if (pairInferModuleName != null) {
                                String str2 = pairInferModuleName.fst;
                                Path path2 = pairInferModuleName.snd;
                                String str3 = ModulePathLocationHandler.this.location.getName() + "[" + this.pathIndex + Constants.ATTRVAL_THIS + i + ":" + str2 + "]";
                                ModulePathLocationHandler modulePathLocationHandler2 = ModulePathLocationHandler.this;
                                linkedHashSet2.add(Locations.this.new ModuleLocationHandler(modulePathLocationHandler2, str3, str2, Collections.singletonList(path2), false));
                                i++;
                            }
                        }
                        return linkedHashSet2;
                    } catch (Throwable th) {
                        if (directoryStreamNewDirectoryStream == null) {
                            throw th;
                        }
                        try {
                            directoryStreamNewDirectoryStream.close();
                            throw th;
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                            throw th;
                        }
                    }
                } catch (IOException | DirectoryIteratorException unused3) {
                    Locations.this.log.error(CompilerProperties.Errors.LocnCantReadDirectory(path));
                    return Collections.EMPTY_SET;
                }
            }

            private Set<JavaFileManager.Location> scanFile(Path path) throws Throwable {
                Pair<String, Path> pairInferModuleName = inferModuleName(path);
                if (pairInferModuleName == null) {
                    return Collections.EMPTY_SET;
                }
                String str = pairInferModuleName.fst;
                Path path2 = pairInferModuleName.snd;
                String str2 = ModulePathLocationHandler.this.location.getName() + "[" + this.pathIndex + ":" + str + "]";
                ModulePathLocationHandler modulePathLocationHandler = ModulePathLocationHandler.this;
                return Collections.singleton(Locations.this.new ModuleLocationHandler(modulePathLocationHandler, str2, str, Collections.singletonList(path2), false));
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                if (this.next != null) {
                    return true;
                }
                while (this.next == null) {
                    if (!this.pathIter.hasNext()) {
                        return false;
                    }
                    Path next = this.pathIter.next();
                    if (Files.isDirectory(next, new LinkOption[0])) {
                        this.next = scanDirectory(next);
                    } else {
                        this.next = scanFile(next);
                    }
                    this.pathIndex++;
                }
                return true;
            }

            @Override // java.util.Iterator
            public Set<JavaFileManager.Location> next() {
                hasNext();
                Set<JavaFileManager.Location> set = this.next;
                if (set != null) {
                    this.next = null;
                    return set;
                }
                z0e.a();
                return null;
            }
        }

        public ModulePathLocationHandler(JavaFileManager.Location location, Option... optionArr) {
            super(location, optionArr);
        }

        private List<Path> checkPaths(Iterable<? extends Path> iterable) throws IOException {
            Objects.requireNonNull(iterable);
            ArrayList arrayList = new ArrayList();
            Iterator<? extends Path> it = iterable.iterator();
            while (it.hasNext()) {
                arrayList.add(checkDirectory(it.next()));
            }
            return arrayList;
        }

        private void checkValidModulePathEntry(Path path) {
            if (Files.exists(path, new LinkOption[0]) && !Files.isDirectory(path, new LinkOption[0])) {
                String string = path.getFileName().toString();
                int iLastIndexOf = string.lastIndexOf(Constants.ATTRVAL_THIS);
                if (iLastIndexOf > 0) {
                    String strSubstring = string.substring(iLastIndexOf);
                    if (strSubstring.equals(".jar") || strSubstring.equals(Module.EXTENSION)) {
                        return;
                    }
                }
                w01.a(path.toString());
            }
        }

        private void initModuleLocations() {
            if (this.moduleTable != null) {
                return;
            }
            this.moduleTable = new ModuleTable();
            Iterator<Set<JavaFileManager.Location>> it = listLocationsForModules().iterator();
            while (it.hasNext()) {
                for (JavaFileManager.Location location : it.next()) {
                    if (location instanceof ModuleLocationHandler) {
                        ModuleLocationHandler moduleLocationHandler = (ModuleLocationHandler) location;
                        if (!this.moduleTable.nameMap.containsKey(moduleLocationHandler.moduleName)) {
                            this.moduleTable.add(moduleLocationHandler);
                        }
                    }
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isModuleName(String str) {
            int i = 0;
            while (true) {
                int iIndexOf = str.indexOf(46, i);
                if (iIndexOf == -1) {
                    return SourceVersion.isName(str.substring(i));
                }
                if (!SourceVersion.isName(str.substring(i, iIndexOf))) {
                    return false;
                }
                i = iIndexOf + 1;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Iterator lambda$listLocationsForModules$0() {
            return new ModulePathIterator();
        }

        @Override // com.sun.tools.javac.file.Locations.SimpleLocationHandler, com.sun.tools.javac.file.Locations.LocationHandler
        public boolean contains(Path path) throws IOException {
            if (this.moduleTable == null) {
                initModuleLocations();
            }
            return this.moduleTable.contains(path);
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public JavaFileManager.Location getLocationForModule(String str) {
            initModuleLocations();
            return this.moduleTable.get(str);
        }

        @Override // com.sun.tools.javac.file.Locations.SimpleLocationHandler, com.sun.tools.javac.file.Locations.LocationHandler
        public boolean handleOption(Option option, String str) {
            if (!this.options.contains(option)) {
                return false;
            }
            setPaths(str == null ? null : Locations.this.getPathEntries(str));
            return true;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public Iterable<Set<JavaFileManager.Location>> listLocationsForModules() {
            ModuleTable moduleTable = this.moduleTable;
            Set<JavaFileManager.Location> setExplicitLocations = moduleTable != null ? moduleTable.explicitLocations() : Collections.EMPTY_SET;
            final List listSingletonList = !setExplicitLocations.isEmpty() ? Collections.singletonList(setExplicitLocations) : Collections.EMPTY_LIST;
            if (this.searchPath == null) {
                return listSingletonList;
            }
            final Iterable iterable = new Iterable() { // from class: com.sun.tools.javac.file.a
                @Override // java.lang.Iterable
                public final Iterator iterator() {
                    return this.b.lambda$listLocationsForModules$0();
                }
            };
            return new Iterable() { // from class: com.sun.tools.javac.file.b
                @Override // java.lang.Iterable
                public final Iterator iterator() {
                    return Iterators.createCompoundIterator(Arrays.asList(listSingletonList, iterable), new qwc());
                }
            };
        }

        @Override // com.sun.tools.javac.file.Locations.SimpleLocationHandler, com.sun.tools.javac.file.Locations.LocationHandler
        public void setPaths(Iterable<? extends Path> iterable) {
            if (iterable != null) {
                Iterator<? extends Path> it = iterable.iterator();
                while (it.hasNext()) {
                    checkValidModulePathEntry(it.next());
                }
            }
            super.setPaths(iterable);
            this.moduleTable = null;
        }

        @Override // com.sun.tools.javac.file.Locations.BasicLocationHandler, com.sun.tools.javac.file.Locations.LocationHandler
        public void setPathsForModule(String str, Iterable<? extends Path> iterable) throws IOException {
            ModulePathLocationHandler modulePathLocationHandler;
            List<Path> listCheckPaths = checkPaths(iterable);
            initModuleLocations();
            ModuleLocationHandler moduleLocationHandler = this.moduleTable.get(str);
            if (moduleLocationHandler == null) {
                modulePathLocationHandler = this;
                ModuleLocationHandler moduleLocationHandler2 = Locations.this.new ModuleLocationHandler(modulePathLocationHandler, this.location.getName() + "[" + str + "]", str, listCheckPaths, true);
                modulePathLocationHandler.moduleTable.add(moduleLocationHandler2);
                moduleLocationHandler = moduleLocationHandler2;
            } else {
                modulePathLocationHandler = this;
                moduleLocationHandler.searchPath = listCheckPaths;
                modulePathLocationHandler.moduleTable.updatePaths(moduleLocationHandler);
            }
            moduleLocationHandler.explicit = true;
            modulePathLocationHandler.explicit = true;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public JavaFileManager.Location getLocationForModule(Path path) {
            initModuleLocations();
            return this.moduleTable.get(path);
        }
    }

    public class SystemModulesLocationHandler extends BasicLocationHandler {
        private ModuleTable moduleTable;
        private Path modules;
        private Path systemJavaHome;

        public SystemModulesLocationHandler() {
            super(StandardLocation.SYSTEM_MODULES, Option.SYSTEM);
            this.systemJavaHome = Locations.javaHome;
        }

        private List<Path> checkPaths(Iterable<? extends Path> iterable) throws IOException {
            Objects.requireNonNull(iterable);
            ArrayList arrayList = new ArrayList();
            Iterator<? extends Path> it = iterable.iterator();
            while (it.hasNext()) {
                arrayList.add(checkDirectory(it.next()));
            }
            return arrayList;
        }

        private void initSystemModules() throws IOException {
            if (this.moduleTable != null) {
                return;
            }
            if (this.systemJavaHome == null) {
                this.moduleTable = new ModuleTable();
                return;
            }
            if (this.modules == null) {
                try {
                    FileSystem fileSystemNewFileSystem = new JrtFileSystemProvider().newFileSystem(URI.create("jrt:/"), new HashMap(0));
                    Locations.this.closeables.add(fileSystemNewFileSystem);
                    this.modules = fileSystemNewFileSystem.getPath("/modules", new String[0]);
                } catch (FileSystemNotFoundException | ProviderNotFoundException e) {
                    Path pathResolveInJavaHomeLib = resolveInJavaHomeLib(this.systemJavaHome, "modules");
                    this.modules = pathResolveInJavaHomeLib;
                    if (!Files.exists(pathResolveInJavaHomeLib, new LinkOption[0])) {
                        dk3.a("can't find system classes", e);
                        return;
                    }
                }
            }
            this.moduleTable = new ModuleTable();
            DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(this.modules, (DirectoryStream.Filter<? super Path>) new DirectoryStream.Filter() { // from class: com.sun.tools.javac.file.k
                @Override // java.nio.file.DirectoryStream.Filter
                public final boolean accept(Object obj) {
                    return Files.isDirectory((Path) obj, new LinkOption[0]);
                }
            });
            try {
                for (Path path : directoryStreamNewDirectoryStream) {
                    String string = path.getFileName().toString();
                    SystemModulesLocationHandler systemModulesLocationHandler = this;
                    systemModulesLocationHandler.moduleTable.add(Locations.this.new ModuleLocationHandler(systemModulesLocationHandler, this.location.getName() + "[" + string + "]", string, Collections.singletonList(path), false));
                    this = systemModulesLocationHandler;
                }
                directoryStreamNewDirectoryStream.close();
            } catch (Throwable th) {
                if (directoryStreamNewDirectoryStream == null) {
                    throw th;
                }
                try {
                    directoryStreamNewDirectoryStream.close();
                    throw th;
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                    throw th;
                }
            }
        }

        private boolean isCurrentPlatform(Path path) {
            try {
                return Files.isSameFile(path, Locations.javaHome);
            } catch (IOException e) {
                nrd.a(path.toString(), e);
                return false;
            }
        }

        private static Path resolveInJavaHomeLib(Path path, String str) {
            return path.resolve("lib").resolve(str);
        }

        private void update(Path path) {
            if (!isCurrentPlatform(path)) {
                boolean zNotExists = Files.notExists(resolveInJavaHomeLib(path, "jrt-fs.jar"), new LinkOption[0]);
                boolean zNotExists2 = Files.notExists(resolveInJavaHomeLib(path, "modules"), new LinkOption[0]);
                if (zNotExists || zNotExists2) {
                    w01.a(path.toString());
                    return;
                }
            }
            this.systemJavaHome = path;
            this.modules = null;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public boolean contains(Path path) throws IOException {
            initSystemModules();
            return this.moduleTable.contains(path);
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public JavaFileManager.Location getLocationForModule(String str) throws IOException {
            initSystemModules();
            return this.moduleTable.get(str);
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public Collection<Path> getPaths() {
            Path path = this.systemJavaHome;
            if (path == null) {
                return null;
            }
            return Collections.singleton(path);
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public boolean handleOption(Option option, String str) {
            if (!this.options.contains(option)) {
                return false;
            }
            this.explicit = true;
            if (str == null) {
                this.systemJavaHome = Locations.javaHome;
            } else if (str.equals(Option.LINT_CUSTOM_NONE)) {
                this.systemJavaHome = null;
            } else {
                update(Locations.this.getPath(str, new String[0]));
            }
            this.modules = null;
            return true;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public Iterable<Set<JavaFileManager.Location>> listLocationsForModules() throws IOException {
            initSystemModules();
            return Collections.singleton(this.moduleTable.locations());
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public void setPaths(Iterable<? extends Path> iterable) throws IOException {
            if (iterable == null) {
                this.systemJavaHome = null;
            } else {
                this.explicit = true;
                update(checkSingletonDirectory(iterable));
            }
        }

        @Override // com.sun.tools.javac.file.Locations.BasicLocationHandler, com.sun.tools.javac.file.Locations.LocationHandler
        public void setPathsForModule(String str, Iterable<? extends Path> iterable) throws IOException {
            SystemModulesLocationHandler systemModulesLocationHandler;
            List<Path> listCheckPaths = checkPaths(iterable);
            initSystemModules();
            ModuleLocationHandler moduleLocationHandler = this.moduleTable.get(str);
            if (moduleLocationHandler == null) {
                systemModulesLocationHandler = this;
                systemModulesLocationHandler.moduleTable.add(Locations.this.new ModuleLocationHandler(systemModulesLocationHandler, this.location.getName() + "[" + str + "]", str, listCheckPaths, true));
            } else {
                systemModulesLocationHandler = this;
                moduleLocationHandler.searchPath = listCheckPaths;
                systemModulesLocationHandler.moduleTable.updatePaths(moduleLocationHandler);
            }
            systemModulesLocationHandler.explicit = true;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public JavaFileManager.Location getLocationForModule(Path path) throws IOException {
            initSystemModules();
            return this.moduleTable.get(path);
        }
    }

    public class ModuleSourcePathLocationHandler extends BasicLocationHandler {
        private final Predicate<Path> checkModuleInfo;
        private ModuleTable moduleTable;
        private List<Path> paths;

        public ModuleSourcePathLocationHandler() {
            super(StandardLocation.MODULE_SOURCE_PATH, Option.MODULE_SOURCE_PATH);
            this.checkModuleInfo = new Predicate() { // from class: com.sun.tools.javac.file.c
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return Files.exists(((Path) obj).resolve("module-info.java"), new LinkOption[0]);
                }
            };
        }

        private List<Path> checkPaths(Iterable<? extends Path> iterable) throws IOException {
            Objects.requireNonNull(iterable);
            ArrayList arrayList = new ArrayList();
            Iterator<? extends Path> it = iterable.iterator();
            while (it.hasNext()) {
                arrayList.add(checkDirectory(it.next()));
            }
            return arrayList;
        }

        private void expandBraces(String str, Collection<String> collection) {
            int i = -1;
            String strSubstring = null;
            int i2 = 0;
            String strSubstring2 = null;
            for (int i3 = 0; i3 < str.length(); i3++) {
                char cCharAt = str.charAt(i3);
                if (cCharAt != ',') {
                    if (cCharAt == '{') {
                        i2++;
                        if (i2 == 1) {
                            strSubstring = str.substring(0, i3);
                            i = i3 + 1;
                            strSubstring2 = str.substring(getMatchingBrace(str, i3) + 1);
                        }
                    } else if (cCharAt != '}') {
                        continue;
                    } else {
                        if (i2 == 0) {
                            w01.a("mismatched braces");
                            return;
                        }
                        if (i2 == 1) {
                            expandBraces(strSubstring + str.substring(i, i3) + strSubstring2, collection);
                            return;
                        }
                        i2--;
                    }
                } else if (i2 == 1) {
                    expandBraces(strSubstring + str.substring(i, i3) + strSubstring2, collection);
                    i = i3 + 1;
                }
            }
            if (i2 <= 0) {
                collection.add(str);
            } else {
                w01.a("mismatched braces");
            }
        }

        private void initModuleTable(Map<String, List<Path>> map) {
            this.moduleTable = new ModuleTable();
            map.forEach(new BiConsumer() { // from class: com.sun.tools.javac.file.e
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    this.a.lambda$initModuleTable$0((String) obj, (List) obj2);
                }
            });
        }

        private boolean isSeparator(char c) {
            return c == File.separatorChar || c == '/';
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$initModuleTable$0(String str, List list) {
            if (list.stream().anyMatch(this.checkModuleInfo)) {
                this.moduleTable.add(Locations.this.new ModuleLocationHandler(this, this.location.getName() + "[" + str + "]", str, list, false));
            }
        }

        public void add(Map<String, List<Path>> map, Path path, Path path2) {
            if (!Files.isDirectory(path, new LinkOption[0])) {
                Locations.this.log.warning(Files.exists(path, new LinkOption[0]) ? CompilerProperties.LintWarnings.DirPathElementNotDirectory(path) : CompilerProperties.LintWarnings.DirPathElementNotFound(path));
                return;
            }
            try {
                DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path, (DirectoryStream.Filter<? super Path>) new DirectoryStream.Filter() { // from class: com.sun.tools.javac.file.f
                    @Override // java.nio.file.DirectoryStream.Filter
                    public final boolean accept(Object obj) {
                        return Files.isDirectory((Path) obj, new LinkOption[0]);
                    }
                });
                try {
                    for (Path path3 : directoryStreamNewDirectoryStream) {
                        Path pathResolve = path2 == null ? path3 : path3.resolve(path2);
                        if (Files.isDirectory(pathResolve, new LinkOption[0])) {
                            String string = path3.getFileName().toString();
                            List<Path> arrayList = map.get(string);
                            if (arrayList == null) {
                                arrayList = new ArrayList<>();
                                map.put(string, arrayList);
                            }
                            arrayList.add(pathResolve);
                        }
                    }
                    directoryStreamNewDirectoryStream.close();
                } catch (Throwable th) {
                    if (directoryStreamNewDirectoryStream != null) {
                        try {
                            directoryStreamNewDirectoryStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (IOException e) {
                System.err.println(e);
            }
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public boolean contains(Path path) throws IOException {
            ModuleTable moduleTable = this.moduleTable;
            if (moduleTable == null) {
                return false;
            }
            return moduleTable.contains(path);
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public JavaFileManager.Location getLocationForModule(String str) {
            ModuleTable moduleTable = this.moduleTable;
            if (moduleTable == null) {
                return null;
            }
            return moduleTable.get(str);
        }

        public int getMatchingBrace(String str, int i) {
            int i2 = 1;
            for (int i3 = i + 1; i3 < str.length(); i3++) {
                char cCharAt = str.charAt(i3);
                if (cCharAt == '{') {
                    i2++;
                } else if (cCharAt == '}' && (i2 = i2 - 1) == 0) {
                    return i3;
                }
            }
            w01.a("mismatched braces");
            return 0;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public Collection<Path> getPaths() {
            List<Path> list = this.paths;
            if (list != null) {
                return list;
            }
            k2d.a("paths not available");
            return null;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public boolean handleOption(Option option, String str) {
            this.explicit = true;
            init(str);
            return true;
        }

        public void init(String str) {
            Pattern patternCompile = Pattern.compile("([\\p{Alnum}$_.]+)=(.*)");
            ArrayList arrayList = new ArrayList();
            String str2 = null;
            for (String str3 : str.split("\u0000")) {
                if (patternCompile.matcher(str3).matches()) {
                    arrayList.add(str3);
                } else {
                    str2 = str3;
                }
            }
            if (str2 != null) {
                initFromPattern(str2);
            }
            arrayList.forEach(new Consumer() { // from class: com.sun.tools.javac.file.d
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.initForModule((String) obj);
                }
            });
        }

        public void initForModule(String str) {
            int iIndexOf = str.indexOf(61);
            String strSubstring = str.substring(0, iIndexOf);
            ArrayList arrayList = new ArrayList();
            for (String str2 : str.substring(iIndexOf + 1).split(File.pathSeparator)) {
                try {
                    arrayList.add(Paths.get(str2, new String[0]));
                } catch (InvalidPathException e) {
                    throw new IllegalArgumentException("invalid path: " + str2, e);
                }
            }
            try {
                setPathsForModule(strSubstring, arrayList);
            } catch (IOException e2) {
                e2.printStackTrace();
                nrd.a("cannot set path for module ".concat(strSubstring), e2);
            }
        }

        public void initFromPattern(String str) {
            ArrayList arrayList = new ArrayList();
            for (String str2 : str.split(File.pathSeparator)) {
                expandBraces(str2, arrayList);
            }
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            ArrayList arrayList2 = new ArrayList();
            Iterator<String> it = arrayList.iterator();
            boolean z = false;
            while (true) {
                Path path = null;
                if (!it.hasNext()) {
                    initModuleTable(linkedHashMap);
                    if (z) {
                        arrayList2 = null;
                    }
                    this.paths = arrayList2;
                    return;
                }
                String next = it.next();
                int iIndexOf = next.indexOf("*");
                if (iIndexOf != -1) {
                    if (iIndexOf != 0) {
                        int i = iIndexOf - 1;
                        if (isSeparator(next.charAt(i))) {
                            Path path2 = Locations.this.getPath(next.substring(0, i), new String[0]);
                            int i2 = iIndexOf + 1;
                            if (i2 != next.length()) {
                                if (!isSeparator(next.charAt(i2)) || next.indexOf("*", i2) != -1) {
                                    w01.a("illegal use of * in ".concat(next));
                                    return;
                                } else {
                                    path = Locations.this.getPath(next.substring(iIndexOf + 2), new String[0]);
                                    z = true;
                                }
                            }
                            add(linkedHashMap, path2, path);
                            if (path == null) {
                                arrayList2.add(path2);
                            }
                        }
                    }
                    w01.a("illegal use of * in ".concat(next));
                    return;
                }
                Path path3 = Locations.this.getPath(next, new String[0]);
                add(linkedHashMap, path3, null);
                arrayList2.add(path3);
            }
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public boolean isSet() {
            return this.moduleTable != null;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public Iterable<Set<JavaFileManager.Location>> listLocationsForModules() {
            ModuleTable moduleTable = this.moduleTable;
            return moduleTable == null ? Collections.EMPTY_SET : Collections.singleton(moduleTable.locations());
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public void setPaths(Iterable<? extends Path> iterable) throws IOException {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            ArrayList arrayList = new ArrayList();
            for (Path path : iterable) {
                add(linkedHashMap, path, null);
                arrayList.add(path);
            }
            initModuleTable(linkedHashMap);
            this.explicit = true;
            this.paths = Collections.unmodifiableList(arrayList);
        }

        @Override // com.sun.tools.javac.file.Locations.BasicLocationHandler, com.sun.tools.javac.file.Locations.LocationHandler
        public void setPathsForModule(String str, Iterable<? extends Path> iterable) throws IOException {
            ModuleSourcePathLocationHandler moduleSourcePathLocationHandler;
            List<Path> listCheckPaths = checkPaths(iterable);
            if (this.moduleTable == null) {
                this.moduleTable = new ModuleTable();
            }
            ModuleLocationHandler moduleLocationHandler = this.moduleTable.get(str);
            if (moduleLocationHandler == null) {
                moduleSourcePathLocationHandler = this;
                moduleSourcePathLocationHandler.moduleTable.add(Locations.this.new ModuleLocationHandler(moduleSourcePathLocationHandler, this.location.getName() + "[" + str + "]", str, listCheckPaths, true));
            } else {
                moduleSourcePathLocationHandler = this;
                moduleLocationHandler.searchPath = listCheckPaths;
                moduleSourcePathLocationHandler.moduleTable.updatePaths(moduleLocationHandler);
            }
            moduleSourcePathLocationHandler.explicit = true;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public JavaFileManager.Location getLocationForModule(Path path) {
            ModuleTable moduleTable = this.moduleTable;
            if (moduleTable == null) {
                return null;
            }
            return moduleTable.get(path);
        }
    }

    public JavaFileManager.Location getLocationForModule(JavaFileManager.Location location, Path path) throws IOException {
        LocationHandler handler = getHandler(location);
        if (handler == null) {
            return null;
        }
        return handler.getLocationForModule(path);
    }

    public class ModuleTable {
        private final Map<String, ModuleLocationHandler> nameMap;
        private final Map<Path, ModuleLocationHandler> pathMap;

        private ModuleTable() {
            this.nameMap = new LinkedHashMap();
            this.pathMap = new LinkedHashMap();
        }

        public static /* synthetic */ ModuleLocationHandler b(Map.Entry entry) {
            return (ModuleLocationHandler) entry.getValue();
        }

        public void add(ModuleLocationHandler moduleLocationHandler) {
            this.nameMap.put(moduleLocationHandler.moduleName, moduleLocationHandler);
            Iterator<Path> it = moduleLocationHandler.searchPath.iterator();
            while (it.hasNext()) {
                this.pathMap.put(Locations.normalize(it.next()), moduleLocationHandler);
            }
        }

        public void clear() {
            this.nameMap.clear();
            this.pathMap.clear();
        }

        public boolean contains(Path path) throws IOException {
            return Locations.this.contains(this.pathMap.keySet(), path);
        }

        public Set<JavaFileManager.Location> explicitLocations() {
            return Collections.unmodifiableSet((Set) this.nameMap.entrySet().stream().filter(new Predicate() { // from class: com.sun.tools.javac.file.g
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((Locations.ModuleLocationHandler) ((Map.Entry) obj).getValue()).explicit;
                }
            }).map(new Function() { // from class: com.sun.tools.javac.file.h
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Locations.ModuleTable.b((Map.Entry) obj);
                }
            }).collect(Collectors.toSet()));
        }

        public ModuleLocationHandler get(Path path) {
            while (path != null) {
                ModuleLocationHandler moduleLocationHandler = this.pathMap.get(path);
                if (moduleLocationHandler != null) {
                    return moduleLocationHandler;
                }
                path = path.getParent();
            }
            return null;
        }

        public boolean isEmpty() {
            return this.nameMap.isEmpty();
        }

        public Set<JavaFileManager.Location> locations() {
            return Collections.unmodifiableSet((Set) this.nameMap.values().stream().collect(Collectors.toSet()));
        }

        public void updatePaths(ModuleLocationHandler moduleLocationHandler) {
            Iterator<Map.Entry<Path, ModuleLocationHandler>> it = this.pathMap.entrySet().iterator();
            while (it.hasNext()) {
                if (it.next().getValue() == moduleLocationHandler) {
                    it.remove();
                }
            }
            Iterator<Path> it2 = moduleLocationHandler.searchPath.iterator();
            while (it2.hasNext()) {
                this.pathMap.put(Locations.normalize(it2.next()), moduleLocationHandler);
            }
        }

        public ModuleLocationHandler get(String str) {
            return this.nameMap.get(str);
        }
    }

    public class SearchPath extends LinkedHashSet<Path> {
        private static final long serialVersionUID = 0;
        private final transient Set<Path> canonicalValues;
        private transient Path emptyPathDefault;
        private boolean expandJarClassPaths;

        private SearchPath() {
            this.expandJarClassPaths = false;
            this.canonicalValues = new HashSet();
            this.emptyPathDefault = null;
        }

        private void addDirectory(Path path, final boolean z) {
            if (!Files.isDirectory(path, new LinkOption[0])) {
                if (z) {
                    Locations.this.log.warning(CompilerProperties.LintWarnings.DirPathElementNotFound(path));
                    return;
                }
                return;
            }
            try {
                Stream<Path> list = Files.list(path);
                try {
                    final Locations locations = Locations.this;
                    list.filter(new Predicate() { // from class: com.sun.tools.javac.file.i
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj) {
                            return locations.isArchive((Path) obj);
                        }
                    }).forEach(new Consumer() { // from class: com.sun.tools.javac.file.j
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            this.b.lambda$addDirectory$0(z, (Path) obj);
                        }
                    });
                    list.close();
                } catch (Throwable th) {
                    if (list != null) {
                        try {
                            list.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } catch (IOException unused) {
            }
        }

        private void addJarClassPath(Path path, boolean z) {
            try {
                Iterator<Path> it = Locations.this.fsInfo.getJarClassPath(path).iterator();
                while (it.hasNext()) {
                    lambda$addDirectory$0(it.next(), z);
                }
            } catch (IOException e) {
                Locations.this.log.error(CompilerProperties.Errors.ErrorReadingFile(path, JavacFileManager.getMessage(e)));
            }
        }

        public SearchPath addDirectories(String str, boolean z) {
            boolean z2 = this.expandJarClassPaths;
            this.expandJarClassPaths = true;
            if (str != null) {
                try {
                    Iterator it = Locations.this.getPathEntries(str).iterator();
                    while (it.hasNext()) {
                        addDirectory((Path) it.next(), z);
                    }
                } catch (Throwable th) {
                    this.expandJarClassPaths = z2;
                    throw th;
                }
            }
            this.expandJarClassPaths = z2;
            return this;
        }

        /* JADX INFO: renamed from: addFile, reason: merged with bridge method [inline-methods] */
        public void lambda$addDirectory$0(Path path, boolean z) {
            if (contains(path)) {
                return;
            }
            if (!Locations.this.fsInfo.exists(path)) {
                if (z) {
                    Locations.this.log.warning(CompilerProperties.LintWarnings.PathElementNotFound(path));
                }
                super.add(path);
                return;
            }
            Path canonicalFile = Locations.this.fsInfo.getCanonicalFile(path);
            if (this.canonicalValues.contains(canonicalFile)) {
                return;
            }
            if (Locations.this.fsInfo.isFile(path) && !path.getFileName().toString().endsWith(Module.EXTENSION) && !path.endsWith("modules")) {
                if (!Locations.this.isArchive(path)) {
                    try {
                        FileSystems.newFileSystem(path, (ClassLoader) null).close();
                        if (z) {
                            Locations.this.log.warning(CompilerProperties.LintWarnings.UnexpectedArchiveFile(path));
                        }
                    } catch (IOException | ProviderNotFoundException unused) {
                        if (z) {
                            Locations.this.log.warning(CompilerProperties.LintWarnings.InvalidArchiveFile(path));
                            return;
                        }
                        return;
                    }
                } else if (Locations.this.fsInfo.getJarFSProvider() == null) {
                    Locations.this.log.error(CompilerProperties.Errors.NoZipfsForArchive(path));
                    return;
                }
            }
            super.add(path);
            this.canonicalValues.add(canonicalFile);
            if (this.expandJarClassPaths && Locations.this.fsInfo.isFile(path) && !path.endsWith("modules")) {
                addJarClassPath(path, z);
            }
        }

        public SearchPath addFiles(Iterable<? extends Path> iterable, boolean z) {
            if (iterable != null) {
                Iterator<? extends Path> it = iterable.iterator();
                while (it.hasNext()) {
                    lambda$addDirectory$0(it.next(), z);
                }
            }
            return this;
        }

        public SearchPath emptyPathDefault(Path path) {
            this.emptyPathDefault = path;
            return this;
        }

        public SearchPath expandJarClassPaths(boolean z) {
            this.expandJarClassPaths = z;
            return this;
        }

        public SearchPath addFiles(String str) {
            return addFiles(str, true);
        }

        public SearchPath addFiles(String str, boolean z) {
            if (str != null) {
                addFiles(Locations.this.getPathEntries(str, this.emptyPathDefault), z);
            }
            return this;
        }

        public SearchPath addFiles(Iterable<? extends Path> iterable) {
            return addFiles(iterable, true);
        }

        public SearchPath addDirectories(String str) {
            return addDirectories(str, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Iterable<Path> getPathEntries(String str) {
        return getPathEntries(str, null);
    }

    public class OutputLocationHandler extends BasicLocationHandler {
        private boolean listed;
        private ModuleTable moduleTable;
        private Path outputDir;

        public OutputLocationHandler(JavaFileManager.Location location, Option... optionArr) {
            super(location, optionArr);
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public boolean contains(Path path) throws IOException {
            ModuleTable moduleTable = this.moduleTable;
            if (moduleTable != null) {
                return moduleTable.contains(path);
            }
            return this.outputDir != null && Locations.normalize(path).startsWith(Locations.normalize(this.outputDir));
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public JavaFileManager.Location getLocationForModule(String str) {
            if (this.moduleTable == null) {
                this.moduleTable = new ModuleTable();
            }
            ModuleLocationHandler moduleLocationHandler = this.moduleTable.get(str);
            if (moduleLocationHandler != null) {
                return moduleLocationHandler;
            }
            Path pathResolve = this.outputDir.resolve(str);
            ModuleLocationHandler moduleLocationHandler2 = Locations.this.new ModuleLocationHandler(this, this.location.getName() + "[" + str + "]", str, Collections.singletonList(pathResolve), true);
            this.moduleTable.add(moduleLocationHandler2);
            return moduleLocationHandler2;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public Collection<Path> getPaths() {
            Path path = this.outputDir;
            if (path == null) {
                return null;
            }
            return Collections.singleton(path);
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public boolean handleOption(Option option, String str) {
            if (!this.options.contains(option)) {
                return false;
            }
            this.explicit = true;
            this.outputDir = str == null ? null : Locations.this.getPath(str, new String[0]);
            return true;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public Iterable<Set<JavaFileManager.Location>> listLocationsForModules() throws IOException {
            Path path;
            if (!this.listed && (path = this.outputDir) != null) {
                DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(path);
                try {
                    Iterator<Path> it = directoryStreamNewDirectoryStream.iterator();
                    while (it.hasNext()) {
                        getLocationForModule(it.next().getFileName().toString());
                    }
                    directoryStreamNewDirectoryStream.close();
                    this.listed = true;
                } catch (Throwable th) {
                    if (directoryStreamNewDirectoryStream != null) {
                        try {
                            directoryStreamNewDirectoryStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
            ModuleTable moduleTable = this.moduleTable;
            return (moduleTable == null || moduleTable.isEmpty()) ? Collections.EMPTY_SET : Collections.singleton(this.moduleTable.locations());
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public void setPaths(Iterable<? extends Path> iterable) throws IOException {
            if (iterable == null) {
                this.outputDir = null;
            } else {
                this.explicit = true;
                this.outputDir = checkSingletonDirectory(iterable);
            }
            this.moduleTable = null;
            this.listed = false;
        }

        @Override // com.sun.tools.javac.file.Locations.BasicLocationHandler, com.sun.tools.javac.file.Locations.LocationHandler
        public void setPathsForModule(String str, Iterable<? extends Path> iterable) throws IOException {
            OutputLocationHandler outputLocationHandler;
            Path pathCheckSingletonDirectory = checkSingletonDirectory(iterable);
            if (this.moduleTable == null) {
                this.moduleTable = new ModuleTable();
            }
            ModuleLocationHandler moduleLocationHandler = this.moduleTable.get(str);
            if (moduleLocationHandler == null) {
                outputLocationHandler = this;
                outputLocationHandler.moduleTable.add(Locations.this.new ModuleLocationHandler(outputLocationHandler, this.location.getName() + "[" + str + "]", str, Collections.singletonList(pathCheckSingletonDirectory), true));
            } else {
                outputLocationHandler = this;
                moduleLocationHandler.searchPath = Collections.singletonList(pathCheckSingletonDirectory);
                outputLocationHandler.moduleTable.updatePaths(moduleLocationHandler);
            }
            outputLocationHandler.explicit = true;
        }

        @Override // com.sun.tools.javac.file.Locations.LocationHandler
        public JavaFileManager.Location getLocationForModule(Path path) {
            ModuleTable moduleTable = this.moduleTable;
            if (moduleTable == null) {
                return null;
            }
            return moduleTable.get(path);
        }
    }

    public boolean contains(JavaFileManager.Location location, Path path) throws IOException {
        LocationHandler handler = getHandler(location);
        if (handler != null) {
            return handler.contains(path);
        }
        w01.a("unknown location");
        return false;
    }
}
