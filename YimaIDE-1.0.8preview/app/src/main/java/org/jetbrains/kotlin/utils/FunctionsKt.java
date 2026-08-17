package org.jetbrains.kotlin.utils;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.kotlin.utils.FunctionsKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\u001a\u0018\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00040\u0001\"\u0004\b\u0000\u0010\u0004\u001a\u0018\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\u00060\u0001\"\u0004\b\u0000\u0010\u0004\u001a$\u0010\t\u001a\u0010\u0012\u0004\u0012\u0002H\u0004\u0012\u0006\u0012\u0004\u0018\u0001H\n0\u0001\"\u0004\b\u0000\u0010\u0004\"\b\b\u0001\u0010\n*\u00020\u0002\u001a\u0018\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u00020\f0\u0001\"\u0004\b\u0000\u0010\u0004\u001a\u0006\u0010\u0017\u001a\u00020\f\u001aG\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\n0\u0001\"\u0004\b\u0000\u0010\u0019\"\u0004\b\u0001\u0010\u001a\"\u0004\b\u0002\u0010\n*\u0014\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001a\u0012\u0004\u0012\u0002H\n0\u00102\u0006\u0010\u001b\u001a\u0002H\u001a¢\u0006\u0002\u0010\u001c\u001aY\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\n0\u0010\"\u0004\b\u0000\u0010\u0019\"\u0004\b\u0001\u0010\u001d\"\u0004\b\u0002\u0010\u001a\"\u0004\b\u0003\u0010\n*\u001a\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001d\u0012\u0004\u0012\u0002H\u001a\u0012\u0004\u0012\u0002H\n0\u00142\u0006\u0010\u001b\u001a\u0002H\u001a¢\u0006\u0002\u0010\u001e\u001a[\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\n0\u0001\"\u0004\b\u0000\u0010\u0019\"\u0004\b\u0001\u0010\u001f\"\u0004\b\u0002\u0010 \"\u0004\b\u0003\u0010\n*\u001a\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001f\u0012\u0004\u0012\u0002H \u0012\u0004\u0012\u0002H\n0\u00142\u0006\u0010!\u001a\u0002H\u001f2\u0006\u0010\"\u001a\u0002H ¢\u0006\u0002\u0010#\"\u001e\u0010\u0000\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001c\u0010\u0005\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\u00060\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001e\u0010\b\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001f\u0010\u000b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\f0\u0001¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\"'\u0010\u000f\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\f0\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"/\u0010\u0013\u001a \u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0004\u0012\u00020\f0\u0014¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006$"}, d2 = {"IDENTITY", "Lkotlin/Function1;", "", "identity", "T", "ALWAYS_TRUE", "", "alwaysTrue", "ALWAYS_NULL", "alwaysNull", "R", "DO_NOTHING", "", "getDO_NOTHING", "()Lkotlin/jvm/functions/Function1;", "DO_NOTHING_2", "Lkotlin/Function2;", "getDO_NOTHING_2", "()Lkotlin/jvm/functions/Function2;", "DO_NOTHING_3", "Lkotlin/Function3;", "getDO_NOTHING_3", "()Lkotlin/jvm/functions/Function3;", "doNothing", "bind", "Arg1", "Bound", "bound", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;)Lkotlin/jvm/functions/Function1;", "Arg2", "(Lkotlin/jvm/functions/Function3;Ljava/lang/Object;)Lkotlin/jvm/functions/Function2;", "Bound1", "Bound2", "bound1", "bound2", "(Lkotlin/jvm/functions/Function3;Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/jvm/functions/Function1;", "org.jetbrains.kotlin:util.runtime"}, k = 2, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final class FunctionsKt {
    private static final Function1<Object, Object> IDENTITY = new Function1() { // from class: xs5
        public final Object invoke(Object obj) {
            return FunctionsKt.e(obj);
        }
    };
    private static final Function1<Object, Boolean> ALWAYS_TRUE = new Function1() { // from class: ys5
        public final Object invoke(Object obj) {
            return Boolean.valueOf(FunctionsKt.c(obj));
        }
    };
    private static final Function1<Object, Object> ALWAYS_NULL = new Function1() { // from class: org.jetbrains.kotlin.utils.FunctionsKt$ALWAYS_NULL$1
        public final Void invoke(Object obj) {
            return null;
        }
    };
    private static final Function1<Object, Unit> DO_NOTHING = new Function1() { // from class: zs5
        public final Object invoke(Object obj) {
            return FunctionsKt.g(obj);
        }
    };
    private static final Function2<Object, Object, Unit> DO_NOTHING_2 = new Function2() { // from class: at5
        public final Object invoke(Object obj, Object obj2) {
            return FunctionsKt.b(obj, obj2);
        }
    };
    private static final Function3<Object, Object, Object, Unit> DO_NOTHING_3 = new Function3() { // from class: bt5
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return FunctionsKt.f(obj, obj2, obj3);
        }
    };

    public static Object a(Function2 function2, Object obj, Object obj2) {
        return function2.invoke(obj2, obj);
    }

    public static final <T, R> Function1<T, R> alwaysNull() {
        Function1<Object, Object> function1 = ALWAYS_NULL;
        function1.getClass();
        return (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function1, 1);
    }

    public static final <T> Function1<T, Boolean> alwaysTrue() {
        return (Function1<T, Boolean>) ALWAYS_TRUE;
    }

    public static Unit b(Object obj, Object obj2) {
        return Unit.INSTANCE;
    }

    public static final <Arg1, Bound, R> Function1<Arg1, R> bind(final Function2<? super Arg1, ? super Bound, ? extends R> function2, final Bound bound) {
        function2.getClass();
        return new Function1() { // from class: dt5
            public final Object invoke(Object obj) {
                return FunctionsKt.a(function2, bound, obj);
            }
        };
    }

    public static boolean c(Object obj) {
        return true;
    }

    public static Object d(Function3 function3, Object obj, Object obj2, Object obj3) {
        return function3.invoke(obj2, obj3, obj);
    }

    public static final <T> Function1<T, Unit> doNothing() {
        return (Function1<T, Unit>) DO_NOTHING;
    }

    public static Object e(Object obj) {
        return obj;
    }

    public static Unit f(Object obj, Object obj2, Object obj3) {
        return Unit.INSTANCE;
    }

    public static Unit g(Object obj) {
        return Unit.INSTANCE;
    }

    public static final Function1<Object, Unit> getDO_NOTHING() {
        return DO_NOTHING;
    }

    public static final Function2<Object, Object, Unit> getDO_NOTHING_2() {
        return DO_NOTHING_2;
    }

    public static final Function3<Object, Object, Object, Unit> getDO_NOTHING_3() {
        return DO_NOTHING_3;
    }

    public static Object h(Function3 function3, Object obj, Object obj2, Object obj3) {
        return function3.invoke(obj3, obj, obj2);
    }

    public static final <T> Function1<T, T> identity() {
        Function1<Object, Object> function1 = IDENTITY;
        function1.getClass();
        return (Function1) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function1, 1);
    }

    /* JADX INFO: renamed from: doNothing, reason: collision with other method in class */
    public static final void m1291doNothing() {
    }

    public static final <Arg1, Arg2, Bound, R> Function2<Arg1, Arg2, R> bind(final Function3<? super Arg1, ? super Arg2, ? super Bound, ? extends R> function3, final Bound bound) {
        function3.getClass();
        return new Function2() { // from class: ws5
            public final Object invoke(Object obj, Object obj2) {
                return FunctionsKt.d(function3, bound, obj, obj2);
            }
        };
    }

    public static final <Arg1, Bound1, Bound2, R> Function1<Arg1, R> bind(final Function3<? super Arg1, ? super Bound1, ? super Bound2, ? extends R> function3, final Bound1 bound1, final Bound2 bound2) {
        function3.getClass();
        return new Function1() { // from class: ct5
            public final Object invoke(Object obj) {
                return FunctionsKt.h(function3, bound1, bound2, obj);
            }
        };
    }
}
