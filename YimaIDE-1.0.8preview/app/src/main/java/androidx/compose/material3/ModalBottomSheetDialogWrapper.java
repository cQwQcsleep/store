package androidx.compose.material3;

import android.graphics.Outline;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.Window;
import androidx.activity.BackEventCompat;
import androidx.activity.ComponentDialog;
import androidx.activity.OnBackPressedCallback;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.material3.internal.BasicEdgeToEdgeDialog_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.ui.platform.AbstractComposeView;
import androidx.compose.ui.platform.ViewRootForInspector;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.window.SecureFlagPolicy;
import androidx.core.app.NotificationCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002:\u00017Ba\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00150\u0013\u0012\u0006\u0010\u0016\u001a\u00020\u0017¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010$\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0002J&\u0010%\u001a\u00020\u00052\u0006\u0010&\u001a\u00020'2\u0011\u0010(\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b)¢\u0006\u0002\u0010*J\u0010\u0010+\u001a\u00020\u00052\u0006\u0010,\u001a\u00020-H\u0002J3\u0010.\u001a\u00020\u00052\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b/\u00100J\u0006\u00101\u001a\u00020\u0005J\u0010\u00102\u001a\u0002032\u0006\u00104\u001a\u000205H\u0016J\b\u00106\u001a\u00020\u0005H\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001aR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001fR\u0014\u0010 \u001a\u00020!8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#¨\u00068"}, d2 = {"Landroidx/compose/material3/ModalBottomSheetDialogWrapper;", "Landroidx/activity/ComponentDialog;", "Landroidx/compose/ui/platform/ViewRootForInspector;", "onDismissRequest", "Lkotlin/Function0;", "", "properties", "Landroidx/compose/material3/ModalBottomSheetProperties;", "contentColor", "Landroidx/compose/ui/graphics/Color;", "composeView", "Landroid/view/View;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "density", "Landroidx/compose/ui/unit/Density;", "dialogId", "Ljava/util/UUID;", "predictiveBackProgress", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "<init>", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/ModalBottomSheetProperties;JLandroid/view/View;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/unit/Density;Ljava/util/UUID;Landroidx/compose/animation/core/Animatable;Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "dialogLayout", "Landroidx/compose/material3/ModalBottomSheetDialogLayout;", "maxSupportedElevation", "Landroidx/compose/ui/unit/Dp;", "F", "subCompositionView", "Landroidx/compose/ui/platform/AbstractComposeView;", "getSubCompositionView", "()Landroidx/compose/ui/platform/AbstractComposeView;", "setLayoutDirection", "setContent", "parentComposition", "Landroidx/compose/runtime/CompositionContext;", "children", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/runtime/CompositionContext;Lkotlin/jvm/functions/Function2;)V", "setSecurePolicy", "securePolicy", "Landroidx/compose/ui/window/SecureFlagPolicy;", "updateParameters", "updateParameters-9LQNqLg", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/ModalBottomSheetProperties;JLandroidx/compose/ui/unit/LayoutDirection;)V", "disposeComposition", "onTouchEvent", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "cancel", "PredictiveBackOnBackPressedCallback", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class ModalBottomSheetDialogWrapper extends ComponentDialog implements ViewRootForInspector {
    private final View composeView;
    private long contentColor;
    private final ModalBottomSheetDialogLayout dialogLayout;
    private final float maxSupportedElevation;
    private Function0<Unit> onDismissRequest;
    private ModalBottomSheetProperties properties;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001b\u001a\u00020\fH\u0016J\b\u0010\u001c\u001a\u00020\fH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R \u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u001d"}, d2 = {"Landroidx/compose/material3/ModalBottomSheetDialogWrapper$PredictiveBackOnBackPressedCallback;", "Landroidx/activity/OnBackPressedCallback;", "isEnabled", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "predictiveBackProgress", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "onDismissRequest", "Lkotlin/Function0;", "", "<init>", "(ZLkotlinx/coroutines/CoroutineScope;Landroidx/compose/animation/core/Animatable;Lkotlin/jvm/functions/Function0;)V", "getScope", "()Lkotlinx/coroutines/CoroutineScope;", "getPredictiveBackProgress", "()Landroidx/compose/animation/core/Animatable;", "getOnDismissRequest", "()Lkotlin/jvm/functions/Function0;", "setOnDismissRequest", "(Lkotlin/jvm/functions/Function0;)V", "handleOnBackStarted", "backEvent", "Landroidx/activity/BackEventCompat;", "handleOnBackProgressed", "handleOnBackPressed", "handleOnBackCancelled", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class PredictiveBackOnBackPressedCallback extends OnBackPressedCallback {
        private Function0<Unit> onDismissRequest;
        private final Animatable<Float, AnimationVector1D> predictiveBackProgress;
        private final CoroutineScope scope;

        public PredictiveBackOnBackPressedCallback(boolean z, CoroutineScope coroutineScope, Animatable<Float, AnimationVector1D> animatable, Function0<Unit> function0) {
            super(z);
            this.scope = coroutineScope;
            this.predictiveBackProgress = animatable;
            this.onDismissRequest = function0;
        }

        public final Function0<Unit> getOnDismissRequest() {
            return this.onDismissRequest;
        }

        public final Animatable<Float, AnimationVector1D> getPredictiveBackProgress() {
            return this.predictiveBackProgress;
        }

        public final CoroutineScope getScope() {
            return this.scope;
        }

        public void handleOnBackCancelled() {
            BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new ModalBottomSheetDialogWrapper$PredictiveBackOnBackPressedCallback$handleOnBackCancelled$1(this, null), 3, (Object) null);
        }

        public void handleOnBackPressed() {
            this.onDismissRequest.invoke();
        }

        public void handleOnBackProgressed(BackEventCompat backEvent) {
            BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new ModalBottomSheetDialogWrapper$PredictiveBackOnBackPressedCallback$handleOnBackProgressed$1(this, backEvent, null), 3, (Object) null);
        }

        public void handleOnBackStarted(BackEventCompat backEvent) {
            BuildersKt.launch$default(this.scope, (CoroutineContext) null, (CoroutineStart) null, new ModalBottomSheetDialogWrapper$PredictiveBackOnBackPressedCallback$handleOnBackStarted$1(this, backEvent, null), 3, (Object) null);
        }

        public final void setOnDismissRequest(Function0<Unit> function0) {
            this.onDismissRequest = function0;
        }
    }

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutDirection.values().length];
            try {
                iArr[LayoutDirection.Ltr.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LayoutDirection.Rtl.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ModalBottomSheetDialogWrapper(Function0<Unit> function0, ModalBottomSheetProperties modalBottomSheetProperties, long j, View view, LayoutDirection layoutDirection, Density density, UUID uuid, Animatable<Float, AnimationVector1D> animatable, CoroutineScope coroutineScope) {
        super(new ContextThemeWrapper(view.getContext(), R.style.EdgeToEdgeFloatingDialogWindowTheme), 0, 2, (DefaultConstructorMarker) null);
        this.onDismissRequest = function0;
        this.properties = modalBottomSheetProperties;
        this.contentColor = j;
        this.composeView = view;
        float fM6022constructorimpl = Dp.m6022constructorimpl(8.0f);
        this.maxSupportedElevation = fM6022constructorimpl;
        Window window = getWindow();
        if (window == null) {
            k2d.a("Dialog has no window");
            throw null;
        }
        window.requestFeature(1);
        window.setBackgroundDrawableResource(android.R.color.transparent);
        WindowCompat.setDecorFitsSystemWindows(window, false);
        ModalBottomSheetDialogLayout modalBottomSheetDialogLayout = new ModalBottomSheetDialogLayout(getContext(), window);
        modalBottomSheetDialogLayout.setTag(androidx.compose.ui.R.id.compose_view_saveable_id_tag, "Dialog:" + uuid);
        modalBottomSheetDialogLayout.setClipChildren(false);
        modalBottomSheetDialogLayout.setElevation(density.mo4557toPx0680j_4(fM6022constructorimpl));
        modalBottomSheetDialogLayout.setOutlineProvider(new ViewOutlineProvider() { // from class: androidx.compose.material3.ModalBottomSheetDialogWrapper$1$2
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view2, Outline result) {
                result.setRect(0, 0, view2.getWidth(), view2.getHeight());
                result.setAlpha(0.0f);
            }
        });
        this.dialogLayout = modalBottomSheetDialogLayout;
        setContentView(modalBottomSheetDialogLayout);
        ViewTreeLifecycleOwner.set(modalBottomSheetDialogLayout, ViewTreeLifecycleOwner.get(view));
        ViewTreeViewModelStoreOwner.set(modalBottomSheetDialogLayout, ViewTreeViewModelStoreOwner.get(view));
        ViewTreeSavedStateRegistryOwner.set(modalBottomSheetDialogLayout, ViewTreeSavedStateRegistryOwner.get(view));
        m629updateParameters9LQNqLg(this.onDismissRequest, this.properties, this.contentColor, layoutDirection);
        WindowInsetsControllerCompat insetsController = WindowCompat.getInsetsController(window, window.getDecorView());
        Boolean isAppearanceLightStatusBars = this.properties.getIsAppearanceLightStatusBars();
        insetsController.setAppearanceLightStatusBars(isAppearanceLightStatusBars != null ? isAppearanceLightStatusBars.booleanValue() : ModalBottomSheet_androidKt.m637isDark8_81llA(this.contentColor));
        Boolean isAppearanceLightNavigationBars = this.properties.getIsAppearanceLightNavigationBars();
        insetsController.setAppearanceLightNavigationBars(isAppearanceLightNavigationBars != null ? isAppearanceLightNavigationBars.booleanValue() : ModalBottomSheet_androidKt.m637isDark8_81llA(this.contentColor));
        getOnBackPressedDispatcher().addCallback(this, new PredictiveBackOnBackPressedCallback(this.properties.getShouldDismissOnBackPress(), coroutineScope, animatable, new Function0() { // from class: androidx.compose.material3.c2
            public final Object invoke() {
                return ModalBottomSheetDialogWrapper.b(this.b);
            }
        }));
    }

    public static Unit b(ModalBottomSheetDialogWrapper modalBottomSheetDialogWrapper) {
        modalBottomSheetDialogWrapper.onDismissRequest.invoke();
        return Unit.INSTANCE;
    }

    private final void setLayoutDirection(LayoutDirection layoutDirection) {
        ModalBottomSheetDialogLayout modalBottomSheetDialogLayout = this.dialogLayout;
        int i = WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()];
        int i2 = 1;
        if (i == 1) {
            i2 = 0;
        } else if (i != 2) {
            bu8.a();
            return;
        }
        modalBottomSheetDialogLayout.setLayoutDirection(i2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void setSecurePolicy(SecureFlagPolicy securePolicy) {
        boolean zShouldApplySecureFlag = BasicEdgeToEdgeDialog_androidKt.shouldApplySecureFlag(securePolicy, ModalBottomSheet_androidKt.isFlagSecureEnabled(this.composeView));
        Window window = getWindow();
        window.getClass();
        window.setFlags(zShouldApplySecureFlag ? 8192 : -8193, 8192);
    }

    public void cancel() {
    }

    public final void disposeComposition() {
        this.dialogLayout.disposeComposition();
    }

    @Override // androidx.compose.ui.platform.ViewRootForInspector
    public AbstractComposeView getSubCompositionView() {
        return this.dialogLayout;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public boolean onTouchEvent(MotionEvent event) {
        boolean zOnTouchEvent = super/*android.app.Dialog*/.onTouchEvent(event);
        if (zOnTouchEvent) {
            this.onDismissRequest.invoke();
        }
        return zOnTouchEvent;
    }

    public final void setContent(CompositionContext parentComposition, Function2<? super Composer, ? super Integer, Unit> children) {
        this.dialogLayout.setContent(parentComposition, children);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: updateParameters-9LQNqLg, reason: not valid java name */
    public final void m629updateParameters9LQNqLg(Function0<Unit> onDismissRequest, ModalBottomSheetProperties properties, long contentColor, LayoutDirection layoutDirection) {
        this.onDismissRequest = onDismissRequest;
        this.properties = properties;
        this.contentColor = contentColor;
        setSecurePolicy(properties.getSecurePolicy());
        setLayoutDirection(layoutDirection);
        Window window = getWindow();
        if (window != null) {
            window.setLayout(-1, -1);
        }
        Window window2 = getWindow();
        if (window2 != null) {
            window2.setSoftInputMode(48);
        }
    }

    public /* synthetic */ ModalBottomSheetDialogWrapper(Function0 function0, ModalBottomSheetProperties modalBottomSheetProperties, long j, View view, LayoutDirection layoutDirection, Density density, UUID uuid, Animatable animatable, CoroutineScope coroutineScope, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, modalBottomSheetProperties, j, view, layoutDirection, density, uuid, animatable, coroutineScope);
    }
}
