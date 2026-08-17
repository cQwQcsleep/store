package com.android.tools.r8.graph;

import com.android.tools.r8.graph.AbstractC0288r3;
import com.android.tools.r8.internal.AbstractC2173nV;
import com.android.tools.r8.internal.C1345dk0;
import com.android.tools.r8.internal.Y6;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: renamed from: com.android.tools.r8.graph.t3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC0302t3 extends AbstractC0330x3 {
    public static final /* synthetic */ boolean e = true;
    public final AbstractC0330x3.a b;
    public final List c;
    public final List d;

    public AbstractC0302t3(AbstractC0330x3.a aVar, ArrayList arrayList, ArrayList arrayList2) {
        boolean z = e;
        if (!z && aVar != null && aVar.d().b0()) {
            x1f.a();
            throw null;
        }
        if (!z && !arrayList2.stream().allMatch(new Predicate() { // from class: fci
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((AbstractC0288r3) obj).x();
            }
        })) {
            x1f.a();
            throw null;
        }
        if (!z) {
            if (arrayList2.size() + arrayList.size() + Y6.a(aVar != null) <= 1) {
                x01.a("Should have been a single or failed result");
                throw null;
            }
        }
        this.b = aVar;
        this.c = arrayList;
        this.d = arrayList2;
    }

    @Override // com.android.tools.r8.graph.AbstractC0330x3
    public final void a(Consumer consumer, Consumer consumer2, Consumer consumer3) {
        AbstractC0330x3.a aVar = this.b;
        if (aVar != null) {
            consumer.accept(aVar);
        }
        this.c.forEach(consumer2);
        this.d.forEach(consumer3);
    }

    @Override // com.android.tools.r8.graph.AbstractC0330x3, com.android.tools.r8.graph.D4
    public final /* bridge */ /* synthetic */ T5 g() {
        return null;
    }

    @Override // com.android.tools.r8.graph.AbstractC0330x3, com.android.tools.r8.graph.D4
    public final boolean j() {
        return this.d.isEmpty();
    }

    @Override // com.android.tools.r8.graph.AbstractC0330x3
    public final boolean s() {
        AbstractC0330x3.a aVar = this.b;
        return aVar != null && aVar.s();
    }

    @Override // com.android.tools.r8.graph.AbstractC0330x3
    public final boolean t() {
        return this.b != null;
    }

    @Override // com.android.tools.r8.graph.AbstractC0330x3
    public final boolean u() {
        AbstractC0330x3.a aVar = this.b;
        return aVar != null && aVar.u();
    }

    @Override // com.android.tools.r8.graph.AbstractC0330x3
    public final boolean v() {
        return t() || !this.c.isEmpty();
    }

    @Override // com.android.tools.r8.graph.AbstractC0330x3
    public final boolean w() {
        return true;
    }

    @Override // com.android.tools.r8.graph.AbstractC0330x3
    public final boolean x() {
        return !this.d.isEmpty();
    }

    @Override // com.android.tools.r8.graph.D4
    public final AbstractC2173nV a(InterfaceC0332x5 interfaceC0332x5, C0333y c0333y, C0229j c0229j) {
        throw new C1345dk0("Should not be called on MultipleFieldResolutionResult");
    }

    @Override // com.android.tools.r8.graph.AbstractC0330x3
    public final E0 a() {
        throw new C1345dk0("Should not be called on MultipleFieldResolutionResult");
    }
}
