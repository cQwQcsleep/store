package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.interaction.DragInteraction;
import androidx.compose.foundation.interaction.FocusInteraction;
import androidx.compose.foundation.interaction.HoverInteraction;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.PressInteraction;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@DebugMetadata(c = "androidx.compose.material3.CardElevation$animateElevation$2$1", f = "Card.kt", i = {}, l = {727, 737}, m = "invokeSuspend", n = {}, s = {})
public final class CardElevation$animateElevation$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Animatable<Dp, AnimationVector1D> $animatable;
    final /* synthetic */ boolean $enabled;
    final /* synthetic */ Interaction $interaction;
    final /* synthetic */ float $target;
    int label;
    final /* synthetic */ CardElevation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CardElevation$animateElevation$2$1(Animatable<Dp, AnimationVector1D> animatable, float f, boolean z, CardElevation cardElevation, Interaction interaction, Continuation<? super CardElevation$animateElevation$2$1> continuation) {
        super(2, continuation);
        this.$animatable = animatable;
        this.$target = f;
        this.$enabled = z;
        this.this$0 = cardElevation;
        this.$interaction = interaction;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new CardElevation$animateElevation$2$1(this.$animatable, this.$target, this.$enabled, this.this$0, this.$interaction, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0044, code lost:
    
        if (r1.snapTo(r7, r6) == r0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00ac, code lost:
    
        if (androidx.compose.material3.internal.ElevationKt.m1388animateElevationrAjV9yQ(r7, r1, r3, r4, r6) == r0) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00ae, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (!Dp.m6027equalsimpl0(((Dp) this.$animatable.getTargetValue()).m6036unboximpl(), this.$target)) {
                boolean z = this.$enabled;
                Animatable<Dp, AnimationVector1D> animatable = this.$animatable;
                if (z) {
                    float fM6036unboximpl = ((Dp) animatable.getTargetValue()).m6036unboximpl();
                    Interaction start = null;
                    if (Dp.m6027equalsimpl0(fM6036unboximpl, this.this$0.pressedElevation)) {
                        start = new PressInteraction.Press(Offset.INSTANCE.m2905getZeroF1C5BW0(), (DefaultConstructorMarker) null);
                    } else if (Dp.m6027equalsimpl0(fM6036unboximpl, this.this$0.hoveredElevation)) {
                        start = new HoverInteraction.Enter();
                    } else if (Dp.m6027equalsimpl0(fM6036unboximpl, this.this$0.focusedElevation)) {
                        start = new FocusInteraction.Focus();
                    } else if (Dp.m6027equalsimpl0(fM6036unboximpl, this.this$0.draggedElevation)) {
                        start = new DragInteraction.Start();
                    }
                    Animatable<Dp, AnimationVector1D> animatable2 = this.$animatable;
                    float f = this.$target;
                    Interaction interaction = this.$interaction;
                    this.label = 2;
                } else {
                    Dp dpM6020boximpl = Dp.m6020boximpl(this.$target);
                    this.label = 1;
                }
            }
        } else {
            if (i != 1 && i != 2) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
