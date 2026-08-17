package com.android.tools.r8;

import com.android.tools.r8.origin.PathOrigin;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface StringConsumer extends I {

    public static class EmptyConsumer implements StringConsumer {
        private static final EmptyConsumer a = new EmptyConsumer();

        @Override // com.android.tools.r8.StringConsumer
        public void accept(String str, DiagnosticsHandler diagnosticsHandler) {
        }

        @Override // com.android.tools.r8.I
        public void finished(DiagnosticsHandler diagnosticsHandler) {
        }
    }

    public static class ForwardingConsumer implements StringConsumer {
        private final StringConsumer a;

        public ForwardingConsumer(StringConsumer stringConsumer) {
            this.a = stringConsumer;
        }

        @Override // com.android.tools.r8.StringConsumer
        public void accept(String str, DiagnosticsHandler diagnosticsHandler) {
            StringConsumer stringConsumer = this.a;
            if (stringConsumer != null) {
                stringConsumer.accept(str, diagnosticsHandler);
            }
        }

        @Override // com.android.tools.r8.I
        public void finished(DiagnosticsHandler diagnosticsHandler) {
            StringConsumer stringConsumer = this.a;
            if (stringConsumer != null) {
                stringConsumer.finished(diagnosticsHandler);
            }
        }
    }

    static EmptyConsumer emptyConsumer() {
        return EmptyConsumer.a;
    }

    void accept(String str, DiagnosticsHandler diagnosticsHandler);

    public static class FileConsumer extends ForwardingConsumer {
        static final /* synthetic */ boolean f = true;
        private final Path b;
        private Charset c;
        private s0 d;
        private boolean e;

        public FileConsumer(Path path, StringConsumer stringConsumer) {
            super(stringConsumer);
            this.c = StandardCharsets.UTF_8;
            this.d = null;
            this.e = false;
            this.b = path;
        }

        private void a(DiagnosticsHandler diagnosticsHandler) {
            if (this.d != null) {
                return;
            }
            PathOrigin pathOrigin = new PathOrigin(this.b);
            try {
                Path parent = this.b.getParent();
                if (parent != null && !parent.toFile().exists()) {
                    Files.createDirectories(parent, new FileAttribute[0]);
                }
                this.d = new s0(pathOrigin, Files.newBufferedWriter(this.b, this.c, new OpenOption[0]));
            } catch (IOException e) {
                this.e = true;
                diagnosticsHandler.error(new ExceptionDiagnostic(e, pathOrigin));
            }
        }

        @Override // com.android.tools.r8.StringConsumer.ForwardingConsumer, com.android.tools.r8.StringConsumer
        public void accept(String str, DiagnosticsHandler diagnosticsHandler) {
            super.accept(str, diagnosticsHandler);
            if (this.e) {
                return;
            }
            a(diagnosticsHandler);
            s0 s0Var = this.d;
            if (s0Var != null) {
                s0Var.accept(str, diagnosticsHandler);
            }
        }

        @Override // com.android.tools.r8.StringConsumer.ForwardingConsumer, com.android.tools.r8.I
        public void finished(DiagnosticsHandler diagnosticsHandler) {
            super.finished(diagnosticsHandler);
            if (this.e) {
                return;
            }
            a(diagnosticsHandler);
            s0 s0Var = this.d;
            if (s0Var != null) {
                s0Var.finished(diagnosticsHandler);
                this.d = null;
            }
        }

        public Charset getEncoding() {
            return this.c;
        }

        public Path getOutputPath() {
            return this.b;
        }

        public void setEncoding(Charset charset) {
            if (!f && charset == null) {
                x1f.a();
            } else if (this.d == null) {
                this.c = charset;
            } else {
                k2d.a("Invalid call to set encoding after file stream is opened");
            }
        }

        public FileConsumer(Path path) {
            this(path, null);
        }
    }
}
