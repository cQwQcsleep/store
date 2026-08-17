package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.ui.SessionMutex;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0096@¢\u0006\u0002\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX\u0096\u0005¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"androidx/compose/ui/platform/ChainedPlatformTextInputInterceptor$textInputSession$2$scope$1", "Landroidx/compose/ui/platform/PlatformTextInputSessionScope;", "startInputMethod", "", "request", "Landroidx/compose/ui/platform/PlatformTextInputMethodRequest;", "(Landroidx/compose/ui/platform/PlatformTextInputMethodRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ChainedPlatformTextInputInterceptor$textInputSession$2$scope$1 implements PlatformTextInputSessionScope {
    private final /* synthetic */ PlatformTextInputSessionScope $$delegate_0;
    final /* synthetic */ AtomicReference<SessionMutex.Session<Unit>> $inputMethodMutex;
    final /* synthetic */ PlatformTextInputSessionScope $parentSession;
    final /* synthetic */ ChainedPlatformTextInputInterceptor this$0;

    public ChainedPlatformTextInputInterceptor$textInputSession$2$scope$1(PlatformTextInputSessionScope platformTextInputSessionScope, AtomicReference<SessionMutex.Session<Unit>> atomicReference, ChainedPlatformTextInputInterceptor chainedPlatformTextInputInterceptor) {
        this.$parentSession = platformTextInputSessionScope;
        this.$inputMethodMutex = atomicReference;
        this.this$0 = chainedPlatformTextInputInterceptor;
        this.$$delegate_0 = platformTextInputSessionScope;
    }

    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    public View getView() {
        return this.$$delegate_0.getView();
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public Object startInputMethod(PlatformTextInputMethodRequest platformTextInputMethodRequest, Continuation<?> continuation) {
        ChainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$1 chainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$1;
        if (continuation instanceof ChainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$1) {
            chainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$1 = (ChainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$1) continuation;
            int i = chainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                chainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$1.label = i - Integer.MIN_VALUE;
            } else {
                chainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$1 = new ChainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$1(this, continuation);
            }
        } else {
            chainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$1 = new ChainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$1(this, continuation);
        }
        Object obj = chainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = chainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$1.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            AtomicReference<SessionMutex.Session<Unit>> atomicReference = this.$inputMethodMutex;
            ChainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$2 chainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$2 = new Function1<CoroutineScope, Unit>() { // from class: androidx.compose.ui.platform.ChainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$2
                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke((CoroutineScope) obj2);
                    return Unit.INSTANCE;
                }

                public final void invoke(CoroutineScope coroutineScope) {
                }
            };
            ChainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$3 chainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$3 = new ChainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$3(this.this$0, platformTextInputMethodRequest, this.$parentSession, null);
            chainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$1.label = 1;
            if (SessionMutex.m2618withSessionCancellingPreviousimpl(atomicReference, chainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$2, chainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$3, chainedPlatformTextInputInterceptor$textInputSession$2$scope$1$startInputMethod$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                k2d.a("call to 'resume' before 'invoke' with coroutine");
                return null;
            }
            ResultKt.throwOnFailure(obj);
        }
        wq6.a();
        return null;
    }
}
