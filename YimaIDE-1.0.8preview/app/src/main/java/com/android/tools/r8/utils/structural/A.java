package com.android.tools.r8.utils.structural;

import com.android.tools.r8.internal.TK;
import com.android.tools.r8.utils.structural.A;
import com.android.tools.r8.utils.structural.AbstractC3519a;
import com.android.tools.r8.utils.structural.x;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class A<T, V extends A<T, V>> {
    public static /* synthetic */ int a(x xVar, x xVar2, AbstractC3519a abstractC3519a) {
        if (xVar == null || xVar2 == null) {
            return abstractC3519a.a(xVar != null, xVar2 != null);
        }
        return xVar.a(xVar2, abstractC3519a);
    }

    public abstract A a();

    public abstract A a(Function function);

    public abstract A a(Predicate predicate);

    public abstract A a(Predicate predicate, Function function, v vVar, w wVar);

    public abstract V a(ToIntFunction<T> toIntFunction);

    public abstract A a(ToLongFunction toLongFunction);

    public final A b(Function function) {
        TK tk = TK.a;
        return b(function.andThen(new Function() { // from class: g
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Arrays.asList((Object[]) obj).iterator();
            }
        }), tk, tk);
    }

    public abstract A b(Function function, v vVar, w wVar);

    public abstract A b(Predicate predicate);

    public abstract A c(Function function);

    public abstract A d(Function function);

    public final <S extends x<S>> V e(Function<T, S> function) {
        return (V) a((Function) function, new Predicate() { // from class: r
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return A.b(obj);
            }
        });
    }

    public final A f(Function function) {
        return i(function.andThen(new Function() { // from class: q
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Arrays.asList((x[]) obj).iterator();
            }
        }));
    }

    public final A g(Function function) {
        return b(function.andThen(new Function() { // from class: m
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Arrays.asList((x[]) obj).iterator();
            }
        }), new v() { // from class: n
            @Override // com.android.tools.r8.utils.structural.v
            public final int a(Object obj, Object obj2, AbstractC3519a abstractC3519a) {
                return A.a((x) obj, (x) obj2, abstractC3519a);
            }
        }, new w() { // from class: o
            @Override // com.android.tools.r8.utils.structural.w
            public final void a(Object obj, com.android.tools.r8.utils.structural.o oVar) {
                A.a((x) obj, oVar);
            }
        });
    }

    public final A h(Function function) {
        return i(function.andThen(new defpackage.p()));
    }

    public final A i(Function function) {
        return b(function, new defpackage.e(), new defpackage.h());
    }

    public final A j(final Function function) {
        return a(function, new Predicate() { // from class: j
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return A.a(function, obj);
            }
        });
    }

    public abstract A k(Function function);

    public static /* synthetic */ boolean b(Object obj) {
        return true;
    }

    public static /* synthetic */ boolean a(Object obj) {
        return true;
    }

    public final A a(Function function, v vVar, w wVar) {
        return a(new Predicate() { // from class: l
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return A.a(obj);
            }
        }, function, vVar, wVar);
    }

    public final A a(Function function, u uVar) {
        return b(function.andThen(new defpackage.p()), uVar, uVar);
    }

    public static /* synthetic */ boolean a(Function function, Object obj) {
        return function.apply(obj) != null;
    }

    public final A a(Function function, Predicate predicate) {
        return a(predicate, function, new defpackage.e(), new defpackage.h());
    }

    public static void a(x xVar, o oVar) {
        if (xVar == null) {
            ((q) oVar).a.a(0);
        } else {
            xVar.a(oVar);
        }
    }
}
