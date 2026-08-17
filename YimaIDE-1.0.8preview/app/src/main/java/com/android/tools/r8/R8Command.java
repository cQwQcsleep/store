package com.android.tools.r8;

import com.android.tools.r8.AndroidResourceConsumer;
import com.android.tools.r8.R8Command;
import com.android.tools.r8.StringConsumer;
import com.android.tools.r8.experimental.graphinfo.GraphConsumer;
import com.android.tools.r8.graph.B1;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC1646hG;
import com.android.tools.r8.internal.AbstractC2243oF;
import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.AbstractC2972wm;
import com.android.tools.r8.internal.C0830So;
import com.android.tools.r8.internal.C0831Sp;
import com.android.tools.r8.internal.C0882Uo;
import com.android.tools.r8.internal.C1755ib0;
import com.android.tools.r8.internal.C1817jH;
import com.android.tools.r8.internal.C2213nw;
import com.android.tools.r8.internal.C2277og0;
import com.android.tools.r8.internal.C2363pg0;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C2996x4;
import com.android.tools.r8.internal.C3057xm;
import com.android.tools.r8.internal.DM;
import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.internal.IY;
import com.android.tools.r8.internal.InterfaceC0852Tk;
import com.android.tools.r8.internal.InterfaceC2105mg0;
import com.android.tools.r8.internal.P40;
import com.android.tools.r8.internal.Ta0;
import com.android.tools.r8.internal.Ud0;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.naming.I0;
import com.android.tools.r8.naming.U0;
import com.android.tools.r8.naming.V0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import com.android.tools.r8.shaking.C3372b3;
import com.android.tools.r8.shaking.C3377c3;
import com.android.tools.r8.shaking.K0;
import com.android.tools.r8.shaking.R2;
import com.android.tools.r8.shaking.T2;
import com.android.tools.r8.shaking.W2;
import com.android.tools.r8.shaking.Y2;
import com.android.tools.r8.startup.StartupProfileProvider;
import com.android.tools.r8.utils.ArchiveResourceProvider;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import com.android.tools.r8.utils.StringDiagnostic;
import defpackage.z63;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class R8Command extends BaseCompilerCommand {
    static final /* synthetic */ boolean W = true;
    private final R2 A;
    private final boolean B;
    private final boolean C;
    private final boolean D;
    private final Optional E;
    private final StringConsumer F;
    private final PartitionMapConsumer G;
    private final StringConsumer H;
    private final StringConsumer I;
    private final StringConsumer J;
    private final GraphConsumer K;
    private final GraphConsumer L;
    private final Consumer M;
    private final StringConsumer N;
    private final InterfaceC0852Tk O;
    private final C0882Uo P;
    private final String Q;
    private final boolean R;
    private final boolean S;
    private final AndroidResourceProvider T;
    private final AndroidResourceConsumer U;
    private final ResourceShrinkerConfiguration V;
    private final List z;

    private R8Command(com.android.tools.r8.utils.i iVar, ProgramConsumer programConsumer, AbstractC0551Hu abstractC0551Hu, StringConsumer stringConsumer, R2 r2, CompilationMode compilationMode, int i, C2742u50 c2742u50, C2752uB.g gVar, boolean z, boolean z2, boolean z3, Optional optional, StringConsumer stringConsumer2, PartitionMapConsumer partitionMapConsumer, StringConsumer stringConsumer3, StringConsumer stringConsumer4, StringConsumer stringConsumer5, GraphConsumer graphConsumer, GraphConsumer graphConsumer2, Consumer consumer, boolean z4, boolean z5, BiPredicate biPredicate, StringConsumer stringConsumer6, InterfaceC0852Tk interfaceC0852Tk, C0882Uo c0882Uo, List list, List list2, String str, int i2, AbstractC2972wm abstractC2972wm, MapIdProvider mapIdProvider, SourceFileProvider sourceFileProvider, boolean z6, boolean z7, boolean z8, List list3, List list4, ClassConflictResolver classConflictResolver, CancelCompilationChecker cancelCompilationChecker, AndroidResourceProvider androidResourceProvider, AndroidResourceConsumer androidResourceConsumer, ResourceShrinkerConfiguration resourceShrinkerConfiguration) {
        AbstractC0551Hu abstractC0551Hu2;
        super(iVar, compilationMode, programConsumer, stringConsumer, i, c2742u50, gVar, z4, z5, biPredicate, list, list2, i2, abstractC2972wm, mapIdProvider, sourceFileProvider, z8, list3, list4, classConflictResolver, cancelCompilationChecker);
        if (W) {
            abstractC0551Hu2 = abstractC0551Hu;
        } else {
            abstractC0551Hu2 = abstractC0551Hu;
            if (abstractC0551Hu2 == null) {
                x1f.a();
                throw null;
            }
        }
        this.z = abstractC0551Hu2;
        this.A = r2;
        this.B = z;
        this.C = z2;
        this.D = z3;
        this.E = optional;
        this.F = stringConsumer2;
        this.G = partitionMapConsumer;
        this.H = stringConsumer3;
        this.I = stringConsumer4;
        this.J = stringConsumer5;
        this.K = graphConsumer;
        this.L = graphConsumer2;
        this.M = consumer;
        this.N = stringConsumer6;
        this.O = interfaceC0852Tk;
        this.P = c0882Uo;
        this.Q = str;
        this.R = z6;
        this.S = z7;
        this.T = androidResourceProvider;
        this.U = androidResourceConsumer;
        this.V = resourceShrinkerConfiguration;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static com.android.tools.r8.naming.Q a(C2752uB c2752uB, AndroidResourceConsumer androidResourceConsumer) {
        boolean z = I0.c;
        return new I0(new k0(c2752uB));
    }

    public static Builder builder() {
        return new Builder(new h0());
    }

    public static List<ParseFlagInfo> getParseFlagsInformation() {
        return AbstractC0551Hu.a(q0.a());
    }

    private C3057xm i() {
        C3057xm.a aVarC = C3057xm.a(com.android.tools.r8.dex.W.b.e).c();
        a(aVarC);
        aVarC.i = this.E;
        C3057xm.a aVarA = aVarC.e(getEnableTreeShaking()).d(getEnableMinification()).a(this.D);
        C0882Uo c0882Uo = this.P;
        aVarA.o = c0882Uo;
        aVarA.t = this.T;
        aVarA.p = this.A;
        aVarA.q = this.z;
        aVarA.n = this.O;
        aVarA.u = this.R;
        return aVarA.a(c0882Uo != null, new Consumer() { // from class: n4c
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.b((C3057xm.a) obj);
            }
        }).a();
    }

    public static String j() {
        AbstractC2554rv abstractC2554rv = q0.b;
        StringBuilder sb = new StringBuilder();
        Wf0.a(sb, "Usage: r8 [options] [@<argfile>] <input-files>", " where <input-files> are any combination class, zip, or jar files", " and each <argfile> is a file containing additional arguments (one per line)", " and options are:");
        new ParseFlagPrinter().addFlags(AbstractC0551Hu.a(q0.a())).appendLinesToBuilder(sb);
        return sb.toString();
    }

    public static Builder parse(String[] strArr, Origin origin) {
        return new q0().a(strArr, origin, builder());
    }

    /* JADX WARN: Code duplicated, block: B:136:0x0283  */
    @Override // com.android.tools.r8.BaseCommand
    public C2752uB b() {
        U0 u0;
        String strP;
        final C2752uB c2752uB = new C2752uB(getMode(), this.A, g());
        boolean z = W;
        if (!z && c2752uB.u1.Q0) {
            x1f.a();
            return null;
        }
        c2752uB.j = getProgramConsumer();
        c2752uB.c(EnumC3077y2.b(getMinApiLevel()));
        c2752uB.E0 = e();
        if (!z && c2752uB.g0() != getEnableTreeShaking()) {
            x1f.a();
            return null;
        }
        if (!z && c2752uB.c0() != getEnableMinification()) {
            x1f.a();
            return null;
        }
        if (!z && c2752uB.Q0) {
            x1f.a();
            return null;
        }
        c2752uB.Q0 = this.A.g || !(!this.D || c2752uB.e0() || c2752uB.g0() || c2752uB.c0());
        c2752uB.v1 = this.z;
        c2752uB.w1 = c2752uB.Z0;
        c2752uB.E1 = getMainDexListConsumer();
        c2752uB.y1 = (c2752uB.e0() || c2752uB.c0()) ? C2752uB.j.c : C2752uB.j.b;
        c2752uB.i0().a = true;
        C2752uB.h hVarS = c2752uB.S();
        if (!z && !c2752uB.e0() && !hVarS.e()) {
            x1f.a();
            return null;
        }
        if (!z && c2752uB.M) {
            x1f.a();
            return null;
        }
        if (!c2752uB.g0()) {
            c2752uB.C = false;
        }
        final StringConsumer fileConsumer = this.F;
        R2 r2 = this.A;
        boolean z2 = r2.o;
        Path pathN = r2.n();
        if (z2) {
            fileConsumer = pathN != null ? new StringConsumer.FileConsumer(pathN, fileConsumer) : new l0(fileConsumer);
        }
        c2752uB.F1 = DM.a(DM.a(DM.a(c2752uB.F1, this.G, new z63()), fileConsumer, new Function() { // from class: l4c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return R8Command.a(fileConsumer, (StringConsumer) obj);
            }
        }), this.U, new Function() { // from class: m4c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return R8Command.a(c2752uB, (AndroidResourceConsumer) obj);
            }
        });
        StringConsumer fileConsumer2 = this.H;
        boolean zA = this.A.A();
        Path pathO = this.A.o();
        if (zA) {
            fileConsumer2 = pathO != null ? new StringConsumer.FileConsumer(pathO, fileConsumer2) : new l0(fileConsumer2);
        }
        c2752uB.G1 = fileConsumer2;
        StringConsumer fileConsumer3 = this.I;
        boolean z3 = this.A.z();
        Path pathR = this.A.r();
        if (z3) {
            fileConsumer3 = pathR != null ? new StringConsumer.FileConsumer(pathR, fileConsumer3) : new l0(fileConsumer3);
        }
        c2752uB.H1 = fileConsumer3;
        StringConsumer fileConsumer4 = this.J;
        boolean zY = this.A.y();
        Path pathM = this.A.m();
        if (zY) {
            fileConsumer4 = pathM != null ? new StringConsumer.FileConsumer(pathM, fileConsumer4) : new l0(fileConsumer4);
        }
        c2752uB.I1 = fileConsumer4;
        c2752uB.N1 = this.K;
        c2752uB.O1 = this.L;
        c2752uB.n = c2752uB.j.getDataResourceConsumer();
        c2752uB.o = this.P;
        c2752uB.Q1 = this.M;
        c2752uB.p = C2213nw.a(getOutputInspections());
        if (!this.R) {
            c2752uB.a().a();
            c2752uB.a().c();
        }
        if (!z && c2752uB.W0 != null) {
            x1f.a();
            return null;
        }
        AssertionsConfiguration.Builder builderA = AssertionsConfiguration.a(g());
        c2752uB.W0 = new C2996x4(getAssertionsConfiguration(), getProgramConsumer() instanceof ClassFileConsumer ? AssertionsConfiguration.Builder.passthroughAllAssertions(builderA) : AssertionsConfiguration.Builder.compileTimeDisableAllAssertions(builderA));
        if (c2752uB.j instanceof ClassFileConsumer) {
            hVarS.a();
            c2752uB.E0 = C2752uB.g.b;
            c2752uB.a().c();
        }
        if (!z && c2752uB.V0) {
            x1f.a();
            return null;
        }
        c2752uB.V0 = this.D;
        c2752uB.x1 = isOptimizeMultidexForLinearAlloc();
        c2752uB.a(this.O, this.Q);
        boolean zIsEmpty = c2752uB.f0.isEmpty();
        c2752uB.M0 = !zIsEmpty;
        if (!zIsEmpty) {
            c2752uB.a().d = false;
        }
        c2752uB.M1 = this.N;
        c2752uB.R1 = getMapIdProvider();
        SourceFileProvider sourceFileProvider = getSourceFileProvider();
        R2 r3 = this.A;
        if (sourceFileProvider == null) {
            if (!r3.f().a) {
                sourceFileProvider = V0.a(c2752uB.s());
            } else if (!c2752uB.V0) {
                String strP2 = c2752uB.H().p();
                if (strP2 != null) {
                    u0 = new U0(strP2, V0.a(c2752uB, strP2));
                    sourceFileProvider = u0;
                } else if (c2752uB.c0() || c2752uB.e0()) {
                    sourceFileProvider = V0.a(c2752uB.s());
                } else {
                    sourceFileProvider = null;
                }
            } else if (!c2752uB.c0() || (strP = c2752uB.H().p()) == null) {
                sourceFileProvider = null;
            } else {
                u0 = new U0(strP, V0.a(c2752uB, strP));
                sourceFileProvider = u0;
            }
        }
        c2752uB.S1 = sourceFileProvider;
        boolean androidPlatformBuild = getAndroidPlatformBuild();
        if (!C2752uB.Y1 && c2752uB.z0) {
            x1f.a();
            return null;
        }
        if (androidPlatformBuild) {
            c2752uB.a().b();
            c2752uB.G0 = true;
            c2752uB.z0 = androidPlatformBuild;
        }
        c2752uB.w().a = c();
        if (!h().isEmpty()) {
            Ud0 ud0L = c2752uB.L();
            ud0L.g = h();
            ud0L.d = this.S;
        }
        c2752uB.k = IY.a(d(), c2752uB.i);
        c2752uB.c = getCancelCompilationChecker();
        c2752uB.d = this.T;
        c2752uB.e = this.U;
        c2752uB.g = this.V;
        if (!C2752uB.V1) {
            if (!z && c2752uB.g0 != -1) {
                x1f.a();
                return null;
            }
            c2752uB.g0 = getThreadCount();
        }
        c2752uB.r0 = com.android.tools.r8.dex.W.b.e;
        c2752uB.a(f());
        c2752uB.q0 = i();
        return c2752uB;
    }

    public B1 getDexItemFactory() {
        return this.A.b;
    }

    public boolean getEnableMinification() {
        return this.C;
    }

    public boolean getEnableTreeShaking() {
        return this.B;
    }

    public boolean getProguardCompatibility() {
        return this.D;
    }

    public static class Builder extends BaseCompilerCommand.Builder<R8Command, Builder> {
        static final /* synthetic */ boolean a0 = true;
        private final ArrayList B;
        private Consumer C;
        private Consumer D;
        private StringConsumer E;
        private final ArrayList F;
        private boolean G;
        private boolean H;
        private boolean I;
        private Optional J;
        private StringConsumer K;
        private StringConsumer L;
        private StringConsumer M;
        private GraphConsumer N;
        private GraphConsumer O;
        private InputDependencyGraphConsumer P;
        private final C0830So Q;
        private String R;
        private boolean S;
        private boolean T;
        private boolean U;
        private Ta0 V;
        private AndroidResourceProvider W;
        private AndroidResourceConsumer X;
        private ResourceShrinkerConfiguration Y;
        private final W2.a Z;
        public boolean enableStartupLayoutOptimization;

        public Builder(DiagnosticsHandler diagnosticsHandler) {
            super(diagnosticsHandler);
            this.B = new ArrayList();
            this.C = null;
            this.D = null;
            this.E = null;
            this.F = new ArrayList();
            this.G = false;
            this.H = false;
            this.I = false;
            this.J = Optional.empty();
            this.K = null;
            this.L = null;
            this.M = null;
            this.N = null;
            this.O = null;
            this.P = null;
            this.Q = new C0830So();
            this.R = XmlPullParser.NO_NAMESPACE;
            this.S = false;
            this.T = System.getProperty("com.android.tools.r8.enableKeepAnnotations") != null;
            this.U = System.getProperty("com.android.tools.r8.enableVersionedKeepEdgeAnnotations") != null;
            this.enableStartupLayoutOptimization = true;
            this.V = null;
            this.W = null;
            this.X = null;
            this.Y = ResourceShrinkerConfiguration.DEFAULT_CONFIGURATION;
            this.Z = W2.a().b();
            b(!(System.getProperty("com.android.tools.r8.allowDexInputToR8") != null));
        }

        private void a(final T2 t2) {
            boolean z = this.T;
            if (z || this.U) {
                if (!a0 && z == this.U) {
                    x1f.a();
                    return;
                }
                try {
                    com.android.tools.r8.utils.i.a aVarA = a();
                    aVarA.b();
                    Iterator it = aVarA.a.iterator();
                    while (it.hasNext()) {
                        for (final ProgramResource programResource : ((ProgramResourceProvider) it.next()).getProgramResources()) {
                            if (programResource.getKind() == ProgramResource.Kind.CF) {
                                ArrayList arrayListA = !this.T ? AbstractC1646hG.a(programResource.getBytes(), false, true) : AbstractC1646hG.a(programResource.getBytes(), true, false);
                                if (!arrayListA.isEmpty()) {
                                    final C1817jH c1817jH = new C1817jH(new Consumer() { // from class: t4c
                                        @Override // java.util.function.Consumer
                                        public final void accept(Object obj) {
                                            R8Command.Builder.a(programResource, t2, (String) obj);
                                        }
                                    });
                                    arrayListA.forEach(new Consumer() { // from class: u4c
                                        @Override // java.util.function.Consumer
                                        public final void accept(Object obj) {
                                            c1817jH.a((AbstractC2243oF) obj);
                                        }
                                    });
                                }
                            }
                        }
                    }
                } catch (ResourceException e) {
                    C2742u50 c2742u50 = a().m;
                    c2742u50.a(null, new ExceptionDiagnostic(e));
                    throw c2742u50.c;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void b(List list, Origin origin) {
            this.F.add(new C3377c3(list, Paths.get(".", new String[0]), origin));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void c(Path[] pathArr) {
            for (Path path : pathArr) {
                this.F.add(new C3372b3(path));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void e(Path path) {
            this.B.add(new C3372b3(path));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void f(Collection collection) {
            collection.forEach(new Consumer() { // from class: w4c
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.e((Path) obj);
                }
            });
        }

        public Builder addFeatureSplit(Function<FeatureSplit.Builder, FeatureSplit> function) {
            FeatureSplit featureSplitApply = function.apply(FeatureSplit.a(b()));
            this.Q.a.add(featureSplitApply);
            Iterator<ProgramResourceProvider> it = featureSplitApply.getProgramResourceProviders().iterator();
            while (it.hasNext()) {
                addProgramResourceProvider((ProgramResourceProvider) new g0(it.next()));
            }
            return this;
        }

        public Builder addMainDexRules(final List<String> list, final Origin origin) {
            a(new Runnable() { // from class: r4c
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.a(list, origin);
                }
            });
            return this;
        }

        public Builder addMainDexRulesFiles(Path... pathArr) {
            return addMainDexRulesFiles(Arrays.asList(pathArr));
        }

        @Override // com.android.tools.r8.BaseCommand.Builder
        public Builder addProgramResourceProvider(ProgramResourceProvider programResourceProvider) {
            return (Builder) super.addProgramResourceProvider((ProgramResourceProvider) new i0(programResourceProvider));
        }

        public Builder addProguardConfiguration(final List<String> list, final Origin origin) {
            a(new Runnable() { // from class: p4c
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.b(list, origin);
                }
            });
            return this;
        }

        public Builder addProguardConfigurationFiles(final Path... pathArr) {
            a(new Runnable() { // from class: q4c
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.c(pathArr);
                }
            });
            return this;
        }

        public Builder addStartupProfileProviders(StartupProfileProvider... startupProfileProviderArr) {
            return (Builder) addStartupProfileProviders((Collection) Arrays.asList(startupProfileProviderArr));
        }

        @Override // com.android.tools.r8.BaseCompilerCommand.Builder
        public U createProgramOutputConsumer(Path path, OutputMode outputMode, boolean z) {
            return super.createProgramOutputConsumer(path, outputMode, z);
        }

        @Override // com.android.tools.r8.BaseCompilerCommand.Builder, com.android.tools.r8.BaseCommand.Builder
        public final void d() {
            if (isPrintHelp()) {
                return;
            }
            C2742u50 c2742u50B = b();
            if (getProgramConsumer() instanceof DexFilePerClassFileConsumer) {
                c2742u50B.a("R8 does not support compiling to a single DEX file per Java class file");
            }
            if (getMainDexListConsumer() != null && this.B.isEmpty() && !a().c()) {
                c2742u50B.a("Option --main-dex-list-output requires --main-dex-rules and/or --main-dex-list");
            }
            if (!(getProgramConsumer() instanceof ClassFileConsumer)) {
                int minApiLevel = getMinApiLevel();
                EnumC3077y2 enumC3077y2 = EnumC3077y2.w;
                if (minApiLevel >= enumC3077y2.d() && (getMainDexListConsumer() != null || !this.B.isEmpty() || a().c())) {
                    c2742u50B.a("R8 does not support main-dex inputs and outputs when compiling to API level " + enumC3077y2.d() + " and above");
                }
            }
            for (FeatureSplit featureSplit : this.Q.a()) {
                if (!a0 && !(featureSplit.getProgramConsumer() instanceof DexIndexedConsumer) && featureSplit.getAndroidResourceProvider() == null) {
                    x1f.a();
                    return;
                } else if (getProgramConsumer() != null && !(getProgramConsumer() instanceof DexIndexedConsumer)) {
                    c2742u50B.a("R8 does not support class file output when using feature splits");
                }
            }
            for (Path path : this.e) {
                if (C0831Sp.d(path)) {
                    c2742u50B.error(new StringDiagnostic("R8 does not support compiling DEX inputs", new PathOrigin(path)));
                }
            }
            if ((getProgramConsumer() instanceof ClassFileConsumer) && n()) {
                c2742u50B.a("R8 does not support --min-api when compiling to class files");
            }
            if (m() && getDisableDesugaring()) {
                c2742u50B.a("Using desugared library configuration requires desugaring to be enabled");
            }
            super.d();
        }

        public boolean getProguardCompatibility() {
            return this.I;
        }

        public StringConsumer getProguardConfigurationConsumer() {
            return this.M;
        }

        /*  JADX ERROR: NullPointerException in pass: InitCodeVariables
            java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.SSAVar.getPhiList()" because "resultVar" is null
            	at jadx.core.dex.visitors.InitCodeVariables.collectConnectedVars(InitCodeVariables.java:119)
            	at jadx.core.dex.visitors.InitCodeVariables.setCodeVar(InitCodeVariables.java:82)
            	at jadx.core.dex.visitors.InitCodeVariables.initCodeVar(InitCodeVariables.java:74)
            	at jadx.core.dex.visitors.InitCodeVariables.initCodeVars(InitCodeVariables.java:48)
            	at jadx.core.dex.visitors.InitCodeVariables.visit(InitCodeVariables.java:29)
            */
        @Override // com.android.tools.r8.BaseCommand.Builder
        /* JADX INFO: renamed from: p, reason: merged with bridge method [inline-methods] */
        public final com.android.tools.r8.R8Command makeCommand() {
            /*
                Method dump skipped, instruction units count: 401
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.tools.r8.R8Command.Builder.makeCommand():com.android.tools.r8.R8Command");
        }

        public void q() {
            this.Z.a(true);
        }

        public void r() {
            this.Z.b(true);
        }

        public void s() {
            this.Z.c(true);
        }

        public Builder setAndroidResourceConsumer(AndroidResourceConsumer androidResourceConsumer) {
            this.X = androidResourceConsumer;
            return this;
        }

        public Builder setAndroidResourceProvider(AndroidResourceProvider androidResourceProvider) {
            this.W = androidResourceProvider;
            return this;
        }

        public Builder setDesugaredLibraryKeepRuleConsumer(StringConsumer stringConsumer) {
            this.E = stringConsumer;
            return this;
        }

        public Builder setDisableMinification(boolean z) {
            this.H = z;
            return this;
        }

        public Builder setDisableTreeShaking(boolean z) {
            this.G = z;
            return this;
        }

        public Builder setEnableExperimentalExtractedKeepAnnotations(boolean z) {
            this.U = z;
            return this;
        }

        public Builder setEnableExperimentalIsolatedSplits(boolean z) {
            return setEnableIsolatedSplits(z);
        }

        public Builder setEnableExperimentalKeepAnnotations(boolean z) {
            this.T = z;
            return this;
        }

        public Builder setEnableExperimentalMissingLibraryApiModeling(boolean z) {
            this.S = z;
            return this;
        }

        public Builder setEnableIsolatedSplits(boolean z) {
            this.Q.b = z;
            return this;
        }

        public Builder setEnableStartupLayoutOptimization(boolean z) {
            this.enableStartupLayoutOptimization = z;
            return this;
        }

        public Builder setInputDependencyGraphConsumer(InputDependencyGraphConsumer inputDependencyGraphConsumer) {
            this.P = inputDependencyGraphConsumer;
            return this;
        }

        public Builder setKeptGraphConsumer(GraphConsumer graphConsumer) {
            this.N = graphConsumer;
            return this;
        }

        public Builder setMainDexKeptGraphConsumer(GraphConsumer graphConsumer) {
            this.O = graphConsumer;
            return this;
        }

        @Override // com.android.tools.r8.BaseCompilerCommand.Builder
        public Builder setOutput(Path path, OutputMode outputMode, boolean z) {
            this.J = Optional.of(Boolean.valueOf(z));
            return (Builder) super.setOutput(path, outputMode, z);
        }

        public Builder setProguardCompatibility(boolean z) {
            this.I = z;
            return this;
        }

        public Builder setProguardConfigurationConsumer(StringConsumer stringConsumer) {
            this.M = stringConsumer;
            return this;
        }

        public Builder setProguardMapConsumer(StringConsumer stringConsumer) {
            this.proguardMapConsumer = stringConsumer;
            return (Builder) ((BaseCompilerCommand.Builder) c());
        }

        public Builder setProguardMapInputFile(Path path) {
            com.android.tools.r8.utils.i.a aVarA = a();
            aVarA.getClass();
            aVarA.l = t0.a(path);
            return this;
        }

        public Builder setProguardMapOutputPath(Path path) {
            if (BaseCompilerCommand.Builder.A || path != null) {
                return (Builder) setProguardMapConsumer((StringConsumer) new StringConsumer.FileConsumer(path));
            }
            x1f.a();
            return null;
        }

        public Builder setProguardSeedsConsumer(StringConsumer stringConsumer) {
            this.L = stringConsumer;
            return this;
        }

        public Builder setProguardUsageConsumer(StringConsumer stringConsumer) {
            this.K = stringConsumer;
            return this;
        }

        public Builder setResourceShrinkerConfiguration(Function<ResourceShrinkerConfiguration.Builder, ResourceShrinkerConfiguration> function) {
            this.Y = function.apply(ResourceShrinkerConfiguration.builder(b()));
            return this;
        }

        public void t() {
            this.Z.d(true);
        }

        public Builder addMainDexRulesFiles(final Collection<Path> collection) {
            a(new Runnable() { // from class: x4c
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.f(collection);
                }
            });
            return this;
        }

        public Builder addProguardConfigurationFiles(final List<Path> list) {
            a(new Runnable() { // from class: o4c
                @Override // java.lang.Runnable
                public final void run() {
                    this.b.a(list);
                }
            });
            return this;
        }

        @Override // com.android.tools.r8.BaseCompilerCommand.Builder
        public final CompilationMode e() {
            return CompilationMode.RELEASE;
        }

        @Override // com.android.tools.r8.BaseCompilerCommand.Builder
        public Builder addStartupProfileProviders(Collection<StartupProfileProvider> collection) {
            return (Builder) super.addStartupProfileProviders((Collection) collection);
        }

        @Override // com.android.tools.r8.BaseCompilerCommand.Builder
        public /* bridge */ /* synthetic */ BaseCompilerCommand.Builder addStartupProfileProviders(Collection collection) {
            return addStartupProfileProviders((Collection<StartupProfileProvider>) collection);
        }

        @Override // com.android.tools.r8.BaseCompilerCommand.Builder
        public Builder setOutput(Path path, OutputMode outputMode) {
            setOutput(path, outputMode, true);
            return this;
        }

        public void b(Consumer<List<Y2>> consumer) {
            Consumer consumer2 = this.D;
            if (consumer2 != null) {
                consumer = consumer2.andThen(consumer);
            }
            this.D = consumer;
        }

        @Override // com.android.tools.r8.BaseCommand.Builder
        public final BaseCommand.Builder c() {
            return this;
        }

        private Builder(com.android.tools.r8.utils.i iVar) {
            super(iVar);
            this.B = new ArrayList();
            this.C = null;
            this.D = null;
            this.E = null;
            this.F = new ArrayList();
            this.G = false;
            this.H = false;
            this.I = false;
            this.J = Optional.empty();
            this.K = null;
            this.L = null;
            this.M = null;
            this.N = null;
            this.O = null;
            this.P = null;
            this.Q = new C0830So();
            this.R = XmlPullParser.NO_NAMESPACE;
            this.S = false;
            this.T = System.getProperty("com.android.tools.r8.enableKeepAnnotations") != null;
            this.U = System.getProperty("com.android.tools.r8.enableVersionedKeepEdgeAnnotations") != null;
            this.enableStartupLayoutOptimization = true;
            this.V = null;
            this.W = null;
            this.X = null;
            this.Y = ResourceShrinkerConfiguration.DEFAULT_CONFIGURATION;
            this.Z = W2.a().b();
            b(!(System.getProperty("com.android.tools.r8.allowDexInputToR8") != null));
        }

        public Builder a(Ta0 ta0) {
            this.V = ta0;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(List list, Origin origin) {
            this.B.add(new C3377c3(list, Paths.get(".", new String[0]), origin));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void a(List list) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                this.F.add(new C3372b3((Path) it.next()));
            }
        }

        private void a(final C2742u50 c2742u50, T2 t2) {
            InterfaceC2105mg0 c2363pg0;
            ArchiveResourceProvider archiveResourceProviderA;
            Set setB = C1755ib0.b(new K0[0]);
            com.android.tools.r8.utils.i.a aVarA = a();
            aVarA.b();
            ArrayDeque arrayDeque = new ArrayDeque(aVarA.a);
            while (true) {
                for (K0 k0 : t2.a.b) {
                    if (setB.add(k0) && (archiveResourceProviderA = a().a(k0)) != null) {
                        arrayDeque.add(archiveResourceProviderA);
                    }
                }
                if (arrayDeque.isEmpty()) {
                    return;
                }
                InterfaceC2105mg0 interfaceC2105mg0 = new InterfaceC2105mg0() { // from class: s4c
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return this.b.a(c2742u50);
                    }
                };
                if (interfaceC2105mg0 instanceof Serializable) {
                    c2363pg0 = new C2277og0(interfaceC2105mg0);
                } else {
                    c2363pg0 = new C2363pg0(interfaceC2105mg0);
                }
                while (!arrayDeque.isEmpty()) {
                    DataResourceProvider dataResourceProvider = ((ProgramResourceProvider) arrayDeque.pop()).getDataResourceProvider();
                    if (dataResourceProvider != null) {
                        try {
                            j0 j0Var = new j0(c2742u50, c2363pg0);
                            dataResourceProvider.accept(j0Var);
                            j0Var.a(t2);
                        } catch (ResourceException e) {
                            c2742u50.error(new ExceptionDiagnostic(e));
                        }
                    }
                }
            }
        }

        private Builder(com.android.tools.r8.utils.i iVar, DiagnosticsHandler diagnosticsHandler) {
            super(iVar, diagnosticsHandler);
            this.B = new ArrayList();
            this.C = null;
            this.D = null;
            this.E = null;
            this.F = new ArrayList();
            this.G = false;
            this.H = false;
            this.I = false;
            this.J = Optional.empty();
            this.K = null;
            this.L = null;
            this.M = null;
            this.N = null;
            this.O = null;
            this.P = null;
            this.Q = new C0830So();
            this.R = XmlPullParser.NO_NAMESPACE;
            this.S = false;
            this.T = System.getProperty("com.android.tools.r8.enableKeepAnnotations") != null;
            this.U = System.getProperty("com.android.tools.r8.enableVersionedKeepEdgeAnnotations") != null;
            this.enableStartupLayoutOptimization = true;
            this.V = null;
            this.W = null;
            this.X = null;
            this.Y = ResourceShrinkerConfiguration.DEFAULT_CONFIGURATION;
            this.Z = W2.a().b();
            b(!(System.getProperty("com.android.tools.r8.allowDexInputToR8") != null));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Ta0 a(C2742u50 c2742u50) {
            Ta0 ta0A = this.V;
            if (ta0A == null) {
                ta0A = Ta0.a(Version.getMajorVersion(), Version.getMinorVersion(), Version.getPatchVersion());
            }
            if (ta0A.a() >= 0) {
                return ta0A;
            }
            Ta0 ta0A2 = Ta0.a(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
            c2742u50.c("Running R8 version " + Version.getVersionString() + ", which cannot be represented as a semantic version. Using an artificial version newer than any known version for selecting Proguard configurations embedded under META-INF/. This means that all rules with a '-upto-' qualifier will be excluded and all rules with a -from- qualifier will be included.");
            return ta0A2;
        }

        public final Builder a(String str) {
            this.R = str;
            return this;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void a(ProgramResource programResource, T2 t2, String str) {
            t2.a(new C3377c3(Collections.singletonList(str), null, programResource.getOrigin()));
        }

        public void a(final Consumer<R2.a> consumer) {
            final Consumer consumer2 = this.C;
            this.C = new Consumer() { // from class: v4c
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    R8Command.Builder.a(consumer2, consumer, (R2.a) obj);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void a(Consumer consumer, Consumer consumer2, R2.a aVar) {
            if (consumer != null) {
                consumer.accept(aVar);
            }
            consumer2.accept(aVar);
        }
    }

    public static Builder builder(DiagnosticsHandler diagnosticsHandler) {
        return new Builder(diagnosticsHandler);
    }

    public static Builder a(com.android.tools.r8.utils.i iVar, DiagnosticsHandler diagnosticsHandler) {
        return new Builder(iVar, diagnosticsHandler);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static com.android.tools.r8.naming.Q a(StringConsumer stringConsumer, StringConsumer stringConsumer2) {
        return new I0(stringConsumer);
    }

    public static Builder parse(String[] strArr, Origin origin, DiagnosticsHandler diagnosticsHandler) {
        return q0.a(strArr, origin, diagnosticsHandler);
    }

    public static Builder a(com.android.tools.r8.utils.i iVar) {
        return new Builder(iVar);
    }

    private R8Command(boolean z, boolean z2) {
        super(z, z2);
        int i = AbstractC0551Hu.c;
        this.z = P40.e;
        this.A = null;
        this.B = false;
        this.C = false;
        this.D = false;
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        this.O = null;
        this.P = null;
        this.Q = null;
        this.R = false;
        this.S = true;
        this.T = null;
        this.U = null;
        this.V = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(C3057xm.a aVar) {
        aVar.c(this.P.b);
    }
}
