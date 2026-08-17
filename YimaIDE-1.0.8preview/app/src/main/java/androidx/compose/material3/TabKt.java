package androidx.compose.material3;

import androidx.compose.animation.ColorVectorConverterKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.Transition;
import androidx.compose.animation.core.TransitionKt;
import androidx.compose.animation.core.TwoWayConverter;
import androidx.compose.foundation.IndicationNodeFactory;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.selection.SelectableKt;
import androidx.compose.material3.TabKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.PrimaryNavigationTabTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.colorspace.ColorSpace;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000p\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0087\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\n2\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a\u007f\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0011\u0010\t\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\n2\u0011\u0010\u000b\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\n2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001aw\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00010\u0017¢\u0006\u0002\b\n¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0004\b\u001a\u0010\u001b\u001a:\u0010\u001c\u001a\u00020\u00012\u0006\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00032\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\nH\u0003¢\u0006\u0004\b\u001f\u0010 \u001a7\u0010!\u001a\u00020\u00012\u0013\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\n2\u0013\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\b\nH\u0003¢\u0006\u0002\u0010\"\u001a\u001c\u0010#\u001a\u00020\u0001*\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0002\u001aD\u0010)\u001a\u00020\u0001*\u00020$2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020&2\u0006\u0010.\u001a\u00020(2\u0006\u0010'\u001a\u00020(2\u0006\u0010/\u001a\u00020(2\u0006\u00100\u001a\u00020(H\u0002\"\u0010\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0004\n\u0002\u00103\"\u0010\u00104\u001a\u000202X\u0082\u0004¢\u0006\u0004\n\u0002\u00103\"\u0016\u00105\u001a\u000202X\u0080\u0004¢\u0006\n\n\u0002\u00103\u001a\u0004\b6\u00107\"\u0010\u00108\u001a\u000202X\u0082\u0004¢\u0006\u0004\n\u0002\u00103\"\u0010\u00109\u001a\u000202X\u0082\u0004¢\u0006\u0004\n\u0002\u00103\"\u0010\u0010:\u001a\u00020;X\u0082\u0004¢\u0006\u0004\n\u0002\u0010<\"\u0010\u0010=\u001a\u000202X\u0082\u0004¢\u0006\u0004\n\u0002\u00103¨\u0006>²\u0006\n\u0010?\u001a\u00020\rX\u008a\u0084\u0002"}, d2 = {"Tab", "", "selected", "", "onClick", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "text", "Landroidx/compose/runtime/Composable;", "icon", "selectedContentColor", "Landroidx/compose/ui/graphics/Color;", "unselectedContentColor", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "Tab-wqdebIU", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;JJLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "LeadingIconTab", "LeadingIconTab-wqdebIU", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZJJLandroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "Tab-bogVsAg", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZJJLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "TabTransition", "activeColor", "inactiveColor", "TabTransition-Klgx-Pg", "(JJZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "TabBaselineLayout", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "placeTextOrIcon", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "textOrIconPlaceable", "Landroidx/compose/ui/layout/Placeable;", "tabHeight", "", "placeTextAndIcon", "density", "Landroidx/compose/ui/unit/Density;", "textPlaceable", "iconPlaceable", "tabWidth", "firstBaseline", "lastBaseline", "SmallTabHeight", "Landroidx/compose/ui/unit/Dp;", "F", "LargeTabHeight", "HorizontalTextPadding", "getHorizontalTextPadding", "()F", "SingleLineTextBaselineWithIcon", "DoubleLineTextBaselineWithIcon", "IconDistanceFromBaseline", "Landroidx/compose/ui/unit/TextUnit;", "J", "TextDistanceFromLeadingIcon", "material3", "color"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TabKt {
    private static final float SmallTabHeight = PrimaryNavigationTabTokens.INSTANCE.m2080getContainerHeightD9Ej5fM();
    private static final float LargeTabHeight = Dp.m6022constructorimpl(72.0f);
    private static final float HorizontalTextPadding = Dp.m6022constructorimpl(16.0f);
    private static final float SingleLineTextBaselineWithIcon = Dp.m6022constructorimpl(14.0f);
    private static final float DoubleLineTextBaselineWithIcon = Dp.m6022constructorimpl(6.0f);
    private static final long IconDistanceFromBaseline = TextUnitKt.getSp(20);
    private static final float TextDistanceFromLeadingIcon = Dp.m6022constructorimpl(8.0f);

    /* JADX WARN: Code duplicated, block: B:100:0x0117  */
    /* JADX WARN: Code duplicated, block: B:101:0x0119  */
    /* JADX WARN: Code duplicated, block: B:104:0x0122  */
    /* JADX WARN: Code duplicated, block: B:106:0x012f  */
    /* JADX WARN: Code duplicated, block: B:116:0x0146 A[PHI: r7 r10 r11 r14 r18
      0x0146: PHI (r7v9 androidx.compose.ui.Modifier) = (r7v4 androidx.compose.ui.Modifier), (r7v2 androidx.compose.ui.Modifier) binds: [B:129:0x0173, B:115:0x0144] A[DONT_GENERATE, DONT_INLINE]
      0x0146: PHI (r10v11 boolean) = (r10v4 boolean), (r10v3 boolean) binds: [B:129:0x0173, B:115:0x0144] A[DONT_GENERATE, DONT_INLINE]
      0x0146: PHI (r11v24 int) = (r11v15 int), (r11v26 int) binds: [B:129:0x0173, B:115:0x0144] A[DONT_GENERATE, DONT_INLINE]
      0x0146: PHI (r14v7 long) = (r14v4 long), (r14v1 long) binds: [B:129:0x0173, B:115:0x0144] A[DONT_GENERATE, DONT_INLINE]
      0x0146: PHI (r18v10 long) = (r18v7 long), (r18v11 long) binds: [B:129:0x0173, B:115:0x0144] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:118:0x014b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:119:0x014d  */
    /* JADX WARN: Code duplicated, block: B:121:0x0152  */
    /* JADX WARN: Code duplicated, block: B:124:0x0157  */
    /* JADX WARN: Code duplicated, block: B:127:0x016b  */
    /* JADX WARN: Code duplicated, block: B:128:0x0171  */
    /* JADX WARN: Code duplicated, block: B:130:0x0175  */
    /* JADX WARN: Code duplicated, block: B:133:0x0181  */
    /* JADX WARN: Code duplicated, block: B:136:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:139:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:142:0x01f3  */
    /* JADX WARN: Code duplicated, block: B:144:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0062  */
    /* JADX WARN: Code duplicated, block: B:38:0x0067  */
    /* JADX WARN: Code duplicated, block: B:40:0x006b  */
    /* JADX WARN: Code duplicated, block: B:42:0x0073  */
    /* JADX WARN: Code duplicated, block: B:43:0x0076  */
    /* JADX WARN: Code duplicated, block: B:47:0x007d  */
    /* JADX WARN: Code duplicated, block: B:49:0x0082  */
    /* JADX WARN: Code duplicated, block: B:51:0x0086  */
    /* JADX WARN: Code duplicated, block: B:53:0x008e  */
    /* JADX WARN: Code duplicated, block: B:54:0x0091  */
    /* JADX WARN: Code duplicated, block: B:58:0x009a  */
    /* JADX WARN: Code duplicated, block: B:60:0x009e  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:71:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:76:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:79:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:81:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:84:0x00df  */
    /* JADX WARN: Code duplicated, block: B:86:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:89:0x00f0  */
    /* JADX WARN: Code duplicated, block: B:91:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:93:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:95:0x0105  */
    /* JADX WARN: Code duplicated, block: B:96:0x0108  */
    /* JADX INFO: renamed from: LeadingIconTab-wqdebIU, reason: not valid java name */
    public static final void m986LeadingIconTabwqdebIU(final boolean z, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, Modifier modifier, boolean z2, long j, long j2, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        final boolean z3;
        int i3;
        final Function0<Unit> function1;
        Function2<? super Composer, ? super Integer, Unit> function4;
        int i4;
        int i5;
        Modifier modifier2;
        int i6;
        int i7;
        boolean z4;
        int i8;
        long jM3144unboximpl;
        int i9;
        int i10;
        int i11;
        boolean z5;
        Composer composer2;
        final MutableInteractionSource mutableInteractionSource2;
        final Modifier modifier3;
        final boolean z6;
        final long j3;
        final long j4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        long j5;
        final MutableInteractionSource mutableInteractionSource3;
        int i12;
        int i13;
        int i14;
        Composer composerStartRestartGroup = composer.startRestartGroup(-611535578);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            z3 = z;
        } else {
            z3 = z;
            if ((i & 6) == 0) {
                i3 = (composerStartRestartGroup.changed(z3) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
            function1 = function0;
        } else {
            function1 = function0;
            if ((i & 48) == 0) {
                i3 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
            }
        }
        if ((i2 & 4) == 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                i3 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
            }
            if ((i2 & 8) != 0) {
                if ((i & 3072) == 0) {
                    function4 = function3;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i4 = 2048;
                    } else {
                        i4 = 1024;
                    }
                    i3 |= i4;
                }
                i5 = i2 & 16;
                if (i5 != 0) {
                    if ((i & 24576) == 0) {
                        modifier2 = modifier;
                        if (composerStartRestartGroup.changed(modifier2)) {
                            i6 = 16384;
                        } else {
                            i6 = 8192;
                        }
                        i3 |= i6;
                    }
                    i7 = i2 & 32;
                    if (i7 != 0) {
                        if ((196608 & i) == 0) {
                            z4 = z2;
                            if (composerStartRestartGroup.changed(z4)) {
                                i8 = 131072;
                            } else {
                                i8 = 65536;
                            }
                            i3 |= i8;
                        }
                        if ((1572864 & i) == 0) {
                            jM3144unboximpl = j;
                            if ((i2 & 64) == 0 || !composerStartRestartGroup.changed(jM3144unboximpl)) {
                                i14 = 524288;
                            } else {
                                i14 = 1048576;
                            }
                            i3 |= i14;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((12582912 & i) == 0) {
                            int i15 = i3;
                            if ((i2 & 128) == 0 || !composerStartRestartGroup.changed(j2)) {
                                i13 = 4194304;
                            } else {
                                i13 = 8388608;
                            }
                            i9 = i15 | i13;
                        } else {
                            i9 = i3;
                        }
                        i10 = i2 & 256;
                        if (i10 != 0) {
                            if ((i & 100663296) == 0) {
                                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                    i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                                } else {
                                    i11 = 33554432;
                                }
                                i9 |= i11;
                            }
                            if ((i9 & 38347923) != 38347922) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                                composerStartRestartGroup.startDefaults();
                                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                    if (i5 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i7 != 0) {
                                        z4 = true;
                                    }
                                    if ((i2 & 64) != 0) {
                                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                        i9 &= -3670017;
                                    }
                                    if ((i2 & 128) != 0) {
                                        i9 &= -29360129;
                                        j5 = jM3144unboximpl;
                                    } else {
                                        j5 = j2;
                                    }
                                    if (i10 != 0) {
                                        mutableInteractionSource3 = null;
                                    }
                                    final boolean z7 = z4;
                                    i12 = i9;
                                    composerStartRestartGroup.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                                    }
                                    final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                                    final Modifier modifier4 = modifier2;
                                    final Function2<? super Composer, ? super Integer, Unit> function5 = function4;
                                    int i16 = i12 >> 18;
                                    m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                                        public final void invoke(Composer composer3, int i17) {
                                            if (!composer3.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                                composer3.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(1831009258, i17, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                                            }
                                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier4, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default, z7, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                            Function2<Composer, Integer, Unit> function6 = function5;
                                            Function2<Composer, Integer, Unit> function7 = function2;
                                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                                            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                            Function0<ComposeUiNode> constructor = companion.getConstructor();
                                            if (composer3.getApplier() == null) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer3.startReusableNode();
                                            if (composer3.getInserting()) {
                                                composer3.createNode(constructor);
                                            } else {
                                                composer3.useNode();
                                            }
                                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                            }
                                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                            function6.invoke(composer3, 0);
                                            SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                                            TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function7, composer3, 0);
                                            composer3.endNode();
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }

                                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                            invoke((Composer) obj, ((Number) obj2).intValue());
                                            return Unit.INSTANCE;
                                        }
                                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i16 & 112) | (i16 & 14) | 3072);
                                    composer2 = composerStartRestartGroup;
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    modifier3 = modifier4;
                                    mutableInteractionSource2 = mutableInteractionSource3;
                                    z6 = z7;
                                    j3 = j5;
                                } else {
                                    composerStartRestartGroup.skipToGroupEnd();
                                    if ((i2 & 64) != 0) {
                                        i9 &= -3670017;
                                    }
                                    if ((i2 & 128) != 0) {
                                        i9 &= -29360129;
                                    }
                                    j5 = j2;
                                }
                                mutableInteractionSource3 = mutableInteractionSource;
                                final boolean z8 = z4;
                                i12 = i9;
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                                }
                                final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default2 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                                final Modifier modifier5 = modifier2;
                                final Function2<? super Composer, ? super Integer, Unit> function6 = function4;
                                int i17 = i12 >> 18;
                                m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                                    public final void invoke(Composer composer3, int i18) {
                                        if (!composer3.shouldExecute((i18 & 3) != 2, i18 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1831009258, i18, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                                        }
                                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier5, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default2, z8, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                        Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                        Function2<Composer, Integer, Unit> function7 = function6;
                                        Function2<Composer, Integer, Unit> function8 = function2;
                                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                                        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                        Function0<ComposeUiNode> constructor = companion.getConstructor();
                                        if (composer3.getApplier() == null) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer3.startReusableNode();
                                        if (composer3.getInserting()) {
                                            composer3.createNode(constructor);
                                        } else {
                                            composer3.useNode();
                                        }
                                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                        function7.invoke(composer3, 0);
                                        SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                                        TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function8, composer3, 0);
                                        composer3.endNode();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i17 & 112) | (i17 & 14) | 3072);
                                composer2 = composerStartRestartGroup;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier3 = modifier5;
                                mutableInteractionSource2 = mutableInteractionSource3;
                                z6 = z8;
                                j3 = j5;
                            } else {
                                composer2 = composerStartRestartGroup;
                                composer2.skipToGroupEnd();
                                mutableInteractionSource2 = mutableInteractionSource;
                                modifier3 = modifier2;
                                z6 = z4;
                                j3 = j2;
                            }
                            j4 = jM3144unboximpl;
                            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                                    public final Object invoke(Object obj, Object obj2) {
                                        return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i9 |= 100663296;
                        if ((i9 & 38347923) != 38347922) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    z4 = true;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i9 &= -3670017;
                                }
                                if ((i2 & 128) != 0) {
                                    i9 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    z4 = true;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i9 &= -3670017;
                                }
                                if ((i2 & 128) != 0) {
                                    i9 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            }
                            final boolean z9 = z4;
                            i12 = i9;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                            }
                            final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default3 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                            final Modifier modifier6 = modifier2;
                            final Function2<? super Composer, ? super Integer, Unit> function7 = function4;
                            int i18 = i12 >> 18;
                            m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                                public final void invoke(Composer composer3, int i19) {
                                    if (!composer3.shouldExecute((i19 & 3) != 2, i19 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1831009258, i19, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                                    }
                                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier6, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default3, z9, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                                    Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                    Function2<Composer, Integer, Unit> function8 = function7;
                                    Function2<Composer, Integer, Unit> function9 = function2;
                                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                                    ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                    Function0<ComposeUiNode> constructor = companion.getConstructor();
                                    if (composer3.getApplier() == null) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        composer3.createNode(constructor);
                                    } else {
                                        composer3.useNode();
                                    }
                                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                    function8.invoke(composer3, 0);
                                    SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                                    TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function9, composer3, 0);
                                    composer3.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i18 & 112) | (i18 & 14) | 3072);
                            composer2 = composerStartRestartGroup;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier6;
                            mutableInteractionSource2 = mutableInteractionSource3;
                            z6 = z9;
                            j3 = j5;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            mutableInteractionSource2 = mutableInteractionSource;
                            modifier3 = modifier2;
                            z6 = z4;
                            j3 = j2;
                        }
                        j4 = jM3144unboximpl;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                                public final Object invoke(Object obj, Object obj2) {
                                    return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    z4 = z2;
                    if ((1572864 & i) == 0) {
                        jM3144unboximpl = j;
                        if ((i2 & 64) == 0) {
                            i14 = 524288;
                        } else {
                            i14 = 524288;
                        }
                        i3 |= i14;
                    } else {
                        jM3144unboximpl = j;
                    }
                    if ((12582912 & i) == 0) {
                        int i19 = i3;
                        if ((i2 & 128) == 0) {
                            i13 = 4194304;
                        } else {
                            i13 = 4194304;
                        }
                        i9 = i19 | i13;
                    } else {
                        i9 = i3;
                    }
                    i10 = i2 & 256;
                    if (i10 != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i11 = 33554432;
                            }
                            i9 |= i11;
                        }
                        if ((i9 & 38347923) != 38347922) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    z4 = true;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i9 &= -3670017;
                                }
                                if ((i2 & 128) != 0) {
                                    i9 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    z4 = true;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i9 &= -3670017;
                                }
                                if ((i2 & 128) != 0) {
                                    i9 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            }
                            final boolean z10 = z4;
                            i12 = i9;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                            }
                            final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default4 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                            final Modifier modifier7 = modifier2;
                            final Function2<? super Composer, ? super Integer, Unit> function8 = function4;
                            int i110 = i12 >> 18;
                            m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                                public final void invoke(Composer composer3, int i111) {
                                    if (!composer3.shouldExecute((i111 & 3) != 2, i111 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1831009258, i111, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                                    }
                                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier7, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default4, z10, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                                    Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                    Function2<Composer, Integer, Unit> function9 = function8;
                                    Function2<Composer, Integer, Unit> function10 = function2;
                                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                                    ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                    Function0<ComposeUiNode> constructor = companion.getConstructor();
                                    if (composer3.getApplier() == null) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        composer3.createNode(constructor);
                                    } else {
                                        composer3.useNode();
                                    }
                                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                    function9.invoke(composer3, 0);
                                    SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                                    TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function10, composer3, 0);
                                    composer3.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i110 & 112) | (i110 & 14) | 3072);
                            composer2 = composerStartRestartGroup;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier7;
                            mutableInteractionSource2 = mutableInteractionSource3;
                            z6 = z10;
                            j3 = j5;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            mutableInteractionSource2 = mutableInteractionSource;
                            modifier3 = modifier2;
                            z6 = z4;
                            j3 = j2;
                        }
                        j4 = jM3144unboximpl;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                                public final Object invoke(Object obj, Object obj2) {
                                    return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i9 |= 100663296;
                    if ((i9 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        final boolean z11 = z4;
                        i12 = i9;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                        }
                        final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default5 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                        final Modifier modifier8 = modifier2;
                        final Function2<? super Composer, ? super Integer, Unit> function9 = function4;
                        int i111 = i12 >> 18;
                        m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                            public final void invoke(Composer composer3, int i112) {
                                if (!composer3.shouldExecute((i112 & 3) != 2, i112 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1831009258, i112, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                                }
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier8, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default5, z11, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                                Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                Function2<Composer, Integer, Unit> function10 = function9;
                                Function2<Composer, Integer, Unit> function11 = function2;
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion.getConstructor();
                                if (composer3.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer3.startReusableNode();
                                if (composer3.getInserting()) {
                                    composer3.createNode(constructor);
                                } else {
                                    composer3.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                function10.invoke(composer3, 0);
                                SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function11, composer3, 0);
                                composer3.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i111 & 112) | (i111 & 14) | 3072);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier8;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        z6 = z11;
                        j3 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        j3 = j2;
                    }
                    j4 = jM3144unboximpl;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                modifier2 = modifier;
                i7 = i2 & 32;
                if (i7 != 0) {
                    if ((196608 & i) == 0) {
                        z4 = z2;
                        if (composerStartRestartGroup.changed(z4)) {
                            i8 = 131072;
                        } else {
                            i8 = 65536;
                        }
                        i3 |= i8;
                    }
                    if ((1572864 & i) == 0) {
                        jM3144unboximpl = j;
                        if ((i2 & 64) == 0) {
                            i14 = 524288;
                        } else {
                            i14 = 524288;
                        }
                        i3 |= i14;
                    } else {
                        jM3144unboximpl = j;
                    }
                    if ((12582912 & i) == 0) {
                        int i112 = i3;
                        if ((i2 & 128) == 0) {
                            i13 = 4194304;
                        } else {
                            i13 = 4194304;
                        }
                        i9 = i112 | i13;
                    } else {
                        i9 = i3;
                    }
                    i10 = i2 & 256;
                    if (i10 != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i11 = 33554432;
                            }
                            i9 |= i11;
                        }
                        if ((i9 & 38347923) != 38347922) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    z4 = true;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i9 &= -3670017;
                                }
                                if ((i2 & 128) != 0) {
                                    i9 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    z4 = true;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i9 &= -3670017;
                                }
                                if ((i2 & 128) != 0) {
                                    i9 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            }
                            final boolean z12 = z4;
                            i12 = i9;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                            }
                            final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default6 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                            final Modifier modifier9 = modifier2;
                            final Function2<? super Composer, ? super Integer, Unit> function10 = function4;
                            int i113 = i12 >> 18;
                            m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                                public final void invoke(Composer composer3, int i114) {
                                    if (!composer3.shouldExecute((i114 & 3) != 2, i114 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1831009258, i114, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                                    }
                                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier9, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default6, z12, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                                    Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                    Function2<Composer, Integer, Unit> function11 = function10;
                                    Function2<Composer, Integer, Unit> function12 = function2;
                                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                                    ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                    Function0<ComposeUiNode> constructor = companion.getConstructor();
                                    if (composer3.getApplier() == null) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        composer3.createNode(constructor);
                                    } else {
                                        composer3.useNode();
                                    }
                                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                    function11.invoke(composer3, 0);
                                    SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                                    TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function12, composer3, 0);
                                    composer3.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i113 & 112) | (i113 & 14) | 3072);
                            composer2 = composerStartRestartGroup;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier9;
                            mutableInteractionSource2 = mutableInteractionSource3;
                            z6 = z12;
                            j3 = j5;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            mutableInteractionSource2 = mutableInteractionSource;
                            modifier3 = modifier2;
                            z6 = z4;
                            j3 = j2;
                        }
                        j4 = jM3144unboximpl;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                                public final Object invoke(Object obj, Object obj2) {
                                    return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i9 |= 100663296;
                    if ((i9 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        final boolean z13 = z4;
                        i12 = i9;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                        }
                        final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default7 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                        final Modifier modifier10 = modifier2;
                        final Function2<? super Composer, ? super Integer, Unit> function11 = function4;
                        int i114 = i12 >> 18;
                        m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                            public final void invoke(Composer composer3, int i115) {
                                if (!composer3.shouldExecute((i115 & 3) != 2, i115 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1831009258, i115, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                                }
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier10, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default7, z13, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                                Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                Function2<Composer, Integer, Unit> function12 = function11;
                                Function2<Composer, Integer, Unit> function13 = function2;
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion.getConstructor();
                                if (composer3.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer3.startReusableNode();
                                if (composer3.getInserting()) {
                                    composer3.createNode(constructor);
                                } else {
                                    composer3.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                function12.invoke(composer3, 0);
                                SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function13, composer3, 0);
                                composer3.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i114 & 112) | (i114 & 14) | 3072);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier10;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        z6 = z13;
                        j3 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        j3 = j2;
                    }
                    j4 = jM3144unboximpl;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                z4 = z2;
                if ((1572864 & i) == 0) {
                    jM3144unboximpl = j;
                    if ((i2 & 64) == 0) {
                        i14 = 524288;
                    } else {
                        i14 = 524288;
                    }
                    i3 |= i14;
                } else {
                    jM3144unboximpl = j;
                }
                if ((12582912 & i) == 0) {
                    int i115 = i3;
                    if ((i2 & 128) == 0) {
                        i13 = 4194304;
                    } else {
                        i13 = 4194304;
                    }
                    i9 = i115 | i13;
                } else {
                    i9 = i3;
                }
                i10 = i2 & 256;
                if (i10 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i11 = 33554432;
                        }
                        i9 |= i11;
                    }
                    if ((i9 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        final boolean z14 = z4;
                        i12 = i9;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                        }
                        final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default8 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                        final Modifier modifier11 = modifier2;
                        final Function2<? super Composer, ? super Integer, Unit> function12 = function4;
                        int i116 = i12 >> 18;
                        m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                            public final void invoke(Composer composer3, int i117) {
                                if (!composer3.shouldExecute((i117 & 3) != 2, i117 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1831009258, i117, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                                }
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier11, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default8, z14, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                                Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                Function2<Composer, Integer, Unit> function13 = function12;
                                Function2<Composer, Integer, Unit> function14 = function2;
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion.getConstructor();
                                if (composer3.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer3.startReusableNode();
                                if (composer3.getInserting()) {
                                    composer3.createNode(constructor);
                                } else {
                                    composer3.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                function13.invoke(composer3, 0);
                                SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function14, composer3, 0);
                                composer3.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i116 & 112) | (i116 & 14) | 3072);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier11;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        z6 = z14;
                        j3 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        j3 = j2;
                    }
                    j4 = jM3144unboximpl;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i9 |= 100663296;
                if ((i9 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    final boolean z15 = z4;
                    i12 = i9;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                    }
                    final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default9 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                    final Modifier modifier12 = modifier2;
                    final Function2<? super Composer, ? super Integer, Unit> function13 = function4;
                    int i117 = i12 >> 18;
                    m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                        public final void invoke(Composer composer3, int i118) {
                            if (!composer3.shouldExecute((i118 & 3) != 2, i118 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1831009258, i118, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                            }
                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier12, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default9, z15, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            Function2<Composer, Integer, Unit> function14 = function13;
                            Function2<Composer, Integer, Unit> function15 = function2;
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                            Function0<ComposeUiNode> constructor = companion.getConstructor();
                            if (composer3.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composer3.startReusableNode();
                            if (composer3.getInserting()) {
                                composer3.createNode(constructor);
                            } else {
                                composer3.useNode();
                            }
                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            function14.invoke(composer3, 0);
                            SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                            TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function15, composer3, 0);
                            composer3.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i117 & 112) | (i117 & 14) | 3072);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier12;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    z6 = z15;
                    j3 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    j3 = j2;
                }
                j4 = jM3144unboximpl;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            function4 = function3;
            i5 = i2 & 16;
            if (i5 != 0) {
                if ((i & 24576) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i6 = 16384;
                    } else {
                        i6 = 8192;
                    }
                    i3 |= i6;
                }
                i7 = i2 & 32;
                if (i7 != 0) {
                    if ((196608 & i) == 0) {
                        z4 = z2;
                        if (composerStartRestartGroup.changed(z4)) {
                            i8 = 131072;
                        } else {
                            i8 = 65536;
                        }
                        i3 |= i8;
                    }
                    if ((1572864 & i) == 0) {
                        jM3144unboximpl = j;
                        if ((i2 & 64) == 0) {
                            i14 = 524288;
                        } else {
                            i14 = 524288;
                        }
                        i3 |= i14;
                    } else {
                        jM3144unboximpl = j;
                    }
                    if ((12582912 & i) == 0) {
                        int i118 = i3;
                        if ((i2 & 128) == 0) {
                            i13 = 4194304;
                        } else {
                            i13 = 4194304;
                        }
                        i9 = i118 | i13;
                    } else {
                        i9 = i3;
                    }
                    i10 = i2 & 256;
                    if (i10 != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i11 = 33554432;
                            }
                            i9 |= i11;
                        }
                        if ((i9 & 38347923) != 38347922) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    z4 = true;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i9 &= -3670017;
                                }
                                if ((i2 & 128) != 0) {
                                    i9 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    z4 = true;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i9 &= -3670017;
                                }
                                if ((i2 & 128) != 0) {
                                    i9 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            }
                            final boolean z16 = z4;
                            i12 = i9;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                            }
                            final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default10 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                            final Modifier modifier13 = modifier2;
                            final Function2<? super Composer, ? super Integer, Unit> function14 = function4;
                            int i119 = i12 >> 18;
                            m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                                public final void invoke(Composer composer3, int i1110) {
                                    if (!composer3.shouldExecute((i1110 & 3) != 2, i1110 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1831009258, i1110, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                                    }
                                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier13, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default10, z16, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                                    Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                    Function2<Composer, Integer, Unit> function15 = function14;
                                    Function2<Composer, Integer, Unit> function16 = function2;
                                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                                    ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                    Function0<ComposeUiNode> constructor = companion.getConstructor();
                                    if (composer3.getApplier() == null) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        composer3.createNode(constructor);
                                    } else {
                                        composer3.useNode();
                                    }
                                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                    function15.invoke(composer3, 0);
                                    SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                                    TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function16, composer3, 0);
                                    composer3.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i119 & 112) | (i119 & 14) | 3072);
                            composer2 = composerStartRestartGroup;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier13;
                            mutableInteractionSource2 = mutableInteractionSource3;
                            z6 = z16;
                            j3 = j5;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            mutableInteractionSource2 = mutableInteractionSource;
                            modifier3 = modifier2;
                            z6 = z4;
                            j3 = j2;
                        }
                        j4 = jM3144unboximpl;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                                public final Object invoke(Object obj, Object obj2) {
                                    return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i9 |= 100663296;
                    if ((i9 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        final boolean z17 = z4;
                        i12 = i9;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                        }
                        final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default11 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                        final Modifier modifier14 = modifier2;
                        final Function2<? super Composer, ? super Integer, Unit> function15 = function4;
                        int i1110 = i12 >> 18;
                        m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                            public final void invoke(Composer composer3, int i1111) {
                                if (!composer3.shouldExecute((i1111 & 3) != 2, i1111 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1831009258, i1111, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                                }
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier14, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default11, z17, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                                Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                Function2<Composer, Integer, Unit> function16 = function15;
                                Function2<Composer, Integer, Unit> function17 = function2;
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion.getConstructor();
                                if (composer3.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer3.startReusableNode();
                                if (composer3.getInserting()) {
                                    composer3.createNode(constructor);
                                } else {
                                    composer3.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                function16.invoke(composer3, 0);
                                SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function17, composer3, 0);
                                composer3.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i1110 & 112) | (i1110 & 14) | 3072);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier14;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        z6 = z17;
                        j3 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        j3 = j2;
                    }
                    j4 = jM3144unboximpl;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                z4 = z2;
                if ((1572864 & i) == 0) {
                    jM3144unboximpl = j;
                    if ((i2 & 64) == 0) {
                        i14 = 524288;
                    } else {
                        i14 = 524288;
                    }
                    i3 |= i14;
                } else {
                    jM3144unboximpl = j;
                }
                if ((12582912 & i) == 0) {
                    int i1111 = i3;
                    if ((i2 & 128) == 0) {
                        i13 = 4194304;
                    } else {
                        i13 = 4194304;
                    }
                    i9 = i1111 | i13;
                } else {
                    i9 = i3;
                }
                i10 = i2 & 256;
                if (i10 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i11 = 33554432;
                        }
                        i9 |= i11;
                    }
                    if ((i9 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        final boolean z18 = z4;
                        i12 = i9;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                        }
                        final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default12 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                        final Modifier modifier15 = modifier2;
                        final Function2<? super Composer, ? super Integer, Unit> function16 = function4;
                        int i1112 = i12 >> 18;
                        m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                            public final void invoke(Composer composer3, int i1113) {
                                if (!composer3.shouldExecute((i1113 & 3) != 2, i1113 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1831009258, i1113, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                                }
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier15, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default12, z18, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                                Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                Function2<Composer, Integer, Unit> function17 = function16;
                                Function2<Composer, Integer, Unit> function18 = function2;
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion.getConstructor();
                                if (composer3.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer3.startReusableNode();
                                if (composer3.getInserting()) {
                                    composer3.createNode(constructor);
                                } else {
                                    composer3.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                function17.invoke(composer3, 0);
                                SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function18, composer3, 0);
                                composer3.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i1112 & 112) | (i1112 & 14) | 3072);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier15;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        z6 = z18;
                        j3 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        j3 = j2;
                    }
                    j4 = jM3144unboximpl;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i9 |= 100663296;
                if ((i9 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    final boolean z19 = z4;
                    i12 = i9;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                    }
                    final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default13 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                    final Modifier modifier16 = modifier2;
                    final Function2<? super Composer, ? super Integer, Unit> function17 = function4;
                    int i1113 = i12 >> 18;
                    m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                        public final void invoke(Composer composer3, int i1114) {
                            if (!composer3.shouldExecute((i1114 & 3) != 2, i1114 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1831009258, i1114, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                            }
                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier16, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default13, z19, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            Function2<Composer, Integer, Unit> function18 = function17;
                            Function2<Composer, Integer, Unit> function19 = function2;
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                            Function0<ComposeUiNode> constructor = companion.getConstructor();
                            if (composer3.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composer3.startReusableNode();
                            if (composer3.getInserting()) {
                                composer3.createNode(constructor);
                            } else {
                                composer3.useNode();
                            }
                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            function18.invoke(composer3, 0);
                            SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                            TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function19, composer3, 0);
                            composer3.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i1113 & 112) | (i1113 & 14) | 3072);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier16;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    z6 = z19;
                    j3 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    j3 = j2;
                }
                j4 = jM3144unboximpl;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            modifier2 = modifier;
            i7 = i2 & 32;
            if (i7 != 0) {
                if ((196608 & i) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i8 = 131072;
                    } else {
                        i8 = 65536;
                    }
                    i3 |= i8;
                }
                if ((1572864 & i) == 0) {
                    jM3144unboximpl = j;
                    if ((i2 & 64) == 0) {
                        i14 = 524288;
                    } else {
                        i14 = 524288;
                    }
                    i3 |= i14;
                } else {
                    jM3144unboximpl = j;
                }
                if ((12582912 & i) == 0) {
                    int i1114 = i3;
                    if ((i2 & 128) == 0) {
                        i13 = 4194304;
                    } else {
                        i13 = 4194304;
                    }
                    i9 = i1114 | i13;
                } else {
                    i9 = i3;
                }
                i10 = i2 & 256;
                if (i10 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i11 = 33554432;
                        }
                        i9 |= i11;
                    }
                    if ((i9 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        final boolean z110 = z4;
                        i12 = i9;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                        }
                        final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default14 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                        final Modifier modifier17 = modifier2;
                        final Function2<? super Composer, ? super Integer, Unit> function18 = function4;
                        int i1115 = i12 >> 18;
                        m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                            public final void invoke(Composer composer3, int i1116) {
                                if (!composer3.shouldExecute((i1116 & 3) != 2, i1116 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1831009258, i1116, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                                }
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier17, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default14, z110, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                                Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                Function2<Composer, Integer, Unit> function19 = function18;
                                Function2<Composer, Integer, Unit> function110 = function2;
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion.getConstructor();
                                if (composer3.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer3.startReusableNode();
                                if (composer3.getInserting()) {
                                    composer3.createNode(constructor);
                                } else {
                                    composer3.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                function19.invoke(composer3, 0);
                                SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function110, composer3, 0);
                                composer3.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i1115 & 112) | (i1115 & 14) | 3072);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier17;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        z6 = z110;
                        j3 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        j3 = j2;
                    }
                    j4 = jM3144unboximpl;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i9 |= 100663296;
                if ((i9 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    final boolean z111 = z4;
                    i12 = i9;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                    }
                    final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default15 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                    final Modifier modifier18 = modifier2;
                    final Function2<? super Composer, ? super Integer, Unit> function19 = function4;
                    int i1116 = i12 >> 18;
                    m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                        public final void invoke(Composer composer3, int i1117) {
                            if (!composer3.shouldExecute((i1117 & 3) != 2, i1117 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1831009258, i1117, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                            }
                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier18, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default15, z111, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            Function2<Composer, Integer, Unit> function110 = function19;
                            Function2<Composer, Integer, Unit> function111 = function2;
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                            Function0<ComposeUiNode> constructor = companion.getConstructor();
                            if (composer3.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composer3.startReusableNode();
                            if (composer3.getInserting()) {
                                composer3.createNode(constructor);
                            } else {
                                composer3.useNode();
                            }
                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            function110.invoke(composer3, 0);
                            SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                            TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function111, composer3, 0);
                            composer3.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i1116 & 112) | (i1116 & 14) | 3072);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier18;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    z6 = z111;
                    j3 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    j3 = j2;
                }
                j4 = jM3144unboximpl;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z4 = z2;
            if ((1572864 & i) == 0) {
                jM3144unboximpl = j;
                if ((i2 & 64) == 0) {
                    i14 = 524288;
                } else {
                    i14 = 524288;
                }
                i3 |= i14;
            } else {
                jM3144unboximpl = j;
            }
            if ((12582912 & i) == 0) {
                int i1117 = i3;
                if ((i2 & 128) == 0) {
                    i13 = 4194304;
                } else {
                    i13 = 4194304;
                }
                i9 = i1117 | i13;
            } else {
                i9 = i3;
            }
            i10 = i2 & 256;
            if (i10 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i11 = 33554432;
                    }
                    i9 |= i11;
                }
                if ((i9 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    final boolean z112 = z4;
                    i12 = i9;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                    }
                    final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default16 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                    final Modifier modifier19 = modifier2;
                    final Function2<? super Composer, ? super Integer, Unit> function110 = function4;
                    int i1118 = i12 >> 18;
                    m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                        public final void invoke(Composer composer3, int i1119) {
                            if (!composer3.shouldExecute((i1119 & 3) != 2, i1119 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1831009258, i1119, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                            }
                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier19, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default16, z112, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            Function2<Composer, Integer, Unit> function111 = function110;
                            Function2<Composer, Integer, Unit> function112 = function2;
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                            Function0<ComposeUiNode> constructor = companion.getConstructor();
                            if (composer3.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composer3.startReusableNode();
                            if (composer3.getInserting()) {
                                composer3.createNode(constructor);
                            } else {
                                composer3.useNode();
                            }
                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            function111.invoke(composer3, 0);
                            SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                            TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function112, composer3, 0);
                            composer3.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i1118 & 112) | (i1118 & 14) | 3072);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier19;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    z6 = z112;
                    j3 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    j3 = j2;
                }
                j4 = jM3144unboximpl;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i9 |= 100663296;
            if ((i9 & 38347923) != 38347922) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i9 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        i9 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i9 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        i9 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                final boolean z113 = z4;
                i12 = i9;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                }
                final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default17 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                final Modifier modifier110 = modifier2;
                final Function2<? super Composer, ? super Integer, Unit> function111 = function4;
                int i1119 = i12 >> 18;
                m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                    public final void invoke(Composer composer3, int i11110) {
                        if (!composer3.shouldExecute((i11110 & 3) != 2, i11110 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1831009258, i11110, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                        }
                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier110, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default17, z113, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                        Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                        Function2<Composer, Integer, Unit> function112 = function111;
                        Function2<Composer, Integer, Unit> function113 = function2;
                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor = companion.getConstructor();
                        if (composer3.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composer3.startReusableNode();
                        if (composer3.getInserting()) {
                            composer3.createNode(constructor);
                        } else {
                            composer3.useNode();
                        }
                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                        function112.invoke(composer3, 0);
                        SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                        TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function113, composer3, 0);
                        composer3.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i1119 & 112) | (i1119 & 14) | 3072);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier110;
                mutableInteractionSource2 = mutableInteractionSource3;
                z6 = z113;
                j3 = j5;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                mutableInteractionSource2 = mutableInteractionSource;
                modifier3 = modifier2;
                z6 = z4;
                j3 = j2;
            }
            j4 = jM3144unboximpl;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        if ((i2 & 8) != 0) {
            if ((i & 3072) == 0) {
                function4 = function3;
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i4 = 2048;
                } else {
                    i4 = 1024;
                }
                i3 |= i4;
            }
            i5 = i2 & 16;
            if (i5 != 0) {
                if ((i & 24576) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i6 = 16384;
                    } else {
                        i6 = 8192;
                    }
                    i3 |= i6;
                }
                i7 = i2 & 32;
                if (i7 != 0) {
                    if ((196608 & i) == 0) {
                        z4 = z2;
                        if (composerStartRestartGroup.changed(z4)) {
                            i8 = 131072;
                        } else {
                            i8 = 65536;
                        }
                        i3 |= i8;
                    }
                    if ((1572864 & i) == 0) {
                        jM3144unboximpl = j;
                        if ((i2 & 64) == 0) {
                            i14 = 524288;
                        } else {
                            i14 = 524288;
                        }
                        i3 |= i14;
                    } else {
                        jM3144unboximpl = j;
                    }
                    if ((12582912 & i) == 0) {
                        int i11110 = i3;
                        if ((i2 & 128) == 0) {
                            i13 = 4194304;
                        } else {
                            i13 = 4194304;
                        }
                        i9 = i11110 | i13;
                    } else {
                        i9 = i3;
                    }
                    i10 = i2 & 256;
                    if (i10 != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i11 = 33554432;
                            }
                            i9 |= i11;
                        }
                        if ((i9 & 38347923) != 38347922) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    z4 = true;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i9 &= -3670017;
                                }
                                if ((i2 & 128) != 0) {
                                    i9 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    z4 = true;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i9 &= -3670017;
                                }
                                if ((i2 & 128) != 0) {
                                    i9 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                if (i10 != 0) {
                                    mutableInteractionSource3 = null;
                                } else {
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            }
                            final boolean z114 = z4;
                            i12 = i9;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                            }
                            final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default18 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                            final Modifier modifier111 = modifier2;
                            final Function2<? super Composer, ? super Integer, Unit> function112 = function4;
                            int i11111 = i12 >> 18;
                            m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                                public final void invoke(Composer composer3, int i11112) {
                                    if (!composer3.shouldExecute((i11112 & 3) != 2, i11112 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1831009258, i11112, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                                    }
                                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier111, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default18, z114, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                                    Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                    Function2<Composer, Integer, Unit> function113 = function112;
                                    Function2<Composer, Integer, Unit> function114 = function2;
                                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                                    ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                    Function0<ComposeUiNode> constructor = companion.getConstructor();
                                    if (composer3.getApplier() == null) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        composer3.createNode(constructor);
                                    } else {
                                        composer3.useNode();
                                    }
                                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                    function113.invoke(composer3, 0);
                                    SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                                    TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function114, composer3, 0);
                                    composer3.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i11111 & 112) | (i11111 & 14) | 3072);
                            composer2 = composerStartRestartGroup;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier111;
                            mutableInteractionSource2 = mutableInteractionSource3;
                            z6 = z114;
                            j3 = j5;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            mutableInteractionSource2 = mutableInteractionSource;
                            modifier3 = modifier2;
                            z6 = z4;
                            j3 = j2;
                        }
                        j4 = jM3144unboximpl;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                                public final Object invoke(Object obj, Object obj2) {
                                    return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i9 |= 100663296;
                    if ((i9 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        final boolean z115 = z4;
                        i12 = i9;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                        }
                        final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default19 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                        final Modifier modifier112 = modifier2;
                        final Function2<? super Composer, ? super Integer, Unit> function113 = function4;
                        int i11112 = i12 >> 18;
                        m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                            public final void invoke(Composer composer3, int i11113) {
                                if (!composer3.shouldExecute((i11113 & 3) != 2, i11113 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1831009258, i11113, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                                }
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier112, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default19, z115, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                                Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                Function2<Composer, Integer, Unit> function114 = function113;
                                Function2<Composer, Integer, Unit> function115 = function2;
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion.getConstructor();
                                if (composer3.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer3.startReusableNode();
                                if (composer3.getInserting()) {
                                    composer3.createNode(constructor);
                                } else {
                                    composer3.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                function114.invoke(composer3, 0);
                                SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function115, composer3, 0);
                                composer3.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i11112 & 112) | (i11112 & 14) | 3072);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier112;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        z6 = z115;
                        j3 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        j3 = j2;
                    }
                    j4 = jM3144unboximpl;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                z4 = z2;
                if ((1572864 & i) == 0) {
                    jM3144unboximpl = j;
                    if ((i2 & 64) == 0) {
                        i14 = 524288;
                    } else {
                        i14 = 524288;
                    }
                    i3 |= i14;
                } else {
                    jM3144unboximpl = j;
                }
                if ((12582912 & i) == 0) {
                    int i11113 = i3;
                    if ((i2 & 128) == 0) {
                        i13 = 4194304;
                    } else {
                        i13 = 4194304;
                    }
                    i9 = i11113 | i13;
                } else {
                    i9 = i3;
                }
                i10 = i2 & 256;
                if (i10 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i11 = 33554432;
                        }
                        i9 |= i11;
                    }
                    if ((i9 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        final boolean z116 = z4;
                        i12 = i9;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                        }
                        final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default110 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                        final Modifier modifier113 = modifier2;
                        final Function2<? super Composer, ? super Integer, Unit> function114 = function4;
                        int i11114 = i12 >> 18;
                        m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                            public final void invoke(Composer composer3, int i11115) {
                                if (!composer3.shouldExecute((i11115 & 3) != 2, i11115 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1831009258, i11115, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                                }
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier113, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default110, z116, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                                Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                Function2<Composer, Integer, Unit> function115 = function114;
                                Function2<Composer, Integer, Unit> function116 = function2;
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion.getConstructor();
                                if (composer3.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer3.startReusableNode();
                                if (composer3.getInserting()) {
                                    composer3.createNode(constructor);
                                } else {
                                    composer3.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                function115.invoke(composer3, 0);
                                SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function116, composer3, 0);
                                composer3.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i11114 & 112) | (i11114 & 14) | 3072);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier113;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        z6 = z116;
                        j3 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        j3 = j2;
                    }
                    j4 = jM3144unboximpl;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i9 |= 100663296;
                if ((i9 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    final boolean z117 = z4;
                    i12 = i9;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                    }
                    final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default111 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                    final Modifier modifier114 = modifier2;
                    final Function2<? super Composer, ? super Integer, Unit> function115 = function4;
                    int i11115 = i12 >> 18;
                    m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                        public final void invoke(Composer composer3, int i11116) {
                            if (!composer3.shouldExecute((i11116 & 3) != 2, i11116 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1831009258, i11116, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                            }
                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier114, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default111, z117, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            Function2<Composer, Integer, Unit> function116 = function115;
                            Function2<Composer, Integer, Unit> function117 = function2;
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                            Function0<ComposeUiNode> constructor = companion.getConstructor();
                            if (composer3.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composer3.startReusableNode();
                            if (composer3.getInserting()) {
                                composer3.createNode(constructor);
                            } else {
                                composer3.useNode();
                            }
                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            function116.invoke(composer3, 0);
                            SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                            TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function117, composer3, 0);
                            composer3.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i11115 & 112) | (i11115 & 14) | 3072);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier114;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    z6 = z117;
                    j3 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    j3 = j2;
                }
                j4 = jM3144unboximpl;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            modifier2 = modifier;
            i7 = i2 & 32;
            if (i7 != 0) {
                if ((196608 & i) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i8 = 131072;
                    } else {
                        i8 = 65536;
                    }
                    i3 |= i8;
                }
                if ((1572864 & i) == 0) {
                    jM3144unboximpl = j;
                    if ((i2 & 64) == 0) {
                        i14 = 524288;
                    } else {
                        i14 = 524288;
                    }
                    i3 |= i14;
                } else {
                    jM3144unboximpl = j;
                }
                if ((12582912 & i) == 0) {
                    int i11116 = i3;
                    if ((i2 & 128) == 0) {
                        i13 = 4194304;
                    } else {
                        i13 = 4194304;
                    }
                    i9 = i11116 | i13;
                } else {
                    i9 = i3;
                }
                i10 = i2 & 256;
                if (i10 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i11 = 33554432;
                        }
                        i9 |= i11;
                    }
                    if ((i9 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        final boolean z118 = z4;
                        i12 = i9;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                        }
                        final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default112 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                        final Modifier modifier115 = modifier2;
                        final Function2<? super Composer, ? super Integer, Unit> function116 = function4;
                        int i11117 = i12 >> 18;
                        m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                            public final void invoke(Composer composer3, int i11118) {
                                if (!composer3.shouldExecute((i11118 & 3) != 2, i11118 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1831009258, i11118, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                                }
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier115, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default112, z118, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                                Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                Function2<Composer, Integer, Unit> function117 = function116;
                                Function2<Composer, Integer, Unit> function118 = function2;
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion.getConstructor();
                                if (composer3.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer3.startReusableNode();
                                if (composer3.getInserting()) {
                                    composer3.createNode(constructor);
                                } else {
                                    composer3.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                function117.invoke(composer3, 0);
                                SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function118, composer3, 0);
                                composer3.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i11117 & 112) | (i11117 & 14) | 3072);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier115;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        z6 = z118;
                        j3 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        j3 = j2;
                    }
                    j4 = jM3144unboximpl;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i9 |= 100663296;
                if ((i9 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    final boolean z119 = z4;
                    i12 = i9;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                    }
                    final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default113 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                    final Modifier modifier116 = modifier2;
                    final Function2<? super Composer, ? super Integer, Unit> function117 = function4;
                    int i11118 = i12 >> 18;
                    m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                        public final void invoke(Composer composer3, int i11119) {
                            if (!composer3.shouldExecute((i11119 & 3) != 2, i11119 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1831009258, i11119, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                            }
                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier116, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default113, z119, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            Function2<Composer, Integer, Unit> function118 = function117;
                            Function2<Composer, Integer, Unit> function119 = function2;
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                            Function0<ComposeUiNode> constructor = companion.getConstructor();
                            if (composer3.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composer3.startReusableNode();
                            if (composer3.getInserting()) {
                                composer3.createNode(constructor);
                            } else {
                                composer3.useNode();
                            }
                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            function118.invoke(composer3, 0);
                            SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                            TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function119, composer3, 0);
                            composer3.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i11118 & 112) | (i11118 & 14) | 3072);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier116;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    z6 = z119;
                    j3 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    j3 = j2;
                }
                j4 = jM3144unboximpl;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z4 = z2;
            if ((1572864 & i) == 0) {
                jM3144unboximpl = j;
                if ((i2 & 64) == 0) {
                    i14 = 524288;
                } else {
                    i14 = 524288;
                }
                i3 |= i14;
            } else {
                jM3144unboximpl = j;
            }
            if ((12582912 & i) == 0) {
                int i11119 = i3;
                if ((i2 & 128) == 0) {
                    i13 = 4194304;
                } else {
                    i13 = 4194304;
                }
                i9 = i11119 | i13;
            } else {
                i9 = i3;
            }
            i10 = i2 & 256;
            if (i10 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i11 = 33554432;
                    }
                    i9 |= i11;
                }
                if ((i9 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    final boolean z1110 = z4;
                    i12 = i9;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                    }
                    final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default114 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                    final Modifier modifier117 = modifier2;
                    final Function2<? super Composer, ? super Integer, Unit> function118 = function4;
                    int i111110 = i12 >> 18;
                    m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                        public final void invoke(Composer composer3, int i111111) {
                            if (!composer3.shouldExecute((i111111 & 3) != 2, i111111 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1831009258, i111111, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                            }
                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier117, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default114, z1110, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            Function2<Composer, Integer, Unit> function119 = function118;
                            Function2<Composer, Integer, Unit> function1110 = function2;
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                            Function0<ComposeUiNode> constructor = companion.getConstructor();
                            if (composer3.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composer3.startReusableNode();
                            if (composer3.getInserting()) {
                                composer3.createNode(constructor);
                            } else {
                                composer3.useNode();
                            }
                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            function119.invoke(composer3, 0);
                            SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                            TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function1110, composer3, 0);
                            composer3.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i111110 & 112) | (i111110 & 14) | 3072);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier117;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    z6 = z1110;
                    j3 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    j3 = j2;
                }
                j4 = jM3144unboximpl;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i9 |= 100663296;
            if ((i9 & 38347923) != 38347922) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i9 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        i9 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i9 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        i9 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                final boolean z1111 = z4;
                i12 = i9;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                }
                final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default115 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                final Modifier modifier118 = modifier2;
                final Function2<? super Composer, ? super Integer, Unit> function119 = function4;
                int i111111 = i12 >> 18;
                m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                    public final void invoke(Composer composer3, int i111112) {
                        if (!composer3.shouldExecute((i111112 & 3) != 2, i111112 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1831009258, i111112, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                        }
                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier118, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default115, z1111, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                        Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                        Function2<Composer, Integer, Unit> function1110 = function119;
                        Function2<Composer, Integer, Unit> function1111 = function2;
                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor = companion.getConstructor();
                        if (composer3.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composer3.startReusableNode();
                        if (composer3.getInserting()) {
                            composer3.createNode(constructor);
                        } else {
                            composer3.useNode();
                        }
                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                        function1110.invoke(composer3, 0);
                        SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                        TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function1111, composer3, 0);
                        composer3.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i111111 & 112) | (i111111 & 14) | 3072);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier118;
                mutableInteractionSource2 = mutableInteractionSource3;
                z6 = z1111;
                j3 = j5;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                mutableInteractionSource2 = mutableInteractionSource;
                modifier3 = modifier2;
                z6 = z4;
                j3 = j2;
            }
            j4 = jM3144unboximpl;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        function4 = function3;
        i5 = i2 & 16;
        if (i5 != 0) {
            if ((i & 24576) == 0) {
                modifier2 = modifier;
                if (composerStartRestartGroup.changed(modifier2)) {
                    i6 = 16384;
                } else {
                    i6 = 8192;
                }
                i3 |= i6;
            }
            i7 = i2 & 32;
            if (i7 != 0) {
                if ((196608 & i) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i8 = 131072;
                    } else {
                        i8 = 65536;
                    }
                    i3 |= i8;
                }
                if ((1572864 & i) == 0) {
                    jM3144unboximpl = j;
                    if ((i2 & 64) == 0) {
                        i14 = 524288;
                    } else {
                        i14 = 524288;
                    }
                    i3 |= i14;
                } else {
                    jM3144unboximpl = j;
                }
                if ((12582912 & i) == 0) {
                    int i111112 = i3;
                    if ((i2 & 128) == 0) {
                        i13 = 4194304;
                    } else {
                        i13 = 4194304;
                    }
                    i9 = i111112 | i13;
                } else {
                    i9 = i3;
                }
                i10 = i2 & 256;
                if (i10 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i11 = 33554432;
                        }
                        i9 |= i11;
                    }
                    if ((i9 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i9 &= -3670017;
                            }
                            if ((i2 & 128) != 0) {
                                i9 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            if (i10 != 0) {
                                mutableInteractionSource3 = null;
                            } else {
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        final boolean z1112 = z4;
                        i12 = i9;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                        }
                        final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default116 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                        final Modifier modifier119 = modifier2;
                        final Function2<? super Composer, ? super Integer, Unit> function1110 = function4;
                        int i111113 = i12 >> 18;
                        m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                            public final void invoke(Composer composer3, int i111114) {
                                if (!composer3.shouldExecute((i111114 & 3) != 2, i111114 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1831009258, i111114, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                                }
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier119, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default116, z1112, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                                Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                                Function2<Composer, Integer, Unit> function1111 = function1110;
                                Function2<Composer, Integer, Unit> function1112 = function2;
                                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion.getConstructor();
                                if (composer3.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer3.startReusableNode();
                                if (composer3.getInserting()) {
                                    composer3.createNode(constructor);
                                } else {
                                    composer3.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                function1111.invoke(composer3, 0);
                                SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function1112, composer3, 0);
                                composer3.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i111113 & 112) | (i111113 & 14) | 3072);
                        composer2 = composerStartRestartGroup;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier119;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        z6 = z1112;
                        j3 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        j3 = j2;
                    }
                    j4 = jM3144unboximpl;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i9 |= 100663296;
                if ((i9 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    final boolean z1113 = z4;
                    i12 = i9;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                    }
                    final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default117 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                    final Modifier modifier1110 = modifier2;
                    final Function2<? super Composer, ? super Integer, Unit> function1111 = function4;
                    int i111114 = i12 >> 18;
                    m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                        public final void invoke(Composer composer3, int i111115) {
                            if (!composer3.shouldExecute((i111115 & 3) != 2, i111115 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1831009258, i111115, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                            }
                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier1110, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default117, z1113, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            Function2<Composer, Integer, Unit> function1112 = function1111;
                            Function2<Composer, Integer, Unit> function1113 = function2;
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                            Function0<ComposeUiNode> constructor = companion.getConstructor();
                            if (composer3.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composer3.startReusableNode();
                            if (composer3.getInserting()) {
                                composer3.createNode(constructor);
                            } else {
                                composer3.useNode();
                            }
                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            function1112.invoke(composer3, 0);
                            SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                            TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function1113, composer3, 0);
                            composer3.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i111114 & 112) | (i111114 & 14) | 3072);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier1110;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    z6 = z1113;
                    j3 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    j3 = j2;
                }
                j4 = jM3144unboximpl;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z4 = z2;
            if ((1572864 & i) == 0) {
                jM3144unboximpl = j;
                if ((i2 & 64) == 0) {
                    i14 = 524288;
                } else {
                    i14 = 524288;
                }
                i3 |= i14;
            } else {
                jM3144unboximpl = j;
            }
            if ((12582912 & i) == 0) {
                int i111115 = i3;
                if ((i2 & 128) == 0) {
                    i13 = 4194304;
                } else {
                    i13 = 4194304;
                }
                i9 = i111115 | i13;
            } else {
                i9 = i3;
            }
            i10 = i2 & 256;
            if (i10 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i11 = 33554432;
                    }
                    i9 |= i11;
                }
                if ((i9 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    final boolean z1114 = z4;
                    i12 = i9;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                    }
                    final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default118 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                    final Modifier modifier1111 = modifier2;
                    final Function2<? super Composer, ? super Integer, Unit> function1112 = function4;
                    int i111116 = i12 >> 18;
                    m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                        public final void invoke(Composer composer3, int i111117) {
                            if (!composer3.shouldExecute((i111117 & 3) != 2, i111117 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1831009258, i111117, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                            }
                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier1111, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default118, z1114, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            Function2<Composer, Integer, Unit> function1113 = function1112;
                            Function2<Composer, Integer, Unit> function1114 = function2;
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                            Function0<ComposeUiNode> constructor = companion.getConstructor();
                            if (composer3.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composer3.startReusableNode();
                            if (composer3.getInserting()) {
                                composer3.createNode(constructor);
                            } else {
                                composer3.useNode();
                            }
                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            function1113.invoke(composer3, 0);
                            SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                            TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function1114, composer3, 0);
                            composer3.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i111116 & 112) | (i111116 & 14) | 3072);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier1111;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    z6 = z1114;
                    j3 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    j3 = j2;
                }
                j4 = jM3144unboximpl;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i9 |= 100663296;
            if ((i9 & 38347923) != 38347922) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i9 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        i9 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i9 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        i9 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                final boolean z1115 = z4;
                i12 = i9;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                }
                final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default119 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                final Modifier modifier1112 = modifier2;
                final Function2<? super Composer, ? super Integer, Unit> function1113 = function4;
                int i111117 = i12 >> 18;
                m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                    public final void invoke(Composer composer3, int i111118) {
                        if (!composer3.shouldExecute((i111118 & 3) != 2, i111118 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1831009258, i111118, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                        }
                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier1112, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default119, z1115, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                        Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                        Function2<Composer, Integer, Unit> function1114 = function1113;
                        Function2<Composer, Integer, Unit> function1115 = function2;
                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor = companion.getConstructor();
                        if (composer3.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composer3.startReusableNode();
                        if (composer3.getInserting()) {
                            composer3.createNode(constructor);
                        } else {
                            composer3.useNode();
                        }
                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                        function1114.invoke(composer3, 0);
                        SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                        TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function1115, composer3, 0);
                        composer3.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i111117 & 112) | (i111117 & 14) | 3072);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier1112;
                mutableInteractionSource2 = mutableInteractionSource3;
                z6 = z1115;
                j3 = j5;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                mutableInteractionSource2 = mutableInteractionSource;
                modifier3 = modifier2;
                z6 = z4;
                j3 = j2;
            }
            j4 = jM3144unboximpl;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        modifier2 = modifier;
        i7 = i2 & 32;
        if (i7 != 0) {
            if ((196608 & i) == 0) {
                z4 = z2;
                if (composerStartRestartGroup.changed(z4)) {
                    i8 = 131072;
                } else {
                    i8 = 65536;
                }
                i3 |= i8;
            }
            if ((1572864 & i) == 0) {
                jM3144unboximpl = j;
                if ((i2 & 64) == 0) {
                    i14 = 524288;
                } else {
                    i14 = 524288;
                }
                i3 |= i14;
            } else {
                jM3144unboximpl = j;
            }
            if ((12582912 & i) == 0) {
                int i111118 = i3;
                if ((i2 & 128) == 0) {
                    i13 = 4194304;
                } else {
                    i13 = 4194304;
                }
                i9 = i111118 | i13;
            } else {
                i9 = i3;
            }
            i10 = i2 & 256;
            if (i10 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i11 = 33554432;
                    }
                    i9 |= i11;
                }
                if ((i9 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i9 &= -3670017;
                        }
                        if ((i2 & 128) != 0) {
                            i9 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        if (i10 != 0) {
                            mutableInteractionSource3 = null;
                        } else {
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    final boolean z1116 = z4;
                    i12 = i9;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                    }
                    final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default1110 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                    final Modifier modifier1113 = modifier2;
                    final Function2<? super Composer, ? super Integer, Unit> function1114 = function4;
                    int i111119 = i12 >> 18;
                    m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                        public final void invoke(Composer composer3, int i1111110) {
                            if (!composer3.shouldExecute((i1111110 & 3) != 2, i1111110 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1831009258, i1111110, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                            }
                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier1113, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default1110, z1116, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                            Function2<Composer, Integer, Unit> function1115 = function1114;
                            Function2<Composer, Integer, Unit> function1116 = function2;
                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                            Function0<ComposeUiNode> constructor = companion.getConstructor();
                            if (composer3.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composer3.startReusableNode();
                            if (composer3.getInserting()) {
                                composer3.createNode(constructor);
                            } else {
                                composer3.useNode();
                            }
                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                            function1115.invoke(composer3, 0);
                            SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                            TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function1116, composer3, 0);
                            composer3.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i111119 & 112) | (i111119 & 14) | 3072);
                    composer2 = composerStartRestartGroup;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier1113;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    z6 = z1116;
                    j3 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    j3 = j2;
                }
                j4 = jM3144unboximpl;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i9 |= 100663296;
            if ((i9 & 38347923) != 38347922) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i9 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        i9 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i9 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        i9 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                final boolean z1117 = z4;
                i12 = i9;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                }
                final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default1111 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                final Modifier modifier1114 = modifier2;
                final Function2<? super Composer, ? super Integer, Unit> function1115 = function4;
                int i1111110 = i12 >> 18;
                m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                    public final void invoke(Composer composer3, int i1111111) {
                        if (!composer3.shouldExecute((i1111111 & 3) != 2, i1111111 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1831009258, i1111111, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                        }
                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier1114, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default1111, z1117, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                        Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                        Function2<Composer, Integer, Unit> function1116 = function1115;
                        Function2<Composer, Integer, Unit> function1117 = function2;
                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor = companion.getConstructor();
                        if (composer3.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composer3.startReusableNode();
                        if (composer3.getInserting()) {
                            composer3.createNode(constructor);
                        } else {
                            composer3.useNode();
                        }
                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                        function1116.invoke(composer3, 0);
                        SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                        TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function1117, composer3, 0);
                        composer3.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i1111110 & 112) | (i1111110 & 14) | 3072);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier1114;
                mutableInteractionSource2 = mutableInteractionSource3;
                z6 = z1117;
                j3 = j5;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                mutableInteractionSource2 = mutableInteractionSource;
                modifier3 = modifier2;
                z6 = z4;
                j3 = j2;
            }
            j4 = jM3144unboximpl;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        z4 = z2;
        if ((1572864 & i) == 0) {
            jM3144unboximpl = j;
            if ((i2 & 64) == 0) {
                i14 = 524288;
            } else {
                i14 = 524288;
            }
            i3 |= i14;
        } else {
            jM3144unboximpl = j;
        }
        if ((12582912 & i) == 0) {
            int i1111111 = i3;
            if ((i2 & 128) == 0) {
                i13 = 4194304;
            } else {
                i13 = 4194304;
            }
            i9 = i1111111 | i13;
        } else {
            i9 = i3;
        }
        i10 = i2 & 256;
        if (i10 != 0) {
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                    i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i11 = 33554432;
                }
                i9 |= i11;
            }
            if ((i9 & 38347923) != 38347922) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i9 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        i9 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i9 &= -3670017;
                    }
                    if ((i2 & 128) != 0) {
                        i9 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    if (i10 != 0) {
                        mutableInteractionSource3 = null;
                    } else {
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                final boolean z1118 = z4;
                i12 = i9;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
                }
                final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default1112 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                final Modifier modifier1115 = modifier2;
                final Function2<? super Composer, ? super Integer, Unit> function1116 = function4;
                int i1111112 = i12 >> 18;
                m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                    public final void invoke(Composer composer3, int i1111113) {
                        if (!composer3.shouldExecute((i1111113 & 3) != 2, i1111113 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1831009258, i1111113, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                        }
                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier1115, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default1112, z1118, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                        Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                        Function2<Composer, Integer, Unit> function1117 = function1116;
                        Function2<Composer, Integer, Unit> function1118 = function2;
                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor = companion.getConstructor();
                        if (composer3.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composer3.startReusableNode();
                        if (composer3.getInserting()) {
                            composer3.createNode(constructor);
                        } else {
                            composer3.useNode();
                        }
                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                        function1117.invoke(composer3, 0);
                        SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                        TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function1118, composer3, 0);
                        composer3.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i1111112 & 112) | (i1111112 & 14) | 3072);
                composer2 = composerStartRestartGroup;
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier1115;
                mutableInteractionSource2 = mutableInteractionSource3;
                z6 = z1118;
                j3 = j5;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                mutableInteractionSource2 = mutableInteractionSource;
                modifier3 = modifier2;
                z6 = z4;
                j3 = j2;
            }
            j4 = jM3144unboximpl;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i9 |= 100663296;
        if ((i9 & 38347923) != 38347922) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z5, i9 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    z4 = true;
                }
                if ((i2 & 64) != 0) {
                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                    i9 &= -3670017;
                }
                if ((i2 & 128) != 0) {
                    i9 &= -29360129;
                    j5 = jM3144unboximpl;
                } else {
                    j5 = j2;
                }
                if (i10 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
            } else {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    z4 = true;
                }
                if ((i2 & 64) != 0) {
                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                    i9 &= -3670017;
                }
                if ((i2 & 128) != 0) {
                    i9 &= -29360129;
                    j5 = jM3144unboximpl;
                } else {
                    j5 = j2;
                }
                if (i10 != 0) {
                    mutableInteractionSource3 = null;
                } else {
                    mutableInteractionSource3 = mutableInteractionSource;
                }
            }
            final boolean z1119 = z4;
            i12 = i9;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-611535578, i12, -1, "androidx.compose.material3.LeadingIconTab (Tab.kt:164)");
            }
            final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default1113 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
            final Modifier modifier1116 = modifier2;
            final Function2<? super Composer, ? super Integer, Unit> function1117 = function4;
            int i1111113 = i12 >> 18;
            m989TabTransitionKlgxPg(jM3144unboximpl, j5, z, ComposableLambdaKt.rememberComposableLambda(1831009258, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$LeadingIconTab$1
                public final void invoke(Composer composer3, int i1111114) {
                    if (!composer3.shouldExecute((i1111114 & 3) != 2, i1111114 & 1)) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1831009258, i1111114, -1, "androidx.compose.material3.LeadingIconTab.<anonymous> (Tab.kt:171)");
                    }
                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(PaddingKt.padding-VpY3zN4$default(SelectableKt.selectable-O2vRcR0(SizeKt.height-3ABfNKs(modifier1116, TabKt.SmallTabHeight), z3, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default1113, z1119, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function1), TabKt.getHorizontalTextPadding(), 0.0f, 2, (Object) null), 0.0f, 1, (Object) null);
                    Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                    Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
                    Function2<Composer, Integer, Unit> function1118 = function1117;
                    Function2<Composer, Integer, Unit> function1119 = function2;
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(center, centerVertically, composer3, 54);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifierFillMaxWidth$default);
                    ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                    Function0<ComposeUiNode> constructor = companion.getConstructor();
                    if (composer3.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                        composer3.createNode(constructor);
                    } else {
                        composer3.useNode();
                    }
                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer3);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    function1118.invoke(composer3, 0);
                    SpacerKt.Spacer(SizeKt.requiredWidth-3ABfNKs(Modifier.INSTANCE, TabKt.TextDistanceFromLeadingIcon), composer3, 6);
                    TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function1119, composer3, 0);
                    composer3.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i12 << 6) & 896) | (i1111113 & 112) | (i1111113 & 14) | 3072);
            composer2 = composerStartRestartGroup;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier1116;
            mutableInteractionSource2 = mutableInteractionSource3;
            z6 = z1119;
            j3 = j5;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            mutableInteractionSource2 = mutableInteractionSource;
            modifier3 = modifier2;
            z6 = z4;
            j3 = j2;
        }
        j4 = jM3144unboximpl;
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: n1e
                public final Object invoke(Object obj, Object obj2) {
                    return TabKt.c(z, function0, function2, function3, modifier3, z6, j4, j3, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:107:0x0129 A[PHI: r0 r4 r8 r9 r13
      0x0129: PHI (r0v28 int) = (r0v16 int), (r0v32 int), (r0v33 int) binds: [B:120:0x015b, B:105:0x0126, B:106:0x0128] A[DONT_GENERATE, DONT_INLINE]
      0x0129: PHI (r4v8 androidx.compose.ui.Modifier) = (r4v5 androidx.compose.ui.Modifier), (r4v2 androidx.compose.ui.Modifier), (r4v2 androidx.compose.ui.Modifier) binds: [B:120:0x015b, B:105:0x0126, B:106:0x0128] A[DONT_GENERATE, DONT_INLINE]
      0x0129: PHI (r8v9 boolean) = (r8v3 boolean), (r8v2 boolean), (r8v2 boolean) binds: [B:120:0x015b, B:105:0x0126, B:106:0x0128] A[DONT_GENERATE, DONT_INLINE]
      0x0129: PHI (r9v13 long) = (r9v9 long), (r9v6 long), (r9v6 long) binds: [B:120:0x015b, B:105:0x0126, B:106:0x0128] A[DONT_GENERATE, DONT_INLINE]
      0x0129: PHI (r13v9 long) = (r13v5 long), (r13v3 long), (r13v3 long) binds: [B:120:0x015b, B:105:0x0126, B:106:0x0128] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:109:0x0136 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:110:0x0138  */
    /* JADX WARN: Code duplicated, block: B:112:0x013d  */
    /* JADX WARN: Code duplicated, block: B:115:0x0142  */
    /* JADX WARN: Code duplicated, block: B:116:0x0153  */
    /* JADX WARN: Code duplicated, block: B:119:0x0159  */
    /* JADX WARN: Code duplicated, block: B:121:0x015d  */
    /* JADX WARN: Code duplicated, block: B:124:0x016f  */
    /* JADX WARN: Code duplicated, block: B:127:0x01b5  */
    /* JADX WARN: Code duplicated, block: B:129:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:132:0x01d1  */
    /* JADX WARN: Code duplicated, block: B:134:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x0047  */
    /* JADX WARN: Code duplicated, block: B:28:0x004c  */
    /* JADX WARN: Code duplicated, block: B:30:0x0050  */
    /* JADX WARN: Code duplicated, block: B:32:0x0058  */
    /* JADX WARN: Code duplicated, block: B:33:0x005b  */
    /* JADX WARN: Code duplicated, block: B:37:0x0062  */
    /* JADX WARN: Code duplicated, block: B:39:0x0067  */
    /* JADX WARN: Code duplicated, block: B:41:0x006b  */
    /* JADX WARN: Code duplicated, block: B:43:0x0073  */
    /* JADX WARN: Code duplicated, block: B:44:0x0076  */
    /* JADX WARN: Code duplicated, block: B:48:0x007d  */
    /* JADX WARN: Code duplicated, block: B:50:0x0081  */
    /* JADX WARN: Code duplicated, block: B:52:0x0089  */
    /* JADX WARN: Code duplicated, block: B:53:0x008c  */
    /* JADX WARN: Code duplicated, block: B:56:0x0092  */
    /* JADX WARN: Code duplicated, block: B:59:0x0099  */
    /* JADX WARN: Code duplicated, block: B:61:0x009d  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:67:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:70:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:71:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:76:0x00ca  */
    /* JADX WARN: Code duplicated, block: B:80:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:82:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:84:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:86:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:87:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:91:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:92:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:95:0x0104  */
    /* JADX WARN: Code duplicated, block: B:97:0x0111  */
    /* JADX INFO: renamed from: Tab-bogVsAg, reason: not valid java name */
    public static final void m987TabbogVsAg(final boolean z, final Function0<Unit> function0, Modifier modifier, boolean z2, long j, long j2, MutableInteractionSource mutableInteractionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        boolean z3;
        int i3;
        int i4;
        Modifier modifier2;
        int i5;
        int i6;
        boolean z4;
        int i7;
        long jM3144unboximpl;
        long j3;
        int i8;
        int i9;
        int i10;
        int i11;
        boolean z5;
        final Modifier modifier3;
        final boolean z6;
        final long j4;
        final long j5;
        final MutableInteractionSource mutableInteractionSource2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        int i12;
        final Modifier modifier4;
        long j6;
        final MutableInteractionSource mutableInteractionSource3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1573136853);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            z3 = z;
        } else {
            z3 = z;
            if ((i & 6) == 0) {
                i3 = (composerStartRestartGroup.changed(z3) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        if ((i2 & 2) == 0) {
            if ((i & 48) == 0) {
                i3 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 8;
                if (i6 != 0) {
                    if ((i & 3072) == 0) {
                        z4 = z2;
                        if (composerStartRestartGroup.changed(z4)) {
                            i7 = 2048;
                        } else {
                            i7 = 1024;
                        }
                        i3 |= i7;
                    }
                    if ((i & 24576) == 0) {
                        if ((i2 & 16) == 0) {
                            jM3144unboximpl = j;
                            int i13 = composerStartRestartGroup.changed(jM3144unboximpl) ? 16384 : 8192;
                            i3 |= i13;
                        } else {
                            jM3144unboximpl = j;
                        }
                        i3 |= i13;
                    } else {
                        jM3144unboximpl = j;
                    }
                    if ((196608 & i) == 0) {
                        if ((i2 & 32) == 0) {
                            j3 = j2;
                            int i14 = composerStartRestartGroup.changed(j3) ? 131072 : 65536;
                            i3 |= i14;
                        } else {
                            j3 = j2;
                        }
                        i3 |= i14;
                    } else {
                        j3 = j2;
                    }
                    i8 = i2 & 64;
                    if (i8 != 0) {
                        i3 |= 1572864;
                    } else if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    if ((i2 & 128) != 0) {
                        if ((i & 12582912) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i10 = 8388608;
                            } else {
                                i10 = 4194304;
                            }
                            i3 |= i10;
                        }
                        i11 = i3;
                        if ((i3 & 4793491) != 4793490) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i4 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i6 != 0) {
                                    z4 = true;
                                }
                                if ((i2 & 16) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i12 = i11 & (-57345);
                                } else {
                                    i12 = i11;
                                }
                                if ((i2 & 32) != 0) {
                                    i12 &= -458753;
                                    j3 = jM3144unboximpl;
                                }
                                if (i8 != 0) {
                                    modifier4 = modifier2;
                                    j6 = j3;
                                    mutableInteractionSource3 = null;
                                }
                                final boolean z7 = z4;
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1573136853, i12, -1, "androidx.compose.material3.Tab (Tab.kt:237)");
                                }
                                final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                                final boolean z8 = z3;
                                int i15 = i12 >> 12;
                                m989TabTransitionKlgxPg(jM3144unboximpl, j6, z, ComposableLambdaKt.rememberComposableLambda(1128552423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$3
                                    public final void invoke(Composer composer2, int i16) {
                                        if (!composer2.shouldExecute((i16 & 3) != 2, i16 & 1)) {
                                            composer2.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(1128552423, i16, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:244)");
                                        }
                                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SelectableKt.selectable-O2vRcR0(modifier4, z8, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default, z7, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function0), 0.0f, 1, (Object) null);
                                        Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                        Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                                        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composer2, 54);
                                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                                        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                        Function0<ComposeUiNode> constructor = companion.getConstructor();
                                        if (composer2.getApplier() == null) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer2.startReusableNode();
                                        if (composer2.getInserting()) {
                                            composer2.createNode(constructor);
                                        } else {
                                            composer2.useNode();
                                        }
                                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                        }
                                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                        function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                                        composer2.endNode();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i15 & 112) | (i15 & 14) | 3072 | ((i12 << 6) & 896));
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                j4 = jM3144unboximpl;
                                mutableInteractionSource2 = mutableInteractionSource3;
                                j5 = j6;
                                modifier3 = modifier4;
                                z6 = z7;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                i12 = (i2 & 16) != 0 ? i11 & (-57345) : i11;
                                if ((i2 & 32) != 0) {
                                    i12 &= -458753;
                                }
                            }
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = mutableInteractionSource;
                            final boolean z9 = z4;
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1573136853, i12, -1, "androidx.compose.material3.Tab (Tab.kt:237)");
                            }
                            final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default2 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                            final boolean z10 = z3;
                            int i16 = i12 >> 12;
                            m989TabTransitionKlgxPg(jM3144unboximpl, j6, z, ComposableLambdaKt.rememberComposableLambda(1128552423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$3
                                public final void invoke(Composer composer2, int i17) {
                                    if (!composer2.shouldExecute((i17 & 3) != 2, i17 & 1)) {
                                        composer2.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(1128552423, i17, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:244)");
                                    }
                                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SelectableKt.selectable-O2vRcR0(modifier4, z10, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default2, z9, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function0), 0.0f, 1, (Object) null);
                                    Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                                    Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                    Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composer2, 54);
                                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                    CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                                    ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                    Function0<ComposeUiNode> constructor = companion.getConstructor();
                                    if (composer2.getApplier() == null) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer2.startReusableNode();
                                    if (composer2.getInserting()) {
                                        composer2.createNode(constructor);
                                    } else {
                                        composer2.useNode();
                                    }
                                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                    }
                                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                    function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                                    composer2.endNode();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i16 & 112) | (i16 & 14) | 3072 | ((i12 << 6) & 896));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            j4 = jM3144unboximpl;
                            mutableInteractionSource2 = mutableInteractionSource3;
                            j5 = j6;
                            modifier3 = modifier4;
                            z6 = z9;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            modifier3 = modifier2;
                            z6 = z4;
                            j4 = jM3144unboximpl;
                            j5 = j3;
                            mutableInteractionSource2 = mutableInteractionSource;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: l1e
                                public final Object invoke(Object obj, Object obj2) {
                                    return TabKt.e(z, function0, modifier3, z6, j4, j5, mutableInteractionSource2, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 12582912;
                    i11 = i3;
                    if ((i3 & 4793491) != 4793490) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 16) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i12 = i11 & (-57345);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 32) != 0) {
                                i12 &= -458753;
                                j3 = jM3144unboximpl;
                            }
                            if (i8 != 0) {
                                modifier4 = modifier2;
                                j6 = j3;
                                mutableInteractionSource3 = null;
                            } else {
                                modifier4 = modifier2;
                                j6 = j3;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 16) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i12 = i11 & (-57345);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 32) != 0) {
                                i12 &= -458753;
                                j3 = jM3144unboximpl;
                            }
                            if (i8 != 0) {
                                modifier4 = modifier2;
                                j6 = j3;
                                mutableInteractionSource3 = null;
                            } else {
                                modifier4 = modifier2;
                                j6 = j3;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        final boolean z11 = z4;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1573136853, i12, -1, "androidx.compose.material3.Tab (Tab.kt:237)");
                        }
                        final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default3 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                        final boolean z12 = z3;
                        int i17 = i12 >> 12;
                        m989TabTransitionKlgxPg(jM3144unboximpl, j6, z, ComposableLambdaKt.rememberComposableLambda(1128552423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$3
                            public final void invoke(Composer composer2, int i18) {
                                if (!composer2.shouldExecute((i18 & 3) != 2, i18 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1128552423, i18, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:244)");
                                }
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SelectableKt.selectable-O2vRcR0(modifier4, z12, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default3, z11, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function0), 0.0f, 1, (Object) null);
                                Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                                Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composer2, 54);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion.getConstructor();
                                if (composer2.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer2.startReusableNode();
                                if (composer2.getInserting()) {
                                    composer2.createNode(constructor);
                                } else {
                                    composer2.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                                composer2.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i17 & 112) | (i17 & 14) | 3072 | ((i12 << 6) & 896));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        j4 = jM3144unboximpl;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        j5 = j6;
                        modifier3 = modifier4;
                        z6 = z11;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        z6 = z4;
                        j4 = jM3144unboximpl;
                        j5 = j3;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: l1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.e(z, function0, modifier3, z6, j4, j5, mutableInteractionSource2, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 3072;
                z4 = z2;
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        jM3144unboximpl = j;
                        if (composerStartRestartGroup.changed(jM3144unboximpl)) {
                        }
                        i3 |= i13;
                    } else {
                        jM3144unboximpl = j;
                    }
                    i3 |= i13;
                } else {
                    jM3144unboximpl = j;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        j3 = j2;
                        if (composerStartRestartGroup.changed(j3)) {
                        }
                        i3 |= i14;
                    } else {
                        j3 = j2;
                    }
                    i3 |= i14;
                } else {
                    j3 = j2;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                if ((i2 & 128) != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i10 = 8388608;
                        } else {
                            i10 = 4194304;
                        }
                        i3 |= i10;
                    }
                    i11 = i3;
                    if ((i3 & 4793491) != 4793490) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 16) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i12 = i11 & (-57345);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 32) != 0) {
                                i12 &= -458753;
                                j3 = jM3144unboximpl;
                            }
                            if (i8 != 0) {
                                modifier4 = modifier2;
                                j6 = j3;
                                mutableInteractionSource3 = null;
                            } else {
                                modifier4 = modifier2;
                                j6 = j3;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 16) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i12 = i11 & (-57345);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 32) != 0) {
                                i12 &= -458753;
                                j3 = jM3144unboximpl;
                            }
                            if (i8 != 0) {
                                modifier4 = modifier2;
                                j6 = j3;
                                mutableInteractionSource3 = null;
                            } else {
                                modifier4 = modifier2;
                                j6 = j3;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        final boolean z13 = z4;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1573136853, i12, -1, "androidx.compose.material3.Tab (Tab.kt:237)");
                        }
                        final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default4 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                        final boolean z14 = z3;
                        int i18 = i12 >> 12;
                        m989TabTransitionKlgxPg(jM3144unboximpl, j6, z, ComposableLambdaKt.rememberComposableLambda(1128552423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$3
                            public final void invoke(Composer composer2, int i19) {
                                if (!composer2.shouldExecute((i19 & 3) != 2, i19 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1128552423, i19, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:244)");
                                }
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SelectableKt.selectable-O2vRcR0(modifier4, z14, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default4, z13, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function0), 0.0f, 1, (Object) null);
                                Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                                Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composer2, 54);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion.getConstructor();
                                if (composer2.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer2.startReusableNode();
                                if (composer2.getInserting()) {
                                    composer2.createNode(constructor);
                                } else {
                                    composer2.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                                composer2.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i18 & 112) | (i18 & 14) | 3072 | ((i12 << 6) & 896));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        j4 = jM3144unboximpl;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        j5 = j6;
                        modifier3 = modifier4;
                        z6 = z13;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        z6 = z4;
                        j4 = jM3144unboximpl;
                        j5 = j3;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: l1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.e(z, function0, modifier3, z6, j4, j5, mutableInteractionSource2, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                i11 = i3;
                if ((i3 & 4793491) != 4793490) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 16) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i12 = i11 & (-57345);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 32) != 0) {
                            i12 &= -458753;
                            j3 = jM3144unboximpl;
                        }
                        if (i8 != 0) {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = null;
                        } else {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 16) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i12 = i11 & (-57345);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 32) != 0) {
                            i12 &= -458753;
                            j3 = jM3144unboximpl;
                        }
                        if (i8 != 0) {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = null;
                        } else {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    final boolean z15 = z4;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1573136853, i12, -1, "androidx.compose.material3.Tab (Tab.kt:237)");
                    }
                    final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default5 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                    final boolean z16 = z3;
                    int i19 = i12 >> 12;
                    m989TabTransitionKlgxPg(jM3144unboximpl, j6, z, ComposableLambdaKt.rememberComposableLambda(1128552423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$3
                        public final void invoke(Composer composer2, int i110) {
                            if (!composer2.shouldExecute((i110 & 3) != 2, i110 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1128552423, i110, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:244)");
                            }
                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SelectableKt.selectable-O2vRcR0(modifier4, z16, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default5, z15, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function0), 0.0f, 1, (Object) null);
                            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composer2, 54);
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                            Function0<ComposeUiNode> constructor = companion.getConstructor();
                            if (composer2.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composer2.startReusableNode();
                            if (composer2.getInserting()) {
                                composer2.createNode(constructor);
                            } else {
                                composer2.useNode();
                            }
                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                            function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                            composer2.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i19 & 112) | (i19 & 14) | 3072 | ((i12 << 6) & 896));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j4 = jM3144unboximpl;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    j5 = j6;
                    modifier3 = modifier4;
                    z6 = z15;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z6 = z4;
                    j4 = jM3144unboximpl;
                    j5 = j3;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: l1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.e(z, function0, modifier3, z6, j4, j5, mutableInteractionSource2, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            modifier2 = modifier;
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        jM3144unboximpl = j;
                        if (composerStartRestartGroup.changed(jM3144unboximpl)) {
                        }
                        i3 |= i13;
                    } else {
                        jM3144unboximpl = j;
                    }
                    i3 |= i13;
                } else {
                    jM3144unboximpl = j;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        j3 = j2;
                        if (composerStartRestartGroup.changed(j3)) {
                        }
                        i3 |= i14;
                    } else {
                        j3 = j2;
                    }
                    i3 |= i14;
                } else {
                    j3 = j2;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                if ((i2 & 128) != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i10 = 8388608;
                        } else {
                            i10 = 4194304;
                        }
                        i3 |= i10;
                    }
                    i11 = i3;
                    if ((i3 & 4793491) != 4793490) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 16) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i12 = i11 & (-57345);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 32) != 0) {
                                i12 &= -458753;
                                j3 = jM3144unboximpl;
                            }
                            if (i8 != 0) {
                                modifier4 = modifier2;
                                j6 = j3;
                                mutableInteractionSource3 = null;
                            } else {
                                modifier4 = modifier2;
                                j6 = j3;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 16) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i12 = i11 & (-57345);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 32) != 0) {
                                i12 &= -458753;
                                j3 = jM3144unboximpl;
                            }
                            if (i8 != 0) {
                                modifier4 = modifier2;
                                j6 = j3;
                                mutableInteractionSource3 = null;
                            } else {
                                modifier4 = modifier2;
                                j6 = j3;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        final boolean z17 = z4;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1573136853, i12, -1, "androidx.compose.material3.Tab (Tab.kt:237)");
                        }
                        final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default6 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                        final boolean z18 = z3;
                        int i110 = i12 >> 12;
                        m989TabTransitionKlgxPg(jM3144unboximpl, j6, z, ComposableLambdaKt.rememberComposableLambda(1128552423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$3
                            public final void invoke(Composer composer2, int i111) {
                                if (!composer2.shouldExecute((i111 & 3) != 2, i111 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1128552423, i111, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:244)");
                                }
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SelectableKt.selectable-O2vRcR0(modifier4, z18, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default6, z17, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function0), 0.0f, 1, (Object) null);
                                Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                                Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composer2, 54);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion.getConstructor();
                                if (composer2.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer2.startReusableNode();
                                if (composer2.getInserting()) {
                                    composer2.createNode(constructor);
                                } else {
                                    composer2.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                                composer2.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i110 & 112) | (i110 & 14) | 3072 | ((i12 << 6) & 896));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        j4 = jM3144unboximpl;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        j5 = j6;
                        modifier3 = modifier4;
                        z6 = z17;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        z6 = z4;
                        j4 = jM3144unboximpl;
                        j5 = j3;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: l1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.e(z, function0, modifier3, z6, j4, j5, mutableInteractionSource2, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                i11 = i3;
                if ((i3 & 4793491) != 4793490) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 16) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i12 = i11 & (-57345);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 32) != 0) {
                            i12 &= -458753;
                            j3 = jM3144unboximpl;
                        }
                        if (i8 != 0) {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = null;
                        } else {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 16) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i12 = i11 & (-57345);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 32) != 0) {
                            i12 &= -458753;
                            j3 = jM3144unboximpl;
                        }
                        if (i8 != 0) {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = null;
                        } else {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    final boolean z19 = z4;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1573136853, i12, -1, "androidx.compose.material3.Tab (Tab.kt:237)");
                    }
                    final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default7 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                    final boolean z110 = z3;
                    int i111 = i12 >> 12;
                    m989TabTransitionKlgxPg(jM3144unboximpl, j6, z, ComposableLambdaKt.rememberComposableLambda(1128552423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$3
                        public final void invoke(Composer composer2, int i112) {
                            if (!composer2.shouldExecute((i112 & 3) != 2, i112 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1128552423, i112, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:244)");
                            }
                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SelectableKt.selectable-O2vRcR0(modifier4, z110, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default7, z19, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function0), 0.0f, 1, (Object) null);
                            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composer2, 54);
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                            Function0<ComposeUiNode> constructor = companion.getConstructor();
                            if (composer2.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composer2.startReusableNode();
                            if (composer2.getInserting()) {
                                composer2.createNode(constructor);
                            } else {
                                composer2.useNode();
                            }
                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                            function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                            composer2.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i111 & 112) | (i111 & 14) | 3072 | ((i12 << 6) & 896));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j4 = jM3144unboximpl;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    j5 = j6;
                    modifier3 = modifier4;
                    z6 = z19;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z6 = z4;
                    j4 = jM3144unboximpl;
                    j5 = j3;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: l1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.e(z, function0, modifier3, z6, j4, j5, mutableInteractionSource2, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z4 = z2;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    jM3144unboximpl = j;
                    if (composerStartRestartGroup.changed(jM3144unboximpl)) {
                    }
                    i3 |= i13;
                } else {
                    jM3144unboximpl = j;
                }
                i3 |= i13;
            } else {
                jM3144unboximpl = j;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    j3 = j2;
                    if (composerStartRestartGroup.changed(j3)) {
                    }
                    i3 |= i14;
                } else {
                    j3 = j2;
                }
                i3 |= i14;
            } else {
                j3 = j2;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
            if ((i2 & 128) != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i10 = 8388608;
                    } else {
                        i10 = 4194304;
                    }
                    i3 |= i10;
                }
                i11 = i3;
                if ((i3 & 4793491) != 4793490) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 16) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i12 = i11 & (-57345);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 32) != 0) {
                            i12 &= -458753;
                            j3 = jM3144unboximpl;
                        }
                        if (i8 != 0) {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = null;
                        } else {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 16) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i12 = i11 & (-57345);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 32) != 0) {
                            i12 &= -458753;
                            j3 = jM3144unboximpl;
                        }
                        if (i8 != 0) {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = null;
                        } else {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    final boolean z111 = z4;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1573136853, i12, -1, "androidx.compose.material3.Tab (Tab.kt:237)");
                    }
                    final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default8 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                    final boolean z112 = z3;
                    int i112 = i12 >> 12;
                    m989TabTransitionKlgxPg(jM3144unboximpl, j6, z, ComposableLambdaKt.rememberComposableLambda(1128552423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$3
                        public final void invoke(Composer composer2, int i113) {
                            if (!composer2.shouldExecute((i113 & 3) != 2, i113 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1128552423, i113, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:244)");
                            }
                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SelectableKt.selectable-O2vRcR0(modifier4, z112, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default8, z111, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function0), 0.0f, 1, (Object) null);
                            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composer2, 54);
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                            Function0<ComposeUiNode> constructor = companion.getConstructor();
                            if (composer2.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composer2.startReusableNode();
                            if (composer2.getInserting()) {
                                composer2.createNode(constructor);
                            } else {
                                composer2.useNode();
                            }
                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                            function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                            composer2.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i112 & 112) | (i112 & 14) | 3072 | ((i12 << 6) & 896));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j4 = jM3144unboximpl;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    j5 = j6;
                    modifier3 = modifier4;
                    z6 = z111;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z6 = z4;
                    j4 = jM3144unboximpl;
                    j5 = j3;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: l1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.e(z, function0, modifier3, z6, j4, j5, mutableInteractionSource2, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            i11 = i3;
            if ((i3 & 4793491) != 4793490) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 16) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i12 = i11 & (-57345);
                    } else {
                        i12 = i11;
                    }
                    if ((i2 & 32) != 0) {
                        i12 &= -458753;
                        j3 = jM3144unboximpl;
                    }
                    if (i8 != 0) {
                        modifier4 = modifier2;
                        j6 = j3;
                        mutableInteractionSource3 = null;
                    } else {
                        modifier4 = modifier2;
                        j6 = j3;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 16) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i12 = i11 & (-57345);
                    } else {
                        i12 = i11;
                    }
                    if ((i2 & 32) != 0) {
                        i12 &= -458753;
                        j3 = jM3144unboximpl;
                    }
                    if (i8 != 0) {
                        modifier4 = modifier2;
                        j6 = j3;
                        mutableInteractionSource3 = null;
                    } else {
                        modifier4 = modifier2;
                        j6 = j3;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                final boolean z113 = z4;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1573136853, i12, -1, "androidx.compose.material3.Tab (Tab.kt:237)");
                }
                final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default9 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                final boolean z114 = z3;
                int i113 = i12 >> 12;
                m989TabTransitionKlgxPg(jM3144unboximpl, j6, z, ComposableLambdaKt.rememberComposableLambda(1128552423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$3
                    public final void invoke(Composer composer2, int i114) {
                        if (!composer2.shouldExecute((i114 & 3) != 2, i114 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1128552423, i114, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:244)");
                        }
                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SelectableKt.selectable-O2vRcR0(modifier4, z114, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default9, z113, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function0), 0.0f, 1, (Object) null);
                        Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                        Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composer2, 54);
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor = companion.getConstructor();
                        if (composer2.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composer2.startReusableNode();
                        if (composer2.getInserting()) {
                            composer2.createNode(constructor);
                        } else {
                            composer2.useNode();
                        }
                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                        function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                        composer2.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i113 & 112) | (i113 & 14) | 3072 | ((i12 << 6) & 896));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j4 = jM3144unboximpl;
                mutableInteractionSource2 = mutableInteractionSource3;
                j5 = j6;
                modifier3 = modifier4;
                z6 = z113;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                z6 = z4;
                j4 = jM3144unboximpl;
                j5 = j3;
                mutableInteractionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: l1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabKt.e(z, function0, modifier3, z6, j4, j5, mutableInteractionSource2, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                modifier2 = modifier;
                if (composerStartRestartGroup.changed(modifier2)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            i6 = i2 & 8;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 16) == 0) {
                        jM3144unboximpl = j;
                        if (composerStartRestartGroup.changed(jM3144unboximpl)) {
                        }
                        i3 |= i13;
                    } else {
                        jM3144unboximpl = j;
                    }
                    i3 |= i13;
                } else {
                    jM3144unboximpl = j;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        j3 = j2;
                        if (composerStartRestartGroup.changed(j3)) {
                        }
                        i3 |= i14;
                    } else {
                        j3 = j2;
                    }
                    i3 |= i14;
                } else {
                    j3 = j2;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    i3 |= 1572864;
                } else if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                if ((i2 & 128) != 0) {
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i10 = 8388608;
                        } else {
                            i10 = 4194304;
                        }
                        i3 |= i10;
                    }
                    i11 = i3;
                    if ((i3 & 4793491) != 4793490) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 16) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i12 = i11 & (-57345);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 32) != 0) {
                                i12 &= -458753;
                                j3 = jM3144unboximpl;
                            }
                            if (i8 != 0) {
                                modifier4 = modifier2;
                                j6 = j3;
                                mutableInteractionSource3 = null;
                            } else {
                                modifier4 = modifier2;
                                j6 = j3;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i4 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 16) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i12 = i11 & (-57345);
                            } else {
                                i12 = i11;
                            }
                            if ((i2 & 32) != 0) {
                                i12 &= -458753;
                                j3 = jM3144unboximpl;
                            }
                            if (i8 != 0) {
                                modifier4 = modifier2;
                                j6 = j3;
                                mutableInteractionSource3 = null;
                            } else {
                                modifier4 = modifier2;
                                j6 = j3;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        final boolean z115 = z4;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1573136853, i12, -1, "androidx.compose.material3.Tab (Tab.kt:237)");
                        }
                        final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default10 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                        final boolean z116 = z3;
                        int i114 = i12 >> 12;
                        m989TabTransitionKlgxPg(jM3144unboximpl, j6, z, ComposableLambdaKt.rememberComposableLambda(1128552423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$3
                            public final void invoke(Composer composer2, int i115) {
                                if (!composer2.shouldExecute((i115 & 3) != 2, i115 & 1)) {
                                    composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1128552423, i115, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:244)");
                                }
                                Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SelectableKt.selectable-O2vRcR0(modifier4, z116, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default10, z115, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function0), 0.0f, 1, (Object) null);
                                Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                                Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                                Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composer2, 54);
                                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                                CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                                ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                                Function0<ComposeUiNode> constructor = companion.getConstructor();
                                if (composer2.getApplier() == null) {
                                    ComposablesKt.invalidApplier();
                                }
                                composer2.startReusableNode();
                                if (composer2.getInserting()) {
                                    composer2.createNode(constructor);
                                } else {
                                    composer2.useNode();
                                }
                                Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                                if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                }
                                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                                function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                                composer2.endNode();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i114 & 112) | (i114 & 14) | 3072 | ((i12 << 6) & 896));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        j4 = jM3144unboximpl;
                        mutableInteractionSource2 = mutableInteractionSource3;
                        j5 = j6;
                        modifier3 = modifier4;
                        z6 = z115;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        z6 = z4;
                        j4 = jM3144unboximpl;
                        j5 = j3;
                        mutableInteractionSource2 = mutableInteractionSource;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: l1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.e(z, function0, modifier3, z6, j4, j5, mutableInteractionSource2, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 12582912;
                i11 = i3;
                if ((i3 & 4793491) != 4793490) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 16) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i12 = i11 & (-57345);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 32) != 0) {
                            i12 &= -458753;
                            j3 = jM3144unboximpl;
                        }
                        if (i8 != 0) {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = null;
                        } else {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 16) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i12 = i11 & (-57345);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 32) != 0) {
                            i12 &= -458753;
                            j3 = jM3144unboximpl;
                        }
                        if (i8 != 0) {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = null;
                        } else {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    final boolean z117 = z4;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1573136853, i12, -1, "androidx.compose.material3.Tab (Tab.kt:237)");
                    }
                    final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default11 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                    final boolean z118 = z3;
                    int i115 = i12 >> 12;
                    m989TabTransitionKlgxPg(jM3144unboximpl, j6, z, ComposableLambdaKt.rememberComposableLambda(1128552423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$3
                        public final void invoke(Composer composer2, int i116) {
                            if (!composer2.shouldExecute((i116 & 3) != 2, i116 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1128552423, i116, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:244)");
                            }
                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SelectableKt.selectable-O2vRcR0(modifier4, z118, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default11, z117, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function0), 0.0f, 1, (Object) null);
                            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composer2, 54);
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                            Function0<ComposeUiNode> constructor = companion.getConstructor();
                            if (composer2.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composer2.startReusableNode();
                            if (composer2.getInserting()) {
                                composer2.createNode(constructor);
                            } else {
                                composer2.useNode();
                            }
                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                            function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                            composer2.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i115 & 112) | (i115 & 14) | 3072 | ((i12 << 6) & 896));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j4 = jM3144unboximpl;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    j5 = j6;
                    modifier3 = modifier4;
                    z6 = z117;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z6 = z4;
                    j4 = jM3144unboximpl;
                    j5 = j3;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: l1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.e(z, function0, modifier3, z6, j4, j5, mutableInteractionSource2, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z4 = z2;
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    jM3144unboximpl = j;
                    if (composerStartRestartGroup.changed(jM3144unboximpl)) {
                    }
                    i3 |= i13;
                } else {
                    jM3144unboximpl = j;
                }
                i3 |= i13;
            } else {
                jM3144unboximpl = j;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    j3 = j2;
                    if (composerStartRestartGroup.changed(j3)) {
                    }
                    i3 |= i14;
                } else {
                    j3 = j2;
                }
                i3 |= i14;
            } else {
                j3 = j2;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
            if ((i2 & 128) != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i10 = 8388608;
                    } else {
                        i10 = 4194304;
                    }
                    i3 |= i10;
                }
                i11 = i3;
                if ((i3 & 4793491) != 4793490) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 16) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i12 = i11 & (-57345);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 32) != 0) {
                            i12 &= -458753;
                            j3 = jM3144unboximpl;
                        }
                        if (i8 != 0) {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = null;
                        } else {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 16) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i12 = i11 & (-57345);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 32) != 0) {
                            i12 &= -458753;
                            j3 = jM3144unboximpl;
                        }
                        if (i8 != 0) {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = null;
                        } else {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    final boolean z119 = z4;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1573136853, i12, -1, "androidx.compose.material3.Tab (Tab.kt:237)");
                    }
                    final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default12 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                    final boolean z1110 = z3;
                    int i116 = i12 >> 12;
                    m989TabTransitionKlgxPg(jM3144unboximpl, j6, z, ComposableLambdaKt.rememberComposableLambda(1128552423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$3
                        public final void invoke(Composer composer2, int i117) {
                            if (!composer2.shouldExecute((i117 & 3) != 2, i117 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1128552423, i117, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:244)");
                            }
                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SelectableKt.selectable-O2vRcR0(modifier4, z1110, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default12, z119, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function0), 0.0f, 1, (Object) null);
                            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composer2, 54);
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                            Function0<ComposeUiNode> constructor = companion.getConstructor();
                            if (composer2.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composer2.startReusableNode();
                            if (composer2.getInserting()) {
                                composer2.createNode(constructor);
                            } else {
                                composer2.useNode();
                            }
                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                            function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                            composer2.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i116 & 112) | (i116 & 14) | 3072 | ((i12 << 6) & 896));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j4 = jM3144unboximpl;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    j5 = j6;
                    modifier3 = modifier4;
                    z6 = z119;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z6 = z4;
                    j4 = jM3144unboximpl;
                    j5 = j3;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: l1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.e(z, function0, modifier3, z6, j4, j5, mutableInteractionSource2, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            i11 = i3;
            if ((i3 & 4793491) != 4793490) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 16) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i12 = i11 & (-57345);
                    } else {
                        i12 = i11;
                    }
                    if ((i2 & 32) != 0) {
                        i12 &= -458753;
                        j3 = jM3144unboximpl;
                    }
                    if (i8 != 0) {
                        modifier4 = modifier2;
                        j6 = j3;
                        mutableInteractionSource3 = null;
                    } else {
                        modifier4 = modifier2;
                        j6 = j3;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 16) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i12 = i11 & (-57345);
                    } else {
                        i12 = i11;
                    }
                    if ((i2 & 32) != 0) {
                        i12 &= -458753;
                        j3 = jM3144unboximpl;
                    }
                    if (i8 != 0) {
                        modifier4 = modifier2;
                        j6 = j3;
                        mutableInteractionSource3 = null;
                    } else {
                        modifier4 = modifier2;
                        j6 = j3;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                final boolean z1111 = z4;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1573136853, i12, -1, "androidx.compose.material3.Tab (Tab.kt:237)");
                }
                final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default13 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                final boolean z1112 = z3;
                int i117 = i12 >> 12;
                m989TabTransitionKlgxPg(jM3144unboximpl, j6, z, ComposableLambdaKt.rememberComposableLambda(1128552423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$3
                    public final void invoke(Composer composer2, int i118) {
                        if (!composer2.shouldExecute((i118 & 3) != 2, i118 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1128552423, i118, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:244)");
                        }
                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SelectableKt.selectable-O2vRcR0(modifier4, z1112, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default13, z1111, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function0), 0.0f, 1, (Object) null);
                        Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                        Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composer2, 54);
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor = companion.getConstructor();
                        if (composer2.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composer2.startReusableNode();
                        if (composer2.getInserting()) {
                            composer2.createNode(constructor);
                        } else {
                            composer2.useNode();
                        }
                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                        function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                        composer2.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i117 & 112) | (i117 & 14) | 3072 | ((i12 << 6) & 896));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j4 = jM3144unboximpl;
                mutableInteractionSource2 = mutableInteractionSource3;
                j5 = j6;
                modifier3 = modifier4;
                z6 = z1111;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                z6 = z4;
                j4 = jM3144unboximpl;
                j5 = j3;
                mutableInteractionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: l1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabKt.e(z, function0, modifier3, z6, j4, j5, mutableInteractionSource2, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        modifier2 = modifier;
        i6 = i2 & 8;
        if (i6 != 0) {
            if ((i & 3072) == 0) {
                z4 = z2;
                if (composerStartRestartGroup.changed(z4)) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i3 |= i7;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 16) == 0) {
                    jM3144unboximpl = j;
                    if (composerStartRestartGroup.changed(jM3144unboximpl)) {
                    }
                    i3 |= i13;
                } else {
                    jM3144unboximpl = j;
                }
                i3 |= i13;
            } else {
                jM3144unboximpl = j;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    j3 = j2;
                    if (composerStartRestartGroup.changed(j3)) {
                    }
                    i3 |= i14;
                } else {
                    j3 = j2;
                }
                i3 |= i14;
            } else {
                j3 = j2;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 1572864;
            } else if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
            if ((i2 & 128) != 0) {
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i10 = 8388608;
                    } else {
                        i10 = 4194304;
                    }
                    i3 |= i10;
                }
                i11 = i3;
                if ((i3 & 4793491) != 4793490) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 16) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i12 = i11 & (-57345);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 32) != 0) {
                            i12 &= -458753;
                            j3 = jM3144unboximpl;
                        }
                        if (i8 != 0) {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = null;
                        } else {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i4 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 16) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i12 = i11 & (-57345);
                        } else {
                            i12 = i11;
                        }
                        if ((i2 & 32) != 0) {
                            i12 &= -458753;
                            j3 = jM3144unboximpl;
                        }
                        if (i8 != 0) {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = null;
                        } else {
                            modifier4 = modifier2;
                            j6 = j3;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    final boolean z1113 = z4;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1573136853, i12, -1, "androidx.compose.material3.Tab (Tab.kt:237)");
                    }
                    final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default14 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                    final boolean z1114 = z3;
                    int i118 = i12 >> 12;
                    m989TabTransitionKlgxPg(jM3144unboximpl, j6, z, ComposableLambdaKt.rememberComposableLambda(1128552423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$3
                        public final void invoke(Composer composer2, int i119) {
                            if (!composer2.shouldExecute((i119 & 3) != 2, i119 & 1)) {
                                composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1128552423, i119, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:244)");
                            }
                            Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SelectableKt.selectable-O2vRcR0(modifier4, z1114, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default14, z1113, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function0), 0.0f, 1, (Object) null);
                            Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                            Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                            Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composer2, 54);
                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                            CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                            ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                            Function0<ComposeUiNode> constructor = companion.getConstructor();
                            if (composer2.getApplier() == null) {
                                ComposablesKt.invalidApplier();
                            }
                            composer2.startReusableNode();
                            if (composer2.getInserting()) {
                                composer2.createNode(constructor);
                            } else {
                                composer2.useNode();
                            }
                            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                            }
                            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                            function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                            composer2.endNode();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i118 & 112) | (i118 & 14) | 3072 | ((i12 << 6) & 896));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    j4 = jM3144unboximpl;
                    mutableInteractionSource2 = mutableInteractionSource3;
                    j5 = j6;
                    modifier3 = modifier4;
                    z6 = z1113;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z6 = z4;
                    j4 = jM3144unboximpl;
                    j5 = j3;
                    mutableInteractionSource2 = mutableInteractionSource;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: l1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.e(z, function0, modifier3, z6, j4, j5, mutableInteractionSource2, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 12582912;
            i11 = i3;
            if ((i3 & 4793491) != 4793490) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 16) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i12 = i11 & (-57345);
                    } else {
                        i12 = i11;
                    }
                    if ((i2 & 32) != 0) {
                        i12 &= -458753;
                        j3 = jM3144unboximpl;
                    }
                    if (i8 != 0) {
                        modifier4 = modifier2;
                        j6 = j3;
                        mutableInteractionSource3 = null;
                    } else {
                        modifier4 = modifier2;
                        j6 = j3;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 16) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i12 = i11 & (-57345);
                    } else {
                        i12 = i11;
                    }
                    if ((i2 & 32) != 0) {
                        i12 &= -458753;
                        j3 = jM3144unboximpl;
                    }
                    if (i8 != 0) {
                        modifier4 = modifier2;
                        j6 = j3;
                        mutableInteractionSource3 = null;
                    } else {
                        modifier4 = modifier2;
                        j6 = j3;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                final boolean z1115 = z4;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1573136853, i12, -1, "androidx.compose.material3.Tab (Tab.kt:237)");
                }
                final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default15 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                final boolean z1116 = z3;
                int i119 = i12 >> 12;
                m989TabTransitionKlgxPg(jM3144unboximpl, j6, z, ComposableLambdaKt.rememberComposableLambda(1128552423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$3
                    public final void invoke(Composer composer2, int i1110) {
                        if (!composer2.shouldExecute((i1110 & 3) != 2, i1110 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1128552423, i1110, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:244)");
                        }
                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SelectableKt.selectable-O2vRcR0(modifier4, z1116, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default15, z1115, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function0), 0.0f, 1, (Object) null);
                        Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                        Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composer2, 54);
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor = companion.getConstructor();
                        if (composer2.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composer2.startReusableNode();
                        if (composer2.getInserting()) {
                            composer2.createNode(constructor);
                        } else {
                            composer2.useNode();
                        }
                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                        function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                        composer2.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i119 & 112) | (i119 & 14) | 3072 | ((i12 << 6) & 896));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j4 = jM3144unboximpl;
                mutableInteractionSource2 = mutableInteractionSource3;
                j5 = j6;
                modifier3 = modifier4;
                z6 = z1115;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                z6 = z4;
                j4 = jM3144unboximpl;
                j5 = j3;
                mutableInteractionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: l1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabKt.e(z, function0, modifier3, z6, j4, j5, mutableInteractionSource2, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z4 = z2;
        if ((i & 24576) == 0) {
            if ((i2 & 16) == 0) {
                jM3144unboximpl = j;
                if (composerStartRestartGroup.changed(jM3144unboximpl)) {
                }
                i3 |= i13;
            } else {
                jM3144unboximpl = j;
            }
            i3 |= i13;
        } else {
            jM3144unboximpl = j;
        }
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                j3 = j2;
                if (composerStartRestartGroup.changed(j3)) {
                }
                i3 |= i14;
            } else {
                j3 = j2;
            }
            i3 |= i14;
        } else {
            j3 = j2;
        }
        i8 = i2 & 64;
        if (i8 != 0) {
            i3 |= 1572864;
        } else if ((i & 1572864) == 0) {
            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                i9 = 1048576;
            } else {
                i9 = 524288;
            }
            i3 |= i9;
        }
        if ((i2 & 128) != 0) {
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i10 = 8388608;
                } else {
                    i10 = 4194304;
                }
                i3 |= i10;
            }
            i11 = i3;
            if ((i3 & 4793491) != 4793490) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 16) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i12 = i11 & (-57345);
                    } else {
                        i12 = i11;
                    }
                    if ((i2 & 32) != 0) {
                        i12 &= -458753;
                        j3 = jM3144unboximpl;
                    }
                    if (i8 != 0) {
                        modifier4 = modifier2;
                        j6 = j3;
                        mutableInteractionSource3 = null;
                    } else {
                        modifier4 = modifier2;
                        j6 = j3;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i4 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 16) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i12 = i11 & (-57345);
                    } else {
                        i12 = i11;
                    }
                    if ((i2 & 32) != 0) {
                        i12 &= -458753;
                        j3 = jM3144unboximpl;
                    }
                    if (i8 != 0) {
                        modifier4 = modifier2;
                        j6 = j3;
                        mutableInteractionSource3 = null;
                    } else {
                        modifier4 = modifier2;
                        j6 = j3;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                final boolean z1117 = z4;
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1573136853, i12, -1, "androidx.compose.material3.Tab (Tab.kt:237)");
                }
                final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default16 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
                final boolean z1118 = z3;
                int i1110 = i12 >> 12;
                m989TabTransitionKlgxPg(jM3144unboximpl, j6, z, ComposableLambdaKt.rememberComposableLambda(1128552423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$3
                    public final void invoke(Composer composer2, int i1111) {
                        if (!composer2.shouldExecute((i1111 & 3) != 2, i1111 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1128552423, i1111, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:244)");
                        }
                        Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SelectableKt.selectable-O2vRcR0(modifier4, z1118, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default16, z1117, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function0), 0.0f, 1, (Object) null);
                        Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                        Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                        Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composer2, 54);
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                        ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor = companion.getConstructor();
                        if (composer2.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composer2.startReusableNode();
                        if (composer2.getInserting()) {
                            composer2.createNode(constructor);
                        } else {
                            composer2.useNode();
                        }
                        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                        function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                        composer2.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i1110 & 112) | (i1110 & 14) | 3072 | ((i12 << 6) & 896));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                j4 = jM3144unboximpl;
                mutableInteractionSource2 = mutableInteractionSource3;
                j5 = j6;
                modifier3 = modifier4;
                z6 = z1117;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                z6 = z4;
                j4 = jM3144unboximpl;
                j5 = j3;
                mutableInteractionSource2 = mutableInteractionSource;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: l1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabKt.e(z, function0, modifier3, z6, j4, j5, mutableInteractionSource2, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 12582912;
        i11 = i3;
        if ((i3 & 4793491) != 4793490) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z5, i11 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    z4 = true;
                }
                if ((i2 & 16) != 0) {
                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                    i12 = i11 & (-57345);
                } else {
                    i12 = i11;
                }
                if ((i2 & 32) != 0) {
                    i12 &= -458753;
                    j3 = jM3144unboximpl;
                }
                if (i8 != 0) {
                    modifier4 = modifier2;
                    j6 = j3;
                    mutableInteractionSource3 = null;
                } else {
                    modifier4 = modifier2;
                    j6 = j3;
                    mutableInteractionSource3 = mutableInteractionSource;
                }
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    z4 = true;
                }
                if ((i2 & 16) != 0) {
                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                    i12 = i11 & (-57345);
                } else {
                    i12 = i11;
                }
                if ((i2 & 32) != 0) {
                    i12 &= -458753;
                    j3 = jM3144unboximpl;
                }
                if (i8 != 0) {
                    modifier4 = modifier2;
                    j6 = j3;
                    mutableInteractionSource3 = null;
                } else {
                    modifier4 = modifier2;
                    j6 = j3;
                    mutableInteractionSource3 = mutableInteractionSource;
                }
            }
            final boolean z1119 = z4;
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1573136853, i12, -1, "androidx.compose.material3.Tab (Tab.kt:237)");
            }
            final IndicationNodeFactory indicationNodeFactoryM782rippleH2RKhps$default17 = RippleKt.m782rippleH2RKhps$default(true, 0.0f, jM3144unboximpl, 2, null);
            final boolean z11110 = z3;
            int i1111 = i12 >> 12;
            m989TabTransitionKlgxPg(jM3144unboximpl, j6, z, ComposableLambdaKt.rememberComposableLambda(1128552423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$3
                public final void invoke(Composer composer2, int i1112) {
                    if (!composer2.shouldExecute((i1112 & 3) != 2, i1112 & 1)) {
                        composer2.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1128552423, i1112, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:244)");
                    }
                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(SelectableKt.selectable-O2vRcR0(modifier4, z11110, mutableInteractionSource3, indicationNodeFactoryM782rippleH2RKhps$default17, z1119, Role.m5238boximpl(Role.INSTANCE.m5252getTabo7Vup1c()), function0), 0.0f, 1, (Object) null);
                    Alignment.Horizontal centerHorizontally = Alignment.INSTANCE.getCenterHorizontally();
                    Arrangement.HorizontalOrVertical center = Arrangement.INSTANCE.getCenter();
                    Function3<ColumnScope, Composer, Integer, Unit> function4 = function3;
                    MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(center, centerHorizontally, composer2, 54);
                    int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                    CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, modifierFillMaxWidth$default);
                    ComposeUiNode.Companion companion = ComposeUiNode.INSTANCE;
                    Function0<ComposeUiNode> constructor = companion.getConstructor();
                    if (composer2.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composer2.startReusableNode();
                    if (composer2.getInserting()) {
                        composer2.createNode(constructor);
                    } else {
                        composer2.useNode();
                    }
                    Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer2);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyColumnMeasurePolicy, companion.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion.getSetModifier());
                    function4.invoke(ColumnScopeInstance.INSTANCE, composer2, 6);
                    composer2.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i1111 & 112) | (i1111 & 14) | 3072 | ((i12 << 6) & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j4 = jM3144unboximpl;
            mutableInteractionSource2 = mutableInteractionSource3;
            j5 = j6;
            modifier3 = modifier4;
            z6 = z1119;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            z6 = z4;
            j4 = jM3144unboximpl;
            j5 = j3;
            mutableInteractionSource2 = mutableInteractionSource;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: l1e
                public final Object invoke(Object obj, Object obj2) {
                    return TabKt.e(z, function0, modifier3, z6, j4, j5, mutableInteractionSource2, function3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x011d  */
    /* JADX WARN: Code duplicated, block: B:102:0x011f  */
    /* JADX WARN: Code duplicated, block: B:105:0x0128  */
    /* JADX WARN: Code duplicated, block: B:107:0x0137  */
    /* JADX WARN: Code duplicated, block: B:117:0x015a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:118:0x015c  */
    /* JADX WARN: Code duplicated, block: B:120:0x0161  */
    /* JADX WARN: Code duplicated, block: B:122:0x0164  */
    /* JADX WARN: Code duplicated, block: B:124:0x0168  */
    /* JADX WARN: Code duplicated, block: B:127:0x016e  */
    /* JADX WARN: Code duplicated, block: B:128:0x017f  */
    /* JADX WARN: Code duplicated, block: B:131:0x0185  */
    /* JADX WARN: Code duplicated, block: B:132:0x018b  */
    /* JADX WARN: Code duplicated, block: B:135:0x0193  */
    /* JADX WARN: Code duplicated, block: B:136:0x019d  */
    /* JADX WARN: Code duplicated, block: B:139:0x01af  */
    /* JADX WARN: Code duplicated, block: B:142:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:144:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:147:0x020e  */
    /* JADX WARN: Code duplicated, block: B:149:0x021d  */
    /* JADX WARN: Code duplicated, block: B:152:0x0232  */
    /* JADX WARN: Code duplicated, block: B:154:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0062  */
    /* JADX WARN: Code duplicated, block: B:38:0x0067  */
    /* JADX WARN: Code duplicated, block: B:40:0x006b  */
    /* JADX WARN: Code duplicated, block: B:42:0x0073  */
    /* JADX WARN: Code duplicated, block: B:43:0x0076  */
    /* JADX WARN: Code duplicated, block: B:47:0x007d  */
    /* JADX WARN: Code duplicated, block: B:49:0x0082  */
    /* JADX WARN: Code duplicated, block: B:51:0x0086  */
    /* JADX WARN: Code duplicated, block: B:53:0x008e  */
    /* JADX WARN: Code duplicated, block: B:54:0x0091  */
    /* JADX WARN: Code duplicated, block: B:58:0x009a  */
    /* JADX WARN: Code duplicated, block: B:60:0x009e  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:71:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:77:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:80:0x00db  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:85:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:87:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:90:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:92:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:94:0x0101  */
    /* JADX WARN: Code duplicated, block: B:96:0x010b  */
    /* JADX WARN: Code duplicated, block: B:97:0x010e  */
    /* JADX INFO: renamed from: Tab-wqdebIU, reason: not valid java name */
    public static final void m988TabwqdebIU(final boolean z, final Function0<Unit> function0, Modifier modifier, boolean z2, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, long j, long j2, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        boolean z3;
        int i3;
        Function0<Unit> function1;
        Modifier modifier2;
        int i4;
        boolean z4;
        int i5;
        int i6;
        final Function2<? super Composer, ? super Integer, Unit> function4;
        int i7;
        int i8;
        final Function2<? super Composer, ? super Integer, Unit> function5;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        boolean z5;
        Composer composer2;
        final MutableInteractionSource mutableInteractionSource2;
        final Modifier modifier3;
        final boolean z6;
        final Function2<? super Composer, ? super Integer, Unit> function6;
        final Function2<? super Composer, ? super Integer, Unit> function7;
        final long j3;
        final long j4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        long jM3144unboximpl;
        long j5;
        boolean z7;
        final Function2 function2RememberComposableLambda;
        long j6;
        long j7;
        int i14;
        MutableInteractionSource mutableInteractionSource3;
        int i15;
        int i16;
        Composer composerStartRestartGroup = composer.startRestartGroup(1015017965);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            z3 = z;
        } else {
            z3 = z;
            if ((i & 6) == 0) {
                i3 = (composerStartRestartGroup.changed(z3) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
            function1 = function0;
        } else {
            function1 = function0;
            if ((i & 48) == 0) {
                i3 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
            }
        }
        int i17 = i2 & 4;
        if (i17 == 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 16;
                if (i6 != 0) {
                    if ((i & 24576) == 0) {
                        function4 = function2;
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i7 = 16384;
                        } else {
                            i7 = 8192;
                        }
                        i3 |= i7;
                    }
                    i8 = i2 & 32;
                    if (i8 != 0) {
                        if ((196608 & i) == 0) {
                            function5 = function3;
                            if (composerStartRestartGroup.changedInstance(function5)) {
                                i9 = 131072;
                            } else {
                                i9 = 65536;
                            }
                            i3 |= i9;
                        }
                        if ((1572864 & i) == 0) {
                            if ((i2 & 64) == 0) {
                                i16 = i3;
                                i11 = i17;
                                int i18 = composerStartRestartGroup.changed(j) ? 1048576 : 524288;
                                i10 = i16 | i18;
                            } else {
                                i16 = i3;
                                i11 = i17;
                            }
                            i10 = i16 | i18;
                        } else {
                            i10 = i3;
                            i11 = i17;
                        }
                        if ((i & 12582912) != 0) {
                            if ((i2 & 128) == 0 || !composerStartRestartGroup.changed(j2)) {
                                i15 = 4194304;
                            } else {
                                i15 = 8388608;
                            }
                            i10 |= i15;
                        }
                        i12 = i2 & 256;
                        if (i12 != 0) {
                            if ((i & 100663296) == 0) {
                                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                    i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                                } else {
                                    i13 = 33554432;
                                }
                                i10 |= i13;
                            }
                            if ((i10 & 38347923) != 38347922) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                                composerStartRestartGroup.startDefaults();
                                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                    if (i11 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i4 != 0) {
                                        z4 = true;
                                    }
                                    if (i6 != 0) {
                                        function4 = null;
                                    }
                                    if (i8 != 0) {
                                        function5 = null;
                                    }
                                    if ((i2 & 64) != 0) {
                                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                        i10 &= -3670017;
                                    } else {
                                        jM3144unboximpl = j;
                                    }
                                    if ((i2 & 128) != 0) {
                                        i10 &= -29360129;
                                        j5 = jM3144unboximpl;
                                    } else {
                                        j5 = j2;
                                    }
                                    z7 = z4;
                                    function2RememberComposableLambda = null;
                                    if (i12 != 0) {
                                        j6 = jM3144unboximpl;
                                        i14 = 1015017965;
                                        mutableInteractionSource3 = null;
                                        j7 = j5;
                                    } else {
                                        j6 = jM3144unboximpl;
                                        j7 = j5;
                                        i14 = 1015017965;
                                        mutableInteractionSource3 = mutableInteractionSource;
                                    }
                                } else {
                                    composerStartRestartGroup.skipToGroupEnd();
                                    if ((i2 & 64) != 0) {
                                        i10 &= -3670017;
                                    }
                                    if ((i2 & 128) != 0) {
                                        i10 &= -29360129;
                                    }
                                    j6 = j;
                                    mutableInteractionSource3 = mutableInteractionSource;
                                    z7 = z4;
                                    function2RememberComposableLambda = null;
                                    i14 = 1015017965;
                                    j7 = j2;
                                }
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                                }
                                if (function4 == null) {
                                    composerStartRestartGroup.startReplaceGroup(1830899669);
                                } else {
                                    composerStartRestartGroup.startReplaceGroup(1830899670);
                                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                                        public final void invoke(Composer composer3, int i19) {
                                            if (!composer3.shouldExecute((i19 & 3) != 2, i19 & 1)) {
                                                composer3.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-1745256900, i19, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                            }
                                            TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }

                                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                            invoke((Composer) obj, ((Number) obj2).intValue());
                                            return Unit.INSTANCE;
                                        }
                                    }, composerStartRestartGroup, 54);
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                int i19 = i10 >> 6;
                                composer2 = composerStartRestartGroup;
                                m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                                    public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                                        if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                                        }
                                        TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                                        invoke(columnScope, composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i19) | (458752 & i19) | (i19 & 3670016), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier3 = modifier2;
                                function6 = function4;
                                function7 = function5;
                                z6 = z7;
                                j3 = j6;
                                j4 = j7;
                                mutableInteractionSource2 = mutableInteractionSource3;
                            } else {
                                composer2 = composerStartRestartGroup;
                                composer2.skipToGroupEnd();
                                mutableInteractionSource2 = mutableInteractionSource;
                                modifier3 = modifier2;
                                z6 = z4;
                                function6 = function4;
                                function7 = function5;
                                j3 = j;
                                j4 = j2;
                            }
                            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                                    public final Object invoke(Object obj, Object obj2) {
                                        return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i10 |= 100663296;
                        if ((i10 & 38347923) != 38347922) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i11 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    function5 = null;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i10 &= -3670017;
                                } else {
                                    jM3144unboximpl = j;
                                }
                                if ((i2 & 128) != 0) {
                                    i10 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                z7 = z4;
                                function2RememberComposableLambda = null;
                                if (i12 != 0) {
                                    j6 = jM3144unboximpl;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = null;
                                    j7 = j5;
                                } else {
                                    j6 = jM3144unboximpl;
                                    j7 = j5;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            } else {
                                if (i11 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    function5 = null;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i10 &= -3670017;
                                } else {
                                    jM3144unboximpl = j;
                                }
                                if ((i2 & 128) != 0) {
                                    i10 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                z7 = z4;
                                function2RememberComposableLambda = null;
                                if (i12 != 0) {
                                    j6 = jM3144unboximpl;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = null;
                                    j7 = j5;
                                } else {
                                    j6 = jM3144unboximpl;
                                    j7 = j5;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                            }
                            if (function4 == null) {
                                composerStartRestartGroup.startReplaceGroup(1830899669);
                            } else {
                                composerStartRestartGroup.startReplaceGroup(1830899670);
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                                    public final void invoke(Composer composer3, int i110) {
                                        if (!composer3.shouldExecute((i110 & 3) != 2, i110 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1745256900, i110, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                        }
                                        TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            int i110 = i10 >> 6;
                            composer2 = composerStartRestartGroup;
                            m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                                public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                                    if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                                    }
                                    TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                                    invoke(columnScope, composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i110) | (458752 & i110) | (i110 & 3670016), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            function7 = function5;
                            z6 = z7;
                            j3 = j6;
                            j4 = j7;
                            mutableInteractionSource2 = mutableInteractionSource3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            mutableInteractionSource2 = mutableInteractionSource;
                            modifier3 = modifier2;
                            z6 = z4;
                            function6 = function4;
                            function7 = function5;
                            j3 = j;
                            j4 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                                public final Object invoke(Object obj, Object obj2) {
                                    return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    function5 = function3;
                    if ((1572864 & i) == 0) {
                        if ((i2 & 64) == 0) {
                            i16 = i3;
                            i11 = i17;
                            if (composerStartRestartGroup.changed(j)) {
                            }
                            i10 = i16 | i18;
                        } else {
                            i16 = i3;
                            i11 = i17;
                        }
                        i10 = i16 | i18;
                    } else {
                        i10 = i3;
                        i11 = i17;
                    }
                    if ((i & 12582912) != 0) {
                        if ((i2 & 128) == 0) {
                            i15 = 4194304;
                        } else {
                            i15 = 4194304;
                        }
                        i10 |= i15;
                    }
                    i12 = i2 & 256;
                    if (i12 != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i13 = 33554432;
                            }
                            i10 |= i13;
                        }
                        if ((i10 & 38347923) != 38347922) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i11 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    function5 = null;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i10 &= -3670017;
                                } else {
                                    jM3144unboximpl = j;
                                }
                                if ((i2 & 128) != 0) {
                                    i10 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                z7 = z4;
                                function2RememberComposableLambda = null;
                                if (i12 != 0) {
                                    j6 = jM3144unboximpl;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = null;
                                    j7 = j5;
                                } else {
                                    j6 = jM3144unboximpl;
                                    j7 = j5;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            } else {
                                if (i11 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    function5 = null;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i10 &= -3670017;
                                } else {
                                    jM3144unboximpl = j;
                                }
                                if ((i2 & 128) != 0) {
                                    i10 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                z7 = z4;
                                function2RememberComposableLambda = null;
                                if (i12 != 0) {
                                    j6 = jM3144unboximpl;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = null;
                                    j7 = j5;
                                } else {
                                    j6 = jM3144unboximpl;
                                    j7 = j5;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                            }
                            if (function4 == null) {
                                composerStartRestartGroup.startReplaceGroup(1830899669);
                            } else {
                                composerStartRestartGroup.startReplaceGroup(1830899670);
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                                    public final void invoke(Composer composer3, int i111) {
                                        if (!composer3.shouldExecute((i111 & 3) != 2, i111 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1745256900, i111, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                        }
                                        TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            int i111 = i10 >> 6;
                            composer2 = composerStartRestartGroup;
                            m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                                public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                                    if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                                    }
                                    TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                                    invoke(columnScope, composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i111) | (458752 & i111) | (i111 & 3670016), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            function7 = function5;
                            z6 = z7;
                            j3 = j6;
                            j4 = j7;
                            mutableInteractionSource2 = mutableInteractionSource3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            mutableInteractionSource2 = mutableInteractionSource;
                            modifier3 = modifier2;
                            z6 = z4;
                            function6 = function4;
                            function7 = function5;
                            j3 = j;
                            j4 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                                public final Object invoke(Object obj, Object obj2) {
                                    return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i10 |= 100663296;
                    if ((i10 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                        }
                        if (function4 == null) {
                            composerStartRestartGroup.startReplaceGroup(1830899669);
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1830899670);
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                                public final void invoke(Composer composer3, int i112) {
                                    if (!composer3.shouldExecute((i112 & 3) != 2, i112 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1745256900, i112, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                    }
                                    TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i112 = i10 >> 6;
                        composer2 = composerStartRestartGroup;
                        m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                            public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                                if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                                }
                                TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                                invoke(columnScope, composer3, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i112) | (458752 & i112) | (i112 & 3670016), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        function7 = function5;
                        z6 = z7;
                        j3 = j6;
                        j4 = j7;
                        mutableInteractionSource2 = mutableInteractionSource3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        function6 = function4;
                        function7 = function5;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                function4 = function2;
                i8 = i2 & 32;
                if (i8 != 0) {
                    if ((196608 & i) == 0) {
                        function5 = function3;
                        if (composerStartRestartGroup.changedInstance(function5)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                    if ((1572864 & i) == 0) {
                        if ((i2 & 64) == 0) {
                            i16 = i3;
                            i11 = i17;
                            if (composerStartRestartGroup.changed(j)) {
                            }
                            i10 = i16 | i18;
                        } else {
                            i16 = i3;
                            i11 = i17;
                        }
                        i10 = i16 | i18;
                    } else {
                        i10 = i3;
                        i11 = i17;
                    }
                    if ((i & 12582912) != 0) {
                        if ((i2 & 128) == 0) {
                            i15 = 4194304;
                        } else {
                            i15 = 4194304;
                        }
                        i10 |= i15;
                    }
                    i12 = i2 & 256;
                    if (i12 != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i13 = 33554432;
                            }
                            i10 |= i13;
                        }
                        if ((i10 & 38347923) != 38347922) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i11 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    function5 = null;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i10 &= -3670017;
                                } else {
                                    jM3144unboximpl = j;
                                }
                                if ((i2 & 128) != 0) {
                                    i10 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                z7 = z4;
                                function2RememberComposableLambda = null;
                                if (i12 != 0) {
                                    j6 = jM3144unboximpl;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = null;
                                    j7 = j5;
                                } else {
                                    j6 = jM3144unboximpl;
                                    j7 = j5;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            } else {
                                if (i11 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    function5 = null;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i10 &= -3670017;
                                } else {
                                    jM3144unboximpl = j;
                                }
                                if ((i2 & 128) != 0) {
                                    i10 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                z7 = z4;
                                function2RememberComposableLambda = null;
                                if (i12 != 0) {
                                    j6 = jM3144unboximpl;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = null;
                                    j7 = j5;
                                } else {
                                    j6 = jM3144unboximpl;
                                    j7 = j5;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                            }
                            if (function4 == null) {
                                composerStartRestartGroup.startReplaceGroup(1830899669);
                            } else {
                                composerStartRestartGroup.startReplaceGroup(1830899670);
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                                    public final void invoke(Composer composer3, int i113) {
                                        if (!composer3.shouldExecute((i113 & 3) != 2, i113 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1745256900, i113, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                        }
                                        TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            int i113 = i10 >> 6;
                            composer2 = composerStartRestartGroup;
                            m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                                public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                                    if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                                    }
                                    TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                                    invoke(columnScope, composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i113) | (458752 & i113) | (i113 & 3670016), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            function7 = function5;
                            z6 = z7;
                            j3 = j6;
                            j4 = j7;
                            mutableInteractionSource2 = mutableInteractionSource3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            mutableInteractionSource2 = mutableInteractionSource;
                            modifier3 = modifier2;
                            z6 = z4;
                            function6 = function4;
                            function7 = function5;
                            j3 = j;
                            j4 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                                public final Object invoke(Object obj, Object obj2) {
                                    return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i10 |= 100663296;
                    if ((i10 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                        }
                        if (function4 == null) {
                            composerStartRestartGroup.startReplaceGroup(1830899669);
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1830899670);
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                                public final void invoke(Composer composer3, int i114) {
                                    if (!composer3.shouldExecute((i114 & 3) != 2, i114 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1745256900, i114, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                    }
                                    TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i114 = i10 >> 6;
                        composer2 = composerStartRestartGroup;
                        m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                            public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                                if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                                }
                                TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                                invoke(columnScope, composer3, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i114) | (458752 & i114) | (i114 & 3670016), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        function7 = function5;
                        z6 = z7;
                        j3 = j6;
                        j4 = j7;
                        mutableInteractionSource2 = mutableInteractionSource3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        function6 = function4;
                        function7 = function5;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                function5 = function3;
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        i16 = i3;
                        i11 = i17;
                        if (composerStartRestartGroup.changed(j)) {
                        }
                        i10 = i16 | i18;
                    } else {
                        i16 = i3;
                        i11 = i17;
                    }
                    i10 = i16 | i18;
                } else {
                    i10 = i3;
                    i11 = i17;
                }
                if ((i & 12582912) != 0) {
                    if ((i2 & 128) == 0) {
                        i15 = 4194304;
                    } else {
                        i15 = 4194304;
                    }
                    i10 |= i15;
                }
                i12 = i2 & 256;
                if (i12 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i13 = 33554432;
                        }
                        i10 |= i13;
                    }
                    if ((i10 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                        }
                        if (function4 == null) {
                            composerStartRestartGroup.startReplaceGroup(1830899669);
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1830899670);
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                                public final void invoke(Composer composer3, int i115) {
                                    if (!composer3.shouldExecute((i115 & 3) != 2, i115 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1745256900, i115, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                    }
                                    TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i115 = i10 >> 6;
                        composer2 = composerStartRestartGroup;
                        m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                            public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                                if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                                }
                                TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                                invoke(columnScope, composer3, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i115) | (458752 & i115) | (i115 & 3670016), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        function7 = function5;
                        z6 = z7;
                        j3 = j6;
                        j4 = j7;
                        mutableInteractionSource2 = mutableInteractionSource3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        function6 = function4;
                        function7 = function5;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i10 |= 100663296;
                if ((i10 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                    }
                    if (function4 == null) {
                        composerStartRestartGroup.startReplaceGroup(1830899669);
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1830899670);
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                            public final void invoke(Composer composer3, int i116) {
                                if (!composer3.shouldExecute((i116 & 3) != 2, i116 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1745256900, i116, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                }
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i116 = i10 >> 6;
                    composer2 = composerStartRestartGroup;
                    m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                        public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                            if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                            }
                            TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                            invoke(columnScope, composer3, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i116) | (458752 & i116) | (i116 & 3670016), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    function7 = function5;
                    z6 = z7;
                    j3 = j6;
                    j4 = j7;
                    mutableInteractionSource2 = mutableInteractionSource3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    function6 = function4;
                    function7 = function5;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z4 = z2;
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    function4 = function2;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 32;
                if (i8 != 0) {
                    if ((196608 & i) == 0) {
                        function5 = function3;
                        if (composerStartRestartGroup.changedInstance(function5)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                    if ((1572864 & i) == 0) {
                        if ((i2 & 64) == 0) {
                            i16 = i3;
                            i11 = i17;
                            if (composerStartRestartGroup.changed(j)) {
                            }
                            i10 = i16 | i18;
                        } else {
                            i16 = i3;
                            i11 = i17;
                        }
                        i10 = i16 | i18;
                    } else {
                        i10 = i3;
                        i11 = i17;
                    }
                    if ((i & 12582912) != 0) {
                        if ((i2 & 128) == 0) {
                            i15 = 4194304;
                        } else {
                            i15 = 4194304;
                        }
                        i10 |= i15;
                    }
                    i12 = i2 & 256;
                    if (i12 != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i13 = 33554432;
                            }
                            i10 |= i13;
                        }
                        if ((i10 & 38347923) != 38347922) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i11 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    function5 = null;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i10 &= -3670017;
                                } else {
                                    jM3144unboximpl = j;
                                }
                                if ((i2 & 128) != 0) {
                                    i10 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                z7 = z4;
                                function2RememberComposableLambda = null;
                                if (i12 != 0) {
                                    j6 = jM3144unboximpl;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = null;
                                    j7 = j5;
                                } else {
                                    j6 = jM3144unboximpl;
                                    j7 = j5;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            } else {
                                if (i11 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    function5 = null;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i10 &= -3670017;
                                } else {
                                    jM3144unboximpl = j;
                                }
                                if ((i2 & 128) != 0) {
                                    i10 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                z7 = z4;
                                function2RememberComposableLambda = null;
                                if (i12 != 0) {
                                    j6 = jM3144unboximpl;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = null;
                                    j7 = j5;
                                } else {
                                    j6 = jM3144unboximpl;
                                    j7 = j5;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                            }
                            if (function4 == null) {
                                composerStartRestartGroup.startReplaceGroup(1830899669);
                            } else {
                                composerStartRestartGroup.startReplaceGroup(1830899670);
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                                    public final void invoke(Composer composer3, int i117) {
                                        if (!composer3.shouldExecute((i117 & 3) != 2, i117 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1745256900, i117, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                        }
                                        TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            int i117 = i10 >> 6;
                            composer2 = composerStartRestartGroup;
                            m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                                public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                                    if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                                    }
                                    TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                                    invoke(columnScope, composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i117) | (458752 & i117) | (i117 & 3670016), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            function7 = function5;
                            z6 = z7;
                            j3 = j6;
                            j4 = j7;
                            mutableInteractionSource2 = mutableInteractionSource3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            mutableInteractionSource2 = mutableInteractionSource;
                            modifier3 = modifier2;
                            z6 = z4;
                            function6 = function4;
                            function7 = function5;
                            j3 = j;
                            j4 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                                public final Object invoke(Object obj, Object obj2) {
                                    return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i10 |= 100663296;
                    if ((i10 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                        }
                        if (function4 == null) {
                            composerStartRestartGroup.startReplaceGroup(1830899669);
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1830899670);
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                                public final void invoke(Composer composer3, int i118) {
                                    if (!composer3.shouldExecute((i118 & 3) != 2, i118 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1745256900, i118, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                    }
                                    TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i118 = i10 >> 6;
                        composer2 = composerStartRestartGroup;
                        m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                            public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                                if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                                }
                                TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                                invoke(columnScope, composer3, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i118) | (458752 & i118) | (i118 & 3670016), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        function7 = function5;
                        z6 = z7;
                        j3 = j6;
                        j4 = j7;
                        mutableInteractionSource2 = mutableInteractionSource3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        function6 = function4;
                        function7 = function5;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                function5 = function3;
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        i16 = i3;
                        i11 = i17;
                        if (composerStartRestartGroup.changed(j)) {
                        }
                        i10 = i16 | i18;
                    } else {
                        i16 = i3;
                        i11 = i17;
                    }
                    i10 = i16 | i18;
                } else {
                    i10 = i3;
                    i11 = i17;
                }
                if ((i & 12582912) != 0) {
                    if ((i2 & 128) == 0) {
                        i15 = 4194304;
                    } else {
                        i15 = 4194304;
                    }
                    i10 |= i15;
                }
                i12 = i2 & 256;
                if (i12 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i13 = 33554432;
                        }
                        i10 |= i13;
                    }
                    if ((i10 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                        }
                        if (function4 == null) {
                            composerStartRestartGroup.startReplaceGroup(1830899669);
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1830899670);
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                                public final void invoke(Composer composer3, int i119) {
                                    if (!composer3.shouldExecute((i119 & 3) != 2, i119 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1745256900, i119, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                    }
                                    TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i119 = i10 >> 6;
                        composer2 = composerStartRestartGroup;
                        m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                            public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                                if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                                }
                                TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                                invoke(columnScope, composer3, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i119) | (458752 & i119) | (i119 & 3670016), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        function7 = function5;
                        z6 = z7;
                        j3 = j6;
                        j4 = j7;
                        mutableInteractionSource2 = mutableInteractionSource3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        function6 = function4;
                        function7 = function5;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i10 |= 100663296;
                if ((i10 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                    }
                    if (function4 == null) {
                        composerStartRestartGroup.startReplaceGroup(1830899669);
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1830899670);
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                            public final void invoke(Composer composer3, int i1110) {
                                if (!composer3.shouldExecute((i1110 & 3) != 2, i1110 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1745256900, i1110, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                }
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i1110 = i10 >> 6;
                    composer2 = composerStartRestartGroup;
                    m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                        public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                            if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                            }
                            TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                            invoke(columnScope, composer3, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i1110) | (458752 & i1110) | (i1110 & 3670016), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    function7 = function5;
                    z6 = z7;
                    j3 = j6;
                    j4 = j7;
                    mutableInteractionSource2 = mutableInteractionSource3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    function6 = function4;
                    function7 = function5;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            function4 = function2;
            i8 = i2 & 32;
            if (i8 != 0) {
                if ((196608 & i) == 0) {
                    function5 = function3;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        i16 = i3;
                        i11 = i17;
                        if (composerStartRestartGroup.changed(j)) {
                        }
                        i10 = i16 | i18;
                    } else {
                        i16 = i3;
                        i11 = i17;
                    }
                    i10 = i16 | i18;
                } else {
                    i10 = i3;
                    i11 = i17;
                }
                if ((i & 12582912) != 0) {
                    if ((i2 & 128) == 0) {
                        i15 = 4194304;
                    } else {
                        i15 = 4194304;
                    }
                    i10 |= i15;
                }
                i12 = i2 & 256;
                if (i12 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i13 = 33554432;
                        }
                        i10 |= i13;
                    }
                    if ((i10 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                        }
                        if (function4 == null) {
                            composerStartRestartGroup.startReplaceGroup(1830899669);
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1830899670);
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                                public final void invoke(Composer composer3, int i1111) {
                                    if (!composer3.shouldExecute((i1111 & 3) != 2, i1111 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1745256900, i1111, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                    }
                                    TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i1111 = i10 >> 6;
                        composer2 = composerStartRestartGroup;
                        m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                            public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                                if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                                }
                                TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                                invoke(columnScope, composer3, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i1111) | (458752 & i1111) | (i1111 & 3670016), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        function7 = function5;
                        z6 = z7;
                        j3 = j6;
                        j4 = j7;
                        mutableInteractionSource2 = mutableInteractionSource3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        function6 = function4;
                        function7 = function5;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i10 |= 100663296;
                if ((i10 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                    }
                    if (function4 == null) {
                        composerStartRestartGroup.startReplaceGroup(1830899669);
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1830899670);
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                            public final void invoke(Composer composer3, int i1112) {
                                if (!composer3.shouldExecute((i1112 & 3) != 2, i1112 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1745256900, i1112, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                }
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i1112 = i10 >> 6;
                    composer2 = composerStartRestartGroup;
                    m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                        public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                            if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                            }
                            TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                            invoke(columnScope, composer3, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i1112) | (458752 & i1112) | (i1112 & 3670016), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    function7 = function5;
                    z6 = z7;
                    j3 = j6;
                    j4 = j7;
                    mutableInteractionSource2 = mutableInteractionSource3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    function6 = function4;
                    function7 = function5;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function5 = function3;
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    i16 = i3;
                    i11 = i17;
                    if (composerStartRestartGroup.changed(j)) {
                    }
                    i10 = i16 | i18;
                } else {
                    i16 = i3;
                    i11 = i17;
                }
                i10 = i16 | i18;
            } else {
                i10 = i3;
                i11 = i17;
            }
            if ((i & 12582912) != 0) {
                if ((i2 & 128) == 0) {
                    i15 = 4194304;
                } else {
                    i15 = 4194304;
                }
                i10 |= i15;
            }
            i12 = i2 & 256;
            if (i12 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i13 = 33554432;
                    }
                    i10 |= i13;
                }
                if ((i10 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                    }
                    if (function4 == null) {
                        composerStartRestartGroup.startReplaceGroup(1830899669);
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1830899670);
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                            public final void invoke(Composer composer3, int i1113) {
                                if (!composer3.shouldExecute((i1113 & 3) != 2, i1113 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1745256900, i1113, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                }
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i1113 = i10 >> 6;
                    composer2 = composerStartRestartGroup;
                    m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                        public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                            if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                            }
                            TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                            invoke(columnScope, composer3, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i1113) | (458752 & i1113) | (i1113 & 3670016), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    function7 = function5;
                    z6 = z7;
                    j3 = j6;
                    j4 = j7;
                    mutableInteractionSource2 = mutableInteractionSource3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    function6 = function4;
                    function7 = function5;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i10 |= 100663296;
            if ((i10 & 38347923) != 38347922) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        function5 = null;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i10 &= -3670017;
                    } else {
                        jM3144unboximpl = j;
                    }
                    if ((i2 & 128) != 0) {
                        i10 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    z7 = z4;
                    function2RememberComposableLambda = null;
                    if (i12 != 0) {
                        j6 = jM3144unboximpl;
                        i14 = 1015017965;
                        mutableInteractionSource3 = null;
                        j7 = j5;
                    } else {
                        j6 = jM3144unboximpl;
                        j7 = j5;
                        i14 = 1015017965;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        function5 = null;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i10 &= -3670017;
                    } else {
                        jM3144unboximpl = j;
                    }
                    if ((i2 & 128) != 0) {
                        i10 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    z7 = z4;
                    function2RememberComposableLambda = null;
                    if (i12 != 0) {
                        j6 = jM3144unboximpl;
                        i14 = 1015017965;
                        mutableInteractionSource3 = null;
                        j7 = j5;
                    } else {
                        j6 = jM3144unboximpl;
                        j7 = j5;
                        i14 = 1015017965;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                }
                if (function4 == null) {
                    composerStartRestartGroup.startReplaceGroup(1830899669);
                } else {
                    composerStartRestartGroup.startReplaceGroup(1830899670);
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                        public final void invoke(Composer composer3, int i1114) {
                            if (!composer3.shouldExecute((i1114 & 3) != 2, i1114 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1745256900, i1114, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                            }
                            TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                }
                composerStartRestartGroup.endReplaceGroup();
                int i1114 = i10 >> 6;
                composer2 = composerStartRestartGroup;
                m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                    public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                        if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                        }
                        TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                        invoke(columnScope, composer3, num.intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i1114) | (458752 & i1114) | (i1114 & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier2;
                function6 = function4;
                function7 = function5;
                z6 = z7;
                j3 = j6;
                j4 = j7;
                mutableInteractionSource2 = mutableInteractionSource3;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                mutableInteractionSource2 = mutableInteractionSource;
                modifier3 = modifier2;
                z6 = z4;
                function6 = function4;
                function7 = function5;
                j3 = j;
                j4 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        modifier2 = modifier;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                z4 = z2;
                if (composerStartRestartGroup.changed(z4)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    function4 = function2;
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 32;
                if (i8 != 0) {
                    if ((196608 & i) == 0) {
                        function5 = function3;
                        if (composerStartRestartGroup.changedInstance(function5)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                    if ((1572864 & i) == 0) {
                        if ((i2 & 64) == 0) {
                            i16 = i3;
                            i11 = i17;
                            if (composerStartRestartGroup.changed(j)) {
                            }
                            i10 = i16 | i18;
                        } else {
                            i16 = i3;
                            i11 = i17;
                        }
                        i10 = i16 | i18;
                    } else {
                        i10 = i3;
                        i11 = i17;
                    }
                    if ((i & 12582912) != 0) {
                        if ((i2 & 128) == 0) {
                            i15 = 4194304;
                        } else {
                            i15 = 4194304;
                        }
                        i10 |= i15;
                    }
                    i12 = i2 & 256;
                    if (i12 != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                                i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i13 = 33554432;
                            }
                            i10 |= i13;
                        }
                        if ((i10 & 38347923) != 38347922) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i11 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    function5 = null;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i10 &= -3670017;
                                } else {
                                    jM3144unboximpl = j;
                                }
                                if ((i2 & 128) != 0) {
                                    i10 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                z7 = z4;
                                function2RememberComposableLambda = null;
                                if (i12 != 0) {
                                    j6 = jM3144unboximpl;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = null;
                                    j7 = j5;
                                } else {
                                    j6 = jM3144unboximpl;
                                    j7 = j5;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            } else {
                                if (i11 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    z4 = true;
                                }
                                if (i6 != 0) {
                                    function4 = null;
                                }
                                if (i8 != 0) {
                                    function5 = null;
                                }
                                if ((i2 & 64) != 0) {
                                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                    i10 &= -3670017;
                                } else {
                                    jM3144unboximpl = j;
                                }
                                if ((i2 & 128) != 0) {
                                    i10 &= -29360129;
                                    j5 = jM3144unboximpl;
                                } else {
                                    j5 = j2;
                                }
                                z7 = z4;
                                function2RememberComposableLambda = null;
                                if (i12 != 0) {
                                    j6 = jM3144unboximpl;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = null;
                                    j7 = j5;
                                } else {
                                    j6 = jM3144unboximpl;
                                    j7 = j5;
                                    i14 = 1015017965;
                                    mutableInteractionSource3 = mutableInteractionSource;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                            }
                            if (function4 == null) {
                                composerStartRestartGroup.startReplaceGroup(1830899669);
                            } else {
                                composerStartRestartGroup.startReplaceGroup(1830899670);
                                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                                    public final void invoke(Composer composer3, int i1115) {
                                        if (!composer3.shouldExecute((i1115 & 3) != 2, i1115 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(-1745256900, i1115, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                        }
                                        TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            int i1115 = i10 >> 6;
                            composer2 = composerStartRestartGroup;
                            m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                                public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                                    if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                                    }
                                    TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                @Override // kotlin.jvm.functions.Function3
                                public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                                    invoke(columnScope, composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i1115) | (458752 & i1115) | (i1115 & 3670016), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier2;
                            function6 = function4;
                            function7 = function5;
                            z6 = z7;
                            j3 = j6;
                            j4 = j7;
                            mutableInteractionSource2 = mutableInteractionSource3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            mutableInteractionSource2 = mutableInteractionSource;
                            modifier3 = modifier2;
                            z6 = z4;
                            function6 = function4;
                            function7 = function5;
                            j3 = j;
                            j4 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                                public final Object invoke(Object obj, Object obj2) {
                                    return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i10 |= 100663296;
                    if ((i10 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                        }
                        if (function4 == null) {
                            composerStartRestartGroup.startReplaceGroup(1830899669);
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1830899670);
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                                public final void invoke(Composer composer3, int i1116) {
                                    if (!composer3.shouldExecute((i1116 & 3) != 2, i1116 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1745256900, i1116, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                    }
                                    TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i1116 = i10 >> 6;
                        composer2 = composerStartRestartGroup;
                        m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                            public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                                if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                                }
                                TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                                invoke(columnScope, composer3, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i1116) | (458752 & i1116) | (i1116 & 3670016), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        function7 = function5;
                        z6 = z7;
                        j3 = j6;
                        j4 = j7;
                        mutableInteractionSource2 = mutableInteractionSource3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        function6 = function4;
                        function7 = function5;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                function5 = function3;
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        i16 = i3;
                        i11 = i17;
                        if (composerStartRestartGroup.changed(j)) {
                        }
                        i10 = i16 | i18;
                    } else {
                        i16 = i3;
                        i11 = i17;
                    }
                    i10 = i16 | i18;
                } else {
                    i10 = i3;
                    i11 = i17;
                }
                if ((i & 12582912) != 0) {
                    if ((i2 & 128) == 0) {
                        i15 = 4194304;
                    } else {
                        i15 = 4194304;
                    }
                    i10 |= i15;
                }
                i12 = i2 & 256;
                if (i12 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i13 = 33554432;
                        }
                        i10 |= i13;
                    }
                    if ((i10 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                        }
                        if (function4 == null) {
                            composerStartRestartGroup.startReplaceGroup(1830899669);
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1830899670);
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                                public final void invoke(Composer composer3, int i1117) {
                                    if (!composer3.shouldExecute((i1117 & 3) != 2, i1117 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1745256900, i1117, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                    }
                                    TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i1117 = i10 >> 6;
                        composer2 = composerStartRestartGroup;
                        m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                            public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                                if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                                }
                                TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                                invoke(columnScope, composer3, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i1117) | (458752 & i1117) | (i1117 & 3670016), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        function7 = function5;
                        z6 = z7;
                        j3 = j6;
                        j4 = j7;
                        mutableInteractionSource2 = mutableInteractionSource3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        function6 = function4;
                        function7 = function5;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i10 |= 100663296;
                if ((i10 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                    }
                    if (function4 == null) {
                        composerStartRestartGroup.startReplaceGroup(1830899669);
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1830899670);
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                            public final void invoke(Composer composer3, int i1118) {
                                if (!composer3.shouldExecute((i1118 & 3) != 2, i1118 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1745256900, i1118, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                }
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i1118 = i10 >> 6;
                    composer2 = composerStartRestartGroup;
                    m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                        public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                            if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                            }
                            TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                            invoke(columnScope, composer3, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i1118) | (458752 & i1118) | (i1118 & 3670016), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    function7 = function5;
                    z6 = z7;
                    j3 = j6;
                    j4 = j7;
                    mutableInteractionSource2 = mutableInteractionSource3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    function6 = function4;
                    function7 = function5;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            function4 = function2;
            i8 = i2 & 32;
            if (i8 != 0) {
                if ((196608 & i) == 0) {
                    function5 = function3;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        i16 = i3;
                        i11 = i17;
                        if (composerStartRestartGroup.changed(j)) {
                        }
                        i10 = i16 | i18;
                    } else {
                        i16 = i3;
                        i11 = i17;
                    }
                    i10 = i16 | i18;
                } else {
                    i10 = i3;
                    i11 = i17;
                }
                if ((i & 12582912) != 0) {
                    if ((i2 & 128) == 0) {
                        i15 = 4194304;
                    } else {
                        i15 = 4194304;
                    }
                    i10 |= i15;
                }
                i12 = i2 & 256;
                if (i12 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i13 = 33554432;
                        }
                        i10 |= i13;
                    }
                    if ((i10 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                        }
                        if (function4 == null) {
                            composerStartRestartGroup.startReplaceGroup(1830899669);
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1830899670);
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                                public final void invoke(Composer composer3, int i1119) {
                                    if (!composer3.shouldExecute((i1119 & 3) != 2, i1119 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1745256900, i1119, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                    }
                                    TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i1119 = i10 >> 6;
                        composer2 = composerStartRestartGroup;
                        m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                            public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                                if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                                }
                                TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                                invoke(columnScope, composer3, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i1119) | (458752 & i1119) | (i1119 & 3670016), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        function7 = function5;
                        z6 = z7;
                        j3 = j6;
                        j4 = j7;
                        mutableInteractionSource2 = mutableInteractionSource3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        function6 = function4;
                        function7 = function5;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i10 |= 100663296;
                if ((i10 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                    }
                    if (function4 == null) {
                        composerStartRestartGroup.startReplaceGroup(1830899669);
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1830899670);
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                            public final void invoke(Composer composer3, int i11110) {
                                if (!composer3.shouldExecute((i11110 & 3) != 2, i11110 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1745256900, i11110, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                }
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i11110 = i10 >> 6;
                    composer2 = composerStartRestartGroup;
                    m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                        public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                            if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                            }
                            TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                            invoke(columnScope, composer3, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i11110) | (458752 & i11110) | (i11110 & 3670016), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    function7 = function5;
                    z6 = z7;
                    j3 = j6;
                    j4 = j7;
                    mutableInteractionSource2 = mutableInteractionSource3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    function6 = function4;
                    function7 = function5;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function5 = function3;
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    i16 = i3;
                    i11 = i17;
                    if (composerStartRestartGroup.changed(j)) {
                    }
                    i10 = i16 | i18;
                } else {
                    i16 = i3;
                    i11 = i17;
                }
                i10 = i16 | i18;
            } else {
                i10 = i3;
                i11 = i17;
            }
            if ((i & 12582912) != 0) {
                if ((i2 & 128) == 0) {
                    i15 = 4194304;
                } else {
                    i15 = 4194304;
                }
                i10 |= i15;
            }
            i12 = i2 & 256;
            if (i12 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i13 = 33554432;
                    }
                    i10 |= i13;
                }
                if ((i10 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                    }
                    if (function4 == null) {
                        composerStartRestartGroup.startReplaceGroup(1830899669);
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1830899670);
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                            public final void invoke(Composer composer3, int i11111) {
                                if (!composer3.shouldExecute((i11111 & 3) != 2, i11111 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1745256900, i11111, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                }
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i11111 = i10 >> 6;
                    composer2 = composerStartRestartGroup;
                    m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                        public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                            if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                            }
                            TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                            invoke(columnScope, composer3, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i11111) | (458752 & i11111) | (i11111 & 3670016), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    function7 = function5;
                    z6 = z7;
                    j3 = j6;
                    j4 = j7;
                    mutableInteractionSource2 = mutableInteractionSource3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    function6 = function4;
                    function7 = function5;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i10 |= 100663296;
            if ((i10 & 38347923) != 38347922) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        function5 = null;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i10 &= -3670017;
                    } else {
                        jM3144unboximpl = j;
                    }
                    if ((i2 & 128) != 0) {
                        i10 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    z7 = z4;
                    function2RememberComposableLambda = null;
                    if (i12 != 0) {
                        j6 = jM3144unboximpl;
                        i14 = 1015017965;
                        mutableInteractionSource3 = null;
                        j7 = j5;
                    } else {
                        j6 = jM3144unboximpl;
                        j7 = j5;
                        i14 = 1015017965;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        function5 = null;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i10 &= -3670017;
                    } else {
                        jM3144unboximpl = j;
                    }
                    if ((i2 & 128) != 0) {
                        i10 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    z7 = z4;
                    function2RememberComposableLambda = null;
                    if (i12 != 0) {
                        j6 = jM3144unboximpl;
                        i14 = 1015017965;
                        mutableInteractionSource3 = null;
                        j7 = j5;
                    } else {
                        j6 = jM3144unboximpl;
                        j7 = j5;
                        i14 = 1015017965;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                }
                if (function4 == null) {
                    composerStartRestartGroup.startReplaceGroup(1830899669);
                } else {
                    composerStartRestartGroup.startReplaceGroup(1830899670);
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                        public final void invoke(Composer composer3, int i11112) {
                            if (!composer3.shouldExecute((i11112 & 3) != 2, i11112 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1745256900, i11112, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                            }
                            TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                }
                composerStartRestartGroup.endReplaceGroup();
                int i11112 = i10 >> 6;
                composer2 = composerStartRestartGroup;
                m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                    public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                        if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                        }
                        TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                        invoke(columnScope, composer3, num.intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i11112) | (458752 & i11112) | (i11112 & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier2;
                function6 = function4;
                function7 = function5;
                z6 = z7;
                j3 = j6;
                j4 = j7;
                mutableInteractionSource2 = mutableInteractionSource3;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                mutableInteractionSource2 = mutableInteractionSource;
                modifier3 = modifier2;
                z6 = z4;
                function6 = function4;
                function7 = function5;
                j3 = j;
                j4 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z4 = z2;
        i6 = i2 & 16;
        if (i6 != 0) {
            if ((i & 24576) == 0) {
                function4 = function2;
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i3 |= i7;
            }
            i8 = i2 & 32;
            if (i8 != 0) {
                if ((196608 & i) == 0) {
                    function5 = function3;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
                if ((1572864 & i) == 0) {
                    if ((i2 & 64) == 0) {
                        i16 = i3;
                        i11 = i17;
                        if (composerStartRestartGroup.changed(j)) {
                        }
                        i10 = i16 | i18;
                    } else {
                        i16 = i3;
                        i11 = i17;
                    }
                    i10 = i16 | i18;
                } else {
                    i10 = i3;
                    i11 = i17;
                }
                if ((i & 12582912) != 0) {
                    if ((i2 & 128) == 0) {
                        i15 = 4194304;
                    } else {
                        i15 = 4194304;
                    }
                    i10 |= i15;
                }
                i12 = i2 & 256;
                if (i12 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                            i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i13 = 33554432;
                        }
                        i10 |= i13;
                    }
                    if ((i10 & 38347923) != 38347922) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        } else {
                            if (i11 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                z4 = true;
                            }
                            if (i6 != 0) {
                                function4 = null;
                            }
                            if (i8 != 0) {
                                function5 = null;
                            }
                            if ((i2 & 64) != 0) {
                                jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                                i10 &= -3670017;
                            } else {
                                jM3144unboximpl = j;
                            }
                            if ((i2 & 128) != 0) {
                                i10 &= -29360129;
                                j5 = jM3144unboximpl;
                            } else {
                                j5 = j2;
                            }
                            z7 = z4;
                            function2RememberComposableLambda = null;
                            if (i12 != 0) {
                                j6 = jM3144unboximpl;
                                i14 = 1015017965;
                                mutableInteractionSource3 = null;
                                j7 = j5;
                            } else {
                                j6 = jM3144unboximpl;
                                j7 = j5;
                                i14 = 1015017965;
                                mutableInteractionSource3 = mutableInteractionSource;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                        }
                        if (function4 == null) {
                            composerStartRestartGroup.startReplaceGroup(1830899669);
                        } else {
                            composerStartRestartGroup.startReplaceGroup(1830899670);
                            function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                                public final void invoke(Composer composer3, int i11113) {
                                    if (!composer3.shouldExecute((i11113 & 3) != 2, i11113 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1745256900, i11113, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                    }
                                    TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        int i11113 = i10 >> 6;
                        composer2 = composerStartRestartGroup;
                        m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                            public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                                if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                                }
                                TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            @Override // kotlin.jvm.functions.Function3
                            public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                                invoke(columnScope, composer3, num.intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i11113) | (458752 & i11113) | (i11113 & 3670016), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier2;
                        function6 = function4;
                        function7 = function5;
                        z6 = z7;
                        j3 = j6;
                        j4 = j7;
                        mutableInteractionSource2 = mutableInteractionSource3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        mutableInteractionSource2 = mutableInteractionSource;
                        modifier3 = modifier2;
                        z6 = z4;
                        function6 = function4;
                        function7 = function5;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                            public final Object invoke(Object obj, Object obj2) {
                                return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i10 |= 100663296;
                if ((i10 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                    }
                    if (function4 == null) {
                        composerStartRestartGroup.startReplaceGroup(1830899669);
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1830899670);
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                            public final void invoke(Composer composer3, int i11114) {
                                if (!composer3.shouldExecute((i11114 & 3) != 2, i11114 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1745256900, i11114, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                }
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i11114 = i10 >> 6;
                    composer2 = composerStartRestartGroup;
                    m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                        public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                            if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                            }
                            TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                            invoke(columnScope, composer3, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i11114) | (458752 & i11114) | (i11114 & 3670016), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    function7 = function5;
                    z6 = z7;
                    j3 = j6;
                    j4 = j7;
                    mutableInteractionSource2 = mutableInteractionSource3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    function6 = function4;
                    function7 = function5;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function5 = function3;
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    i16 = i3;
                    i11 = i17;
                    if (composerStartRestartGroup.changed(j)) {
                    }
                    i10 = i16 | i18;
                } else {
                    i16 = i3;
                    i11 = i17;
                }
                i10 = i16 | i18;
            } else {
                i10 = i3;
                i11 = i17;
            }
            if ((i & 12582912) != 0) {
                if ((i2 & 128) == 0) {
                    i15 = 4194304;
                } else {
                    i15 = 4194304;
                }
                i10 |= i15;
            }
            i12 = i2 & 256;
            if (i12 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i13 = 33554432;
                    }
                    i10 |= i13;
                }
                if ((i10 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                    }
                    if (function4 == null) {
                        composerStartRestartGroup.startReplaceGroup(1830899669);
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1830899670);
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                            public final void invoke(Composer composer3, int i11115) {
                                if (!composer3.shouldExecute((i11115 & 3) != 2, i11115 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1745256900, i11115, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                }
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i11115 = i10 >> 6;
                    composer2 = composerStartRestartGroup;
                    m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                        public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                            if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                            }
                            TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                            invoke(columnScope, composer3, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i11115) | (458752 & i11115) | (i11115 & 3670016), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    function7 = function5;
                    z6 = z7;
                    j3 = j6;
                    j4 = j7;
                    mutableInteractionSource2 = mutableInteractionSource3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    function6 = function4;
                    function7 = function5;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i10 |= 100663296;
            if ((i10 & 38347923) != 38347922) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        function5 = null;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i10 &= -3670017;
                    } else {
                        jM3144unboximpl = j;
                    }
                    if ((i2 & 128) != 0) {
                        i10 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    z7 = z4;
                    function2RememberComposableLambda = null;
                    if (i12 != 0) {
                        j6 = jM3144unboximpl;
                        i14 = 1015017965;
                        mutableInteractionSource3 = null;
                        j7 = j5;
                    } else {
                        j6 = jM3144unboximpl;
                        j7 = j5;
                        i14 = 1015017965;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        function5 = null;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i10 &= -3670017;
                    } else {
                        jM3144unboximpl = j;
                    }
                    if ((i2 & 128) != 0) {
                        i10 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    z7 = z4;
                    function2RememberComposableLambda = null;
                    if (i12 != 0) {
                        j6 = jM3144unboximpl;
                        i14 = 1015017965;
                        mutableInteractionSource3 = null;
                        j7 = j5;
                    } else {
                        j6 = jM3144unboximpl;
                        j7 = j5;
                        i14 = 1015017965;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                }
                if (function4 == null) {
                    composerStartRestartGroup.startReplaceGroup(1830899669);
                } else {
                    composerStartRestartGroup.startReplaceGroup(1830899670);
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                        public final void invoke(Composer composer3, int i11116) {
                            if (!composer3.shouldExecute((i11116 & 3) != 2, i11116 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1745256900, i11116, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                            }
                            TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                }
                composerStartRestartGroup.endReplaceGroup();
                int i11116 = i10 >> 6;
                composer2 = composerStartRestartGroup;
                m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                    public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                        if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                        }
                        TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                        invoke(columnScope, composer3, num.intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i11116) | (458752 & i11116) | (i11116 & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier2;
                function6 = function4;
                function7 = function5;
                z6 = z7;
                j3 = j6;
                j4 = j7;
                mutableInteractionSource2 = mutableInteractionSource3;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                mutableInteractionSource2 = mutableInteractionSource;
                modifier3 = modifier2;
                z6 = z4;
                function6 = function4;
                function7 = function5;
                j3 = j;
                j4 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        function4 = function2;
        i8 = i2 & 32;
        if (i8 != 0) {
            if ((196608 & i) == 0) {
                function5 = function3;
                if (composerStartRestartGroup.changedInstance(function5)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i3 |= i9;
            }
            if ((1572864 & i) == 0) {
                if ((i2 & 64) == 0) {
                    i16 = i3;
                    i11 = i17;
                    if (composerStartRestartGroup.changed(j)) {
                    }
                    i10 = i16 | i18;
                } else {
                    i16 = i3;
                    i11 = i17;
                }
                i10 = i16 | i18;
            } else {
                i10 = i3;
                i11 = i17;
            }
            if ((i & 12582912) != 0) {
                if ((i2 & 128) == 0) {
                    i15 = 4194304;
                } else {
                    i15 = 4194304;
                }
                i10 |= i15;
            }
            i12 = i2 & 256;
            if (i12 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                        i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i13 = 33554432;
                    }
                    i10 |= i13;
                }
                if ((i10 & 38347923) != 38347922) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    } else {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            z4 = true;
                        }
                        if (i6 != 0) {
                            function4 = null;
                        }
                        if (i8 != 0) {
                            function5 = null;
                        }
                        if ((i2 & 64) != 0) {
                            jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                            i10 &= -3670017;
                        } else {
                            jM3144unboximpl = j;
                        }
                        if ((i2 & 128) != 0) {
                            i10 &= -29360129;
                            j5 = jM3144unboximpl;
                        } else {
                            j5 = j2;
                        }
                        z7 = z4;
                        function2RememberComposableLambda = null;
                        if (i12 != 0) {
                            j6 = jM3144unboximpl;
                            i14 = 1015017965;
                            mutableInteractionSource3 = null;
                            j7 = j5;
                        } else {
                            j6 = jM3144unboximpl;
                            j7 = j5;
                            i14 = 1015017965;
                            mutableInteractionSource3 = mutableInteractionSource;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                    }
                    if (function4 == null) {
                        composerStartRestartGroup.startReplaceGroup(1830899669);
                    } else {
                        composerStartRestartGroup.startReplaceGroup(1830899670);
                        function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                            public final void invoke(Composer composer3, int i11117) {
                                if (!composer3.shouldExecute((i11117 & 3) != 2, i11117 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1745256900, i11117, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                                }
                                TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    int i11117 = i10 >> 6;
                    composer2 = composerStartRestartGroup;
                    m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                        public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                            if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                            }
                            TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                            invoke(columnScope, composer3, num.intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i11117) | (458752 & i11117) | (i11117 & 3670016), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier2;
                    function6 = function4;
                    function7 = function5;
                    z6 = z7;
                    j3 = j6;
                    j4 = j7;
                    mutableInteractionSource2 = mutableInteractionSource3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    mutableInteractionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    z6 = z4;
                    function6 = function4;
                    function7 = function5;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                        public final Object invoke(Object obj, Object obj2) {
                            return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i10 |= 100663296;
            if ((i10 & 38347923) != 38347922) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        function5 = null;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i10 &= -3670017;
                    } else {
                        jM3144unboximpl = j;
                    }
                    if ((i2 & 128) != 0) {
                        i10 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    z7 = z4;
                    function2RememberComposableLambda = null;
                    if (i12 != 0) {
                        j6 = jM3144unboximpl;
                        i14 = 1015017965;
                        mutableInteractionSource3 = null;
                        j7 = j5;
                    } else {
                        j6 = jM3144unboximpl;
                        j7 = j5;
                        i14 = 1015017965;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        function5 = null;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i10 &= -3670017;
                    } else {
                        jM3144unboximpl = j;
                    }
                    if ((i2 & 128) != 0) {
                        i10 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    z7 = z4;
                    function2RememberComposableLambda = null;
                    if (i12 != 0) {
                        j6 = jM3144unboximpl;
                        i14 = 1015017965;
                        mutableInteractionSource3 = null;
                        j7 = j5;
                    } else {
                        j6 = jM3144unboximpl;
                        j7 = j5;
                        i14 = 1015017965;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                }
                if (function4 == null) {
                    composerStartRestartGroup.startReplaceGroup(1830899669);
                } else {
                    composerStartRestartGroup.startReplaceGroup(1830899670);
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                        public final void invoke(Composer composer3, int i11118) {
                            if (!composer3.shouldExecute((i11118 & 3) != 2, i11118 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1745256900, i11118, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                            }
                            TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                }
                composerStartRestartGroup.endReplaceGroup();
                int i11118 = i10 >> 6;
                composer2 = composerStartRestartGroup;
                m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                    public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                        if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                        }
                        TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                        invoke(columnScope, composer3, num.intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i11118) | (458752 & i11118) | (i11118 & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier2;
                function6 = function4;
                function7 = function5;
                z6 = z7;
                j3 = j6;
                j4 = j7;
                mutableInteractionSource2 = mutableInteractionSource3;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                mutableInteractionSource2 = mutableInteractionSource;
                modifier3 = modifier2;
                z6 = z4;
                function6 = function4;
                function7 = function5;
                j3 = j;
                j4 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        function5 = function3;
        if ((1572864 & i) == 0) {
            if ((i2 & 64) == 0) {
                i16 = i3;
                i11 = i17;
                if (composerStartRestartGroup.changed(j)) {
                }
                i10 = i16 | i18;
            } else {
                i16 = i3;
                i11 = i17;
            }
            i10 = i16 | i18;
        } else {
            i10 = i3;
            i11 = i17;
        }
        if ((i & 12582912) != 0) {
            if ((i2 & 128) == 0) {
                i15 = 4194304;
            } else {
                i15 = 4194304;
            }
            i10 |= i15;
        }
        i12 = i2 & 256;
        if (i12 != 0) {
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changed(mutableInteractionSource)) {
                    i13 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i13 = 33554432;
                }
                i10 |= i13;
            }
            if ((i10 & 38347923) != 38347922) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        function5 = null;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i10 &= -3670017;
                    } else {
                        jM3144unboximpl = j;
                    }
                    if ((i2 & 128) != 0) {
                        i10 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    z7 = z4;
                    function2RememberComposableLambda = null;
                    if (i12 != 0) {
                        j6 = jM3144unboximpl;
                        i14 = 1015017965;
                        mutableInteractionSource3 = null;
                        j7 = j5;
                    } else {
                        j6 = jM3144unboximpl;
                        j7 = j5;
                        i14 = 1015017965;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                } else {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        z4 = true;
                    }
                    if (i6 != 0) {
                        function4 = null;
                    }
                    if (i8 != 0) {
                        function5 = null;
                    }
                    if ((i2 & 64) != 0) {
                        jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                        i10 &= -3670017;
                    } else {
                        jM3144unboximpl = j;
                    }
                    if ((i2 & 128) != 0) {
                        i10 &= -29360129;
                        j5 = jM3144unboximpl;
                    } else {
                        j5 = j2;
                    }
                    z7 = z4;
                    function2RememberComposableLambda = null;
                    if (i12 != 0) {
                        j6 = jM3144unboximpl;
                        i14 = 1015017965;
                        mutableInteractionSource3 = null;
                        j7 = j5;
                    } else {
                        j6 = jM3144unboximpl;
                        j7 = j5;
                        i14 = 1015017965;
                        mutableInteractionSource3 = mutableInteractionSource;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
                }
                if (function4 == null) {
                    composerStartRestartGroup.startReplaceGroup(1830899669);
                } else {
                    composerStartRestartGroup.startReplaceGroup(1830899670);
                    function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                        public final void invoke(Composer composer3, int i11119) {
                            if (!composer3.shouldExecute((i11119 & 3) != 2, i11119 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1745256900, i11119, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                            }
                            TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54);
                }
                composerStartRestartGroup.endReplaceGroup();
                int i11119 = i10 >> 6;
                composer2 = composerStartRestartGroup;
                m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                    public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                        if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                        }
                        TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                        invoke(columnScope, composer3, num.intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i11119) | (458752 & i11119) | (i11119 & 3670016), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier2;
                function6 = function4;
                function7 = function5;
                z6 = z7;
                j3 = j6;
                j4 = j7;
                mutableInteractionSource2 = mutableInteractionSource3;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                mutableInteractionSource2 = mutableInteractionSource;
                modifier3 = modifier2;
                z6 = z4;
                function6 = function4;
                function7 = function5;
                j3 = j;
                j4 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                    public final Object invoke(Object obj, Object obj2) {
                        return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i10 |= 100663296;
        if ((i10 & 38347923) != 38347922) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z5, i10 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i11 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z4 = true;
                }
                if (i6 != 0) {
                    function4 = null;
                }
                if (i8 != 0) {
                    function5 = null;
                }
                if ((i2 & 64) != 0) {
                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                    i10 &= -3670017;
                } else {
                    jM3144unboximpl = j;
                }
                if ((i2 & 128) != 0) {
                    i10 &= -29360129;
                    j5 = jM3144unboximpl;
                } else {
                    j5 = j2;
                }
                z7 = z4;
                function2RememberComposableLambda = null;
                if (i12 != 0) {
                    j6 = jM3144unboximpl;
                    i14 = 1015017965;
                    mutableInteractionSource3 = null;
                    j7 = j5;
                } else {
                    j6 = jM3144unboximpl;
                    j7 = j5;
                    i14 = 1015017965;
                    mutableInteractionSource3 = mutableInteractionSource;
                }
            } else {
                if (i11 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    z4 = true;
                }
                if (i6 != 0) {
                    function4 = null;
                }
                if (i8 != 0) {
                    function5 = null;
                }
                if ((i2 & 64) != 0) {
                    jM3144unboximpl = ((Color) composerStartRestartGroup.consume(ContentColorKt.getLocalContentColor())).m3144unboximpl();
                    i10 &= -3670017;
                } else {
                    jM3144unboximpl = j;
                }
                if ((i2 & 128) != 0) {
                    i10 &= -29360129;
                    j5 = jM3144unboximpl;
                } else {
                    j5 = j2;
                }
                z7 = z4;
                function2RememberComposableLambda = null;
                if (i12 != 0) {
                    j6 = jM3144unboximpl;
                    i14 = 1015017965;
                    mutableInteractionSource3 = null;
                    j7 = j5;
                } else {
                    j6 = jM3144unboximpl;
                    j7 = j5;
                    i14 = 1015017965;
                    mutableInteractionSource3 = mutableInteractionSource;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i14, i10, -1, "androidx.compose.material3.Tab (Tab.kt:100)");
            }
            if (function4 == null) {
                composerStartRestartGroup.startReplaceGroup(1830899669);
            } else {
                composerStartRestartGroup.startReplaceGroup(1830899670);
                function2RememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1745256900, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$styledText$1$1
                    public final void invoke(Composer composer3, int i111110) {
                        if (!composer3.shouldExecute((i111110 & 3) != 2, i111110 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1745256900, i111110, -1, "androidx.compose.material3.Tab.<anonymous>.<anonymous> (Tab.kt:104)");
                        }
                        TextKt.ProvideTextStyle(TextStyle.m5492copyp1EtxEg$default(TypographyKt.getValue(PrimaryNavigationTabTokens.INSTANCE.getLabelTextFont(), composer3, 6), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m5900getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null), function4, composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54);
            }
            composerStartRestartGroup.endReplaceGroup();
            int i111110 = i10 >> 6;
            composer2 = composerStartRestartGroup;
            m987TabbogVsAg(z3, function1, BadgeKt.badgeBounds(modifier2), z7, j6, j7, mutableInteractionSource3, ComposableLambdaKt.rememberComposableLambda(-906085472, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TabKt$Tab$1
                public final void invoke(ColumnScope columnScope, Composer composer3, int i20) {
                    if (!composer3.shouldExecute((i20 & 17) != 16, i20 & 1)) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-906085472, i20, -1, "androidx.compose.material3.Tab.<anonymous> (Tab.kt:120)");
                    }
                    TabKt.TabBaselineLayout(function2RememberComposableLambda, function5, composer3, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer3, Integer num) {
                    invoke(columnScope, composer3, num.intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), composer2, (i10 & 14) | 12582912 | (i10 & 112) | (i10 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i111110) | (458752 & i111110) | (i111110 & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            function6 = function4;
            function7 = function5;
            z6 = z7;
            j3 = j6;
            j4 = j7;
            mutableInteractionSource2 = mutableInteractionSource3;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            mutableInteractionSource2 = mutableInteractionSource;
            modifier3 = modifier2;
            z6 = z4;
            function6 = function4;
            function7 = function5;
            j3 = j;
            j4 = j2;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: m1e
                public final Object invoke(Object obj, Object obj2) {
                    return TabKt.a(z, function0, modifier3, z6, function6, function7, j3, j4, mutableInteractionSource2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void TabBaselineLayout(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1349901398);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function3) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1349901398, i2, -1, "androidx.compose.material3.TabBaselineLayout (Tab.kt:300)");
            }
            int i3 = i2 & 14;
            boolean z = (i3 == 4) | ((i2 & 112) == 32);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new TabKt$TabBaselineLayout$2$1(function2, function3);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
            Modifier.Companion companion = Modifier.INSTANCE;
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, companion);
            ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion2.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicy, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
            if (function2 != null) {
                composerStartRestartGroup.startReplaceGroup(870361332);
                Modifier modifier = PaddingKt.padding-VpY3zN4$default(LayoutIdKt.layoutId(companion, "text"), HorizontalTextPadding, 0.0f, 2, (Object) null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier);
                Function0<ComposeUiNode> constructor2 = companion2.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion2.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = companion2.getSetCompositeKeyHash();
                if (composerM2388constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                    composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion2.getSetModifier());
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                function2.invoke(composerStartRestartGroup, Integer.valueOf(i3));
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(870466081);
                composerStartRestartGroup.endReplaceGroup();
            }
            if (function3 != null) {
                composerStartRestartGroup.startReplaceGroup(870494880);
                Modifier modifierLayoutId = LayoutIdKt.layoutId(companion, "icon");
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierLayoutId);
                Function0<ComposeUiNode> constructor3 = companion2.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composerM2388constructorimpl3 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicyMaybeCachedBoxMeasurePolicy2, companion2.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap3, companion2.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = companion2.getSetCompositeKeyHash();
                if (composerM2388constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                    composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier3, companion2.getSetModifier());
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                function3.invoke(composerStartRestartGroup, Integer.valueOf((i2 >> 3) & 14));
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(870557345);
                composerStartRestartGroup.endReplaceGroup();
            }
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: p1e
                public final Object invoke(Object obj, Object obj2) {
                    return TabKt.b(function2, function3, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: TabTransition-Klgx-Pg, reason: not valid java name */
    private static final void m989TabTransitionKlgxPg(final long j, final long j2, boolean z, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        final boolean z2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-833145221);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(j2) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            z2 = z;
            i2 |= composerStartRestartGroup.changed(z2) ? 256 : 128;
        } else {
            z2 = z;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 2048 : 1024;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 1171) != 1170, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-833145221, i2, -1, "androidx.compose.material3.TabTransition (Tab.kt:274)");
            }
            int i3 = i2 >> 6;
            Transition transitionUpdateTransition = TransitionKt.updateTransition(Boolean.valueOf(z2), (String) null, composerStartRestartGroup, i3 & 14, 2);
            TabKt$TabTransition$color$2 tabKt$TabTransition$color$2 = new Function3<Transition.Segment<Boolean>, Composer, Integer, FiniteAnimationSpec<Color>>() { // from class: androidx.compose.material3.TabKt$TabTransition$color$2
                public final FiniteAnimationSpec<Color> invoke(Transition.Segment<Boolean> segment, Composer composer2, int i4) {
                    FiniteAnimationSpec<Color> finiteAnimationSpecValue;
                    composer2.startReplaceGroup(1058649156);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1058649156, i4, -1, "androidx.compose.material3.TabTransition.<anonymous> (Tab.kt:280)");
                    }
                    if (segment.isTransitioningTo(Boolean.FALSE, Boolean.TRUE)) {
                        composer2.startReplaceGroup(272207019);
                        finiteAnimationSpecValue = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultEffects, composer2, 6);
                        composer2.endReplaceGroup();
                    } else {
                        composer2.startReplaceGroup(272326989);
                        finiteAnimationSpecValue = MotionSchemeKt.value(MotionSchemeKeyTokens.FastEffects, composer2, 6);
                        composer2.endReplaceGroup();
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2.endReplaceGroup();
                    return finiteAnimationSpecValue;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ FiniteAnimationSpec<Color> invoke(Transition.Segment<Boolean> segment, Composer composer2, Integer num) {
                    return invoke(segment, composer2, num.intValue());
                }
            };
            boolean zBooleanValue = ((Boolean) transitionUpdateTransition.getTargetState()).booleanValue();
            composerStartRestartGroup.startReplaceGroup(-1069234984);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1069234984, 0, -1, "androidx.compose.material3.TabTransition.<anonymous> (Tab.kt:289)");
            }
            long j3 = zBooleanValue ? j : j2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            ColorSpace colorSpaceM3138getColorSpaceimpl = Color.m3138getColorSpaceimpl(j3);
            boolean zChanged = composerStartRestartGroup.changed(colorSpaceM3138getColorSpaceimpl);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = (TwoWayConverter) ColorVectorConverterKt.getVectorConverter(Color.INSTANCE).invoke(colorSpaceM3138getColorSpaceimpl);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            TwoWayConverter twoWayConverter = (TwoWayConverter) objRememberedValue;
            boolean zBooleanValue2 = ((Boolean) transitionUpdateTransition.getCurrentState()).booleanValue();
            composerStartRestartGroup.startReplaceGroup(-1069234984);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1069234984, 0, -1, "androidx.compose.material3.TabTransition.<anonymous> (Tab.kt:289)");
            }
            long j4 = zBooleanValue2 ? j : j2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            Color colorM3124boximpl = Color.m3124boximpl(j4);
            boolean zBooleanValue3 = ((Boolean) transitionUpdateTransition.getTargetState()).booleanValue();
            composerStartRestartGroup.startReplaceGroup(-1069234984);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1069234984, 0, -1, "androidx.compose.material3.TabTransition.<anonymous> (Tab.kt:289)");
            }
            long j5 = zBooleanValue3 ? j : j2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composerStartRestartGroup.endReplaceGroup();
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(TabTransition_Klgx_Pg$lambda$5(TransitionKt.createTransitionAnimation(transitionUpdateTransition, colorM3124boximpl, Color.m3124boximpl(j5), tabKt$TabTransition$color$2.invoke(transitionUpdateTransition.getSegment(), composerStartRestartGroup, 0), twoWayConverter, "ColorAnimation", composerStartRestartGroup, 0)))), function2, composerStartRestartGroup, ProvidedValue.$stable | (i3 & 112));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: o1e
                public final Object invoke(Object obj, Object obj2) {
                    return TabKt.d(j, j2, z2, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final long TabTransition_Klgx_Pg$lambda$5(State<Color> state) {
        return state.getValue().m3144unboximpl();
    }

    public static Unit a(boolean z, Function0 function0, Modifier modifier, boolean z2, Function2 function2, Function2 function3, long j, long j2, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        m988TabwqdebIU(z, function0, modifier, z2, function2, function3, j, j2, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit b(Function2 function2, Function2 function3, int i, Composer composer, int i2) {
        TabBaselineLayout(function2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit c(boolean z, Function0 function0, Function2 function2, Function2 function3, Modifier modifier, boolean z2, long j, long j2, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        m986LeadingIconTabwqdebIU(z, function0, function2, function3, modifier, z2, j, j2, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit d(long j, long j2, boolean z, Function2 function2, int i, Composer composer, int i2) {
        m989TabTransitionKlgxPg(j, j2, z, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit e(boolean z, Function0 function0, Modifier modifier, boolean z2, long j, long j2, MutableInteractionSource mutableInteractionSource, Function3 function3, int i, int i2, Composer composer, int i3) {
        m987TabbogVsAg(z, function0, modifier, z2, j, j2, mutableInteractionSource, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final float getHorizontalTextPadding() {
        return HorizontalTextPadding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeTextAndIcon(Placeable.PlacementScope placementScope, Density density, Placeable placeable, Placeable placeable2, int i, int i2, int i3, int i4) {
        int iMo4551roundToPx0680j_4 = density.mo4551roundToPx0680j_4(i3 == i4 ? SingleLineTextBaselineWithIcon : DoubleLineTextBaselineWithIcon) + density.mo4551roundToPx0680j_4(PrimaryNavigationTabTokens.INSTANCE.m2078getActiveIndicatorHeightD9Ej5fM());
        int height = (placeable2.getHeight() + density.mo4550roundToPxR2X_6o(IconDistanceFromBaseline)) - i3;
        int i5 = (i2 - i4) - iMo4551roundToPx0680j_4;
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, (i - placeable.getWidth()) / 2, i5, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, (i - placeable2.getWidth()) / 2, i5 - height, 0.0f, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeTextOrIcon(Placeable.PlacementScope placementScope, Placeable placeable, int i) {
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, (i - placeable.getHeight()) / 2, 0.0f, 4, null);
    }
}
