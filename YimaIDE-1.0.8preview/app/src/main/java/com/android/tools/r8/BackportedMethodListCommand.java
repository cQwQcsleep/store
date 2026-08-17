package com.android.tools.r8;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.C0394Bt;
import com.android.tools.r8.internal.C0878Uk;
import com.android.tools.r8.internal.C2124mt;
import com.android.tools.r8.internal.C2552rt;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.internal.InterfaceC0852Tk;
import com.android.tools.r8.internal.P40;
import com.android.tools.r8.internal.T40;
import com.android.tools.r8.internal.W40;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.utils.StringDiagnostic;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class BackportedMethodListCommand {
    private final boolean a;
    private final boolean b;
    private final C2742u50 c;
    private final int d;
    private final boolean e;
    private final InterfaceC0852Tk f;
    private final com.android.tools.r8.utils.i g;
    private final StringConsumer h;

    private BackportedMethodListCommand(boolean z, boolean z2) {
        this.a = z;
        this.b = z2;
        this.c = new C2742u50();
        this.d = -1;
        this.e = false;
        this.f = null;
        this.g = null;
        this.h = null;
    }

    private static void a(Builder builder, String str) {
        try {
            int i = Integer.parseInt(str);
            if (i >= 1) {
                builder.setMinApiLevel(i);
                return;
            }
            builder.a(new StringDiagnostic("Invalid argument to --min-api: " + str));
        } catch (NumberFormatException unused) {
            builder.a(new StringDiagnostic("Invalid argument to --min-api: " + str));
        }
    }

    public static Builder builder() {
        return new Builder(new C0008d());
    }

    public static Builder parse(String[] strArr) {
        int i;
        String str;
        AbstractC2554rv abstractC2554rvJ = AbstractC2554rv.j();
        Builder builder = builder();
        boolean z = false;
        for (int i2 = 0; i2 < strArr.length; i2 = i + 1) {
            String strTrim = strArr[i2].trim();
            if (abstractC2554rvJ.contains(strTrim)) {
                i = i2 + 1;
                if (i >= strArr.length) {
                    builder.a(new StringDiagnostic("Missing parameter for " + strArr[i2] + "."));
                    return builder;
                }
                str = strArr[i];
            } else {
                i = i2;
                str = null;
            }
            if (strTrim.equals("--help")) {
                builder.setPrintHelp(true);
            } else if (strTrim.equals("--version")) {
                builder.setPrintVersion(true);
            } else if (strTrim.equals("--android-platform-build")) {
                builder.setAndroidPlatformBuild(true);
            } else if (strTrim.equals("--min-api")) {
                if (z) {
                    builder.a(new StringDiagnostic("Cannot set multiple --min-api options"));
                } else {
                    a(builder, str);
                    z = true;
                }
            } else if (strTrim.equals("--desugared-lib")) {
                builder.addDesugaredLibraryConfiguration(t0.a(Paths.get(str, new String[0])));
            } else if (strTrim.equals("--lib")) {
                builder.addLibraryFiles(Paths.get(str, new String[0]));
            } else if (strTrim.equals("--output")) {
                builder.setOutputPath(Paths.get(str, new String[0]));
            } else {
                builder.a(new StringDiagnostic("Unknown option: ".concat(strTrim)));
            }
        }
        return builder;
    }

    public final C2742u50 b() {
        return this.c;
    }

    public StringConsumer getBackportedMethodListConsumer() {
        return this.h;
    }

    public InterfaceC0852Tk getDesugaredLibraryConfiguration() {
        return this.f;
    }

    public int getMinApiLevel() {
        return this.d;
    }

    public boolean isAndroidPlatformBuild() {
        return this.e;
    }

    public boolean isPrintHelp() {
        return this.a;
    }

    public boolean isPrintVersion() {
        return this.b;
    }

    public static class Builder {
        private final C2742u50 a;
        private int b;
        private final ArrayList c;
        private final com.android.tools.r8.utils.i.a d;
        private StringConsumer e;
        private boolean f;
        private boolean g;
        private boolean h;

        private Builder(DiagnosticsHandler diagnosticsHandler) {
            this.b = EnumC3077y2.c.d();
            this.c = new ArrayList();
            this.f = false;
            this.g = false;
            this.h = false;
            this.d = com.android.tools.r8.utils.i.b();
            this.a = new C2742u50(diagnosticsHandler);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(StringDiagnostic stringDiagnostic) {
            this.a.error(stringDiagnostic);
        }

        public Builder addDesugaredLibraryConfiguration(String str) {
            return addDesugaredLibraryConfiguration(t0.a(str, Origin.unknown()));
        }

        public Builder addLibraryFiles(Collection<Path> collection) {
            Iterator<Path> it = collection.iterator();
            while (it.hasNext()) {
                this.d.b(it.next());
            }
            return this;
        }

        public Builder addLibraryResourceProvider(ClassFileResourceProvider classFileResourceProvider) {
            this.d.b(classFileResourceProvider);
            return this;
        }

        public BackportedMethodListCommand build() {
            InterfaceC0852Tk interfaceC0852TkA;
            com.android.tools.r8.utils.i iVarA = this.d.a();
            if (!this.c.isEmpty() && iVarA.h().isEmpty()) {
                this.a.error(new StringDiagnostic("With desugared library configuration a library is required"));
            }
            if (isPrintHelp() || isPrintVersion()) {
                return new BackportedMethodListCommand(isPrintHelp(), isPrintVersion());
            }
            if (this.e == null) {
                this.e = new C0164f();
            }
            B1 b1 = new B1();
            C2742u50 c2742u50 = this.a;
            int i = this.b;
            boolean z = this.h;
            if (this.c.isEmpty()) {
                EnumC3077y2 enumC3077y2 = EnumC3077y2.c;
                int i2 = AbstractC0551Hu.c;
                C0394Bt c0394Bt = new C0394Bt(enumC3077y2, "unused", null, null, true, P40.e);
                T40 t40 = T40.i;
                int i3 = AbstractC2554rv.c;
                W40 w40 = W40.j;
                interfaceC0852TkA = new C2124mt(c0394Bt, new C2552rt(t40, w40, w40, t40, t40, t40, t40, t40, t40, t40, t40, t40, t40, t40, w40, t40, w40, t40, t40), false);
            } else {
                if (this.c.size() > 1) {
                    this.a.b("Only one desugared library configuration is supported.");
                }
                interfaceC0852TkA = C0878Uk.a((t0) this.c.get(0), b1, this.a, false, getMinApiLevel());
            }
            return new BackportedMethodListCommand(c2742u50, i, z, interfaceC0852TkA, iVarA, this.e);
        }

        public int getMinApiLevel() {
            return this.b;
        }

        public boolean isPrintHelp() {
            return this.f;
        }

        public boolean isPrintVersion() {
            return this.g;
        }

        public Builder setAndroidPlatformBuild(boolean z) {
            this.h = z;
            return this;
        }

        public Builder setConsumer(StringConsumer stringConsumer) {
            this.e = stringConsumer;
            return this;
        }

        public Builder setMinApiLevel(int i) {
            if (i > 0) {
                this.b = i;
                return this;
            }
            this.a.error(new StringDiagnostic("Invalid minApiLevel: " + i));
            return this;
        }

        public Builder setOutputPath(Path path) {
            this.e = new C0163e(path);
            return this;
        }

        public Builder setPrintHelp(boolean z) {
            this.f = z;
            return this;
        }

        public Builder setPrintVersion(boolean z) {
            this.g = z;
            return this;
        }

        public Builder addDesugaredLibraryConfiguration(t0 t0Var) {
            this.c.add(t0Var);
            return this;
        }

        public Builder addLibraryFiles(Path... pathArr) {
            addLibraryFiles(Arrays.asList(pathArr));
            return this;
        }
    }

    public static Builder builder(DiagnosticsHandler diagnosticsHandler) {
        return new Builder(diagnosticsHandler);
    }

    private BackportedMethodListCommand(C2742u50 c2742u50, int i, boolean z, InterfaceC0852Tk interfaceC0852Tk, com.android.tools.r8.utils.i iVar, StringConsumer stringConsumer) {
        this.a = false;
        this.b = false;
        this.c = c2742u50;
        this.d = i;
        this.e = z;
        this.f = interfaceC0852Tk;
        this.g = iVar;
        this.h = stringConsumer;
    }

    public final com.android.tools.r8.utils.i a() {
        return this.g;
    }
}
