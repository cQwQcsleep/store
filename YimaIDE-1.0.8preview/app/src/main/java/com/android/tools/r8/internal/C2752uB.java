package com.android.tools.r8.internal;

import com.android.tools.r8.AndroidResourceConsumer;
import com.android.tools.r8.AndroidResourceProvider;
import com.android.tools.r8.CancelCompilationChecker;
import com.android.tools.r8.ClassFileConsumer;
import com.android.tools.r8.CompilationMode;
import com.android.tools.r8.DataResourceConsumer;
import com.android.tools.r8.DesugarGraphConsumer;
import com.android.tools.r8.DexFilePerClassFileConsumer;
import com.android.tools.r8.DexIndexedConsumer;
import com.android.tools.r8.DiagnosticsLevel;
import com.android.tools.r8.FeatureSplit;
import com.android.tools.r8.GlobalSyntheticsConsumer;
import com.android.tools.r8.MapIdProvider;
import com.android.tools.r8.ProgramConsumer;
import com.android.tools.r8.ResourceShrinkerConfiguration;
import com.android.tools.r8.SourceFileProvider;
import com.android.tools.r8.StringConsumer;
import com.android.tools.r8.SyntheticInfoConsumer;
import com.android.tools.r8.Version;
import com.android.tools.r8.dex.InterfaceC0010b;
import com.android.tools.r8.dex.Y;
import com.android.tools.r8.dex.t0;
import com.android.tools.r8.errors.DuplicateTypeInProgramAndLibraryDiagnostic;
import com.android.tools.r8.errors.InvalidLibrarySuperclassDiagnostic;
import com.android.tools.r8.experimental.graphinfo.GraphConsumer;
import com.android.tools.r8.graph.AbstractC0327x0;
import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0281q2;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.C0333y;
import com.android.tools.r8.graph.D2;
import com.android.tools.r8.graph.E0;
import com.android.tools.r8.graph.H0;
import com.android.tools.r8.graph.I0;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.AbstractC2624sj0;
import com.android.tools.r8.internal.C1290d50;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.Ch0;
import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.internal.InterfaceC3093yB;
import com.android.tools.r8.naming.AbstractC3345r0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.shaking.C3403i;
import com.android.tools.r8.threading.ThreadingModule;
import com.android.tools.r8.utils.StringDiagnostic;
import defpackage.fd6;
import defpackage.jfh;
import defpackage.ofi;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.uB, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2752uB implements com.android.tools.r8.shaking.L0 {
    public static final boolean V1;
    public static final C1159bb W1;
    public static final int X1;
    public static final /* synthetic */ boolean Y1 = true;
    public boolean A;
    public boolean A0;
    public final HashMap A1;
    public boolean B;
    public boolean B0;
    public final HashMap B1;
    public boolean C;
    public boolean C0;
    public ConcurrentHashMap C1;
    public boolean D;
    public boolean D0;
    public boolean D1;
    public boolean E;
    public g E0;
    public StringConsumer E1;
    public boolean F;
    public boolean F0;
    public com.android.tools.r8.naming.Q F1;
    public boolean G;
    public boolean G0;
    public StringConsumer G1;
    public boolean H;
    public boolean H0;
    public StringConsumer H1;
    public final boolean I;
    public EnumC1066aV I0;
    public StringConsumer I1;
    public final boolean J;
    public EnumC1066aV J0;
    public InterfaceC1766ih0 J1;
    public boolean K;
    public boolean K0;
    public C2592sM K1;
    public boolean L;
    public final boolean L0;
    public boolean L1;
    public boolean M;
    public boolean M0;
    public StringConsumer M1;
    public boolean N;
    public boolean N0;
    public GraphConsumer N1;
    public BiPredicate O;
    public final boolean O0;
    public GraphConsumer O1;
    public boolean P;
    public final boolean P0;
    public DesugarGraphConsumer P1;
    public boolean Q;
    public boolean Q0;
    public Consumer Q1;
    public boolean R;
    public boolean R0;
    public MapIdProvider R1;
    public final int S;
    public boolean S0;
    public SourceFileProvider S1;
    public final Thread T;
    public boolean T0;
    public final Set T1;
    public boolean U;
    public boolean U0;
    public final Set U1;
    public boolean V;
    public boolean V0;
    public int W;
    public C2996x4 W0;
    public boolean X;
    public final boolean X0;
    public boolean Y;
    public boolean Y0;
    public final m Z;
    public boolean Z0;
    public final com.android.tools.r8.graph.B1 a;
    public boolean a0;
    public final R1 a1;
    public boolean b0;
    public final p b1;
    public boolean c0;
    public final c c1;
    public final boolean d0;
    public final d d1;
    public boolean e0;
    public final e e1;
    public String f0;
    public final i f1;
    public int g0;
    public final C1585gc0 g1;
    public final com.android.tools.r8.shaking.R2 h;
    public boolean h0;
    public final C1218cD h1;
    public final C2742u50 i;
    public boolean i0;
    public final h i1;
    public boolean j0;
    public final Wl0 j1;
    public boolean k0;
    public final l k1;
    public final boolean l0;
    public final o l1;
    public boolean m0;
    public final C20 m1;
    public DataResourceConsumer n;
    public boolean n0;
    public final C2923wB n1;
    public C0882Uo o;
    public final boolean o0;
    public final a o1;
    public AbstractC2972wm p0;
    public final f p1;
    public C3057xm q0;
    public final k q1;
    public final boolean r;
    public com.android.tools.r8.dex.W.b r0;
    public final C2226o4 r1;
    public final boolean s;
    public boolean s0;
    public final Ud0 s1;
    public boolean t;
    public com.android.tools.r8.dex.W t0;
    public final Sd0 t1;
    public final boolean u;
    public final AbstractC2554rv u0;
    public final q u1;
    public final C3009xB v;
    public final AbstractC2554rv v0;
    public List v1;
    public boolean w;
    public final P40 w0;
    public boolean w1;
    public final boolean x;
    public EnumC3077y2 x0;
    public boolean x1;
    public boolean y;
    public boolean y0;
    public j y1;
    public boolean z;
    public boolean z0;
    public final HashMap z1;
    public final AtomicBoolean b = new AtomicBoolean(false);
    public CancelCompilationChecker c = null;
    public AndroidResourceProvider d = null;
    public AndroidResourceConsumer e = null;
    public List f = null;
    public ResourceShrinkerConfiguration g = ResourceShrinkerConfiguration.DEFAULT_CONFIGURATION;
    public ProgramConsumer j = null;
    public InterfaceC0010b k = null;
    public GlobalSyntheticsConsumer l = null;
    public SyntheticInfoConsumer m = null;
    public List p = Collections.EMPTY_LIST;
    public ThreadingModule q = null;

    /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$b */
    public interface b {
        boolean a(C0333y<?> c0333y, com.android.tools.r8.graph.B5 b5, int i);
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$d */
    public static class d {
        public boolean a = true;
        public boolean b = false;

        public d a(boolean z) {
            this.a = z;
            return this;
        }

        public d b(boolean z) {
            this.b = z;
            return this;
        }
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$e */
    public class e {
        public static final /* synthetic */ boolean c = true;
        public int a = -1;

        public e() {
        }
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$f */
    public static class f {
        public final boolean a;
        public final boolean b;
        public boolean c;
        public final boolean d;

        public f() {
            this.a = System.getProperty("com.android.tools.r8.sortMethodsOnCfWriting") != null;
            this.b = System.getProperty("com.android.tools.r8.allowAllDesugaredInput") != null;
            this.c = System.getProperty("com.android.tools.r8.noCfMarkerForDesugaredCode") != null;
            this.d = System.getProperty("com.android.tools.r8.lambdaClassFieldsNotFinal") == null;
        }
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$g */
    public enum g {
        b,
        c;

        g() {
        }

        public final boolean a() {
            return this == c;
        }
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$j */
    public static final class j {
        public static final j b = new j(0, "OFF");
        public static final j c = new j(1, "ON");

        public j(int i, String str) {
            super(str, i);
        }
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$k */
    public static class k {
        public boolean a = false;
        public final boolean b = Kg0.a("com.android.tools.r8.allowemptymappedranges", Kg0.a("com.android.tools.r8.allowemptymappedranges"), false);
        public final boolean c = true;
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$m */
    public static class m {
        public boolean a = true;
        public int b = 3;
        public int c = 99;
        public int d = 20;
    }

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$n */
    public static final class n {
        public static final n b = new n(0, "NONE");
        public static final n c = new n(1, "MINIFICATION");
        public static final n d = new n(2, "REPACKAGE");
        public static final n e = new n(3, "FLATTEN");

        public n(int i, String str) {
            super(str, i);
        }

        public final boolean a() {
            return this == e;
        }

        public final boolean b() {
            return this == c;
        }

        public final boolean c() {
            return this == d;
        }
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$o */
    public static class o {
        public boolean a = false;
        public boolean b = false;
        public boolean c = false;
        public boolean d = false;
        public boolean e = false;

        public final boolean a() {
            return this.e;
        }

        public final boolean b() {
            return this.a || this.b || this.c || this.e;
        }
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$p */
    public class p {
        public static final /* synthetic */ boolean c = true;
        public int a = 239;

        public p() {
        }
    }

    static {
        V1 = System.getProperty("com.android.tools.r8.deterministicdebugging") != null;
        W1 = C1159bb.n;
        EnumC3077y2.L.c().getClass();
        X1 = EnumC1095am.V41.b;
    }

    public C2752uB(CompilationMode compilationMode, com.android.tools.r8.shaking.R2 r2, C2742u50 c2742u50) {
        this.r = System.getProperty("com.android.tools.r8.printtimes") != null;
        this.s = System.getProperty("com.android.tools.r8.printmemory") != null;
        this.t = false;
        this.u = true;
        int i2 = AbstractC0551Hu.c;
        this.v = new C3009xB(new Bc0("j$."), new Bc0("java."));
        this.w = false;
        this.x = System.getProperty("com.android.tools.r8.fieldBitAccessAnalysis") != null;
        this.y = true;
        this.z = true;
        this.A = true;
        this.B = true;
        this.C = true;
        this.D = true;
        this.E = true;
        this.F = true;
        this.G = true;
        this.H = true;
        this.I = true;
        this.J = true;
        this.K = true;
        this.L = true;
        this.M = false;
        this.N = false;
        this.O = new BiPredicate() { // from class: mfi
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return C2752uB.a((String) obj, (Long) obj2);
            }
        };
        this.P = false;
        this.Q = false;
        this.R = System.getProperty("com.android.tools.r8.disableL8AnnotationRemoval") != null;
        this.S = 50;
        this.T = Thread.currentThread();
        this.U = true;
        this.V = true;
        this.W = 3;
        this.X = true;
        this.Y = true;
        this.Z = new m();
        this.a0 = true;
        this.b0 = true;
        this.c0 = false;
        this.d0 = true;
        this.e0 = false;
        this.f0 = XmlPullParser.NO_NAMESPACE;
        this.g0 = V1 ? 1 : -1;
        this.h0 = false;
        this.i0 = System.getProperty("com.android.tools.r8.strictdebuginfo") != null;
        this.j0 = false;
        this.k0 = false;
        this.l0 = System.getProperty("com.android.tools.r8.emitRecordAnnotationsInDex") != null;
        this.m0 = System.getProperty("com.android.tools.r8.emitNestAnnotationsInDex") != null;
        this.n0 = System.getProperty("com.android.tools.r8.forceNestDesugaring") != null;
        this.o0 = System.getProperty("com.android.tools.r8.emitPermittedSubclassesAnnotationsInDex") != null;
        this.p0 = AbstractC2972wm.a();
        this.r0 = null;
        this.s0 = false;
        this.u0 = B();
        this.v0 = A();
        P40 p40 = P40.e;
        this.w0 = p40;
        this.x0 = EnumC3077y2.b();
        this.y0 = false;
        this.z0 = false;
        this.A0 = true;
        this.B0 = System.getProperty("com.android.tools.r8.ignoreBootClasspathEnumsForMaindexTracing") != null;
        this.C0 = System.getProperty("com.android.tools.r8.pruneNonVissibleAnnotationClasses") != null;
        this.D0 = true;
        this.E0 = g.c;
        this.F0 = false;
        this.G0 = false;
        this.H0 = true;
        EnumC1066aV enumC1066aV = EnumC1066aV.c;
        this.I0 = enumC1066aV;
        this.J0 = enumC1066aV;
        this.K0 = true;
        this.L0 = System.getProperty("com.android.tools.r8.disableEnqueuerDeferredTracing") == null;
        this.M0 = false;
        this.N0 = true;
        this.Q0 = false;
        this.R0 = false;
        this.S0 = false;
        this.T0 = false;
        this.U0 = false;
        this.V0 = false;
        this.W0 = null;
        this.X0 = false;
        this.Y0 = false;
        this.Z0 = false;
        this.a1 = new R1(this);
        this.b1 = new p();
        this.c1 = new c();
        this.d1 = new d();
        this.e1 = new e();
        this.f1 = new i(this);
        this.g1 = new C1585gc0(this);
        this.h1 = new C1218cD(this);
        this.i1 = new h();
        this.j1 = new Wl0(this);
        this.k1 = new l();
        o oVar = new o();
        this.l1 = oVar;
        this.m1 = new C20();
        this.n1 = new C2923wB();
        this.o1 = new a();
        this.p1 = new f();
        this.q1 = new k();
        this.r1 = new C2226o4(this);
        this.s1 = new Ud0();
        this.t1 = new Sd0();
        this.u1 = new q();
        this.v1 = p40;
        this.x1 = true;
        this.y1 = j.c;
        this.z1 = new HashMap();
        this.A1 = new HashMap();
        this.B1 = new HashMap();
        this.D1 = false;
        this.E1 = null;
        this.F1 = null;
        this.G1 = null;
        this.H1 = null;
        this.I1 = null;
        this.J1 = null;
        this.K1 = C2592sM.g();
        this.L1 = false;
        this.M1 = null;
        this.N1 = null;
        this.O1 = null;
        this.P1 = null;
        this.Q1 = null;
        this.R1 = null;
        this.S1 = null;
        this.T1 = C1755ib0.a();
        new AtomicBoolean(false);
        this.U1 = C1755ib0.a();
        boolean z = Y1;
        if (!z && c2742u50 == null) {
            x1f.a();
            throw null;
        }
        if (!z && r2 == null) {
            x1f.a();
            throw null;
        }
        this.Z0 = compilationMode == CompilationMode.DEBUG;
        this.i = c2742u50;
        this.h = r2;
        this.a = r2.b;
        this.O0 = r2.B();
        this.P0 = r2.w();
        if (!r2.x()) {
            t();
            this.K = false;
            this.L = false;
        }
        if (this.Z0) {
            if (!z && c0()) {
                x1f.a();
                throw null;
            }
            if (!z && e0()) {
                x1f.a();
                throw null;
            }
            if (!z && r2.w()) {
                x1f.a();
                throw null;
            }
            H().f().a = true;
            H().f().k = true;
            H().f().g = true;
            H().f().h = true;
            H().f().i = true;
        }
        this.X0 = r2.u();
        if (r2.K) {
            this.x = true;
            oVar.b = true;
            oVar.c = true;
            oVar.a = true;
            oVar.e = true;
        }
    }

    public static AbstractC2554rv A() {
        String property = System.getProperty("com.android.tools.r8.extensiveInterfaceMethodMinifierLoggingFilter");
        if (property == null) {
            int i2 = AbstractC2554rv.c;
            return W40.j;
        }
        int i3 = AbstractC2554rv.c;
        C1870jv c1870jv = new C1870jv();
        Wf0.a(property, ';', (Consumer) new ofi(c1870jv));
        return c1870jv.a();
    }

    public static AbstractC2554rv B() {
        String property = System.getProperty("com.android.tools.r8.extensiveLoggingFilter");
        if (property == null) {
            int i2 = AbstractC2554rv.c;
            return W40.j;
        }
        int i3 = AbstractC2554rv.c;
        C1870jv c1870jv = new C1870jv();
        Wf0.a(property, ';', (Consumer) new ofi(c1870jv));
        return c1870jv.a();
    }

    public static String a(C3024xQ c3024xQ) {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList();
        c3024xQ.a.a(new Consumer() { // from class: pfi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList.add((D2) obj);
            }
        }, new Consumer() { // from class: qfi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList2.add((I0) obj);
            }
        }, new Consumer() { // from class: rfi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                arrayList3.add((C0281q2) obj);
            }
        });
        Iterator it = c3024xQ.b.iterator();
        while (it.hasNext()) {
            ((com.android.tools.r8.graph.E0) it.next()).a(new Consumer() { // from class: pfi
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    arrayList.add((D2) obj);
                }
            }, new Consumer() { // from class: qfi
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    arrayList2.add((I0) obj);
                }
            }, new Consumer() { // from class: rfi
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    arrayList3.add((C0281q2) obj);
                }
            });
        }
        StringBuilder sb = new StringBuilder("Compilation of classes ");
        sb.append(Wf0.a(", ", arrayList, new Function() { // from class: ffi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((D2) obj).e1();
            }
        }));
        sb.append(" requires its nest mates ");
        if (!c3024xQ.c.isEmpty()) {
            sb.append(Wf0.a(", ", c3024xQ.c, new fd6()));
            sb.append(" (unavailable) ");
        }
        if (!arrayList3.isEmpty()) {
            sb.append(Wf0.a(", ", arrayList3, new Function() { // from class: gfi
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((C0281q2) obj).e1();
                }
            }));
            sb.append(" (on library path) ");
        }
        sb.append("to be on program or class path.");
        if (!arrayList2.isEmpty()) {
            sb.append("(Classes ");
            sb.append(Wf0.a(", ", arrayList2, new Function() { // from class: hfi
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((I0) obj).e1();
                }
            }));
            sb.append(" from the same nest are on class path).");
        }
        return sb.toString();
    }

    public final AB C() {
        if (!Y1 && this.j == null) {
            x1f.a();
            return null;
        }
        ProgramConsumer programConsumer = this.j;
        if (programConsumer instanceof DexIndexedConsumer) {
            return AB.b;
        }
        if (programConsumer instanceof DexFilePerClassFileConsumer) {
            return AB.c;
        }
        if (programConsumer instanceof ClassFileConsumer) {
            return AB.d;
        }
        c41.a("Cannot find internal output mode.");
        return null;
    }

    public C1218cD D() {
        return this.h1;
    }

    public final com.android.tools.r8.dex.W E() {
        if (!Y1 && this.r0 == null) {
            x1f.a();
            return null;
        }
        if (this.s0) {
            return this.t0;
        }
        com.android.tools.r8.dex.W.b bVar = this.r0;
        com.android.tools.r8.dex.W wA = new com.android.tools.r8.dex.W(bVar).a(Version.LABEL);
        CompilationMode compilationMode = this.Z0 ? CompilationMode.DEBUG : CompilationMode.RELEASE;
        boolean z = com.android.tools.r8.dex.W.g;
        if (!z && wA.a.b.containsKey("compilation-mode")) {
            x1f.a();
            return null;
        }
        wA.a.a("compilation-mode", Wf0.i(compilationMode.toString()));
        com.android.tools.r8.dex.W.a aVar = this.j instanceof ClassFileConsumer ? com.android.tools.r8.dex.W.a.b : com.android.tools.r8.dex.W.a.c;
        if (!z && wA.a.b.containsKey("backend")) {
            x1f.a();
            return null;
        }
        wA.a.a("backend", Wf0.i(aVar.name()));
        boolean z2 = this.N;
        if (!z && wA.a.b.containsKey("has-checksums")) {
            x1f.a();
            return null;
        }
        C1898kD c1898kD = wA.a;
        Boolean boolValueOf = Boolean.valueOf(z2);
        c1898kD.getClass();
        c1898kD.b.put("has-checksums", new C2155nD(boolValueOf));
        if (Z() || this.E0 == g.c) {
            wA.a(F().d());
        }
        if (this.K1.b.c() != null) {
            String[] strArr = {this.K1.b.c()};
            if (!z && wA.a.b.containsKey("desugared-library-identifiers")) {
                x1f.a();
                return null;
            }
            C1558gD c1558gD = new C1558gD();
            String str = strArr[0];
            c1558gD.b.add(str == null ? C1813jD.b : new C2155nD(str));
            wA.a.b.put("desugared-library-identifiers", c1558gD);
        }
        if (Version.isDevelopmentVersion()) {
            String strB = Pl0.c.b();
            if (!z && wA.a.b.containsKey("sha-1")) {
                x1f.a();
                return null;
            }
            wA.a.a("sha-1", strB);
        }
        if (bVar == com.android.tools.r8.dex.W.b.e) {
            String str2 = this.V0 ? "compatibility" : "full";
            if (!z && wA.a.b.containsKey("r8-mode")) {
                x1f.a();
                return null;
            }
            wA.a.a("r8-mode", str2);
        }
        if (this.z0) {
            if (!z && wA.a.b.containsKey("platform")) {
                x1f.a();
                return null;
            }
            C1898kD c1898kD2 = wA.a;
            Boolean bool = Boolean.TRUE;
            c1898kD2.getClass();
            c1898kD2.b.put("platform", new C2155nD(bool));
        }
        return wA;
    }

    public final EnumC3077y2 F() {
        if (Y1 || this.E0.a() || Z() || this.x0.equals(EnumC3077y2.c)) {
            return this.x0;
        }
        x1f.a();
        return null;
    }

    public l G() {
        return this.k1;
    }

    public com.android.tools.r8.shaking.R2 H() {
        return this.h;
    }

    public C20 I() {
        return this.m1;
    }

    public C1585gc0 J() {
        return this.g1;
    }

    public Sd0 K() {
        return this.t1;
    }

    public Ud0 L() {
        return this.s1;
    }

    public q M() {
        return this.u1;
    }

    public ThreadingModule N() {
        if (this.q == null) {
            this.q = com.android.tools.r8.threading.a.b().create();
        }
        return this.q;
    }

    public final Dj0 O() {
        C2592sM c2592sM = this.K1;
        return (c2592sM.m().isEmpty() && c2592sM.c.m().isEmpty()) ? new Bj0() : new Cj0(this.K1);
    }

    public Wl0 P() {
        return this.j1;
    }

    public final boolean Q() {
        return this.F1 != null;
    }

    public final boolean R() {
        return this.w0.d.length > 0;
    }

    public h S() {
        return this.i1;
    }

    public i T() {
        return this.f1;
    }

    public final boolean U() {
        return (this.j instanceof ClassFileConsumer) && this.E0.a();
    }

    public final boolean V() {
        return this.K1.a;
    }

    public final boolean W() {
        return this.E0.a();
    }

    public final boolean X() {
        return this.V0;
    }

    public final boolean Y() {
        return this.j instanceof ClassFileConsumer;
    }

    public boolean Z() {
        ProgramConsumer programConsumer = this.j;
        return (programConsumer instanceof DexIndexedConsumer) || (programConsumer instanceof DexFilePerClassFileConsumer);
    }

    public final boolean a0() {
        return this.j != null && this.E0.a() && this.I0 == EnumC1066aV.c && !i();
    }

    public final boolean b(EnumC3077y2 enumC3077y2) {
        if (this.E0.a() || Z()) {
            return enumC3077y2 != null && F().a(enumC3077y2);
        }
        if (Y1 || this.x0.equals(EnumC3077y2.c)) {
            return true;
        }
        x1f.a();
        return false;
    }

    public final boolean b0() {
        com.android.tools.r8.shaking.R2 r2 = this.h;
        return r2 == null || r2.f().e;
    }

    public void c(EnumC3077y2 enumC3077y2) {
        if (Y1 || enumC3077y2 != null) {
            this.x0 = enumC3077y2;
        } else {
            x1f.a();
        }
    }

    public final boolean c0() {
        com.android.tools.r8.shaking.R2 r2;
        if (Y1 || (r2 = this.h) == null || this.P0 == r2.w()) {
            return this.P0;
        }
        x1f.a();
        return false;
    }

    public boolean d() {
        return a(EnumC3077y2.r);
    }

    public final boolean d0() {
        return this.d != null && this.g.isOptimizedShrinking();
    }

    public final boolean e() {
        return a(EnumC3077y2.E);
    }

    public final boolean e0() {
        return this.h != null && H().x();
    }

    public final boolean f() {
        return a(EnumC3077y2.w);
    }

    public final boolean f0() {
        com.android.tools.r8.shaking.R2 r2;
        if (!this.Z0 && (r2 = this.h) != null) {
            n nVarK = r2.k();
            nVarK.getClass();
            if (!(nVarK == n.b) && (c0() || !this.V0)) {
                return true;
            }
        }
        return false;
    }

    public final boolean g() {
        return a(EnumC3077y2.z);
    }

    public final boolean g0() {
        com.android.tools.r8.shaking.R2 r2;
        if (Y1 || (r2 = this.h) == null || this.O0 == r2.B()) {
            return this.O0;
        }
        x1f.a();
        return false;
    }

    public final boolean h() {
        return Z() && this.x0.a(EnumC3077y2.w);
    }

    public final boolean h0() {
        return H().f().e || H().f().c;
    }

    public boolean i() {
        return b(EnumC3077y2.z);
    }

    public k i0() {
        return this.q1;
    }

    public final boolean j() {
        if (!Z()) {
            return false;
        }
        j jVar = this.y1;
        jVar.getClass();
        return jVar == j.c;
    }

    public final boolean j0() {
        return b0();
    }

    public final boolean k() {
        SourceFileProvider sourceFileProvider;
        if (j()) {
            return F().a(EnumC3077y2.B) && (sourceFileProvider = this.S1) != null && sourceFileProvider.allowDiscardingSourceFile();
        }
        return false;
    }

    public final void k0() {
        this.C1 = new ConcurrentHashMap();
    }

    public final boolean l() {
        return (b((EnumC3077y2) null) || this.m0) && !this.n0;
    }

    public final void l0() {
        boolean zE;
        if (this.A1.size() > 0) {
            this.i.info(new StringDiagnostic("Invalid parameter counts in MethodParameter attributes. This is likely due to Proguard having removed a parameter."));
            for (Origin origin : new TreeSet(this.A1.keySet())) {
                StringBuilder sb = new StringBuilder("Methods with invalid MethodParameter attributes:");
                for (C2837vB c2837vB : (List) this.A1.get(origin)) {
                    sb.append("\n  ");
                    sb.append(c2837vB.a);
                    sb.append(" expected count: ");
                    sb.append(c2837vB.b);
                    sb.append(" actual count: ");
                    sb.append(c2837vB.c);
                }
                this.i.info(new StringDiagnostic(sb.toString(), origin));
            }
        }
        if (this.B1.size() > 0) {
            Iterator it = this.B1.values().iterator();
            int size = 0;
            while (it.hasNext()) {
                size += ((List) it.next()).size();
            }
            this.i.info(new StringDiagnostic("Stripped invalid locals information from " + size + (size == 1 ? " method." : " methods.")));
            for (Origin origin2 : new TreeSet(this.B1.keySet())) {
                StringBuilder sb2 = new StringBuilder("Methods with invalid locals information:");
                for (C1405eW c1405eW : (List) this.B1.get(origin2)) {
                    sb2.append("\n  ");
                    sb2.append(((com.android.tools.r8.graph.B5) c1405eW.a()).v());
                    sb2.append("\n  ");
                    sb2.append((String) c1405eW.b());
                }
                this.i.info(new StringDiagnostic(sb2.toString(), origin2));
            }
            zE = true;
        } else {
            zE = false;
        }
        if (this.z1.size() > 0) {
            this.i.info(new StringDiagnostic("InnerClasses attribute has entries missing a corresponding EnclosingMethod attribute. Such InnerClasses attribute entries are ignored."));
            for (Origin origin3 : new TreeSet(this.z1.keySet())) {
                StringBuilder sb3 = new StringBuilder("Classes with missing EnclosingMethod: ");
                boolean z = true;
                for (C3178zB c3178zB : (List) this.z1.get(origin3)) {
                    if (z) {
                        z = false;
                    } else {
                        sb3.append(", ");
                    }
                    sb3.append(c3178zB.b);
                    zE |= c3178zB.a.e(C1159bb.f);
                }
                this.i.info(new StringDiagnostic(sb3.toString(), origin3));
            }
        }
        if (zE) {
            this.i.info(new StringDiagnostic("Some warnings are typically a sign of using an outdated Java toolchain. To fix, recompile the source with an updated toolchain."));
        }
    }

    public final boolean m() {
        return b(EnumC3077y2.w);
    }

    public o m0() {
        return this.l1;
    }

    public final boolean n() {
        return b(EnumC3077y2.z);
    }

    public p n0() {
        return this.b1;
    }

    public final boolean o() {
        return b(EnumC3077y2.D);
    }

    public final boolean o0() {
        return (!this.E0.a() || b((EnumC3077y2) null) || this.l0) ? false : true;
    }

    public final boolean p() {
        return this.a.d6 || b(EnumC3077y2.F);
    }

    public final boolean p0() {
        return this.E0.a() && !b(EnumC3077y2.I) && this.F0;
    }

    public e q() {
        return this.e1;
    }

    public final boolean q0() {
        return g0() || c0() || H().s();
    }

    public f r() {
        return this.p1;
    }

    public void r0() {
        ProgramConsumer programConsumer = this.j;
        if (programConsumer != null) {
            programConsumer.finished(this.i);
            DataResourceConsumer dataResourceConsumer = this.n;
            if (dataResourceConsumer != null) {
                dataResourceConsumer.finished(this.i);
            }
        }
        C0882Uo c0882Uo = this.o;
        if (c0882Uo != null) {
            Iterator it = c0882Uo.a.iterator();
            while (it.hasNext()) {
                ProgramConsumer programConsumer2 = ((FeatureSplit) it.next()).getProgramConsumer();
                if (programConsumer2 != null) {
                    programConsumer2.finished(this.i);
                    DataResourceConsumer dataResourceConsumer2 = programConsumer2.getDataResourceConsumer();
                    if (dataResourceConsumer2 != null) {
                        dataResourceConsumer2.finished(this.i);
                    }
                }
            }
        }
        DesugarGraphConsumer desugarGraphConsumer = this.P1;
        if (desugarGraphConsumer != null) {
            desugarGraphConsumer.finished();
        }
    }

    public com.android.tools.r8.graph.B1 s() {
        return this.a;
    }

    public final void t() {
        this.f1.b = false;
        this.E = false;
        this.B = false;
        this.C = false;
        this.Z.a = false;
        this.X = false;
        this.H = false;
        this.M = false;
        this.G = false;
        this.c1.a();
        this.i1.h();
        this.j1.a();
    }

    public void u() {
        this.K = false;
    }

    public R1 v() {
        return this.a1;
    }

    public C2226o4 w() {
        return this.r1;
    }

    public d x() {
        return this.d1;
    }

    public final ClassFileConsumer y() {
        return (ClassFileConsumer) this.j;
    }

    public final DexIndexedConsumer z() {
        return (DexIndexedConsumer) this.j;
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$c */
    public class c {
        public static final /* synthetic */ boolean e = true;
        public boolean a = true;
        public boolean b = true;
        public boolean c = false;

        public c() {
        }

        public void a() {
            this.a = false;
        }

        public c b(boolean z) {
            if (!z) {
                a();
                return this;
            }
            if (e) {
                return this;
            }
            if ((C2752uB.this.e0() && C2752uB.this.g0()) ? this.a : false) {
                return this;
            }
            x1f.a();
            return null;
        }

        public c c(boolean z) {
            this.c = z;
            return this;
        }

        public c a(boolean z) {
            this.b = z;
            return this;
        }
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$h */
    public class h {
        public boolean a;
        public boolean b;
        public boolean c;
        public boolean d;
        public boolean e;
        public boolean f;

        public h() {
            this.a = System.getProperty("com.android.tools.r8.disableHorizontalClassMerging") == null;
            this.b = true;
            this.c = System.getProperty("com.android.tools.r8.enableHorizontalInterfaceMerging") != null;
            this.d = System.getProperty("com.android.tools.r8.enableSameFilePolicy") != null;
            this.e = true;
            this.f = false;
        }

        public final boolean a(int i) {
            if (this.a) {
                C2752uB c2752uB = C2752uB.this;
                if (!c2752uB.Z0 && !c2752uB.y0) {
                    if (i != 0) {
                        return i != 1 || (c2752uB.e0() && C2752uB.this.g0());
                    }
                    throw null;
                }
            }
            return false;
        }

        public void b() {
            this.e = false;
        }

        public void c() {
            this.a = true;
        }

        public int d() {
            return 30;
        }

        public final boolean e() {
            return (!this.f && C2752uB.this.e0() && C2752uB.this.g0()) ? false : true;
        }

        public void f() {
            this.b = true;
        }

        public void g() {
            this.c = true;
        }

        public void h() {
            this.f = true;
        }

        public void b(boolean z) {
            this.c = z;
        }

        public void c(boolean z) {
            this.d = z;
        }

        public void a(boolean z) {
            this.a = z;
        }

        public void a() {
            this.a = false;
        }
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$i */
    public static class i {
        public boolean a = true;
        public boolean b = !Kg0.a("com.android.tools.r8.disableinlining", Kg0.a("com.android.tools.r8.disableinlining"), false);
        public int c;
        public boolean d;
        public int[] e;
        public int f;
        public int g;
        public final int h;
        public boolean i;
        public boolean j;
        public final boolean k;
        public b l;
        public final C2752uB m;

        public i(C2752uB c2752uB) {
            String strA = Kg0.a("com.android.tools.r8.inliningInstructionLimit");
            this.c = strA != null ? Integer.parseInt(strA) : -1;
            this.d = true;
            this.e = new int[]{Integer.MAX_VALUE, 28, 16, 12, 10};
            this.f = 1500;
            this.g = 4;
            this.h = 15;
            this.i = true;
            this.j = true;
            this.k = true;
            this.l = null;
            this.m = c2752uB;
        }

        public static void a(C2752uB c2752uB) {
            c2752uB.T().b = false;
        }

        public static void b(C2752uB c2752uB) {
            q qVar = c2752uB.u1;
            int i = AbstractC2554rv.c;
            qVar.k0 = W40.j;
        }

        public void a(boolean z) {
            this.a = z;
        }
    }

    public c c() {
        return this.c1;
    }

    public static /* synthetic */ List c(Origin origin) {
        return new ArrayList();
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$q */
    public static class q {
        public static final /* synthetic */ boolean b1 = true;
        public final Function A;
        public final boolean A0;
        public BiConsumer<C0333y<?>, C2039lt> B;
        public boolean B0;
        public Function<List<AbstractC1238cX>, List<AbstractC1238cX>> C;
        public boolean C0;
        public InterfaceC1938ki0<C0333y<?>, Iterable<com.android.tools.r8.graph.D2>, com.android.tools.r8.graph.D2, com.android.tools.r8.graph.D2> D;
        public Set<String> D0;
        public BiConsumer<com.android.tools.r8.graph.B1, AbstractC3345r0> E;
        public boolean E0;
        public BiConsumer<com.android.tools.r8.graph.B1, C2058m50> F;
        public final boolean F0;
        public BiConsumer<com.android.tools.r8.graph.B1, C2974wn> G;
        public final boolean G0;
        public BiConsumer<com.android.tools.r8.graph.B1, C1605gm0> H;
        public boolean H0;
        public Consumer<Deque<UY>> I;
        public boolean I0;
        public Consumer<com.android.tools.r8.graph.D2> J;
        public boolean J0;
        public boolean K;
        public boolean K0;
        public boolean L;
        public boolean L0;
        public boolean M;
        public boolean M0;
        public boolean N;
        public boolean N0;
        public boolean O;
        public boolean O0;
        public boolean P;
        public boolean P0;
        public boolean Q;
        public boolean Q0;
        public boolean R;
        public int R0;
        public boolean S;
        public a S0;
        public boolean T;
        public Runnable T0;
        public boolean U;
        public boolean U0;
        public boolean V;
        public b V0;
        public boolean W;
        public Consumer<com.android.tools.r8.graph.B5> W0;
        public boolean X;
        public Predicate<C0322w2> X0;
        public boolean Y;
        public boolean Y0;
        public boolean Z;
        public boolean Z0;
        public boolean a0;
        public boolean b0;
        public boolean c0;
        public boolean d0;
        public boolean e0;
        public boolean f0;
        public boolean g0;
        public boolean h0;
        public boolean i0;
        public final int j0;
        public Set<com.android.tools.r8.ir.optimize.T> k0;
        public boolean l0;
        public boolean m0;
        public boolean n;
        public boolean n0;
        public boolean o;
        public BiConsumer<C0705Nt, C0333y<?>> o0;
        public final boolean p;
        public Consumer<C0705Nt> p0;
        public boolean q;
        public int q0;
        public boolean r;
        public boolean r0;
        public final boolean s;
        public boolean s0;
        public InterfaceC2053m3 t;
        public boolean t0;
        public Predicate<com.android.tools.r8.graph.D2> u;
        public boolean u0;
        public byte[] v;
        public boolean v0;
        public final InterfaceC0965Xt w;
        public boolean w0;
        public BiFunction x;
        public int x0;
        public BiConsumer<C3403i, com.android.tools.r8.shaking.M.a> y;
        public boolean y0;
        public Consumer<String> z;
        public boolean z0;
        public boolean a = false;
        public boolean b = false;
        public boolean c = false;
        public boolean d = false;
        public boolean e = false;
        public int a1 = 1;
        public boolean f = true;
        public boolean g = false;
        public boolean h = true;
        public boolean i = false;
        public boolean j = false;
        public boolean k = false;
        public C1858jl l = null;
        public boolean m = false;

        /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$q$b */
        public static class b {
            public int a = 0;
            public int b = 0;
        }

        public q() {
            this.n = System.getProperty("com.android.tools.r8.dexVersion40ForApiLevel30") != null;
            this.o = System.getProperty("com.android.tools.r8.dexContainerExperiment") != null;
            this.p = System.getProperty("com.android.tools.r8.nullOutDebugInfo") != null;
            this.q = true;
            this.r = false;
            this.s = System.getProperty("com.android.tools.r8.testing.forceThrowInConvert") != null;
            this.t = new C1881k3();
            this.u = MX.b;
            this.v = null;
            this.w = (!C2752uB.b() || C2752uB.V1) ? C0913Vt.a : C0939Wt.a;
            this.x = new BiFunction() { // from class: agi
                @Override // java.util.function.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return C2752uB.q.a((Y) obj, (t0) obj2);
                }
            };
            this.y = null;
            this.z = null;
            this.A = new Function() { // from class: bgi
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return new C1290d50((C0333y) obj);
                }
            };
            this.B = C0822Sg.a();
            this.C = Function.identity();
            this.D = new InterfaceC1938ki0() { // from class: cgi
                @Override // com.android.tools.r8.internal.InterfaceC1938ki0
                public final Object a(Object obj, Object obj2, Object obj3) {
                    return C2752uB.q.a((C0333y) obj, (Iterable) obj2, (D2) obj3);
                }
            };
            this.E = C0822Sg.a();
            this.F = C0822Sg.a();
            this.G = C0822Sg.a();
            this.H = C0822Sg.a();
            this.I = new Consumer() { // from class: dgi
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    C2752uB.q.a((Deque) obj);
                }
            };
            this.J = null;
            this.K = true;
            this.L = false;
            this.M = true;
            this.N = true;
            this.O = true;
            this.P = (Version.isDevelopmentVersion() && System.getProperty("com.android.tools.r8.allowTypeErrors") == null) ? false : true;
            this.Q = false;
            this.R = true;
            this.S = true;
            this.T = true;
            this.U = false;
            this.V = true;
            this.W = true;
            this.X = true;
            this.Y = false;
            this.Z = false;
            this.a0 = true;
            this.b0 = true;
            this.c0 = System.getProperty("com.android.tools.r8.enableEnumUnboxingDebugLogs") != null;
            this.d0 = false;
            this.e0 = false;
            this.f0 = false;
            this.g0 = false;
            this.h0 = false;
            this.i0 = false;
            this.j0 = System.getProperty("com.android.tools.r8.pc2pcOverheadThreshold") != null ? Integer.parseInt(System.getProperty("com.android.tools.r8.pc2pcOverheadThreshold")) : 200000;
            this.k0 = null;
            this.l0 = false;
            this.m0 = false;
            this.n0 = false;
            this.o0 = null;
            this.p0 = null;
            this.q0 = -1;
            this.r0 = System.getProperty("com.android.tools.r8.testing.dontReportFailingCheckDiscarded") != null;
            this.s0 = false;
            this.t0 = System.getProperty("com.android.tools.r8.trackDesugaredAPIConversions") != null;
            this.u0 = false;
            this.v0 = false;
            this.w0 = false;
            this.x0 = -1;
            this.y0 = System.getProperty("com.android.tools.r8.forceIRForCfToCfDesugar") != null;
            this.z0 = false;
            this.A0 = System.getProperty("com.android.tools.r8.allowInvalidCfAccessFlags") != null;
            this.B0 = System.getProperty("com.android.tools.r8.verifyInputs") != null;
            this.C0 = false;
            this.D0 = new HashSet();
            this.E0 = System.getProperty("com.android.tools.r8.enableTestAssertions") != null;
            this.F0 = System.getProperty("com.android.tools.r8.disableMarkingMethodsFinal") != null;
            this.G0 = System.getProperty("com.android.tools.r8.disableMarkingClassesFinal") != null;
            this.H0 = false;
            this.I0 = true;
            this.J0 = false;
            this.K0 = false;
            this.L0 = true;
            this.M0 = false;
            this.N0 = false;
            this.O0 = false;
            this.P0 = false;
            this.Q0 = false;
            this.R0 = -1;
            this.S0 = new a();
            this.T0 = null;
            this.U0 = false;
            this.V0 = new b();
            this.W0 = C0822Sg.b();
            this.X0 = null;
            this.Y0 = false;
            this.Z0 = false;
        }

        public final void a(InterfaceC1936kh0 interfaceC1936kh0) {
            if (this.l == null && !this.k) {
                this.k = true;
                String property = System.getProperty("com.android.tools.r8.checkdeterminism");
                if (property != null) {
                    a(C1858jl.a(Paths.get(property, new String[0])));
                }
            }
            C1858jl c1858jl = this.l;
            if (c1858jl != null) {
                interfaceC1936kh0.a(c1858jl);
            }
        }

        public final void b(C0333y c0333y) {
            if (this.l == null && !this.k) {
                this.k = true;
                String property = System.getProperty("com.android.tools.r8.checkdeterminism");
                if (property != null) {
                    a(C1858jl.a(Paths.get(property, new String[0])));
                }
            }
            C1858jl c1858jl = this.l;
            if (c1858jl != null) {
                c1858jl.a(c0333y);
            }
        }

        public final boolean c() {
            return this.a1 == 1;
        }

        public final boolean d() {
            return this.a1 == 2;
        }

        public void e() {
            if (b1 || this.a1 == 1) {
                this.a1 = 3;
            } else {
                x1f.a();
            }
        }

        /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$q$a */
        public static class a {
            public Comparator<C0322w2> a = null;

            public final /* synthetic */ int a(com.android.tools.r8.graph.H0 h0, com.android.tools.r8.graph.H0 h1) {
                return this.a.compare(h0.getReference(), h1.getReference());
            }

            public final Comparator a(Comparator comparator) {
                return this.a != null ? new Comparator() { // from class: egi
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return this.b.a((H0) obj, (H0) obj2);
                    }
                } : comparator;
            }
        }

        public static /* synthetic */ com.android.tools.r8.graph.D2 a(C0333y c0333y, Iterable iterable, com.android.tools.r8.graph.D2 d2) {
            return d2;
        }

        public final boolean b() {
            return this.a || this.b;
        }

        public static /* synthetic */ void a(Deque deque) {
        }

        public static boolean a(C0333y c0333y) {
            return c0333y.o();
        }

        public final void a() {
            if (b1 || d()) {
                this.a1 = 3;
            } else {
                x1f.a();
            }
        }

        public static /* synthetic */ com.android.tools.r8.dex.Y a(com.android.tools.r8.dex.Y y, com.android.tools.r8.dex.t0 t0Var) {
            return y;
        }

        public void a(C1858jl c1858jl) {
            this.l = c1858jl;
        }

        public void a(BiFunction<com.android.tools.r8.dex.Y, com.android.tools.r8.dex.t0, com.android.tools.r8.dex.Y> biFunction) {
            this.x = biFunction;
        }
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$l */
    public static class l {
        public static final /* synthetic */ boolean c = true;
        public boolean a = true;
        public final ArrayList b = new ArrayList();

        public static /* synthetic */ boolean d(C0333y c0333y, AbstractC2624sj0 abstractC2624sj0, com.android.tools.r8.graph.E0 e0) {
            return abstractC2624sj0.w() && abstractC2624sj0.b().Q().H0().equals("java.util.zip.ZipFile") && e0.e1().equals("java.lang.AutoCloseable");
        }

        public final boolean a(final C0333y c0333y, final AbstractC2624sj0 abstractC2624sj0, final com.android.tools.r8.graph.E0 e0) {
            return this.a || this.b.stream().anyMatch(new Predicate() { // from class: vfi
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((InterfaceC3093yB) obj).a(c0333y, abstractC2624sj0, e0);
                }
            });
        }

        public void b() {
            if (c || !this.a) {
                this.b.add(new InterfaceC3093yB() { // from class: wfi
                    @Override // com.android.tools.r8.internal.InterfaceC3093yB
                    public final boolean a(C0333y c0333y, AbstractC2624sj0 abstractC2624sj0, E0 e0) {
                        return C2752uB.l.b(c0333y, abstractC2624sj0, e0);
                    }
                });
            } else {
                x1f.a();
            }
        }

        public void c() {
            if (c || !this.a) {
                this.b.add(new InterfaceC3093yB() { // from class: xfi
                    @Override // com.android.tools.r8.internal.InterfaceC3093yB
                    public final boolean a(C0333y c0333y, AbstractC2624sj0 abstractC2624sj0, E0 e0) {
                        return abstractC2624sj0.a(c0333y);
                    }
                });
            } else {
                x1f.a();
            }
        }

        public static /* synthetic */ boolean b(C0333y c0333y, AbstractC2624sj0 abstractC2624sj0, com.android.tools.r8.graph.E0 e0) {
            return true;
        }

        public l a(final ClassReference classReference) {
            if (c || !this.a) {
                this.b.add(new InterfaceC3093yB() { // from class: zfi
                    @Override // com.android.tools.r8.internal.InterfaceC3093yB
                    public final boolean a(C0333y c0333y, AbstractC2624sj0 abstractC2624sj0, E0 e0) {
                        return e0.e1().equals(classReference.getTypeName());
                    }
                });
                return this;
            }
            x1f.a();
            return null;
        }

        public void a() {
            this.a = false;
        }

        public void d() {
            if (c || !this.a) {
                this.b.add(new InterfaceC3093yB() { // from class: yfi
                    @Override // com.android.tools.r8.internal.InterfaceC3093yB
                    public final boolean a(C0333y c0333y, AbstractC2624sj0 abstractC2624sj0, E0 e0) {
                        return C2752uB.l.d(c0333y, abstractC2624sj0, e0);
                    }
                });
            } else {
                x1f.a();
            }
        }
    }

    public final void b(final InterfaceC0852Tk interfaceC0852Tk, final String str) {
        if (interfaceC0852Tk.isEmpty()) {
            return;
        }
        this.J1 = new InterfaceC1766ih0() { // from class: lfi
            @Override // com.android.tools.r8.internal.InterfaceC1766ih0
            public final void accept(Object obj, Object obj2) throws IOException {
                this.a.a(interfaceC0852Tk, str, (Ch0) obj, (AbstractC0327x0) obj2);
            }
        };
    }

    public static boolean b() {
        return !Y1;
    }

    public static /* synthetic */ List b(Origin origin) {
        return new ArrayList();
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.uB$a */
    public static class a {
        public boolean a;
        public boolean b;
        public boolean c;
        public boolean d;
        public boolean e;
        public final boolean f;
        public Map<MethodReference, EnumC3077y2> g;
        public Map<FieldReference, EnumC3077y2> h;
        public Map<ClassReference, EnumC3077y2> i;
        public BiConsumer<MethodReference, com.android.tools.r8.androidapi.f> j;

        public a() {
            this.a = System.getProperty("com.android.tools.r8.disableApiModeling") == null;
            this.b = System.getProperty("com.android.tools.r8.disableApiModeling") == null;
            this.c = System.getProperty("com.android.tools.r8.disableApiModeling") == null;
            this.d = System.getProperty("com.android.tools.r8.disableApiModeling") == null;
            this.e = System.getProperty("com.android.tools.r8.disableApiModeling") == null;
            this.f = System.getProperty("com.android.tools.r8.reportUnknownApiReferences") != null;
            this.g = new HashMap();
            this.h = new HashMap();
            this.i = new HashMap();
            this.j = null;
        }

        public final void a(final com.android.tools.r8.graph.B1 b1, final BiConsumer biConsumer) {
            if (this.g.isEmpty() && this.h.isEmpty() && this.i.isEmpty()) {
                return;
            }
            this.i.forEach(new BiConsumer() { // from class: sfi
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    biConsumer.accept(b1.e(((ClassReference) obj).getDescriptor()), (EnumC3077y2) obj2);
                }
            });
            this.h.forEach(new BiConsumer() { // from class: tfi
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    biConsumer.accept(b1.a((FieldReference) obj), (EnumC3077y2) obj2);
                }
            });
            this.g.forEach(new BiConsumer() { // from class: ufi
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    biConsumer.accept(b1.a((MethodReference) obj), (EnumC3077y2) obj2);
                }
            });
        }

        public void b() {
            this.a = false;
            this.b = false;
            this.e = false;
            this.d = false;
            this.c = false;
        }

        public void c() {
            this.e = false;
            this.d = false;
        }

        public boolean d() {
            return this.a && this.b;
        }

        public void a() {
            this.b = false;
        }
    }

    public final void b(final com.android.tools.r8.graph.D2 d2, final C0281q2 c0281q2, com.android.tools.r8.graph.I2 i2) {
        ConcurrentHashMap concurrentHashMap = this.C1;
        if (concurrentHashMap != null) {
            concurrentHashMap.computeIfAbsent(i2, new Function() { // from class: nfi
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return C2752uB.a(d2, c0281q2, (I2) obj);
                }
            });
        }
    }

    public void a(com.android.tools.r8.dex.W w) {
        this.s0 = true;
        this.t0 = w;
    }

    public void a(AbstractC2972wm abstractC2972wm) {
        this.p0 = abstractC2972wm;
    }

    public final boolean a(com.android.tools.r8.shaking.M.a aVar) {
        if (Y1 || aVar.b()) {
            return this.i1.a(1) && !this.i1.e();
        }
        x1f.a();
        return false;
    }

    public a a() {
        return this.o1;
    }

    public static /* synthetic */ C1405eW a(com.android.tools.r8.graph.D2 d2, C0281q2 c0281q2, com.android.tools.r8.graph.I2 i2) {
        return new C1405eW(d2, c0281q2);
    }

    public final void a(C0333y c0333y) {
        if (!Y1 && this.C1 == null) {
            x1f.a();
            return;
        }
        if (this.C1.isEmpty()) {
            this.C1 = null;
            return;
        }
        for (com.android.tools.r8.graph.I2 i2 : C2847vL.a(this.C1.keySet(), new jfh())) {
            if (com.android.tools.r8.graph.D2.b(((C3403i) c0333y.g()).c(i2)) != null) {
                C1405eW c1405eW = (C1405eW) this.C1.get(i2);
                C2742u50 c2742u50 = this.i;
                DuplicateTypeInProgramAndLibraryDiagnostic duplicateTypeInProgramAndLibraryDiagnostic = new DuplicateTypeInProgramAndLibraryDiagnostic(i2.w0(), ((com.android.tools.r8.graph.D2) c1405eW.a()).d, ((C0281q2) c1405eW.b()).d);
                synchronized (c2742u50) {
                    c2742u50.a(DiagnosticsLevel.INFO, duplicateTypeInProgramAndLibraryDiagnostic);
                }
            }
        }
        this.C1 = null;
        this.i.a();
    }

    public final void a(InterfaceC0852Tk interfaceC0852Tk, String str) {
        boolean z = Y1;
        if (!z && str == null) {
            x1f.a();
            return;
        }
        if (!z && interfaceC0852Tk == null) {
            x1f.a();
            return;
        }
        boolean zIsEmpty = str.isEmpty();
        String str2 = XmlPullParser.NO_NAMESPACE;
        if (zIsEmpty) {
            str = System.getProperty("com.android.tools.r8.synthesizedClassPrefix", XmlPullParser.NO_NAMESPACE);
        }
        String property = System.getProperty("com.android.tools.r8.desugaredLibraryPostPrefix", null);
        b(interfaceC0852Tk, property);
        String strO = property == null ? XmlPullParser.NO_NAMESPACE : C0929Wj.o(property);
        if (!str.isEmpty()) {
            str2 = str + strO;
        }
        this.f0 = str2;
    }

    public void a(InterfaceC0852Tk interfaceC0852Tk) {
        b(interfaceC0852Tk, null);
    }

    public final /* synthetic */ void a(InterfaceC0852Tk interfaceC0852Tk, String str, Ch0 ch0, AbstractC0327x0 abstractC0327x0) throws IOException {
        C2592sM c2592sMA = interfaceC0852Tk.a(abstractC0327x0, ch0);
        if (str != null) {
            c2592sMA = c2592sMA.a(str, s());
        }
        this.K1 = c2592sMA;
    }

    public void a(Ch0 ch0, AbstractC0327x0 abstractC0327x0) throws Throwable {
        if (this.J1 == null) {
            return;
        }
        ch0.a("Load machine specification");
        this.J1.accept(ch0, abstractC0327x0);
        ch0.b();
    }

    public static String a(com.android.tools.r8.graph.E0 e0) {
        String strB0 = e0.W0().B0();
        return "Class " + e0.e.B0() + " requires its nest host " + strB0 + " to be on program or class path.";
    }

    public static /* synthetic */ boolean a(String str, Long l2) {
        return true;
    }

    public final void a(Origin origin, com.android.tools.r8.graph.I2 i2, com.android.tools.r8.graph.I2 i3, String str, Set set) {
        if (this.U1.add(i3)) {
            this.i.warning(new InvalidLibrarySuperclassDiagnostic(origin, Reference.classFromDescriptor(i2.Z0()), Reference.classFromDescriptor(i3.Z0()), str, AL.a(AbstractC3179zC.a(set, new InterfaceC0392Br() { // from class: jfi
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((C0322w2) obj).z0();
                }
            }))));
        }
    }

    public final void a(com.android.tools.r8.graph.I2 i2, Origin origin, C1159bb c1159bb) {
        C3178zB c3178zB = new C3178zB(c1159bb, i2);
        synchronized (this.z1) {
            ((List) this.z1.computeIfAbsent(origin, new Function() { // from class: ifi
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return C2752uB.c((Origin) obj);
                }
            })).add(c3178zB);
        }
    }

    public final void a(C0322w2 c0322w2, Origin origin, int i2, int i3) {
        C2837vB c2837vB = new C2837vB(i2, i3, c0322w2);
        synchronized (this.A1) {
            ((List) this.A1.computeIfAbsent(origin, new Function() { // from class: kfi
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return C2752uB.b((Origin) obj);
                }
            })).add(c2837vB);
        }
    }

    public final void a(com.android.tools.r8.graph.B5 b5, NB nb) {
        if (!this.h0) {
            synchronized (this.B1) {
                ((List) this.B1.computeIfAbsent(b5.b.d, new Function() { // from class: efi
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return C2752uB.a((Origin) obj);
                    }
                })).add(new C1405eW(b5, nb.getMessage()));
            }
            return;
        }
        throw new C0613Ke("Fatal warning: Invalid debug info", nb);
    }

    public static /* synthetic */ List a(Origin origin) {
        return new ArrayList();
    }

    public final boolean a(C0231j1 c0231j1) {
        if (!R()) {
            return true;
        }
        return this.w0.contains(c0231j1.D1());
    }

    public final boolean a(EnumC3077y2 enumC3077y2) {
        if (this.E0.a() || Z()) {
            return enumC3077y2 == null || !F().a(enumC3077y2);
        }
        if (Y1 || this.x0.equals(EnumC3077y2.c)) {
            return true;
        }
        x1f.a();
        return false;
    }

    public final C1159bb a(C1159bb c1159bb) {
        if (!Y1 && !(this.j instanceof ClassFileConsumer)) {
            x1f.a();
            return null;
        }
        if (this.E0.a()) {
            C1159bb c1159bb2 = i() ? C1159bb.i : C1159bb.h;
            if (c1159bb2.e(c1159bb)) {
                return c1159bb2;
            }
        }
        return c1159bb;
    }

    public C2752uB(com.android.tools.r8.graph.B1 b1, C2742u50 c2742u50) {
        this.r = System.getProperty("com.android.tools.r8.printtimes") != null;
        this.s = System.getProperty("com.android.tools.r8.printmemory") != null;
        this.t = false;
        this.u = true;
        int i2 = AbstractC0551Hu.c;
        this.v = new C3009xB(new Bc0("j$."), new Bc0("java."));
        this.w = false;
        this.x = System.getProperty("com.android.tools.r8.fieldBitAccessAnalysis") != null;
        this.y = true;
        this.z = true;
        this.A = true;
        this.B = true;
        this.C = true;
        this.D = true;
        this.E = true;
        this.F = true;
        this.G = true;
        this.H = true;
        this.I = true;
        this.J = true;
        this.K = true;
        this.L = true;
        this.M = false;
        this.N = false;
        this.O = new BiPredicate() { // from class: mfi
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return C2752uB.a((String) obj, (Long) obj2);
            }
        };
        this.P = false;
        this.Q = false;
        this.R = System.getProperty("com.android.tools.r8.disableL8AnnotationRemoval") != null;
        this.S = 50;
        this.T = Thread.currentThread();
        this.U = true;
        this.V = true;
        this.W = 3;
        this.X = true;
        this.Y = true;
        this.Z = new m();
        this.a0 = true;
        this.b0 = true;
        this.c0 = false;
        this.d0 = true;
        this.e0 = false;
        this.f0 = XmlPullParser.NO_NAMESPACE;
        this.g0 = V1 ? 1 : -1;
        this.h0 = false;
        this.i0 = System.getProperty("com.android.tools.r8.strictdebuginfo") != null;
        this.j0 = false;
        this.k0 = false;
        this.l0 = System.getProperty("com.android.tools.r8.emitRecordAnnotationsInDex") != null;
        this.m0 = System.getProperty("com.android.tools.r8.emitNestAnnotationsInDex") != null;
        this.n0 = System.getProperty("com.android.tools.r8.forceNestDesugaring") != null;
        this.o0 = System.getProperty("com.android.tools.r8.emitPermittedSubclassesAnnotationsInDex") != null;
        this.p0 = AbstractC2972wm.a();
        this.r0 = null;
        this.s0 = false;
        this.u0 = B();
        this.v0 = A();
        P40 p40 = P40.e;
        this.w0 = p40;
        this.x0 = EnumC3077y2.b();
        this.y0 = false;
        this.z0 = false;
        this.A0 = true;
        this.B0 = System.getProperty("com.android.tools.r8.ignoreBootClasspathEnumsForMaindexTracing") != null;
        this.C0 = System.getProperty("com.android.tools.r8.pruneNonVissibleAnnotationClasses") != null;
        this.D0 = true;
        this.E0 = g.c;
        this.F0 = false;
        this.G0 = false;
        this.H0 = true;
        EnumC1066aV enumC1066aV = EnumC1066aV.c;
        this.I0 = enumC1066aV;
        this.J0 = enumC1066aV;
        this.K0 = true;
        this.L0 = System.getProperty("com.android.tools.r8.disableEnqueuerDeferredTracing") == null;
        this.M0 = false;
        this.N0 = true;
        this.Q0 = false;
        this.R0 = false;
        this.S0 = false;
        this.T0 = false;
        this.U0 = false;
        this.V0 = false;
        this.W0 = null;
        this.X0 = false;
        this.Y0 = false;
        this.Z0 = false;
        this.a1 = new R1(this);
        this.b1 = new p();
        this.c1 = new c();
        this.d1 = new d();
        this.e1 = new e();
        this.f1 = new i(this);
        this.g1 = new C1585gc0(this);
        this.h1 = new C1218cD(this);
        this.i1 = new h();
        this.j1 = new Wl0(this);
        this.k1 = new l();
        this.l1 = new o();
        this.m1 = new C20();
        this.n1 = new C2923wB();
        this.o1 = new a();
        this.p1 = new f();
        this.q1 = new k();
        this.r1 = new C2226o4(this);
        this.s1 = new Ud0();
        this.t1 = new Sd0();
        this.u1 = new q();
        this.v1 = p40;
        this.x1 = true;
        this.y1 = j.c;
        this.z1 = new HashMap();
        this.A1 = new HashMap();
        this.B1 = new HashMap();
        this.D1 = false;
        this.E1 = null;
        this.F1 = null;
        this.G1 = null;
        this.H1 = null;
        this.I1 = null;
        this.J1 = null;
        this.K1 = C2592sM.g();
        this.L1 = false;
        this.M1 = null;
        this.N1 = null;
        this.O1 = null;
        this.P1 = null;
        this.Q1 = null;
        this.R1 = null;
        this.S1 = null;
        this.T1 = C1755ib0.a();
        new AtomicBoolean(false);
        this.U1 = C1755ib0.a();
        boolean z = Y1;
        if (!z && c2742u50 == null) {
            x1f.a();
            throw null;
        }
        if (!z && b1 == null) {
            x1f.a();
            throw null;
        }
        this.i = c2742u50;
        this.a = b1;
        this.h = null;
        this.O0 = false;
        this.P0 = false;
        t();
    }

    public C2752uB() {
        this.r = System.getProperty("com.android.tools.r8.printtimes") != null;
        this.s = System.getProperty("com.android.tools.r8.printmemory") != null;
        this.t = false;
        this.u = true;
        int i2 = AbstractC0551Hu.c;
        this.v = new C3009xB(new Bc0("j$."), new Bc0("java."));
        this.w = false;
        this.x = System.getProperty("com.android.tools.r8.fieldBitAccessAnalysis") != null;
        this.y = true;
        this.z = true;
        this.A = true;
        this.B = true;
        this.C = true;
        this.D = true;
        this.E = true;
        this.F = true;
        this.G = true;
        this.H = true;
        this.I = true;
        this.J = true;
        this.K = true;
        this.L = true;
        this.M = false;
        this.N = false;
        this.O = new BiPredicate() { // from class: mfi
            @Override // java.util.function.BiPredicate
            public final boolean test(Object obj, Object obj2) {
                return C2752uB.a((String) obj, (Long) obj2);
            }
        };
        this.P = false;
        this.Q = false;
        this.R = System.getProperty("com.android.tools.r8.disableL8AnnotationRemoval") != null;
        this.S = 50;
        this.T = Thread.currentThread();
        this.U = true;
        this.V = true;
        this.W = 3;
        this.X = true;
        this.Y = true;
        this.Z = new m();
        this.a0 = true;
        this.b0 = true;
        this.c0 = false;
        this.d0 = true;
        this.e0 = false;
        this.f0 = XmlPullParser.NO_NAMESPACE;
        this.g0 = V1 ? 1 : -1;
        this.h0 = false;
        this.i0 = System.getProperty("com.android.tools.r8.strictdebuginfo") != null;
        this.j0 = false;
        this.k0 = false;
        this.l0 = System.getProperty("com.android.tools.r8.emitRecordAnnotationsInDex") != null;
        this.m0 = System.getProperty("com.android.tools.r8.emitNestAnnotationsInDex") != null;
        this.n0 = System.getProperty("com.android.tools.r8.forceNestDesugaring") != null;
        this.o0 = System.getProperty("com.android.tools.r8.emitPermittedSubclassesAnnotationsInDex") != null;
        this.p0 = AbstractC2972wm.a();
        this.r0 = null;
        this.s0 = false;
        this.u0 = B();
        this.v0 = A();
        P40 p40 = P40.e;
        this.w0 = p40;
        this.x0 = EnumC3077y2.b();
        this.y0 = false;
        this.z0 = false;
        this.A0 = true;
        this.B0 = System.getProperty("com.android.tools.r8.ignoreBootClasspathEnumsForMaindexTracing") != null;
        this.C0 = System.getProperty("com.android.tools.r8.pruneNonVissibleAnnotationClasses") != null;
        this.D0 = true;
        this.E0 = g.c;
        this.F0 = false;
        this.G0 = false;
        this.H0 = true;
        EnumC1066aV enumC1066aV = EnumC1066aV.c;
        this.I0 = enumC1066aV;
        this.J0 = enumC1066aV;
        this.K0 = true;
        this.L0 = System.getProperty("com.android.tools.r8.disableEnqueuerDeferredTracing") == null;
        this.M0 = false;
        this.N0 = true;
        this.Q0 = false;
        this.R0 = false;
        this.S0 = false;
        this.T0 = false;
        this.U0 = false;
        this.V0 = false;
        this.W0 = null;
        this.X0 = false;
        this.Y0 = false;
        this.Z0 = false;
        this.a1 = new R1(this);
        this.b1 = new p();
        this.c1 = new c();
        this.d1 = new d();
        this.e1 = new e();
        this.f1 = new i(this);
        this.g1 = new C1585gc0(this);
        this.h1 = new C1218cD(this);
        this.i1 = new h();
        this.j1 = new Wl0(this);
        this.k1 = new l();
        this.l1 = new o();
        this.m1 = new C20();
        this.n1 = new C2923wB();
        this.o1 = new a();
        this.p1 = new f();
        this.q1 = new k();
        this.r1 = new C2226o4(this);
        this.s1 = new Ud0();
        this.t1 = new Sd0();
        this.u1 = new q();
        this.v1 = p40;
        this.x1 = true;
        this.y1 = j.c;
        this.z1 = new HashMap();
        this.A1 = new HashMap();
        this.B1 = new HashMap();
        this.D1 = false;
        this.E1 = null;
        this.F1 = null;
        this.G1 = null;
        this.H1 = null;
        this.I1 = null;
        this.J1 = null;
        this.K1 = C2592sM.g();
        this.L1 = false;
        this.M1 = null;
        this.N1 = null;
        this.O1 = null;
        this.P1 = null;
        this.Q1 = null;
        this.R1 = null;
        this.S1 = null;
        this.T1 = C1755ib0.a();
        new AtomicBoolean(false);
        this.U1 = C1755ib0.a();
        this.i = new C2742u50();
        this.a = new com.android.tools.r8.graph.B1();
        this.h = null;
        this.O0 = false;
        this.P0 = false;
    }
}
