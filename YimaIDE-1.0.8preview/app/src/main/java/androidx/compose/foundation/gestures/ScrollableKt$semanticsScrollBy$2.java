package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.ui.input.nestedscroll.NestedScrollSource;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/NestedScrollScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.ScrollableKt$semanticsScrollBy$2", f = "Scrollable.kt", i = {}, l = {1119}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class ScrollableKt$semanticsScrollBy$2 extends SuspendLambda implements Function2<NestedScrollScope, Continuation<? super Unit>, Object> {

    /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-geometry-Offset$-offset$0, reason: not valid java name */
    final /* synthetic */ long f17$$v$c$androidxcomposeuigeometryOffset$offset$0;
    final /* synthetic */ Ref.FloatRef $previousValue;
    final /* synthetic */ ScrollingLogic $this_semanticsScrollBy;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScrollableKt$semanticsScrollBy$2(ScrollingLogic scrollingLogic, long j, Ref.FloatRef floatRef, Continuation<? super ScrollableKt$semanticsScrollBy$2> continuation) {
        super(2, continuation);
        this.$this_semanticsScrollBy = scrollingLogic;
        this.f17$$v$c$androidxcomposeuigeometryOffset$offset$0 = j;
        this.$previousValue = floatRef;
    }

    public static Unit b(Ref.FloatRef floatRef, ScrollingLogic scrollingLogic, NestedScrollScope nestedScrollScope, float f, float f2) {
        floatRef.element += scrollingLogic.reverseIfNeeded(scrollingLogic.m703toFloatk4lQ0M(nestedScrollScope.mo629scrollByOzD1aCk(scrollingLogic.m704toOffsettuRUvjQ(scrollingLogic.reverseIfNeeded(f - floatRef.element)), NestedScrollSource.Companion.getUserInput-WNlRxjI())));
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ScrollableKt$semanticsScrollBy$2 scrollableKt$semanticsScrollBy$2 = new ScrollableKt$semanticsScrollBy$2(this.$this_semanticsScrollBy, this.f17$$v$c$androidxcomposeuigeometryOffset$offset$0, this.$previousValue, continuation);
        scrollableKt$semanticsScrollBy$2.L$0 = obj;
        return scrollableKt$semanticsScrollBy$2;
    }

    public final Object invoke(NestedScrollScope nestedScrollScope, Continuation<? super Unit> continuation) {
        return create(nestedScrollScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final NestedScrollScope nestedScrollScope = (NestedScrollScope) this.L$0;
            float fM703toFloatk4lQ0M = this.$this_semanticsScrollBy.m703toFloatk4lQ0M(this.f17$$v$c$androidxcomposeuigeometryOffset$offset$0);
            final Ref.FloatRef floatRef = this.$previousValue;
            final ScrollingLogic scrollingLogic = this.$this_semanticsScrollBy;
            Function2 function2 = new Function2() { // from class: androidx.compose.foundation.gestures.c0
                public final Object invoke(Object obj2, Object obj3) {
                    return ScrollableKt$semanticsScrollBy$2.b(floatRef, scrollingLogic, nestedScrollScope, ((Float) obj2).floatValue(), ((Float) obj3).floatValue());
                }
            };
            this.label = 1;
            if (SuspendAnimationKt.animate$default(0.0f, fM703toFloatk4lQ0M, 0.0f, null, function2, this, 12, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
