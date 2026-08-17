package androidx.compose.material3;

import androidx.compose.animation.core.SnapSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.Indication;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.ToggleableKt;
import androidx.compose.material3.SwitchKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.material3.tokens.SwitchTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\u001al\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0015\b\u0002\u0010\b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t¢\u0006\u0002\b\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0007¢\u0006\u0002\u0010\u0010\u001aR\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0013\u0010\b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t¢\u0006\u0002\b\n2\u0006\u0010\u000e\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0003¢\u0006\u0002\u0010\u0015\"\u0016\u0010\u0016\u001a\u00020\u0017X\u0080\u0004¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u0018\u0010\u0019\"\u0016\u0010\u001b\u001a\u00020\u0017X\u0080\u0004¢\u0006\n\n\u0002\u0010\u001a\u001a\u0004\b\u001c\u0010\u0019\"\u0010\u0010\u001d\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001a\"\u0010\u0010\u001e\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001a\"\u0010\u0010\u001f\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001a\"\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Switch", "", "checked", "", "onCheckedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "thumbContent", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "enabled", "colors", "Landroidx/compose/material3/SwitchColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/SwitchColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "SwitchImpl", "Landroidx/compose/foundation/interaction/InteractionSource;", "thumbShape", "Landroidx/compose/ui/graphics/Shape;", "(Landroidx/compose/ui/Modifier;ZZLandroidx/compose/material3/SwitchColors;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;I)V", "ThumbDiameter", "Landroidx/compose/ui/unit/Dp;", "getThumbDiameter", "()F", "F", "UncheckedThumbDiameter", "getUncheckedThumbDiameter", "SwitchWidth", "SwitchHeight", "ThumbPadding", "SnapSpec", "Landroidx/compose/animation/core/SnapSpec;", "", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SwitchKt {
    private static final SnapSpec<Float> SnapSpec;
    private static final float SwitchHeight;
    private static final float SwitchWidth;
    private static final float ThumbDiameter;
    private static final float ThumbPadding;
    private static final float UncheckedThumbDiameter;

    static {
        SwitchTokens switchTokens = SwitchTokens.INSTANCE;
        float fM2179getSelectedHandleWidthD9Ej5fM = switchTokens.m2179getSelectedHandleWidthD9Ej5fM();
        ThumbDiameter = fM2179getSelectedHandleWidthD9Ej5fM;
        UncheckedThumbDiameter = switchTokens.m2186getUnselectedHandleWidthD9Ej5fM();
        SwitchWidth = switchTokens.m2184getTrackWidthD9Ej5fM();
        float fM2182getTrackHeightD9Ej5fM = switchTokens.m2182getTrackHeightD9Ej5fM();
        SwitchHeight = fM2182getTrackHeightD9Ej5fM;
        ThumbPadding = Dp.m6022constructorimpl(Dp.m6022constructorimpl(fM2182getTrackHeightD9Ej5fM - fM2179getSelectedHandleWidthD9Ej5fM) / 2.0f);
        SnapSpec = new SnapSpec<>(0, 1, (DefaultConstructorMarker) null);
    }

    /* JADX WARN: Code duplicated, block: B:102:0x011a  */
    /* JADX WARN: Code duplicated, block: B:104:0x0125  */
    /* JADX WARN: Code duplicated, block: B:106:0x012d  */
    /* JADX WARN: Code duplicated, block: B:109:0x013c  */
    /* JADX WARN: Code duplicated, block: B:111:0x0147  */
    /* JADX WARN: Code duplicated, block: B:113:0x0159  */
    /* JADX WARN: Code duplicated, block: B:115:0x0167  */
    /* JADX WARN: Code duplicated, block: B:117:0x0174  */
    /* JADX WARN: Code duplicated, block: B:118:0x0191  */
    /* JADX WARN: Code duplicated, block: B:121:0x01da  */
    /* JADX WARN: Code duplicated, block: B:123:0x01e4  */
    /* JADX WARN: Code duplicated, block: B:126:0x01f4  */
    /* JADX WARN: Code duplicated, block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0061  */
    /* JADX WARN: Code duplicated, block: B:38:0x0066  */
    /* JADX WARN: Code duplicated, block: B:40:0x006a  */
    /* JADX WARN: Code duplicated, block: B:42:0x0072  */
    /* JADX WARN: Code duplicated, block: B:43:0x0075  */
    /* JADX WARN: Code duplicated, block: B:47:0x007c  */
    /* JADX WARN: Code duplicated, block: B:49:0x0081  */
    /* JADX WARN: Code duplicated, block: B:51:0x0085  */
    /* JADX WARN: Code duplicated, block: B:53:0x008d  */
    /* JADX WARN: Code duplicated, block: B:54:0x0090  */
    /* JADX WARN: Code duplicated, block: B:58:0x0098  */
    /* JADX WARN: Code duplicated, block: B:60:0x009c  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:66:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:71:0x00b9  */
    /* JADX WARN: Code duplicated, block: B:73:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:76:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:80:0x00da  */
    /* JADX WARN: Code duplicated, block: B:81:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:86:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:93:0x0109 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:94:0x010b  */
    /* JADX WARN: Code duplicated, block: B:95:0x010e  */
    /* JADX WARN: Code duplicated, block: B:97:0x0111  */
    /* JADX WARN: Code duplicated, block: B:99:0x0114  */
    public static final void Switch(final boolean z, final Function1<? super Boolean, Unit> function1, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, boolean z2, SwitchColors switchColors, MutableInteractionSource mutableInteractionSource, Composer composer, final int i, final int i2) {
        boolean z3;
        int i3;
        Modifier modifier2;
        int i4;
        Function2<? super Composer, ? super Integer, Unit> function3;
        int i5;
        int i6;
        boolean z4;
        int i7;
        SwitchColors switchColorsColors;
        int i8;
        MutableInteractionSource mutableInteractionSource2;
        int i9;
        boolean z5;
        Composer composer2;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function4;
        final boolean z6;
        final SwitchColors switchColors2;
        final MutableInteractionSource mutableInteractionSource3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        MutableInteractionSource mutableInteractionSource4;
        Function2<? super Composer, ? super Integer, Unit> function5;
        Modifier modifier5;
        MutableInteractionSource mutableInteractionSource5;
        boolean z7;
        boolean z8;
        Modifier modifier6;
        Object objRememberedValue;
        Composer composerStartRestartGroup = composer.startRestartGroup(-263339167);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            z3 = z;
        } else if ((i & 6) == 0) {
            z3 = z;
            i3 = (composerStartRestartGroup.changed(z3) ? 4 : 2) | i;
        } else {
            z3 = z;
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        int i10 = i2 & 4;
        if (i10 == 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    function3 = function2;
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 16;
                if (i6 != 0) {
                    if ((i & 24576) == 0) {
                        z4 = z2;
                        if (composerStartRestartGroup.changed(z4)) {
                            i7 = 16384;
                        } else {
                            i7 = 8192;
                        }
                        i3 |= i7;
                    }
                    if ((196608 & i) == 0) {
                        if ((i2 & 32) == 0) {
                            switchColorsColors = switchColors;
                            int i11 = composerStartRestartGroup.changed(switchColorsColors) ? 131072 : 65536;
                            i3 |= i11;
                        } else {
                            switchColorsColors = switchColors;
                        }
                        i3 |= i11;
                    } else {
                        switchColorsColors = switchColors;
                    }
                    i8 = i2 & 64;
                    if (i8 != 0) {
                        if ((1572864 & i) == 0) {
                            mutableInteractionSource2 = mutableInteractionSource;
                            if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                                i9 = 1048576;
                            } else {
                                i9 = 524288;
                            }
                            i3 |= i9;
                        }
                        if ((i3 & 599187) != 599186) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i10 != 0) {
                                    modifier4 = Modifier.INSTANCE;
                                } else {
                                    modifier4 = modifier2;
                                }
                                if (i4 != 0) {
                                    function3 = null;
                                }
                                if (i6 != 0) {
                                    z4 = true;
                                }
                                if ((i2 & 32) != 0) {
                                    i3 &= -458753;
                                    switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                                }
                                if (i8 != 0) {
                                    mutableInteractionSource4 = null;
                                } else {
                                    mutableInteractionSource4 = mutableInteractionSource2;
                                }
                                function5 = function3;
                                modifier5 = modifier4;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i2 & 32) != 0) {
                                    i3 &= -458753;
                                }
                                i3 = i3;
                                z4 = z4;
                                switchColorsColors = switchColorsColors;
                                mutableInteractionSource4 = mutableInteractionSource2;
                                function5 = function3;
                                modifier5 = modifier2;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-263339167, i3, -1, "androidx.compose.material3.Switch (Switch.kt:98)");
                            }
                            if (mutableInteractionSource4 == null) {
                                composerStartRestartGroup.startReplaceGroup(1768604058);
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue;
                            } else {
                                composerStartRestartGroup.startReplaceGroup(334145757);
                                composerStartRestartGroup.endReplaceGroup();
                                mutableInteractionSource5 = mutableInteractionSource4;
                            }
                            if (function1 != null) {
                                z7 = z4;
                                z8 = false;
                                modifier6 = ToggleableKt.toggleable-O2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource5, (Indication) null, z7, Role.m5238boximpl(Role.INSTANCE.m5251getSwitcho7Vup1c()), function1);
                            } else {
                                z7 = z4;
                                z8 = false;
                                modifier6 = Modifier.INSTANCE;
                            }
                            int i12 = i3 << 3;
                            int i13 = i3 >> 6;
                            composer2 = composerStartRestartGroup;
                            Modifier modifier7 = modifier5;
                            SwitchImpl(SizeKt.requiredSize-VpY3zN4(SizeKt.wrapContentSize$default(modifier5.then(modifier6), Alignment.INSTANCE.getCenter(), z8, 2, (Object) null), SwitchWidth, SwitchHeight), z, z7, switchColorsColors, function5, mutableInteractionSource5, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i12 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i12 & 57344));
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier7;
                            z6 = z7;
                            switchColors2 = switchColorsColors;
                            function4 = function5;
                            mutableInteractionSource3 = mutableInteractionSource4;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier3 = modifier2;
                            function4 = function3;
                            z6 = z4;
                            switchColors2 = switchColorsColors;
                            mutableInteractionSource3 = mutableInteractionSource2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fwd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SwitchKt.b(z, function1, modifier3, function4, z6, switchColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 1572864;
                    mutableInteractionSource2 = mutableInteractionSource;
                    if ((i3 & 599187) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i10 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource4 = null;
                            } else {
                                mutableInteractionSource4 = mutableInteractionSource2;
                            }
                            function5 = function3;
                            modifier5 = modifier4;
                        } else {
                            if (i10 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource4 = null;
                            } else {
                                mutableInteractionSource4 = mutableInteractionSource2;
                            }
                            function5 = function3;
                            modifier5 = modifier4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-263339167, i3, -1, "androidx.compose.material3.Switch (Switch.kt:98)");
                        }
                        if (mutableInteractionSource4 == null) {
                            composerStartRestartGroup.startReplaceGroup(1768604058);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(334145757);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource5 = mutableInteractionSource4;
                        }
                        if (function1 != null) {
                            z7 = z4;
                            z8 = false;
                            modifier6 = ToggleableKt.toggleable-O2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource5, (Indication) null, z7, Role.m5238boximpl(Role.INSTANCE.m5251getSwitcho7Vup1c()), function1);
                        } else {
                            z7 = z4;
                            z8 = false;
                            modifier6 = Modifier.INSTANCE;
                        }
                        int i14 = i3 << 3;
                        int i15 = i3 >> 6;
                        composer2 = composerStartRestartGroup;
                        Modifier modifier8 = modifier5;
                        SwitchImpl(SizeKt.requiredSize-VpY3zN4(SizeKt.wrapContentSize$default(modifier5.then(modifier6), Alignment.INSTANCE.getCenter(), z8, 2, (Object) null), SwitchWidth, SwitchHeight), z, z7, switchColorsColors, function5, mutableInteractionSource5, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i14 & 112) | (i15 & 896) | (i15 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i14 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier8;
                        z6 = z7;
                        switchColors2 = switchColorsColors;
                        function4 = function5;
                        mutableInteractionSource3 = mutableInteractionSource4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        function4 = function3;
                        z6 = z4;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fwd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwitchKt.b(z, function1, modifier3, function4, z6, switchColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                z4 = z2;
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        switchColorsColors = switchColors;
                        if (composerStartRestartGroup.changed(switchColorsColors)) {
                        }
                        i3 |= i11;
                    } else {
                        switchColorsColors = switchColors;
                    }
                    i3 |= i11;
                } else {
                    switchColorsColors = switchColors;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    if ((1572864 & i) == 0) {
                        mutableInteractionSource2 = mutableInteractionSource;
                        if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    if ((i3 & 599187) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i10 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource4 = null;
                            } else {
                                mutableInteractionSource4 = mutableInteractionSource2;
                            }
                            function5 = function3;
                            modifier5 = modifier4;
                        } else {
                            if (i10 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource4 = null;
                            } else {
                                mutableInteractionSource4 = mutableInteractionSource2;
                            }
                            function5 = function3;
                            modifier5 = modifier4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-263339167, i3, -1, "androidx.compose.material3.Switch (Switch.kt:98)");
                        }
                        if (mutableInteractionSource4 == null) {
                            composerStartRestartGroup.startReplaceGroup(1768604058);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(334145757);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource5 = mutableInteractionSource4;
                        }
                        if (function1 != null) {
                            z7 = z4;
                            z8 = false;
                            modifier6 = ToggleableKt.toggleable-O2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource5, (Indication) null, z7, Role.m5238boximpl(Role.INSTANCE.m5251getSwitcho7Vup1c()), function1);
                        } else {
                            z7 = z4;
                            z8 = false;
                            modifier6 = Modifier.INSTANCE;
                        }
                        int i16 = i3 << 3;
                        int i17 = i3 >> 6;
                        composer2 = composerStartRestartGroup;
                        Modifier modifier9 = modifier5;
                        SwitchImpl(SizeKt.requiredSize-VpY3zN4(SizeKt.wrapContentSize$default(modifier5.then(modifier6), Alignment.INSTANCE.getCenter(), z8, 2, (Object) null), SwitchWidth, SwitchHeight), z, z7, switchColorsColors, function5, mutableInteractionSource5, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i16 & 112) | (i17 & 896) | (i17 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i16 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier9;
                        z6 = z7;
                        switchColors2 = switchColorsColors;
                        function4 = function5;
                        mutableInteractionSource3 = mutableInteractionSource4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        function4 = function3;
                        z6 = z4;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fwd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwitchKt.b(z, function1, modifier3, function4, z6, switchColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                mutableInteractionSource2 = mutableInteractionSource;
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                        function5 = function3;
                        modifier5 = modifier4;
                    } else {
                        if (i10 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                        function5 = function3;
                        modifier5 = modifier4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-263339167, i3, -1, "androidx.compose.material3.Switch (Switch.kt:98)");
                    }
                    if (mutableInteractionSource4 == null) {
                        composerStartRestartGroup.startReplaceGroup(1768604058);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(334145757);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource5 = mutableInteractionSource4;
                    }
                    if (function1 != null) {
                        z7 = z4;
                        z8 = false;
                        modifier6 = ToggleableKt.toggleable-O2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource5, (Indication) null, z7, Role.m5238boximpl(Role.INSTANCE.m5251getSwitcho7Vup1c()), function1);
                    } else {
                        z7 = z4;
                        z8 = false;
                        modifier6 = Modifier.INSTANCE;
                    }
                    int i18 = i3 << 3;
                    int i19 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifier10 = modifier5;
                    SwitchImpl(SizeKt.requiredSize-VpY3zN4(SizeKt.wrapContentSize$default(modifier5.then(modifier6), Alignment.INSTANCE.getCenter(), z8, 2, (Object) null), SwitchWidth, SwitchHeight), z, z7, switchColorsColors, function5, mutableInteractionSource5, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i18 & 112) | (i19 & 896) | (i19 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i18 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier10;
                    z6 = z7;
                    switchColors2 = switchColorsColors;
                    function4 = function5;
                    mutableInteractionSource3 = mutableInteractionSource4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    function4 = function3;
                    z6 = z4;
                    switchColors2 = switchColorsColors;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fwd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwitchKt.b(z, function1, modifier3, function4, z6, switchColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            function3 = function2;
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        switchColorsColors = switchColors;
                        if (composerStartRestartGroup.changed(switchColorsColors)) {
                        }
                        i3 |= i11;
                    } else {
                        switchColorsColors = switchColors;
                    }
                    i3 |= i11;
                } else {
                    switchColorsColors = switchColors;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    if ((1572864 & i) == 0) {
                        mutableInteractionSource2 = mutableInteractionSource;
                        if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    if ((i3 & 599187) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i10 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource4 = null;
                            } else {
                                mutableInteractionSource4 = mutableInteractionSource2;
                            }
                            function5 = function3;
                            modifier5 = modifier4;
                        } else {
                            if (i10 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource4 = null;
                            } else {
                                mutableInteractionSource4 = mutableInteractionSource2;
                            }
                            function5 = function3;
                            modifier5 = modifier4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-263339167, i3, -1, "androidx.compose.material3.Switch (Switch.kt:98)");
                        }
                        if (mutableInteractionSource4 == null) {
                            composerStartRestartGroup.startReplaceGroup(1768604058);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(334145757);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource5 = mutableInteractionSource4;
                        }
                        if (function1 != null) {
                            z7 = z4;
                            z8 = false;
                            modifier6 = ToggleableKt.toggleable-O2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource5, (Indication) null, z7, Role.m5238boximpl(Role.INSTANCE.m5251getSwitcho7Vup1c()), function1);
                        } else {
                            z7 = z4;
                            z8 = false;
                            modifier6 = Modifier.INSTANCE;
                        }
                        int i110 = i3 << 3;
                        int i111 = i3 >> 6;
                        composer2 = composerStartRestartGroup;
                        Modifier modifier11 = modifier5;
                        SwitchImpl(SizeKt.requiredSize-VpY3zN4(SizeKt.wrapContentSize$default(modifier5.then(modifier6), Alignment.INSTANCE.getCenter(), z8, 2, (Object) null), SwitchWidth, SwitchHeight), z, z7, switchColorsColors, function5, mutableInteractionSource5, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i110 & 112) | (i111 & 896) | (i111 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i110 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier11;
                        z6 = z7;
                        switchColors2 = switchColorsColors;
                        function4 = function5;
                        mutableInteractionSource3 = mutableInteractionSource4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        function4 = function3;
                        z6 = z4;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fwd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwitchKt.b(z, function1, modifier3, function4, z6, switchColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                mutableInteractionSource2 = mutableInteractionSource;
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                        function5 = function3;
                        modifier5 = modifier4;
                    } else {
                        if (i10 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                        function5 = function3;
                        modifier5 = modifier4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-263339167, i3, -1, "androidx.compose.material3.Switch (Switch.kt:98)");
                    }
                    if (mutableInteractionSource4 == null) {
                        composerStartRestartGroup.startReplaceGroup(1768604058);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(334145757);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource5 = mutableInteractionSource4;
                    }
                    if (function1 != null) {
                        z7 = z4;
                        z8 = false;
                        modifier6 = ToggleableKt.toggleable-O2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource5, (Indication) null, z7, Role.m5238boximpl(Role.INSTANCE.m5251getSwitcho7Vup1c()), function1);
                    } else {
                        z7 = z4;
                        z8 = false;
                        modifier6 = Modifier.INSTANCE;
                    }
                    int i112 = i3 << 3;
                    int i113 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifier12 = modifier5;
                    SwitchImpl(SizeKt.requiredSize-VpY3zN4(SizeKt.wrapContentSize$default(modifier5.then(modifier6), Alignment.INSTANCE.getCenter(), z8, 2, (Object) null), SwitchWidth, SwitchHeight), z, z7, switchColorsColors, function5, mutableInteractionSource5, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i112 & 112) | (i113 & 896) | (i113 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i112 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier12;
                    z6 = z7;
                    switchColors2 = switchColorsColors;
                    function4 = function5;
                    mutableInteractionSource3 = mutableInteractionSource4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    function4 = function3;
                    z6 = z4;
                    switchColors2 = switchColorsColors;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fwd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwitchKt.b(z, function1, modifier3, function4, z6, switchColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            z4 = z2;
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    switchColorsColors = switchColors;
                    if (composerStartRestartGroup.changed(switchColorsColors)) {
                    }
                    i3 |= i11;
                } else {
                    switchColorsColors = switchColors;
                }
                i3 |= i11;
            } else {
                switchColorsColors = switchColors;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                if ((1572864 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                        function5 = function3;
                        modifier5 = modifier4;
                    } else {
                        if (i10 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                        function5 = function3;
                        modifier5 = modifier4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-263339167, i3, -1, "androidx.compose.material3.Switch (Switch.kt:98)");
                    }
                    if (mutableInteractionSource4 == null) {
                        composerStartRestartGroup.startReplaceGroup(1768604058);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(334145757);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource5 = mutableInteractionSource4;
                    }
                    if (function1 != null) {
                        z7 = z4;
                        z8 = false;
                        modifier6 = ToggleableKt.toggleable-O2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource5, (Indication) null, z7, Role.m5238boximpl(Role.INSTANCE.m5251getSwitcho7Vup1c()), function1);
                    } else {
                        z7 = z4;
                        z8 = false;
                        modifier6 = Modifier.INSTANCE;
                    }
                    int i114 = i3 << 3;
                    int i115 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifier13 = modifier5;
                    SwitchImpl(SizeKt.requiredSize-VpY3zN4(SizeKt.wrapContentSize$default(modifier5.then(modifier6), Alignment.INSTANCE.getCenter(), z8, 2, (Object) null), SwitchWidth, SwitchHeight), z, z7, switchColorsColors, function5, mutableInteractionSource5, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i114 & 112) | (i115 & 896) | (i115 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i114 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier13;
                    z6 = z7;
                    switchColors2 = switchColorsColors;
                    function4 = function5;
                    mutableInteractionSource3 = mutableInteractionSource4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    function4 = function3;
                    z6 = z4;
                    switchColors2 = switchColorsColors;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fwd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwitchKt.b(z, function1, modifier3, function4, z6, switchColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((i3 & 599187) != 599186) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource4 = null;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                    function5 = function3;
                    modifier5 = modifier4;
                } else {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource4 = null;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                    function5 = function3;
                    modifier5 = modifier4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-263339167, i3, -1, "androidx.compose.material3.Switch (Switch.kt:98)");
                }
                if (mutableInteractionSource4 == null) {
                    composerStartRestartGroup.startReplaceGroup(1768604058);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue;
                } else {
                    composerStartRestartGroup.startReplaceGroup(334145757);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource5 = mutableInteractionSource4;
                }
                if (function1 != null) {
                    z7 = z4;
                    z8 = false;
                    modifier6 = ToggleableKt.toggleable-O2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource5, (Indication) null, z7, Role.m5238boximpl(Role.INSTANCE.m5251getSwitcho7Vup1c()), function1);
                } else {
                    z7 = z4;
                    z8 = false;
                    modifier6 = Modifier.INSTANCE;
                }
                int i116 = i3 << 3;
                int i117 = i3 >> 6;
                composer2 = composerStartRestartGroup;
                Modifier modifier14 = modifier5;
                SwitchImpl(SizeKt.requiredSize-VpY3zN4(SizeKt.wrapContentSize$default(modifier5.then(modifier6), Alignment.INSTANCE.getCenter(), z8, 2, (Object) null), SwitchWidth, SwitchHeight), z, z7, switchColorsColors, function5, mutableInteractionSource5, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i116 & 112) | (i117 & 896) | (i117 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i116 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier14;
                z6 = z7;
                switchColors2 = switchColorsColors;
                function4 = function5;
                mutableInteractionSource3 = mutableInteractionSource4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                function4 = function3;
                z6 = z4;
                switchColors2 = switchColorsColors;
                mutableInteractionSource3 = mutableInteractionSource2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fwd
                    public final Object invoke(Object obj, Object obj2) {
                        return SwitchKt.b(z, function1, modifier3, function4, z6, switchColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        modifier2 = modifier;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                function3 = function2;
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    z4 = z2;
                    if (composerStartRestartGroup.changed(z4)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                if ((196608 & i) == 0) {
                    if ((i2 & 32) == 0) {
                        switchColorsColors = switchColors;
                        if (composerStartRestartGroup.changed(switchColorsColors)) {
                        }
                        i3 |= i11;
                    } else {
                        switchColorsColors = switchColors;
                    }
                    i3 |= i11;
                } else {
                    switchColorsColors = switchColors;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    if ((1572864 & i) == 0) {
                        mutableInteractionSource2 = mutableInteractionSource;
                        if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                            i9 = 1048576;
                        } else {
                            i9 = 524288;
                        }
                        i3 |= i9;
                    }
                    if ((i3 & 599187) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i10 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource4 = null;
                            } else {
                                mutableInteractionSource4 = mutableInteractionSource2;
                            }
                            function5 = function3;
                            modifier5 = modifier4;
                        } else {
                            if (i10 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i4 != 0) {
                                function3 = null;
                            }
                            if (i6 != 0) {
                                z4 = true;
                            }
                            if ((i2 & 32) != 0) {
                                i3 &= -458753;
                                switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                            }
                            if (i8 != 0) {
                                mutableInteractionSource4 = null;
                            } else {
                                mutableInteractionSource4 = mutableInteractionSource2;
                            }
                            function5 = function3;
                            modifier5 = modifier4;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-263339167, i3, -1, "androidx.compose.material3.Switch (Switch.kt:98)");
                        }
                        if (mutableInteractionSource4 == null) {
                            composerStartRestartGroup.startReplaceGroup(1768604058);
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue;
                        } else {
                            composerStartRestartGroup.startReplaceGroup(334145757);
                            composerStartRestartGroup.endReplaceGroup();
                            mutableInteractionSource5 = mutableInteractionSource4;
                        }
                        if (function1 != null) {
                            z7 = z4;
                            z8 = false;
                            modifier6 = ToggleableKt.toggleable-O2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource5, (Indication) null, z7, Role.m5238boximpl(Role.INSTANCE.m5251getSwitcho7Vup1c()), function1);
                        } else {
                            z7 = z4;
                            z8 = false;
                            modifier6 = Modifier.INSTANCE;
                        }
                        int i118 = i3 << 3;
                        int i119 = i3 >> 6;
                        composer2 = composerStartRestartGroup;
                        Modifier modifier15 = modifier5;
                        SwitchImpl(SizeKt.requiredSize-VpY3zN4(SizeKt.wrapContentSize$default(modifier5.then(modifier6), Alignment.INSTANCE.getCenter(), z8, 2, (Object) null), SwitchWidth, SwitchHeight), z, z7, switchColorsColors, function5, mutableInteractionSource5, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i118 & 112) | (i119 & 896) | (i119 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i118 & 57344));
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier15;
                        z6 = z7;
                        switchColors2 = switchColorsColors;
                        function4 = function5;
                        mutableInteractionSource3 = mutableInteractionSource4;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier3 = modifier2;
                        function4 = function3;
                        z6 = z4;
                        switchColors2 = switchColorsColors;
                        mutableInteractionSource3 = mutableInteractionSource2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fwd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwitchKt.b(z, function1, modifier3, function4, z6, switchColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                mutableInteractionSource2 = mutableInteractionSource;
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                        function5 = function3;
                        modifier5 = modifier4;
                    } else {
                        if (i10 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                        function5 = function3;
                        modifier5 = modifier4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-263339167, i3, -1, "androidx.compose.material3.Switch (Switch.kt:98)");
                    }
                    if (mutableInteractionSource4 == null) {
                        composerStartRestartGroup.startReplaceGroup(1768604058);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(334145757);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource5 = mutableInteractionSource4;
                    }
                    if (function1 != null) {
                        z7 = z4;
                        z8 = false;
                        modifier6 = ToggleableKt.toggleable-O2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource5, (Indication) null, z7, Role.m5238boximpl(Role.INSTANCE.m5251getSwitcho7Vup1c()), function1);
                    } else {
                        z7 = z4;
                        z8 = false;
                        modifier6 = Modifier.INSTANCE;
                    }
                    int i1110 = i3 << 3;
                    int i1111 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifier16 = modifier5;
                    SwitchImpl(SizeKt.requiredSize-VpY3zN4(SizeKt.wrapContentSize$default(modifier5.then(modifier6), Alignment.INSTANCE.getCenter(), z8, 2, (Object) null), SwitchWidth, SwitchHeight), z, z7, switchColorsColors, function5, mutableInteractionSource5, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1110 & 112) | (i1111 & 896) | (i1111 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i1110 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier16;
                    z6 = z7;
                    switchColors2 = switchColorsColors;
                    function4 = function5;
                    mutableInteractionSource3 = mutableInteractionSource4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    function4 = function3;
                    z6 = z4;
                    switchColors2 = switchColorsColors;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fwd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwitchKt.b(z, function1, modifier3, function4, z6, switchColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            z4 = z2;
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    switchColorsColors = switchColors;
                    if (composerStartRestartGroup.changed(switchColorsColors)) {
                    }
                    i3 |= i11;
                } else {
                    switchColorsColors = switchColors;
                }
                i3 |= i11;
            } else {
                switchColorsColors = switchColors;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                if ((1572864 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                        function5 = function3;
                        modifier5 = modifier4;
                    } else {
                        if (i10 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                        function5 = function3;
                        modifier5 = modifier4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-263339167, i3, -1, "androidx.compose.material3.Switch (Switch.kt:98)");
                    }
                    if (mutableInteractionSource4 == null) {
                        composerStartRestartGroup.startReplaceGroup(1768604058);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(334145757);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource5 = mutableInteractionSource4;
                    }
                    if (function1 != null) {
                        z7 = z4;
                        z8 = false;
                        modifier6 = ToggleableKt.toggleable-O2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource5, (Indication) null, z7, Role.m5238boximpl(Role.INSTANCE.m5251getSwitcho7Vup1c()), function1);
                    } else {
                        z7 = z4;
                        z8 = false;
                        modifier6 = Modifier.INSTANCE;
                    }
                    int i1112 = i3 << 3;
                    int i1113 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifier17 = modifier5;
                    SwitchImpl(SizeKt.requiredSize-VpY3zN4(SizeKt.wrapContentSize$default(modifier5.then(modifier6), Alignment.INSTANCE.getCenter(), z8, 2, (Object) null), SwitchWidth, SwitchHeight), z, z7, switchColorsColors, function5, mutableInteractionSource5, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1112 & 112) | (i1113 & 896) | (i1113 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i1112 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier17;
                    z6 = z7;
                    switchColors2 = switchColorsColors;
                    function4 = function5;
                    mutableInteractionSource3 = mutableInteractionSource4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    function4 = function3;
                    z6 = z4;
                    switchColors2 = switchColorsColors;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fwd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwitchKt.b(z, function1, modifier3, function4, z6, switchColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((i3 & 599187) != 599186) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource4 = null;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                    function5 = function3;
                    modifier5 = modifier4;
                } else {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource4 = null;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                    function5 = function3;
                    modifier5 = modifier4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-263339167, i3, -1, "androidx.compose.material3.Switch (Switch.kt:98)");
                }
                if (mutableInteractionSource4 == null) {
                    composerStartRestartGroup.startReplaceGroup(1768604058);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue;
                } else {
                    composerStartRestartGroup.startReplaceGroup(334145757);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource5 = mutableInteractionSource4;
                }
                if (function1 != null) {
                    z7 = z4;
                    z8 = false;
                    modifier6 = ToggleableKt.toggleable-O2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource5, (Indication) null, z7, Role.m5238boximpl(Role.INSTANCE.m5251getSwitcho7Vup1c()), function1);
                } else {
                    z7 = z4;
                    z8 = false;
                    modifier6 = Modifier.INSTANCE;
                }
                int i1114 = i3 << 3;
                int i1115 = i3 >> 6;
                composer2 = composerStartRestartGroup;
                Modifier modifier18 = modifier5;
                SwitchImpl(SizeKt.requiredSize-VpY3zN4(SizeKt.wrapContentSize$default(modifier5.then(modifier6), Alignment.INSTANCE.getCenter(), z8, 2, (Object) null), SwitchWidth, SwitchHeight), z, z7, switchColorsColors, function5, mutableInteractionSource5, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1114 & 112) | (i1115 & 896) | (i1115 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i1114 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier18;
                z6 = z7;
                switchColors2 = switchColorsColors;
                function4 = function5;
                mutableInteractionSource3 = mutableInteractionSource4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                function4 = function3;
                z6 = z4;
                switchColors2 = switchColorsColors;
                mutableInteractionSource3 = mutableInteractionSource2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fwd
                    public final Object invoke(Object obj, Object obj2) {
                        return SwitchKt.b(z, function1, modifier3, function4, z6, switchColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        function3 = function2;
        i6 = i2 & 16;
        if (i6 != 0) {
            if ((i & 24576) == 0) {
                z4 = z2;
                if (composerStartRestartGroup.changed(z4)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i3 |= i7;
            }
            if ((196608 & i) == 0) {
                if ((i2 & 32) == 0) {
                    switchColorsColors = switchColors;
                    if (composerStartRestartGroup.changed(switchColorsColors)) {
                    }
                    i3 |= i11;
                } else {
                    switchColorsColors = switchColors;
                }
                i3 |= i11;
            } else {
                switchColorsColors = switchColors;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                if ((1572864 & i) == 0) {
                    mutableInteractionSource2 = mutableInteractionSource;
                    if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                        i9 = 1048576;
                    } else {
                        i9 = 524288;
                    }
                    i3 |= i9;
                }
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i10 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                        function5 = function3;
                        modifier5 = modifier4;
                    } else {
                        if (i10 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            function3 = null;
                        }
                        if (i6 != 0) {
                            z4 = true;
                        }
                        if ((i2 & 32) != 0) {
                            i3 &= -458753;
                            switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                        }
                        if (i8 != 0) {
                            mutableInteractionSource4 = null;
                        } else {
                            mutableInteractionSource4 = mutableInteractionSource2;
                        }
                        function5 = function3;
                        modifier5 = modifier4;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-263339167, i3, -1, "androidx.compose.material3.Switch (Switch.kt:98)");
                    }
                    if (mutableInteractionSource4 == null) {
                        composerStartRestartGroup.startReplaceGroup(1768604058);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue;
                    } else {
                        composerStartRestartGroup.startReplaceGroup(334145757);
                        composerStartRestartGroup.endReplaceGroup();
                        mutableInteractionSource5 = mutableInteractionSource4;
                    }
                    if (function1 != null) {
                        z7 = z4;
                        z8 = false;
                        modifier6 = ToggleableKt.toggleable-O2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource5, (Indication) null, z7, Role.m5238boximpl(Role.INSTANCE.m5251getSwitcho7Vup1c()), function1);
                    } else {
                        z7 = z4;
                        z8 = false;
                        modifier6 = Modifier.INSTANCE;
                    }
                    int i1116 = i3 << 3;
                    int i1117 = i3 >> 6;
                    composer2 = composerStartRestartGroup;
                    Modifier modifier19 = modifier5;
                    SwitchImpl(SizeKt.requiredSize-VpY3zN4(SizeKt.wrapContentSize$default(modifier5.then(modifier6), Alignment.INSTANCE.getCenter(), z8, 2, (Object) null), SwitchWidth, SwitchHeight), z, z7, switchColorsColors, function5, mutableInteractionSource5, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1116 & 112) | (i1117 & 896) | (i1117 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i1116 & 57344));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier19;
                    z6 = z7;
                    switchColors2 = switchColorsColors;
                    function4 = function5;
                    mutableInteractionSource3 = mutableInteractionSource4;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier3 = modifier2;
                    function4 = function3;
                    z6 = z4;
                    switchColors2 = switchColorsColors;
                    mutableInteractionSource3 = mutableInteractionSource2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fwd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwitchKt.b(z, function1, modifier3, function4, z6, switchColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            mutableInteractionSource2 = mutableInteractionSource;
            if ((i3 & 599187) != 599186) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource4 = null;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                    function5 = function3;
                    modifier5 = modifier4;
                } else {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource4 = null;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                    function5 = function3;
                    modifier5 = modifier4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-263339167, i3, -1, "androidx.compose.material3.Switch (Switch.kt:98)");
                }
                if (mutableInteractionSource4 == null) {
                    composerStartRestartGroup.startReplaceGroup(1768604058);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue;
                } else {
                    composerStartRestartGroup.startReplaceGroup(334145757);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource5 = mutableInteractionSource4;
                }
                if (function1 != null) {
                    z7 = z4;
                    z8 = false;
                    modifier6 = ToggleableKt.toggleable-O2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource5, (Indication) null, z7, Role.m5238boximpl(Role.INSTANCE.m5251getSwitcho7Vup1c()), function1);
                } else {
                    z7 = z4;
                    z8 = false;
                    modifier6 = Modifier.INSTANCE;
                }
                int i1118 = i3 << 3;
                int i1119 = i3 >> 6;
                composer2 = composerStartRestartGroup;
                Modifier modifier110 = modifier5;
                SwitchImpl(SizeKt.requiredSize-VpY3zN4(SizeKt.wrapContentSize$default(modifier5.then(modifier6), Alignment.INSTANCE.getCenter(), z8, 2, (Object) null), SwitchWidth, SwitchHeight), z, z7, switchColorsColors, function5, mutableInteractionSource5, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i1118 & 112) | (i1119 & 896) | (i1119 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i1118 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier110;
                z6 = z7;
                switchColors2 = switchColorsColors;
                function4 = function5;
                mutableInteractionSource3 = mutableInteractionSource4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                function4 = function3;
                z6 = z4;
                switchColors2 = switchColorsColors;
                mutableInteractionSource3 = mutableInteractionSource2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fwd
                    public final Object invoke(Object obj, Object obj2) {
                        return SwitchKt.b(z, function1, modifier3, function4, z6, switchColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        z4 = z2;
        if ((196608 & i) == 0) {
            if ((i2 & 32) == 0) {
                switchColorsColors = switchColors;
                if (composerStartRestartGroup.changed(switchColorsColors)) {
                }
                i3 |= i11;
            } else {
                switchColorsColors = switchColors;
            }
            i3 |= i11;
        } else {
            switchColorsColors = switchColors;
        }
        i8 = i2 & 64;
        if (i8 != 0) {
            if ((1572864 & i) == 0) {
                mutableInteractionSource2 = mutableInteractionSource;
                if (composerStartRestartGroup.changed(mutableInteractionSource2)) {
                    i9 = 1048576;
                } else {
                    i9 = 524288;
                }
                i3 |= i9;
            }
            if ((i3 & 599187) != 599186) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource4 = null;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                    function5 = function3;
                    modifier5 = modifier4;
                } else {
                    if (i10 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        function3 = null;
                    }
                    if (i6 != 0) {
                        z4 = true;
                    }
                    if ((i2 & 32) != 0) {
                        i3 &= -458753;
                        switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                    }
                    if (i8 != 0) {
                        mutableInteractionSource4 = null;
                    } else {
                        mutableInteractionSource4 = mutableInteractionSource2;
                    }
                    function5 = function3;
                    modifier5 = modifier4;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-263339167, i3, -1, "androidx.compose.material3.Switch (Switch.kt:98)");
                }
                if (mutableInteractionSource4 == null) {
                    composerStartRestartGroup.startReplaceGroup(1768604058);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue;
                } else {
                    composerStartRestartGroup.startReplaceGroup(334145757);
                    composerStartRestartGroup.endReplaceGroup();
                    mutableInteractionSource5 = mutableInteractionSource4;
                }
                if (function1 != null) {
                    z7 = z4;
                    z8 = false;
                    modifier6 = ToggleableKt.toggleable-O2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource5, (Indication) null, z7, Role.m5238boximpl(Role.INSTANCE.m5251getSwitcho7Vup1c()), function1);
                } else {
                    z7 = z4;
                    z8 = false;
                    modifier6 = Modifier.INSTANCE;
                }
                int i11110 = i3 << 3;
                int i11111 = i3 >> 6;
                composer2 = composerStartRestartGroup;
                Modifier modifier111 = modifier5;
                SwitchImpl(SizeKt.requiredSize-VpY3zN4(SizeKt.wrapContentSize$default(modifier5.then(modifier6), Alignment.INSTANCE.getCenter(), z8, 2, (Object) null), SwitchWidth, SwitchHeight), z, z7, switchColorsColors, function5, mutableInteractionSource5, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i11110 & 112) | (i11111 & 896) | (i11111 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i11110 & 57344));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier111;
                z6 = z7;
                switchColors2 = switchColorsColors;
                function4 = function5;
                mutableInteractionSource3 = mutableInteractionSource4;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
                function4 = function3;
                z6 = z4;
                switchColors2 = switchColorsColors;
                mutableInteractionSource3 = mutableInteractionSource2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fwd
                    public final Object invoke(Object obj, Object obj2) {
                        return SwitchKt.b(z, function1, modifier3, function4, z6, switchColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 1572864;
        mutableInteractionSource2 = mutableInteractionSource;
        if ((i3 & 599187) != 599186) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i10 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    function3 = null;
                }
                if (i6 != 0) {
                    z4 = true;
                }
                if ((i2 & 32) != 0) {
                    i3 &= -458753;
                    switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i8 != 0) {
                    mutableInteractionSource4 = null;
                } else {
                    mutableInteractionSource4 = mutableInteractionSource2;
                }
                function5 = function3;
                modifier5 = modifier4;
            } else {
                if (i10 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    function3 = null;
                }
                if (i6 != 0) {
                    z4 = true;
                }
                if ((i2 & 32) != 0) {
                    i3 &= -458753;
                    switchColorsColors = SwitchDefaults.INSTANCE.colors(composerStartRestartGroup, 6);
                }
                if (i8 != 0) {
                    mutableInteractionSource4 = null;
                } else {
                    mutableInteractionSource4 = mutableInteractionSource2;
                }
                function5 = function3;
                modifier5 = modifier4;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-263339167, i3, -1, "androidx.compose.material3.Switch (Switch.kt:98)");
            }
            if (mutableInteractionSource4 == null) {
                composerStartRestartGroup.startReplaceGroup(1768604058);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composerStartRestartGroup.endReplaceGroup();
                mutableInteractionSource5 = (MutableInteractionSource) objRememberedValue;
            } else {
                composerStartRestartGroup.startReplaceGroup(334145757);
                composerStartRestartGroup.endReplaceGroup();
                mutableInteractionSource5 = mutableInteractionSource4;
            }
            if (function1 != null) {
                z7 = z4;
                z8 = false;
                modifier6 = ToggleableKt.toggleable-O2vRcR0(InteractiveComponentSizeKt.minimumInteractiveComponentSize(Modifier.INSTANCE), z3, mutableInteractionSource5, (Indication) null, z7, Role.m5238boximpl(Role.INSTANCE.m5251getSwitcho7Vup1c()), function1);
            } else {
                z7 = z4;
                z8 = false;
                modifier6 = Modifier.INSTANCE;
            }
            int i11112 = i3 << 3;
            int i11113 = i3 >> 6;
            composer2 = composerStartRestartGroup;
            Modifier modifier112 = modifier5;
            SwitchImpl(SizeKt.requiredSize-VpY3zN4(SizeKt.wrapContentSize$default(modifier5.then(modifier6), Alignment.INSTANCE.getCenter(), z8, 2, (Object) null), SwitchWidth, SwitchHeight), z, z7, switchColorsColors, function5, mutableInteractionSource5, ShapesKt.getValue(SwitchTokens.INSTANCE.getHandleShape(), composerStartRestartGroup, 6), composer2, (i11112 & 112) | (i11113 & 896) | (i11113 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i11112 & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier112;
            z6 = z7;
            switchColors2 = switchColorsColors;
            function4 = function5;
            mutableInteractionSource3 = mutableInteractionSource4;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
            function4 = function3;
            z6 = z4;
            switchColors2 = switchColorsColors;
            mutableInteractionSource3 = mutableInteractionSource2;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fwd
                public final Object invoke(Object obj, Object obj2) {
                    return SwitchKt.b(z, function1, modifier3, function4, z6, switchColors2, mutableInteractionSource3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    private static final void SwitchImpl(final Modifier modifier, final boolean z, final boolean z2, final SwitchColors switchColors, final Function2<? super Composer, ? super Integer, Unit> function2, final InteractionSource interactionSource, Shape shape, Composer composer, final int i) {
        int i2;
        final Shape shape2 = shape;
        Composer composerStartRestartGroup = composer.startRestartGroup(-670917213);
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
            i2 |= composerStartRestartGroup.changed(switchColors) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(interactionSource) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(shape2) ? 1048576 : 524288;
        }
        if (composerStartRestartGroup.shouldExecute((599187 & i2) != 599186, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-670917213, i2, -1, "androidx.compose.material3.SwitchImpl (Switch.kt:143)");
            }
            long jM983trackColorWaAFU9c$material3 = switchColors.m983trackColorWaAFU9c$material3(z2, z);
            long jM982thumbColorWaAFU9c$material3 = switchColors.m982thumbColorWaAFU9c$material3(z2, z);
            SwitchTokens switchTokens = SwitchTokens.INSTANCE;
            Shape value = ShapesKt.getValue(switchTokens.getTrackShape(), composerStartRestartGroup, 6);
            Modifier modifier2 = BackgroundKt.background-bw27NRU(BorderKt.border-xT4_qwU(modifier, switchTokens.m2183getTrackOutlineWidthD9Ej5fM(), switchColors.m963borderColorWaAFU9c$material3(z2, z), value), jM983trackColorWaAFU9c$material3, value);
            Alignment.Companion companion = Alignment.INSTANCE;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(companion.getTopStart(), false);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
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
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
            Modifier modifierIndication = IndicationKt.indication(BoxScopeInstance.INSTANCE.align(Modifier.INSTANCE, companion.getCenterStart()).then(new ThumbElement(interactionSource, z, MotionSchemeKt.value(MotionSchemeKeyTokens.FastSpatial, composerStartRestartGroup, 6))), interactionSource, RippleKt.m782rippleH2RKhps$default(false, Dp.m6022constructorimpl(switchTokens.m2181getStateLayerSizeD9Ej5fM() / 2.0f), 0L, 4, null));
            shape2 = shape;
            Modifier modifier3 = BackgroundKt.background-bw27NRU(modifierIndication, jM982thumbColorWaAFU9c$material3, shape2);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(companion.getCenter(), false);
            int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
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
            Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyMaybeCachedBoxMeasurePolicy2, companion2.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion2.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = companion2.getSetCompositeKeyHash();
            if (composerM2388constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion2.getSetModifier());
            if (function2 != null) {
                composerStartRestartGroup.startReplaceGroup(1235836927);
                CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m3124boximpl(switchColors.m981iconColorWaAFU9c$material3(z2, z))), function2, composerStartRestartGroup, ProvidedValue.$stable | ((i2 >> 9) & 112));
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(1236071411);
                composerStartRestartGroup.endReplaceGroup();
            }
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gwd
                public final Object invoke(Object obj, Object obj2) {
                    return SwitchKt.a(modifier, z, z2, switchColors, function2, interactionSource, shape2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(Modifier modifier, boolean z, boolean z2, SwitchColors switchColors, Function2 function2, InteractionSource interactionSource, Shape shape, int i, Composer composer, int i2) {
        SwitchImpl(modifier, z, z2, switchColors, function2, interactionSource, shape, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit b(boolean z, Function1 function1, Modifier modifier, Function2 function2, boolean z2, SwitchColors switchColors, MutableInteractionSource mutableInteractionSource, int i, int i2, Composer composer, int i3) {
        Switch(z, function1, modifier, function2, z2, switchColors, mutableInteractionSource, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final float getThumbDiameter() {
        return ThumbDiameter;
    }

    public static final float getUncheckedThumbDiameter() {
        return UncheckedThumbDiameter;
    }
}
