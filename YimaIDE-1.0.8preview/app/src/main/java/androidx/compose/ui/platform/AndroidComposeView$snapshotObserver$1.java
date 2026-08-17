package androidx.compose.ui.platform;

import android.os.Handler;
import android.os.Looper;
import androidx.compose.ui.node.RootForTest;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "command", "Lkotlin/Function0;", "invoke"}, k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AndroidComposeView$snapshotObserver$1 extends Lambda implements Function1<Function0<? extends Unit>, Unit> {
    final /* synthetic */ AndroidComposeView this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AndroidComposeView$snapshotObserver$1(AndroidComposeView androidComposeView) {
        super(1);
        this.this$0 = androidComposeView;
    }

    public static void b(Function0 function0) {
        function0.invoke();
    }

    public final void invoke(final Function0<Unit> function0) {
        final RootForTest.UncaughtExceptionHandler uncaughtExceptionHandler = this.this$0.getUncaughtExceptionHandler();
        if (uncaughtExceptionHandler != null) {
            function0 = new Function0<Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeView$snapshotObserver$1$command$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m5080invoke() {
                    try {
                        function0.invoke();
                    } catch (Exception e) {
                        uncaughtExceptionHandler.onUncaughtException(e);
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke() {
                    m5080invoke();
                    return Unit.INSTANCE;
                }
            };
        }
        Handler handler = this.this$0.getHandler();
        if ((handler != null ? handler.getLooper() : null) == Looper.myLooper()) {
            function0.invoke();
            return;
        }
        Handler handler2 = this.this$0.getHandler();
        if (handler2 != null) {
            handler2.post(new Runnable() { // from class: androidx.compose.ui.platform.a
                @Override // java.lang.Runnable
                public final void run() {
                    AndroidComposeView$snapshotObserver$1.b(function0);
                }
            });
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Function0<Unit>) obj);
        return Unit.INSTANCE;
    }
}
