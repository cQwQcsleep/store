package androidx.compose.foundation;

import androidx.compose.foundation.gestures.PressGestureScope;
import androidx.compose.foundation.interaction.Interaction;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.interaction.PressInteraction;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.AbstractClickableNode$handlePressInteraction$2$1", f = "Clickable.kt", i = {0, 1, 2}, l = {1750, 1752, 1759, 1760, 1770}, m = "invokeSuspend", n = {"delayJob", "success", "release"}, s = {"L$0", "Z$0", "L$0"}, v = 1)
public final class AbstractClickableNode$handlePressInteraction$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-geometry-Offset$-offset$0, reason: not valid java name */
    final /* synthetic */ long f5$$v$c$androidxcomposeuigeometryOffset$offset$0;
    final /* synthetic */ MutableInteractionSource $interactionSource;
    final /* synthetic */ PressGestureScope $this_handlePressInteraction;
    private /* synthetic */ Object L$0;
    boolean Z$0;
    int label;
    final /* synthetic */ AbstractClickableNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractClickableNode$handlePressInteraction$2$1(PressGestureScope pressGestureScope, long j, MutableInteractionSource mutableInteractionSource, AbstractClickableNode abstractClickableNode, Continuation<? super AbstractClickableNode$handlePressInteraction$2$1> continuation) {
        super(2, continuation);
        this.$this_handlePressInteraction = pressGestureScope;
        this.f5$$v$c$androidxcomposeuigeometryOffset$offset$0 = j;
        this.$interactionSource = mutableInteractionSource;
        this.this$0 = abstractClickableNode;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AbstractClickableNode$handlePressInteraction$2$1 abstractClickableNode$handlePressInteraction$2$1 = new AbstractClickableNode$handlePressInteraction$2$1(this.$this_handlePressInteraction, this.f5$$v$c$androidxcomposeuigeometryOffset$offset$0, this.$interactionSource, this.this$0, continuation);
        abstractClickableNode$handlePressInteraction$2$1.L$0 = obj;
        return abstractClickableNode$handlePressInteraction$2$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:26:0x0085  */
    /* JADX WARN: Code duplicated, block: B:29:0x009e  */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a9, code lost:
    
        if (r3.emit(r2, r16) == r1) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00cb, code lost:
    
        if (r4.emit(r5, r16) == r1) goto L41;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Job jobLaunch$default;
        Object objTryAwaitRelease;
        boolean z;
        PressInteraction.Press press;
        PressInteraction.Release release;
        MutableInteractionSource mutableInteractionSource;
        PressInteraction.Release release2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            jobLaunch$default = BuildersKt.launch$default((CoroutineScope) this.L$0, (CoroutineContext) null, (CoroutineStart) null, new AbstractClickableNode$handlePressInteraction$2$1$delayJob$1(this.this$0, this.f5$$v$c$androidxcomposeuigeometryOffset$offset$0, this.$interactionSource, null), 3, (Object) null);
            PressGestureScope pressGestureScope = this.$this_handlePressInteraction;
            this.L$0 = jobLaunch$default;
            this.label = 1;
            objTryAwaitRelease = pressGestureScope.tryAwaitRelease(this);
            if (objTryAwaitRelease != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i == 1) {
            jobLaunch$default = (Job) this.L$0;
            ResultKt.throwOnFailure(obj);
            objTryAwaitRelease = obj;
        } else if (i == 2) {
            z = this.Z$0;
            ResultKt.throwOnFailure(obj);
            if (z) {
                press = new PressInteraction.Press(this.f5$$v$c$androidxcomposeuigeometryOffset$offset$0, null);
                release = new PressInteraction.Release(press);
                mutableInteractionSource = this.$interactionSource;
                this.L$0 = release;
                this.label = 3;
                if (mutableInteractionSource.emit(press, this) != coroutine_suspended) {
                    release2 = release;
                    MutableInteractionSource mutableInteractionSource2 = this.$interactionSource;
                    this.L$0 = null;
                    this.label = 4;
                }
                return coroutine_suspended;
            }
        } else if (i == 3) {
            release2 = (PressInteraction.Release) this.L$0;
            ResultKt.throwOnFailure(obj);
            MutableInteractionSource mutableInteractionSource3 = this.$interactionSource;
            this.L$0 = null;
            this.label = 4;
        } else {
            if (i != 4 && i != 5) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        this.this$0.pressInteraction = null;
        return Unit.INSTANCE;
        boolean zBooleanValue = ((Boolean) objTryAwaitRelease).booleanValue();
        if (!jobLaunch$default.isActive()) {
            PressInteraction.Press press2 = this.this$0.pressInteraction;
            if (press2 != null) {
                MutableInteractionSource mutableInteractionSource4 = this.$interactionSource;
                Interaction release3 = zBooleanValue ? new PressInteraction.Release(press2) : new PressInteraction.Cancel(press2);
                this.L$0 = null;
                this.label = 5;
            }
            this.this$0.pressInteraction = null;
            return Unit.INSTANCE;
        }
        this.L$0 = null;
        this.Z$0 = zBooleanValue;
        this.label = 2;
        if (JobKt.cancelAndJoin(jobLaunch$default, this) != coroutine_suspended) {
            z = zBooleanValue;
            if (z) {
                press = new PressInteraction.Press(this.f5$$v$c$androidxcomposeuigeometryOffset$offset$0, null);
                release = new PressInteraction.Release(press);
                mutableInteractionSource = this.$interactionSource;
                this.L$0 = release;
                this.label = 3;
                if (mutableInteractionSource.emit(press, this) != coroutine_suspended) {
                    release2 = release;
                    MutableInteractionSource mutableInteractionSource5 = this.$interactionSource;
                    this.L$0 = null;
                    this.label = 4;
                }
            }
            this.this$0.pressInteraction = null;
            return Unit.INSTANCE;
        }
        return coroutine_suspended;
    }
}
