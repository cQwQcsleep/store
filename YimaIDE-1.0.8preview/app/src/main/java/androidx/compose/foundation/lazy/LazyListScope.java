package androidx.compose.foundation.lazy;

import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@LazyScopeMarker
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bg\u0018\u00002\u00020\u0001JC\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00012\u001c\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\b\t¢\u0006\u0002\b\nH\u0016¢\u0006\u0002\u0010\u000bJ7\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00012\u001c\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\b\t¢\u0006\u0002\b\nH\u0017¢\u0006\u0002\u0010\fJ\u0096\u0001\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2%\b\u0002\u0010\u0004\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00072%\b\u0002\u0010\u0005\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000721\u0010\u0013\u001a-\u0012\u0004\u0012\u00020\b\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00030\u0014¢\u0006\u0002\b\t¢\u0006\u0002\b\nH\u0016¢\u0006\u0002\u0010\u0015Jo\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2%\b\u0002\u0010\u0004\u001a\u001f\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000721\u0010\u0013\u001a-\u0012\u0004\u0012\u00020\b\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\u0004\u0012\u00020\u00030\u0014¢\u0006\u0002\b\t¢\u0006\u0002\b\nH\u0017¢\u0006\u0002\u0010\u0016JC\u0010\u0017\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00012\u001c\u0010\u0006\u001a\u0018\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00030\u0007¢\u0006\u0002\b\t¢\u0006\u0002\b\nH\u0017¢\u0006\u0002\u0010\u000bJI\u0010\u0017\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00012\"\u0010\u0006\u001a\u001e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00030\u0014¢\u0006\u0002\b\t¢\u0006\u0002\b\nH\u0016¢\u0006\u0002\u0010\u0018ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0019À\u0006\u0003"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListScope;", "", "item", "", "key", "contentType", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/LazyItemScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)V", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)V", "items", "count", "", "Lkotlin/ParameterName;", "name", "index", "itemContent", "Lkotlin/Function2;", "(ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "(ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "stickyHeader", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function4;)V", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface LazyListScope {
    static Object a(int i) {
        return null;
    }

    static Unit b(Function4 function4, LazyItemScope lazyItemScope, Composer composer, int i) {
        if ((i & 6) == 0) {
            i |= composer.changed(lazyItemScope) ? 4 : 2;
        }
        if (composer.shouldExecute((i & 19) != 18, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1691919627, i, -1, "androidx.compose.foundation.lazy.LazyListScope.stickyHeader.<anonymous> (LazyDsl.kt:148)");
            }
            function4.invoke(lazyItemScope, 0, composer, Integer.valueOf((i & 14) | 48));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static Unit c(Function3 function3, LazyItemScope lazyItemScope, int i, Composer composer, int i2) {
        if ((i2 & 6) == 0) {
            i2 |= composer.changed(lazyItemScope) ? 4 : 2;
        }
        if (composer.shouldExecute((i2 & 131) != 130, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-764088514, i2, -1, "androidx.compose.foundation.lazy.LazyListScope.stickyHeader.<anonymous> (LazyDsl.kt:122)");
            }
            function3.invoke(lazyItemScope, composer, Integer.valueOf(i2 & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    static /* synthetic */ void item$default(LazyListScope lazyListScope, Object obj, Object obj2, Function3 function3, int i, Object obj3) {
        if (obj3 != null) {
            c41.a("Super calls with default arguments not supported in this target, function: item");
            return;
        }
        if ((i & 1) != 0) {
            obj = null;
        }
        if ((i & 2) != 0) {
            obj2 = null;
        }
        lazyListScope.item(obj, obj2, function3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void items$default(LazyListScope lazyListScope, int i, Function1 function1, Function1 function2, Function4 function4, int i2, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: items");
            return;
        }
        if ((i2 & 2) != 0) {
            function1 = null;
        }
        if ((i2 & 4) != 0) {
            function2 = new Function1() { // from class: androidx.compose.foundation.lazy.LazyListScope.items.1
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    return invoke(((Number) obj2).intValue());
                }

                public final Void invoke(int i3) {
                    return null;
                }
            };
        }
        lazyListScope.items(i, function1, function2, function4);
    }

    static /* synthetic */ void stickyHeader$default(LazyListScope lazyListScope, Object obj, Object obj2, Function3 function3, int i, Object obj3) {
        if (obj3 != null) {
            c41.a("Super calls with default arguments not supported in this target, function: stickyHeader");
            return;
        }
        if ((i & 1) != 0) {
            obj = null;
        }
        if ((i & 2) != 0) {
            obj2 = null;
        }
        lazyListScope.stickyHeader(obj, obj2, function3);
    }

    default void item(Object key, Object contentType, Function3<? super LazyItemScope, ? super Composer, ? super Integer, Unit> content) {
        throw new IllegalStateException("The method is not implemented");
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the non deprecated overload")
    /* synthetic */ default void items(int count, Function1 key, Function4 itemContent) {
        items(count, key, new Function1() { // from class: jv8
            public final Object invoke(Object obj) {
                return LazyListScope.a(((Integer) obj).intValue());
            }
        }, itemContent);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Please use the overload with indexing capabilities.", replaceWith = @ReplaceWith(expression = "stickyHeader(key, contentType, { _ -> content() })", imports = {}))
    /* synthetic */ default void stickyHeader(Object key, Object contentType, final Function3 content) {
        stickyHeader(key, contentType, (Function4<? super LazyItemScope, ? super Integer, ? super Composer, ? super Integer, Unit>) ComposableLambdaKt.composableLambdaInstance(-764088514, true, new Function4() { // from class: iv8
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return LazyListScope.c(content, (LazyItemScope) obj, ((Integer) obj2).intValue(), (Composer) obj3, ((Integer) obj4).intValue());
            }
        }));
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void item(LazyListScope lazyListScope, Object obj, Object obj2, Function3<? super LazyItemScope, ? super Composer, ? super Integer, Unit> function3) {
            LazyListScope.super.item(obj, obj2, function3);
        }

        @Deprecated
        public static void items(LazyListScope lazyListScope, int i, Function1<? super Integer, ? extends Object> function1, Function1<? super Integer, ? extends Object> function2, Function4<? super LazyItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4) {
            LazyListScope.super.items(i, function1, function2, function4);
        }

        @Deprecated
        public static void stickyHeader(LazyListScope lazyListScope, Object obj, Object obj2, Function4<? super LazyItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4) {
            LazyListScope.super.stickyHeader(obj, obj2, function4);
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the non deprecated overload")
    /* synthetic */ default void item(Object key, Function3 content) {
        item(key, null, content);
    }

    default void items(int count, Function1<? super Integer, ? extends Object> key, Function1<? super Integer, ? extends Object> contentType, Function4<? super LazyItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> itemContent) {
        throw new IllegalStateException("The method is not implemented");
    }

    default void stickyHeader(Object key, Object contentType, final Function4<? super LazyItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> content) {
        item(key, contentType, ComposableLambdaKt.composableLambdaInstance(1691919627, true, new Function3() { // from class: kv8
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return LazyListScope.b(content, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }));
    }

    static /* synthetic */ void item$default(LazyListScope lazyListScope, Object obj, Function3 function3, int i, Object obj2) {
        if (obj2 != null) {
            c41.a("Super calls with default arguments not supported in this target, function: item");
            return;
        }
        if ((i & 1) != 0) {
            obj = null;
        }
        lazyListScope.item(obj, function3);
    }

    static /* synthetic */ void items$default(LazyListScope lazyListScope, int i, Function1 function1, Function4 function4, int i2, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: items");
            return;
        }
        if ((i2 & 2) != 0) {
            function1 = null;
        }
        lazyListScope.items(i, function1, function4);
    }

    static /* synthetic */ void stickyHeader$default(LazyListScope lazyListScope, Object obj, Object obj2, Function4 function4, int i, Object obj3) {
        if (obj3 != null) {
            c41.a("Super calls with default arguments not supported in this target, function: stickyHeader");
            return;
        }
        if ((i & 1) != 0) {
            obj = null;
        }
        if ((i & 2) != 0) {
            obj2 = null;
        }
        lazyListScope.stickyHeader(obj, obj2, (Function4<? super LazyItemScope, ? super Integer, ? super Composer, ? super Integer, Unit>) function4);
    }
}
