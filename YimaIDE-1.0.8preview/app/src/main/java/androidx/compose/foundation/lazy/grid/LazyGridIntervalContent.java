package androidx.compose.foundation.lazy.grid;

import androidx.collection.IntList;
import androidx.collection.IntListKt;
import androidx.collection.MutableIntList;
import androidx.compose.foundation.lazy.grid.LazyGridIntervalContent;
import androidx.compose.foundation.lazy.grid.LazyGridItemScope;
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent;
import androidx.compose.foundation.lazy.layout.MutableIntervalList;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u0000 32\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u00013B \u0012\u0017\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b\u0007¢\u0006\u0004\b\b\u0010\tJZ\u0010\u001e\u001a\u00020\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010 2\u0019\u0010!\u001a\u0015\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#\u0018\u00010\u0005¢\u0006\u0002\b\u00072\b\u0010$\u001a\u0004\u0018\u00010 2\u001c\u0010\u0004\u001a\u0018\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\b&¢\u0006\u0002\b\u0007H\u0016¢\u0006\u0002\u0010'J³\u0001\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020*2#\u0010\u001f\u001a\u001f\u0012\u0013\u0012\u00110*¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020 \u0018\u00010\u00052\u001f\u0010!\u001a\u001b\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020#\u0018\u00010.¢\u0006\u0002\b\u00072#\u0010$\u001a\u001f\u0012\u0013\u0012\u00110*¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(-\u0012\u0006\u0012\u0004\u0018\u00010 0\u000521\u0010/\u001a-\u0012\u0004\u0012\u00020%\u0012\u0013\u0012\u00110*¢\u0006\f\b+\u0012\b\b,\u0012\u0004\b\b(-\u0012\u0004\u0012\u00020\u00060.¢\u0006\u0002\b&¢\u0006\u0002\b\u0007H\u0016¢\u0006\u0002\u00100JE\u00101\u001a\u00020\u00062\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010$\u001a\u0004\u0018\u00010 2\"\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00060.¢\u0006\u0002\b&¢\u0006\u0002\b\u0007H\u0016¢\u0006\u0002\u00102R\u0014\u0010\n\u001a\u00020\u000bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00030\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001a\u001a\u00020\u001b8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u00064"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridIntervalContent;", "Landroidx/compose/foundation/lazy/grid/LazyGridScope;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutIntervalContent;", "Landroidx/compose/foundation/lazy/grid/LazyGridInterval;", "content", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "spanLayoutProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider;", "getSpanLayoutProvider$foundation", "()Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider;", "intervals", "Landroidx/compose/foundation/lazy/layout/MutableIntervalList;", "getIntervals", "()Landroidx/compose/foundation/lazy/layout/MutableIntervalList;", "hasCustomSpans", "", "getHasCustomSpans$foundation", "()Z", "setHasCustomSpans$foundation", "(Z)V", "_headerIndexes", "Landroidx/collection/MutableIntList;", "headerIndexes", "Landroidx/collection/IntList;", "getHeaderIndexes", "()Landroidx/collection/IntList;", "item", "key", "", "span", "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", "contentType", "Landroidx/compose/foundation/lazy/grid/LazyGridItemScope;", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)V", "items", "count", "", "Lkotlin/ParameterName;", "name", "index", "Lkotlin/Function2;", "itemContent", "(ILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "stickyHeader", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function4;)V", "Companion", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LazyGridIntervalContent extends LazyLayoutIntervalContent<LazyGridInterval> implements LazyGridScope {
    private MutableIntList _headerIndexes;
    private boolean hasCustomSpans;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final Function2<LazyGridItemSpanScope, Integer, GridItemSpan> DefaultSpan = new Function2() { // from class: gr8
        public final Object invoke(Object obj, Object obj2) {
            return LazyGridIntervalContent.j((LazyGridItemSpanScope) obj, ((Integer) obj2).intValue());
        }
    };
    private final LazyGridSpanLayoutProvider spanLayoutProvider = new LazyGridSpanLayoutProvider(this);
    private final MutableIntervalList<LazyGridInterval> intervals = new MutableIntervalList<>();

    public LazyGridIntervalContent(Function1<? super LazyGridScope, Unit> function1) {
        function1.invoke(this);
    }

    public static Unit d(Function3 function3, LazyGridItemScope lazyGridItemScope, int i, Composer composer, int i2) {
        if ((i2 & 6) == 0) {
            i2 |= composer.changed(lazyGridItemScope) ? 4 : 2;
        }
        if (composer.shouldExecute((i2 & 131) != 130, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-291643851, i2, -1, "androidx.compose.foundation.lazy.grid.LazyGridIntervalContent.item.<anonymous> (LazyGridIntervalContent.kt:55)");
            }
            function3.invoke(lazyGridItemScope, composer, Integer.valueOf(i2 & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit f(Function4 function4, int i, LazyGridItemScope lazyGridItemScope, Composer composer, int i2) {
        if ((i2 & 6) == 0) {
            i2 |= composer.changed(lazyGridItemScope) ? 4 : 2;
        }
        if (composer.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(46791243, i2, -1, "androidx.compose.foundation.lazy.grid.LazyGridIntervalContent.stickyHeader.<anonymous> (LazyGridIntervalContent.kt:88)");
            }
            function4.invoke(lazyGridItemScope, Integer.valueOf(i), composer, Integer.valueOf(i2 & 14));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static GridItemSpan g(LazyGridItemSpanScope lazyGridItemSpanScope) {
        return GridItemSpan.m1072boximpl(LazyGridSpanKt.GridItemSpan(lazyGridItemSpanScope.getMaxLineSpan()));
    }

    public static Object i(Object obj, int i) {
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Object item$lambda$0$0(Object obj, int i) {
        return obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final GridItemSpan item$lambda$1$0(Function1 function1, LazyGridItemSpanScope lazyGridItemSpanScope, int i) {
        return (GridItemSpan) function1.invoke(lazyGridItemSpanScope);
    }

    public static GridItemSpan j(LazyGridItemSpanScope lazyGridItemSpanScope, int i) {
        return GridItemSpan.m1072boximpl(LazyGridSpanKt.GridItemSpan(1));
    }

    /* JADX INFO: renamed from: getHasCustomSpans$foundation, reason: from getter */
    public final boolean getHasCustomSpans() {
        return this.hasCustomSpans;
    }

    public final IntList getHeaderIndexes() {
        MutableIntList mutableIntList = this._headerIndexes;
        return mutableIntList != null ? mutableIntList : IntListKt.emptyIntList();
    }

    /* JADX INFO: renamed from: getSpanLayoutProvider$foundation, reason: from getter */
    public final LazyGridSpanLayoutProvider getSpanLayoutProvider() {
        return this.spanLayoutProvider;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridScope
    public void item(final Object key, final Function1<? super LazyGridItemSpanScope, GridItemSpan> span, final Object contentType, final Function3<? super LazyGridItemScope, ? super Composer, ? super Integer, Unit> content) {
        getIntervals().addInterval(1, new LazyGridInterval(key != null ? new Function1() { // from class: cr8
            public final Object invoke(Object obj) {
                return LazyGridIntervalContent.item$lambda$0$0(key, ((Integer) obj).intValue());
            }
        } : null, span != null ? new Function2() { // from class: dr8
            public final Object invoke(Object obj, Object obj2) {
                return LazyGridIntervalContent.item$lambda$1$0(span, (LazyGridItemSpanScope) obj, ((Integer) obj2).intValue());
            }
        } : DefaultSpan, new Function1() { // from class: er8
            public final Object invoke(Object obj) {
                return LazyGridIntervalContent.i(contentType, ((Integer) obj).intValue());
            }
        }, ComposableLambdaKt.composableLambdaInstance(-291643851, true, new Function4() { // from class: fr8
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return LazyGridIntervalContent.d(content, (LazyGridItemScope) obj, ((Integer) obj2).intValue(), (Composer) obj3, ((Integer) obj4).intValue());
            }
        })));
        if (span != null) {
            this.hasCustomSpans = true;
        }
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridScope
    public void items(int count, Function1<? super Integer, ? extends Object> key, Function2<? super LazyGridItemSpanScope, ? super Integer, GridItemSpan> span, Function1<? super Integer, ? extends Object> contentType, Function4<? super LazyGridItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> itemContent) {
        getIntervals().addInterval(count, new LazyGridInterval(key, span == null ? DefaultSpan : span, contentType, itemContent));
        if (span != null) {
            this.hasCustomSpans = true;
        }
    }

    public final void setHasCustomSpans$foundation(boolean z) {
        this.hasCustomSpans = z;
    }

    @Override // androidx.compose.foundation.lazy.grid.LazyGridScope
    public void stickyHeader(Object key, Object contentType, final Function4<? super LazyGridItemScope, ? super Integer, ? super Composer, ? super Integer, Unit> content) {
        MutableIntList mutableIntList = this._headerIndexes;
        if (mutableIntList == null) {
            mutableIntList = new MutableIntList(0, 1, null);
            this._headerIndexes = mutableIntList;
        }
        final int size = getIntervals().getSize();
        mutableIntList.add(size);
        item(key, new Function1() { // from class: ar8
            public final Object invoke(Object obj) {
                return LazyGridIntervalContent.g((LazyGridItemSpanScope) obj);
            }
        }, contentType, ComposableLambdaKt.composableLambdaInstance(46791243, true, new Function3() { // from class: br8
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return LazyGridIntervalContent.f(content, size, (LazyGridItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }));
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R(\u0010\u0004\u001a\u0019\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0002\b\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridIntervalContent$Companion;", "", "<init>", "()V", "DefaultSpan", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", "Lkotlin/ExtensionFunctionType;", "getDefaultSpan", "()Lkotlin/jvm/functions/Function2;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Function2<LazyGridItemSpanScope, Integer, GridItemSpan> getDefaultSpan() {
            return LazyGridIntervalContent.DefaultSpan;
        }

        private Companion() {
        }
    }

    @Override // androidx.compose.foundation.lazy.layout.LazyLayoutIntervalContent
    public MutableIntervalList<LazyGridInterval> getIntervals() {
        return this.intervals;
    }
}
