package com.android.tools.r8.tracereferences;

import com.android.tools.r8.ArchiveClassFileProvider;
import com.android.tools.r8.ClassFileResourceProvider;
import com.android.tools.r8.CompilationFailedException;
import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.dex.W;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC2632so;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C0831Sp;
import com.android.tools.r8.internal.C1586gd;
import com.android.tools.r8.internal.C1975l7;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C3057xm;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import com.android.tools.r8.utils.ArchiveResourceProvider;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class TraceReferencesCommand {
    private final boolean a;
    private final boolean b;
    private final C2742u50 c;
    private final AbstractC0551Hu d;
    private final AbstractC0551Hu e;
    private final AbstractC0551Hu f;
    private final TraceReferencesConsumer g;

    public TraceReferencesCommand(boolean z, boolean z2) {
        this.a = z;
        this.b = z2;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
    }

    public static Builder builder() {
        return new Builder(new c());
    }

    public static Builder parse(Collection<String> collection, Origin origin) {
        return h.a((String[]) collection.toArray(new String[collection.size()]), origin, builder());
    }

    public final TraceReferencesConsumer a() {
        return this.g;
    }

    public C2752uB b() {
        C2752uB c2752uB = new C2752uB();
        c2752uB.M0 = true;
        TraceReferencesConsumer traceReferencesConsumer = this.g;
        C3057xm.a aVarC = C3057xm.a(W.b.g).c();
        aVarC.w = traceReferencesConsumer.getClass().getName();
        if (traceReferencesConsumer instanceof TraceReferencesKeepRules) {
            aVarC.d(((TraceReferencesKeepRules) traceReferencesConsumer).allowObfuscation());
        }
        c2752uB.q0 = aVarC.a();
        return c2752uB;
    }

    public final AbstractC0551Hu c() {
        return this.d;
    }

    public final C2742u50 d() {
        return this.c;
    }

    public final AbstractC0551Hu e() {
        return this.f;
    }

    public final AbstractC0551Hu f() {
        return this.e;
    }

    public boolean isPrintHelp() {
        return this.a;
    }

    public boolean isPrintVersion() {
        return this.b;
    }

    public static Builder builder(DiagnosticsHandler diagnosticsHandler) {
        return new Builder(diagnosticsHandler);
    }

    public TraceReferencesCommand(boolean z, boolean z2, C2742u50 c2742u50, AbstractC0551Hu abstractC0551Hu, AbstractC0551Hu abstractC0551Hu2, AbstractC0551Hu abstractC0551Hu3, TraceReferencesConsumer traceReferencesConsumer) {
        this.a = z;
        this.b = z2;
        this.c = c2742u50;
        this.d = abstractC0551Hu;
        this.e = abstractC0551Hu2;
        this.f = abstractC0551Hu3;
        this.g = traceReferencesConsumer;
    }

    public static class Builder {
        private boolean a;
        private boolean b;
        private final C2742u50 c;
        private final C0473Eu d;
        private final C0473Eu e;
        private final C0473Eu f;
        private TraceReferencesConsumer g;

        private Builder(DiagnosticsHandler diagnosticsHandler) {
            this.a = false;
            this.b = false;
            this.d = AbstractC0551Hu.g();
            this.e = AbstractC0551Hu.g();
            this.f = AbstractC0551Hu.g();
            this.c = new C2742u50(diagnosticsHandler);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(C1975l7 c1975l7) {
            TraceReferencesCommand traceReferencesCommand;
            if (isPrintHelp() || isPrintVersion()) {
                traceReferencesCommand = new TraceReferencesCommand(isPrintHelp(), isPrintVersion());
            } else {
                AbstractC0551Hu abstractC0551HuA = this.d.a();
                AbstractC0551Hu abstractC0551HuA2 = this.e.a();
                AbstractC0551Hu abstractC0551HuA3 = this.f.a();
                if (abstractC0551HuA.isEmpty()) {
                    this.c.error(new StringDiagnostic("No library specified"));
                }
                abstractC0551HuA2.isEmpty();
                if (abstractC0551HuA3.isEmpty()) {
                    this.c.error(new StringDiagnostic("No source specified"));
                }
                if (this.g == null) {
                    this.c.error(new StringDiagnostic("No consumer specified"));
                }
                traceReferencesCommand = new TraceReferencesCommand(this.a, this.b, this.c, abstractC0551HuA, abstractC0551HuA2, abstractC0551HuA3, this.g);
            }
            c1975l7.a(traceReferencesCommand);
            this.c.a();
        }

        public Builder addLibraryFiles(Collection<Path> collection) {
            Iterator<Path> it = collection.iterator();
            while (it.hasNext()) {
                a(it.next(), this.d);
            }
            return this;
        }

        public Builder addLibraryResourceProvider(ClassFileResourceProvider classFileResourceProvider) {
            this.d.a(classFileResourceProvider);
            return this;
        }

        public Builder addSourceFiles(Collection<Path> collection) {
            for (Path path : collection) {
                if (!Files.exists(path, new LinkOption[0])) {
                    this.c.error(new ExceptionDiagnostic(new NoSuchFileException(path.toString()), new PathOrigin(path)));
                }
                if (C0831Sp.a(path)) {
                    this.f.a(ArchiveResourceProvider.fromArchive(path, false));
                } else if (C0831Sp.b(path)) {
                    try {
                        C0473Eu c0473Eu = this.f;
                        byte[] allBytes = Files.readAllBytes(path);
                        c0473Eu.a(new e(path, allBytes, a(allBytes)));
                    } catch (IOException e) {
                        this.c.error(new ExceptionDiagnostic(e));
                    }
                } else if (C0831Sp.d(path)) {
                    this.f.a(new f(path));
                } else {
                    this.c.error(new StringDiagnostic("Unsupported source file type", new PathOrigin(path)));
                }
            }
            return this;
        }

        public Builder addTargetFiles(Collection<Path> collection) {
            Iterator<Path> it = collection.iterator();
            while (it.hasNext()) {
                a(it.next(), this.e);
            }
            return this;
        }

        public final TraceReferencesCommand build() throws CompilationFailedException {
            final C1975l7 c1975l7 = new C1975l7(null);
            AbstractC2632so.a(this.c, new AbstractC2632so.a() { // from class: sie
                @Override // com.android.tools.r8.internal.AbstractC2632so.a
                public final void run() {
                    this.a.a(c1975l7);
                }
            });
            return (TraceReferencesCommand) c1975l7.a();
        }

        public boolean isPrintHelp() {
            return this.a;
        }

        public boolean isPrintVersion() {
            return this.b;
        }

        public Builder setConsumer(TraceReferencesConsumer traceReferencesConsumer) {
            this.g = traceReferencesConsumer;
            return this;
        }

        public Builder setPrintHelp(boolean z) {
            this.a = z;
            return this;
        }

        public Builder setPrintVersion(boolean z) {
            this.b = z;
            return this;
        }

        public Builder addLibraryFiles(Path... pathArr) {
            addLibraryFiles(Arrays.asList(pathArr));
            return this;
        }

        public Builder addTargetFiles(Path... pathArr) {
            addTargetFiles(Arrays.asList(pathArr));
            return this;
        }

        public final C2742u50 a() {
            return this.c;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String a(byte[] bArr) {
            C1586gd c1586gd = new C1586gd(bArr);
            d dVar = new d();
            c1586gd.a(dVar, 7);
            return "L" + dVar.b() + ";";
        }

        private void a(Path path, C0473Eu c0473Eu) {
            if (!Files.exists(path, new LinkOption[0])) {
                this.c.error(new ExceptionDiagnostic(new NoSuchFileException(path.toString()), new PathOrigin(path)));
            }
            if (C0831Sp.a(path)) {
                try {
                    c0473Eu.a(new ArchiveClassFileProvider(path));
                    return;
                } catch (IOException e) {
                    this.c.error(new ExceptionDiagnostic(e, new PathOrigin(path)));
                    return;
                }
            }
            if (C0831Sp.b(path)) {
                try {
                    c0473Eu.a(new g(new PathOrigin(path), Files.readAllBytes(path)));
                    return;
                } catch (IOException e2) {
                    this.c.error(new ExceptionDiagnostic(e2));
                    return;
                }
            }
            this.c.error(new StringDiagnostic("Unsupported source file type", new PathOrigin(path)));
        }

        public Builder addSourceFiles(Path... pathArr) {
            addSourceFiles(Arrays.asList(pathArr));
            return this;
        }

        public final void a(Diagnostic diagnostic) {
            this.c.error(diagnostic);
        }
    }

    public static Builder parse(String[] strArr, Origin origin) {
        return h.a(strArr, origin, builder());
    }

    public static Builder parse(String[] strArr, Origin origin, DiagnosticsHandler diagnosticsHandler) {
        return h.a(strArr, origin, builder(diagnosticsHandler));
    }
}
