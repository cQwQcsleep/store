package androidx.compose.material3;

import androidx.compose.material3.AndroidAlertDialog_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.window.DialogProperties;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aÜ\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0015\b\u0002\u0010\b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u000f2\b\b\u0002\u0010\u0012\u001a\u00020\u000f2\b\b\u0002\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u0016H\u0007¢\u0006\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"AlertDialog", "", "onDismissRequest", "Lkotlin/Function0;", "confirmButton", "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "dismissButton", "icon", "title", "text", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "iconContentColor", "titleContentColor", "textContentColor", "tonalElevation", "Landroidx/compose/ui/unit/Dp;", "properties", "Landroidx/compose/ui/window/DialogProperties;", "AlertDialog-Oix01E0", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;JJJJFLandroidx/compose/ui/window/DialogProperties;Landroidx/compose/runtime/Composer;III)V", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AndroidAlertDialog_androidKt {
    /* JADX WARN: Code duplicated, block: B:100:0x011a  */
    /* JADX WARN: Code duplicated, block: B:102:0x0120  */
    /* JADX WARN: Code duplicated, block: B:105:0x0129  */
    /* JADX WARN: Code duplicated, block: B:107:0x012d  */
    /* JADX WARN: Code duplicated, block: B:110:0x0133  */
    /* JADX WARN: Code duplicated, block: B:112:0x0139  */
    /* JADX WARN: Code duplicated, block: B:115:0x0142  */
    /* JADX WARN: Code duplicated, block: B:117:0x0147  */
    /* JADX WARN: Code duplicated, block: B:120:0x014e  */
    /* JADX WARN: Code duplicated, block: B:122:0x0154  */
    /* JADX WARN: Code duplicated, block: B:125:0x015d  */
    /* JADX WARN: Code duplicated, block: B:127:0x0162  */
    /* JADX WARN: Code duplicated, block: B:130:0x0168  */
    /* JADX WARN: Code duplicated, block: B:132:0x016d  */
    /* JADX WARN: Code duplicated, block: B:134:0x0171  */
    /* JADX WARN: Code duplicated, block: B:136:0x0179  */
    /* JADX WARN: Code duplicated, block: B:137:0x017c  */
    /* JADX WARN: Code duplicated, block: B:141:0x0184  */
    /* JADX WARN: Code duplicated, block: B:143:0x018b  */
    /* JADX WARN: Code duplicated, block: B:145:0x0191  */
    /* JADX WARN: Code duplicated, block: B:147:0x0199  */
    /* JADX WARN: Code duplicated, block: B:151:0x01a7  */
    /* JADX WARN: Code duplicated, block: B:155:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:158:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:160:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:179:0x020a A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:180:0x020c  */
    /* JADX WARN: Code duplicated, block: B:183:0x0212  */
    /* JADX WARN: Code duplicated, block: B:184:0x0214  */
    /* JADX WARN: Code duplicated, block: B:186:0x0218  */
    /* JADX WARN: Code duplicated, block: B:188:0x021b  */
    /* JADX WARN: Code duplicated, block: B:190:0x021e  */
    /* JADX WARN: Code duplicated, block: B:193:0x0226  */
    /* JADX WARN: Code duplicated, block: B:196:0x0233  */
    /* JADX WARN: Code duplicated, block: B:197:0x023c  */
    /* JADX WARN: Code duplicated, block: B:200:0x0242  */
    /* JADX WARN: Code duplicated, block: B:201:0x024c  */
    /* JADX WARN: Code duplicated, block: B:204:0x0252  */
    /* JADX WARN: Code duplicated, block: B:205:0x025b  */
    /* JADX WARN: Code duplicated, block: B:208:0x0261  */
    /* JADX WARN: Code duplicated, block: B:209:0x026b  */
    /* JADX WARN: Code duplicated, block: B:211:0x026e  */
    /* JADX WARN: Code duplicated, block: B:212:0x0275  */
    /* JADX WARN: Code duplicated, block: B:214:0x0279  */
    /* JADX WARN: Code duplicated, block: B:216:0x02ae  */
    /* JADX WARN: Code duplicated, block: B:219:0x02bc  */
    /* JADX WARN: Code duplicated, block: B:222:0x02d9  */
    /* JADX WARN: Code duplicated, block: B:224:0x02ed  */
    /* JADX WARN: Code duplicated, block: B:227:0x030a  */
    /* JADX WARN: Code duplicated, block: B:229:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:26:0x004c  */
    /* JADX WARN: Code duplicated, block: B:28:0x0051  */
    /* JADX WARN: Code duplicated, block: B:30:0x0055  */
    /* JADX WARN: Code duplicated, block: B:32:0x005d  */
    /* JADX WARN: Code duplicated, block: B:33:0x0060  */
    /* JADX WARN: Code duplicated, block: B:37:0x006c  */
    /* JADX WARN: Code duplicated, block: B:39:0x0071  */
    /* JADX WARN: Code duplicated, block: B:41:0x0075  */
    /* JADX WARN: Code duplicated, block: B:43:0x007d  */
    /* JADX WARN: Code duplicated, block: B:44:0x0080  */
    /* JADX WARN: Code duplicated, block: B:48:0x0088  */
    /* JADX WARN: Code duplicated, block: B:50:0x008d  */
    /* JADX WARN: Code duplicated, block: B:52:0x0091  */
    /* JADX WARN: Code duplicated, block: B:54:0x0099  */
    /* JADX WARN: Code duplicated, block: B:55:0x009c  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:60:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:62:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:64:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:69:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:70:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:72:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:75:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:79:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:81:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:83:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:84:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:90:0x0100  */
    /* JADX WARN: Code duplicated, block: B:92:0x0106  */
    /* JADX WARN: Code duplicated, block: B:95:0x010f  */
    /* JADX WARN: Code duplicated, block: B:97:0x0113  */
    /* JADX INFO: renamed from: AlertDialog-Oix01E0, reason: not valid java name */
    public static final void m77AlertDialogOix01E0(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function4, Function2<? super Composer, ? super Integer, Unit> function5, Function2<? super Composer, ? super Integer, Unit> function6, Shape shape, long j, long j2, long j3, long j4, float f, DialogProperties dialogProperties, Composer composer, final int i, final int i2, final int i3) {
        int i4;
        int i5;
        Modifier modifier2;
        int i6;
        int i7;
        int i8;
        int i9;
        Function2<? super Composer, ? super Integer, Unit> function7;
        int i10;
        int i11;
        Function2<? super Composer, ? super Integer, Unit> function8;
        int i12;
        int i13;
        Function2<? super Composer, ? super Integer, Unit> function9;
        int i14;
        Shape shape2;
        int i15;
        long j5;
        int i16;
        int i17;
        int i18;
        int i19;
        boolean z;
        Composer composer2;
        final Function2<? super Composer, ? super Integer, Unit> function10;
        final float f2;
        final DialogProperties dialogProperties2;
        final Shape shape3;
        final Modifier modifier3;
        final long j6;
        final long j7;
        final Function2<? super Composer, ? super Integer, Unit> function11;
        final Function2<? super Composer, ? super Integer, Unit> function12;
        final Function2<? super Composer, ? super Integer, Unit> function13;
        final long j8;
        final long j9;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Function2<? super Composer, ? super Integer, Unit> function14;
        long containerColor;
        long iconContentColor;
        long titleContentColor;
        long textContentColor;
        float fM69getTonalElevationD9Ej5fM;
        DialogProperties dialogProperties3;
        Function2<? super Composer, ? super Integer, Unit> function15;
        Function2<? super Composer, ? super Integer, Unit> function16;
        Shape shape4;
        Modifier modifier4;
        int i20;
        Function2<? super Composer, ? super Integer, Unit> function17;
        Function2<? super Composer, ? super Integer, Unit> function18;
        long j10;
        long j11;
        int i21;
        int i22;
        long j12;
        long j13;
        int i23;
        int i24;
        int i25;
        int i26;
        Composer composerStartRestartGroup = composer.startRestartGroup(94478519);
        if ((i3 & 1) != 0) {
            i4 = i | 6;
        } else if ((i & 6) == 0) {
            i4 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i4 = i;
        }
        if ((i3 & 2) == 0) {
            if ((i & 48) == 0) {
                i4 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
            }
            i5 = i3 & 4;
            if (i5 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    modifier2 = modifier;
                    if (composerStartRestartGroup.changed(modifier2)) {
                        i6 = 256;
                    } else {
                        i6 = 128;
                    }
                    i4 |= i6;
                }
                i7 = i3 & 8;
                if (i7 != 0) {
                    if ((i & 3072) == 0) {
                        if (composerStartRestartGroup.changedInstance(function3)) {
                            i8 = 2048;
                        } else {
                            i8 = 1024;
                        }
                        i4 |= i8;
                    }
                    i9 = i3 & 16;
                    if (i9 != 0) {
                        if ((i & 24576) == 0) {
                            function7 = function4;
                            if (composerStartRestartGroup.changedInstance(function7)) {
                                i10 = 16384;
                            } else {
                                i10 = 8192;
                            }
                            i4 |= i10;
                        }
                        i11 = i3 & 32;
                        if (i11 != 0) {
                            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                            function8 = function5;
                        } else {
                            function8 = function5;
                            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                                if (composerStartRestartGroup.changedInstance(function8)) {
                                    i12 = 131072;
                                } else {
                                    i12 = 65536;
                                }
                                i4 |= i12;
                            }
                        }
                        i13 = i3 & 64;
                        if (i13 != 0) {
                            i4 |= 1572864;
                            function9 = function6;
                        } else {
                            function9 = function6;
                            if ((i & 1572864) == 0) {
                                if (composerStartRestartGroup.changedInstance(function9)) {
                                    i14 = 1048576;
                                } else {
                                    i14 = 524288;
                                }
                                i4 |= i14;
                            }
                        }
                        if ((i & 12582912) == 0) {
                            if ((i3 & 128) == 0) {
                                shape2 = shape;
                                int i27 = composerStartRestartGroup.changed(shape2) ? 8388608 : 4194304;
                                i4 |= i27;
                            } else {
                                shape2 = shape;
                            }
                            i4 |= i27;
                        } else {
                            shape2 = shape;
                        }
                        if ((i & 100663296) != 0) {
                            if ((i3 & 256) == 0 || !composerStartRestartGroup.changed(j)) {
                                i26 = 33554432;
                            } else {
                                i26 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            }
                            i4 |= i26;
                        }
                        if ((805306368 & i) != 0) {
                            if ((i3 & 512) == 0 || !composerStartRestartGroup.changed(j2)) {
                                i25 = 268435456;
                            } else {
                                i25 = 536870912;
                            }
                            i4 |= i25;
                        }
                        if ((i2 & 6) == 0) {
                            if ((i3 & 1024) == 0 || !composerStartRestartGroup.changed(j3)) {
                                i24 = 2;
                            } else {
                                i24 = 4;
                            }
                            i15 = i2 | i24;
                        } else {
                            i15 = i2;
                        }
                        if ((i2 & 48) == 0) {
                            j5 = j4;
                            if ((i3 & 2048) == 0 || !composerStartRestartGroup.changed(j5)) {
                                i23 = 16;
                            } else {
                                i23 = 32;
                            }
                            i15 |= i23;
                        } else {
                            j5 = j4;
                        }
                        i16 = i3 & 4096;
                        if (i16 != 0) {
                            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                                if (composerStartRestartGroup.changed(f)) {
                                    i17 = 256;
                                } else {
                                    i17 = 128;
                                }
                                i15 |= i17;
                            }
                            i18 = i3 & 8192;
                            if (i18 != 0) {
                                i19 = i18;
                                if ((i2 & 3072) == 0) {
                                    i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                                }
                                if ((i4 & 306783379) == 306783378 || (i15 & 1171) != 1170) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                                    composerStartRestartGroup.startDefaults();
                                    if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                        if (i5 != 0) {
                                            modifier2 = Modifier.INSTANCE;
                                        }
                                        if (i7 != 0) {
                                            function14 = null;
                                        } else {
                                            function14 = function3;
                                        }
                                        if (i9 != 0) {
                                            function7 = null;
                                        }
                                        if (i11 != 0) {
                                            function8 = null;
                                        }
                                        if (i13 != 0) {
                                            function9 = null;
                                        }
                                        if ((i3 & 128) != 0) {
                                            i4 &= -29360129;
                                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                        }
                                        if ((i3 & 256) != 0) {
                                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                            i4 &= -234881025;
                                        } else {
                                            containerColor = j;
                                        }
                                        if ((i3 & 512) != 0) {
                                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                            i4 &= -1879048193;
                                        } else {
                                            iconContentColor = j2;
                                        }
                                        if ((i3 & 1024) != 0) {
                                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                            i15 &= -15;
                                        } else {
                                            titleContentColor = j3;
                                        }
                                        if ((i3 & 2048) != 0) {
                                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                            i15 &= -113;
                                        } else {
                                            textContentColor = j5;
                                        }
                                        if (i16 != 0) {
                                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                        } else {
                                            fM69getTonalElevationD9Ej5fM = f;
                                        }
                                        if (i19 != 0) {
                                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                        } else {
                                            dialogProperties3 = dialogProperties;
                                        }
                                        function15 = function8;
                                        function16 = function9;
                                        shape4 = shape2;
                                        modifier4 = modifier2;
                                        i20 = 94478519;
                                        int i28 = i4;
                                        function17 = function7;
                                        function18 = function14;
                                        j10 = containerColor;
                                        j11 = textContentColor;
                                        i21 = i28;
                                        i22 = i15;
                                        j12 = iconContentColor;
                                        j13 = titleContentColor;
                                    } else {
                                        composerStartRestartGroup.skipToGroupEnd();
                                        if ((i3 & 128) != 0) {
                                            i4 &= -29360129;
                                        }
                                        if ((i3 & 256) != 0) {
                                            i4 &= -234881025;
                                        }
                                        if ((i3 & 512) != 0) {
                                            i4 &= -1879048193;
                                        }
                                        if ((i3 & 1024) != 0) {
                                            i15 &= -15;
                                        }
                                        if ((i3 & 2048) != 0) {
                                            i15 &= -113;
                                        }
                                        j13 = j3;
                                        fM69getTonalElevationD9Ej5fM = f;
                                        dialogProperties3 = dialogProperties;
                                        j11 = j5;
                                        i21 = i4;
                                        function15 = function8;
                                        function16 = function9;
                                        shape4 = shape2;
                                        i22 = i15;
                                        modifier4 = modifier2;
                                        i20 = 94478519;
                                        j10 = j;
                                        j12 = j2;
                                        function17 = function7;
                                        function18 = function3;
                                    }
                                    composerStartRestartGroup.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                                    }
                                    composer2 = composerStartRestartGroup;
                                    AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    modifier3 = modifier4;
                                    function10 = function18;
                                    function11 = function17;
                                    function12 = function15;
                                    function13 = function16;
                                    shape3 = shape4;
                                    j8 = j10;
                                    j9 = j12;
                                    j6 = j13;
                                    j7 = j11;
                                    f2 = fM69getTonalElevationD9Ej5fM;
                                    dialogProperties2 = dialogProperties3;
                                } else {
                                    composer2 = composerStartRestartGroup;
                                    composer2.skipToGroupEnd();
                                    function10 = function3;
                                    f2 = f;
                                    dialogProperties2 = dialogProperties;
                                    shape3 = shape2;
                                    modifier3 = modifier2;
                                    j6 = j3;
                                    j7 = j5;
                                    function11 = function7;
                                    function12 = function8;
                                    function13 = function9;
                                    j8 = j;
                                    j9 = j2;
                                }
                                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                                if (scopeUpdateScopeEndRestartGroup != null) {
                                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                        public final Object invoke(Object obj, Object obj2) {
                                            return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                        }
                                    });
                                }
                            }
                            i15 |= 3072;
                            i19 = i18;
                            if ((i4 & 306783379) == 306783378) {
                                z = true;
                            } else {
                                z = true;
                            }
                            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                                composerStartRestartGroup.startDefaults();
                                if ((i & 1) != 0) {
                                    if (i5 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i7 != 0) {
                                        function14 = null;
                                    } else {
                                        function14 = function3;
                                    }
                                    if (i9 != 0) {
                                        function7 = null;
                                    }
                                    if (i11 != 0) {
                                        function8 = null;
                                    }
                                    if (i13 != 0) {
                                        function9 = null;
                                    }
                                    if ((i3 & 128) != 0) {
                                        i4 &= -29360129;
                                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    }
                                    if ((i3 & 256) != 0) {
                                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                        i4 &= -234881025;
                                    } else {
                                        containerColor = j;
                                    }
                                    if ((i3 & 512) != 0) {
                                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                        i4 &= -1879048193;
                                    } else {
                                        iconContentColor = j2;
                                    }
                                    if ((i3 & 1024) != 0) {
                                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                        i15 &= -15;
                                    } else {
                                        titleContentColor = j3;
                                    }
                                    if ((i3 & 2048) != 0) {
                                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                        i15 &= -113;
                                    } else {
                                        textContentColor = j5;
                                    }
                                    if (i16 != 0) {
                                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                    } else {
                                        fM69getTonalElevationD9Ej5fM = f;
                                    }
                                    if (i19 != 0) {
                                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                    } else {
                                        dialogProperties3 = dialogProperties;
                                    }
                                    function15 = function8;
                                    function16 = function9;
                                    shape4 = shape2;
                                    modifier4 = modifier2;
                                    i20 = 94478519;
                                    int i29 = i4;
                                    function17 = function7;
                                    function18 = function14;
                                    j10 = containerColor;
                                    j11 = textContentColor;
                                    i21 = i29;
                                    i22 = i15;
                                    j12 = iconContentColor;
                                    j13 = titleContentColor;
                                } else {
                                    if (i5 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i7 != 0) {
                                        function14 = null;
                                    } else {
                                        function14 = function3;
                                    }
                                    if (i9 != 0) {
                                        function7 = null;
                                    }
                                    if (i11 != 0) {
                                        function8 = null;
                                    }
                                    if (i13 != 0) {
                                        function9 = null;
                                    }
                                    if ((i3 & 128) != 0) {
                                        i4 &= -29360129;
                                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    }
                                    if ((i3 & 256) != 0) {
                                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                        i4 &= -234881025;
                                    } else {
                                        containerColor = j;
                                    }
                                    if ((i3 & 512) != 0) {
                                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                        i4 &= -1879048193;
                                    } else {
                                        iconContentColor = j2;
                                    }
                                    if ((i3 & 1024) != 0) {
                                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                        i15 &= -15;
                                    } else {
                                        titleContentColor = j3;
                                    }
                                    if ((i3 & 2048) != 0) {
                                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                        i15 &= -113;
                                    } else {
                                        textContentColor = j5;
                                    }
                                    if (i16 != 0) {
                                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                    } else {
                                        fM69getTonalElevationD9Ej5fM = f;
                                    }
                                    if (i19 != 0) {
                                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                    } else {
                                        dialogProperties3 = dialogProperties;
                                    }
                                    function15 = function8;
                                    function16 = function9;
                                    shape4 = shape2;
                                    modifier4 = modifier2;
                                    i20 = 94478519;
                                    int i210 = i4;
                                    function17 = function7;
                                    function18 = function14;
                                    j10 = containerColor;
                                    j11 = textContentColor;
                                    i21 = i210;
                                    i22 = i15;
                                    j12 = iconContentColor;
                                    j13 = titleContentColor;
                                }
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                                }
                                composer2 = composerStartRestartGroup;
                                AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier3 = modifier4;
                                function10 = function18;
                                function11 = function17;
                                function12 = function15;
                                function13 = function16;
                                shape3 = shape4;
                                j8 = j10;
                                j9 = j12;
                                j6 = j13;
                                j7 = j11;
                                f2 = fM69getTonalElevationD9Ej5fM;
                                dialogProperties2 = dialogProperties3;
                            } else {
                                composer2 = composerStartRestartGroup;
                                composer2.skipToGroupEnd();
                                function10 = function3;
                                f2 = f;
                                dialogProperties2 = dialogProperties;
                                shape3 = shape2;
                                modifier3 = modifier2;
                                j6 = j3;
                                j7 = j5;
                                function11 = function7;
                                function12 = function8;
                                function13 = function9;
                                j8 = j;
                                j9 = j2;
                            }
                            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                    public final Object invoke(Object obj, Object obj2) {
                                        return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i15 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                        i18 = i3 & 8192;
                        if (i18 != 0) {
                            i19 = i18;
                            if ((i2 & 3072) == 0) {
                                i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                            }
                            if ((i4 & 306783379) == 306783378) {
                                z = true;
                            } else {
                                z = true;
                            }
                            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                                composerStartRestartGroup.startDefaults();
                                if ((i & 1) != 0) {
                                    if (i5 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i7 != 0) {
                                        function14 = null;
                                    } else {
                                        function14 = function3;
                                    }
                                    if (i9 != 0) {
                                        function7 = null;
                                    }
                                    if (i11 != 0) {
                                        function8 = null;
                                    }
                                    if (i13 != 0) {
                                        function9 = null;
                                    }
                                    if ((i3 & 128) != 0) {
                                        i4 &= -29360129;
                                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    }
                                    if ((i3 & 256) != 0) {
                                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                        i4 &= -234881025;
                                    } else {
                                        containerColor = j;
                                    }
                                    if ((i3 & 512) != 0) {
                                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                        i4 &= -1879048193;
                                    } else {
                                        iconContentColor = j2;
                                    }
                                    if ((i3 & 1024) != 0) {
                                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                        i15 &= -15;
                                    } else {
                                        titleContentColor = j3;
                                    }
                                    if ((i3 & 2048) != 0) {
                                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                        i15 &= -113;
                                    } else {
                                        textContentColor = j5;
                                    }
                                    if (i16 != 0) {
                                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                    } else {
                                        fM69getTonalElevationD9Ej5fM = f;
                                    }
                                    if (i19 != 0) {
                                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                    } else {
                                        dialogProperties3 = dialogProperties;
                                    }
                                    function15 = function8;
                                    function16 = function9;
                                    shape4 = shape2;
                                    modifier4 = modifier2;
                                    i20 = 94478519;
                                    int i211 = i4;
                                    function17 = function7;
                                    function18 = function14;
                                    j10 = containerColor;
                                    j11 = textContentColor;
                                    i21 = i211;
                                    i22 = i15;
                                    j12 = iconContentColor;
                                    j13 = titleContentColor;
                                } else {
                                    if (i5 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i7 != 0) {
                                        function14 = null;
                                    } else {
                                        function14 = function3;
                                    }
                                    if (i9 != 0) {
                                        function7 = null;
                                    }
                                    if (i11 != 0) {
                                        function8 = null;
                                    }
                                    if (i13 != 0) {
                                        function9 = null;
                                    }
                                    if ((i3 & 128) != 0) {
                                        i4 &= -29360129;
                                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    }
                                    if ((i3 & 256) != 0) {
                                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                        i4 &= -234881025;
                                    } else {
                                        containerColor = j;
                                    }
                                    if ((i3 & 512) != 0) {
                                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                        i4 &= -1879048193;
                                    } else {
                                        iconContentColor = j2;
                                    }
                                    if ((i3 & 1024) != 0) {
                                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                        i15 &= -15;
                                    } else {
                                        titleContentColor = j3;
                                    }
                                    if ((i3 & 2048) != 0) {
                                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                        i15 &= -113;
                                    } else {
                                        textContentColor = j5;
                                    }
                                    if (i16 != 0) {
                                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                    } else {
                                        fM69getTonalElevationD9Ej5fM = f;
                                    }
                                    if (i19 != 0) {
                                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                    } else {
                                        dialogProperties3 = dialogProperties;
                                    }
                                    function15 = function8;
                                    function16 = function9;
                                    shape4 = shape2;
                                    modifier4 = modifier2;
                                    i20 = 94478519;
                                    int i212 = i4;
                                    function17 = function7;
                                    function18 = function14;
                                    j10 = containerColor;
                                    j11 = textContentColor;
                                    i21 = i212;
                                    i22 = i15;
                                    j12 = iconContentColor;
                                    j13 = titleContentColor;
                                }
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                                }
                                composer2 = composerStartRestartGroup;
                                AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier3 = modifier4;
                                function10 = function18;
                                function11 = function17;
                                function12 = function15;
                                function13 = function16;
                                shape3 = shape4;
                                j8 = j10;
                                j9 = j12;
                                j6 = j13;
                                j7 = j11;
                                f2 = fM69getTonalElevationD9Ej5fM;
                                dialogProperties2 = dialogProperties3;
                            } else {
                                composer2 = composerStartRestartGroup;
                                composer2.skipToGroupEnd();
                                function10 = function3;
                                f2 = f;
                                dialogProperties2 = dialogProperties;
                                shape3 = shape2;
                                modifier3 = modifier2;
                                j6 = j3;
                                j7 = j5;
                                function11 = function7;
                                function12 = function8;
                                function13 = function9;
                                j8 = j;
                                j9 = j2;
                            }
                            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                    public final Object invoke(Object obj, Object obj2) {
                                        return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i15 |= 3072;
                        i19 = i18;
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i213 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i213;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i214 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i214;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                            }
                            composer2 = composerStartRestartGroup;
                            AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            function10 = function18;
                            function11 = function17;
                            function12 = function15;
                            function13 = function16;
                            shape3 = shape4;
                            j8 = j10;
                            j9 = j12;
                            j6 = j13;
                            j7 = j11;
                            f2 = fM69getTonalElevationD9Ej5fM;
                            dialogProperties2 = dialogProperties3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function3;
                            f2 = f;
                            dialogProperties2 = dialogProperties;
                            shape3 = shape2;
                            modifier3 = modifier2;
                            j6 = j3;
                            j7 = j5;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            j8 = j;
                            j9 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                public final Object invoke(Object obj, Object obj2) {
                                    return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i4 |= 24576;
                    function7 = function4;
                    i11 = i3 & 32;
                    if (i11 != 0) {
                        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        function8 = function5;
                    } else {
                        function8 = function5;
                        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changedInstance(function8)) {
                                i12 = 131072;
                            } else {
                                i12 = 65536;
                            }
                            i4 |= i12;
                        }
                    }
                    i13 = i3 & 64;
                    if (i13 != 0) {
                        i4 |= 1572864;
                        function9 = function6;
                    } else {
                        function9 = function6;
                        if ((i & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function9)) {
                                i14 = 1048576;
                            } else {
                                i14 = 524288;
                            }
                            i4 |= i14;
                        }
                    }
                    if ((i & 12582912) == 0) {
                        if ((i3 & 128) == 0) {
                            shape2 = shape;
                            if (composerStartRestartGroup.changed(shape2)) {
                            }
                            i4 |= i27;
                        } else {
                            shape2 = shape;
                        }
                        i4 |= i27;
                    } else {
                        shape2 = shape;
                    }
                    if ((i & 100663296) != 0) {
                        if ((i3 & 256) == 0) {
                            i26 = 33554432;
                        } else {
                            i26 = 33554432;
                        }
                        i4 |= i26;
                    }
                    if ((805306368 & i) != 0) {
                        if ((i3 & 512) == 0) {
                            i25 = 268435456;
                        } else {
                            i25 = 268435456;
                        }
                        i4 |= i25;
                    }
                    if ((i2 & 6) == 0) {
                        if ((i3 & 1024) == 0) {
                            i24 = 2;
                        } else {
                            i24 = 2;
                        }
                        i15 = i2 | i24;
                    } else {
                        i15 = i2;
                    }
                    if ((i2 & 48) == 0) {
                        j5 = j4;
                        if ((i3 & 2048) == 0) {
                            i23 = 16;
                        } else {
                            i23 = 16;
                        }
                        i15 |= i23;
                    } else {
                        j5 = j4;
                    }
                    i16 = i3 & 4096;
                    if (i16 != 0) {
                        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                            if (composerStartRestartGroup.changed(f)) {
                                i17 = 256;
                            } else {
                                i17 = 128;
                            }
                            i15 |= i17;
                        }
                        i18 = i3 & 8192;
                        if (i18 != 0) {
                            i19 = i18;
                            if ((i2 & 3072) == 0) {
                                i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                            }
                            if ((i4 & 306783379) == 306783378) {
                                z = true;
                            } else {
                                z = true;
                            }
                            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                                composerStartRestartGroup.startDefaults();
                                if ((i & 1) != 0) {
                                    if (i5 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i7 != 0) {
                                        function14 = null;
                                    } else {
                                        function14 = function3;
                                    }
                                    if (i9 != 0) {
                                        function7 = null;
                                    }
                                    if (i11 != 0) {
                                        function8 = null;
                                    }
                                    if (i13 != 0) {
                                        function9 = null;
                                    }
                                    if ((i3 & 128) != 0) {
                                        i4 &= -29360129;
                                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    }
                                    if ((i3 & 256) != 0) {
                                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                        i4 &= -234881025;
                                    } else {
                                        containerColor = j;
                                    }
                                    if ((i3 & 512) != 0) {
                                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                        i4 &= -1879048193;
                                    } else {
                                        iconContentColor = j2;
                                    }
                                    if ((i3 & 1024) != 0) {
                                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                        i15 &= -15;
                                    } else {
                                        titleContentColor = j3;
                                    }
                                    if ((i3 & 2048) != 0) {
                                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                        i15 &= -113;
                                    } else {
                                        textContentColor = j5;
                                    }
                                    if (i16 != 0) {
                                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                    } else {
                                        fM69getTonalElevationD9Ej5fM = f;
                                    }
                                    if (i19 != 0) {
                                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                    } else {
                                        dialogProperties3 = dialogProperties;
                                    }
                                    function15 = function8;
                                    function16 = function9;
                                    shape4 = shape2;
                                    modifier4 = modifier2;
                                    i20 = 94478519;
                                    int i215 = i4;
                                    function17 = function7;
                                    function18 = function14;
                                    j10 = containerColor;
                                    j11 = textContentColor;
                                    i21 = i215;
                                    i22 = i15;
                                    j12 = iconContentColor;
                                    j13 = titleContentColor;
                                } else {
                                    if (i5 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i7 != 0) {
                                        function14 = null;
                                    } else {
                                        function14 = function3;
                                    }
                                    if (i9 != 0) {
                                        function7 = null;
                                    }
                                    if (i11 != 0) {
                                        function8 = null;
                                    }
                                    if (i13 != 0) {
                                        function9 = null;
                                    }
                                    if ((i3 & 128) != 0) {
                                        i4 &= -29360129;
                                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    }
                                    if ((i3 & 256) != 0) {
                                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                        i4 &= -234881025;
                                    } else {
                                        containerColor = j;
                                    }
                                    if ((i3 & 512) != 0) {
                                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                        i4 &= -1879048193;
                                    } else {
                                        iconContentColor = j2;
                                    }
                                    if ((i3 & 1024) != 0) {
                                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                        i15 &= -15;
                                    } else {
                                        titleContentColor = j3;
                                    }
                                    if ((i3 & 2048) != 0) {
                                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                        i15 &= -113;
                                    } else {
                                        textContentColor = j5;
                                    }
                                    if (i16 != 0) {
                                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                    } else {
                                        fM69getTonalElevationD9Ej5fM = f;
                                    }
                                    if (i19 != 0) {
                                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                    } else {
                                        dialogProperties3 = dialogProperties;
                                    }
                                    function15 = function8;
                                    function16 = function9;
                                    shape4 = shape2;
                                    modifier4 = modifier2;
                                    i20 = 94478519;
                                    int i216 = i4;
                                    function17 = function7;
                                    function18 = function14;
                                    j10 = containerColor;
                                    j11 = textContentColor;
                                    i21 = i216;
                                    i22 = i15;
                                    j12 = iconContentColor;
                                    j13 = titleContentColor;
                                }
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                                }
                                composer2 = composerStartRestartGroup;
                                AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier3 = modifier4;
                                function10 = function18;
                                function11 = function17;
                                function12 = function15;
                                function13 = function16;
                                shape3 = shape4;
                                j8 = j10;
                                j9 = j12;
                                j6 = j13;
                                j7 = j11;
                                f2 = fM69getTonalElevationD9Ej5fM;
                                dialogProperties2 = dialogProperties3;
                            } else {
                                composer2 = composerStartRestartGroup;
                                composer2.skipToGroupEnd();
                                function10 = function3;
                                f2 = f;
                                dialogProperties2 = dialogProperties;
                                shape3 = shape2;
                                modifier3 = modifier2;
                                j6 = j3;
                                j7 = j5;
                                function11 = function7;
                                function12 = function8;
                                function13 = function9;
                                j8 = j;
                                j9 = j2;
                            }
                            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                    public final Object invoke(Object obj, Object obj2) {
                                        return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i15 |= 3072;
                        i19 = i18;
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i217 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i217;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i218 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i218;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                            }
                            composer2 = composerStartRestartGroup;
                            AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            function10 = function18;
                            function11 = function17;
                            function12 = function15;
                            function13 = function16;
                            shape3 = shape4;
                            j8 = j10;
                            j9 = j12;
                            j6 = j13;
                            j7 = j11;
                            f2 = fM69getTonalElevationD9Ej5fM;
                            dialogProperties2 = dialogProperties3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function3;
                            f2 = f;
                            dialogProperties2 = dialogProperties;
                            shape3 = shape2;
                            modifier3 = modifier2;
                            j6 = j3;
                            j7 = j5;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            j8 = j;
                            j9 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                public final Object invoke(Object obj, Object obj2) {
                                    return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i15 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                    i18 = i3 & 8192;
                    if (i18 != 0) {
                        i19 = i18;
                        if ((i2 & 3072) == 0) {
                            i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                        }
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i219 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i219;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i2110 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i2110;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                            }
                            composer2 = composerStartRestartGroup;
                            AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            function10 = function18;
                            function11 = function17;
                            function12 = function15;
                            function13 = function16;
                            shape3 = shape4;
                            j8 = j10;
                            j9 = j12;
                            j6 = j13;
                            j7 = j11;
                            f2 = fM69getTonalElevationD9Ej5fM;
                            dialogProperties2 = dialogProperties3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function3;
                            f2 = f;
                            dialogProperties2 = dialogProperties;
                            shape3 = shape2;
                            modifier3 = modifier2;
                            j6 = j3;
                            j7 = j5;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            j8 = j;
                            j9 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                public final Object invoke(Object obj, Object obj2) {
                                    return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i15 |= 3072;
                    i19 = i18;
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i2111 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i2111;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i2112 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i2112;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 3072;
                i9 = i3 & 16;
                if (i9 != 0) {
                    if ((i & 24576) == 0) {
                        function7 = function4;
                        if (composerStartRestartGroup.changedInstance(function7)) {
                            i10 = 16384;
                        } else {
                            i10 = 8192;
                        }
                        i4 |= i10;
                    }
                    i11 = i3 & 32;
                    if (i11 != 0) {
                        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        function8 = function5;
                    } else {
                        function8 = function5;
                        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changedInstance(function8)) {
                                i12 = 131072;
                            } else {
                                i12 = 65536;
                            }
                            i4 |= i12;
                        }
                    }
                    i13 = i3 & 64;
                    if (i13 != 0) {
                        i4 |= 1572864;
                        function9 = function6;
                    } else {
                        function9 = function6;
                        if ((i & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function9)) {
                                i14 = 1048576;
                            } else {
                                i14 = 524288;
                            }
                            i4 |= i14;
                        }
                    }
                    if ((i & 12582912) == 0) {
                        if ((i3 & 128) == 0) {
                            shape2 = shape;
                            if (composerStartRestartGroup.changed(shape2)) {
                            }
                            i4 |= i27;
                        } else {
                            shape2 = shape;
                        }
                        i4 |= i27;
                    } else {
                        shape2 = shape;
                    }
                    if ((i & 100663296) != 0) {
                        if ((i3 & 256) == 0) {
                            i26 = 33554432;
                        } else {
                            i26 = 33554432;
                        }
                        i4 |= i26;
                    }
                    if ((805306368 & i) != 0) {
                        if ((i3 & 512) == 0) {
                            i25 = 268435456;
                        } else {
                            i25 = 268435456;
                        }
                        i4 |= i25;
                    }
                    if ((i2 & 6) == 0) {
                        if ((i3 & 1024) == 0) {
                            i24 = 2;
                        } else {
                            i24 = 2;
                        }
                        i15 = i2 | i24;
                    } else {
                        i15 = i2;
                    }
                    if ((i2 & 48) == 0) {
                        j5 = j4;
                        if ((i3 & 2048) == 0) {
                            i23 = 16;
                        } else {
                            i23 = 16;
                        }
                        i15 |= i23;
                    } else {
                        j5 = j4;
                    }
                    i16 = i3 & 4096;
                    if (i16 != 0) {
                        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                            if (composerStartRestartGroup.changed(f)) {
                                i17 = 256;
                            } else {
                                i17 = 128;
                            }
                            i15 |= i17;
                        }
                        i18 = i3 & 8192;
                        if (i18 != 0) {
                            i19 = i18;
                            if ((i2 & 3072) == 0) {
                                i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                            }
                            if ((i4 & 306783379) == 306783378) {
                                z = true;
                            } else {
                                z = true;
                            }
                            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                                composerStartRestartGroup.startDefaults();
                                if ((i & 1) != 0) {
                                    if (i5 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i7 != 0) {
                                        function14 = null;
                                    } else {
                                        function14 = function3;
                                    }
                                    if (i9 != 0) {
                                        function7 = null;
                                    }
                                    if (i11 != 0) {
                                        function8 = null;
                                    }
                                    if (i13 != 0) {
                                        function9 = null;
                                    }
                                    if ((i3 & 128) != 0) {
                                        i4 &= -29360129;
                                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    }
                                    if ((i3 & 256) != 0) {
                                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                        i4 &= -234881025;
                                    } else {
                                        containerColor = j;
                                    }
                                    if ((i3 & 512) != 0) {
                                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                        i4 &= -1879048193;
                                    } else {
                                        iconContentColor = j2;
                                    }
                                    if ((i3 & 1024) != 0) {
                                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                        i15 &= -15;
                                    } else {
                                        titleContentColor = j3;
                                    }
                                    if ((i3 & 2048) != 0) {
                                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                        i15 &= -113;
                                    } else {
                                        textContentColor = j5;
                                    }
                                    if (i16 != 0) {
                                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                    } else {
                                        fM69getTonalElevationD9Ej5fM = f;
                                    }
                                    if (i19 != 0) {
                                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                    } else {
                                        dialogProperties3 = dialogProperties;
                                    }
                                    function15 = function8;
                                    function16 = function9;
                                    shape4 = shape2;
                                    modifier4 = modifier2;
                                    i20 = 94478519;
                                    int i2113 = i4;
                                    function17 = function7;
                                    function18 = function14;
                                    j10 = containerColor;
                                    j11 = textContentColor;
                                    i21 = i2113;
                                    i22 = i15;
                                    j12 = iconContentColor;
                                    j13 = titleContentColor;
                                } else {
                                    if (i5 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i7 != 0) {
                                        function14 = null;
                                    } else {
                                        function14 = function3;
                                    }
                                    if (i9 != 0) {
                                        function7 = null;
                                    }
                                    if (i11 != 0) {
                                        function8 = null;
                                    }
                                    if (i13 != 0) {
                                        function9 = null;
                                    }
                                    if ((i3 & 128) != 0) {
                                        i4 &= -29360129;
                                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    }
                                    if ((i3 & 256) != 0) {
                                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                        i4 &= -234881025;
                                    } else {
                                        containerColor = j;
                                    }
                                    if ((i3 & 512) != 0) {
                                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                        i4 &= -1879048193;
                                    } else {
                                        iconContentColor = j2;
                                    }
                                    if ((i3 & 1024) != 0) {
                                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                        i15 &= -15;
                                    } else {
                                        titleContentColor = j3;
                                    }
                                    if ((i3 & 2048) != 0) {
                                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                        i15 &= -113;
                                    } else {
                                        textContentColor = j5;
                                    }
                                    if (i16 != 0) {
                                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                    } else {
                                        fM69getTonalElevationD9Ej5fM = f;
                                    }
                                    if (i19 != 0) {
                                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                    } else {
                                        dialogProperties3 = dialogProperties;
                                    }
                                    function15 = function8;
                                    function16 = function9;
                                    shape4 = shape2;
                                    modifier4 = modifier2;
                                    i20 = 94478519;
                                    int i2114 = i4;
                                    function17 = function7;
                                    function18 = function14;
                                    j10 = containerColor;
                                    j11 = textContentColor;
                                    i21 = i2114;
                                    i22 = i15;
                                    j12 = iconContentColor;
                                    j13 = titleContentColor;
                                }
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                                }
                                composer2 = composerStartRestartGroup;
                                AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier3 = modifier4;
                                function10 = function18;
                                function11 = function17;
                                function12 = function15;
                                function13 = function16;
                                shape3 = shape4;
                                j8 = j10;
                                j9 = j12;
                                j6 = j13;
                                j7 = j11;
                                f2 = fM69getTonalElevationD9Ej5fM;
                                dialogProperties2 = dialogProperties3;
                            } else {
                                composer2 = composerStartRestartGroup;
                                composer2.skipToGroupEnd();
                                function10 = function3;
                                f2 = f;
                                dialogProperties2 = dialogProperties;
                                shape3 = shape2;
                                modifier3 = modifier2;
                                j6 = j3;
                                j7 = j5;
                                function11 = function7;
                                function12 = function8;
                                function13 = function9;
                                j8 = j;
                                j9 = j2;
                            }
                            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                    public final Object invoke(Object obj, Object obj2) {
                                        return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i15 |= 3072;
                        i19 = i18;
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i2115 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i2115;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i2116 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i2116;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                            }
                            composer2 = composerStartRestartGroup;
                            AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            function10 = function18;
                            function11 = function17;
                            function12 = function15;
                            function13 = function16;
                            shape3 = shape4;
                            j8 = j10;
                            j9 = j12;
                            j6 = j13;
                            j7 = j11;
                            f2 = fM69getTonalElevationD9Ej5fM;
                            dialogProperties2 = dialogProperties3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function3;
                            f2 = f;
                            dialogProperties2 = dialogProperties;
                            shape3 = shape2;
                            modifier3 = modifier2;
                            j6 = j3;
                            j7 = j5;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            j8 = j;
                            j9 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                public final Object invoke(Object obj, Object obj2) {
                                    return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i15 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                    i18 = i3 & 8192;
                    if (i18 != 0) {
                        i19 = i18;
                        if ((i2 & 3072) == 0) {
                            i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                        }
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i2117 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i2117;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i2118 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i2118;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                            }
                            composer2 = composerStartRestartGroup;
                            AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            function10 = function18;
                            function11 = function17;
                            function12 = function15;
                            function13 = function16;
                            shape3 = shape4;
                            j8 = j10;
                            j9 = j12;
                            j6 = j13;
                            j7 = j11;
                            f2 = fM69getTonalElevationD9Ej5fM;
                            dialogProperties2 = dialogProperties3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function3;
                            f2 = f;
                            dialogProperties2 = dialogProperties;
                            shape3 = shape2;
                            modifier3 = modifier2;
                            j6 = j3;
                            j7 = j5;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            j8 = j;
                            j9 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                public final Object invoke(Object obj, Object obj2) {
                                    return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i15 |= 3072;
                    i19 = i18;
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i2119 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i2119;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i21110 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i21110;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 24576;
                function7 = function4;
                i11 = i3 & 32;
                if (i11 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    function8 = function5;
                } else {
                    function8 = function5;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changedInstance(function8)) {
                            i12 = 131072;
                        } else {
                            i12 = 65536;
                        }
                        i4 |= i12;
                    }
                }
                i13 = i3 & 64;
                if (i13 != 0) {
                    i4 |= 1572864;
                    function9 = function6;
                } else {
                    function9 = function6;
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function9)) {
                            i14 = 1048576;
                        } else {
                            i14 = 524288;
                        }
                        i4 |= i14;
                    }
                }
                if ((i & 12582912) == 0) {
                    if ((i3 & 128) == 0) {
                        shape2 = shape;
                        if (composerStartRestartGroup.changed(shape2)) {
                        }
                        i4 |= i27;
                    } else {
                        shape2 = shape;
                    }
                    i4 |= i27;
                } else {
                    shape2 = shape;
                }
                if ((i & 100663296) != 0) {
                    if ((i3 & 256) == 0) {
                        i26 = 33554432;
                    } else {
                        i26 = 33554432;
                    }
                    i4 |= i26;
                }
                if ((805306368 & i) != 0) {
                    if ((i3 & 512) == 0) {
                        i25 = 268435456;
                    } else {
                        i25 = 268435456;
                    }
                    i4 |= i25;
                }
                if ((i2 & 6) == 0) {
                    if ((i3 & 1024) == 0) {
                        i24 = 2;
                    } else {
                        i24 = 2;
                    }
                    i15 = i2 | i24;
                } else {
                    i15 = i2;
                }
                if ((i2 & 48) == 0) {
                    j5 = j4;
                    if ((i3 & 2048) == 0) {
                        i23 = 16;
                    } else {
                        i23 = 16;
                    }
                    i15 |= i23;
                } else {
                    j5 = j4;
                }
                i16 = i3 & 4096;
                if (i16 != 0) {
                    if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                        if (composerStartRestartGroup.changed(f)) {
                            i17 = 256;
                        } else {
                            i17 = 128;
                        }
                        i15 |= i17;
                    }
                    i18 = i3 & 8192;
                    if (i18 != 0) {
                        i19 = i18;
                        if ((i2 & 3072) == 0) {
                            i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                        }
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i21111 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i21111;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i21112 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i21112;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                            }
                            composer2 = composerStartRestartGroup;
                            AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            function10 = function18;
                            function11 = function17;
                            function12 = function15;
                            function13 = function16;
                            shape3 = shape4;
                            j8 = j10;
                            j9 = j12;
                            j6 = j13;
                            j7 = j11;
                            f2 = fM69getTonalElevationD9Ej5fM;
                            dialogProperties2 = dialogProperties3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function3;
                            f2 = f;
                            dialogProperties2 = dialogProperties;
                            shape3 = shape2;
                            modifier3 = modifier2;
                            j6 = j3;
                            j7 = j5;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            j8 = j;
                            j9 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                public final Object invoke(Object obj, Object obj2) {
                                    return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i15 |= 3072;
                    i19 = i18;
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i21113 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i21113;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i21114 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i21114;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i15 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                i18 = i3 & 8192;
                if (i18 != 0) {
                    i19 = i18;
                    if ((i2 & 3072) == 0) {
                        i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i21115 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i21115;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i21116 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i21116;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i15 |= 3072;
                i19 = i18;
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21117 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21117;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21118 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21118;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                    }
                    composer2 = composerStartRestartGroup;
                    AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    function10 = function18;
                    function11 = function17;
                    function12 = function15;
                    function13 = function16;
                    shape3 = shape4;
                    j8 = j10;
                    j9 = j12;
                    j6 = j13;
                    j7 = j11;
                    f2 = fM69getTonalElevationD9Ej5fM;
                    dialogProperties2 = dialogProperties3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function3;
                    f2 = f;
                    dialogProperties2 = dialogProperties;
                    shape3 = shape2;
                    modifier3 = modifier2;
                    j6 = j3;
                    j7 = j5;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    j8 = j;
                    j9 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            modifier2 = modifier;
            i7 = i3 & 8;
            if (i7 != 0) {
                if ((i & 3072) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 2048;
                    } else {
                        i8 = 1024;
                    }
                    i4 |= i8;
                }
                i9 = i3 & 16;
                if (i9 != 0) {
                    if ((i & 24576) == 0) {
                        function7 = function4;
                        if (composerStartRestartGroup.changedInstance(function7)) {
                            i10 = 16384;
                        } else {
                            i10 = 8192;
                        }
                        i4 |= i10;
                    }
                    i11 = i3 & 32;
                    if (i11 != 0) {
                        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        function8 = function5;
                    } else {
                        function8 = function5;
                        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changedInstance(function8)) {
                                i12 = 131072;
                            } else {
                                i12 = 65536;
                            }
                            i4 |= i12;
                        }
                    }
                    i13 = i3 & 64;
                    if (i13 != 0) {
                        i4 |= 1572864;
                        function9 = function6;
                    } else {
                        function9 = function6;
                        if ((i & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function9)) {
                                i14 = 1048576;
                            } else {
                                i14 = 524288;
                            }
                            i4 |= i14;
                        }
                    }
                    if ((i & 12582912) == 0) {
                        if ((i3 & 128) == 0) {
                            shape2 = shape;
                            if (composerStartRestartGroup.changed(shape2)) {
                            }
                            i4 |= i27;
                        } else {
                            shape2 = shape;
                        }
                        i4 |= i27;
                    } else {
                        shape2 = shape;
                    }
                    if ((i & 100663296) != 0) {
                        if ((i3 & 256) == 0) {
                            i26 = 33554432;
                        } else {
                            i26 = 33554432;
                        }
                        i4 |= i26;
                    }
                    if ((805306368 & i) != 0) {
                        if ((i3 & 512) == 0) {
                            i25 = 268435456;
                        } else {
                            i25 = 268435456;
                        }
                        i4 |= i25;
                    }
                    if ((i2 & 6) == 0) {
                        if ((i3 & 1024) == 0) {
                            i24 = 2;
                        } else {
                            i24 = 2;
                        }
                        i15 = i2 | i24;
                    } else {
                        i15 = i2;
                    }
                    if ((i2 & 48) == 0) {
                        j5 = j4;
                        if ((i3 & 2048) == 0) {
                            i23 = 16;
                        } else {
                            i23 = 16;
                        }
                        i15 |= i23;
                    } else {
                        j5 = j4;
                    }
                    i16 = i3 & 4096;
                    if (i16 != 0) {
                        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                            if (composerStartRestartGroup.changed(f)) {
                                i17 = 256;
                            } else {
                                i17 = 128;
                            }
                            i15 |= i17;
                        }
                        i18 = i3 & 8192;
                        if (i18 != 0) {
                            i19 = i18;
                            if ((i2 & 3072) == 0) {
                                i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                            }
                            if ((i4 & 306783379) == 306783378) {
                                z = true;
                            } else {
                                z = true;
                            }
                            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                                composerStartRestartGroup.startDefaults();
                                if ((i & 1) != 0) {
                                    if (i5 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i7 != 0) {
                                        function14 = null;
                                    } else {
                                        function14 = function3;
                                    }
                                    if (i9 != 0) {
                                        function7 = null;
                                    }
                                    if (i11 != 0) {
                                        function8 = null;
                                    }
                                    if (i13 != 0) {
                                        function9 = null;
                                    }
                                    if ((i3 & 128) != 0) {
                                        i4 &= -29360129;
                                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    }
                                    if ((i3 & 256) != 0) {
                                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                        i4 &= -234881025;
                                    } else {
                                        containerColor = j;
                                    }
                                    if ((i3 & 512) != 0) {
                                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                        i4 &= -1879048193;
                                    } else {
                                        iconContentColor = j2;
                                    }
                                    if ((i3 & 1024) != 0) {
                                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                        i15 &= -15;
                                    } else {
                                        titleContentColor = j3;
                                    }
                                    if ((i3 & 2048) != 0) {
                                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                        i15 &= -113;
                                    } else {
                                        textContentColor = j5;
                                    }
                                    if (i16 != 0) {
                                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                    } else {
                                        fM69getTonalElevationD9Ej5fM = f;
                                    }
                                    if (i19 != 0) {
                                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                    } else {
                                        dialogProperties3 = dialogProperties;
                                    }
                                    function15 = function8;
                                    function16 = function9;
                                    shape4 = shape2;
                                    modifier4 = modifier2;
                                    i20 = 94478519;
                                    int i21119 = i4;
                                    function17 = function7;
                                    function18 = function14;
                                    j10 = containerColor;
                                    j11 = textContentColor;
                                    i21 = i21119;
                                    i22 = i15;
                                    j12 = iconContentColor;
                                    j13 = titleContentColor;
                                } else {
                                    if (i5 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i7 != 0) {
                                        function14 = null;
                                    } else {
                                        function14 = function3;
                                    }
                                    if (i9 != 0) {
                                        function7 = null;
                                    }
                                    if (i11 != 0) {
                                        function8 = null;
                                    }
                                    if (i13 != 0) {
                                        function9 = null;
                                    }
                                    if ((i3 & 128) != 0) {
                                        i4 &= -29360129;
                                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    }
                                    if ((i3 & 256) != 0) {
                                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                        i4 &= -234881025;
                                    } else {
                                        containerColor = j;
                                    }
                                    if ((i3 & 512) != 0) {
                                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                        i4 &= -1879048193;
                                    } else {
                                        iconContentColor = j2;
                                    }
                                    if ((i3 & 1024) != 0) {
                                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                        i15 &= -15;
                                    } else {
                                        titleContentColor = j3;
                                    }
                                    if ((i3 & 2048) != 0) {
                                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                        i15 &= -113;
                                    } else {
                                        textContentColor = j5;
                                    }
                                    if (i16 != 0) {
                                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                    } else {
                                        fM69getTonalElevationD9Ej5fM = f;
                                    }
                                    if (i19 != 0) {
                                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                    } else {
                                        dialogProperties3 = dialogProperties;
                                    }
                                    function15 = function8;
                                    function16 = function9;
                                    shape4 = shape2;
                                    modifier4 = modifier2;
                                    i20 = 94478519;
                                    int i211110 = i4;
                                    function17 = function7;
                                    function18 = function14;
                                    j10 = containerColor;
                                    j11 = textContentColor;
                                    i21 = i211110;
                                    i22 = i15;
                                    j12 = iconContentColor;
                                    j13 = titleContentColor;
                                }
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                                }
                                composer2 = composerStartRestartGroup;
                                AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier3 = modifier4;
                                function10 = function18;
                                function11 = function17;
                                function12 = function15;
                                function13 = function16;
                                shape3 = shape4;
                                j8 = j10;
                                j9 = j12;
                                j6 = j13;
                                j7 = j11;
                                f2 = fM69getTonalElevationD9Ej5fM;
                                dialogProperties2 = dialogProperties3;
                            } else {
                                composer2 = composerStartRestartGroup;
                                composer2.skipToGroupEnd();
                                function10 = function3;
                                f2 = f;
                                dialogProperties2 = dialogProperties;
                                shape3 = shape2;
                                modifier3 = modifier2;
                                j6 = j3;
                                j7 = j5;
                                function11 = function7;
                                function12 = function8;
                                function13 = function9;
                                j8 = j;
                                j9 = j2;
                            }
                            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                    public final Object invoke(Object obj, Object obj2) {
                                        return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i15 |= 3072;
                        i19 = i18;
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i211111 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i211111;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i211112 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i211112;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                            }
                            composer2 = composerStartRestartGroup;
                            AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            function10 = function18;
                            function11 = function17;
                            function12 = function15;
                            function13 = function16;
                            shape3 = shape4;
                            j8 = j10;
                            j9 = j12;
                            j6 = j13;
                            j7 = j11;
                            f2 = fM69getTonalElevationD9Ej5fM;
                            dialogProperties2 = dialogProperties3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function3;
                            f2 = f;
                            dialogProperties2 = dialogProperties;
                            shape3 = shape2;
                            modifier3 = modifier2;
                            j6 = j3;
                            j7 = j5;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            j8 = j;
                            j9 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                public final Object invoke(Object obj, Object obj2) {
                                    return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i15 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                    i18 = i3 & 8192;
                    if (i18 != 0) {
                        i19 = i18;
                        if ((i2 & 3072) == 0) {
                            i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                        }
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i211113 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i211113;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i211114 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i211114;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                            }
                            composer2 = composerStartRestartGroup;
                            AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            function10 = function18;
                            function11 = function17;
                            function12 = function15;
                            function13 = function16;
                            shape3 = shape4;
                            j8 = j10;
                            j9 = j12;
                            j6 = j13;
                            j7 = j11;
                            f2 = fM69getTonalElevationD9Ej5fM;
                            dialogProperties2 = dialogProperties3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function3;
                            f2 = f;
                            dialogProperties2 = dialogProperties;
                            shape3 = shape2;
                            modifier3 = modifier2;
                            j6 = j3;
                            j7 = j5;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            j8 = j;
                            j9 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                public final Object invoke(Object obj, Object obj2) {
                                    return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i15 |= 3072;
                    i19 = i18;
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i211115 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i211115;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i211116 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i211116;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 24576;
                function7 = function4;
                i11 = i3 & 32;
                if (i11 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    function8 = function5;
                } else {
                    function8 = function5;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changedInstance(function8)) {
                            i12 = 131072;
                        } else {
                            i12 = 65536;
                        }
                        i4 |= i12;
                    }
                }
                i13 = i3 & 64;
                if (i13 != 0) {
                    i4 |= 1572864;
                    function9 = function6;
                } else {
                    function9 = function6;
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function9)) {
                            i14 = 1048576;
                        } else {
                            i14 = 524288;
                        }
                        i4 |= i14;
                    }
                }
                if ((i & 12582912) == 0) {
                    if ((i3 & 128) == 0) {
                        shape2 = shape;
                        if (composerStartRestartGroup.changed(shape2)) {
                        }
                        i4 |= i27;
                    } else {
                        shape2 = shape;
                    }
                    i4 |= i27;
                } else {
                    shape2 = shape;
                }
                if ((i & 100663296) != 0) {
                    if ((i3 & 256) == 0) {
                        i26 = 33554432;
                    } else {
                        i26 = 33554432;
                    }
                    i4 |= i26;
                }
                if ((805306368 & i) != 0) {
                    if ((i3 & 512) == 0) {
                        i25 = 268435456;
                    } else {
                        i25 = 268435456;
                    }
                    i4 |= i25;
                }
                if ((i2 & 6) == 0) {
                    if ((i3 & 1024) == 0) {
                        i24 = 2;
                    } else {
                        i24 = 2;
                    }
                    i15 = i2 | i24;
                } else {
                    i15 = i2;
                }
                if ((i2 & 48) == 0) {
                    j5 = j4;
                    if ((i3 & 2048) == 0) {
                        i23 = 16;
                    } else {
                        i23 = 16;
                    }
                    i15 |= i23;
                } else {
                    j5 = j4;
                }
                i16 = i3 & 4096;
                if (i16 != 0) {
                    if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                        if (composerStartRestartGroup.changed(f)) {
                            i17 = 256;
                        } else {
                            i17 = 128;
                        }
                        i15 |= i17;
                    }
                    i18 = i3 & 8192;
                    if (i18 != 0) {
                        i19 = i18;
                        if ((i2 & 3072) == 0) {
                            i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                        }
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i211117 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i211117;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i211118 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i211118;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                            }
                            composer2 = composerStartRestartGroup;
                            AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            function10 = function18;
                            function11 = function17;
                            function12 = function15;
                            function13 = function16;
                            shape3 = shape4;
                            j8 = j10;
                            j9 = j12;
                            j6 = j13;
                            j7 = j11;
                            f2 = fM69getTonalElevationD9Ej5fM;
                            dialogProperties2 = dialogProperties3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function3;
                            f2 = f;
                            dialogProperties2 = dialogProperties;
                            shape3 = shape2;
                            modifier3 = modifier2;
                            j6 = j3;
                            j7 = j5;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            j8 = j;
                            j9 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                public final Object invoke(Object obj, Object obj2) {
                                    return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i15 |= 3072;
                    i19 = i18;
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i211119 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i211119;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i2111110 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i2111110;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i15 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                i18 = i3 & 8192;
                if (i18 != 0) {
                    i19 = i18;
                    if ((i2 & 3072) == 0) {
                        i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i2111111 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i2111111;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i2111112 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i2111112;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i15 |= 3072;
                i19 = i18;
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i2111113 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i2111113;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i2111114 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i2111114;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                    }
                    composer2 = composerStartRestartGroup;
                    AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    function10 = function18;
                    function11 = function17;
                    function12 = function15;
                    function13 = function16;
                    shape3 = shape4;
                    j8 = j10;
                    j9 = j12;
                    j6 = j13;
                    j7 = j11;
                    f2 = fM69getTonalElevationD9Ej5fM;
                    dialogProperties2 = dialogProperties3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function3;
                    f2 = f;
                    dialogProperties2 = dialogProperties;
                    shape3 = shape2;
                    modifier3 = modifier2;
                    j6 = j3;
                    j7 = j5;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    j8 = j;
                    j9 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 3072;
            i9 = i3 & 16;
            if (i9 != 0) {
                if ((i & 24576) == 0) {
                    function7 = function4;
                    if (composerStartRestartGroup.changedInstance(function7)) {
                        i10 = 16384;
                    } else {
                        i10 = 8192;
                    }
                    i4 |= i10;
                }
                i11 = i3 & 32;
                if (i11 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    function8 = function5;
                } else {
                    function8 = function5;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changedInstance(function8)) {
                            i12 = 131072;
                        } else {
                            i12 = 65536;
                        }
                        i4 |= i12;
                    }
                }
                i13 = i3 & 64;
                if (i13 != 0) {
                    i4 |= 1572864;
                    function9 = function6;
                } else {
                    function9 = function6;
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function9)) {
                            i14 = 1048576;
                        } else {
                            i14 = 524288;
                        }
                        i4 |= i14;
                    }
                }
                if ((i & 12582912) == 0) {
                    if ((i3 & 128) == 0) {
                        shape2 = shape;
                        if (composerStartRestartGroup.changed(shape2)) {
                        }
                        i4 |= i27;
                    } else {
                        shape2 = shape;
                    }
                    i4 |= i27;
                } else {
                    shape2 = shape;
                }
                if ((i & 100663296) != 0) {
                    if ((i3 & 256) == 0) {
                        i26 = 33554432;
                    } else {
                        i26 = 33554432;
                    }
                    i4 |= i26;
                }
                if ((805306368 & i) != 0) {
                    if ((i3 & 512) == 0) {
                        i25 = 268435456;
                    } else {
                        i25 = 268435456;
                    }
                    i4 |= i25;
                }
                if ((i2 & 6) == 0) {
                    if ((i3 & 1024) == 0) {
                        i24 = 2;
                    } else {
                        i24 = 2;
                    }
                    i15 = i2 | i24;
                } else {
                    i15 = i2;
                }
                if ((i2 & 48) == 0) {
                    j5 = j4;
                    if ((i3 & 2048) == 0) {
                        i23 = 16;
                    } else {
                        i23 = 16;
                    }
                    i15 |= i23;
                } else {
                    j5 = j4;
                }
                i16 = i3 & 4096;
                if (i16 != 0) {
                    if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                        if (composerStartRestartGroup.changed(f)) {
                            i17 = 256;
                        } else {
                            i17 = 128;
                        }
                        i15 |= i17;
                    }
                    i18 = i3 & 8192;
                    if (i18 != 0) {
                        i19 = i18;
                        if ((i2 & 3072) == 0) {
                            i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                        }
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i2111115 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i2111115;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i2111116 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i2111116;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                            }
                            composer2 = composerStartRestartGroup;
                            AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            function10 = function18;
                            function11 = function17;
                            function12 = function15;
                            function13 = function16;
                            shape3 = shape4;
                            j8 = j10;
                            j9 = j12;
                            j6 = j13;
                            j7 = j11;
                            f2 = fM69getTonalElevationD9Ej5fM;
                            dialogProperties2 = dialogProperties3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function3;
                            f2 = f;
                            dialogProperties2 = dialogProperties;
                            shape3 = shape2;
                            modifier3 = modifier2;
                            j6 = j3;
                            j7 = j5;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            j8 = j;
                            j9 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                public final Object invoke(Object obj, Object obj2) {
                                    return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i15 |= 3072;
                    i19 = i18;
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i2111117 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i2111117;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i2111118 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i2111118;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i15 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                i18 = i3 & 8192;
                if (i18 != 0) {
                    i19 = i18;
                    if ((i2 & 3072) == 0) {
                        i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i2111119 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i2111119;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i21111110 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i21111110;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i15 |= 3072;
                i19 = i18;
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21111111 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21111111;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21111112 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21111112;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                    }
                    composer2 = composerStartRestartGroup;
                    AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    function10 = function18;
                    function11 = function17;
                    function12 = function15;
                    function13 = function16;
                    shape3 = shape4;
                    j8 = j10;
                    j9 = j12;
                    j6 = j13;
                    j7 = j11;
                    f2 = fM69getTonalElevationD9Ej5fM;
                    dialogProperties2 = dialogProperties3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function3;
                    f2 = f;
                    dialogProperties2 = dialogProperties;
                    shape3 = shape2;
                    modifier3 = modifier2;
                    j6 = j3;
                    j7 = j5;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    j8 = j;
                    j9 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            function7 = function4;
            i11 = i3 & 32;
            if (i11 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                function8 = function5;
            } else {
                function8 = function5;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function8)) {
                        i12 = 131072;
                    } else {
                        i12 = 65536;
                    }
                    i4 |= i12;
                }
            }
            i13 = i3 & 64;
            if (i13 != 0) {
                i4 |= 1572864;
                function9 = function6;
            } else {
                function9 = function6;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function9)) {
                        i14 = 1048576;
                    } else {
                        i14 = 524288;
                    }
                    i4 |= i14;
                }
            }
            if ((i & 12582912) == 0) {
                if ((i3 & 128) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i4 |= i27;
                } else {
                    shape2 = shape;
                }
                i4 |= i27;
            } else {
                shape2 = shape;
            }
            if ((i & 100663296) != 0) {
                if ((i3 & 256) == 0) {
                    i26 = 33554432;
                } else {
                    i26 = 33554432;
                }
                i4 |= i26;
            }
            if ((805306368 & i) != 0) {
                if ((i3 & 512) == 0) {
                    i25 = 268435456;
                } else {
                    i25 = 268435456;
                }
                i4 |= i25;
            }
            if ((i2 & 6) == 0) {
                if ((i3 & 1024) == 0) {
                    i24 = 2;
                } else {
                    i24 = 2;
                }
                i15 = i2 | i24;
            } else {
                i15 = i2;
            }
            if ((i2 & 48) == 0) {
                j5 = j4;
                if ((i3 & 2048) == 0) {
                    i23 = 16;
                } else {
                    i23 = 16;
                }
                i15 |= i23;
            } else {
                j5 = j4;
            }
            i16 = i3 & 4096;
            if (i16 != 0) {
                if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    if (composerStartRestartGroup.changed(f)) {
                        i17 = 256;
                    } else {
                        i17 = 128;
                    }
                    i15 |= i17;
                }
                i18 = i3 & 8192;
                if (i18 != 0) {
                    i19 = i18;
                    if ((i2 & 3072) == 0) {
                        i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i21111113 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i21111113;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i21111114 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i21111114;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i15 |= 3072;
                i19 = i18;
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21111115 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21111115;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21111116 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21111116;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                    }
                    composer2 = composerStartRestartGroup;
                    AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    function10 = function18;
                    function11 = function17;
                    function12 = function15;
                    function13 = function16;
                    shape3 = shape4;
                    j8 = j10;
                    j9 = j12;
                    j6 = j13;
                    j7 = j11;
                    f2 = fM69getTonalElevationD9Ej5fM;
                    dialogProperties2 = dialogProperties3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function3;
                    f2 = f;
                    dialogProperties2 = dialogProperties;
                    shape3 = shape2;
                    modifier3 = modifier2;
                    j6 = j3;
                    j7 = j5;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    j8 = j;
                    j9 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i15 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            i18 = i3 & 8192;
            if (i18 != 0) {
                i19 = i18;
                if ((i2 & 3072) == 0) {
                    i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                }
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21111117 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21111117;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21111118 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21111118;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                    }
                    composer2 = composerStartRestartGroup;
                    AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    function10 = function18;
                    function11 = function17;
                    function12 = function15;
                    function13 = function16;
                    shape3 = shape4;
                    j8 = j10;
                    j9 = j12;
                    j6 = j13;
                    j7 = j11;
                    f2 = fM69getTonalElevationD9Ej5fM;
                    dialogProperties2 = dialogProperties3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function3;
                    f2 = f;
                    dialogProperties2 = dialogProperties;
                    shape3 = shape2;
                    modifier3 = modifier2;
                    j6 = j3;
                    j7 = j5;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    j8 = j;
                    j9 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i15 |= 3072;
            i19 = i18;
            if ((i4 & 306783379) == 306783378) {
                z = true;
            } else {
                z = true;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        function14 = null;
                    } else {
                        function14 = function3;
                    }
                    if (i9 != 0) {
                        function7 = null;
                    }
                    if (i11 != 0) {
                        function8 = null;
                    }
                    if (i13 != 0) {
                        function9 = null;
                    }
                    if ((i3 & 128) != 0) {
                        i4 &= -29360129;
                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i3 & 256) != 0) {
                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        containerColor = j;
                    }
                    if ((i3 & 512) != 0) {
                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                        i4 &= -1879048193;
                    } else {
                        iconContentColor = j2;
                    }
                    if ((i3 & 1024) != 0) {
                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                        i15 &= -15;
                    } else {
                        titleContentColor = j3;
                    }
                    if ((i3 & 2048) != 0) {
                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                        i15 &= -113;
                    } else {
                        textContentColor = j5;
                    }
                    if (i16 != 0) {
                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                    } else {
                        fM69getTonalElevationD9Ej5fM = f;
                    }
                    if (i19 != 0) {
                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties3 = dialogProperties;
                    }
                    function15 = function8;
                    function16 = function9;
                    shape4 = shape2;
                    modifier4 = modifier2;
                    i20 = 94478519;
                    int i21111119 = i4;
                    function17 = function7;
                    function18 = function14;
                    j10 = containerColor;
                    j11 = textContentColor;
                    i21 = i21111119;
                    i22 = i15;
                    j12 = iconContentColor;
                    j13 = titleContentColor;
                } else {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        function14 = null;
                    } else {
                        function14 = function3;
                    }
                    if (i9 != 0) {
                        function7 = null;
                    }
                    if (i11 != 0) {
                        function8 = null;
                    }
                    if (i13 != 0) {
                        function9 = null;
                    }
                    if ((i3 & 128) != 0) {
                        i4 &= -29360129;
                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i3 & 256) != 0) {
                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        containerColor = j;
                    }
                    if ((i3 & 512) != 0) {
                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                        i4 &= -1879048193;
                    } else {
                        iconContentColor = j2;
                    }
                    if ((i3 & 1024) != 0) {
                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                        i15 &= -15;
                    } else {
                        titleContentColor = j3;
                    }
                    if ((i3 & 2048) != 0) {
                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                        i15 &= -113;
                    } else {
                        textContentColor = j5;
                    }
                    if (i16 != 0) {
                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                    } else {
                        fM69getTonalElevationD9Ej5fM = f;
                    }
                    if (i19 != 0) {
                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties3 = dialogProperties;
                    }
                    function15 = function8;
                    function16 = function9;
                    shape4 = shape2;
                    modifier4 = modifier2;
                    i20 = 94478519;
                    int i211111110 = i4;
                    function17 = function7;
                    function18 = function14;
                    j10 = containerColor;
                    j11 = textContentColor;
                    i21 = i211111110;
                    i22 = i15;
                    j12 = iconContentColor;
                    j13 = titleContentColor;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                }
                composer2 = composerStartRestartGroup;
                AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                function10 = function18;
                function11 = function17;
                function12 = function15;
                function13 = function16;
                shape3 = shape4;
                j8 = j10;
                j9 = j12;
                j6 = j13;
                j7 = j11;
                f2 = fM69getTonalElevationD9Ej5fM;
                dialogProperties2 = dialogProperties3;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                function10 = function3;
                f2 = f;
                dialogProperties2 = dialogProperties;
                shape3 = shape2;
                modifier3 = modifier2;
                j6 = j3;
                j7 = j5;
                function11 = function7;
                function12 = function8;
                function13 = function9;
                j8 = j;
                j9 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                    public final Object invoke(Object obj, Object obj2) {
                        return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 48;
        i5 = i3 & 4;
        if (i5 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                modifier2 = modifier;
                if (composerStartRestartGroup.changed(modifier2)) {
                    i6 = 256;
                } else {
                    i6 = 128;
                }
                i4 |= i6;
            }
            i7 = i3 & 8;
            if (i7 != 0) {
                if ((i & 3072) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i8 = 2048;
                    } else {
                        i8 = 1024;
                    }
                    i4 |= i8;
                }
                i9 = i3 & 16;
                if (i9 != 0) {
                    if ((i & 24576) == 0) {
                        function7 = function4;
                        if (composerStartRestartGroup.changedInstance(function7)) {
                            i10 = 16384;
                        } else {
                            i10 = 8192;
                        }
                        i4 |= i10;
                    }
                    i11 = i3 & 32;
                    if (i11 != 0) {
                        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        function8 = function5;
                    } else {
                        function8 = function5;
                        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changedInstance(function8)) {
                                i12 = 131072;
                            } else {
                                i12 = 65536;
                            }
                            i4 |= i12;
                        }
                    }
                    i13 = i3 & 64;
                    if (i13 != 0) {
                        i4 |= 1572864;
                        function9 = function6;
                    } else {
                        function9 = function6;
                        if ((i & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function9)) {
                                i14 = 1048576;
                            } else {
                                i14 = 524288;
                            }
                            i4 |= i14;
                        }
                    }
                    if ((i & 12582912) == 0) {
                        if ((i3 & 128) == 0) {
                            shape2 = shape;
                            if (composerStartRestartGroup.changed(shape2)) {
                            }
                            i4 |= i27;
                        } else {
                            shape2 = shape;
                        }
                        i4 |= i27;
                    } else {
                        shape2 = shape;
                    }
                    if ((i & 100663296) != 0) {
                        if ((i3 & 256) == 0) {
                            i26 = 33554432;
                        } else {
                            i26 = 33554432;
                        }
                        i4 |= i26;
                    }
                    if ((805306368 & i) != 0) {
                        if ((i3 & 512) == 0) {
                            i25 = 268435456;
                        } else {
                            i25 = 268435456;
                        }
                        i4 |= i25;
                    }
                    if ((i2 & 6) == 0) {
                        if ((i3 & 1024) == 0) {
                            i24 = 2;
                        } else {
                            i24 = 2;
                        }
                        i15 = i2 | i24;
                    } else {
                        i15 = i2;
                    }
                    if ((i2 & 48) == 0) {
                        j5 = j4;
                        if ((i3 & 2048) == 0) {
                            i23 = 16;
                        } else {
                            i23 = 16;
                        }
                        i15 |= i23;
                    } else {
                        j5 = j4;
                    }
                    i16 = i3 & 4096;
                    if (i16 != 0) {
                        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                            if (composerStartRestartGroup.changed(f)) {
                                i17 = 256;
                            } else {
                                i17 = 128;
                            }
                            i15 |= i17;
                        }
                        i18 = i3 & 8192;
                        if (i18 != 0) {
                            i19 = i18;
                            if ((i2 & 3072) == 0) {
                                i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                            }
                            if ((i4 & 306783379) == 306783378) {
                                z = true;
                            } else {
                                z = true;
                            }
                            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                                composerStartRestartGroup.startDefaults();
                                if ((i & 1) != 0) {
                                    if (i5 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i7 != 0) {
                                        function14 = null;
                                    } else {
                                        function14 = function3;
                                    }
                                    if (i9 != 0) {
                                        function7 = null;
                                    }
                                    if (i11 != 0) {
                                        function8 = null;
                                    }
                                    if (i13 != 0) {
                                        function9 = null;
                                    }
                                    if ((i3 & 128) != 0) {
                                        i4 &= -29360129;
                                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    }
                                    if ((i3 & 256) != 0) {
                                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                        i4 &= -234881025;
                                    } else {
                                        containerColor = j;
                                    }
                                    if ((i3 & 512) != 0) {
                                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                        i4 &= -1879048193;
                                    } else {
                                        iconContentColor = j2;
                                    }
                                    if ((i3 & 1024) != 0) {
                                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                        i15 &= -15;
                                    } else {
                                        titleContentColor = j3;
                                    }
                                    if ((i3 & 2048) != 0) {
                                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                        i15 &= -113;
                                    } else {
                                        textContentColor = j5;
                                    }
                                    if (i16 != 0) {
                                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                    } else {
                                        fM69getTonalElevationD9Ej5fM = f;
                                    }
                                    if (i19 != 0) {
                                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                    } else {
                                        dialogProperties3 = dialogProperties;
                                    }
                                    function15 = function8;
                                    function16 = function9;
                                    shape4 = shape2;
                                    modifier4 = modifier2;
                                    i20 = 94478519;
                                    int i211111111 = i4;
                                    function17 = function7;
                                    function18 = function14;
                                    j10 = containerColor;
                                    j11 = textContentColor;
                                    i21 = i211111111;
                                    i22 = i15;
                                    j12 = iconContentColor;
                                    j13 = titleContentColor;
                                } else {
                                    if (i5 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i7 != 0) {
                                        function14 = null;
                                    } else {
                                        function14 = function3;
                                    }
                                    if (i9 != 0) {
                                        function7 = null;
                                    }
                                    if (i11 != 0) {
                                        function8 = null;
                                    }
                                    if (i13 != 0) {
                                        function9 = null;
                                    }
                                    if ((i3 & 128) != 0) {
                                        i4 &= -29360129;
                                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                    }
                                    if ((i3 & 256) != 0) {
                                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                        i4 &= -234881025;
                                    } else {
                                        containerColor = j;
                                    }
                                    if ((i3 & 512) != 0) {
                                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                        i4 &= -1879048193;
                                    } else {
                                        iconContentColor = j2;
                                    }
                                    if ((i3 & 1024) != 0) {
                                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                        i15 &= -15;
                                    } else {
                                        titleContentColor = j3;
                                    }
                                    if ((i3 & 2048) != 0) {
                                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                        i15 &= -113;
                                    } else {
                                        textContentColor = j5;
                                    }
                                    if (i16 != 0) {
                                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                    } else {
                                        fM69getTonalElevationD9Ej5fM = f;
                                    }
                                    if (i19 != 0) {
                                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                    } else {
                                        dialogProperties3 = dialogProperties;
                                    }
                                    function15 = function8;
                                    function16 = function9;
                                    shape4 = shape2;
                                    modifier4 = modifier2;
                                    i20 = 94478519;
                                    int i211111112 = i4;
                                    function17 = function7;
                                    function18 = function14;
                                    j10 = containerColor;
                                    j11 = textContentColor;
                                    i21 = i211111112;
                                    i22 = i15;
                                    j12 = iconContentColor;
                                    j13 = titleContentColor;
                                }
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                                }
                                composer2 = composerStartRestartGroup;
                                AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier3 = modifier4;
                                function10 = function18;
                                function11 = function17;
                                function12 = function15;
                                function13 = function16;
                                shape3 = shape4;
                                j8 = j10;
                                j9 = j12;
                                j6 = j13;
                                j7 = j11;
                                f2 = fM69getTonalElevationD9Ej5fM;
                                dialogProperties2 = dialogProperties3;
                            } else {
                                composer2 = composerStartRestartGroup;
                                composer2.skipToGroupEnd();
                                function10 = function3;
                                f2 = f;
                                dialogProperties2 = dialogProperties;
                                shape3 = shape2;
                                modifier3 = modifier2;
                                j6 = j3;
                                j7 = j5;
                                function11 = function7;
                                function12 = function8;
                                function13 = function9;
                                j8 = j;
                                j9 = j2;
                            }
                            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                    public final Object invoke(Object obj, Object obj2) {
                                        return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i15 |= 3072;
                        i19 = i18;
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i211111113 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i211111113;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i211111114 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i211111114;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                            }
                            composer2 = composerStartRestartGroup;
                            AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            function10 = function18;
                            function11 = function17;
                            function12 = function15;
                            function13 = function16;
                            shape3 = shape4;
                            j8 = j10;
                            j9 = j12;
                            j6 = j13;
                            j7 = j11;
                            f2 = fM69getTonalElevationD9Ej5fM;
                            dialogProperties2 = dialogProperties3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function3;
                            f2 = f;
                            dialogProperties2 = dialogProperties;
                            shape3 = shape2;
                            modifier3 = modifier2;
                            j6 = j3;
                            j7 = j5;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            j8 = j;
                            j9 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                public final Object invoke(Object obj, Object obj2) {
                                    return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i15 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                    i18 = i3 & 8192;
                    if (i18 != 0) {
                        i19 = i18;
                        if ((i2 & 3072) == 0) {
                            i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                        }
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i211111115 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i211111115;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i211111116 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i211111116;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                            }
                            composer2 = composerStartRestartGroup;
                            AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            function10 = function18;
                            function11 = function17;
                            function12 = function15;
                            function13 = function16;
                            shape3 = shape4;
                            j8 = j10;
                            j9 = j12;
                            j6 = j13;
                            j7 = j11;
                            f2 = fM69getTonalElevationD9Ej5fM;
                            dialogProperties2 = dialogProperties3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function3;
                            f2 = f;
                            dialogProperties2 = dialogProperties;
                            shape3 = shape2;
                            modifier3 = modifier2;
                            j6 = j3;
                            j7 = j5;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            j8 = j;
                            j9 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                public final Object invoke(Object obj, Object obj2) {
                                    return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i15 |= 3072;
                    i19 = i18;
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i211111117 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i211111117;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i211111118 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i211111118;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 24576;
                function7 = function4;
                i11 = i3 & 32;
                if (i11 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    function8 = function5;
                } else {
                    function8 = function5;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changedInstance(function8)) {
                            i12 = 131072;
                        } else {
                            i12 = 65536;
                        }
                        i4 |= i12;
                    }
                }
                i13 = i3 & 64;
                if (i13 != 0) {
                    i4 |= 1572864;
                    function9 = function6;
                } else {
                    function9 = function6;
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function9)) {
                            i14 = 1048576;
                        } else {
                            i14 = 524288;
                        }
                        i4 |= i14;
                    }
                }
                if ((i & 12582912) == 0) {
                    if ((i3 & 128) == 0) {
                        shape2 = shape;
                        if (composerStartRestartGroup.changed(shape2)) {
                        }
                        i4 |= i27;
                    } else {
                        shape2 = shape;
                    }
                    i4 |= i27;
                } else {
                    shape2 = shape;
                }
                if ((i & 100663296) != 0) {
                    if ((i3 & 256) == 0) {
                        i26 = 33554432;
                    } else {
                        i26 = 33554432;
                    }
                    i4 |= i26;
                }
                if ((805306368 & i) != 0) {
                    if ((i3 & 512) == 0) {
                        i25 = 268435456;
                    } else {
                        i25 = 268435456;
                    }
                    i4 |= i25;
                }
                if ((i2 & 6) == 0) {
                    if ((i3 & 1024) == 0) {
                        i24 = 2;
                    } else {
                        i24 = 2;
                    }
                    i15 = i2 | i24;
                } else {
                    i15 = i2;
                }
                if ((i2 & 48) == 0) {
                    j5 = j4;
                    if ((i3 & 2048) == 0) {
                        i23 = 16;
                    } else {
                        i23 = 16;
                    }
                    i15 |= i23;
                } else {
                    j5 = j4;
                }
                i16 = i3 & 4096;
                if (i16 != 0) {
                    if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                        if (composerStartRestartGroup.changed(f)) {
                            i17 = 256;
                        } else {
                            i17 = 128;
                        }
                        i15 |= i17;
                    }
                    i18 = i3 & 8192;
                    if (i18 != 0) {
                        i19 = i18;
                        if ((i2 & 3072) == 0) {
                            i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                        }
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i211111119 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i211111119;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i2111111110 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i2111111110;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                            }
                            composer2 = composerStartRestartGroup;
                            AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            function10 = function18;
                            function11 = function17;
                            function12 = function15;
                            function13 = function16;
                            shape3 = shape4;
                            j8 = j10;
                            j9 = j12;
                            j6 = j13;
                            j7 = j11;
                            f2 = fM69getTonalElevationD9Ej5fM;
                            dialogProperties2 = dialogProperties3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function3;
                            f2 = f;
                            dialogProperties2 = dialogProperties;
                            shape3 = shape2;
                            modifier3 = modifier2;
                            j6 = j3;
                            j7 = j5;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            j8 = j;
                            j9 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                public final Object invoke(Object obj, Object obj2) {
                                    return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i15 |= 3072;
                    i19 = i18;
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i2111111111 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i2111111111;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i2111111112 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i2111111112;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i15 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                i18 = i3 & 8192;
                if (i18 != 0) {
                    i19 = i18;
                    if ((i2 & 3072) == 0) {
                        i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i2111111113 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i2111111113;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i2111111114 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i2111111114;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i15 |= 3072;
                i19 = i18;
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i2111111115 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i2111111115;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i2111111116 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i2111111116;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                    }
                    composer2 = composerStartRestartGroup;
                    AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    function10 = function18;
                    function11 = function17;
                    function12 = function15;
                    function13 = function16;
                    shape3 = shape4;
                    j8 = j10;
                    j9 = j12;
                    j6 = j13;
                    j7 = j11;
                    f2 = fM69getTonalElevationD9Ej5fM;
                    dialogProperties2 = dialogProperties3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function3;
                    f2 = f;
                    dialogProperties2 = dialogProperties;
                    shape3 = shape2;
                    modifier3 = modifier2;
                    j6 = j3;
                    j7 = j5;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    j8 = j;
                    j9 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 3072;
            i9 = i3 & 16;
            if (i9 != 0) {
                if ((i & 24576) == 0) {
                    function7 = function4;
                    if (composerStartRestartGroup.changedInstance(function7)) {
                        i10 = 16384;
                    } else {
                        i10 = 8192;
                    }
                    i4 |= i10;
                }
                i11 = i3 & 32;
                if (i11 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    function8 = function5;
                } else {
                    function8 = function5;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changedInstance(function8)) {
                            i12 = 131072;
                        } else {
                            i12 = 65536;
                        }
                        i4 |= i12;
                    }
                }
                i13 = i3 & 64;
                if (i13 != 0) {
                    i4 |= 1572864;
                    function9 = function6;
                } else {
                    function9 = function6;
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function9)) {
                            i14 = 1048576;
                        } else {
                            i14 = 524288;
                        }
                        i4 |= i14;
                    }
                }
                if ((i & 12582912) == 0) {
                    if ((i3 & 128) == 0) {
                        shape2 = shape;
                        if (composerStartRestartGroup.changed(shape2)) {
                        }
                        i4 |= i27;
                    } else {
                        shape2 = shape;
                    }
                    i4 |= i27;
                } else {
                    shape2 = shape;
                }
                if ((i & 100663296) != 0) {
                    if ((i3 & 256) == 0) {
                        i26 = 33554432;
                    } else {
                        i26 = 33554432;
                    }
                    i4 |= i26;
                }
                if ((805306368 & i) != 0) {
                    if ((i3 & 512) == 0) {
                        i25 = 268435456;
                    } else {
                        i25 = 268435456;
                    }
                    i4 |= i25;
                }
                if ((i2 & 6) == 0) {
                    if ((i3 & 1024) == 0) {
                        i24 = 2;
                    } else {
                        i24 = 2;
                    }
                    i15 = i2 | i24;
                } else {
                    i15 = i2;
                }
                if ((i2 & 48) == 0) {
                    j5 = j4;
                    if ((i3 & 2048) == 0) {
                        i23 = 16;
                    } else {
                        i23 = 16;
                    }
                    i15 |= i23;
                } else {
                    j5 = j4;
                }
                i16 = i3 & 4096;
                if (i16 != 0) {
                    if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                        if (composerStartRestartGroup.changed(f)) {
                            i17 = 256;
                        } else {
                            i17 = 128;
                        }
                        i15 |= i17;
                    }
                    i18 = i3 & 8192;
                    if (i18 != 0) {
                        i19 = i18;
                        if ((i2 & 3072) == 0) {
                            i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                        }
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i2111111117 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i2111111117;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i2111111118 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i2111111118;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                            }
                            composer2 = composerStartRestartGroup;
                            AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            function10 = function18;
                            function11 = function17;
                            function12 = function15;
                            function13 = function16;
                            shape3 = shape4;
                            j8 = j10;
                            j9 = j12;
                            j6 = j13;
                            j7 = j11;
                            f2 = fM69getTonalElevationD9Ej5fM;
                            dialogProperties2 = dialogProperties3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function3;
                            f2 = f;
                            dialogProperties2 = dialogProperties;
                            shape3 = shape2;
                            modifier3 = modifier2;
                            j6 = j3;
                            j7 = j5;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            j8 = j;
                            j9 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                public final Object invoke(Object obj, Object obj2) {
                                    return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i15 |= 3072;
                    i19 = i18;
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i2111111119 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i2111111119;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i21111111110 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i21111111110;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i15 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                i18 = i3 & 8192;
                if (i18 != 0) {
                    i19 = i18;
                    if ((i2 & 3072) == 0) {
                        i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i21111111111 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i21111111111;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i21111111112 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i21111111112;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i15 |= 3072;
                i19 = i18;
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21111111113 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21111111113;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21111111114 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21111111114;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                    }
                    composer2 = composerStartRestartGroup;
                    AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    function10 = function18;
                    function11 = function17;
                    function12 = function15;
                    function13 = function16;
                    shape3 = shape4;
                    j8 = j10;
                    j9 = j12;
                    j6 = j13;
                    j7 = j11;
                    f2 = fM69getTonalElevationD9Ej5fM;
                    dialogProperties2 = dialogProperties3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function3;
                    f2 = f;
                    dialogProperties2 = dialogProperties;
                    shape3 = shape2;
                    modifier3 = modifier2;
                    j6 = j3;
                    j7 = j5;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    j8 = j;
                    j9 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            function7 = function4;
            i11 = i3 & 32;
            if (i11 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                function8 = function5;
            } else {
                function8 = function5;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function8)) {
                        i12 = 131072;
                    } else {
                        i12 = 65536;
                    }
                    i4 |= i12;
                }
            }
            i13 = i3 & 64;
            if (i13 != 0) {
                i4 |= 1572864;
                function9 = function6;
            } else {
                function9 = function6;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function9)) {
                        i14 = 1048576;
                    } else {
                        i14 = 524288;
                    }
                    i4 |= i14;
                }
            }
            if ((i & 12582912) == 0) {
                if ((i3 & 128) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i4 |= i27;
                } else {
                    shape2 = shape;
                }
                i4 |= i27;
            } else {
                shape2 = shape;
            }
            if ((i & 100663296) != 0) {
                if ((i3 & 256) == 0) {
                    i26 = 33554432;
                } else {
                    i26 = 33554432;
                }
                i4 |= i26;
            }
            if ((805306368 & i) != 0) {
                if ((i3 & 512) == 0) {
                    i25 = 268435456;
                } else {
                    i25 = 268435456;
                }
                i4 |= i25;
            }
            if ((i2 & 6) == 0) {
                if ((i3 & 1024) == 0) {
                    i24 = 2;
                } else {
                    i24 = 2;
                }
                i15 = i2 | i24;
            } else {
                i15 = i2;
            }
            if ((i2 & 48) == 0) {
                j5 = j4;
                if ((i3 & 2048) == 0) {
                    i23 = 16;
                } else {
                    i23 = 16;
                }
                i15 |= i23;
            } else {
                j5 = j4;
            }
            i16 = i3 & 4096;
            if (i16 != 0) {
                if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    if (composerStartRestartGroup.changed(f)) {
                        i17 = 256;
                    } else {
                        i17 = 128;
                    }
                    i15 |= i17;
                }
                i18 = i3 & 8192;
                if (i18 != 0) {
                    i19 = i18;
                    if ((i2 & 3072) == 0) {
                        i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i21111111115 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i21111111115;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i21111111116 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i21111111116;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i15 |= 3072;
                i19 = i18;
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21111111117 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21111111117;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21111111118 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21111111118;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                    }
                    composer2 = composerStartRestartGroup;
                    AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    function10 = function18;
                    function11 = function17;
                    function12 = function15;
                    function13 = function16;
                    shape3 = shape4;
                    j8 = j10;
                    j9 = j12;
                    j6 = j13;
                    j7 = j11;
                    f2 = fM69getTonalElevationD9Ej5fM;
                    dialogProperties2 = dialogProperties3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function3;
                    f2 = f;
                    dialogProperties2 = dialogProperties;
                    shape3 = shape2;
                    modifier3 = modifier2;
                    j6 = j3;
                    j7 = j5;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    j8 = j;
                    j9 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i15 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            i18 = i3 & 8192;
            if (i18 != 0) {
                i19 = i18;
                if ((i2 & 3072) == 0) {
                    i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                }
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21111111119 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21111111119;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i211111111110 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i211111111110;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                    }
                    composer2 = composerStartRestartGroup;
                    AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    function10 = function18;
                    function11 = function17;
                    function12 = function15;
                    function13 = function16;
                    shape3 = shape4;
                    j8 = j10;
                    j9 = j12;
                    j6 = j13;
                    j7 = j11;
                    f2 = fM69getTonalElevationD9Ej5fM;
                    dialogProperties2 = dialogProperties3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function3;
                    f2 = f;
                    dialogProperties2 = dialogProperties;
                    shape3 = shape2;
                    modifier3 = modifier2;
                    j6 = j3;
                    j7 = j5;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    j8 = j;
                    j9 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i15 |= 3072;
            i19 = i18;
            if ((i4 & 306783379) == 306783378) {
                z = true;
            } else {
                z = true;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        function14 = null;
                    } else {
                        function14 = function3;
                    }
                    if (i9 != 0) {
                        function7 = null;
                    }
                    if (i11 != 0) {
                        function8 = null;
                    }
                    if (i13 != 0) {
                        function9 = null;
                    }
                    if ((i3 & 128) != 0) {
                        i4 &= -29360129;
                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i3 & 256) != 0) {
                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        containerColor = j;
                    }
                    if ((i3 & 512) != 0) {
                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                        i4 &= -1879048193;
                    } else {
                        iconContentColor = j2;
                    }
                    if ((i3 & 1024) != 0) {
                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                        i15 &= -15;
                    } else {
                        titleContentColor = j3;
                    }
                    if ((i3 & 2048) != 0) {
                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                        i15 &= -113;
                    } else {
                        textContentColor = j5;
                    }
                    if (i16 != 0) {
                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                    } else {
                        fM69getTonalElevationD9Ej5fM = f;
                    }
                    if (i19 != 0) {
                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties3 = dialogProperties;
                    }
                    function15 = function8;
                    function16 = function9;
                    shape4 = shape2;
                    modifier4 = modifier2;
                    i20 = 94478519;
                    int i211111111111 = i4;
                    function17 = function7;
                    function18 = function14;
                    j10 = containerColor;
                    j11 = textContentColor;
                    i21 = i211111111111;
                    i22 = i15;
                    j12 = iconContentColor;
                    j13 = titleContentColor;
                } else {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        function14 = null;
                    } else {
                        function14 = function3;
                    }
                    if (i9 != 0) {
                        function7 = null;
                    }
                    if (i11 != 0) {
                        function8 = null;
                    }
                    if (i13 != 0) {
                        function9 = null;
                    }
                    if ((i3 & 128) != 0) {
                        i4 &= -29360129;
                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i3 & 256) != 0) {
                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        containerColor = j;
                    }
                    if ((i3 & 512) != 0) {
                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                        i4 &= -1879048193;
                    } else {
                        iconContentColor = j2;
                    }
                    if ((i3 & 1024) != 0) {
                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                        i15 &= -15;
                    } else {
                        titleContentColor = j3;
                    }
                    if ((i3 & 2048) != 0) {
                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                        i15 &= -113;
                    } else {
                        textContentColor = j5;
                    }
                    if (i16 != 0) {
                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                    } else {
                        fM69getTonalElevationD9Ej5fM = f;
                    }
                    if (i19 != 0) {
                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties3 = dialogProperties;
                    }
                    function15 = function8;
                    function16 = function9;
                    shape4 = shape2;
                    modifier4 = modifier2;
                    i20 = 94478519;
                    int i211111111112 = i4;
                    function17 = function7;
                    function18 = function14;
                    j10 = containerColor;
                    j11 = textContentColor;
                    i21 = i211111111112;
                    i22 = i15;
                    j12 = iconContentColor;
                    j13 = titleContentColor;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                }
                composer2 = composerStartRestartGroup;
                AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                function10 = function18;
                function11 = function17;
                function12 = function15;
                function13 = function16;
                shape3 = shape4;
                j8 = j10;
                j9 = j12;
                j6 = j13;
                j7 = j11;
                f2 = fM69getTonalElevationD9Ej5fM;
                dialogProperties2 = dialogProperties3;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                function10 = function3;
                f2 = f;
                dialogProperties2 = dialogProperties;
                shape3 = shape2;
                modifier3 = modifier2;
                j6 = j3;
                j7 = j5;
                function11 = function7;
                function12 = function8;
                function13 = function9;
                j8 = j;
                j9 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                    public final Object invoke(Object obj, Object obj2) {
                        return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        modifier2 = modifier;
        i7 = i3 & 8;
        if (i7 != 0) {
            if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i8 = 2048;
                } else {
                    i8 = 1024;
                }
                i4 |= i8;
            }
            i9 = i3 & 16;
            if (i9 != 0) {
                if ((i & 24576) == 0) {
                    function7 = function4;
                    if (composerStartRestartGroup.changedInstance(function7)) {
                        i10 = 16384;
                    } else {
                        i10 = 8192;
                    }
                    i4 |= i10;
                }
                i11 = i3 & 32;
                if (i11 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    function8 = function5;
                } else {
                    function8 = function5;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changedInstance(function8)) {
                            i12 = 131072;
                        } else {
                            i12 = 65536;
                        }
                        i4 |= i12;
                    }
                }
                i13 = i3 & 64;
                if (i13 != 0) {
                    i4 |= 1572864;
                    function9 = function6;
                } else {
                    function9 = function6;
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function9)) {
                            i14 = 1048576;
                        } else {
                            i14 = 524288;
                        }
                        i4 |= i14;
                    }
                }
                if ((i & 12582912) == 0) {
                    if ((i3 & 128) == 0) {
                        shape2 = shape;
                        if (composerStartRestartGroup.changed(shape2)) {
                        }
                        i4 |= i27;
                    } else {
                        shape2 = shape;
                    }
                    i4 |= i27;
                } else {
                    shape2 = shape;
                }
                if ((i & 100663296) != 0) {
                    if ((i3 & 256) == 0) {
                        i26 = 33554432;
                    } else {
                        i26 = 33554432;
                    }
                    i4 |= i26;
                }
                if ((805306368 & i) != 0) {
                    if ((i3 & 512) == 0) {
                        i25 = 268435456;
                    } else {
                        i25 = 268435456;
                    }
                    i4 |= i25;
                }
                if ((i2 & 6) == 0) {
                    if ((i3 & 1024) == 0) {
                        i24 = 2;
                    } else {
                        i24 = 2;
                    }
                    i15 = i2 | i24;
                } else {
                    i15 = i2;
                }
                if ((i2 & 48) == 0) {
                    j5 = j4;
                    if ((i3 & 2048) == 0) {
                        i23 = 16;
                    } else {
                        i23 = 16;
                    }
                    i15 |= i23;
                } else {
                    j5 = j4;
                }
                i16 = i3 & 4096;
                if (i16 != 0) {
                    if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                        if (composerStartRestartGroup.changed(f)) {
                            i17 = 256;
                        } else {
                            i17 = 128;
                        }
                        i15 |= i17;
                    }
                    i18 = i3 & 8192;
                    if (i18 != 0) {
                        i19 = i18;
                        if ((i2 & 3072) == 0) {
                            i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                        }
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i211111111113 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i211111111113;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            } else {
                                if (i5 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i7 != 0) {
                                    function14 = null;
                                } else {
                                    function14 = function3;
                                }
                                if (i9 != 0) {
                                    function7 = null;
                                }
                                if (i11 != 0) {
                                    function8 = null;
                                }
                                if (i13 != 0) {
                                    function9 = null;
                                }
                                if ((i3 & 128) != 0) {
                                    i4 &= -29360129;
                                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                                }
                                if ((i3 & 256) != 0) {
                                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                    i4 &= -234881025;
                                } else {
                                    containerColor = j;
                                }
                                if ((i3 & 512) != 0) {
                                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                    i4 &= -1879048193;
                                } else {
                                    iconContentColor = j2;
                                }
                                if ((i3 & 1024) != 0) {
                                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                    i15 &= -15;
                                } else {
                                    titleContentColor = j3;
                                }
                                if ((i3 & 2048) != 0) {
                                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                    i15 &= -113;
                                } else {
                                    textContentColor = j5;
                                }
                                if (i16 != 0) {
                                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                                } else {
                                    fM69getTonalElevationD9Ej5fM = f;
                                }
                                if (i19 != 0) {
                                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                                } else {
                                    dialogProperties3 = dialogProperties;
                                }
                                function15 = function8;
                                function16 = function9;
                                shape4 = shape2;
                                modifier4 = modifier2;
                                i20 = 94478519;
                                int i211111111114 = i4;
                                function17 = function7;
                                function18 = function14;
                                j10 = containerColor;
                                j11 = textContentColor;
                                i21 = i211111111114;
                                i22 = i15;
                                j12 = iconContentColor;
                                j13 = titleContentColor;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                            }
                            composer2 = composerStartRestartGroup;
                            AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            function10 = function18;
                            function11 = function17;
                            function12 = function15;
                            function13 = function16;
                            shape3 = shape4;
                            j8 = j10;
                            j9 = j12;
                            j6 = j13;
                            j7 = j11;
                            f2 = fM69getTonalElevationD9Ej5fM;
                            dialogProperties2 = dialogProperties3;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            function10 = function3;
                            f2 = f;
                            dialogProperties2 = dialogProperties;
                            shape3 = shape2;
                            modifier3 = modifier2;
                            j6 = j3;
                            j7 = j5;
                            function11 = function7;
                            function12 = function8;
                            function13 = function9;
                            j8 = j;
                            j9 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                                public final Object invoke(Object obj, Object obj2) {
                                    return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i15 |= 3072;
                    i19 = i18;
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i211111111115 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i211111111115;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i211111111116 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i211111111116;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i15 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
                i18 = i3 & 8192;
                if (i18 != 0) {
                    i19 = i18;
                    if ((i2 & 3072) == 0) {
                        i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i211111111117 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i211111111117;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i211111111118 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i211111111118;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i15 |= 3072;
                i19 = i18;
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i211111111119 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i211111111119;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i2111111111110 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i2111111111110;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                    }
                    composer2 = composerStartRestartGroup;
                    AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    function10 = function18;
                    function11 = function17;
                    function12 = function15;
                    function13 = function16;
                    shape3 = shape4;
                    j8 = j10;
                    j9 = j12;
                    j6 = j13;
                    j7 = j11;
                    f2 = fM69getTonalElevationD9Ej5fM;
                    dialogProperties2 = dialogProperties3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function3;
                    f2 = f;
                    dialogProperties2 = dialogProperties;
                    shape3 = shape2;
                    modifier3 = modifier2;
                    j6 = j3;
                    j7 = j5;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    j8 = j;
                    j9 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            function7 = function4;
            i11 = i3 & 32;
            if (i11 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                function8 = function5;
            } else {
                function8 = function5;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function8)) {
                        i12 = 131072;
                    } else {
                        i12 = 65536;
                    }
                    i4 |= i12;
                }
            }
            i13 = i3 & 64;
            if (i13 != 0) {
                i4 |= 1572864;
                function9 = function6;
            } else {
                function9 = function6;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function9)) {
                        i14 = 1048576;
                    } else {
                        i14 = 524288;
                    }
                    i4 |= i14;
                }
            }
            if ((i & 12582912) == 0) {
                if ((i3 & 128) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i4 |= i27;
                } else {
                    shape2 = shape;
                }
                i4 |= i27;
            } else {
                shape2 = shape;
            }
            if ((i & 100663296) != 0) {
                if ((i3 & 256) == 0) {
                    i26 = 33554432;
                } else {
                    i26 = 33554432;
                }
                i4 |= i26;
            }
            if ((805306368 & i) != 0) {
                if ((i3 & 512) == 0) {
                    i25 = 268435456;
                } else {
                    i25 = 268435456;
                }
                i4 |= i25;
            }
            if ((i2 & 6) == 0) {
                if ((i3 & 1024) == 0) {
                    i24 = 2;
                } else {
                    i24 = 2;
                }
                i15 = i2 | i24;
            } else {
                i15 = i2;
            }
            if ((i2 & 48) == 0) {
                j5 = j4;
                if ((i3 & 2048) == 0) {
                    i23 = 16;
                } else {
                    i23 = 16;
                }
                i15 |= i23;
            } else {
                j5 = j4;
            }
            i16 = i3 & 4096;
            if (i16 != 0) {
                if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    if (composerStartRestartGroup.changed(f)) {
                        i17 = 256;
                    } else {
                        i17 = 128;
                    }
                    i15 |= i17;
                }
                i18 = i3 & 8192;
                if (i18 != 0) {
                    i19 = i18;
                    if ((i2 & 3072) == 0) {
                        i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i2111111111111 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i2111111111111;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i2111111111112 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i2111111111112;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i15 |= 3072;
                i19 = i18;
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i2111111111113 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i2111111111113;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i2111111111114 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i2111111111114;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                    }
                    composer2 = composerStartRestartGroup;
                    AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    function10 = function18;
                    function11 = function17;
                    function12 = function15;
                    function13 = function16;
                    shape3 = shape4;
                    j8 = j10;
                    j9 = j12;
                    j6 = j13;
                    j7 = j11;
                    f2 = fM69getTonalElevationD9Ej5fM;
                    dialogProperties2 = dialogProperties3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function3;
                    f2 = f;
                    dialogProperties2 = dialogProperties;
                    shape3 = shape2;
                    modifier3 = modifier2;
                    j6 = j3;
                    j7 = j5;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    j8 = j;
                    j9 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i15 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            i18 = i3 & 8192;
            if (i18 != 0) {
                i19 = i18;
                if ((i2 & 3072) == 0) {
                    i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                }
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i2111111111115 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i2111111111115;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i2111111111116 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i2111111111116;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                    }
                    composer2 = composerStartRestartGroup;
                    AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    function10 = function18;
                    function11 = function17;
                    function12 = function15;
                    function13 = function16;
                    shape3 = shape4;
                    j8 = j10;
                    j9 = j12;
                    j6 = j13;
                    j7 = j11;
                    f2 = fM69getTonalElevationD9Ej5fM;
                    dialogProperties2 = dialogProperties3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function3;
                    f2 = f;
                    dialogProperties2 = dialogProperties;
                    shape3 = shape2;
                    modifier3 = modifier2;
                    j6 = j3;
                    j7 = j5;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    j8 = j;
                    j9 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i15 |= 3072;
            i19 = i18;
            if ((i4 & 306783379) == 306783378) {
                z = true;
            } else {
                z = true;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        function14 = null;
                    } else {
                        function14 = function3;
                    }
                    if (i9 != 0) {
                        function7 = null;
                    }
                    if (i11 != 0) {
                        function8 = null;
                    }
                    if (i13 != 0) {
                        function9 = null;
                    }
                    if ((i3 & 128) != 0) {
                        i4 &= -29360129;
                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i3 & 256) != 0) {
                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        containerColor = j;
                    }
                    if ((i3 & 512) != 0) {
                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                        i4 &= -1879048193;
                    } else {
                        iconContentColor = j2;
                    }
                    if ((i3 & 1024) != 0) {
                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                        i15 &= -15;
                    } else {
                        titleContentColor = j3;
                    }
                    if ((i3 & 2048) != 0) {
                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                        i15 &= -113;
                    } else {
                        textContentColor = j5;
                    }
                    if (i16 != 0) {
                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                    } else {
                        fM69getTonalElevationD9Ej5fM = f;
                    }
                    if (i19 != 0) {
                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties3 = dialogProperties;
                    }
                    function15 = function8;
                    function16 = function9;
                    shape4 = shape2;
                    modifier4 = modifier2;
                    i20 = 94478519;
                    int i2111111111117 = i4;
                    function17 = function7;
                    function18 = function14;
                    j10 = containerColor;
                    j11 = textContentColor;
                    i21 = i2111111111117;
                    i22 = i15;
                    j12 = iconContentColor;
                    j13 = titleContentColor;
                } else {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        function14 = null;
                    } else {
                        function14 = function3;
                    }
                    if (i9 != 0) {
                        function7 = null;
                    }
                    if (i11 != 0) {
                        function8 = null;
                    }
                    if (i13 != 0) {
                        function9 = null;
                    }
                    if ((i3 & 128) != 0) {
                        i4 &= -29360129;
                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i3 & 256) != 0) {
                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        containerColor = j;
                    }
                    if ((i3 & 512) != 0) {
                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                        i4 &= -1879048193;
                    } else {
                        iconContentColor = j2;
                    }
                    if ((i3 & 1024) != 0) {
                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                        i15 &= -15;
                    } else {
                        titleContentColor = j3;
                    }
                    if ((i3 & 2048) != 0) {
                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                        i15 &= -113;
                    } else {
                        textContentColor = j5;
                    }
                    if (i16 != 0) {
                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                    } else {
                        fM69getTonalElevationD9Ej5fM = f;
                    }
                    if (i19 != 0) {
                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties3 = dialogProperties;
                    }
                    function15 = function8;
                    function16 = function9;
                    shape4 = shape2;
                    modifier4 = modifier2;
                    i20 = 94478519;
                    int i2111111111118 = i4;
                    function17 = function7;
                    function18 = function14;
                    j10 = containerColor;
                    j11 = textContentColor;
                    i21 = i2111111111118;
                    i22 = i15;
                    j12 = iconContentColor;
                    j13 = titleContentColor;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                }
                composer2 = composerStartRestartGroup;
                AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                function10 = function18;
                function11 = function17;
                function12 = function15;
                function13 = function16;
                shape3 = shape4;
                j8 = j10;
                j9 = j12;
                j6 = j13;
                j7 = j11;
                f2 = fM69getTonalElevationD9Ej5fM;
                dialogProperties2 = dialogProperties3;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                function10 = function3;
                f2 = f;
                dialogProperties2 = dialogProperties;
                shape3 = shape2;
                modifier3 = modifier2;
                j6 = j3;
                j7 = j5;
                function11 = function7;
                function12 = function8;
                function13 = function9;
                j8 = j;
                j9 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                    public final Object invoke(Object obj, Object obj2) {
                        return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 3072;
        i9 = i3 & 16;
        if (i9 != 0) {
            if ((i & 24576) == 0) {
                function7 = function4;
                if (composerStartRestartGroup.changedInstance(function7)) {
                    i10 = 16384;
                } else {
                    i10 = 8192;
                }
                i4 |= i10;
            }
            i11 = i3 & 32;
            if (i11 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                function8 = function5;
            } else {
                function8 = function5;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changedInstance(function8)) {
                        i12 = 131072;
                    } else {
                        i12 = 65536;
                    }
                    i4 |= i12;
                }
            }
            i13 = i3 & 64;
            if (i13 != 0) {
                i4 |= 1572864;
                function9 = function6;
            } else {
                function9 = function6;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function9)) {
                        i14 = 1048576;
                    } else {
                        i14 = 524288;
                    }
                    i4 |= i14;
                }
            }
            if ((i & 12582912) == 0) {
                if ((i3 & 128) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i4 |= i27;
                } else {
                    shape2 = shape;
                }
                i4 |= i27;
            } else {
                shape2 = shape;
            }
            if ((i & 100663296) != 0) {
                if ((i3 & 256) == 0) {
                    i26 = 33554432;
                } else {
                    i26 = 33554432;
                }
                i4 |= i26;
            }
            if ((805306368 & i) != 0) {
                if ((i3 & 512) == 0) {
                    i25 = 268435456;
                } else {
                    i25 = 268435456;
                }
                i4 |= i25;
            }
            if ((i2 & 6) == 0) {
                if ((i3 & 1024) == 0) {
                    i24 = 2;
                } else {
                    i24 = 2;
                }
                i15 = i2 | i24;
            } else {
                i15 = i2;
            }
            if ((i2 & 48) == 0) {
                j5 = j4;
                if ((i3 & 2048) == 0) {
                    i23 = 16;
                } else {
                    i23 = 16;
                }
                i15 |= i23;
            } else {
                j5 = j4;
            }
            i16 = i3 & 4096;
            if (i16 != 0) {
                if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    if (composerStartRestartGroup.changed(f)) {
                        i17 = 256;
                    } else {
                        i17 = 128;
                    }
                    i15 |= i17;
                }
                i18 = i3 & 8192;
                if (i18 != 0) {
                    i19 = i18;
                    if ((i2 & 3072) == 0) {
                        i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i2111111111119 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i2111111111119;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        } else {
                            if (i5 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i7 != 0) {
                                function14 = null;
                            } else {
                                function14 = function3;
                            }
                            if (i9 != 0) {
                                function7 = null;
                            }
                            if (i11 != 0) {
                                function8 = null;
                            }
                            if (i13 != 0) {
                                function9 = null;
                            }
                            if ((i3 & 128) != 0) {
                                i4 &= -29360129;
                                shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                            }
                            if ((i3 & 256) != 0) {
                                containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                                i4 &= -234881025;
                            } else {
                                containerColor = j;
                            }
                            if ((i3 & 512) != 0) {
                                iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                                i4 &= -1879048193;
                            } else {
                                iconContentColor = j2;
                            }
                            if ((i3 & 1024) != 0) {
                                titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                                i15 &= -15;
                            } else {
                                titleContentColor = j3;
                            }
                            if ((i3 & 2048) != 0) {
                                textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                                i15 &= -113;
                            } else {
                                textContentColor = j5;
                            }
                            if (i16 != 0) {
                                fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                            } else {
                                fM69getTonalElevationD9Ej5fM = f;
                            }
                            if (i19 != 0) {
                                dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                            } else {
                                dialogProperties3 = dialogProperties;
                            }
                            function15 = function8;
                            function16 = function9;
                            shape4 = shape2;
                            modifier4 = modifier2;
                            i20 = 94478519;
                            int i21111111111110 = i4;
                            function17 = function7;
                            function18 = function14;
                            j10 = containerColor;
                            j11 = textContentColor;
                            i21 = i21111111111110;
                            i22 = i15;
                            j12 = iconContentColor;
                            j13 = titleContentColor;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                        }
                        composer2 = composerStartRestartGroup;
                        AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        function10 = function18;
                        function11 = function17;
                        function12 = function15;
                        function13 = function16;
                        shape3 = shape4;
                        j8 = j10;
                        j9 = j12;
                        j6 = j13;
                        j7 = j11;
                        f2 = fM69getTonalElevationD9Ej5fM;
                        dialogProperties2 = dialogProperties3;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        function10 = function3;
                        f2 = f;
                        dialogProperties2 = dialogProperties;
                        shape3 = shape2;
                        modifier3 = modifier2;
                        j6 = j3;
                        j7 = j5;
                        function11 = function7;
                        function12 = function8;
                        function13 = function9;
                        j8 = j;
                        j9 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                            public final Object invoke(Object obj, Object obj2) {
                                return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i15 |= 3072;
                i19 = i18;
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21111111111111 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21111111111111;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21111111111112 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21111111111112;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                    }
                    composer2 = composerStartRestartGroup;
                    AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    function10 = function18;
                    function11 = function17;
                    function12 = function15;
                    function13 = function16;
                    shape3 = shape4;
                    j8 = j10;
                    j9 = j12;
                    j6 = j13;
                    j7 = j11;
                    f2 = fM69getTonalElevationD9Ej5fM;
                    dialogProperties2 = dialogProperties3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function3;
                    f2 = f;
                    dialogProperties2 = dialogProperties;
                    shape3 = shape2;
                    modifier3 = modifier2;
                    j6 = j3;
                    j7 = j5;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    j8 = j;
                    j9 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i15 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            i18 = i3 & 8192;
            if (i18 != 0) {
                i19 = i18;
                if ((i2 & 3072) == 0) {
                    i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                }
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21111111111113 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21111111111113;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21111111111114 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21111111111114;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                    }
                    composer2 = composerStartRestartGroup;
                    AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    function10 = function18;
                    function11 = function17;
                    function12 = function15;
                    function13 = function16;
                    shape3 = shape4;
                    j8 = j10;
                    j9 = j12;
                    j6 = j13;
                    j7 = j11;
                    f2 = fM69getTonalElevationD9Ej5fM;
                    dialogProperties2 = dialogProperties3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function3;
                    f2 = f;
                    dialogProperties2 = dialogProperties;
                    shape3 = shape2;
                    modifier3 = modifier2;
                    j6 = j3;
                    j7 = j5;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    j8 = j;
                    j9 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i15 |= 3072;
            i19 = i18;
            if ((i4 & 306783379) == 306783378) {
                z = true;
            } else {
                z = true;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        function14 = null;
                    } else {
                        function14 = function3;
                    }
                    if (i9 != 0) {
                        function7 = null;
                    }
                    if (i11 != 0) {
                        function8 = null;
                    }
                    if (i13 != 0) {
                        function9 = null;
                    }
                    if ((i3 & 128) != 0) {
                        i4 &= -29360129;
                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i3 & 256) != 0) {
                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        containerColor = j;
                    }
                    if ((i3 & 512) != 0) {
                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                        i4 &= -1879048193;
                    } else {
                        iconContentColor = j2;
                    }
                    if ((i3 & 1024) != 0) {
                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                        i15 &= -15;
                    } else {
                        titleContentColor = j3;
                    }
                    if ((i3 & 2048) != 0) {
                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                        i15 &= -113;
                    } else {
                        textContentColor = j5;
                    }
                    if (i16 != 0) {
                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                    } else {
                        fM69getTonalElevationD9Ej5fM = f;
                    }
                    if (i19 != 0) {
                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties3 = dialogProperties;
                    }
                    function15 = function8;
                    function16 = function9;
                    shape4 = shape2;
                    modifier4 = modifier2;
                    i20 = 94478519;
                    int i21111111111115 = i4;
                    function17 = function7;
                    function18 = function14;
                    j10 = containerColor;
                    j11 = textContentColor;
                    i21 = i21111111111115;
                    i22 = i15;
                    j12 = iconContentColor;
                    j13 = titleContentColor;
                } else {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        function14 = null;
                    } else {
                        function14 = function3;
                    }
                    if (i9 != 0) {
                        function7 = null;
                    }
                    if (i11 != 0) {
                        function8 = null;
                    }
                    if (i13 != 0) {
                        function9 = null;
                    }
                    if ((i3 & 128) != 0) {
                        i4 &= -29360129;
                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i3 & 256) != 0) {
                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        containerColor = j;
                    }
                    if ((i3 & 512) != 0) {
                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                        i4 &= -1879048193;
                    } else {
                        iconContentColor = j2;
                    }
                    if ((i3 & 1024) != 0) {
                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                        i15 &= -15;
                    } else {
                        titleContentColor = j3;
                    }
                    if ((i3 & 2048) != 0) {
                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                        i15 &= -113;
                    } else {
                        textContentColor = j5;
                    }
                    if (i16 != 0) {
                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                    } else {
                        fM69getTonalElevationD9Ej5fM = f;
                    }
                    if (i19 != 0) {
                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties3 = dialogProperties;
                    }
                    function15 = function8;
                    function16 = function9;
                    shape4 = shape2;
                    modifier4 = modifier2;
                    i20 = 94478519;
                    int i21111111111116 = i4;
                    function17 = function7;
                    function18 = function14;
                    j10 = containerColor;
                    j11 = textContentColor;
                    i21 = i21111111111116;
                    i22 = i15;
                    j12 = iconContentColor;
                    j13 = titleContentColor;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                }
                composer2 = composerStartRestartGroup;
                AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                function10 = function18;
                function11 = function17;
                function12 = function15;
                function13 = function16;
                shape3 = shape4;
                j8 = j10;
                j9 = j12;
                j6 = j13;
                j7 = j11;
                f2 = fM69getTonalElevationD9Ej5fM;
                dialogProperties2 = dialogProperties3;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                function10 = function3;
                f2 = f;
                dialogProperties2 = dialogProperties;
                shape3 = shape2;
                modifier3 = modifier2;
                j6 = j3;
                j7 = j5;
                function11 = function7;
                function12 = function8;
                function13 = function9;
                j8 = j;
                j9 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                    public final Object invoke(Object obj, Object obj2) {
                        return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 24576;
        function7 = function4;
        i11 = i3 & 32;
        if (i11 != 0) {
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function8 = function5;
        } else {
            function8 = function5;
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changedInstance(function8)) {
                    i12 = 131072;
                } else {
                    i12 = 65536;
                }
                i4 |= i12;
            }
        }
        i13 = i3 & 64;
        if (i13 != 0) {
            i4 |= 1572864;
            function9 = function6;
        } else {
            function9 = function6;
            if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function9)) {
                    i14 = 1048576;
                } else {
                    i14 = 524288;
                }
                i4 |= i14;
            }
        }
        if ((i & 12582912) == 0) {
            if ((i3 & 128) == 0) {
                shape2 = shape;
                if (composerStartRestartGroup.changed(shape2)) {
                }
                i4 |= i27;
            } else {
                shape2 = shape;
            }
            i4 |= i27;
        } else {
            shape2 = shape;
        }
        if ((i & 100663296) != 0) {
            if ((i3 & 256) == 0) {
                i26 = 33554432;
            } else {
                i26 = 33554432;
            }
            i4 |= i26;
        }
        if ((805306368 & i) != 0) {
            if ((i3 & 512) == 0) {
                i25 = 268435456;
            } else {
                i25 = 268435456;
            }
            i4 |= i25;
        }
        if ((i2 & 6) == 0) {
            if ((i3 & 1024) == 0) {
                i24 = 2;
            } else {
                i24 = 2;
            }
            i15 = i2 | i24;
        } else {
            i15 = i2;
        }
        if ((i2 & 48) == 0) {
            j5 = j4;
            if ((i3 & 2048) == 0) {
                i23 = 16;
            } else {
                i23 = 16;
            }
            i15 |= i23;
        } else {
            j5 = j4;
        }
        i16 = i3 & 4096;
        if (i16 != 0) {
            if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if (composerStartRestartGroup.changed(f)) {
                    i17 = 256;
                } else {
                    i17 = 128;
                }
                i15 |= i17;
            }
            i18 = i3 & 8192;
            if (i18 != 0) {
                i19 = i18;
                if ((i2 & 3072) == 0) {
                    i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
                }
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21111111111117 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21111111111117;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    } else {
                        if (i5 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i7 != 0) {
                            function14 = null;
                        } else {
                            function14 = function3;
                        }
                        if (i9 != 0) {
                            function7 = null;
                        }
                        if (i11 != 0) {
                            function8 = null;
                        }
                        if (i13 != 0) {
                            function9 = null;
                        }
                        if ((i3 & 128) != 0) {
                            i4 &= -29360129;
                            shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                        }
                        if ((i3 & 256) != 0) {
                            containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                            i4 &= -234881025;
                        } else {
                            containerColor = j;
                        }
                        if ((i3 & 512) != 0) {
                            iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                            i4 &= -1879048193;
                        } else {
                            iconContentColor = j2;
                        }
                        if ((i3 & 1024) != 0) {
                            titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                            i15 &= -15;
                        } else {
                            titleContentColor = j3;
                        }
                        if ((i3 & 2048) != 0) {
                            textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                            i15 &= -113;
                        } else {
                            textContentColor = j5;
                        }
                        if (i16 != 0) {
                            fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                        } else {
                            fM69getTonalElevationD9Ej5fM = f;
                        }
                        if (i19 != 0) {
                            dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                        } else {
                            dialogProperties3 = dialogProperties;
                        }
                        function15 = function8;
                        function16 = function9;
                        shape4 = shape2;
                        modifier4 = modifier2;
                        i20 = 94478519;
                        int i21111111111118 = i4;
                        function17 = function7;
                        function18 = function14;
                        j10 = containerColor;
                        j11 = textContentColor;
                        i21 = i21111111111118;
                        i22 = i15;
                        j12 = iconContentColor;
                        j13 = titleContentColor;
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                    }
                    composer2 = composerStartRestartGroup;
                    AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    function10 = function18;
                    function11 = function17;
                    function12 = function15;
                    function13 = function16;
                    shape3 = shape4;
                    j8 = j10;
                    j9 = j12;
                    j6 = j13;
                    j7 = j11;
                    f2 = fM69getTonalElevationD9Ej5fM;
                    dialogProperties2 = dialogProperties3;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function10 = function3;
                    f2 = f;
                    dialogProperties2 = dialogProperties;
                    shape3 = shape2;
                    modifier3 = modifier2;
                    j6 = j3;
                    j7 = j5;
                    function11 = function7;
                    function12 = function8;
                    function13 = function9;
                    j8 = j;
                    j9 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                        public final Object invoke(Object obj, Object obj2) {
                            return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i15 |= 3072;
            i19 = i18;
            if ((i4 & 306783379) == 306783378) {
                z = true;
            } else {
                z = true;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        function14 = null;
                    } else {
                        function14 = function3;
                    }
                    if (i9 != 0) {
                        function7 = null;
                    }
                    if (i11 != 0) {
                        function8 = null;
                    }
                    if (i13 != 0) {
                        function9 = null;
                    }
                    if ((i3 & 128) != 0) {
                        i4 &= -29360129;
                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i3 & 256) != 0) {
                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        containerColor = j;
                    }
                    if ((i3 & 512) != 0) {
                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                        i4 &= -1879048193;
                    } else {
                        iconContentColor = j2;
                    }
                    if ((i3 & 1024) != 0) {
                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                        i15 &= -15;
                    } else {
                        titleContentColor = j3;
                    }
                    if ((i3 & 2048) != 0) {
                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                        i15 &= -113;
                    } else {
                        textContentColor = j5;
                    }
                    if (i16 != 0) {
                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                    } else {
                        fM69getTonalElevationD9Ej5fM = f;
                    }
                    if (i19 != 0) {
                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties3 = dialogProperties;
                    }
                    function15 = function8;
                    function16 = function9;
                    shape4 = shape2;
                    modifier4 = modifier2;
                    i20 = 94478519;
                    int i21111111111119 = i4;
                    function17 = function7;
                    function18 = function14;
                    j10 = containerColor;
                    j11 = textContentColor;
                    i21 = i21111111111119;
                    i22 = i15;
                    j12 = iconContentColor;
                    j13 = titleContentColor;
                } else {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        function14 = null;
                    } else {
                        function14 = function3;
                    }
                    if (i9 != 0) {
                        function7 = null;
                    }
                    if (i11 != 0) {
                        function8 = null;
                    }
                    if (i13 != 0) {
                        function9 = null;
                    }
                    if ((i3 & 128) != 0) {
                        i4 &= -29360129;
                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i3 & 256) != 0) {
                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        containerColor = j;
                    }
                    if ((i3 & 512) != 0) {
                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                        i4 &= -1879048193;
                    } else {
                        iconContentColor = j2;
                    }
                    if ((i3 & 1024) != 0) {
                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                        i15 &= -15;
                    } else {
                        titleContentColor = j3;
                    }
                    if ((i3 & 2048) != 0) {
                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                        i15 &= -113;
                    } else {
                        textContentColor = j5;
                    }
                    if (i16 != 0) {
                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                    } else {
                        fM69getTonalElevationD9Ej5fM = f;
                    }
                    if (i19 != 0) {
                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties3 = dialogProperties;
                    }
                    function15 = function8;
                    function16 = function9;
                    shape4 = shape2;
                    modifier4 = modifier2;
                    i20 = 94478519;
                    int i211111111111110 = i4;
                    function17 = function7;
                    function18 = function14;
                    j10 = containerColor;
                    j11 = textContentColor;
                    i21 = i211111111111110;
                    i22 = i15;
                    j12 = iconContentColor;
                    j13 = titleContentColor;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                }
                composer2 = composerStartRestartGroup;
                AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                function10 = function18;
                function11 = function17;
                function12 = function15;
                function13 = function16;
                shape3 = shape4;
                j8 = j10;
                j9 = j12;
                j6 = j13;
                j7 = j11;
                f2 = fM69getTonalElevationD9Ej5fM;
                dialogProperties2 = dialogProperties3;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                function10 = function3;
                f2 = f;
                dialogProperties2 = dialogProperties;
                shape3 = shape2;
                modifier3 = modifier2;
                j6 = j3;
                j7 = j5;
                function11 = function7;
                function12 = function8;
                function13 = function9;
                j8 = j;
                j9 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                    public final Object invoke(Object obj, Object obj2) {
                        return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i15 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        i18 = i3 & 8192;
        if (i18 != 0) {
            i19 = i18;
            if ((i2 & 3072) == 0) {
                i15 |= composerStartRestartGroup.changed(dialogProperties) ? 2048 : 1024;
            }
            if ((i4 & 306783379) == 306783378) {
                z = true;
            } else {
                z = true;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        function14 = null;
                    } else {
                        function14 = function3;
                    }
                    if (i9 != 0) {
                        function7 = null;
                    }
                    if (i11 != 0) {
                        function8 = null;
                    }
                    if (i13 != 0) {
                        function9 = null;
                    }
                    if ((i3 & 128) != 0) {
                        i4 &= -29360129;
                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i3 & 256) != 0) {
                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        containerColor = j;
                    }
                    if ((i3 & 512) != 0) {
                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                        i4 &= -1879048193;
                    } else {
                        iconContentColor = j2;
                    }
                    if ((i3 & 1024) != 0) {
                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                        i15 &= -15;
                    } else {
                        titleContentColor = j3;
                    }
                    if ((i3 & 2048) != 0) {
                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                        i15 &= -113;
                    } else {
                        textContentColor = j5;
                    }
                    if (i16 != 0) {
                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                    } else {
                        fM69getTonalElevationD9Ej5fM = f;
                    }
                    if (i19 != 0) {
                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties3 = dialogProperties;
                    }
                    function15 = function8;
                    function16 = function9;
                    shape4 = shape2;
                    modifier4 = modifier2;
                    i20 = 94478519;
                    int i211111111111111 = i4;
                    function17 = function7;
                    function18 = function14;
                    j10 = containerColor;
                    j11 = textContentColor;
                    i21 = i211111111111111;
                    i22 = i15;
                    j12 = iconContentColor;
                    j13 = titleContentColor;
                } else {
                    if (i5 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i7 != 0) {
                        function14 = null;
                    } else {
                        function14 = function3;
                    }
                    if (i9 != 0) {
                        function7 = null;
                    }
                    if (i11 != 0) {
                        function8 = null;
                    }
                    if (i13 != 0) {
                        function9 = null;
                    }
                    if ((i3 & 128) != 0) {
                        i4 &= -29360129;
                        shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                    }
                    if ((i3 & 256) != 0) {
                        containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                        i4 &= -234881025;
                    } else {
                        containerColor = j;
                    }
                    if ((i3 & 512) != 0) {
                        iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                        i4 &= -1879048193;
                    } else {
                        iconContentColor = j2;
                    }
                    if ((i3 & 1024) != 0) {
                        titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                        i15 &= -15;
                    } else {
                        titleContentColor = j3;
                    }
                    if ((i3 & 2048) != 0) {
                        textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                        i15 &= -113;
                    } else {
                        textContentColor = j5;
                    }
                    if (i16 != 0) {
                        fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                    } else {
                        fM69getTonalElevationD9Ej5fM = f;
                    }
                    if (i19 != 0) {
                        dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                    } else {
                        dialogProperties3 = dialogProperties;
                    }
                    function15 = function8;
                    function16 = function9;
                    shape4 = shape2;
                    modifier4 = modifier2;
                    i20 = 94478519;
                    int i211111111111112 = i4;
                    function17 = function7;
                    function18 = function14;
                    j10 = containerColor;
                    j11 = textContentColor;
                    i21 = i211111111111112;
                    i22 = i15;
                    j12 = iconContentColor;
                    j13 = titleContentColor;
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
                }
                composer2 = composerStartRestartGroup;
                AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                function10 = function18;
                function11 = function17;
                function12 = function15;
                function13 = function16;
                shape3 = shape4;
                j8 = j10;
                j9 = j12;
                j6 = j13;
                j7 = j11;
                f2 = fM69getTonalElevationD9Ej5fM;
                dialogProperties2 = dialogProperties3;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                function10 = function3;
                f2 = f;
                dialogProperties2 = dialogProperties;
                shape3 = shape2;
                modifier3 = modifier2;
                j6 = j3;
                j7 = j5;
                function11 = function7;
                function12 = function8;
                function13 = function9;
                j8 = j;
                j9 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                    public final Object invoke(Object obj, Object obj2) {
                        return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i15 |= 3072;
        i19 = i18;
        if ((i4 & 306783379) == 306783378) {
            z = true;
        } else {
            z = true;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    function14 = null;
                } else {
                    function14 = function3;
                }
                if (i9 != 0) {
                    function7 = null;
                }
                if (i11 != 0) {
                    function8 = null;
                }
                if (i13 != 0) {
                    function9 = null;
                }
                if ((i3 & 128) != 0) {
                    i4 &= -29360129;
                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                }
                if ((i3 & 256) != 0) {
                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i4 &= -234881025;
                } else {
                    containerColor = j;
                }
                if ((i3 & 512) != 0) {
                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                    i4 &= -1879048193;
                } else {
                    iconContentColor = j2;
                }
                if ((i3 & 1024) != 0) {
                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                    i15 &= -15;
                } else {
                    titleContentColor = j3;
                }
                if ((i3 & 2048) != 0) {
                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                    i15 &= -113;
                } else {
                    textContentColor = j5;
                }
                if (i16 != 0) {
                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                } else {
                    fM69getTonalElevationD9Ej5fM = f;
                }
                if (i19 != 0) {
                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                } else {
                    dialogProperties3 = dialogProperties;
                }
                function15 = function8;
                function16 = function9;
                shape4 = shape2;
                modifier4 = modifier2;
                i20 = 94478519;
                int i211111111111113 = i4;
                function17 = function7;
                function18 = function14;
                j10 = containerColor;
                j11 = textContentColor;
                i21 = i211111111111113;
                i22 = i15;
                j12 = iconContentColor;
                j13 = titleContentColor;
            } else {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    function14 = null;
                } else {
                    function14 = function3;
                }
                if (i9 != 0) {
                    function7 = null;
                }
                if (i11 != 0) {
                    function8 = null;
                }
                if (i13 != 0) {
                    function9 = null;
                }
                if ((i3 & 128) != 0) {
                    i4 &= -29360129;
                    shape2 = AlertDialogDefaults.INSTANCE.getShape(composerStartRestartGroup, 6);
                }
                if ((i3 & 256) != 0) {
                    containerColor = AlertDialogDefaults.INSTANCE.getContainerColor(composerStartRestartGroup, 6);
                    i4 &= -234881025;
                } else {
                    containerColor = j;
                }
                if ((i3 & 512) != 0) {
                    iconContentColor = AlertDialogDefaults.INSTANCE.getIconContentColor(composerStartRestartGroup, 6);
                    i4 &= -1879048193;
                } else {
                    iconContentColor = j2;
                }
                if ((i3 & 1024) != 0) {
                    titleContentColor = AlertDialogDefaults.INSTANCE.getTitleContentColor(composerStartRestartGroup, 6);
                    i15 &= -15;
                } else {
                    titleContentColor = j3;
                }
                if ((i3 & 2048) != 0) {
                    textContentColor = AlertDialogDefaults.INSTANCE.getTextContentColor(composerStartRestartGroup, 6);
                    i15 &= -113;
                } else {
                    textContentColor = j5;
                }
                if (i16 != 0) {
                    fM69getTonalElevationD9Ej5fM = AlertDialogDefaults.INSTANCE.m69getTonalElevationD9Ej5fM();
                } else {
                    fM69getTonalElevationD9Ej5fM = f;
                }
                if (i19 != 0) {
                    dialogProperties3 = new DialogProperties(false, false, false, 7, (DefaultConstructorMarker) null);
                } else {
                    dialogProperties3 = dialogProperties;
                }
                function15 = function8;
                function16 = function9;
                shape4 = shape2;
                modifier4 = modifier2;
                i20 = 94478519;
                int i211111111111114 = i4;
                function17 = function7;
                function18 = function14;
                j10 = containerColor;
                j11 = textContentColor;
                i21 = i211111111111114;
                i22 = i15;
                j12 = iconContentColor;
                j13 = titleContentColor;
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i20, i21, i22, "androidx.compose.material3.AlertDialog (AndroidAlertDialog.android.kt:46)");
            }
            composer2 = composerStartRestartGroup;
            AlertDialogKt.m72AlertDialogImplwrnwzgE(function0, function2, modifier4, function18, function17, function15, function16, shape4, j10, j12, j13, j11, fM69getTonalElevationD9Ej5fM, dialogProperties3, composer2, i21 & 2147483646, i22 & 8190);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            function10 = function18;
            function11 = function17;
            function12 = function15;
            function13 = function16;
            shape3 = shape4;
            j8 = j10;
            j9 = j12;
            j6 = j13;
            j7 = j11;
            f2 = fM69getTonalElevationD9Ej5fM;
            dialogProperties2 = dialogProperties3;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            function10 = function3;
            f2 = f;
            dialogProperties2 = dialogProperties;
            shape3 = shape2;
            modifier3 = modifier2;
            j6 = j3;
            j7 = j5;
            function11 = function7;
            function12 = function8;
            function13 = function9;
            j8 = j;
            j9 = j2;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: d50
                public final Object invoke(Object obj, Object obj2) {
                    return AndroidAlertDialog_androidKt.a(function0, function2, modifier3, function10, function11, function12, function13, shape3, j8, j9, j6, j7, f2, dialogProperties2, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(Function0 function0, Function2 function2, Modifier modifier, Function2 function3, Function2 function4, Function2 function5, Function2 function6, Shape shape, long j, long j2, long j3, long j4, float f, DialogProperties dialogProperties, int i, int i2, int i3, Composer composer, int i4) {
        m77AlertDialogOix01E0(function0, function2, modifier, function3, function4, function5, function6, shape, j, j2, j3, j4, f, dialogProperties, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }
}
