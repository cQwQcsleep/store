package androidx.compose.material3;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.foundation.layout.WindowInsetsPaddingKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.internal.MutableWindowInsets;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a®\u0001\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0013\b\u0002\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0013\b\u0002\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0013\b\u0002\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0013\b\u0002\u0010\t\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0084\u0001\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u000b2\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u00062\u0011\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0011\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0003¢\u0006\u0004\b\u001a\u0010\u001b\"\u0010\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001e¨\u0006\u001f"}, d2 = {"Scaffold", "", "modifier", "Landroidx/compose/ui/Modifier;", "topBar", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "bottomBar", "snackbarHost", "floatingActionButton", "floatingActionButtonPosition", "Landroidx/compose/material3/FabPosition;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "contentWindowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/PaddingValues;", "Scaffold-TvnljyQ", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;IJJLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ScaffoldLayout", "fabPosition", "snackbar", "fab", "ScaffoldLayout-FMILGgc", "(ILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "FabSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ScaffoldKt {
    private static final float FabSpacing = Dp.m6022constructorimpl(16.0f);

    /* JADX WARN: Code duplicated, block: B:100:0x0116  */
    /* JADX WARN: Code duplicated, block: B:102:0x011b  */
    /* JADX WARN: Code duplicated, block: B:104:0x011f  */
    /* JADX WARN: Code duplicated, block: B:106:0x0127  */
    /* JADX WARN: Code duplicated, block: B:107:0x012a  */
    /* JADX WARN: Code duplicated, block: B:111:0x0138  */
    /* JADX WARN: Code duplicated, block: B:112:0x013a  */
    /* JADX WARN: Code duplicated, block: B:115:0x0143  */
    /* JADX WARN: Code duplicated, block: B:117:0x0153  */
    /* JADX WARN: Code duplicated, block: B:130:0x0181 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:131:0x0183  */
    /* JADX WARN: Code duplicated, block: B:132:0x0186  */
    /* JADX WARN: Code duplicated, block: B:134:0x018a  */
    /* JADX WARN: Code duplicated, block: B:135:0x0191  */
    /* JADX WARN: Code duplicated, block: B:137:0x0194  */
    /* JADX WARN: Code duplicated, block: B:138:0x019b  */
    /* JADX WARN: Code duplicated, block: B:140:0x019e  */
    /* JADX WARN: Code duplicated, block: B:141:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:143:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:144:0x01af  */
    /* JADX WARN: Code duplicated, block: B:146:0x01b2  */
    /* JADX WARN: Code duplicated, block: B:147:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:150:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:151:0x01d0  */
    /* JADX WARN: Code duplicated, block: B:154:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:155:0x01e2  */
    /* JADX WARN: Code duplicated, block: B:158:0x01e8  */
    /* JADX WARN: Code duplicated, block: B:160:0x01f5  */
    /* JADX WARN: Code duplicated, block: B:163:0x0201  */
    /* JADX WARN: Code duplicated, block: B:164:0x020d  */
    /* JADX WARN: Code duplicated, block: B:167:0x0218  */
    /* JADX WARN: Code duplicated, block: B:169:0x021e  */
    /* JADX WARN: Code duplicated, block: B:175:0x022b  */
    /* JADX WARN: Code duplicated, block: B:177:0x0233  */
    /* JADX WARN: Code duplicated, block: B:180:0x0247  */
    /* JADX WARN: Code duplicated, block: B:182:0x024d  */
    /* JADX WARN: Code duplicated, block: B:188:0x025c  */
    /* JADX WARN: Code duplicated, block: B:190:0x0264  */
    /* JADX WARN: Code duplicated, block: B:193:0x02b3  */
    /* JADX WARN: Code duplicated, block: B:195:0x02c2  */
    /* JADX WARN: Code duplicated, block: B:198:0x02db  */
    /* JADX WARN: Code duplicated, block: B:200:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004a  */
    /* JADX WARN: Code duplicated, block: B:28:0x004f  */
    /* JADX WARN: Code duplicated, block: B:30:0x0053  */
    /* JADX WARN: Code duplicated, block: B:32:0x005b  */
    /* JADX WARN: Code duplicated, block: B:33:0x005e  */
    /* JADX WARN: Code duplicated, block: B:37:0x0065  */
    /* JADX WARN: Code duplicated, block: B:39:0x006a  */
    /* JADX WARN: Code duplicated, block: B:41:0x006e  */
    /* JADX WARN: Code duplicated, block: B:43:0x0076  */
    /* JADX WARN: Code duplicated, block: B:44:0x0079  */
    /* JADX WARN: Code duplicated, block: B:48:0x0080  */
    /* JADX WARN: Code duplicated, block: B:50:0x0085  */
    /* JADX WARN: Code duplicated, block: B:52:0x0089  */
    /* JADX WARN: Code duplicated, block: B:54:0x0091  */
    /* JADX WARN: Code duplicated, block: B:55:0x0094  */
    /* JADX WARN: Code duplicated, block: B:59:0x009d  */
    /* JADX WARN: Code duplicated, block: B:60:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:65:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:69:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:74:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:79:0x00db  */
    /* JADX WARN: Code duplicated, block: B:81:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:84:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:86:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:89:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:91:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:94:0x0107 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:97:0x010e  */
    /* JADX INFO: renamed from: Scaffold-TvnljyQ, reason: not valid java name */
    public static final void m785ScaffoldTvnljyQ(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, int i, long j, long j2, WindowInsets windowInsets, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function6, Composer composer, final int i2, final int i3) {
        int i4;
        Function2<? super Composer, ? super Integer, Unit> function7;
        int i5;
        Function2<? super Composer, ? super Integer, Unit> function8;
        int i6;
        int i7;
        Function2<? super Composer, ? super Integer, Unit> function9;
        int i8;
        int i9;
        Function2<? super Composer, ? super Integer, Unit> function10;
        int i10;
        int i11;
        int i12;
        int i13;
        boolean z;
        Composer composer2;
        final Modifier modifier2;
        final WindowInsets windowInsets2;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final Function2<? super Composer, ? super Integer, Unit> function12;
        final Function2<? super Composer, ? super Integer, Unit> function13;
        final Function2<? super Composer, ? super Integer, Unit> function14;
        final int i14;
        final long j3;
        final long j4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier3;
        Function2<? super Composer, ? super Integer, Unit> function2M319getLambda$39202156$material3;
        Function2<? super Composer, ? super Integer, Unit> lambda$1582488484$material3;
        Function2<? super Composer, ? super Integer, Unit> lambda$414328099$material3;
        Function2<? super Composer, ? super Integer, Unit> function2M318getLambda$1514016380$material3;
        int iM472getEndERTFSPs;
        long background;
        long jM278contentColorForek8zF_U;
        final WindowInsets contentWindowInsets;
        long j5;
        boolean z2;
        Object objRememberedValue;
        final MutableWindowInsets mutableWindowInsets;
        boolean zChanged;
        Object objRememberedValue2;
        int i15;
        int i16;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1211482744);
        int i17 = i3 & 1;
        if (i17 != 0) {
            i4 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        int i18 = i3 & 2;
        if (i18 == 0) {
            if ((i2 & 48) == 0) {
                function7 = function2;
                i4 |= composerStartRestartGroup.changedInstance(function7) ? 32 : 16;
            }
            i5 = i3 & 4;
            if (i5 != 0) {
                if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    function8 = function3;
                    if (composerStartRestartGroup.changedInstance(function8)) {
                        i6 = 256;
                    } else {
                        i6 = 128;
                    }
                    i4 |= i6;
                }
                i7 = i3 & 8;
                if (i7 != 0) {
                    if ((i2 & 3072) == 0) {
                        function9 = function4;
                        if (composerStartRestartGroup.changedInstance(function9)) {
                            i8 = 2048;
                        } else {
                            i8 = 1024;
                        }
                        i4 |= i8;
                    }
                    i9 = i3 & 16;
                    if (i9 != 0) {
                        if ((i2 & 24576) == 0) {
                            function10 = function5;
                            if (composerStartRestartGroup.changedInstance(function10)) {
                                i10 = 16384;
                            } else {
                                i10 = 8192;
                            }
                            i4 |= i10;
                        }
                        i11 = i3 & 32;
                        if (i11 != 0) {
                            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changed(i)) {
                                i12 = 131072;
                            } else {
                                i12 = 65536;
                            }
                            i4 |= i12;
                        }
                        if ((i2 & 1572864) != 0) {
                            if ((i3 & 64) == 0 || !composerStartRestartGroup.changed(j)) {
                                i16 = 524288;
                            } else {
                                i16 = 1048576;
                            }
                            i4 |= i16;
                        }
                        if ((i2 & 12582912) != 0) {
                            if ((i3 & 128) == 0 || !composerStartRestartGroup.changed(j2)) {
                                i15 = 4194304;
                            } else {
                                i15 = 8388608;
                            }
                            i4 |= i15;
                        }
                        if ((i2 & 100663296) != 0) {
                            i4 |= ((i3 & 256) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        }
                        if ((i3 & 512) != 0) {
                            if ((i2 & 805306368) == 0) {
                                if (composerStartRestartGroup.changedInstance(function6)) {
                                    i13 = 536870912;
                                } else {
                                    i13 = 268435456;
                                }
                                i4 |= i13;
                            }
                            if ((i4 & 306783379) != 306783378) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                                composerStartRestartGroup.startDefaults();
                                if ((i2 & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                    if (i17 != 0) {
                                        modifier3 = Modifier.INSTANCE;
                                    } else {
                                        modifier3 = modifier;
                                    }
                                    if (i18 != 0) {
                                        function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                                    } else {
                                        function2M319getLambda$39202156$material3 = function7;
                                    }
                                    if (i5 != 0) {
                                        lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                                    } else {
                                        lambda$1582488484$material3 = function8;
                                    }
                                    if (i7 != 0) {
                                        lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                                    } else {
                                        lambda$414328099$material3 = function9;
                                    }
                                    if (i9 != 0) {
                                        function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                                    } else {
                                        function2M318getLambda$1514016380$material3 = function10;
                                    }
                                    if (i11 != 0) {
                                        iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                                    } else {
                                        iM472getEndERTFSPs = i;
                                    }
                                    if ((i3 & 64) != 0) {
                                        i4 &= -3670017;
                                        background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                                    } else {
                                        background = j;
                                    }
                                    if ((i3 & 128) != 0) {
                                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                        i4 &= -29360129;
                                    } else {
                                        jM278contentColorForek8zF_U = j2;
                                    }
                                    if ((i3 & 256) != 0) {
                                        contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                        i4 &= -234881025;
                                    } else {
                                        contentWindowInsets = windowInsets;
                                    }
                                    j5 = jM278contentColorForek8zF_U;
                                } else {
                                    composerStartRestartGroup.skipToGroupEnd();
                                    if ((i3 & 64) != 0) {
                                        i4 &= -3670017;
                                    }
                                    if ((i3 & 128) != 0) {
                                        i4 &= -29360129;
                                    }
                                    if ((i3 & 256) != 0) {
                                        i4 &= -234881025;
                                    }
                                    modifier3 = modifier;
                                    iM472getEndERTFSPs = i;
                                    background = j;
                                    function2M319getLambda$39202156$material3 = function7;
                                    lambda$1582488484$material3 = function8;
                                    lambda$414328099$material3 = function9;
                                    function2M318getLambda$1514016380$material3 = function10;
                                    j5 = j2;
                                    contentWindowInsets = windowInsets;
                                }
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                                }
                                int i19 = (234881024 & i4) ^ r19;
                                z2 = (i19 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & r19) == 67108864;
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (z2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                                long j6 = background;
                                zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i19 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                                if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue2 = new Function1() { // from class: wuc
                                        public final Object invoke(Object obj) {
                                            return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                                }
                                final Function2<? super Composer, ? super Integer, Unit> function15 = function2M319getLambda$39202156$material3;
                                final Function2<? super Composer, ? super Integer, Unit> function16 = lambda$1582488484$material3;
                                final Function2<? super Composer, ? super Integer, Unit> function17 = lambda$414328099$material3;
                                final Function2<? super Composer, ? super Integer, Unit> function18 = function2M318getLambda$1514016380$material3;
                                final int i20 = iM472getEndERTFSPs;
                                int i21 = i4 >> 12;
                                composer2 = composerStartRestartGroup;
                                SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j6, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                                    public final void invoke(Composer composer3, int i22) {
                                        if (!composer3.shouldExecute((i22 & 3) != 2, i22 & 1)) {
                                            composer3.skipToGroupEnd();
                                            return;
                                        }
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(848889571, i22, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                                        }
                                        ScaffoldKt.m786ScaffoldLayoutFMILGgc(i20, function15, function6, function17, function18, mutableWindowInsets, function16, composer3, 0);
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                        }
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                        invoke((Composer) obj, ((Number) obj2).intValue());
                                        return Unit.INSTANCE;
                                    }
                                }, composerStartRestartGroup, 54), composer2, (i21 & 896) | 12582912 | (i21 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier2 = modifier3;
                                function11 = function2M319getLambda$39202156$material3;
                                function12 = lambda$1582488484$material3;
                                function13 = lambda$414328099$material3;
                                function14 = function2M318getLambda$1514016380$material3;
                                i14 = iM472getEndERTFSPs;
                                windowInsets2 = contentWindowInsets;
                                j3 = j6;
                                j4 = j5;
                            } else {
                                composer2 = composerStartRestartGroup;
                                composer2.skipToGroupEnd();
                                modifier2 = modifier;
                                windowInsets2 = windowInsets;
                                function11 = function7;
                                function12 = function8;
                                function13 = function9;
                                function14 = function10;
                                i14 = i;
                                j3 = j;
                                j4 = j2;
                            }
                            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                                    public final Object invoke(Object obj, Object obj2) {
                                        return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i4 |= 805306368;
                        if ((i4 & 306783379) != 306783378) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i2 & 1) != 0) {
                                if (i17 != 0) {
                                    modifier3 = Modifier.INSTANCE;
                                } else {
                                    modifier3 = modifier;
                                }
                                if (i18 != 0) {
                                    function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                                } else {
                                    function2M319getLambda$39202156$material3 = function7;
                                }
                                if (i5 != 0) {
                                    lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                                } else {
                                    lambda$1582488484$material3 = function8;
                                }
                                if (i7 != 0) {
                                    lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                                } else {
                                    lambda$414328099$material3 = function9;
                                }
                                if (i9 != 0) {
                                    function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                                } else {
                                    function2M318getLambda$1514016380$material3 = function10;
                                }
                                if (i11 != 0) {
                                    iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                                } else {
                                    iM472getEndERTFSPs = i;
                                }
                                if ((i3 & 64) != 0) {
                                    i4 &= -3670017;
                                    background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                                } else {
                                    background = j;
                                }
                                if ((i3 & 128) != 0) {
                                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                    i4 &= -29360129;
                                } else {
                                    jM278contentColorForek8zF_U = j2;
                                }
                                if ((i3 & 256) != 0) {
                                    contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    contentWindowInsets = windowInsets;
                                }
                                j5 = jM278contentColorForek8zF_U;
                            } else {
                                if (i17 != 0) {
                                    modifier3 = Modifier.INSTANCE;
                                } else {
                                    modifier3 = modifier;
                                }
                                if (i18 != 0) {
                                    function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                                } else {
                                    function2M319getLambda$39202156$material3 = function7;
                                }
                                if (i5 != 0) {
                                    lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                                } else {
                                    lambda$1582488484$material3 = function8;
                                }
                                if (i7 != 0) {
                                    lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                                } else {
                                    lambda$414328099$material3 = function9;
                                }
                                if (i9 != 0) {
                                    function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                                } else {
                                    function2M318getLambda$1514016380$material3 = function10;
                                }
                                if (i11 != 0) {
                                    iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                                } else {
                                    iM472getEndERTFSPs = i;
                                }
                                if ((i3 & 64) != 0) {
                                    i4 &= -3670017;
                                    background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                                } else {
                                    background = j;
                                }
                                if ((i3 & 128) != 0) {
                                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                    i4 &= -29360129;
                                } else {
                                    jM278contentColorForek8zF_U = j2;
                                }
                                if ((i3 & 256) != 0) {
                                    contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    contentWindowInsets = windowInsets;
                                }
                                j5 = jM278contentColorForek8zF_U;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                            }
                            int i110 = (234881024 & i4) ^ r19;
                            if (i110 <= 67108864) {
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (z2) {
                                objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            } else {
                                objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                            long j7 = background;
                            zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i110 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (zChanged) {
                                objRememberedValue2 = new Function1() { // from class: wuc
                                    public final Object invoke(Object obj) {
                                        return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function1() { // from class: wuc
                                    public final Object invoke(Object obj) {
                                        return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            final Function2<? super Composer, ? super Integer, Unit> function19 = function2M319getLambda$39202156$material3;
                            final Function2<? super Composer, ? super Integer, Unit> function110 = lambda$1582488484$material3;
                            final Function2<? super Composer, ? super Integer, Unit> function111 = lambda$414328099$material3;
                            final Function2<? super Composer, ? super Integer, Unit> function112 = function2M318getLambda$1514016380$material3;
                            final int i22 = iM472getEndERTFSPs;
                            int i23 = i4 >> 12;
                            composer2 = composerStartRestartGroup;
                            SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j7, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                                public final void invoke(Composer composer3, int i24) {
                                    if (!composer3.shouldExecute((i24 & 3) != 2, i24 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(848889571, i24, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                                    }
                                    ScaffoldKt.m786ScaffoldLayoutFMILGgc(i22, function19, function6, function111, function112, mutableWindowInsets, function110, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composer2, (i23 & 896) | 12582912 | (i23 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier2 = modifier3;
                            function11 = function2M319getLambda$39202156$material3;
                            function12 = lambda$1582488484$material3;
                            function13 = lambda$414328099$material3;
                            function14 = function2M318getLambda$1514016380$material3;
                            i14 = iM472getEndERTFSPs;
                            windowInsets2 = contentWindowInsets;
                            j3 = j7;
                            j4 = j5;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier2 = modifier;
                            windowInsets2 = windowInsets;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            function14 = function10;
                            i14 = i;
                            j3 = j;
                            j4 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                                public final Object invoke(Object obj, Object obj2) {
                                    return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i4 |= 24576;
                    function10 = function5;
                    i11 = i3 & 32;
                    if (i11 != 0) {
                        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i)) {
                            i12 = 131072;
                        } else {
                            i12 = 65536;
                        }
                        i4 |= i12;
                    }
                    if ((i2 & 1572864) != 0) {
                        if ((i3 & 64) == 0) {
                            i16 = 524288;
                        } else {
                            i16 = 524288;
                        }
                        i4 |= i16;
                    }
                    if ((i2 & 12582912) != 0) {
                        if ((i3 & 128) == 0) {
                            i15 = 4194304;
                        } else {
                            i15 = 4194304;
                        }
                        i4 |= i15;
                    }
                    if ((i2 & 100663296) != 0) {
                        i4 |= ((i3 & 256) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    }
                    if ((i3 & 512) != 0) {
                        if ((i2 & 805306368) == 0) {
                            if (composerStartRestartGroup.changedInstance(function6)) {
                                i13 = 536870912;
                            } else {
                                i13 = 268435456;
                            }
                            i4 |= i13;
                        }
                        if ((i4 & 306783379) != 306783378) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i2 & 1) != 0) {
                                if (i17 != 0) {
                                    modifier3 = Modifier.INSTANCE;
                                } else {
                                    modifier3 = modifier;
                                }
                                if (i18 != 0) {
                                    function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                                } else {
                                    function2M319getLambda$39202156$material3 = function7;
                                }
                                if (i5 != 0) {
                                    lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                                } else {
                                    lambda$1582488484$material3 = function8;
                                }
                                if (i7 != 0) {
                                    lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                                } else {
                                    lambda$414328099$material3 = function9;
                                }
                                if (i9 != 0) {
                                    function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                                } else {
                                    function2M318getLambda$1514016380$material3 = function10;
                                }
                                if (i11 != 0) {
                                    iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                                } else {
                                    iM472getEndERTFSPs = i;
                                }
                                if ((i3 & 64) != 0) {
                                    i4 &= -3670017;
                                    background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                                } else {
                                    background = j;
                                }
                                if ((i3 & 128) != 0) {
                                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                    i4 &= -29360129;
                                } else {
                                    jM278contentColorForek8zF_U = j2;
                                }
                                if ((i3 & 256) != 0) {
                                    contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    contentWindowInsets = windowInsets;
                                }
                                j5 = jM278contentColorForek8zF_U;
                            } else {
                                if (i17 != 0) {
                                    modifier3 = Modifier.INSTANCE;
                                } else {
                                    modifier3 = modifier;
                                }
                                if (i18 != 0) {
                                    function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                                } else {
                                    function2M319getLambda$39202156$material3 = function7;
                                }
                                if (i5 != 0) {
                                    lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                                } else {
                                    lambda$1582488484$material3 = function8;
                                }
                                if (i7 != 0) {
                                    lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                                } else {
                                    lambda$414328099$material3 = function9;
                                }
                                if (i9 != 0) {
                                    function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                                } else {
                                    function2M318getLambda$1514016380$material3 = function10;
                                }
                                if (i11 != 0) {
                                    iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                                } else {
                                    iM472getEndERTFSPs = i;
                                }
                                if ((i3 & 64) != 0) {
                                    i4 &= -3670017;
                                    background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                                } else {
                                    background = j;
                                }
                                if ((i3 & 128) != 0) {
                                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                    i4 &= -29360129;
                                } else {
                                    jM278contentColorForek8zF_U = j2;
                                }
                                if ((i3 & 256) != 0) {
                                    contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    contentWindowInsets = windowInsets;
                                }
                                j5 = jM278contentColorForek8zF_U;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                            }
                            int i111 = (234881024 & i4) ^ r19;
                            if (i111 <= 67108864) {
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (z2) {
                                objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            } else {
                                objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                            long j8 = background;
                            zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i111 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (zChanged) {
                                objRememberedValue2 = new Function1() { // from class: wuc
                                    public final Object invoke(Object obj) {
                                        return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function1() { // from class: wuc
                                    public final Object invoke(Object obj) {
                                        return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            final Function2<? super Composer, ? super Integer, Unit> function113 = function2M319getLambda$39202156$material3;
                            final Function2<? super Composer, ? super Integer, Unit> function114 = lambda$1582488484$material3;
                            final Function2<? super Composer, ? super Integer, Unit> function115 = lambda$414328099$material3;
                            final Function2<? super Composer, ? super Integer, Unit> function116 = function2M318getLambda$1514016380$material3;
                            final int i24 = iM472getEndERTFSPs;
                            int i25 = i4 >> 12;
                            composer2 = composerStartRestartGroup;
                            SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j8, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                                public final void invoke(Composer composer3, int i26) {
                                    if (!composer3.shouldExecute((i26 & 3) != 2, i26 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(848889571, i26, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                                    }
                                    ScaffoldKt.m786ScaffoldLayoutFMILGgc(i24, function113, function6, function115, function116, mutableWindowInsets, function114, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composer2, (i25 & 896) | 12582912 | (i25 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier2 = modifier3;
                            function11 = function2M319getLambda$39202156$material3;
                            function12 = lambda$1582488484$material3;
                            function13 = lambda$414328099$material3;
                            function14 = function2M318getLambda$1514016380$material3;
                            i14 = iM472getEndERTFSPs;
                            windowInsets2 = contentWindowInsets;
                            j3 = j8;
                            j4 = j5;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier2 = modifier;
                            windowInsets2 = windowInsets;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            function14 = function10;
                            i14 = i;
                            j3 = j;
                            j4 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                                public final Object invoke(Object obj, Object obj2) {
                                    return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i4 |= 805306368;
                    if ((i4 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        } else {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                        }
                        int i112 = (234881024 & i4) ^ r19;
                        if (i112 <= 67108864) {
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                        long j9 = background;
                        zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i112 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function117 = function2M319getLambda$39202156$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function118 = lambda$1582488484$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function119 = lambda$414328099$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function1110 = function2M318getLambda$1514016380$material3;
                        final int i26 = iM472getEndERTFSPs;
                        int i27 = i4 >> 12;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j9, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                            public final void invoke(Composer composer3, int i28) {
                                if (!composer3.shouldExecute((i28 & 3) != 2, i28 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(848889571, i28, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                                }
                                ScaffoldKt.m786ScaffoldLayoutFMILGgc(i26, function117, function6, function119, function1110, mutableWindowInsets, function118, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i27 & 896) | 12582912 | (i27 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier3;
                        function11 = function2M319getLambda$39202156$material3;
                        function12 = lambda$1582488484$material3;
                        function13 = lambda$414328099$material3;
                        function14 = function2M318getLambda$1514016380$material3;
                        i14 = iM472getEndERTFSPs;
                        windowInsets2 = contentWindowInsets;
                        j3 = j9;
                        j4 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        windowInsets2 = windowInsets;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        function14 = function10;
                        i14 = i;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                            public final Object invoke(Object obj, Object obj2) {
                                return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 3072;
                function9 = function4;
                i9 = i3 & 16;
                if (i9 != 0) {
                    if ((i2 & 24576) == 0) {
                        function10 = function5;
                        if (composerStartRestartGroup.changedInstance(function10)) {
                            i10 = 16384;
                        } else {
                            i10 = 8192;
                        }
                        i4 |= i10;
                    }
                    i11 = i3 & 32;
                    if (i11 != 0) {
                        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i)) {
                            i12 = 131072;
                        } else {
                            i12 = 65536;
                        }
                        i4 |= i12;
                    }
                    if ((i2 & 1572864) != 0) {
                        if ((i3 & 64) == 0) {
                            i16 = 524288;
                        } else {
                            i16 = 524288;
                        }
                        i4 |= i16;
                    }
                    if ((i2 & 12582912) != 0) {
                        if ((i3 & 128) == 0) {
                            i15 = 4194304;
                        } else {
                            i15 = 4194304;
                        }
                        i4 |= i15;
                    }
                    if ((i2 & 100663296) != 0) {
                        i4 |= ((i3 & 256) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    }
                    if ((i3 & 512) != 0) {
                        if ((i2 & 805306368) == 0) {
                            if (composerStartRestartGroup.changedInstance(function6)) {
                                i13 = 536870912;
                            } else {
                                i13 = 268435456;
                            }
                            i4 |= i13;
                        }
                        if ((i4 & 306783379) != 306783378) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i2 & 1) != 0) {
                                if (i17 != 0) {
                                    modifier3 = Modifier.INSTANCE;
                                } else {
                                    modifier3 = modifier;
                                }
                                if (i18 != 0) {
                                    function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                                } else {
                                    function2M319getLambda$39202156$material3 = function7;
                                }
                                if (i5 != 0) {
                                    lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                                } else {
                                    lambda$1582488484$material3 = function8;
                                }
                                if (i7 != 0) {
                                    lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                                } else {
                                    lambda$414328099$material3 = function9;
                                }
                                if (i9 != 0) {
                                    function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                                } else {
                                    function2M318getLambda$1514016380$material3 = function10;
                                }
                                if (i11 != 0) {
                                    iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                                } else {
                                    iM472getEndERTFSPs = i;
                                }
                                if ((i3 & 64) != 0) {
                                    i4 &= -3670017;
                                    background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                                } else {
                                    background = j;
                                }
                                if ((i3 & 128) != 0) {
                                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                    i4 &= -29360129;
                                } else {
                                    jM278contentColorForek8zF_U = j2;
                                }
                                if ((i3 & 256) != 0) {
                                    contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    contentWindowInsets = windowInsets;
                                }
                                j5 = jM278contentColorForek8zF_U;
                            } else {
                                if (i17 != 0) {
                                    modifier3 = Modifier.INSTANCE;
                                } else {
                                    modifier3 = modifier;
                                }
                                if (i18 != 0) {
                                    function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                                } else {
                                    function2M319getLambda$39202156$material3 = function7;
                                }
                                if (i5 != 0) {
                                    lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                                } else {
                                    lambda$1582488484$material3 = function8;
                                }
                                if (i7 != 0) {
                                    lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                                } else {
                                    lambda$414328099$material3 = function9;
                                }
                                if (i9 != 0) {
                                    function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                                } else {
                                    function2M318getLambda$1514016380$material3 = function10;
                                }
                                if (i11 != 0) {
                                    iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                                } else {
                                    iM472getEndERTFSPs = i;
                                }
                                if ((i3 & 64) != 0) {
                                    i4 &= -3670017;
                                    background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                                } else {
                                    background = j;
                                }
                                if ((i3 & 128) != 0) {
                                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                    i4 &= -29360129;
                                } else {
                                    jM278contentColorForek8zF_U = j2;
                                }
                                if ((i3 & 256) != 0) {
                                    contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    contentWindowInsets = windowInsets;
                                }
                                j5 = jM278contentColorForek8zF_U;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                            }
                            int i113 = (234881024 & i4) ^ r19;
                            if (i113 <= 67108864) {
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (z2) {
                                objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            } else {
                                objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                            long j10 = background;
                            zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i113 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (zChanged) {
                                objRememberedValue2 = new Function1() { // from class: wuc
                                    public final Object invoke(Object obj) {
                                        return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function1() { // from class: wuc
                                    public final Object invoke(Object obj) {
                                        return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            final Function2<? super Composer, ? super Integer, Unit> function1111 = function2M319getLambda$39202156$material3;
                            final Function2<? super Composer, ? super Integer, Unit> function1112 = lambda$1582488484$material3;
                            final Function2<? super Composer, ? super Integer, Unit> function1113 = lambda$414328099$material3;
                            final Function2<? super Composer, ? super Integer, Unit> function1114 = function2M318getLambda$1514016380$material3;
                            final int i28 = iM472getEndERTFSPs;
                            int i29 = i4 >> 12;
                            composer2 = composerStartRestartGroup;
                            SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j10, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                                public final void invoke(Composer composer3, int i210) {
                                    if (!composer3.shouldExecute((i210 & 3) != 2, i210 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(848889571, i210, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                                    }
                                    ScaffoldKt.m786ScaffoldLayoutFMILGgc(i28, function1111, function6, function1113, function1114, mutableWindowInsets, function1112, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composer2, (i29 & 896) | 12582912 | (i29 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier2 = modifier3;
                            function11 = function2M319getLambda$39202156$material3;
                            function12 = lambda$1582488484$material3;
                            function13 = lambda$414328099$material3;
                            function14 = function2M318getLambda$1514016380$material3;
                            i14 = iM472getEndERTFSPs;
                            windowInsets2 = contentWindowInsets;
                            j3 = j10;
                            j4 = j5;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier2 = modifier;
                            windowInsets2 = windowInsets;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            function14 = function10;
                            i14 = i;
                            j3 = j;
                            j4 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                                public final Object invoke(Object obj, Object obj2) {
                                    return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i4 |= 805306368;
                    if ((i4 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        } else {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                        }
                        int i114 = (234881024 & i4) ^ r19;
                        if (i114 <= 67108864) {
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                        long j11 = background;
                        zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i114 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function1115 = function2M319getLambda$39202156$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function1116 = lambda$1582488484$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function1117 = lambda$414328099$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function1118 = function2M318getLambda$1514016380$material3;
                        final int i210 = iM472getEndERTFSPs;
                        int i211 = i4 >> 12;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j11, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                            public final void invoke(Composer composer3, int i212) {
                                if (!composer3.shouldExecute((i212 & 3) != 2, i212 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(848889571, i212, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                                }
                                ScaffoldKt.m786ScaffoldLayoutFMILGgc(i210, function1115, function6, function1117, function1118, mutableWindowInsets, function1116, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i211 & 896) | 12582912 | (i211 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier3;
                        function11 = function2M319getLambda$39202156$material3;
                        function12 = lambda$1582488484$material3;
                        function13 = lambda$414328099$material3;
                        function14 = function2M318getLambda$1514016380$material3;
                        i14 = iM472getEndERTFSPs;
                        windowInsets2 = contentWindowInsets;
                        j3 = j11;
                        j4 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        windowInsets2 = windowInsets;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        function14 = function10;
                        i14 = i;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                            public final Object invoke(Object obj, Object obj2) {
                                return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 24576;
                function10 = function5;
                i11 = i3 & 32;
                if (i11 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i)) {
                        i12 = 131072;
                    } else {
                        i12 = 65536;
                    }
                    i4 |= i12;
                }
                if ((i2 & 1572864) != 0) {
                    if ((i3 & 64) == 0) {
                        i16 = 524288;
                    } else {
                        i16 = 524288;
                    }
                    i4 |= i16;
                }
                if ((i2 & 12582912) != 0) {
                    if ((i3 & 128) == 0) {
                        i15 = 4194304;
                    } else {
                        i15 = 4194304;
                    }
                    i4 |= i15;
                }
                if ((i2 & 100663296) != 0) {
                    i4 |= ((i3 & 256) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                }
                if ((i3 & 512) != 0) {
                    if ((i2 & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i13 = 536870912;
                        } else {
                            i13 = 268435456;
                        }
                        i4 |= i13;
                    }
                    if ((i4 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        } else {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                        }
                        int i115 = (234881024 & i4) ^ r19;
                        if (i115 <= 67108864) {
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                        long j12 = background;
                        zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i115 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function1119 = function2M319getLambda$39202156$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function11110 = lambda$1582488484$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function11111 = lambda$414328099$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function11112 = function2M318getLambda$1514016380$material3;
                        final int i212 = iM472getEndERTFSPs;
                        int i213 = i4 >> 12;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j12, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                            public final void invoke(Composer composer3, int i214) {
                                if (!composer3.shouldExecute((i214 & 3) != 2, i214 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(848889571, i214, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                                }
                                ScaffoldKt.m786ScaffoldLayoutFMILGgc(i212, function1119, function6, function11111, function11112, mutableWindowInsets, function11110, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i213 & 896) | 12582912 | (i213 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier3;
                        function11 = function2M319getLambda$39202156$material3;
                        function12 = lambda$1582488484$material3;
                        function13 = lambda$414328099$material3;
                        function14 = function2M318getLambda$1514016380$material3;
                        i14 = iM472getEndERTFSPs;
                        windowInsets2 = contentWindowInsets;
                        j3 = j12;
                        j4 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        windowInsets2 = windowInsets;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        function14 = function10;
                        i14 = i;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                            public final Object invoke(Object obj, Object obj2) {
                                return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                if ((i4 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    } else {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                    }
                    int i116 = (234881024 & i4) ^ r19;
                    if (i116 <= 67108864) {
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                    long j13 = background;
                    zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i116 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function11113 = function2M319getLambda$39202156$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function11114 = lambda$1582488484$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function11115 = lambda$414328099$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function11116 = function2M318getLambda$1514016380$material3;
                    final int i214 = iM472getEndERTFSPs;
                    int i215 = i4 >> 12;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j13, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                        public final void invoke(Composer composer3, int i216) {
                            if (!composer3.shouldExecute((i216 & 3) != 2, i216 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(848889571, i216, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                            }
                            ScaffoldKt.m786ScaffoldLayoutFMILGgc(i214, function11113, function6, function11115, function11116, mutableWindowInsets, function11114, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i215 & 896) | 12582912 | (i215 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    function11 = function2M319getLambda$39202156$material3;
                    function12 = lambda$1582488484$material3;
                    function13 = lambda$414328099$material3;
                    function14 = function2M318getLambda$1514016380$material3;
                    i14 = iM472getEndERTFSPs;
                    windowInsets2 = contentWindowInsets;
                    j3 = j13;
                    j4 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    windowInsets2 = windowInsets;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    function14 = function10;
                    i14 = i;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                        public final Object invoke(Object obj, Object obj2) {
                            return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            function8 = function3;
            i7 = i3 & 8;
            if (i7 != 0) {
                if ((i2 & 3072) == 0) {
                    function9 = function4;
                    if (composerStartRestartGroup.changedInstance(function9)) {
                        i8 = 2048;
                    } else {
                        i8 = 1024;
                    }
                    i4 |= i8;
                }
                i9 = i3 & 16;
                if (i9 != 0) {
                    if ((i2 & 24576) == 0) {
                        function10 = function5;
                        if (composerStartRestartGroup.changedInstance(function10)) {
                            i10 = 16384;
                        } else {
                            i10 = 8192;
                        }
                        i4 |= i10;
                    }
                    i11 = i3 & 32;
                    if (i11 != 0) {
                        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i)) {
                            i12 = 131072;
                        } else {
                            i12 = 65536;
                        }
                        i4 |= i12;
                    }
                    if ((i2 & 1572864) != 0) {
                        if ((i3 & 64) == 0) {
                            i16 = 524288;
                        } else {
                            i16 = 524288;
                        }
                        i4 |= i16;
                    }
                    if ((i2 & 12582912) != 0) {
                        if ((i3 & 128) == 0) {
                            i15 = 4194304;
                        } else {
                            i15 = 4194304;
                        }
                        i4 |= i15;
                    }
                    if ((i2 & 100663296) != 0) {
                        i4 |= ((i3 & 256) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    }
                    if ((i3 & 512) != 0) {
                        if ((i2 & 805306368) == 0) {
                            if (composerStartRestartGroup.changedInstance(function6)) {
                                i13 = 536870912;
                            } else {
                                i13 = 268435456;
                            }
                            i4 |= i13;
                        }
                        if ((i4 & 306783379) != 306783378) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i2 & 1) != 0) {
                                if (i17 != 0) {
                                    modifier3 = Modifier.INSTANCE;
                                } else {
                                    modifier3 = modifier;
                                }
                                if (i18 != 0) {
                                    function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                                } else {
                                    function2M319getLambda$39202156$material3 = function7;
                                }
                                if (i5 != 0) {
                                    lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                                } else {
                                    lambda$1582488484$material3 = function8;
                                }
                                if (i7 != 0) {
                                    lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                                } else {
                                    lambda$414328099$material3 = function9;
                                }
                                if (i9 != 0) {
                                    function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                                } else {
                                    function2M318getLambda$1514016380$material3 = function10;
                                }
                                if (i11 != 0) {
                                    iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                                } else {
                                    iM472getEndERTFSPs = i;
                                }
                                if ((i3 & 64) != 0) {
                                    i4 &= -3670017;
                                    background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                                } else {
                                    background = j;
                                }
                                if ((i3 & 128) != 0) {
                                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                    i4 &= -29360129;
                                } else {
                                    jM278contentColorForek8zF_U = j2;
                                }
                                if ((i3 & 256) != 0) {
                                    contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    contentWindowInsets = windowInsets;
                                }
                                j5 = jM278contentColorForek8zF_U;
                            } else {
                                if (i17 != 0) {
                                    modifier3 = Modifier.INSTANCE;
                                } else {
                                    modifier3 = modifier;
                                }
                                if (i18 != 0) {
                                    function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                                } else {
                                    function2M319getLambda$39202156$material3 = function7;
                                }
                                if (i5 != 0) {
                                    lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                                } else {
                                    lambda$1582488484$material3 = function8;
                                }
                                if (i7 != 0) {
                                    lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                                } else {
                                    lambda$414328099$material3 = function9;
                                }
                                if (i9 != 0) {
                                    function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                                } else {
                                    function2M318getLambda$1514016380$material3 = function10;
                                }
                                if (i11 != 0) {
                                    iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                                } else {
                                    iM472getEndERTFSPs = i;
                                }
                                if ((i3 & 64) != 0) {
                                    i4 &= -3670017;
                                    background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                                } else {
                                    background = j;
                                }
                                if ((i3 & 128) != 0) {
                                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                    i4 &= -29360129;
                                } else {
                                    jM278contentColorForek8zF_U = j2;
                                }
                                if ((i3 & 256) != 0) {
                                    contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    contentWindowInsets = windowInsets;
                                }
                                j5 = jM278contentColorForek8zF_U;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                            }
                            int i117 = (234881024 & i4) ^ r19;
                            if (i117 <= 67108864) {
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (z2) {
                                objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            } else {
                                objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                            long j14 = background;
                            zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i117 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (zChanged) {
                                objRememberedValue2 = new Function1() { // from class: wuc
                                    public final Object invoke(Object obj) {
                                        return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function1() { // from class: wuc
                                    public final Object invoke(Object obj) {
                                        return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            final Function2<? super Composer, ? super Integer, Unit> function11117 = function2M319getLambda$39202156$material3;
                            final Function2<? super Composer, ? super Integer, Unit> function11118 = lambda$1582488484$material3;
                            final Function2<? super Composer, ? super Integer, Unit> function11119 = lambda$414328099$material3;
                            final Function2<? super Composer, ? super Integer, Unit> function111110 = function2M318getLambda$1514016380$material3;
                            final int i216 = iM472getEndERTFSPs;
                            int i217 = i4 >> 12;
                            composer2 = composerStartRestartGroup;
                            SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j14, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                                public final void invoke(Composer composer3, int i218) {
                                    if (!composer3.shouldExecute((i218 & 3) != 2, i218 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(848889571, i218, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                                    }
                                    ScaffoldKt.m786ScaffoldLayoutFMILGgc(i216, function11117, function6, function11119, function111110, mutableWindowInsets, function11118, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composer2, (i217 & 896) | 12582912 | (i217 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier2 = modifier3;
                            function11 = function2M319getLambda$39202156$material3;
                            function12 = lambda$1582488484$material3;
                            function13 = lambda$414328099$material3;
                            function14 = function2M318getLambda$1514016380$material3;
                            i14 = iM472getEndERTFSPs;
                            windowInsets2 = contentWindowInsets;
                            j3 = j14;
                            j4 = j5;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier2 = modifier;
                            windowInsets2 = windowInsets;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            function14 = function10;
                            i14 = i;
                            j3 = j;
                            j4 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                                public final Object invoke(Object obj, Object obj2) {
                                    return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i4 |= 805306368;
                    if ((i4 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        } else {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                        }
                        int i118 = (234881024 & i4) ^ r19;
                        if (i118 <= 67108864) {
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                        long j15 = background;
                        zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i118 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function111111 = function2M319getLambda$39202156$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function111112 = lambda$1582488484$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function111113 = lambda$414328099$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function111114 = function2M318getLambda$1514016380$material3;
                        final int i218 = iM472getEndERTFSPs;
                        int i219 = i4 >> 12;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j15, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                            public final void invoke(Composer composer3, int i2110) {
                                if (!composer3.shouldExecute((i2110 & 3) != 2, i2110 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(848889571, i2110, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                                }
                                ScaffoldKt.m786ScaffoldLayoutFMILGgc(i218, function111111, function6, function111113, function111114, mutableWindowInsets, function111112, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i219 & 896) | 12582912 | (i219 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier3;
                        function11 = function2M319getLambda$39202156$material3;
                        function12 = lambda$1582488484$material3;
                        function13 = lambda$414328099$material3;
                        function14 = function2M318getLambda$1514016380$material3;
                        i14 = iM472getEndERTFSPs;
                        windowInsets2 = contentWindowInsets;
                        j3 = j15;
                        j4 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        windowInsets2 = windowInsets;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        function14 = function10;
                        i14 = i;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                            public final Object invoke(Object obj, Object obj2) {
                                return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 24576;
                function10 = function5;
                i11 = i3 & 32;
                if (i11 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i)) {
                        i12 = 131072;
                    } else {
                        i12 = 65536;
                    }
                    i4 |= i12;
                }
                if ((i2 & 1572864) != 0) {
                    if ((i3 & 64) == 0) {
                        i16 = 524288;
                    } else {
                        i16 = 524288;
                    }
                    i4 |= i16;
                }
                if ((i2 & 12582912) != 0) {
                    if ((i3 & 128) == 0) {
                        i15 = 4194304;
                    } else {
                        i15 = 4194304;
                    }
                    i4 |= i15;
                }
                if ((i2 & 100663296) != 0) {
                    i4 |= ((i3 & 256) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                }
                if ((i3 & 512) != 0) {
                    if ((i2 & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i13 = 536870912;
                        } else {
                            i13 = 268435456;
                        }
                        i4 |= i13;
                    }
                    if ((i4 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        } else {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                        }
                        int i119 = (234881024 & i4) ^ r19;
                        if (i119 <= 67108864) {
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                        long j16 = background;
                        zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i119 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function111115 = function2M319getLambda$39202156$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function111116 = lambda$1582488484$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function111117 = lambda$414328099$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function111118 = function2M318getLambda$1514016380$material3;
                        final int i2110 = iM472getEndERTFSPs;
                        int i2111 = i4 >> 12;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j16, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                            public final void invoke(Composer composer3, int i2112) {
                                if (!composer3.shouldExecute((i2112 & 3) != 2, i2112 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(848889571, i2112, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                                }
                                ScaffoldKt.m786ScaffoldLayoutFMILGgc(i2110, function111115, function6, function111117, function111118, mutableWindowInsets, function111116, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i2111 & 896) | 12582912 | (i2111 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier3;
                        function11 = function2M319getLambda$39202156$material3;
                        function12 = lambda$1582488484$material3;
                        function13 = lambda$414328099$material3;
                        function14 = function2M318getLambda$1514016380$material3;
                        i14 = iM472getEndERTFSPs;
                        windowInsets2 = contentWindowInsets;
                        j3 = j16;
                        j4 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        windowInsets2 = windowInsets;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        function14 = function10;
                        i14 = i;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                            public final Object invoke(Object obj, Object obj2) {
                                return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                if ((i4 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    } else {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                    }
                    int i1110 = (234881024 & i4) ^ r19;
                    if (i1110 <= 67108864) {
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                    long j17 = background;
                    zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i1110 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function111119 = function2M319getLambda$39202156$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function1111110 = lambda$1582488484$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function1111111 = lambda$414328099$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function1111112 = function2M318getLambda$1514016380$material3;
                    final int i2112 = iM472getEndERTFSPs;
                    int i2113 = i4 >> 12;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j17, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                        public final void invoke(Composer composer3, int i2114) {
                            if (!composer3.shouldExecute((i2114 & 3) != 2, i2114 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(848889571, i2114, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                            }
                            ScaffoldKt.m786ScaffoldLayoutFMILGgc(i2112, function111119, function6, function1111111, function1111112, mutableWindowInsets, function1111110, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i2113 & 896) | 12582912 | (i2113 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    function11 = function2M319getLambda$39202156$material3;
                    function12 = lambda$1582488484$material3;
                    function13 = lambda$414328099$material3;
                    function14 = function2M318getLambda$1514016380$material3;
                    i14 = iM472getEndERTFSPs;
                    windowInsets2 = contentWindowInsets;
                    j3 = j17;
                    j4 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    windowInsets2 = windowInsets;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    function14 = function10;
                    i14 = i;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                        public final Object invoke(Object obj, Object obj2) {
                            return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 3072;
            function9 = function4;
            i9 = i3 & 16;
            if (i9 != 0) {
                if ((i2 & 24576) == 0) {
                    function10 = function5;
                    if (composerStartRestartGroup.changedInstance(function10)) {
                        i10 = 16384;
                    } else {
                        i10 = 8192;
                    }
                    i4 |= i10;
                }
                i11 = i3 & 32;
                if (i11 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i)) {
                        i12 = 131072;
                    } else {
                        i12 = 65536;
                    }
                    i4 |= i12;
                }
                if ((i2 & 1572864) != 0) {
                    if ((i3 & 64) == 0) {
                        i16 = 524288;
                    } else {
                        i16 = 524288;
                    }
                    i4 |= i16;
                }
                if ((i2 & 12582912) != 0) {
                    if ((i3 & 128) == 0) {
                        i15 = 4194304;
                    } else {
                        i15 = 4194304;
                    }
                    i4 |= i15;
                }
                if ((i2 & 100663296) != 0) {
                    i4 |= ((i3 & 256) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                }
                if ((i3 & 512) != 0) {
                    if ((i2 & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i13 = 536870912;
                        } else {
                            i13 = 268435456;
                        }
                        i4 |= i13;
                    }
                    if ((i4 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        } else {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                        }
                        int i1111 = (234881024 & i4) ^ r19;
                        if (i1111 <= 67108864) {
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                        long j18 = background;
                        zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i1111 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function1111113 = function2M319getLambda$39202156$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function1111114 = lambda$1582488484$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function1111115 = lambda$414328099$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function1111116 = function2M318getLambda$1514016380$material3;
                        final int i2114 = iM472getEndERTFSPs;
                        int i2115 = i4 >> 12;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j18, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                            public final void invoke(Composer composer3, int i2116) {
                                if (!composer3.shouldExecute((i2116 & 3) != 2, i2116 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(848889571, i2116, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                                }
                                ScaffoldKt.m786ScaffoldLayoutFMILGgc(i2114, function1111113, function6, function1111115, function1111116, mutableWindowInsets, function1111114, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i2115 & 896) | 12582912 | (i2115 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier3;
                        function11 = function2M319getLambda$39202156$material3;
                        function12 = lambda$1582488484$material3;
                        function13 = lambda$414328099$material3;
                        function14 = function2M318getLambda$1514016380$material3;
                        i14 = iM472getEndERTFSPs;
                        windowInsets2 = contentWindowInsets;
                        j3 = j18;
                        j4 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        windowInsets2 = windowInsets;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        function14 = function10;
                        i14 = i;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                            public final Object invoke(Object obj, Object obj2) {
                                return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                if ((i4 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    } else {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                    }
                    int i1112 = (234881024 & i4) ^ r19;
                    if (i1112 <= 67108864) {
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                    long j19 = background;
                    zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i1112 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function1111117 = function2M319getLambda$39202156$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function1111118 = lambda$1582488484$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function1111119 = lambda$414328099$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function11111110 = function2M318getLambda$1514016380$material3;
                    final int i2116 = iM472getEndERTFSPs;
                    int i2117 = i4 >> 12;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j19, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                        public final void invoke(Composer composer3, int i2118) {
                            if (!composer3.shouldExecute((i2118 & 3) != 2, i2118 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(848889571, i2118, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                            }
                            ScaffoldKt.m786ScaffoldLayoutFMILGgc(i2116, function1111117, function6, function1111119, function11111110, mutableWindowInsets, function1111118, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i2117 & 896) | 12582912 | (i2117 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    function11 = function2M319getLambda$39202156$material3;
                    function12 = lambda$1582488484$material3;
                    function13 = lambda$414328099$material3;
                    function14 = function2M318getLambda$1514016380$material3;
                    i14 = iM472getEndERTFSPs;
                    windowInsets2 = contentWindowInsets;
                    j3 = j19;
                    j4 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    windowInsets2 = windowInsets;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    function14 = function10;
                    i14 = i;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                        public final Object invoke(Object obj, Object obj2) {
                            return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            function10 = function5;
            i11 = i3 & 32;
            if (i11 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(i)) {
                    i12 = 131072;
                } else {
                    i12 = 65536;
                }
                i4 |= i12;
            }
            if ((i2 & 1572864) != 0) {
                if ((i3 & 64) == 0) {
                    i16 = 524288;
                } else {
                    i16 = 524288;
                }
                i4 |= i16;
            }
            if ((i2 & 12582912) != 0) {
                if ((i3 & 128) == 0) {
                    i15 = 4194304;
                } else {
                    i15 = 4194304;
                }
                i4 |= i15;
            }
            if ((i2 & 100663296) != 0) {
                i4 |= ((i3 & 256) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            }
            if ((i3 & 512) != 0) {
                if ((i2 & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i13 = 536870912;
                    } else {
                        i13 = 268435456;
                    }
                    i4 |= i13;
                }
                if ((i4 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    } else {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                    }
                    int i1113 = (234881024 & i4) ^ r19;
                    if (i1113 <= 67108864) {
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                    long j110 = background;
                    zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i1113 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function11111111 = function2M319getLambda$39202156$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function11111112 = lambda$1582488484$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function11111113 = lambda$414328099$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function11111114 = function2M318getLambda$1514016380$material3;
                    final int i2118 = iM472getEndERTFSPs;
                    int i2119 = i4 >> 12;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j110, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                        public final void invoke(Composer composer3, int i21110) {
                            if (!composer3.shouldExecute((i21110 & 3) != 2, i21110 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(848889571, i21110, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                            }
                            ScaffoldKt.m786ScaffoldLayoutFMILGgc(i2118, function11111111, function6, function11111113, function11111114, mutableWindowInsets, function11111112, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i2119 & 896) | 12582912 | (i2119 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    function11 = function2M319getLambda$39202156$material3;
                    function12 = lambda$1582488484$material3;
                    function13 = lambda$414328099$material3;
                    function14 = function2M318getLambda$1514016380$material3;
                    i14 = iM472getEndERTFSPs;
                    windowInsets2 = contentWindowInsets;
                    j3 = j110;
                    j4 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    windowInsets2 = windowInsets;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    function14 = function10;
                    i14 = i;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                        public final Object invoke(Object obj, Object obj2) {
                            return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 805306368;
            if ((i4 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i17 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i18 != 0) {
                        function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                    } else {
                        function2M319getLambda$39202156$material3 = function7;
                    }
                    if (i5 != 0) {
                        lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                    } else {
                        lambda$1582488484$material3 = function8;
                    }
                    if (i7 != 0) {
                        lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                    } else {
                        lambda$414328099$material3 = function9;
                    }
                    if (i9 != 0) {
                        function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                    } else {
                        function2M318getLambda$1514016380$material3 = function10;
                    }
                    if (i11 != 0) {
                        iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                    } else {
                        iM472getEndERTFSPs = i;
                    }
                    if ((i3 & 64) != 0) {
                        i4 &= -3670017;
                        background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                    } else {
                        background = j;
                    }
                    if ((i3 & 128) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                        i4 &= -29360129;
                    } else {
                        jM278contentColorForek8zF_U = j2;
                    }
                    if ((i3 & 256) != 0) {
                        contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        contentWindowInsets = windowInsets;
                    }
                    j5 = jM278contentColorForek8zF_U;
                } else {
                    if (i17 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i18 != 0) {
                        function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                    } else {
                        function2M319getLambda$39202156$material3 = function7;
                    }
                    if (i5 != 0) {
                        lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                    } else {
                        lambda$1582488484$material3 = function8;
                    }
                    if (i7 != 0) {
                        lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                    } else {
                        lambda$414328099$material3 = function9;
                    }
                    if (i9 != 0) {
                        function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                    } else {
                        function2M318getLambda$1514016380$material3 = function10;
                    }
                    if (i11 != 0) {
                        iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                    } else {
                        iM472getEndERTFSPs = i;
                    }
                    if ((i3 & 64) != 0) {
                        i4 &= -3670017;
                        background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                    } else {
                        background = j;
                    }
                    if ((i3 & 128) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                        i4 &= -29360129;
                    } else {
                        jM278contentColorForek8zF_U = j2;
                    }
                    if ((i3 & 256) != 0) {
                        contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        contentWindowInsets = windowInsets;
                    }
                    j5 = jM278contentColorForek8zF_U;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                }
                int i1114 = (234881024 & i4) ^ r19;
                if (i1114 <= 67108864) {
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                long j111 = background;
                zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i1114 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue2 = new Function1() { // from class: wuc
                        public final Object invoke(Object obj) {
                            return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: wuc
                        public final Object invoke(Object obj) {
                            return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final Function2<? super Composer, ? super Integer, Unit> function11111115 = function2M319getLambda$39202156$material3;
                final Function2<? super Composer, ? super Integer, Unit> function11111116 = lambda$1582488484$material3;
                final Function2<? super Composer, ? super Integer, Unit> function11111117 = lambda$414328099$material3;
                final Function2<? super Composer, ? super Integer, Unit> function11111118 = function2M318getLambda$1514016380$material3;
                final int i21110 = iM472getEndERTFSPs;
                int i21111 = i4 >> 12;
                composer2 = composerStartRestartGroup;
                SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j111, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                    public final void invoke(Composer composer3, int i21112) {
                        if (!composer3.shouldExecute((i21112 & 3) != 2, i21112 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(848889571, i21112, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                        }
                        ScaffoldKt.m786ScaffoldLayoutFMILGgc(i21110, function11111115, function6, function11111117, function11111118, mutableWindowInsets, function11111116, composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, (i21111 & 896) | 12582912 | (i21111 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
                function11 = function2M319getLambda$39202156$material3;
                function12 = lambda$1582488484$material3;
                function13 = lambda$414328099$material3;
                function14 = function2M318getLambda$1514016380$material3;
                i14 = iM472getEndERTFSPs;
                windowInsets2 = contentWindowInsets;
                j3 = j111;
                j4 = j5;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                windowInsets2 = windowInsets;
                function11 = function7;
                function12 = function8;
                function13 = function9;
                function14 = function10;
                i14 = i;
                j3 = j;
                j4 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                    public final Object invoke(Object obj, Object obj2) {
                        return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 48;
        function7 = function2;
        i5 = i3 & 4;
        if (i5 != 0) {
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                function8 = function3;
                if (composerStartRestartGroup.changedInstance(function8)) {
                    i6 = 256;
                } else {
                    i6 = 128;
                }
                i4 |= i6;
            }
            i7 = i3 & 8;
            if (i7 != 0) {
                if ((i2 & 3072) == 0) {
                    function9 = function4;
                    if (composerStartRestartGroup.changedInstance(function9)) {
                        i8 = 2048;
                    } else {
                        i8 = 1024;
                    }
                    i4 |= i8;
                }
                i9 = i3 & 16;
                if (i9 != 0) {
                    if ((i2 & 24576) == 0) {
                        function10 = function5;
                        if (composerStartRestartGroup.changedInstance(function10)) {
                            i10 = 16384;
                        } else {
                            i10 = 8192;
                        }
                        i4 |= i10;
                    }
                    i11 = i3 & 32;
                    if (i11 != 0) {
                        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(i)) {
                            i12 = 131072;
                        } else {
                            i12 = 65536;
                        }
                        i4 |= i12;
                    }
                    if ((i2 & 1572864) != 0) {
                        if ((i3 & 64) == 0) {
                            i16 = 524288;
                        } else {
                            i16 = 524288;
                        }
                        i4 |= i16;
                    }
                    if ((i2 & 12582912) != 0) {
                        if ((i3 & 128) == 0) {
                            i15 = 4194304;
                        } else {
                            i15 = 4194304;
                        }
                        i4 |= i15;
                    }
                    if ((i2 & 100663296) != 0) {
                        i4 |= ((i3 & 256) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    }
                    if ((i3 & 512) != 0) {
                        if ((i2 & 805306368) == 0) {
                            if (composerStartRestartGroup.changedInstance(function6)) {
                                i13 = 536870912;
                            } else {
                                i13 = 268435456;
                            }
                            i4 |= i13;
                        }
                        if ((i4 & 306783379) != 306783378) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i2 & 1) != 0) {
                                if (i17 != 0) {
                                    modifier3 = Modifier.INSTANCE;
                                } else {
                                    modifier3 = modifier;
                                }
                                if (i18 != 0) {
                                    function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                                } else {
                                    function2M319getLambda$39202156$material3 = function7;
                                }
                                if (i5 != 0) {
                                    lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                                } else {
                                    lambda$1582488484$material3 = function8;
                                }
                                if (i7 != 0) {
                                    lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                                } else {
                                    lambda$414328099$material3 = function9;
                                }
                                if (i9 != 0) {
                                    function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                                } else {
                                    function2M318getLambda$1514016380$material3 = function10;
                                }
                                if (i11 != 0) {
                                    iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                                } else {
                                    iM472getEndERTFSPs = i;
                                }
                                if ((i3 & 64) != 0) {
                                    i4 &= -3670017;
                                    background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                                } else {
                                    background = j;
                                }
                                if ((i3 & 128) != 0) {
                                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                    i4 &= -29360129;
                                } else {
                                    jM278contentColorForek8zF_U = j2;
                                }
                                if ((i3 & 256) != 0) {
                                    contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    contentWindowInsets = windowInsets;
                                }
                                j5 = jM278contentColorForek8zF_U;
                            } else {
                                if (i17 != 0) {
                                    modifier3 = Modifier.INSTANCE;
                                } else {
                                    modifier3 = modifier;
                                }
                                if (i18 != 0) {
                                    function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                                } else {
                                    function2M319getLambda$39202156$material3 = function7;
                                }
                                if (i5 != 0) {
                                    lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                                } else {
                                    lambda$1582488484$material3 = function8;
                                }
                                if (i7 != 0) {
                                    lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                                } else {
                                    lambda$414328099$material3 = function9;
                                }
                                if (i9 != 0) {
                                    function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                                } else {
                                    function2M318getLambda$1514016380$material3 = function10;
                                }
                                if (i11 != 0) {
                                    iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                                } else {
                                    iM472getEndERTFSPs = i;
                                }
                                if ((i3 & 64) != 0) {
                                    i4 &= -3670017;
                                    background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                                } else {
                                    background = j;
                                }
                                if ((i3 & 128) != 0) {
                                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                    i4 &= -29360129;
                                } else {
                                    jM278contentColorForek8zF_U = j2;
                                }
                                if ((i3 & 256) != 0) {
                                    contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    contentWindowInsets = windowInsets;
                                }
                                j5 = jM278contentColorForek8zF_U;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                            }
                            int i1115 = (234881024 & i4) ^ r19;
                            if (i1115 <= 67108864) {
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (z2) {
                                objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            } else {
                                objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                            long j112 = background;
                            zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i1115 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (zChanged) {
                                objRememberedValue2 = new Function1() { // from class: wuc
                                    public final Object invoke(Object obj) {
                                        return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            } else {
                                objRememberedValue2 = new Function1() { // from class: wuc
                                    public final Object invoke(Object obj) {
                                        return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            final Function2<? super Composer, ? super Integer, Unit> function11111119 = function2M319getLambda$39202156$material3;
                            final Function2<? super Composer, ? super Integer, Unit> function111111110 = lambda$1582488484$material3;
                            final Function2<? super Composer, ? super Integer, Unit> function111111111 = lambda$414328099$material3;
                            final Function2<? super Composer, ? super Integer, Unit> function111111112 = function2M318getLambda$1514016380$material3;
                            final int i21112 = iM472getEndERTFSPs;
                            int i21113 = i4 >> 12;
                            composer2 = composerStartRestartGroup;
                            SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j112, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                                public final void invoke(Composer composer3, int i21114) {
                                    if (!composer3.shouldExecute((i21114 & 3) != 2, i21114 & 1)) {
                                        composer3.skipToGroupEnd();
                                        return;
                                    }
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(848889571, i21114, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                                    }
                                    ScaffoldKt.m786ScaffoldLayoutFMILGgc(i21112, function11111119, function6, function111111111, function111111112, mutableWindowInsets, function111111110, composer3, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    invoke((Composer) obj, ((Number) obj2).intValue());
                                    return Unit.INSTANCE;
                                }
                            }, composerStartRestartGroup, 54), composer2, (i21113 & 896) | 12582912 | (i21113 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier2 = modifier3;
                            function11 = function2M319getLambda$39202156$material3;
                            function12 = lambda$1582488484$material3;
                            function13 = lambda$414328099$material3;
                            function14 = function2M318getLambda$1514016380$material3;
                            i14 = iM472getEndERTFSPs;
                            windowInsets2 = contentWindowInsets;
                            j3 = j112;
                            j4 = j5;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            modifier2 = modifier;
                            windowInsets2 = windowInsets;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            function14 = function10;
                            i14 = i;
                            j3 = j;
                            j4 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                                public final Object invoke(Object obj, Object obj2) {
                                    return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i4 |= 805306368;
                    if ((i4 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        } else {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                        }
                        int i1116 = (234881024 & i4) ^ r19;
                        if (i1116 <= 67108864) {
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                        long j113 = background;
                        zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i1116 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function111111113 = function2M319getLambda$39202156$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function111111114 = lambda$1582488484$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function111111115 = lambda$414328099$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function111111116 = function2M318getLambda$1514016380$material3;
                        final int i21114 = iM472getEndERTFSPs;
                        int i21115 = i4 >> 12;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j113, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                            public final void invoke(Composer composer3, int i21116) {
                                if (!composer3.shouldExecute((i21116 & 3) != 2, i21116 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(848889571, i21116, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                                }
                                ScaffoldKt.m786ScaffoldLayoutFMILGgc(i21114, function111111113, function6, function111111115, function111111116, mutableWindowInsets, function111111114, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i21115 & 896) | 12582912 | (i21115 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier3;
                        function11 = function2M319getLambda$39202156$material3;
                        function12 = lambda$1582488484$material3;
                        function13 = lambda$414328099$material3;
                        function14 = function2M318getLambda$1514016380$material3;
                        i14 = iM472getEndERTFSPs;
                        windowInsets2 = contentWindowInsets;
                        j3 = j113;
                        j4 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        windowInsets2 = windowInsets;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        function14 = function10;
                        i14 = i;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                            public final Object invoke(Object obj, Object obj2) {
                                return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 24576;
                function10 = function5;
                i11 = i3 & 32;
                if (i11 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i)) {
                        i12 = 131072;
                    } else {
                        i12 = 65536;
                    }
                    i4 |= i12;
                }
                if ((i2 & 1572864) != 0) {
                    if ((i3 & 64) == 0) {
                        i16 = 524288;
                    } else {
                        i16 = 524288;
                    }
                    i4 |= i16;
                }
                if ((i2 & 12582912) != 0) {
                    if ((i3 & 128) == 0) {
                        i15 = 4194304;
                    } else {
                        i15 = 4194304;
                    }
                    i4 |= i15;
                }
                if ((i2 & 100663296) != 0) {
                    i4 |= ((i3 & 256) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                }
                if ((i3 & 512) != 0) {
                    if ((i2 & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i13 = 536870912;
                        } else {
                            i13 = 268435456;
                        }
                        i4 |= i13;
                    }
                    if ((i4 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        } else {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                        }
                        int i1117 = (234881024 & i4) ^ r19;
                        if (i1117 <= 67108864) {
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                        long j114 = background;
                        zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i1117 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function111111117 = function2M319getLambda$39202156$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function111111118 = lambda$1582488484$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function111111119 = lambda$414328099$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function1111111110 = function2M318getLambda$1514016380$material3;
                        final int i21116 = iM472getEndERTFSPs;
                        int i21117 = i4 >> 12;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j114, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                            public final void invoke(Composer composer3, int i21118) {
                                if (!composer3.shouldExecute((i21118 & 3) != 2, i21118 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(848889571, i21118, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                                }
                                ScaffoldKt.m786ScaffoldLayoutFMILGgc(i21116, function111111117, function6, function111111119, function1111111110, mutableWindowInsets, function111111118, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i21117 & 896) | 12582912 | (i21117 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier3;
                        function11 = function2M319getLambda$39202156$material3;
                        function12 = lambda$1582488484$material3;
                        function13 = lambda$414328099$material3;
                        function14 = function2M318getLambda$1514016380$material3;
                        i14 = iM472getEndERTFSPs;
                        windowInsets2 = contentWindowInsets;
                        j3 = j114;
                        j4 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        windowInsets2 = windowInsets;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        function14 = function10;
                        i14 = i;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                            public final Object invoke(Object obj, Object obj2) {
                                return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                if ((i4 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    } else {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                    }
                    int i1118 = (234881024 & i4) ^ r19;
                    if (i1118 <= 67108864) {
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                    long j115 = background;
                    zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i1118 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function1111111111 = function2M319getLambda$39202156$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function1111111112 = lambda$1582488484$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function1111111113 = lambda$414328099$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function1111111114 = function2M318getLambda$1514016380$material3;
                    final int i21118 = iM472getEndERTFSPs;
                    int i21119 = i4 >> 12;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j115, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                        public final void invoke(Composer composer3, int i211110) {
                            if (!composer3.shouldExecute((i211110 & 3) != 2, i211110 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(848889571, i211110, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                            }
                            ScaffoldKt.m786ScaffoldLayoutFMILGgc(i21118, function1111111111, function6, function1111111113, function1111111114, mutableWindowInsets, function1111111112, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i21119 & 896) | 12582912 | (i21119 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    function11 = function2M319getLambda$39202156$material3;
                    function12 = lambda$1582488484$material3;
                    function13 = lambda$414328099$material3;
                    function14 = function2M318getLambda$1514016380$material3;
                    i14 = iM472getEndERTFSPs;
                    windowInsets2 = contentWindowInsets;
                    j3 = j115;
                    j4 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    windowInsets2 = windowInsets;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    function14 = function10;
                    i14 = i;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                        public final Object invoke(Object obj, Object obj2) {
                            return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 3072;
            function9 = function4;
            i9 = i3 & 16;
            if (i9 != 0) {
                if ((i2 & 24576) == 0) {
                    function10 = function5;
                    if (composerStartRestartGroup.changedInstance(function10)) {
                        i10 = 16384;
                    } else {
                        i10 = 8192;
                    }
                    i4 |= i10;
                }
                i11 = i3 & 32;
                if (i11 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i)) {
                        i12 = 131072;
                    } else {
                        i12 = 65536;
                    }
                    i4 |= i12;
                }
                if ((i2 & 1572864) != 0) {
                    if ((i3 & 64) == 0) {
                        i16 = 524288;
                    } else {
                        i16 = 524288;
                    }
                    i4 |= i16;
                }
                if ((i2 & 12582912) != 0) {
                    if ((i3 & 128) == 0) {
                        i15 = 4194304;
                    } else {
                        i15 = 4194304;
                    }
                    i4 |= i15;
                }
                if ((i2 & 100663296) != 0) {
                    i4 |= ((i3 & 256) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                }
                if ((i3 & 512) != 0) {
                    if ((i2 & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i13 = 536870912;
                        } else {
                            i13 = 268435456;
                        }
                        i4 |= i13;
                    }
                    if ((i4 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        } else {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                        }
                        int i1119 = (234881024 & i4) ^ r19;
                        if (i1119 <= 67108864) {
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                        long j116 = background;
                        zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i1119 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function1111111115 = function2M319getLambda$39202156$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function1111111116 = lambda$1582488484$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function1111111117 = lambda$414328099$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function1111111118 = function2M318getLambda$1514016380$material3;
                        final int i211110 = iM472getEndERTFSPs;
                        int i211111 = i4 >> 12;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j116, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                            public final void invoke(Composer composer3, int i211112) {
                                if (!composer3.shouldExecute((i211112 & 3) != 2, i211112 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(848889571, i211112, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                                }
                                ScaffoldKt.m786ScaffoldLayoutFMILGgc(i211110, function1111111115, function6, function1111111117, function1111111118, mutableWindowInsets, function1111111116, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i211111 & 896) | 12582912 | (i211111 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier3;
                        function11 = function2M319getLambda$39202156$material3;
                        function12 = lambda$1582488484$material3;
                        function13 = lambda$414328099$material3;
                        function14 = function2M318getLambda$1514016380$material3;
                        i14 = iM472getEndERTFSPs;
                        windowInsets2 = contentWindowInsets;
                        j3 = j116;
                        j4 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        windowInsets2 = windowInsets;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        function14 = function10;
                        i14 = i;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                            public final Object invoke(Object obj, Object obj2) {
                                return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                if ((i4 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    } else {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                    }
                    int i11110 = (234881024 & i4) ^ r19;
                    if (i11110 <= 67108864) {
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                    long j117 = background;
                    zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i11110 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function1111111119 = function2M319getLambda$39202156$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function11111111110 = lambda$1582488484$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function11111111111 = lambda$414328099$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function11111111112 = function2M318getLambda$1514016380$material3;
                    final int i211112 = iM472getEndERTFSPs;
                    int i211113 = i4 >> 12;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j117, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                        public final void invoke(Composer composer3, int i211114) {
                            if (!composer3.shouldExecute((i211114 & 3) != 2, i211114 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(848889571, i211114, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                            }
                            ScaffoldKt.m786ScaffoldLayoutFMILGgc(i211112, function1111111119, function6, function11111111111, function11111111112, mutableWindowInsets, function11111111110, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i211113 & 896) | 12582912 | (i211113 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    function11 = function2M319getLambda$39202156$material3;
                    function12 = lambda$1582488484$material3;
                    function13 = lambda$414328099$material3;
                    function14 = function2M318getLambda$1514016380$material3;
                    i14 = iM472getEndERTFSPs;
                    windowInsets2 = contentWindowInsets;
                    j3 = j117;
                    j4 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    windowInsets2 = windowInsets;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    function14 = function10;
                    i14 = i;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                        public final Object invoke(Object obj, Object obj2) {
                            return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            function10 = function5;
            i11 = i3 & 32;
            if (i11 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(i)) {
                    i12 = 131072;
                } else {
                    i12 = 65536;
                }
                i4 |= i12;
            }
            if ((i2 & 1572864) != 0) {
                if ((i3 & 64) == 0) {
                    i16 = 524288;
                } else {
                    i16 = 524288;
                }
                i4 |= i16;
            }
            if ((i2 & 12582912) != 0) {
                if ((i3 & 128) == 0) {
                    i15 = 4194304;
                } else {
                    i15 = 4194304;
                }
                i4 |= i15;
            }
            if ((i2 & 100663296) != 0) {
                i4 |= ((i3 & 256) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            }
            if ((i3 & 512) != 0) {
                if ((i2 & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i13 = 536870912;
                    } else {
                        i13 = 268435456;
                    }
                    i4 |= i13;
                }
                if ((i4 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    } else {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                    }
                    int i11111 = (234881024 & i4) ^ r19;
                    if (i11111 <= 67108864) {
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                    long j118 = background;
                    zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i11111 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function11111111113 = function2M319getLambda$39202156$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function11111111114 = lambda$1582488484$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function11111111115 = lambda$414328099$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function11111111116 = function2M318getLambda$1514016380$material3;
                    final int i211114 = iM472getEndERTFSPs;
                    int i211115 = i4 >> 12;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j118, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                        public final void invoke(Composer composer3, int i211116) {
                            if (!composer3.shouldExecute((i211116 & 3) != 2, i211116 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(848889571, i211116, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                            }
                            ScaffoldKt.m786ScaffoldLayoutFMILGgc(i211114, function11111111113, function6, function11111111115, function11111111116, mutableWindowInsets, function11111111114, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i211115 & 896) | 12582912 | (i211115 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    function11 = function2M319getLambda$39202156$material3;
                    function12 = lambda$1582488484$material3;
                    function13 = lambda$414328099$material3;
                    function14 = function2M318getLambda$1514016380$material3;
                    i14 = iM472getEndERTFSPs;
                    windowInsets2 = contentWindowInsets;
                    j3 = j118;
                    j4 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    windowInsets2 = windowInsets;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    function14 = function10;
                    i14 = i;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                        public final Object invoke(Object obj, Object obj2) {
                            return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 805306368;
            if ((i4 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i17 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i18 != 0) {
                        function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                    } else {
                        function2M319getLambda$39202156$material3 = function7;
                    }
                    if (i5 != 0) {
                        lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                    } else {
                        lambda$1582488484$material3 = function8;
                    }
                    if (i7 != 0) {
                        lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                    } else {
                        lambda$414328099$material3 = function9;
                    }
                    if (i9 != 0) {
                        function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                    } else {
                        function2M318getLambda$1514016380$material3 = function10;
                    }
                    if (i11 != 0) {
                        iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                    } else {
                        iM472getEndERTFSPs = i;
                    }
                    if ((i3 & 64) != 0) {
                        i4 &= -3670017;
                        background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                    } else {
                        background = j;
                    }
                    if ((i3 & 128) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                        i4 &= -29360129;
                    } else {
                        jM278contentColorForek8zF_U = j2;
                    }
                    if ((i3 & 256) != 0) {
                        contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        contentWindowInsets = windowInsets;
                    }
                    j5 = jM278contentColorForek8zF_U;
                } else {
                    if (i17 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i18 != 0) {
                        function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                    } else {
                        function2M319getLambda$39202156$material3 = function7;
                    }
                    if (i5 != 0) {
                        lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                    } else {
                        lambda$1582488484$material3 = function8;
                    }
                    if (i7 != 0) {
                        lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                    } else {
                        lambda$414328099$material3 = function9;
                    }
                    if (i9 != 0) {
                        function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                    } else {
                        function2M318getLambda$1514016380$material3 = function10;
                    }
                    if (i11 != 0) {
                        iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                    } else {
                        iM472getEndERTFSPs = i;
                    }
                    if ((i3 & 64) != 0) {
                        i4 &= -3670017;
                        background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                    } else {
                        background = j;
                    }
                    if ((i3 & 128) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                        i4 &= -29360129;
                    } else {
                        jM278contentColorForek8zF_U = j2;
                    }
                    if ((i3 & 256) != 0) {
                        contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        contentWindowInsets = windowInsets;
                    }
                    j5 = jM278contentColorForek8zF_U;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                }
                int i11112 = (234881024 & i4) ^ r19;
                if (i11112 <= 67108864) {
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                long j119 = background;
                zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i11112 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue2 = new Function1() { // from class: wuc
                        public final Object invoke(Object obj) {
                            return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: wuc
                        public final Object invoke(Object obj) {
                            return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final Function2<? super Composer, ? super Integer, Unit> function11111111117 = function2M319getLambda$39202156$material3;
                final Function2<? super Composer, ? super Integer, Unit> function11111111118 = lambda$1582488484$material3;
                final Function2<? super Composer, ? super Integer, Unit> function11111111119 = lambda$414328099$material3;
                final Function2<? super Composer, ? super Integer, Unit> function111111111110 = function2M318getLambda$1514016380$material3;
                final int i211116 = iM472getEndERTFSPs;
                int i211117 = i4 >> 12;
                composer2 = composerStartRestartGroup;
                SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j119, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                    public final void invoke(Composer composer3, int i211118) {
                        if (!composer3.shouldExecute((i211118 & 3) != 2, i211118 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(848889571, i211118, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                        }
                        ScaffoldKt.m786ScaffoldLayoutFMILGgc(i211116, function11111111117, function6, function11111111119, function111111111110, mutableWindowInsets, function11111111118, composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, (i211117 & 896) | 12582912 | (i211117 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
                function11 = function2M319getLambda$39202156$material3;
                function12 = lambda$1582488484$material3;
                function13 = lambda$414328099$material3;
                function14 = function2M318getLambda$1514016380$material3;
                i14 = iM472getEndERTFSPs;
                windowInsets2 = contentWindowInsets;
                j3 = j119;
                j4 = j5;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                windowInsets2 = windowInsets;
                function11 = function7;
                function12 = function8;
                function13 = function9;
                function14 = function10;
                i14 = i;
                j3 = j;
                j4 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                    public final Object invoke(Object obj, Object obj2) {
                        return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        function8 = function3;
        i7 = i3 & 8;
        if (i7 != 0) {
            if ((i2 & 3072) == 0) {
                function9 = function4;
                if (composerStartRestartGroup.changedInstance(function9)) {
                    i8 = 2048;
                } else {
                    i8 = 1024;
                }
                i4 |= i8;
            }
            i9 = i3 & 16;
            if (i9 != 0) {
                if ((i2 & 24576) == 0) {
                    function10 = function5;
                    if (composerStartRestartGroup.changedInstance(function10)) {
                        i10 = 16384;
                    } else {
                        i10 = 8192;
                    }
                    i4 |= i10;
                }
                i11 = i3 & 32;
                if (i11 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(i)) {
                        i12 = 131072;
                    } else {
                        i12 = 65536;
                    }
                    i4 |= i12;
                }
                if ((i2 & 1572864) != 0) {
                    if ((i3 & 64) == 0) {
                        i16 = 524288;
                    } else {
                        i16 = 524288;
                    }
                    i4 |= i16;
                }
                if ((i2 & 12582912) != 0) {
                    if ((i3 & 128) == 0) {
                        i15 = 4194304;
                    } else {
                        i15 = 4194304;
                    }
                    i4 |= i15;
                }
                if ((i2 & 100663296) != 0) {
                    i4 |= ((i3 & 256) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                }
                if ((i3 & 512) != 0) {
                    if ((i2 & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i13 = 536870912;
                        } else {
                            i13 = 268435456;
                        }
                        i4 |= i13;
                    }
                    if ((i4 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i2 & 1) != 0) {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        } else {
                            if (i17 != 0) {
                                modifier3 = Modifier.INSTANCE;
                            } else {
                                modifier3 = modifier;
                            }
                            if (i18 != 0) {
                                function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                            } else {
                                function2M319getLambda$39202156$material3 = function7;
                            }
                            if (i5 != 0) {
                                lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                            } else {
                                lambda$1582488484$material3 = function8;
                            }
                            if (i7 != 0) {
                                lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                            } else {
                                lambda$414328099$material3 = function9;
                            }
                            if (i9 != 0) {
                                function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                            } else {
                                function2M318getLambda$1514016380$material3 = function10;
                            }
                            if (i11 != 0) {
                                iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                            } else {
                                iM472getEndERTFSPs = i;
                            }
                            if ((i3 & 64) != 0) {
                                i4 &= -3670017;
                                background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                            } else {
                                background = j;
                            }
                            if ((i3 & 128) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                                i4 &= -29360129;
                            } else {
                                jM278contentColorForek8zF_U = j2;
                            }
                            if ((i3 & 256) != 0) {
                                contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                contentWindowInsets = windowInsets;
                            }
                            j5 = jM278contentColorForek8zF_U;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                        }
                        int i11113 = (234881024 & i4) ^ r19;
                        if (i11113 <= 67108864) {
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                        long j1110 = background;
                        zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i11113 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (zChanged) {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new Function1() { // from class: wuc
                                public final Object invoke(Object obj) {
                                    return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        final Function2<? super Composer, ? super Integer, Unit> function111111111111 = function2M319getLambda$39202156$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function111111111112 = lambda$1582488484$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function111111111113 = lambda$414328099$material3;
                        final Function2<? super Composer, ? super Integer, Unit> function111111111114 = function2M318getLambda$1514016380$material3;
                        final int i211118 = iM472getEndERTFSPs;
                        int i211119 = i4 >> 12;
                        composer2 = composerStartRestartGroup;
                        SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j1110, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                            public final void invoke(Composer composer3, int i2111110) {
                                if (!composer3.shouldExecute((i2111110 & 3) != 2, i2111110 & 1)) {
                                    composer3.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(848889571, i2111110, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                                }
                                ScaffoldKt.m786ScaffoldLayoutFMILGgc(i211118, function111111111111, function6, function111111111113, function111111111114, mutableWindowInsets, function111111111112, composer3, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                invoke((Composer) obj, ((Number) obj2).intValue());
                                return Unit.INSTANCE;
                            }
                        }, composerStartRestartGroup, 54), composer2, (i211119 & 896) | 12582912 | (i211119 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier2 = modifier3;
                        function11 = function2M319getLambda$39202156$material3;
                        function12 = lambda$1582488484$material3;
                        function13 = lambda$414328099$material3;
                        function14 = function2M318getLambda$1514016380$material3;
                        i14 = iM472getEndERTFSPs;
                        windowInsets2 = contentWindowInsets;
                        j3 = j1110;
                        j4 = j5;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        modifier2 = modifier;
                        windowInsets2 = windowInsets;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        function14 = function10;
                        i14 = i;
                        j3 = j;
                        j4 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                            public final Object invoke(Object obj, Object obj2) {
                                return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                if ((i4 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    } else {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                    }
                    int i11114 = (234881024 & i4) ^ r19;
                    if (i11114 <= 67108864) {
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                    long j1111 = background;
                    zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i11114 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function111111111115 = function2M319getLambda$39202156$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function111111111116 = lambda$1582488484$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function111111111117 = lambda$414328099$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function111111111118 = function2M318getLambda$1514016380$material3;
                    final int i2111110 = iM472getEndERTFSPs;
                    int i2111111 = i4 >> 12;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j1111, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                        public final void invoke(Composer composer3, int i2111112) {
                            if (!composer3.shouldExecute((i2111112 & 3) != 2, i2111112 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(848889571, i2111112, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                            }
                            ScaffoldKt.m786ScaffoldLayoutFMILGgc(i2111110, function111111111115, function6, function111111111117, function111111111118, mutableWindowInsets, function111111111116, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i2111111 & 896) | 12582912 | (i2111111 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    function11 = function2M319getLambda$39202156$material3;
                    function12 = lambda$1582488484$material3;
                    function13 = lambda$414328099$material3;
                    function14 = function2M318getLambda$1514016380$material3;
                    i14 = iM472getEndERTFSPs;
                    windowInsets2 = contentWindowInsets;
                    j3 = j1111;
                    j4 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    windowInsets2 = windowInsets;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    function14 = function10;
                    i14 = i;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                        public final Object invoke(Object obj, Object obj2) {
                            return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            function10 = function5;
            i11 = i3 & 32;
            if (i11 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(i)) {
                    i12 = 131072;
                } else {
                    i12 = 65536;
                }
                i4 |= i12;
            }
            if ((i2 & 1572864) != 0) {
                if ((i3 & 64) == 0) {
                    i16 = 524288;
                } else {
                    i16 = 524288;
                }
                i4 |= i16;
            }
            if ((i2 & 12582912) != 0) {
                if ((i3 & 128) == 0) {
                    i15 = 4194304;
                } else {
                    i15 = 4194304;
                }
                i4 |= i15;
            }
            if ((i2 & 100663296) != 0) {
                i4 |= ((i3 & 256) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            }
            if ((i3 & 512) != 0) {
                if ((i2 & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i13 = 536870912;
                    } else {
                        i13 = 268435456;
                    }
                    i4 |= i13;
                }
                if ((i4 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    } else {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                    }
                    int i11115 = (234881024 & i4) ^ r19;
                    if (i11115 <= 67108864) {
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                    long j1112 = background;
                    zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i11115 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function111111111119 = function2M319getLambda$39202156$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function1111111111110 = lambda$1582488484$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function1111111111111 = lambda$414328099$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function1111111111112 = function2M318getLambda$1514016380$material3;
                    final int i2111112 = iM472getEndERTFSPs;
                    int i2111113 = i4 >> 12;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j1112, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                        public final void invoke(Composer composer3, int i2111114) {
                            if (!composer3.shouldExecute((i2111114 & 3) != 2, i2111114 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(848889571, i2111114, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                            }
                            ScaffoldKt.m786ScaffoldLayoutFMILGgc(i2111112, function111111111119, function6, function1111111111111, function1111111111112, mutableWindowInsets, function1111111111110, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i2111113 & 896) | 12582912 | (i2111113 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    function11 = function2M319getLambda$39202156$material3;
                    function12 = lambda$1582488484$material3;
                    function13 = lambda$414328099$material3;
                    function14 = function2M318getLambda$1514016380$material3;
                    i14 = iM472getEndERTFSPs;
                    windowInsets2 = contentWindowInsets;
                    j3 = j1112;
                    j4 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    windowInsets2 = windowInsets;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    function14 = function10;
                    i14 = i;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                        public final Object invoke(Object obj, Object obj2) {
                            return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 805306368;
            if ((i4 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i17 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i18 != 0) {
                        function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                    } else {
                        function2M319getLambda$39202156$material3 = function7;
                    }
                    if (i5 != 0) {
                        lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                    } else {
                        lambda$1582488484$material3 = function8;
                    }
                    if (i7 != 0) {
                        lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                    } else {
                        lambda$414328099$material3 = function9;
                    }
                    if (i9 != 0) {
                        function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                    } else {
                        function2M318getLambda$1514016380$material3 = function10;
                    }
                    if (i11 != 0) {
                        iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                    } else {
                        iM472getEndERTFSPs = i;
                    }
                    if ((i3 & 64) != 0) {
                        i4 &= -3670017;
                        background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                    } else {
                        background = j;
                    }
                    if ((i3 & 128) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                        i4 &= -29360129;
                    } else {
                        jM278contentColorForek8zF_U = j2;
                    }
                    if ((i3 & 256) != 0) {
                        contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        contentWindowInsets = windowInsets;
                    }
                    j5 = jM278contentColorForek8zF_U;
                } else {
                    if (i17 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i18 != 0) {
                        function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                    } else {
                        function2M319getLambda$39202156$material3 = function7;
                    }
                    if (i5 != 0) {
                        lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                    } else {
                        lambda$1582488484$material3 = function8;
                    }
                    if (i7 != 0) {
                        lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                    } else {
                        lambda$414328099$material3 = function9;
                    }
                    if (i9 != 0) {
                        function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                    } else {
                        function2M318getLambda$1514016380$material3 = function10;
                    }
                    if (i11 != 0) {
                        iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                    } else {
                        iM472getEndERTFSPs = i;
                    }
                    if ((i3 & 64) != 0) {
                        i4 &= -3670017;
                        background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                    } else {
                        background = j;
                    }
                    if ((i3 & 128) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                        i4 &= -29360129;
                    } else {
                        jM278contentColorForek8zF_U = j2;
                    }
                    if ((i3 & 256) != 0) {
                        contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        contentWindowInsets = windowInsets;
                    }
                    j5 = jM278contentColorForek8zF_U;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                }
                int i11116 = (234881024 & i4) ^ r19;
                if (i11116 <= 67108864) {
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                long j1113 = background;
                zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i11116 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue2 = new Function1() { // from class: wuc
                        public final Object invoke(Object obj) {
                            return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: wuc
                        public final Object invoke(Object obj) {
                            return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final Function2<? super Composer, ? super Integer, Unit> function1111111111113 = function2M319getLambda$39202156$material3;
                final Function2<? super Composer, ? super Integer, Unit> function1111111111114 = lambda$1582488484$material3;
                final Function2<? super Composer, ? super Integer, Unit> function1111111111115 = lambda$414328099$material3;
                final Function2<? super Composer, ? super Integer, Unit> function1111111111116 = function2M318getLambda$1514016380$material3;
                final int i2111114 = iM472getEndERTFSPs;
                int i2111115 = i4 >> 12;
                composer2 = composerStartRestartGroup;
                SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j1113, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                    public final void invoke(Composer composer3, int i2111116) {
                        if (!composer3.shouldExecute((i2111116 & 3) != 2, i2111116 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(848889571, i2111116, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                        }
                        ScaffoldKt.m786ScaffoldLayoutFMILGgc(i2111114, function1111111111113, function6, function1111111111115, function1111111111116, mutableWindowInsets, function1111111111114, composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, (i2111115 & 896) | 12582912 | (i2111115 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
                function11 = function2M319getLambda$39202156$material3;
                function12 = lambda$1582488484$material3;
                function13 = lambda$414328099$material3;
                function14 = function2M318getLambda$1514016380$material3;
                i14 = iM472getEndERTFSPs;
                windowInsets2 = contentWindowInsets;
                j3 = j1113;
                j4 = j5;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                windowInsets2 = windowInsets;
                function11 = function7;
                function12 = function8;
                function13 = function9;
                function14 = function10;
                i14 = i;
                j3 = j;
                j4 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                    public final Object invoke(Object obj, Object obj2) {
                        return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 3072;
        function9 = function4;
        i9 = i3 & 16;
        if (i9 != 0) {
            if ((i2 & 24576) == 0) {
                function10 = function5;
                if (composerStartRestartGroup.changedInstance(function10)) {
                    i10 = 16384;
                } else {
                    i10 = 8192;
                }
                i4 |= i10;
            }
            i11 = i3 & 32;
            if (i11 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(i)) {
                    i12 = 131072;
                } else {
                    i12 = 65536;
                }
                i4 |= i12;
            }
            if ((i2 & 1572864) != 0) {
                if ((i3 & 64) == 0) {
                    i16 = 524288;
                } else {
                    i16 = 524288;
                }
                i4 |= i16;
            }
            if ((i2 & 12582912) != 0) {
                if ((i3 & 128) == 0) {
                    i15 = 4194304;
                } else {
                    i15 = 4194304;
                }
                i4 |= i15;
            }
            if ((i2 & 100663296) != 0) {
                i4 |= ((i3 & 256) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
            }
            if ((i3 & 512) != 0) {
                if ((i2 & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i13 = 536870912;
                    } else {
                        i13 = 268435456;
                    }
                    i4 |= i13;
                }
                if ((i4 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i2 & 1) != 0) {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    } else {
                        if (i17 != 0) {
                            modifier3 = Modifier.INSTANCE;
                        } else {
                            modifier3 = modifier;
                        }
                        if (i18 != 0) {
                            function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                        } else {
                            function2M319getLambda$39202156$material3 = function7;
                        }
                        if (i5 != 0) {
                            lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                        } else {
                            lambda$1582488484$material3 = function8;
                        }
                        if (i7 != 0) {
                            lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                        } else {
                            lambda$414328099$material3 = function9;
                        }
                        if (i9 != 0) {
                            function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                        } else {
                            function2M318getLambda$1514016380$material3 = function10;
                        }
                        if (i11 != 0) {
                            iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                        } else {
                            iM472getEndERTFSPs = i;
                        }
                        if ((i3 & 64) != 0) {
                            i4 &= -3670017;
                            background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                        } else {
                            background = j;
                        }
                        if ((i3 & 128) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                            i4 &= -29360129;
                        } else {
                            jM278contentColorForek8zF_U = j2;
                        }
                        if ((i3 & 256) != 0) {
                            contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            contentWindowInsets = windowInsets;
                        }
                        j5 = jM278contentColorForek8zF_U;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                    }
                    int i11117 = (234881024 & i4) ^ r19;
                    if (i11117 <= 67108864) {
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                    long j1114 = background;
                    zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i11117 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (zChanged) {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new Function1() { // from class: wuc
                            public final Object invoke(Object obj) {
                                return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    final Function2<? super Composer, ? super Integer, Unit> function1111111111117 = function2M319getLambda$39202156$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function1111111111118 = lambda$1582488484$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function1111111111119 = lambda$414328099$material3;
                    final Function2<? super Composer, ? super Integer, Unit> function11111111111110 = function2M318getLambda$1514016380$material3;
                    final int i2111116 = iM472getEndERTFSPs;
                    int i2111117 = i4 >> 12;
                    composer2 = composerStartRestartGroup;
                    SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j1114, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                        public final void invoke(Composer composer3, int i2111118) {
                            if (!composer3.shouldExecute((i2111118 & 3) != 2, i2111118 & 1)) {
                                composer3.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(848889571, i2111118, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                            }
                            ScaffoldKt.m786ScaffoldLayoutFMILGgc(i2111116, function1111111111117, function6, function1111111111119, function11111111111110, mutableWindowInsets, function1111111111118, composer3, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            invoke((Composer) obj, ((Number) obj2).intValue());
                            return Unit.INSTANCE;
                        }
                    }, composerStartRestartGroup, 54), composer2, (i2111117 & 896) | 12582912 | (i2111117 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier2 = modifier3;
                    function11 = function2M319getLambda$39202156$material3;
                    function12 = lambda$1582488484$material3;
                    function13 = lambda$414328099$material3;
                    function14 = function2M318getLambda$1514016380$material3;
                    i14 = iM472getEndERTFSPs;
                    windowInsets2 = contentWindowInsets;
                    j3 = j1114;
                    j4 = j5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    modifier2 = modifier;
                    windowInsets2 = windowInsets;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    function14 = function10;
                    i14 = i;
                    j3 = j;
                    j4 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                        public final Object invoke(Object obj, Object obj2) {
                            return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 805306368;
            if ((i4 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i17 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i18 != 0) {
                        function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                    } else {
                        function2M319getLambda$39202156$material3 = function7;
                    }
                    if (i5 != 0) {
                        lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                    } else {
                        lambda$1582488484$material3 = function8;
                    }
                    if (i7 != 0) {
                        lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                    } else {
                        lambda$414328099$material3 = function9;
                    }
                    if (i9 != 0) {
                        function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                    } else {
                        function2M318getLambda$1514016380$material3 = function10;
                    }
                    if (i11 != 0) {
                        iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                    } else {
                        iM472getEndERTFSPs = i;
                    }
                    if ((i3 & 64) != 0) {
                        i4 &= -3670017;
                        background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                    } else {
                        background = j;
                    }
                    if ((i3 & 128) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                        i4 &= -29360129;
                    } else {
                        jM278contentColorForek8zF_U = j2;
                    }
                    if ((i3 & 256) != 0) {
                        contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        contentWindowInsets = windowInsets;
                    }
                    j5 = jM278contentColorForek8zF_U;
                } else {
                    if (i17 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i18 != 0) {
                        function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                    } else {
                        function2M319getLambda$39202156$material3 = function7;
                    }
                    if (i5 != 0) {
                        lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                    } else {
                        lambda$1582488484$material3 = function8;
                    }
                    if (i7 != 0) {
                        lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                    } else {
                        lambda$414328099$material3 = function9;
                    }
                    if (i9 != 0) {
                        function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                    } else {
                        function2M318getLambda$1514016380$material3 = function10;
                    }
                    if (i11 != 0) {
                        iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                    } else {
                        iM472getEndERTFSPs = i;
                    }
                    if ((i3 & 64) != 0) {
                        i4 &= -3670017;
                        background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                    } else {
                        background = j;
                    }
                    if ((i3 & 128) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                        i4 &= -29360129;
                    } else {
                        jM278contentColorForek8zF_U = j2;
                    }
                    if ((i3 & 256) != 0) {
                        contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        contentWindowInsets = windowInsets;
                    }
                    j5 = jM278contentColorForek8zF_U;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                }
                int i11118 = (234881024 & i4) ^ r19;
                if (i11118 <= 67108864) {
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                long j1115 = background;
                zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i11118 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue2 = new Function1() { // from class: wuc
                        public final Object invoke(Object obj) {
                            return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: wuc
                        public final Object invoke(Object obj) {
                            return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final Function2<? super Composer, ? super Integer, Unit> function11111111111111 = function2M319getLambda$39202156$material3;
                final Function2<? super Composer, ? super Integer, Unit> function11111111111112 = lambda$1582488484$material3;
                final Function2<? super Composer, ? super Integer, Unit> function11111111111113 = lambda$414328099$material3;
                final Function2<? super Composer, ? super Integer, Unit> function11111111111114 = function2M318getLambda$1514016380$material3;
                final int i2111118 = iM472getEndERTFSPs;
                int i2111119 = i4 >> 12;
                composer2 = composerStartRestartGroup;
                SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j1115, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                    public final void invoke(Composer composer3, int i21111110) {
                        if (!composer3.shouldExecute((i21111110 & 3) != 2, i21111110 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(848889571, i21111110, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                        }
                        ScaffoldKt.m786ScaffoldLayoutFMILGgc(i2111118, function11111111111111, function6, function11111111111113, function11111111111114, mutableWindowInsets, function11111111111112, composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, (i2111119 & 896) | 12582912 | (i2111119 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
                function11 = function2M319getLambda$39202156$material3;
                function12 = lambda$1582488484$material3;
                function13 = lambda$414328099$material3;
                function14 = function2M318getLambda$1514016380$material3;
                i14 = iM472getEndERTFSPs;
                windowInsets2 = contentWindowInsets;
                j3 = j1115;
                j4 = j5;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                windowInsets2 = windowInsets;
                function11 = function7;
                function12 = function8;
                function13 = function9;
                function14 = function10;
                i14 = i;
                j3 = j;
                j4 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                    public final Object invoke(Object obj, Object obj2) {
                        return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 24576;
        function10 = function5;
        i11 = i3 & 32;
        if (i11 != 0) {
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if (composerStartRestartGroup.changed(i)) {
                i12 = 131072;
            } else {
                i12 = 65536;
            }
            i4 |= i12;
        }
        if ((i2 & 1572864) != 0) {
            if ((i3 & 64) == 0) {
                i16 = 524288;
            } else {
                i16 = 524288;
            }
            i4 |= i16;
        }
        if ((i2 & 12582912) != 0) {
            if ((i3 & 128) == 0) {
                i15 = 4194304;
            } else {
                i15 = 4194304;
            }
            i4 |= i15;
        }
        if ((i2 & 100663296) != 0) {
            i4 |= ((i3 & 256) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 33554432 : AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
        }
        if ((i3 & 512) != 0) {
            if ((i2 & 805306368) == 0) {
                if (composerStartRestartGroup.changedInstance(function6)) {
                    i13 = 536870912;
                } else {
                    i13 = 268435456;
                }
                i4 |= i13;
            }
            if ((i4 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i2 & 1) != 0) {
                    if (i17 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i18 != 0) {
                        function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                    } else {
                        function2M319getLambda$39202156$material3 = function7;
                    }
                    if (i5 != 0) {
                        lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                    } else {
                        lambda$1582488484$material3 = function8;
                    }
                    if (i7 != 0) {
                        lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                    } else {
                        lambda$414328099$material3 = function9;
                    }
                    if (i9 != 0) {
                        function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                    } else {
                        function2M318getLambda$1514016380$material3 = function10;
                    }
                    if (i11 != 0) {
                        iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                    } else {
                        iM472getEndERTFSPs = i;
                    }
                    if ((i3 & 64) != 0) {
                        i4 &= -3670017;
                        background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                    } else {
                        background = j;
                    }
                    if ((i3 & 128) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                        i4 &= -29360129;
                    } else {
                        jM278contentColorForek8zF_U = j2;
                    }
                    if ((i3 & 256) != 0) {
                        contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        contentWindowInsets = windowInsets;
                    }
                    j5 = jM278contentColorForek8zF_U;
                } else {
                    if (i17 != 0) {
                        modifier3 = Modifier.INSTANCE;
                    } else {
                        modifier3 = modifier;
                    }
                    if (i18 != 0) {
                        function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                    } else {
                        function2M319getLambda$39202156$material3 = function7;
                    }
                    if (i5 != 0) {
                        lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                    } else {
                        lambda$1582488484$material3 = function8;
                    }
                    if (i7 != 0) {
                        lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                    } else {
                        lambda$414328099$material3 = function9;
                    }
                    if (i9 != 0) {
                        function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                    } else {
                        function2M318getLambda$1514016380$material3 = function10;
                    }
                    if (i11 != 0) {
                        iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                    } else {
                        iM472getEndERTFSPs = i;
                    }
                    if ((i3 & 64) != 0) {
                        i4 &= -3670017;
                        background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                    } else {
                        background = j;
                    }
                    if ((i3 & 128) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                        i4 &= -29360129;
                    } else {
                        jM278contentColorForek8zF_U = j2;
                    }
                    if ((i3 & 256) != 0) {
                        contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        contentWindowInsets = windowInsets;
                    }
                    j5 = jM278contentColorForek8zF_U;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
                }
                int i11119 = (234881024 & i4) ^ r19;
                if (i11119 <= 67108864) {
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
                long j1116 = background;
                zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i11119 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (zChanged) {
                    objRememberedValue2 = new Function1() { // from class: wuc
                        public final Object invoke(Object obj) {
                            return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: wuc
                        public final Object invoke(Object obj) {
                            return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                final Function2<? super Composer, ? super Integer, Unit> function11111111111115 = function2M319getLambda$39202156$material3;
                final Function2<? super Composer, ? super Integer, Unit> function11111111111116 = lambda$1582488484$material3;
                final Function2<? super Composer, ? super Integer, Unit> function11111111111117 = lambda$414328099$material3;
                final Function2<? super Composer, ? super Integer, Unit> function11111111111118 = function2M318getLambda$1514016380$material3;
                final int i21111110 = iM472getEndERTFSPs;
                int i21111111 = i4 >> 12;
                composer2 = composerStartRestartGroup;
                SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j1116, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                    public final void invoke(Composer composer3, int i21111112) {
                        if (!composer3.shouldExecute((i21111112 & 3) != 2, i21111112 & 1)) {
                            composer3.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(848889571, i21111112, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                        }
                        ScaffoldKt.m786ScaffoldLayoutFMILGgc(i21111110, function11111111111115, function6, function11111111111117, function11111111111118, mutableWindowInsets, function11111111111116, composer3, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                }, composerStartRestartGroup, 54), composer2, (i21111111 & 896) | 12582912 | (i21111111 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier2 = modifier3;
                function11 = function2M319getLambda$39202156$material3;
                function12 = lambda$1582488484$material3;
                function13 = lambda$414328099$material3;
                function14 = function2M318getLambda$1514016380$material3;
                i14 = iM472getEndERTFSPs;
                windowInsets2 = contentWindowInsets;
                j3 = j1116;
                j4 = j5;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier2 = modifier;
                windowInsets2 = windowInsets;
                function11 = function7;
                function12 = function8;
                function13 = function9;
                function14 = function10;
                i14 = i;
                j3 = j;
                j4 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                    public final Object invoke(Object obj, Object obj2) {
                        return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 805306368;
        if ((i4 & 306783379) != 306783378) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i2 & 1) != 0) {
                if (i17 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if (i18 != 0) {
                    function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                } else {
                    function2M319getLambda$39202156$material3 = function7;
                }
                if (i5 != 0) {
                    lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                } else {
                    lambda$1582488484$material3 = function8;
                }
                if (i7 != 0) {
                    lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                } else {
                    lambda$414328099$material3 = function9;
                }
                if (i9 != 0) {
                    function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                } else {
                    function2M318getLambda$1514016380$material3 = function10;
                }
                if (i11 != 0) {
                    iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                } else {
                    iM472getEndERTFSPs = i;
                }
                if ((i3 & 64) != 0) {
                    i4 &= -3670017;
                    background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                } else {
                    background = j;
                }
                if ((i3 & 128) != 0) {
                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                    i4 &= -29360129;
                } else {
                    jM278contentColorForek8zF_U = j2;
                }
                if ((i3 & 256) != 0) {
                    contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                    i4 &= -234881025;
                } else {
                    contentWindowInsets = windowInsets;
                }
                j5 = jM278contentColorForek8zF_U;
            } else {
                if (i17 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if (i18 != 0) {
                    function2M319getLambda$39202156$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m319getLambda$39202156$material3();
                } else {
                    function2M319getLambda$39202156$material3 = function7;
                }
                if (i5 != 0) {
                    lambda$1582488484$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$1582488484$material3();
                } else {
                    lambda$1582488484$material3 = function8;
                }
                if (i7 != 0) {
                    lambda$414328099$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.getLambda$414328099$material3();
                } else {
                    lambda$414328099$material3 = function9;
                }
                if (i9 != 0) {
                    function2M318getLambda$1514016380$material3 = ComposableSingletons$ScaffoldKt.INSTANCE.m318getLambda$1514016380$material3();
                } else {
                    function2M318getLambda$1514016380$material3 = function10;
                }
                if (i11 != 0) {
                    iM472getEndERTFSPs = FabPosition.INSTANCE.m472getEndERTFSPs();
                } else {
                    iM472getEndERTFSPs = i;
                }
                if ((i3 & 64) != 0) {
                    i4 &= -3670017;
                    background = MaterialTheme.INSTANCE.getColorScheme(composerStartRestartGroup, 6).getBackground();
                } else {
                    background = j;
                }
                if ((i3 & 128) != 0) {
                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(background, composerStartRestartGroup, (i4 >> 18) & 14);
                    i4 &= -29360129;
                } else {
                    jM278contentColorForek8zF_U = j2;
                }
                if ((i3 & 256) != 0) {
                    contentWindowInsets = ScaffoldDefaults.INSTANCE.getContentWindowInsets(composerStartRestartGroup, 6);
                    i4 &= -234881025;
                } else {
                    contentWindowInsets = windowInsets;
                }
                j5 = jM278contentColorForek8zF_U;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1211482744, i4, -1, "androidx.compose.material3.Scaffold (Scaffold.kt:93)");
            }
            int i111110 = (234881024 & i4) ^ r19;
            if (i111110 <= 67108864) {
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2) {
                objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new MutableWindowInsets(contentWindowInsets);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            mutableWindowInsets = (MutableWindowInsets) objRememberedValue;
            long j1117 = background;
            zChanged = composerStartRestartGroup.changed(mutableWindowInsets) | ((i111110 <= 67108864 && composerStartRestartGroup.changed(contentWindowInsets)) || (i4 & 100663296) == 67108864);
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged) {
                objRememberedValue2 = new Function1() { // from class: wuc
                    public final Object invoke(Object obj) {
                        return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = new Function1() { // from class: wuc
                    public final Object invoke(Object obj) {
                        return ScaffoldKt.b(mutableWindowInsets, contentWindowInsets, (WindowInsets) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final Function2<? super Composer, ? super Integer, Unit> function11111111111119 = function2M319getLambda$39202156$material3;
            final Function2<? super Composer, ? super Integer, Unit> function111111111111110 = lambda$1582488484$material3;
            final Function2<? super Composer, ? super Integer, Unit> function111111111111111 = lambda$414328099$material3;
            final Function2<? super Composer, ? super Integer, Unit> function111111111111112 = function2M318getLambda$1514016380$material3;
            final int i21111112 = iM472getEndERTFSPs;
            int i21111113 = i4 >> 12;
            composer2 = composerStartRestartGroup;
            SurfaceKt.m954SurfaceT9BRK9s(WindowInsetsPaddingKt.onConsumedWindowInsetsChanged(modifier3, (Function1) objRememberedValue2), null, j1117, j5, 0.0f, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(848889571, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$Scaffold$2
                public final void invoke(Composer composer3, int i21111114) {
                    if (!composer3.shouldExecute((i21111114 & 3) != 2, i21111114 & 1)) {
                        composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(848889571, i21111114, -1, "androidx.compose.material3.Scaffold.<anonymous> (Scaffold.kt:104)");
                    }
                    ScaffoldKt.m786ScaffoldLayoutFMILGgc(i21111112, function11111111111119, function6, function111111111111111, function111111111111112, mutableWindowInsets, function111111111111110, composer3, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((Composer) obj, ((Number) obj2).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), composer2, (i21111113 & 896) | 12582912 | (i21111113 & V4Signature.MAX_SIGNING_INFOS_SIZE), 114);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            function11 = function2M319getLambda$39202156$material3;
            function12 = lambda$1582488484$material3;
            function13 = lambda$414328099$material3;
            function14 = function2M318getLambda$1514016380$material3;
            i14 = iM472getEndERTFSPs;
            windowInsets2 = contentWindowInsets;
            j3 = j1117;
            j4 = j5;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier2 = modifier;
            windowInsets2 = windowInsets;
            function11 = function7;
            function12 = function8;
            function13 = function9;
            function14 = function10;
            i14 = i;
            j3 = j;
            j4 = j2;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xuc
                public final Object invoke(Object obj, Object obj2) {
                    return ScaffoldKt.c(modifier2, function11, function12, function13, function14, i14, j3, j4, windowInsets2, function6, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ScaffoldLayout-FMILGgc, reason: not valid java name */
    public static final void m786ScaffoldLayoutFMILGgc(final int i, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function4, final Function2<? super Composer, ? super Integer, Unit> function5, final WindowInsets windowInsets, final Function2<? super Composer, ? super Integer, Unit> function6, Composer composer, final int i2) {
        int i3;
        int i4;
        int i5;
        Composer composerStartRestartGroup = composer.startRestartGroup(-280287501);
        if ((i2 & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(i) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function3) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function4) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function5) ? 16384 : 8192;
        }
        if ((196608 & i2) == 0) {
            i3 |= composerStartRestartGroup.changed(windowInsets) ? 131072 : 65536;
        }
        if ((i2 & 1572864) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function6) ? 1048576 : 524288;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 599187) != 599186, i3 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-280287501, i3, -1, "androidx.compose.material3.ScaffoldLayout (Scaffold.kt:137)");
            }
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new ScaffoldKt$ScaffoldLayout$contentPadding$1$1();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final ScaffoldKt$ScaffoldLayout$contentPadding$1$1 scaffoldKt$ScaffoldLayout$contentPadding$1$1 = (ScaffoldKt$ScaffoldLayout$contentPadding$1$1) objRememberedValue;
            boolean z = (i3 & 112) == 32;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = ComposableLambdaKt.composableLambdaInstance(605195056, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$topBarContent$1$1
                    public final void invoke(Composer composer2, int i6) {
                        if (!composer2.shouldExecute((i6 & 3) != 2, i6 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(605195056, i6, -1, "androidx.compose.material3.ScaffoldLayout.<anonymous>.<anonymous> (Scaffold.kt:158)");
                        }
                        Function2<Composer, Integer, Unit> function7 = function2;
                        Modifier.Companion companion2 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, companion2);
                        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor = companion3.getConstructor();
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion3.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        function7.invoke(composer2, 0);
                        composer2.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            final Function2 function7 = (Function2) objRememberedValue2;
            boolean z2 = (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048;
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z2 || objRememberedValue3 == companion.getEmpty()) {
                objRememberedValue3 = ComposableLambdaKt.composableLambdaInstance(418899191, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$snackbarContent$1$1
                    public final void invoke(Composer composer2, int i6) {
                        if (!composer2.shouldExecute((i6 & 3) != 2, i6 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(418899191, i6, -1, "androidx.compose.material3.ScaffoldLayout.<anonymous>.<anonymous> (Scaffold.kt:159)");
                        }
                        Function2<Composer, Integer, Unit> function8 = function4;
                        Modifier.Companion companion2 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, companion2);
                        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor = companion3.getConstructor();
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion3.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        function8.invoke(composer2, 0);
                        composer2.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            final Function2 function8 = (Function2) objRememberedValue3;
            boolean z3 = (57344 & i3) == 16384;
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (z3 || objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = ComposableLambdaKt.composableLambdaInstance(338600263, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$fabContent$1$1
                    public final void invoke(Composer composer2, int i6) {
                        if (!composer2.shouldExecute((i6 & 3) != 2, i6 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(338600263, i6, -1, "androidx.compose.material3.ScaffoldLayout.<anonymous>.<anonymous> (Scaffold.kt:160)");
                        }
                        Function2<Composer, Integer, Unit> function9 = function5;
                        Modifier.Companion companion2 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, companion2);
                        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor = companion3.getConstructor();
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion3.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        function9.invoke(composer2, 0);
                        composer2.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            final Function2 function9 = (Function2) objRememberedValue4;
            boolean z4 = (i3 & 896) == 256;
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (z4 || objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = ComposableLambdaKt.composableLambdaInstance(-1776388365, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$bodyContent$1$1
                    public final void invoke(Composer composer2, int i6) {
                        if (!composer2.shouldExecute((i6 & 3) != 2, i6 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1776388365, i6, -1, "androidx.compose.material3.ScaffoldLayout.<anonymous>.<anonymous> (Scaffold.kt:162)");
                        }
                        Function3<PaddingValues, Composer, Integer, Unit> function10 = function3;
                        ScaffoldKt$ScaffoldLayout$contentPadding$1$1 scaffoldKt$ScaffoldLayout$contentPadding$1$2 = scaffoldKt$ScaffoldLayout$contentPadding$1$1;
                        Modifier.Companion companion2 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, companion2);
                        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor = companion3.getConstructor();
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion3.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        function10.invoke(scaffoldKt$ScaffoldLayout$contentPadding$1$2, composer2, 6);
                        composer2.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            final Function2 function10 = (Function2) objRememberedValue5;
            boolean z5 = (3670016 & i3) == 1048576;
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue6 == companion.getEmpty()) {
                objRememberedValue6 = ComposableLambdaKt.composableLambdaInstance(-1731662488, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$bottomBarContent$1$1
                    public final void invoke(Composer composer2, int i6) {
                        if (!composer2.shouldExecute((i6 & 3) != 2, i6 & 1)) {
                            composer2.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1731662488, i6, -1, "androidx.compose.material3.ScaffoldLayout.<anonymous>.<anonymous> (Scaffold.kt:163)");
                        }
                        Function2<Composer, Integer, Unit> function11 = function6;
                        Modifier.Companion companion2 = Modifier.INSTANCE;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
                        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap = composer2.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer2, companion2);
                        ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
                        Function0<ComposeUiNode> constructor = companion3.getConstructor();
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
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion3.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion3.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        function11.invoke(composer2, 0);
                        composer2.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        invoke((Composer) obj, ((Number) obj2).intValue());
                        return Unit.INSTANCE;
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objRememberedValue6);
            }
            final Function2 function11 = (Function2) objRememberedValue6;
            boolean zChanged = ((458752 & i3) == 131072) | composerStartRestartGroup.changed(function7) | composerStartRestartGroup.changed(function8) | composerStartRestartGroup.changed(function9) | ((i3 & 14) == 4) | composerStartRestartGroup.changed(function11) | composerStartRestartGroup.changed(function10);
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue7 == companion.getEmpty()) {
                i4 = 1;
                i5 = 0;
                Function2 function12 = new Function2() { // from class: yuc
                    public final Object invoke(Object obj, Object obj2) {
                        return ScaffoldKt.e(windowInsets, function7, function8, function9, i, function11, scaffoldKt$ScaffoldLayout$contentPadding$1$1, function10, (SubcomposeMeasureScope) obj, (Constraints) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(function12);
                objRememberedValue7 = function12;
            } else {
                i5 = 0;
                i4 = 1;
            }
            SubcomposeLayoutKt.SubcomposeLayout(null, (Function2) objRememberedValue7, composerStartRestartGroup, i5, i4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zuc
                public final Object invoke(Object obj, Object obj2) {
                    return ScaffoldKt.a(i, function2, function3, function4, function5, windowInsets, function6, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(int i, Function2 function2, Function3 function3, Function2 function4, Function2 function5, WindowInsets windowInsets, Function2 function6, int i2, Composer composer, int i3) {
        m786ScaffoldLayoutFMILGgc(i, function2, function3, function4, function5, windowInsets, function6, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    public static Unit b(MutableWindowInsets mutableWindowInsets, WindowInsets windowInsets, WindowInsets windowInsets2) {
        mutableWindowInsets.setInsets(WindowInsetsKt.exclude(windowInsets, windowInsets2));
        return Unit.INSTANCE;
    }

    public static Unit c(Modifier modifier, Function2 function2, Function2 function3, Function2 function4, Function2 function5, int i, long j, long j2, WindowInsets windowInsets, Function3 function6, int i2, int i3, Composer composer, int i4) {
        m785ScaffoldTvnljyQ(modifier, function2, function3, function4, function5, i, j, j2, windowInsets, function6, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static Unit d(Placeable placeable, Placeable placeable2, Placeable placeable3, int i, WindowInsets windowInsets, SubcomposeMeasureScope subcomposeMeasureScope, int i2, int i3, Placeable placeable4, FabPlacement fabPlacement, Placeable placeable5, Integer num, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, null);
        Placeable.PlacementScope.place$default(placementScope, placeable2, 0, 0, 0.0f, 4, null);
        Placeable.PlacementScope.place$default(placementScope, placeable3, (((i - placeable3.getWidth()) + windowInsets.getLeft(subcomposeMeasureScope, subcomposeMeasureScope.getLayoutDirection())) - windowInsets.getRight(subcomposeMeasureScope, subcomposeMeasureScope.getLayoutDirection())) / 2, i2 - i3, 0.0f, 4, null);
        Placeable.PlacementScope.place$default(placementScope, placeable4, 0, i2 - placeable4.getHeight(), 0.0f, 4, null);
        if (fabPlacement != null) {
            int left = fabPlacement.getLeft();
            num.getClass();
            Placeable.PlacementScope.place$default(placementScope, placeable5, left, i2 - num.intValue(), 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }

    public static MeasureResult e(final WindowInsets windowInsets, Function2 function2, Function2 function3, Function2 function4, int i, Function2 function5, ScaffoldKt$ScaffoldLayout$contentPadding$1$1 scaffoldKt$ScaffoldLayout$contentPadding$1$1, Function2 function6, final SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
        int iMo4551roundToPx0680j_4;
        int iMo4551roundToPx0680j_5;
        int i2;
        FabPlacement fabPlacement;
        Integer numValueOf;
        int iIntValue;
        int height;
        int bottom;
        final int iM5975getMaxWidthimpl = Constraints.m5975getMaxWidthimpl(constraints.getValue());
        final int iM5974getMaxHeightimpl = Constraints.m5974getMaxHeightimpl(constraints.getValue());
        long jM5965copyZbe2FdA$default = Constraints.m5965copyZbe2FdA$default(constraints.getValue(), 0, 0, 0, 0, 10, null);
        int left = windowInsets.getLeft(subcomposeMeasureScope, subcomposeMeasureScope.getLayoutDirection());
        int right = windowInsets.getRight(subcomposeMeasureScope, subcomposeMeasureScope.getLayoutDirection());
        int bottom2 = windowInsets.getBottom(subcomposeMeasureScope);
        final Placeable placeableMo4605measureBRTryo0 = ((Measurable) CollectionsKt.first(subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.TopBar, function2))).mo4605measureBRTryo0(jM5965copyZbe2FdA$default);
        int i3 = (-left) - right;
        int i4 = -bottom2;
        final Placeable placeableMo4605measureBRTryo1 = ((Measurable) CollectionsKt.first(subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.Snackbar, function3))).mo4605measureBRTryo0(ConstraintsKt.m5994offsetNN6EwU(jM5965copyZbe2FdA$default, i3, i4));
        final Placeable placeableMo4605measureBRTryo2 = ((Measurable) CollectionsKt.first(subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.Fab, function4))).mo4605measureBRTryo0(ConstraintsKt.m5994offsetNN6EwU(jM5965copyZbe2FdA$default, i3, i4));
        if (placeableMo4605measureBRTryo2.getWidth() == 0 && placeableMo4605measureBRTryo2.getHeight() == 0) {
            fabPlacement = null;
        } else {
            int width = placeableMo4605measureBRTryo2.getWidth();
            int height2 = placeableMo4605measureBRTryo2.getHeight();
            FabPosition.Companion companion = FabPosition.INSTANCE;
            if (FabPosition.m467equalsimpl0(i, companion.m474getStartERTFSPs())) {
                if (subcomposeMeasureScope.getLayoutDirection() == LayoutDirection.Ltr) {
                    iMo4551roundToPx0680j_4 = subcomposeMeasureScope.mo4551roundToPx0680j_4(FabSpacing);
                    i2 = iMo4551roundToPx0680j_4 + left;
                } else {
                    iMo4551roundToPx0680j_5 = subcomposeMeasureScope.mo4551roundToPx0680j_4(FabSpacing);
                    i2 = ((iM5975getMaxWidthimpl - iMo4551roundToPx0680j_5) - width) - right;
                }
            } else if (!FabPosition.m467equalsimpl0(i, companion.m472getEndERTFSPs()) && !FabPosition.m467equalsimpl0(i, companion.m473getEndOverlayERTFSPs())) {
                i2 = (((iM5975getMaxWidthimpl - width) + left) - right) / 2;
            } else if (subcomposeMeasureScope.getLayoutDirection() == LayoutDirection.Ltr) {
                iMo4551roundToPx0680j_5 = subcomposeMeasureScope.mo4551roundToPx0680j_4(FabSpacing);
                i2 = ((iM5975getMaxWidthimpl - iMo4551roundToPx0680j_5) - width) - right;
            } else {
                iMo4551roundToPx0680j_4 = subcomposeMeasureScope.mo4551roundToPx0680j_4(FabSpacing);
                i2 = iMo4551roundToPx0680j_4 + left;
            }
            fabPlacement = new FabPlacement(i2, width, height2);
        }
        final Placeable placeableMo4605measureBRTryo3 = ((Measurable) CollectionsKt.first(subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.BottomBar, function5))).mo4605measureBRTryo0(jM5965copyZbe2FdA$default);
        int i5 = 0;
        boolean z = placeableMo4605measureBRTryo3.getWidth() == 0 && placeableMo4605measureBRTryo3.getHeight() == 0;
        if (fabPlacement != null) {
            if (z || FabPosition.m467equalsimpl0(i, FabPosition.INSTANCE.m473getEndOverlayERTFSPs())) {
                height = fabPlacement.getHeight() + subcomposeMeasureScope.mo4551roundToPx0680j_4(FabSpacing);
                bottom = windowInsets.getBottom(subcomposeMeasureScope);
            } else {
                height = placeableMo4605measureBRTryo3.getHeight() + fabPlacement.getHeight();
                bottom = subcomposeMeasureScope.mo4551roundToPx0680j_4(FabSpacing);
            }
            numValueOf = Integer.valueOf(height + bottom);
        } else {
            numValueOf = null;
        }
        int height3 = placeableMo4605measureBRTryo1.getHeight();
        if (height3 != 0) {
            if (numValueOf != null) {
                iIntValue = numValueOf.intValue();
            } else {
                Integer numValueOf2 = Integer.valueOf(placeableMo4605measureBRTryo3.getHeight());
                if (z) {
                    numValueOf2 = null;
                }
                iIntValue = numValueOf2 != null ? numValueOf2.intValue() : windowInsets.getBottom(subcomposeMeasureScope);
            }
            i5 = iIntValue + height3;
        }
        PaddingValues paddingValuesAsPaddingValues = WindowInsetsKt.asPaddingValues(windowInsets, subcomposeMeasureScope);
        final Integer num = numValueOf;
        final FabPlacement fabPlacement2 = fabPlacement;
        scaffoldKt$ScaffoldLayout$contentPadding$1$1.setPaddingHolder(PaddingKt.PaddingValues-a9UjIt4(PaddingKt.calculateStartPadding(paddingValuesAsPaddingValues, subcomposeMeasureScope.getLayoutDirection()), (placeableMo4605measureBRTryo0.getWidth() == 0 && placeableMo4605measureBRTryo0.getHeight() == 0) ? paddingValuesAsPaddingValues.calculateTopPadding-D9Ej5fM() : subcomposeMeasureScope.mo4554toDpu2uoSUM(placeableMo4605measureBRTryo0.getHeight()), PaddingKt.calculateEndPadding(paddingValuesAsPaddingValues, subcomposeMeasureScope.getLayoutDirection()), z ? paddingValuesAsPaddingValues.calculateBottomPadding-D9Ej5fM() : subcomposeMeasureScope.mo4554toDpu2uoSUM(placeableMo4605measureBRTryo3.getHeight())));
        final Placeable placeableMo4605measureBRTryo4 = ((Measurable) CollectionsKt.first(subcomposeMeasureScope.subcompose(ScaffoldLayoutContent.MainContent, function6))).mo4605measureBRTryo0(jM5965copyZbe2FdA$default);
        final int i6 = i5;
        return MeasureScope.layout$default(subcomposeMeasureScope, iM5975getMaxWidthimpl, iM5974getMaxHeightimpl, null, new Function1() { // from class: avc
            public final Object invoke(Object obj) {
                return ScaffoldKt.d(placeableMo4605measureBRTryo4, placeableMo4605measureBRTryo0, placeableMo4605measureBRTryo1, iM5975getMaxWidthimpl, windowInsets, subcomposeMeasureScope, iM5974getMaxHeightimpl, i6, placeableMo4605measureBRTryo3, fabPlacement2, placeableMo4605measureBRTryo2, num, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }
}
