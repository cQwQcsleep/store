package androidx.compose.foundation.lazy;

import androidx.collection.IntList;
import androidx.collection.IntListKt;
import androidx.collection.MutableIntList;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListIntervalContent;
import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent;
import androidx.compose.foundation.lazy.layout.MutableIntervalList;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B \u0012\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¢\u0006\u0004\b\b\u0010\tJ\u0092\u0001\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00162#\u0010\u0017\u001a\u001f\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u00052#\u0010\u001c\u001a\u001f\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u000521\u0010\u001d\u001a-\u0012\u0004\u0012\u00020\u001f\u0012\u0013\u0012\u00110\u0016¢\u0006\f\b\u0018\u0012\b\b\u0019\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00060\u001e¢\u0006\u0002\b ¢\u0006\u0002\b\u0007H\u0016¢\u0006\u0002\u0010!J?\u0010\"\u001a\u00020\u00062\b\u0010\u0017\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\u001c\u0010\u0004\u001a\u0018\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b ¢\u0006\u0002\b\u0007H\u0016¢\u0006\u0002\u0010#JE\u0010$\u001a\u00020\u00062\b\u0010\u0017\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\"\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00060\u001e¢\u0006\u0002\b ¢\u0006\u0002\b\u0007H\u0016¢\u0006\u0002\u0010%R\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006&"}, d2 = {"Landroidx/compose/foundation/lazy/LazyListIntervalContent;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent;", "Landroidx/compose/foundation/lazy/LazyListInterval;", "Landroidx/compose/foundation/lazy/LazyListScope;", "content", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "intervals", "Landroidx/compose/foundation/lazy/layout/MutableIntervalList;", "getIntervals", "()Landroidx/compose/foundation/lazy/layout/MutableIntervalList;", "_headerIndexes", "Landroidx/collection/MutableIntList;", "headerIndexes", "Landroidx/collection/IntList;", "getHeaderIndexes", "()Landroidx/collection/IntList;", "items", "count", "", "key", "Lkotlin/ParameterName;", "name", "index", "", "contentType", "itemContent", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/LazyItemScope;", "Landroidx/compose/runtime/Composable;", "(ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "item", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)V", "stickyHeader", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function4;)V", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LazyListIntervalContent extends LazyLayoutIntervalContent<LazyListInterval> implements LazyListScope {
    public static final int $stable = 8;
    private MutableIntList _headerIndexes;
    private final MutableIntervalList<LazyListInterval> intervals = new MutableIntervalList<>();

    public LazyListIntervalContent(Function1<? super LazyListScope, Unit> function1) {
        function1.invoke(this);
    }

    public static Unit d(Function4 function4, int i, LazyItemScope lazyItemScope, Composer composer, int i2) {
        if ((i2 & 6) == 0) {
            i2 |= composer.changed(lazyItemScope) ? 4 : 2;
        }
        if (composer.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1588696110, i2, -1, "androidx.compose.foundation.lazy.LazyListIntervalContent.stickyHeader.<anonymous> (LazyListIntervalContent.kt:70)");
            }
            function4.invoke(lazyItemScope, Integer.valueOf(i), composer, Integer.valueOf(i2 & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Object e(Object obj, int i) {
        return obj;
    }

    public static Unit f(Function3 function3, LazyItemScope lazyItemScope, int i, Composer composer, int i2) {
        if ((i2 & 6) == 0) {
            i2 |= composer.changed(lazyItemScope) ? 4 : 2;
        }
        if (composer.shouldExecute((i2 & 131) != 130, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-857469575, i2, -1, "androidx.compose.foundation.lazy.LazyListIntervalContent.item.<anonymous> (LazyListIntervalContent.kt:56)");
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

    public static Object g(Object obj, int i) {
        return obj;
    }

    public final IntList getHeaderIndexes() {
        MutableIntList mutableIntList = this._headerIndexes;
        return mutableIntList != null ? mutableIntList : IntListKt.emptyIntList();
    }

    @Override // androidx.compose.foundation.lazy.LazyListScope
    public void item(final Object key, final Object contentType, final Function3<? super LazyItemScope, ? super Composer, ? super Integer, Unit> content) {
        getIntervals().addInterval(1, new LazyListInterval(key != null ? new Function1() { // from class: xu8
            public final Object invoke(Object obj) {
                return LazyListIntervalContent.g(key, ((Integer) obj).intValue());
            }
        } : null, new Function1() { // from class: yu8
            public final Object invoke(Object obj) {
                return LazyListIntervalContent.e(contentType, ((Integer) obj).intValue());
            }
        }, ComposableLambdaKt.composableLambdaInstance(-857469575, true, new Function4() { // from class: zu8
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return LazyListIntervalContent.f(content, (LazyItemScope) obj, ((Integer) obj2).intValue(), (Composer) obj3, ((Integer) obj4).intValue());
            }
        })));
    }

    @Override // androidx.compose.foundation.lazy.LazyListScope
    public void items(int count, Function1<? super Integer, ? extends Object> key, Function1<? super Integer, ? extends Object> contentType, Function4<? super LazyItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> itemContent) {
        getIntervals().addInterval(count, new LazyListInterval(key, contentType, itemContent));
    }

    @Override // androidx.compose.foundation.lazy.LazyListScope
    public void stickyHeader(Object key, Object contentType, final Function4<? super LazyItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> content) {
        MutableIntList mutableIntList = this._headerIndexes;
        if (mutableIntList == null) {
            mutableIntList = new MutableIntList(0, 1, null);
            this._headerIndexes = mutableIntList;
        }
        mutableIntList.add(getIntervals().getSize());
        final int size = getIntervals().getSize();
        item(key, contentType, ComposableLambdaKt.composableLambdaInstance(-1588696110, true, new Function3() { // from class: av8
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return LazyListIntervalContent.d(content, size, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }));
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent
    public MutableIntervalList<LazyListInterval> getIntervals() {
        return this.intervals;
    }
}
