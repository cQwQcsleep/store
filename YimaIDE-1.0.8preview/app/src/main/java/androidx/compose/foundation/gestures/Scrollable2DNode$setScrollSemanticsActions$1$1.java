package androidx.compose.foundation.gestures;

import androidx.compose.ui.geometry.Offset;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 0, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.Scrollable2DNode$setScrollSemanticsActions$1$1", f = "Scrollable2D.kt", i = {}, l = {285}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class Scrollable2DNode$setScrollSemanticsActions$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ float $x;
    final /* synthetic */ float $y;
    int label;
    final /* synthetic */ Scrollable2DNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Scrollable2DNode$setScrollSemanticsActions$1$1(Scrollable2DNode scrollable2DNode, float f, float f2, Continuation<? super Scrollable2DNode$setScrollSemanticsActions$1$1> continuation) {
        super(2, continuation);
        this.this$0 = scrollable2DNode;
        this.$x = f;
        this.$y = f2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new Scrollable2DNode$setScrollSemanticsActions$1$1(this.this$0, this.$x, this.$y, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            ScrollingLogic2D scrollingLogic2D = this.this$0.scrollingLogic;
            float f = this.$x;
            float f2 = this.$y;
            long j = Offset.constructor-impl((((long) Float.floatToRawIntBits(f)) << 32) | (((long) Float.floatToRawIntBits(f2)) & 4294967295L));
            this.label = 1;
            if (Scrollable2DKt.m680semanticsScrollByd4ec7I(scrollingLogic2D, j, this) == coroutine_suspended) {
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
