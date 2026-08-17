package androidx.compose.material3.internal;

import androidx.compose.animation.ColorVectorConverterKt;
import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.animation.core.VectorConvertersKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.ContentColorKt;
import androidx.compose.material3.InteractiveComponentSizeKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.MotionSchemeKt;
import androidx.compose.material3.OutlinedTextFieldKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.material3.TextFieldKt;
import androidx.compose.material3.TextFieldLabelPosition;
import androidx.compose.material3.TextFieldLabelScope;
import androidx.compose.material3.Typography;
import androidx.compose.material3.internal.TextFieldImplKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.SmallIconButtonTokens;
import androidx.compose.material3.tokens.TypeScaleTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.LayoutModifierKt;
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
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnit;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import androidx.savedstate.serialization.ClassDiscriminatorModeKt;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function7;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FloatCompanionObject;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b&\u001a\u0099\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\b2\u0006\u0010\t\u001a\u00020\n2\u001e\u0010\u000b\u001a\u001a\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f¢\u0006\u0002\b\b¢\u0006\u0002\b\u000e2\u0013\u0010\u000f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0013\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0013\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0013\u0010\u0012\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0013\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0013\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0007¢\u0006\u0002\b\b2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0011\u0010\u001f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\bH\u0001¢\u0006\u0002\u0010 \u001a2\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0011\u0010/\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\bH\u0003¢\u0006\u0004\b0\u00101\u001a*\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020,2\u0011\u0010/\u001a\r\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\bH\u0003¢\u0006\u0004\b2\u00103\u001a\u001c\u00104\u001a\u000205*\u0002052\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u00106\u001a\u000207H\u0000\u001a\u001c\u00108\u001a\u000205*\u0002052\u0006\u00109\u001a\u00020:2\u0006\u0010;\u001a\u00020<H\u0000\u001a\u001a\u0010=\u001a\u000205*\u0002052\f\u0010>\u001a\b\u0012\u0004\u0012\u00020?0\u0007H\u0000\u001aÔ\u0001\u0010@\u001a\u00020\u00012\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020,2\u0006\u0010D\u001a\u00020,2\u0006\u0010E\u001a\u00020,2\u0006\u0010!\u001a\u00020\u00162\u0099\u0001\u0010/\u001a\u0094\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020H0G¢\u0006\f\bI\u0012\b\bJ\u0012\u0004\b\b(K\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020,0G¢\u0006\f\bI\u0012\b\bJ\u0012\u0004\b\b(L\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020,0G¢\u0006\f\bI\u0012\b\bJ\u0012\u0004\b\b(M\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020H0G¢\u0006\f\bI\u0012\b\bJ\u0012\u0004\b\b(N\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020H0G¢\u0006\f\bI\u0012\b\bJ\u0012\u0004\b\b(O\u0012\u0004\u0012\u00020\u00010F¢\u0006\u0002\b\bH\u0083\b¢\u0006\u0004\bP\u0010Q\u001aE\u0010R\u001a\b\u0012\u0004\u0012\u00020S0G2\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u00162\u0006\u0010T\u001a\u00020\u00162\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010U\u001a\u00020?2\u0006\u0010V\u001a\u00020?H\u0001¢\u0006\u0004\bW\u0010X\u001a\r\u0010b\u001a\u00020?H\u0001¢\u0006\u0002\u0010c\u001a\r\u0010d\u001a\u00020?H\u0001¢\u0006\u0002\u0010c\"\u0018\u0010!\u001a\u00020\u0016*\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#\"\u0018\u0010$\u001a\u00020%*\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'\"\u0018\u0010(\u001a\u00020%*\u00020\n8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b)\u0010'\"\u000e\u0010Y\u001a\u000207X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010Z\u001a\u000207X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010[\u001a\u000207X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\\\u001a\u000207X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010]\u001a\u000207X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010^\u001a\u000207X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010_\u001a\u000207X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010`\u001a\u000207X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010a\u001a\u000207X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010e\u001a\u00020?X\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bf\u0010g\"\u0016\u0010i\u001a\u00020?X\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bj\u0010g\"\u0016\u0010k\u001a\u00020?X\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bl\u0010g\"\u0016\u0010m\u001a\u00020?X\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bn\u0010g\"\u0016\u0010o\u001a\u00020?X\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bp\u0010g\"\u0016\u0010q\u001a\u00020?X\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\br\u0010g\"\u0016\u0010s\u001a\u00020?X\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bt\u0010g\"\u0016\u0010u\u001a\u00020?X\u0080\u0004¢\u0006\n\n\u0002\u0010h\u001a\u0004\bv\u0010g¨\u0006w²\u0006\n\u0010x\u001a\u00020\u0016X\u008a\u0084\u0002²\u0006\n\u0010y\u001a\u00020\u0016X\u008a\u0084\u0002"}, d2 = {"CommonDecorationBox", "", ClassDiscriminatorModeKt.CLASS_DISCRIMINATOR_KEY, "Landroidx/compose/material3/internal/TextFieldType;", "visualText", "", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "labelPosition", "Landroidx/compose/material3/TextFieldLabelPosition;", "label", "Lkotlin/Function1;", "Landroidx/compose/material3/TextFieldLabelScope;", "Lkotlin/ExtensionFunctionType;", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "singleLine", "", "enabled", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "colors", "Landroidx/compose/material3/TextFieldColors;", "container", "(Landroidx/compose/material3/internal/TextFieldType;Ljava/lang/CharSequence;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/TextFieldLabelPosition;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/material3/TextFieldColors;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "showExpandedLabel", "getShowExpandedLabel", "(Landroidx/compose/material3/TextFieldLabelPosition;)Z", "minimizedAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "getMinimizedAlignment", "(Landroidx/compose/material3/TextFieldLabelPosition;)Landroidx/compose/ui/Alignment$Horizontal;", "expandedAlignment", "getExpandedAlignment", "Decoration", "contentColor", "Landroidx/compose/ui/graphics/Color;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "content", "Decoration-3J-VO9M", "(JLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "Decoration-Iv8Zu3U", "(JLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "defaultErrorSemantics", "Landroidx/compose/ui/Modifier;", "defaultErrorMessage", "", "textFieldBackground", "color", "Landroidx/compose/ui/graphics/ColorProducer;", "shape", "Landroidx/compose/ui/graphics/Shape;", "textFieldLabelMinHeight", "minHeight", "Landroidx/compose/ui/unit/Dp;", "TextFieldTransitionScope", "inputState", "Landroidx/compose/material3/internal/InputPhase;", "focusedLabelTextStyleColor", "unfocusedLabelTextStyleColor", "labelColor", "Lkotlin/Function5;", "Landroidx/compose/runtime/State;", "", "Lkotlin/ParameterName;", "name", "labelProgress", "labelTextStyleColor", "labelContentColor", "placeholderOpacity", "prefixSuffixOpacity", "TextFieldTransitionScope-Jy8F4Js", "(Landroidx/compose/material3/internal/InputPhase;JJJZLkotlin/jvm/functions/Function7;Landroidx/compose/runtime/Composer;I)V", "animateBorderStrokeAsState", "Landroidx/compose/foundation/BorderStroke;", "focused", "focusedBorderThickness", "unfocusedBorderThickness", "animateBorderStrokeAsState-NuRrP5Q", "(ZZZLandroidx/compose/material3/TextFieldColors;FFLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "TextFieldId", "PlaceholderId", "LabelId", "LeadingId", "TrailingId", "PrefixId", "SuffixId", "SupportingId", "ContainerId", "textFieldHorizontalIconPadding", "(Landroidx/compose/runtime/Composer;I)F", "minimizedLabelHalfHeight", "TextFieldPadding", "getTextFieldPadding", "()F", "F", "AboveLabelHorizontalPadding", "getAboveLabelHorizontalPadding", "AboveLabelBottomPadding", "getAboveLabelBottomPadding", "SupportingTopPadding", "getSupportingTopPadding", "PrefixSuffixTextPadding", "getPrefixSuffixTextPadding", "MinTextLineHeight", "getMinTextLineHeight", "MinFocusedLabelLineHeight", "getMinFocusedLabelLineHeight", "MinSupportingTextLineHeight", "getMinSupportingTextLineHeight", "material3", "showPlaceholder", "showPrefixSuffix"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TextFieldImplKt {
    public static final String ContainerId = "Container";
    public static final String LabelId = "Label";
    public static final String LeadingId = "Leading";
    public static final String PlaceholderId = "Hint";
    public static final String PrefixId = "Prefix";
    public static final String SuffixId = "Suffix";
    public static final String SupportingId = "Supporting";
    public static final String TextFieldId = "TextField";
    public static final String TrailingId = "Trailing";
    private static final float TextFieldPadding = Dp.m6022constructorimpl(16.0f);
    private static final float AboveLabelHorizontalPadding = Dp.m6022constructorimpl(4.0f);
    private static final float AboveLabelBottomPadding = Dp.m6022constructorimpl(4.0f);
    private static final float SupportingTopPadding = Dp.m6022constructorimpl(4.0f);
    private static final float PrefixSuffixTextPadding = Dp.m6022constructorimpl(2.0f);
    private static final float MinTextLineHeight = Dp.m6022constructorimpl(24.0f);
    private static final float MinFocusedLabelLineHeight = Dp.m6022constructorimpl(16.0f);
    private static final float MinSupportingTextLineHeight = Dp.m6022constructorimpl(16.0f);

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[TextFieldType.values().length];
            try {
                iArr[TextFieldType.Filled.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TextFieldType.Outlined.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[InputPhase.values().length];
            try {
                iArr2[InputPhase.Focused.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[InputPhase.UnfocusedEmpty.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[InputPhase.UnfocusedNotEmpty.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:222:0x0382  */
    /* JADX WARN: Code duplicated, block: B:240:0x03c2  */
    public static final void CommonDecorationBox(final TextFieldType textFieldType, final CharSequence charSequence, final Function2<? super Composer, ? super Integer, Unit> function2, final TextFieldLabelPosition textFieldLabelPosition, final Function3<? super TextFieldLabelScope, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final Function2<? super Composer, ? super Integer, Unit> function6, final Function2<? super Composer, ? super Integer, Unit> function7, final Function2<? super Composer, ? super Integer, Unit> function8, final Function2<? super Composer, ? super Integer, Unit> function9, final boolean z, final boolean z2, final boolean z3, final InteractionSource interactionSource, final PaddingValues paddingValues, final TextFieldColors textFieldColors, final Function2<? super Composer, ? super Integer, Unit> function10, Composer composer, final int i, final int i2) {
        int i3;
        int i4;
        int i5;
        TextFieldColors textFieldColors2;
        Composer composer2;
        InputPhase inputPhase;
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        boolean z4;
        Composer composer3;
        int i6;
        TextStyle textStyle;
        final TextStyle textStyle2;
        final State state;
        ComposableLambda composableLambda;
        final State state2;
        State state3;
        ComposableLambda composableLambda2;
        ComposableLambda composableLambdaRememberComposableLambda;
        ComposableLambda composableLambdaRememberComposableLambda2;
        Function2 function11;
        int i7;
        ComposableLambda composableLambdaRememberComposableLambda3;
        Composer composerStartRestartGroup = composer.startRestartGroup(546805032);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(textFieldType.ordinal()) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i4 = i3 | (composerStartRestartGroup.changedInstance(charSequence) ? 32 : 16);
        } else {
            i4 = i3;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i4 |= composerStartRestartGroup.changed(textFieldLabelPosition) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function3) ? 16384 : 8192;
        }
        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function4) ? 131072 : 65536;
        }
        if ((i & 1572864) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function5) ? 1048576 : 524288;
        }
        if ((i & 12582912) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function6) ? 8388608 : 4194304;
        }
        if ((i & 100663296) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function7) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i & 805306368) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function8) ? 536870912 : 268435456;
        }
        int i8 = i4;
        if ((i2 & 6) == 0) {
            i5 = i2 | (composerStartRestartGroup.changedInstance(function9) ? 4 : 2);
        } else {
            i5 = i2;
        }
        if ((i2 & 48) == 0) {
            i5 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i5 |= composerStartRestartGroup.changed(z2) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i5 |= composerStartRestartGroup.changed(z3) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i5 |= composerStartRestartGroup.changed(interactionSource) ? 16384 : 8192;
        }
        if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            i5 |= composerStartRestartGroup.changed(paddingValues) ? 131072 : 65536;
        }
        if ((i2 & 1572864) == 0) {
            textFieldColors2 = textFieldColors;
            i5 |= composerStartRestartGroup.changed(textFieldColors2) ? 1048576 : 524288;
        } else {
            textFieldColors2 = textFieldColors;
        }
        if ((i2 & 12582912) == 0) {
            i5 |= composerStartRestartGroup.changedInstance(function10) ? 8388608 : 4194304;
        }
        int i9 = i5;
        if (composerStartRestartGroup.shouldExecute(((i8 & 306783379) == 306783378 && (4793491 & i9) == 4793490) ? false : true, i8 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(546805032, i8, i9, "androidx.compose.material3.internal.CommonDecorationBox (TextFieldImpl.kt:98)");
            }
            boolean zBooleanValue = ((Boolean) FocusInteractionKt.collectIsFocusedAsState(interactionSource, composerStartRestartGroup, (i9 >> 12) & 14).getValue()).booleanValue();
            if (zBooleanValue) {
                inputPhase = InputPhase.Focused;
            } else {
                inputPhase = charSequence.length() == 0 ? InputPhase.UnfocusedEmpty : InputPhase.UnfocusedNotEmpty;
            }
            long jM1058labelColorXeAY9LY$material3 = textFieldColors2.m1058labelColorXeAY9LY$material3(z2, z3, zBooleanValue);
            Typography typography = MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6);
            final TextStyle bodyLarge = typography.getBodyLarge();
            final TextStyle bodySmall = typography.getBodySmall();
            long jM5509getColor0d7_KjU = bodyLarge.m5509getColor0d7_KjU();
            Color.Companion companion = Color.INSTANCE;
            boolean z5 = (Color.m3135equalsimpl0(jM5509getColor0d7_KjU, companion.m3170getUnspecified0d7_KjU()) && !Color.m3135equalsimpl0(bodySmall.m5509getColor0d7_KjU(), companion.m3170getUnspecified0d7_KjU())) || (!Color.m3135equalsimpl0(bodyLarge.m5509getColor0d7_KjU(), companion.m3170getUnspecified0d7_KjU()) && Color.m3135equalsimpl0(bodySmall.m5509getColor0d7_KjU(), companion.m3170getUnspecified0d7_KjU()));
            long jM5509getColor0d7_KjU2 = bodySmall.m5509getColor0d7_KjU();
            if (z5 && jM5509getColor0d7_KjU2 == 16) {
                jM5509getColor0d7_KjU2 = jM1058labelColorXeAY9LY$material3;
            }
            long jM5509getColor0d7_KjU3 = bodyLarge.m5509getColor0d7_KjU();
            long j = (z5 && jM5509getColor0d7_KjU3 == 16) ? jM1058labelColorXeAY9LY$material3 : jM5509getColor0d7_KjU3;
            boolean z6 = function3 != null && getShowExpandedLabel(textFieldLabelPosition);
            final boolean z7 = z5;
            long j2 = jM5509getColor0d7_KjU2;
            Transition transitionUpdateTransition = TransitionKt.updateTransition(inputPhase, "TextFieldInputState", composerStartRestartGroup, 48, 0);
            TextFieldImplKt$TextFieldTransitionScope$labelProgress$1 textFieldImplKt$TextFieldTransitionScope$labelProgress$1 = new TextFieldImplKt$TextFieldTransitionScope$labelProgress$1(MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6));
            FloatCompanionObject floatCompanionObject = FloatCompanionObject.INSTANCE;
            TwoWayConverter vectorConverter = VectorConvertersKt.getVectorConverter(floatCompanionObject);
            InputPhase inputPhase2 = (InputPhase) transitionUpdateTransition.getCurrentState();
            composerStartRestartGroup.startReplaceGroup(-1436405362);
            boolean z8 = z6;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1436405362, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:391)");
            }
            int[] iArr = WhenMappings.$EnumSwitchMapping$1;
            int i10 = iArr[inputPhase2.ordinal()];
            float f6 = 1.0f;
            if (i10 == 1) {
                f = 1.0f;
            } else {
                if (i10 != 2) {
                    if (i10 != 3) {
                        bu8.a();
                        return;
                    }
                } else if (z8) {
                    f = 0.0f;
                }
                f = 1.0f;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            Float fValueOf = Float.valueOf(f);
            InputPhase inputPhase3 = (InputPhase) transitionUpdateTransition.getTargetState();
            composerStartRestartGroup.startReplaceGroup(-1436405362);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1436405362, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:391)");
            }
            int i11 = iArr[inputPhase3.ordinal()];
            if (i11 == 1) {
                f2 = 1.0f;
            } else {
                if (i11 != 2) {
                    if (i11 != 3) {
                        bu8.a();
                        return;
                    }
                } else if (z8) {
                    f2 = 0.0f;
                }
                f2 = 1.0f;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            final State stateCreateTransitionAnimation = TransitionKt.createTransitionAnimation(transitionUpdateTransition, fValueOf, Float.valueOf(f2), textFieldImplKt$TextFieldTransitionScope$labelProgress$1.invoke(transitionUpdateTransition.getSegment(), composerStartRestartGroup, 0), vectorConverter, "LabelProgress", composerStartRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
            MotionSchemeKeyTokens motionSchemeKeyTokens = MotionSchemeKeyTokens.FastEffects;
            FiniteAnimationSpec finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens, composerStartRestartGroup, 6);
            TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 = new TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1(finiteAnimationSpecValue, MotionSchemeKt.value(MotionSchemeKeyTokens.SlowEffects, composerStartRestartGroup, 6));
            TwoWayConverter vectorConverter2 = VectorConvertersKt.getVectorConverter(floatCompanionObject);
            InputPhase inputPhase4 = (InputPhase) transitionUpdateTransition.getCurrentState();
            composerStartRestartGroup.startReplaceGroup(-1093194547);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1093194547, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:416)");
            }
            int i12 = iArr[inputPhase4.ordinal()];
            if (i12 == 1) {
                f3 = 1.0f;
            } else {
                if (i12 != 2) {
                    if (i12 != 3) {
                        bu8.a();
                        return;
                    }
                } else if (!z8) {
                    f3 = 1.0f;
                }
                f3 = 0.0f;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            Float fValueOf2 = Float.valueOf(f3);
            InputPhase inputPhase5 = (InputPhase) transitionUpdateTransition.getTargetState();
            composerStartRestartGroup.startReplaceGroup(-1093194547);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1093194547, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:416)");
            }
            int i13 = iArr[inputPhase5.ordinal()];
            if (i13 == 1) {
                f4 = 1.0f;
            } else {
                if (i13 != 2) {
                    if (i13 != 3) {
                        bu8.a();
                        return;
                    }
                } else if (!z8) {
                    f4 = 1.0f;
                }
                f4 = 0.0f;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            final State stateCreateTransitionAnimation2 = TransitionKt.createTransitionAnimation(transitionUpdateTransition, fValueOf2, Float.valueOf(f4), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.invoke(transitionUpdateTransition.getSegment(), composerStartRestartGroup, 0), vectorConverter2, "PlaceholderOpacity", composerStartRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
            TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 = new TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1(finiteAnimationSpecValue);
            TwoWayConverter vectorConverter3 = VectorConvertersKt.getVectorConverter(floatCompanionObject);
            InputPhase inputPhase6 = (InputPhase) transitionUpdateTransition.getCurrentState();
            composerStartRestartGroup.startReplaceGroup(-1258455321);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1258455321, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:428)");
            }
            int i14 = iArr[inputPhase6.ordinal()];
            if (i14 == 1) {
                f5 = 1.0f;
            } else {
                if (i14 != 2) {
                    if (i14 != 3) {
                        bu8.a();
                        return;
                    }
                } else if (z8) {
                    f5 = 0.0f;
                }
                f5 = 1.0f;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            Float fValueOf3 = Float.valueOf(f5);
            InputPhase inputPhase7 = (InputPhase) transitionUpdateTransition.getTargetState();
            composerStartRestartGroup.startReplaceGroup(-1258455321);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1258455321, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:428)");
            }
            int i15 = iArr[inputPhase7.ordinal()];
            if (i15 != 1) {
                if (i15 != 2) {
                    if (i15 != 3) {
                        bu8.a();
                        return;
                    }
                } else if (z8) {
                    f6 = 0.0f;
                }
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            State stateCreateTransitionAnimation3 = TransitionKt.createTransitionAnimation(transitionUpdateTransition, fValueOf3, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.invoke(transitionUpdateTransition.getSegment(), composerStartRestartGroup, 0), vectorConverter3, "PrefixSuffixOpacity", composerStartRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
            FiniteAnimationSpec finiteAnimationSpecValue2 = MotionSchemeKt.value(motionSchemeKeyTokens, composerStartRestartGroup, 6);
            TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 = new TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1(finiteAnimationSpecValue2);
            InputPhase inputPhase8 = (InputPhase) transitionUpdateTransition.getTargetState();
            composerStartRestartGroup.startReplaceGroup(-12973394);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-12973394, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:441)");
            }
            long j3 = iArr[inputPhase8.ordinal()] == 1 ? j2 : j;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            ColorSpace colorSpaceM3138getColorSpaceimpl = Color.m3138getColorSpaceimpl(j3);
            boolean zChanged = composerStartRestartGroup.changed(colorSpaceM3138getColorSpaceimpl);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(companion).invoke(colorSpaceM3138getColorSpaceimpl);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            TwoWayConverter twoWayConverter = (TwoWayConverter) objRememberedValue;
            InputPhase inputPhase9 = (InputPhase) transitionUpdateTransition.getCurrentState();
            composerStartRestartGroup.startReplaceGroup(-12973394);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-12973394, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:441)");
            }
            long j4 = iArr[inputPhase9.ordinal()] == 1 ? j2 : j;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            Color colorM3124boximpl = Color.m3124boximpl(j4);
            InputPhase inputPhase10 = (InputPhase) transitionUpdateTransition.getTargetState();
            composerStartRestartGroup.startReplaceGroup(-12973394);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-12973394, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:441)");
            }
            if (iArr[inputPhase10.ordinal()] == 1) {
                j = j2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            final State stateCreateTransitionAnimation4 = TransitionKt.createTransitionAnimation(transitionUpdateTransition, colorM3124boximpl, Color.m3124boximpl(j), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.invoke(transitionUpdateTransition.getSegment(), composerStartRestartGroup, 0), twoWayConverter, "LabelTextStyleColor", composerStartRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
            TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$1 = new TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1(finiteAnimationSpecValue2);
            composerStartRestartGroup.startReplaceGroup(-464752477);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-464752477, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:452)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            ColorSpace colorSpaceM3138getColorSpaceimpl2 = Color.m3138getColorSpaceimpl(jM1058labelColorXeAY9LY$material3);
            boolean zChanged2 = composerStartRestartGroup.changed(colorSpaceM3138getColorSpaceimpl2);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(companion).invoke(colorSpaceM3138getColorSpaceimpl2);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            TwoWayConverter twoWayConverter2 = (TwoWayConverter) objRememberedValue2;
            composerStartRestartGroup.startReplaceGroup(-464752477);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-464752477, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:452)");
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            Color colorM3124boximpl2 = Color.m3124boximpl(jM1058labelColorXeAY9LY$material3);
            composerStartRestartGroup.startReplaceGroup(-464752477);
            if (ComposerKt.isTraceInProgress()) {
                z4 = false;
                ComposerKt.traceEventStart(-464752477, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:452)");
            } else {
                z4 = false;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            final State stateCreateTransitionAnimation5 = TransitionKt.createTransitionAnimation(transitionUpdateTransition, colorM3124boximpl2, Color.m3124boximpl(jM1058labelColorXeAY9LY$material3), textFieldImplKt$TextFieldTransitionScope$labelContentColor$1.invoke(transitionUpdateTransition.getSegment(), composerStartRestartGroup, 0), twoWayConverter2, "LabelContentColor", composerStartRestartGroup, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion2 = Composer.INSTANCE;
            if (objRememberedValue3 == companion2.getEmpty()) {
                objRememberedValue3 = new TextFieldLabelScope() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$labelScope$1$1
                    @Override // androidx.compose.material3.TextFieldLabelScope
                    public float getLabelMinimizedProgress() {
                        return stateCreateTransitionAnimation.getValue().floatValue();
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final TextFieldImplKt$CommonDecorationBox$3$labelScope$1$1 textFieldImplKt$CommonDecorationBox$3$labelScope$1$1 = (TextFieldImplKt$CommonDecorationBox$3$labelScope$1$1) objRememberedValue3;
            Function2 function2RememberComposableLambda = null;
            if (function3 == null) {
                composerStartRestartGroup.startReplaceGroup(-1891724857);
                composerStartRestartGroup.endReplaceGroup();
                state = stateCreateTransitionAnimation;
                composer3 = composerStartRestartGroup;
                textStyle2 = bodySmall;
                textStyle = bodyLarge;
                i6 = 54;
            } else {
                composerStartRestartGroup.startReplaceGroup(-1891724856);
                composer3 = composerStartRestartGroup;
                i6 = 54;
                Function2<Composer, Integer, Unit> function12 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedLabel$1$1
                    public final void invoke(Composer composer4, int i16) {
                        if (!composer4.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                            composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1076580032, i16, -1, "androidx.compose.material3.internal.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:139)");
                        }
                        TextStyle textStyleLerp = TextStyleKt.lerp(bodyLarge, bodySmall, stateCreateTransitionAnimation.getValue().floatValue());
                        boolean z9 = z7;
                        State<Color> state4 = stateCreateTransitionAnimation4;
                        if (z9) {
                            textStyleLerp = TextStyle.m5492copyp1EtxEg$default(textStyleLerp, state4.getValue().m3144unboximpl(), 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, null, 0, 0, null, 16777214, null);
                        }
                        TextStyle textStyle3 = textStyleLerp;
                        long jM3144unboximpl = stateCreateTransitionAnimation5.getValue().m3144unboximpl();
                        final Function3<TextFieldLabelScope, Composer, Integer, Unit> function13 = function3;
                        final TextFieldImplKt$CommonDecorationBox$3$labelScope$1$1 textFieldImplKt$CommonDecorationBox$3$labelScope$1$2 = textFieldImplKt$CommonDecorationBox$3$labelScope$1$1;
                        TextFieldImplKt.m1473Decoration3JVO9M(jM3144unboximpl, textStyle3, ComposableLambdaKt.rememberComposableLambda(1157484991, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedLabel$1$1.1
                            public final void invoke(Composer composer5, int i17) {
                                if (!composer5.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                    composer5.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1157484991, i17, -1, "androidx.compose.material3.internal.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:147)");
                                }
                                function13.invoke(textFieldImplKt$CommonDecorationBox$3$labelScope$1$2, composer5, 6);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composer4, 54), composer4, AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                };
                textStyle = bodyLarge;
                textStyle2 = bodySmall;
                state = stateCreateTransitionAnimation;
                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1076580032, true, function12, composer3, 54);
                composer3.endReplaceGroup();
            }
            Function2 function13 = function2RememberComposableLambda;
            long jM1060placeholderColorXeAY9LY$material3 = textFieldColors.m1060placeholderColorXeAY9LY$material3(z2, z3, r45);
            Object objRememberedValue4 = composer3.rememberedValue();
            if (objRememberedValue4 == companion2.getEmpty()) {
                objRememberedValue4 = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0() { // from class: x6e
                    public final Object invoke() {
                        return Boolean.valueOf(TextFieldImplKt.k(stateCreateTransitionAnimation2));
                    }
                });
                composer3.updateRememberedValue(objRememberedValue4);
            }
            State state4 = (State) objRememberedValue4;
            if (function4 != null && charSequence.length() == 0 && CommonDecorationBox$lambda$18$lambda$8(state4)) {
                composer3.startReplaceGroup(-1890614312);
                ComposableLambda composableLambdaRememberComposableLambda4 = ComposableLambdaKt.rememberComposableLambda(1405547205, true, new TextFieldImplKt$CommonDecorationBox$3$decoratedPlaceholder$1(stateCreateTransitionAnimation2, jM1060placeholderColorXeAY9LY$material3, textStyle, function4), composer3, i6);
                composer3.endReplaceGroup();
                composableLambda = composableLambdaRememberComposableLambda4;
            } else {
                composer3.startReplaceGroup(-1890217110);
                composer3.endReplaceGroup();
                composableLambda = null;
            }
            long jM1061prefixColorXeAY9LY$material3 = textFieldColors.m1061prefixColorXeAY9LY$material3(z2, z3, r45);
            Object objRememberedValue5 = composer3.rememberedValue();
            if (objRememberedValue5 == companion2.getEmpty()) {
                state2 = stateCreateTransitionAnimation3;
                objRememberedValue5 = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0() { // from class: y6e
                    public final Object invoke() {
                        return Boolean.valueOf(TextFieldImplKt.f(state2));
                    }
                });
                composer3.updateRememberedValue(objRememberedValue5);
            } else {
                state2 = stateCreateTransitionAnimation3;
            }
            State state5 = (State) objRememberedValue5;
            if (function7 == null || !CommonDecorationBox$lambda$18$lambda$11(state5)) {
                state3 = state2;
                composer3.startReplaceGroup(-1889500886);
                composer3.endReplaceGroup();
                composableLambda2 = null;
            } else {
                composer3.startReplaceGroup(-1889877907);
                state3 = state2;
                ComposableLambda composableLambdaRememberComposableLambda5 = ComposableLambdaKt.rememberComposableLambda(606594655, true, new TextFieldImplKt$CommonDecorationBox$3$decoratedPrefix$1(state3, jM1061prefixColorXeAY9LY$material3, textStyle, function7), composer3, i6);
                composer3.endReplaceGroup();
                composableLambda2 = composableLambdaRememberComposableLambda5;
            }
            long jM1062suffixColorXeAY9LY$material3 = textFieldColors.m1062suffixColorXeAY9LY$material3(z2, z3, r45);
            if (function8 == null || !CommonDecorationBox$lambda$18$lambda$11(state5)) {
                composer3.startReplaceGroup(-1888924534);
                composer3.endReplaceGroup();
                composableLambdaRememberComposableLambda = null;
            } else {
                composer3.startReplaceGroup(-1889301555);
                composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-45078754, true, new TextFieldImplKt$CommonDecorationBox$3$decoratedSuffix$1(state3, jM1062suffixColorXeAY9LY$material3, textStyle, function8), composer3, i6);
                composer3.endReplaceGroup();
            }
            final long jM1059leadingIconColorXeAY9LY$material3 = textFieldColors.m1059leadingIconColorXeAY9LY$material3(z2, z3, r45);
            if (function5 == null) {
                composer3.startReplaceGroup(-1888749663);
                composer3.endReplaceGroup();
                composableLambdaRememberComposableLambda2 = null;
            } else {
                composer3.startReplaceGroup(-1888749662);
                composableLambdaRememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(-1736293487, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedLeading$1$1
                    public final void invoke(Composer composer4, int i16) {
                        if (!composer4.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                            composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1736293487, i16, -1, "androidx.compose.material3.internal.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:205)");
                        }
                        TextFieldImplKt.m1474DecorationIv8Zu3U(jM1059leadingIconColorXeAY9LY$material3, function5, composer4, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composer3, i6);
                composer3.endReplaceGroup();
            }
            final long jM1065trailingIconColorXeAY9LY$material3 = textFieldColors.m1065trailingIconColorXeAY9LY$material3(z2, z3, r45);
            if (function6 == null) {
                composer3.startReplaceGroup(-1888469888);
                composer3.endReplaceGroup();
                function11 = null;
            } else {
                composer3.startReplaceGroup(-1888469887);
                Function2 function2RememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1334518521, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedTrailing$1$1
                    public final void invoke(Composer composer4, int i16) {
                        if (!composer4.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                            composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1334518521, i16, -1, "androidx.compose.material3.internal.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:211)");
                        }
                        TextFieldImplKt.m1474DecorationIv8Zu3U(jM1065trailingIconColorXeAY9LY$material3, function6, composer4, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composer3, 54);
                composer3.endReplaceGroup();
                function11 = function2RememberComposableLambda2;
            }
            final long jM1063supportingTextColorXeAY9LY$material3 = textFieldColors.m1063supportingTextColorXeAY9LY$material3(z2, z3, zBooleanValue);
            if (function9 == null) {
                composer3.startReplaceGroup(-1888176380);
                composer3.endReplaceGroup();
                composableLambdaRememberComposableLambda3 = null;
                i7 = 1;
            } else {
                composer3.startReplaceGroup(-1888176379);
                Function2<Composer, Integer, Unit> function14 = new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$decoratedSupporting$1$1
                    public final void invoke(Composer composer4, int i16) {
                        if (!composer4.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                            composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(837168720, i16, -1, "androidx.compose.material3.internal.CommonDecorationBox.<anonymous>.<anonymous>.<anonymous> (TextFieldImpl.kt:218)");
                        }
                        TextFieldImplKt.m1473Decoration3JVO9M(jM1063supportingTextColorXeAY9LY$material3, textStyle2, function9, composer4, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                };
                i7 = 1;
                composableLambdaRememberComposableLambda3 = ComposableLambdaKt.rememberComposableLambda(837168720, true, function14, composer3, 54);
                composer3.endReplaceGroup();
            }
            int i16 = WhenMappings.$EnumSwitchMapping$0[textFieldType.ordinal()];
            if (i16 == i7) {
                Composer composer4 = composer3;
                composer4.startReplaceGroup(-1887830698);
                TextFieldKt.TextFieldLayout(Modifier.INSTANCE, function2, function13, composableLambda, composableLambdaRememberComposableLambda2, function11, composableLambda2, composableLambdaRememberComposableLambda, z, textFieldLabelPosition, new TextFieldImplKt$sam$androidx_compose_material3_internal_FloatProducer$0(new PropertyReference0Impl(state) { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$1
                    public Object get() {
                        return ((State) ((CallableReference) this).receiver).getValue();
                    }
                }), ComposableLambdaKt.rememberComposableLambda(-1729858187, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$containerWithId$1
                    public final void invoke(Composer composer5, int i17) {
                        if (!composer5.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                            composer5.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1729858187, i17, -1, "androidx.compose.material3.internal.CommonDecorationBox.<anonymous>.<anonymous> (TextFieldImpl.kt:229)");
                        }
                        Modifier modifierLayoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.ContainerId);
                        Function2<Composer, Integer, Unit> function15 = function10;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), true);
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer5.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer5, modifierLayoutId);
                        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor = companion3.getConstructor();
                        if (composer5.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composer5.startReusableNode();
                        if (composer5.getInserting()) {
                            composer5.createNode(constructor);
                        } else {
                            composer5.useNode();
                        }
                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer5);
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion3.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        function15.invoke(composer5, 0);
                        composer5.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composer4, 54), composableLambdaRememberComposableLambda3, paddingValues, composer4, ((i8 >> 3) & 112) | 6 | ((i9 << 21) & 234881024) | ((i8 << 18) & 1879048192), ((i9 >> 6) & V4Signature.MAX_SIGNING_INFOS_SIZE) | 48);
                composer2 = composer4;
                composer2.endReplaceGroup();
                Unit unit = Unit.INSTANCE;
            } else {
                if (i16 != 2) {
                    Composer composer5 = composer3;
                    composer5.startReplaceGroup(493292232);
                    composer5.endReplaceGroup();
                    bu8.a();
                    return;
                }
                composer3.startReplaceGroup(-1886778186);
                Object objRememberedValue6 = composer3.rememberedValue();
                if (objRememberedValue6 == companion2.getEmpty()) {
                    objRememberedValue6 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Size.m2946boximpl(Size.INSTANCE.m2967getZeroNHjbRc()), null, 2, null);
                    composer3.updateRememberedValue(objRememberedValue6);
                }
                final MutableState mutableState = (MutableState) objRememberedValue6;
                ComposableLambda composableLambda3 = composableLambdaRememberComposableLambda3;
                ComposableLambda composableLambda4 = composableLambda;
                ComposableLambda composableLambda5 = composableLambdaRememberComposableLambda;
                ComposableLambda composableLambdaRememberComposableLambda6 = ComposableLambdaKt.rememberComposableLambda(528115858, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$borderContainerWithId$1
                    public final void invoke(Composer composer6, int i17) {
                        if (!composer6.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                            composer6.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(528115858, i17, -1, "androidx.compose.material3.internal.CommonDecorationBox.<anonymous>.<anonymous> (TextFieldImpl.kt:255)");
                        }
                        Modifier modifierOutlineCutout = OutlinedTextFieldKt.outlineCutout(LayoutIdKt.layoutId(Modifier.INSTANCE, TextFieldImplKt.ContainerId), new MutablePropertyReference0Impl(mutableState) { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$borderContainerWithId$1.1
                            public Object get() {
                                return ((MutableState) ((CallableReference) this).receiver).getValue();
                            }

                            public void set(Object obj) {
                                ((MutableState) ((CallableReference) this).receiver).setValue(obj);
                            }
                        }, TextFieldImplKt.getMinimizedAlignment(textFieldLabelPosition), paddingValues);
                        Function2<Composer, Integer, Unit> function15 = function10;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), true);
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer6, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer6.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer6, modifierOutlineCutout);
                        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor = companion3.getConstructor();
                        if (composer6.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composer6.startReusableNode();
                        if (composer6.getInserting()) {
                            composer6.createNode(constructor);
                        } else {
                            composer6.useNode();
                        }
                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer6);
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion3.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        function15.invoke(composer6, 0);
                        composer6.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composer3, 54);
                Modifier.Companion companion3 = Modifier.INSTANCE;
                TextFieldImplKt$sam$androidx_compose_material3_internal_FloatProducer$0 textFieldImplKt$sam$androidx_compose_material3_internal_FloatProducer$0 = new TextFieldImplKt$sam$androidx_compose_material3_internal_FloatProducer$0(new PropertyReference0Impl(state) { // from class: androidx.compose.material3.internal.TextFieldImplKt$CommonDecorationBox$3$2
                    public Object get() {
                        return ((State) ((CallableReference) this).receiver).getValue();
                    }
                });
                boolean zChanged3 = ((i8 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048 ? true : z4) | composer3.changed(state);
                Object objRememberedValue7 = composer3.rememberedValue();
                if (zChanged3 || objRememberedValue7 == companion2.getEmpty()) {
                    objRememberedValue7 = new Function1() { // from class: z6e
                        public final Object invoke(Object obj) {
                            return TextFieldImplKt.i(textFieldLabelPosition, state, mutableState, (Size) obj);
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue7);
                }
                Composer composer6 = composer3;
                OutlinedTextFieldKt.OutlinedTextFieldLayout(companion3, function2, composableLambda4, function13, composableLambdaRememberComposableLambda2, function11, composableLambda2, composableLambda5, z, textFieldLabelPosition, textFieldImplKt$sam$androidx_compose_material3_internal_FloatProducer$0, (Function1) objRememberedValue7, composableLambdaRememberComposableLambda6, composableLambda3, paddingValues, composer6, ((i8 >> 3) & 112) | 6 | ((i9 << 21) & 234881024) | ((i8 << 18) & 1879048192), (57344 & (i9 >> 3)) | AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP);
                composer2 = composer6;
                composer2.endReplaceGroup();
                Unit unit2 = Unit.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a7e
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldImplKt.a(textFieldType, charSequence, function2, textFieldLabelPosition, function3, function4, function5, function6, function7, function8, function9, z, z2, z3, interactionSource, paddingValues, textFieldColors, function10, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final boolean CommonDecorationBox$lambda$18$lambda$11(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    private static final boolean CommonDecorationBox$lambda$18$lambda$8(State<Boolean> state) {
        return state.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Decoration-3J-VO9M, reason: not valid java name */
    public static final void m1473Decoration3JVO9M(long j, TextStyle textStyle, Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        final Function2<? super Composer, ? super Integer, Unit> function3;
        final TextStyle textStyle2;
        final long j2;
        Composer composerStartRestartGroup = composer.startRestartGroup(396611577);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(textStyle) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 147) != 146, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(396611577, i2, -1, "androidx.compose.material3.internal.Decoration (TextFieldImpl.kt:325)");
            }
            ProvideContentColorTextStyleKt.m1390ProvideContentColorTextStyle3JVO9M(j, textStyle, function2, composerStartRestartGroup, i2 & 1022);
            j2 = j;
            textStyle2 = textStyle;
            function3 = function2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            function3 = function2;
            textStyle2 = textStyle;
            j2 = j;
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: f7e
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldImplKt.e(j2, textStyle2, function3, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Decoration-Iv8Zu3U, reason: not valid java name */
    public static final void m1474DecorationIv8Zu3U(final long j, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(590397809);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(590397809, i2, -1, "androidx.compose.material3.internal.Decoration (TextFieldImpl.kt:330)");
            }
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(j)), function2, composerStartRestartGroup, (i2 & 112) | ProvidedValue.$stable);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: w6e
                public final Object invoke(Object obj, Object obj2) {
                    return TextFieldImplKt.d(j, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:45:0x0103  */
    /* JADX WARN: Code duplicated, block: B:63:0x013f  */
    /* JADX INFO: renamed from: TextFieldTransitionScope-Jy8F4Js, reason: not valid java name */
    private static final void m1475TextFieldTransitionScopeJy8F4Js(InputPhase inputPhase, long j, long j2, long j3, boolean z, Function7<? super State<Float>, ? super State<Color>, ? super State<Color>, ? super State<Float>, ? super State<Float>, ? super Composer, ? super Integer, Unit> function7, Composer composer, int i) {
        float f;
        float f2;
        float f3;
        float f4;
        float f5;
        Transition transitionUpdateTransition = TransitionKt.updateTransition(inputPhase, "TextFieldInputState", composer, (i & 14) | 48, 0);
        TextFieldImplKt$TextFieldTransitionScope$labelProgress$1 textFieldImplKt$TextFieldTransitionScope$labelProgress$1 = new TextFieldImplKt$TextFieldTransitionScope$labelProgress$1(MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composer, 6));
        FloatCompanionObject floatCompanionObject = FloatCompanionObject.INSTANCE;
        TwoWayConverter vectorConverter = VectorConvertersKt.getVectorConverter(floatCompanionObject);
        InputPhase inputPhase2 = (InputPhase) transitionUpdateTransition.getCurrentState();
        composer.startReplaceGroup(-1436405362);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1436405362, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:391)");
        }
        int[] iArr = WhenMappings.$EnumSwitchMapping$1;
        int i2 = iArr[inputPhase2.ordinal()];
        float f6 = 0.0f;
        if (i2 == 1) {
            f = 1.0f;
        } else {
            if (i2 != 2) {
                if (i2 != 3) {
                    bu8.a();
                    return;
                }
            } else if (z) {
                f = 0.0f;
            }
            f = 1.0f;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        Float fValueOf = Float.valueOf(f);
        InputPhase inputPhase3 = (InputPhase) transitionUpdateTransition.getTargetState();
        composer.startReplaceGroup(-1436405362);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1436405362, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:391)");
        }
        int i3 = iArr[inputPhase3.ordinal()];
        if (i3 == 1) {
            f2 = 1.0f;
        } else {
            if (i3 != 2) {
                if (i3 != 3) {
                    bu8.a();
                    return;
                }
            } else if (z) {
                f2 = 0.0f;
            }
            f2 = 1.0f;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        State stateCreateTransitionAnimation = TransitionKt.createTransitionAnimation(transitionUpdateTransition, fValueOf, Float.valueOf(f2), textFieldImplKt$TextFieldTransitionScope$labelProgress$1.invoke(transitionUpdateTransition.getSegment(), composer, 0), vectorConverter, "LabelProgress", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
        MotionSchemeKeyTokens motionSchemeKeyTokens = MotionSchemeKeyTokens.FastEffects;
        FiniteAnimationSpec finiteAnimationSpecValue = MotionSchemeKt.value(motionSchemeKeyTokens, composer, 6);
        TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1 = new TextFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1(finiteAnimationSpecValue, MotionSchemeKt.value(MotionSchemeKeyTokens.SlowEffects, composer, 6));
        TwoWayConverter vectorConverter2 = VectorConvertersKt.getVectorConverter(floatCompanionObject);
        InputPhase inputPhase4 = (InputPhase) transitionUpdateTransition.getCurrentState();
        composer.startReplaceGroup(-1093194547);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1093194547, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:416)");
        }
        int i4 = iArr[inputPhase4.ordinal()];
        if (i4 == 1) {
            f3 = 1.0f;
        } else {
            if (i4 != 2) {
                if (i4 != 3) {
                    bu8.a();
                    return;
                }
            } else if (!z) {
                f3 = 1.0f;
            }
            f3 = 0.0f;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        Float fValueOf2 = Float.valueOf(f3);
        InputPhase inputPhase5 = (InputPhase) transitionUpdateTransition.getTargetState();
        composer.startReplaceGroup(-1093194547);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1093194547, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:416)");
        }
        int i5 = iArr[inputPhase5.ordinal()];
        if (i5 == 1) {
            f4 = 1.0f;
        } else {
            if (i5 != 2) {
                if (i5 != 3) {
                    bu8.a();
                    return;
                }
            } else if (!z) {
                f4 = 1.0f;
            }
            f4 = 0.0f;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        State stateCreateTransitionAnimation2 = TransitionKt.createTransitionAnimation(transitionUpdateTransition, fValueOf2, Float.valueOf(f4), textFieldImplKt$TextFieldTransitionScope$placeholderOpacity$1.invoke(transitionUpdateTransition.getSegment(), composer, 0), vectorConverter2, "PlaceholderOpacity", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
        TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1 = new TextFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1(finiteAnimationSpecValue);
        TwoWayConverter vectorConverter3 = VectorConvertersKt.getVectorConverter(floatCompanionObject);
        InputPhase inputPhase6 = (InputPhase) transitionUpdateTransition.getCurrentState();
        composer.startReplaceGroup(-1258455321);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1258455321, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:428)");
        }
        int i6 = iArr[inputPhase6.ordinal()];
        if (i6 == 1) {
            f5 = 1.0f;
        } else {
            if (i6 != 2) {
                if (i6 != 3) {
                    bu8.a();
                    return;
                }
            } else if (z) {
                f5 = 0.0f;
            }
            f5 = 1.0f;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        Float fValueOf3 = Float.valueOf(f5);
        InputPhase inputPhase7 = (InputPhase) transitionUpdateTransition.getTargetState();
        composer.startReplaceGroup(-1258455321);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1258455321, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:428)");
        }
        int i7 = iArr[inputPhase7.ordinal()];
        if (i7 == 1) {
            f6 = 1.0f;
        } else {
            if (i7 != 2) {
                if (i7 != 3) {
                    bu8.a();
                    return;
                }
            } else if (!z) {
            }
            f6 = 1.0f;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        State stateCreateTransitionAnimation3 = TransitionKt.createTransitionAnimation(transitionUpdateTransition, fValueOf3, Float.valueOf(f6), textFieldImplKt$TextFieldTransitionScope$prefixSuffixOpacity$1.invoke(transitionUpdateTransition.getSegment(), composer, 0), vectorConverter3, "PrefixSuffixOpacity", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
        FiniteAnimationSpec finiteAnimationSpecValue2 = MotionSchemeKt.value(motionSchemeKeyTokens, composer, 6);
        TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1 = new TextFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1(finiteAnimationSpecValue2);
        InputPhase inputPhase8 = (InputPhase) transitionUpdateTransition.getTargetState();
        composer.startReplaceGroup(-12973394);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-12973394, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:441)");
        }
        long j4 = iArr[inputPhase8.ordinal()] == 1 ? j : j2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        ColorSpace colorSpaceM3138getColorSpaceimpl = Color.m3138getColorSpaceimpl(j4);
        boolean zChanged = composer.changed(colorSpaceM3138getColorSpaceimpl);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(colorSpaceM3138getColorSpaceimpl);
            composer.updateRememberedValue(objRememberedValue);
        }
        TwoWayConverter twoWayConverter = (TwoWayConverter) objRememberedValue;
        InputPhase inputPhase9 = (InputPhase) transitionUpdateTransition.getCurrentState();
        composer.startReplaceGroup(-12973394);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-12973394, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:441)");
        }
        long j5 = iArr[inputPhase9.ordinal()] == 1 ? j : j2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        Color colorM3124boximpl = Color.m3124boximpl(j5);
        InputPhase inputPhase10 = (InputPhase) transitionUpdateTransition.getTargetState();
        composer.startReplaceGroup(-12973394);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-12973394, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:441)");
        }
        long j6 = iArr[inputPhase10.ordinal()] == 1 ? j : j2;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        State stateCreateTransitionAnimation4 = TransitionKt.createTransitionAnimation(transitionUpdateTransition, colorM3124boximpl, Color.m3124boximpl(j6), textFieldImplKt$TextFieldTransitionScope$labelTextStyleColor$1.invoke(transitionUpdateTransition.getSegment(), composer, 0), twoWayConverter, "LabelTextStyleColor", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE);
        TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1 textFieldImplKt$TextFieldTransitionScope$labelContentColor$1 = new TextFieldImplKt$TextFieldTransitionScope$labelContentColor$1(finiteAnimationSpecValue2);
        composer.startReplaceGroup(-464752477);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-464752477, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:452)");
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        ColorSpace colorSpaceM3138getColorSpaceimpl2 = Color.m3138getColorSpaceimpl(j3);
        boolean zChanged2 = composer.changed(colorSpaceM3138getColorSpaceimpl2);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(colorSpaceM3138getColorSpaceimpl2);
            composer.updateRememberedValue(objRememberedValue2);
        }
        TwoWayConverter twoWayConverter2 = (TwoWayConverter) objRememberedValue2;
        composer.startReplaceGroup(-464752477);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-464752477, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:452)");
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        Color colorM3124boximpl2 = Color.m3124boximpl(j3);
        composer.startReplaceGroup(-464752477);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-464752477, 0, -1, "androidx.compose.material3.internal.TextFieldTransitionScope.<anonymous> (TextFieldImpl.kt:452)");
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        function7.invoke(stateCreateTransitionAnimation, stateCreateTransitionAnimation4, TransitionKt.createTransitionAnimation(transitionUpdateTransition, colorM3124boximpl2, Color.m3124boximpl(j3), textFieldImplKt$TextFieldTransitionScope$labelContentColor$1.invoke(transitionUpdateTransition.getSegment(), composer, 0), twoWayConverter2, "LabelContentColor", composer, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE), stateCreateTransitionAnimation2, stateCreateTransitionAnimation3, composer, Integer.valueOf(i & 458752));
    }

    public static Unit a(TextFieldType textFieldType, CharSequence charSequence, Function2 function2, TextFieldLabelPosition textFieldLabelPosition, Function3 function3, Function2 function4, Function2 function5, Function2 function6, Function2 function7, Function2 function8, Function2 function9, boolean z, boolean z2, boolean z3, InteractionSource interactionSource, PaddingValues paddingValues, TextFieldColors textFieldColors, Function2 function10, int i, int i2, Composer composer, int i3) {
        CommonDecorationBox(textFieldType, charSequence, function2, textFieldLabelPosition, function3, function4, function5, function6, function7, function8, function9, z, z2, z3, interactionSource, paddingValues, textFieldColors, function10, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: animateBorderStrokeAsState-NuRrP5Q, reason: not valid java name */
    public static final State<BorderStroke> m1478animateBorderStrokeAsStateNuRrP5Q(boolean z, boolean z2, boolean z3, TextFieldColors textFieldColors, float f, float f2, Composer composer, int i) {
        State stateRememberUpdatedState;
        State stateRememberUpdatedState2;
        Composer composer2 = composer;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2047013045, i, -1, "androidx.compose.material3.internal.animateBorderStrokeAsState (TextFieldImpl.kt:472)");
        }
        long jM1057indicatorColorXeAY9LY$material3 = textFieldColors.m1057indicatorColorXeAY9LY$material3(z, z2, z3);
        FiniteAnimationSpec finiteAnimationSpecValue = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, composer2, 6);
        if (z) {
            composer2.startReplaceGroup(-1674507999);
            stateRememberUpdatedState = SingleValueAnimationKt.animateColorAsState-euL9pac(jM1057indicatorColorXeAY9LY$material3, finiteAnimationSpecValue, (String) null, (Function1) null, composer, 0, 12);
            composer2 = composer;
            composer2.endReplaceGroup();
        } else {
            composer2.startReplaceGroup(-1674427244);
            stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m3124boximpl(jM1057indicatorColorXeAY9LY$material3), composer2, 0);
            composer2.endReplaceGroup();
        }
        State state = stateRememberUpdatedState;
        FiniteAnimationSpec finiteAnimationSpecValue2 = MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composer2, 6);
        if (z) {
            composer2.startReplaceGroup(-1674245832);
            stateRememberUpdatedState2 = AnimateAsStateKt.animateDpAsState-AjpBEmI(z3 ? f : f2, finiteAnimationSpecValue2, (String) null, (Function1) null, composer2, 0, 12);
            composer2.endReplaceGroup();
        } else {
            composer2.startReplaceGroup(-1674063769);
            stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(Dp.m6020boximpl(f2), composer2, (i >> 15) & 14);
            composer2.endReplaceGroup();
        }
        State<BorderStroke> stateRememberUpdatedState3 = SnapshotStateKt.rememberUpdatedState(BorderStrokeKt.m12BorderStrokecXLIe8U(((Dp) stateRememberUpdatedState2.getValue()).m6036unboximpl(), ((Color) state.getValue()).m3144unboximpl()), composer2, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return stateRememberUpdatedState3;
    }

    public static Unit b(Placeable placeable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static Unit c(Outline outline, ColorProducer colorProducer, DrawScope drawScope) {
        OutlineKt.m3407drawOutlinewDX37Ww$default(drawScope, outline, colorProducer.mo403invoke0d7_KjU(), 0.0f, null, null, 0, 60, null);
        return Unit.INSTANCE;
    }

    public static Unit d(long j, Function2 function2, int i, Composer composer, int i2) {
        m1474DecorationIv8Zu3U(j, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final Modifier defaultErrorSemantics(Modifier modifier, boolean z, final String str) {
        return z ? SemanticsModifierKt.semantics$default(modifier, false, new Function1() { // from class: c7e
            public final Object invoke(Object obj) {
                return TextFieldImplKt.g(str, (SemanticsPropertyReceiver) obj);
            }
        }, 1, null) : modifier;
    }

    public static Unit e(long j, TextStyle textStyle, Function2 function2, int i, Composer composer, int i2) {
        m1473Decoration3JVO9M(j, textStyle, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static boolean f(State state) {
        return ((Number) state.getValue()).floatValue() > 0.0f;
    }

    public static Unit g(String str, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        SemanticsPropertiesKt.error(semanticsPropertyReceiver, str);
        return Unit.INSTANCE;
    }

    public static final float getAboveLabelBottomPadding() {
        return AboveLabelBottomPadding;
    }

    public static final float getAboveLabelHorizontalPadding() {
        return AboveLabelHorizontalPadding;
    }

    public static final Alignment.Horizontal getExpandedAlignment(TextFieldLabelPosition textFieldLabelPosition) {
        if (textFieldLabelPosition instanceof TextFieldLabelPosition.Above) {
            return ((TextFieldLabelPosition.Above) textFieldLabelPosition).getAlignment();
        }
        if (textFieldLabelPosition instanceof TextFieldLabelPosition.Attached) {
            return ((TextFieldLabelPosition.Attached) textFieldLabelPosition).getExpandedAlignment();
        }
        aca.a("Unknown position: ", textFieldLabelPosition);
        return null;
    }

    public static final float getMinFocusedLabelLineHeight() {
        return MinFocusedLabelLineHeight;
    }

    public static final float getMinSupportingTextLineHeight() {
        return MinSupportingTextLineHeight;
    }

    public static final float getMinTextLineHeight() {
        return MinTextLineHeight;
    }

    public static final Alignment.Horizontal getMinimizedAlignment(TextFieldLabelPosition textFieldLabelPosition) {
        if (textFieldLabelPosition instanceof TextFieldLabelPosition.Above) {
            return ((TextFieldLabelPosition.Above) textFieldLabelPosition).getAlignment();
        }
        if (textFieldLabelPosition instanceof TextFieldLabelPosition.Attached) {
            return ((TextFieldLabelPosition.Attached) textFieldLabelPosition).getMinimizedAlignment();
        }
        aca.a("Unknown position: ", textFieldLabelPosition);
        return null;
    }

    public static final float getPrefixSuffixTextPadding() {
        return PrefixSuffixTextPadding;
    }

    private static final boolean getShowExpandedLabel(TextFieldLabelPosition textFieldLabelPosition) {
        return (textFieldLabelPosition instanceof TextFieldLabelPosition.Attached) && !((TextFieldLabelPosition.Attached) textFieldLabelPosition).getAlwaysMinimize();
    }

    public static final float getSupportingTopPadding() {
        return SupportingTopPadding;
    }

    public static final float getTextFieldPadding() {
        return TextFieldPadding;
    }

    public static DrawResult h(Shape shape, final ColorProducer colorProducer, CacheDrawScope cacheDrawScope) {
        final Outline outlineMo44createOutlinePq9zytI = shape.mo44createOutlinePq9zytI(cacheDrawScope.m2664getSizeNHjbRc(), cacheDrawScope.getLayoutDirection(), cacheDrawScope);
        return cacheDrawScope.onDrawBehind(new Function1() { // from class: d7e
            public final Object invoke(Object obj) {
                return TextFieldImplKt.c(outlineMo44createOutlinePq9zytI, colorProducer, (DrawScope) obj);
            }
        });
    }

    public static Unit i(TextFieldLabelPosition textFieldLabelPosition, State state, MutableState mutableState, Size size) {
        if (textFieldLabelPosition instanceof TextFieldLabelPosition.Above) {
            return Unit.INSTANCE;
        }
        float fFloatValue = ((Number) state.getValue()).floatValue();
        float fIntBitsToFloat = Float.intBitsToFloat((int) (size.m2963unboximpl() >> 32)) * fFloatValue;
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (size.m2963unboximpl() & 4294967295L)) * fFloatValue;
        if (Float.intBitsToFloat((int) (((Size) mutableState.getValue()).m2963unboximpl() >> 32)) != fIntBitsToFloat || Float.intBitsToFloat((int) (((Size) mutableState.getValue()).m2963unboximpl() & 4294967295L)) != fIntBitsToFloat2) {
            mutableState.setValue(Size.m2946boximpl(Size.m2949constructorimpl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & 4294967295L))));
        }
        return Unit.INSTANCE;
    }

    public static MeasureResult j(Function0 function0, MeasureScope measureScope, Measurable measurable, Constraints constraints) {
        float fM6036unboximpl = ((Dp) function0.invoke()).m6036unboximpl();
        final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(constraints.getValue(), 0, 0, ConstraintsKt.m5991constrainHeightK40F9xA(constraints.getValue(), !Dp.m6027equalsimpl0(fM6036unboximpl, Dp.INSTANCE.m6042getUnspecifiedD9Ej5fM()) ? measureScope.mo4551roundToPx0680j_4(fM6036unboximpl) : 0), 0, 11, null));
        return MeasureScope.layout$default(measureScope, placeableMo4605measureBRTryo0.getWidth(), placeableMo4605measureBRTryo0.getHeight(), null, new Function1() { // from class: v6e
            public final Object invoke(Object obj) {
                return TextFieldImplKt.b(placeableMo4605measureBRTryo0, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    public static boolean k(State state) {
        return ((Number) state.getValue()).floatValue() > 0.0f;
    }

    public static final float minimizedLabelHalfHeight(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1251545215, i, -1, "androidx.compose.material3.internal.minimizedLabelHalfHeight (TextFieldImpl.kt:527)");
        }
        long jM5518getLineHeightXSAIIZE = MaterialTheme.INSTANCE.getTypography(composer, 6).getBodySmall().m5518getLineHeightXSAIIZE();
        long jM2228getBodySmallLineHeightXSAIIZE = TypeScaleTokens.INSTANCE.m2228getBodySmallLineHeightXSAIIZE();
        if (!TextUnit.m6219isSpimpl(jM5518getLineHeightXSAIIZE)) {
            jM5518getLineHeightXSAIIZE = jM2228getBodySmallLineHeightXSAIIZE;
        }
        float fM6022constructorimpl = Dp.m6022constructorimpl(((Density) composer.consume(CompositionLocalsKt.getLocalDensity())).mo4552toDpGaN1DYA(jM5518getLineHeightXSAIIZE) / 2.0f);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return fM6022constructorimpl;
    }

    public static final Modifier textFieldBackground(Modifier modifier, final ColorProducer colorProducer, final Shape shape) {
        return DrawModifierKt.drawWithCache(modifier, new Function1() { // from class: e7e
            public final Object invoke(Object obj) {
                return TextFieldImplKt.h(shape, colorProducer, (CacheDrawScope) obj);
            }
        });
    }

    public static final float textFieldHorizontalIconPadding(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1986450462, i, -1, "androidx.compose.material3.internal.textFieldHorizontalIconPadding (TextFieldImpl.kt:520)");
        }
        float fM6036unboximpl = ((Dp) composer.consume(InteractiveComponentSizeKt.getLocalMinimumInteractiveComponentSize())).m6036unboximpl();
        if (Float.isNaN(fM6036unboximpl)) {
            fM6036unboximpl = Dp.m6022constructorimpl(0.0f);
        }
        float fM6022constructorimpl = Dp.m6022constructorimpl(RangesKt.coerceAtLeast(Dp.m6022constructorimpl(Dp.m6022constructorimpl(fM6036unboximpl - SmallIconButtonTokens.INSTANCE.m2119getIconSizeD9Ej5fM()) / 2.0f), Dp.m6022constructorimpl(0.0f)));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return fM6022constructorimpl;
    }

    public static final Modifier textFieldLabelMinHeight(Modifier modifier, final Function0<Dp> function0) {
        return LayoutModifierKt.layout(modifier, new Function3() { // from class: b7e
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return TextFieldImplKt.j(function0, (MeasureScope) obj, (Measurable) obj2, (Constraints) obj3);
            }
        });
    }
}
