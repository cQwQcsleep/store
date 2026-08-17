package androidx.compose.material3;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.material3.ModalBottomSheet_androidKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import java.util.UUID;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000l\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a¶\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\r2\u0015\b\u0002\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\u001c\u0010\u0017\u001a\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u00010\u0018¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u001aH\u0007¢\u0006\u0004\b\u001b\u0010\u001c\u001aT\u0010\u001d\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u00162\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001f2\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0012H\u0001¢\u0006\u0004\b\"\u0010#\u001a\f\u0010$\u001a\u00020%*\u00020&H\u0000\u001a\u0013\u0010'\u001a\u00020%*\u00020\rH\u0000¢\u0006\u0004\b(\u0010)¨\u0006*²\u0006\u0015\u0010+\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0012X\u008a\u0084\u0002"}, d2 = {"ModalBottomSheet", "", "onDismissRequest", "Lkotlin/Function0;", "modifier", "Landroidx/compose/ui/Modifier;", "sheetState", "Landroidx/compose/material3/SheetState;", "sheetMaxWidth", "Landroidx/compose/ui/unit/Dp;", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "tonalElevation", "scrimColor", "dragHandle", "Landroidx/compose/runtime/Composable;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "properties", "Landroidx/compose/material3/ModalBottomSheetProperties;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "ModalBottomSheet-dYc4hso", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/SheetState;FLandroidx/compose/ui/graphics/Shape;JJFJLkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/ModalBottomSheetProperties;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "ModalBottomSheetDialog", "predictiveBackProgress", "Landroidx/compose/animation/core/Animatable;", "", "Landroidx/compose/animation/core/AnimationVector1D;", "ModalBottomSheetDialog-sW7UJKQ", "(Lkotlin/jvm/functions/Function0;JLandroidx/compose/material3/ModalBottomSheetProperties;Landroidx/compose/animation/core/Animatable;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "isFlagSecureEnabled", "", "Landroid/view/View;", "isDark", "isDark-8_81llA", "(J)Z", "material3", "currentContent"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ModalBottomSheet_androidKt {
    /* JADX WARN: Code duplicated, block: B:101:0x011a  */
    /* JADX WARN: Code duplicated, block: B:103:0x011e  */
    /* JADX WARN: Code duplicated, block: B:105:0x0128  */
    /* JADX WARN: Code duplicated, block: B:106:0x012b  */
    /* JADX WARN: Code duplicated, block: B:110:0x0133  */
    /* JADX WARN: Code duplicated, block: B:112:0x0137  */
    /* JADX WARN: Code duplicated, block: B:115:0x0142 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:118:0x0149  */
    /* JADX WARN: Code duplicated, block: B:121:0x0151  */
    /* JADX WARN: Code duplicated, block: B:123:0x0158  */
    /* JADX WARN: Code duplicated, block: B:125:0x015c  */
    /* JADX WARN: Code duplicated, block: B:127:0x0166  */
    /* JADX WARN: Code duplicated, block: B:128:0x0169  */
    /* JADX WARN: Code duplicated, block: B:130:0x016e  */
    /* JADX WARN: Code duplicated, block: B:133:0x0179  */
    /* JADX WARN: Code duplicated, block: B:135:0x017e  */
    /* JADX WARN: Code duplicated, block: B:137:0x0182  */
    /* JADX WARN: Code duplicated, block: B:139:0x018a  */
    /* JADX WARN: Code duplicated, block: B:140:0x018d  */
    /* JADX WARN: Code duplicated, block: B:144:0x019c  */
    /* JADX WARN: Code duplicated, block: B:148:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:151:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:153:0x01c2  */
    /* JADX WARN: Code duplicated, block: B:175:0x0203 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:176:0x0205  */
    /* JADX WARN: Code duplicated, block: B:179:0x020c  */
    /* JADX WARN: Code duplicated, block: B:181:0x0216  */
    /* JADX WARN: Code duplicated, block: B:182:0x021d  */
    /* JADX WARN: Code duplicated, block: B:185:0x0224  */
    /* JADX WARN: Code duplicated, block: B:186:0x022d  */
    /* JADX WARN: Code duplicated, block: B:189:0x0232  */
    /* JADX WARN: Code duplicated, block: B:192:0x023e  */
    /* JADX WARN: Code duplicated, block: B:194:0x0249  */
    /* JADX WARN: Code duplicated, block: B:195:0x024f  */
    /* JADX WARN: Code duplicated, block: B:198:0x0255  */
    /* JADX WARN: Code duplicated, block: B:199:0x025f  */
    /* JADX WARN: Code duplicated, block: B:201:0x0263  */
    /* JADX WARN: Code duplicated, block: B:202:0x026a  */
    /* JADX WARN: Code duplicated, block: B:205:0x0272  */
    /* JADX WARN: Code duplicated, block: B:206:0x027b  */
    /* JADX WARN: Code duplicated, block: B:208:0x027f  */
    /* JADX WARN: Code duplicated, block: B:210:0x0297  */
    /* JADX WARN: Code duplicated, block: B:213:0x02a3  */
    /* JADX WARN: Code duplicated, block: B:216:0x02f3  */
    /* JADX WARN: Code duplicated, block: B:218:0x0307  */
    /* JADX WARN: Code duplicated, block: B:221:0x0323  */
    /* JADX WARN: Code duplicated, block: B:223:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004c  */
    /* JADX WARN: Code duplicated, block: B:28:0x0050  */
    /* JADX WARN: Code duplicated, block: B:30:0x0058  */
    /* JADX WARN: Code duplicated, block: B:31:0x005b  */
    /* JADX WARN: Code duplicated, block: B:34:0x0062  */
    /* JADX WARN: Code duplicated, block: B:37:0x0068  */
    /* JADX WARN: Code duplicated, block: B:39:0x006d  */
    /* JADX WARN: Code duplicated, block: B:41:0x0071  */
    /* JADX WARN: Code duplicated, block: B:43:0x0079  */
    /* JADX WARN: Code duplicated, block: B:44:0x007c  */
    /* JADX WARN: Code duplicated, block: B:48:0x0084  */
    /* JADX WARN: Code duplicated, block: B:50:0x0088  */
    /* JADX WARN: Code duplicated, block: B:52:0x0090  */
    /* JADX WARN: Code duplicated, block: B:53:0x0093  */
    /* JADX WARN: Code duplicated, block: B:56:0x009a  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:66:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:69:0x00be  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:74:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d2  */
    /* JADX WARN: Code duplicated, block: B:79:0x00da  */
    /* JADX WARN: Code duplicated, block: B:80:0x00df  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:84:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:85:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:89:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:91:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:94:0x0107  */
    /* JADX WARN: Code duplicated, block: B:96:0x010b  */
    /* JADX WARN: Code duplicated, block: B:99:0x0113  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use constructor with contentWindowInsets parameter.", replaceWith = @ReplaceWith(expression = "ModalBottomSheet(onDismissRequest,modifier,sheetState,sheetMaxWidth,shape,containerColor,contentColor,tonalElevation,scrimColor,dragHandle,{ windowInsets },properties,content,)", imports = {}))
    /* JADX INFO: renamed from: ModalBottomSheet-dYc4hso, reason: not valid java name */
    public static final /* synthetic */ void m635ModalBottomSheetdYc4hso(final Function0 function0, Modifier modifier, SheetState sheetState, float f, Shape shape, long j, long j2, float f2, long j3, Function2 function2, WindowInsets windowInsets, ModalBottomSheetProperties modalBottomSheetProperties, final Function3 function3, Composer composer, final int i, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        SheetState sheetStateRememberModalBottomSheetState;
        int i5;
        int i6;
        Shape shape2;
        long containerColor;
        long jM278contentColorForek8zF_U;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        boolean z;
        Composer composer2;
        final float f3;
        final Shape shape3;
        final long j4;
        final Modifier modifier3;
        final SheetState sheetState2;
        final long j5;
        final float f4;
        final long j6;
        final Function2 function4;
        final WindowInsets windowInsets2;
        final ModalBottomSheetProperties modalBottomSheetProperties2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        float fM121getSheetMaxWidthD9Ej5fM;
        Shape expandedShape;
        float fM6022constructorimpl;
        long scrimColor;
        Function2 function2M316getLambda$1294623166$material3;
        final WindowInsets windowInsets3;
        ModalBottomSheetProperties properties;
        Shape shape4;
        Modifier modifier4;
        float f5;
        float f6;
        SheetState sheetState3;
        long j7;
        long j8;
        Function2 function5;
        int i16;
        int i17;
        int i18;
        Composer composerStartRestartGroup = composer.startRestartGroup(1342054200);
        if ((i3 & 1) != 0) {
            i4 = i | 6;
        } else if ((i & 6) == 0) {
            i4 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i4 = i;
        }
        int i19 = i3 & 2;
        if (i19 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i3 & 4) == 0) {
                    sheetStateRememberModalBottomSheetState = sheetState;
                    int i20 = composerStartRestartGroup.changed(sheetStateRememberModalBottomSheetState) ? 256 : 128;
                    i4 |= i20;
                } else {
                    sheetStateRememberModalBottomSheetState = sheetState;
                }
                i4 |= i20;
            } else {
                sheetStateRememberModalBottomSheetState = sheetState;
            }
            i5 = i3 & 8;
            if (i5 != 0) {
                if ((i & 3072) == 0) {
                    if (composerStartRestartGroup.changed(f)) {
                        i6 = 2048;
                    } else {
                        i6 = 1024;
                    }
                    i4 |= i6;
                }
                if ((i & 24576) == 0) {
                    if ((i3 & 16) == 0) {
                        shape2 = shape;
                        int i21 = composerStartRestartGroup.changed(shape2) ? 16384 : 8192;
                        i4 |= i21;
                    } else {
                        shape2 = shape;
                    }
                    i4 |= i21;
                } else {
                    shape2 = shape;
                }
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    containerColor = j;
                    if ((i3 & 32) == 0 || !composerStartRestartGroup.changed(containerColor)) {
                        i18 = 65536;
                    } else {
                        i18 = 131072;
                    }
                    i4 |= i18;
                } else {
                    containerColor = j;
                }
                if ((i & 1572864) == 0) {
                    jM278contentColorForek8zF_U = j2;
                    if ((i3 & 64) == 0 || !composerStartRestartGroup.changed(jM278contentColorForek8zF_U)) {
                        i17 = 524288;
                    } else {
                        i17 = 1048576;
                    }
                    i4 |= i17;
                } else {
                    jM278contentColorForek8zF_U = j2;
                }
                i7 = i3 & 128;
                if (i7 != 0) {
                    i4 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(f2)) {
                        i8 = 8388608;
                    } else {
                        i8 = 4194304;
                    }
                    i4 |= i8;
                }
                if ((i & 100663296) != 0) {
                    if ((i3 & 256) == 0 || !composerStartRestartGroup.changed(j3)) {
                        i16 = 33554432;
                    } else {
                        i16 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    }
                    i4 |= i16;
                }
                i9 = i3 & 512;
                if (i9 != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i10 = 536870912;
                        } else {
                            i10 = 268435456;
                        }
                        i4 |= i10;
                    }
                    if ((i2 & 6) == 0) {
                        i11 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 2 : 4);
                    } else {
                        i11 = i2;
                    }
                    i12 = i3 & 2048;
                    if (i12 != 0) {
                        i11 |= 48;
                    } else if ((i2 & 48) != 0) {
                        if (composerStartRestartGroup.changed(modalBottomSheetProperties)) {
                            i13 = 32;
                        } else {
                            i13 = 16;
                        }
                        i11 |= i13;
                    }
                    i14 = i11;
                    if ((i3 & 4096) != 0) {
                        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                            if (composerStartRestartGroup.changedInstance(function3)) {
                                i15 = 256;
                            } else {
                                i15 = 128;
                            }
                            i14 |= i15;
                        }
                        if ((i4 & 306783379) == 306783378 || (i14 & 147) != 146) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                if (i19 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if ((i3 & 4) != 0) {
                                    i4 &= -897;
                                    sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                                }
                                if (i5 != 0) {
                                    fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                                } else {
                                    fM121getSheetMaxWidthD9Ej5fM = f;
                                }
                                if ((i3 & 16) != 0) {
                                    expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                    i4 &= -57345;
                                } else {
                                    expandedShape = shape2;
                                }
                                if ((i3 & 32) != 0) {
                                    containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -458753;
                                }
                                if ((i3 & 64) != 0) {
                                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                    i4 &= -3670017;
                                }
                                if (i7 != 0) {
                                    fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                                } else {
                                    fM6022constructorimpl = f2;
                                }
                                if ((i3 & 256) != 0) {
                                    scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    scrimColor = j3;
                                }
                                if (i9 != 0) {
                                    function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                                } else {
                                    function2M316getLambda$1294623166$material3 = function2;
                                }
                                if ((i3 & 1024) != 0) {
                                    windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                    i14 &= -15;
                                } else {
                                    windowInsets3 = windowInsets;
                                }
                                if (i12 != 0) {
                                    properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                                } else {
                                    properties = modalBottomSheetProperties;
                                }
                                shape4 = expandedShape;
                                modifier4 = modifier2;
                                Function2 function6 = function2M316getLambda$1294623166$material3;
                                f5 = fM121getSheetMaxWidthD9Ej5fM;
                                long j9 = jM278contentColorForek8zF_U;
                                f6 = fM6022constructorimpl;
                                sheetState3 = sheetStateRememberModalBottomSheetState;
                                j7 = j9;
                                j8 = scrimColor;
                                function5 = function6;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                if ((i3 & 4) != 0) {
                                    i4 &= -897;
                                }
                                if ((i3 & 16) != 0) {
                                    i4 &= -57345;
                                }
                                if ((i3 & 32) != 0) {
                                    i4 &= -458753;
                                }
                                if ((i3 & 64) != 0) {
                                    i4 &= -3670017;
                                }
                                if ((i3 & 256) != 0) {
                                    i4 &= -234881025;
                                }
                                if ((i3 & 1024) != 0) {
                                    i14 &= -15;
                                }
                                function5 = function2;
                                properties = modalBottomSheetProperties;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                sheetState3 = sheetStateRememberModalBottomSheetState;
                                j7 = jM278contentColorForek8zF_U;
                                f5 = f;
                                f6 = f2;
                                j8 = j3;
                                windowInsets3 = windowInsets;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1342054200, i4, i14, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:343)");
                            }
                            int i22 = i4 << 3;
                            int i23 = i14 << 3;
                            composer2 = composerStartRestartGroup;
                            ModalBottomSheetKt.m630ModalBottomSheetYbuCTN8(function0, modifier4, sheetState3, f5, false, shape4, containerColor, j7, f6, j8, function5, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                                public final WindowInsets invoke(Composer composer3, int i24) {
                                    composer3.startReplaceGroup(-677688734);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-677688734, i24, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:354)");
                                    }
                                    WindowInsets windowInsets4 = windowInsets3;
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    composer3.endReplaceGroup();
                                    return windowInsets4;
                                }

                                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                    return invoke((Composer) obj, ((Number) obj2).intValue());
                                }
                            }, properties, function3, composer2, (i4 & 8190) | (i22 & 458752) | (i22 & 3670016) | (i22 & 29360128) | (i22 & 234881024) | (i22 & 1879048192), ((i4 >> 27) & 14) | (i23 & 896) | (i23 & V4Signature.MAX_SIGNING_INFOS_SIZE), 16);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            f3 = f5;
                            shape3 = shape4;
                            j5 = j7;
                            function4 = function5;
                            float f7 = f6;
                            windowInsets2 = windowInsets3;
                            sheetState2 = sheetState3;
                            j4 = containerColor;
                            f4 = f7;
                            j6 = j8;
                            modalBottomSheetProperties2 = properties;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            f3 = f;
                            shape3 = shape2;
                            j4 = containerColor;
                            modifier3 = modifier2;
                            sheetState2 = sheetStateRememberModalBottomSheetState;
                            j5 = jM278contentColorForek8zF_U;
                            f4 = f2;
                            j6 = j3;
                            function4 = function2;
                            windowInsets2 = windowInsets;
                            modalBottomSheetProperties2 = modalBottomSheetProperties;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a4a
                                public final Object invoke(Object obj, Object obj2) {
                                    return ModalBottomSheet_androidKt.b(function0, modifier3, sheetState2, f3, shape3, j4, j5, f4, j6, function4, windowInsets2, modalBottomSheetProperties2, function3, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i14 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i19 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM121getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            } else {
                                expandedShape = shape2;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            }
                            if ((i3 & 64) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                            } else {
                                fM6022constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                scrimColor = j3;
                            }
                            if (i9 != 0) {
                                function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                            } else {
                                function2M316getLambda$1294623166$material3 = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets3 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            shape4 = expandedShape;
                            modifier4 = modifier2;
                            Function2 function7 = function2M316getLambda$1294623166$material3;
                            f5 = fM121getSheetMaxWidthD9Ej5fM;
                            long j10 = jM278contentColorForek8zF_U;
                            f6 = fM6022constructorimpl;
                            sheetState3 = sheetStateRememberModalBottomSheetState;
                            j7 = j10;
                            j8 = scrimColor;
                            function5 = function7;
                        } else {
                            if (i19 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM121getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            } else {
                                expandedShape = shape2;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            }
                            if ((i3 & 64) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                            } else {
                                fM6022constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                scrimColor = j3;
                            }
                            if (i9 != 0) {
                                function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                            } else {
                                function2M316getLambda$1294623166$material3 = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets3 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            shape4 = expandedShape;
                            modifier4 = modifier2;
                            Function2 function8 = function2M316getLambda$1294623166$material3;
                            f5 = fM121getSheetMaxWidthD9Ej5fM;
                            long j11 = jM278contentColorForek8zF_U;
                            f6 = fM6022constructorimpl;
                            sheetState3 = sheetStateRememberModalBottomSheetState;
                            j7 = j11;
                            j8 = scrimColor;
                            function5 = function8;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1342054200, i4, i14, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:343)");
                        }
                        int i24 = i4 << 3;
                        int i25 = i14 << 3;
                        composer2 = composerStartRestartGroup;
                        ModalBottomSheetKt.m630ModalBottomSheetYbuCTN8(function0, modifier4, sheetState3, f5, false, shape4, containerColor, j7, f6, j8, function5, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                            public final WindowInsets invoke(Composer composer3, int i26) {
                                composer3.startReplaceGroup(-677688734);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-677688734, i26, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:354)");
                                }
                                WindowInsets windowInsets4 = windowInsets3;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                composer3.endReplaceGroup();
                                return windowInsets4;
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                return invoke((Composer) obj, ((Number) obj2).intValue());
                            }
                        }, properties, function3, composer2, (i4 & 8190) | (i24 & 458752) | (i24 & 3670016) | (i24 & 29360128) | (i24 & 234881024) | (i24 & 1879048192), ((i4 >> 27) & 14) | (i25 & 896) | (i25 & V4Signature.MAX_SIGNING_INFOS_SIZE), 16);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        f3 = f5;
                        shape3 = shape4;
                        j5 = j7;
                        function4 = function5;
                        float f8 = f6;
                        windowInsets2 = windowInsets3;
                        sheetState2 = sheetState3;
                        j4 = containerColor;
                        f4 = f8;
                        j6 = j8;
                        modalBottomSheetProperties2 = properties;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        f3 = f;
                        shape3 = shape2;
                        j4 = containerColor;
                        modifier3 = modifier2;
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        j5 = jM278contentColorForek8zF_U;
                        f4 = f2;
                        j6 = j3;
                        function4 = function2;
                        windowInsets2 = windowInsets;
                        modalBottomSheetProperties2 = modalBottomSheetProperties;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a4a
                            public final Object invoke(Object obj, Object obj2) {
                                return ModalBottomSheet_androidKt.b(function0, modifier3, sheetState2, f3, shape3, j4, j5, f4, j6, function4, windowInsets2, modalBottomSheetProperties2, function3, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                if ((i2 & 6) == 0) {
                    i11 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 2 : 4);
                } else {
                    i11 = i2;
                }
                i12 = i3 & 2048;
                if (i12 != 0) {
                    i11 |= 48;
                } else if ((i2 & 48) != 0) {
                    if (composerStartRestartGroup.changed(modalBottomSheetProperties)) {
                        i13 = 32;
                    } else {
                        i13 = 16;
                    }
                    i11 |= i13;
                }
                i14 = i11;
                if ((i3 & 4096) != 0) {
                    if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i15 = 256;
                        } else {
                            i15 = 128;
                        }
                        i14 |= i15;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i19 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM121getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            } else {
                                expandedShape = shape2;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            }
                            if ((i3 & 64) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                            } else {
                                fM6022constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                scrimColor = j3;
                            }
                            if (i9 != 0) {
                                function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                            } else {
                                function2M316getLambda$1294623166$material3 = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets3 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            shape4 = expandedShape;
                            modifier4 = modifier2;
                            Function2 function9 = function2M316getLambda$1294623166$material3;
                            f5 = fM121getSheetMaxWidthD9Ej5fM;
                            long j12 = jM278contentColorForek8zF_U;
                            f6 = fM6022constructorimpl;
                            sheetState3 = sheetStateRememberModalBottomSheetState;
                            j7 = j12;
                            j8 = scrimColor;
                            function5 = function9;
                        } else {
                            if (i19 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM121getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            } else {
                                expandedShape = shape2;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            }
                            if ((i3 & 64) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                            } else {
                                fM6022constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                scrimColor = j3;
                            }
                            if (i9 != 0) {
                                function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                            } else {
                                function2M316getLambda$1294623166$material3 = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets3 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            shape4 = expandedShape;
                            modifier4 = modifier2;
                            Function2 function10 = function2M316getLambda$1294623166$material3;
                            f5 = fM121getSheetMaxWidthD9Ej5fM;
                            long j13 = jM278contentColorForek8zF_U;
                            f6 = fM6022constructorimpl;
                            sheetState3 = sheetStateRememberModalBottomSheetState;
                            j7 = j13;
                            j8 = scrimColor;
                            function5 = function10;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1342054200, i4, i14, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:343)");
                        }
                        int i26 = i4 << 3;
                        int i27 = i14 << 3;
                        composer2 = composerStartRestartGroup;
                        ModalBottomSheetKt.m630ModalBottomSheetYbuCTN8(function0, modifier4, sheetState3, f5, false, shape4, containerColor, j7, f6, j8, function5, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                            public final WindowInsets invoke(Composer composer3, int i28) {
                                composer3.startReplaceGroup(-677688734);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-677688734, i28, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:354)");
                                }
                                WindowInsets windowInsets4 = windowInsets3;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                composer3.endReplaceGroup();
                                return windowInsets4;
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                return invoke((Composer) obj, ((Number) obj2).intValue());
                            }
                        }, properties, function3, composer2, (i4 & 8190) | (i26 & 458752) | (i26 & 3670016) | (i26 & 29360128) | (i26 & 234881024) | (i26 & 1879048192), ((i4 >> 27) & 14) | (i27 & 896) | (i27 & V4Signature.MAX_SIGNING_INFOS_SIZE), 16);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        f3 = f5;
                        shape3 = shape4;
                        j5 = j7;
                        function4 = function5;
                        float f9 = f6;
                        windowInsets2 = windowInsets3;
                        sheetState2 = sheetState3;
                        j4 = containerColor;
                        f4 = f9;
                        j6 = j8;
                        modalBottomSheetProperties2 = properties;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        f3 = f;
                        shape3 = shape2;
                        j4 = containerColor;
                        modifier3 = modifier2;
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        j5 = jM278contentColorForek8zF_U;
                        f4 = f2;
                        j6 = j3;
                        function4 = function2;
                        windowInsets2 = windowInsets;
                        modalBottomSheetProperties2 = modalBottomSheetProperties;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a4a
                            public final Object invoke(Object obj, Object obj2) {
                                return ModalBottomSheet_androidKt.b(function0, modifier3, sheetState2, f3, shape3, j4, j5, f4, j6, function4, windowInsets2, modalBottomSheetProperties2, function3, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i14 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i19 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM121getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        } else {
                            expandedShape = shape2;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            scrimColor = j3;
                        }
                        if (i9 != 0) {
                            function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                        } else {
                            function2M316getLambda$1294623166$material3 = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets3 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        shape4 = expandedShape;
                        modifier4 = modifier2;
                        Function2 function11 = function2M316getLambda$1294623166$material3;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        long j14 = jM278contentColorForek8zF_U;
                        f6 = fM6022constructorimpl;
                        sheetState3 = sheetStateRememberModalBottomSheetState;
                        j7 = j14;
                        j8 = scrimColor;
                        function5 = function11;
                    } else {
                        if (i19 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM121getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        } else {
                            expandedShape = shape2;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            scrimColor = j3;
                        }
                        if (i9 != 0) {
                            function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                        } else {
                            function2M316getLambda$1294623166$material3 = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets3 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        shape4 = expandedShape;
                        modifier4 = modifier2;
                        Function2 function12 = function2M316getLambda$1294623166$material3;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        long j15 = jM278contentColorForek8zF_U;
                        f6 = fM6022constructorimpl;
                        sheetState3 = sheetStateRememberModalBottomSheetState;
                        j7 = j15;
                        j8 = scrimColor;
                        function5 = function12;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1342054200, i4, i14, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:343)");
                    }
                    int i28 = i4 << 3;
                    int i29 = i14 << 3;
                    composer2 = composerStartRestartGroup;
                    ModalBottomSheetKt.m630ModalBottomSheetYbuCTN8(function0, modifier4, sheetState3, f5, false, shape4, containerColor, j7, f6, j8, function5, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                        public final WindowInsets invoke(Composer composer3, int i210) {
                            composer3.startReplaceGroup(-677688734);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-677688734, i210, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:354)");
                            }
                            WindowInsets windowInsets4 = windowInsets3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer3.endReplaceGroup();
                            return windowInsets4;
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            return invoke((Composer) obj, ((Number) obj2).intValue());
                        }
                    }, properties, function3, composer2, (i4 & 8190) | (i28 & 458752) | (i28 & 3670016) | (i28 & 29360128) | (i28 & 234881024) | (i28 & 1879048192), ((i4 >> 27) & 14) | (i29 & 896) | (i29 & V4Signature.MAX_SIGNING_INFOS_SIZE), 16);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    f3 = f5;
                    shape3 = shape4;
                    j5 = j7;
                    function4 = function5;
                    float f10 = f6;
                    windowInsets2 = windowInsets3;
                    sheetState2 = sheetState3;
                    j4 = containerColor;
                    f4 = f10;
                    j6 = j8;
                    modalBottomSheetProperties2 = properties;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    f3 = f;
                    shape3 = shape2;
                    j4 = containerColor;
                    modifier3 = modifier2;
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    j5 = jM278contentColorForek8zF_U;
                    f4 = f2;
                    j6 = j3;
                    function4 = function2;
                    windowInsets2 = windowInsets;
                    modalBottomSheetProperties2 = modalBottomSheetProperties;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a4a
                        public final Object invoke(Object obj, Object obj2) {
                            return ModalBottomSheet_androidKt.b(function0, modifier3, sheetState2, f3, shape3, j4, j5, f4, j6, function4, windowInsets2, modalBottomSheetProperties2, function3, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 3072;
            if ((i & 24576) == 0) {
                if ((i3 & 16) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i4 |= i21;
                } else {
                    shape2 = shape;
                }
                i4 |= i21;
            } else {
                shape2 = shape;
            }
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                containerColor = j;
                if ((i3 & 32) == 0) {
                    i18 = 65536;
                } else {
                    i18 = 65536;
                }
                i4 |= i18;
            } else {
                containerColor = j;
            }
            if ((i & 1572864) == 0) {
                jM278contentColorForek8zF_U = j2;
                if ((i3 & 64) == 0) {
                    i17 = 524288;
                } else {
                    i17 = 524288;
                }
                i4 |= i17;
            } else {
                jM278contentColorForek8zF_U = j2;
            }
            i7 = i3 & 128;
            if (i7 != 0) {
                i4 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(f2)) {
                    i8 = 8388608;
                } else {
                    i8 = 4194304;
                }
                i4 |= i8;
            }
            if ((i & 100663296) != 0) {
                if ((i3 & 256) == 0) {
                    i16 = 33554432;
                } else {
                    i16 = 33554432;
                }
                i4 |= i16;
            }
            i9 = i3 & 512;
            if (i9 != 0) {
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i10 = 536870912;
                    } else {
                        i10 = 268435456;
                    }
                    i4 |= i10;
                }
                if ((i2 & 6) == 0) {
                    i11 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 2 : 4);
                } else {
                    i11 = i2;
                }
                i12 = i3 & 2048;
                if (i12 != 0) {
                    i11 |= 48;
                } else if ((i2 & 48) != 0) {
                    if (composerStartRestartGroup.changed(modalBottomSheetProperties)) {
                        i13 = 32;
                    } else {
                        i13 = 16;
                    }
                    i11 |= i13;
                }
                i14 = i11;
                if ((i3 & 4096) != 0) {
                    if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i15 = 256;
                        } else {
                            i15 = 128;
                        }
                        i14 |= i15;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i19 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM121getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            } else {
                                expandedShape = shape2;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            }
                            if ((i3 & 64) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                            } else {
                                fM6022constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                scrimColor = j3;
                            }
                            if (i9 != 0) {
                                function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                            } else {
                                function2M316getLambda$1294623166$material3 = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets3 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            shape4 = expandedShape;
                            modifier4 = modifier2;
                            Function2 function13 = function2M316getLambda$1294623166$material3;
                            f5 = fM121getSheetMaxWidthD9Ej5fM;
                            long j16 = jM278contentColorForek8zF_U;
                            f6 = fM6022constructorimpl;
                            sheetState3 = sheetStateRememberModalBottomSheetState;
                            j7 = j16;
                            j8 = scrimColor;
                            function5 = function13;
                        } else {
                            if (i19 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM121getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            } else {
                                expandedShape = shape2;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            }
                            if ((i3 & 64) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                            } else {
                                fM6022constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                scrimColor = j3;
                            }
                            if (i9 != 0) {
                                function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                            } else {
                                function2M316getLambda$1294623166$material3 = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets3 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            shape4 = expandedShape;
                            modifier4 = modifier2;
                            Function2 function14 = function2M316getLambda$1294623166$material3;
                            f5 = fM121getSheetMaxWidthD9Ej5fM;
                            long j17 = jM278contentColorForek8zF_U;
                            f6 = fM6022constructorimpl;
                            sheetState3 = sheetStateRememberModalBottomSheetState;
                            j7 = j17;
                            j8 = scrimColor;
                            function5 = function14;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1342054200, i4, i14, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:343)");
                        }
                        int i210 = i4 << 3;
                        int i211 = i14 << 3;
                        composer2 = composerStartRestartGroup;
                        ModalBottomSheetKt.m630ModalBottomSheetYbuCTN8(function0, modifier4, sheetState3, f5, false, shape4, containerColor, j7, f6, j8, function5, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                            public final WindowInsets invoke(Composer composer3, int i212) {
                                composer3.startReplaceGroup(-677688734);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-677688734, i212, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:354)");
                                }
                                WindowInsets windowInsets4 = windowInsets3;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                composer3.endReplaceGroup();
                                return windowInsets4;
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                return invoke((Composer) obj, ((Number) obj2).intValue());
                            }
                        }, properties, function3, composer2, (i4 & 8190) | (i210 & 458752) | (i210 & 3670016) | (i210 & 29360128) | (i210 & 234881024) | (i210 & 1879048192), ((i4 >> 27) & 14) | (i211 & 896) | (i211 & V4Signature.MAX_SIGNING_INFOS_SIZE), 16);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        f3 = f5;
                        shape3 = shape4;
                        j5 = j7;
                        function4 = function5;
                        float f11 = f6;
                        windowInsets2 = windowInsets3;
                        sheetState2 = sheetState3;
                        j4 = containerColor;
                        f4 = f11;
                        j6 = j8;
                        modalBottomSheetProperties2 = properties;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        f3 = f;
                        shape3 = shape2;
                        j4 = containerColor;
                        modifier3 = modifier2;
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        j5 = jM278contentColorForek8zF_U;
                        f4 = f2;
                        j6 = j3;
                        function4 = function2;
                        windowInsets2 = windowInsets;
                        modalBottomSheetProperties2 = modalBottomSheetProperties;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a4a
                            public final Object invoke(Object obj, Object obj2) {
                                return ModalBottomSheet_androidKt.b(function0, modifier3, sheetState2, f3, shape3, j4, j5, f4, j6, function4, windowInsets2, modalBottomSheetProperties2, function3, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i14 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i19 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM121getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        } else {
                            expandedShape = shape2;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            scrimColor = j3;
                        }
                        if (i9 != 0) {
                            function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                        } else {
                            function2M316getLambda$1294623166$material3 = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets3 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        shape4 = expandedShape;
                        modifier4 = modifier2;
                        Function2 function15 = function2M316getLambda$1294623166$material3;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        long j18 = jM278contentColorForek8zF_U;
                        f6 = fM6022constructorimpl;
                        sheetState3 = sheetStateRememberModalBottomSheetState;
                        j7 = j18;
                        j8 = scrimColor;
                        function5 = function15;
                    } else {
                        if (i19 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM121getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        } else {
                            expandedShape = shape2;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            scrimColor = j3;
                        }
                        if (i9 != 0) {
                            function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                        } else {
                            function2M316getLambda$1294623166$material3 = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets3 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        shape4 = expandedShape;
                        modifier4 = modifier2;
                        Function2 function16 = function2M316getLambda$1294623166$material3;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        long j19 = jM278contentColorForek8zF_U;
                        f6 = fM6022constructorimpl;
                        sheetState3 = sheetStateRememberModalBottomSheetState;
                        j7 = j19;
                        j8 = scrimColor;
                        function5 = function16;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1342054200, i4, i14, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:343)");
                    }
                    int i212 = i4 << 3;
                    int i213 = i14 << 3;
                    composer2 = composerStartRestartGroup;
                    ModalBottomSheetKt.m630ModalBottomSheetYbuCTN8(function0, modifier4, sheetState3, f5, false, shape4, containerColor, j7, f6, j8, function5, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                        public final WindowInsets invoke(Composer composer3, int i214) {
                            composer3.startReplaceGroup(-677688734);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-677688734, i214, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:354)");
                            }
                            WindowInsets windowInsets4 = windowInsets3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer3.endReplaceGroup();
                            return windowInsets4;
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            return invoke((Composer) obj, ((Number) obj2).intValue());
                        }
                    }, properties, function3, composer2, (i4 & 8190) | (i212 & 458752) | (i212 & 3670016) | (i212 & 29360128) | (i212 & 234881024) | (i212 & 1879048192), ((i4 >> 27) & 14) | (i213 & 896) | (i213 & V4Signature.MAX_SIGNING_INFOS_SIZE), 16);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    f3 = f5;
                    shape3 = shape4;
                    j5 = j7;
                    function4 = function5;
                    float f12 = f6;
                    windowInsets2 = windowInsets3;
                    sheetState2 = sheetState3;
                    j4 = containerColor;
                    f4 = f12;
                    j6 = j8;
                    modalBottomSheetProperties2 = properties;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    f3 = f;
                    shape3 = shape2;
                    j4 = containerColor;
                    modifier3 = modifier2;
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    j5 = jM278contentColorForek8zF_U;
                    f4 = f2;
                    j6 = j3;
                    function4 = function2;
                    windowInsets2 = windowInsets;
                    modalBottomSheetProperties2 = modalBottomSheetProperties;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a4a
                        public final Object invoke(Object obj, Object obj2) {
                            return ModalBottomSheet_androidKt.b(function0, modifier3, sheetState2, f3, shape3, j4, j5, f4, j6, function4, windowInsets2, modalBottomSheetProperties2, function3, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 805306368;
            if ((i2 & 6) == 0) {
                i11 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 2 : 4);
            } else {
                i11 = i2;
            }
            i12 = i3 & 2048;
            if (i12 != 0) {
                i11 |= 48;
            } else if ((i2 & 48) != 0) {
                if (composerStartRestartGroup.changed(modalBottomSheetProperties)) {
                    i13 = 32;
                } else {
                    i13 = 16;
                }
                i11 |= i13;
            }
            i14 = i11;
            if ((i3 & 4096) != 0) {
                if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i15 = 256;
                    } else {
                        i15 = 128;
                    }
                    i14 |= i15;
                }
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i19 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM121getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        } else {
                            expandedShape = shape2;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            scrimColor = j3;
                        }
                        if (i9 != 0) {
                            function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                        } else {
                            function2M316getLambda$1294623166$material3 = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets3 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        shape4 = expandedShape;
                        modifier4 = modifier2;
                        Function2 function17 = function2M316getLambda$1294623166$material3;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        long j110 = jM278contentColorForek8zF_U;
                        f6 = fM6022constructorimpl;
                        sheetState3 = sheetStateRememberModalBottomSheetState;
                        j7 = j110;
                        j8 = scrimColor;
                        function5 = function17;
                    } else {
                        if (i19 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM121getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        } else {
                            expandedShape = shape2;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            scrimColor = j3;
                        }
                        if (i9 != 0) {
                            function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                        } else {
                            function2M316getLambda$1294623166$material3 = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets3 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        shape4 = expandedShape;
                        modifier4 = modifier2;
                        Function2 function18 = function2M316getLambda$1294623166$material3;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        long j111 = jM278contentColorForek8zF_U;
                        f6 = fM6022constructorimpl;
                        sheetState3 = sheetStateRememberModalBottomSheetState;
                        j7 = j111;
                        j8 = scrimColor;
                        function5 = function18;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1342054200, i4, i14, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:343)");
                    }
                    int i214 = i4 << 3;
                    int i215 = i14 << 3;
                    composer2 = composerStartRestartGroup;
                    ModalBottomSheetKt.m630ModalBottomSheetYbuCTN8(function0, modifier4, sheetState3, f5, false, shape4, containerColor, j7, f6, j8, function5, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                        public final WindowInsets invoke(Composer composer3, int i216) {
                            composer3.startReplaceGroup(-677688734);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-677688734, i216, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:354)");
                            }
                            WindowInsets windowInsets4 = windowInsets3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer3.endReplaceGroup();
                            return windowInsets4;
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            return invoke((Composer) obj, ((Number) obj2).intValue());
                        }
                    }, properties, function3, composer2, (i4 & 8190) | (i214 & 458752) | (i214 & 3670016) | (i214 & 29360128) | (i214 & 234881024) | (i214 & 1879048192), ((i4 >> 27) & 14) | (i215 & 896) | (i215 & V4Signature.MAX_SIGNING_INFOS_SIZE), 16);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    f3 = f5;
                    shape3 = shape4;
                    j5 = j7;
                    function4 = function5;
                    float f13 = f6;
                    windowInsets2 = windowInsets3;
                    sheetState2 = sheetState3;
                    j4 = containerColor;
                    f4 = f13;
                    j6 = j8;
                    modalBottomSheetProperties2 = properties;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    f3 = f;
                    shape3 = shape2;
                    j4 = containerColor;
                    modifier3 = modifier2;
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    j5 = jM278contentColorForek8zF_U;
                    f4 = f2;
                    j6 = j3;
                    function4 = function2;
                    windowInsets2 = windowInsets;
                    modalBottomSheetProperties2 = modalBottomSheetProperties;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a4a
                        public final Object invoke(Object obj, Object obj2) {
                            return ModalBottomSheet_androidKt.b(function0, modifier3, sheetState2, f3, shape3, j4, j5, f4, j6, function4, windowInsets2, modalBottomSheetProperties2, function3, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i14 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            if ((i4 & 306783379) == 306783378) {
                z = true;
            } else {
                z = true;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i19 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM121getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        expandedShape = shape2;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    }
                    if ((i3 & 64) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        scrimColor = j3;
                    }
                    if (i9 != 0) {
                        function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                    } else {
                        function2M316getLambda$1294623166$material3 = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets3 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    shape4 = expandedShape;
                    modifier4 = modifier2;
                    Function2 function19 = function2M316getLambda$1294623166$material3;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    long j112 = jM278contentColorForek8zF_U;
                    f6 = fM6022constructorimpl;
                    sheetState3 = sheetStateRememberModalBottomSheetState;
                    j7 = j112;
                    j8 = scrimColor;
                    function5 = function19;
                } else {
                    if (i19 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM121getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        expandedShape = shape2;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    }
                    if ((i3 & 64) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        scrimColor = j3;
                    }
                    if (i9 != 0) {
                        function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                    } else {
                        function2M316getLambda$1294623166$material3 = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets3 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    shape4 = expandedShape;
                    modifier4 = modifier2;
                    Function2 function110 = function2M316getLambda$1294623166$material3;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    long j113 = jM278contentColorForek8zF_U;
                    f6 = fM6022constructorimpl;
                    sheetState3 = sheetStateRememberModalBottomSheetState;
                    j7 = j113;
                    j8 = scrimColor;
                    function5 = function110;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1342054200, i4, i14, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:343)");
                }
                int i216 = i4 << 3;
                int i217 = i14 << 3;
                composer2 = composerStartRestartGroup;
                ModalBottomSheetKt.m630ModalBottomSheetYbuCTN8(function0, modifier4, sheetState3, f5, false, shape4, containerColor, j7, f6, j8, function5, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                    public final WindowInsets invoke(Composer composer3, int i218) {
                        composer3.startReplaceGroup(-677688734);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-677688734, i218, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:354)");
                        }
                        WindowInsets windowInsets4 = windowInsets3;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer3.endReplaceGroup();
                        return windowInsets4;
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        return invoke((Composer) obj, ((Number) obj2).intValue());
                    }
                }, properties, function3, composer2, (i4 & 8190) | (i216 & 458752) | (i216 & 3670016) | (i216 & 29360128) | (i216 & 234881024) | (i216 & 1879048192), ((i4 >> 27) & 14) | (i217 & 896) | (i217 & V4Signature.MAX_SIGNING_INFOS_SIZE), 16);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                f3 = f5;
                shape3 = shape4;
                j5 = j7;
                function4 = function5;
                float f14 = f6;
                windowInsets2 = windowInsets3;
                sheetState2 = sheetState3;
                j4 = containerColor;
                f4 = f14;
                j6 = j8;
                modalBottomSheetProperties2 = properties;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                f3 = f;
                shape3 = shape2;
                j4 = containerColor;
                modifier3 = modifier2;
                sheetState2 = sheetStateRememberModalBottomSheetState;
                j5 = jM278contentColorForek8zF_U;
                f4 = f2;
                j6 = j3;
                function4 = function2;
                windowInsets2 = windowInsets;
                modalBottomSheetProperties2 = modalBottomSheetProperties;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a4a
                    public final Object invoke(Object obj, Object obj2) {
                        return ModalBottomSheet_androidKt.b(function0, modifier3, sheetState2, f3, shape3, j4, j5, f4, j6, function4, windowInsets2, modalBottomSheetProperties2, function3, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i3 & 4) == 0) {
                sheetStateRememberModalBottomSheetState = sheetState;
                if (composerStartRestartGroup.changed(sheetStateRememberModalBottomSheetState)) {
                }
                i4 |= i20;
            } else {
                sheetStateRememberModalBottomSheetState = sheetState;
            }
            i4 |= i20;
        } else {
            sheetStateRememberModalBottomSheetState = sheetState;
        }
        i5 = i3 & 8;
        if (i5 != 0) {
            if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changed(f)) {
                    i6 = 2048;
                } else {
                    i6 = 1024;
                }
                i4 |= i6;
            }
            if ((i & 24576) == 0) {
                if ((i3 & 16) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i4 |= i21;
                } else {
                    shape2 = shape;
                }
                i4 |= i21;
            } else {
                shape2 = shape;
            }
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                containerColor = j;
                if ((i3 & 32) == 0) {
                    i18 = 65536;
                } else {
                    i18 = 65536;
                }
                i4 |= i18;
            } else {
                containerColor = j;
            }
            if ((i & 1572864) == 0) {
                jM278contentColorForek8zF_U = j2;
                if ((i3 & 64) == 0) {
                    i17 = 524288;
                } else {
                    i17 = 524288;
                }
                i4 |= i17;
            } else {
                jM278contentColorForek8zF_U = j2;
            }
            i7 = i3 & 128;
            if (i7 != 0) {
                i4 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(f2)) {
                    i8 = 8388608;
                } else {
                    i8 = 4194304;
                }
                i4 |= i8;
            }
            if ((i & 100663296) != 0) {
                if ((i3 & 256) == 0) {
                    i16 = 33554432;
                } else {
                    i16 = 33554432;
                }
                i4 |= i16;
            }
            i9 = i3 & 512;
            if (i9 != 0) {
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i10 = 536870912;
                    } else {
                        i10 = 268435456;
                    }
                    i4 |= i10;
                }
                if ((i2 & 6) == 0) {
                    i11 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 2 : 4);
                } else {
                    i11 = i2;
                }
                i12 = i3 & 2048;
                if (i12 != 0) {
                    i11 |= 48;
                } else if ((i2 & 48) != 0) {
                    if (composerStartRestartGroup.changed(modalBottomSheetProperties)) {
                        i13 = 32;
                    } else {
                        i13 = 16;
                    }
                    i11 |= i13;
                }
                i14 = i11;
                if ((i3 & 4096) != 0) {
                    if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i15 = 256;
                        } else {
                            i15 = 128;
                        }
                        i14 |= i15;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i19 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM121getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            } else {
                                expandedShape = shape2;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            }
                            if ((i3 & 64) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                            } else {
                                fM6022constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                scrimColor = j3;
                            }
                            if (i9 != 0) {
                                function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                            } else {
                                function2M316getLambda$1294623166$material3 = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets3 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            shape4 = expandedShape;
                            modifier4 = modifier2;
                            Function2 function111 = function2M316getLambda$1294623166$material3;
                            f5 = fM121getSheetMaxWidthD9Ej5fM;
                            long j114 = jM278contentColorForek8zF_U;
                            f6 = fM6022constructorimpl;
                            sheetState3 = sheetStateRememberModalBottomSheetState;
                            j7 = j114;
                            j8 = scrimColor;
                            function5 = function111;
                        } else {
                            if (i19 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if ((i3 & 4) != 0) {
                                i4 &= -897;
                                sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                            }
                            if (i5 != 0) {
                                fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                            } else {
                                fM121getSheetMaxWidthD9Ej5fM = f;
                            }
                            if ((i3 & 16) != 0) {
                                expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                                i4 &= -57345;
                            } else {
                                expandedShape = shape2;
                            }
                            if ((i3 & 32) != 0) {
                                containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -458753;
                            }
                            if ((i3 & 64) != 0) {
                                jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                                i4 &= -3670017;
                            }
                            if (i7 != 0) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                            } else {
                                fM6022constructorimpl = f2;
                            }
                            if ((i3 & 256) != 0) {
                                scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                scrimColor = j3;
                            }
                            if (i9 != 0) {
                                function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                            } else {
                                function2M316getLambda$1294623166$material3 = function2;
                            }
                            if ((i3 & 1024) != 0) {
                                windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                                i14 &= -15;
                            } else {
                                windowInsets3 = windowInsets;
                            }
                            if (i12 != 0) {
                                properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                            } else {
                                properties = modalBottomSheetProperties;
                            }
                            shape4 = expandedShape;
                            modifier4 = modifier2;
                            Function2 function112 = function2M316getLambda$1294623166$material3;
                            f5 = fM121getSheetMaxWidthD9Ej5fM;
                            long j115 = jM278contentColorForek8zF_U;
                            f6 = fM6022constructorimpl;
                            sheetState3 = sheetStateRememberModalBottomSheetState;
                            j7 = j115;
                            j8 = scrimColor;
                            function5 = function112;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1342054200, i4, i14, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:343)");
                        }
                        int i218 = i4 << 3;
                        int i219 = i14 << 3;
                        composer2 = composerStartRestartGroup;
                        ModalBottomSheetKt.m630ModalBottomSheetYbuCTN8(function0, modifier4, sheetState3, f5, false, shape4, containerColor, j7, f6, j8, function5, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                            public final WindowInsets invoke(Composer composer3, int i2110) {
                                composer3.startReplaceGroup(-677688734);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-677688734, i2110, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:354)");
                                }
                                WindowInsets windowInsets4 = windowInsets3;
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                composer3.endReplaceGroup();
                                return windowInsets4;
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                                return invoke((Composer) obj, ((Number) obj2).intValue());
                            }
                        }, properties, function3, composer2, (i4 & 8190) | (i218 & 458752) | (i218 & 3670016) | (i218 & 29360128) | (i218 & 234881024) | (i218 & 1879048192), ((i4 >> 27) & 14) | (i219 & 896) | (i219 & V4Signature.MAX_SIGNING_INFOS_SIZE), 16);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        f3 = f5;
                        shape3 = shape4;
                        j5 = j7;
                        function4 = function5;
                        float f15 = f6;
                        windowInsets2 = windowInsets3;
                        sheetState2 = sheetState3;
                        j4 = containerColor;
                        f4 = f15;
                        j6 = j8;
                        modalBottomSheetProperties2 = properties;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        f3 = f;
                        shape3 = shape2;
                        j4 = containerColor;
                        modifier3 = modifier2;
                        sheetState2 = sheetStateRememberModalBottomSheetState;
                        j5 = jM278contentColorForek8zF_U;
                        f4 = f2;
                        j6 = j3;
                        function4 = function2;
                        windowInsets2 = windowInsets;
                        modalBottomSheetProperties2 = modalBottomSheetProperties;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a4a
                            public final Object invoke(Object obj, Object obj2) {
                                return ModalBottomSheet_androidKt.b(function0, modifier3, sheetState2, f3, shape3, j4, j5, f4, j6, function4, windowInsets2, modalBottomSheetProperties2, function3, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i14 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i19 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM121getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        } else {
                            expandedShape = shape2;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            scrimColor = j3;
                        }
                        if (i9 != 0) {
                            function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                        } else {
                            function2M316getLambda$1294623166$material3 = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets3 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        shape4 = expandedShape;
                        modifier4 = modifier2;
                        Function2 function113 = function2M316getLambda$1294623166$material3;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        long j116 = jM278contentColorForek8zF_U;
                        f6 = fM6022constructorimpl;
                        sheetState3 = sheetStateRememberModalBottomSheetState;
                        j7 = j116;
                        j8 = scrimColor;
                        function5 = function113;
                    } else {
                        if (i19 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM121getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        } else {
                            expandedShape = shape2;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            scrimColor = j3;
                        }
                        if (i9 != 0) {
                            function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                        } else {
                            function2M316getLambda$1294623166$material3 = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets3 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        shape4 = expandedShape;
                        modifier4 = modifier2;
                        Function2 function114 = function2M316getLambda$1294623166$material3;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        long j117 = jM278contentColorForek8zF_U;
                        f6 = fM6022constructorimpl;
                        sheetState3 = sheetStateRememberModalBottomSheetState;
                        j7 = j117;
                        j8 = scrimColor;
                        function5 = function114;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1342054200, i4, i14, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:343)");
                    }
                    int i2110 = i4 << 3;
                    int i2111 = i14 << 3;
                    composer2 = composerStartRestartGroup;
                    ModalBottomSheetKt.m630ModalBottomSheetYbuCTN8(function0, modifier4, sheetState3, f5, false, shape4, containerColor, j7, f6, j8, function5, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                        public final WindowInsets invoke(Composer composer3, int i2112) {
                            composer3.startReplaceGroup(-677688734);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-677688734, i2112, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:354)");
                            }
                            WindowInsets windowInsets4 = windowInsets3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer3.endReplaceGroup();
                            return windowInsets4;
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            return invoke((Composer) obj, ((Number) obj2).intValue());
                        }
                    }, properties, function3, composer2, (i4 & 8190) | (i2110 & 458752) | (i2110 & 3670016) | (i2110 & 29360128) | (i2110 & 234881024) | (i2110 & 1879048192), ((i4 >> 27) & 14) | (i2111 & 896) | (i2111 & V4Signature.MAX_SIGNING_INFOS_SIZE), 16);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    f3 = f5;
                    shape3 = shape4;
                    j5 = j7;
                    function4 = function5;
                    float f16 = f6;
                    windowInsets2 = windowInsets3;
                    sheetState2 = sheetState3;
                    j4 = containerColor;
                    f4 = f16;
                    j6 = j8;
                    modalBottomSheetProperties2 = properties;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    f3 = f;
                    shape3 = shape2;
                    j4 = containerColor;
                    modifier3 = modifier2;
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    j5 = jM278contentColorForek8zF_U;
                    f4 = f2;
                    j6 = j3;
                    function4 = function2;
                    windowInsets2 = windowInsets;
                    modalBottomSheetProperties2 = modalBottomSheetProperties;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a4a
                        public final Object invoke(Object obj, Object obj2) {
                            return ModalBottomSheet_androidKt.b(function0, modifier3, sheetState2, f3, shape3, j4, j5, f4, j6, function4, windowInsets2, modalBottomSheetProperties2, function3, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 805306368;
            if ((i2 & 6) == 0) {
                i11 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 2 : 4);
            } else {
                i11 = i2;
            }
            i12 = i3 & 2048;
            if (i12 != 0) {
                i11 |= 48;
            } else if ((i2 & 48) != 0) {
                if (composerStartRestartGroup.changed(modalBottomSheetProperties)) {
                    i13 = 32;
                } else {
                    i13 = 16;
                }
                i11 |= i13;
            }
            i14 = i11;
            if ((i3 & 4096) != 0) {
                if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i15 = 256;
                    } else {
                        i15 = 128;
                    }
                    i14 |= i15;
                }
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i19 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM121getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        } else {
                            expandedShape = shape2;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            scrimColor = j3;
                        }
                        if (i9 != 0) {
                            function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                        } else {
                            function2M316getLambda$1294623166$material3 = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets3 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        shape4 = expandedShape;
                        modifier4 = modifier2;
                        Function2 function115 = function2M316getLambda$1294623166$material3;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        long j118 = jM278contentColorForek8zF_U;
                        f6 = fM6022constructorimpl;
                        sheetState3 = sheetStateRememberModalBottomSheetState;
                        j7 = j118;
                        j8 = scrimColor;
                        function5 = function115;
                    } else {
                        if (i19 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM121getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        } else {
                            expandedShape = shape2;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            scrimColor = j3;
                        }
                        if (i9 != 0) {
                            function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                        } else {
                            function2M316getLambda$1294623166$material3 = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets3 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        shape4 = expandedShape;
                        modifier4 = modifier2;
                        Function2 function116 = function2M316getLambda$1294623166$material3;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        long j119 = jM278contentColorForek8zF_U;
                        f6 = fM6022constructorimpl;
                        sheetState3 = sheetStateRememberModalBottomSheetState;
                        j7 = j119;
                        j8 = scrimColor;
                        function5 = function116;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1342054200, i4, i14, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:343)");
                    }
                    int i2112 = i4 << 3;
                    int i2113 = i14 << 3;
                    composer2 = composerStartRestartGroup;
                    ModalBottomSheetKt.m630ModalBottomSheetYbuCTN8(function0, modifier4, sheetState3, f5, false, shape4, containerColor, j7, f6, j8, function5, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                        public final WindowInsets invoke(Composer composer3, int i2114) {
                            composer3.startReplaceGroup(-677688734);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-677688734, i2114, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:354)");
                            }
                            WindowInsets windowInsets4 = windowInsets3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer3.endReplaceGroup();
                            return windowInsets4;
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            return invoke((Composer) obj, ((Number) obj2).intValue());
                        }
                    }, properties, function3, composer2, (i4 & 8190) | (i2112 & 458752) | (i2112 & 3670016) | (i2112 & 29360128) | (i2112 & 234881024) | (i2112 & 1879048192), ((i4 >> 27) & 14) | (i2113 & 896) | (i2113 & V4Signature.MAX_SIGNING_INFOS_SIZE), 16);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    f3 = f5;
                    shape3 = shape4;
                    j5 = j7;
                    function4 = function5;
                    float f17 = f6;
                    windowInsets2 = windowInsets3;
                    sheetState2 = sheetState3;
                    j4 = containerColor;
                    f4 = f17;
                    j6 = j8;
                    modalBottomSheetProperties2 = properties;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    f3 = f;
                    shape3 = shape2;
                    j4 = containerColor;
                    modifier3 = modifier2;
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    j5 = jM278contentColorForek8zF_U;
                    f4 = f2;
                    j6 = j3;
                    function4 = function2;
                    windowInsets2 = windowInsets;
                    modalBottomSheetProperties2 = modalBottomSheetProperties;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a4a
                        public final Object invoke(Object obj, Object obj2) {
                            return ModalBottomSheet_androidKt.b(function0, modifier3, sheetState2, f3, shape3, j4, j5, f4, j6, function4, windowInsets2, modalBottomSheetProperties2, function3, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i14 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            if ((i4 & 306783379) == 306783378) {
                z = true;
            } else {
                z = true;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i19 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM121getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        expandedShape = shape2;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    }
                    if ((i3 & 64) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        scrimColor = j3;
                    }
                    if (i9 != 0) {
                        function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                    } else {
                        function2M316getLambda$1294623166$material3 = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets3 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    shape4 = expandedShape;
                    modifier4 = modifier2;
                    Function2 function117 = function2M316getLambda$1294623166$material3;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    long j1110 = jM278contentColorForek8zF_U;
                    f6 = fM6022constructorimpl;
                    sheetState3 = sheetStateRememberModalBottomSheetState;
                    j7 = j1110;
                    j8 = scrimColor;
                    function5 = function117;
                } else {
                    if (i19 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM121getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        expandedShape = shape2;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    }
                    if ((i3 & 64) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        scrimColor = j3;
                    }
                    if (i9 != 0) {
                        function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                    } else {
                        function2M316getLambda$1294623166$material3 = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets3 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    shape4 = expandedShape;
                    modifier4 = modifier2;
                    Function2 function118 = function2M316getLambda$1294623166$material3;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    long j1111 = jM278contentColorForek8zF_U;
                    f6 = fM6022constructorimpl;
                    sheetState3 = sheetStateRememberModalBottomSheetState;
                    j7 = j1111;
                    j8 = scrimColor;
                    function5 = function118;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1342054200, i4, i14, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:343)");
                }
                int i2114 = i4 << 3;
                int i2115 = i14 << 3;
                composer2 = composerStartRestartGroup;
                ModalBottomSheetKt.m630ModalBottomSheetYbuCTN8(function0, modifier4, sheetState3, f5, false, shape4, containerColor, j7, f6, j8, function5, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                    public final WindowInsets invoke(Composer composer3, int i2116) {
                        composer3.startReplaceGroup(-677688734);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-677688734, i2116, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:354)");
                        }
                        WindowInsets windowInsets4 = windowInsets3;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer3.endReplaceGroup();
                        return windowInsets4;
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        return invoke((Composer) obj, ((Number) obj2).intValue());
                    }
                }, properties, function3, composer2, (i4 & 8190) | (i2114 & 458752) | (i2114 & 3670016) | (i2114 & 29360128) | (i2114 & 234881024) | (i2114 & 1879048192), ((i4 >> 27) & 14) | (i2115 & 896) | (i2115 & V4Signature.MAX_SIGNING_INFOS_SIZE), 16);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                f3 = f5;
                shape3 = shape4;
                j5 = j7;
                function4 = function5;
                float f18 = f6;
                windowInsets2 = windowInsets3;
                sheetState2 = sheetState3;
                j4 = containerColor;
                f4 = f18;
                j6 = j8;
                modalBottomSheetProperties2 = properties;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                f3 = f;
                shape3 = shape2;
                j4 = containerColor;
                modifier3 = modifier2;
                sheetState2 = sheetStateRememberModalBottomSheetState;
                j5 = jM278contentColorForek8zF_U;
                f4 = f2;
                j6 = j3;
                function4 = function2;
                windowInsets2 = windowInsets;
                modalBottomSheetProperties2 = modalBottomSheetProperties;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a4a
                    public final Object invoke(Object obj, Object obj2) {
                        return ModalBottomSheet_androidKt.b(function0, modifier3, sheetState2, f3, shape3, j4, j5, f4, j6, function4, windowInsets2, modalBottomSheetProperties2, function3, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 3072;
        if ((i & 24576) == 0) {
            if ((i3 & 16) == 0) {
                shape2 = shape;
                if (composerStartRestartGroup.changed(shape2)) {
                }
                i4 |= i21;
            } else {
                shape2 = shape;
            }
            i4 |= i21;
        } else {
            shape2 = shape;
        }
        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            containerColor = j;
            if ((i3 & 32) == 0) {
                i18 = 65536;
            } else {
                i18 = 65536;
            }
            i4 |= i18;
        } else {
            containerColor = j;
        }
        if ((i & 1572864) == 0) {
            jM278contentColorForek8zF_U = j2;
            if ((i3 & 64) == 0) {
                i17 = 524288;
            } else {
                i17 = 524288;
            }
            i4 |= i17;
        } else {
            jM278contentColorForek8zF_U = j2;
        }
        i7 = i3 & 128;
        if (i7 != 0) {
            i4 |= 12582912;
        } else if ((i & 12582912) == 0) {
            if (composerStartRestartGroup.changed(f2)) {
                i8 = 8388608;
            } else {
                i8 = 4194304;
            }
            i4 |= i8;
        }
        if ((i & 100663296) != 0) {
            if ((i3 & 256) == 0) {
                i16 = 33554432;
            } else {
                i16 = 33554432;
            }
            i4 |= i16;
        }
        i9 = i3 & 512;
        if (i9 != 0) {
            if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i10 = 536870912;
                } else {
                    i10 = 268435456;
                }
                i4 |= i10;
            }
            if ((i2 & 6) == 0) {
                i11 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 2 : 4);
            } else {
                i11 = i2;
            }
            i12 = i3 & 2048;
            if (i12 != 0) {
                i11 |= 48;
            } else if ((i2 & 48) != 0) {
                if (composerStartRestartGroup.changed(modalBottomSheetProperties)) {
                    i13 = 32;
                } else {
                    i13 = 16;
                }
                i11 |= i13;
            }
            i14 = i11;
            if ((i3 & 4096) != 0) {
                if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i15 = 256;
                    } else {
                        i15 = 128;
                    }
                    i14 |= i15;
                }
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i19 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM121getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        } else {
                            expandedShape = shape2;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            scrimColor = j3;
                        }
                        if (i9 != 0) {
                            function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                        } else {
                            function2M316getLambda$1294623166$material3 = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets3 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        shape4 = expandedShape;
                        modifier4 = modifier2;
                        Function2 function119 = function2M316getLambda$1294623166$material3;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        long j1112 = jM278contentColorForek8zF_U;
                        f6 = fM6022constructorimpl;
                        sheetState3 = sheetStateRememberModalBottomSheetState;
                        j7 = j1112;
                        j8 = scrimColor;
                        function5 = function119;
                    } else {
                        if (i19 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i3 & 4) != 0) {
                            i4 &= -897;
                            sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                        }
                        if (i5 != 0) {
                            fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                        } else {
                            fM121getSheetMaxWidthD9Ej5fM = f;
                        }
                        if ((i3 & 16) != 0) {
                            expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                            i4 &= -57345;
                        } else {
                            expandedShape = shape2;
                        }
                        if ((i3 & 32) != 0) {
                            containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -458753;
                        }
                        if ((i3 & 64) != 0) {
                            jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                            i4 &= -3670017;
                        }
                        if (i7 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f2;
                        }
                        if ((i3 & 256) != 0) {
                            scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            scrimColor = j3;
                        }
                        if (i9 != 0) {
                            function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                        } else {
                            function2M316getLambda$1294623166$material3 = function2;
                        }
                        if ((i3 & 1024) != 0) {
                            windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                            i14 &= -15;
                        } else {
                            windowInsets3 = windowInsets;
                        }
                        if (i12 != 0) {
                            properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                        } else {
                            properties = modalBottomSheetProperties;
                        }
                        shape4 = expandedShape;
                        modifier4 = modifier2;
                        Function2 function1110 = function2M316getLambda$1294623166$material3;
                        f5 = fM121getSheetMaxWidthD9Ej5fM;
                        long j1113 = jM278contentColorForek8zF_U;
                        f6 = fM6022constructorimpl;
                        sheetState3 = sheetStateRememberModalBottomSheetState;
                        j7 = j1113;
                        j8 = scrimColor;
                        function5 = function1110;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1342054200, i4, i14, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:343)");
                    }
                    int i2116 = i4 << 3;
                    int i2117 = i14 << 3;
                    composer2 = composerStartRestartGroup;
                    ModalBottomSheetKt.m630ModalBottomSheetYbuCTN8(function0, modifier4, sheetState3, f5, false, shape4, containerColor, j7, f6, j8, function5, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                        public final WindowInsets invoke(Composer composer3, int i2118) {
                            composer3.startReplaceGroup(-677688734);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-677688734, i2118, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:354)");
                            }
                            WindowInsets windowInsets4 = windowInsets3;
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            composer3.endReplaceGroup();
                            return windowInsets4;
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            return invoke((Composer) obj, ((Number) obj2).intValue());
                        }
                    }, properties, function3, composer2, (i4 & 8190) | (i2116 & 458752) | (i2116 & 3670016) | (i2116 & 29360128) | (i2116 & 234881024) | (i2116 & 1879048192), ((i4 >> 27) & 14) | (i2117 & 896) | (i2117 & V4Signature.MAX_SIGNING_INFOS_SIZE), 16);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    f3 = f5;
                    shape3 = shape4;
                    j5 = j7;
                    function4 = function5;
                    float f19 = f6;
                    windowInsets2 = windowInsets3;
                    sheetState2 = sheetState3;
                    j4 = containerColor;
                    f4 = f19;
                    j6 = j8;
                    modalBottomSheetProperties2 = properties;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    f3 = f;
                    shape3 = shape2;
                    j4 = containerColor;
                    modifier3 = modifier2;
                    sheetState2 = sheetStateRememberModalBottomSheetState;
                    j5 = jM278contentColorForek8zF_U;
                    f4 = f2;
                    j6 = j3;
                    function4 = function2;
                    windowInsets2 = windowInsets;
                    modalBottomSheetProperties2 = modalBottomSheetProperties;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a4a
                        public final Object invoke(Object obj, Object obj2) {
                            return ModalBottomSheet_androidKt.b(function0, modifier3, sheetState2, f3, shape3, j4, j5, f4, j6, function4, windowInsets2, modalBottomSheetProperties2, function3, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i14 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            if ((i4 & 306783379) == 306783378) {
                z = true;
            } else {
                z = true;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i19 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM121getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        expandedShape = shape2;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    }
                    if ((i3 & 64) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        scrimColor = j3;
                    }
                    if (i9 != 0) {
                        function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                    } else {
                        function2M316getLambda$1294623166$material3 = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets3 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    shape4 = expandedShape;
                    modifier4 = modifier2;
                    Function2 function1111 = function2M316getLambda$1294623166$material3;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    long j1114 = jM278contentColorForek8zF_U;
                    f6 = fM6022constructorimpl;
                    sheetState3 = sheetStateRememberModalBottomSheetState;
                    j7 = j1114;
                    j8 = scrimColor;
                    function5 = function1111;
                } else {
                    if (i19 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM121getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        expandedShape = shape2;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    }
                    if ((i3 & 64) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        scrimColor = j3;
                    }
                    if (i9 != 0) {
                        function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                    } else {
                        function2M316getLambda$1294623166$material3 = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets3 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    shape4 = expandedShape;
                    modifier4 = modifier2;
                    Function2 function1112 = function2M316getLambda$1294623166$material3;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    long j1115 = jM278contentColorForek8zF_U;
                    f6 = fM6022constructorimpl;
                    sheetState3 = sheetStateRememberModalBottomSheetState;
                    j7 = j1115;
                    j8 = scrimColor;
                    function5 = function1112;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1342054200, i4, i14, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:343)");
                }
                int i2118 = i4 << 3;
                int i2119 = i14 << 3;
                composer2 = composerStartRestartGroup;
                ModalBottomSheetKt.m630ModalBottomSheetYbuCTN8(function0, modifier4, sheetState3, f5, false, shape4, containerColor, j7, f6, j8, function5, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                    public final WindowInsets invoke(Composer composer3, int i21110) {
                        composer3.startReplaceGroup(-677688734);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-677688734, i21110, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:354)");
                        }
                        WindowInsets windowInsets4 = windowInsets3;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer3.endReplaceGroup();
                        return windowInsets4;
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        return invoke((Composer) obj, ((Number) obj2).intValue());
                    }
                }, properties, function3, composer2, (i4 & 8190) | (i2118 & 458752) | (i2118 & 3670016) | (i2118 & 29360128) | (i2118 & 234881024) | (i2118 & 1879048192), ((i4 >> 27) & 14) | (i2119 & 896) | (i2119 & V4Signature.MAX_SIGNING_INFOS_SIZE), 16);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                f3 = f5;
                shape3 = shape4;
                j5 = j7;
                function4 = function5;
                float f110 = f6;
                windowInsets2 = windowInsets3;
                sheetState2 = sheetState3;
                j4 = containerColor;
                f4 = f110;
                j6 = j8;
                modalBottomSheetProperties2 = properties;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                f3 = f;
                shape3 = shape2;
                j4 = containerColor;
                modifier3 = modifier2;
                sheetState2 = sheetStateRememberModalBottomSheetState;
                j5 = jM278contentColorForek8zF_U;
                f4 = f2;
                j6 = j3;
                function4 = function2;
                windowInsets2 = windowInsets;
                modalBottomSheetProperties2 = modalBottomSheetProperties;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a4a
                    public final Object invoke(Object obj, Object obj2) {
                        return ModalBottomSheet_androidKt.b(function0, modifier3, sheetState2, f3, shape3, j4, j5, f4, j6, function4, windowInsets2, modalBottomSheetProperties2, function3, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 805306368;
        if ((i2 & 6) == 0) {
            i11 = i2 | (((i3 & 1024) == 0 || !composerStartRestartGroup.changed(windowInsets)) ? 2 : 4);
        } else {
            i11 = i2;
        }
        i12 = i3 & 2048;
        if (i12 != 0) {
            i11 |= 48;
        } else if ((i2 & 48) != 0) {
            if (composerStartRestartGroup.changed(modalBottomSheetProperties)) {
                i13 = 32;
            } else {
                i13 = 16;
            }
            i11 |= i13;
        }
        i14 = i11;
        if ((i3 & 4096) != 0) {
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i15 = 256;
                } else {
                    i15 = 128;
                }
                i14 |= i15;
            }
            if ((i4 & 306783379) == 306783378) {
                z = true;
            } else {
                z = true;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i19 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM121getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        expandedShape = shape2;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    }
                    if ((i3 & 64) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        scrimColor = j3;
                    }
                    if (i9 != 0) {
                        function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                    } else {
                        function2M316getLambda$1294623166$material3 = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets3 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    shape4 = expandedShape;
                    modifier4 = modifier2;
                    Function2 function1113 = function2M316getLambda$1294623166$material3;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    long j1116 = jM278contentColorForek8zF_U;
                    f6 = fM6022constructorimpl;
                    sheetState3 = sheetStateRememberModalBottomSheetState;
                    j7 = j1116;
                    j8 = scrimColor;
                    function5 = function1113;
                } else {
                    if (i19 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i3 & 4) != 0) {
                        i4 &= -897;
                        sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                    }
                    if (i5 != 0) {
                        fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                    } else {
                        fM121getSheetMaxWidthD9Ej5fM = f;
                    }
                    if ((i3 & 16) != 0) {
                        expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                        i4 &= -57345;
                    } else {
                        expandedShape = shape2;
                    }
                    if ((i3 & 32) != 0) {
                        containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -458753;
                    }
                    if ((i3 & 64) != 0) {
                        jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                        i4 &= -3670017;
                    }
                    if (i7 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f2;
                    }
                    if ((i3 & 256) != 0) {
                        scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        scrimColor = j3;
                    }
                    if (i9 != 0) {
                        function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                    } else {
                        function2M316getLambda$1294623166$material3 = function2;
                    }
                    if ((i3 & 1024) != 0) {
                        windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                        i14 &= -15;
                    } else {
                        windowInsets3 = windowInsets;
                    }
                    if (i12 != 0) {
                        properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                    } else {
                        properties = modalBottomSheetProperties;
                    }
                    shape4 = expandedShape;
                    modifier4 = modifier2;
                    Function2 function1114 = function2M316getLambda$1294623166$material3;
                    f5 = fM121getSheetMaxWidthD9Ej5fM;
                    long j1117 = jM278contentColorForek8zF_U;
                    f6 = fM6022constructorimpl;
                    sheetState3 = sheetStateRememberModalBottomSheetState;
                    j7 = j1117;
                    j8 = scrimColor;
                    function5 = function1114;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1342054200, i4, i14, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:343)");
                }
                int i21110 = i4 << 3;
                int i21111 = i14 << 3;
                composer2 = composerStartRestartGroup;
                ModalBottomSheetKt.m630ModalBottomSheetYbuCTN8(function0, modifier4, sheetState3, f5, false, shape4, containerColor, j7, f6, j8, function5, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                    public final WindowInsets invoke(Composer composer3, int i21112) {
                        composer3.startReplaceGroup(-677688734);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-677688734, i21112, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:354)");
                        }
                        WindowInsets windowInsets4 = windowInsets3;
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer3.endReplaceGroup();
                        return windowInsets4;
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        return invoke((Composer) obj, ((Number) obj2).intValue());
                    }
                }, properties, function3, composer2, (i4 & 8190) | (i21110 & 458752) | (i21110 & 3670016) | (i21110 & 29360128) | (i21110 & 234881024) | (i21110 & 1879048192), ((i4 >> 27) & 14) | (i21111 & 896) | (i21111 & V4Signature.MAX_SIGNING_INFOS_SIZE), 16);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                f3 = f5;
                shape3 = shape4;
                j5 = j7;
                function4 = function5;
                float f111 = f6;
                windowInsets2 = windowInsets3;
                sheetState2 = sheetState3;
                j4 = containerColor;
                f4 = f111;
                j6 = j8;
                modalBottomSheetProperties2 = properties;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                f3 = f;
                shape3 = shape2;
                j4 = containerColor;
                modifier3 = modifier2;
                sheetState2 = sheetStateRememberModalBottomSheetState;
                j5 = jM278contentColorForek8zF_U;
                f4 = f2;
                j6 = j3;
                function4 = function2;
                windowInsets2 = windowInsets;
                modalBottomSheetProperties2 = modalBottomSheetProperties;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a4a
                    public final Object invoke(Object obj, Object obj2) {
                        return ModalBottomSheet_androidKt.b(function0, modifier3, sheetState2, f3, shape3, j4, j5, f4, j6, function4, windowInsets2, modalBottomSheetProperties2, function3, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i14 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        if ((i4 & 306783379) == 306783378) {
            z = true;
        } else {
            z = true;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i19 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    i4 &= -897;
                    sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                }
                if (i5 != 0) {
                    fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                } else {
                    fM121getSheetMaxWidthD9Ej5fM = f;
                }
                if ((i3 & 16) != 0) {
                    expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                    i4 &= -57345;
                } else {
                    expandedShape = shape2;
                }
                if ((i3 & 32) != 0) {
                    containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i4 &= -458753;
                }
                if ((i3 & 64) != 0) {
                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                    i4 &= -3670017;
                }
                if (i7 != 0) {
                    fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                } else {
                    fM6022constructorimpl = f2;
                }
                if ((i3 & 256) != 0) {
                    scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                    i4 &= -234881025;
                } else {
                    scrimColor = j3;
                }
                if (i9 != 0) {
                    function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                } else {
                    function2M316getLambda$1294623166$material3 = function2;
                }
                if ((i3 & 1024) != 0) {
                    windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                    i14 &= -15;
                } else {
                    windowInsets3 = windowInsets;
                }
                if (i12 != 0) {
                    properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                } else {
                    properties = modalBottomSheetProperties;
                }
                shape4 = expandedShape;
                modifier4 = modifier2;
                Function2 function1115 = function2M316getLambda$1294623166$material3;
                f5 = fM121getSheetMaxWidthD9Ej5fM;
                long j1118 = jM278contentColorForek8zF_U;
                f6 = fM6022constructorimpl;
                sheetState3 = sheetStateRememberModalBottomSheetState;
                j7 = j1118;
                j8 = scrimColor;
                function5 = function1115;
            } else {
                if (i19 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i3 & 4) != 0) {
                    i4 &= -897;
                    sheetStateRememberModalBottomSheetState = ModalBottomSheetKt.rememberModalBottomSheetState(false, null, composerStartRestartGroup, 0, 3);
                }
                if (i5 != 0) {
                    fM121getSheetMaxWidthD9Ej5fM = BottomSheetDefaults.INSTANCE.m121getSheetMaxWidthD9Ej5fM();
                } else {
                    fM121getSheetMaxWidthD9Ej5fM = f;
                }
                if ((i3 & 16) != 0) {
                    expandedShape = BottomSheetDefaults.INSTANCE.getExpandedShape(composerStartRestartGroup, 6);
                    i4 &= -57345;
                } else {
                    expandedShape = shape2;
                }
                if ((i3 & 32) != 0) {
                    containerColor = BottomSheetDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i4 &= -458753;
                }
                if ((i3 & 64) != 0) {
                    jM278contentColorForek8zF_U = ColorSchemeKt.m278contentColorForek8zF_U(containerColor, composerStartRestartGroup, (i4 >> 15) & 14);
                    i4 &= -3670017;
                }
                if (i7 != 0) {
                    fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                } else {
                    fM6022constructorimpl = f2;
                }
                if ((i3 & 256) != 0) {
                    scrimColor = BottomSheetDefaults.INSTANCE.getScrimColor(composerStartRestartGroup, 6);
                    i4 &= -234881025;
                } else {
                    scrimColor = j3;
                }
                if (i9 != 0) {
                    function2M316getLambda$1294623166$material3 = ComposableSingletons$ModalBottomSheet_androidKt.INSTANCE.m316getLambda$1294623166$material3();
                } else {
                    function2M316getLambda$1294623166$material3 = function2;
                }
                if ((i3 & 1024) != 0) {
                    windowInsets3 = BottomSheetDefaults.INSTANCE.getWindowInsets(composerStartRestartGroup, 6);
                    i14 &= -15;
                } else {
                    windowInsets3 = windowInsets;
                }
                if (i12 != 0) {
                    properties = ModalBottomSheetDefaults.INSTANCE.getProperties();
                } else {
                    properties = modalBottomSheetProperties;
                }
                shape4 = expandedShape;
                modifier4 = modifier2;
                Function2 function1116 = function2M316getLambda$1294623166$material3;
                f5 = fM121getSheetMaxWidthD9Ej5fM;
                long j1119 = jM278contentColorForek8zF_U;
                f6 = fM6022constructorimpl;
                sheetState3 = sheetStateRememberModalBottomSheetState;
                j7 = j1119;
                j8 = scrimColor;
                function5 = function1116;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1342054200, i4, i14, "androidx.compose.material3.ModalBottomSheet (ModalBottomSheet.android.kt:343)");
            }
            int i21112 = i4 << 3;
            int i21113 = i14 << 3;
            composer2 = composerStartRestartGroup;
            ModalBottomSheetKt.m630ModalBottomSheetYbuCTN8(function0, modifier4, sheetState3, f5, false, shape4, containerColor, j7, f6, j8, function5, new Function2<Composer, Integer, WindowInsets>() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheet$1
                public final WindowInsets invoke(Composer composer3, int i21114) {
                    composer3.startReplaceGroup(-677688734);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-677688734, i21114, -1, "androidx.compose.material3.ModalBottomSheet.<anonymous> (ModalBottomSheet.android.kt:354)");
                    }
                    WindowInsets windowInsets4 = windowInsets3;
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer3.endReplaceGroup();
                    return windowInsets4;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    return invoke((Composer) obj, ((Number) obj2).intValue());
                }
            }, properties, function3, composer2, (i4 & 8190) | (i21112 & 458752) | (i21112 & 3670016) | (i21112 & 29360128) | (i21112 & 234881024) | (i21112 & 1879048192), ((i4 >> 27) & 14) | (i21113 & 896) | (i21113 & V4Signature.MAX_SIGNING_INFOS_SIZE), 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            f3 = f5;
            shape3 = shape4;
            j5 = j7;
            function4 = function5;
            float f112 = f6;
            windowInsets2 = windowInsets3;
            sheetState2 = sheetState3;
            j4 = containerColor;
            f4 = f112;
            j6 = j8;
            modalBottomSheetProperties2 = properties;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            f3 = f;
            shape3 = shape2;
            j4 = containerColor;
            modifier3 = modifier2;
            sheetState2 = sheetStateRememberModalBottomSheetState;
            j5 = jM278contentColorForek8zF_U;
            f4 = f2;
            j6 = j3;
            function4 = function2;
            windowInsets2 = windowInsets;
            modalBottomSheetProperties2 = modalBottomSheetProperties;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: a4a
                public final Object invoke(Object obj, Object obj2) {
                    return ModalBottomSheet_androidKt.b(function0, modifier3, sheetState2, f3, shape3, j4, j5, f4, j6, function4, windowInsets2, modalBottomSheetProperties2, function3, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: ModalBottomSheetDialog-sW7UJKQ, reason: not valid java name */
    public static final void m636ModalBottomSheetDialogsW7UJKQ(final Function0<Unit> function0, final long j, final ModalBottomSheetProperties modalBottomSheetProperties, final Animatable<Float, AnimationVector1D> animatable, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        ModalBottomSheetProperties modalBottomSheetProperties2;
        int i3;
        final LayoutDirection layoutDirection;
        boolean z;
        Object obj;
        Composer composerStartRestartGroup = composer.startRestartGroup(766784632);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(j) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            modalBottomSheetProperties2 = modalBottomSheetProperties;
            i2 |= composerStartRestartGroup.changed(modalBottomSheetProperties2) ? 256 : 128;
        } else {
            modalBottomSheetProperties2 = modalBottomSheetProperties;
        }
        if ((i & 3072) == 0) {
            i2 |= (i & 4096) == 0 ? composerStartRestartGroup.changed(animatable) : composerStartRestartGroup.changedInstance(animatable) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 16384 : 8192;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 9363) != 9362, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(766784632, i2, -1, "androidx.compose.material3.ModalBottomSheetDialog (ModalBottomSheet.android.kt:369)");
            }
            View view = (View) composerStartRestartGroup.consume(AndroidCompositionLocals_androidKt.getLocalView());
            Density density = (Density) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalDensity());
            LayoutDirection layoutDirection2 = (LayoutDirection) composerStartRestartGroup.consume(CompositionLocalsKt.getLocalLayoutDirection());
            CompositionContext compositionContextRememberCompositionContext = ComposablesKt.rememberCompositionContext(composerStartRestartGroup, 0);
            State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function2, composerStartRestartGroup, (i2 >> 12) & 14);
            Object[] objArr = new Object[0];
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new Function0() { // from class: y3a
                    public final Object invoke() {
                        return ModalBottomSheet_androidKt.a();
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            UUID uuid = (UUID) RememberSaveableKt.rememberSaveable(objArr, (Function0) objRememberedValue, composerStartRestartGroup, 48);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composerStartRestartGroup);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            CoroutineScope coroutineScope = (CoroutineScope) objRememberedValue2;
            boolean zChanged = composerStartRestartGroup.changed(view) | composerStartRestartGroup.changed(density);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue3 == companion.getEmpty()) {
                i3 = 256;
                ModalBottomSheetDialogWrapper modalBottomSheetDialogWrapper = new ModalBottomSheetDialogWrapper(function0, modalBottomSheetProperties2, j, view, layoutDirection2, density, uuid, animatable, coroutineScope, null);
                layoutDirection = layoutDirection2;
                z = true;
                modalBottomSheetDialogWrapper.setContent(compositionContextRememberCompositionContext, ComposableLambdaKt.composableLambdaInstance(-1051373467, true, new ModalBottomSheet_androidKt$ModalBottomSheetDialog$dialog$1$1$1(stateRememberUpdatedState)));
                composerStartRestartGroup.updateRememberedValue(modalBottomSheetDialogWrapper);
                obj = modalBottomSheetDialogWrapper;
            } else {
                layoutDirection = layoutDirection2;
                z = true;
                i3 = 256;
                obj = objRememberedValue3;
            }
            final ModalBottomSheetDialogWrapper modalBottomSheetDialogWrapper2 = (ModalBottomSheetDialogWrapper) obj;
            boolean zChangedInstance = composerStartRestartGroup.changedInstance(modalBottomSheetDialogWrapper2);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance || objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = new Function1() { // from class: androidx.compose.material3.l2
                    public final Object invoke(Object obj2) {
                        return ModalBottomSheet_androidKt.e(modalBottomSheetDialogWrapper2, (DisposableEffectScope) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            EffectsKt.DisposableEffect(modalBottomSheetDialogWrapper2, (Function1<? super DisposableEffectScope, ? extends DisposableEffectResult>) objRememberedValue4, composerStartRestartGroup, 0);
            int i4 = i2;
            boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(modalBottomSheetDialogWrapper2) | ((i4 & 14) == 4 ? z : false) | ((i4 & 896) == i3 ? z : false) | ((i4 & 112) == 32 ? z : false) | composerStartRestartGroup.changed(layoutDirection.ordinal());
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance2 || objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = new Function0() { // from class: androidx.compose.material3.m2
                    public final Object invoke() {
                        return ModalBottomSheet_androidKt.c(modalBottomSheetDialogWrapper2, function0, modalBottomSheetProperties, j, layoutDirection);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue5);
            }
            EffectsKt.SideEffect((Function0) objRememberedValue5, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: z3a
                public final Object invoke(Object obj2, Object obj3) {
                    return ModalBottomSheet_androidKt.d(function0, j, modalBottomSheetProperties, animatable, function2, i, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Function2<Composer, Integer, Unit> ModalBottomSheetDialog_sW7UJKQ$lambda$1(State<? extends Function2<? super Composer, ? super Integer, Unit>> state) {
        return state.getValue();
    }

    public static UUID a() {
        return UUID.randomUUID();
    }

    public static Unit b(Function0 function0, Modifier modifier, SheetState sheetState, float f, Shape shape, long j, long j2, float f2, long j3, Function2 function2, WindowInsets windowInsets, ModalBottomSheetProperties modalBottomSheetProperties, Function3 function3, int i, int i2, int i3, Composer composer, int i4) {
        m635ModalBottomSheetdYc4hso(function0, modifier, sheetState, f, shape, j, j2, f2, j3, function2, windowInsets, modalBottomSheetProperties, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    public static Unit c(ModalBottomSheetDialogWrapper modalBottomSheetDialogWrapper, Function0 function0, ModalBottomSheetProperties modalBottomSheetProperties, long j, LayoutDirection layoutDirection) {
        modalBottomSheetDialogWrapper.m629updateParameters9LQNqLg(function0, modalBottomSheetProperties, j, layoutDirection);
        return Unit.INSTANCE;
    }

    public static Unit d(Function0 function0, long j, ModalBottomSheetProperties modalBottomSheetProperties, Animatable animatable, Function2 function2, int i, Composer composer, int i2) {
        m636ModalBottomSheetDialogsW7UJKQ(function0, j, modalBottomSheetProperties, animatable, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static DisposableEffectResult e(final ModalBottomSheetDialogWrapper modalBottomSheetDialogWrapper, DisposableEffectScope disposableEffectScope) {
        modalBottomSheetDialogWrapper.show();
        return new DisposableEffectResult() { // from class: androidx.compose.material3.ModalBottomSheet_androidKt$ModalBottomSheetDialog_sW7UJKQ$lambda$8$lambda$7$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                modalBottomSheetDialogWrapper.dismiss();
                modalBottomSheetDialogWrapper.disposeComposition();
            }
        };
    }

    /* JADX INFO: renamed from: isDark-8_81llA, reason: not valid java name */
    public static final boolean m637isDark8_81llA(long j) {
        return !Color.m3135equalsimpl0(j, Color.INSTANCE.m3169getTransparent0d7_KjU()) && ((double) ColorKt.m3186luminance8_81llA(j)) <= 0.5d;
    }

    public static final boolean isFlagSecureEnabled(View view) {
        ViewGroup.LayoutParams layoutParams = view.getRootView().getLayoutParams();
        WindowManager.LayoutParams layoutParams2 = layoutParams instanceof WindowManager.LayoutParams ? (WindowManager.LayoutParams) layoutParams : null;
        return (layoutParams2 == null || (layoutParams2.flags & 8192) == 0) ? false : true;
    }
}
