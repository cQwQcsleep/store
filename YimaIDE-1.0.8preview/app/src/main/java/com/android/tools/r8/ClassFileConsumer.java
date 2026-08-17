package com.android.tools.r8;

import com.android.tools.r8.internal.C0378Bd;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C1031a3;
import com.android.tools.r8.internal.C1264cm;
import java.io.BufferedOutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ClassFileConsumer extends ProgramConsumer {

    public static class ForwardingConsumer implements ClassFileConsumer {
        private static final ForwardingConsumer b = new ForwardingConsumer(null);
        private final ClassFileConsumer a;

        public ForwardingConsumer(ClassFileConsumer classFileConsumer) {
            this.a = classFileConsumer;
        }

        @Override // com.android.tools.r8.ClassFileConsumer
        public void accept(ByteDataView byteDataView, String str, DiagnosticsHandler diagnosticsHandler) {
            ClassFileConsumer classFileConsumer = this.a;
            if (classFileConsumer != null) {
                classFileConsumer.accept(byteDataView, str, diagnosticsHandler);
            }
        }

        @Override // com.android.tools.r8.ProgramConsumer, com.android.tools.r8.DataResourceConsumer
        public void finished(DiagnosticsHandler diagnosticsHandler) {
            ClassFileConsumer classFileConsumer = this.a;
            if (classFileConsumer != null) {
                classFileConsumer.finished(diagnosticsHandler);
            }
        }

        @Override // com.android.tools.r8.ProgramConsumer
        public DataResourceConsumer getDataResourceConsumer() {
            ClassFileConsumer classFileConsumer = this.a;
            if (classFileConsumer != null) {
                return classFileConsumer.getDataResourceConsumer();
            }
            return null;
        }
    }

    static ClassFileConsumer emptyConsumer() {
        return ForwardingConsumer.b;
    }

    void accept(ByteDataView byteDataView, String str, DiagnosticsHandler diagnosticsHandler);

    public static class DirectoryConsumer extends ForwardingConsumer implements U {
        private final C1264cm c;
        protected final boolean consumeDataResouces;

        public DirectoryConsumer(Path path, ClassFileConsumer classFileConsumer, boolean z) {
            super(classFileConsumer);
            this.c = new C1264cm(path);
            this.consumeDataResouces = z;
        }

        @Override // com.android.tools.r8.ClassFileConsumer.ForwardingConsumer, com.android.tools.r8.ClassFileConsumer
        public void accept(ByteDataView byteDataView, String str, DiagnosticsHandler diagnosticsHandler) {
            super.accept(byteDataView, str, diagnosticsHandler);
            this.c.a(byteDataView, C0929Wj.j(str), diagnosticsHandler);
        }

        @Override // com.android.tools.r8.ClassFileConsumer.ForwardingConsumer, com.android.tools.r8.ProgramConsumer, com.android.tools.r8.DataResourceConsumer
        public void finished(DiagnosticsHandler diagnosticsHandler) {
            super.finished(diagnosticsHandler);
        }

        @Override // com.android.tools.r8.ClassFileConsumer.ForwardingConsumer, com.android.tools.r8.ProgramConsumer
        public DataResourceConsumer getDataResourceConsumer() {
            if (this.consumeDataResouces) {
                return this;
            }
            return null;
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

        public DirectoryConsumer(Path path, ClassFileConsumer classFileConsumer) {
            this(path, classFileConsumer, false);
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
        private final C1031a3 c;
        protected final boolean consumeDataResources;

        public ArchiveConsumer(Path path, ClassFileConsumer classFileConsumer, boolean z) {
            super(classFileConsumer);
            C1031a3 c1031a3 = new C1031a3(path);
            this.c = c1031a3;
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
                try {
                    com.android.tools.r8.utils.v.a(list, set, set2, c0378Bd, zipOutputStream);
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

        @Override // com.android.tools.r8.ClassFileConsumer.ForwardingConsumer, com.android.tools.r8.ClassFileConsumer
        public void accept(ByteDataView byteDataView, String str, DiagnosticsHandler diagnosticsHandler) {
            super.accept(byteDataView, str, diagnosticsHandler);
            C1031a3 c1031a3 = this.c;
            String strJ = C0929Wj.j(str);
            synchronized (c1031a3) {
                c1031a3.a(strJ, byteDataView, true);
            }
        }

        @Override // com.android.tools.r8.ClassFileConsumer.ForwardingConsumer, com.android.tools.r8.ProgramConsumer, com.android.tools.r8.DataResourceConsumer
        public void finished(DiagnosticsHandler diagnosticsHandler) {
            super.finished(diagnosticsHandler);
            this.c.a(diagnosticsHandler);
        }

        @Override // com.android.tools.r8.ClassFileConsumer.ForwardingConsumer, com.android.tools.r8.ProgramConsumer
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

        public ArchiveConsumer(Path path, ClassFileConsumer classFileConsumer) {
            this(path, classFileConsumer, false);
        }

        public ArchiveConsumer(Path path) {
            this(path, null, false);
        }
    }
}
