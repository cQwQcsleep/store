package androidx.compose.foundation.layout;

import android.graphics.Path;
import android.view.View;
import androidx.compose.foundation.layout.WindowInsetsHolder;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.core.graphics.Insets;
import androidx.core.view.DisplayCutoutCompat;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsAnimationCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u0001\u0018\u0000 Q2\u00020\u0001:\u0001QB\u001b\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010I\u001a\u00020J2\u0006\u0010\u0004\u001a\u00020\u0005J\u000e\u0010K\u001a\u00020J2\u0006\u0010\u0004\u001a\u00020\u0005J\u0018\u0010L\u001a\u00020J2\u0006\u0010M\u001a\u00020\u00032\b\b\u0002\u0010N\u001a\u00020FJ\u000e\u0010O\u001a\u00020J2\u0006\u0010M\u001a\u00020\u0003J\u000e\u0010P\u001a\u00020J2\u0006\u0010M\u001a\u00020\u0003R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000bR\u0011\u0010\u0012\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000bR\u0011\u0010\u0014\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000bR\u0011\u0010\u0016\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000bR\u0011\u0010\u0018\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000bR\u0011\u0010\u001a\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000bR\u0011\u0010\u001c\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR/\u0010\"\u001a\u0004\u0018\u00010!2\b\u0010 \u001a\u0004\u0018\u00010!8F@BX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\u0011\u0010)\u001a\u00020*¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010-\u001a\u00020*¢\u0006\b\n\u0000\u001a\u0004\b.\u0010,R\u0011\u0010/\u001a\u00020*¢\u0006\b\n\u0000\u001a\u0004\b0\u0010,R\u0011\u00101\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001fR\u0011\u00103\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u001fR\u0011\u00105\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u001fR\u0011\u00107\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u001fR\u0011\u00109\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u001fR\u0011\u0010;\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u001fR\u0011\u0010=\u001a\u00020\u001d¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u001fR\u0017\u0010?\u001a\u00020@¢\u0006\u000e\n\u0000\u0012\u0004\bA\u0010B\u001a\u0004\bC\u0010DR\u000e\u0010E\u001a\u00020FX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020HX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006R"}, d2 = {"Landroidx/compose/foundation/layout/WindowInsetsHolder;", "", "insets", "Landroidx/core/view/WindowInsetsCompat;", "view", "Landroid/view/View;", "<init>", "(Landroidx/core/view/WindowInsetsCompat;Landroid/view/View;)V", "captionBar", "Landroidx/compose/foundation/layout/AndroidWindowInsets;", "getCaptionBar", "()Landroidx/compose/foundation/layout/AndroidWindowInsets;", "displayCutout", "getDisplayCutout", "ime", "getIme", "mandatorySystemGestures", "getMandatorySystemGestures", "navigationBars", "getNavigationBars", "statusBars", "getStatusBars", "systemBars", "getSystemBars", "systemGestures", "getSystemGestures", "tappableElement", "getTappableElement", "waterfall", "Landroidx/compose/foundation/layout/ValueInsets;", "getWaterfall", "()Landroidx/compose/foundation/layout/ValueInsets;", "<set-?>", "Landroidx/compose/ui/graphics/Path;", "cutoutPath", "getCutoutPath", "()Landroidx/compose/ui/graphics/Path;", "setCutoutPath", "(Landroidx/compose/ui/graphics/Path;)V", "cutoutPath$delegate", "Landroidx/compose/runtime/MutableState;", "safeDrawing", "Landroidx/compose/foundation/layout/WindowInsets;", "getSafeDrawing", "()Landroidx/compose/foundation/layout/WindowInsets;", "safeGestures", "getSafeGestures", "safeContent", "getSafeContent", "captionBarIgnoringVisibility", "getCaptionBarIgnoringVisibility", "navigationBarsIgnoringVisibility", "getNavigationBarsIgnoringVisibility", "statusBarsIgnoringVisibility", "getStatusBarsIgnoringVisibility", "systemBarsIgnoringVisibility", "getSystemBarsIgnoringVisibility", "tappableElementIgnoringVisibility", "getTappableElementIgnoringVisibility", "imeAnimationTarget", "getImeAnimationTarget", "imeAnimationSource", "getImeAnimationSource", "consumes", "", "getConsumes$annotations", "()V", "getConsumes", "()Z", "accessCount", "", "insetsListener", "Landroidx/compose/foundation/layout/InsetsListener;", "incrementAccessors", "", "decrementAccessors", "update", "windowInsets", "types", "updateImeAnimationSource", "updateImeAnimationTarget", "Companion", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class WindowInsetsHolder {
    private static boolean testInsets;
    private int accessCount;
    private final AndroidWindowInsets captionBar;
    private final ValueInsets captionBarIgnoringVisibility;
    private final boolean consumes;

    /* JADX INFO: renamed from: cutoutPath$delegate, reason: from kotlin metadata */
    private final MutableState cutoutPath;
    private final AndroidWindowInsets displayCutout;
    private final AndroidWindowInsets ime;
    private final ValueInsets imeAnimationSource;
    private final ValueInsets imeAnimationTarget;
    private final InsetsListener insetsListener;
    private final AndroidWindowInsets mandatorySystemGestures;
    private final AndroidWindowInsets navigationBars;
    private final ValueInsets navigationBarsIgnoringVisibility;
    private final WindowInsets safeContent;
    private final WindowInsets safeDrawing;
    private final WindowInsets safeGestures;
    private final AndroidWindowInsets statusBars;
    private final ValueInsets statusBarsIgnoringVisibility;
    private final AndroidWindowInsets systemBars;
    private final ValueInsets systemBarsIgnoringVisibility;
    private final AndroidWindowInsets systemGestures;
    private final AndroidWindowInsets tappableElement;
    private final ValueInsets tappableElementIgnoringVisibility;
    private final ValueInsets waterfall;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final WeakHashMap<View, WindowInsetsHolder> viewMap = new WeakHashMap<>();

    private WindowInsetsHolder(WindowInsetsCompat windowInsetsCompat, View view) {
        DisplayCutoutCompat displayCutout;
        Path cutoutPath;
        DisplayCutoutCompat displayCutout2;
        Insets waterfallInsets;
        Companion companion = INSTANCE;
        AndroidWindowInsets androidWindowInsetsSystemInsets = companion.systemInsets(windowInsetsCompat, WindowInsetsCompat.Type.captionBar(), "captionBar");
        this.captionBar = androidWindowInsetsSystemInsets;
        AndroidWindowInsets androidWindowInsetsSystemInsets2 = companion.systemInsets(windowInsetsCompat, WindowInsetsCompat.Type.displayCutout(), "displayCutout");
        this.displayCutout = androidWindowInsetsSystemInsets2;
        AndroidWindowInsets androidWindowInsetsSystemInsets3 = companion.systemInsets(windowInsetsCompat, WindowInsetsCompat.Type.ime(), "ime");
        this.ime = androidWindowInsetsSystemInsets3;
        AndroidWindowInsets androidWindowInsetsSystemInsets4 = companion.systemInsets(windowInsetsCompat, WindowInsetsCompat.Type.mandatorySystemGestures(), "mandatorySystemGestures");
        this.mandatorySystemGestures = androidWindowInsetsSystemInsets4;
        AndroidWindowInsets androidWindowInsetsSystemInsets5 = companion.systemInsets(windowInsetsCompat, WindowInsetsCompat.Type.navigationBars(), "navigationBars");
        this.navigationBars = androidWindowInsetsSystemInsets5;
        AndroidWindowInsets androidWindowInsetsSystemInsets6 = companion.systemInsets(windowInsetsCompat, WindowInsetsCompat.Type.statusBars(), "statusBars");
        this.statusBars = androidWindowInsetsSystemInsets6;
        AndroidWindowInsets androidWindowInsetsSystemInsets7 = companion.systemInsets(windowInsetsCompat, WindowInsetsCompat.Type.systemBars(), "systemBars");
        this.systemBars = androidWindowInsetsSystemInsets7;
        AndroidWindowInsets androidWindowInsetsSystemInsets8 = companion.systemInsets(windowInsetsCompat, WindowInsetsCompat.Type.systemGestures(), "systemGestures");
        this.systemGestures = androidWindowInsetsSystemInsets8;
        AndroidWindowInsets androidWindowInsetsSystemInsets9 = companion.systemInsets(windowInsetsCompat, WindowInsetsCompat.Type.tappableElement(), "tappableElement");
        this.tappableElement = androidWindowInsetsSystemInsets9;
        ValueInsets ValueInsets = WindowInsets_androidKt.ValueInsets((windowInsetsCompat == null || (displayCutout2 = windowInsetsCompat.getDisplayCutout()) == null || (waterfallInsets = displayCutout2.getWaterfallInsets()) == null) ? Insets.NONE : waterfallInsets, "waterfall");
        this.waterfall = ValueInsets;
        this.cutoutPath = SnapshotStateKt.mutableStateOf$default((windowInsetsCompat == null || (displayCutout = windowInsetsCompat.getDisplayCutout()) == null || (cutoutPath = displayCutout.getCutoutPath()) == null) ? null : AndroidPath_androidKt.asComposePath(cutoutPath), (SnapshotMutationPolicy) null, 2, (Object) null);
        WindowInsets windowInsetsUnion = WindowInsetsKt.union(WindowInsetsKt.union(androidWindowInsetsSystemInsets7, androidWindowInsetsSystemInsets3), androidWindowInsetsSystemInsets2);
        this.safeDrawing = windowInsetsUnion;
        WindowInsets windowInsetsUnion2 = WindowInsetsKt.union(WindowInsetsKt.union(WindowInsetsKt.union(androidWindowInsetsSystemInsets9, androidWindowInsetsSystemInsets4), androidWindowInsetsSystemInsets8), ValueInsets);
        this.safeGestures = windowInsetsUnion2;
        this.safeContent = WindowInsetsKt.union(windowInsetsUnion, windowInsetsUnion2);
        this.captionBarIgnoringVisibility = companion.valueInsetsIgnoringVisibility(windowInsetsCompat, WindowInsetsCompat.Type.captionBar(), "captionBarIgnoringVisibility");
        this.navigationBarsIgnoringVisibility = companion.valueInsetsIgnoringVisibility(windowInsetsCompat, WindowInsetsCompat.Type.navigationBars(), "navigationBarsIgnoringVisibility");
        this.statusBarsIgnoringVisibility = companion.valueInsetsIgnoringVisibility(windowInsetsCompat, WindowInsetsCompat.Type.statusBars(), "statusBarsIgnoringVisibility");
        this.systemBarsIgnoringVisibility = companion.valueInsetsIgnoringVisibility(windowInsetsCompat, WindowInsetsCompat.Type.systemBars(), "systemBarsIgnoringVisibility");
        this.tappableElementIgnoringVisibility = companion.valueInsetsIgnoringVisibility(windowInsetsCompat, WindowInsetsCompat.Type.tappableElement(), "tappableElementIgnoringVisibility");
        Insets insets = Insets.NONE;
        this.imeAnimationTarget = WindowInsets_androidKt.ValueInsets(insets, "imeAnimationTarget");
        this.imeAnimationSource = WindowInsets_androidKt.ValueInsets(insets, "imeAnimationSource");
        Object parent = view.getParent();
        View view2 = parent instanceof View ? (View) parent : null;
        Object tag = view2 != null ? view2.getTag(androidx.compose.ui.R.id.consume_window_insets_tag) : null;
        Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
        this.consumes = bool != null ? bool.booleanValue() : false;
        this.insetsListener = new InsetsListener(this);
        WindowInsetsCompat rootWindowInsets = ViewCompat.getRootWindowInsets(view);
        if (rootWindowInsets != null) {
            androidWindowInsetsSystemInsets.setVisible(rootWindowInsets.isVisible(WindowInsetsCompat.Type.captionBar()));
            androidWindowInsetsSystemInsets2.setVisible(rootWindowInsets.isVisible(WindowInsetsCompat.Type.displayCutout()));
            androidWindowInsetsSystemInsets3.setVisible(rootWindowInsets.isVisible(WindowInsetsCompat.Type.ime()));
            androidWindowInsetsSystemInsets4.setVisible(rootWindowInsets.isVisible(WindowInsetsCompat.Type.mandatorySystemGestures()));
            androidWindowInsetsSystemInsets5.setVisible(rootWindowInsets.isVisible(WindowInsetsCompat.Type.navigationBars()));
            androidWindowInsetsSystemInsets6.setVisible(rootWindowInsets.isVisible(WindowInsetsCompat.Type.statusBars()));
            androidWindowInsetsSystemInsets7.setVisible(rootWindowInsets.isVisible(WindowInsetsCompat.Type.systemBars()));
            androidWindowInsetsSystemInsets8.setVisible(rootWindowInsets.isVisible(WindowInsetsCompat.Type.systemGestures()));
            androidWindowInsetsSystemInsets9.setVisible(rootWindowInsets.isVisible(WindowInsetsCompat.Type.tappableElement()));
        }
    }

    public static /* synthetic */ void getConsumes$annotations() {
    }

    private final void setCutoutPath(androidx.compose.ui.graphics.Path path) {
        this.cutoutPath.setValue(path);
    }

    public static /* synthetic */ void update$default(WindowInsetsHolder windowInsetsHolder, WindowInsetsCompat windowInsetsCompat, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        windowInsetsHolder.update(windowInsetsCompat, i);
    }

    public final void decrementAccessors(View view) {
        int i = this.accessCount - 1;
        this.accessCount = i;
        if (i == 0) {
            ViewCompat.setOnApplyWindowInsetsListener(view, (OnApplyWindowInsetsListener) null);
            ViewCompat.setWindowInsetsAnimationCallback(view, (WindowInsetsAnimationCompat.Callback) null);
            view.removeOnAttachStateChangeListener(this.insetsListener);
        }
    }

    public final AndroidWindowInsets getCaptionBar() {
        return this.captionBar;
    }

    public final ValueInsets getCaptionBarIgnoringVisibility() {
        return this.captionBarIgnoringVisibility;
    }

    public final boolean getConsumes() {
        return this.consumes;
    }

    public final androidx.compose.ui.graphics.Path getCutoutPath() {
        return (androidx.compose.ui.graphics.Path) this.cutoutPath.getValue();
    }

    public final AndroidWindowInsets getDisplayCutout() {
        return this.displayCutout;
    }

    public final AndroidWindowInsets getIme() {
        return this.ime;
    }

    public final ValueInsets getImeAnimationSource() {
        return this.imeAnimationSource;
    }

    public final ValueInsets getImeAnimationTarget() {
        return this.imeAnimationTarget;
    }

    public final AndroidWindowInsets getMandatorySystemGestures() {
        return this.mandatorySystemGestures;
    }

    public final AndroidWindowInsets getNavigationBars() {
        return this.navigationBars;
    }

    public final ValueInsets getNavigationBarsIgnoringVisibility() {
        return this.navigationBarsIgnoringVisibility;
    }

    public final WindowInsets getSafeContent() {
        return this.safeContent;
    }

    public final WindowInsets getSafeDrawing() {
        return this.safeDrawing;
    }

    public final WindowInsets getSafeGestures() {
        return this.safeGestures;
    }

    public final AndroidWindowInsets getStatusBars() {
        return this.statusBars;
    }

    public final ValueInsets getStatusBarsIgnoringVisibility() {
        return this.statusBarsIgnoringVisibility;
    }

    public final AndroidWindowInsets getSystemBars() {
        return this.systemBars;
    }

    public final ValueInsets getSystemBarsIgnoringVisibility() {
        return this.systemBarsIgnoringVisibility;
    }

    public final AndroidWindowInsets getSystemGestures() {
        return this.systemGestures;
    }

    public final AndroidWindowInsets getTappableElement() {
        return this.tappableElement;
    }

    public final ValueInsets getTappableElementIgnoringVisibility() {
        return this.tappableElementIgnoringVisibility;
    }

    public final ValueInsets getWaterfall() {
        return this.waterfall;
    }

    public final void incrementAccessors(View view) {
        if (this.accessCount == 0) {
            ViewCompat.setOnApplyWindowInsetsListener(view, this.insetsListener);
            if (view.isAttachedToWindow()) {
                view.requestApplyInsets();
            }
            view.addOnAttachStateChangeListener(this.insetsListener);
            ViewCompat.setWindowInsetsAnimationCallback(view, this.insetsListener);
        }
        this.accessCount++;
    }

    public final void update(WindowInsetsCompat windowInsets, int types) {
        Insets waterfallInsets;
        Path cutoutPath;
        if (testInsets) {
            android.view.WindowInsets windowInsets2 = windowInsets.toWindowInsets();
            windowInsets2.getClass();
            windowInsets = WindowInsetsCompat.toWindowInsetsCompat(windowInsets2);
        }
        this.captionBar.update$foundation_layout(windowInsets, types);
        this.ime.update$foundation_layout(windowInsets, types);
        this.displayCutout.update$foundation_layout(windowInsets, types);
        this.navigationBars.update$foundation_layout(windowInsets, types);
        this.statusBars.update$foundation_layout(windowInsets, types);
        this.systemBars.update$foundation_layout(windowInsets, types);
        this.systemGestures.update$foundation_layout(windowInsets, types);
        this.tappableElement.update$foundation_layout(windowInsets, types);
        this.mandatorySystemGestures.update$foundation_layout(windowInsets, types);
        if (types == 0) {
            this.captionBarIgnoringVisibility.setValue$foundation_layout(WindowInsets_androidKt.toInsetsValues(windowInsets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.captionBar())));
            this.navigationBarsIgnoringVisibility.setValue$foundation_layout(WindowInsets_androidKt.toInsetsValues(windowInsets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.navigationBars())));
            this.statusBarsIgnoringVisibility.setValue$foundation_layout(WindowInsets_androidKt.toInsetsValues(windowInsets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.statusBars())));
            this.systemBarsIgnoringVisibility.setValue$foundation_layout(WindowInsets_androidKt.toInsetsValues(windowInsets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars())));
            this.tappableElementIgnoringVisibility.setValue$foundation_layout(WindowInsets_androidKt.toInsetsValues(windowInsets.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.tappableElement())));
            DisplayCutoutCompat displayCutout = windowInsets.getDisplayCutout();
            ValueInsets valueInsets = this.waterfall;
            if (displayCutout == null || (waterfallInsets = displayCutout.getWaterfallInsets()) == null) {
                waterfallInsets = Insets.NONE;
            }
            valueInsets.setValue$foundation_layout(WindowInsets_androidKt.toInsetsValues(waterfallInsets));
            setCutoutPath((displayCutout == null || (cutoutPath = displayCutout.getCutoutPath()) == null) ? null : AndroidPath_androidKt.asComposePath(cutoutPath));
        }
        Snapshot.Companion.sendApplyNotifications();
    }

    public final void updateImeAnimationSource(WindowInsetsCompat windowInsets) {
        this.imeAnimationSource.setValue$foundation_layout(WindowInsets_androidKt.toInsetsValues(windowInsets.getInsets(WindowInsetsCompat.Type.ime())));
    }

    public final void updateImeAnimationTarget(WindowInsetsCompat windowInsets) {
        this.imeAnimationTarget.setValue$foundation_layout(WindowInsets_androidKt.toInsetsValues(windowInsets.getInsets(WindowInsetsCompat.Type.ime())));
    }

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0007J\r\u0010\f\u001a\u00020\u0007H\u0007¢\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0006J\"\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\"\u0010\u0018\u001a\u00020\u00192\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Landroidx/compose/foundation/layout/WindowInsetsHolder$Companion;", "", "<init>", "()V", "viewMap", "Ljava/util/WeakHashMap;", "Landroid/view/View;", "Landroidx/compose/foundation/layout/WindowInsetsHolder;", "testInsets", "", "setUseTestInsets", "", "current", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsetsHolder;", "getOrCreateFor", "view", "systemInsets", "Landroidx/compose/foundation/layout/AndroidWindowInsets;", "windowInsets", "Landroidx/core/view/WindowInsetsCompat;", "type", "", "name", "", "valueInsetsIgnoringVisibility", "Landroidx/compose/foundation/layout/ValueInsets;", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final DisposableEffectResult current$lambda$0$0(final WindowInsetsHolder windowInsetsHolder, final View view, DisposableEffectScope disposableEffectScope) {
            windowInsetsHolder.incrementAccessors(view);
            return new DisposableEffectResult() { // from class: androidx.compose.foundation.layout.WindowInsetsHolder$Companion$current$lambda$0$0$$inlined$onDispose$1
                public void dispose() {
                    windowInsetsHolder.decrementAccessors(view);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final AndroidWindowInsets systemInsets(WindowInsetsCompat windowInsets, int type, String name) {
            AndroidWindowInsets androidWindowInsets = new AndroidWindowInsets(type, name);
            if (windowInsets != null) {
                androidWindowInsets.update$foundation_layout(windowInsets, type);
            }
            return androidWindowInsets;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ValueInsets valueInsetsIgnoringVisibility(WindowInsetsCompat windowInsets, int type, String name) {
            Insets insetsIgnoringVisibility;
            if (windowInsets == null || (insetsIgnoringVisibility = windowInsets.getInsetsIgnoringVisibility(type)) == null) {
                insetsIgnoringVisibility = Insets.NONE;
            }
            return WindowInsets_androidKt.ValueInsets(insetsIgnoringVisibility, name);
        }

        public final WindowInsetsHolder current(Composer composer, int i) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1366542614, i, -1, "androidx.compose.foundation.layout.WindowInsetsHolder.Companion.current (WindowInsets.android.kt:574)");
            }
            final View view = (View) composer.consume(AndroidCompositionLocals_androidKt.getLocalView());
            final WindowInsetsHolder orCreateFor = getOrCreateFor(view);
            boolean zChangedInstance = composer.changedInstance(orCreateFor) | composer.changedInstance(view);
            Object objRememberedValue = composer.rememberedValue();
            if (zChangedInstance || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: unf
                    public final Object invoke(Object obj) {
                        return WindowInsetsHolder.Companion.current$lambda$0$0(orCreateFor, view, (DisposableEffectScope) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            EffectsKt.DisposableEffect(orCreateFor, (Function1) objRememberedValue, composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return orCreateFor;
        }

        public final WindowInsetsHolder getOrCreateFor(View view) {
            WindowInsetsHolder windowInsetsHolder;
            synchronized (WindowInsetsHolder.viewMap) {
                try {
                    WeakHashMap weakHashMap = WindowInsetsHolder.viewMap;
                    Object windowInsetsHolder2 = weakHashMap.get(view);
                    if (windowInsetsHolder2 == null) {
                        WindowInsetsCompat windowInsetsCompat = null;
                        windowInsetsHolder2 = new WindowInsetsHolder(windowInsetsCompat, view, windowInsetsCompat);
                        weakHashMap.put(view, windowInsetsHolder2);
                    }
                    windowInsetsHolder = (WindowInsetsHolder) windowInsetsHolder2;
                } catch (Throwable th) {
                    throw th;
                }
            }
            return windowInsetsHolder;
        }

        public final void setUseTestInsets(boolean testInsets) {
            WindowInsetsHolder.testInsets = testInsets;
        }

        private Companion() {
        }
    }

    public /* synthetic */ WindowInsetsHolder(WindowInsetsCompat windowInsetsCompat, View view, DefaultConstructorMarker defaultConstructorMarker) {
        this(windowInsetsCompat, view);
    }
}
