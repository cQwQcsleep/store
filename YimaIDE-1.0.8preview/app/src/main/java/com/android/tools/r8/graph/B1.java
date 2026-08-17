package com.android.tools.r8.graph;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.E2;
import com.android.tools.r8.graph.H2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.N0;
import com.android.tools.r8.graph.O0;
import com.android.tools.r8.graph.P0;
import com.android.tools.r8.graph.Q0;
import com.android.tools.r8.graph.S0;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC0706Nu;
import com.android.tools.r8.internal.AbstractC1120b40;
import com.android.tools.r8.internal.AbstractC2004lX;
import com.android.tools.r8.internal.AbstractC2005lY;
import com.android.tools.r8.internal.AbstractC2173nV;
import com.android.tools.r8.internal.AbstractC2554rv;
import com.android.tools.r8.internal.AbstractC2624sj0;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.internal.Bc0;
import com.android.tools.r8.internal.C0473Eu;
import com.android.tools.r8.internal.C0629Ku;
import com.android.tools.r8.internal.C0756Ps;
import com.android.tools.r8.internal.C0784Qu;
import com.android.tools.r8.internal.C0822Sg;
import com.android.tools.r8.internal.C0860Ts;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C1022Zy;
import com.android.tools.r8.internal.C1311dN;
import com.android.tools.r8.internal.C1870jv;
import com.android.tools.r8.internal.C2427qS;
import com.android.tools.r8.internal.C2441qd;
import com.android.tools.r8.internal.C2847vL;
import com.android.tools.r8.internal.C2986wz;
import com.android.tools.r8.internal.DX;
import com.android.tools.r8.internal.IA;
import com.android.tools.r8.internal.InterfaceC2762uL;
import com.android.tools.r8.internal.Q40;
import com.android.tools.r8.internal.T40;
import com.android.tools.r8.internal.VI;
import com.android.tools.r8.internal.XC;
import com.android.tools.r8.kotlin.C3289g;
import com.android.tools.r8.references.ClassReference;
import com.android.tools.r8.references.FieldReference;
import com.android.tools.r8.references.MethodReference;
import com.android.tools.r8.references.TypeReference;
import com.reandroid.arsc.chunk.TypeBlock;
import defpackage.gk0;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class B1 {
    public static final I2 e6;
    public static final H2 f6;
    public static final IdentityHashMap g6;
    public static final AbstractC0551Hu h6;
    public static final AbstractC0551Hu i6;
    public static final AbstractC0551Hu j6;
    public static final /* synthetic */ boolean k6 = true;
    public final H2 A;
    public final H2 A0;
    public final I2 A1;
    public final I2 A2;
    public final I2 A3;
    public final C0183c2 A4;
    public final I2 A5;
    public final H2 B;
    public final H2 B0;
    public final I2 B1;
    public final I2 B2;
    public final I2 B3;
    public final K1 B4;
    public final I2 B5;
    public final H2 C;
    public final H2 C0;
    public final I2 C1;
    public final I2 C2;
    public final I2 C3;
    public final R1 C4;
    public final I2 C5;
    public final H2 D;
    public final H2 D0;
    public final I2 D1;
    public final I2 D2;
    public final I2 D3;
    public final Q1 D4;
    public final I2 D5;
    public final H2 E;
    public final H2 E0;
    public final I2 E1;
    public final I2 E2;
    public final I2 E3;
    public final S1 E4;
    public final I2 E5;
    public final H2 F;
    public final H2 F0;
    public final I2 F1;
    public final I2 F2;
    public final I2 F3;
    public final C0190d2 F4;
    public final I2 F5;
    public final H2 G;
    public final H2 G0;
    public final I2 G1;
    public final I2 G2;
    public final I2 G3;
    public final N1 G4;
    public final I2 G5;
    public final H2 H;
    public final H2 H0;
    public final I2 H1;
    public final I2 H2;
    public final I2 H3;
    public final C0218h2 H4;
    public final I2 H5;
    public final H2 I;
    public final H2 I0;
    public final I2 I1;
    public final I2 I2;
    public final I2 I3;
    public final A1 I4;
    public final I2 I5;
    public final H2 J;
    public final H2 J0;
    public final I2 J1;
    public final I2 J2;
    public final I2 J3;
    public final C3289g J4;
    public final I2 J5;
    public final H2 K;
    public final H2 K0;
    public final I2 K1;
    public final I2 K2;
    public final I2 K3;
    public final C0211g2 K4;
    public final I2 K5;
    public final H2 L;
    public final H2 L0;
    public final I2 L1;
    public final I2 L2;
    public final I2 L3;
    public final C0225i2 L4;
    public final I2 L5;
    public final H2 M;
    public final H2 M0;
    public final I2 M1;
    public final I2 M2;
    public final I2 M3;
    public final C0307u1 M4;
    public final I2 M5;
    public final H2 N;
    public final H2 N0;
    public final I2 N1;
    public final I2 N2;
    public final I2 N3;
    public final C0328x1 N4;
    public final I2 N5;
    public final H2 O;
    public final H2 O0;
    public final I2 O1;
    public final I2 O2;
    public final I2 O3;
    public final C0273p1 O4;
    public final C0197e2 O5;
    public final H2 P;
    public final H2 P0;
    public final I2 P1;
    public final I2 P2;
    public final I2 P3;
    public final C0266o1 P4;
    public final C0239k2 P5;
    public final H2 Q;
    public final H2 Q0;
    public final I2 Q1;
    public final I2 Q2;
    public final I2 Q3;
    public final C0280q1 Q4;
    public final C0260n2 Q5;
    public final H2 R;
    public final H2 R0;
    public final I2 R1;
    public final I2 R2;
    public final I2 R3;
    public final C0286r1 R4;
    public final com.android.tools.r8.synthesis.S R5;
    public final H2 S;
    public final H2 S0;
    public final I2 S1;
    public final I2 S2;
    public final I2 S3;
    public final C0293s1 S4;
    public final C0860Ts S5;
    public final H2 T;
    public final H2 T0;
    public final I2 T1;
    public final I2 T2;
    public final I2 T3;
    public final V1 T4;
    public final Map<I2, C0322w2> T5;
    public final H2 U;
    public final H2 U0;
    public final I2 U1;
    public final I2 U2;
    public final I2 U3;
    public final Y1 U4;
    public final C0322w2 U5;
    public final H2 V;
    public final H2 V0;
    public final I2 V1;
    public final I2 V2;
    public final I2 V3;
    public final AbstractC0551Hu V4;
    public final C0322w2 V5;
    public final H2 W;
    public final H2 W0;
    public final I2 W1;
    public final I2 W2;
    public final I2 W3;
    public final H2 W4;
    public final C0322w2 W5;
    public final H2 X;
    public final H2 X0;
    public final I2 X1;
    public final I2 X2;
    public final I2 X3;
    public final E2 X4;
    public final AbstractC0706Nu X5;
    public final H2 Y;
    public final H2 Y0;
    public final I2 Y1;
    public final I2 Y2;
    public final I2 Y3;
    public final H2 Y4;
    public Set<C0322w2> Y5;
    public final H2 Z;
    public final H2 Z0;
    public final I2 Z1;
    public final I2 Z2;
    public final I2 Z3;
    public final I2 Z4;
    public final AbstractC2554rv Z5;
    public final H2 a0;
    public final H2 a1;
    public final I2 a2;
    public final I2 a3;
    public final I2 a4;
    public final E2 a5;
    public final AbstractC2554rv a6;
    public final H2 b0;
    public final H2 b1;
    public final I2 b2;
    public final I2 b3;
    public final I2 b4;
    public final H2 b5;
    public Set<I2> b6;
    public final H2 c0;
    public final H2 c1;
    public final I2 c2;
    public final I2 c3;
    public final H2 c4;
    public final I2 c5;
    public final AbstractC2554rv c6;
    public final H2 d0;
    public final H2 d1;
    public final I2 d2;
    public final I2 d3;
    public final E2 d4;
    public final I2 d5;
    public boolean d6;
    public final H2 e0;
    public final H2 e1;
    public final I2 e2;
    public final I2 e3;
    public final C0322w2 e4;
    public final I2 e5;
    public final H2 f0;
    public final H2 f1;
    public final I2 f2;
    public final I2 f3;
    public final C0253m2 f4;
    public final I2 f5;
    public final H2 g0;
    public final H2 g1;
    public final I2 g2;
    public final AbstractC0551Hu g3;
    public final C0253m2 g4;
    public final I2 g5;
    public final H2 h0;
    public final H2 h1;
    public final I2 h2;
    public final AbstractC0551Hu h3;
    public final a h4;
    public final I2 h5;
    public final H2 i0;
    public final H2 i1;
    public final I2 i2;
    public final I2 i3;
    public final F1 i4;
    public final I2 i5;
    public final H2 j0;
    public final H2 j1;
    public final I2 j2;
    public final I2 j3;
    public final G1 j4;
    public final I2 j5;
    public final H2 k0;
    public final H2 k1;
    public final I2 k2;
    public final I2 k3;
    public final L1 k4;
    public final I2 k5;
    public final H2 l0;
    public final H2 l1;
    public final I2 l2;
    public final I2 l3;
    public final O1 l4;
    public final I2 l5;
    public final H2 m0;
    public final H2 m1;
    public final I2 m2;
    public final I2 m3;
    public final C0176b2 m4;
    public final I2 m5;
    public final H2 n0;
    public final H2 n1;
    public final I2 n2;
    public final I2 n3;
    public final C0274p2 n4;
    public final H2 n5;
    public final H2 o0;
    public final H2 o1;
    public final I2 o2;
    public final I2 o3;
    public final C0204f2 o4;
    public final H2 o5;
    public final H2 p0;
    public final H2 p1;
    public final I2 p2;
    public final I2 p3;
    public final b p4;
    public final H2 p5;
    public final H2 q0;
    public final H2 q1;
    public final I2 q2;
    public final I2 q3;
    public final E1 q4;
    public final H2 q5;
    public final H2 r0;
    public final H2 r1;
    public final I2 r2;
    public final I2 r3;
    public final C0232j2 r4;
    public final H2 r5;
    public final H2 s0;
    public final H2 s1;
    public final I2 s2;
    public final I2 s3;
    public final C0246l2 s4;
    public final I2 s5;
    public final H2 t0;
    public final H2 t1;
    public final I2 t2;
    public final I2 t3;
    public final c t4;
    public final I2 t5;
    public final H2 u0;
    public final H2 u1;
    public final I2 u2;
    public final I2 u3;
    public final C0267o2 u4;
    public final I2 u5;
    public final H2 v0;
    public final H2 v1;
    public final I2 v2;
    public final I2 v3;
    public final J1 v4;
    public final I2 v5;
    public final H2 w;
    public final H2 w0;
    public final I2 w1;
    public final I2 w2;
    public final I2 w3;
    public final d w4;
    public final I2 w5;
    public final H2 x;
    public final H2 x0;
    public final I2 x1;
    public final I2 x2;
    public final I2 x3;
    public final C0342z1 x4;
    public final I2 x5;
    public final H2 y;
    public final H2 y0;
    public final I2 y1;
    public final I2 y2;
    public final C0322w2 y3;
    public final H1 y4;
    public final I2 y5;
    public final H2 z;
    public final H2 z0;
    public final I2 z1;
    public final I2 z2;
    public final I2 z3;
    public final I1 z4;
    public final I2 z5;
    public final Set a = AbstractC2780ub0.c();
    public final ConcurrentHashMap b = new ConcurrentHashMap();
    public final ConcurrentHashMap c = new ConcurrentHashMap();
    public final ConcurrentHashMap d = new ConcurrentHashMap();
    public final ConcurrentHashMap e = new ConcurrentHashMap();
    public final ConcurrentHashMap f = new ConcurrentHashMap();
    public final ConcurrentHashMap g = new ConcurrentHashMap();
    public final ConcurrentHashMap h = new ConcurrentHashMap();
    public final C2986wz i = new C2986wz(16);
    public final C2986wz j = new C2986wz(16);
    public final C2986wz k = new C2986wz(16);
    public final C2986wz l = new C2986wz(16);
    public final C2986wz m = new C2986wz(16);
    public final R0 n = new R0();
    public final T0 o = new T0();
    public final HashMap p = new HashMap();
    public final HashMap q = new HashMap();
    public final O0.b r = c(new O0.b(14).d);
    public final O0.b s = c(new O0.b(30).d);
    public final ConcurrentHashMap t = new ConcurrentHashMap();
    public final ConcurrentHashMap u = new ConcurrentHashMap();
    public final VI v = new VI();

    public class b {
        public final C0245l1 a;
        public final C0322w2 b;
        public final C0322w2 c;
        public final C0322w2 d;
        public final C0322w2 e;
        public final C0322w2 f;
        public final C0322w2 g;
        public final C0322w2 h;
        public final C0322w2 i;
        public final C0322w2 j;
        public final C0322w2 k;
        public final C0322w2 l;
        public final C0322w2 m;

        public b(B1 b1) {
            this.a = b1.a(b1.a2, b1.B1, "$r8$clinit");
            I2 i2 = b1.a2;
            this.c = b1.a(i2, b1.a(b1.w1, i2), "equals");
            this.e = b1.a(b1.a2, b1.a(b1.B1, new I2[0]), "hashCode");
            I2 i3 = b1.a2;
            this.b = b1.a(i3, b1.a(i3, new I2[0]), b1.f0);
            H2 h2 = b1.K0;
            H2 h3 = b1.o0;
            H2 h4 = b1.M0;
            H2[] h2Arr = H2.g;
            this.d = b1.a(h2, h3, h4, h2Arr);
            this.f = b1.a(b1.K0, b1.c1, b1.E1.f, h2Arr);
            this.g = b1.a(b1.K0, b1.p0, b1.E1.f, h2Arr);
            this.h = b1.a(b1.K0, b1.k0, b1.J0, h2Arr);
            this.i = b1.a(b1.K0, b1.O, b1.E, h2Arr);
            this.j = b1.a(b1.K0, b1.P, b1.E, h2Arr);
            this.k = b1.a(b1.K0, b1.N, b1.E, h2Arr);
            this.l = b1.a(b1.K0, b1.N, b1.E, new H2[]{b1.C});
            this.m = b1.a(b1.K0, b1.N, b1.E, new H2[]{b1.C, b1.B});
        }

        public boolean a(C0322w2 c0322w2) {
            return c0322w2.d(this.b) || c0322w2.d(this.d) || c0322w2.d(this.f) || c0322w2.d(this.g) || c0322w2.d(this.h) || c0322w2.d(this.e) || c0322w2.d(this.c) || c0322w2.d(this.i) || c0322w2.d(this.j) || c0322w2.d(this.k) || c0322w2.d(this.l) || c0322w2.d(this.m);
        }
    }

    public class c extends AbstractC0169a2 {
        public final C0245l1 a;
        public final C0322w2 b;
        public final C0322w2 c;
        public final C0322w2 d;
        public final C0322w2 e;
        public final C0322w2 f;
        public final C0322w2 g;
        public final C0322w2 h;
        public final C0322w2 i;
        public final C0322w2 j;
        public final C0322w2 k;
        public final C0322w2 l;
        public final C0322w2 m;
        public final C0322w2 n;
        public final C0322w2 o;
        public final C0322w2 p;
        public final C0322w2 q;
        public final C0322w2 r;
        public final C0322w2 s;
        public final C0322w2 t;
        public final C0322w2 u;
        public final C0322w2 v;
        public final C0322w2 w;

        public c(B1 b1) {
            this.a = b1.a(b1.Y1, b1.D3, "CASE_INSENSITIVE_ORDER");
            I2 i2 = b1.Y1;
            this.e = b1.a(i2, b1.a(b1.E1, i2), b1.c1);
            I2 i3 = b1.Y1;
            this.w = b1.a(i3, b1.a(i3, new I2[0]), b1.i0);
            H2 h2 = b1.J0;
            H2 h3 = b1.Q;
            H2 h4 = b1.w;
            H2[] h2Arr = H2.g;
            this.b = b1.a(h2, h3, h4, h2Arr);
            this.c = b1.a(b1.J0, b1.R, b1.B, h2Arr);
            H2[] h2Arr2 = {b1.I0};
            H2 h5 = b1.J0;
            H2[] h2Arr3 = {h5};
            H2[] h2Arr4 = {b1.K0};
            H2[] h2Arr5 = {b1.B};
            this.d = b1.a(h5, b1.S, h5, h2Arr3);
            this.f = b1.a(b1.J0, b1.T, b1.w, h2Arr2);
            this.g = b1.a(b1.J0, b1.U, b1.w, h2Arr3);
            this.h = b1.a(b1.J0, b1.V, b1.w, h2Arr3);
            this.i = b1.a(b1.J0, b1.W, b1.w, h2Arr4);
            this.j = b1.a(b1.J0, b1.Z, b1.w, h2Arr3);
            this.k = b1.a(b1.J0, b1.a0, b1.w, h2Arr2);
            this.m = b1.a(b1.J0, b1.b0, b1.B, h2Arr3);
            this.l = b1.a(b1.J0, b1.b0, b1.B, h2Arr5);
            this.o = b1.a(b1.J0, b1.c0, b1.B, h2Arr3);
            this.n = b1.a(b1.J0, b1.c0, b1.B, h2Arr5);
            this.p = b1.a(b1.J0, b1.d0, b1.B, h2Arr3);
            this.q = b1.a(b1.J0, b1.e0, b1.B, h2Arr3);
            this.r = b1.a(b1.Y1, b1.a(b1.B1, new I2[0]), b1.X);
            H2 h6 = b1.J0;
            this.s = b1.a(h6, b1.g0, h6, new H2[]{h6, b1.L0});
            H2 h7 = b1.J0;
            this.t = b1.a(h7, b1.g0, h7, new H2[]{b1.T0, h7, b1.L0});
            H2 h8 = b1.J0;
            this.u = b1.a(h8, b1.j0, h8, h2Arr4);
            H2 h9 = b1.J0;
            this.v = b1.a(h9, b1.k0, h9, h2Arr);
            H2 h10 = b1.J0;
            b1.a(h10, b1.l0, h10, h2Arr);
        }

        @Override // com.android.tools.r8.graph.AbstractC0169a2
        public final void a(Consumer consumer) {
            consumer.accept(this.a);
        }
    }

    public class d {
        public final C0322w2 a;
        public final C0322w2 b;
        public final C0322w2 c;

        public d(B1 b1) {
            this.a = b1.a(b1.W0, b1.c("addSuppressed"), b1.E, new H2[]{b1.W0});
            H2 h2 = b1.W0;
            H2 h2C = b1.c("getSuppressed");
            H2 h3 = b1.h1;
            H2[] h2Arr = H2.g;
            this.c = b1.a(h2, h2C, h3, h2Arr);
            H2 h4 = b1.W0;
            H2 h2C2 = b1.c("initCause");
            H2 h5 = b1.W0;
            b1.a(h4, h2C2, h5, new H2[]{h5});
            this.b = b1.a(b1.W0, b1.c("getMessage"), b1.J0, h2Arr);
        }
    }

    static {
        Map mapA;
        I2 i2 = new I2(new H2("NULL"));
        e6 = i2;
        H2 h2 = new H2("UNKNOWN");
        f6 = h2;
        Map.Entry[] entryArr = {new C0784Qu(i2, i2), new C0784Qu(h2, h2)};
        T40 t40 = T40.i;
        DX.b(2, 2);
        try {
            mapA = T40.a(2, entryArr);
        } catch (Q40 unused) {
            mapA = XC.a(2, entryArr);
        }
        g6 = new IdentityHashMap(mapA);
        h6 = AbstractC0551Hu.a("androidx/", "android/support/");
        i6 = AbstractC0551Hu.a("multidex/MultiDex$V14$ElementConstructor;", "multidex/MultiDex$V14$ICSElementConstructor;", "multidex/MultiDex$V14$JBMR11ElementConstructor;", "multidex/MultiDex$V14$JBMR2ElementConstructor;", "multidex/MultiDex$V14;", "multidex/MultiDex$V19;", "multidex/MultiDex$V21_PLUS;", "multidex/MultiDex$V4;", "multidex/MultiDexApplication;", "multidex/MultiDexExtractor$1;", "multidex/MultiDexExtractor$ExtractedDex;", "multidex/MultiDexExtractor;", "multidex/MultiDex;", "multidex/ZipUtil;", "multidex/ZipUtil$CentralDirectory;");
        j6 = AbstractC0551Hu.a("Landroid/support/multidex/instrumentation/BuildConfig;", "Landroid/test/runner/MultiDexTestRunner;");
    }

    public B1() {
        H2 h2C = c("Z");
        this.w = h2C;
        H2 h2C2 = c("B");
        this.x = h2C2;
        H2 h2C3 = c("C");
        this.y = h2C3;
        H2 h2C4 = c("D");
        this.z = h2C4;
        H2 h2C5 = c("F");
        this.A = h2C5;
        H2 h2C6 = c("I");
        this.B = h2C6;
        H2 h2C7 = c("J");
        this.C = h2C7;
        H2 h2C8 = c("S");
        this.D = h2C8;
        H2 h2C9 = c("V");
        this.E = h2C9;
        this.F = c("/");
        this.G = c("Lcom/sun/");
        this.H = c("Ljava/");
        this.I = c("Ljavax/");
        this.J = c("Ljdk/");
        this.K = c("Lsun/");
        this.L = c("Lj$/");
        H2 h2C10 = c("[Z");
        H2 h2C11 = c("[B");
        H2 h2C12 = c("[C");
        H2 h2C13 = c("[D");
        H2 h2C14 = c("[F");
        H2 h2C15 = c("[I");
        H2 h2C16 = c("[J");
        H2 h2C17 = c("[S");
        H2 h2C18 = c("Ljava/lang/Boolean;");
        H2 h2C19 = c("Ljava/lang/Byte;");
        H2 h2C20 = c("Ljava/lang/Character;");
        H2 h2C21 = c("Ljava/lang/Double;");
        H2 h2C22 = c("Ljava/lang/Float;");
        H2 h2C23 = c("Ljava/lang/Integer;");
        H2 h2C24 = c("Ljava/lang/Long;");
        H2 h2C25 = c("Ljava/lang/Short;");
        H2 h2C26 = c("Ljava/lang/Number;");
        this.M = h2C26;
        H2 h2C27 = c("Ljava/lang/Void;");
        this.N = c("wait");
        this.O = c("notify");
        this.P = c("notifyAll");
        H2 h2C28 = c("booleanValue");
        H2 h2C29 = c("byteValue");
        H2 h2C30 = c("charValue");
        H2 h2C31 = c("shortValue");
        H2 h2C32 = c("intValue");
        H2 h2C33 = c("longValue");
        H2 h2C34 = c("floatValue");
        H2 h2C35 = c("doubleValue");
        this.Q = c("isEmpty");
        this.R = c("length");
        this.S = c("concat");
        this.T = c("contains");
        this.U = c("startsWith");
        this.V = c("endsWith");
        this.W = c("equals");
        this.X = c("hashCode");
        this.Y = c("identityHashCode");
        this.Z = c("equalsIgnoreCase");
        this.a0 = c("contentEquals");
        this.b0 = c("indexOf");
        this.c0 = c("lastIndexOf");
        this.d0 = c("compareTo");
        this.e0 = c("compareToIgnoreCase");
        this.f0 = c("clone");
        this.g0 = c("format");
        this.h0 = c("substring");
        this.i0 = c("trim");
        this.j0 = c("valueOf");
        c("values");
        this.k0 = c("toString");
        this.l0 = c("intern");
        this.m0 = c("convert");
        this.n0 = c("wrappedValue");
        this.o0 = c("getClass");
        this.p0 = c("finalize");
        this.q0 = c("ordinal");
        this.r0 = c(TypeBlock.NAME_name);
        this.s0 = c("desiredAssertionStatus");
        this.t0 = c("forName");
        this.u0 = c("getName");
        this.v0 = c("getCanonicalName");
        this.w0 = c("getSimpleName");
        this.x0 = c("getTypeName");
        this.y0 = c("getDeclaredConstructor");
        this.z0 = c("getField");
        this.A0 = c("getDeclaredField");
        this.B0 = c("getMethod");
        this.C0 = c("getDeclaredMethod");
        this.D0 = c("newInstance");
        this.E0 = c("$assertionsDisabled");
        this.F0 = c("invoke");
        this.G0 = c("invokeExact");
        H2 h2C36 = c("Ljava/lang/RuntimeException;");
        H2 h2C37 = c("Ljava/lang/AssertionError;");
        this.H0 = h2C37;
        H2 h2C38 = c("Ljava/lang/CharSequence;");
        this.I0 = h2C38;
        H2 h2C39 = c("[Ljava/lang/CharSequence;");
        H2 h2C40 = c("Ljava/lang/String;");
        this.J0 = h2C40;
        H2 h2C41 = c("[Ljava/lang/String;");
        H2 h2C42 = c("Ljava/lang/Object;");
        this.K0 = h2C42;
        H2 h2C43 = c("Ljava/lang/Record;");
        H2 h2C44 = c("Lcom/android/tools/r8/RecordTag;");
        H2 h2C45 = c("[Ljava/lang/Object;");
        this.L0 = h2C45;
        H2 h2C46 = c("Ljava/lang/Class;");
        this.M0 = h2C46;
        H2 h2C47 = c("Ljava/lang/ClassLoader;");
        this.N0 = h2C47;
        H2 h2C48 = c("Ljava/lang/AutoCloseable;");
        H2 h2C49 = c("[Ljava/lang/Class;");
        this.O0 = h2C49;
        H2 h2C50 = c("Ljava/lang/reflect/Constructor;");
        this.P0 = h2C50;
        H2 h2C51 = c("Ljava/lang/reflect/Field;");
        this.Q0 = h2C51;
        H2 h2C52 = c("Ljava/lang/reflect/Method;");
        this.R0 = h2C52;
        H2 h2C53 = c("Ljava/lang/Enum;");
        this.S0 = h2C53;
        H2 h2C54 = c("Ljava/lang/System;");
        H2 h2C55 = c("Ljava/lang/annotation/Annotation;");
        H2 h2C56 = c("Ljava/util/Objects;");
        H2 h2C57 = c("Ljava/util/Collections;");
        H2 h2C58 = c("Ljava/lang/Iterable;");
        H2 h2C59 = c("Ljava/lang/Math;");
        H2 h2C60 = c("Ljava/lang/StrictMath;");
        H2 h2C61 = c("Ljava/io/Closeable;");
        H2 h2C62 = c("Ljava/util/zip/ZipFile;");
        H2 h2C63 = c("Ljava/nio/Buffer;");
        H2 h2C64 = c("Ljava/nio/ByteBuffer;");
        H2 h2C65 = c("Ljava/nio/MappedByteBuffer;");
        H2 h2C66 = c("Ljava/nio/CharBuffer;");
        H2 h2C67 = c("Ljava/nio/ShortBuffer;");
        H2 h2C68 = c("Ljava/nio/IntBuffer;");
        H2 h2C69 = c("Ljava/nio/LongBuffer;");
        H2 h2C70 = c("Ljava/nio/FloatBuffer;");
        H2 h2C71 = c("Ljava/nio/DoubleBuffer;");
        H2 h2C72 = c("Ljava/lang/StringBuilder;");
        H2 h2C73 = c("Ljava/lang/StringBuffer;");
        H2 h2C74 = c("Ljava/lang/invoke/VarHandle;");
        H2 h2C75 = c("Ljava/lang/invoke/MethodHandle;");
        H2 h2C76 = c("Ljava/lang/invoke/MethodHandles;");
        H2 h2C77 = c("Ljava/lang/invoke/MethodHandles$Lookup;");
        H2 h2C78 = c("Ljava/lang/invoke/MethodType;");
        H2 h2C79 = c("Ljava/lang/reflect/InvocationHandler;");
        H2 h2C80 = c("Ljava/lang/reflect/Proxy;");
        H2 h2C81 = c("Ljava/util/ServiceLoader;");
        H2 h2C82 = c("Ljava/util/ServiceConfigurationError;");
        H2 h2C83 = c("Ljava/util/Locale;");
        this.T0 = h2C83;
        H2 h2C84 = c("Ljava/util/List;");
        this.U0 = h2C84;
        H2 h2C85 = c("Ljava/util/Set;");
        H2 h2C86 = c("Ljava/util/Map;");
        H2 h2C87 = c("Ljava/util/Map$Entry;");
        H2 h2C88 = c("Ljava/util/Collection;");
        H2 h2C89 = c("Ljava/util/Comparator;");
        H2 h2C90 = c("Ljava/util/concurrent/Callable;");
        H2 h2C91 = c("Ljava/util/function/Supplier;");
        H2 h2C92 = c("Ljava/util/function/Predicate;");
        H2 h2C93 = c("Ljava/util/function/Consumer;");
        H2 h2C94 = c("Ljava/lang/Runnable;");
        H2 h2C95 = c("Ljava/util/Optional;");
        H2 h2C96 = c("Ljava/util/OptionalDouble;");
        H2 h2C97 = c("Ljava/util/OptionalInt;");
        H2 h2C98 = c("Ljava/util/OptionalLong;");
        H2 h2C99 = c("Ljava/util/stream/Stream;");
        H2 h2C100 = c("Ljava/util/Arrays;");
        this.V0 = h2C100;
        H2 h2C101 = c("Ljava/lang/ThreadLocal;");
        H2 h2C102 = c("Ljava/util/concurrent/ConcurrentHashMap;");
        H2 h2C103 = c("Ljava/util/concurrent/ConcurrentHashMap$KeySetView;");
        H2 h2C104 = c("Ljava/lang/Throwable;");
        this.W0 = h2C104;
        H2 h2C105 = c("Ljava/lang/IllegalAccessError;");
        H2 h2C106 = c("Ljava/lang/IllegalArgumentException;");
        H2 h2C107 = c("Ljava/lang/AbstractMethodError;");
        H2 h2C108 = c("Ljava/lang/IncompatibleClassChangeError;");
        H2 h2C109 = c("Ljava/lang/ExceptionInInitializerError;");
        H2 h2C110 = c("Ljava/lang/NoClassDefFoundError;");
        H2 h2C111 = c("Ljava/lang/NoSuchFieldError;");
        H2 h2C112 = c("Ljava/lang/NullPointerException;");
        this.X0 = h2C112;
        H2 h2C113 = c("Ljava/lang/ReflectiveOperationException;");
        H2 h2C114 = c("Lkotlin/Metadata;");
        H2 h2C115 = c("Lkotlin/jvm/JvmName;");
        this.Y0 = c("Ljava/util/concurrent/atomic/AtomicIntegerFieldUpdater;");
        this.Z0 = c("Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;");
        H2 h2C116 = c("Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;");
        this.a1 = h2C116;
        this.b1 = c("newUpdater");
        H2 h2C117 = c("<init>");
        this.c1 = h2C117;
        this.d1 = c("<clinit>");
        this.e1 = c("this");
        c("INSTANCE");
        this.f1 = c("lambda$");
        c("$VALUES");
        this.g1 = c("ENABLED");
        this.h1 = c("[Ljava/lang/Throwable;");
        this.i1 = c("value");
        this.j1 = c("kind");
        this.k1 = c("versionHash");
        this.l1 = c("apiLevel");
        this.m1 = c("Ldalvik/annotation/optimization/");
        this.n1 = c("get");
        this.o1 = c("set");
        this.p1 = c("compareAndSet");
        this.q1 = c("weakCompareAndSet");
        this.r1 = c("getVolatile");
        this.s1 = c("setVolatile");
        this.t1 = c("setRelease");
        this.u1 = c("lookup");
        this.v1 = c("privateLookupIn");
        I2 i2D = d(h2C);
        a(i2D);
        this.w1 = i2D;
        I2 i2D2 = d(h2C2);
        a(i2D2);
        this.x1 = i2D2;
        I2 i2D3 = d(h2C3);
        a(i2D3);
        this.y1 = i2D3;
        I2 i2D4 = d(h2C4);
        a(i2D4);
        this.z1 = i2D4;
        I2 i2D5 = d(h2C5);
        a(i2D5);
        this.A1 = i2D5;
        I2 i2D6 = d(h2C6);
        a(i2D6);
        this.B1 = i2D6;
        I2 i2D7 = d(h2C7);
        a(i2D7);
        this.C1 = i2D7;
        I2 i2D8 = d(h2C8);
        a(i2D8);
        this.D1 = i2D8;
        I2 i2D9 = d(h2C9);
        a(i2D9);
        this.E1 = i2D9;
        I2 i2D10 = d(h2C10);
        a(i2D10);
        this.F1 = i2D10;
        I2 i2D11 = d(h2C11);
        a(i2D11);
        this.G1 = i2D11;
        I2 i2D12 = d(h2C12);
        a(i2D12);
        this.H1 = i2D12;
        I2 i2D13 = d(h2C13);
        a(i2D13);
        this.I1 = i2D13;
        I2 i2D14 = d(h2C14);
        a(i2D14);
        this.J1 = i2D14;
        I2 i2D15 = d(h2C15);
        a(i2D15);
        this.K1 = i2D15;
        I2 i2D16 = d(h2C16);
        a(i2D16);
        this.L1 = i2D16;
        I2 i2D17 = d(h2C17);
        a(i2D17);
        this.M1 = i2D17;
        I2 i2D18 = d(h2C18);
        a(i2D18);
        this.N1 = i2D18;
        I2 i2D19 = d(h2C19);
        a(i2D19);
        this.O1 = i2D19;
        I2 i2D20 = d(h2C20);
        a(i2D20);
        this.P1 = i2D20;
        I2 i2D21 = d(h2C21);
        a(i2D21);
        this.Q1 = i2D21;
        I2 i2D22 = d(h2C22);
        a(i2D22);
        this.R1 = i2D22;
        I2 i2D23 = d(h2C23);
        a(i2D23);
        this.S1 = i2D23;
        I2 i2D24 = d(h2C24);
        a(i2D24);
        this.T1 = i2D24;
        I2 i2D25 = d(h2C25);
        a(i2D25);
        this.U1 = i2D25;
        I2 i2D26 = d(h2C26);
        a(i2D26);
        this.V1 = i2D26;
        I2 i2D27 = d(h2C27);
        a(i2D27);
        I2 i2D28 = d(h2C38);
        a(i2D28);
        this.W1 = i2D28;
        I2 i2D29 = d(h2C39);
        a(i2D29);
        this.X1 = i2D29;
        I2 i2D30 = d(h2C40);
        a(i2D30);
        this.Y1 = i2D30;
        I2 i2D31 = d(h2C41);
        a(i2D31);
        this.Z1 = i2D31;
        I2 i2D32 = d(h2C42);
        a(i2D32);
        this.a2 = i2D32;
        I2 i2D33 = d(h2C43);
        a(i2D33);
        this.b2 = i2D33;
        I2 i2D34 = d(h2C44);
        a(i2D34);
        this.c2 = i2D34;
        I2 i2D35 = d(h2C45);
        a(i2D35);
        this.d2 = i2D35;
        I2 i2D36 = d(h2C49);
        a(i2D36);
        this.e2 = i2D36;
        I2 i2D37 = d(h2C53);
        a(i2D37);
        this.f2 = i2D37;
        I2 i2D38 = d(h2C55);
        a(i2D38);
        this.g2 = i2D38;
        I2 i2D39 = d(h2C100);
        a(i2D39);
        this.h2 = i2D39;
        I2 i2D40 = d(h2C56);
        a(i2D40);
        this.i2 = i2D40;
        I2 i2D41 = d(h2C57);
        a(i2D41);
        this.j2 = i2D41;
        I2 i2D42 = d(h2C58);
        a(i2D42);
        this.k2 = i2D42;
        I2 i2D43 = d(h2C59);
        a(i2D43);
        this.l2 = i2D43;
        I2 i2D44 = d(h2C60);
        a(i2D44);
        this.m2 = i2D44;
        I2 i2D45 = d(h2C116);
        a(i2D45);
        this.n2 = i2D45;
        I2 i2D46 = d(h2C46);
        a(i2D46);
        this.o2 = i2D46;
        this.p2 = e();
        I2 i2D47 = d(h2C47);
        a(i2D47);
        this.q2 = i2D47;
        I2 i2D48 = d(h2C50);
        a(i2D48);
        this.r2 = i2D48;
        I2 i2D49 = d(h2C51);
        a(i2D49);
        this.s2 = i2D49;
        I2 i2D50 = d(h2C52);
        a(i2D50);
        this.t2 = i2D50;
        I2 i2D51 = d(h2C48);
        a(i2D51);
        this.u2 = i2D51;
        I2 i2D52 = d(h2C61);
        a(i2D52);
        this.v2 = i2D52;
        I2 i2D53 = d(h2C62);
        a(i2D53);
        this.w2 = i2D53;
        I2 i2D54 = d(h2C72);
        a(i2D54);
        this.x2 = i2D54;
        I2 i2D55 = d(h2C73);
        a(i2D55);
        this.y2 = i2D55;
        this.z2 = b("Ljava/lang/annotation/RetentionPolicy;");
        this.A2 = b("Ljava/lang/reflect/Array;");
        I2 i2D56 = d(h2C54);
        a(i2D56);
        this.B2 = i2D56;
        I2 i2B = b("Ljava/io/PrintStream;");
        this.C2 = i2B;
        I2 i2D57 = d(h2C74);
        a(i2D57);
        this.D2 = i2D57;
        I2 i2D58 = d(h2C75);
        a(i2D58);
        this.E2 = i2D58;
        I2 i2D59 = d(h2C76);
        a(i2D59);
        this.F2 = i2D59;
        I2 i2D60 = d(h2C77);
        a(i2D60);
        this.G2 = i2D60;
        I2 i2D61 = d(h2C78);
        a(i2D61);
        this.H2 = i2D61;
        I2 i2D62 = d(h2C79);
        a(i2D62);
        this.I2 = i2D62;
        I2 i2D63 = d(h2C80);
        a(i2D63);
        this.J2 = i2D63;
        I2 i2D64 = d(h2C81);
        a(i2D64);
        this.K2 = i2D64;
        I2 i2D65 = d(h2C82);
        a(i2D65);
        this.L2 = i2D65;
        I2 i2D66 = d(h2C84);
        a(i2D66);
        this.M2 = i2D66;
        I2 i2D67 = d(h2C85);
        a(i2D67);
        this.N2 = i2D67;
        I2 i2D68 = d(h2C86);
        a(i2D68);
        this.O2 = i2D68;
        this.P2 = b(h2C87);
        this.Q2 = b("Ljava/util/AbstractMap$SimpleEntry;");
        this.R2 = b(h2C88);
        this.S2 = b(h2C89);
        I2 i2B2 = b(h2C90);
        this.T2 = b(h2C91);
        this.U2 = b(h2C92);
        this.V2 = b(h2C93);
        this.W2 = b(h2C94);
        this.X2 = b(h2C95);
        this.Y2 = b(h2C96);
        this.Z2 = b(h2C97);
        this.a3 = b(h2C98);
        this.b3 = b(h2C99);
        this.c3 = b(h2C101);
        this.d3 = b(h2C102);
        this.e3 = b(h2C103);
        this.f3 = b(h2C63);
        this.g3 = AbstractC0551Hu.a(b(h2C64), b(h2C65), b(h2C66), b(h2C67), b(h2C68), b(h2C69), b(h2C70), b(h2C71));
        this.h3 = d();
        this.i3 = b("Ljava/util/function/DoubleConsumer;");
        this.j3 = b("Ljava/util/function/LongConsumer;");
        this.k3 = b("Ljava/util/function/IntConsumer;");
        this.l3 = b("Ljava/lang/annotation/Retention;");
        this.m3 = b(h2C36);
        this.n3 = b(h2C37);
        I2 i2B3 = b(h2C104);
        this.o3 = i2B3;
        this.p3 = b(h2C105);
        this.q3 = b(h2C106);
        this.r3 = b(h2C107);
        this.s3 = b(h2C108);
        b(h2C109);
        this.t3 = b(h2C110);
        b(h2C111);
        this.u3 = b("Ljava/lang/NoSuchMethodError;");
        I2 i2B4 = b(h2C112);
        this.v3 = i2B4;
        b(h2C113);
        this.w3 = b(h2C114);
        this.x3 = b(h2C115);
        this.y3 = a(b("Lkotlin/enums/EnumEntriesList;"), a(i2D9, a(1, i2D37)), h2C117);
        this.z3 = b("Ljava/io/File;");
        this.A3 = b("Ljava/math/BigInteger;");
        this.B3 = b("Ljava/nio/ByteOrder;");
        this.C3 = b("Ljava/util/Collections;");
        this.D3 = b("Ljava/util/Comparator;");
        this.E3 = b("Ljava/util/concurrent/TimeUnit;");
        this.F3 = b("Ljava/util/Formattable;");
        this.G3 = b("Ljava/util/List;");
        this.H3 = b(h2C83);
        this.I3 = b("Ljava/util/logging/Level;");
        this.J3 = b("Ljava/util/logging/Logger;");
        this.K3 = b("Ljava/util/Set;");
        I2 i2B5 = b("Landroid/app/Activity;");
        this.L3 = b("Landroid/app/Fragment;");
        this.M3 = b("Landroid/app/ZygotePreload;");
        this.N3 = b("Landroid/os/Build;");
        this.O3 = b("Landroid/os/Build$VERSION;");
        this.P3 = b("Landroid/os/Bundle;");
        I2 i2B6 = b("Landroid/os/Handler;");
        this.Q3 = b("Landroid/os/Parcelable$Creator;");
        this.R3 = b("Landroid/system/OsConstants;");
        I2 i2B7 = b("Landroid/util/Log;");
        this.S3 = i2B7;
        this.T3 = b("Landroid/util/Property;");
        this.U3 = b("Landroid/view/View;");
        this.V3 = b("Landroid/util/SparseArray;");
        this.W3 = b("Landroid/content/res/TypedArray;");
        this.X3 = b("Landroid/content/ContentProviderClient;");
        this.Y3 = b("Landroid/drm/DrmManagerClient;");
        this.Z3 = b("Landroid/media/MediaDrm;");
        this.a4 = b("Landroid/media/MediaMetadataRetriever;");
        I2 i2B8 = b("Landroid/content/res/Resources;");
        this.b4 = i2B8;
        H2 h2C118 = c("getString");
        this.c4 = h2C118;
        E2 e2A = a(i2D30, i2D6);
        this.d4 = e2A;
        this.e4 = a(i2B8, e2A, h2C118);
        C0253m2 c0253m2 = new C0253m2(this, i2D54);
        this.f4 = c0253m2;
        C0253m2 c0253m3 = new C0253m2(this, i2D55);
        this.g4 = c0253m3;
        a aVar = new a(this);
        this.h4 = aVar;
        this.i4 = new F1(this);
        this.j4 = new G1(this);
        L1 l1 = new L1(this);
        this.k4 = l1;
        O1 o1 = new O1(this);
        this.l4 = o1;
        C0176b2 c0176b2 = new C0176b2(this);
        this.m4 = c0176b2;
        this.n4 = new C0274p2(this);
        C0204f2 c0204f2 = new C0204f2(this);
        this.o4 = c0204f2;
        b bVar = new b(this);
        this.p4 = bVar;
        this.q4 = new E1(this);
        this.r4 = new C0232j2(this);
        this.s4 = new C0246l2(this);
        c cVar = new c(this);
        this.t4 = cVar;
        this.u4 = new C0267o2(this);
        this.v4 = new J1(this);
        this.w4 = new d(this);
        this.x4 = new C0342z1(this);
        H1 h1 = new H1(this);
        this.y4 = h1;
        this.z4 = new I1(this);
        this.A4 = new C0183c2(this);
        this.B4 = new K1(this);
        a(i2B7, a(i2D6, i2D30, i2D30), "i");
        this.C4 = new R1(this);
        this.D4 = new Q1(this);
        this.E4 = new S1(this);
        a(i2B, a(i2D9, i2D30), "println");
        this.F4 = new C0190d2(this);
        this.G4 = new N1(this);
        this.H4 = new C0218h2(this);
        this.I4 = new A1(this);
        this.K4 = new C0211g2(this);
        this.L4 = new C0225i2(this);
        C0300t1 c0300t1 = new C0300t1(this);
        C0307u1 c0307u1 = new C0307u1(this);
        this.M4 = c0307u1;
        C0314v1 c0314v1 = new C0314v1(this);
        C0321w1 c0321w1 = new C0321w1(this);
        C0335y1 c0335y1 = new C0335y1(this);
        this.N4 = new C0328x1(this);
        this.O4 = new C0273p1(this);
        this.P4 = new C0266o1(this);
        this.Q4 = new C0280q1(this);
        this.R4 = new C0286r1(this);
        this.S4 = new C0293s1(this);
        P1 p1 = new P1(this);
        T1 t1 = new T1(this);
        U1 u1 = new U1(this);
        this.T4 = new V1(this);
        W1 w1 = new W1(this);
        X1 x1 = new X1(this);
        Y1 y1 = new Y1(this);
        this.U4 = y1;
        this.V4 = AbstractC0551Hu.a(aVar, l1, o1, c0176b2, cVar, c0300t1, c0307u1, c0314v1, c0321w1, c0335y1, p1, t1, u1, w1, x1, y1, new Z1(this));
        this.W4 = c("$closeResource");
        this.X4 = a(i2D9, i2B3, i2D51);
        H2 h2C119 = c("$deserializeLambda$");
        this.Y4 = h2C119;
        I2 i2B9 = b("Ljava/lang/invoke/SerializedLambda;");
        this.Z4 = i2B9;
        E2 e2A2 = a(i2D32, i2B9);
        this.a5 = e2A2;
        this.b5 = c("SourceFile");
        this.c5 = b("Ldalvik/annotation/AnnotationDefault;");
        this.d5 = b("Ldalvik/annotation/EnclosingClass;");
        this.e5 = b("Ldalvik/annotation/EnclosingMethod;");
        this.f5 = b("Ldalvik/annotation/InnerClass;");
        this.g5 = b("Ldalvik/annotation/MemberClasses;");
        this.h5 = b("Ldalvik/annotation/MethodParameters;");
        this.i5 = b("Ldalvik/annotation/Signature;");
        this.j5 = b("Ldalvik/annotation/NestHost;");
        this.k5 = b("Ldalvik/annotation/NestMembers;");
        this.l5 = b("Ldalvik/annotation/PermittedSubclasses;");
        this.m5 = b("Ldalvik/annotation/Record;");
        this.n5 = c("componentNames");
        this.o5 = c("componentTypes");
        this.p5 = c("componentSignatures");
        this.q5 = c("componentAnnotationVisibilities");
        this.r5 = c("componentAnnotations");
        this.s5 = b("Ldalvik/annotation/SourceDebugExtension;");
        this.t5 = b("Ldalvik/annotation/Throws;");
        this.u5 = b("Lcom/android/tools/r8/annotations/SynthesizedClassV2;");
        this.v5 = b("Ldalvik/annotation/codegen/CovariantReturnType;");
        this.w5 = b("Ldalvik/annotation/codegen/CovariantReturnType$CovariantReturnTypes;");
        this.x5 = b("Ldalvik/annotation/optimization/ReachabilitySensitive;");
        I2 i2B10 = b("Ljava/lang/invoke/LambdaMetafactory;");
        this.y5 = b("Ljava/lang/invoke/ConstantBootstraps;");
        I2 i2B11 = b("Ljava/lang/invoke/CallSite;");
        this.z5 = i2B11;
        I2 i2B12 = b("Ljava/lang/invoke/MethodHandles$Lookup;");
        this.A5 = i2B12;
        this.B5 = b("Ljava/lang/runtime/ObjectMethods;");
        this.C5 = b("Ljava/lang/invoke/TypeDescriptor;");
        this.D5 = b("Ljava/util/Iterator;");
        this.E5 = b("Ljava/util/ListIterator;");
        this.F5 = b("Ljava/util/Enumeration;");
        this.G5 = b("Ljava/io/Serializable;");
        this.H5 = b("Ljava/io/Externalizable;");
        this.I5 = b("Ljava/lang/Cloneable;");
        this.J5 = b("Ljava/lang/Comparable;");
        this.K5 = b("Ljava/lang/invoke/StringConcatFactory;");
        this.L5 = b("Lsun/misc/Unsafe;");
        this.M5 = b("Lcom/android/tools/r8/DesugarVarHandle;");
        this.N5 = b("Lcom/android/tools/r8/DesugarMethodHandlesLookup;");
        this.O5 = new C0197e2(this);
        this.P5 = new C0239k2(this);
        this.Q5 = new C0260n2(this);
        this.R5 = new com.android.tools.r8.synthesis.S();
        AbstractC0706Nu abstractC0706NuA = AbstractC0706Nu.e().a(i2D, i2D18).a(i2D2, i2D19).a(i2D3, i2D20).a(i2D8, i2D25).a(i2D6, i2D23).a(i2D7, i2D24).a(i2D5, i2D22).a(i2D4, i2D21).a();
        int i = C0860Ts.j;
        C0860Ts c0860Ts = new C0860Ts(abstractC0706NuA.size());
        c0860Ts.putAll(abstractC0706NuA);
        this.S5 = c0860Ts;
        this.T5 = AbstractC0706Nu.e().a(i2D18, a(h2C28, i2D)).a(i2D19, a(h2C29, i2D2)).a(i2D20, a(h2C30, i2D3)).a(i2D25, a(h2C31, i2D8)).a(i2D23, a(h2C32, i2D6)).a(i2D24, a(h2C33, i2D7)).a(i2D22, a(h2C34, i2D5)).a(i2D21, a(h2C35, i2D4)).a();
        this.U5 = a(i2B10, a(i2B11, i2B12, i2D30, i2D61, i2D61, i2D58, i2D61), c("metafactory"));
        this.V5 = a(i2B10, a(i2B11, i2B12, i2D30, i2D61, i2D35), c("altMetafactory"));
        this.W5 = a(i2D32, e2A2, h2C119);
        this.X5 = b();
        this.Y5 = AbstractC2554rv.g().a((Set) c0253m3.s).a((Set) c0253m2.s).a();
        C1870jv c1870jvG = AbstractC2554rv.g();
        C0322w2[] c0322w2Arr = {h1.e, h1.g, h1.b, bVar.d, c0204f2.f, c0204f2.g, c0204f2.h, cVar.s, cVar.t, cVar.u};
        for (int i2 = 0; i2 < 10; i2++) {
            c1870jvG.a(c0322w2Arr[i2]);
        }
        this.Z5 = c1870jvG.a(a()).a((Set) c0253m3.s).a((Set) c0253m2.s).a();
        this.a6 = AbstractC2554rv.g().a(a()).a();
        C1870jv c1870jvG2 = AbstractC2554rv.g();
        I2[] i2Arr = {i2B5, i2B6, i2B2, i2D37, i2B4, i2D32, i2D55, i2D54, i2D30};
        for (int i3 = 0; i3 < 9; i3++) {
            c1870jvG2.a(i2Arr[i3]);
        }
        this.b6 = c1870jvG2.a(c0860Ts.values()).a();
        this.c6 = AbstractC2554rv.a(i2D18, i2D19, i2D20, i2D21, i2D22, i2D23, i2D24, i2D26, i2D25, i2D27, i2D37, i2D56, i2B4, i2D32, i2D55, i2D54, i2D30);
        this.d6 = false;
        this.J4 = new C3289g(this);
    }

    public static /* synthetic */ I2 i(I2 i2) {
        return i2;
    }

    public final C0322w2 a(E2 e2, List list, Function function, Consumer consumer) {
        Optional optional = (Optional) function.apply(e2);
        if (optional.isPresent()) {
            return (C0322w2) optional.get();
        }
        if (!k6 && list.isEmpty()) {
            x1f.a();
            return null;
        }
        ArrayDeque arrayDeque = new ArrayDeque(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayDeque.addLast(new M1(this, e2, (Supplier) it.next(), Collections.EMPTY_SET));
        }
        int i = 0;
        while (true) {
            boolean z = k6;
            if (!z) {
                int i2 = i + 1;
                if (i >= 100) {
                    x1f.a();
                    return null;
                }
                i = i2;
            }
            if (!z && arrayDeque.isEmpty()) {
                x1f.a();
                return null;
            }
            M1 m1 = (M1) arrayDeque.removeFirst();
            I2 i3 = (I2) m1.b.get();
            m1.c.add(i3);
            B1 b1 = m1.d;
            E2 e3 = m1.a;
            b1.getClass();
            int size = e3.f.size();
            I2[] i2Arr = new I2[size + 1];
            K2 k2 = e3.f;
            System.arraycopy(k2.b, 0, i2Arr, 0, k2.size());
            i2Arr[size] = i3;
            E2 e2A = b1.a(e3.e, i2Arr);
            Optional optional2 = (Optional) function.apply(e2A);
            if (optional2.isPresent()) {
                if (z || !m1.c.isEmpty()) {
                    consumer.accept(m1.c);
                    return (C0322w2) optional2.get();
                }
                x1f.a();
                return null;
            }
            Iterator it2 = list.iterator();
            while (it2.hasNext()) {
                arrayDeque.addLast(new M1(this, e2A, (Supplier) it2.next(), m1.c));
            }
        }
    }

    public final AbstractC0706Nu b() {
        C0629Ku c0629KuE = AbstractC0706Nu.e();
        C0204f2 c0204f2 = this.o4;
        Iterator<E> it = AbstractC0551Hu.a(c0204f2.f, c0204f2.g, c0204f2.h).iterator();
        while (it.hasNext()) {
            c0629KuE.a((C0322w2) it.next(), new int[]{0});
        }
        return c0629KuE.b();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public C0322w2 c(I2 i2) {
        I2 i3 = (I2) this.S5.getOrDefault(i2, i2);
        I2 i2F = f(i3);
        if (i2F != null) {
            return a(i3, a(i3, i2F), this.j0);
        }
        gk0.a("Invalid primitive type descriptor: ", i2);
        return null;
    }

    public final AbstractC0551Hu d() {
        C0473Eu c0473EuG = AbstractC0551Hu.g();
        for (String str : h6) {
            Iterator it = i6.iterator();
            while (it.hasNext()) {
                c0473EuG.a(e("L" + str + ((String) it.next())));
            }
        }
        Iterator it2 = j6.iterator();
        while (it2.hasNext()) {
            c0473EuG.a(e((String) it2.next()));
        }
        return c0473EuG.a();
    }

    public final D1 e(I2 i2) {
        if (!k6 && !i2.T0() && !i2.W0()) {
            x1f.a();
            return null;
        }
        char cR0 = i2.z0().r0();
        if (cR0 == 'F') {
            return this.k4;
        }
        if (cR0 == 'S') {
            return this.s4;
        }
        if (cR0 == 'V') {
            return this.n4;
        }
        if (cR0 == 'Z') {
            return this.h4;
        }
        if (cR0 == 'I') {
            return this.l4;
        }
        if (cR0 == 'J') {
            return this.m4;
        }
        switch (cR0) {
            case 'B':
                return this.i4;
            case 'C':
                return this.j4;
            case 'D':
                return this.v4;
            default:
                gk0.a("Unknown type ", i2);
                return null;
        }
    }

    public synchronized Collection<com.android.tools.r8.dex.W> f() {
        HashSet hashSet;
        hashSet = new HashSet();
        Iterator it = this.b.keySet().iterator();
        while (it.hasNext()) {
            com.android.tools.r8.dex.W wA = com.android.tools.r8.dex.W.a((H2) it.next());
            if (wA != null) {
                hashSet.add(wA);
            }
        }
        return hashSet;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public C0322w2 g(I2 i2) {
        C0322w2 c0322w2 = this.T5.get((I2) this.S5.getOrDefault(i2, i2));
        if (c0322w2 != null) {
            return c0322w2;
        }
        gk0.a("Invalid primitive type descriptor: ", i2);
        return null;
    }

    public final void h() {
        boolean z = k6;
        if (!z && !this.t.isEmpty()) {
            x1f.a();
            return;
        }
        if (!z && !this.u.isEmpty()) {
            x1f.a();
        } else {
            if (z || this.v.isEmpty()) {
                return;
            }
            x1f.a();
        }
    }

    public class a extends D1 {
        public final C0245l1 a;
        public final C0245l1 b;
        public final C0245l1 c;
        public final C0322w2 d;
        public final C0322w2 e;
        public final C0322w2 f;
        public final C0322w2 g;

        public a(B1 b1) {
            I2 i2 = b1.N1;
            this.a = b1.a(i2, i2, "FALSE");
            I2 i3 = b1.N1;
            this.b = b1.a(i3, i3, "TRUE");
            this.c = b1.a(b1.N1, b1.o2, "TYPE");
            this.d = b1.a(b1.N1, b1.a(b1.w1, new I2[0]), "booleanValue");
            this.e = b1.a(b1.N1, b1.a(b1.w1, b1.Y1), "parseBoolean");
            I2 i4 = b1.N1;
            this.f = b1.a(i4, b1.a(i4, b1.w1), "valueOf");
            this.g = b1.a(b1.N1, b1.a(b1.Y1, new I2[0]), "toString");
        }

        @Override // com.android.tools.r8.graph.AbstractC0169a2
        public final void a(Consumer consumer) {
            consumer.accept(this.a);
            consumer.accept(this.b);
            consumer.accept(this.c);
        }

        @Override // com.android.tools.r8.graph.D1
        public final C0245l1 a() {
            return this.c;
        }
    }

    public final boolean g() {
        return this.d6;
    }

    public H2 c(String str) {
        return (H2) a(this.c, new H2(str));
    }

    public synchronized I2 c(H2 h2) {
        return d(h2);
    }

    public final E2 c(C0322w2 c0322w2) {
        return c0322w2.C0().b(this, c0322w2.w0());
    }

    public final O0.b c(int i) {
        O0.b bVar;
        synchronized (this.k) {
            bVar = (O0.b) this.k.computeIfAbsent(Integer.valueOf(i), new Function() { // from class: uk0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return new O0.b(((Integer) obj).intValue());
                }
            });
        }
        return bVar;
    }

    public final void c() {
        this.t.clear();
        this.u.clear();
        this.v.clear();
    }

    public final I2 f(I2 i2) {
        return (I2) ((C0756Ps) this.S5.f()).get(i2);
    }

    public final Optional b(C0322w2 c0322w2, Predicate predicate, E2 e2) {
        return Optional.of(a(c0322w2.f, e2, c0322w2.g)).filter(predicate);
    }

    public final boolean h(I2 i2) {
        return this.a.contains(i2);
    }

    public final C0322w2 b(I2 i2) {
        return a(i2, a(this.E1, new I2[0]), this.d1);
    }

    public final I2 b(String str) {
        I2 i2D = d(c(str));
        a(i2D);
        return i2D;
    }

    public final I2 b(H2 h2) {
        I2 i2D = d(h2);
        a(i2D);
        return i2D;
    }

    public final O0.a b(int i) {
        O0.a aVar;
        synchronized (this.j) {
            aVar = (O0.a) this.j.computeIfAbsent(Integer.valueOf(i), new Function() { // from class: sk0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return new O0.a(((Integer) obj).intValue());
                }
            });
        }
        return aVar;
    }

    public final boolean b(C0322w2 c0322w2) {
        return c0322w2.g == this.c1;
    }

    public static /* synthetic */ IA b(C0333y c0333y, I2 i2) {
        IA iaH = ((C0229j) c0333y.g()).h(i2);
        return C2441qd.a((C0333y<? extends C0229j>) c0333y, iaH, iaH);
    }

    public final I2 e() {
        I2 i2D = d(c(C0929Wj.I(Package.class.getName())));
        a(i2D);
        return i2D;
    }

    public I2 e(String str) {
        return c(c(str));
    }

    public final Q0 e(int i) {
        Q0 q0;
        synchronized (this.m) {
            q0 = (Q0) this.m.computeIfAbsent(Integer.valueOf(i), new Function() { // from class: nk0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return new Q0(((Integer) obj).intValue());
                }
            });
        }
        return q0;
    }

    public final I2 d(I2 i2) {
        if (k6 || i2.T0()) {
            return (I2) this.S5.get(i2);
        }
        x1f.a();
        return null;
    }

    public final I2 d(H2 h2) {
        boolean z = k6;
        if (!z && h2 == null) {
            x1f.a();
            return null;
        }
        I2 i2 = (I2) this.d.get(h2);
        if (i2 == null) {
            i2 = new I2(h2);
            if (!z && !i2.I0() && !i2.M0() && !i2.T0() && !i2.W0()) {
                throw new AssertionError(h2.toString());
            }
            if (!z && g6.containsKey(i2)) {
                x1f.a();
                return null;
            }
            this.d.put(h2, i2);
        }
        return i2;
    }

    public final synchronized I2 d(String str) {
        I2 i2D;
        i2D = d(c(str));
        a(i2D);
        return i2D;
    }

    public final P0 d(int i) {
        P0 p0;
        synchronized (this.l) {
            p0 = (P0) this.l.computeIfAbsent(Integer.valueOf(i), new Function() { // from class: rk0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return new P0(((Integer) obj).intValue());
                }
            });
        }
        return p0;
    }

    public final Optional a(C0322w2 c0322w2, Predicate predicate, E2 e2) {
        return Optional.of(a(c0322w2.f, e2, c0322w2.g)).filter(predicate);
    }

    public final C0322w2 a(H2 h2, I2 i2) {
        return a((I2) this.S5.get(i2), a(i2, new I2[0]), h2);
    }

    public final Set a() {
        C0860Ts c0860Ts = this.S5;
        c0860Ts.getClass();
        return (Set) new C1311dN(c0860Ts).stream().map(new Function() { // from class: vk0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a((Map.Entry) obj);
            }
        }).collect(Collectors.toSet());
    }

    public final /* synthetic */ C0322w2 a(Map.Entry entry) {
        I2 i2 = (I2) entry.getKey();
        H2 h2 = ((I2) entry.getValue()).f;
        return a(h2, this.j0, h2, new H2[]{i2.f});
    }

    public void a(boolean z) {
        this.d6 = z;
    }

    public static AbstractC0259n1 a(Map map, X3 x3) {
        if (!k6 && g6.containsKey(x3)) {
            x1f.a();
            return null;
        }
        AbstractC0259n1 abstractC0259n1 = (AbstractC0259n1) map.putIfAbsent(x3, x3);
        return abstractC0259n1 == null ? x3 : abstractC0259n1;
    }

    public final H2 a(int i, byte[] bArr) {
        return (H2) a(this.c, new H2(i, bArr));
    }

    public final H2 a(String str) {
        int i = 0;
        while (true) {
            int i2 = i + 1;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            if (i > 0) {
                sb.append("$");
                sb.append(i);
            }
            String string = sb.toString();
            if (((H2) this.c.get(new H2(string))) == null) {
                return c(string);
            }
            i = i2;
        }
    }

    public final Object a(Function function, String str, I2 i2, int i) {
        int i3 = 0;
        while (true) {
            if (!k6 && i3 >= 1000) {
                x1f.a();
                return null;
            }
            int i4 = i + i3;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            if (i2 != null) {
                sb.append('$');
                sb.append(i2.m0().replace('.', '$'));
            }
            if (i4 > 0) {
                sb.append("$");
                sb.append(i4);
            }
            Optional optional = (Optional) function.apply(c(sb.toString()));
            if (optional.isPresent()) {
                return optional.get();
            }
            i3++;
        }
    }

    public final C0322w2 a(String str, E2 e2, I2 i2, Predicate predicate) {
        return a(str, null, e2, i2, predicate, 0);
    }

    public final C0322w2 a(String str, I2 i2, final E2 e2, final I2 i3, final Predicate predicate, int i) {
        return (C0322w2) a(new Function() { // from class: qk0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(i3, e2, predicate, (H2) obj);
            }
        }, str, i2, i);
    }

    public final /* synthetic */ Optional a(I2 i2, E2 e2, Predicate predicate, H2 h2) {
        C0322w2 c0322w2A = a(i2, e2, h2);
        if (predicate.test(c0322w2A)) {
            return Optional.of(c0322w2A);
        }
        return Optional.empty();
    }

    public final B2 a(String str, final E2 e2, final Predicate predicate) {
        return (B2) a(new Function() { // from class: lk0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return B1.a(e2, predicate, (H2) obj);
            }
        }, str, (I2) null, 0);
    }

    public static Optional a(E2 e2, Predicate predicate, H2 h2) {
        int i = B2.b;
        A2 a2 = new A2(e2, h2);
        if (predicate.test(a2)) {
            return Optional.of(a2);
        }
        return Optional.empty();
    }

    public final C0245l1 a(final I2 i2, final I2 i3, String str, final Predicate predicate) {
        return (C0245l1) a(new Function() { // from class: pk0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(i2, i3, predicate, (H2) obj);
            }
        }, str, (I2) null, 0);
    }

    public final /* synthetic */ Optional a(I2 i2, I2 i3, Predicate predicate, H2 h2) {
        C0245l1 c0245l1A = a(i2, i3, h2);
        return predicate.test(c0245l1A) ? Optional.of(c0245l1A) : Optional.empty();
    }

    public final C0322w2 a(final C0322w2 c0322w2, List list, final Predicate predicate, Consumer consumer) {
        if (k6 || c0322w2.b(this)) {
            return a(c0322w2.i, list, new Function() { // from class: ik0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.a(c0322w2, predicate, (E2) obj);
                }
            }, consumer);
        }
        x1f.a();
        return null;
    }

    public final C0322w2 a(final C0322w2 c0322w2, final I2 i2, final Predicate predicate) {
        if (k6 || c0322w2.b(this)) {
            return a(c0322w2.i, new Bc0(new Supplier() { // from class: xk0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return B1.i(i2);
                }
            }), new Function() { // from class: hk0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.b(c0322w2, predicate, (E2) obj);
                }
            }, C0822Sg.b());
        }
        x1f.a();
        return null;
    }

    public static /* synthetic */ S0 a(S0 s0) {
        return s0;
    }

    public final void a(I2 i2) {
        if (i2.I0()) {
            i2 = i2.a(this);
        }
        if (i2.M0()) {
            this.a.add(i2);
        }
    }

    public I2 a(ClassReference classReference) {
        return e(classReference.getDescriptor());
    }

    public I2 a(int i, I2 i2) {
        if (!k6 && i <= 0) {
            x1f.a();
            return null;
        }
        return e("[".repeat(i) + i2.Z0());
    }

    public C0245l1 a(I2 i2, I2 i3, H2 h2) {
        return (C0245l1) a(this.e, new C0245l1(i2, i3, h2, this.d6));
    }

    public C0245l1 a(I2 i2, I2 i3, String str) {
        return a(i2, i3, c(str));
    }

    public C0245l1 a(FieldReference fieldReference) {
        return a(e(fieldReference.getHolderClass().getDescriptor()), e(fieldReference.getFieldType().getDescriptor()), fieldReference.getFieldName());
    }

    public E2 a(I2 i2, K2 k2) {
        return (E2) a(this.f, new E2(i2, k2));
    }

    public E2 a(I2 i2, I2... i2Arr) {
        return a(i2, i2Arr.length == 0 ? K2.n0() : new K2(i2Arr));
    }

    public E2 a(I2 i2, List<I2> list) {
        return a(i2, (I2[]) list.toArray(I2.h));
    }

    public final E2 a(E2 e2, Function function, ConcurrentHashMap concurrentHashMap) {
        E2 e2A;
        I2[] i2Arr;
        if (!k6 && concurrentHashMap == null) {
            x1f.a();
            return null;
        }
        E2 e3 = (E2) concurrentHashMap.get(e2);
        if (e3 != null) {
            return e3;
        }
        I2 i2 = (I2) function.apply(e2.e);
        I2[] i2Arr2 = e2.f.b;
        C1022Zy c1022Zy = new C1022Zy();
        for (int i = 0; i < i2Arr2.length; i++) {
            I2 i3 = (I2) function.apply(i2Arr2[i]);
            if (i3 != i2Arr2[i]) {
                c1022Zy.put(Integer.valueOf(i), i3);
            }
        }
        if (!c1022Zy.isEmpty()) {
            i2Arr2 = (I2[]) com.android.tools.r8.internal.R3.a(I2[].class, i2Arr2, c1022Zy);
        }
        I2 i4 = e2.e;
        if (i2 == i4 && i2Arr2 == e2.f.b) {
            e2A = e2;
        } else {
            boolean z = k6;
            if (!z && i2 != i4 && i2.equals(i4)) {
                x1f.a();
                return null;
            }
            if (!z && i2Arr2 != (i2Arr = e2.f.b) && Arrays.equals(i2Arr2, i2Arr)) {
                x1f.a();
                return null;
            }
            e2A = a(i2, i2Arr2);
        }
        concurrentHashMap.put(e2, e2A);
        return e2A;
    }

    public C0322w2 a(I2 i2, E2 e2, H2 h2) {
        return (C0322w2) a(this.g, new C0322w2(i2, e2, h2, this.d6));
    }

    public C0322w2 a(I2 i2, E2 e2, String str) {
        return a(i2, e2, c(str));
    }

    public C0322w2 a(MethodReference methodReference) {
        H2 h2C;
        final H2[] h2Arr = new H2[methodReference.getFormalTypes().size()];
        List<TypeReference> formalTypes = methodReference.getFormalTypes();
        InterfaceC2762uL interfaceC2762uL = new InterfaceC2762uL() { // from class: wk0
            @Override // com.android.tools.r8.internal.InterfaceC2762uL
            public final void accept(Object obj, int i) {
                this.a.a(h2Arr, (TypeReference) obj, i);
            }
        };
        boolean z = C2847vL.a;
        for (int i = 0; i < formalTypes.size(); i++) {
            interfaceC2762uL.accept(formalTypes.get(i), i);
        }
        H2 h2C2 = c(methodReference.getHolderClass().getDescriptor());
        H2 h2C3 = c(methodReference.getMethodName());
        if (methodReference.getReturnType() == null) {
            h2C = this.E;
        } else {
            h2C = c(methodReference.getReturnType().getDescriptor());
        }
        return a(h2C2, h2C3, h2C, h2Arr);
    }

    public final /* synthetic */ void a(H2[] h2Arr, TypeReference typeReference, int i) {
        h2Arr[i] = c(typeReference.getDescriptor());
    }

    public final C0336y2 a(EnumC0329x2 enumC0329x2, AbstractC0287r2 abstractC0287r2, boolean z, C0322w2 c0322w2) {
        return (C0336y2) a(this.h, new C0336y2(enumC0329x2, abstractC0287r2, z, c0322w2));
    }

    public C0322w2 a(H2 h2, H2 h3, H2 h4, H2[] h2Arr) {
        I2 i2C = c(h2);
        I2 i2C2 = c(h4);
        I2[] i2Arr = new I2[h2Arr.length];
        for (int i = 0; i < h2Arr.length; i++) {
            i2Arr[i] = c(h2Arr[i]);
        }
        return a(i2C, a(i2C2, i2Arr), h3);
    }

    public final N0 a(int i) {
        N0 n0;
        synchronized (this.i) {
            n0 = (N0) this.i.computeIfAbsent(Integer.valueOf(i), new Function() { // from class: kk0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return new N0(((Integer) obj).intValue());
                }
            });
        }
        return n0;
    }

    public final O0.c a(H2 h2) {
        O0.c cVar;
        synchronized (this.p) {
            cVar = (O0.c) this.p.computeIfAbsent(h2, new Function() { // from class: jk0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return new O0.c((H2) obj);
                }
            });
        }
        return cVar;
    }

    public final S0 a(AbstractC2004lX abstractC2004lX) {
        S0 s0;
        synchronized (this.q) {
            s0 = (S0) this.q.computeIfAbsent(new S0(abstractC2004lX), new Function() { // from class: mk0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return B1.a((S0) obj);
                }
            });
        }
        return s0;
    }

    public final boolean a(C0322w2 c0322w2) {
        return c0322w2.g == this.d1;
    }

    public final AbstractC1120b40 a(final I2 i2, final C2427qS c2427qS, final C0333y c0333y) {
        AbstractC2624sj0 abstractC2624sj0A = null;
        if (i2.I0()) {
            AbstractC1120b40 abstractC1120b40 = (AbstractC1120b40) this.t.get(i2);
            if (abstractC1120b40 != null) {
                return abstractC1120b40.a(c2427qS);
            }
            I2 i2A = i2.a(1, this);
            C2427qS c2427qSH = C2427qS.h();
            if (i2A == e6) {
                if (!AbstractC2624sj0.a && c2427qSH.d()) {
                    x1f.a();
                    return null;
                }
                abstractC2624sj0A = AbstractC2624sj0.m();
            } else {
                boolean z = AbstractC2624sj0.a;
                if (i2A.T0()) {
                    if (!AbstractC2005lY.b && !i2A.T0()) {
                        x1f.a();
                        return null;
                    }
                    abstractC2624sj0A = AbstractC2005lY.a((char) i2A.f.f[0], true);
                } else {
                    abstractC2624sj0A = c0333y.a().a(i2A, c2427qSH, c0333y);
                }
            }
        }
        final AbstractC2624sj0 abstractC2624sj0 = abstractC2624sj0A;
        return ((AbstractC1120b40) this.t.computeIfAbsent(i2, new Function() { // from class: ok0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(i2, c0333y, c2427qS, abstractC2624sj0, (I2) obj);
            }
        })).a(c2427qS);
    }

    public final AbstractC1120b40 a(I2 i2, C0333y c0333y, C2427qS c2427qS, AbstractC2624sj0 abstractC2624sj0, I2 i3) {
        AbstractC2173nV abstractC2173nVA;
        E0 e0D;
        if (i2.M0()) {
            if (!c0333y.o()) {
                return C2441qd.a(i2, c2427qS);
            }
            if (!k6 && !c0333y.g().h()) {
                x1f.a();
                return null;
            }
            if (!C0333y.X && !i2.M0()) {
                x1f.a();
                return null;
            }
            if (!c0333y.o() || (e0D = c0333y.d(i2)) == null) {
                abstractC2173nVA = AbstractC2173nV.c;
            } else {
                abstractC2173nVA = AbstractC2173nV.a(e0D.isInterface());
            }
            if (abstractC2173nVA.d()) {
                return C2441qd.a(this.a2, c2427qS, (C0333y<? extends C0229j>) c0333y.U(), IA.c(i2));
            }
            return C2441qd.b(i2, c2427qS, (C0333y<? extends C0229j>) c0333y.U());
        }
        if (k6 || i2.I0()) {
            return com.android.tools.r8.internal.Q3.a(abstractC2624sj0, c2427qS);
        }
        x1f.a();
        return null;
    }

    public final IA a(final C0333y c0333y, I2 i2) {
        return (IA) this.u.computeIfAbsent(i2, new Function() { // from class: tk0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return B1.b(c0333y, (I2) obj);
            }
        });
    }

    public final synchronized void a(Consumer consumer) {
        new ArrayList(this.d.values()).forEach(consumer);
    }
}
