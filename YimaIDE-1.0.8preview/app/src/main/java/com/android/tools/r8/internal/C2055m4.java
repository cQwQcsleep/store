package com.android.tools.r8.internal;

import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.profile.art.ArtProfileMethodRuleBuilder;
import com.android.tools.r8.references.MethodReference;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.m4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2055m4 extends AbstractC2824v4 implements InterfaceC2049m1 {
    public static final /* synthetic */ boolean d = true;
    public final C0322w2 b;
    public final C2140n4 c;

    public C2055m4(C0322w2 c0322w2, C2140n4 c2140n4) {
        if (!d && c2140n4.a == 0) {
            x1f.a();
            throw null;
        }
        this.b = c0322w2;
        this.c = c2140n4;
    }

    public static a d() {
        return new a();
    }

    @Override // com.android.tools.r8.internal.AbstractC2824v4
    public final void a(OutputStreamWriter outputStreamWriter) throws IOException {
        C2140n4 c2140n4 = this.c;
        if (c2140n4.isHot()) {
            outputStreamWriter.write(72);
        }
        if (c2140n4.isStartup()) {
            outputStreamWriter.write(83);
        }
        if (c2140n4.isPostStartup()) {
            outputStreamWriter.write(80);
        }
        outputStreamWriter.write(this.b.l0());
    }

    @Override // com.android.tools.r8.internal.AbstractC2824v4
    public final com.android.tools.r8.graph.F2 c() {
        return e();
    }

    public C0322w2 e() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            C2055m4 c2055m4 = (C2055m4) obj;
            if (this.b.equals(c2055m4.b) && this.c.equals(c2055m4.c)) {
                return true;
            }
        }
        return false;
    }

    public C2140n4 f() {
        return this.c;
    }

    @Override // com.android.tools.r8.internal.InterfaceC2049m1
    /* JADX INFO: renamed from: getReference */
    public final C0322w2 c() {
        return e();
    }

    public final int hashCode() {
        return this.b.hashCode();
    }

    public final String toString() {
        return this.c.toString() + this.b.l0();
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.m4$a */
    public static class a extends AbstractC2739u4 implements ArtProfileMethodRuleBuilder, InterfaceC1963l1 {
        public static final /* synthetic */ boolean d = true;
        public final com.android.tools.r8.graph.B1 a;
        public C0322w2 b;
        public final C2140n4.a c;

        public a() {
            this.c = C2140n4.a();
            this.a = null;
        }

        @Override // com.android.tools.r8.internal.InterfaceC1963l1
        public final InterfaceC1963l1 a(InterfaceC1963l1 interfaceC1963l1, Runnable runnable) {
            C2140n4.a aVar = this.c;
            int i = aVar.a;
            int i2 = ((a) interfaceC1963l1).c.a | i;
            aVar.a = i2;
            if (i2 != i) {
                runnable.run();
            }
            return this;
        }

        @Override // com.android.tools.r8.internal.AbstractC2739u4
        public final a b() {
            return this;
        }

        @Override // com.android.tools.r8.internal.AbstractC2739u4
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public C2055m4 c() {
            return new C2055m4(this.b, this.c.a());
        }

        @Override // com.android.tools.r8.profile.art.ArtProfileMethodRuleBuilder
        public final ArtProfileMethodRuleBuilder setMethodReference(MethodReference methodReference) {
            if (d || this.a != null) {
                this.b = MO.a(methodReference, this.a);
                return this;
            }
            x1f.a();
            return null;
        }

        @Override // com.android.tools.r8.profile.art.ArtProfileMethodRuleBuilder
        public final ArtProfileMethodRuleBuilder setMethodRuleInfo(Consumer consumer) {
            this.c.a = 0;
            return a((Consumer<? super C2140n4.a>) consumer);
        }

        public a(com.android.tools.r8.graph.B1 b1) {
            this.c = C2140n4.a();
            this.a = b1;
        }

        @Override // com.android.tools.r8.internal.InterfaceC1963l1
        public final InterfaceC1963l1 a(InterfaceC1963l1 interfaceC1963l1) {
            C2140n4.a aVar = this.c;
            aVar.a = ((a) interfaceC1963l1).c.a | aVar.a;
            return this;
        }

        @Override // com.android.tools.r8.internal.InterfaceC1963l1
        public final InterfaceC1963l1 a(InterfaceC2049m1 interfaceC2049m1) {
            C2140n4.a aVar = this.c;
            C2140n4 c2140n4F = ((C2055m4) interfaceC2049m1).f();
            aVar.a = c2140n4F.a | aVar.a;
            return this;
        }

        @Override // com.android.tools.r8.internal.InterfaceC1963l1
        public final InterfaceC1963l1 a() {
            this.c.d();
            return this;
        }

        @Override // com.android.tools.r8.internal.InterfaceC1963l1
        public final InterfaceC1963l1 a(C0322w2 c0322w2) {
            this.b = c0322w2;
            return this;
        }

        public a a(Consumer<? super C2140n4.a> consumer) {
            consumer.accept(this.c);
            return this;
        }
    }

    @Override // com.android.tools.r8.internal.AbstractC2824v4
    public final Object a(InterfaceC2022lh0 interfaceC2022lh0, InterfaceC2022lh0 interfaceC2022lh1) {
        return interfaceC2022lh1.apply(this);
    }

    @Override // com.android.tools.r8.internal.AbstractC2824v4
    public final void a(InterfaceC1936kh0 interfaceC1936kh0, InterfaceC1936kh0 interfaceC1936kh1) throws Throwable {
        interfaceC1936kh1.accept(this);
    }
}
