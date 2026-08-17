package com.android.tools.r8.internal;

import com.android.tools.r8.AbstractC0005a;
import com.android.tools.r8.ClassFileResourceProvider;
import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.internal.C1641hB;
import com.android.tools.r8.origin.ArchiveEntryOrigin;
import com.android.tools.r8.origin.PathOrigin;
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

/* JADX INFO: renamed from: com.android.tools.r8.internal.hB, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1641hB implements ClassFileResourceProvider, AutoCloseable {
    public static final /* synthetic */ boolean e = true;
    public final Path a;
    public final PathOrigin b;
    public final HashSet c;
    public ZipFile d;

    public C1641hB(Path path, Predicate predicate) {
        this.c = new HashSet();
        this.d = null;
        if (!e && !C0831Sp.a(path)) {
            x1f.a();
            throw null;
        }
        this.a = path;
        this.b = new PathOrigin(path);
        Enumeration<? extends ZipEntry> enumerationEntries = c().entries();
        while (enumerationEntries.hasMoreElements()) {
            String name = enumerationEntries.nextElement().getName();
            if (com.android.tools.r8.utils.v.a(name) && predicate.test(name)) {
                this.c.add(C0929Wj.y(name));
            }
        }
    }

    public final ZipEntry b(String str) throws IOException {
        return c().getEntry(AbstractC0005a.a(1, 1, str) + ".class");
    }

    public final ZipFile c() throws IOException {
        if (this.d == null) {
            try {
                this.d = C0831Sp.a(this.a.toFile(), StandardCharsets.UTF_8);
            } catch (IOException e2) {
                if (Files.exists(this.a, new LinkOption[0])) {
                    throw e2;
                }
                throw new NoSuchFileException(this.a.toString());
            }
        }
        return this.d;
    }

    @Override // java.lang.AutoCloseable
    public final void close() throws IOException {
        ZipFile zipFile = this.d;
        if (zipFile != null) {
            zipFile.close();
            this.d = null;
        }
    }

    @Override // com.android.tools.r8.ClassFileResourceProvider
    public final void finished(DiagnosticsHandler diagnosticsHandler) throws IOException {
        close();
    }

    @Override // com.android.tools.r8.ClassFileResourceProvider
    public final Set getClassDescriptors() {
        return Collections.unmodifiableSet(this.c);
    }

    @Override // com.android.tools.r8.ClassFileResourceProvider
    public final ProgramResource getProgramResource(String str) {
        if (!this.c.contains(str)) {
            return null;
        }
        try {
            ZipEntry zipEntryB = b(str);
            InputStream inputStream = c().getInputStream(zipEntryB);
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

    public static /* synthetic */ boolean c(String str) {
        return true;
    }

    public C1641hB(Path path) {
        this(path, new Predicate() { // from class: r2h
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return C1641hB.c((String) obj);
            }
        });
    }
}
