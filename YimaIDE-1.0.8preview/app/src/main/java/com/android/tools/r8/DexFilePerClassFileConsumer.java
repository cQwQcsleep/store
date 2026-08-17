package com.android.tools.r8;

import com.android.tools.r8.internal.C0378Bd;
import com.android.tools.r8.internal.C0831Sp;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C1031a3;
import com.android.tools.r8.internal.C1264cm;
import com.android.tools.r8.internal.K7;
import com.android.tools.r8.utils.StringDiagnostic;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface DexFilePerClassFileConsumer extends ProgramConsumer, ByteBufferProvider {
    public static final boolean SHOULD_COMBINE_SYNTHETIC_CLASSES = true;

    public static class ForwardingConsumer implements DexFilePerClassFileConsumer {
        private static final ForwardingConsumer b = new ForwardingConsumer(null);
        private final DexFilePerClassFileConsumer a;

        public ForwardingConsumer(DexFilePerClassFileConsumer dexFilePerClassFileConsumer) {
            this.a = dexFilePerClassFileConsumer;
        }

        @Override // com.android.tools.r8.DexFilePerClassFileConsumer
        public void accept(String str, ByteDataView byteDataView, Set<String> set, DiagnosticsHandler diagnosticsHandler) {
            DexFilePerClassFileConsumer dexFilePerClassFileConsumer = this.a;
            if (dexFilePerClassFileConsumer != null) {
                dexFilePerClassFileConsumer.accept(str, byteDataView, set, diagnosticsHandler);
            }
        }

        @Override // com.android.tools.r8.DexFilePerClassFileConsumer
        public boolean combineSyntheticClassesWithPrimaryClass() {
            DexFilePerClassFileConsumer dexFilePerClassFileConsumer = this.a;
            if (dexFilePerClassFileConsumer == null) {
                return true;
            }
            return dexFilePerClassFileConsumer.combineSyntheticClassesWithPrimaryClass();
        }

        @Override // com.android.tools.r8.ProgramConsumer, com.android.tools.r8.DataResourceConsumer
        public void finished(DiagnosticsHandler diagnosticsHandler) {
            DexFilePerClassFileConsumer dexFilePerClassFileConsumer = this.a;
            if (dexFilePerClassFileConsumer != null) {
                dexFilePerClassFileConsumer.finished(diagnosticsHandler);
            }
        }

        @Override // com.android.tools.r8.ProgramConsumer
        public DataResourceConsumer getDataResourceConsumer() {
            DexFilePerClassFileConsumer dexFilePerClassFileConsumer = this.a;
            if (dexFilePerClassFileConsumer != null) {
                return dexFilePerClassFileConsumer.getDataResourceConsumer();
            }
            return null;
        }
    }

    static DexFilePerClassFileConsumer emptyConsumer() {
        return ForwardingConsumer.b;
    }

    default void accept(String str, byte[] bArr, Set<String> set, DiagnosticsHandler diagnosticsHandler) {
        diagnosticsHandler.error(new StringDiagnostic("Deprecated use of DexFilePerClassFileConsumer::accept(..., byte[], ...)"));
    }

    @Override // com.android.tools.r8.ByteBufferProvider
    /* bridge */ /* synthetic */ default ByteBuffer acquireByteBuffer(int i) {
        return super.acquireByteBuffer(i);
    }

    default boolean combineSyntheticClassesWithPrimaryClass() {
        return true;
    }

    @Override // com.android.tools.r8.ByteBufferProvider
    /* bridge */ /* synthetic */ default void releaseByteBuffer(ByteBuffer byteBuffer) {
        super.releaseByteBuffer(byteBuffer);
    }

    default void accept(String str, ByteDataView byteDataView, Set<String> set, DiagnosticsHandler diagnosticsHandler) {
        accept(str, byteDataView.copyByteData(), set, diagnosticsHandler);
    }

    public static class DirectoryConsumer extends ForwardingConsumer implements DataResourceConsumer, U {
        static final /* synthetic */ boolean d = true;
        private final C1264cm c;
        protected final boolean consumeDataResouces;

        public DirectoryConsumer(Path path, DexFilePerClassFileConsumer dexFilePerClassFileConsumer, boolean z) {
            super(dexFilePerClassFileConsumer);
            this.c = new C1264cm(path);
            this.consumeDataResouces = z;
        }

        private static String a(String str) {
            if (!d && (str == null || !C0929Wj.z(str))) {
                x1f.a();
                return null;
            }
            return C0929Wj.i(str) + ".dex";
        }

        public static void writeResources(Path path, List<ProgramResource> list, Map<Resource, String> map) throws Throwable {
            C0378Bd c0378Bd = new C0378Bd(C0378Bd.c);
            try {
                for (ProgramResource programResource : list) {
                    Path pathResolve = path.resolve(ArchiveConsumer.a(map.get(programResource)));
                    byte[] bArrA = K7.a((InputStream) c0378Bd.a(programResource.getByteStream()));
                    Files.createDirectories(pathResolve.getParent(), new FileAttribute[0]);
                    C0831Sp.a(pathResolve, (OutputStream) null, bArrA);
                }
                c0378Bd.close();
            } catch (Throwable th) {
                try {
                    c0378Bd.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }

        @Override // com.android.tools.r8.DexFilePerClassFileConsumer.ForwardingConsumer, com.android.tools.r8.DexFilePerClassFileConsumer
        public void accept(String str, ByteDataView byteDataView, Set<String> set, DiagnosticsHandler diagnosticsHandler) {
            super.accept(str, byteDataView, set, diagnosticsHandler);
            this.c.a(byteDataView, a(str), diagnosticsHandler);
        }

        @Override // com.android.tools.r8.DexFilePerClassFileConsumer.ForwardingConsumer, com.android.tools.r8.ProgramConsumer, com.android.tools.r8.DataResourceConsumer
        public void finished(DiagnosticsHandler diagnosticsHandler) {
            super.finished(diagnosticsHandler);
        }

        @Override // com.android.tools.r8.U
        public Path internalGetOutputPath() {
            return this.c.a;
        }

        public DirectoryConsumer(Path path, boolean z) {
            this(path, null, z);
        }

        @Override // com.android.tools.r8.DataResourceConsumer
        public void accept(DataDirectoryResource dataDirectoryResource, DiagnosticsHandler diagnosticsHandler) {
            this.c.a(dataDirectoryResource.getName(), diagnosticsHandler);
        }

        public DirectoryConsumer(Path path, DexFilePerClassFileConsumer dexFilePerClassFileConsumer) {
            this(path, dexFilePerClassFileConsumer, false);
        }

        @Override // com.android.tools.r8.DataResourceConsumer
        public void accept(DataEntryResource dataEntryResource, DiagnosticsHandler diagnosticsHandler) {
            this.c.a(dataEntryResource.getName(), dataEntryResource, diagnosticsHandler);
        }

        public DirectoryConsumer(Path path) {
            this(path, null, false);
        }
    }

    public static class ArchiveConsumer extends ForwardingConsumer implements DataResourceConsumer, U {
        static final /* synthetic */ boolean d = true;
        private final C1031a3 c;
        protected final boolean consumeDataResources;

        public ArchiveConsumer(Path path, DexFilePerClassFileConsumer dexFilePerClassFileConsumer, boolean z) {
            super(dexFilePerClassFileConsumer);
            C1031a3 c1031a3 = new C1031a3(path);
            this.c = c1031a3;
            this.consumeDataResources = z;
            c1031a3.open();
            if (getDataResourceConsumer() != null) {
                c1031a3.open();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String a(String str) {
            if (!d && (str == null || !C0929Wj.z(str))) {
                x1f.a();
                return null;
            }
            return C0929Wj.i(str) + ".dex";
        }

        public static void writeResourcesForTesting(Path path, List<ProgramResource> list, Map<Resource, String> map) throws Throwable {
            OpenOption[] openOptionArr = {StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};
            C0378Bd c0378Bd = new C0378Bd(C0378Bd.c);
            try {
                ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(Files.newOutputStream(path, openOptionArr)));
                try {
                    for (ProgramResource programResource : list) {
                        com.android.tools.r8.utils.v.a(zipOutputStream, a(map.get(programResource)), K7.a((InputStream) c0378Bd.a(programResource.getByteStream())), 0);
                    }
                    zipOutputStream.close();
                    c0378Bd.close();
                } catch (Throwable th) {
                    try {
                        zipOutputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                try {
                    c0378Bd.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
                throw th3;
            }
        }

        @Override // com.android.tools.r8.DexFilePerClassFileConsumer.ForwardingConsumer, com.android.tools.r8.DexFilePerClassFileConsumer
        public void accept(String str, ByteDataView byteDataView, Set<String> set, DiagnosticsHandler diagnosticsHandler) {
            super.accept(str, byteDataView, set, diagnosticsHandler);
            C1031a3 c1031a3 = this.c;
            String strA = a(str);
            synchronized (c1031a3) {
                c1031a3.a(strA, byteDataView, true);
            }
        }

        @Override // com.android.tools.r8.DexFilePerClassFileConsumer.ForwardingConsumer, com.android.tools.r8.ProgramConsumer, com.android.tools.r8.DataResourceConsumer
        public void finished(DiagnosticsHandler diagnosticsHandler) {
            super.finished(diagnosticsHandler);
            this.c.a(diagnosticsHandler);
        }

        @Override // com.android.tools.r8.DexFilePerClassFileConsumer.ForwardingConsumer, com.android.tools.r8.ProgramConsumer
        public DataResourceConsumer getDataResourceConsumer() {
            if (this.consumeDataResources) {
                return this;
            }
            return null;
        }

        @Override // com.android.tools.r8.U
        public Path internalGetOutputPath() {
            return this.c.a;
        }

        @Override // com.android.tools.r8.DataResourceConsumer
        public void accept(DataDirectoryResource dataDirectoryResource, DiagnosticsHandler diagnosticsHandler) {
            this.c.a(dataDirectoryResource.getName(), diagnosticsHandler);
        }

        @Override // com.android.tools.r8.DataResourceConsumer
        public void accept(DataEntryResource dataEntryResource, DiagnosticsHandler diagnosticsHandler) {
            this.c.a(dataEntryResource.getName(), dataEntryResource, diagnosticsHandler);
        }

        public ArchiveConsumer(Path path, boolean z) {
            this(path, null, z);
        }

        public ArchiveConsumer(Path path, DexFilePerClassFileConsumer dexFilePerClassFileConsumer) {
            this(path, dexFilePerClassFileConsumer, false);
        }

        public ArchiveConsumer(Path path) {
            this(path, null, false);
        }
    }
}
