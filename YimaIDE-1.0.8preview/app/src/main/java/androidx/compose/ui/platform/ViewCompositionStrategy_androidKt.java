package androidx.compose.ui.platform;

import androidx.compose.ui.platform.ViewCompositionStrategy_androidKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002¨\u0006\u0007"}, d2 = {"installForLifecycle", "Lkotlin/Function0;", "", "view", "Landroidx/compose/ui/platform/AbstractComposeView;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ViewCompositionStrategy_androidKt {
    public static void a(AbstractComposeView abstractComposeView, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            abstractComposeView.disposeComposition();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function0<Unit> installForLifecycle(final AbstractComposeView abstractComposeView, final Lifecycle lifecycle) {
        if (lifecycle.getState().compareTo(Lifecycle.State.DESTROYED) <= 0) {
            n9a.a("Cannot configure ", abstractComposeView, " to disposeComposition at Lifecycle ON_DESTROY: ", lifecycle, "is already destroyed");
            return null;
        }
        final LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: jcf
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                ViewCompositionStrategy_androidKt.a(abstractComposeView, lifecycleOwner, event);
            }
        };
        lifecycle.addObserver(lifecycleEventObserver);
        return new Function0<Unit>() { // from class: androidx.compose.ui.platform.ViewCompositionStrategy_androidKt.installForLifecycle.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m5219invoke() {
                lifecycle.removeObserver(lifecycleEventObserver);
            }

            public /* bridge */ /* synthetic */ Object invoke() {
                m5219invoke();
                return Unit.INSTANCE;
            }
        };
    }
}
