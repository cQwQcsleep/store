package com.android.tools.r8.relocator;

import com.android.tools.r8.AbstractC3309m;
import com.android.tools.r8.ClassFileConsumer;
import com.android.tools.r8.CompilationFailedException;
import com.android.tools.r8.CompilationMode;
import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.graph.B1;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC0706Nu;
import com.android.tools.r8.internal.AbstractC0728Oq;
import com.android.tools.r8.internal.AbstractC2379pq;
import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.AbstractC2632so;
import com.android.tools.r8.internal.Bc0;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C0629Ku;
import com.android.tools.r8.internal.C1534g;
import com.android.tools.r8.internal.C1975l7;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.Z40;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.PackageReference;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.shaking.F3;
import com.android.tools.r8.shaking.G3;
import com.android.tools.r8.shaking.H3;
import com.android.tools.r8.shaking.R2;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import com.android.tools.r8.utils.i;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class RelocatorCommand {
    private static final AbstractC2554rv i = AbstractC2554rv.a(4, 4, "--output", "--input", "--map", "--thread-count");
    static final String j = String.join("\n", AbstractC0728Oq.a((Iterable[]) Arrays.copyOf(new Iterable[]{Arrays.asList("The Relocator CLI is EXPERIMENTAL and is subject to change", "Usage: relocator [options]", " where options are:", "  --input <file>          # Input file to remap, class, zip or jar.", "  --output <file>         # Output result in <outfile>.", "  --map <from->to>        # Registers a mapping.", "  --thread-count <number> # A specified number of threads to run with.", "  --version               # Print the version of d8.", "  --help                  # Print this message.")}, 1)));
    static final /* synthetic */ boolean k = true;
    private final boolean a;
    private final boolean b;
    private final C2742u50 c;
    private final B1 d;
    private final ClassFileConsumer e;
    private final i f;
    private final Z40 g;
    private final int h;

    private RelocatorCommand(boolean z, boolean z2) {
        this.a = z;
        this.b = z2;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = -1;
    }

    public static Builder builder(DiagnosticsHandler diagnosticsHandler) {
        return new Builder(i.a(new C2742u50(diagnosticsHandler)));
    }

    public static Builder parse(String[] strArr, Origin origin) {
        return Builder.parse(strArr, origin);
    }

    public i getApp() {
        return this.f;
    }

    public ClassFileConsumer getConsumer() {
        return this.e;
    }

    public B1 getFactory() {
        return this.d;
    }

    public C2752uB getInternalOptions() {
        CompilationMode compilationMode = CompilationMode.DEBUG;
        R2.a aVarD = R2.a(this.d, getReporter()).e().c().d();
        int i2 = AbstractC0551Hu.c;
        R2.a aVarA = aVarD.a(new Bc0("*"));
        C0473Eu c0473EuG = AbstractC0551Hu.g();
        c0473EuG.a(new G3("**", false));
        AbstractC0551Hu abstractC0551HuA = c0473EuG.a();
        C2752uB c2752uB = new C2752uB(compilationMode, aVarA.b(abstractC0551HuA.size() > 0 ? new H3(abstractC0551HuA) : new F3()).a(), getReporter());
        c2752uB.L1 = true;
        c2752uB.g0 = getThreadCount();
        ClassFileConsumer classFileConsumer = this.e;
        c2752uB.j = classFileConsumer;
        if (k || classFileConsumer != null) {
            c2752uB.n = classFileConsumer.getDataResourceConsumer();
            return c2752uB;
        }
        x1f.a();
        return null;
    }

    public Z40 getMapping() {
        return this.g;
    }

    public C2742u50 getReporter() {
        return this.c;
    }

    public int getThreadCount() {
        return this.h;
    }

    public boolean isPrintHelp() {
        return this.a;
    }

    public boolean isPrintVersion() {
        return this.b;
    }

    public static class Builder {
        static final /* synthetic */ boolean j = true;
        private final i.a a;
        private final C2742u50 b;
        private final C0629Ku c = AbstractC0706Nu.e();
        private final C0629Ku d = AbstractC0706Nu.e();
        private final C0629Ku e = AbstractC0706Nu.e();
        private ClassFileConsumer f = null;
        private int g = -1;
        private boolean h;
        private boolean i;

        public Builder(i.a aVar) {
            this.a = aVar;
            this.b = aVar.m;
        }

        /* JADX WARN: Code duplicated, block: B:75:0x015d  */
        private static Builder a(String[] strArr, Origin origin, final Builder builder) {
            int i;
            String str;
            Objects.requireNonNull(builder);
            String[] strArrA = AbstractC2379pq.a(strArr, new Consumer() { // from class: zbc
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.error((Diagnostic) obj);
                }
            });
            Path path = null;
            for (int i2 = 0; i2 < strArrA.length; i2 = i + 1) {
                String strTrim = strArrA[i2].trim();
                if (RelocatorCommand.i.contains(strTrim)) {
                    i = i2 + 1;
                    if (i >= strArrA.length) {
                        builder.error(new StringDiagnostic("Missing parameter for " + strArrA[i2] + ".", origin));
                        if (path == null) {
                            path = Paths.get(".", new String[0]);
                        }
                        builder.setOutputPath(path);
                        return builder;
                    }
                    str = strArrA[i];
                } else {
                    i = i2;
                    str = null;
                }
                if (strTrim.length() != 0) {
                    switch (strTrim) {
                        case "--input":
                            if (!j && str == null) {
                                x1f.a();
                                return null;
                            }
                            builder.addProgramFile(Paths.get(str, new String[0]));
                            break;
                            break;
                        case "--map":
                            if (!j && str == null) {
                                x1f.a();
                                return null;
                            }
                            int iIndexOf = str.indexOf("->");
                            if (iIndexOf < 0) {
                                builder.error(new StringDiagnostic("--map " + str + " is not on the form from->to"));
                            } else {
                                addMapping(str.substring(0, iIndexOf), str.substring(iIndexOf + 2), builder);
                            }
                            break;
                            break;
                        case "--thread-count":
                            AbstractC3309m.a(new Consumer() { // from class: zbc
                                @Override // java.util.function.Consumer
                                public final void accept(Object obj) {
                                    this.b.error((Diagnostic) obj);
                                }
                            }, strTrim, str, origin, new Consumer() { // from class: acc
                                @Override // java.util.function.Consumer
                                public final void accept(Object obj) {
                                    this.b.setThreadCount(((Integer) obj).intValue());
                                }
                            });
                            break;
                        case "--help":
                            builder.setPrintHelp(true);
                            break;
                        case "--output":
                            if (!j && str == null) {
                                x1f.a();
                                return null;
                            }
                            if (path != null) {
                                builder.error(new StringDiagnostic("Cannot output both to '" + path.toString() + "' and '" + str + "'", origin));
                            } else {
                                path = Paths.get(str, new String[0]);
                            }
                            break;
                            break;
                        case "--version":
                            builder.setPrintVersion(true);
                            break;
                        default:
                            builder.error(new StringDiagnostic("Unknown argument: ".concat(strTrim), origin));
                            break;
                    }
                }
            }
            if (path == null) {
                path = Paths.get(".", new String[0]);
            }
            builder.setOutputPath(path);
            return builder;
        }

        public static void addMapping(String str, String str2, Builder builder) {
            if (str.endsWith(".**")) {
                builder.addSubPackageMapping(Reference.packageFromString(str.substring(0, str.length() - 3)), Reference.packageFromString(str2));
            } else if (str.endsWith(".*")) {
                builder.addPackageMapping(Reference.packageFromString(str.substring(0, str.length() - 2)), Reference.packageFromString(str2));
            } else {
                builder.addClassMapping(Reference.classFromTypeName(str), Reference.classFromTypeName(str2));
            }
        }

        public static Builder parse(String[] strArr, Origin origin) {
            return a(strArr, origin, RelocatorCommand.builder());
        }

        public Builder addClassMapping(ClassReference classReference, ClassReference classReference2) {
            this.d.a(classReference, classReference2);
            return this;
        }

        public Builder addPackageMapping(PackageReference packageReference, PackageReference packageReference2) {
            this.c.a(packageReference, packageReference2);
            return this;
        }

        public Builder addProgramFile(final Path path) {
            try {
                new Runnable() { // from class: bcc
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.b.a(path);
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

        public Builder addProgramFiles(final Collection<Path> collection) {
            try {
                new Runnable() { // from class: xbc
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

        public Builder addSubPackageMapping(PackageReference packageReference, PackageReference packageReference2) {
            this.e.a(packageReference, packageReference2);
            return this;
        }

        public RelocatorCommand build() throws CompilationFailedException {
            final C1975l7 c1975l7 = new C1975l7();
            AbstractC2632so.a(this.b, new AbstractC2632so.a() { // from class: ybc
                @Override // com.android.tools.r8.internal.AbstractC2632so.a
                public final void run() {
                    this.a.a(c1975l7);
                }
            });
            return (RelocatorCommand) c1975l7.a();
        }

        public void error(Diagnostic diagnostic) {
            this.b.error(diagnostic);
        }

        public Builder setConsumer(ClassFileConsumer classFileConsumer) {
            this.f = classFileConsumer;
            return this;
        }

        public Builder setOutputPath(Path path) {
            if (path == null) {
                this.f = null;
                return this;
            }
            this.f = new ClassFileConsumer.ArchiveConsumer(path, true);
            return this;
        }

        public Builder setPrintHelp(boolean z) {
            this.i = z;
            return this;
        }

        public Builder setPrintVersion(boolean z) {
            this.h = z;
            return this;
        }

        public Builder setThreadCount(int i) {
            if (i > 0) {
                this.g = i;
                return this;
            }
            this.b.a("Invalid threadCount: " + i);
            return this;
        }

        public static Builder parse(String[] strArr, Origin origin, DiagnosticsHandler diagnosticsHandler) {
            return a(strArr, origin, RelocatorCommand.builder(diagnosticsHandler));
        }

        public Builder addProgramFiles(Path... pathArr) {
            return addProgramFiles(Arrays.asList(pathArr));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(Collection collection) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                Path path = (Path) it.next();
                try {
                    this.a.c(path);
                } catch (C0613Ke e) {
                    this.b.error(new ExceptionDiagnostic(e, new PathOrigin(path)));
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(Path path) {
            try {
                this.a.c(path);
            } catch (C0613Ke e) {
                this.b.error(new ExceptionDiagnostic(e, new PathOrigin(path)));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(C1975l7 c1975l7) {
            if (!this.i && !this.h) {
                this.b.a();
                if (this.f == null) {
                    this.b.error(new StringDiagnostic("No output path or consumer has been specified"));
                }
                this.b.a();
                c1975l7.a(new RelocatorCommand(new Z40(this.c.b(), this.d.b(), this.e.b()), this.a.a(), this.b, new B1(), this.f, this.g));
                return;
            }
            c1975l7.a(new RelocatorCommand(this.i, this.h));
        }
    }

    public static Builder builder() {
        return new Builder(i.b());
    }

    private RelocatorCommand(Z40 z40, i iVar, C2742u50 c2742u50, B1 b1, ClassFileConsumer classFileConsumer, int i2) {
        this.a = false;
        this.b = false;
        this.g = z40;
        this.f = iVar;
        this.c = c2742u50;
        this.d = b1;
        this.e = classFileConsumer;
        this.h = i2;
    }
}
