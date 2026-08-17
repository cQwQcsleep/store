package com.android.tools.r8.internal;

import com.android.tools.r8.ByteDataView;
import com.android.tools.r8.DataEntryResource;
import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.ResourceException;
import com.android.tools.r8.origin.PathOrigin;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

/* JADX INFO: renamed from: com.android.tools.r8.internal.a3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1031a3 implements YV {
    public static final /* synthetic */ boolean i = true;
    public final Path a;
    public final PathOrigin b;
    public ZipOutputStream c = null;
    public boolean d = false;
    public int e = 0;
    public int f = 0;
    public final HashMap g = new HashMap();
    public final TreeSet h = new TreeSet();

    public C1031a3(Path path) {
        this.a = path;
        this.b = new PathOrigin(path);
    }

    @Override // com.android.tools.r8.internal.YV
    public final synchronized void a(DiagnosticsHandler diagnosticsHandler) {
        try {
            boolean z = i;
            if (!z && this.d) {
                throw new AssertionError();
            }
            int i2 = this.e - 1;
            this.e = i2;
            if (i2 == 0) {
                if (!z && !this.g.isEmpty()) {
                    throw new AssertionError();
                }
                for (Z2 z2 : this.h) {
                    if (z2.d) {
                        if (!i && z2.c != null) {
                            throw new AssertionError();
                        }
                        b(z2.b, diagnosticsHandler);
                    } else {
                        if (!i && z2.c == null) {
                            throw new AssertionError();
                        }
                        a(z2.b, z2.c, diagnosticsHandler, z2.e);
                    }
                }
                this.d = true;
                try {
                    ZipOutputStream zipOutputStream = this.c;
                    if (zipOutputStream == null) {
                        zipOutputStream = new ZipOutputStream(new BufferedOutputStream(Files.newOutputStream(this.a, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)));
                        this.c = zipOutputStream;
                    }
                    zipOutputStream.close();
                    this.c = null;
                } catch (IOException e) {
                    diagnosticsHandler.error(new ExceptionDiagnostic(e, this.b));
                }
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    public final void b(String str, DiagnosticsHandler diagnosticsHandler) {
        if (str.charAt(str.length() - 1) != '/') {
            str = str.concat("/");
        }
        ZipEntry zipEntry = new ZipEntry(str);
        zipEntry.setTime(0L);
        synchronized (this) {
            try {
                ZipOutputStream zipOutputStreamA = a();
                zipOutputStreamA.putNextEntry(zipEntry);
                zipOutputStreamA.closeEntry();
            } catch (IOException e) {
                ExceptionDiagnostic exceptionDiagnostic = new ExceptionDiagnostic(e, this.b);
                if ((e instanceof ZipException) && e.getMessage().startsWith("duplicate entry")) {
                    diagnosticsHandler.warning(exceptionDiagnostic);
                } else {
                    diagnosticsHandler.error(exceptionDiagnostic);
                }
            }
        }
    }

    @Override // com.android.tools.r8.internal.YV
    public final PathOrigin getOrigin() {
        return this.b;
    }

    @Override // com.android.tools.r8.internal.YV
    public final Path getPath() {
        return this.a;
    }

    @Override // com.android.tools.r8.internal.YV
    public final synchronized void open() {
        try {
            if (!i && this.d) {
                throw new AssertionError();
            }
            this.e++;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized ZipOutputStream a() {
        ZipOutputStream zipOutputStream;
        try {
            if (!i && this.d) {
                throw new AssertionError();
            }
            zipOutputStream = this.c;
            if (zipOutputStream == null) {
                zipOutputStream = new ZipOutputStream(new BufferedOutputStream(Files.newOutputStream(this.a, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)));
                this.c = zipOutputStream;
            }
        } catch (Throwable th) {
            throw th;
        }
        return zipOutputStream;
    }

    @Override // com.android.tools.r8.internal.YV
    public final synchronized void a(String str, DiagnosticsHandler diagnosticsHandler) {
        this.h.add(new Z2(str, null, true, true));
    }

    @Override // com.android.tools.r8.internal.YV
    public final void a(String str, DataEntryResource dataEntryResource, DiagnosticsHandler diagnosticsHandler) {
        try {
            InputStream byteStream = dataEntryResource.getByteStream();
            try {
                ByteDataView byteDataViewOf = ByteDataView.of(K7.a(byteStream));
                synchronized (this) {
                    try {
                        if (AbstractC2735u2.a(str)) {
                            a(str, byteDataViewOf, diagnosticsHandler, true);
                        } else {
                            this.h.add(Z2.a(str, byteDataViewOf));
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                byteStream.close();
            } catch (Throwable th2) {
                if (byteStream != null) {
                    try {
                        byteStream.close();
                    } catch (Throwable th3) {
                        th2.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        } catch (ResourceException e) {
            diagnosticsHandler.error(new StringDiagnostic("Failed to open input: " + e.getMessage(), dataEntryResource.getOrigin()));
        } catch (IOException e2) {
            ExceptionDiagnostic exceptionDiagnostic = new ExceptionDiagnostic(e2, this.b);
            if ((e2 instanceof ZipException) && e2.getMessage().startsWith("duplicate entry")) {
                diagnosticsHandler.warning(exceptionDiagnostic);
            } else {
                diagnosticsHandler.error(exceptionDiagnostic);
            }
        }
    }

    @Override // com.android.tools.r8.internal.YV
    public final synchronized void a(ByteDataView byteDataView, String str, DiagnosticsHandler diagnosticsHandler) {
        a(str, byteDataView, true);
    }

    public final synchronized void a(String str, ByteDataView byteDataView, boolean z) {
        this.h.add(new Z2(str, ByteDataView.of(byteDataView.copyByteData()), false, z));
    }

    public final void a(String str, ByteDataView byteDataView, DiagnosticsHandler diagnosticsHandler, boolean z) {
        try {
            ZipOutputStream zipOutputStreamA = a();
            boolean z2 = AbstractC2735u2.b;
            int i2 = ("resources/new_api_database.ser".equals(str) || !z) ? 0 : 8;
            boolean z3 = com.android.tools.r8.utils.v.a;
            byte[] buffer = byteDataView.getBuffer();
            int offset = byteDataView.getOffset();
            int length = byteDataView.getLength();
            CRC32 crc32 = new CRC32();
            crc32.update(buffer, offset, length);
            ZipEntry zipEntry = new ZipEntry(str);
            zipEntry.setMethod(i2);
            zipEntry.setSize(length);
            zipEntry.setCrc(crc32.getValue());
            zipEntry.setTime(0L);
            zipOutputStreamA.putNextEntry(zipEntry);
            zipOutputStreamA.write(buffer, offset, length);
            zipOutputStreamA.closeEntry();
        } catch (IOException e) {
            ExceptionDiagnostic exceptionDiagnostic = new ExceptionDiagnostic(e, this.b);
            if ((e instanceof ZipException) && e.getMessage().startsWith("duplicate entry")) {
                diagnosticsHandler.warning(exceptionDiagnostic);
            } else {
                diagnosticsHandler.error(exceptionDiagnostic);
            }
        }
    }

    @Override // com.android.tools.r8.internal.YV
    public final synchronized void a(int i2, String str, ByteDataView byteDataView, DiagnosticsHandler diagnosticsHandler) {
        try {
            if (i2 == this.f) {
                a(str, byteDataView, diagnosticsHandler, true);
                int i3 = this.f + 1;
                this.f = i3;
                Z2 z2 = (Z2) this.g.remove(Integer.valueOf(i3));
                while (z2 != null) {
                    a(z2.b, z2.c, diagnosticsHandler, z2.e);
                    int i4 = this.f + 1;
                    this.f = i4;
                    z2 = (Z2) this.g.remove(Integer.valueOf(i4));
                }
            } else {
                this.g.put(Integer.valueOf(i2), new Z2(str, ByteDataView.of(byteDataView.copyByteData()), false, true));
            }
        } catch (Throwable th) {
            throw th;
        }
    }
}
