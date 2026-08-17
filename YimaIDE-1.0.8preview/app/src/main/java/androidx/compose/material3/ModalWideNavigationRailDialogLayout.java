package androidx.compose.material3;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.compose.material3.internal.PredictiveBack;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.platform.AbstractComposeView;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.DialogWindowProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u000212B_\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000b0\r\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015J&\u0010\u001f\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020)2\u0011\u0010\u001c\u001a\r\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\u001b¢\u0006\u0002\u0010*J\r\u0010+\u001a\u00020\u000bH\u0017¢\u0006\u0002\u0010,J\b\u0010-\u001a\u00020\u000bH\u0014J\b\u0010.\u001a\u00020\u000bH\u0014J\b\u0010/\u001a\u00020\u000bH\u0002J\b\u00100\u001a\u00020\u000bH\u0002R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000b0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000RA\u0010\u001c\u001a\r\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\u001b2\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0002\b\u001b8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010&\u001a\u00020\b2\u0006\u0010%\u001a\u00020\b@RX\u0094\u000e¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0019¨\u00063"}, d2 = {"Landroidx/compose/material3/ModalWideNavigationRailDialogLayout;", "Landroidx/compose/ui/platform/AbstractComposeView;", "Landroidx/compose/ui/window/DialogWindowProvider;", "context", "Landroid/content/Context;", "window", "Landroid/view/Window;", "shouldDismissOnBackPress", "", "onDismissRequest", "Lkotlin/Function0;", "", "onPredictiveBack", "Lkotlin/Function1;", "", "onPredictiveBackCancelled", "predictiveBackState", "Landroidx/compose/material3/RailPredictiveBackState;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "<init>", "(Landroid/content/Context;Landroid/view/Window;ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/RailPredictiveBackState;Landroidx/compose/ui/unit/LayoutDirection;)V", "getWindow", "()Landroid/view/Window;", "getShouldDismissOnBackPress", "()Z", "<set-?>", "Landroidx/compose/runtime/Composable;", "content", "getContent", "()Lkotlin/jvm/functions/Function2;", "setContent", "(Lkotlin/jvm/functions/Function2;)V", "content$delegate", "Landroidx/compose/runtime/MutableState;", "backCallback", "", "value", "shouldCreateCompositionOnAttachedToWindow", "getShouldCreateCompositionOnAttachedToWindow", "parent", "Landroidx/compose/runtime/CompositionContext;", "(Landroidx/compose/runtime/CompositionContext;Lkotlin/jvm/functions/Function2;)V", "Content", "(Landroidx/compose/runtime/Composer;I)V", "onAttachedToWindow", "onDetachedFromWindow", "maybeRegisterBackCallback", "maybeUnregisterBackCallback", "Api34Impl", "Api33Impl", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class ModalWideNavigationRailDialogLayout extends AbstractComposeView implements DialogWindowProvider {
    private Object backCallback;

    /* JADX INFO: renamed from: content$delegate, reason: from kotlin metadata */
    private final MutableState content;
    private final LayoutDirection layoutDirection;
    private final Function0<Unit> onDismissRequest;
    private final Function1<Float, Unit> onPredictiveBack;
    private final Function0<Unit> onPredictiveBackCancelled;
    private final RailPredictiveBackState predictiveBackState;
    private boolean shouldCreateCompositionOnAttachedToWindow;
    private final boolean shouldDismissOnBackPress;
    private final Window window;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0007J\u001a\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0007J\u001a\u0010\r\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0007¨\u0006\u000e"}, d2 = {"Landroidx/compose/material3/ModalWideNavigationRailDialogLayout$Api33Impl;", "", "<init>", "()V", "createBackCallback", "Landroid/window/OnBackInvokedCallback;", "onDismissRequest", "Lkotlin/Function0;", "", "maybeRegisterBackCallback", "view", "Landroid/view/View;", "backCallback", "maybeUnregisterBackCallback", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Api33Impl {
        public static final Api33Impl INSTANCE = new Api33Impl();

        private Api33Impl() {
        }

        public static void a(Function0 function0) {
            function0.invoke();
        }

        @JvmStatic
        public static final OnBackInvokedCallback createBackCallback(final Function0<Unit> onDismissRequest) {
            return new OnBackInvokedCallback() { // from class: androidx.compose.material3.p2
                @Override // android.window.OnBackInvokedCallback
                public final void onBackInvoked() {
                    ModalWideNavigationRailDialogLayout.Api33Impl.a(onDismissRequest);
                }
            };
        }

        @JvmStatic
        public static final void maybeRegisterBackCallback(View view, Object backCallback) {
            OnBackInvokedDispatcher onBackInvokedDispatcherFindOnBackInvokedDispatcher;
            if (!(backCallback instanceof OnBackInvokedCallback) || (onBackInvokedDispatcherFindOnBackInvokedDispatcher = view.findOnBackInvokedDispatcher()) == null) {
                return;
            }
            onBackInvokedDispatcherFindOnBackInvokedDispatcher.registerOnBackInvokedCallback(1000000, (OnBackInvokedCallback) backCallback);
        }

        @JvmStatic
        public static final void maybeUnregisterBackCallback(View view, Object backCallback) {
            OnBackInvokedDispatcher onBackInvokedDispatcherFindOnBackInvokedDispatcher;
            if (!(backCallback instanceof OnBackInvokedCallback) || (onBackInvokedDispatcherFindOnBackInvokedDispatcher = view.findOnBackInvokedDispatcher()) == null) {
                return;
            }
            onBackInvokedDispatcherFindOnBackInvokedDispatcher.unregisterOnBackInvokedCallback((OnBackInvokedCallback) backCallback);
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0\n2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007¨\u0006\u0011"}, d2 = {"Landroidx/compose/material3/ModalWideNavigationRailDialogLayout$Api34Impl;", "", "<init>", "()V", "createBackCallback", "Landroid/window/OnBackAnimationCallback;", "onDismissRequest", "Lkotlin/Function0;", "", "onPredictiveBack", "Lkotlin/Function1;", "", "onPredictiveBackCancelled", "predictiveBackState", "Landroidx/compose/material3/RailPredictiveBackState;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Api34Impl {
        public static final Api34Impl INSTANCE = new Api34Impl();

        private Api34Impl() {
        }

        @JvmStatic
        public static final OnBackAnimationCallback createBackCallback(final Function0<Unit> onDismissRequest, final Function1<? super Float, Unit> onPredictiveBack, final Function0<Unit> onPredictiveBackCancelled, final RailPredictiveBackState predictiveBackState, final LayoutDirection layoutDirection) {
            return new OnBackAnimationCallback() { // from class: androidx.compose.material3.ModalWideNavigationRailDialogLayout$Api34Impl$createBackCallback$1
                public void onBackCancelled() {
                    onPredictiveBackCancelled.invoke();
                }

                @Override // android.window.OnBackInvokedCallback
                public void onBackInvoked() {
                    onDismissRequest.invoke();
                }

                public void onBackProgressed(BackEvent backEvent) {
                    predictiveBackState.update(backEvent.getSwipeEdge() == 0, layoutDirection == LayoutDirection.Rtl);
                    onPredictiveBack.invoke(Float.valueOf(PredictiveBack.INSTANCE.transform$material3(backEvent.getProgress())));
                }

                public void onBackStarted(BackEvent backEvent) {
                    predictiveBackState.update(backEvent.getSwipeEdge() == 0, layoutDirection == LayoutDirection.Rtl);
                    onPredictiveBack.invoke(Float.valueOf(PredictiveBack.INSTANCE.transform$material3(backEvent.getProgress())));
                }
            };
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ModalWideNavigationRailDialogLayout(Context context, Window window, boolean z, Function0<Unit> function0, Function1<? super Float, Unit> function1, Function0<Unit> function2, RailPredictiveBackState railPredictiveBackState, LayoutDirection layoutDirection) {
        super(context, null, 0, 6, null);
        this.window = window;
        this.shouldDismissOnBackPress = z;
        this.onDismissRequest = function0;
        this.onPredictiveBack = function1;
        this.onPredictiveBackCancelled = function2;
        this.predictiveBackState = railPredictiveBackState;
        this.layoutDirection = layoutDirection;
        this.content = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(ComposableSingletons$WideNavigationRail_androidKt.INSTANCE.getLambda$2011757776$material3(), null, 2, null);
    }

    public static Unit a(ModalWideNavigationRailDialogLayout modalWideNavigationRailDialogLayout, int i, Composer composer, int i2) {
        modalWideNavigationRailDialogLayout.Content(composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private final Function2<Composer, Integer, Unit> getContent() {
        return (Function2) this.content.getValue();
    }

    private final void maybeRegisterBackCallback() {
        if (this.shouldDismissOnBackPress) {
            if (this.backCallback == null) {
                int i = Build.VERSION.SDK_INT;
                Function0<Unit> function0 = this.onDismissRequest;
                this.backCallback = i >= 34 ? Api34Impl.createBackCallback(function0, this.onPredictiveBack, this.onPredictiveBackCancelled, this.predictiveBackState, this.layoutDirection) : Api33Impl.createBackCallback(function0);
            }
            Api33Impl.maybeRegisterBackCallback(this, this.backCallback);
        }
    }

    private final void maybeUnregisterBackCallback() {
        Api33Impl.maybeUnregisterBackCallback(this, this.backCallback);
        this.backCallback = null;
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public void Content(Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-640057148);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(this) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-640057148, i2, -1, "androidx.compose.material3.ModalWideNavigationRailDialogLayout.Content (WideNavigationRail.android.kt:202)");
            }
            getContent().invoke(composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.o2
                public final Object invoke(Object obj, Object obj2) {
                    return ModalWideNavigationRailDialogLayout.a(this.b, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public boolean getShouldCreateCompositionOnAttachedToWindow() {
        return this.shouldCreateCompositionOnAttachedToWindow;
    }

    public final boolean getShouldDismissOnBackPress() {
        return this.shouldDismissOnBackPress;
    }

    @Override // androidx.compose.ui.window.DialogWindowProvider
    public Window getWindow() {
        return this.window;
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        maybeRegisterBackCallback();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        maybeUnregisterBackCallback();
    }

    public final void setContent(CompositionContext parent, Function2<? super Composer, ? super Integer, Unit> content) {
        setParentCompositionContext(parent);
        setContent(content);
        this.shouldCreateCompositionOnAttachedToWindow = true;
        createComposition();
    }

    private final void setContent(Function2<? super Composer, ? super Integer, Unit> function2) {
        this.content.setValue(function2);
    }
}
