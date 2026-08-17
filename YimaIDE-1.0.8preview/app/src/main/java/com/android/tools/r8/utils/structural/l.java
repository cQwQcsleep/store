package com.android.tools.r8.utils.structural;

import com.android.tools.r8.graph.F2;
import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class l<T> extends A<T, l<T>> {
    public static final /* synthetic */ boolean c = true;
    public final Object a;
    public int b = 0;

    public l(Object obj) {
        this.a = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.utils.structural.A
    public final A a(Predicate predicate, Function function, v vVar, w wVar) {
        return predicate.test(this.a) ? a(function.apply(this.a).hashCode()) : a(1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.utils.structural.A
    public final A b(Function function, v vVar, w wVar) {
        Iterator it = (Iterator) function.apply(this.a);
        while (it.hasNext()) {
            a(it.next().hashCode());
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.utils.structural.A
    public final A c(Function function) {
        return a(((F2) function.apply(this.a)).hashCode());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.utils.structural.A
    public final A d(Function function) {
        return a(Arrays.hashCode((int[]) function.apply(this.a)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.utils.structural.A
    public final A k(Function function) {
        return a(Arrays.hashCode((short[]) function.apply(this.a)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.utils.structural.A
    public final A b(Predicate predicate) {
        return a(Boolean.hashCode(predicate.test(this.a)));
    }

    public static <T> int a(T t, y<T> yVar) {
        l lVar = new l(t);
        yVar.a(lVar);
        return lVar.b;
    }

    public final l a(int i) {
        this.b = (this.b * 31) + i;
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.utils.structural.A
    public final A a(Predicate predicate) {
        if (c || predicate.test(this.a)) {
            return this;
        }
        x1f.a();
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.utils.structural.A
    public final A a(ToIntFunction toIntFunction) {
        return a(Integer.hashCode(toIntFunction.applyAsInt(this.a)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.utils.structural.A
    public final A a(ToLongFunction toLongFunction) {
        return a(Long.hashCode(toLongFunction.applyAsLong(this.a)));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.utils.structural.A
    public final A a(Function function) {
        return a(Arrays.hashCode((byte[]) function.apply(this.a)));
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A a() {
        return this;
    }
}
