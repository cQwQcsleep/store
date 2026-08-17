package com.android.tools.r8;

import com.android.tools.r8.internal.AbstractC0983Yl;
import com.android.tools.r8.internal.C0378Bd;
import com.android.tools.r8.internal.C0831Sp;
import com.android.tools.r8.internal.C1031a3;
import com.android.tools.r8.internal.C1264cm;
import com.android.tools.r8.internal.K7;
import com.android.tools.r8.internal.YV;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface DexIndexedConsumer extends ProgramConsumer, ByteBufferProvider {

    public static class ForwardingConsumer implements DexIndexedConsumer {
        private static final ForwardingConsumer b = new ForwardingConsumer(null);
        private final DexIndexedConsumer a;

        public ForwardingConsumer(DexIndexedConsumer dexIndexedConsumer) {
            this.a = dexIndexedConsumer;
        }

        @Override // com.android.tools.r8.DexIndexedConsumer
        public void accept(int i, ByteDataView byteDataView, Set<String> set, DiagnosticsHandler diagnosticsHandler) {
            DexIndexedConsumer dexIndexedConsumer = this.a;
            if (dexIndexedConsumer != null) {
                dexIndexedConsumer.accept(i, byteDataView, set, diagnosticsHandler);
            }
        }

        @Override // com.android.tools.r8.ProgramConsumer, com.android.tools.r8.DataResourceConsumer
        public void finished(DiagnosticsHandler diagnosticsHandler) {
            DexIndexedConsumer dexIndexedConsumer = this.a;
            if (dexIndexedConsumer != null) {
                dexIndexedConsumer.finished(diagnosticsHandler);
            }
        }

        @Override // com.android.tools.r8.ProgramConsumer
        public DataResourceConsumer getDataResourceConsumer() {
            DexIndexedConsumer dexIndexedConsumer = this.a;
            if (dexIndexedConsumer != null) {
                return dexIndexedConsumer.getDataResourceConsumer();
            }
            return null;
        }
    }

    static DexIndexedConsumer emptyConsumer() {
        return ForwardingConsumer.b;
    }

    default void accept(int i, byte[] bArr, Set<String> set, DiagnosticsHandler diagnosticsHandler) {
        diagnosticsHandler.error(new StringDiagnostic("Deprecated use of DexIndexedConsumer::accept(..., byte[], ...)"));
    }

    @Override // com.android.tools.r8.ByteBufferProvider
    /* bridge */ /* synthetic */ default ByteBuffer acquireByteBuffer(int i) {
        return super.acquireByteBuffer(i);
    }

    @Override // com.android.tools.r8.ByteBufferProvider
    /* bridge */ /* synthetic */ default void releaseByteBuffer(ByteBuffer byteBuffer) {
        super.releaseByteBuffer(byteBuffer);
    }

    default void accept(int i, ByteDataView byteDataView, Set<String> set, DiagnosticsHandler diagnosticsHandler) {
        accept(i, byteDataView.copyByteData(), set, diagnosticsHandler);
    }

    public static class ArchiveConsumer extends ForwardingConsumer implements DataResourceConsumer, U {
        protected final boolean consumeDataResources;
        protected final YV outputBuilder;

        public ArchiveConsumer(Path path, DexIndexedConsumer dexIndexedConsumer, boolean z) {
            super(dexIndexedConsumer);
            C1031a3 c1031a3 = new C1031a3(path);
            this.outputBuilder = c1031a3;
            this.consumeDataResources = z;
            c1031a3.open();
            if (getDataResourceConsumer() != null) {
                c1031a3.open();
            }
        }

        public static void writeResourcesForTesting(Path path, List<ProgramResource> list, Set<DataDirectoryResource> set, Set<DataEntryResource> set2) throws Throwable {
            OpenOption[] openOptionArr = {StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING};
            C0378Bd c0378Bd = new C0378Bd(C0378Bd.c);
            try {
                ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(Files.newOutputStream(path, openOptionArr)));
                for (int i = 0; i < list.size(); i++) {
                    try {
                        com.android.tools.r8.utils.v.a(zipOutputStream, AbstractC0983Yl.a(i), K7.a((InputStream) c0378Bd.a(list.get(i).getByteStream())), 0);
                    } catch (Throwable th) {
                        try {
                            zipOutputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                }
                Iterator<DataDirectoryResource> it = set.iterator();
                while (it.hasNext()) {
                    com.android.tools.r8.utils.v.a(zipOutputStream, it.next().getName(), new byte[0], 0);
                }
                for (DataEntryResource dataEntryResource : set2) {
                    com.android.tools.r8.utils.v.a(zipOutputStream, dataEntryResource.getName(), K7.a((InputStream) c0378Bd.a(dataEntryResource.getByteStream())), 0);
                }
                zipOutputStream.close();
                c0378Bd.close();
            } catch (Throwable th3) {
                try {
                    c0378Bd.close();
                } catch (Throwable th4) {
                    th3.addSuppressed(th4);
                }
                throw th3;
            }
        }

        @Override // com.android.tools.r8.DexIndexedConsumer.ForwardingConsumer, com.android.tools.r8.DexIndexedConsumer
        public void accept(int i, ByteDataView byteDataView, Set<String> set, DiagnosticsHandler diagnosticsHandler) {
            super.accept(i, byteDataView, set, diagnosticsHandler);
            this.outputBuilder.a(i, AbstractC0983Yl.a(i), byteDataView, diagnosticsHandler);
        }

        @Override // com.android.tools.r8.DexIndexedConsumer.ForwardingConsumer, com.android.tools.r8.ProgramConsumer, com.android.tools.r8.DataResourceConsumer
        public void finished(DiagnosticsHandler diagnosticsHandler) {
            super.finished(diagnosticsHandler);
            this.outputBuilder.a(diagnosticsHandler);
        }

        @Override // com.android.tools.r8.DexIndexedConsumer.ForwardingConsumer, com.android.tools.r8.ProgramConsumer
        public DataResourceConsumer getDataResourceConsumer() {
            if (this.consumeDataResources) {
                return this;
            }
            return null;
        }

        public Origin getOrigin() {
            return this.outputBuilder.getOrigin();
        }

        @Override // com.android.tools.r8.U
        public Path internalGetOutputPath() {
            return this.outputBuilder.getPath();
        }

        @Override // com.android.tools.r8.DataResourceConsumer
        public void accept(DataDirectoryResource dataDirectoryResource, DiagnosticsHandler diagnosticsHandler) {
            this.outputBuilder.a(dataDirectoryResource.getName(), diagnosticsHandler);
        }

        @Override // com.android.tools.r8.DataResourceConsumer
        public void accept(DataEntryResource dataEntryResource, DiagnosticsHandler diagnosticsHandler) {
            this.outputBuilder.a(dataEntryResource.getName(), dataEntryResource, diagnosticsHandler);
        }

        public ArchiveConsumer(Path path, boolean z) {
            this(path, null, z);
        }

        public ArchiveConsumer(Path path, DexIndexedConsumer dexIndexedConsumer) {
            this(path, dexIndexedConsumer, false);
        }

        public ArchiveConsumer(Path path) {
            this(path, null, false);
        }
    }

    public static class DirectoryConsumer extends ForwardingConsumer implements DataResourceConsumer, U {
        private final Path c;
        protected final boolean consumeDataResouces;
        private boolean d;
        private final C1264cm e;

        public DirectoryConsumer(Path path, DexIndexedConsumer dexIndexedConsumer, boolean z) {
            super(dexIndexedConsumer);
            this.d = false;
            this.c = path;
            this.e = new C1264cm(path);
            this.consumeDataResouces = z;
        }

        public static void a(Path path) throws IOException {
            Stream<Path> list = Files.list(path);
            try {
                for (Path path2 : (List) list.collect(Collectors.toList())) {
                    if (C0831Sp.c(path2)) {
                        Files.delete(path2);
                    }
                }
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
        }

        public static void writeResources(Path path, List<ProgramResource> list) throws Throwable {
            a(path);
            C0378Bd c0378Bd = new C0378Bd(C0378Bd.c);
            for (int i = 0; i < list.size(); i++) {
                try {
                    ProgramResource programResource = list.get(i);
                    Path pathResolve = path.resolve(AbstractC0983Yl.a(i));
                    byte[] bArrA = K7.a((InputStream) c0378Bd.a(programResource.getByteStream()));
                    Files.createDirectories(pathResolve.getParent(), new FileAttribute[0]);
                    C0831Sp.a(pathResolve, (OutputStream) null, bArrA);
                } catch (Throwable th) {
                    try {
                        c0378Bd.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            c0378Bd.close();
        }

        @Override // com.android.tools.r8.DexIndexedConsumer.ForwardingConsumer, com.android.tools.r8.DexIndexedConsumer
        public void accept(int i, ByteDataView byteDataView, Set<String> set, DiagnosticsHandler diagnosticsHandler) {
            super.accept(i, byteDataView, set, diagnosticsHandler);
            try {
                synchronized (this) {
                    if (!this.d) {
                        this.d = true;
                        a(this.c);
                    }
                }
            } catch (IOException e) {
                diagnosticsHandler.error(new ExceptionDiagnostic(e, new PathOrigin(this.c)));
            }
            this.e.a(byteDataView, AbstractC0983Yl.a(i), diagnosticsHandler);
        }

        @Override // com.android.tools.r8.DexIndexedConsumer.ForwardingConsumer, com.android.tools.r8.ProgramConsumer, com.android.tools.r8.DataResourceConsumer
        public void finished(DiagnosticsHandler diagnosticsHandler) {
            super.finished(diagnosticsHandler);
            this.e.getClass();
        }

        @Override // com.android.tools.r8.DexIndexedConsumer.ForwardingConsumer, com.android.tools.r8.ProgramConsumer
        public DataResourceConsumer getDataResourceConsumer() {
            if (this.consumeDataResouces) {
                return this;
            }
            return null;
        }

        @Override // com.android.tools.r8.U
        public Path internalGetOutputPath() {
            return this.e.a;
        }

        public DirectoryConsumer(Path path, boolean z) {
            this(path, null, z);
        }

        public DirectoryConsumer(Path path, DexIndexedConsumer dexIndexedConsumer) {
            this(path, dexIndexedConsumer, false);
        }

        public DirectoryConsumer(Path path) {
            this(path, null, false);
        }

        @Override // com.android.tools.r8.DataResourceConsumer
        public void accept(DataDirectoryResource dataDirectoryResource, DiagnosticsHandler diagnosticsHandler) {
            this.e.a(dataDirectoryResource.getName(), diagnosticsHandler);
        }

        @Override // com.android.tools.r8.DataResourceConsumer
        public void accept(DataEntryResource dataEntryResource, DiagnosticsHandler diagnosticsHandler) {
            this.e.a(dataEntryResource.getName(), dataEntryResource, diagnosticsHandler);
        }
    }
}
