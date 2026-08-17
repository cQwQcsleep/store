package androidx.compose.ui.window;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.platform.AbstractComposeView;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.math.MathKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0016\u0010\u001f\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018J\u001d\u0010 \u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0010¢\u0006\u0002\b$J\u0018\u0010%\u001a\u00020\"2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\"H\u0002J5\u0010'\u001a\u00020\u000e2\u0006\u0010(\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\"2\u0006\u0010*\u001a\u00020\"2\u0006\u0010+\u001a\u00020\"2\u0006\u0010,\u001a\u00020\"H\u0010¢\u0006\u0002\b-J&\u0010\u0013\u001a\u00020\u000e2\u0006\u0010.\u001a\u00020/2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000f¢\u0006\u0002\u00100J\u0018\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000202H\u0016J~\u00106\u001a\u0002H7\"\u0004\b\u0000\u001072\u0006\u00108\u001a\u0002H72`\u00109\u001a\\\u0012\u0013\u0012\u00110\"¢\u0006\f\b;\u0012\b\b<\u0012\u0004\b\b()\u0012\u0013\u0012\u00110\"¢\u0006\f\b;\u0012\b\b<\u0012\u0004\b\b(*\u0012\u0013\u0012\u00110\"¢\u0006\f\b;\u0012\b\b<\u0012\u0004\b\b(+\u0012\u0013\u0012\u00110\"¢\u0006\f\b;\u0012\b\b<\u0012\u0004\b\b(,\u0012\u0004\u0012\u0002H70:H\u0082\b¢\u0006\u0002\u0010=J\u000e\u0010>\u001a\u00020\u00182\u0006\u0010?\u001a\u00020@J\r\u0010A\u001a\u00020\u000eH\u0017¢\u0006\u0002\u0010BR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bRA\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000f2\u0011\u0010\f\u001a\r\u0012\u0004\u0012\u00020\u000e0\r¢\u0006\u0002\b\u000f8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u0018@RX\u0094\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006C"}, d2 = {"Landroidx/compose/ui/window/DialogLayout;", "Landroidx/compose/ui/platform/AbstractComposeView;", "Landroidx/compose/ui/window/DialogWindowProvider;", "Landroidx/core/view/OnApplyWindowInsetsListener;", "context", "Landroid/content/Context;", "window", "Landroid/view/Window;", "<init>", "(Landroid/content/Context;Landroid/view/Window;)V", "getWindow", "()Landroid/view/Window;", "<set-?>", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "content", "getContent", "()Lkotlin/jvm/functions/Function2;", "setContent", "(Lkotlin/jvm/functions/Function2;)V", "content$delegate", "Landroidx/compose/runtime/MutableState;", "usePlatformDefaultWidth", "", "decorFitsSystemWindows", "hasCalledSetLayout", "value", "shouldCreateCompositionOnAttachedToWindow", "getShouldCreateCompositionOnAttachedToWindow", "()Z", "updateProperties", "internalOnMeasure", "widthMeasureSpec", "", "heightMeasureSpec", "internalOnMeasure$ui", "getMaxDialogHeightExcludingInsets", "height", "internalOnLayout", "changed", "left", "top", "right", "bottom", "internalOnLayout$ui", "parent", "Landroidx/compose/runtime/CompositionContext;", "(Landroidx/compose/runtime/CompositionContext;Lkotlin/jvm/functions/Function2;)V", "onApplyWindowInsets", "Landroidx/core/view/WindowInsetsCompat;", "v", "Landroid/view/View;", "insets", "insetValue", "T", "unchangedValue", "block", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function4;)Ljava/lang/Object;", "isInsideContent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "Content", "(Landroidx/compose/runtime/Composer;I)V", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class DialogLayout extends AbstractComposeView implements DialogWindowProvider, OnApplyWindowInsetsListener {

    /* JADX INFO: renamed from: content$delegate, reason: from kotlin metadata */
    private final MutableState content;
    private boolean decorFitsSystemWindows;
    private boolean hasCalledSetLayout;
    private boolean shouldCreateCompositionOnAttachedToWindow;
    private boolean usePlatformDefaultWidth;
    private final Window window;

    public DialogLayout(Context context, Window window) {
        super(context, null, 0, 6, null);
        this.window = window;
        this.content = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(ComposableSingletons$AndroidDialog_androidKt.INSTANCE.getLambda$210148896$ui(), null, 2, null);
        ViewCompat.setOnApplyWindowInsetsListener(this, this);
        ViewCompat.setWindowInsetsAnimationCallback(this, new WindowInsetsAnimationCompat.Callback() { // from class: androidx.compose.ui.window.DialogLayout.1
            {
                super(1);
            }

            @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
            public WindowInsetsCompat onProgress(WindowInsetsCompat insets, List<WindowInsetsAnimationCompat> runningAnimations) {
                DialogLayout dialogLayout = DialogLayout.this;
                if (!dialogLayout.decorFitsSystemWindows) {
                    View childAt = dialogLayout.getChildAt(0);
                    int iMax = Math.max(0, childAt.getLeft());
                    int iMax2 = Math.max(0, childAt.getTop());
                    int iMax3 = Math.max(0, dialogLayout.getWidth() - childAt.getRight());
                    int iMax4 = Math.max(0, dialogLayout.getHeight() - childAt.getBottom());
                    if (iMax != 0 || iMax2 != 0 || iMax3 != 0 || iMax4 != 0) {
                        return insets.inset(iMax, iMax2, iMax3, iMax4);
                    }
                }
                return insets;
            }

            @Override // androidx.core.view.WindowInsetsAnimationCompat.Callback
            public WindowInsetsAnimationCompat.BoundsCompat onStart(WindowInsetsAnimationCompat animation, WindowInsetsAnimationCompat.BoundsCompat bounds) {
                DialogLayout dialogLayout = DialogLayout.this;
                if (!dialogLayout.decorFitsSystemWindows) {
                    View childAt = dialogLayout.getChildAt(0);
                    int iMax = Math.max(0, childAt.getLeft());
                    int iMax2 = Math.max(0, childAt.getTop());
                    int iMax3 = Math.max(0, dialogLayout.getWidth() - childAt.getRight());
                    int iMax4 = Math.max(0, dialogLayout.getHeight() - childAt.getBottom());
                    if (iMax != 0 || iMax2 != 0 || iMax3 != 0 || iMax4 != 0) {
                        return bounds.inset(Insets.of(iMax, iMax2, iMax3, iMax4));
                    }
                }
                return bounds;
            }
        });
    }

    private final Function2<Composer, Integer, Unit> getContent() {
        return (Function2) this.content.getValue();
    }

    private final int getMaxDialogHeightExcludingInsets(Window window, int height) {
        return height;
    }

    private final <T> T insetValue(T unchangedValue, Function4<? super Integer, ? super Integer, ? super Integer, ? super Integer, ? extends T> block) {
        if (!this.decorFitsSystemWindows) {
            View childAt = getChildAt(0);
            int iMax = Math.max(0, childAt.getLeft());
            int iMax2 = Math.max(0, childAt.getTop());
            int iMax3 = Math.max(0, getWidth() - childAt.getRight());
            int iMax4 = Math.max(0, getHeight() - childAt.getBottom());
            if (iMax != 0 || iMax2 != 0 || iMax3 != 0 || iMax4 != 0) {
                return (T) block.invoke(Integer.valueOf(iMax), Integer.valueOf(iMax2), Integer.valueOf(iMax3), Integer.valueOf(iMax4));
            }
        }
        return unchangedValue;
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public void Content(Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1735448596);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(this) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1735448596, i2, -1, "androidx.compose.ui.window.DialogLayout.Content (AndroidDialog.android.kt:454)");
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.DialogLayout.Content.4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i3) {
                    DialogLayout.this.Content(composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public boolean getShouldCreateCompositionOnAttachedToWindow() {
        return this.shouldCreateCompositionOnAttachedToWindow;
    }

    @Override // androidx.compose.ui.window.DialogWindowProvider
    public Window getWindow() {
        return this.window;
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public void internalOnLayout$ui(boolean changed, int left, int top, int right, int bottom) {
        View childAt = getChildAt(0);
        if (childAt == null) {
            return;
        }
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int i = right - left;
        int i2 = bottom - top;
        int measuredWidth = childAt.getMeasuredWidth();
        int measuredHeight = childAt.getMeasuredHeight();
        int paddingLeft2 = getPaddingLeft() + (((i - measuredWidth) - paddingLeft) / 2);
        int paddingTop2 = getPaddingTop() + (((i2 - measuredHeight) - paddingTop) / 2);
        childAt.layout(paddingLeft2, paddingTop2, measuredWidth + paddingLeft2, measuredHeight + paddingTop2);
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public void internalOnMeasure$ui(int widthMeasureSpec, int heightMeasureSpec) {
        int maxDialogHeightExcludingInsets;
        int iMin;
        View childAt = getChildAt(0);
        if (childAt == null) {
            super.internalOnMeasure$ui(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        int mode = View.MeasureSpec.getMode(heightMeasureSpec);
        if (mode == Integer.MIN_VALUE && !this.usePlatformDefaultWidth && getWindow().getAttributes().height == -2) {
            maxDialogHeightExcludingInsets = this.decorFitsSystemWindows ? getMaxDialogHeightExcludingInsets(getWindow(), size2) : size2 + 1;
        } else {
            maxDialogHeightExcludingInsets = size2;
        }
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        int i = size - paddingLeft;
        if (i < 0) {
            i = 0;
        }
        int i2 = maxDialogHeightExcludingInsets - paddingTop;
        int i3 = i2 >= 0 ? i2 : 0;
        int mode2 = View.MeasureSpec.getMode(widthMeasureSpec);
        if (mode2 != 0) {
            widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE);
        }
        if (mode != 0) {
            heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE);
        }
        childAt.measure(widthMeasureSpec, heightMeasureSpec);
        if (mode2 == Integer.MIN_VALUE) {
            size = Math.min(size, childAt.getMeasuredWidth() + paddingLeft);
        } else if (mode2 != 1073741824) {
            size = childAt.getMeasuredWidth() + paddingLeft;
        }
        if (mode != Integer.MIN_VALUE) {
            iMin = mode != 1073741824 ? childAt.getMeasuredHeight() + paddingTop : size2;
        } else {
            iMin = Math.min(size2, childAt.getMeasuredHeight() + paddingTop);
        }
        setMeasuredDimension(size, iMin);
        if (this.decorFitsSystemWindows || childAt.getMeasuredHeight() + paddingTop <= size2 || getWindow().getAttributes().height != -2) {
            return;
        }
        getWindow().addFlags(Integer.MIN_VALUE);
        if (this.usePlatformDefaultWidth) {
            return;
        }
        getWindow().setLayout(-1, -1);
    }

    public final boolean isInsideContent(MotionEvent event) {
        View childAt;
        int iRoundToInt;
        float x = event.getX();
        if (!Float.isInfinite(x) && !Float.isNaN(x)) {
            float y = event.getY();
            if (Float.isInfinite(y) || Float.isNaN(y) || (childAt = getChildAt(0)) == null) {
                return false;
            }
            int left = getLeft() + childAt.getLeft();
            int width = childAt.getWidth() + left;
            int top = getTop() + childAt.getTop();
            int height = childAt.getHeight() + top;
            int iRoundToInt2 = MathKt.roundToInt(event.getX());
            if (left <= iRoundToInt2 && iRoundToInt2 <= width && top <= (iRoundToInt = MathKt.roundToInt(event.getY())) && iRoundToInt <= height) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.core.view.OnApplyWindowInsetsListener
    public WindowInsetsCompat onApplyWindowInsets(View v, WindowInsetsCompat insets) {
        if (!this.decorFitsSystemWindows) {
            View childAt = getChildAt(0);
            int iMax = Math.max(0, childAt.getLeft());
            int iMax2 = Math.max(0, childAt.getTop());
            int iMax3 = Math.max(0, getWidth() - childAt.getRight());
            int iMax4 = Math.max(0, getHeight() - childAt.getBottom());
            if (iMax != 0 || iMax2 != 0 || iMax3 != 0 || iMax4 != 0) {
                return insets.inset(iMax, iMax2, iMax3, iMax4);
            }
        }
        return insets;
    }

    public final void setContent(CompositionContext parent, Function2<? super Composer, ? super Integer, Unit> content) {
        setParentCompositionContext(parent);
        setContent(content);
        this.shouldCreateCompositionOnAttachedToWindow = true;
        createComposition();
    }

    public final void updateProperties(boolean usePlatformDefaultWidth, boolean decorFitsSystemWindows) {
        boolean z = (this.hasCalledSetLayout && usePlatformDefaultWidth == this.usePlatformDefaultWidth && decorFitsSystemWindows == this.decorFitsSystemWindows) ? false : true;
        this.usePlatformDefaultWidth = usePlatformDefaultWidth;
        this.decorFitsSystemWindows = decorFitsSystemWindows;
        if (z) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            int i = usePlatformDefaultWidth ? -2 : -1;
            if (i == attributes.width && this.hasCalledSetLayout) {
                return;
            }
            getWindow().setLayout(i, -2);
            this.hasCalledSetLayout = true;
        }
    }

    private final void setContent(Function2<? super Composer, ? super Integer, Unit> function2) {
        this.content.setValue(function2);
    }
}
