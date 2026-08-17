package com.android.tools.r8.utils;

import com.android.tools.r8.DataDirectoryResource;
import com.android.tools.r8.DataEntryResource;
import com.android.tools.r8.DataResourceProvider;
import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.ProgramResourceProvider;
import com.android.tools.r8.ResourceException;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C0831Sp;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C1152bV;
import com.android.tools.r8.internal.K7;
import com.android.tools.r8.internal.P40;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.origin.ArchiveEntryOrigin;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.shaking.K0;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.function.Consumer;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ArchiveResourceProvider implements ProgramResourceProvider, DataResourceProvider {
    static final /* synthetic */ boolean d = true;
    private final PathOrigin a;
    private final K0 b;
    private final boolean c;

    public ArchiveResourceProvider(K0 k0, boolean z) {
        if (!d && !C0831Sp.a(k0.a)) {
            x1f.a();
            throw null;
        }
        this.a = new PathOrigin(k0.a);
        this.b = k0;
        this.c = z;
    }

    private ArrayList a() throws IOException {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        try {
            ZipFile zipFileA = C0831Sp.a(this.b.a().toFile(), StandardCharsets.UTF_8);
            try {
                Enumeration<? extends ZipEntry> enumerationEntries = zipFileA.entries();
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    InputStream inputStream = zipFileA.getInputStream(zipEntryNextElement);
                    try {
                        String name = zipEntryNextElement.getName();
                        ArchiveEntryOrigin archiveEntryOrigin = new ArchiveEntryOrigin(name, this.a);
                        if (this.b.a(name)) {
                            if (v.b(name)) {
                                if (!this.c) {
                                    arrayList.add(C1152bV.a(ProgramResource.Kind.DEX, archiveEntryOrigin, K7.a(inputStream), null));
                                }
                            } else if (v.a(name)) {
                                arrayList2.add(C1152bV.a(ProgramResource.Kind.CF, archiveEntryOrigin, K7.a(inputStream), Collections.singleton(C0929Wj.y(name))));
                            }
                        }
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
                zipFileA.close();
                if (arrayList.isEmpty() || arrayList2.isEmpty()) {
                    return !arrayList.isEmpty() ? arrayList : arrayList2;
                }
                throw new C0613Ke("Cannot create android app from an archive '" + this.b + "' containing both DEX and Java-bytecode content", this.a);
            } catch (Throwable th3) {
                try {
                    zipFileA.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
                throw th3;
            }
        } catch (ZipException e) {
            throw new C0613Ke("Zip error while reading '" + this.b + "': " + e.getMessage(), e);
        }
    }

    public static ArchiveResourceProvider fromArchive(Path path, boolean z) {
        int i = AbstractC0551Hu.c;
        return new ArchiveResourceProvider(new K0(path, P40.e, Origin.unknown(), Position.UNKNOWN), z);
    }

    public void accept(Consumer<ProgramResource> consumer) throws ResourceException {
        try {
            ZipFile zipFileA = C0831Sp.a(this.b.a().toFile(), StandardCharsets.UTF_8);
            try {
                Enumeration<? extends ZipEntry> enumerationEntries = zipFileA.entries();
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    String name = zipEntryNextElement.getName();
                    if (this.b.a(name) && (v.a(name) || (Wf0.i(name).endsWith(".dex") && !this.c))) {
                        ArchiveEntryOrigin archiveEntryOrigin = new ArchiveEntryOrigin(name, this.a);
                        InputStream inputStream = zipFileA.getInputStream(zipEntryNextElement);
                        try {
                            if (v.b(name)) {
                                consumer.accept(C1152bV.a(ProgramResource.Kind.DEX, archiveEntryOrigin, K7.a(inputStream), null));
                            } else if (v.a(name)) {
                                consumer.accept(C1152bV.a(ProgramResource.Kind.CF, archiveEntryOrigin, K7.a(inputStream), Collections.singleton(C0929Wj.y(name))));
                            }
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
                }
                zipFileA.close();
            } catch (Throwable th3) {
                try {
                    zipFileA.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
                throw th3;
            }
        } catch (ZipException e) {
            throw new ResourceException(this.a, new C0613Ke("Zip error while reading '" + this.b + "': " + e.getMessage(), e));
        } catch (IOException e2) {
            throw new ResourceException(this.a, new C0613Ke("I/O exception while reading '" + this.b + "': " + e2.getMessage(), e2));
        }
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public /* bridge */ /* synthetic */ void finished(DiagnosticsHandler diagnosticsHandler) throws IOException {
        super.finished(diagnosticsHandler);
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public DataResourceProvider getDataResourceProvider() {
        return this;
    }

    public Origin getOrigin() {
        return this.a;
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public Collection<ProgramResource> getProgramResources() throws ResourceException {
        try {
            return a();
        } catch (IOException e) {
            throw new ResourceException(this.a, e);
        }
    }

    @Override // com.android.tools.r8.DataResourceProvider
    public void accept(DataResourceProvider.Visitor visitor) throws ResourceException {
        try {
            ZipFile zipFileA = C0831Sp.a(this.b.a().toFile(), StandardCharsets.UTF_8);
            try {
                Enumeration<? extends ZipEntry> enumerationEntries = zipFileA.entries();
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    String name = zipEntryNextElement.getName();
                    if (this.b.a(name) && !v.a(name) && (!Wf0.i(name).endsWith(".dex") || this.c)) {
                        if (zipEntryNextElement.isDirectory()) {
                            visitor.visit(DataDirectoryResource.fromZip(zipFileA, zipEntryNextElement));
                        } else {
                            visitor.visit(DataEntryResource.fromZip(zipFileA, zipEntryNextElement));
                        }
                    }
                }
                zipFileA.close();
            } catch (Throwable th) {
                try {
                    zipFileA.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (ZipException e) {
            throw new ResourceException(this.a, new C0613Ke("Zip error while reading '" + this.b + "': " + e.getMessage(), e));
        } catch (IOException e2) {
            throw new ResourceException(this.a, new C0613Ke("I/O exception while reading '" + this.b + "': " + e2.getMessage(), e2));
        }
    }
}
