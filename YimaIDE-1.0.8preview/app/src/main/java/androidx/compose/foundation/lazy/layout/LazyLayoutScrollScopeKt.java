package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.AnimationScope;
import androidx.compose.animation.core.AnimationState;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0017\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0082\b\u001a\u0014\u0010\f\u001a\u00020\u0006*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000\u001a2\u0010\u0010\u001a\u00020\b*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0080@¢\u0006\u0002\u0010\u0015\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"TargetDistance", "Landroidx/compose/ui/unit/Dp;", "F", "BoundDistance", "MinimumDistance", "DEBUG", "", "debugLog", "", "generateMsg", "Lkotlin/Function0;", "", "isItemVisible", "Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollScope;", "index", "", "animateScrollToItem", "scrollOffset", "numOfItemsForTeleport", "density", "Landroidx/compose/ui/unit/Density;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutScrollScope;IIILandroidx/compose/ui/unit/Density;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyLayoutScrollScopeKt {
    private static final boolean DEBUG = false;
    private static final float TargetDistance = Dp.constructor-impl(2500.0f);
    private static final float BoundDistance = Dp.constructor-impl(1500.0f);
    private static final float MinimumDistance = Dp.constructor-impl(50.0f);

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt$animateScrollToItem$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt", f = "LazyLayoutScrollScope.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1}, l = {177, 264}, m = "animateScrollToItem", n = {"$this$animateScrollToItem", "loop", "anim", "loops", "index", "scrollOffset", "numOfItemsForTeleport", "targetDistancePx", "boundDistancePx", "minDistancePx", "forward", "$this$animateScrollToItem", "index", "scrollOffset"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0", "I$1", "I$2", "F$0", "F$1", "F$2", "I$3", "L$0", "I$0", "I$1"}, v = 1)
    public static final class AnonymousClass1 extends ContinuationImpl {
        float F$0;
        float F$1;
        float F$2;
        int I$0;
        int I$1;
        int I$2;
        int I$3;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LazyLayoutScrollScopeKt.animateScrollToItem(null, 0, 0, 0, null, this);
        }
    }

    public static Unit a(LazyLayoutScrollScope lazyLayoutScrollScope, int i, float f, Ref.FloatRef floatRef, Ref.BooleanRef booleanRef, boolean z, float f2, Ref.IntRef intRef, int i2, int i3, Ref.ObjectRef objectRef, AnimationScope animationScope) {
        if (!isItemVisible(lazyLayoutScrollScope, i)) {
            float fCoerceAtMost = (f > 0.0f ? RangesKt.coerceAtMost(((Number) animationScope.getValue()).floatValue(), f) : RangesKt.coerceAtLeast(((Number) animationScope.getValue()).floatValue(), f)) - floatRef.element;
            float fScrollBy = lazyLayoutScrollScope.scrollBy(fCoerceAtMost);
            if (!isItemVisible(lazyLayoutScrollScope, i) && !animateScrollToItem$isOvershot(z, lazyLayoutScrollScope, i, i3)) {
                if (fCoerceAtMost != fScrollBy) {
                    animationScope.cancelAnimation();
                    booleanRef.element = false;
                    return Unit.INSTANCE;
                }
                floatRef.element += fCoerceAtMost;
                if (z) {
                    if (((Number) animationScope.getValue()).floatValue() > f2) {
                        animationScope.cancelAnimation();
                    }
                } else if (((Number) animationScope.getValue()).floatValue() < (-f2)) {
                    animationScope.cancelAnimation();
                }
                if (z) {
                    if (intRef.element >= 2 && i - lazyLayoutScrollScope.getLastVisibleItemIndex() > i2) {
                        lazyLayoutScrollScope.snapToItem(i - i2, 0);
                    }
                } else if (intRef.element >= 2 && lazyLayoutScrollScope.getFirstVisibleItemIndex() - i > i2) {
                    lazyLayoutScrollScope.snapToItem(i2 + i, 0);
                }
            }
        }
        if (!animateScrollToItem$isOvershot(z, lazyLayoutScrollScope, i, i3)) {
            if (isItemVisible(lazyLayoutScrollScope, i)) {
                throw new ItemFoundInScroll(LazyLayoutScrollScope.calculateDistanceTo$default(lazyLayoutScrollScope, i, 0, 2, null), (AnimationState) objectRef.element);
            }
            return Unit.INSTANCE;
        }
        lazyLayoutScrollScope.snapToItem(i, i3);
        booleanRef.element = false;
        animationScope.cancelAnimation();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:37:0x00e9 A[Catch: ItemFoundInScroll -> 0x01eb, TryCatch #5 {ItemFoundInScroll -> 0x01eb, blocks: (B:35:0x00e5, B:37:0x00e9, B:39:0x00ef, B:53:0x0120, B:57:0x015c, B:61:0x0164), top: B:115:0x00e5 }] */
    /* JADX WARN: Code duplicated, block: B:50:0x0119 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:51:0x011b  */
    /* JADX WARN: Code duplicated, block: B:55:0x0159  */
    /* JADX WARN: Code duplicated, block: B:56:0x015b  */
    /* JADX WARN: Code duplicated, block: B:59:0x015f  */
    /* JADX WARN: Code duplicated, block: B:60:0x0162  */
    /* JADX WARN: Code duplicated, block: B:70:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:70:0x01b4 -> B:18:0x0071). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public static final java.lang.Object animateScrollToItem(androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope r37, int r38, int r39, int r40, androidx.compose.ui.unit.Density r41, kotlin.coroutines.Continuation<? super kotlin.Unit> r42) {
        /*
            Method dump skipped, instruction units count: 642
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyLayoutScrollScopeKt.animateScrollToItem(androidx.compose.foundation.lazy.layout.LazyLayoutScrollScope, int, int, int, androidx.compose.ui.unit.Density, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private static final boolean animateScrollToItem$isOvershot(boolean z, LazyLayoutScrollScope lazyLayoutScrollScope, int i, int i2) {
        if (z) {
            if (lazyLayoutScrollScope.getFirstVisibleItemIndex() > i) {
                return true;
            }
            return lazyLayoutScrollScope.getFirstVisibleItemIndex() == i && lazyLayoutScrollScope.getFirstVisibleItemScrollOffset() > i2;
        }
        if (lazyLayoutScrollScope.getFirstVisibleItemIndex() < i) {
            return true;
        }
        return lazyLayoutScrollScope.getFirstVisibleItemIndex() == i && lazyLayoutScrollScope.getFirstVisibleItemScrollOffset() < i2;
    }

    public static Unit b(float f, Ref.FloatRef floatRef, LazyLayoutScrollScope lazyLayoutScrollScope, AnimationScope animationScope) {
        float fCoerceAtLeast = 0.0f;
        if (f > 0.0f) {
            fCoerceAtLeast = RangesKt.coerceAtMost(((Number) animationScope.getValue()).floatValue(), f);
        } else if (f < 0.0f) {
            fCoerceAtLeast = RangesKt.coerceAtLeast(((Number) animationScope.getValue()).floatValue(), f);
        }
        float f2 = fCoerceAtLeast - floatRef.element;
        if (f2 != lazyLayoutScrollScope.scrollBy(f2) || fCoerceAtLeast != ((Number) animationScope.getValue()).floatValue()) {
            animationScope.cancelAnimation();
        }
        floatRef.element += f2;
        return Unit.INSTANCE;
    }

    private static final void debugLog(Function0<String> function0) {
    }

    public static final boolean isItemVisible(LazyLayoutScrollScope lazyLayoutScrollScope, int i) {
        return i <= lazyLayoutScrollScope.getLastVisibleItemIndex() && lazyLayoutScrollScope.getFirstVisibleItemIndex() <= i;
    }
}
