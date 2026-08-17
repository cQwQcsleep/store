package com.android.tools.r8;

import com.android.tools.r8.ArchiveProgramResourceProvider;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C0831Sp;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.K7;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.origin.ArchiveEntryOrigin;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import defpackage.he0;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ArchiveProgramResourceProvider implements ProgramResourceProvider {
    static final /* synthetic */ boolean d = true;
    private final Origin a;
    private final ZipFileSupplier b;
    private final Predicate c;

    public interface ZipFileSupplier {
        ZipFile open() throws IOException;
    }

    public interface a {
        void a(ArchiveEntryOrigin archiveEntryOrigin, InputStream inputStream) throws IOException;
    }

    private ArchiveProgramResourceProvider(Origin origin, ZipFileSupplier zipFileSupplier, Predicate predicate) {
        boolean z = d;
        if (!z && origin == null) {
            x1f.a();
            throw null;
        }
        if (!z && zipFileSupplier == null) {
            x1f.a();
            throw null;
        }
        if (!z && predicate == null) {
            x1f.a();
            throw null;
        }
        this.a = origin;
        this.b = zipFileSupplier;
        this.c = predicate;
    }

    public static ArchiveProgramResourceProvider fromArchive(final Path path, Predicate<String> predicate) {
        return fromSupplier(new PathOrigin(path), new ZipFileSupplier() { // from class: ie0
            @Override // com.android.tools.r8.ArchiveProgramResourceProvider.ZipFileSupplier
            public final ZipFile open() {
                return ArchiveProgramResourceProvider.a(path);
            }
        }, predicate);
    }

    public static ArchiveProgramResourceProvider fromSupplier(Origin origin, ZipFileSupplier zipFileSupplier) {
        return fromSupplier(origin, zipFileSupplier, new he0());
    }

    public static boolean includeClassFileEntries(String str) {
        return com.android.tools.r8.utils.v.a(str);
    }

    public static boolean includeClassFileOrDexEntries(String str) {
        return com.android.tools.r8.utils.v.a(str) || Wf0.i(str).endsWith(".dex");
    }

    public static boolean includeDexEntries(String str) {
        boolean z = com.android.tools.r8.utils.v.a;
        return Wf0.i(str).endsWith(".dex");
    }

    public void a(a aVar) throws IOException {
        try {
            ZipFile zipFileOpen = this.b.open();
            try {
                Enumeration<? extends ZipEntry> enumerationEntries = zipFileOpen.entries();
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    InputStream inputStream = zipFileOpen.getInputStream(zipEntryNextElement);
                    try {
                        aVar.a(new ArchiveEntryOrigin(zipEntryNextElement.getName(), this.a), inputStream);
                        if (inputStream != null) {
                            inputStream.close();
                        }
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
                }
                zipFileOpen.close();
            } catch (Throwable th3) {
                if (zipFileOpen != null) {
                    try {
                        zipFileOpen.close();
                    } catch (Throwable th4) {
                        th3.addSuppressed(th4);
                    }
                }
                throw th3;
            }
        } catch (ZipException e) {
            throw new C0613Ke(this.a, "Zip error while reading archive" + e.getMessage(), e);
        }
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public /* bridge */ /* synthetic */ void finished(DiagnosticsHandler diagnosticsHandler) throws IOException {
        super.finished(diagnosticsHandler);
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public Collection<ProgramResource> getProgramResources() throws ResourceException {
        try {
            final ArrayList arrayList = new ArrayList();
            final ArrayList arrayList2 = new ArrayList();
            a(new a() { // from class: com.android.tools.r8.v0
                @Override // com.android.tools.r8.ArchiveProgramResourceProvider.a
                public final void a(ArchiveEntryOrigin archiveEntryOrigin, InputStream inputStream) {
                    this.a.a(arrayList, arrayList2, archiveEntryOrigin, inputStream);
                }
            });
            if (!arrayList.isEmpty() && !arrayList2.isEmpty()) {
                throw new C0613Ke(this.a, "Cannot create android app from an archive containing both DEX and Java-bytecode content.", null);
            }
            return !arrayList.isEmpty() ? arrayList : arrayList2;
        } catch (IOException e) {
            throw new ResourceException(this.a, e);
        }
    }

    public static ArchiveProgramResourceProvider fromSupplier(Origin origin, ZipFileSupplier zipFileSupplier, Predicate<String> predicate) {
        return new ArchiveProgramResourceProvider(origin, zipFileSupplier, predicate);
    }

    public static ArchiveProgramResourceProvider fromArchive(Path path) {
        return fromArchive(path, new he0());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ ZipFile a(Path path) {
        return C0831Sp.a(path.toFile(), StandardCharsets.UTF_8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List list, List list2, ArchiveEntryOrigin archiveEntryOrigin, InputStream inputStream) {
        String entryName = archiveEntryOrigin.getEntryName();
        if (this.c.test(entryName)) {
            boolean z = com.android.tools.r8.utils.v.a;
            if (Wf0.i(entryName).endsWith(".dex")) {
                list.add(ProgramResource.fromBytes(archiveEntryOrigin, ProgramResource.Kind.DEX, K7.a(inputStream), null));
            } else if (com.android.tools.r8.utils.v.a(entryName)) {
                list2.add(ProgramResource.fromBytes(archiveEntryOrigin, ProgramResource.Kind.CF, K7.a(inputStream), Collections.singleton(C0929Wj.y(entryName))));
            }
        }
    }
}
