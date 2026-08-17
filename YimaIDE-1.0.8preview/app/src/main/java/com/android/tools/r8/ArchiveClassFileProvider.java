package com.android.tools.r8;

import com.android.tools.r8.ArchiveClassFileProvider;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C0831Sp;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.K7;
import com.android.tools.r8.origin.ArchiveEntryOrigin;
import com.android.tools.r8.origin.PathOrigin;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ArchiveClassFileProvider implements ClassFileResourceProvider, Closeable {
    static final /* synthetic */ boolean f = true;
    private final Path a;
    private final PathOrigin b;
    private final Predicate c;
    private ZipFile d;
    private HashSet e;

    public ArchiveClassFileProvider(Path path, Predicate<String> predicate) throws IOException {
        this.d = null;
        this.e = null;
        if (!f && !C0831Sp.a(path)) {
            x1f.a();
            throw null;
        }
        this.a = path;
        this.c = predicate;
        this.b = new PathOrigin(path);
        c();
    }

    private ZipEntry b(String str) {
        return c().getEntry(AbstractC0005a.a(1, 1, str) + ".class");
    }

    private ZipFile c() {
        if (this.d == null) {
            try {
                d();
            } catch (IOException e) {
                rc6.a(e);
                return null;
            }
        }
        return this.d;
    }

    private void d() throws IOException {
        boolean z = f;
        if (!z && this.d != null) {
            x1f.a();
            return;
        }
        if (!z && this.e != null) {
            x1f.a();
            return;
        }
        try {
            this.d = C0831Sp.a(this.a.toFile(), StandardCharsets.UTF_8);
            this.e = new HashSet();
            Enumeration<? extends ZipEntry> enumerationEntries = this.d.entries();
            while (enumerationEntries.hasMoreElements()) {
                String name = enumerationEntries.nextElement().getName();
                if (com.android.tools.r8.utils.v.a(name) && this.c.test(name)) {
                    this.e.add(C0929Wj.y(name));
                }
            }
        } catch (IOException e) {
            if (!Files.exists(this.a, new LinkOption[0])) {
                throw new NoSuchFileException(this.a.toString());
            }
            throw e;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        ZipFile zipFile = this.d;
        if (zipFile != null) {
            zipFile.close();
        }
        this.d = null;
        this.e = null;
    }

    @Override // com.android.tools.r8.ClassFileResourceProvider
    public void finished(DiagnosticsHandler diagnosticsHandler) throws IOException {
        close();
    }

    @Override // com.android.tools.r8.ClassFileResourceProvider
    public Set<String> getClassDescriptors() {
        c();
        return Collections.unmodifiableSet(this.e);
    }

    @Override // com.android.tools.r8.ClassFileResourceProvider
    public ProgramResource getProgramResource(String str) {
        c();
        if (!Collections.unmodifiableSet(this.e).contains(str)) {
            return null;
        }
        ZipFile zipFileC = c();
        ZipEntry zipEntryB = b(str);
        try {
            InputStream inputStream = zipFileC.getInputStream(zipEntryB);
            try {
                ProgramResource programResourceFromBytes = ProgramResource.fromBytes(new ArchiveEntryOrigin(zipEntryB.getName(), this.b), ProgramResource.Kind.CF, K7.a(inputStream), Collections.singleton(str));
                inputStream.close();
                return programResourceFromBytes;
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException unused) {
            throw new C0613Ke("Failed to read '" + str, this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean c(String str) {
        return true;
    }

    public ArchiveClassFileProvider(Path path) throws IOException {
        this(path, new Predicate() { // from class: ge0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ArchiveClassFileProvider.c((String) obj);
            }
        });
    }
}
