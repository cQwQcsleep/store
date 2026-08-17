package com.sun.tools.javac.platform;

import com.sun.nio.zipfs.ZipFileSystemProvider;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import com.sun.source.util.Plugin;
import com.sun.tools.javac.ConfigProvider;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.file.CacheFSInfo;
import com.sun.tools.javac.file.JavacFileManager;
import com.sun.tools.javac.jvm.Target;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.platform.JDKPlatformProvider;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.StringUtils;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.ProviderNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import javax.annotation.processing.Processor;
import javax.tools.ForwardingJavaFileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JDKPlatformProvider implements PlatformProvider {
    private static final Map<String, ?> CT_SYM_ZIP_ENV;
    public static final Comparator<String> NUMERICAL_COMPARATOR;
    private static final Set<String> SUPPORTED_JAVA_PLATFORM_VERSIONS;
    private static final String[] symbolFileLocation = {"lib", "ct.sym"};

    public static class PlatformDescriptionImpl implements PlatformDescription {
        private final Map<Path, FileSystem> ctSym2FileSystem = new HashMap();
        private final String ctSymVersion;
        private final String sourceVersion;

        /* JADX INFO: renamed from: com.sun.tools.javac.platform.JDKPlatformProvider$PlatformDescriptionImpl$1, reason: invalid class name */
        public class AnonymousClass1 extends JavacFileManager {
            public AnonymousClass1(Context context, boolean z, Charset charset) {
                super(context, z, charset);
            }

            public static /* synthetic */ Iterator p(AnonymousClass1 anonymousClass1, Iterable iterable, Set set) {
                anonymousClass1.getClass();
                return new Iterator<JavaFileObject>(iterable, set) { // from class: com.sun.tools.javac.platform.JDKPlatformProvider.PlatformDescriptionImpl.1.1
                    private JavaFileObject next;
                    private final Iterator<JavaFileObject> original;
                    final /* synthetic */ Set val$kinds;
                    final /* synthetic */ Iterable val$listed;

                    {
                        this.val$listed = iterable;
                        this.val$kinds = set;
                        this.original = iterable.iterator();
                    }

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        if (this.next == null) {
                            while (this.original.hasNext()) {
                                JavaFileObject next = this.original.next();
                                if (next.getKind() == JavaFileObject.Kind.OTHER && next.getName().endsWith(".sig")) {
                                    this.next = new SigJavaFileObject(next);
                                    break;
                                }
                                if (this.val$kinds.contains(next.getKind())) {
                                    this.next = next;
                                    break;
                                }
                            }
                        }
                        return this.next != null;
                    }

                    @Override // java.util.Iterator
                    public JavaFileObject next() {
                        if (!hasNext()) {
                            z0e.a();
                            return null;
                        }
                        JavaFileObject javaFileObject = this.next;
                        this.next = null;
                        return javaFileObject;
                    }
                };
            }

            @Override // com.sun.tools.javac.file.JavacFileManager, javax.tools.JavaFileManager
            public JavaFileObject getJavaFileForInput(JavaFileManager.Location location, String str, JavaFileObject.Kind kind) throws IOException {
                if (kind == JavaFileObject.Kind.CLASS) {
                    JavaFileObject javaFileObject = (JavaFileObject) getFileForInput(location, "", str.replace('.', '/') + ".sig");
                    if (javaFileObject != null) {
                        return new SigJavaFileObject(javaFileObject);
                    }
                }
                return super.getJavaFileForInput(location, str, kind);
            }

            @Override // com.sun.tools.javac.file.JavacFileManager, javax.tools.JavaFileManager
            public boolean hasLocation(JavaFileManager.Location location) {
                return super.hasExplicitLocation(location);
            }

            @Override // com.sun.tools.javac.file.JavacFileManager, javax.tools.JavaFileManager
            public String inferBinaryName(JavaFileManager.Location location, JavaFileObject javaFileObject) {
                if (javaFileObject instanceof SigJavaFileObject) {
                    javaFileObject = ((SigJavaFileObject) javaFileObject).getDelegate();
                }
                return super.inferBinaryName(location, javaFileObject);
            }

            @Override // com.sun.tools.javac.file.JavacFileManager, javax.tools.JavaFileManager
            public Iterable<JavaFileObject> list(JavaFileManager.Location location, String str, final Set<JavaFileObject.Kind> set, boolean z) throws IOException {
                EnumSet enumSetCopyOf = EnumSet.copyOf((Collection) set);
                enumSetCopyOf.add(JavaFileObject.Kind.OTHER);
                final Iterable<JavaFileObject> list = super.list(location, str, enumSetCopyOf, z);
                return new Iterable() { // from class: com.sun.tools.javac.platform.b
                    @Override // java.lang.Iterable
                    public final Iterator iterator() {
                        return JDKPlatformProvider.PlatformDescriptionImpl.AnonymousClass1.p(this.b, list, set);
                    }
                };
            }
        }

        public static class SigJavaFileObject extends ForwardingJavaFileObject<JavaFileObject> {
            public SigJavaFileObject(JavaFileObject javaFileObject) {
                super(javaFileObject);
            }

            public JavaFileObject getDelegate() {
                return (JavaFileObject) this.fileObject;
            }

            @Override // javax.tools.ForwardingJavaFileObject, javax.tools.JavaFileObject
            public JavaFileObject.Kind getKind() {
                return JavaFileObject.Kind.CLASS;
            }

            @Override // javax.tools.ForwardingJavaFileObject, javax.tools.JavaFileObject
            public boolean isNameCompatible(String str, JavaFileObject.Kind kind) {
                return super.isNameCompatible(str + ".sig", JavaFileObject.Kind.OTHER);
            }
        }

        public PlatformDescriptionImpl(String str) {
            this.sourceVersion = str;
            this.ctSymVersion = StringUtils.toUpperCase(Integer.toString(Integer.parseInt(str), 36));
        }

        public static /* synthetic */ List c(String str) {
            return new ArrayList();
        }

        @Override // com.sun.tools.javac.platform.PlatformDescription, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            Iterator<FileSystem> it = this.ctSym2FileSystem.values().iterator();
            while (it.hasNext()) {
                it.next().close();
            }
            this.ctSym2FileSystem.clear();
        }

        @Override // com.sun.tools.javac.platform.PlatformDescription
        public List<String> getAdditionalOptions() {
            return Collections.EMPTY_LIST;
        }

        @Override // com.sun.tools.javac.platform.PlatformDescription
        public List<PlatformDescription.PluginInfo<Processor>> getAnnotationProcessors() {
            return Collections.EMPTY_LIST;
        }

        @Override // com.sun.tools.javac.platform.PlatformDescription
        public JavaFileManager getFileManager() {
            Context context = new Context();
            context.put(Log.errKey, new PrintWriter((OutputStream) System.err, true));
            CacheFSInfo.preRegister(context);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(context, true, null);
            anonymousClass1.handleOption(Option.MULTIRELEASE, this.sourceVersion);
            Path pathFindCtSym = JDKPlatformProvider.findCtSym();
            if (!Files.exists(pathFindCtSym, new LinkOption[0])) {
                k2d.a("Cannot find ct.sym!");
                return null;
            }
            try {
                FileSystem fileSystemNewFileSystem = this.ctSym2FileSystem.get(pathFindCtSym);
                if (fileSystemNewFileSystem == null) {
                    fileSystemNewFileSystem = new ZipFileSystemProvider().newFileSystem(pathFindCtSym, JDKPlatformProvider.CT_SYM_ZIP_ENV);
                    this.ctSym2FileSystem.put(pathFindCtSym, fileSystemNewFileSystem);
                }
                Path next = fileSystemNewFileSystem.getRootDirectories().iterator().next();
                if (!Source.Feature.MODULES.allowedInSource(Source.lookup(this.sourceVersion))) {
                    ArrayList arrayList = new ArrayList();
                    DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(next);
                    try {
                        for (Path path : directoryStreamNewDirectoryStream) {
                            if (path.getFileName().toString().contains(this.ctSymVersion) && !path.getFileName().toString().contains("-")) {
                                DirectoryStream<Path> directoryStreamNewDirectoryStream2 = Files.newDirectoryStream(path);
                                try {
                                    Iterator<Path> it = directoryStreamNewDirectoryStream2.iterator();
                                    while (it.hasNext()) {
                                        arrayList.add(it.next());
                                    }
                                    directoryStreamNewDirectoryStream2.close();
                                } catch (Throwable th) {
                                    if (directoryStreamNewDirectoryStream2 != null) {
                                        try {
                                            directoryStreamNewDirectoryStream2.close();
                                        } catch (Throwable th2) {
                                            th.addSuppressed(th2);
                                        }
                                    }
                                    throw th;
                                }
                            }
                        }
                        directoryStreamNewDirectoryStream.close();
                        anonymousClass1.setLocationFromPaths(StandardLocation.PLATFORM_CLASS_PATH, arrayList);
                        return anonymousClass1;
                    } catch (Throwable th3) {
                        if (directoryStreamNewDirectoryStream != null) {
                            try {
                                directoryStreamNewDirectoryStream.close();
                            } catch (Throwable th4) {
                                th3.addSuppressed(th4);
                            }
                        }
                        throw th3;
                    }
                }
                HashMap map = new HashMap();
                DirectoryStream<Path> directoryStreamNewDirectoryStream3 = Files.newDirectoryStream(next);
                try {
                    for (Path path2 : directoryStreamNewDirectoryStream3) {
                        if (path2.getFileName().toString().contains(this.ctSymVersion) && !path2.getFileName().toString().contains("-")) {
                            DirectoryStream<Path> directoryStreamNewDirectoryStream4 = Files.newDirectoryStream(path2);
                            try {
                                for (Path path3 : directoryStreamNewDirectoryStream4) {
                                    String string = path3.getFileName().toString();
                                    if (string.endsWith(PsuedoNames.PSEUDONAME_ROOT)) {
                                        string = string.substring(0, string.length() - 1);
                                    }
                                    ((List) map.computeIfAbsent(string, new Function() { // from class: com.sun.tools.javac.platform.a
                                        @Override // java.util.function.Function
                                        public final Object apply(Object obj) {
                                            return JDKPlatformProvider.PlatformDescriptionImpl.c((String) obj);
                                        }
                                    })).add(path3);
                                }
                                directoryStreamNewDirectoryStream4.close();
                            } catch (Throwable th5) {
                                if (directoryStreamNewDirectoryStream4 != null) {
                                    try {
                                        directoryStreamNewDirectoryStream4.close();
                                    } catch (Throwable th6) {
                                        th5.addSuppressed(th6);
                                    }
                                }
                                throw th5;
                            }
                        }
                    }
                    directoryStreamNewDirectoryStream3.close();
                    anonymousClass1.handleOption("--system", Arrays.asList(Option.LINT_CUSTOM_NONE).iterator());
                    for (Map.Entry entry : map.entrySet()) {
                        anonymousClass1.setLocationForModule(StandardLocation.SYSTEM_MODULES, (String) entry.getKey(), (Collection) entry.getValue());
                    }
                    return anonymousClass1;
                } catch (Throwable th7) {
                    if (directoryStreamNewDirectoryStream3 != null) {
                        try {
                            directoryStreamNewDirectoryStream3.close();
                        } catch (Throwable th8) {
                            th7.addSuppressed(th8);
                        }
                    }
                    throw th7;
                }
            } catch (IOException e) {
                e7f.a(e);
                return null;
            }
            e7f.a(e);
            return null;
        }

        @Override // com.sun.tools.javac.platform.PlatformDescription
        public List<PlatformDescription.PluginInfo<Plugin>> getPlugins() {
            return Collections.EMPTY_LIST;
        }

        @Override // com.sun.tools.javac.platform.PlatformDescription
        public String getSourceVersion() {
            return this.sourceVersion;
        }

        @Override // com.sun.tools.javac.platform.PlatformDescription
        public String getTargetVersion() {
            return this.sourceVersion;
        }
    }

    static {
        Map<String, ?> mapOf = Map.of("accessMode", "readOnly", "zipinfo-time", "false");
        CT_SYM_ZIP_ENV = mapOf;
        Comparator<String> comparator = new Comparator() { // from class: ja7
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return JDKPlatformProvider.a((String) obj, (String) obj2);
            }
        };
        NUMERICAL_COMPARATOR = comparator;
        SUPPORTED_JAVA_PLATFORM_VERSIONS = new TreeSet(comparator);
        Path pathFindCtSym = findCtSym();
        if (Files.exists(pathFindCtSym, new LinkOption[0])) {
            try {
                FileSystem fileSystemNewFileSystem = new ZipFileSystemProvider().newFileSystem(pathFindCtSym, mapOf);
                try {
                    DirectoryStream<Path> directoryStreamNewDirectoryStream = Files.newDirectoryStream(fileSystemNewFileSystem.getRootDirectories().iterator().next());
                    try {
                        for (Path path : directoryStreamNewDirectoryStream) {
                            if (!path.getFileName().toString().contains("-")) {
                                for (char c : path.getFileName().toString().toCharArray()) {
                                    if (Character.digit(c, 36) != -1) {
                                        try {
                                            Target targetLookup = Target.lookup(Integer.toString(Integer.parseInt(Character.toString(c), 36)));
                                            if (targetLookup != null) {
                                                SUPPORTED_JAVA_PLATFORM_VERSIONS.add(targetNumericVersion(targetLookup));
                                            } else {
                                                continue;
                                            }
                                        } catch (NumberFormatException unused) {
                                            continue;
                                        }
                                    }
                                }
                            }
                        }
                        directoryStreamNewDirectoryStream.close();
                        fileSystemNewFileSystem.close();
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
                } catch (Throwable th3) {
                    if (fileSystemNewFileSystem != null) {
                        try {
                            fileSystemNewFileSystem.close();
                        } catch (Throwable th4) {
                            th3.addSuppressed(th4);
                        }
                    }
                    throw th3;
                }
            } catch (IOException | ProviderNotFoundException unused2) {
            }
        }
    }

    public static /* synthetic */ int a(String str, String str2) {
        int i;
        int i2 = Integer.MAX_VALUE;
        try {
            i = Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            i = Integer.MAX_VALUE;
        }
        try {
            i2 = Integer.parseInt(str2);
        } catch (NumberFormatException unused2) {
        }
        return i != i2 ? i - i2 : str.compareTo(str2);
    }

    public static Path findCtSym() {
        Path pathResolve = Paths.get(ConfigProvider.getJavaHome(), new String[0]);
        for (String str : symbolFileLocation) {
            pathResolve = pathResolve.resolve(str);
        }
        return pathResolve;
    }

    private static String targetNumericVersion(Target target) {
        return Integer.toString((target.ordinal() - Target.JDK1_1.ordinal()) + 1);
    }

    @Override // com.sun.tools.javac.platform.PlatformProvider
    public PlatformDescription getPlatform(String str, String str2) throws PlatformProvider.PlatformNotSupported {
        if (SUPPORTED_JAVA_PLATFORM_VERSIONS.contains(str)) {
            return getPlatformTrusted(str);
        }
        throw new PlatformProvider.PlatformNotSupported();
    }

    public PlatformDescription getPlatformTrusted(String str) {
        return new PlatformDescriptionImpl(str);
    }

    @Override // com.sun.tools.javac.platform.PlatformProvider
    public Iterable<String> getSupportedPlatformNames() {
        return SUPPORTED_JAVA_PLATFORM_VERSIONS;
    }
}
