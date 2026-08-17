package androidx.window.layout.adapter.extensions;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.extensions.layout.WindowLayoutInfo;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final /* synthetic */ class ExtensionWindowBackendApi1$registerLayoutChangeCallback$1$2$disposableToken$1 extends FunctionReferenceImpl implements Function1<WindowLayoutInfo, Unit> {
    public ExtensionWindowBackendApi1$registerLayoutChangeCallback$1$2$disposableToken$1(Object obj) {
        super(1, obj, MulticastConsumer.class, "accept", "accept(Landroidx/window/extensions/layout/WindowLayoutInfo;)V", 0);
    }

    public final void invoke(WindowLayoutInfo windowLayoutInfo) {
        windowLayoutInfo.getClass();
        ((MulticastConsumer) ((CallableReference) this).receiver).accept(windowLayoutInfo);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((WindowLayoutInfo) obj);
        return Unit.INSTANCE;
    }
}
