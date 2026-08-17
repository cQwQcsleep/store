package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0231j1;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import defpackage.cx0;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.v10, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2819v10 {
    public final com.android.tools.r8.graph.H2 A;
    public final com.android.tools.r8.graph.H2 B;
    public final com.android.tools.r8.graph.H2 C;
    public final com.android.tools.r8.graph.E2 D;
    public final com.android.tools.r8.graph.E2 E;
    public final C0322w2 F;
    public final C0322w2 G;
    public final C0322w2 H;
    public final C0245l1 I;
    public final C0245l1 J;
    public final com.android.tools.r8.graph.B1 a;
    public final com.android.tools.r8.graph.I2 b;
    public final com.android.tools.r8.graph.I2 c;
    public final com.android.tools.r8.graph.I2 d;
    public final com.android.tools.r8.graph.I2 e;
    public final com.android.tools.r8.graph.I2 f;
    public final com.android.tools.r8.graph.I2 g;
    public final com.android.tools.r8.graph.I2 h;
    public final com.android.tools.r8.graph.I2 i;
    public final com.android.tools.r8.graph.I2 j;
    public final com.android.tools.r8.graph.I2 k;
    public final com.android.tools.r8.graph.I2 l;
    public final com.android.tools.r8.graph.I2 m;
    public final com.android.tools.r8.graph.I2 n;
    public final com.android.tools.r8.graph.I2 o;
    public final com.android.tools.r8.graph.I2 p;
    public final C2393q10 q;
    public final C2648t10 r;
    public final C2477r10 s;
    public final C2563s10 t;
    public final C2734u10 u;
    public final com.android.tools.r8.graph.H2 v;
    public final com.android.tools.r8.graph.H2 w;
    public final com.android.tools.r8.graph.H2 x;
    public final com.android.tools.r8.graph.H2 y;
    public final com.android.tools.r8.graph.H2 z;

    public C2819v10(com.android.tools.r8.graph.B1 b1) {
        this.a = b1;
        this.b = b1.e("Lcom/google/protobuf/Internal$EnumLite;");
        this.c = b1.e("Lcom/google/protobuf/Internal$EnumLiteMap;");
        this.d = b1.e("Lcom/google/protobuf/Internal$EnumVerifier;");
        this.e = b1.e("Lcom/google/protobuf/GeneratedMessageLite$ExtendableMessage;");
        this.f = b1.e("Lcom/google/protobuf/GeneratedMessageLite$ExtensionDescriptor;");
        this.g = b1.e("Lcom/google/protobuf/ExtensionRegistryLite;");
        com.android.tools.r8.graph.I2 i2E = b1.e("Lcom/google/protobuf/GeneratedMessageLite$GeneratedExtension;");
        this.h = i2E;
        com.android.tools.r8.graph.I2 i2E2 = b1.e("Lcom/google/protobuf/GeneratedMessageLite;");
        this.i = i2E2;
        this.j = b1.e("Lcom/google/protobuf/GeneratedMessageLite$Builder;");
        this.k = b1.e("Lcom/google/protobuf/GeneratedMessageLite$ExtendableBuilder;");
        this.l = b1.e("Lcom/google/protobuf/GeneratedMessageLite$ExtendableMessage;");
        com.android.tools.r8.graph.I2 i2E3 = b1.e("Lcom/google/protobuf/RawMessageInfo;");
        this.m = i2E3;
        com.android.tools.r8.graph.I2 i2E4 = b1.e("Lcom/google/protobuf/MessageLite;");
        this.n = i2E4;
        com.android.tools.r8.graph.I2 i2E5 = b1.e("Lcom/google/protobuf/GeneratedMessageLite$MethodToInvoke;");
        this.o = i2E5;
        this.p = b1.e("Lcom/google/protobuf/WireFormat$FieldType;");
        this.v = b1.c("DEFAULT_INSTANCE");
        this.w = b1.c("INSTANCE");
        this.x = b1.c("internalValueMap");
        com.android.tools.r8.graph.H2 h2C = b1.c("dynamicMethod");
        this.y = h2C;
        this.z = b1.c("findLiteExtensionByNumber");
        this.A = b1.c("newBuilder");
        this.B = b1.c("findValueByNumber");
        this.C = b1.c("Lcom/google/protobuf/");
        com.android.tools.r8.graph.I2 i2 = b1.a2;
        com.android.tools.r8.graph.E2 e2A = b1.a(i2, i2E5, i2, i2);
        this.D = e2A;
        this.E = b1.a(i2E, i2E4, b1.B1);
        this.F = b1.a(i2E2, e2A, h2C);
        this.G = b1.a(i2E2, b1.a(b1.a2, i2E4, b1.Y1, b1.d2), b1.c("newMessageInfo"));
        this.H = b1.a(i2E3, b1.a(b1.E1, i2E4, b1.Y1, b1.d2), b1.c1);
        this.I = b1.a(i2E3, b1.Y1, "info");
        this.J = b1.a(i2E3, b1.d2, "objects");
        this.q = new C2393q10(this, b1);
        this.r = new C2648t10(this, b1);
        this.s = new C2477r10(this, b1);
        this.t = new C2563s10(this, b1);
        this.u = new C2734u10(this, b1);
    }

    public final void a(Consumer consumer) {
        C2393q10 c2393q10 = this.q;
        consumer.accept(c2393q10.a);
        consumer.accept(c2393q10.b);
        C2648t10 c2648t10 = this.r;
        consumer.accept(c2648t10.a);
        consumer.accept(c2648t10.b);
        consumer.accept(c2648t10.c);
        consumer.accept(c2648t10.d);
        consumer.accept(c2648t10.e);
        consumer.accept(this.s.a);
        C2563s10 c2563s10 = this.t;
        consumer.accept(c2563s10.a);
        consumer.accept(c2563s10.b);
        this.u.getClass();
        consumer.accept(this.F);
        consumer.accept(this.G);
        consumer.accept(this.H);
    }

    public final boolean b(C0322w2 c0322w2) {
        C2648t10 c2648t10 = this.r;
        return c0322w2 == c2648t10.b || c0322w2 == c2648t10.c;
    }

    public final boolean c(C0322w2 c0322w2) {
        if (c0322w2.i != this.E) {
            return false;
        }
        com.android.tools.r8.graph.H2 h2 = c0322w2.g;
        com.android.tools.r8.graph.H2 h3 = this.z;
        h2.getClass();
        return h2.b(h3.f) && c0322w2.f != this.g;
    }

    public final boolean a(C0322w2 c0322w2) {
        return c0322w2.g == this.y && c0322w2.i == this.D;
    }

    public boolean a(C0231j1 c0231j1) {
        return a(c0231j1.getReference());
    }

    public final boolean a(com.android.tools.r8.graph.B5 b5) {
        return c(b5.getReference());
    }

    public final boolean a(AbstractC1047aC abstractC1047aC) {
        if (abstractC1047aC.U2().d(this.G)) {
            return true;
        }
        if (abstractC1047aC.a(this.a)) {
            C2543rl0 c2543rl0V2 = abstractC1047aC.Z().V2();
            if (c2543rl0V2.c(new cx0()) && c2543rl0V2.p().t0().L2() == this.m) {
                return true;
            }
        }
        return false;
    }
}
