package androidx.compose.foundation.layout;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.platform.AbstractComposeView;
import androidx.compose.ui.platform.ComposeView;
import androidx.core.graphics.Insets;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b/\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\".\u0010\n\u001a\u00020\t*\u00020\u000b2\u0006\u0010\b\u001a\u00020\t8F@FX\u0086\u000e¢\u0006\u0012\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011\".\u0010\n\u001a\u00020\t*\u00020\u00122\u0006\u0010\b\u001a\u00020\t8F@FX\u0087\u000e¢\u0006\u0012\u0012\u0004\b\f\u0010\u0013\u001a\u0004\b\u000e\u0010\u0014\"\u0004\b\u0010\u0010\u0015\"\u0015\u0010\u0016\u001a\u00020\u0017*\u00020\u00188G¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\"\u0015\u0010\u001b\u001a\u00020\u0017*\u00020\u00188G¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001a\"\u0015\u0010\u001d\u001a\u00020\u0017*\u00020\u00188G¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001a\"\u0015\u0010\u001f\u001a\u00020\u0017*\u00020\u00188G¢\u0006\u0006\u001a\u0004\b \u0010\u001a\"\u0015\u0010!\u001a\u00020\u0017*\u00020\u00188G¢\u0006\u0006\u001a\u0004\b\"\u0010\u001a\"\u0015\u0010#\u001a\u00020\u0017*\u00020\u00188G¢\u0006\u0006\u001a\u0004\b$\u0010\u001a\"\u0015\u0010%\u001a\u00020\u0017*\u00020\u00188G¢\u0006\u0006\u001a\u0004\b&\u0010\u001a\"\u0015\u0010'\u001a\u00020\u0017*\u00020\u00188G¢\u0006\u0006\u001a\u0004\b(\u0010\u001a\"\u0015\u0010)\u001a\u00020\u0017*\u00020\u00188G¢\u0006\u0006\u001a\u0004\b*\u0010\u001a\"\u0015\u0010+\u001a\u00020\u0017*\u00020\u00188G¢\u0006\u0006\u001a\u0004\b,\u0010\u001a\"\u0017\u0010-\u001a\u0004\u0018\u00010.*\u00020\u00188G¢\u0006\u0006\u001a\u0004\b/\u00100\"\u0015\u00101\u001a\u00020\u0017*\u00020\u00188G¢\u0006\u0006\u001a\u0004\b2\u0010\u001a\"\u0015\u00103\u001a\u00020\u0017*\u00020\u00188G¢\u0006\u0006\u001a\u0004\b4\u0010\u001a\"\u0015\u00105\u001a\u00020\u0017*\u00020\u00188G¢\u0006\u0006\u001a\u0004\b6\u0010\u001a\"\u001e\u00107\u001a\u00020\u0017*\u00020\u00188GX\u0087\u0004¢\u0006\f\u0012\u0004\b8\u00109\u001a\u0004\b:\u0010\u001a\"\u001e\u0010;\u001a\u00020\u0017*\u00020\u00188GX\u0087\u0004¢\u0006\f\u0012\u0004\b<\u00109\u001a\u0004\b=\u0010\u001a\"\u001e\u0010>\u001a\u00020\u0017*\u00020\u00188GX\u0087\u0004¢\u0006\f\u0012\u0004\b?\u00109\u001a\u0004\b@\u0010\u001a\"\u001e\u0010A\u001a\u00020\u0017*\u00020\u00188GX\u0087\u0004¢\u0006\f\u0012\u0004\bB\u00109\u001a\u0004\bC\u0010\u001a\"\u001e\u0010D\u001a\u00020\u0017*\u00020\u00188GX\u0087\u0004¢\u0006\f\u0012\u0004\bE\u00109\u001a\u0004\bF\u0010\u001a\"\u001e\u0010G\u001a\u00020\t*\u00020\u00188GX\u0087\u0004¢\u0006\f\u0012\u0004\bH\u00109\u001a\u0004\bG\u0010I\"\u001e\u0010J\u001a\u00020\t*\u00020\u00188GX\u0087\u0004¢\u0006\f\u0012\u0004\bK\u00109\u001a\u0004\bJ\u0010I\"\u001e\u0010L\u001a\u00020\t*\u00020\u00188GX\u0087\u0004¢\u0006\f\u0012\u0004\bM\u00109\u001a\u0004\bN\u0010I\"\u001e\u0010O\u001a\u00020\t*\u00020\u00188GX\u0087\u0004¢\u0006\f\u0012\u0004\bP\u00109\u001a\u0004\bQ\u0010I\"\u001e\u0010R\u001a\u00020\t*\u00020\u00188GX\u0087\u0004¢\u0006\f\u0012\u0004\bS\u00109\u001a\u0004\bT\u0010I\"\u001e\u0010U\u001a\u00020\t*\u00020\u00188GX\u0087\u0004¢\u0006\f\u0012\u0004\bV\u00109\u001a\u0004\bU\u0010I\"\u001e\u0010W\u001a\u00020\u0017*\u00020\u00188GX\u0087\u0004¢\u0006\f\u0012\u0004\bX\u00109\u001a\u0004\bY\u0010\u001a\"\u001e\u0010Z\u001a\u00020\u0017*\u00020\u00188GX\u0087\u0004¢\u0006\f\u0012\u0004\b[\u00109\u001a\u0004\b\\\u0010\u001a¨\u0006]"}, d2 = {"toInsetsValues", "Landroidx/compose/foundation/layout/InsetsValues;", "Landroidx/core/graphics/Insets;", "ValueInsets", "Landroidx/compose/foundation/layout/ValueInsets;", "insets", "name", "", "value", "", "consumeWindowInsets", "Landroidx/compose/ui/platform/AbstractComposeView;", "getConsumeWindowInsets$annotations", "(Landroidx/compose/ui/platform/AbstractComposeView;)V", "getConsumeWindowInsets", "(Landroidx/compose/ui/platform/AbstractComposeView;)Z", "setConsumeWindowInsets", "(Landroidx/compose/ui/platform/AbstractComposeView;Z)V", "Landroidx/compose/ui/platform/ComposeView;", "(Landroidx/compose/ui/platform/ComposeView;)V", "(Landroidx/compose/ui/platform/ComposeView;)Z", "(Landroidx/compose/ui/platform/ComposeView;Z)V", "captionBar", "Landroidx/compose/foundation/layout/WindowInsets;", "Landroidx/compose/foundation/layout/WindowInsets$Companion;", "getCaptionBar", "(Landroidx/compose/foundation/layout/WindowInsets$Companion;Landroidx/compose/runtime/Composer;I)Landroidx/compose/foundation/layout/WindowInsets;", "displayCutout", "getDisplayCutout", "ime", "getIme", "mandatorySystemGestures", "getMandatorySystemGestures", "navigationBars", "getNavigationBars", "statusBars", "getStatusBars", "systemBars", "getSystemBars", "systemGestures", "getSystemGestures", "tappableElement", "getTappableElement", "waterfall", "getWaterfall", "cutoutPath", "Landroidx/compose/ui/graphics/Path;", "getCutoutPath", "(Landroidx/compose/foundation/layout/WindowInsets$Companion;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Path;", "safeDrawing", "getSafeDrawing", "safeGestures", "getSafeGestures", "safeContent", "getSafeContent", "captionBarIgnoringVisibility", "getCaptionBarIgnoringVisibility$annotations", "(Landroidx/compose/foundation/layout/WindowInsets$Companion;Landroidx/compose/runtime/Composer;I)V", "getCaptionBarIgnoringVisibility", "navigationBarsIgnoringVisibility", "getNavigationBarsIgnoringVisibility$annotations", "getNavigationBarsIgnoringVisibility", "statusBarsIgnoringVisibility", "getStatusBarsIgnoringVisibility$annotations", "getStatusBarsIgnoringVisibility", "systemBarsIgnoringVisibility", "getSystemBarsIgnoringVisibility$annotations", "getSystemBarsIgnoringVisibility", "tappableElementIgnoringVisibility", "getTappableElementIgnoringVisibility$annotations", "getTappableElementIgnoringVisibility", "isCaptionBarVisible", "isCaptionBarVisible$annotations", "(Landroidx/compose/foundation/layout/WindowInsets$Companion;Landroidx/compose/runtime/Composer;I)Z", "isImeVisible", "isImeVisible$annotations", "areStatusBarsVisible", "getAreStatusBarsVisible$annotations", "getAreStatusBarsVisible", "areNavigationBarsVisible", "getAreNavigationBarsVisible$annotations", "getAreNavigationBarsVisible", "areSystemBarsVisible", "getAreSystemBarsVisible$annotations", "getAreSystemBarsVisible", "isTappableElementVisible", "isTappableElementVisible$annotations", "imeAnimationSource", "getImeAnimationSource$annotations", "getImeAnimationSource", "imeAnimationTarget", "getImeAnimationTarget$annotations", "getImeAnimationTarget", "foundation-layout"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class WindowInsets_androidKt {
    public static final ValueInsets ValueInsets(Insets insets, String str) {
        return new ValueInsets(toInsetsValues(insets), str);
    }

    public static final boolean getAreNavigationBarsVisible(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(710310464, i, -1, "androidx.compose.foundation.layout.<get-areNavigationBarsVisible> (WindowInsets.android.kt:313)");
        }
        boolean zIsVisible = WindowInsetsHolder.INSTANCE.current(composer, 6).getNavigationBars().isVisible();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return zIsVisible;
    }

    public static /* synthetic */ void getAreNavigationBarsVisible$annotations(WindowInsets.Companion companion, Composer composer, int i) {
    }

    public static final boolean getAreStatusBarsVisible(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1613283456, i, -1, "androidx.compose.foundation.layout.<get-areStatusBarsVisible> (WindowInsets.android.kt:303)");
        }
        boolean zIsVisible = WindowInsetsHolder.INSTANCE.current(composer, 6).getStatusBars().isVisible();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return zIsVisible;
    }

    public static /* synthetic */ void getAreStatusBarsVisible$annotations(WindowInsets.Companion companion, Composer composer, int i) {
    }

    public static final boolean getAreSystemBarsVisible(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1985490720, i, -1, "androidx.compose.foundation.layout.<get-areSystemBarsVisible> (WindowInsets.android.kt:321)");
        }
        boolean zIsVisible = WindowInsetsHolder.INSTANCE.current(composer, 6).getSystemBars().isVisible();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return zIsVisible;
    }

    public static /* synthetic */ void getAreSystemBarsVisible$annotations(WindowInsets.Companion companion, Composer composer, int i) {
    }

    public static final WindowInsets getCaptionBar(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1832025528, i, -1, "androidx.compose.foundation.layout.<get-captionBar> (WindowInsets.android.kt:141)");
        }
        AndroidWindowInsets captionBar = WindowInsetsHolder.INSTANCE.current(composer, 6).getCaptionBar();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return captionBar;
    }

    public static final WindowInsets getCaptionBarIgnoringVisibility(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1731251574, i, -1, "androidx.compose.foundation.layout.<get-captionBarIgnoringVisibility> (WindowInsets.android.kt:236)");
        }
        ValueInsets captionBarIgnoringVisibility = WindowInsetsHolder.INSTANCE.current(composer, 6).getCaptionBarIgnoringVisibility();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return captionBarIgnoringVisibility;
    }

    public static /* synthetic */ void getCaptionBarIgnoringVisibility$annotations(WindowInsets.Companion companion, Composer composer, int i) {
    }

    public static final boolean getConsumeWindowInsets(AbstractComposeView abstractComposeView) {
        Object tag = abstractComposeView.getTag(androidx.compose.ui.R.id.consume_window_insets_tag);
        Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }

    public static /* synthetic */ void getConsumeWindowInsets$annotations(AbstractComposeView abstractComposeView) {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Please use AbstractComposeView.consumeWindowInsets")
    public static /* synthetic */ void getConsumeWindowInsets$annotations(ComposeView composeView) {
    }

    public static final Path getCutoutPath(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-454263855, i, -1, "androidx.compose.foundation.layout.<get-cutoutPath> (WindowInsets.android.kt:204)");
        }
        Path cutoutPath = WindowInsetsHolder.INSTANCE.current(composer, 6).getCutoutPath();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return cutoutPath;
    }

    public static final WindowInsets getDisplayCutout(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1324817724, i, -1, "androidx.compose.foundation.layout.<get-displayCutout> (WindowInsets.android.kt:148)");
        }
        AndroidWindowInsets displayCutout = WindowInsetsHolder.INSTANCE.current(composer, 6).getDisplayCutout();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return displayCutout;
    }

    public static final WindowInsets getIme(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1466917860, i, -1, "androidx.compose.foundation.layout.<get-ime> (WindowInsets.android.kt:160)");
        }
        AndroidWindowInsets ime = WindowInsetsHolder.INSTANCE.current(composer, 6).getIme();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return ime;
    }

    public static final WindowInsets getImeAnimationSource(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1126064918, i, -1, "androidx.compose.foundation.layout.<get-imeAnimationSource> (WindowInsets.android.kt:340)");
        }
        ValueInsets imeAnimationSource = WindowInsetsHolder.INSTANCE.current(composer, 6).getImeAnimationSource();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return imeAnimationSource;
    }

    public static /* synthetic */ void getImeAnimationSource$annotations(WindowInsets.Companion companion, Composer composer, int i) {
    }

    public static final WindowInsets getImeAnimationTarget(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-466319786, i, -1, "androidx.compose.foundation.layout.<get-imeAnimationTarget> (WindowInsets.android.kt:350)");
        }
        ValueInsets imeAnimationTarget = WindowInsetsHolder.INSTANCE.current(composer, 6).getImeAnimationTarget();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return imeAnimationTarget;
    }

    public static /* synthetic */ void getImeAnimationTarget$annotations(WindowInsets.Companion companion, Composer composer, int i) {
    }

    public static final WindowInsets getMandatorySystemGestures(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1369492988, i, -1, "androidx.compose.foundation.layout.<get-mandatorySystemGestures> (WindowInsets.android.kt:169)");
        }
        AndroidWindowInsets mandatorySystemGestures = WindowInsetsHolder.INSTANCE.current(composer, 6).getMandatorySystemGestures();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return mandatorySystemGestures;
    }

    public static final WindowInsets getNavigationBars(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1596175702, i, -1, "androidx.compose.foundation.layout.<get-navigationBars> (WindowInsets.android.kt:176)");
        }
        AndroidWindowInsets navigationBars = WindowInsetsHolder.INSTANCE.current(composer, 6).getNavigationBars();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return navigationBars;
    }

    public static final WindowInsets getNavigationBarsIgnoringVisibility(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1990981160, i, -1, "androidx.compose.foundation.layout.<get-navigationBarsIgnoringVisibility> (WindowInsets.android.kt:247)");
        }
        ValueInsets navigationBarsIgnoringVisibility = WindowInsetsHolder.INSTANCE.current(composer, 6).getNavigationBarsIgnoringVisibility();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return navigationBarsIgnoringVisibility;
    }

    public static /* synthetic */ void getNavigationBarsIgnoringVisibility$annotations(WindowInsets.Companion companion, Composer composer, int i) {
    }

    public static final WindowInsets getSafeContent(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2026663876, i, -1, "androidx.compose.foundation.layout.<get-safeContent> (WindowInsets.android.kt:226)");
        }
        WindowInsets safeContent = WindowInsetsHolder.INSTANCE.current(composer, 6).getSafeContent();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return safeContent;
    }

    public static final WindowInsets getSafeDrawing(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-49441252, i, -1, "androidx.compose.foundation.layout.<get-safeDrawing> (WindowInsets.android.kt:211)");
        }
        WindowInsets safeDrawing = WindowInsetsHolder.INSTANCE.current(composer, 6).getSafeDrawing();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return safeDrawing;
    }

    public static final WindowInsets getSafeGestures(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1594247780, i, -1, "androidx.compose.foundation.layout.<get-safeGestures> (WindowInsets.android.kt:219)");
        }
        WindowInsets safeGestures = WindowInsetsHolder.INSTANCE.current(composer, 6).getSafeGestures();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return safeGestures;
    }

    public static final WindowInsets getStatusBars(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-675090670, i, -1, "androidx.compose.foundation.layout.<get-statusBars> (WindowInsets.android.kt:180)");
        }
        AndroidWindowInsets statusBars = WindowInsetsHolder.INSTANCE.current(composer, 6).getStatusBars();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return statusBars;
    }

    public static final WindowInsets getStatusBarsIgnoringVisibility(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(594020756, i, -1, "androidx.compose.foundation.layout.<get-statusBarsIgnoringVisibility> (WindowInsets.android.kt:257)");
        }
        ValueInsets statusBarsIgnoringVisibility = WindowInsetsHolder.INSTANCE.current(composer, 6).getStatusBarsIgnoringVisibility();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return statusBarsIgnoringVisibility;
    }

    public static /* synthetic */ void getStatusBarsIgnoringVisibility$annotations(WindowInsets.Companion companion, Composer composer, int i) {
    }

    public static final WindowInsets getSystemBars(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-282936756, i, -1, "androidx.compose.foundation.layout.<get-systemBars> (WindowInsets.android.kt:184)");
        }
        AndroidWindowInsets systemBars = WindowInsetsHolder.INSTANCE.current(composer, 6).getSystemBars();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return systemBars;
    }

    public static final WindowInsets getSystemBarsIgnoringVisibility(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1564566798, i, -1, "androidx.compose.foundation.layout.<get-systemBarsIgnoringVisibility> (WindowInsets.android.kt:268)");
        }
        ValueInsets systemBarsIgnoringVisibility = WindowInsetsHolder.INSTANCE.current(composer, 6).getSystemBarsIgnoringVisibility();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return systemBarsIgnoringVisibility;
    }

    public static /* synthetic */ void getSystemBarsIgnoringVisibility$annotations(WindowInsets.Companion companion, Composer composer, int i) {
    }

    public static final WindowInsets getSystemGestures(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(989216224, i, -1, "androidx.compose.foundation.layout.<get-systemGestures> (WindowInsets.android.kt:188)");
        }
        AndroidWindowInsets systemGestures = WindowInsetsHolder.INSTANCE.current(composer, 6).getSystemGestures();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return systemGestures;
    }

    public static final WindowInsets getTappableElement(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1994205284, i, -1, "androidx.compose.foundation.layout.<get-tappableElement> (WindowInsets.android.kt:192)");
        }
        AndroidWindowInsets tappableElement = WindowInsetsHolder.INSTANCE.current(composer, 6).getTappableElement();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return tappableElement;
    }

    public static final WindowInsets getTappableElementIgnoringVisibility(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1488788292, i, -1, "androidx.compose.foundation.layout.<get-tappableElementIgnoringVisibility> (WindowInsets.android.kt:279)");
        }
        ValueInsets tappableElementIgnoringVisibility = WindowInsetsHolder.INSTANCE.current(composer, 6).getTappableElementIgnoringVisibility();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return tappableElementIgnoringVisibility;
    }

    public static /* synthetic */ void getTappableElementIgnoringVisibility$annotations(WindowInsets.Companion companion, Composer composer, int i) {
    }

    public static final WindowInsets getWaterfall(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1943241020, i, -1, "androidx.compose.foundation.layout.<get-waterfall> (WindowInsets.android.kt:196)");
        }
        ValueInsets waterfall = WindowInsetsHolder.INSTANCE.current(composer, 6).getWaterfall();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return waterfall;
    }

    public static final boolean isCaptionBarVisible(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-501076620, i, -1, "androidx.compose.foundation.layout.<get-isCaptionBarVisible> (WindowInsets.android.kt:287)");
        }
        boolean zIsVisible = WindowInsetsHolder.INSTANCE.current(composer, 6).getCaptionBar().isVisible();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return zIsVisible;
    }

    public static /* synthetic */ void isCaptionBarVisible$annotations(WindowInsets.Companion companion, Composer composer, int i) {
    }

    public static final boolean isImeVisible(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1873571424, i, -1, "androidx.compose.foundation.layout.<get-isImeVisible> (WindowInsets.android.kt:295)");
        }
        boolean zIsVisible = WindowInsetsHolder.INSTANCE.current(composer, 6).getIme().isVisible();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return zIsVisible;
    }

    public static /* synthetic */ void isImeVisible$annotations(WindowInsets.Companion companion, Composer composer, int i) {
    }

    public static final boolean isTappableElementVisible(WindowInsets.Companion companion, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1737201120, i, -1, "androidx.compose.foundation.layout.<get-isTappableElementVisible> (WindowInsets.android.kt:330)");
        }
        boolean zIsVisible = WindowInsetsHolder.INSTANCE.current(composer, 6).getTappableElement().isVisible();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return zIsVisible;
    }

    public static /* synthetic */ void isTappableElementVisible$annotations(WindowInsets.Companion companion, Composer composer, int i) {
    }

    public static final void setConsumeWindowInsets(AbstractComposeView abstractComposeView, boolean z) {
        abstractComposeView.setTag(androidx.compose.ui.R.id.consume_window_insets_tag, Boolean.valueOf(z));
    }

    public static final InsetsValues toInsetsValues(Insets insets) {
        return new InsetsValues(insets.left, insets.top, insets.right, insets.bottom);
    }

    public static final /* synthetic */ boolean getConsumeWindowInsets(ComposeView composeView) {
        Object tag = composeView.getTag(androidx.compose.ui.R.id.consume_window_insets_tag);
        Boolean bool = tag instanceof Boolean ? (Boolean) tag : null;
        if (bool != null) {
            return bool.booleanValue();
        }
        return false;
    }
}
