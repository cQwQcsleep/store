package com.android.tools.r8.internal;

import com.android.tools.r8.DataResourceProvider;
import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.ProgramResourceProvider;
import com.android.tools.r8.ResourceException;
import com.android.tools.r8.origin.ArchiveEntryOrigin;
import com.android.tools.r8.origin.PathOrigin;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/* JADX INFO: renamed from: com.android.tools.r8.internal.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1448f implements ProgramResourceProvider {
    public static final /* synthetic */ boolean c = true;
    public final ArchiveEntryOrigin a;
    public final Path b;

    public C1448f(Path path) {
        if (!c && !C0831Sp.a(path)) {
            x1f.a();
            throw null;
        }
        this.a = new ArchiveEntryOrigin("classes.jar", new PathOrigin(path));
        this.b = path;
    }

    public final List a() throws IOException {
        ArrayList arrayListA;
        try {
            ZipFile zipFileA = C0831Sp.a(this.b.toFile(), StandardCharsets.UTF_8);
            try {
                Enumeration<? extends ZipEntry> enumerationEntries = zipFileA.entries();
                while (true) {
                    if (!enumerationEntries.hasMoreElements()) {
                        arrayListA = null;
                        break;
                    }
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    InputStream inputStream = zipFileA.getInputStream(zipEntryNextElement);
                    try {
                        if (zipEntryNextElement.getName().equals("classes.jar")) {
                            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
                            try {
                                arrayListA = a(zipInputStream);
                                zipInputStream.close();
                                if (inputStream == null) {
                                    break;
                                }
                                inputStream.close();
                                break;
                            } catch (Throwable th) {
                                try {
                                    zipInputStream.close();
                                } catch (Throwable th2) {
                                    th.addSuppressed(th2);
                                }
                                throw th;
                            }
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                    } catch (Throwable th3) {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Throwable th4) {
                                th3.addSuppressed(th4);
                            }
                        }
                        throw th3;
                    }
                    try {
                        zipFileA.close();
                    } catch (Throwable th5) {
                        th.addSuppressed(th5);
                    }
                    throw th;
                }
                zipFileA.close();
                return arrayListA == null ? Collections.EMPTY_LIST : arrayListA;
            } catch (Throwable th6) {
                zipFileA.close();
                throw th6;
            }
        } catch (ZipException e) {
            throw new C0613Ke("Zip error while reading '" + this.b + "': " + e.getMessage(), e);
        }
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public final DataResourceProvider getDataResourceProvider() {
        return null;
    }

    @Override // com.android.tools.r8.ProgramResourceProvider
    public final Collection getProgramResources() throws ResourceException {
        try {
            return a();
        } catch (IOException e) {
            throw new ResourceException(this.a, e);
        }
    }

    public final ArrayList a(ZipInputStream zipInputStream) throws IOException {
        ArrayList arrayList = new ArrayList();
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                return arrayList;
            }
            String name = nextEntry.getName();
            if (com.android.tools.r8.utils.v.a(name)) {
                arrayList.add(new C1152bV(new ArchiveEntryOrigin(name, this.a), ProgramResource.Kind.CF, K7.a(zipInputStream), Collections.singleton(C0929Wj.y(name))));
            }
        }
    }
}
