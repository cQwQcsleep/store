package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.gestures.DraggableKt;
import androidx.compose.foundation.gestures.DraggableState;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.material3.WideNavigationRailKt;
import androidx.compose.material3.internal.AnchoredDraggableKt;
import androidx.compose.material3.internal.AnchoredDraggableState;
import androidx.compose.material3.internal.DraggableAnchorsConfig;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.NavigationRailBaselineItemTokens;
import androidx.compose.material3.tokens.NavigationRailCollapsedTokens;
import androidx.compose.material3.tokens.NavigationRailExpandedTokens;
import androidx.compose.material3.tokens.NavigationRailHorizontalItemTokens;
import androidx.compose.material3.tokens.NavigationRailVerticalItemTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.ListUtilsKt;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000Ê\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\u001as\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\fH\u0007¢\u0006\u0002\u0010\u0012\u001am\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\fH\u0003¢\u0006\u0002\u0010\u0017\u001a\u009d\u0001\u0010\u0018\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0019\u001a\u00020\u00152\b\b\u0002\u0010\u001a\u001a\u00020\u00072\b\b\u0002\u0010\u001b\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\fH\u0007¢\u0006\u0004\b \u0010!\u001a\u0089\u0001\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020\u00152\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0011\u0010%\u001a\r\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\f2\u0013\u0010&\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0006\u0010'\u001a\u00020\u00152\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010(\u001a\u00020\u00152\b\b\u0002\u0010)\u001a\u00020*2\b\b\u0002\u0010\b\u001a\u00020+2\n\b\u0002\u0010,\u001a\u0004\u0018\u00010-H\u0007¢\u0006\u0004\b.\u0010/\u001aÖ\u0001\u00100\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u00101\u001a\u00020\u00152\u0012\u00102\u001a\u000e\u0012\u0004\u0012\u000204\u0012\u0004\u0012\u000205032\u0006\u00106\u001a\u00020721\u00108\u001a-\b\u0001\u0012\u0013\u0012\u001104¢\u0006\f\b:\u0012\b\b;\u0012\u0004\b\b(<\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010=\u0012\u0006\u0012\u0004\u0018\u00010>092\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010?\u001a\u00020@2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010A\u001a\u00020\u001d2\u0013\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b¢\u0006\u0002\b\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010B\u001a\u00020\u00152\u0006\u0010\u000f\u001a\u00020\u00102\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\u00010\u000b¢\u0006\u0002\b\fH\u0003¢\u0006\u0004\bC\u0010D\u001a\u001c\u0010E\u001a\u000204*\u00020F2\u0006\u0010G\u001a\u0002042\u0006\u0010H\u001a\u00020\u0015H\u0002\u001a\u0014\u0010I\u001a\u000204*\u00020F2\u0006\u0010G\u001a\u000204H\u0002\u001a=\u0010J\u001a\u00020\u00012\u0006\u0010K\u001a\u00020L2\u001c\u0010M\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010=\u0012\u0006\u0012\u0004\u0018\u00010>0N2\u0006\u0010O\u001a\u00020\u0015H\u0003¢\u0006\u0004\bP\u0010Q\"\u0016\u0010R\u001a\u00020\u001dX\u0080\u0004¢\u0006\n\n\u0002\u0010U\u001a\u0004\bS\u0010T\"\u0010\u0010V\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010W\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010X\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010Y\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010Z\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010[\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010\\\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010]\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010^\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010_\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010`\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u0010\u0010a\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010U\"\u000e\u0010b\u001a\u000204X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010c\u001a\u00020dX\u0082T¢\u0006\u0002\n\u0000\"\u001a\u0010e\u001a\b\u0012\u0004\u0012\u00020g0fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u0010i\"\u001a\u0010j\u001a\b\u0012\u0004\u0012\u00020k0fX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bl\u0010i¨\u0006m²\u0006\n\u0010n\u001a\u00020oX\u008a\u008e\u0002²\u0006\n\u0010p\u001a\u00020oX\u008a\u008e\u0002²\u0006\n\u0010q\u001a\u00020\u001dX\u008a\u0084\u0002²\u0006\n\u0010r\u001a\u00020\u001dX\u008a\u0084\u0002²\u0006\n\u0010s\u001a\u00020\u001dX\u008a\u0084\u0002²\u0006\n\u0010t\u001a\u00020\u001dX\u008a\u0084\u0002²\u0006\n\u0010u\u001a\u000204X\u008a\u0084\u0002²\u0006\n\u0010v\u001a\u00020\u0015X\u008a\u008e\u0002"}, d2 = {"WideNavigationRail", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/material3/WideNavigationRailState;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/WideNavigationRailColors;", WideNavigationRailKt.HeaderLayoutIdTag, "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "arrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "content", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/WideNavigationRailState;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/WideNavigationRailColors;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/foundation/layout/Arrangement$Vertical;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "WideNavigationRailLayout", "isModal", "", "expanded", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/material3/WideNavigationRailColors;Landroidx/compose/ui/graphics/Shape;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/foundation/layout/Arrangement$Vertical;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "ModalWideNavigationRail", "hideOnCollapse", "collapsedShape", "expandedShape", "expandedHeaderTopPadding", "Landroidx/compose/ui/unit/Dp;", "expandedProperties", "Landroidx/compose/material3/ModalWideNavigationRailProperties;", "ModalWideNavigationRail-k3FuEkE", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/WideNavigationRailState;ZLandroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/WideNavigationRailColors;Lkotlin/jvm/functions/Function2;FLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/material3/ModalWideNavigationRailProperties;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "WideNavigationRailItem", "selected", "onClick", "icon", "label", "railExpanded", "enabled", "iconPosition", "Landroidx/compose/material3/NavigationItemIconPosition;", "Landroidx/compose/material3/NavigationItemColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "WideNavigationRailItem-pli-t6k", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/ui/Modifier;ZILandroidx/compose/material3/NavigationItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "ModalWideNavigationRailContent", "isStandaloneModal", "predictiveBackProgress", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "predictiveBackState", "Landroidx/compose/material3/RailPredictiveBackState;", "settleToDismiss", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "velocity", "Lkotlin/coroutines/Continuation;", "", "railState", "Landroidx/compose/material3/ModalWideNavigationRailState;", "openModalRailMaxWidth", "gesturesEnabled", "ModalWideNavigationRailContent-pU6N4AM", "(ZZLandroidx/compose/animation/core/Animatable;Landroidx/compose/material3/RailPredictiveBackState;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/ModalWideNavigationRailState;Landroidx/compose/material3/WideNavigationRailColors;Landroidx/compose/ui/graphics/Shape;FLkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "calculatePredictiveBackScaleX", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", NotificationCompat.CATEGORY_PROGRESS, "swipeEdgeMatchesRail", "calculatePredictiveBackScaleY", "Scrim", "color", "Landroidx/compose/ui/graphics/Color;", "onDismissRequest", "Lkotlin/Function1;", "visible", "Scrim-3J-VO9M", "(JLkotlin/jvm/functions/Function1;ZLandroidx/compose/runtime/Composer;I)V", "WNRItemNoLabelIndicatorPadding", "getWNRItemNoLabelIndicatorPadding", "()F", "F", "ItemHorizontalPadding", "WNRVerticalPadding", "WNRHeaderPadding", "CollapsedRailWidth", "ExpandedRailMinWidth", "ExpandedRailMaxWidth", "TopIconItemMinHeight", "ItemTopIconIndicatorVerticalPadding", "ItemTopIconIndicatorHorizontalPadding", "ItemStartIconIndicatorVerticalPadding", "PredictiveBackMaxScaleXDistance", "PredictiveBackMaxScaleYDistance", "PredictiveBackPivotFractionY", "HeaderLayoutIdTag", "", "LocalWideNavigationRailOverride", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/material3/WideNavigationRailOverride;", "getLocalWideNavigationRailOverride", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalModalWideNavigationRailOverride", "Landroidx/compose/material3/ModalWideNavigationRailOverride;", "getLocalModalWideNavigationRailOverride", "material3", "currentWidth", "", "actualMaxExpandedWidth", "minWidth", "widthFullRange", "itemVerticalSpacedBy", "itemMinHeight", "alpha", "dismiss"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WideNavigationRailKt {
    private static final float CollapsedRailWidth;
    private static final float ExpandedRailMaxWidth;
    private static final float ExpandedRailMinWidth;
    private static final String HeaderLayoutIdTag = "header";
    private static final float ItemHorizontalPadding;
    private static final float ItemStartIconIndicatorVerticalPadding;
    private static final float ItemTopIconIndicatorHorizontalPadding;
    private static final float ItemTopIconIndicatorVerticalPadding;
    private static final ProvidableCompositionLocal<ModalWideNavigationRailOverride> LocalModalWideNavigationRailOverride;
    private static final ProvidableCompositionLocal<WideNavigationRailOverride> LocalWideNavigationRailOverride;
    private static final float PredictiveBackMaxScaleXDistance;
    private static final float PredictiveBackMaxScaleYDistance;
    private static final float PredictiveBackPivotFractionY = 0.5f;
    private static final float TopIconItemMinHeight;
    private static final float WNRHeaderPadding;
    private static final float WNRItemNoLabelIndicatorPadding;
    private static final float WNRVerticalPadding;

    static {
        NavigationRailVerticalItemTokens navigationRailVerticalItemTokens = NavigationRailVerticalItemTokens.INSTANCE;
        float fM1958getActiveIndicatorWidthD9Ej5fM = navigationRailVerticalItemTokens.m1958getActiveIndicatorWidthD9Ej5fM();
        NavigationRailBaselineItemTokens navigationRailBaselineItemTokens = NavigationRailBaselineItemTokens.INSTANCE;
        WNRItemNoLabelIndicatorPadding = Dp.m6022constructorimpl(Dp.m6022constructorimpl(fM1958getActiveIndicatorWidthD9Ej5fM - navigationRailBaselineItemTokens.m1941getIconSizeD9Ej5fM()) / 2.0f);
        ItemHorizontalPadding = Dp.m6022constructorimpl(20.0f);
        NavigationRailCollapsedTokens navigationRailCollapsedTokens = NavigationRailCollapsedTokens.INSTANCE;
        WNRVerticalPadding = navigationRailCollapsedTokens.m1946getTopSpaceD9Ej5fM();
        WNRHeaderPadding = navigationRailBaselineItemTokens.m1940getHeaderSpaceMinimumD9Ej5fM();
        CollapsedRailWidth = navigationRailCollapsedTokens.m1943getContainerWidthD9Ej5fM();
        NavigationRailExpandedTokens navigationRailExpandedTokens = NavigationRailExpandedTokens.INSTANCE;
        ExpandedRailMinWidth = navigationRailExpandedTokens.m1949getContainerWidthMinimumD9Ej5fM();
        ExpandedRailMaxWidth = navigationRailExpandedTokens.m1948getContainerWidthMaximumD9Ej5fM();
        TopIconItemMinHeight = navigationRailBaselineItemTokens.m1938getContainerHeightD9Ej5fM();
        ItemTopIconIndicatorVerticalPadding = Dp.m6022constructorimpl(Dp.m6022constructorimpl(navigationRailVerticalItemTokens.m1957getActiveIndicatorHeightD9Ej5fM() - navigationRailBaselineItemTokens.m1941getIconSizeD9Ej5fM()) / 2.0f);
        ItemTopIconIndicatorHorizontalPadding = Dp.m6022constructorimpl(Dp.m6022constructorimpl(navigationRailVerticalItemTokens.m1958getActiveIndicatorWidthD9Ej5fM() - navigationRailBaselineItemTokens.m1941getIconSizeD9Ej5fM()) / 2.0f);
        ItemStartIconIndicatorVerticalPadding = Dp.m6022constructorimpl(Dp.m6022constructorimpl(NavigationRailHorizontalItemTokens.INSTANCE.m1952getActiveIndicatorHeightD9Ej5fM() - navigationRailBaselineItemTokens.m1941getIconSizeD9Ej5fM()) / 2.0f);
        PredictiveBackMaxScaleXDistance = Dp.m6022constructorimpl(24.0f);
        PredictiveBackMaxScaleYDistance = Dp.m6022constructorimpl(48.0f);
        LocalWideNavigationRailOverride = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: tmf
            public final Object invoke() {
                return WideNavigationRailKt.f();
            }
        }, 1, null);
        LocalModalWideNavigationRailOverride = CompositionLocalKt.compositionLocalOf$default(null, new Function0() { // from class: umf
            public final Object invoke() {
                return WideNavigationRailKt.b();
            }
        }, 1, null);
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0118  */
    /* JADX WARN: Code duplicated, block: B:109:0x012e  */
    /* JADX WARN: Code duplicated, block: B:112:0x0134  */
    /* JADX WARN: Code duplicated, block: B:113:0x013b  */
    /* JADX WARN: Code duplicated, block: B:115:0x013f  */
    /* JADX WARN: Code duplicated, block: B:117:0x0149  */
    /* JADX WARN: Code duplicated, block: B:118:0x014c  */
    /* JADX WARN: Code duplicated, block: B:120:0x0151  */
    /* JADX WARN: Code duplicated, block: B:123:0x015b  */
    /* JADX WARN: Code duplicated, block: B:125:0x0160  */
    /* JADX WARN: Code duplicated, block: B:127:0x0164  */
    /* JADX WARN: Code duplicated, block: B:129:0x016c  */
    /* JADX WARN: Code duplicated, block: B:130:0x016f  */
    /* JADX WARN: Code duplicated, block: B:132:0x0174  */
    /* JADX WARN: Code duplicated, block: B:139:0x018d  */
    /* JADX WARN: Code duplicated, block: B:142:0x0196  */
    /* JADX WARN: Code duplicated, block: B:166:0x01ee A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:167:0x01f0  */
    /* JADX WARN: Code duplicated, block: B:168:0x01f3  */
    /* JADX WARN: Code duplicated, block: B:171:0x01fb  */
    /* JADX WARN: Code duplicated, block: B:172:0x0203  */
    /* JADX WARN: Code duplicated, block: B:174:0x0207  */
    /* JADX WARN: Code duplicated, block: B:177:0x020d  */
    /* JADX WARN: Code duplicated, block: B:178:0x0216  */
    /* JADX WARN: Code duplicated, block: B:181:0x021b  */
    /* JADX WARN: Code duplicated, block: B:182:0x0224  */
    /* JADX WARN: Code duplicated, block: B:185:0x0229  */
    /* JADX WARN: Code duplicated, block: B:186:0x0232  */
    /* JADX WARN: Code duplicated, block: B:189:0x0237  */
    /* JADX WARN: Code duplicated, block: B:191:0x023a  */
    /* JADX WARN: Code duplicated, block: B:192:0x0240  */
    /* JADX WARN: Code duplicated, block: B:195:0x0245  */
    /* JADX WARN: Code duplicated, block: B:196:0x024e  */
    /* JADX WARN: Code duplicated, block: B:199:0x0254  */
    /* JADX WARN: Code duplicated, block: B:200:0x025d  */
    /* JADX WARN: Code duplicated, block: B:202:0x0261  */
    /* JADX WARN: Code duplicated, block: B:204:0x027e  */
    /* JADX WARN: Code duplicated, block: B:207:0x0298  */
    /* JADX WARN: Code duplicated, block: B:210:0x02bd  */
    /* JADX WARN: Code duplicated, block: B:212:0x02d8  */
    /* JADX WARN: Code duplicated, block: B:215:0x02f2  */
    /* JADX WARN: Code duplicated, block: B:217:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:23:0x0047  */
    /* JADX WARN: Code duplicated, block: B:38:0x0069  */
    /* JADX WARN: Code duplicated, block: B:40:0x006d  */
    /* JADX WARN: Code duplicated, block: B:42:0x0075  */
    /* JADX WARN: Code duplicated, block: B:43:0x0078  */
    /* JADX WARN: Code duplicated, block: B:46:0x007e  */
    /* JADX WARN: Code duplicated, block: B:49:0x0084  */
    /* JADX WARN: Code duplicated, block: B:51:0x0088  */
    /* JADX WARN: Code duplicated, block: B:53:0x0090  */
    /* JADX WARN: Code duplicated, block: B:54:0x0093  */
    /* JADX WARN: Code duplicated, block: B:57:0x009a  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:70:0x00be  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:75:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:80:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:81:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:85:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:86:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:90:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:98:0x0110  */
    /* JADX INFO: renamed from: ModalWideNavigationRail-k3FuEkE, reason: not valid java name */
    public static final void m1336ModalWideNavigationRailk3FuEkE(Modifier modifier, WideNavigationRailState wideNavigationRailState, boolean z, Shape shape, Shape shape2, WideNavigationRailColors wideNavigationRailColors, Function2<? super Composer, ? super Integer, Unit> function2, float f, WindowInsets windowInsets, Arrangement.Vertical vertical, ModalWideNavigationRailProperties modalWideNavigationRailProperties, final Function2<? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2, final int i3) {
        Modifier modifier2;
        int i4;
        boolean z2;
        Shape shape3;
        Shape shape4;
        int i5;
        Function2<? super Composer, ? super Integer, Unit> function4;
        int i6;
        int i7;
        float f2;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        boolean z3;
        final WindowInsets windowInsets2;
        final Function2<? super Composer, ? super Integer, Unit> function5;
        final Modifier modifier3;
        final float f3;
        final boolean z4;
        final Shape shape5;
        final Shape shape6;
        final WideNavigationRailState wideNavigationRailState2;
        final WideNavigationRailColors wideNavigationRailColors2;
        final Arrangement.Vertical vertical2;
        final ModalWideNavigationRailProperties modalWideNavigationRailProperties2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        WideNavigationRailState wideNavigationRailStateRememberWideNavigationRailState;
        Shape modalCollapsedShape;
        Shape modalExpandedShape;
        WideNavigationRailColors wideNavigationRailColorsColors;
        Function2<? super Composer, ? super Integer, Unit> function6;
        float fM6022constructorimpl;
        WindowInsets windowInsets3;
        Arrangement.Vertical arrangement;
        ModalWideNavigationRailProperties modalExpandedProperties;
        boolean z5;
        Shape shape7;
        WideNavigationRailColors wideNavigationRailColors3;
        int i14;
        int i15;
        Composer composerStartRestartGroup = composer.startRestartGroup(-38559147);
        int i16 = i3 & 1;
        if (i16 != 0) {
            i4 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i4 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i4 = i;
        }
        if ((i & 48) == 0) {
            if ((i3 & 2) != 0) {
                i15 = 16;
            } else {
                if ((i & 64) == 0 ? composerStartRestartGroup.changed(wideNavigationRailState) : composerStartRestartGroup.changedInstance(wideNavigationRailState)) {
                    i15 = 32;
                } else {
                    i15 = 16;
                }
            }
            i4 |= i15;
        }
        int i17 = i3 & 4;
        if (i17 == 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                z2 = z;
                i4 |= composerStartRestartGroup.changed(z2) ? 256 : 128;
            }
            if ((i & 3072) == 0) {
                if ((i3 & 8) == 0) {
                    shape3 = shape;
                    int i18 = composerStartRestartGroup.changed(shape3) ? 2048 : 1024;
                    i4 |= i18;
                } else {
                    shape3 = shape;
                }
                i4 |= i18;
            } else {
                shape3 = shape;
            }
            if ((i & 24576) == 0) {
                if ((i3 & 16) == 0) {
                    shape4 = shape2;
                    int i19 = composerStartRestartGroup.changed(shape4) ? 16384 : 8192;
                    i4 |= i19;
                } else {
                    shape4 = shape2;
                }
                i4 |= i19;
            } else {
                shape4 = shape2;
            }
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
                if ((i3 & 32) == 0 || !composerStartRestartGroup.changed(wideNavigationRailColors)) {
                    i14 = 65536;
                } else {
                    i14 = 131072;
                }
                i4 |= i14;
            }
            i5 = i3 & 64;
            if (i5 != 0) {
                i4 |= 1572864;
                function4 = function2;
            } else {
                function4 = function2;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i6 = 1048576;
                    } else {
                        i6 = 524288;
                    }
                    i4 |= i6;
                }
            }
            i7 = i3 & 128;
            if (i7 != 0) {
                i4 |= 12582912;
                f2 = f;
            } else {
                f2 = f;
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(f2)) {
                        i8 = 8388608;
                    } else {
                        i8 = 4194304;
                    }
                    i4 |= i8;
                }
            }
            if ((i & 100663296) != 0) {
                i4 |= ((i3 & 256) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            }
            if ((i & 805306368) != 0) {
                i4 |= ((i3 & 512) == 0 || !composerStartRestartGroup.changed(vertical)) ? 268435456 : 536870912;
            }
            i9 = i3 & 1024;
            if (i9 != 0) {
                i10 = i2 | 6;
            } else if ((i2 & 6) == 0) {
                if (composerStartRestartGroup.changed(modalWideNavigationRailProperties)) {
                    i11 = 4;
                } else {
                    i11 = 2;
                }
                i10 = i2 | i11;
            } else {
                i10 = i2;
            }
            if ((i3 & 2048) != 0) {
                i10 |= 48;
            } else if ((i2 & 48) != 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i12 = 32;
                } else {
                    i12 = 16;
                }
                i10 |= i12;
            }
            i13 = i10;
            if ((i4 & 306783379) == 306783378 || (i13 & 19) != 18) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                    if (i16 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i3 & 2) != 0) {
                        wideNavigationRailStateRememberWideNavigationRailState = WideNavigationRailStateKt.rememberWideNavigationRailState(null, composerStartRestartGroup, 0, 1);
                        i4 &= -113;
                    } else {
                        wideNavigationRailStateRememberWideNavigationRailState = wideNavigationRailState;
                    }
                    if (i17 != 0) {
                        z2 = false;
                    }
                    if ((i3 & 8) != 0) {
                        modalCollapsedShape = WideNavigationRailDefaults.INSTANCE.getModalCollapsedShape(composerStartRestartGroup, 6);
                        i4 &= -7169;
                    } else {
                        modalCollapsedShape = shape3;
                    }
                    if ((i3 & 16) != 0) {
                        modalExpandedShape = WideNavigationRailDefaults.INSTANCE.getModalExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        modalExpandedShape = shape4;
                    }
                    if ((i3 & 32) != 0) {
                        wideNavigationRailColorsColors = WideNavigationRailDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    } else {
                        wideNavigationRailColorsColors = wideNavigationRailColors;
                    }
                    function6 = i5 == 0 ? function4 : null;
                    if (i7 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        windowInsets3 = WideNavigationRailDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        windowInsets3 = windowInsets;
                    }
                    if ((i3 & 512) != 0) {
                        arrangement = WideNavigationRailDefaults.INSTANCE.getArrangement();
                        i4 &= -1879048193;
                    } else {
                        arrangement = vertical;
                    }
                    if (i9 != 0) {
                        modalExpandedProperties = WideNavigationRailDefaults.INSTANCE.getModalExpandedProperties();
                    } else {
                        modalExpandedProperties = modalWideNavigationRailProperties;
                    }
                    z5 = z2;
                    shape7 = modalExpandedShape;
                    wideNavigationRailColors3 = wideNavigationRailColorsColors;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    if ((i3 & 2) != 0) {
                        i4 &= -113;
                    }
                    if ((i3 & 8) != 0) {
                        i4 &= -7169;
                    }
                    if ((i3 & 16) != 0) {
                        i4 &= -57345;
                    }
                    if ((i3 & 32) != 0) {
                        i4 &= -458753;
                    }
                    if ((i3 & 256) != 0) {
                        i4 &= -234881025;
                    }
                    if ((i3 & 512) != 0) {
                        i4 &= -1879048193;
                    }
                    wideNavigationRailStateRememberWideNavigationRailState = wideNavigationRailState;
                    wideNavigationRailColors3 = wideNavigationRailColors;
                    windowInsets3 = windowInsets;
                    arrangement = vertical;
                    modalExpandedProperties = modalWideNavigationRailProperties;
                    function6 = function4;
                    modifier4 = modifier2;
                    fM6022constructorimpl = f2;
                    z5 = z2;
                    modalCollapsedShape = shape3;
                    shape7 = shape4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-38559147, i4, i13, "androidx.compose.material3.ModalWideNavigationRail (WideNavigationRail.kt:477)");
                }
                ((ModalWideNavigationRailOverride) composerStartRestartGroup.consume(LocalModalWideNavigationRailOverride)).ModalWideNavigationRail(new ModalWideNavigationRailOverrideScope(modifier4, wideNavigationRailStateRememberWideNavigationRailState, z5, modalCollapsedShape, shape7, wideNavigationRailColors3, function6, fM6022constructorimpl, windowInsets3, arrangement, modalExpandedProperties, function3, null), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                wideNavigationRailState2 = wideNavigationRailStateRememberWideNavigationRailState;
                z4 = z5;
                shape5 = modalCollapsedShape;
                shape6 = shape7;
                wideNavigationRailColors2 = wideNavigationRailColors3;
                function5 = function6;
                f3 = fM6022constructorimpl;
                windowInsets2 = windowInsets3;
                vertical2 = arrangement;
                modalWideNavigationRailProperties2 = modalExpandedProperties;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                windowInsets2 = windowInsets;
                function5 = function4;
                modifier3 = modifier2;
                f3 = f2;
                z4 = z2;
                shape5 = shape3;
                shape6 = shape4;
                wideNavigationRailState2 = wideNavigationRailState;
                wideNavigationRailColors2 = wideNavigationRailColors;
                vertical2 = vertical;
                modalWideNavigationRailProperties2 = modalWideNavigationRailProperties;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: pmf
                    public final Object invoke(Object obj, Object obj2) {
                        return WideNavigationRailKt.e(modifier3, wideNavigationRailState2, z4, shape5, shape6, wideNavigationRailColors2, function5, f3, windowInsets2, vertical2, modalWideNavigationRailProperties2, function3, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        z2 = z;
        if ((i & 3072) == 0) {
            if ((i3 & 8) == 0) {
                shape3 = shape;
                if (composerStartRestartGroup.changed(shape3)) {
                }
                i4 |= i18;
            } else {
                shape3 = shape;
            }
            i4 |= i18;
        } else {
            shape3 = shape;
        }
        if ((i & 24576) == 0) {
            if ((i3 & 16) == 0) {
                shape4 = shape2;
                if (composerStartRestartGroup.changed(shape4)) {
                }
                i4 |= i19;
            } else {
                shape4 = shape2;
            }
            i4 |= i19;
        } else {
            shape4 = shape2;
        }
        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) != 0) {
            if ((i3 & 32) == 0) {
                i14 = 65536;
            } else {
                i14 = 65536;
            }
            i4 |= i14;
        }
        i5 = i3 & 64;
        if (i5 != 0) {
            i4 |= 1572864;
            function4 = function2;
        } else {
            function4 = function2;
            if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i6 = 1048576;
                } else {
                    i6 = 524288;
                }
                i4 |= i6;
            }
        }
        i7 = i3 & 128;
        if (i7 != 0) {
            i4 |= 12582912;
            f2 = f;
        } else {
            f2 = f;
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(f2)) {
                    i8 = 8388608;
                } else {
                    i8 = 4194304;
                }
                i4 |= i8;
            }
        }
        if ((i & 100663296) != 0) {
            i4 |= ((i3 & 256) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
        }
        if ((i & 805306368) != 0) {
            i4 |= ((i3 & 512) == 0 || !composerStartRestartGroup.changed(vertical)) ? 268435456 : 536870912;
        }
        i9 = i3 & 1024;
        if (i9 != 0) {
            i10 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            if (composerStartRestartGroup.changed(modalWideNavigationRailProperties)) {
                i11 = 4;
            } else {
                i11 = 2;
            }
            i10 = i2 | i11;
        } else {
            i10 = i2;
        }
        if ((i3 & 2048) != 0) {
            i10 |= 48;
        } else if ((i2 & 48) != 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i12 = 32;
            } else {
                i12 = 16;
            }
            i10 |= i12;
        }
        i13 = i10;
        if ((i4 & 306783379) == 306783378) {
            z3 = true;
        } else {
            z3 = true;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i16 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 2) != 0) {
                    wideNavigationRailStateRememberWideNavigationRailState = WideNavigationRailStateKt.rememberWideNavigationRailState(null, composerStartRestartGroup, 0, 1);
                    i4 &= -113;
                } else {
                    wideNavigationRailStateRememberWideNavigationRailState = wideNavigationRailState;
                }
                if (i17 != 0) {
                    z2 = false;
                }
                if ((i3 & 8) != 0) {
                    modalCollapsedShape = WideNavigationRailDefaults.INSTANCE.getModalCollapsedShape(composerStartRestartGroup, 6);
                    i4 &= -7169;
                } else {
                    modalCollapsedShape = shape3;
                }
                if ((i3 & 16) != 0) {
                    modalExpandedShape = WideNavigationRailDefaults.INSTANCE.getModalExpandedShape(composerStartRestartGroup, 6);
                    i4 &= -57345;
                } else {
                    modalExpandedShape = shape4;
                }
                if ((i3 & 32) != 0) {
                    wideNavigationRailColorsColors = WideNavigationRailDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    i4 &= -458753;
                } else {
                    wideNavigationRailColorsColors = wideNavigationRailColors;
                }
                if (i5 == 0) {
                }
                if (i7 != 0) {
                    fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                } else {
                    fM6022constructorimpl = f2;
                }
                if ((i3 & 256) != 0) {
                    windowInsets3 = WideNavigationRailDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                    i4 &= -234881025;
                } else {
                    windowInsets3 = windowInsets;
                }
                if ((i3 & 512) != 0) {
                    arrangement = WideNavigationRailDefaults.INSTANCE.getArrangement();
                    i4 &= -1879048193;
                } else {
                    arrangement = vertical;
                }
                if (i9 != 0) {
                    modalExpandedProperties = WideNavigationRailDefaults.INSTANCE.getModalExpandedProperties();
                } else {
                    modalExpandedProperties = modalWideNavigationRailProperties;
                }
                z5 = z2;
                shape7 = modalExpandedShape;
                wideNavigationRailColors3 = wideNavigationRailColorsColors;
            } else {
                if (i16 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i3 & 2) != 0) {
                    wideNavigationRailStateRememberWideNavigationRailState = WideNavigationRailStateKt.rememberWideNavigationRailState(null, composerStartRestartGroup, 0, 1);
                    i4 &= -113;
                } else {
                    wideNavigationRailStateRememberWideNavigationRailState = wideNavigationRailState;
                }
                if (i17 != 0) {
                    z2 = false;
                }
                if ((i3 & 8) != 0) {
                    modalCollapsedShape = WideNavigationRailDefaults.INSTANCE.getModalCollapsedShape(composerStartRestartGroup, 6);
                    i4 &= -7169;
                } else {
                    modalCollapsedShape = shape3;
                }
                if ((i3 & 16) != 0) {
                    modalExpandedShape = WideNavigationRailDefaults.INSTANCE.getModalExpandedShape(composerStartRestartGroup, 6);
                    i4 &= -57345;
                } else {
                    modalExpandedShape = shape4;
                }
                if ((i3 & 32) != 0) {
                    wideNavigationRailColorsColors = WideNavigationRailDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    i4 &= -458753;
                } else {
                    wideNavigationRailColorsColors = wideNavigationRailColors;
                }
                if (i5 == 0) {
                }
                if (i7 != 0) {
                    fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                } else {
                    fM6022constructorimpl = f2;
                }
                if ((i3 & 256) != 0) {
                    windowInsets3 = WideNavigationRailDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                    i4 &= -234881025;
                } else {
                    windowInsets3 = windowInsets;
                }
                if ((i3 & 512) != 0) {
                    arrangement = WideNavigationRailDefaults.INSTANCE.getArrangement();
                    i4 &= -1879048193;
                } else {
                    arrangement = vertical;
                }
                if (i9 != 0) {
                    modalExpandedProperties = WideNavigationRailDefaults.INSTANCE.getModalExpandedProperties();
                } else {
                    modalExpandedProperties = modalWideNavigationRailProperties;
                }
                z5 = z2;
                shape7 = modalExpandedShape;
                wideNavigationRailColors3 = wideNavigationRailColorsColors;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-38559147, i4, i13, "androidx.compose.material3.ModalWideNavigationRail (WideNavigationRail.kt:477)");
            }
            ((ModalWideNavigationRailOverride) composerStartRestartGroup.consume(LocalModalWideNavigationRailOverride)).ModalWideNavigationRail(new ModalWideNavigationRailOverrideScope(modifier4, wideNavigationRailStateRememberWideNavigationRailState, z5, modalCollapsedShape, shape7, wideNavigationRailColors3, function6, fM6022constructorimpl, windowInsets3, arrangement, modalExpandedProperties, function3, null), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            wideNavigationRailState2 = wideNavigationRailStateRememberWideNavigationRailState;
            z4 = z5;
            shape5 = modalCollapsedShape;
            shape6 = shape7;
            wideNavigationRailColors2 = wideNavigationRailColors3;
            function5 = function6;
            f3 = fM6022constructorimpl;
            windowInsets2 = windowInsets3;
            vertical2 = arrangement;
            modalWideNavigationRailProperties2 = modalExpandedProperties;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            windowInsets2 = windowInsets;
            function5 = function4;
            modifier3 = modifier2;
            f3 = f2;
            z4 = z2;
            shape5 = shape3;
            shape6 = shape4;
            wideNavigationRailState2 = wideNavigationRailState;
            wideNavigationRailColors2 = wideNavigationRailColors;
            vertical2 = vertical;
            modalWideNavigationRailProperties2 = modalWideNavigationRailProperties;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: pmf
                public final Object invoke(Object obj, Object obj2) {
                    return WideNavigationRailKt.e(modifier3, wideNavigationRailState2, z4, shape5, shape6, wideNavigationRailColors2, function5, f3, windowInsets2, vertical2, modalWideNavigationRailProperties2, function3, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ModalWideNavigationRailContent-pU6N4AM, reason: not valid java name */
    public static final void m1337ModalWideNavigationRailContentpU6N4AM(final boolean z, final boolean z2, final Animatable<Float, AnimationVector1D> animatable, final RailPredictiveBackState railPredictiveBackState, final Function2<? super Float, ? super Continuation<? super Unit>, ? extends Object> function2, final Modifier modifier, final ModalWideNavigationRailState modalWideNavigationRailState, final WideNavigationRailColors wideNavigationRailColors, final Shape shape, final float f, final Function2<? super Composer, ? super Integer, Unit> function3, final WindowInsets windowInsets, final boolean z3, final Arrangement.Vertical vertical, final Function2<? super Composer, ? super Integer, Unit> function4, Composer composer, final int i, final int i2) {
        int i3;
        WideNavigationRailColors wideNavigationRailColors2;
        int i4;
        Composer composer2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1593438005);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(z2) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i3 |= (i & 512) == 0 ? composerStartRestartGroup.changed(animatable) : composerStartRestartGroup.changedInstance(animatable) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i3 |= composerStartRestartGroup.changed(railPredictiveBackState) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i3 |= composerStartRestartGroup.changed(modifier) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(modalWideNavigationRailState) ? 1048576 : 524288;
        }
        if ((i & 12582912) == 0) {
            wideNavigationRailColors2 = wideNavigationRailColors;
            i3 |= composerStartRestartGroup.changed(wideNavigationRailColors2) ? 8388608 : 4194304;
        } else {
            wideNavigationRailColors2 = wideNavigationRailColors;
        }
        if ((i & 100663296) == 0) {
            i3 |= composerStartRestartGroup.changed(shape) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 805306368) == 0) {
            i3 |= composerStartRestartGroup.changed(f) ? 536870912 : 268435456;
        }
        if ((i2 & 6) == 0) {
            i4 = i2 | (composerStartRestartGroup.changedInstance(function3) ? 4 : 2);
        } else {
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            i4 |= composerStartRestartGroup.changed(windowInsets) ? 32 : 16;
        }
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i4 |= composerStartRestartGroup.changed(z3) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i4 |= composerStartRestartGroup.changed(vertical) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function4) ? 16384 : 8192;
        }
        int i5 = i4;
        if (composerStartRestartGroup.shouldExecute(((i3 & 306783379) == 306783378 && (i5 & 9363) == 9362) ? false : true, i3 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1593438005, i3, i5, "androidx.compose.material3.ModalWideNavigationRailContent (WideNavigationRail.kt:988)");
            }
            final boolean z4 = composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection()) == LayoutDirection.Rtl;
            Strings.Companion companion = Strings.INSTANCE;
            final String strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_wide_navigation_rail_pane_title), composerStartRestartGroup, 0);
            long modalContainerColor = wideNavigationRailColors2.getModalContainerColor();
            long modalContentColor = wideNavigationRailColors2.getModalContentColor();
            Modifier modifierFillMaxHeight$default = SizeKt.fillMaxHeight$default(SizeKt.widthIn-VpY3zN4$default(modifier, 0.0f, f, 1, (Object) null), 0.0f, 1, (Object) null);
            boolean zChanged = composerStartRestartGroup.changed(strM1471getString2EP1pXo);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: vmf
                    public final Object invoke(Object obj) {
                        return WideNavigationRailKt.h(strM1471getString2EP1pXo, (SemanticsPropertyReceiver) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierFillMaxHeight$default, false, (Function1) objRememberedValue, 1, null);
            boolean zChangedInstance = ((i3 & 896) == 256 || ((i3 & 512) != 0 && composerStartRestartGroup.changedInstance(animatable))) | composerStartRestartGroup.changedInstance(modalWideNavigationRailState) | ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) | composerStartRestartGroup.changed(z4);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = new Function1() { // from class: wmf
                    public final Object invoke(Object obj) {
                        return WideNavigationRailKt.i(animatable, modalWideNavigationRailState, railPredictiveBackState, z4, (GraphicsLayerScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifierSemantics$default, (Function1) objRememberedValue2);
            AnchoredDraggableState<WideNavigationRailValue> anchoredDraggableState$material3 = modalWideNavigationRailState.getAnchoredDraggableState$material3();
            Orientation orientation = Orientation.Horizontal;
            boolean zChanged2 = ((i3 & 112) == 32) | composerStartRestartGroup.changed(z4) | composerStartRestartGroup.changedInstance(modalWideNavigationRailState);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function2() { // from class: jmf
                    public final Object invoke(Object obj, Object obj2) {
                        return WideNavigationRailKt.j(z2, z4, modalWideNavigationRailState, (IntSize) obj, (Constraints) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            Modifier modifierDraggableAnchors = AnchoredDraggableKt.draggableAnchors(modifierGraphicsLayer, anchoredDraggableState$material3, orientation, (Function2) objRememberedValue3);
            DraggableState draggableState = modalWideNavigationRailState.getAnchoredDraggableState$material3().getDraggableState();
            boolean zIsAnimationRunning = modalWideNavigationRailState.getAnchoredDraggableState$material3().isAnimationRunning();
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(function2);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue4 = new WideNavigationRailKt$ModalWideNavigationRailContent$4$1(function2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            composer2 = composerStartRestartGroup;
            SurfaceKt.m954SurfaceT9BRK9s(DraggableKt.draggable$default(modifierDraggableAnchors, draggableState, orientation, z3, (MutableInteractionSource) null, zIsAnimationRunning, (Function3) null, (Function3) objRememberedValue4, false, 168, (Object) null), shape, modalContainerColor, modalContentColor, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1043835354, true, new WideNavigationRailKt$ModalWideNavigationRailContent$5(animatable, railPredictiveBackState, z4, z, wideNavigationRailColors2, shape, function3, windowInsets, vertical, function4), composer2, 54), composer2, ((i3 >> 21) & 112) | 12582912, 112);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kmf
                public final Object invoke(Object obj, Object obj2) {
                    return WideNavigationRailKt.o(z, z2, animatable, railPredictiveBackState, function2, modifier, modalWideNavigationRailState, wideNavigationRailColors, shape, f, function3, windowInsets, z3, vertical, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Scrim-3J-VO9M, reason: not valid java name */
    public static final void m1338Scrim3JVO9M(final long j, final Function1<? super Continuation<? super Unit>, ? extends Object> function1, final boolean z, Composer composer, final int i) {
        int i2;
        Modifier modifierSemantics;
        Composer composerStartRestartGroup = composer.startRestartGroup(144695261);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 256 : 128;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(144695261, i2, -1, "androidx.compose.material3.Scrim (WideNavigationRail.kt:1102)");
            }
            if (j != 16) {
                composerStartRestartGroup.startReplaceGroup(-1530482291);
                int i3 = i2;
                boolean z2 = true;
                final State stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(z ? 1.0f : 0.0f, MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composerStartRestartGroup, 6), 0.0f, (String) null, (Function1) null, composerStartRestartGroup, 0, 28);
                Object objRememberedValue = composerStartRestartGroup.rememberedValue();
                Composer.Companion companion = Composer.INSTANCE;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.FALSE, null, 2, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                final MutableState mutableState = (MutableState) objRememberedValue;
                Strings.Companion companion2 = Strings.INSTANCE;
                final String strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_wide_navigation_rail_close_rail), composerStartRestartGroup, 0);
                if (z) {
                    composerStartRestartGroup.startReplaceGroup(-1530047423);
                    Modifier.Companion companion3 = Modifier.INSTANCE;
                    Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new WideNavigationRailKt$Scrim$dismissModalRail$1$1(mutableState);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifierPointerInput = SuspendingPointerInputFilterKt.pointerInput(companion3, function1, (PointerInputEventHandler) objRememberedValue2);
                    boolean zChanged = composerStartRestartGroup.changed(strM1471getString2EP1pXo);
                    Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (zChanged || objRememberedValue3 == companion.getEmpty()) {
                        objRememberedValue3 = new Function1() { // from class: lmf
                            public final Object invoke(Object obj) {
                                return WideNavigationRailKt.a(strM1471getString2EP1pXo, mutableState, (SemanticsPropertyReceiver) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    modifierSemantics = SemanticsModifierKt.semantics(modifierPointerInput, true, (Function1) objRememberedValue3);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-1529667363);
                    composerStartRestartGroup.endReplaceGroup();
                    modifierSemantics = Modifier.INSTANCE;
                }
                Modifier modifierThen = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, (Object) null).then(modifierSemantics);
                if ((i3 & 14) != 4) {
                    z2 = false;
                }
                boolean zChanged2 = z2 | composerStartRestartGroup.changed(stateAnimateFloatAsState);
                Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (zChanged2 || objRememberedValue4 == companion.getEmpty()) {
                    objRememberedValue4 = new Function1() { // from class: mmf
                        public final Object invoke(Object obj) {
                            return WideNavigationRailKt.k(j, stateAnimateFloatAsState, (DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                CanvasKt.Canvas(modifierThen, (Function1) objRememberedValue4, composerStartRestartGroup, 0);
                Boolean boolValueOf = Boolean.valueOf(Scrim_3J_VO9M$lambda$28(mutableState));
                boolean zChangedInstance = composerStartRestartGroup.changedInstance(function1);
                Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance || objRememberedValue5 == companion.getEmpty()) {
                    objRememberedValue5 = new WideNavigationRailKt$Scrim$2$1(function1, mutableState, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
                }
                EffectsKt.LaunchedEffect(boolValueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composerStartRestartGroup, 0);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-1529413659);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: nmf
                public final Object invoke(Object obj, Object obj2) {
                    return WideNavigationRailKt.m(j, function1, z, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final float Scrim_3J_VO9M$lambda$26(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean Scrim_3J_VO9M$lambda$28(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void Scrim_3J_VO9M$lambda$29(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Code duplicated, block: B:119:0x0147 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:120:0x0149  */
    /* JADX WARN: Code duplicated, block: B:121:0x014c  */
    /* JADX WARN: Code duplicated, block: B:124:0x0152  */
    /* JADX WARN: Code duplicated, block: B:125:0x015a  */
    /* JADX WARN: Code duplicated, block: B:128:0x0161  */
    /* JADX WARN: Code duplicated, block: B:131:0x016e  */
    /* JADX WARN: Code duplicated, block: B:133:0x0179  */
    /* JADX WARN: Code duplicated, block: B:136:0x017e  */
    /* JADX WARN: Code duplicated, block: B:139:0x018c  */
    /* JADX WARN: Code duplicated, block: B:140:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:143:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:146:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:148:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:151:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:153:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:23:0x0047  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:65:0x00af  */
    /* JADX WARN: Code duplicated, block: B:68:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:71:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:76:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:82:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:84:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:86:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:88:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:89:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:93:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:94:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:97:0x0104  */
    /* JADX WARN: Code duplicated, block: B:99:0x0111  */
    public static final void WideNavigationRail(Modifier modifier, WideNavigationRailState wideNavigationRailState, Shape shape, WideNavigationRailColors wideNavigationRailColors, Function2<? super Composer, ? super Integer, Unit> function2, WindowInsets windowInsets, Arrangement.Vertical vertical, final Function2<? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        int i3;
        Shape shape2;
        WideNavigationRailColors wideNavigationRailColorsColors;
        Function2<? super Composer, ? super Integer, Unit> function4;
        WindowInsets windowInsets2;
        Arrangement.Vertical vertical2;
        Function2<? super Composer, ? super Integer, Unit> function5;
        int i4;
        boolean z;
        final Modifier modifier3;
        final Shape shape3;
        final WideNavigationRailColors wideNavigationRailColors2;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        final WindowInsets windowInsets3;
        final Arrangement.Vertical vertical3;
        final WideNavigationRailState wideNavigationRailState2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        WideNavigationRailState wideNavigationRailStateRememberWideNavigationRailState;
        WideNavigationRailState wideNavigationRailState3;
        Arrangement.Vertical arrangement;
        Shape shape4;
        WideNavigationRailColors wideNavigationRailColors3;
        Function2<? super Composer, ? super Integer, Unit> function7;
        WindowInsets windowInsets4;
        int i5;
        Composer composerStartRestartGroup = composer.startRestartGroup(164193188);
        int i6 = i2 & 1;
        if (i6 != 0) {
            i3 = i | 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i3 = (composerStartRestartGroup.changed(modifier2) ? 4 : 2) | i;
        } else {
            modifier2 = modifier;
            i3 = i;
        }
        if ((i & 48) == 0) {
            if ((i2 & 2) != 0) {
                i5 = 16;
            } else {
                if ((i & 64) == 0 ? composerStartRestartGroup.changed(wideNavigationRailState) : composerStartRestartGroup.changedInstance(wideNavigationRailState)) {
                    i5 = 32;
                } else {
                    i5 = 16;
                }
            }
            i3 |= i5;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i2 & 4) == 0) {
                shape2 = shape;
                int i7 = composerStartRestartGroup.changed(shape2) ? 256 : 128;
                i3 |= i7;
            } else {
                shape2 = shape;
            }
            i3 |= i7;
        } else {
            shape2 = shape;
        }
        if ((i & 3072) == 0) {
            if ((i2 & 8) == 0) {
                wideNavigationRailColorsColors = wideNavigationRailColors;
                int i8 = composerStartRestartGroup.changed(wideNavigationRailColorsColors) ? 2048 : 1024;
                i3 |= i8;
            } else {
                wideNavigationRailColorsColors = wideNavigationRailColors;
            }
            i3 |= i8;
        } else {
            wideNavigationRailColorsColors = wideNavigationRailColors;
        }
        int i9 = i2 & 16;
        if (i9 == 0) {
            if ((i & 24576) == 0) {
                function4 = function2;
                i3 |= composerStartRestartGroup.changedInstance(function4) ? 16384 : 8192;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    windowInsets2 = windowInsets;
                    int i10 = composerStartRestartGroup.changed(windowInsets2) ? 131072 : 65536;
                    i3 |= i10;
                } else {
                    windowInsets2 = windowInsets;
                }
                i3 |= i10;
            } else {
                windowInsets2 = windowInsets;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    vertical2 = vertical;
                    int i11 = composerStartRestartGroup.changed(vertical2) ? 1048576 : 524288;
                    i3 |= i11;
                } else {
                    vertical2 = vertical;
                }
                i3 |= i11;
            } else {
                vertical2 = vertical;
            }
            if ((i2 & 128) != 0) {
                if ((i & 12582912) == 0) {
                    function5 = function3;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i4 = 8388608;
                    } else {
                        i4 = 4194304;
                    }
                    i3 |= i4;
                }
                if ((4793491 & i3) != 4793490) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                        if (i6 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i2 & 2) != 0) {
                            wideNavigationRailStateRememberWideNavigationRailState = WideNavigationRailStateKt.rememberWideNavigationRailState(null, composerStartRestartGroup, 0, 1);
                            i3 &= -113;
                        } else {
                            wideNavigationRailStateRememberWideNavigationRailState = wideNavigationRailState;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                            shape2 = WideNavigationRailDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                            wideNavigationRailColorsColors = WideNavigationRailDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i9 != 0) {
                            function4 = null;
                        }
                        if ((i2 & 32) != 0) {
                            windowInsets2 = WideNavigationRailDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        }
                        if ((i2 & 64) != 0) {
                            i3 &= -3670017;
                            wideNavigationRailState3 = wideNavigationRailStateRememberWideNavigationRailState;
                            arrangement = WideNavigationRailDefaults.INSTANCE.getArrangement();
                            shape4 = shape2;
                            wideNavigationRailColors3 = wideNavigationRailColorsColors;
                            function7 = function4;
                            windowInsets4 = windowInsets2;
                        } else {
                            wideNavigationRailState3 = wideNavigationRailStateRememberWideNavigationRailState;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(164193188, i3, -1, "androidx.compose.material3.WideNavigationRail (WideNavigationRail.kt:169)");
                        }
                        Modifier modifier5 = modifier4;
                        ((WideNavigationRailOverride) composerStartRestartGroup.consume(LocalWideNavigationRailOverride)).WideNavigationRail(new WideNavigationRailOverrideScope(modifier5, wideNavigationRailState3, shape4, wideNavigationRailColors3, function7, windowInsets4, arrangement, function5), composerStartRestartGroup, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier5;
                        wideNavigationRailState2 = wideNavigationRailState3;
                        shape3 = shape4;
                        wideNavigationRailColors2 = wideNavigationRailColors3;
                        function6 = function7;
                        windowInsets3 = windowInsets4;
                        vertical3 = arrangement;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i2 & 2) != 0) {
                            i3 &= -113;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -897;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -7169;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                        }
                        if ((i2 & 64) != 0) {
                            i3 &= -3670017;
                        }
                        wideNavigationRailState3 = wideNavigationRailState;
                        modifier4 = modifier2;
                    }
                    shape4 = shape2;
                    wideNavigationRailColors3 = wideNavigationRailColorsColors;
                    function7 = function4;
                    windowInsets4 = windowInsets2;
                    arrangement = vertical2;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(164193188, i3, -1, "androidx.compose.material3.WideNavigationRail (WideNavigationRail.kt:169)");
                    }
                    Modifier modifier6 = modifier4;
                    ((WideNavigationRailOverride) composerStartRestartGroup.consume(LocalWideNavigationRailOverride)).WideNavigationRail(new WideNavigationRailOverrideScope(modifier6, wideNavigationRailState3, shape4, wideNavigationRailColors3, function7, windowInsets4, arrangement, function5), composerStartRestartGroup, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier6;
                    wideNavigationRailState2 = wideNavigationRailState3;
                    shape3 = shape4;
                    wideNavigationRailColors2 = wideNavigationRailColors3;
                    function6 = function7;
                    windowInsets3 = windowInsets4;
                    vertical3 = arrangement;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    shape3 = shape2;
                    wideNavigationRailColors2 = wideNavigationRailColorsColors;
                    function6 = function4;
                    windowInsets3 = windowInsets2;
                    vertical3 = vertical2;
                    wideNavigationRailState2 = wideNavigationRailState;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: smf
                        public final Object invoke(Object obj, Object obj2) {
                            return WideNavigationRailKt.g(modifier3, wideNavigationRailState2, shape3, wideNavigationRailColors2, function6, windowInsets3, vertical3, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            function5 = function3;
            if ((4793491 & i3) != 4793490) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i6 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        wideNavigationRailStateRememberWideNavigationRailState = WideNavigationRailStateKt.rememberWideNavigationRailState(null, composerStartRestartGroup, 0, 1);
                        i3 &= -113;
                    } else {
                        wideNavigationRailStateRememberWideNavigationRailState = wideNavigationRailState;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        shape2 = WideNavigationRailDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        wideNavigationRailColorsColors = WideNavigationRailDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i9 != 0) {
                        function4 = null;
                    }
                    if ((i2 & 32) != 0) {
                        windowInsets2 = WideNavigationRailDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    }
                    if ((i2 & 64) != 0) {
                        i3 &= -3670017;
                        wideNavigationRailState3 = wideNavigationRailStateRememberWideNavigationRailState;
                        arrangement = WideNavigationRailDefaults.INSTANCE.getArrangement();
                        shape4 = shape2;
                        wideNavigationRailColors3 = wideNavigationRailColorsColors;
                        function7 = function4;
                        windowInsets4 = windowInsets2;
                    } else {
                        wideNavigationRailState3 = wideNavigationRailStateRememberWideNavigationRailState;
                        shape4 = shape2;
                        wideNavigationRailColors3 = wideNavigationRailColorsColors;
                        function7 = function4;
                        windowInsets4 = windowInsets2;
                        arrangement = vertical2;
                    }
                } else {
                    if (i6 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        wideNavigationRailStateRememberWideNavigationRailState = WideNavigationRailStateKt.rememberWideNavigationRailState(null, composerStartRestartGroup, 0, 1);
                        i3 &= -113;
                    } else {
                        wideNavigationRailStateRememberWideNavigationRailState = wideNavigationRailState;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        shape2 = WideNavigationRailDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        wideNavigationRailColorsColors = WideNavigationRailDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i9 != 0) {
                        function4 = null;
                    }
                    if ((i2 & 32) != 0) {
                        windowInsets2 = WideNavigationRailDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    }
                    if ((i2 & 64) != 0) {
                        i3 &= -3670017;
                        wideNavigationRailState3 = wideNavigationRailStateRememberWideNavigationRailState;
                        arrangement = WideNavigationRailDefaults.INSTANCE.getArrangement();
                        shape4 = shape2;
                        wideNavigationRailColors3 = wideNavigationRailColorsColors;
                        function7 = function4;
                        windowInsets4 = windowInsets2;
                    } else {
                        wideNavigationRailState3 = wideNavigationRailStateRememberWideNavigationRailState;
                        shape4 = shape2;
                        wideNavigationRailColors3 = wideNavigationRailColorsColors;
                        function7 = function4;
                        windowInsets4 = windowInsets2;
                        arrangement = vertical2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(164193188, i3, -1, "androidx.compose.material3.WideNavigationRail (WideNavigationRail.kt:169)");
                }
                Modifier modifier7 = modifier4;
                ((WideNavigationRailOverride) composerStartRestartGroup.consume(LocalWideNavigationRailOverride)).WideNavigationRail(new WideNavigationRailOverrideScope(modifier7, wideNavigationRailState3, shape4, wideNavigationRailColors3, function7, windowInsets4, arrangement, function5), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier7;
                wideNavigationRailState2 = wideNavigationRailState3;
                shape3 = shape4;
                wideNavigationRailColors2 = wideNavigationRailColors3;
                function6 = function7;
                windowInsets3 = windowInsets4;
                vertical3 = arrangement;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                shape3 = shape2;
                wideNavigationRailColors2 = wideNavigationRailColorsColors;
                function6 = function4;
                windowInsets3 = windowInsets2;
                vertical3 = vertical2;
                wideNavigationRailState2 = wideNavigationRailState;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: smf
                    public final Object invoke(Object obj, Object obj2) {
                        return WideNavigationRailKt.g(modifier3, wideNavigationRailState2, shape3, wideNavigationRailColors2, function6, windowInsets3, vertical3, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        function4 = function2;
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                windowInsets2 = windowInsets;
                if (composerStartRestartGroup.changed(windowInsets2)) {
                }
                i3 |= i10;
            } else {
                windowInsets2 = windowInsets;
            }
            i3 |= i10;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((1572864 & i) == 0) {
            if ((i2 & 64) == 0) {
                vertical2 = vertical;
                if (composerStartRestartGroup.changed(vertical2)) {
                }
                i3 |= i11;
            } else {
                vertical2 = vertical;
            }
            i3 |= i11;
        } else {
            vertical2 = vertical;
        }
        if ((i2 & 128) != 0) {
            if ((i & 12582912) == 0) {
                function5 = function3;
                if (composerStartRestartGroup.changedInstance(function5)) {
                    i4 = 8388608;
                } else {
                    i4 = 4194304;
                }
                i3 |= i4;
            }
            if ((4793491 & i3) != 4793490) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i6 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        wideNavigationRailStateRememberWideNavigationRailState = WideNavigationRailStateKt.rememberWideNavigationRailState(null, composerStartRestartGroup, 0, 1);
                        i3 &= -113;
                    } else {
                        wideNavigationRailStateRememberWideNavigationRailState = wideNavigationRailState;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        shape2 = WideNavigationRailDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        wideNavigationRailColorsColors = WideNavigationRailDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i9 != 0) {
                        function4 = null;
                    }
                    if ((i2 & 32) != 0) {
                        windowInsets2 = WideNavigationRailDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    }
                    if ((i2 & 64) != 0) {
                        i3 &= -3670017;
                        wideNavigationRailState3 = wideNavigationRailStateRememberWideNavigationRailState;
                        arrangement = WideNavigationRailDefaults.INSTANCE.getArrangement();
                        shape4 = shape2;
                        wideNavigationRailColors3 = wideNavigationRailColorsColors;
                        function7 = function4;
                        windowInsets4 = windowInsets2;
                    } else {
                        wideNavigationRailState3 = wideNavigationRailStateRememberWideNavigationRailState;
                        shape4 = shape2;
                        wideNavigationRailColors3 = wideNavigationRailColorsColors;
                        function7 = function4;
                        windowInsets4 = windowInsets2;
                        arrangement = vertical2;
                    }
                } else {
                    if (i6 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        wideNavigationRailStateRememberWideNavigationRailState = WideNavigationRailStateKt.rememberWideNavigationRailState(null, composerStartRestartGroup, 0, 1);
                        i3 &= -113;
                    } else {
                        wideNavigationRailStateRememberWideNavigationRailState = wideNavigationRailState;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -897;
                        shape2 = WideNavigationRailDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 8) != 0) {
                        i3 &= -7169;
                        wideNavigationRailColorsColors = WideNavigationRailDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i9 != 0) {
                        function4 = null;
                    }
                    if ((i2 & 32) != 0) {
                        windowInsets2 = WideNavigationRailDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    }
                    if ((i2 & 64) != 0) {
                        i3 &= -3670017;
                        wideNavigationRailState3 = wideNavigationRailStateRememberWideNavigationRailState;
                        arrangement = WideNavigationRailDefaults.INSTANCE.getArrangement();
                        shape4 = shape2;
                        wideNavigationRailColors3 = wideNavigationRailColorsColors;
                        function7 = function4;
                        windowInsets4 = windowInsets2;
                    } else {
                        wideNavigationRailState3 = wideNavigationRailStateRememberWideNavigationRailState;
                        shape4 = shape2;
                        wideNavigationRailColors3 = wideNavigationRailColorsColors;
                        function7 = function4;
                        windowInsets4 = windowInsets2;
                        arrangement = vertical2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(164193188, i3, -1, "androidx.compose.material3.WideNavigationRail (WideNavigationRail.kt:169)");
                }
                Modifier modifier8 = modifier4;
                ((WideNavigationRailOverride) composerStartRestartGroup.consume(LocalWideNavigationRailOverride)).WideNavigationRail(new WideNavigationRailOverrideScope(modifier8, wideNavigationRailState3, shape4, wideNavigationRailColors3, function7, windowInsets4, arrangement, function5), composerStartRestartGroup, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier8;
                wideNavigationRailState2 = wideNavigationRailState3;
                shape3 = shape4;
                wideNavigationRailColors2 = wideNavigationRailColors3;
                function6 = function7;
                windowInsets3 = windowInsets4;
                vertical3 = arrangement;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                shape3 = shape2;
                wideNavigationRailColors2 = wideNavigationRailColorsColors;
                function6 = function4;
                windowInsets3 = windowInsets2;
                vertical3 = vertical2;
                wideNavigationRailState2 = wideNavigationRailState;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: smf
                    public final Object invoke(Object obj, Object obj2) {
                        return WideNavigationRailKt.g(modifier3, wideNavigationRailState2, shape3, wideNavigationRailColors2, function6, windowInsets3, vertical3, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 12582912;
        function5 = function3;
        if ((4793491 & i3) != 4793490) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i6 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 2) != 0) {
                    wideNavigationRailStateRememberWideNavigationRailState = WideNavigationRailStateKt.rememberWideNavigationRailState(null, composerStartRestartGroup, 0, 1);
                    i3 &= -113;
                } else {
                    wideNavigationRailStateRememberWideNavigationRailState = wideNavigationRailState;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    shape2 = WideNavigationRailDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    wideNavigationRailColorsColors = WideNavigationRailDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i9 != 0) {
                    function4 = null;
                }
                if ((i2 & 32) != 0) {
                    windowInsets2 = WideNavigationRailDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                    i3 &= -458753;
                }
                if ((i2 & 64) != 0) {
                    i3 &= -3670017;
                    wideNavigationRailState3 = wideNavigationRailStateRememberWideNavigationRailState;
                    arrangement = WideNavigationRailDefaults.INSTANCE.getArrangement();
                    shape4 = shape2;
                    wideNavigationRailColors3 = wideNavigationRailColorsColors;
                    function7 = function4;
                    windowInsets4 = windowInsets2;
                } else {
                    wideNavigationRailState3 = wideNavigationRailStateRememberWideNavigationRailState;
                    shape4 = shape2;
                    wideNavigationRailColors3 = wideNavigationRailColorsColors;
                    function7 = function4;
                    windowInsets4 = windowInsets2;
                    arrangement = vertical2;
                }
            } else {
                if (i6 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 2) != 0) {
                    wideNavigationRailStateRememberWideNavigationRailState = WideNavigationRailStateKt.rememberWideNavigationRailState(null, composerStartRestartGroup, 0, 1);
                    i3 &= -113;
                } else {
                    wideNavigationRailStateRememberWideNavigationRailState = wideNavigationRailState;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -897;
                    shape2 = WideNavigationRailDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 8) != 0) {
                    i3 &= -7169;
                    wideNavigationRailColorsColors = WideNavigationRailDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i9 != 0) {
                    function4 = null;
                }
                if ((i2 & 32) != 0) {
                    windowInsets2 = WideNavigationRailDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                    i3 &= -458753;
                }
                if ((i2 & 64) != 0) {
                    i3 &= -3670017;
                    wideNavigationRailState3 = wideNavigationRailStateRememberWideNavigationRailState;
                    arrangement = WideNavigationRailDefaults.INSTANCE.getArrangement();
                    shape4 = shape2;
                    wideNavigationRailColors3 = wideNavigationRailColorsColors;
                    function7 = function4;
                    windowInsets4 = windowInsets2;
                } else {
                    wideNavigationRailState3 = wideNavigationRailStateRememberWideNavigationRailState;
                    shape4 = shape2;
                    wideNavigationRailColors3 = wideNavigationRailColorsColors;
                    function7 = function4;
                    windowInsets4 = windowInsets2;
                    arrangement = vertical2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(164193188, i3, -1, "androidx.compose.material3.WideNavigationRail (WideNavigationRail.kt:169)");
            }
            Modifier modifier9 = modifier4;
            ((WideNavigationRailOverride) composerStartRestartGroup.consume(LocalWideNavigationRailOverride)).WideNavigationRail(new WideNavigationRailOverrideScope(modifier9, wideNavigationRailState3, shape4, wideNavigationRailColors3, function7, windowInsets4, arrangement, function5), composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier9;
            wideNavigationRailState2 = wideNavigationRailState3;
            shape3 = shape4;
            wideNavigationRailColors2 = wideNavigationRailColors3;
            function6 = function7;
            windowInsets3 = windowInsets4;
            vertical3 = arrangement;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            shape3 = shape2;
            wideNavigationRailColors2 = wideNavigationRailColorsColors;
            function6 = function4;
            windowInsets3 = windowInsets2;
            vertical3 = vertical2;
            wideNavigationRailState2 = wideNavigationRailState;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: smf
                public final Object invoke(Object obj, Object obj2) {
                    return WideNavigationRailKt.g(modifier3, wideNavigationRailState2, shape3, wideNavigationRailColors2, function6, windowInsets3, vertical3, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0107  */
    /* JADX WARN: Code duplicated, block: B:103:0x010e  */
    /* JADX WARN: Code duplicated, block: B:105:0x0112  */
    /* JADX WARN: Code duplicated, block: B:107:0x011c  */
    /* JADX WARN: Code duplicated, block: B:108:0x011f  */
    /* JADX WARN: Code duplicated, block: B:112:0x0131  */
    /* JADX WARN: Code duplicated, block: B:113:0x0134  */
    /* JADX WARN: Code duplicated, block: B:116:0x013d  */
    /* JADX WARN: Code duplicated, block: B:118:0x014b  */
    /* JADX WARN: Code duplicated, block: B:128:0x0164 A[PHI: r0 r7 r9 r10 r15
      0x0164: PHI (r0v21 int) = (r0v11 int), (r0v25 int), (r0v26 int) binds: [B:142:0x0196, B:126:0x0160, B:127:0x0162] A[DONT_GENERATE, DONT_INLINE]
      0x0164: PHI (r7v30 androidx.compose.ui.Modifier) = (r7v6 androidx.compose.ui.Modifier), (r7v3 androidx.compose.ui.Modifier), (r7v3 androidx.compose.ui.Modifier) binds: [B:142:0x0196, B:126:0x0160, B:127:0x0162] A[DONT_GENERATE, DONT_INLINE]
      0x0164: PHI (r9v16 boolean) = (r9v6 boolean), (r9v3 boolean), (r9v3 boolean) binds: [B:142:0x0196, B:126:0x0160, B:127:0x0162] A[DONT_GENERATE, DONT_INLINE]
      0x0164: PHI (r10v14 int) = (r10v10 int), (r10v7 int), (r10v7 int) binds: [B:142:0x0196, B:126:0x0160, B:127:0x0162] A[DONT_GENERATE, DONT_INLINE]
      0x0164: PHI (r15v8 androidx.compose.material3.NavigationItemColors) = 
      (r15v4 androidx.compose.material3.NavigationItemColors)
      (r15v3 androidx.compose.material3.NavigationItemColors)
      (r15v3 androidx.compose.material3.NavigationItemColors)
     binds: [B:142:0x0196, B:126:0x0160, B:127:0x0162] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:130:0x016f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:131:0x0171  */
    /* JADX WARN: Code duplicated, block: B:133:0x0176  */
    /* JADX WARN: Code duplicated, block: B:136:0x017c  */
    /* JADX WARN: Code duplicated, block: B:137:0x0186  */
    /* JADX WARN: Code duplicated, block: B:140:0x018c  */
    /* JADX WARN: Code duplicated, block: B:143:0x0198  */
    /* JADX WARN: Code duplicated, block: B:146:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:148:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:150:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:152:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:155:0x024c  */
    /* JADX WARN: Code duplicated, block: B:157:0x0259  */
    /* JADX WARN: Code duplicated, block: B:160:0x026a  */
    /* JADX WARN: Code duplicated, block: B:162:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0064  */
    /* JADX WARN: Code duplicated, block: B:38:0x0069  */
    /* JADX WARN: Code duplicated, block: B:40:0x006d  */
    /* JADX WARN: Code duplicated, block: B:42:0x0075  */
    /* JADX WARN: Code duplicated, block: B:43:0x0078  */
    /* JADX WARN: Code duplicated, block: B:47:0x007f  */
    /* JADX WARN: Code duplicated, block: B:48:0x0082  */
    /* JADX WARN: Code duplicated, block: B:50:0x0086  */
    /* JADX WARN: Code duplicated, block: B:52:0x008c  */
    /* JADX WARN: Code duplicated, block: B:53:0x008f  */
    /* JADX WARN: Code duplicated, block: B:57:0x0098  */
    /* JADX WARN: Code duplicated, block: B:59:0x009c  */
    /* JADX WARN: Code duplicated, block: B:61:0x009f  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:64:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:68:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:72:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:79:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:81:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:83:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:84:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:87:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:90:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:92:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:94:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:95:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:98:0x00ff  */
    /* JADX INFO: renamed from: WideNavigationRailItem-pli-t6k, reason: not valid java name */
    public static final void m1339WideNavigationRailItemplit6k(final boolean z, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final boolean z2, Modifier modifier, boolean z3, int i, NavigationItemColors navigationItemColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i2, final int i3) {
        boolean z4;
        int i4;
        Function0<Unit> function1;
        Function2<? super Composer, ? super Integer, Unit> function4;
        Function2<? super Composer, ? super Integer, Unit> function5;
        int i5;
        int i6;
        int i7;
        Modifier modifier2;
        int i8;
        int i9;
        boolean z5;
        int i10;
        int iM1335iconPositionFors8pcRp0;
        NavigationItemColors navigationItemColorsColors;
        int i11;
        int i12;
        int i13;
        boolean z6;
        Composer composer2;
        final Modifier modifier3;
        final boolean z7;
        final int i14;
        final NavigationItemColors navigationItemColors2;
        final MutableInteractionSource mutableInteractionSource2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i15;
        int i16;
        MutableInteractionSource mutableInteractionSource3;
        MutableInteractionSource mutableInteractionSource4;
        Object objRememberedValue;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1894733304);
        if ((i3 & 1) != 0) {
            i4 = i2 | 6;
            z4 = z;
        } else {
            z4 = z;
            if ((i2 & 6) == 0) {
                i4 = (composerStartRestartGroup.changed(z4) ? 4 : 2) | i2;
            } else {
                i4 = i2;
            }
        }
        if ((i3 & 2) != 0) {
            i4 |= 48;
            function1 = function0;
        } else {
            function1 = function0;
            if ((i2 & 48) == 0) {
                i4 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
            }
        }
        if ((i3 & 4) == 0) {
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                function4 = function2;
                i4 |= composerStartRestartGroup.changedInstance(function4) ? 256 : 128;
            }
            if ((i3 & 8) != 0) {
                if ((i2 & 3072) == 0) {
                    function5 = function3;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i4 |= i5;
                }
                if ((i3 & 16) != 0) {
                    i4 |= 24576;
                } else if ((i2 & 24576) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i6 = 16384;
                    } else {
                        i6 = 8192;
                    }
                    i4 |= i6;
                }
                i7 = i3 & 32;
                if (i7 != 0) {
                    if ((196608 & i2) == 0) {
                        modifier2 = modifier;
                        if (composerStartRestartGroup.changed(modifier2)) {
                            i8 = 131072;
                        } else {
                            i8 = 65536;
                        }
                        i4 |= i8;
                    }
                    i9 = i3 & 64;
                    if (i9 != 0) {
                        if ((1572864 & i2) == 0) {
                            z5 = z3;
                            if (composerStartRestartGroup.changed(z5)) {
                                i10 = 1048576;
                            } else {
                                i10 = 524288;
                            }
                            i4 |= i10;
                        }
                        if ((12582912 & i2) == 0) {
                            if ((i3 & 128) == 0) {
                                iM1335iconPositionFors8pcRp0 = i;
                                int i17 = composerStartRestartGroup.changed(iM1335iconPositionFors8pcRp0) ? 8388608 : 4194304;
                                i4 |= i17;
                            } else {
                                iM1335iconPositionFors8pcRp0 = i;
                            }
                            i4 |= i17;
                        } else {
                            iM1335iconPositionFors8pcRp0 = i;
                        }
                        if ((100663296 & i2) == 0) {
                            if ((i3 & 256) == 0) {
                                navigationItemColorsColors = navigationItemColors;
                                int i18 = composerStartRestartGroup.changed(navigationItemColorsColors) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
                                i4 |= i18;
                            } else {
                                navigationItemColorsColors = navigationItemColors;
                            }
                            i4 |= i18;
                        } else {
                            navigationItemColorsColors = navigationItemColors;
                        }
                        i11 = i3 & 512;
                        if (i11 != 0) {
                            if ((i2 & 805306368) == 0) {
                                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                    i12 = 536870912;
                                } else {
                                    i12 = 268435456;
                                }
                                i4 |= i12;
                            }
                            i13 = i4;
                            if ((i4 & 306783379) != 306783378) {
                                z6 = true;
                            } else {
                                z6 = false;
                            }
                            if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                                composerStartRestartGroup.startDefaults();
                                if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                    if (i7 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i9 != 0) {
                                        z5 = true;
                                    }
                                    if ((i3 & 128) != 0) {
                                        i15 = i13 & (-29360129);
                                        iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                                    } else {
                                        i15 = i13;
                                    }
                                    if ((i3 & 256) != 0) {
                                        i15 &= -234881025;
                                        navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                    }
                                    i16 = i15;
                                    if (i11 != 0) {
                                        mutableInteractionSource3 = null;
                                    }
                                    Modifier modifier4 = modifier2;
                                    boolean z8 = z5;
                                    int i19 = iM1335iconPositionFors8pcRp0;
                                    NavigationItemColors navigationItemColors3 = navigationItemColorsColors;
                                    composerStartRestartGroup.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                                    }
                                    if (mutableInteractionSource3 == null) {
                                        composerStartRestartGroup.startReplaceGroup(-1539072909);
                                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                        }
                                        composerStartRestartGroup.endReplaceGroup();
                                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                                    } else {
                                        composerStartRestartGroup.startReplaceGroup(227446500);
                                        composerStartRestartGroup.endReplaceGroup();
                                        mutableInteractionSource4 = mutableInteractionSource3;
                                    }
                                    Shape value = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                                    NavigationRailVerticalItemTokens navigationRailVerticalItemTokens = NavigationRailVerticalItemTokens.INSTANCE;
                                    float fM1958getActiveIndicatorWidthD9Ej5fM = navigationRailVerticalItemTokens.m1958getActiveIndicatorWidthD9Ej5fM();
                                    TextStyle value2 = TypographyKt.getValue(navigationRailVerticalItemTokens.getLabelTextFont(), composerStartRestartGroup, 6);
                                    NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens = NavigationRailHorizontalItemTokens.INSTANCE;
                                    int i20 = i16 << 3;
                                    composer2 = composerStartRestartGroup;
                                    NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value, fM1958getActiveIndicatorWidthD9Ej5fM, value2, TypographyKt.getValue(navigationRailHorizontalItemTokens.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors3, modifier4, z8, function5, i19, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i20) | (i20 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    mutableInteractionSource2 = mutableInteractionSource3;
                                    navigationItemColors2 = navigationItemColors3;
                                    modifier3 = modifier4;
                                    z7 = z8;
                                    i14 = i19;
                                } else {
                                    composerStartRestartGroup.skipToGroupEnd();
                                    i16 = (i3 & 128) != 0 ? i13 & (-29360129) : i13;
                                    if ((i3 & 256) != 0) {
                                        i16 &= -234881025;
                                    }
                                }
                                mutableInteractionSource3 = mutableInteractionSource;
                                Modifier modifier5 = modifier2;
                                boolean z9 = z5;
                                int i110 = iM1335iconPositionFors8pcRp0;
                                NavigationItemColors navigationItemColors4 = navigationItemColorsColors;
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                                }
                                if (mutableInteractionSource3 == null) {
                                    composerStartRestartGroup.startReplaceGroup(-1539072909);
                                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                    }
                                    composerStartRestartGroup.endReplaceGroup();
                                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                                } else {
                                    composerStartRestartGroup.startReplaceGroup(227446500);
                                    composerStartRestartGroup.endReplaceGroup();
                                    mutableInteractionSource4 = mutableInteractionSource3;
                                }
                                Shape value3 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                                NavigationRailVerticalItemTokens navigationRailVerticalItemTokens2 = NavigationRailVerticalItemTokens.INSTANCE;
                                float fM1958getActiveIndicatorWidthD9Ej5fM2 = navigationRailVerticalItemTokens2.m1958getActiveIndicatorWidthD9Ej5fM();
                                TextStyle value4 = TypographyKt.getValue(navigationRailVerticalItemTokens2.getLabelTextFont(), composerStartRestartGroup, 6);
                                NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens2 = NavigationRailHorizontalItemTokens.INSTANCE;
                                int i21 = i16 << 3;
                                composer2 = composerStartRestartGroup;
                                NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value3, fM1958getActiveIndicatorWidthD9Ej5fM2, value4, TypographyKt.getValue(navigationRailHorizontalItemTokens2.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens2.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens2.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens2.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors4, modifier5, z9, function5, i110, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i21) | (i21 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                mutableInteractionSource2 = mutableInteractionSource3;
                                navigationItemColors2 = navigationItemColors4;
                                modifier3 = modifier5;
                                z7 = z9;
                                i14 = i110;
                            } else {
                                composer2 = composerStartRestartGroup;
                                composer2.skipToGroupEnd();
                                modifier3 = modifier2;
                                z7 = z5;
                                i14 = iM1335iconPositionFors8pcRp0;
                                navigationItemColors2 = navigationItemColorsColors;
                                mutableInteractionSource2 = mutableInteractionSource;
                            }
                            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                                    public final Object invoke(Object obj, Object obj2) {
                                        return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i4 |= 805306368;
                        i13 = i4;
                        if ((i4 & 306783379) != 306783378) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i2 & 1) != 0) {
                                if (i7 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i9 != 0) {
                                    z5 = true;
                                }
                                if ((i3 & 128) != 0) {
                                    i15 = i13 & (-29360129);
                                    iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                                } else {
                                    i15 = i13;
                                }
                                if ((i3 & 256) != 0) {
                                    i15 &= -234881025;
                                    navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                }
                                i16 = i15;
                                if (i11 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            } else {
                                if (i7 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i9 != 0) {
                                    z5 = true;
                                }
                                if ((i3 & 128) != 0) {
                                    i15 = i13 & (-29360129);
                                    iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                                } else {
                                    i15 = i13;
                                }
                                if ((i3 & 256) != 0) {
                                    i15 &= -234881025;
                                    navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                }
                                i16 = i15;
                                if (i11 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            }
                            Modifier modifier6 = modifier2;
                            boolean z10 = z5;
                            int i111 = iM1335iconPositionFors8pcRp0;
                            NavigationItemColors navigationItemColors5 = navigationItemColorsColors;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                            }
                            if (mutableInteractionSource3 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1539072909);
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(227446500);
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = mutableInteractionSource3;
                            }
                            Shape value5 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                            NavigationRailVerticalItemTokens navigationRailVerticalItemTokens3 = NavigationRailVerticalItemTokens.INSTANCE;
                            float fM1958getActiveIndicatorWidthD9Ej5fM3 = navigationRailVerticalItemTokens3.m1958getActiveIndicatorWidthD9Ej5fM();
                            TextStyle value6 = TypographyKt.getValue(navigationRailVerticalItemTokens3.getLabelTextFont(), composerStartRestartGroup, 6);
                            NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens3 = NavigationRailHorizontalItemTokens.INSTANCE;
                            int i22 = i16 << 3;
                            composer2 = composerStartRestartGroup;
                            NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value5, fM1958getActiveIndicatorWidthD9Ej5fM3, value6, TypographyKt.getValue(navigationRailHorizontalItemTokens3.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens3.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens3.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens3.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors5, modifier6, z10, function5, i111, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i22) | (i22 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            mutableInteractionSource2 = mutableInteractionSource3;
                            navigationItemColors2 = navigationItemColors5;
                            modifier3 = modifier6;
                            z7 = z10;
                            i14 = i111;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier3 = modifier2;
                            z7 = z5;
                            i14 = iM1335iconPositionFors8pcRp0;
                            navigationItemColors2 = navigationItemColorsColors;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                                public final Object invoke(Object obj, Object obj2) {
                                    return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i4 |= 1572864;
                    z5 = z3;
                    if ((12582912 & i2) == 0) {
                        if ((i3 & 128) == 0) {
                            iM1335iconPositionFors8pcRp0 = i;
                            if (composerStartRestartGroup.changed(iM1335iconPositionFors8pcRp0)) {
                            }
                            i4 |= i17;
                        } else {
                            iM1335iconPositionFors8pcRp0 = i;
                        }
                        i4 |= i17;
                    } else {
                        iM1335iconPositionFors8pcRp0 = i;
                    }
                    if ((100663296 & i2) == 0) {
                        if ((i3 & 256) == 0) {
                            navigationItemColorsColors = navigationItemColors;
                            if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                            }
                            i4 |= i18;
                        } else {
                            navigationItemColorsColors = navigationItemColors;
                        }
                        i4 |= i18;
                    } else {
                        navigationItemColorsColors = navigationItemColors;
                    }
                    i11 = i3 & 512;
                    if (i11 != 0) {
                        if ((i2 & 805306368) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i12 = 536870912;
                            } else {
                                i12 = 268435456;
                            }
                            i4 |= i12;
                        }
                        i13 = i4;
                        if ((i4 & 306783379) != 306783378) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i2 & 1) != 0) {
                                if (i7 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i9 != 0) {
                                    z5 = true;
                                }
                                if ((i3 & 128) != 0) {
                                    i15 = i13 & (-29360129);
                                    iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                                } else {
                                    i15 = i13;
                                }
                                if ((i3 & 256) != 0) {
                                    i15 &= -234881025;
                                    navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                }
                                i16 = i15;
                                if (i11 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            } else {
                                if (i7 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i9 != 0) {
                                    z5 = true;
                                }
                                if ((i3 & 128) != 0) {
                                    i15 = i13 & (-29360129);
                                    iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                                } else {
                                    i15 = i13;
                                }
                                if ((i3 & 256) != 0) {
                                    i15 &= -234881025;
                                    navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                }
                                i16 = i15;
                                if (i11 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            }
                            Modifier modifier7 = modifier2;
                            boolean z11 = z5;
                            int i112 = iM1335iconPositionFors8pcRp0;
                            NavigationItemColors navigationItemColors6 = navigationItemColorsColors;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                            }
                            if (mutableInteractionSource3 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1539072909);
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(227446500);
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = mutableInteractionSource3;
                            }
                            Shape value7 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                            NavigationRailVerticalItemTokens navigationRailVerticalItemTokens4 = NavigationRailVerticalItemTokens.INSTANCE;
                            float fM1958getActiveIndicatorWidthD9Ej5fM4 = navigationRailVerticalItemTokens4.m1958getActiveIndicatorWidthD9Ej5fM();
                            TextStyle value8 = TypographyKt.getValue(navigationRailVerticalItemTokens4.getLabelTextFont(), composerStartRestartGroup, 6);
                            NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens4 = NavigationRailHorizontalItemTokens.INSTANCE;
                            int i23 = i16 << 3;
                            composer2 = composerStartRestartGroup;
                            NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value7, fM1958getActiveIndicatorWidthD9Ej5fM4, value8, TypographyKt.getValue(navigationRailHorizontalItemTokens4.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens4.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens4.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens4.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors6, modifier7, z11, function5, i112, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i23) | (i23 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            mutableInteractionSource2 = mutableInteractionSource3;
                            navigationItemColors2 = navigationItemColors6;
                            modifier3 = modifier7;
                            z7 = z11;
                            i14 = i112;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier3 = modifier2;
                            z7 = z5;
                            i14 = iM1335iconPositionFors8pcRp0;
                            navigationItemColors2 = navigationItemColorsColors;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                                public final Object invoke(Object obj, Object obj2) {
                                    return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i4 |= 805306368;
                    i13 = i4;
                    if ((i4 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        Modifier modifier8 = modifier2;
                        boolean z12 = z5;
                        int i113 = iM1335iconPositionFors8pcRp0;
                        NavigationItemColors navigationItemColors7 = navigationItemColorsColors;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1539072909);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(227446500);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        Shape value9 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        NavigationRailVerticalItemTokens navigationRailVerticalItemTokens5 = NavigationRailVerticalItemTokens.INSTANCE;
                        float fM1958getActiveIndicatorWidthD9Ej5fM5 = navigationRailVerticalItemTokens5.m1958getActiveIndicatorWidthD9Ej5fM();
                        TextStyle value10 = TypographyKt.getValue(navigationRailVerticalItemTokens5.getLabelTextFont(), composerStartRestartGroup, 6);
                        NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens5 = NavigationRailHorizontalItemTokens.INSTANCE;
                        int i24 = i16 << 3;
                        composer2 = composerStartRestartGroup;
                        NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value9, fM1958getActiveIndicatorWidthD9Ej5fM5, value10, TypographyKt.getValue(navigationRailHorizontalItemTokens5.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens5.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens5.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens5.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors7, modifier8, z12, function5, i113, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i24) | (i24 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource2 = mutableInteractionSource3;
                        navigationItemColors2 = navigationItemColors7;
                        modifier3 = modifier8;
                        z7 = z12;
                        i14 = i113;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        z7 = z5;
                        i14 = iM1335iconPositionFors8pcRp0;
                        navigationItemColors2 = navigationItemColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                            public final Object invoke(Object obj, Object obj2) {
                                return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                modifier2 = modifier;
                i9 = i3 & 64;
                if (i9 != 0) {
                    if ((1572864 & i2) == 0) {
                        z5 = z3;
                        if (composerStartRestartGroup.changed(z5)) {
                            i10 = 1048576;
                        } else {
                            i10 = 524288;
                        }
                        i4 |= i10;
                    }
                    if ((12582912 & i2) == 0) {
                        if ((i3 & 128) == 0) {
                            iM1335iconPositionFors8pcRp0 = i;
                            if (composerStartRestartGroup.changed(iM1335iconPositionFors8pcRp0)) {
                            }
                            i4 |= i17;
                        } else {
                            iM1335iconPositionFors8pcRp0 = i;
                        }
                        i4 |= i17;
                    } else {
                        iM1335iconPositionFors8pcRp0 = i;
                    }
                    if ((100663296 & i2) == 0) {
                        if ((i3 & 256) == 0) {
                            navigationItemColorsColors = navigationItemColors;
                            if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                            }
                            i4 |= i18;
                        } else {
                            navigationItemColorsColors = navigationItemColors;
                        }
                        i4 |= i18;
                    } else {
                        navigationItemColorsColors = navigationItemColors;
                    }
                    i11 = i3 & 512;
                    if (i11 != 0) {
                        if ((i2 & 805306368) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i12 = 536870912;
                            } else {
                                i12 = 268435456;
                            }
                            i4 |= i12;
                        }
                        i13 = i4;
                        if ((i4 & 306783379) != 306783378) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i2 & 1) != 0) {
                                if (i7 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i9 != 0) {
                                    z5 = true;
                                }
                                if ((i3 & 128) != 0) {
                                    i15 = i13 & (-29360129);
                                    iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                                } else {
                                    i15 = i13;
                                }
                                if ((i3 & 256) != 0) {
                                    i15 &= -234881025;
                                    navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                }
                                i16 = i15;
                                if (i11 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            } else {
                                if (i7 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i9 != 0) {
                                    z5 = true;
                                }
                                if ((i3 & 128) != 0) {
                                    i15 = i13 & (-29360129);
                                    iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                                } else {
                                    i15 = i13;
                                }
                                if ((i3 & 256) != 0) {
                                    i15 &= -234881025;
                                    navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                }
                                i16 = i15;
                                if (i11 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            }
                            Modifier modifier9 = modifier2;
                            boolean z13 = z5;
                            int i114 = iM1335iconPositionFors8pcRp0;
                            NavigationItemColors navigationItemColors8 = navigationItemColorsColors;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                            }
                            if (mutableInteractionSource3 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1539072909);
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(227446500);
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = mutableInteractionSource3;
                            }
                            Shape value11 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                            NavigationRailVerticalItemTokens navigationRailVerticalItemTokens6 = NavigationRailVerticalItemTokens.INSTANCE;
                            float fM1958getActiveIndicatorWidthD9Ej5fM6 = navigationRailVerticalItemTokens6.m1958getActiveIndicatorWidthD9Ej5fM();
                            TextStyle value12 = TypographyKt.getValue(navigationRailVerticalItemTokens6.getLabelTextFont(), composerStartRestartGroup, 6);
                            NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens6 = NavigationRailHorizontalItemTokens.INSTANCE;
                            int i25 = i16 << 3;
                            composer2 = composerStartRestartGroup;
                            NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value11, fM1958getActiveIndicatorWidthD9Ej5fM6, value12, TypographyKt.getValue(navigationRailHorizontalItemTokens6.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens6.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens6.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens6.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors8, modifier9, z13, function5, i114, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i25) | (i25 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            mutableInteractionSource2 = mutableInteractionSource3;
                            navigationItemColors2 = navigationItemColors8;
                            modifier3 = modifier9;
                            z7 = z13;
                            i14 = i114;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier3 = modifier2;
                            z7 = z5;
                            i14 = iM1335iconPositionFors8pcRp0;
                            navigationItemColors2 = navigationItemColorsColors;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                                public final Object invoke(Object obj, Object obj2) {
                                    return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i4 |= 805306368;
                    i13 = i4;
                    if ((i4 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        Modifier modifier10 = modifier2;
                        boolean z14 = z5;
                        int i115 = iM1335iconPositionFors8pcRp0;
                        NavigationItemColors navigationItemColors9 = navigationItemColorsColors;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1539072909);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(227446500);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        Shape value13 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        NavigationRailVerticalItemTokens navigationRailVerticalItemTokens7 = NavigationRailVerticalItemTokens.INSTANCE;
                        float fM1958getActiveIndicatorWidthD9Ej5fM7 = navigationRailVerticalItemTokens7.m1958getActiveIndicatorWidthD9Ej5fM();
                        TextStyle value14 = TypographyKt.getValue(navigationRailVerticalItemTokens7.getLabelTextFont(), composerStartRestartGroup, 6);
                        NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens7 = NavigationRailHorizontalItemTokens.INSTANCE;
                        int i26 = i16 << 3;
                        composer2 = composerStartRestartGroup;
                        NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value13, fM1958getActiveIndicatorWidthD9Ej5fM7, value14, TypographyKt.getValue(navigationRailHorizontalItemTokens7.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens7.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens7.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens7.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors9, modifier10, z14, function5, i115, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i26) | (i26 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource2 = mutableInteractionSource3;
                        navigationItemColors2 = navigationItemColors9;
                        modifier3 = modifier10;
                        z7 = z14;
                        i14 = i115;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        z7 = z5;
                        i14 = iM1335iconPositionFors8pcRp0;
                        navigationItemColors2 = navigationItemColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                            public final Object invoke(Object obj, Object obj2) {
                                return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 1572864;
                z5 = z3;
                if ((12582912 & i2) == 0) {
                    if ((i3 & 128) == 0) {
                        iM1335iconPositionFors8pcRp0 = i;
                        if (composerStartRestartGroup.changed(iM1335iconPositionFors8pcRp0)) {
                        }
                        i4 |= i17;
                    } else {
                        iM1335iconPositionFors8pcRp0 = i;
                    }
                    i4 |= i17;
                } else {
                    iM1335iconPositionFors8pcRp0 = i;
                }
                if ((100663296 & i2) == 0) {
                    if ((i3 & 256) == 0) {
                        navigationItemColorsColors = navigationItemColors;
                        if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                        }
                        i4 |= i18;
                    } else {
                        navigationItemColorsColors = navigationItemColors;
                    }
                    i4 |= i18;
                } else {
                    navigationItemColorsColors = navigationItemColors;
                }
                i11 = i3 & 512;
                if (i11 != 0) {
                    if ((i2 & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i12 = 536870912;
                        } else {
                            i12 = 268435456;
                        }
                        i4 |= i12;
                    }
                    i13 = i4;
                    if ((i4 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        Modifier modifier11 = modifier2;
                        boolean z15 = z5;
                        int i116 = iM1335iconPositionFors8pcRp0;
                        NavigationItemColors navigationItemColors10 = navigationItemColorsColors;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1539072909);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(227446500);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        Shape value15 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        NavigationRailVerticalItemTokens navigationRailVerticalItemTokens8 = NavigationRailVerticalItemTokens.INSTANCE;
                        float fM1958getActiveIndicatorWidthD9Ej5fM8 = navigationRailVerticalItemTokens8.m1958getActiveIndicatorWidthD9Ej5fM();
                        TextStyle value16 = TypographyKt.getValue(navigationRailVerticalItemTokens8.getLabelTextFont(), composerStartRestartGroup, 6);
                        NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens8 = NavigationRailHorizontalItemTokens.INSTANCE;
                        int i27 = i16 << 3;
                        composer2 = composerStartRestartGroup;
                        NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value15, fM1958getActiveIndicatorWidthD9Ej5fM8, value16, TypographyKt.getValue(navigationRailHorizontalItemTokens8.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens8.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens8.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens8.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors10, modifier11, z15, function5, i116, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i27) | (i27 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource2 = mutableInteractionSource3;
                        navigationItemColors2 = navigationItemColors10;
                        modifier3 = modifier11;
                        z7 = z15;
                        i14 = i116;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        z7 = z5;
                        i14 = iM1335iconPositionFors8pcRp0;
                        navigationItemColors2 = navigationItemColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                            public final Object invoke(Object obj, Object obj2) {
                                return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                i13 = i4;
                if ((i4 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    Modifier modifier12 = modifier2;
                    boolean z16 = z5;
                    int i117 = iM1335iconPositionFors8pcRp0;
                    NavigationItemColors navigationItemColors11 = navigationItemColorsColors;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1539072909);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(227446500);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    Shape value17 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                    NavigationRailVerticalItemTokens navigationRailVerticalItemTokens9 = NavigationRailVerticalItemTokens.INSTANCE;
                    float fM1958getActiveIndicatorWidthD9Ej5fM9 = navigationRailVerticalItemTokens9.m1958getActiveIndicatorWidthD9Ej5fM();
                    TextStyle value18 = TypographyKt.getValue(navigationRailVerticalItemTokens9.getLabelTextFont(), composerStartRestartGroup, 6);
                    NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens9 = NavigationRailHorizontalItemTokens.INSTANCE;
                    int i28 = i16 << 3;
                    composer2 = composerStartRestartGroup;
                    NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value17, fM1958getActiveIndicatorWidthD9Ej5fM9, value18, TypographyKt.getValue(navigationRailHorizontalItemTokens9.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens9.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens9.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens9.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors11, modifier12, z16, function5, i117, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i28) | (i28 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource2 = mutableInteractionSource3;
                    navigationItemColors2 = navigationItemColors11;
                    modifier3 = modifier12;
                    z7 = z16;
                    i14 = i117;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    z7 = z5;
                    i14 = iM1335iconPositionFors8pcRp0;
                    navigationItemColors2 = navigationItemColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                        public final Object invoke(Object obj, Object obj2) {
                            return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 3072;
            function5 = function3;
            if ((i3 & 16) != 0) {
                i4 |= 24576;
            } else if ((i2 & 24576) == 0) {
                if (composerStartRestartGroup.changed(z2)) {
                    i6 = 16384;
                } else {
                    i6 = 8192;
                }
                i4 |= i6;
            }
            i7 = i3 & 32;
            if (i7 != 0) {
                if ((196608 & i2) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i8 = 131072;
                    } else {
                        i8 = 65536;
                    }
                    i4 |= i8;
                }
                i9 = i3 & 64;
                if (i9 != 0) {
                    if ((1572864 & i2) == 0) {
                        z5 = z3;
                        if (composerStartRestartGroup.changed(z5)) {
                            i10 = 1048576;
                        } else {
                            i10 = 524288;
                        }
                        i4 |= i10;
                    }
                    if ((12582912 & i2) == 0) {
                        if ((i3 & 128) == 0) {
                            iM1335iconPositionFors8pcRp0 = i;
                            if (composerStartRestartGroup.changed(iM1335iconPositionFors8pcRp0)) {
                            }
                            i4 |= i17;
                        } else {
                            iM1335iconPositionFors8pcRp0 = i;
                        }
                        i4 |= i17;
                    } else {
                        iM1335iconPositionFors8pcRp0 = i;
                    }
                    if ((100663296 & i2) == 0) {
                        if ((i3 & 256) == 0) {
                            navigationItemColorsColors = navigationItemColors;
                            if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                            }
                            i4 |= i18;
                        } else {
                            navigationItemColorsColors = navigationItemColors;
                        }
                        i4 |= i18;
                    } else {
                        navigationItemColorsColors = navigationItemColors;
                    }
                    i11 = i3 & 512;
                    if (i11 != 0) {
                        if ((i2 & 805306368) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i12 = 536870912;
                            } else {
                                i12 = 268435456;
                            }
                            i4 |= i12;
                        }
                        i13 = i4;
                        if ((i4 & 306783379) != 306783378) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i2 & 1) != 0) {
                                if (i7 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i9 != 0) {
                                    z5 = true;
                                }
                                if ((i3 & 128) != 0) {
                                    i15 = i13 & (-29360129);
                                    iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                                } else {
                                    i15 = i13;
                                }
                                if ((i3 & 256) != 0) {
                                    i15 &= -234881025;
                                    navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                }
                                i16 = i15;
                                if (i11 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            } else {
                                if (i7 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i9 != 0) {
                                    z5 = true;
                                }
                                if ((i3 & 128) != 0) {
                                    i15 = i13 & (-29360129);
                                    iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                                } else {
                                    i15 = i13;
                                }
                                if ((i3 & 256) != 0) {
                                    i15 &= -234881025;
                                    navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                }
                                i16 = i15;
                                if (i11 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            }
                            Modifier modifier13 = modifier2;
                            boolean z17 = z5;
                            int i118 = iM1335iconPositionFors8pcRp0;
                            NavigationItemColors navigationItemColors12 = navigationItemColorsColors;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                            }
                            if (mutableInteractionSource3 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1539072909);
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(227446500);
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = mutableInteractionSource3;
                            }
                            Shape value19 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                            NavigationRailVerticalItemTokens navigationRailVerticalItemTokens10 = NavigationRailVerticalItemTokens.INSTANCE;
                            float fM1958getActiveIndicatorWidthD9Ej5fM10 = navigationRailVerticalItemTokens10.m1958getActiveIndicatorWidthD9Ej5fM();
                            TextStyle value110 = TypographyKt.getValue(navigationRailVerticalItemTokens10.getLabelTextFont(), composerStartRestartGroup, 6);
                            NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens10 = NavigationRailHorizontalItemTokens.INSTANCE;
                            int i29 = i16 << 3;
                            composer2 = composerStartRestartGroup;
                            NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value19, fM1958getActiveIndicatorWidthD9Ej5fM10, value110, TypographyKt.getValue(navigationRailHorizontalItemTokens10.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens10.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens10.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens10.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors12, modifier13, z17, function5, i118, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i29) | (i29 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            mutableInteractionSource2 = mutableInteractionSource3;
                            navigationItemColors2 = navigationItemColors12;
                            modifier3 = modifier13;
                            z7 = z17;
                            i14 = i118;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier3 = modifier2;
                            z7 = z5;
                            i14 = iM1335iconPositionFors8pcRp0;
                            navigationItemColors2 = navigationItemColorsColors;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                                public final Object invoke(Object obj, Object obj2) {
                                    return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i4 |= 805306368;
                    i13 = i4;
                    if ((i4 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        Modifier modifier14 = modifier2;
                        boolean z18 = z5;
                        int i119 = iM1335iconPositionFors8pcRp0;
                        NavigationItemColors navigationItemColors13 = navigationItemColorsColors;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1539072909);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(227446500);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        Shape value111 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        NavigationRailVerticalItemTokens navigationRailVerticalItemTokens11 = NavigationRailVerticalItemTokens.INSTANCE;
                        float fM1958getActiveIndicatorWidthD9Ej5fM11 = navigationRailVerticalItemTokens11.m1958getActiveIndicatorWidthD9Ej5fM();
                        TextStyle value112 = TypographyKt.getValue(navigationRailVerticalItemTokens11.getLabelTextFont(), composerStartRestartGroup, 6);
                        NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens11 = NavigationRailHorizontalItemTokens.INSTANCE;
                        int i210 = i16 << 3;
                        composer2 = composerStartRestartGroup;
                        NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value111, fM1958getActiveIndicatorWidthD9Ej5fM11, value112, TypographyKt.getValue(navigationRailHorizontalItemTokens11.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens11.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens11.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens11.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors13, modifier14, z18, function5, i119, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i210) | (i210 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource2 = mutableInteractionSource3;
                        navigationItemColors2 = navigationItemColors13;
                        modifier3 = modifier14;
                        z7 = z18;
                        i14 = i119;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        z7 = z5;
                        i14 = iM1335iconPositionFors8pcRp0;
                        navigationItemColors2 = navigationItemColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                            public final Object invoke(Object obj, Object obj2) {
                                return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 1572864;
                z5 = z3;
                if ((12582912 & i2) == 0) {
                    if ((i3 & 128) == 0) {
                        iM1335iconPositionFors8pcRp0 = i;
                        if (composerStartRestartGroup.changed(iM1335iconPositionFors8pcRp0)) {
                        }
                        i4 |= i17;
                    } else {
                        iM1335iconPositionFors8pcRp0 = i;
                    }
                    i4 |= i17;
                } else {
                    iM1335iconPositionFors8pcRp0 = i;
                }
                if ((100663296 & i2) == 0) {
                    if ((i3 & 256) == 0) {
                        navigationItemColorsColors = navigationItemColors;
                        if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                        }
                        i4 |= i18;
                    } else {
                        navigationItemColorsColors = navigationItemColors;
                    }
                    i4 |= i18;
                } else {
                    navigationItemColorsColors = navigationItemColors;
                }
                i11 = i3 & 512;
                if (i11 != 0) {
                    if ((i2 & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i12 = 536870912;
                        } else {
                            i12 = 268435456;
                        }
                        i4 |= i12;
                    }
                    i13 = i4;
                    if ((i4 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        Modifier modifier15 = modifier2;
                        boolean z19 = z5;
                        int i1110 = iM1335iconPositionFors8pcRp0;
                        NavigationItemColors navigationItemColors14 = navigationItemColorsColors;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1539072909);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(227446500);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        Shape value113 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        NavigationRailVerticalItemTokens navigationRailVerticalItemTokens12 = NavigationRailVerticalItemTokens.INSTANCE;
                        float fM1958getActiveIndicatorWidthD9Ej5fM12 = navigationRailVerticalItemTokens12.m1958getActiveIndicatorWidthD9Ej5fM();
                        TextStyle value114 = TypographyKt.getValue(navigationRailVerticalItemTokens12.getLabelTextFont(), composerStartRestartGroup, 6);
                        NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens12 = NavigationRailHorizontalItemTokens.INSTANCE;
                        int i211 = i16 << 3;
                        composer2 = composerStartRestartGroup;
                        NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value113, fM1958getActiveIndicatorWidthD9Ej5fM12, value114, TypographyKt.getValue(navigationRailHorizontalItemTokens12.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens12.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens12.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens12.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors14, modifier15, z19, function5, i1110, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i211) | (i211 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource2 = mutableInteractionSource3;
                        navigationItemColors2 = navigationItemColors14;
                        modifier3 = modifier15;
                        z7 = z19;
                        i14 = i1110;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        z7 = z5;
                        i14 = iM1335iconPositionFors8pcRp0;
                        navigationItemColors2 = navigationItemColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                            public final Object invoke(Object obj, Object obj2) {
                                return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                i13 = i4;
                if ((i4 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    Modifier modifier16 = modifier2;
                    boolean z110 = z5;
                    int i1111 = iM1335iconPositionFors8pcRp0;
                    NavigationItemColors navigationItemColors15 = navigationItemColorsColors;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1539072909);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(227446500);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    Shape value115 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                    NavigationRailVerticalItemTokens navigationRailVerticalItemTokens13 = NavigationRailVerticalItemTokens.INSTANCE;
                    float fM1958getActiveIndicatorWidthD9Ej5fM13 = navigationRailVerticalItemTokens13.m1958getActiveIndicatorWidthD9Ej5fM();
                    TextStyle value116 = TypographyKt.getValue(navigationRailVerticalItemTokens13.getLabelTextFont(), composerStartRestartGroup, 6);
                    NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens13 = NavigationRailHorizontalItemTokens.INSTANCE;
                    int i212 = i16 << 3;
                    composer2 = composerStartRestartGroup;
                    NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value115, fM1958getActiveIndicatorWidthD9Ej5fM13, value116, TypographyKt.getValue(navigationRailHorizontalItemTokens13.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens13.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens13.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens13.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors15, modifier16, z110, function5, i1111, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i212) | (i212 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource2 = mutableInteractionSource3;
                    navigationItemColors2 = navigationItemColors15;
                    modifier3 = modifier16;
                    z7 = z110;
                    i14 = i1111;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    z7 = z5;
                    i14 = iM1335iconPositionFors8pcRp0;
                    navigationItemColors2 = navigationItemColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                        public final Object invoke(Object obj, Object obj2) {
                            return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            modifier2 = modifier;
            i9 = i3 & 64;
            if (i9 != 0) {
                if ((1572864 & i2) == 0) {
                    z5 = z3;
                    if (composerStartRestartGroup.changed(z5)) {
                        i10 = 1048576;
                    } else {
                        i10 = 524288;
                    }
                    i4 |= i10;
                }
                if ((12582912 & i2) == 0) {
                    if ((i3 & 128) == 0) {
                        iM1335iconPositionFors8pcRp0 = i;
                        if (composerStartRestartGroup.changed(iM1335iconPositionFors8pcRp0)) {
                        }
                        i4 |= i17;
                    } else {
                        iM1335iconPositionFors8pcRp0 = i;
                    }
                    i4 |= i17;
                } else {
                    iM1335iconPositionFors8pcRp0 = i;
                }
                if ((100663296 & i2) == 0) {
                    if ((i3 & 256) == 0) {
                        navigationItemColorsColors = navigationItemColors;
                        if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                        }
                        i4 |= i18;
                    } else {
                        navigationItemColorsColors = navigationItemColors;
                    }
                    i4 |= i18;
                } else {
                    navigationItemColorsColors = navigationItemColors;
                }
                i11 = i3 & 512;
                if (i11 != 0) {
                    if ((i2 & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i12 = 536870912;
                        } else {
                            i12 = 268435456;
                        }
                        i4 |= i12;
                    }
                    i13 = i4;
                    if ((i4 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        Modifier modifier17 = modifier2;
                        boolean z111 = z5;
                        int i1112 = iM1335iconPositionFors8pcRp0;
                        NavigationItemColors navigationItemColors16 = navigationItemColorsColors;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1539072909);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(227446500);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        Shape value117 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        NavigationRailVerticalItemTokens navigationRailVerticalItemTokens14 = NavigationRailVerticalItemTokens.INSTANCE;
                        float fM1958getActiveIndicatorWidthD9Ej5fM14 = navigationRailVerticalItemTokens14.m1958getActiveIndicatorWidthD9Ej5fM();
                        TextStyle value118 = TypographyKt.getValue(navigationRailVerticalItemTokens14.getLabelTextFont(), composerStartRestartGroup, 6);
                        NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens14 = NavigationRailHorizontalItemTokens.INSTANCE;
                        int i213 = i16 << 3;
                        composer2 = composerStartRestartGroup;
                        NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value117, fM1958getActiveIndicatorWidthD9Ej5fM14, value118, TypographyKt.getValue(navigationRailHorizontalItemTokens14.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens14.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens14.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens14.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors16, modifier17, z111, function5, i1112, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i213) | (i213 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource2 = mutableInteractionSource3;
                        navigationItemColors2 = navigationItemColors16;
                        modifier3 = modifier17;
                        z7 = z111;
                        i14 = i1112;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        z7 = z5;
                        i14 = iM1335iconPositionFors8pcRp0;
                        navigationItemColors2 = navigationItemColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                            public final Object invoke(Object obj, Object obj2) {
                                return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                i13 = i4;
                if ((i4 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    Modifier modifier18 = modifier2;
                    boolean z112 = z5;
                    int i1113 = iM1335iconPositionFors8pcRp0;
                    NavigationItemColors navigationItemColors17 = navigationItemColorsColors;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1539072909);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(227446500);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    Shape value119 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                    NavigationRailVerticalItemTokens navigationRailVerticalItemTokens15 = NavigationRailVerticalItemTokens.INSTANCE;
                    float fM1958getActiveIndicatorWidthD9Ej5fM15 = navigationRailVerticalItemTokens15.m1958getActiveIndicatorWidthD9Ej5fM();
                    TextStyle value1110 = TypographyKt.getValue(navigationRailVerticalItemTokens15.getLabelTextFont(), composerStartRestartGroup, 6);
                    NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens15 = NavigationRailHorizontalItemTokens.INSTANCE;
                    int i214 = i16 << 3;
                    composer2 = composerStartRestartGroup;
                    NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value119, fM1958getActiveIndicatorWidthD9Ej5fM15, value1110, TypographyKt.getValue(navigationRailHorizontalItemTokens15.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens15.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens15.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens15.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors17, modifier18, z112, function5, i1113, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i214) | (i214 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource2 = mutableInteractionSource3;
                    navigationItemColors2 = navigationItemColors17;
                    modifier3 = modifier18;
                    z7 = z112;
                    i14 = i1113;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    z7 = z5;
                    i14 = iM1335iconPositionFors8pcRp0;
                    navigationItemColors2 = navigationItemColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                        public final Object invoke(Object obj, Object obj2) {
                            return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 1572864;
            z5 = z3;
            if ((12582912 & i2) == 0) {
                if ((i3 & 128) == 0) {
                    iM1335iconPositionFors8pcRp0 = i;
                    if (composerStartRestartGroup.changed(iM1335iconPositionFors8pcRp0)) {
                    }
                    i4 |= i17;
                } else {
                    iM1335iconPositionFors8pcRp0 = i;
                }
                i4 |= i17;
            } else {
                iM1335iconPositionFors8pcRp0 = i;
            }
            if ((100663296 & i2) == 0) {
                if ((i3 & 256) == 0) {
                    navigationItemColorsColors = navigationItemColors;
                    if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                    }
                    i4 |= i18;
                } else {
                    navigationItemColorsColors = navigationItemColors;
                }
                i4 |= i18;
            } else {
                navigationItemColorsColors = navigationItemColors;
            }
            i11 = i3 & 512;
            if (i11 != 0) {
                if ((i2 & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i12 = 536870912;
                    } else {
                        i12 = 268435456;
                    }
                    i4 |= i12;
                }
                i13 = i4;
                if ((i4 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    Modifier modifier19 = modifier2;
                    boolean z113 = z5;
                    int i1114 = iM1335iconPositionFors8pcRp0;
                    NavigationItemColors navigationItemColors18 = navigationItemColorsColors;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1539072909);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(227446500);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    Shape value1111 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                    NavigationRailVerticalItemTokens navigationRailVerticalItemTokens16 = NavigationRailVerticalItemTokens.INSTANCE;
                    float fM1958getActiveIndicatorWidthD9Ej5fM16 = navigationRailVerticalItemTokens16.m1958getActiveIndicatorWidthD9Ej5fM();
                    TextStyle value1112 = TypographyKt.getValue(navigationRailVerticalItemTokens16.getLabelTextFont(), composerStartRestartGroup, 6);
                    NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens16 = NavigationRailHorizontalItemTokens.INSTANCE;
                    int i215 = i16 << 3;
                    composer2 = composerStartRestartGroup;
                    NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value1111, fM1958getActiveIndicatorWidthD9Ej5fM16, value1112, TypographyKt.getValue(navigationRailHorizontalItemTokens16.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens16.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens16.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens16.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors18, modifier19, z113, function5, i1114, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i215) | (i215 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource2 = mutableInteractionSource3;
                    navigationItemColors2 = navigationItemColors18;
                    modifier3 = modifier19;
                    z7 = z113;
                    i14 = i1114;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    z7 = z5;
                    i14 = iM1335iconPositionFors8pcRp0;
                    navigationItemColors2 = navigationItemColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                        public final Object invoke(Object obj, Object obj2) {
                            return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 805306368;
            i13 = i4;
            if ((i4 & 306783379) != 306783378) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i9 != 0) {
                        z5 = true;
                    }
                    if ((i3 & 128) != 0) {
                        i15 = i13 & (-29360129);
                        iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                    } else {
                        i15 = i13;
                    }
                    if ((i3 & 256) != 0) {
                        i15 &= -234881025;
                        navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i16 = i15;
                    if (i11 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i9 != 0) {
                        z5 = true;
                    }
                    if ((i3 & 128) != 0) {
                        i15 = i13 & (-29360129);
                        iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                    } else {
                        i15 = i13;
                    }
                    if ((i3 & 256) != 0) {
                        i15 &= -234881025;
                        navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i16 = i15;
                    if (i11 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                Modifier modifier110 = modifier2;
                boolean z114 = z5;
                int i1115 = iM1335iconPositionFors8pcRp0;
                NavigationItemColors navigationItemColors19 = navigationItemColorsColors;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                }
                if (mutableInteractionSource3 == null) {
                    composerStartRestartGroup.startReplaceGroup(-1539072909);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                } else {
                    composerStartRestartGroup.startReplaceGroup(227446500);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                Shape value1113 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                NavigationRailVerticalItemTokens navigationRailVerticalItemTokens17 = NavigationRailVerticalItemTokens.INSTANCE;
                float fM1958getActiveIndicatorWidthD9Ej5fM17 = navigationRailVerticalItemTokens17.m1958getActiveIndicatorWidthD9Ej5fM();
                TextStyle value1114 = TypographyKt.getValue(navigationRailVerticalItemTokens17.getLabelTextFont(), composerStartRestartGroup, 6);
                NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens17 = NavigationRailHorizontalItemTokens.INSTANCE;
                int i216 = i16 << 3;
                composer2 = composerStartRestartGroup;
                NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value1113, fM1958getActiveIndicatorWidthD9Ej5fM17, value1114, TypographyKt.getValue(navigationRailHorizontalItemTokens17.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens17.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens17.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens17.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors19, modifier110, z114, function5, i1115, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i216) | (i216 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource2 = mutableInteractionSource3;
                navigationItemColors2 = navigationItemColors19;
                modifier3 = modifier110;
                z7 = z114;
                i14 = i1115;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                z7 = z5;
                i14 = iM1335iconPositionFors8pcRp0;
                navigationItemColors2 = navigationItemColorsColors;
                mutableInteractionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                    public final Object invoke(Object obj, Object obj2) {
                        return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        function4 = function2;
        if ((i3 & 8) != 0) {
            if ((i2 & 3072) == 0) {
                function5 = function3;
                if (composerStartRestartGroup.changedInstance(function5)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i4 |= i5;
            }
            if ((i3 & 16) != 0) {
                i4 |= 24576;
            } else if ((i2 & 24576) == 0) {
                if (composerStartRestartGroup.changed(z2)) {
                    i6 = 16384;
                } else {
                    i6 = 8192;
                }
                i4 |= i6;
            }
            i7 = i3 & 32;
            if (i7 != 0) {
                if ((196608 & i2) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i8 = 131072;
                    } else {
                        i8 = 65536;
                    }
                    i4 |= i8;
                }
                i9 = i3 & 64;
                if (i9 != 0) {
                    if ((1572864 & i2) == 0) {
                        z5 = z3;
                        if (composerStartRestartGroup.changed(z5)) {
                            i10 = 1048576;
                        } else {
                            i10 = 524288;
                        }
                        i4 |= i10;
                    }
                    if ((12582912 & i2) == 0) {
                        if ((i3 & 128) == 0) {
                            iM1335iconPositionFors8pcRp0 = i;
                            if (composerStartRestartGroup.changed(iM1335iconPositionFors8pcRp0)) {
                            }
                            i4 |= i17;
                        } else {
                            iM1335iconPositionFors8pcRp0 = i;
                        }
                        i4 |= i17;
                    } else {
                        iM1335iconPositionFors8pcRp0 = i;
                    }
                    if ((100663296 & i2) == 0) {
                        if ((i3 & 256) == 0) {
                            navigationItemColorsColors = navigationItemColors;
                            if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                            }
                            i4 |= i18;
                        } else {
                            navigationItemColorsColors = navigationItemColors;
                        }
                        i4 |= i18;
                    } else {
                        navigationItemColorsColors = navigationItemColors;
                    }
                    i11 = i3 & 512;
                    if (i11 != 0) {
                        if ((i2 & 805306368) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i12 = 536870912;
                            } else {
                                i12 = 268435456;
                            }
                            i4 |= i12;
                        }
                        i13 = i4;
                        if ((i4 & 306783379) != 306783378) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i2 & 1) != 0) {
                                if (i7 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i9 != 0) {
                                    z5 = true;
                                }
                                if ((i3 & 128) != 0) {
                                    i15 = i13 & (-29360129);
                                    iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                                } else {
                                    i15 = i13;
                                }
                                if ((i3 & 256) != 0) {
                                    i15 &= -234881025;
                                    navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                }
                                i16 = i15;
                                if (i11 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            } else {
                                if (i7 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i9 != 0) {
                                    z5 = true;
                                }
                                if ((i3 & 128) != 0) {
                                    i15 = i13 & (-29360129);
                                    iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                                } else {
                                    i15 = i13;
                                }
                                if ((i3 & 256) != 0) {
                                    i15 &= -234881025;
                                    navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                }
                                i16 = i15;
                                if (i11 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            }
                            Modifier modifier111 = modifier2;
                            boolean z115 = z5;
                            int i1116 = iM1335iconPositionFors8pcRp0;
                            NavigationItemColors navigationItemColors110 = navigationItemColorsColors;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                            }
                            if (mutableInteractionSource3 == null) {
                                composerStartRestartGroup.startReplaceGroup(-1539072909);
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(227446500);
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource4 = mutableInteractionSource3;
                            }
                            Shape value1115 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                            NavigationRailVerticalItemTokens navigationRailVerticalItemTokens18 = NavigationRailVerticalItemTokens.INSTANCE;
                            float fM1958getActiveIndicatorWidthD9Ej5fM18 = navigationRailVerticalItemTokens18.m1958getActiveIndicatorWidthD9Ej5fM();
                            TextStyle value1116 = TypographyKt.getValue(navigationRailVerticalItemTokens18.getLabelTextFont(), composerStartRestartGroup, 6);
                            NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens18 = NavigationRailHorizontalItemTokens.INSTANCE;
                            int i217 = i16 << 3;
                            composer2 = composerStartRestartGroup;
                            NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value1115, fM1958getActiveIndicatorWidthD9Ej5fM18, value1116, TypographyKt.getValue(navigationRailHorizontalItemTokens18.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens18.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens18.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens18.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors110, modifier111, z115, function5, i1116, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i217) | (i217 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            mutableInteractionSource2 = mutableInteractionSource3;
                            navigationItemColors2 = navigationItemColors110;
                            modifier3 = modifier111;
                            z7 = z115;
                            i14 = i1116;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier3 = modifier2;
                            z7 = z5;
                            i14 = iM1335iconPositionFors8pcRp0;
                            navigationItemColors2 = navigationItemColorsColors;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                                public final Object invoke(Object obj, Object obj2) {
                                    return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i4 |= 805306368;
                    i13 = i4;
                    if ((i4 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        Modifier modifier112 = modifier2;
                        boolean z116 = z5;
                        int i1117 = iM1335iconPositionFors8pcRp0;
                        NavigationItemColors navigationItemColors111 = navigationItemColorsColors;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1539072909);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(227446500);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        Shape value1117 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        NavigationRailVerticalItemTokens navigationRailVerticalItemTokens19 = NavigationRailVerticalItemTokens.INSTANCE;
                        float fM1958getActiveIndicatorWidthD9Ej5fM19 = navigationRailVerticalItemTokens19.m1958getActiveIndicatorWidthD9Ej5fM();
                        TextStyle value1118 = TypographyKt.getValue(navigationRailVerticalItemTokens19.getLabelTextFont(), composerStartRestartGroup, 6);
                        NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens19 = NavigationRailHorizontalItemTokens.INSTANCE;
                        int i218 = i16 << 3;
                        composer2 = composerStartRestartGroup;
                        NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value1117, fM1958getActiveIndicatorWidthD9Ej5fM19, value1118, TypographyKt.getValue(navigationRailHorizontalItemTokens19.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens19.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens19.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens19.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors111, modifier112, z116, function5, i1117, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i218) | (i218 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource2 = mutableInteractionSource3;
                        navigationItemColors2 = navigationItemColors111;
                        modifier3 = modifier112;
                        z7 = z116;
                        i14 = i1117;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        z7 = z5;
                        i14 = iM1335iconPositionFors8pcRp0;
                        navigationItemColors2 = navigationItemColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                            public final Object invoke(Object obj, Object obj2) {
                                return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 1572864;
                z5 = z3;
                if ((12582912 & i2) == 0) {
                    if ((i3 & 128) == 0) {
                        iM1335iconPositionFors8pcRp0 = i;
                        if (composerStartRestartGroup.changed(iM1335iconPositionFors8pcRp0)) {
                        }
                        i4 |= i17;
                    } else {
                        iM1335iconPositionFors8pcRp0 = i;
                    }
                    i4 |= i17;
                } else {
                    iM1335iconPositionFors8pcRp0 = i;
                }
                if ((100663296 & i2) == 0) {
                    if ((i3 & 256) == 0) {
                        navigationItemColorsColors = navigationItemColors;
                        if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                        }
                        i4 |= i18;
                    } else {
                        navigationItemColorsColors = navigationItemColors;
                    }
                    i4 |= i18;
                } else {
                    navigationItemColorsColors = navigationItemColors;
                }
                i11 = i3 & 512;
                if (i11 != 0) {
                    if ((i2 & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i12 = 536870912;
                        } else {
                            i12 = 268435456;
                        }
                        i4 |= i12;
                    }
                    i13 = i4;
                    if ((i4 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        Modifier modifier113 = modifier2;
                        boolean z117 = z5;
                        int i1118 = iM1335iconPositionFors8pcRp0;
                        NavigationItemColors navigationItemColors112 = navigationItemColorsColors;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1539072909);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(227446500);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        Shape value1119 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        NavigationRailVerticalItemTokens navigationRailVerticalItemTokens110 = NavigationRailVerticalItemTokens.INSTANCE;
                        float fM1958getActiveIndicatorWidthD9Ej5fM110 = navigationRailVerticalItemTokens110.m1958getActiveIndicatorWidthD9Ej5fM();
                        TextStyle value11110 = TypographyKt.getValue(navigationRailVerticalItemTokens110.getLabelTextFont(), composerStartRestartGroup, 6);
                        NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens110 = NavigationRailHorizontalItemTokens.INSTANCE;
                        int i219 = i16 << 3;
                        composer2 = composerStartRestartGroup;
                        NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value1119, fM1958getActiveIndicatorWidthD9Ej5fM110, value11110, TypographyKt.getValue(navigationRailHorizontalItemTokens110.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens110.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens110.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens110.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors112, modifier113, z117, function5, i1118, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i219) | (i219 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource2 = mutableInteractionSource3;
                        navigationItemColors2 = navigationItemColors112;
                        modifier3 = modifier113;
                        z7 = z117;
                        i14 = i1118;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        z7 = z5;
                        i14 = iM1335iconPositionFors8pcRp0;
                        navigationItemColors2 = navigationItemColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                            public final Object invoke(Object obj, Object obj2) {
                                return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                i13 = i4;
                if ((i4 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    Modifier modifier114 = modifier2;
                    boolean z118 = z5;
                    int i1119 = iM1335iconPositionFors8pcRp0;
                    NavigationItemColors navigationItemColors113 = navigationItemColorsColors;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1539072909);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(227446500);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    Shape value11111 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                    NavigationRailVerticalItemTokens navigationRailVerticalItemTokens111 = NavigationRailVerticalItemTokens.INSTANCE;
                    float fM1958getActiveIndicatorWidthD9Ej5fM111 = navigationRailVerticalItemTokens111.m1958getActiveIndicatorWidthD9Ej5fM();
                    TextStyle value11112 = TypographyKt.getValue(navigationRailVerticalItemTokens111.getLabelTextFont(), composerStartRestartGroup, 6);
                    NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens111 = NavigationRailHorizontalItemTokens.INSTANCE;
                    int i2110 = i16 << 3;
                    composer2 = composerStartRestartGroup;
                    NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value11111, fM1958getActiveIndicatorWidthD9Ej5fM111, value11112, TypographyKt.getValue(navigationRailHorizontalItemTokens111.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens111.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens111.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens111.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors113, modifier114, z118, function5, i1119, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i2110) | (i2110 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource2 = mutableInteractionSource3;
                    navigationItemColors2 = navigationItemColors113;
                    modifier3 = modifier114;
                    z7 = z118;
                    i14 = i1119;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    z7 = z5;
                    i14 = iM1335iconPositionFors8pcRp0;
                    navigationItemColors2 = navigationItemColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                        public final Object invoke(Object obj, Object obj2) {
                            return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            modifier2 = modifier;
            i9 = i3 & 64;
            if (i9 != 0) {
                if ((1572864 & i2) == 0) {
                    z5 = z3;
                    if (composerStartRestartGroup.changed(z5)) {
                        i10 = 1048576;
                    } else {
                        i10 = 524288;
                    }
                    i4 |= i10;
                }
                if ((12582912 & i2) == 0) {
                    if ((i3 & 128) == 0) {
                        iM1335iconPositionFors8pcRp0 = i;
                        if (composerStartRestartGroup.changed(iM1335iconPositionFors8pcRp0)) {
                        }
                        i4 |= i17;
                    } else {
                        iM1335iconPositionFors8pcRp0 = i;
                    }
                    i4 |= i17;
                } else {
                    iM1335iconPositionFors8pcRp0 = i;
                }
                if ((100663296 & i2) == 0) {
                    if ((i3 & 256) == 0) {
                        navigationItemColorsColors = navigationItemColors;
                        if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                        }
                        i4 |= i18;
                    } else {
                        navigationItemColorsColors = navigationItemColors;
                    }
                    i4 |= i18;
                } else {
                    navigationItemColorsColors = navigationItemColors;
                }
                i11 = i3 & 512;
                if (i11 != 0) {
                    if ((i2 & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i12 = 536870912;
                        } else {
                            i12 = 268435456;
                        }
                        i4 |= i12;
                    }
                    i13 = i4;
                    if ((i4 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        Modifier modifier115 = modifier2;
                        boolean z119 = z5;
                        int i11110 = iM1335iconPositionFors8pcRp0;
                        NavigationItemColors navigationItemColors114 = navigationItemColorsColors;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1539072909);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(227446500);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        Shape value11113 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        NavigationRailVerticalItemTokens navigationRailVerticalItemTokens112 = NavigationRailVerticalItemTokens.INSTANCE;
                        float fM1958getActiveIndicatorWidthD9Ej5fM112 = navigationRailVerticalItemTokens112.m1958getActiveIndicatorWidthD9Ej5fM();
                        TextStyle value11114 = TypographyKt.getValue(navigationRailVerticalItemTokens112.getLabelTextFont(), composerStartRestartGroup, 6);
                        NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens112 = NavigationRailHorizontalItemTokens.INSTANCE;
                        int i2111 = i16 << 3;
                        composer2 = composerStartRestartGroup;
                        NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value11113, fM1958getActiveIndicatorWidthD9Ej5fM112, value11114, TypographyKt.getValue(navigationRailHorizontalItemTokens112.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens112.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens112.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens112.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors114, modifier115, z119, function5, i11110, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i2111) | (i2111 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource2 = mutableInteractionSource3;
                        navigationItemColors2 = navigationItemColors114;
                        modifier3 = modifier115;
                        z7 = z119;
                        i14 = i11110;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        z7 = z5;
                        i14 = iM1335iconPositionFors8pcRp0;
                        navigationItemColors2 = navigationItemColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                            public final Object invoke(Object obj, Object obj2) {
                                return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                i13 = i4;
                if ((i4 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    Modifier modifier116 = modifier2;
                    boolean z1110 = z5;
                    int i11111 = iM1335iconPositionFors8pcRp0;
                    NavigationItemColors navigationItemColors115 = navigationItemColorsColors;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1539072909);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(227446500);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    Shape value11115 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                    NavigationRailVerticalItemTokens navigationRailVerticalItemTokens113 = NavigationRailVerticalItemTokens.INSTANCE;
                    float fM1958getActiveIndicatorWidthD9Ej5fM113 = navigationRailVerticalItemTokens113.m1958getActiveIndicatorWidthD9Ej5fM();
                    TextStyle value11116 = TypographyKt.getValue(navigationRailVerticalItemTokens113.getLabelTextFont(), composerStartRestartGroup, 6);
                    NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens113 = NavigationRailHorizontalItemTokens.INSTANCE;
                    int i2112 = i16 << 3;
                    composer2 = composerStartRestartGroup;
                    NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value11115, fM1958getActiveIndicatorWidthD9Ej5fM113, value11116, TypographyKt.getValue(navigationRailHorizontalItemTokens113.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens113.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens113.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens113.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors115, modifier116, z1110, function5, i11111, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i2112) | (i2112 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource2 = mutableInteractionSource3;
                    navigationItemColors2 = navigationItemColors115;
                    modifier3 = modifier116;
                    z7 = z1110;
                    i14 = i11111;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    z7 = z5;
                    i14 = iM1335iconPositionFors8pcRp0;
                    navigationItemColors2 = navigationItemColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                        public final Object invoke(Object obj, Object obj2) {
                            return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 1572864;
            z5 = z3;
            if ((12582912 & i2) == 0) {
                if ((i3 & 128) == 0) {
                    iM1335iconPositionFors8pcRp0 = i;
                    if (composerStartRestartGroup.changed(iM1335iconPositionFors8pcRp0)) {
                    }
                    i4 |= i17;
                } else {
                    iM1335iconPositionFors8pcRp0 = i;
                }
                i4 |= i17;
            } else {
                iM1335iconPositionFors8pcRp0 = i;
            }
            if ((100663296 & i2) == 0) {
                if ((i3 & 256) == 0) {
                    navigationItemColorsColors = navigationItemColors;
                    if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                    }
                    i4 |= i18;
                } else {
                    navigationItemColorsColors = navigationItemColors;
                }
                i4 |= i18;
            } else {
                navigationItemColorsColors = navigationItemColors;
            }
            i11 = i3 & 512;
            if (i11 != 0) {
                if ((i2 & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i12 = 536870912;
                    } else {
                        i12 = 268435456;
                    }
                    i4 |= i12;
                }
                i13 = i4;
                if ((i4 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    Modifier modifier117 = modifier2;
                    boolean z1111 = z5;
                    int i11112 = iM1335iconPositionFors8pcRp0;
                    NavigationItemColors navigationItemColors116 = navigationItemColorsColors;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1539072909);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(227446500);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    Shape value11117 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                    NavigationRailVerticalItemTokens navigationRailVerticalItemTokens114 = NavigationRailVerticalItemTokens.INSTANCE;
                    float fM1958getActiveIndicatorWidthD9Ej5fM114 = navigationRailVerticalItemTokens114.m1958getActiveIndicatorWidthD9Ej5fM();
                    TextStyle value11118 = TypographyKt.getValue(navigationRailVerticalItemTokens114.getLabelTextFont(), composerStartRestartGroup, 6);
                    NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens114 = NavigationRailHorizontalItemTokens.INSTANCE;
                    int i2113 = i16 << 3;
                    composer2 = composerStartRestartGroup;
                    NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value11117, fM1958getActiveIndicatorWidthD9Ej5fM114, value11118, TypographyKt.getValue(navigationRailHorizontalItemTokens114.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens114.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens114.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens114.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors116, modifier117, z1111, function5, i11112, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i2113) | (i2113 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource2 = mutableInteractionSource3;
                    navigationItemColors2 = navigationItemColors116;
                    modifier3 = modifier117;
                    z7 = z1111;
                    i14 = i11112;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    z7 = z5;
                    i14 = iM1335iconPositionFors8pcRp0;
                    navigationItemColors2 = navigationItemColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                        public final Object invoke(Object obj, Object obj2) {
                            return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 805306368;
            i13 = i4;
            if ((i4 & 306783379) != 306783378) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i9 != 0) {
                        z5 = true;
                    }
                    if ((i3 & 128) != 0) {
                        i15 = i13 & (-29360129);
                        iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                    } else {
                        i15 = i13;
                    }
                    if ((i3 & 256) != 0) {
                        i15 &= -234881025;
                        navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i16 = i15;
                    if (i11 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i9 != 0) {
                        z5 = true;
                    }
                    if ((i3 & 128) != 0) {
                        i15 = i13 & (-29360129);
                        iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                    } else {
                        i15 = i13;
                    }
                    if ((i3 & 256) != 0) {
                        i15 &= -234881025;
                        navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i16 = i15;
                    if (i11 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                Modifier modifier118 = modifier2;
                boolean z1112 = z5;
                int i11113 = iM1335iconPositionFors8pcRp0;
                NavigationItemColors navigationItemColors117 = navigationItemColorsColors;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                }
                if (mutableInteractionSource3 == null) {
                    composerStartRestartGroup.startReplaceGroup(-1539072909);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                } else {
                    composerStartRestartGroup.startReplaceGroup(227446500);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                Shape value11119 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                NavigationRailVerticalItemTokens navigationRailVerticalItemTokens115 = NavigationRailVerticalItemTokens.INSTANCE;
                float fM1958getActiveIndicatorWidthD9Ej5fM115 = navigationRailVerticalItemTokens115.m1958getActiveIndicatorWidthD9Ej5fM();
                TextStyle value111110 = TypographyKt.getValue(navigationRailVerticalItemTokens115.getLabelTextFont(), composerStartRestartGroup, 6);
                NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens115 = NavigationRailHorizontalItemTokens.INSTANCE;
                int i2114 = i16 << 3;
                composer2 = composerStartRestartGroup;
                NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value11119, fM1958getActiveIndicatorWidthD9Ej5fM115, value111110, TypographyKt.getValue(navigationRailHorizontalItemTokens115.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens115.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens115.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens115.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors117, modifier118, z1112, function5, i11113, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i2114) | (i2114 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource2 = mutableInteractionSource3;
                navigationItemColors2 = navigationItemColors117;
                modifier3 = modifier118;
                z7 = z1112;
                i14 = i11113;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                z7 = z5;
                i14 = iM1335iconPositionFors8pcRp0;
                navigationItemColors2 = navigationItemColorsColors;
                mutableInteractionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                    public final Object invoke(Object obj, Object obj2) {
                        return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 3072;
        function5 = function3;
        if ((i3 & 16) != 0) {
            i4 |= 24576;
        } else if ((i2 & 24576) == 0) {
            if (composerStartRestartGroup.changed(z2)) {
                i6 = 16384;
            } else {
                i6 = 8192;
            }
            i4 |= i6;
        }
        i7 = i3 & 32;
        if (i7 != 0) {
            if ((196608 & i2) == 0) {
                modifier2 = modifier;
                if (composerStartRestartGroup.changed(modifier2)) {
                    i8 = 131072;
                } else {
                    i8 = 65536;
                }
                i4 |= i8;
            }
            i9 = i3 & 64;
            if (i9 != 0) {
                if ((1572864 & i2) == 0) {
                    z5 = z3;
                    if (composerStartRestartGroup.changed(z5)) {
                        i10 = 1048576;
                    } else {
                        i10 = 524288;
                    }
                    i4 |= i10;
                }
                if ((12582912 & i2) == 0) {
                    if ((i3 & 128) == 0) {
                        iM1335iconPositionFors8pcRp0 = i;
                        if (composerStartRestartGroup.changed(iM1335iconPositionFors8pcRp0)) {
                        }
                        i4 |= i17;
                    } else {
                        iM1335iconPositionFors8pcRp0 = i;
                    }
                    i4 |= i17;
                } else {
                    iM1335iconPositionFors8pcRp0 = i;
                }
                if ((100663296 & i2) == 0) {
                    if ((i3 & 256) == 0) {
                        navigationItemColorsColors = navigationItemColors;
                        if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                        }
                        i4 |= i18;
                    } else {
                        navigationItemColorsColors = navigationItemColors;
                    }
                    i4 |= i18;
                } else {
                    navigationItemColorsColors = navigationItemColors;
                }
                i11 = i3 & 512;
                if (i11 != 0) {
                    if ((i2 & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i12 = 536870912;
                        } else {
                            i12 = 268435456;
                        }
                        i4 |= i12;
                    }
                    i13 = i4;
                    if ((i4 & 306783379) != 306783378) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i7 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i9 != 0) {
                                z5 = true;
                            }
                            if ((i3 & 128) != 0) {
                                i15 = i13 & (-29360129);
                                iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                            } else {
                                i15 = i13;
                            }
                            if ((i3 & 256) != 0) {
                                i15 &= -234881025;
                                navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            i16 = i15;
                            if (i11 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        Modifier modifier119 = modifier2;
                        boolean z1113 = z5;
                        int i11114 = iM1335iconPositionFors8pcRp0;
                        NavigationItemColors navigationItemColors118 = navigationItemColorsColors;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                        }
                        if (mutableInteractionSource3 == null) {
                            composerStartRestartGroup.startReplaceGroup(-1539072909);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(227446500);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource4 = mutableInteractionSource3;
                        }
                        Shape value111111 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                        NavigationRailVerticalItemTokens navigationRailVerticalItemTokens116 = NavigationRailVerticalItemTokens.INSTANCE;
                        float fM1958getActiveIndicatorWidthD9Ej5fM116 = navigationRailVerticalItemTokens116.m1958getActiveIndicatorWidthD9Ej5fM();
                        TextStyle value111112 = TypographyKt.getValue(navigationRailVerticalItemTokens116.getLabelTextFont(), composerStartRestartGroup, 6);
                        NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens116 = NavigationRailHorizontalItemTokens.INSTANCE;
                        int i2115 = i16 << 3;
                        composer2 = composerStartRestartGroup;
                        NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value111111, fM1958getActiveIndicatorWidthD9Ej5fM116, value111112, TypographyKt.getValue(navigationRailHorizontalItemTokens116.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens116.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens116.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens116.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors118, modifier119, z1113, function5, i11114, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i2115) | (i2115 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        mutableInteractionSource2 = mutableInteractionSource3;
                        navigationItemColors2 = navigationItemColors118;
                        modifier3 = modifier119;
                        z7 = z1113;
                        i14 = i11114;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        z7 = z5;
                        i14 = iM1335iconPositionFors8pcRp0;
                        navigationItemColors2 = navigationItemColorsColors;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                            public final Object invoke(Object obj, Object obj2) {
                                return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                i13 = i4;
                if ((i4 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    Modifier modifier1110 = modifier2;
                    boolean z1114 = z5;
                    int i11115 = iM1335iconPositionFors8pcRp0;
                    NavigationItemColors navigationItemColors119 = navigationItemColorsColors;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1539072909);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(227446500);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    Shape value111113 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                    NavigationRailVerticalItemTokens navigationRailVerticalItemTokens117 = NavigationRailVerticalItemTokens.INSTANCE;
                    float fM1958getActiveIndicatorWidthD9Ej5fM117 = navigationRailVerticalItemTokens117.m1958getActiveIndicatorWidthD9Ej5fM();
                    TextStyle value111114 = TypographyKt.getValue(navigationRailVerticalItemTokens117.getLabelTextFont(), composerStartRestartGroup, 6);
                    NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens117 = NavigationRailHorizontalItemTokens.INSTANCE;
                    int i2116 = i16 << 3;
                    composer2 = composerStartRestartGroup;
                    NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value111113, fM1958getActiveIndicatorWidthD9Ej5fM117, value111114, TypographyKt.getValue(navigationRailHorizontalItemTokens117.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens117.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens117.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens117.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors119, modifier1110, z1114, function5, i11115, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i2116) | (i2116 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource2 = mutableInteractionSource3;
                    navigationItemColors2 = navigationItemColors119;
                    modifier3 = modifier1110;
                    z7 = z1114;
                    i14 = i11115;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    z7 = z5;
                    i14 = iM1335iconPositionFors8pcRp0;
                    navigationItemColors2 = navigationItemColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                        public final Object invoke(Object obj, Object obj2) {
                            return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 1572864;
            z5 = z3;
            if ((12582912 & i2) == 0) {
                if ((i3 & 128) == 0) {
                    iM1335iconPositionFors8pcRp0 = i;
                    if (composerStartRestartGroup.changed(iM1335iconPositionFors8pcRp0)) {
                    }
                    i4 |= i17;
                } else {
                    iM1335iconPositionFors8pcRp0 = i;
                }
                i4 |= i17;
            } else {
                iM1335iconPositionFors8pcRp0 = i;
            }
            if ((100663296 & i2) == 0) {
                if ((i3 & 256) == 0) {
                    navigationItemColorsColors = navigationItemColors;
                    if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                    }
                    i4 |= i18;
                } else {
                    navigationItemColorsColors = navigationItemColors;
                }
                i4 |= i18;
            } else {
                navigationItemColorsColors = navigationItemColors;
            }
            i11 = i3 & 512;
            if (i11 != 0) {
                if ((i2 & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i12 = 536870912;
                    } else {
                        i12 = 268435456;
                    }
                    i4 |= i12;
                }
                i13 = i4;
                if ((i4 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    Modifier modifier1111 = modifier2;
                    boolean z1115 = z5;
                    int i11116 = iM1335iconPositionFors8pcRp0;
                    NavigationItemColors navigationItemColors1110 = navigationItemColorsColors;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1539072909);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(227446500);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    Shape value111115 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                    NavigationRailVerticalItemTokens navigationRailVerticalItemTokens118 = NavigationRailVerticalItemTokens.INSTANCE;
                    float fM1958getActiveIndicatorWidthD9Ej5fM118 = navigationRailVerticalItemTokens118.m1958getActiveIndicatorWidthD9Ej5fM();
                    TextStyle value111116 = TypographyKt.getValue(navigationRailVerticalItemTokens118.getLabelTextFont(), composerStartRestartGroup, 6);
                    NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens118 = NavigationRailHorizontalItemTokens.INSTANCE;
                    int i2117 = i16 << 3;
                    composer2 = composerStartRestartGroup;
                    NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value111115, fM1958getActiveIndicatorWidthD9Ej5fM118, value111116, TypographyKt.getValue(navigationRailHorizontalItemTokens118.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens118.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens118.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens118.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors1110, modifier1111, z1115, function5, i11116, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i2117) | (i2117 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource2 = mutableInteractionSource3;
                    navigationItemColors2 = navigationItemColors1110;
                    modifier3 = modifier1111;
                    z7 = z1115;
                    i14 = i11116;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    z7 = z5;
                    i14 = iM1335iconPositionFors8pcRp0;
                    navigationItemColors2 = navigationItemColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                        public final Object invoke(Object obj, Object obj2) {
                            return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 805306368;
            i13 = i4;
            if ((i4 & 306783379) != 306783378) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i9 != 0) {
                        z5 = true;
                    }
                    if ((i3 & 128) != 0) {
                        i15 = i13 & (-29360129);
                        iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                    } else {
                        i15 = i13;
                    }
                    if ((i3 & 256) != 0) {
                        i15 &= -234881025;
                        navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i16 = i15;
                    if (i11 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i9 != 0) {
                        z5 = true;
                    }
                    if ((i3 & 128) != 0) {
                        i15 = i13 & (-29360129);
                        iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                    } else {
                        i15 = i13;
                    }
                    if ((i3 & 256) != 0) {
                        i15 &= -234881025;
                        navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i16 = i15;
                    if (i11 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                Modifier modifier1112 = modifier2;
                boolean z1116 = z5;
                int i11117 = iM1335iconPositionFors8pcRp0;
                NavigationItemColors navigationItemColors1111 = navigationItemColorsColors;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                }
                if (mutableInteractionSource3 == null) {
                    composerStartRestartGroup.startReplaceGroup(-1539072909);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                } else {
                    composerStartRestartGroup.startReplaceGroup(227446500);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                Shape value111117 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                NavigationRailVerticalItemTokens navigationRailVerticalItemTokens119 = NavigationRailVerticalItemTokens.INSTANCE;
                float fM1958getActiveIndicatorWidthD9Ej5fM119 = navigationRailVerticalItemTokens119.m1958getActiveIndicatorWidthD9Ej5fM();
                TextStyle value111118 = TypographyKt.getValue(navigationRailVerticalItemTokens119.getLabelTextFont(), composerStartRestartGroup, 6);
                NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens119 = NavigationRailHorizontalItemTokens.INSTANCE;
                int i2118 = i16 << 3;
                composer2 = composerStartRestartGroup;
                NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value111117, fM1958getActiveIndicatorWidthD9Ej5fM119, value111118, TypographyKt.getValue(navigationRailHorizontalItemTokens119.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens119.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens119.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens119.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors1111, modifier1112, z1116, function5, i11117, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i2118) | (i2118 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource2 = mutableInteractionSource3;
                navigationItemColors2 = navigationItemColors1111;
                modifier3 = modifier1112;
                z7 = z1116;
                i14 = i11117;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                z7 = z5;
                i14 = iM1335iconPositionFors8pcRp0;
                navigationItemColors2 = navigationItemColorsColors;
                mutableInteractionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                    public final Object invoke(Object obj, Object obj2) {
                        return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        modifier2 = modifier;
        i9 = i3 & 64;
        if (i9 != 0) {
            if ((1572864 & i2) == 0) {
                z5 = z3;
                if (composerStartRestartGroup.changed(z5)) {
                    i10 = 1048576;
                } else {
                    i10 = 524288;
                }
                i4 |= i10;
            }
            if ((12582912 & i2) == 0) {
                if ((i3 & 128) == 0) {
                    iM1335iconPositionFors8pcRp0 = i;
                    if (composerStartRestartGroup.changed(iM1335iconPositionFors8pcRp0)) {
                    }
                    i4 |= i17;
                } else {
                    iM1335iconPositionFors8pcRp0 = i;
                }
                i4 |= i17;
            } else {
                iM1335iconPositionFors8pcRp0 = i;
            }
            if ((100663296 & i2) == 0) {
                if ((i3 & 256) == 0) {
                    navigationItemColorsColors = navigationItemColors;
                    if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                    }
                    i4 |= i18;
                } else {
                    navigationItemColorsColors = navigationItemColors;
                }
                i4 |= i18;
            } else {
                navigationItemColorsColors = navigationItemColors;
            }
            i11 = i3 & 512;
            if (i11 != 0) {
                if ((i2 & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i12 = 536870912;
                    } else {
                        i12 = 268435456;
                    }
                    i4 |= i12;
                }
                i13 = i4;
                if ((i4 & 306783379) != 306783378) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i7 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i9 != 0) {
                            z5 = true;
                        }
                        if ((i3 & 128) != 0) {
                            i15 = i13 & (-29360129);
                            iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                        } else {
                            i15 = i13;
                        }
                        if ((i3 & 256) != 0) {
                            i15 &= -234881025;
                            navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        i16 = i15;
                        if (i11 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    Modifier modifier1113 = modifier2;
                    boolean z1117 = z5;
                    int i11118 = iM1335iconPositionFors8pcRp0;
                    NavigationItemColors navigationItemColors1112 = navigationItemColorsColors;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                    }
                    if (mutableInteractionSource3 == null) {
                        composerStartRestartGroup.startReplaceGroup(-1539072909);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(227446500);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource4 = mutableInteractionSource3;
                    }
                    Shape value111119 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                    NavigationRailVerticalItemTokens navigationRailVerticalItemTokens1110 = NavigationRailVerticalItemTokens.INSTANCE;
                    float fM1958getActiveIndicatorWidthD9Ej5fM1110 = navigationRailVerticalItemTokens1110.m1958getActiveIndicatorWidthD9Ej5fM();
                    TextStyle value1111110 = TypographyKt.getValue(navigationRailVerticalItemTokens1110.getLabelTextFont(), composerStartRestartGroup, 6);
                    NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens1110 = NavigationRailHorizontalItemTokens.INSTANCE;
                    int i2119 = i16 << 3;
                    composer2 = composerStartRestartGroup;
                    NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value111119, fM1958getActiveIndicatorWidthD9Ej5fM1110, value1111110, TypographyKt.getValue(navigationRailHorizontalItemTokens1110.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens1110.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens1110.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens1110.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors1112, modifier1113, z1117, function5, i11118, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i2119) | (i2119 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    mutableInteractionSource2 = mutableInteractionSource3;
                    navigationItemColors2 = navigationItemColors1112;
                    modifier3 = modifier1113;
                    z7 = z1117;
                    i14 = i11118;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    z7 = z5;
                    i14 = iM1335iconPositionFors8pcRp0;
                    navigationItemColors2 = navigationItemColorsColors;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                        public final Object invoke(Object obj, Object obj2) {
                            return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 805306368;
            i13 = i4;
            if ((i4 & 306783379) != 306783378) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i9 != 0) {
                        z5 = true;
                    }
                    if ((i3 & 128) != 0) {
                        i15 = i13 & (-29360129);
                        iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                    } else {
                        i15 = i13;
                    }
                    if ((i3 & 256) != 0) {
                        i15 &= -234881025;
                        navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i16 = i15;
                    if (i11 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i9 != 0) {
                        z5 = true;
                    }
                    if ((i3 & 128) != 0) {
                        i15 = i13 & (-29360129);
                        iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                    } else {
                        i15 = i13;
                    }
                    if ((i3 & 256) != 0) {
                        i15 &= -234881025;
                        navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i16 = i15;
                    if (i11 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                Modifier modifier1114 = modifier2;
                boolean z1118 = z5;
                int i11119 = iM1335iconPositionFors8pcRp0;
                NavigationItemColors navigationItemColors1113 = navigationItemColorsColors;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                }
                if (mutableInteractionSource3 == null) {
                    composerStartRestartGroup.startReplaceGroup(-1539072909);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                } else {
                    composerStartRestartGroup.startReplaceGroup(227446500);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                Shape value1111111 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                NavigationRailVerticalItemTokens navigationRailVerticalItemTokens1111 = NavigationRailVerticalItemTokens.INSTANCE;
                float fM1958getActiveIndicatorWidthD9Ej5fM1111 = navigationRailVerticalItemTokens1111.m1958getActiveIndicatorWidthD9Ej5fM();
                TextStyle value1111112 = TypographyKt.getValue(navigationRailVerticalItemTokens1111.getLabelTextFont(), composerStartRestartGroup, 6);
                NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens1111 = NavigationRailHorizontalItemTokens.INSTANCE;
                int i21110 = i16 << 3;
                composer2 = composerStartRestartGroup;
                NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value1111111, fM1958getActiveIndicatorWidthD9Ej5fM1111, value1111112, TypographyKt.getValue(navigationRailHorizontalItemTokens1111.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens1111.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens1111.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens1111.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors1113, modifier1114, z1118, function5, i11119, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i21110) | (i21110 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource2 = mutableInteractionSource3;
                navigationItemColors2 = navigationItemColors1113;
                modifier3 = modifier1114;
                z7 = z1118;
                i14 = i11119;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                z7 = z5;
                i14 = iM1335iconPositionFors8pcRp0;
                navigationItemColors2 = navigationItemColorsColors;
                mutableInteractionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                    public final Object invoke(Object obj, Object obj2) {
                        return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 1572864;
        z5 = z3;
        if ((12582912 & i2) == 0) {
            if ((i3 & 128) == 0) {
                iM1335iconPositionFors8pcRp0 = i;
                if (composerStartRestartGroup.changed(iM1335iconPositionFors8pcRp0)) {
                }
                i4 |= i17;
            } else {
                iM1335iconPositionFors8pcRp0 = i;
            }
            i4 |= i17;
        } else {
            iM1335iconPositionFors8pcRp0 = i;
        }
        if ((100663296 & i2) == 0) {
            if ((i3 & 256) == 0) {
                navigationItemColorsColors = navigationItemColors;
                if (composerStartRestartGroup.changed(navigationItemColorsColors)) {
                }
                i4 |= i18;
            } else {
                navigationItemColorsColors = navigationItemColors;
            }
            i4 |= i18;
        } else {
            navigationItemColorsColors = navigationItemColors;
        }
        i11 = i3 & 512;
        if (i11 != 0) {
            if ((i2 & 805306368) == 0) {
                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                    i12 = 536870912;
                } else {
                    i12 = 268435456;
                }
                i4 |= i12;
            }
            i13 = i4;
            if ((i4 & 306783379) != 306783378) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i9 != 0) {
                        z5 = true;
                    }
                    if ((i3 & 128) != 0) {
                        i15 = i13 & (-29360129);
                        iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                    } else {
                        i15 = i13;
                    }
                    if ((i3 & 256) != 0) {
                        i15 &= -234881025;
                        navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i16 = i15;
                    if (i11 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i7 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i9 != 0) {
                        z5 = true;
                    }
                    if ((i3 & 128) != 0) {
                        i15 = i13 & (-29360129);
                        iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                    } else {
                        i15 = i13;
                    }
                    if ((i3 & 256) != 0) {
                        i15 &= -234881025;
                        navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    i16 = i15;
                    if (i11 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                Modifier modifier1115 = modifier2;
                boolean z1119 = z5;
                int i111110 = iM1335iconPositionFors8pcRp0;
                NavigationItemColors navigationItemColors1114 = navigationItemColorsColors;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
                }
                if (mutableInteractionSource3 == null) {
                    composerStartRestartGroup.startReplaceGroup(-1539072909);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
                } else {
                    composerStartRestartGroup.startReplaceGroup(227446500);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource4 = mutableInteractionSource3;
                }
                Shape value1111113 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
                NavigationRailVerticalItemTokens navigationRailVerticalItemTokens1112 = NavigationRailVerticalItemTokens.INSTANCE;
                float fM1958getActiveIndicatorWidthD9Ej5fM1112 = navigationRailVerticalItemTokens1112.m1958getActiveIndicatorWidthD9Ej5fM();
                TextStyle value1111114 = TypographyKt.getValue(navigationRailVerticalItemTokens1112.getLabelTextFont(), composerStartRestartGroup, 6);
                NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens1112 = NavigationRailHorizontalItemTokens.INSTANCE;
                int i21111 = i16 << 3;
                composer2 = composerStartRestartGroup;
                NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value1111113, fM1958getActiveIndicatorWidthD9Ej5fM1112, value1111114, TypographyKt.getValue(navigationRailHorizontalItemTokens1112.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens1112.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens1112.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens1112.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors1114, modifier1115, z1119, function5, i111110, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i21111) | (i21111 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                mutableInteractionSource2 = mutableInteractionSource3;
                navigationItemColors2 = navigationItemColors1114;
                modifier3 = modifier1115;
                z7 = z1119;
                i14 = i111110;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                z7 = z5;
                i14 = iM1335iconPositionFors8pcRp0;
                navigationItemColors2 = navigationItemColorsColors;
                mutableInteractionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                    public final Object invoke(Object obj, Object obj2) {
                        return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 805306368;
        i13 = i4;
        if ((i4 & 306783379) != 306783378) {
            z6 = true;
        } else {
            z6 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z6, i13 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i7 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i9 != 0) {
                    z5 = true;
                }
                if ((i3 & 128) != 0) {
                    i15 = i13 & (-29360129);
                    iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                } else {
                    i15 = i13;
                }
                if ((i3 & 256) != 0) {
                    i15 &= -234881025;
                    navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                i16 = i15;
                if (i11 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
            } else {
                if (i7 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i9 != 0) {
                    z5 = true;
                }
                if ((i3 & 128) != 0) {
                    i15 = i13 & (-29360129);
                    iM1335iconPositionFors8pcRp0 = WideNavigationRailItemDefaults.INSTANCE.m1335iconPositionFors8pcRp0(z2);
                } else {
                    i15 = i13;
                }
                if ((i3 & 256) != 0) {
                    i15 &= -234881025;
                    navigationItemColorsColors = WideNavigationRailItemDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                i16 = i15;
                if (i11 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
            }
            Modifier modifier1116 = modifier2;
            boolean z11110 = z5;
            int i111111 = iM1335iconPositionFors8pcRp0;
            NavigationItemColors navigationItemColors1115 = navigationItemColorsColors;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1894733304, i16, -1, "androidx.compose.material3.WideNavigationRailItem (WideNavigationRail.kt:688)");
            }
            if (mutableInteractionSource3 == null) {
                composerStartRestartGroup.startReplaceGroup(-1539072909);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composerStartRestartGroup.endReplaceGroup();
                mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
            } else {
                composerStartRestartGroup.startReplaceGroup(227446500);
                composerStartRestartGroup.endReplaceGroup();
                mutableInteractionSource4 = mutableInteractionSource3;
            }
            Shape value1111115 = ShapesKt.getValue(NavigationRailBaselineItemTokens.INSTANCE.getActiveIndicatorShape(), composerStartRestartGroup, 6);
            NavigationRailVerticalItemTokens navigationRailVerticalItemTokens1113 = NavigationRailVerticalItemTokens.INSTANCE;
            float fM1958getActiveIndicatorWidthD9Ej5fM1113 = navigationRailVerticalItemTokens1113.m1958getActiveIndicatorWidthD9Ej5fM();
            TextStyle value1111116 = TypographyKt.getValue(navigationRailVerticalItemTokens1113.getLabelTextFont(), composerStartRestartGroup, 6);
            NavigationRailHorizontalItemTokens navigationRailHorizontalItemTokens1113 = NavigationRailHorizontalItemTokens.INSTANCE;
            int i21112 = i16 << 3;
            composer2 = composerStartRestartGroup;
            NavigationItemKt.m691AnimatedNavigationItemDQd_Gtc(z4, function1, function4, value1111115, fM1958getActiveIndicatorWidthD9Ej5fM1113, value1111116, TypographyKt.getValue(navigationRailHorizontalItemTokens1113.getLabelTextFont(), composerStartRestartGroup, 6), ItemTopIconIndicatorHorizontalPadding, ItemTopIconIndicatorVerticalPadding, navigationRailVerticalItemTokens1113.m1959getIconLabelSpaceD9Ej5fM(), navigationRailHorizontalItemTokens1113.m1953getFullWidthLeadingSpaceD9Ej5fM(), ItemStartIconIndicatorVerticalPadding, WNRItemNoLabelIndicatorPadding, navigationRailHorizontalItemTokens1113.m1955getIconLabelSpaceD9Ej5fM(), ItemHorizontalPadding, navigationItemColors1115, modifier1116, z11110, function5, i111111, mutableInteractionSource4, composer2, (i16 & 14) | 918577152 | (i16 & 112) | (i16 & 896), ((i16 >> 9) & 458752) | 28086 | (3670016 & i21112) | (i21112 & 29360128) | ((i16 << 15) & 234881024) | ((i16 << 6) & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            mutableInteractionSource2 = mutableInteractionSource3;
            navigationItemColors2 = navigationItemColors1115;
            modifier3 = modifier1116;
            z7 = z11110;
            i14 = i111111;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
            z7 = z5;
            i14 = iM1335iconPositionFors8pcRp0;
            navigationItemColors2 = navigationItemColorsColors;
            mutableInteractionSource2 = mutableInteractionSource;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: imf
                public final Object invoke(Object obj, Object obj2) {
                    return WideNavigationRailKt.n(z, function0, function2, function3, z2, modifier3, z7, i14, navigationItemColors2, mutableInteractionSource2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void WideNavigationRailLayout(final Modifier modifier, final boolean z, final boolean z2, final WideNavigationRailColors wideNavigationRailColors, final Shape shape, final Function2<? super Composer, ? super Integer, Unit> function2, final WindowInsets windowInsets, final Arrangement.Vertical vertical, final Function2<? super Composer, ? super Integer, Unit> function3, Composer composer, final int i) {
        int i2;
        WideNavigationRailColors wideNavigationRailColors2;
        float fM6036unboximpl;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1004308036);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changed(z2) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            wideNavigationRailColors2 = wideNavigationRailColors;
            i2 |= composerStartRestartGroup.changed(wideNavigationRailColors2) ? 2048 : 1024;
        } else {
            wideNavigationRailColors2 = wideNavigationRailColors;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changed(shape) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(windowInsets) ? 1048576 : 524288;
        }
        if ((i & 12582912) == 0) {
            i2 |= composerStartRestartGroup.changed(vertical) ? 8388608 : 4194304;
        }
        if ((100663296 & i) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (composerStartRestartGroup.shouldExecute((38347923 & i2) != 38347922, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1004308036, i2, -1, "androidx.compose.material3.WideNavigationRailLayout (WideNavigationRail.kt:218)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableIntState mutableIntState = (MutableIntState) objRememberedValue;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = SnapshotIntStateKt.mutableIntStateOf(0);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            MutableIntState mutableIntState2 = (MutableIntState) objRememberedValue2;
            if (Dp.m6027equalsimpl0(((Dp) composerStartRestartGroup.consume(InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize())).m6036unboximpl(), Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM())) {
                composerStartRestartGroup.startReplaceGroup(-597966102);
                composerStartRestartGroup.endReplaceGroup();
                fM6036unboximpl = Dp.m6022constructorimpl(0.0f);
            } else {
                composerStartRestartGroup.startReplaceGroup(-597931134);
                fM6036unboximpl = ((Dp) composerStartRestartGroup.consume(InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize())).m6036unboximpl();
                composerStartRestartGroup.endReplaceGroup();
            }
            float f = fM6036unboximpl;
            AnimationSpec animationSpecValue = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, composerStartRestartGroup, 6);
            AnimationSpec animationSpecValue2 = MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6);
            State state = AnimateAsStateKt.animateDpAsState-AjpBEmI(!z2 ? CollapsedRailWidth : ExpandedRailMinWidth, !z ? animationSpecValue : animationSpecValue2, (String) null, (Function1) null, composerStartRestartGroup, 0, 12);
            State state2 = AnimateAsStateKt.animateDpAsState-AjpBEmI(!z2 ? CollapsedRailWidth : ExpandedRailMaxWidth, !z ? animationSpecValue : animationSpecValue2, (String) null, (Function1) null, composerStartRestartGroup, 0, 12);
            State state3 = AnimateAsStateKt.animateDpAsState-AjpBEmI(!z2 ? NavigationRailCollapsedTokens.INSTANCE.m1944getItemVerticalSpaceD9Ej5fM() : Dp.m6022constructorimpl(0.0f), animationSpecValue, (String) null, (Function1) null, composerStartRestartGroup, 0, 12);
            int i3 = i2;
            SurfaceKt.m954SurfaceT9BRK9s(modifier, shape, !z ? wideNavigationRailColors2.getContainerColor() : wideNavigationRailColors2.getModalContainerColor(), !z ? wideNavigationRailColors2.getContentColor() : wideNavigationRailColors2.getModalContentColor(), 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(-1489314345, true, new AnonymousClass1(windowInsets, function2, z2, state, f, AnimateAsStateKt.animateDpAsState-AjpBEmI(!z2 ? TopIconItemMinHeight : f, animationSpecValue, (String) null, (Function1) null, composerStartRestartGroup, 0, 12), state2, mutableIntState2, mutableIntState, vertical, state3, function3), composerStartRestartGroup, 54), composerStartRestartGroup, (i3 & 14) | 12582912 | ((i3 >> 9) & 112), 112);
            composerStartRestartGroup = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: qmf
                public final Object invoke(Object obj, Object obj2) {
                    return WideNavigationRailKt.c(modifier, z, z2, wideNavigationRailColors, shape, function2, windowInsets, vertical, function3, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float WideNavigationRailLayout$lambda$10(State<Dp> state) {
        return state.getValue().m6036unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float WideNavigationRailLayout$lambda$11(State<Dp> state) {
        return state.getValue().m6036unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int WideNavigationRailLayout$lambda$3(MutableIntState mutableIntState) {
        return mutableIntState.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void WideNavigationRailLayout$lambda$4(MutableIntState mutableIntState, int i) {
        mutableIntState.setIntValue(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int WideNavigationRailLayout$lambda$6(MutableIntState mutableIntState) {
        return mutableIntState.getIntValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void WideNavigationRailLayout$lambda$7(MutableIntState mutableIntState, int i) {
        mutableIntState.setIntValue(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float WideNavigationRailLayout$lambda$8(State<Dp> state) {
        return state.getValue().m6036unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float WideNavigationRailLayout$lambda$9(State<Dp> state) {
        return state.getValue().m6036unboximpl();
    }

    public static Unit a(String str, final MutableState mutableState, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, str);
        SemanticsPropertiesKt.onClick$default(semanticsPropertyReceiver, null, new Function0() { // from class: rmf
            public final Object invoke() {
                return Boolean.valueOf(WideNavigationRailKt.l(mutableState));
            }
        }, 1, null);
        return Unit.INSTANCE;
    }

    public static ModalWideNavigationRailOverride b() {
        return DefaultModalWideNavigationRailOverride.INSTANCE;
    }

    public static Unit c(Modifier modifier, boolean z, boolean z2, WideNavigationRailColors wideNavigationRailColors, Shape shape, Function2 function2, WindowInsets windowInsets, Arrangement.Vertical vertical, Function2 function3, int i, Composer composer, int i2) {
        WideNavigationRailLayout(modifier, z, z2, wideNavigationRailColors, shape, function2, windowInsets, vertical, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculatePredictiveBackScaleX(GraphicsLayerScope graphicsLayerScope, float f, boolean z) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (graphicsLayerScope.getSize() >> 32));
        if (Float.isNaN(fIntBitsToFloat) || fIntBitsToFloat == 0.0f) {
            return 1.0f;
        }
        return (((z ? 1.0f : -1.0f) * MathHelpersKt.lerp(0.0f, Math.min(graphicsLayerScope.mo4557toPx0680j_4(PredictiveBackMaxScaleXDistance), fIntBitsToFloat), f)) / fIntBitsToFloat) + 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculatePredictiveBackScaleY(GraphicsLayerScope graphicsLayerScope, float f) {
        float fIntBitsToFloat = Float.intBitsToFloat((int) (graphicsLayerScope.getSize() & 4294967295L));
        if (Float.isNaN(fIntBitsToFloat) || fIntBitsToFloat == 0.0f) {
            return 1.0f;
        }
        return 1.0f - (MathHelpersKt.lerp(0.0f, Math.min(graphicsLayerScope.mo4557toPx0680j_4(PredictiveBackMaxScaleYDistance), fIntBitsToFloat), f) / fIntBitsToFloat);
    }

    public static Unit d(float f, float f2, DraggableAnchorsConfig draggableAnchorsConfig) {
        draggableAnchorsConfig.at(WideNavigationRailValue.Collapsed, f);
        draggableAnchorsConfig.at(WideNavigationRailValue.Expanded, f2);
        return Unit.INSTANCE;
    }

    public static Unit e(Modifier modifier, WideNavigationRailState wideNavigationRailState, boolean z, Shape shape, Shape shape2, WideNavigationRailColors wideNavigationRailColors, Function2 function2, float f, WindowInsets windowInsets, Arrangement.Vertical vertical, ModalWideNavigationRailProperties modalWideNavigationRailProperties, Function2 function3, int i, int i2, int i3, Composer composer, int i4) {
        m1336ModalWideNavigationRailk3FuEkE(modifier, wideNavigationRailState, z, shape, shape2, wideNavigationRailColors, function2, f, windowInsets, vertical, modalWideNavigationRailProperties, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    public static WideNavigationRailOverride f() {
        return DefaultWideNavigationRailOverride.INSTANCE;
    }

    public static Unit g(Modifier modifier, WideNavigationRailState wideNavigationRailState, Shape shape, WideNavigationRailColors wideNavigationRailColors, Function2 function2, WindowInsets windowInsets, Arrangement.Vertical vertical, Function2 function3, int i, int i2, Composer composer, int i3) {
        WideNavigationRail(modifier, wideNavigationRailState, shape, wideNavigationRailColors, function2, windowInsets, vertical, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final ProvidableCompositionLocal<ModalWideNavigationRailOverride> getLocalModalWideNavigationRailOverride() {
        return LocalModalWideNavigationRailOverride;
    }

    public static final ProvidableCompositionLocal<WideNavigationRailOverride> getLocalWideNavigationRailOverride() {
        return LocalWideNavigationRailOverride;
    }

    public static final float getWNRItemNoLabelIndicatorPadding() {
        return WNRItemNoLabelIndicatorPadding;
    }

    public static Unit h(String str, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.setPaneTitle(semanticsPropertyReceiver, str);
        return Unit.INSTANCE;
    }

    public static Unit i(Animatable animatable, ModalWideNavigationRailState modalWideNavigationRailState, RailPredictiveBackState railPredictiveBackState, boolean z, GraphicsLayerScope graphicsLayerScope) {
        float fFloatValue = ((Number) animatable.getValue()).floatValue();
        if (fFloatValue <= 0.0f) {
            return Unit.INSTANCE;
        }
        float currentOffset = modalWideNavigationRailState.getCurrentOffset();
        float fIntBitsToFloat = Float.intBitsToFloat((int) (graphicsLayerScope.getSize() >> 32));
        if (!Float.isNaN(currentOffset) && !Float.isNaN(fIntBitsToFloat) && fIntBitsToFloat != 0.0f) {
            graphicsLayerScope.setScaleX(calculatePredictiveBackScaleX(graphicsLayerScope, fFloatValue, railPredictiveBackState.getSwipeEdgeMatchesRail()));
            graphicsLayerScope.setScaleY(calculatePredictiveBackScaleY(graphicsLayerScope, fFloatValue));
            graphicsLayerScope.mo3335setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(z ? 1.0f : 0.0f, 0.5f));
        }
        return Unit.INSTANCE;
    }

    public static Pair j(boolean z, boolean z2, ModalWideNavigationRailState modalWideNavigationRailState, IntSize intSize, Constraints constraints) {
        final float fM6197unboximpl = (int) (intSize.m6197unboximpl() >> 32);
        final float f = 0.0f;
        if (!z) {
            fM6197unboximpl = 0.0f;
        } else if (!z2) {
            fM6197unboximpl = -fM6197unboximpl;
        }
        return TuplesKt.to(AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: omf
            public final Object invoke(Object obj) {
                return WideNavigationRailKt.d(fM6197unboximpl, f, (DraggableAnchorsConfig) obj);
            }
        }), modalWideNavigationRailState.getTargetValue());
    }

    public static Unit k(long j, State state, DrawScope drawScope) {
        DrawScope.m3702drawRectnJ9OG0$default(drawScope, j, 0L, 0L, RangesKt.coerceIn(Scrim_3J_VO9M$lambda$26(state), 0.0f, 1.0f), null, null, 0, 118, null);
        return Unit.INSTANCE;
    }

    public static boolean l(MutableState mutableState) {
        Scrim_3J_VO9M$lambda$29(mutableState, true);
        return true;
    }

    public static Unit m(long j, Function1 function1, boolean z, int i, Composer composer, int i2) {
        m1338Scrim3JVO9M(j, function1, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit n(boolean z, Function0 function0, Function2 function2, Function2 function3, boolean z2, Modifier modifier, boolean z3, int i, NavigationItemColors navigationItemColors, MutableInteractionSource mutableInteractionSource, int i2, int i3, Composer composer, int i4) {
        m1339WideNavigationRailItemplit6k(z, function0, function2, function3, z2, modifier, z3, i, navigationItemColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static Unit o(boolean z, boolean z2, Animatable animatable, RailPredictiveBackState railPredictiveBackState, Function2 function2, Modifier modifier, ModalWideNavigationRailState modalWideNavigationRailState, WideNavigationRailColors wideNavigationRailColors, Shape shape, float f, Function2 function3, WindowInsets windowInsets, boolean z3, Arrangement.Vertical vertical, Function2 function4, int i, int i2, Composer composer, int i3) {
        m1337ModalWideNavigationRailContentpU6N4AM(z, z2, animatable, railPredictiveBackState, function2, modifier, modalWideNavigationRailState, wideNavigationRailColors, shape, f, function3, windowInsets, z3, vertical, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.material3.WideNavigationRailKt$WideNavigationRailLayout$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class AnonymousClass1 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ MutableIntState $actualMaxExpandedWidth$delegate;
        final /* synthetic */ Arrangement.Vertical $arrangement;
        final /* synthetic */ Function2<Composer, Integer, Unit> $content;
        final /* synthetic */ MutableIntState $currentWidth$delegate;
        final /* synthetic */ boolean $expanded;
        final /* synthetic */ Function2<Composer, Integer, Unit> $header;
        final /* synthetic */ State<Dp> $itemMinHeight$delegate;
        final /* synthetic */ State<Dp> $itemVerticalSpacedBy$delegate;
        final /* synthetic */ State<Dp> $minWidth$delegate;
        final /* synthetic */ float $minimumA11ySize;
        final /* synthetic */ State<Dp> $widthFullRange$delegate;
        final /* synthetic */ WindowInsets $windowInsets;

        /* JADX INFO: renamed from: androidx.compose.material3.WideNavigationRailKt$WideNavigationRailLayout$1$2, reason: invalid class name */
        @Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J)\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"androidx/compose/material3/WideNavigationRailKt$WideNavigationRailLayout$1$2", "Landroidx/compose/ui/layout/MeasurePolicy;", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
        public static final class AnonymousClass2 implements MeasurePolicy {
            final /* synthetic */ MutableIntState $actualMaxExpandedWidth$delegate;
            final /* synthetic */ Arrangement.Vertical $arrangement;
            final /* synthetic */ MutableIntState $currentWidth$delegate;
            final /* synthetic */ boolean $expanded;
            final /* synthetic */ Function2<Composer, Integer, Unit> $header;
            final /* synthetic */ State<Dp> $itemMinHeight$delegate;
            final /* synthetic */ State<Dp> $itemVerticalSpacedBy$delegate;
            final /* synthetic */ State<Dp> $minWidth$delegate;
            final /* synthetic */ float $minimumA11ySize;
            final /* synthetic */ State<Dp> $widthFullRange$delegate;

            /* JADX WARN: Multi-variable type inference failed */
            public AnonymousClass2(Function2<? super Composer, ? super Integer, Unit> function2, boolean z, State<Dp> state, float f, State<Dp> state2, State<Dp> state3, MutableIntState mutableIntState, MutableIntState mutableIntState2, Arrangement.Vertical vertical, State<Dp> state4) {
                this.$header = function2;
                this.$expanded = z;
                this.$minWidth$delegate = state;
                this.$minimumA11ySize = f;
                this.$itemMinHeight$delegate = state2;
                this.$widthFullRange$delegate = state3;
                this.$actualMaxExpandedWidth$delegate = mutableIntState;
                this.$currentWidth$delegate = mutableIntState2;
                this.$arrangement = vertical;
                this.$itemVerticalSpacedBy$delegate = state4;
            }

            public static Unit a(int i, MeasureScope measureScope, Ref.ObjectRef objectRef, List list, Arrangement.Vertical vertical, State state, Placeable.PlacementScope placementScope) {
                int height;
                int iMo4551roundToPx0680j_4 = i - measureScope.mo4551roundToPx0680j_4(WideNavigationRailKt.WNRVerticalPadding);
                Object obj = objectRef.element;
                if (obj == null || ((Placeable) obj).getHeight() <= 0) {
                    height = 0;
                } else {
                    Placeable.PlacementScope.placeRelative$default(placementScope, (Placeable) objectRef.element, 0, 0, 0.0f, 4, null);
                    height = ((Placeable) objectRef.element).getHeight() + measureScope.mo4551roundToPx0680j_4(WideNavigationRailKt.WNRHeaderPadding);
                }
                if (list != null) {
                    if (!Intrinsics.areEqual(vertical, Arrangement.INSTANCE.getCenter())) {
                        iMo4551roundToPx0680j_4 -= height;
                    }
                    int[] iArr = new int[list.size()];
                    List list2 = list;
                    int size = list2.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        iArr[i2] = ((Placeable) list.get(i2)).getHeight();
                        if (i2 < list.size() - 1) {
                            iArr[i2] = iArr[i2] + measureScope.mo4551roundToPx0680j_4(WideNavigationRailKt.WideNavigationRailLayout$lambda$10(state));
                        }
                    }
                    int[] iArr2 = new int[list.size()];
                    vertical.arrange(measureScope, iMo4551roundToPx0680j_4, iArr, iArr2);
                    if (Intrinsics.areEqual(vertical, Arrangement.INSTANCE.getCenter())) {
                        height = 0;
                    }
                    int size2 = list2.size();
                    for (int i3 = 0; i3 < size2; i3++) {
                        Placeable.PlacementScope.placeRelative$default(placementScope, (Placeable) list.get(i3), 0, iArr2[i3] + height, 0.0f, 4, null);
                    }
                }
                return Unit.INSTANCE;
            }

            public static Unit b(Placeable.PlacementScope placementScope) {
                return Unit.INSTANCE;
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            /* JADX INFO: renamed from: measure-3p2s80s */
            public MeasureResult mo14measure3p2s80s(final MeasureScope measureScope, List<? extends Measurable> list, long j) {
                int iM5977getMinWidthimpl;
                ArrayList arrayList;
                int height;
                int iMo4551roundToPx0680j_4;
                List<? extends Measurable> listSubList = list;
                final int iM5974getMaxHeightimpl = Constraints.m5974getMaxHeightimpl(j);
                int size = listSubList.size();
                int iM5977getMinWidthimpl2 = Constraints.m5977getMinWidthimpl(j);
                if (Constraints.m5977getMinWidthimpl(j) == 0) {
                    iM5977getMinWidthimpl2 = RangesKt.coerceAtMost(measureScope.mo4551roundToPx0680j_4(WideNavigationRailKt.ExpandedRailMinWidth), Constraints.m5975getMaxWidthimpl(j));
                    iM5977getMinWidthimpl = RangesKt.coerceAtMost(measureScope.mo4551roundToPx0680j_4(WideNavigationRailKt.WideNavigationRailLayout$lambda$8(this.$minWidth$delegate)), Constraints.m5975getMaxWidthimpl(j));
                } else {
                    iM5977getMinWidthimpl = Constraints.m5977getMinWidthimpl(j);
                }
                if (size < 1) {
                    return MeasureScope.layout$default(measureScope, iM5977getMinWidthimpl, iM5974getMaxHeightimpl, null, new Function1() { // from class: xmf
                        public final Object invoke(Object obj) {
                            return WideNavigationRailKt.AnonymousClass1.AnonymousClass2.b((Placeable.PlacementScope) obj);
                        }
                    }, 4, null);
                }
                long jM5965copyZbe2FdA$default = Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                if (this.$header != null) {
                    int size2 = listSubList.size();
                    int i = 0;
                    while (true) {
                        if (i >= size2) {
                            ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                            wq6.a();
                            return null;
                        }
                        Measurable measurable = listSubList.get(i);
                        arrayList = null;
                        if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), WideNavigationRailKt.HeaderLayoutIdTag)) {
                            objectRef.element = measurable.mo4605measureBRTryo0(jM5965copyZbe2FdA$default);
                            if (size > 1) {
                                listSubList = listSubList.subList(1, size);
                            }
                            size--;
                            height = ((Placeable) objectRef.element).getHeight();
                            break;
                        }
                        i++;
                    }
                } else {
                    arrayList = null;
                    height = 0;
                }
                final ArrayList arrayList2 = size > 0 ? new ArrayList() : arrayList;
                int iM5975getMaxWidthimpl = this.$expanded ? Constraints.m5975getMaxWidthimpl(jM5965copyZbe2FdA$default) : iM5977getMinWidthimpl;
                if (arrayList2 != null) {
                    float f = this.$minimumA11ySize;
                    boolean z = this.$expanded;
                    State<Dp> state = this.$itemMinHeight$delegate;
                    int i2 = height;
                    ArrayList arrayList3 = new ArrayList(listSubList.size());
                    int size3 = listSubList.size();
                    int i3 = 0;
                    iMo4551roundToPx0680j_4 = 0;
                    int height2 = i2;
                    while (i3 < size3) {
                        List<? extends Measurable> list2 = listSubList;
                        int i4 = size3;
                        int i5 = i3;
                        boolean z2 = z;
                        float f2 = f;
                        State<Dp> state2 = state;
                        Placeable placeableMo4605measureBRTryo0 = listSubList.get(i3).mo4605measureBRTryo0(ConstraintsKt.m5990constrainN9IONVI(ConstraintsKt.m5995offsetNN6EwU$default(jM5965copyZbe2FdA$default, 0, -height2, 1, null), Constraints.INSTANCE.m5984fitPrioritizingWidthZbe2FdA(measureScope.mo4551roundToPx0680j_4(f), iM5975getMaxWidthimpl, measureScope.mo4551roundToPx0680j_4(WideNavigationRailKt.WideNavigationRailLayout$lambda$11(state)), Constraints.m5974getMaxHeightimpl(jM5965copyZbe2FdA$default))));
                        int measuredWidth = placeableMo4605measureBRTryo0.getMeasuredWidth();
                        if (z2 && iMo4551roundToPx0680j_4 < measuredWidth) {
                            iMo4551roundToPx0680j_4 = measuredWidth + measureScope.mo4551roundToPx0680j_4(WideNavigationRailKt.ItemHorizontalPadding);
                        }
                        height2 = placeableMo4605measureBRTryo0.getHeight();
                        arrayList3.add(Boolean.valueOf(arrayList2.add(placeableMo4605measureBRTryo0)));
                        i3 = i5 + 1;
                        listSubList = list2;
                        z = z2;
                        f = f2;
                        state = state2;
                        size3 = i4;
                    }
                } else {
                    iMo4551roundToPx0680j_4 = 0;
                }
                if (this.$expanded) {
                    Placeable placeable = (Placeable) objectRef.element;
                    int iMax = Math.max(iMo4551roundToPx0680j_4, placeable != null ? placeable.getWidth() : 0);
                    if (iMax > iM5977getMinWidthimpl && iMax > iM5977getMinWidthimpl2) {
                        iM5977getMinWidthimpl = RangesKt.coerceAtMost(measureScope.mo4551roundToPx0680j_4(WideNavigationRailKt.WideNavigationRailLayout$lambda$9(this.$widthFullRange$delegate)), RangesKt.coerceAtMost(Math.max(iMax, iM5977getMinWidthimpl2), Constraints.m5975getMaxWidthimpl(j)));
                        WideNavigationRailKt.WideNavigationRailLayout$lambda$7(this.$actualMaxExpandedWidth$delegate, iM5977getMinWidthimpl);
                    }
                } else if (WideNavigationRailKt.WideNavigationRailLayout$lambda$6(this.$actualMaxExpandedWidth$delegate) > 0) {
                    iM5977getMinWidthimpl = RangesKt.coerceIn(measureScope.mo4551roundToPx0680j_4(WideNavigationRailKt.WideNavigationRailLayout$lambda$9(this.$widthFullRange$delegate)), iM5977getMinWidthimpl, RangesKt.coerceAtLeast(WideNavigationRailKt.WideNavigationRailLayout$lambda$3(this.$currentWidth$delegate), iM5977getMinWidthimpl));
                }
                int i6 = iM5977getMinWidthimpl;
                WideNavigationRailKt.WideNavigationRailLayout$lambda$4(this.$currentWidth$delegate, i6);
                final Arrangement.Vertical vertical = this.$arrangement;
                final State<Dp> state3 = this.$itemVerticalSpacedBy$delegate;
                return MeasureScope.layout$default(measureScope, i6, iM5974getMaxHeightimpl, null, new Function1() { // from class: ymf
                    public final Object invoke(Object obj) {
                        return WideNavigationRailKt.AnonymousClass1.AnonymousClass2.a(iM5974getMaxHeightimpl, measureScope, objectRef, arrayList2, vertical, state3, (Placeable.PlacementScope) obj);
                    }
                }, 4, null);
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(WindowInsets windowInsets, Function2<? super Composer, ? super Integer, Unit> function2, boolean z, State<Dp> state, float f, State<Dp> state2, State<Dp> state3, MutableIntState mutableIntState, MutableIntState mutableIntState2, Arrangement.Vertical vertical, State<Dp> state4, Function2<? super Composer, ? super Integer, Unit> function3) {
            this.$windowInsets = windowInsets;
            this.$header = function2;
            this.$expanded = z;
            this.$minWidth$delegate = state;
            this.$minimumA11ySize = f;
            this.$itemMinHeight$delegate = state2;
            this.$widthFullRange$delegate = state3;
            this.$actualMaxExpandedWidth$delegate = mutableIntState;
            this.$currentWidth$delegate = mutableIntState2;
            this.$arrangement = vertical;
            this.$itemVerticalSpacedBy$delegate = state4;
            this.$content = function3;
        }

        public static Unit a(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            SemanticsPropertiesKt.setTraversalGroup(semanticsPropertyReceiver, true);
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1489314345, i, -1, "androidx.compose.material3.WideNavigationRailLayout.<anonymous> (WideNavigationRail.kt:258)");
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            Modifier modifierSelectableGroup = SelectableGroupKt.selectableGroup(PaddingKt.padding-qDBjuR0$default(SizeKt.widthIn-VpY3zN4$default(WindowInsetsPaddingKt.windowInsetsPadding(SizeKt.fillMaxHeight$default(companion, 0.0f, 1, (Object) null), this.$windowInsets), 0.0f, WideNavigationRailKt.ExpandedRailMaxWidth, 1, (Object) null), 0.0f, WideNavigationRailKt.WNRVerticalPadding, 0.0f, 0.0f, 13, (Object) null));
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: androidx.compose.material3.s5
                    public final Object invoke(Object obj) {
                        return WideNavigationRailKt.AnonymousClass1.a((SemanticsPropertyReceiver) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierSemantics$default = SemanticsModifierKt.semantics$default(modifierSelectableGroup, false, (Function1) objRememberedValue, 1, null);
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$header, this.$expanded, this.$minWidth$delegate, this.$minimumA11ySize, this.$itemMinHeight$delegate, this.$widthFullRange$delegate, this.$actualMaxExpandedWidth$delegate, this.$currentWidth$delegate, this.$arrangement, this.$itemVerticalSpacedBy$delegate);
            Function2<Composer, Integer, Unit> function2 = this.$header;
            Function2<Composer, Integer, Unit> function3 = this.$content;
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, modifierSemantics$default);
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion2.getConstructor();
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer);
            Updater.m2396setimpl(composerM2388constructorimpl, anonymousClass2, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
            if (function2 != null) {
                composer.startReplaceGroup(1714892004);
                Modifier modifierLayoutId = LayoutIdKt.layoutId(companion, WideNavigationRailKt.HeaderLayoutIdTag);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composer.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer, modifierLayoutId);
                Function0<ComposeUiNode> constructor2 = companion2.getConstructor();
                if (composer.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer.startReusableNode();
                if (composer.getInserting()) {
                    composer.createNode(constructor2);
                } else {
                    composer.useNode();
                }
                Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composer);
                Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion2.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = companion2.getSetCompositeKeyHash();
                if (composerM2388constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion2.getSetModifier());
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                function2.invoke(composer, 0);
                composer.endNode();
                composer.endReplaceGroup();
            } else {
                composer.startReplaceGroup(1714982338);
                composer.endReplaceGroup();
            }
            function3.invoke(composer, 0);
            composer.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }
    }
}
