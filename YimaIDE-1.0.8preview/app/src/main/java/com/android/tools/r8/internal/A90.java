package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C1405eW;
import com.android.tools.r8.naming.V;
import com.android.tools.r8.references.Reference;
import com.android.tools.r8.retrace.RetraceFieldResult;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class A90 implements RetraceFieldResult {
    public static final /* synthetic */ boolean d = true;
    public final List a;
    public final AbstractC1523fp b;
    public final C1667ha0 c;

    public A90(C3091y90 c3091y90, List list, AbstractC1523fp abstractC1523fp, C1667ha0 c1667ha0) {
        this.a = list;
        this.b = abstractC1523fp;
        this.c = c1667ha0;
        boolean z = d;
        if (!z && c3091y90 == null) {
            x1f.a();
            throw null;
        }
        if (z || !list.isEmpty()) {
            return;
        }
        x1f.a();
        throw null;
    }

    public final C3176z90 a(C3007x90 c3007x90, com.android.tools.r8.naming.V v) {
        com.android.tools.r8.naming.V.a aVarA = v.b().a();
        return new C3176z90(this, c3007x90, new X90(Reference.field((aVarA.e() ? new W90(true, Reference.classFromDescriptor(C0929Wj.I(aVarA.g()))) : c3007x90.b).a, aVarA.e() ? aVarA.h() : aVarA.a, Reference.typeFromTypeName(aVarA.c))), v);
    }

    @Override // com.android.tools.r8.retrace.RetraceResult
    public final boolean isAmbiguous() {
        if (this.a.size() > 1) {
            return true;
        }
        List list = (List) ((C1405eW) this.a.get(0)).b();
        return list != null && list.size() > 1;
    }

    @Override // com.android.tools.r8.retrace.RetraceFieldResult, com.android.tools.r8.retrace.RetraceResult
    public final boolean isEmpty() {
        List list = this.a;
        return list == null || list.isEmpty() || (this.a.size() == 1 && ((C1405eW) this.a.get(0)).b() == null);
    }

    @Override // com.android.tools.r8.retrace.RetraceResult
    public final Stream stream() {
        return this.a.stream().flatMap(new Function() { // from class: j0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a((C1405eW) obj);
            }
        });
    }

    public final Stream a(C1405eW c1405eW) {
        final C3007x90 c3007x90 = (C3007x90) c1405eW.a();
        List list = (List) c1405eW.b();
        if (list == null) {
            return Stream.of(new C3176z90(this, c3007x90, new Y90(this.b.a(c3007x90.b.a)), null));
        }
        return list.stream().map(new Function() { // from class: k0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a(c3007x90, (V) obj);
            }
        });
    }
}
