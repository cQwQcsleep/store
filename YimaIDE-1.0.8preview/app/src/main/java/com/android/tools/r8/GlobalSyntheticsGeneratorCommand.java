package com.android.tools.r8;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C1534g;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class GlobalSyntheticsGeneratorCommand {
    static final /* synthetic */ boolean i = true;
    private final GlobalSyntheticsConsumer a;
    private final C2742u50 b;
    private final int c;
    private final boolean d;
    private final boolean e;
    private final boolean f;
    private final com.android.tools.r8.utils.i g;
    private final B1 h;

    private GlobalSyntheticsGeneratorCommand(boolean z, boolean z2) {
        this.h = new B1();
        this.e = z;
        this.f = z2;
        this.g = null;
        this.a = null;
        this.c = EnumC3077y2.c.d();
        this.d = false;
        this.b = new C2742u50();
    }

    public static Builder builder() {
        return new Builder(new N());
    }

    public static Builder parse(String[] strArr, Origin origin) {
        return Q.a(strArr, origin, builder());
    }

    public C2752uB a() {
        C2752uB c2752uB = new C2752uB(this.h, this.b);
        boolean z = i;
        if (!z && c2752uB.Z0) {
            x1f.a();
            return null;
        }
        if (!z && c2752uB.w1) {
            x1f.a();
            return null;
        }
        c2752uB.c(EnumC3077y2.b(this.c));
        if (!z && !c2752uB.A0) {
            x1f.a();
            return null;
        }
        c2752uB.y0 = true;
        boolean z2 = this.d;
        c2752uB.j = z2 ? new O() : new P();
        c2752uB.l = this.a;
        if (z2) {
            c2752uB.a().a();
            c2752uB.a().c();
        }
        if (!z && c2752uB.g0()) {
            x1f.a();
            return null;
        }
        if (!z && c2752uB.c0()) {
            x1f.a();
            return null;
        }
        if (!z && c2752uB.t) {
            x1f.a();
            return null;
        }
        c2752uB.r0 = com.android.tools.r8.dex.W.b.c;
        c2752uB.E0 = C2752uB.g.c;
        c2752uB.F0 = true;
        c2752uB.w().b(false);
        return c2752uB;
    }

    public com.android.tools.r8.utils.i getInputApp() {
        return this.g;
    }

    public boolean isPrintHelp() {
        return this.e;
    }

    public boolean isPrintVersion() {
        return this.f;
    }

    public static class Builder {
        private GlobalSyntheticsConsumer a;
        private final C2742u50 b;
        private int c;
        private boolean d;
        private boolean e;
        private boolean f;
        private final com.android.tools.r8.utils.i.a g;

        private Builder(DiagnosticsHandler diagnosticsHandler) {
            this.a = null;
            this.c = EnumC3077y2.c.d();
            this.d = false;
            this.e = false;
            this.f = false;
            this.g = com.android.tools.r8.utils.i.b();
            this.b = new C2742u50(diagnosticsHandler);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(Collection collection) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                Path path = (Path) it.next();
                try {
                    this.g.b(path);
                } catch (C0613Ke e) {
                    error(new C0347h(path), e);
                }
            }
        }

        public Builder addLibraryFiles(final Collection<Path> collection) {
            try {
                new Runnable() { // from class: c06
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.b.a(collection);
                    }
                }.run();
                return this;
            } catch (C0613Ke e) {
                this.b.error(new StringDiagnostic(e.getMessage(), e.b, e.c));
                return this;
            } catch (C1534g unused) {
                return this;
            }
        }

        public GlobalSyntheticsGeneratorCommand build() {
            if (!this.e && !this.f && this.a == null) {
                this.b.a("GlobalSyntheticsGenerator does not support compiling without output");
            }
            return (this.e || this.f) ? new GlobalSyntheticsGeneratorCommand(this.e, this.f) : new GlobalSyntheticsGeneratorCommand(this.g.a(), this.a, this.b, this.c, this.d);
        }

        public void error(Origin origin, Throwable th) {
            this.b.error(new ExceptionDiagnostic(th, origin));
        }

        public Builder setClassfileDesugaringOnly(boolean z) {
            this.d = z;
            return this;
        }

        public Builder setGlobalSyntheticsConsumer(GlobalSyntheticsConsumer globalSyntheticsConsumer) {
            this.a = globalSyntheticsConsumer;
            return this;
        }

        public Builder setGlobalSyntheticsOutput(Path path) {
            return setGlobalSyntheticsConsumer(new M(path));
        }

        public Builder setMinApiLevel(int i) {
            this.c = i;
            return this;
        }

        public Builder setPrintHelp(boolean z) {
            this.e = z;
            return this;
        }

        public Builder setPrintVersion(boolean z) {
            this.f = z;
            return this;
        }

        public void error(Diagnostic diagnostic) {
            this.b.error(diagnostic);
        }

        public Builder addLibraryFiles(Path... pathArr) {
            addLibraryFiles(Arrays.asList(pathArr));
            return this;
        }
    }

    public static Builder parse(String[] strArr, Origin origin, DiagnosticsHandler diagnosticsHandler) {
        return Q.a(strArr, origin, builder(diagnosticsHandler));
    }

    public static Builder builder(DiagnosticsHandler diagnosticsHandler) {
        return new Builder(diagnosticsHandler);
    }

    private GlobalSyntheticsGeneratorCommand(com.android.tools.r8.utils.i iVar, GlobalSyntheticsConsumer globalSyntheticsConsumer, C2742u50 c2742u50, int i2, boolean z) {
        this.h = new B1();
        this.g = iVar;
        this.a = globalSyntheticsConsumer;
        this.c = i2;
        this.d = z;
        this.b = c2742u50;
        this.e = false;
        this.f = false;
    }
}
