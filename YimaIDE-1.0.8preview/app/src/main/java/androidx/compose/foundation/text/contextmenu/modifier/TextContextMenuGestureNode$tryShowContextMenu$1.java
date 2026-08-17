package androidx.compose.foundation.text.contextmenu.modifier;

import androidx.compose.foundation.text.contextmenu.provider.TextContextMenuProvider;
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
@DebugMetadata(c = "androidx.compose.foundation.text.contextmenu.modifier.TextContextMenuGestureNode$tryShowContextMenu$1", f = "TextContextMenuGesturesModifier.kt", i = {}, l = {107, 108}, m = "invokeSuspend", n = {}, s = {}, v = 1)
public final class TextContextMenuGestureNode$tryShowContextMenu$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* JADX INFO: renamed from: $$v$c$androidx-compose-ui-geometry-Offset$-localClickOffset$0, reason: not valid java name */
    final /* synthetic */ long f59$$v$c$androidxcomposeuigeometryOffset$localClickOffset$0;
    final /* synthetic */ TextContextMenuGestureNode.ClickTextContextMenuDataProvider $dataProvider;
    final /* synthetic */ TextContextMenuProvider $provider;
    int label;
    final /* synthetic */ TextContextMenuGestureNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TextContextMenuGestureNode$tryShowContextMenu$1(TextContextMenuGestureNode textContextMenuGestureNode, long j, TextContextMenuProvider textContextMenuProvider, TextContextMenuGestureNode.ClickTextContextMenuDataProvider clickTextContextMenuDataProvider, Continuation<? super TextContextMenuGestureNode$tryShowContextMenu$1> continuation) {
        super(2, continuation);
        this.this$0 = textContextMenuGestureNode;
        this.f59$$v$c$androidxcomposeuigeometryOffset$localClickOffset$0 = j;
        this.$provider = textContextMenuProvider;
        this.$dataProvider = clickTextContextMenuDataProvider;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TextContextMenuGestureNode$tryShowContextMenu$1(this.this$0, this.f59$$v$c$androidxcomposeuigeometryOffset$localClickOffset$0, this.$provider, this.$dataProvider, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return create(coroutineScope, continuation).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0041, code lost:
    
        if (r7.showTextContextMenu(r1, r6) == r0) goto L17;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i != 0) {
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                if (i != 2) {
                    k2d.a("call to 'resume' before 'invoke' with coroutine");
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        Function2 function2 = this.this$0.onPreShowContextMenu;
        if (function2 != null) {
            Offset offset = Offset.box-impl(this.f59$$v$c$androidxcomposeuigeometryOffset$localClickOffset$0);
            this.label = 1;
            if (function2.invoke(offset, this) != coroutine_suspended) {
            }
        }
        return coroutine_suspended;
        TextContextMenuProvider textContextMenuProvider = this.$provider;
        TextContextMenuGestureNode.ClickTextContextMenuDataProvider clickTextContextMenuDataProvider = this.$dataProvider;
        this.label = 2;
    }
}
