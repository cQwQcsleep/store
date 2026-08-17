package com.android.tools.r8.utils.structural;

import com.android.tools.r8.graph.F2;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class p extends A {
    public static final /* synthetic */ boolean c = true;
    public final q a;
    public final Object b;

    public p(Object obj, q qVar) {
        this.b = obj;
        this.a = qVar;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A a(Predicate predicate, Function function, v vVar, w wVar) {
        boolean zTest = predicate.test(this.b);
        this.a.a.a(zTest);
        if (zTest) {
            wVar.a(function.apply(this.b), this.a);
        }
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A b(Function function, v vVar, w wVar) {
        q qVar = this.a;
        Iterator it = (Iterator) function.apply(this.b);
        qVar.getClass();
        while (it.hasNext()) {
            wVar.a(it.next(), qVar);
        }
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A c(Function function) {
        this.a.a((F2) function.apply(this.b));
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A d(Function function) {
        for (int i : (int[]) function.apply(this.b)) {
            this.a.a.a(i);
        }
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A k(Function function) {
        for (short s : (short[]) function.apply(this.b)) {
            this.a.a.a((int) s);
        }
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A a(Predicate predicate) {
        if (c || predicate.test(this.b)) {
            return this;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A a(ToIntFunction toIntFunction) {
        q qVar = this.a;
        qVar.a.a(toIntFunction.applyAsInt(this.b));
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A b(Predicate predicate) {
        q qVar = this.a;
        qVar.a.a(predicate.test(this.b));
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A a(ToLongFunction toLongFunction) {
        q qVar = this.a;
        qVar.a.a(toLongFunction.applyAsLong(this.b));
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A a(Function function) {
        for (byte b : (byte[]) function.apply(this.b)) {
            this.a.a.a((int) b);
        }
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A a() {
        return this;
    }
}
