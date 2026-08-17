package androidx.compose.foundation.gestures;

import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.SuspendAnimationKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.ui.geometry.Offset;
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
@DebugMetadata(c = "androidx.compose.foundation.gestures.Scrollable2DKt$semanticsScrollBy$2", f = "Scrollable2D.kt", i = {}, l = {510}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class Scrollable2DKt$semanticsScrollBy$2 extends SuspendLambda implements Function2<NestedScrollScope, Continuation<? super Unit>, Object> {

    /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-geometry-Offset$-offset$0, reason: not valid java name */
    final /* synthetic */ long f16$$v$c$androidxcomposeuigeometryOffset$offset$0;
    final /* synthetic */ Ref.LongRef $previousValue;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Scrollable2DKt$semanticsScrollBy$2(long j, Ref.LongRef longRef, Continuation<? super Scrollable2DKt$semanticsScrollBy$2> continuation) {
        super(2, continuation);
        this.f16$$v$c$androidxcomposeuigeometryOffset$offset$0 = j;
        this.$previousValue = longRef;
    }

    public static Unit b(Ref.LongRef longRef, NestedScrollScope nestedScrollScope, Offset offset, Offset offset2) {
        longRef.element = Offset.plus-MK-Hz9U(longRef.element, nestedScrollScope.mo629scrollByOzD1aCk(Offset.minus-MK-Hz9U(offset.unbox-impl(), longRef.element), NestedScrollSource.Companion.getUserInput-WNlRxjI()));
        return Unit.INSTANCE;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Scrollable2DKt$semanticsScrollBy$2 scrollable2DKt$semanticsScrollBy$2 = new Scrollable2DKt$semanticsScrollBy$2(this.f16$$v$c$androidxcomposeuigeometryOffset$offset$0, this.$previousValue, continuation);
        scrollable2DKt$semanticsScrollBy$2.L$0 = obj;
        return scrollable2DKt$semanticsScrollBy$2;
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
            Offset.Companion companion = Offset.Companion;
            TwoWayConverter<Offset, AnimationVector2D> vectorConverter = VectorConvertersKt.getVectorConverter(companion);
            Offset offset = Offset.box-impl(companion.getZero-F1C5BW0());
            Offset offset2 = Offset.box-impl(this.f16$$v$c$androidxcomposeuigeometryOffset$offset$0);
            final Ref.LongRef longRef = this.$previousValue;
            Function2 function2 = new Function2() { // from class: androidx.compose.foundation.gestures.a0
                public final Object invoke(Object obj2, Object obj3) {
                    return Scrollable2DKt$semanticsScrollBy$2.b(longRef, nestedScrollScope, (Offset) obj2, (Offset) obj3);
                }
            };
            this.label = 1;
            if (SuspendAnimationKt.animate$default(vectorConverter, offset, offset2, null, null, function2, this, 24, null) == coroutine_suspended) {
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
