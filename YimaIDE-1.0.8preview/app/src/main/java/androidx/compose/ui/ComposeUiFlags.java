package androidx.compose.ui;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Deprecated;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b*\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0003R\u0018\u0010\u0007\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0003R\u0018\u0010\t\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0003R\u0018\u0010\u000b\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\u0003R\u0018\u0010\r\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u000e\u0010\u0003R\u0018\u0010\u000f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0010\u0010\u0003R\u0018\u0010\u0011\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0012\u0010\u0003R\u0018\u0010\u0013\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0014\u0010\u0003R\u0018\u0010\u0015\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0016\u0010\u0003R\u0018\u0010\u0017\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0018\u0010\u0003R\u0018\u0010\u0019\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001a\u0010\u0003R\u0018\u0010\u001b\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001c\u0010\u0003R\u0018\u0010\u001d\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u001e\u0010\u0003R\u0018\u0010\u001f\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b \u0010\u0003R\u0018\u0010!\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b\"\u0010\u0003R\u0018\u0010#\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b$\u0010\u0003R\u0018\u0010%\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b&\u0010\u0003R\u0018\u0010'\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b(\u0010\u0003R\u0018\u0010)\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b*\u0010\u0003R\u0018\u0010+\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b,\u0010\u0003R\u0018\u0010-\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\b\n\u0000\u0012\u0004\b.\u0010\u0003¨\u0006/"}, d2 = {"Landroidx/compose/ui/ComposeUiFlags;", "", "<init>", "()V", "isSemanticAutofillEnabled", "", "isSemanticAutofillEnabled$annotations", "isViewFocusFixEnabled", "isViewFocusFixEnabled$annotations", "isBypassUnfocusableComposeViewEnabled", "isBypassUnfocusableComposeViewEnabled$annotations", "isPre26FocusFinderFixEnabled", "isPre26FocusFinderFixEnabled$annotations", "isIgnoreInvalidPrevFocusRectEnabled", "isIgnoreInvalidPrevFocusRectEnabled$annotations", "isRemoveFocusedViewFixEnabled", "isRemoveFocusedViewFixEnabled$annotations", "areWindowInsetsRulersEnabled", "getAreWindowInsetsRulersEnabled$annotations", "isFocusActionExitsTouchModeEnabled", "isFocusActionExitsTouchModeEnabled$annotations", "isNoPinningInFocusRestorationEnabled", "isNoPinningInFocusRestorationEnabled$annotations", "isClearFocusOnResetEnabled", "isClearFocusOnResetEnabled$annotations", "isInitialFocusOnFocusableAvailable", "isInitialFocusOnFocusableAvailable$annotations", "isRequestFocusOnNonFocusableFocusTargetEnabled", "isRequestFocusOnNonFocusableFocusTargetEnabled$annotations", "isAdaptiveRefreshRateEnabled", "isAdaptiveRefreshRateEnabled$annotations", "isIndirectPointerNavigationGestureDetectorEnabled", "isIndirectPointerNavigationGestureDetectorEnabled$annotations", "isOptimizedFocusEventDispatchEnabled", "isOptimizedFocusEventDispatchEnabled$annotations", "isGraphicsLayerShapeSemanticsEnabled", "isGraphicsLayerShapeSemanticsEnabled$annotations", "isNestedScrollInteropIntegerPropagationEnabled", "isNestedScrollInteropIntegerPropagationEnabled$annotations", "isCanScrollUsingLastDownEventFixEnabled", "isCanScrollUsingLastDownEventFixEnabled$annotations", "isScrollCaptureCenteringEnabled", "isScrollCaptureCenteringEnabled$annotations", "isRectManagerOffsetUsageFromLayoutCoordinatesEnabled", "isRectManagerOffsetUsageFromLayoutCoordinatesEnabled$annotations", "isPinningFocusedAndroidViewsEnabled", "isPinningFocusedAndroidViewsEnabled$annotations", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ComposeUiFlags {
    public static boolean isClearFocusOnResetEnabled;
    public static boolean isFocusActionExitsTouchModeEnabled;
    public static boolean isInitialFocusOnFocusableAvailable;
    public static boolean isNoPinningInFocusRestorationEnabled;
    public static boolean isPre26FocusFinderFixEnabled;
    public static boolean isRemoveFocusedViewFixEnabled;
    public static boolean isViewFocusFixEnabled;
    public static final ComposeUiFlags INSTANCE = new ComposeUiFlags();
    public static boolean isSemanticAutofillEnabled = true;
    public static boolean isBypassUnfocusableComposeViewEnabled = true;
    public static boolean isIgnoreInvalidPrevFocusRectEnabled = true;
    public static boolean areWindowInsetsRulersEnabled = true;
    public static boolean isRequestFocusOnNonFocusableFocusTargetEnabled = true;
    public static boolean isAdaptiveRefreshRateEnabled = true;
    public static boolean isIndirectPointerNavigationGestureDetectorEnabled = true;
    public static boolean isOptimizedFocusEventDispatchEnabled = true;
    public static boolean isGraphicsLayerShapeSemanticsEnabled = true;
    public static boolean isNestedScrollInteropIntegerPropagationEnabled = true;
    public static boolean isCanScrollUsingLastDownEventFixEnabled = true;
    public static boolean isScrollCaptureCenteringEnabled = true;
    public static boolean isRectManagerOffsetUsageFromLayoutCoordinatesEnabled = true;
    public static boolean isPinningFocusedAndroidViewsEnabled = true;
    public static final int $stable = 8;

    private ComposeUiFlags() {
    }

    public static /* synthetic */ void getAreWindowInsetsRulersEnabled$annotations() {
    }

    public static /* synthetic */ void isAdaptiveRefreshRateEnabled$annotations() {
    }

    public static /* synthetic */ void isBypassUnfocusableComposeViewEnabled$annotations() {
    }

    public static /* synthetic */ void isCanScrollUsingLastDownEventFixEnabled$annotations() {
    }

    @Deprecated(message = "This flag is no longer needed.")
    public static /* synthetic */ void isClearFocusOnResetEnabled$annotations() {
    }

    @Deprecated(message = "This flag is no longer needed.")
    public static /* synthetic */ void isFocusActionExitsTouchModeEnabled$annotations() {
    }

    public static /* synthetic */ void isGraphicsLayerShapeSemanticsEnabled$annotations() {
    }

    public static /* synthetic */ void isIgnoreInvalidPrevFocusRectEnabled$annotations() {
    }

    public static /* synthetic */ void isIndirectPointerNavigationGestureDetectorEnabled$annotations() {
    }

    public static /* synthetic */ void isInitialFocusOnFocusableAvailable$annotations() {
    }

    public static /* synthetic */ void isNestedScrollInteropIntegerPropagationEnabled$annotations() {
    }

    @Deprecated(message = "This flag is no longer needed.")
    public static /* synthetic */ void isNoPinningInFocusRestorationEnabled$annotations() {
    }

    public static /* synthetic */ void isOptimizedFocusEventDispatchEnabled$annotations() {
    }

    public static /* synthetic */ void isPinningFocusedAndroidViewsEnabled$annotations() {
    }

    public static /* synthetic */ void isPre26FocusFinderFixEnabled$annotations() {
    }

    public static /* synthetic */ void isRectManagerOffsetUsageFromLayoutCoordinatesEnabled$annotations() {
    }

    @Deprecated(message = "This flag is no longer needed.")
    public static /* synthetic */ void isRemoveFocusedViewFixEnabled$annotations() {
    }

    public static /* synthetic */ void isRequestFocusOnNonFocusableFocusTargetEnabled$annotations() {
    }

    public static /* synthetic */ void isScrollCaptureCenteringEnabled$annotations() {
    }

    public static /* synthetic */ void isSemanticAutofillEnabled$annotations() {
    }

    public static /* synthetic */ void isViewFocusFixEnabled$annotations() {
    }
}
