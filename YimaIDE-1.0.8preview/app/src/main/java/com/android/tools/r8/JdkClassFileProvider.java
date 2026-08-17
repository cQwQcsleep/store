package com.android.tools.r8;

import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C0831Sp;
import com.android.tools.r8.internal.C0903Vj;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import java.io.Closeable;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class JdkClassFileProvider implements ClassFileResourceProvider, Closeable {
    static final /* synthetic */ boolean f = true;
    private final Origin a;
    private final HashSet b;
    private final HashMap c;
    private final URLClassLoader d;
    private FileSystem e;

    private JdkClassFileProvider(Path path) {
        this.b = new HashSet();
        this.c = new HashMap();
        this.a = new PathOrigin(path);
        Path pathResolve = path.resolve("lib").resolve("jrt-fs.jar");
        if (!f && !Files.exists(pathResolve, new LinkOption[0])) {
            x1f.a();
            throw null;
        }
        URLClassLoader uRLClassLoader = new URLClassLoader(new URL[]{pathResolve.toUri().toURL()});
        this.d = uRLClassLoader;
        a(FileSystems.newFileSystem(URI.create("jrt:/"), Collections.EMPTY_MAP, uRLClassLoader));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Path path) {
        if (C0831Sp.b(path)) {
            C0903Vj c0903VjX = C0929Wj.x(path.toString());
            this.c.put(c0903VjX.b, c0903VjX.a);
            this.b.add(c0903VjX.b);
        }
    }

    public static ClassFileResourceProvider fromJavaRuntimeJar(Path path) throws IOException {
        return new ArchiveClassFileProvider(path);
    }

    public static ClassFileResourceProvider fromJdkHome(Path path) throws IOException {
        if (Files.exists(path.resolve("lib").resolve("jrt-fs.jar"), new LinkOption[0])) {
            return fromSystemModulesJdk(path);
        }
        Path pathResolve = path.resolve("jre").resolve("lib").resolve("rt.jar");
        if (Files.exists(pathResolve, new LinkOption[0])) {
            return fromJavaRuntimeJar(pathResolve);
        }
        Path pathResolve2 = path.resolve("lib").resolve("rt.jar");
        if (Files.exists(pathResolve2, new LinkOption[0])) {
            return fromJavaRuntimeJar(pathResolve2);
        }
        xba.a("Path ", path, " does not look like a Java home");
        return null;
    }

    public static ClassFileResourceProvider fromSystemJdk() throws IOException {
        return new JdkClassFileProvider();
    }

    public static ClassFileResourceProvider fromSystemModulesJdk(Path path) throws IOException {
        Path pathResolve = path.resolve("lib").resolve("jrt-fs.jar");
        if (Files.exists(pathResolve, new LinkOption[0])) {
            return new JdkClassFileProvider(path);
        }
        throw new NoSuchFileException(pathResolve.toString());
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.e.close();
        URLClassLoader uRLClassLoader = this.d;
        if (uRLClassLoader != null) {
            uRLClassLoader.close();
        }
    }

    public void finalize() throws Throwable {
        close();
        super.finalize();
    }

    @Override // com.android.tools.r8.ClassFileResourceProvider
    public /* bridge */ /* synthetic */ void finished(DiagnosticsHandler diagnosticsHandler) throws IOException {
        super.finished(diagnosticsHandler);
    }

    @Override // com.android.tools.r8.ClassFileResourceProvider
    public Set<String> getClassDescriptors() {
        return Collections.unmodifiableSet(this.b);
    }

    @Override // com.android.tools.r8.ClassFileResourceProvider
    public ProgramResource getProgramResource(String str) {
        if (!this.b.contains(str)) {
            return null;
        }
        try {
            return ProgramResource.fromBytes(Origin.unknown(), ProgramResource.Kind.CF, Files.readAllBytes(this.e.getPath("modules", (String) this.c.get(str), C0929Wj.s(str))), Collections.singleton(str));
        } catch (IOException unused) {
            throw new C0613Ke("Failed to read '" + str, this.a);
        }
    }

    private void a(FileSystem fileSystem) {
        this.e = fileSystem;
        Files.walk(fileSystem.getPath("/modules", new String[0]), new FileVisitOption[0]).forEach(new Consumer() { // from class: ho7
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.a((Path) obj);
            }
        });
    }

    private JdkClassFileProvider() {
        this.b = new HashSet();
        this.c = new HashMap();
        this.a = Origin.unknown();
        a(FileSystems.newFileSystem(URI.create("jrt:/"), (Map<String, ?>) Collections.EMPTY_MAP));
    }
}
