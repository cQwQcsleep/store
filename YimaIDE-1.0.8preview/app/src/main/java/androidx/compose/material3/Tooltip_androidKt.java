package androidx.compose.material3;

import androidx.compose.material3.Tooltip_androidKt;
import androidx.compose.material3.tokens.ElevationTokens;
import androidx.compose.material3.tokens.RichTooltipTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpSize;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u001al\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0004\b\u0012\u0010\u0013\u001av\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\r2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\u0011\u0010\u000f\u001a\r\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0004\b\u0012\u0010\u0015\u001a\u0090\u0001\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0010¢\u0006\u0002\b\u00112\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0010¢\u0006\u0002\b\u00112\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u009a\u0001\u0010\u0016\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0010¢\u0006\u0002\b\u00112\u0015\b\u0002\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0010¢\u0006\u0002\b\u00112\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0014\u001a\u00020\r2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u0019\u001a\u00020\u001a2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\u00010\u0010¢\u0006\u0002\b\u0011H\u0007¢\u0006\u0004\b\u001c\u0010\u001e¨\u0006\u001f"}, d2 = {"PlainTooltipAndroid", "", "Landroidx/compose/material3/TooltipScope;", "modifier", "Landroidx/compose/ui/Modifier;", "caretSize", "Landroidx/compose/ui/unit/DpSize;", "shape", "Landroidx/compose/ui/graphics/Shape;", "contentColor", "Landroidx/compose/ui/graphics/Color;", "containerColor", "tonalElevation", "Landroidx/compose/ui/unit/Dp;", "shadowElevation", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "PlainTooltip", "(Landroidx/compose/material3/TooltipScope;Landroidx/compose/ui/Modifier;JLandroidx/compose/ui/graphics/Shape;JJFFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "maxWidth", "(Landroidx/compose/material3/TooltipScope;Landroidx/compose/ui/Modifier;JFLandroidx/compose/ui/graphics/Shape;JJFFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "RichTooltipAndroid", "title", "action", "colors", "Landroidx/compose/material3/RichTooltipColors;", "text", "RichTooltip", "(Landroidx/compose/material3/TooltipScope;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;JLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/RichTooltipColors;FFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/material3/TooltipScope;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;JFLandroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/RichTooltipColors;FFLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class Tooltip_androidKt {
    /* JADX WARN: Code duplicated, block: B:103:0x011d A[PHI: r19
      0x011d: PHI (r19v8 int) = (r19v4 int), (r19v6 int), (r19v7 int) binds: [B:102:0x011b, B:110:0x0131, B:109:0x012e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:105:0x0122  */
    /* JADX WARN: Code duplicated, block: B:107:0x0126  */
    /* JADX WARN: Code duplicated, block: B:109:0x012e  */
    /* JADX WARN: Code duplicated, block: B:110:0x0131  */
    /* JADX WARN: Code duplicated, block: B:111:0x0134  */
    /* JADX WARN: Code duplicated, block: B:114:0x0141  */
    /* JADX WARN: Code duplicated, block: B:115:0x0143  */
    /* JADX WARN: Code duplicated, block: B:118:0x014c  */
    /* JADX WARN: Code duplicated, block: B:120:0x015c  */
    /* JADX WARN: Code duplicated, block: B:137:0x0188 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:138:0x018a  */
    /* JADX WARN: Code duplicated, block: B:139:0x018d  */
    /* JADX WARN: Code duplicated, block: B:142:0x0192  */
    /* JADX WARN: Code duplicated, block: B:143:0x019b  */
    /* JADX WARN: Code duplicated, block: B:145:0x019e  */
    /* JADX WARN: Code duplicated, block: B:146:0x01a5  */
    /* JADX WARN: Code duplicated, block: B:149:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:150:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:153:0x01b9  */
    /* JADX WARN: Code duplicated, block: B:154:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:157:0x01c6  */
    /* JADX WARN: Code duplicated, block: B:158:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:161:0x01d3  */
    /* JADX WARN: Code duplicated, block: B:162:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:164:0x01db  */
    /* JADX WARN: Code duplicated, block: B:165:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:168:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:169:0x0203  */
    /* JADX WARN: Code duplicated, block: B:172:0x0225  */
    /* JADX WARN: Code duplicated, block: B:174:0x0233  */
    /* JADX WARN: Code duplicated, block: B:177:0x0246  */
    /* JADX WARN: Code duplicated, block: B:179:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:29:0x004f  */
    /* JADX WARN: Code duplicated, block: B:31:0x0053  */
    /* JADX WARN: Code duplicated, block: B:33:0x005b  */
    /* JADX WARN: Code duplicated, block: B:34:0x005e  */
    /* JADX WARN: Code duplicated, block: B:37:0x0064  */
    /* JADX WARN: Code duplicated, block: B:40:0x006a  */
    /* JADX WARN: Code duplicated, block: B:42:0x006f  */
    /* JADX WARN: Code duplicated, block: B:44:0x0073  */
    /* JADX WARN: Code duplicated, block: B:46:0x007b  */
    /* JADX WARN: Code duplicated, block: B:47:0x007e  */
    /* JADX WARN: Code duplicated, block: B:51:0x0085  */
    /* JADX WARN: Code duplicated, block: B:53:0x0089  */
    /* JADX WARN: Code duplicated, block: B:55:0x0091  */
    /* JADX WARN: Code duplicated, block: B:56:0x0094  */
    /* JADX WARN: Code duplicated, block: B:59:0x009a  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:72:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:77:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:82:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:87:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:88:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:92:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:94:0x0102  */
    /* JADX WARN: Code duplicated, block: B:96:0x0106  */
    /* JADX WARN: Code duplicated, block: B:98:0x0110  */
    /* JADX WARN: Code duplicated, block: B:99:0x0113  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility.")
    public static final /* synthetic */ void PlainTooltip(final TooltipScope tooltipScope, Modifier modifier, long j, float f, Shape shape, long j2, long j3, float f2, float f3, final Function2 function2, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        long j4;
        int i4;
        float f4;
        int i5;
        Shape shape2;
        long j5;
        int i6;
        int i7;
        final float f5;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        boolean z;
        final Modifier modifier3;
        final long j6;
        final float f6;
        final Shape shape3;
        final long j7;
        final long j8;
        final float f7;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        long jM6129getUnspecifiedMYxV2XQ;
        float fM1272getPlainTooltipMaxWidthD9Ej5fM;
        Shape plainTooltipContainerShape;
        long plainTooltipContentColor;
        long plainTooltipContainerColor;
        float fM6022constructorimpl;
        long j9;
        Shape shape4;
        long j10;
        int i13;
        float fM6022constructorimpl2;
        int i14;
        int i15;
        Composer composerStartRestartGroup = composer.startRestartGroup(1456881596);
        if ((Integer.MIN_VALUE & i2) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = ((i & 8) == 0 ? composerStartRestartGroup.changed(tooltipScope) : composerStartRestartGroup.changedInstance(tooltipScope) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i16 = i2 & 1;
        if (i16 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i2 & 2) == 0) {
                    j4 = j;
                    int i17 = composerStartRestartGroup.changed(j4) ? 256 : 128;
                    i3 |= i17;
                } else {
                    j4 = j;
                }
                i3 |= i17;
            } else {
                j4 = j;
            }
            i4 = i2 & 4;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    f4 = f;
                    if (composerStartRestartGroup.changed(f4)) {
                        i5 = 2048;
                    } else {
                        i5 = 1024;
                    }
                    i3 |= i5;
                }
                if ((i & 24576) == 0) {
                    if ((i2 & 8) == 0) {
                        shape2 = shape;
                        int i18 = composerStartRestartGroup.changed(shape2) ? 16384 : 8192;
                        i3 |= i18;
                    } else {
                        shape2 = shape;
                    }
                    i3 |= i18;
                } else {
                    shape2 = shape;
                }
                if ((196608 & i) == 0) {
                    j5 = j2;
                    if ((i2 & 16) == 0 || !composerStartRestartGroup.changed(j5)) {
                        i15 = 65536;
                    } else {
                        i15 = 131072;
                    }
                    i3 |= i15;
                } else {
                    j5 = j2;
                }
                if ((1572864 & i) == 0) {
                    int i19 = i3;
                    if ((i2 & 32) == 0 || !composerStartRestartGroup.changed(j3)) {
                        i14 = 524288;
                    } else {
                        i14 = 1048576;
                    }
                    i6 = i19 | i14;
                } else {
                    i6 = i3;
                }
                i7 = i2 & 64;
                if (i7 != 0) {
                    i6 |= 12582912;
                    f5 = f2;
                } else {
                    f5 = f2;
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(f5)) {
                            i8 = 8388608;
                        } else {
                            i8 = 4194304;
                        }
                        i6 |= i8;
                    }
                }
                i9 = i2 & 128;
                if (i9 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i10 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i10 = 33554432;
                        }
                        i6 |= i10;
                    }
                    i11 = 805306368;
                    if ((i2 & 256) == 0) {
                        i6 |= i11;
                    } else if ((i & 805306368) != 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i11 = 536870912;
                        } else {
                            i11 = 268435456;
                        }
                        i6 |= i11;
                    }
                    i12 = i6;
                    if ((i12 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i12 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                            if (i16 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if ((i2 & 2) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i12 &= -897;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j4;
                            }
                            if (i4 != 0) {
                                fM1272getPlainTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1272getPlainTooltipMaxWidthD9Ej5fM();
                            } else {
                                fM1272getPlainTooltipMaxWidthD9Ej5fM = f4;
                            }
                            if ((i2 & 8) != 0) {
                                plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                                i12 &= -57345;
                            } else {
                                plainTooltipContainerShape = shape2;
                            }
                            if ((i2 & 16) != 0) {
                                plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                                i12 &= -458753;
                            } else {
                                plainTooltipContentColor = j5;
                            }
                            if ((i2 & 32) != 0) {
                                plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                                i12 &= -3670017;
                            } else {
                                plainTooltipContainerColor = j3;
                            }
                            if (i7 != 0) {
                                fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                            } else {
                                fM6022constructorimpl = f5;
                            }
                            if (i9 != 0) {
                                f5 = fM6022constructorimpl;
                                i13 = i12;
                                fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                                j9 = jM6129getUnspecifiedMYxV2XQ;
                                shape4 = plainTooltipContainerShape;
                                j10 = plainTooltipContainerColor;
                            } else {
                                f5 = fM6022constructorimpl;
                                j9 = jM6129getUnspecifiedMYxV2XQ;
                                shape4 = plainTooltipContainerShape;
                                j10 = plainTooltipContainerColor;
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1456881596, i13, -1, "androidx.compose.material3.PlainTooltipAndroid (Tooltip.android.kt:104)");
                            }
                            int i20 = 2147482750 & i13;
                            long j11 = j9;
                            float f8 = fM1272getPlainTooltipMaxWidthD9Ej5fM;
                            long j12 = plainTooltipContentColor;
                            float f9 = f5;
                            float f10 = fM6022constructorimpl2;
                            TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, modifier4, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j9), f8, shape4, j12, j10, f9, f10, function2, composerStartRestartGroup, i20, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            f5 = f9;
                            f7 = f10;
                            j8 = j10;
                            j7 = j12;
                            f6 = f8;
                            shape3 = shape4;
                            j6 = j11;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            if ((i2 & 2) != 0) {
                                i12 &= -897;
                            }
                            if ((i2 & 8) != 0) {
                                i12 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i12 &= -458753;
                            }
                            if ((i2 & 32) != 0) {
                                i12 &= -3670017;
                            }
                            modifier4 = modifier2;
                            j9 = j4;
                            fM1272getPlainTooltipMaxWidthD9Ej5fM = f4;
                            shape4 = shape2;
                            plainTooltipContentColor = j5;
                            j10 = j3;
                        }
                        i13 = i12;
                        fM6022constructorimpl2 = f3;
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1456881596, i13, -1, "androidx.compose.material3.PlainTooltipAndroid (Tooltip.android.kt:104)");
                        }
                        int i21 = 2147482750 & i13;
                        long j13 = j9;
                        float f11 = fM1272getPlainTooltipMaxWidthD9Ej5fM;
                        long j14 = plainTooltipContentColor;
                        float f12 = f5;
                        float f13 = fM6022constructorimpl2;
                        TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, modifier4, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j9), f11, shape4, j14, j10, f12, f13, function2, composerStartRestartGroup, i21, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        f5 = f12;
                        f7 = f13;
                        j8 = j10;
                        j7 = j14;
                        f6 = f11;
                        shape3 = shape4;
                        j6 = j13;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        j6 = j4;
                        f6 = f4;
                        shape3 = shape2;
                        j7 = j5;
                        j8 = j3;
                        f7 = f3;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jhe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.d(tooltipScope, modifier3, j6, f6, shape3, j7, j8, f5, f7, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i6 |= 100663296;
                i11 = 805306368;
                if ((i2 & 256) == 0) {
                    i6 |= i11;
                } else if ((i & 805306368) != 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i6 |= i11;
                }
                i12 = i6;
                if ((i12 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i12 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i16 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i2 & 2) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i12 &= -897;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j4;
                        }
                        if (i4 != 0) {
                            fM1272getPlainTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1272getPlainTooltipMaxWidthD9Ej5fM();
                        } else {
                            fM1272getPlainTooltipMaxWidthD9Ej5fM = f4;
                        }
                        if ((i2 & 8) != 0) {
                            plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                            i12 &= -57345;
                        } else {
                            plainTooltipContainerShape = shape2;
                        }
                        if ((i2 & 16) != 0) {
                            plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                            i12 &= -458753;
                        } else {
                            plainTooltipContentColor = j5;
                        }
                        if ((i2 & 32) != 0) {
                            plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                            i12 &= -3670017;
                        } else {
                            plainTooltipContainerColor = j3;
                        }
                        if (i7 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f5;
                        }
                        if (i9 != 0) {
                            f5 = fM6022constructorimpl;
                            i13 = i12;
                            fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                            j9 = jM6129getUnspecifiedMYxV2XQ;
                            shape4 = plainTooltipContainerShape;
                            j10 = plainTooltipContainerColor;
                        } else {
                            f5 = fM6022constructorimpl;
                            j9 = jM6129getUnspecifiedMYxV2XQ;
                            shape4 = plainTooltipContainerShape;
                            j10 = plainTooltipContainerColor;
                            i13 = i12;
                            fM6022constructorimpl2 = f3;
                        }
                    } else {
                        if (i16 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i2 & 2) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i12 &= -897;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j4;
                        }
                        if (i4 != 0) {
                            fM1272getPlainTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1272getPlainTooltipMaxWidthD9Ej5fM();
                        } else {
                            fM1272getPlainTooltipMaxWidthD9Ej5fM = f4;
                        }
                        if ((i2 & 8) != 0) {
                            plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                            i12 &= -57345;
                        } else {
                            plainTooltipContainerShape = shape2;
                        }
                        if ((i2 & 16) != 0) {
                            plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                            i12 &= -458753;
                        } else {
                            plainTooltipContentColor = j5;
                        }
                        if ((i2 & 32) != 0) {
                            plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                            i12 &= -3670017;
                        } else {
                            plainTooltipContainerColor = j3;
                        }
                        if (i7 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f5;
                        }
                        if (i9 != 0) {
                            f5 = fM6022constructorimpl;
                            i13 = i12;
                            fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                            j9 = jM6129getUnspecifiedMYxV2XQ;
                            shape4 = plainTooltipContainerShape;
                            j10 = plainTooltipContainerColor;
                        } else {
                            f5 = fM6022constructorimpl;
                            j9 = jM6129getUnspecifiedMYxV2XQ;
                            shape4 = plainTooltipContainerShape;
                            j10 = plainTooltipContainerColor;
                            i13 = i12;
                            fM6022constructorimpl2 = f3;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1456881596, i13, -1, "androidx.compose.material3.PlainTooltipAndroid (Tooltip.android.kt:104)");
                    }
                    int i22 = 2147482750 & i13;
                    long j15 = j9;
                    float f14 = fM1272getPlainTooltipMaxWidthD9Ej5fM;
                    long j16 = plainTooltipContentColor;
                    float f15 = f5;
                    float f16 = fM6022constructorimpl2;
                    TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, modifier4, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j9), f14, shape4, j16, j10, f15, f16, function2, composerStartRestartGroup, i22, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    f5 = f15;
                    f7 = f16;
                    j8 = j10;
                    j7 = j16;
                    f6 = f14;
                    shape3 = shape4;
                    j6 = j15;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    j6 = j4;
                    f6 = f4;
                    shape3 = shape2;
                    j7 = j5;
                    j8 = j3;
                    f7 = f3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jhe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.d(tooltipScope, modifier3, j6, f6, shape3, j7, j8, f5, f7, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            f4 = f;
            if ((i & 24576) == 0) {
                if ((i2 & 8) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i3 |= i18;
                } else {
                    shape2 = shape;
                }
                i3 |= i18;
            } else {
                shape2 = shape;
            }
            if ((196608 & i) == 0) {
                j5 = j2;
                if ((i2 & 16) == 0) {
                    i15 = 65536;
                } else {
                    i15 = 65536;
                }
                i3 |= i15;
            } else {
                j5 = j2;
            }
            if ((1572864 & i) == 0) {
                int i110 = i3;
                if ((i2 & 32) == 0) {
                    i14 = 524288;
                } else {
                    i14 = 524288;
                }
                i6 = i110 | i14;
            } else {
                i6 = i3;
            }
            i7 = i2 & 64;
            if (i7 != 0) {
                i6 |= 12582912;
                f5 = f2;
            } else {
                f5 = f2;
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(f5)) {
                        i8 = 8388608;
                    } else {
                        i8 = 4194304;
                    }
                    i6 |= i8;
                }
            }
            i9 = i2 & 128;
            if (i9 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i10 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i10 = 33554432;
                    }
                    i6 |= i10;
                }
                i11 = 805306368;
                if ((i2 & 256) == 0) {
                    i6 |= i11;
                } else if ((i & 805306368) != 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i6 |= i11;
                }
                i12 = i6;
                if ((i12 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i12 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i16 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i2 & 2) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i12 &= -897;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j4;
                        }
                        if (i4 != 0) {
                            fM1272getPlainTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1272getPlainTooltipMaxWidthD9Ej5fM();
                        } else {
                            fM1272getPlainTooltipMaxWidthD9Ej5fM = f4;
                        }
                        if ((i2 & 8) != 0) {
                            plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                            i12 &= -57345;
                        } else {
                            plainTooltipContainerShape = shape2;
                        }
                        if ((i2 & 16) != 0) {
                            plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                            i12 &= -458753;
                        } else {
                            plainTooltipContentColor = j5;
                        }
                        if ((i2 & 32) != 0) {
                            plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                            i12 &= -3670017;
                        } else {
                            plainTooltipContainerColor = j3;
                        }
                        if (i7 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f5;
                        }
                        if (i9 != 0) {
                            f5 = fM6022constructorimpl;
                            i13 = i12;
                            fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                            j9 = jM6129getUnspecifiedMYxV2XQ;
                            shape4 = plainTooltipContainerShape;
                            j10 = plainTooltipContainerColor;
                        } else {
                            f5 = fM6022constructorimpl;
                            j9 = jM6129getUnspecifiedMYxV2XQ;
                            shape4 = plainTooltipContainerShape;
                            j10 = plainTooltipContainerColor;
                            i13 = i12;
                            fM6022constructorimpl2 = f3;
                        }
                    } else {
                        if (i16 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i2 & 2) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i12 &= -897;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j4;
                        }
                        if (i4 != 0) {
                            fM1272getPlainTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1272getPlainTooltipMaxWidthD9Ej5fM();
                        } else {
                            fM1272getPlainTooltipMaxWidthD9Ej5fM = f4;
                        }
                        if ((i2 & 8) != 0) {
                            plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                            i12 &= -57345;
                        } else {
                            plainTooltipContainerShape = shape2;
                        }
                        if ((i2 & 16) != 0) {
                            plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                            i12 &= -458753;
                        } else {
                            plainTooltipContentColor = j5;
                        }
                        if ((i2 & 32) != 0) {
                            plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                            i12 &= -3670017;
                        } else {
                            plainTooltipContainerColor = j3;
                        }
                        if (i7 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f5;
                        }
                        if (i9 != 0) {
                            f5 = fM6022constructorimpl;
                            i13 = i12;
                            fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                            j9 = jM6129getUnspecifiedMYxV2XQ;
                            shape4 = plainTooltipContainerShape;
                            j10 = plainTooltipContainerColor;
                        } else {
                            f5 = fM6022constructorimpl;
                            j9 = jM6129getUnspecifiedMYxV2XQ;
                            shape4 = plainTooltipContainerShape;
                            j10 = plainTooltipContainerColor;
                            i13 = i12;
                            fM6022constructorimpl2 = f3;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1456881596, i13, -1, "androidx.compose.material3.PlainTooltipAndroid (Tooltip.android.kt:104)");
                    }
                    int i23 = 2147482750 & i13;
                    long j17 = j9;
                    float f17 = fM1272getPlainTooltipMaxWidthD9Ej5fM;
                    long j18 = plainTooltipContentColor;
                    float f18 = f5;
                    float f19 = fM6022constructorimpl2;
                    TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, modifier4, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j9), f17, shape4, j18, j10, f18, f19, function2, composerStartRestartGroup, i23, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    f5 = f18;
                    f7 = f19;
                    j8 = j10;
                    j7 = j18;
                    f6 = f17;
                    shape3 = shape4;
                    j6 = j17;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    j6 = j4;
                    f6 = f4;
                    shape3 = shape2;
                    j7 = j5;
                    j8 = j3;
                    f7 = f3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jhe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.d(tooltipScope, modifier3, j6, f6, shape3, j7, j8, f5, f7, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i6 |= 100663296;
            i11 = 805306368;
            if ((i2 & 256) == 0) {
                i6 |= i11;
            } else if ((i & 805306368) != 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i11 = 536870912;
                } else {
                    i11 = 268435456;
                }
                i6 |= i11;
            }
            i12 = i6;
            if ((i12 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i12 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i16 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i12 &= -897;
                    } else {
                        jM6129getUnspecifiedMYxV2XQ = j4;
                    }
                    if (i4 != 0) {
                        fM1272getPlainTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1272getPlainTooltipMaxWidthD9Ej5fM();
                    } else {
                        fM1272getPlainTooltipMaxWidthD9Ej5fM = f4;
                    }
                    if ((i2 & 8) != 0) {
                        plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                        i12 &= -57345;
                    } else {
                        plainTooltipContainerShape = shape2;
                    }
                    if ((i2 & 16) != 0) {
                        plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                        i12 &= -458753;
                    } else {
                        plainTooltipContentColor = j5;
                    }
                    if ((i2 & 32) != 0) {
                        plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                        i12 &= -3670017;
                    } else {
                        plainTooltipContainerColor = j3;
                    }
                    if (i7 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f5;
                    }
                    if (i9 != 0) {
                        f5 = fM6022constructorimpl;
                        i13 = i12;
                        fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                        j9 = jM6129getUnspecifiedMYxV2XQ;
                        shape4 = plainTooltipContainerShape;
                        j10 = plainTooltipContainerColor;
                    } else {
                        f5 = fM6022constructorimpl;
                        j9 = jM6129getUnspecifiedMYxV2XQ;
                        shape4 = plainTooltipContainerShape;
                        j10 = plainTooltipContainerColor;
                        i13 = i12;
                        fM6022constructorimpl2 = f3;
                    }
                } else {
                    if (i16 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i12 &= -897;
                    } else {
                        jM6129getUnspecifiedMYxV2XQ = j4;
                    }
                    if (i4 != 0) {
                        fM1272getPlainTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1272getPlainTooltipMaxWidthD9Ej5fM();
                    } else {
                        fM1272getPlainTooltipMaxWidthD9Ej5fM = f4;
                    }
                    if ((i2 & 8) != 0) {
                        plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                        i12 &= -57345;
                    } else {
                        plainTooltipContainerShape = shape2;
                    }
                    if ((i2 & 16) != 0) {
                        plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                        i12 &= -458753;
                    } else {
                        plainTooltipContentColor = j5;
                    }
                    if ((i2 & 32) != 0) {
                        plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                        i12 &= -3670017;
                    } else {
                        plainTooltipContainerColor = j3;
                    }
                    if (i7 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f5;
                    }
                    if (i9 != 0) {
                        f5 = fM6022constructorimpl;
                        i13 = i12;
                        fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                        j9 = jM6129getUnspecifiedMYxV2XQ;
                        shape4 = plainTooltipContainerShape;
                        j10 = plainTooltipContainerColor;
                    } else {
                        f5 = fM6022constructorimpl;
                        j9 = jM6129getUnspecifiedMYxV2XQ;
                        shape4 = plainTooltipContainerShape;
                        j10 = plainTooltipContainerColor;
                        i13 = i12;
                        fM6022constructorimpl2 = f3;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1456881596, i13, -1, "androidx.compose.material3.PlainTooltipAndroid (Tooltip.android.kt:104)");
                }
                int i24 = 2147482750 & i13;
                long j19 = j9;
                float f110 = fM1272getPlainTooltipMaxWidthD9Ej5fM;
                long j110 = plainTooltipContentColor;
                float f111 = f5;
                float f112 = fM6022constructorimpl2;
                TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, modifier4, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j9), f110, shape4, j110, j10, f111, f112, function2, composerStartRestartGroup, i24, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                f5 = f111;
                f7 = f112;
                j8 = j10;
                j7 = j110;
                f6 = f110;
                shape3 = shape4;
                j6 = j19;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                j6 = j4;
                f6 = f4;
                shape3 = shape2;
                j7 = j5;
                j8 = j3;
                f7 = f3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jhe
                    public final Object invoke(Object obj, Object obj2) {
                        return Tooltip_androidKt.d(tooltipScope, modifier3, j6, f6, shape3, j7, j8, f5, f7, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i2 & 2) == 0) {
                j4 = j;
                if (composerStartRestartGroup.changed(j4)) {
                }
                i3 |= i17;
            } else {
                j4 = j;
            }
            i3 |= i17;
        } else {
            j4 = j;
        }
        i4 = i2 & 4;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                f4 = f;
                if (composerStartRestartGroup.changed(f4)) {
                    i5 = 2048;
                } else {
                    i5 = 1024;
                }
                i3 |= i5;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 8) == 0) {
                    shape2 = shape;
                    if (composerStartRestartGroup.changed(shape2)) {
                    }
                    i3 |= i18;
                } else {
                    shape2 = shape;
                }
                i3 |= i18;
            } else {
                shape2 = shape;
            }
            if ((196608 & i) == 0) {
                j5 = j2;
                if ((i2 & 16) == 0) {
                    i15 = 65536;
                } else {
                    i15 = 65536;
                }
                i3 |= i15;
            } else {
                j5 = j2;
            }
            if ((1572864 & i) == 0) {
                int i111 = i3;
                if ((i2 & 32) == 0) {
                    i14 = 524288;
                } else {
                    i14 = 524288;
                }
                i6 = i111 | i14;
            } else {
                i6 = i3;
            }
            i7 = i2 & 64;
            if (i7 != 0) {
                i6 |= 12582912;
                f5 = f2;
            } else {
                f5 = f2;
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(f5)) {
                        i8 = 8388608;
                    } else {
                        i8 = 4194304;
                    }
                    i6 |= i8;
                }
            }
            i9 = i2 & 128;
            if (i9 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i10 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i10 = 33554432;
                    }
                    i6 |= i10;
                }
                i11 = 805306368;
                if ((i2 & 256) == 0) {
                    i6 |= i11;
                } else if ((i & 805306368) != 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i11 = 536870912;
                    } else {
                        i11 = 268435456;
                    }
                    i6 |= i11;
                }
                i12 = i6;
                if ((i12 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i12 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i16 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i2 & 2) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i12 &= -897;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j4;
                        }
                        if (i4 != 0) {
                            fM1272getPlainTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1272getPlainTooltipMaxWidthD9Ej5fM();
                        } else {
                            fM1272getPlainTooltipMaxWidthD9Ej5fM = f4;
                        }
                        if ((i2 & 8) != 0) {
                            plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                            i12 &= -57345;
                        } else {
                            plainTooltipContainerShape = shape2;
                        }
                        if ((i2 & 16) != 0) {
                            plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                            i12 &= -458753;
                        } else {
                            plainTooltipContentColor = j5;
                        }
                        if ((i2 & 32) != 0) {
                            plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                            i12 &= -3670017;
                        } else {
                            plainTooltipContainerColor = j3;
                        }
                        if (i7 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f5;
                        }
                        if (i9 != 0) {
                            f5 = fM6022constructorimpl;
                            i13 = i12;
                            fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                            j9 = jM6129getUnspecifiedMYxV2XQ;
                            shape4 = plainTooltipContainerShape;
                            j10 = plainTooltipContainerColor;
                        } else {
                            f5 = fM6022constructorimpl;
                            j9 = jM6129getUnspecifiedMYxV2XQ;
                            shape4 = plainTooltipContainerShape;
                            j10 = plainTooltipContainerColor;
                            i13 = i12;
                            fM6022constructorimpl2 = f3;
                        }
                    } else {
                        if (i16 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if ((i2 & 2) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i12 &= -897;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j4;
                        }
                        if (i4 != 0) {
                            fM1272getPlainTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1272getPlainTooltipMaxWidthD9Ej5fM();
                        } else {
                            fM1272getPlainTooltipMaxWidthD9Ej5fM = f4;
                        }
                        if ((i2 & 8) != 0) {
                            plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                            i12 &= -57345;
                        } else {
                            plainTooltipContainerShape = shape2;
                        }
                        if ((i2 & 16) != 0) {
                            plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                            i12 &= -458753;
                        } else {
                            plainTooltipContentColor = j5;
                        }
                        if ((i2 & 32) != 0) {
                            plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                            i12 &= -3670017;
                        } else {
                            plainTooltipContainerColor = j3;
                        }
                        if (i7 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f5;
                        }
                        if (i9 != 0) {
                            f5 = fM6022constructorimpl;
                            i13 = i12;
                            fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                            j9 = jM6129getUnspecifiedMYxV2XQ;
                            shape4 = plainTooltipContainerShape;
                            j10 = plainTooltipContainerColor;
                        } else {
                            f5 = fM6022constructorimpl;
                            j9 = jM6129getUnspecifiedMYxV2XQ;
                            shape4 = plainTooltipContainerShape;
                            j10 = plainTooltipContainerColor;
                            i13 = i12;
                            fM6022constructorimpl2 = f3;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1456881596, i13, -1, "androidx.compose.material3.PlainTooltipAndroid (Tooltip.android.kt:104)");
                    }
                    int i25 = 2147482750 & i13;
                    long j111 = j9;
                    float f113 = fM1272getPlainTooltipMaxWidthD9Ej5fM;
                    long j112 = plainTooltipContentColor;
                    float f114 = f5;
                    float f115 = fM6022constructorimpl2;
                    TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, modifier4, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j9), f113, shape4, j112, j10, f114, f115, function2, composerStartRestartGroup, i25, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    f5 = f114;
                    f7 = f115;
                    j8 = j10;
                    j7 = j112;
                    f6 = f113;
                    shape3 = shape4;
                    j6 = j111;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    j6 = j4;
                    f6 = f4;
                    shape3 = shape2;
                    j7 = j5;
                    j8 = j3;
                    f7 = f3;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jhe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.d(tooltipScope, modifier3, j6, f6, shape3, j7, j8, f5, f7, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i6 |= 100663296;
            i11 = 805306368;
            if ((i2 & 256) == 0) {
                i6 |= i11;
            } else if ((i & 805306368) != 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i11 = 536870912;
                } else {
                    i11 = 268435456;
                }
                i6 |= i11;
            }
            i12 = i6;
            if ((i12 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i12 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i16 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i12 &= -897;
                    } else {
                        jM6129getUnspecifiedMYxV2XQ = j4;
                    }
                    if (i4 != 0) {
                        fM1272getPlainTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1272getPlainTooltipMaxWidthD9Ej5fM();
                    } else {
                        fM1272getPlainTooltipMaxWidthD9Ej5fM = f4;
                    }
                    if ((i2 & 8) != 0) {
                        plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                        i12 &= -57345;
                    } else {
                        plainTooltipContainerShape = shape2;
                    }
                    if ((i2 & 16) != 0) {
                        plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                        i12 &= -458753;
                    } else {
                        plainTooltipContentColor = j5;
                    }
                    if ((i2 & 32) != 0) {
                        plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                        i12 &= -3670017;
                    } else {
                        plainTooltipContainerColor = j3;
                    }
                    if (i7 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f5;
                    }
                    if (i9 != 0) {
                        f5 = fM6022constructorimpl;
                        i13 = i12;
                        fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                        j9 = jM6129getUnspecifiedMYxV2XQ;
                        shape4 = plainTooltipContainerShape;
                        j10 = plainTooltipContainerColor;
                    } else {
                        f5 = fM6022constructorimpl;
                        j9 = jM6129getUnspecifiedMYxV2XQ;
                        shape4 = plainTooltipContainerShape;
                        j10 = plainTooltipContainerColor;
                        i13 = i12;
                        fM6022constructorimpl2 = f3;
                    }
                } else {
                    if (i16 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i12 &= -897;
                    } else {
                        jM6129getUnspecifiedMYxV2XQ = j4;
                    }
                    if (i4 != 0) {
                        fM1272getPlainTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1272getPlainTooltipMaxWidthD9Ej5fM();
                    } else {
                        fM1272getPlainTooltipMaxWidthD9Ej5fM = f4;
                    }
                    if ((i2 & 8) != 0) {
                        plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                        i12 &= -57345;
                    } else {
                        plainTooltipContainerShape = shape2;
                    }
                    if ((i2 & 16) != 0) {
                        plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                        i12 &= -458753;
                    } else {
                        plainTooltipContentColor = j5;
                    }
                    if ((i2 & 32) != 0) {
                        plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                        i12 &= -3670017;
                    } else {
                        plainTooltipContainerColor = j3;
                    }
                    if (i7 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f5;
                    }
                    if (i9 != 0) {
                        f5 = fM6022constructorimpl;
                        i13 = i12;
                        fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                        j9 = jM6129getUnspecifiedMYxV2XQ;
                        shape4 = plainTooltipContainerShape;
                        j10 = plainTooltipContainerColor;
                    } else {
                        f5 = fM6022constructorimpl;
                        j9 = jM6129getUnspecifiedMYxV2XQ;
                        shape4 = plainTooltipContainerShape;
                        j10 = plainTooltipContainerColor;
                        i13 = i12;
                        fM6022constructorimpl2 = f3;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1456881596, i13, -1, "androidx.compose.material3.PlainTooltipAndroid (Tooltip.android.kt:104)");
                }
                int i26 = 2147482750 & i13;
                long j113 = j9;
                float f116 = fM1272getPlainTooltipMaxWidthD9Ej5fM;
                long j114 = plainTooltipContentColor;
                float f117 = f5;
                float f118 = fM6022constructorimpl2;
                TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, modifier4, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j9), f116, shape4, j114, j10, f117, f118, function2, composerStartRestartGroup, i26, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                f5 = f117;
                f7 = f118;
                j8 = j10;
                j7 = j114;
                f6 = f116;
                shape3 = shape4;
                j6 = j113;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                j6 = j4;
                f6 = f4;
                shape3 = shape2;
                j7 = j5;
                j8 = j3;
                f7 = f3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jhe
                    public final Object invoke(Object obj, Object obj2) {
                        return Tooltip_androidKt.d(tooltipScope, modifier3, j6, f6, shape3, j7, j8, f5, f7, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        f4 = f;
        if ((i & 24576) == 0) {
            if ((i2 & 8) == 0) {
                shape2 = shape;
                if (composerStartRestartGroup.changed(shape2)) {
                }
                i3 |= i18;
            } else {
                shape2 = shape;
            }
            i3 |= i18;
        } else {
            shape2 = shape;
        }
        if ((196608 & i) == 0) {
            j5 = j2;
            if ((i2 & 16) == 0) {
                i15 = 65536;
            } else {
                i15 = 65536;
            }
            i3 |= i15;
        } else {
            j5 = j2;
        }
        if ((1572864 & i) == 0) {
            int i112 = i3;
            if ((i2 & 32) == 0) {
                i14 = 524288;
            } else {
                i14 = 524288;
            }
            i6 = i112 | i14;
        } else {
            i6 = i3;
        }
        i7 = i2 & 64;
        if (i7 != 0) {
            i6 |= 12582912;
            f5 = f2;
        } else {
            f5 = f2;
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(f5)) {
                    i8 = 8388608;
                } else {
                    i8 = 4194304;
                }
                i6 |= i8;
            }
        }
        i9 = i2 & 128;
        if (i9 != 0) {
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changed(f3)) {
                    i10 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i10 = 33554432;
                }
                i6 |= i10;
            }
            i11 = 805306368;
            if ((i2 & 256) == 0) {
                i6 |= i11;
            } else if ((i & 805306368) != 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i11 = 536870912;
                } else {
                    i11 = 268435456;
                }
                i6 |= i11;
            }
            i12 = i6;
            if ((i12 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i12 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i16 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i12 &= -897;
                    } else {
                        jM6129getUnspecifiedMYxV2XQ = j4;
                    }
                    if (i4 != 0) {
                        fM1272getPlainTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1272getPlainTooltipMaxWidthD9Ej5fM();
                    } else {
                        fM1272getPlainTooltipMaxWidthD9Ej5fM = f4;
                    }
                    if ((i2 & 8) != 0) {
                        plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                        i12 &= -57345;
                    } else {
                        plainTooltipContainerShape = shape2;
                    }
                    if ((i2 & 16) != 0) {
                        plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                        i12 &= -458753;
                    } else {
                        plainTooltipContentColor = j5;
                    }
                    if ((i2 & 32) != 0) {
                        plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                        i12 &= -3670017;
                    } else {
                        plainTooltipContainerColor = j3;
                    }
                    if (i7 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f5;
                    }
                    if (i9 != 0) {
                        f5 = fM6022constructorimpl;
                        i13 = i12;
                        fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                        j9 = jM6129getUnspecifiedMYxV2XQ;
                        shape4 = plainTooltipContainerShape;
                        j10 = plainTooltipContainerColor;
                    } else {
                        f5 = fM6022constructorimpl;
                        j9 = jM6129getUnspecifiedMYxV2XQ;
                        shape4 = plainTooltipContainerShape;
                        j10 = plainTooltipContainerColor;
                        i13 = i12;
                        fM6022constructorimpl2 = f3;
                    }
                } else {
                    if (i16 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if ((i2 & 2) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i12 &= -897;
                    } else {
                        jM6129getUnspecifiedMYxV2XQ = j4;
                    }
                    if (i4 != 0) {
                        fM1272getPlainTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1272getPlainTooltipMaxWidthD9Ej5fM();
                    } else {
                        fM1272getPlainTooltipMaxWidthD9Ej5fM = f4;
                    }
                    if ((i2 & 8) != 0) {
                        plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                        i12 &= -57345;
                    } else {
                        plainTooltipContainerShape = shape2;
                    }
                    if ((i2 & 16) != 0) {
                        plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                        i12 &= -458753;
                    } else {
                        plainTooltipContentColor = j5;
                    }
                    if ((i2 & 32) != 0) {
                        plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                        i12 &= -3670017;
                    } else {
                        plainTooltipContainerColor = j3;
                    }
                    if (i7 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f5;
                    }
                    if (i9 != 0) {
                        f5 = fM6022constructorimpl;
                        i13 = i12;
                        fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                        j9 = jM6129getUnspecifiedMYxV2XQ;
                        shape4 = plainTooltipContainerShape;
                        j10 = plainTooltipContainerColor;
                    } else {
                        f5 = fM6022constructorimpl;
                        j9 = jM6129getUnspecifiedMYxV2XQ;
                        shape4 = plainTooltipContainerShape;
                        j10 = plainTooltipContainerColor;
                        i13 = i12;
                        fM6022constructorimpl2 = f3;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1456881596, i13, -1, "androidx.compose.material3.PlainTooltipAndroid (Tooltip.android.kt:104)");
                }
                int i27 = 2147482750 & i13;
                long j115 = j9;
                float f119 = fM1272getPlainTooltipMaxWidthD9Ej5fM;
                long j116 = plainTooltipContentColor;
                float f1110 = f5;
                float f1111 = fM6022constructorimpl2;
                TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, modifier4, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j9), f119, shape4, j116, j10, f1110, f1111, function2, composerStartRestartGroup, i27, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                f5 = f1110;
                f7 = f1111;
                j8 = j10;
                j7 = j116;
                f6 = f119;
                shape3 = shape4;
                j6 = j115;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                j6 = j4;
                f6 = f4;
                shape3 = shape2;
                j7 = j5;
                j8 = j3;
                f7 = f3;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jhe
                    public final Object invoke(Object obj, Object obj2) {
                        return Tooltip_androidKt.d(tooltipScope, modifier3, j6, f6, shape3, j7, j8, f5, f7, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i6 |= 100663296;
        i11 = 805306368;
        if ((i2 & 256) == 0) {
            i6 |= i11;
        } else if ((i & 805306368) != 0) {
            if (composerStartRestartGroup.changedInstance(function2)) {
                i11 = 536870912;
            } else {
                i11 = 268435456;
            }
            i6 |= i11;
        }
        i12 = i6;
        if ((i12 & 306783379) != 306783378) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i12 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i16 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 2) != 0) {
                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                    i12 &= -897;
                } else {
                    jM6129getUnspecifiedMYxV2XQ = j4;
                }
                if (i4 != 0) {
                    fM1272getPlainTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1272getPlainTooltipMaxWidthD9Ej5fM();
                } else {
                    fM1272getPlainTooltipMaxWidthD9Ej5fM = f4;
                }
                if ((i2 & 8) != 0) {
                    plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                    i12 &= -57345;
                } else {
                    plainTooltipContainerShape = shape2;
                }
                if ((i2 & 16) != 0) {
                    plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                    i12 &= -458753;
                } else {
                    plainTooltipContentColor = j5;
                }
                if ((i2 & 32) != 0) {
                    plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                    i12 &= -3670017;
                } else {
                    plainTooltipContainerColor = j3;
                }
                if (i7 != 0) {
                    fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                } else {
                    fM6022constructorimpl = f5;
                }
                if (i9 != 0) {
                    f5 = fM6022constructorimpl;
                    i13 = i12;
                    fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                    j9 = jM6129getUnspecifiedMYxV2XQ;
                    shape4 = plainTooltipContainerShape;
                    j10 = plainTooltipContainerColor;
                } else {
                    f5 = fM6022constructorimpl;
                    j9 = jM6129getUnspecifiedMYxV2XQ;
                    shape4 = plainTooltipContainerShape;
                    j10 = plainTooltipContainerColor;
                    i13 = i12;
                    fM6022constructorimpl2 = f3;
                }
            } else {
                if (i16 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i2 & 2) != 0) {
                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                    i12 &= -897;
                } else {
                    jM6129getUnspecifiedMYxV2XQ = j4;
                }
                if (i4 != 0) {
                    fM1272getPlainTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1272getPlainTooltipMaxWidthD9Ej5fM();
                } else {
                    fM1272getPlainTooltipMaxWidthD9Ej5fM = f4;
                }
                if ((i2 & 8) != 0) {
                    plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                    i12 &= -57345;
                } else {
                    plainTooltipContainerShape = shape2;
                }
                if ((i2 & 16) != 0) {
                    plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                    i12 &= -458753;
                } else {
                    plainTooltipContentColor = j5;
                }
                if ((i2 & 32) != 0) {
                    plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                    i12 &= -3670017;
                } else {
                    plainTooltipContainerColor = j3;
                }
                if (i7 != 0) {
                    fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                } else {
                    fM6022constructorimpl = f5;
                }
                if (i9 != 0) {
                    f5 = fM6022constructorimpl;
                    i13 = i12;
                    fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                    j9 = jM6129getUnspecifiedMYxV2XQ;
                    shape4 = plainTooltipContainerShape;
                    j10 = plainTooltipContainerColor;
                } else {
                    f5 = fM6022constructorimpl;
                    j9 = jM6129getUnspecifiedMYxV2XQ;
                    shape4 = plainTooltipContainerShape;
                    j10 = plainTooltipContainerColor;
                    i13 = i12;
                    fM6022constructorimpl2 = f3;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1456881596, i13, -1, "androidx.compose.material3.PlainTooltipAndroid (Tooltip.android.kt:104)");
            }
            int i28 = 2147482750 & i13;
            long j117 = j9;
            float f1112 = fM1272getPlainTooltipMaxWidthD9Ej5fM;
            long j118 = plainTooltipContentColor;
            float f1113 = f5;
            float f1114 = fM6022constructorimpl2;
            TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, modifier4, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j9), f1112, shape4, j118, j10, f1113, f1114, function2, composerStartRestartGroup, i28, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            f5 = f1113;
            f7 = f1114;
            j8 = j10;
            j7 = j118;
            f6 = f1112;
            shape3 = shape4;
            j6 = j117;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            j6 = j4;
            f6 = f4;
            shape3 = shape2;
            j7 = j5;
            j8 = j3;
            f7 = f3;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jhe
                public final Object invoke(Object obj, Object obj2) {
                    return Tooltip_androidKt.d(tooltipScope, modifier3, j6, f6, shape3, j7, j8, f5, f7, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:103:0x011b  */
    /* JADX WARN: Code duplicated, block: B:105:0x0122  */
    /* JADX WARN: Code duplicated, block: B:107:0x0126  */
    /* JADX WARN: Code duplicated, block: B:109:0x0130  */
    /* JADX WARN: Code duplicated, block: B:110:0x0133  */
    /* JADX WARN: Code duplicated, block: B:114:0x013b  */
    /* JADX WARN: Code duplicated, block: B:115:0x013e  */
    /* JADX WARN: Code duplicated, block: B:117:0x0142  */
    /* JADX WARN: Code duplicated, block: B:119:0x014a  */
    /* JADX WARN: Code duplicated, block: B:120:0x014d  */
    /* JADX WARN: Code duplicated, block: B:122:0x0154  */
    /* JADX WARN: Code duplicated, block: B:125:0x0162  */
    /* JADX WARN: Code duplicated, block: B:129:0x016a  */
    /* JADX WARN: Code duplicated, block: B:132:0x0173  */
    /* JADX WARN: Code duplicated, block: B:134:0x0183  */
    /* JADX WARN: Code duplicated, block: B:147:0x01ae A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:148:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:149:0x01b3  */
    /* JADX WARN: Code duplicated, block: B:152:0x01b7  */
    /* JADX WARN: Code duplicated, block: B:155:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:158:0x01c0  */
    /* JADX WARN: Code duplicated, block: B:159:0x01c9  */
    /* JADX WARN: Code duplicated, block: B:161:0x01cc  */
    /* JADX WARN: Code duplicated, block: B:164:0x01d7  */
    /* JADX WARN: Code duplicated, block: B:165:0x01e0  */
    /* JADX WARN: Code duplicated, block: B:168:0x01e6  */
    /* JADX WARN: Code duplicated, block: B:170:0x01f0  */
    /* JADX WARN: Code duplicated, block: B:171:0x01f7  */
    /* JADX WARN: Code duplicated, block: B:173:0x01fb  */
    /* JADX WARN: Code duplicated, block: B:174:0x020d  */
    /* JADX WARN: Code duplicated, block: B:177:0x0225  */
    /* JADX WARN: Code duplicated, block: B:180:0x024d  */
    /* JADX WARN: Code duplicated, block: B:182:0x025c  */
    /* JADX WARN: Code duplicated, block: B:185:0x0272  */
    /* JADX WARN: Code duplicated, block: B:187:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:29:0x004f  */
    /* JADX WARN: Code duplicated, block: B:31:0x0054  */
    /* JADX WARN: Code duplicated, block: B:33:0x0058  */
    /* JADX WARN: Code duplicated, block: B:35:0x0060  */
    /* JADX WARN: Code duplicated, block: B:36:0x0063  */
    /* JADX WARN: Code duplicated, block: B:40:0x006a  */
    /* JADX WARN: Code duplicated, block: B:42:0x006f  */
    /* JADX WARN: Code duplicated, block: B:44:0x0073  */
    /* JADX WARN: Code duplicated, block: B:46:0x007b  */
    /* JADX WARN: Code duplicated, block: B:47:0x007e  */
    /* JADX WARN: Code duplicated, block: B:51:0x0085  */
    /* JADX WARN: Code duplicated, block: B:53:0x0089  */
    /* JADX WARN: Code duplicated, block: B:55:0x0091  */
    /* JADX WARN: Code duplicated, block: B:56:0x0094  */
    /* JADX WARN: Code duplicated, block: B:59:0x009b  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a3  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:68:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:77:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:82:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:87:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:89:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:92:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:94:0x0100  */
    /* JADX WARN: Code duplicated, block: B:96:0x0104  */
    /* JADX WARN: Code duplicated, block: B:98:0x010e  */
    /* JADX WARN: Code duplicated, block: B:99:0x0111  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility.")
    public static final /* synthetic */ void RichTooltip(final TooltipScope tooltipScope, Modifier modifier, Function2 function2, Function2 function3, long j, float f, Shape shape, RichTooltipColors richTooltipColors, float f2, float f3, final Function2 function4, Composer composer, final int i, final int i2, final int i3) {
        int i4;
        Modifier modifier2;
        int i5;
        Function2 function5;
        int i6;
        int i7;
        Function2 function6;
        int i8;
        long j2;
        int i9;
        float fM1273getRichTooltipMaxWidthD9Ej5fM;
        int i10;
        RichTooltipColors richTooltipColors2;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        boolean z;
        final Shape shape2;
        final float f4;
        final Modifier modifier3;
        final Function2 function7;
        final float f5;
        final float f6;
        final RichTooltipColors richTooltipColors3;
        final Function2 function8;
        final long j3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        Function2 function9;
        long jM6129getUnspecifiedMYxV2XQ;
        Shape richTooltipContainerShape;
        float fM1742getLevel0D9Ej5fM;
        int i17;
        Function2 function10;
        float f7;
        float fM2085getContainerElevationD9Ej5fM;
        float f8;
        RichTooltipColors richTooltipColors4;
        long j4;
        Function2 function11;
        int i18;
        int i19;
        Composer composerStartRestartGroup = composer.startRestartGroup(-905938553);
        if ((Integer.MIN_VALUE & i3) != 0) {
            i4 = i | 6;
        } else if ((i & 6) == 0) {
            i4 = ((i & 8) == 0 ? composerStartRestartGroup.changed(tooltipScope) : composerStartRestartGroup.changedInstance(tooltipScope) ? 4 : 2) | i;
        } else {
            i4 = i;
        }
        int i20 = i3 & 1;
        if (i20 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i4 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i5 = i3 & 2;
            if (i5 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    function5 = function2;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i6 = 256;
                    } else {
                        i6 = 128;
                    }
                    i4 |= i6;
                }
                i7 = i3 & 4;
                if (i7 != 0) {
                    if ((i & 3072) == 0) {
                        function6 = function3;
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i8 = 2048;
                        } else {
                            i8 = 1024;
                        }
                        i4 |= i8;
                    }
                    if ((i & 24576) == 0) {
                        if ((i3 & 8) == 0) {
                            j2 = j;
                            int i21 = composerStartRestartGroup.changed(j2) ? 16384 : 8192;
                            i4 |= i21;
                        } else {
                            j2 = j;
                        }
                        i4 |= i21;
                    } else {
                        j2 = j;
                    }
                    i9 = i3 & 16;
                    if (i9 != 0) {
                        i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        fM1273getRichTooltipMaxWidthD9Ej5fM = f;
                    } else {
                        fM1273getRichTooltipMaxWidthD9Ej5fM = f;
                        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changed(fM1273getRichTooltipMaxWidthD9Ej5fM)) {
                                i10 = 131072;
                            } else {
                                i10 = 65536;
                            }
                            i4 |= i10;
                        }
                    }
                    if ((i & 1572864) != 0) {
                        if ((i3 & 32) == 0 || !composerStartRestartGroup.changed(shape)) {
                            i19 = 524288;
                        } else {
                            i19 = 1048576;
                        }
                        i4 |= i19;
                    }
                    if ((i & 12582912) == 0) {
                        richTooltipColors2 = richTooltipColors;
                        if ((i3 & 64) == 0 || !composerStartRestartGroup.changed(richTooltipColors2)) {
                            i18 = 4194304;
                        } else {
                            i18 = 8388608;
                        }
                        i4 |= i18;
                    } else {
                        richTooltipColors2 = richTooltipColors;
                    }
                    i11 = i3 & 128;
                    if (i11 != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(f2)) {
                                i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i12 = 33554432;
                            }
                            i4 |= i12;
                        }
                        i13 = i3 & 256;
                        if (i13 != 0) {
                            if ((i & 805306368) == 0) {
                                if (composerStartRestartGroup.changed(f3)) {
                                    i14 = 536870912;
                                } else {
                                    i14 = 268435456;
                                }
                                i4 |= i14;
                            }
                            if ((i3 & 512) != 0) {
                                i15 = i2 | 6;
                            } else if ((i2 & 6) == 0) {
                                if (composerStartRestartGroup.changedInstance(function4)) {
                                    i16 = 4;
                                } else {
                                    i16 = 2;
                                }
                                i15 = i2 | i16;
                            } else {
                                i15 = i2;
                            }
                            if ((i4 & 306783379) == 306783378 || (i15 & 3) != 2) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                                composerStartRestartGroup.startDefaults();
                                if ((i & 1) != 0 || composerStartRestartGroup.getDefaultsInvalid()) {
                                    if (i20 != 0) {
                                        modifier4 = Modifier.INSTANCE;
                                    } else {
                                        modifier4 = modifier2;
                                    }
                                    if (i5 != 0) {
                                        function5 = null;
                                    }
                                    function9 = i7 == 0 ? function6 : null;
                                    if ((i3 & 8) != 0) {
                                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                        i4 &= -57345;
                                    } else {
                                        jM6129getUnspecifiedMYxV2XQ = j2;
                                    }
                                    if (i9 != 0) {
                                        fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                                    }
                                    if ((i3 & 32) != 0) {
                                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                        i4 &= -3670017;
                                    } else {
                                        richTooltipContainerShape = shape;
                                    }
                                    if ((i3 & 64) != 0) {
                                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                        i4 = (-29360129) & i4;
                                    }
                                    if (i11 != 0) {
                                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                    } else {
                                        fM1742getLevel0D9Ej5fM = f2;
                                    }
                                    if (i13 != 0) {
                                        long j5 = jM6129getUnspecifiedMYxV2XQ;
                                        f8 = fM1742getLevel0D9Ej5fM;
                                        richTooltipColors4 = richTooltipColors2;
                                        function11 = function5;
                                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                        i17 = i4;
                                        function10 = function9;
                                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                        j4 = j5;
                                    } else {
                                        i17 = i4;
                                        function10 = function9;
                                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                        Function2 function12 = function5;
                                        fM2085getContainerElevationD9Ej5fM = f3;
                                        long j6 = jM6129getUnspecifiedMYxV2XQ;
                                        f8 = fM1742getLevel0D9Ej5fM;
                                        richTooltipColors4 = richTooltipColors2;
                                        j4 = j6;
                                        function11 = function12;
                                    }
                                } else {
                                    composerStartRestartGroup.skipToGroupEnd();
                                    if ((i3 & 8) != 0) {
                                        i4 &= -57345;
                                    }
                                    if ((i3 & 32) != 0) {
                                        i4 &= -3670017;
                                    }
                                    if ((i3 & 64) != 0) {
                                        i4 &= -29360129;
                                    }
                                    Function2 function13 = function6;
                                    i17 = i4;
                                    function10 = function13;
                                    richTooltipContainerShape = shape;
                                    f8 = f2;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    modifier4 = modifier2;
                                    function11 = function5;
                                    fM2085getContainerElevationD9Ej5fM = f3;
                                    richTooltipColors4 = richTooltipColors2;
                                    j4 = j2;
                                }
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                                }
                                long j7 = j4;
                                float f9 = f7;
                                Shape shape3 = richTooltipContainerShape;
                                TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f9, shape3, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                function8 = function10;
                                f4 = f8;
                                function7 = function11;
                                shape2 = shape3;
                                f5 = fM2085getContainerElevationD9Ej5fM;
                                modifier3 = modifier4;
                                richTooltipColors3 = richTooltipColors4;
                                f6 = f9;
                                j3 = j7;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                shape2 = shape;
                                f4 = f2;
                                modifier3 = modifier2;
                                function7 = function5;
                                f5 = f3;
                                f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                richTooltipColors3 = richTooltipColors2;
                                function8 = function6;
                                j3 = j2;
                            }
                            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                                    public final Object invoke(Object obj, Object obj2) {
                                        return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i4 |= 805306368;
                        if ((i3 & 512) != 0) {
                            i15 = i2 | 6;
                        } else if ((i2 & 6) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i16 = 4;
                            } else {
                                i16 = 2;
                            }
                            i15 = i2 | i16;
                        } else {
                            i15 = i2;
                        }
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i20 != 0) {
                                    modifier4 = Modifier.INSTANCE;
                                } else {
                                    modifier4 = modifier2;
                                }
                                if (i5 != 0) {
                                    function5 = null;
                                }
                                if (i7 == 0) {
                                }
                                if ((i3 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i4 &= -57345;
                                } else {
                                    jM6129getUnspecifiedMYxV2XQ = j2;
                                }
                                if (i9 != 0) {
                                    fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                                }
                                if ((i3 & 32) != 0) {
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                    i4 &= -3670017;
                                } else {
                                    richTooltipContainerShape = shape;
                                }
                                if ((i3 & 64) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i4 = (-29360129) & i4;
                                }
                                if (i11 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                } else {
                                    fM1742getLevel0D9Ej5fM = f2;
                                }
                                if (i13 != 0) {
                                    long j8 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    function11 = function5;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    j4 = j8;
                                } else {
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    Function2 function14 = function5;
                                    fM2085getContainerElevationD9Ej5fM = f3;
                                    long j9 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    j4 = j9;
                                    function11 = function14;
                                }
                            } else {
                                if (i20 != 0) {
                                    modifier4 = Modifier.INSTANCE;
                                } else {
                                    modifier4 = modifier2;
                                }
                                if (i5 != 0) {
                                    function5 = null;
                                }
                                if (i7 == 0) {
                                }
                                if ((i3 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i4 &= -57345;
                                } else {
                                    jM6129getUnspecifiedMYxV2XQ = j2;
                                }
                                if (i9 != 0) {
                                    fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                                }
                                if ((i3 & 32) != 0) {
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                    i4 &= -3670017;
                                } else {
                                    richTooltipContainerShape = shape;
                                }
                                if ((i3 & 64) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i4 = (-29360129) & i4;
                                }
                                if (i11 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                } else {
                                    fM1742getLevel0D9Ej5fM = f2;
                                }
                                if (i13 != 0) {
                                    long j10 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    function11 = function5;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    j4 = j10;
                                } else {
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    Function2 function15 = function5;
                                    fM2085getContainerElevationD9Ej5fM = f3;
                                    long j11 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    j4 = j11;
                                    function11 = function15;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                            }
                            long j12 = j4;
                            float f10 = f7;
                            Shape shape4 = richTooltipContainerShape;
                            TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f10, shape4, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function8 = function10;
                            f4 = f8;
                            function7 = function11;
                            shape2 = shape4;
                            f5 = fM2085getContainerElevationD9Ej5fM;
                            modifier3 = modifier4;
                            richTooltipColors3 = richTooltipColors4;
                            f6 = f10;
                            j3 = j12;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            shape2 = shape;
                            f4 = f2;
                            modifier3 = modifier2;
                            function7 = function5;
                            f5 = f3;
                            f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            richTooltipColors3 = richTooltipColors2;
                            function8 = function6;
                            j3 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                                public final Object invoke(Object obj, Object obj2) {
                                    return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i4 |= 100663296;
                    i13 = i3 & 256;
                    if (i13 != 0) {
                        if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changed(f3)) {
                                i14 = 536870912;
                            } else {
                                i14 = 268435456;
                            }
                            i4 |= i14;
                        }
                        if ((i3 & 512) != 0) {
                            i15 = i2 | 6;
                        } else if ((i2 & 6) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i16 = 4;
                            } else {
                                i16 = 2;
                            }
                            i15 = i2 | i16;
                        } else {
                            i15 = i2;
                        }
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i20 != 0) {
                                    modifier4 = Modifier.INSTANCE;
                                } else {
                                    modifier4 = modifier2;
                                }
                                if (i5 != 0) {
                                    function5 = null;
                                }
                                if (i7 == 0) {
                                }
                                if ((i3 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i4 &= -57345;
                                } else {
                                    jM6129getUnspecifiedMYxV2XQ = j2;
                                }
                                if (i9 != 0) {
                                    fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                                }
                                if ((i3 & 32) != 0) {
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                    i4 &= -3670017;
                                } else {
                                    richTooltipContainerShape = shape;
                                }
                                if ((i3 & 64) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i4 = (-29360129) & i4;
                                }
                                if (i11 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                } else {
                                    fM1742getLevel0D9Ej5fM = f2;
                                }
                                if (i13 != 0) {
                                    long j13 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    function11 = function5;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    j4 = j13;
                                } else {
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    Function2 function16 = function5;
                                    fM2085getContainerElevationD9Ej5fM = f3;
                                    long j14 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    j4 = j14;
                                    function11 = function16;
                                }
                            } else {
                                if (i20 != 0) {
                                    modifier4 = Modifier.INSTANCE;
                                } else {
                                    modifier4 = modifier2;
                                }
                                if (i5 != 0) {
                                    function5 = null;
                                }
                                if (i7 == 0) {
                                }
                                if ((i3 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i4 &= -57345;
                                } else {
                                    jM6129getUnspecifiedMYxV2XQ = j2;
                                }
                                if (i9 != 0) {
                                    fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                                }
                                if ((i3 & 32) != 0) {
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                    i4 &= -3670017;
                                } else {
                                    richTooltipContainerShape = shape;
                                }
                                if ((i3 & 64) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i4 = (-29360129) & i4;
                                }
                                if (i11 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                } else {
                                    fM1742getLevel0D9Ej5fM = f2;
                                }
                                if (i13 != 0) {
                                    long j15 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    function11 = function5;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    j4 = j15;
                                } else {
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    Function2 function17 = function5;
                                    fM2085getContainerElevationD9Ej5fM = f3;
                                    long j16 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    j4 = j16;
                                    function11 = function17;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                            }
                            long j17 = j4;
                            float f11 = f7;
                            Shape shape5 = richTooltipContainerShape;
                            TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f11, shape5, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function8 = function10;
                            f4 = f8;
                            function7 = function11;
                            shape2 = shape5;
                            f5 = fM2085getContainerElevationD9Ej5fM;
                            modifier3 = modifier4;
                            richTooltipColors3 = richTooltipColors4;
                            f6 = f11;
                            j3 = j17;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            shape2 = shape;
                            f4 = f2;
                            modifier3 = modifier2;
                            function7 = function5;
                            f5 = f3;
                            f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            richTooltipColors3 = richTooltipColors2;
                            function8 = function6;
                            j3 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                                public final Object invoke(Object obj, Object obj2) {
                                    return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i4 |= 805306368;
                    if ((i3 & 512) != 0) {
                        i15 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i16 = 4;
                        } else {
                            i16 = 2;
                        }
                        i15 = i2 | i16;
                    } else {
                        i15 = i2;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j18 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j18;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function18 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j19 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j19;
                                function11 = function18;
                            }
                        } else {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j110 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j110;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function19 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j111 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j111;
                                function11 = function19;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                        }
                        long j112 = j4;
                        float f12 = f7;
                        Shape shape6 = richTooltipContainerShape;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f12, shape6, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function10;
                        f4 = f8;
                        function7 = function11;
                        shape2 = shape6;
                        f5 = fM2085getContainerElevationD9Ej5fM;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        f6 = f12;
                        j3 = j112;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        shape2 = shape;
                        f4 = f2;
                        modifier3 = modifier2;
                        function7 = function5;
                        f5 = f3;
                        f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        richTooltipColors3 = richTooltipColors2;
                        function8 = function6;
                        j3 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 3072;
                function6 = function3;
                if ((i & 24576) == 0) {
                    if ((i3 & 8) == 0) {
                        j2 = j;
                        if (composerStartRestartGroup.changed(j2)) {
                        }
                        i4 |= i21;
                    } else {
                        j2 = j;
                    }
                    i4 |= i21;
                } else {
                    j2 = j;
                }
                i9 = i3 & 16;
                if (i9 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    fM1273getRichTooltipMaxWidthD9Ej5fM = f;
                } else {
                    fM1273getRichTooltipMaxWidthD9Ej5fM = f;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(fM1273getRichTooltipMaxWidthD9Ej5fM)) {
                            i10 = 131072;
                        } else {
                            i10 = 65536;
                        }
                        i4 |= i10;
                    }
                }
                if ((i & 1572864) != 0) {
                    if ((i3 & 32) == 0) {
                        i19 = 524288;
                    } else {
                        i19 = 524288;
                    }
                    i4 |= i19;
                }
                if ((i & 12582912) == 0) {
                    richTooltipColors2 = richTooltipColors;
                    if ((i3 & 64) == 0) {
                        i18 = 4194304;
                    } else {
                        i18 = 4194304;
                    }
                    i4 |= i18;
                } else {
                    richTooltipColors2 = richTooltipColors;
                }
                i11 = i3 & 128;
                if (i11 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(f2)) {
                            i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i12 = 33554432;
                        }
                        i4 |= i12;
                    }
                    i13 = i3 & 256;
                    if (i13 != 0) {
                        if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changed(f3)) {
                                i14 = 536870912;
                            } else {
                                i14 = 268435456;
                            }
                            i4 |= i14;
                        }
                        if ((i3 & 512) != 0) {
                            i15 = i2 | 6;
                        } else if ((i2 & 6) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i16 = 4;
                            } else {
                                i16 = 2;
                            }
                            i15 = i2 | i16;
                        } else {
                            i15 = i2;
                        }
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i20 != 0) {
                                    modifier4 = Modifier.INSTANCE;
                                } else {
                                    modifier4 = modifier2;
                                }
                                if (i5 != 0) {
                                    function5 = null;
                                }
                                if (i7 == 0) {
                                }
                                if ((i3 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i4 &= -57345;
                                } else {
                                    jM6129getUnspecifiedMYxV2XQ = j2;
                                }
                                if (i9 != 0) {
                                    fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                                }
                                if ((i3 & 32) != 0) {
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                    i4 &= -3670017;
                                } else {
                                    richTooltipContainerShape = shape;
                                }
                                if ((i3 & 64) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i4 = (-29360129) & i4;
                                }
                                if (i11 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                } else {
                                    fM1742getLevel0D9Ej5fM = f2;
                                }
                                if (i13 != 0) {
                                    long j113 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    function11 = function5;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    j4 = j113;
                                } else {
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    Function2 function110 = function5;
                                    fM2085getContainerElevationD9Ej5fM = f3;
                                    long j114 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    j4 = j114;
                                    function11 = function110;
                                }
                            } else {
                                if (i20 != 0) {
                                    modifier4 = Modifier.INSTANCE;
                                } else {
                                    modifier4 = modifier2;
                                }
                                if (i5 != 0) {
                                    function5 = null;
                                }
                                if (i7 == 0) {
                                }
                                if ((i3 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i4 &= -57345;
                                } else {
                                    jM6129getUnspecifiedMYxV2XQ = j2;
                                }
                                if (i9 != 0) {
                                    fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                                }
                                if ((i3 & 32) != 0) {
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                    i4 &= -3670017;
                                } else {
                                    richTooltipContainerShape = shape;
                                }
                                if ((i3 & 64) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i4 = (-29360129) & i4;
                                }
                                if (i11 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                } else {
                                    fM1742getLevel0D9Ej5fM = f2;
                                }
                                if (i13 != 0) {
                                    long j115 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    function11 = function5;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    j4 = j115;
                                } else {
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    Function2 function111 = function5;
                                    fM2085getContainerElevationD9Ej5fM = f3;
                                    long j116 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    j4 = j116;
                                    function11 = function111;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                            }
                            long j117 = j4;
                            float f13 = f7;
                            Shape shape7 = richTooltipContainerShape;
                            TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f13, shape7, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function8 = function10;
                            f4 = f8;
                            function7 = function11;
                            shape2 = shape7;
                            f5 = fM2085getContainerElevationD9Ej5fM;
                            modifier3 = modifier4;
                            richTooltipColors3 = richTooltipColors4;
                            f6 = f13;
                            j3 = j117;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            shape2 = shape;
                            f4 = f2;
                            modifier3 = modifier2;
                            function7 = function5;
                            f5 = f3;
                            f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            richTooltipColors3 = richTooltipColors2;
                            function8 = function6;
                            j3 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                                public final Object invoke(Object obj, Object obj2) {
                                    return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i4 |= 805306368;
                    if ((i3 & 512) != 0) {
                        i15 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i16 = 4;
                        } else {
                            i16 = 2;
                        }
                        i15 = i2 | i16;
                    } else {
                        i15 = i2;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j118 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j118;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function112 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j119 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j119;
                                function11 = function112;
                            }
                        } else {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j1110 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j1110;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function113 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j1111 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j1111;
                                function11 = function113;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                        }
                        long j1112 = j4;
                        float f14 = f7;
                        Shape shape8 = richTooltipContainerShape;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f14, shape8, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function10;
                        f4 = f8;
                        function7 = function11;
                        shape2 = shape8;
                        f5 = fM2085getContainerElevationD9Ej5fM;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        f6 = f14;
                        j3 = j1112;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        shape2 = shape;
                        f4 = f2;
                        modifier3 = modifier2;
                        function7 = function5;
                        f5 = f3;
                        f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        richTooltipColors3 = richTooltipColors2;
                        function8 = function6;
                        j3 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 100663296;
                i13 = i3 & 256;
                if (i13 != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i14 = 536870912;
                        } else {
                            i14 = 268435456;
                        }
                        i4 |= i14;
                    }
                    if ((i3 & 512) != 0) {
                        i15 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i16 = 4;
                        } else {
                            i16 = 2;
                        }
                        i15 = i2 | i16;
                    } else {
                        i15 = i2;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j1113 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j1113;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function114 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j1114 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j1114;
                                function11 = function114;
                            }
                        } else {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j1115 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j1115;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function115 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j1116 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j1116;
                                function11 = function115;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                        }
                        long j1117 = j4;
                        float f15 = f7;
                        Shape shape9 = richTooltipContainerShape;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f15, shape9, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function10;
                        f4 = f8;
                        function7 = function11;
                        shape2 = shape9;
                        f5 = fM2085getContainerElevationD9Ej5fM;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        f6 = f15;
                        j3 = j1117;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        shape2 = shape;
                        f4 = f2;
                        modifier3 = modifier2;
                        function7 = function5;
                        f5 = f3;
                        f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        richTooltipColors3 = richTooltipColors2;
                        function8 = function6;
                        j3 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                if ((i3 & 512) != 0) {
                    i15 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i16 = 4;
                    } else {
                        i16 = 2;
                    }
                    i15 = i2 | i16;
                } else {
                    i15 = i2;
                }
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j1118 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j1118;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function116 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j1119 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j1119;
                            function11 = function116;
                        }
                    } else {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j11110 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j11110;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function117 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j11111 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j11111;
                            function11 = function117;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                    }
                    long j11112 = j4;
                    float f16 = f7;
                    Shape shape10 = richTooltipContainerShape;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f16, shape10, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function10;
                    f4 = f8;
                    function7 = function11;
                    shape2 = shape10;
                    f5 = fM2085getContainerElevationD9Ej5fM;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    f6 = f16;
                    j3 = j11112;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    shape2 = shape;
                    f4 = f2;
                    modifier3 = modifier2;
                    function7 = function5;
                    f5 = f3;
                    f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                    richTooltipColors3 = richTooltipColors2;
                    function8 = function6;
                    j3 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            function5 = function2;
            i7 = i3 & 4;
            if (i7 != 0) {
                if ((i & 3072) == 0) {
                    function6 = function3;
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i8 = 2048;
                    } else {
                        i8 = 1024;
                    }
                    i4 |= i8;
                }
                if ((i & 24576) == 0) {
                    if ((i3 & 8) == 0) {
                        j2 = j;
                        if (composerStartRestartGroup.changed(j2)) {
                        }
                        i4 |= i21;
                    } else {
                        j2 = j;
                    }
                    i4 |= i21;
                } else {
                    j2 = j;
                }
                i9 = i3 & 16;
                if (i9 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    fM1273getRichTooltipMaxWidthD9Ej5fM = f;
                } else {
                    fM1273getRichTooltipMaxWidthD9Ej5fM = f;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(fM1273getRichTooltipMaxWidthD9Ej5fM)) {
                            i10 = 131072;
                        } else {
                            i10 = 65536;
                        }
                        i4 |= i10;
                    }
                }
                if ((i & 1572864) != 0) {
                    if ((i3 & 32) == 0) {
                        i19 = 524288;
                    } else {
                        i19 = 524288;
                    }
                    i4 |= i19;
                }
                if ((i & 12582912) == 0) {
                    richTooltipColors2 = richTooltipColors;
                    if ((i3 & 64) == 0) {
                        i18 = 4194304;
                    } else {
                        i18 = 4194304;
                    }
                    i4 |= i18;
                } else {
                    richTooltipColors2 = richTooltipColors;
                }
                i11 = i3 & 128;
                if (i11 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(f2)) {
                            i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i12 = 33554432;
                        }
                        i4 |= i12;
                    }
                    i13 = i3 & 256;
                    if (i13 != 0) {
                        if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changed(f3)) {
                                i14 = 536870912;
                            } else {
                                i14 = 268435456;
                            }
                            i4 |= i14;
                        }
                        if ((i3 & 512) != 0) {
                            i15 = i2 | 6;
                        } else if ((i2 & 6) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i16 = 4;
                            } else {
                                i16 = 2;
                            }
                            i15 = i2 | i16;
                        } else {
                            i15 = i2;
                        }
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i20 != 0) {
                                    modifier4 = Modifier.INSTANCE;
                                } else {
                                    modifier4 = modifier2;
                                }
                                if (i5 != 0) {
                                    function5 = null;
                                }
                                if (i7 == 0) {
                                }
                                if ((i3 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i4 &= -57345;
                                } else {
                                    jM6129getUnspecifiedMYxV2XQ = j2;
                                }
                                if (i9 != 0) {
                                    fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                                }
                                if ((i3 & 32) != 0) {
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                    i4 &= -3670017;
                                } else {
                                    richTooltipContainerShape = shape;
                                }
                                if ((i3 & 64) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i4 = (-29360129) & i4;
                                }
                                if (i11 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                } else {
                                    fM1742getLevel0D9Ej5fM = f2;
                                }
                                if (i13 != 0) {
                                    long j11113 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    function11 = function5;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    j4 = j11113;
                                } else {
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    Function2 function118 = function5;
                                    fM2085getContainerElevationD9Ej5fM = f3;
                                    long j11114 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    j4 = j11114;
                                    function11 = function118;
                                }
                            } else {
                                if (i20 != 0) {
                                    modifier4 = Modifier.INSTANCE;
                                } else {
                                    modifier4 = modifier2;
                                }
                                if (i5 != 0) {
                                    function5 = null;
                                }
                                if (i7 == 0) {
                                }
                                if ((i3 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i4 &= -57345;
                                } else {
                                    jM6129getUnspecifiedMYxV2XQ = j2;
                                }
                                if (i9 != 0) {
                                    fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                                }
                                if ((i3 & 32) != 0) {
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                    i4 &= -3670017;
                                } else {
                                    richTooltipContainerShape = shape;
                                }
                                if ((i3 & 64) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i4 = (-29360129) & i4;
                                }
                                if (i11 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                } else {
                                    fM1742getLevel0D9Ej5fM = f2;
                                }
                                if (i13 != 0) {
                                    long j11115 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    function11 = function5;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    j4 = j11115;
                                } else {
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    Function2 function119 = function5;
                                    fM2085getContainerElevationD9Ej5fM = f3;
                                    long j11116 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    j4 = j11116;
                                    function11 = function119;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                            }
                            long j11117 = j4;
                            float f17 = f7;
                            Shape shape11 = richTooltipContainerShape;
                            TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f17, shape11, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function8 = function10;
                            f4 = f8;
                            function7 = function11;
                            shape2 = shape11;
                            f5 = fM2085getContainerElevationD9Ej5fM;
                            modifier3 = modifier4;
                            richTooltipColors3 = richTooltipColors4;
                            f6 = f17;
                            j3 = j11117;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            shape2 = shape;
                            f4 = f2;
                            modifier3 = modifier2;
                            function7 = function5;
                            f5 = f3;
                            f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            richTooltipColors3 = richTooltipColors2;
                            function8 = function6;
                            j3 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                                public final Object invoke(Object obj, Object obj2) {
                                    return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i4 |= 805306368;
                    if ((i3 & 512) != 0) {
                        i15 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i16 = 4;
                        } else {
                            i16 = 2;
                        }
                        i15 = i2 | i16;
                    } else {
                        i15 = i2;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j11118 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j11118;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function1110 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j11119 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j11119;
                                function11 = function1110;
                            }
                        } else {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j111110 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j111110;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function1111 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j111111 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j111111;
                                function11 = function1111;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                        }
                        long j111112 = j4;
                        float f18 = f7;
                        Shape shape12 = richTooltipContainerShape;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f18, shape12, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function10;
                        f4 = f8;
                        function7 = function11;
                        shape2 = shape12;
                        f5 = fM2085getContainerElevationD9Ej5fM;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        f6 = f18;
                        j3 = j111112;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        shape2 = shape;
                        f4 = f2;
                        modifier3 = modifier2;
                        function7 = function5;
                        f5 = f3;
                        f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        richTooltipColors3 = richTooltipColors2;
                        function8 = function6;
                        j3 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 100663296;
                i13 = i3 & 256;
                if (i13 != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i14 = 536870912;
                        } else {
                            i14 = 268435456;
                        }
                        i4 |= i14;
                    }
                    if ((i3 & 512) != 0) {
                        i15 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i16 = 4;
                        } else {
                            i16 = 2;
                        }
                        i15 = i2 | i16;
                    } else {
                        i15 = i2;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j111113 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j111113;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function1112 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j111114 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j111114;
                                function11 = function1112;
                            }
                        } else {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j111115 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j111115;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function1113 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j111116 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j111116;
                                function11 = function1113;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                        }
                        long j111117 = j4;
                        float f19 = f7;
                        Shape shape13 = richTooltipContainerShape;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f19, shape13, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function10;
                        f4 = f8;
                        function7 = function11;
                        shape2 = shape13;
                        f5 = fM2085getContainerElevationD9Ej5fM;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        f6 = f19;
                        j3 = j111117;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        shape2 = shape;
                        f4 = f2;
                        modifier3 = modifier2;
                        function7 = function5;
                        f5 = f3;
                        f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        richTooltipColors3 = richTooltipColors2;
                        function8 = function6;
                        j3 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                if ((i3 & 512) != 0) {
                    i15 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i16 = 4;
                    } else {
                        i16 = 2;
                    }
                    i15 = i2 | i16;
                } else {
                    i15 = i2;
                }
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j111118 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j111118;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function1114 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j111119 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j111119;
                            function11 = function1114;
                        }
                    } else {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j1111110 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j1111110;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function1115 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j1111111 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j1111111;
                            function11 = function1115;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                    }
                    long j1111112 = j4;
                    float f110 = f7;
                    Shape shape14 = richTooltipContainerShape;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f110, shape14, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function10;
                    f4 = f8;
                    function7 = function11;
                    shape2 = shape14;
                    f5 = fM2085getContainerElevationD9Ej5fM;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    f6 = f110;
                    j3 = j1111112;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    shape2 = shape;
                    f4 = f2;
                    modifier3 = modifier2;
                    function7 = function5;
                    f5 = f3;
                    f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                    richTooltipColors3 = richTooltipColors2;
                    function8 = function6;
                    j3 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 3072;
            function6 = function3;
            if ((i & 24576) == 0) {
                if ((i3 & 8) == 0) {
                    j2 = j;
                    if (composerStartRestartGroup.changed(j2)) {
                    }
                    i4 |= i21;
                } else {
                    j2 = j;
                }
                i4 |= i21;
            } else {
                j2 = j;
            }
            i9 = i3 & 16;
            if (i9 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                fM1273getRichTooltipMaxWidthD9Ej5fM = f;
            } else {
                fM1273getRichTooltipMaxWidthD9Ej5fM = f;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(fM1273getRichTooltipMaxWidthD9Ej5fM)) {
                        i10 = 131072;
                    } else {
                        i10 = 65536;
                    }
                    i4 |= i10;
                }
            }
            if ((i & 1572864) != 0) {
                if ((i3 & 32) == 0) {
                    i19 = 524288;
                } else {
                    i19 = 524288;
                }
                i4 |= i19;
            }
            if ((i & 12582912) == 0) {
                richTooltipColors2 = richTooltipColors;
                if ((i3 & 64) == 0) {
                    i18 = 4194304;
                } else {
                    i18 = 4194304;
                }
                i4 |= i18;
            } else {
                richTooltipColors2 = richTooltipColors;
            }
            i11 = i3 & 128;
            if (i11 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f2)) {
                        i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i12 = 33554432;
                    }
                    i4 |= i12;
                }
                i13 = i3 & 256;
                if (i13 != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i14 = 536870912;
                        } else {
                            i14 = 268435456;
                        }
                        i4 |= i14;
                    }
                    if ((i3 & 512) != 0) {
                        i15 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i16 = 4;
                        } else {
                            i16 = 2;
                        }
                        i15 = i2 | i16;
                    } else {
                        i15 = i2;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j1111113 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j1111113;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function1116 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j1111114 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j1111114;
                                function11 = function1116;
                            }
                        } else {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j1111115 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j1111115;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function1117 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j1111116 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j1111116;
                                function11 = function1117;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                        }
                        long j1111117 = j4;
                        float f111 = f7;
                        Shape shape15 = richTooltipContainerShape;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f111, shape15, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function10;
                        f4 = f8;
                        function7 = function11;
                        shape2 = shape15;
                        f5 = fM2085getContainerElevationD9Ej5fM;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        f6 = f111;
                        j3 = j1111117;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        shape2 = shape;
                        f4 = f2;
                        modifier3 = modifier2;
                        function7 = function5;
                        f5 = f3;
                        f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        richTooltipColors3 = richTooltipColors2;
                        function8 = function6;
                        j3 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                if ((i3 & 512) != 0) {
                    i15 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i16 = 4;
                    } else {
                        i16 = 2;
                    }
                    i15 = i2 | i16;
                } else {
                    i15 = i2;
                }
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j1111118 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j1111118;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function1118 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j1111119 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j1111119;
                            function11 = function1118;
                        }
                    } else {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j11111110 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j11111110;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function1119 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j11111111 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j11111111;
                            function11 = function1119;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                    }
                    long j11111112 = j4;
                    float f112 = f7;
                    Shape shape16 = richTooltipContainerShape;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f112, shape16, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function10;
                    f4 = f8;
                    function7 = function11;
                    shape2 = shape16;
                    f5 = fM2085getContainerElevationD9Ej5fM;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    f6 = f112;
                    j3 = j11111112;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    shape2 = shape;
                    f4 = f2;
                    modifier3 = modifier2;
                    function7 = function5;
                    f5 = f3;
                    f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                    richTooltipColors3 = richTooltipColors2;
                    function8 = function6;
                    j3 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 100663296;
            i13 = i3 & 256;
            if (i13 != 0) {
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i4 |= i14;
                }
                if ((i3 & 512) != 0) {
                    i15 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i16 = 4;
                    } else {
                        i16 = 2;
                    }
                    i15 = i2 | i16;
                } else {
                    i15 = i2;
                }
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j11111113 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j11111113;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function11110 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j11111114 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j11111114;
                            function11 = function11110;
                        }
                    } else {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j11111115 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j11111115;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function11111 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j11111116 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j11111116;
                            function11 = function11111;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                    }
                    long j11111117 = j4;
                    float f113 = f7;
                    Shape shape17 = richTooltipContainerShape;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f113, shape17, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function10;
                    f4 = f8;
                    function7 = function11;
                    shape2 = shape17;
                    f5 = fM2085getContainerElevationD9Ej5fM;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    f6 = f113;
                    j3 = j11111117;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    shape2 = shape;
                    f4 = f2;
                    modifier3 = modifier2;
                    function7 = function5;
                    f5 = f3;
                    f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                    richTooltipColors3 = richTooltipColors2;
                    function8 = function6;
                    j3 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 805306368;
            if ((i3 & 512) != 0) {
                i15 = i2 | 6;
            } else if ((i2 & 6) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i16 = 4;
                } else {
                    i16 = 2;
                }
                i15 = i2 | i16;
            } else {
                i15 = i2;
            }
            if ((i4 & 306783379) == 306783378) {
                z = true;
            } else {
                z = true;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i20 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i5 != 0) {
                        function5 = null;
                    }
                    if (i7 == 0) {
                    }
                    if ((i3 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i4 &= -57345;
                    } else {
                        jM6129getUnspecifiedMYxV2XQ = j2;
                    }
                    if (i9 != 0) {
                        fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                    }
                    if ((i3 & 32) != 0) {
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        i4 &= -3670017;
                    } else {
                        richTooltipContainerShape = shape;
                    }
                    if ((i3 & 64) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i4 = (-29360129) & i4;
                    }
                    if (i11 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    } else {
                        fM1742getLevel0D9Ej5fM = f2;
                    }
                    if (i13 != 0) {
                        long j11111118 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        function11 = function5;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        j4 = j11111118;
                    } else {
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        Function2 function11112 = function5;
                        fM2085getContainerElevationD9Ej5fM = f3;
                        long j11111119 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        j4 = j11111119;
                        function11 = function11112;
                    }
                } else {
                    if (i20 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i5 != 0) {
                        function5 = null;
                    }
                    if (i7 == 0) {
                    }
                    if ((i3 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i4 &= -57345;
                    } else {
                        jM6129getUnspecifiedMYxV2XQ = j2;
                    }
                    if (i9 != 0) {
                        fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                    }
                    if ((i3 & 32) != 0) {
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        i4 &= -3670017;
                    } else {
                        richTooltipContainerShape = shape;
                    }
                    if ((i3 & 64) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i4 = (-29360129) & i4;
                    }
                    if (i11 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    } else {
                        fM1742getLevel0D9Ej5fM = f2;
                    }
                    if (i13 != 0) {
                        long j111111110 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        function11 = function5;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        j4 = j111111110;
                    } else {
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        Function2 function11113 = function5;
                        fM2085getContainerElevationD9Ej5fM = f3;
                        long j111111111 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        j4 = j111111111;
                        function11 = function11113;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                }
                long j111111112 = j4;
                float f114 = f7;
                Shape shape18 = richTooltipContainerShape;
                TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f114, shape18, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function8 = function10;
                f4 = f8;
                function7 = function11;
                shape2 = shape18;
                f5 = fM2085getContainerElevationD9Ej5fM;
                modifier3 = modifier4;
                richTooltipColors3 = richTooltipColors4;
                f6 = f114;
                j3 = j111111112;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                shape2 = shape;
                f4 = f2;
                modifier3 = modifier2;
                function7 = function5;
                f5 = f3;
                f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                richTooltipColors3 = richTooltipColors2;
                function8 = function6;
                j3 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                    public final Object invoke(Object obj, Object obj2) {
                        return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 48;
        modifier2 = modifier;
        i5 = i3 & 2;
        if (i5 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                function5 = function2;
                if (composerStartRestartGroup.changedInstance(function5)) {
                    i6 = 256;
                } else {
                    i6 = 128;
                }
                i4 |= i6;
            }
            i7 = i3 & 4;
            if (i7 != 0) {
                if ((i & 3072) == 0) {
                    function6 = function3;
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i8 = 2048;
                    } else {
                        i8 = 1024;
                    }
                    i4 |= i8;
                }
                if ((i & 24576) == 0) {
                    if ((i3 & 8) == 0) {
                        j2 = j;
                        if (composerStartRestartGroup.changed(j2)) {
                        }
                        i4 |= i21;
                    } else {
                        j2 = j;
                    }
                    i4 |= i21;
                } else {
                    j2 = j;
                }
                i9 = i3 & 16;
                if (i9 != 0) {
                    i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    fM1273getRichTooltipMaxWidthD9Ej5fM = f;
                } else {
                    fM1273getRichTooltipMaxWidthD9Ej5fM = f;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(fM1273getRichTooltipMaxWidthD9Ej5fM)) {
                            i10 = 131072;
                        } else {
                            i10 = 65536;
                        }
                        i4 |= i10;
                    }
                }
                if ((i & 1572864) != 0) {
                    if ((i3 & 32) == 0) {
                        i19 = 524288;
                    } else {
                        i19 = 524288;
                    }
                    i4 |= i19;
                }
                if ((i & 12582912) == 0) {
                    richTooltipColors2 = richTooltipColors;
                    if ((i3 & 64) == 0) {
                        i18 = 4194304;
                    } else {
                        i18 = 4194304;
                    }
                    i4 |= i18;
                } else {
                    richTooltipColors2 = richTooltipColors;
                }
                i11 = i3 & 128;
                if (i11 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(f2)) {
                            i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i12 = 33554432;
                        }
                        i4 |= i12;
                    }
                    i13 = i3 & 256;
                    if (i13 != 0) {
                        if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changed(f3)) {
                                i14 = 536870912;
                            } else {
                                i14 = 268435456;
                            }
                            i4 |= i14;
                        }
                        if ((i3 & 512) != 0) {
                            i15 = i2 | 6;
                        } else if ((i2 & 6) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i16 = 4;
                            } else {
                                i16 = 2;
                            }
                            i15 = i2 | i16;
                        } else {
                            i15 = i2;
                        }
                        if ((i4 & 306783379) == 306783378) {
                            z = true;
                        } else {
                            z = true;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) != 0) {
                                if (i20 != 0) {
                                    modifier4 = Modifier.INSTANCE;
                                } else {
                                    modifier4 = modifier2;
                                }
                                if (i5 != 0) {
                                    function5 = null;
                                }
                                if (i7 == 0) {
                                }
                                if ((i3 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i4 &= -57345;
                                } else {
                                    jM6129getUnspecifiedMYxV2XQ = j2;
                                }
                                if (i9 != 0) {
                                    fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                                }
                                if ((i3 & 32) != 0) {
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                    i4 &= -3670017;
                                } else {
                                    richTooltipContainerShape = shape;
                                }
                                if ((i3 & 64) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i4 = (-29360129) & i4;
                                }
                                if (i11 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                } else {
                                    fM1742getLevel0D9Ej5fM = f2;
                                }
                                if (i13 != 0) {
                                    long j111111113 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    function11 = function5;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    j4 = j111111113;
                                } else {
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    Function2 function11114 = function5;
                                    fM2085getContainerElevationD9Ej5fM = f3;
                                    long j111111114 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    j4 = j111111114;
                                    function11 = function11114;
                                }
                            } else {
                                if (i20 != 0) {
                                    modifier4 = Modifier.INSTANCE;
                                } else {
                                    modifier4 = modifier2;
                                }
                                if (i5 != 0) {
                                    function5 = null;
                                }
                                if (i7 == 0) {
                                }
                                if ((i3 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i4 &= -57345;
                                } else {
                                    jM6129getUnspecifiedMYxV2XQ = j2;
                                }
                                if (i9 != 0) {
                                    fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                                }
                                if ((i3 & 32) != 0) {
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                    i4 &= -3670017;
                                } else {
                                    richTooltipContainerShape = shape;
                                }
                                if ((i3 & 64) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i4 = (-29360129) & i4;
                                }
                                if (i11 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                } else {
                                    fM1742getLevel0D9Ej5fM = f2;
                                }
                                if (i13 != 0) {
                                    long j111111115 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    function11 = function5;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    j4 = j111111115;
                                } else {
                                    i17 = i4;
                                    function10 = function9;
                                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                    Function2 function11115 = function5;
                                    fM2085getContainerElevationD9Ej5fM = f3;
                                    long j111111116 = jM6129getUnspecifiedMYxV2XQ;
                                    f8 = fM1742getLevel0D9Ej5fM;
                                    richTooltipColors4 = richTooltipColors2;
                                    j4 = j111111116;
                                    function11 = function11115;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                            }
                            long j111111117 = j4;
                            float f115 = f7;
                            Shape shape19 = richTooltipContainerShape;
                            TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f115, shape19, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function8 = function10;
                            f4 = f8;
                            function7 = function11;
                            shape2 = shape19;
                            f5 = fM2085getContainerElevationD9Ej5fM;
                            modifier3 = modifier4;
                            richTooltipColors3 = richTooltipColors4;
                            f6 = f115;
                            j3 = j111111117;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            shape2 = shape;
                            f4 = f2;
                            modifier3 = modifier2;
                            function7 = function5;
                            f5 = f3;
                            f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            richTooltipColors3 = richTooltipColors2;
                            function8 = function6;
                            j3 = j2;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                                public final Object invoke(Object obj, Object obj2) {
                                    return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i4 |= 805306368;
                    if ((i3 & 512) != 0) {
                        i15 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i16 = 4;
                        } else {
                            i16 = 2;
                        }
                        i15 = i2 | i16;
                    } else {
                        i15 = i2;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j111111118 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j111111118;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function11116 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j111111119 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j111111119;
                                function11 = function11116;
                            }
                        } else {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j1111111110 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j1111111110;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function11117 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j1111111111 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j1111111111;
                                function11 = function11117;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                        }
                        long j1111111112 = j4;
                        float f116 = f7;
                        Shape shape110 = richTooltipContainerShape;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f116, shape110, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function10;
                        f4 = f8;
                        function7 = function11;
                        shape2 = shape110;
                        f5 = fM2085getContainerElevationD9Ej5fM;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        f6 = f116;
                        j3 = j1111111112;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        shape2 = shape;
                        f4 = f2;
                        modifier3 = modifier2;
                        function7 = function5;
                        f5 = f3;
                        f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        richTooltipColors3 = richTooltipColors2;
                        function8 = function6;
                        j3 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 100663296;
                i13 = i3 & 256;
                if (i13 != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i14 = 536870912;
                        } else {
                            i14 = 268435456;
                        }
                        i4 |= i14;
                    }
                    if ((i3 & 512) != 0) {
                        i15 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i16 = 4;
                        } else {
                            i16 = 2;
                        }
                        i15 = i2 | i16;
                    } else {
                        i15 = i2;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j1111111113 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j1111111113;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function11118 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j1111111114 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j1111111114;
                                function11 = function11118;
                            }
                        } else {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j1111111115 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j1111111115;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function11119 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j1111111116 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j1111111116;
                                function11 = function11119;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                        }
                        long j1111111117 = j4;
                        float f117 = f7;
                        Shape shape111 = richTooltipContainerShape;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f117, shape111, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function10;
                        f4 = f8;
                        function7 = function11;
                        shape2 = shape111;
                        f5 = fM2085getContainerElevationD9Ej5fM;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        f6 = f117;
                        j3 = j1111111117;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        shape2 = shape;
                        f4 = f2;
                        modifier3 = modifier2;
                        function7 = function5;
                        f5 = f3;
                        f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        richTooltipColors3 = richTooltipColors2;
                        function8 = function6;
                        j3 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                if ((i3 & 512) != 0) {
                    i15 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i16 = 4;
                    } else {
                        i16 = 2;
                    }
                    i15 = i2 | i16;
                } else {
                    i15 = i2;
                }
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j1111111118 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j1111111118;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function111110 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j1111111119 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j1111111119;
                            function11 = function111110;
                        }
                    } else {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j11111111110 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j11111111110;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function111111 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j11111111111 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j11111111111;
                            function11 = function111111;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                    }
                    long j11111111112 = j4;
                    float f118 = f7;
                    Shape shape112 = richTooltipContainerShape;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f118, shape112, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function10;
                    f4 = f8;
                    function7 = function11;
                    shape2 = shape112;
                    f5 = fM2085getContainerElevationD9Ej5fM;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    f6 = f118;
                    j3 = j11111111112;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    shape2 = shape;
                    f4 = f2;
                    modifier3 = modifier2;
                    function7 = function5;
                    f5 = f3;
                    f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                    richTooltipColors3 = richTooltipColors2;
                    function8 = function6;
                    j3 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 3072;
            function6 = function3;
            if ((i & 24576) == 0) {
                if ((i3 & 8) == 0) {
                    j2 = j;
                    if (composerStartRestartGroup.changed(j2)) {
                    }
                    i4 |= i21;
                } else {
                    j2 = j;
                }
                i4 |= i21;
            } else {
                j2 = j;
            }
            i9 = i3 & 16;
            if (i9 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                fM1273getRichTooltipMaxWidthD9Ej5fM = f;
            } else {
                fM1273getRichTooltipMaxWidthD9Ej5fM = f;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(fM1273getRichTooltipMaxWidthD9Ej5fM)) {
                        i10 = 131072;
                    } else {
                        i10 = 65536;
                    }
                    i4 |= i10;
                }
            }
            if ((i & 1572864) != 0) {
                if ((i3 & 32) == 0) {
                    i19 = 524288;
                } else {
                    i19 = 524288;
                }
                i4 |= i19;
            }
            if ((i & 12582912) == 0) {
                richTooltipColors2 = richTooltipColors;
                if ((i3 & 64) == 0) {
                    i18 = 4194304;
                } else {
                    i18 = 4194304;
                }
                i4 |= i18;
            } else {
                richTooltipColors2 = richTooltipColors;
            }
            i11 = i3 & 128;
            if (i11 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f2)) {
                        i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i12 = 33554432;
                    }
                    i4 |= i12;
                }
                i13 = i3 & 256;
                if (i13 != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i14 = 536870912;
                        } else {
                            i14 = 268435456;
                        }
                        i4 |= i14;
                    }
                    if ((i3 & 512) != 0) {
                        i15 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i16 = 4;
                        } else {
                            i16 = 2;
                        }
                        i15 = i2 | i16;
                    } else {
                        i15 = i2;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j11111111113 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j11111111113;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function111112 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j11111111114 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j11111111114;
                                function11 = function111112;
                            }
                        } else {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j11111111115 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j11111111115;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function111113 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j11111111116 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j11111111116;
                                function11 = function111113;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                        }
                        long j11111111117 = j4;
                        float f119 = f7;
                        Shape shape113 = richTooltipContainerShape;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f119, shape113, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function10;
                        f4 = f8;
                        function7 = function11;
                        shape2 = shape113;
                        f5 = fM2085getContainerElevationD9Ej5fM;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        f6 = f119;
                        j3 = j11111111117;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        shape2 = shape;
                        f4 = f2;
                        modifier3 = modifier2;
                        function7 = function5;
                        f5 = f3;
                        f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        richTooltipColors3 = richTooltipColors2;
                        function8 = function6;
                        j3 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                if ((i3 & 512) != 0) {
                    i15 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i16 = 4;
                    } else {
                        i16 = 2;
                    }
                    i15 = i2 | i16;
                } else {
                    i15 = i2;
                }
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j11111111118 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j11111111118;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function111114 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j11111111119 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j11111111119;
                            function11 = function111114;
                        }
                    } else {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j111111111110 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j111111111110;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function111115 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j111111111111 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j111111111111;
                            function11 = function111115;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                    }
                    long j111111111112 = j4;
                    float f1110 = f7;
                    Shape shape114 = richTooltipContainerShape;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f1110, shape114, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function10;
                    f4 = f8;
                    function7 = function11;
                    shape2 = shape114;
                    f5 = fM2085getContainerElevationD9Ej5fM;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    f6 = f1110;
                    j3 = j111111111112;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    shape2 = shape;
                    f4 = f2;
                    modifier3 = modifier2;
                    function7 = function5;
                    f5 = f3;
                    f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                    richTooltipColors3 = richTooltipColors2;
                    function8 = function6;
                    j3 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 100663296;
            i13 = i3 & 256;
            if (i13 != 0) {
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i4 |= i14;
                }
                if ((i3 & 512) != 0) {
                    i15 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i16 = 4;
                    } else {
                        i16 = 2;
                    }
                    i15 = i2 | i16;
                } else {
                    i15 = i2;
                }
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j111111111113 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j111111111113;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function111116 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j111111111114 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j111111111114;
                            function11 = function111116;
                        }
                    } else {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j111111111115 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j111111111115;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function111117 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j111111111116 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j111111111116;
                            function11 = function111117;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                    }
                    long j111111111117 = j4;
                    float f1111 = f7;
                    Shape shape115 = richTooltipContainerShape;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f1111, shape115, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function10;
                    f4 = f8;
                    function7 = function11;
                    shape2 = shape115;
                    f5 = fM2085getContainerElevationD9Ej5fM;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    f6 = f1111;
                    j3 = j111111111117;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    shape2 = shape;
                    f4 = f2;
                    modifier3 = modifier2;
                    function7 = function5;
                    f5 = f3;
                    f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                    richTooltipColors3 = richTooltipColors2;
                    function8 = function6;
                    j3 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 805306368;
            if ((i3 & 512) != 0) {
                i15 = i2 | 6;
            } else if ((i2 & 6) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i16 = 4;
                } else {
                    i16 = 2;
                }
                i15 = i2 | i16;
            } else {
                i15 = i2;
            }
            if ((i4 & 306783379) == 306783378) {
                z = true;
            } else {
                z = true;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i20 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i5 != 0) {
                        function5 = null;
                    }
                    if (i7 == 0) {
                    }
                    if ((i3 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i4 &= -57345;
                    } else {
                        jM6129getUnspecifiedMYxV2XQ = j2;
                    }
                    if (i9 != 0) {
                        fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                    }
                    if ((i3 & 32) != 0) {
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        i4 &= -3670017;
                    } else {
                        richTooltipContainerShape = shape;
                    }
                    if ((i3 & 64) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i4 = (-29360129) & i4;
                    }
                    if (i11 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    } else {
                        fM1742getLevel0D9Ej5fM = f2;
                    }
                    if (i13 != 0) {
                        long j111111111118 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        function11 = function5;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        j4 = j111111111118;
                    } else {
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        Function2 function111118 = function5;
                        fM2085getContainerElevationD9Ej5fM = f3;
                        long j111111111119 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        j4 = j111111111119;
                        function11 = function111118;
                    }
                } else {
                    if (i20 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i5 != 0) {
                        function5 = null;
                    }
                    if (i7 == 0) {
                    }
                    if ((i3 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i4 &= -57345;
                    } else {
                        jM6129getUnspecifiedMYxV2XQ = j2;
                    }
                    if (i9 != 0) {
                        fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                    }
                    if ((i3 & 32) != 0) {
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        i4 &= -3670017;
                    } else {
                        richTooltipContainerShape = shape;
                    }
                    if ((i3 & 64) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i4 = (-29360129) & i4;
                    }
                    if (i11 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    } else {
                        fM1742getLevel0D9Ej5fM = f2;
                    }
                    if (i13 != 0) {
                        long j1111111111110 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        function11 = function5;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        j4 = j1111111111110;
                    } else {
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        Function2 function111119 = function5;
                        fM2085getContainerElevationD9Ej5fM = f3;
                        long j1111111111111 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        j4 = j1111111111111;
                        function11 = function111119;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                }
                long j1111111111112 = j4;
                float f1112 = f7;
                Shape shape116 = richTooltipContainerShape;
                TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f1112, shape116, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function8 = function10;
                f4 = f8;
                function7 = function11;
                shape2 = shape116;
                f5 = fM2085getContainerElevationD9Ej5fM;
                modifier3 = modifier4;
                richTooltipColors3 = richTooltipColors4;
                f6 = f1112;
                j3 = j1111111111112;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                shape2 = shape;
                f4 = f2;
                modifier3 = modifier2;
                function7 = function5;
                f5 = f3;
                f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                richTooltipColors3 = richTooltipColors2;
                function8 = function6;
                j3 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                    public final Object invoke(Object obj, Object obj2) {
                        return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        function5 = function2;
        i7 = i3 & 4;
        if (i7 != 0) {
            if ((i & 3072) == 0) {
                function6 = function3;
                if (composerStartRestartGroup.changedInstance(function6)) {
                    i8 = 2048;
                } else {
                    i8 = 1024;
                }
                i4 |= i8;
            }
            if ((i & 24576) == 0) {
                if ((i3 & 8) == 0) {
                    j2 = j;
                    if (composerStartRestartGroup.changed(j2)) {
                    }
                    i4 |= i21;
                } else {
                    j2 = j;
                }
                i4 |= i21;
            } else {
                j2 = j;
            }
            i9 = i3 & 16;
            if (i9 != 0) {
                i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                fM1273getRichTooltipMaxWidthD9Ej5fM = f;
            } else {
                fM1273getRichTooltipMaxWidthD9Ej5fM = f;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(fM1273getRichTooltipMaxWidthD9Ej5fM)) {
                        i10 = 131072;
                    } else {
                        i10 = 65536;
                    }
                    i4 |= i10;
                }
            }
            if ((i & 1572864) != 0) {
                if ((i3 & 32) == 0) {
                    i19 = 524288;
                } else {
                    i19 = 524288;
                }
                i4 |= i19;
            }
            if ((i & 12582912) == 0) {
                richTooltipColors2 = richTooltipColors;
                if ((i3 & 64) == 0) {
                    i18 = 4194304;
                } else {
                    i18 = 4194304;
                }
                i4 |= i18;
            } else {
                richTooltipColors2 = richTooltipColors;
            }
            i11 = i3 & 128;
            if (i11 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f2)) {
                        i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i12 = 33554432;
                    }
                    i4 |= i12;
                }
                i13 = i3 & 256;
                if (i13 != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changed(f3)) {
                            i14 = 536870912;
                        } else {
                            i14 = 268435456;
                        }
                        i4 |= i14;
                    }
                    if ((i3 & 512) != 0) {
                        i15 = i2 | 6;
                    } else if ((i2 & 6) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i16 = 4;
                        } else {
                            i16 = 2;
                        }
                        i15 = i2 | i16;
                    } else {
                        i15 = i2;
                    }
                    if ((i4 & 306783379) == 306783378) {
                        z = true;
                    } else {
                        z = true;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) != 0) {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j1111111111113 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j1111111111113;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function1111110 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j1111111111114 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j1111111111114;
                                function11 = function1111110;
                            }
                        } else {
                            if (i20 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i5 != 0) {
                                function5 = null;
                            }
                            if (i7 == 0) {
                            }
                            if ((i3 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i4 &= -57345;
                            } else {
                                jM6129getUnspecifiedMYxV2XQ = j2;
                            }
                            if (i9 != 0) {
                                fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                            }
                            if ((i3 & 32) != 0) {
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                i4 &= -3670017;
                            } else {
                                richTooltipContainerShape = shape;
                            }
                            if ((i3 & 64) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i4 = (-29360129) & i4;
                            }
                            if (i11 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            } else {
                                fM1742getLevel0D9Ej5fM = f2;
                            }
                            if (i13 != 0) {
                                long j1111111111115 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                function11 = function5;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                j4 = j1111111111115;
                            } else {
                                i17 = i4;
                                function10 = function9;
                                f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                                Function2 function1111111 = function5;
                                fM2085getContainerElevationD9Ej5fM = f3;
                                long j1111111111116 = jM6129getUnspecifiedMYxV2XQ;
                                f8 = fM1742getLevel0D9Ej5fM;
                                richTooltipColors4 = richTooltipColors2;
                                j4 = j1111111111116;
                                function11 = function1111111;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                        }
                        long j1111111111117 = j4;
                        float f1113 = f7;
                        Shape shape117 = richTooltipContainerShape;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f1113, shape117, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function10;
                        f4 = f8;
                        function7 = function11;
                        shape2 = shape117;
                        f5 = fM2085getContainerElevationD9Ej5fM;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        f6 = f1113;
                        j3 = j1111111111117;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        shape2 = shape;
                        f4 = f2;
                        modifier3 = modifier2;
                        function7 = function5;
                        f5 = f3;
                        f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        richTooltipColors3 = richTooltipColors2;
                        function8 = function6;
                        j3 = j2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i4 |= 805306368;
                if ((i3 & 512) != 0) {
                    i15 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i16 = 4;
                    } else {
                        i16 = 2;
                    }
                    i15 = i2 | i16;
                } else {
                    i15 = i2;
                }
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j1111111111118 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j1111111111118;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function1111112 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j1111111111119 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j1111111111119;
                            function11 = function1111112;
                        }
                    } else {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j11111111111110 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j11111111111110;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function1111113 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j11111111111111 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j11111111111111;
                            function11 = function1111113;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                    }
                    long j11111111111112 = j4;
                    float f1114 = f7;
                    Shape shape118 = richTooltipContainerShape;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f1114, shape118, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function10;
                    f4 = f8;
                    function7 = function11;
                    shape2 = shape118;
                    f5 = fM2085getContainerElevationD9Ej5fM;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    f6 = f1114;
                    j3 = j11111111111112;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    shape2 = shape;
                    f4 = f2;
                    modifier3 = modifier2;
                    function7 = function5;
                    f5 = f3;
                    f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                    richTooltipColors3 = richTooltipColors2;
                    function8 = function6;
                    j3 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 100663296;
            i13 = i3 & 256;
            if (i13 != 0) {
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i4 |= i14;
                }
                if ((i3 & 512) != 0) {
                    i15 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i16 = 4;
                    } else {
                        i16 = 2;
                    }
                    i15 = i2 | i16;
                } else {
                    i15 = i2;
                }
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j11111111111113 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j11111111111113;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function1111114 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j11111111111114 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j11111111111114;
                            function11 = function1111114;
                        }
                    } else {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j11111111111115 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j11111111111115;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function1111115 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j11111111111116 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j11111111111116;
                            function11 = function1111115;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                    }
                    long j11111111111117 = j4;
                    float f1115 = f7;
                    Shape shape119 = richTooltipContainerShape;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f1115, shape119, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function10;
                    f4 = f8;
                    function7 = function11;
                    shape2 = shape119;
                    f5 = fM2085getContainerElevationD9Ej5fM;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    f6 = f1115;
                    j3 = j11111111111117;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    shape2 = shape;
                    f4 = f2;
                    modifier3 = modifier2;
                    function7 = function5;
                    f5 = f3;
                    f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                    richTooltipColors3 = richTooltipColors2;
                    function8 = function6;
                    j3 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 805306368;
            if ((i3 & 512) != 0) {
                i15 = i2 | 6;
            } else if ((i2 & 6) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i16 = 4;
                } else {
                    i16 = 2;
                }
                i15 = i2 | i16;
            } else {
                i15 = i2;
            }
            if ((i4 & 306783379) == 306783378) {
                z = true;
            } else {
                z = true;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i20 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i5 != 0) {
                        function5 = null;
                    }
                    if (i7 == 0) {
                    }
                    if ((i3 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i4 &= -57345;
                    } else {
                        jM6129getUnspecifiedMYxV2XQ = j2;
                    }
                    if (i9 != 0) {
                        fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                    }
                    if ((i3 & 32) != 0) {
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        i4 &= -3670017;
                    } else {
                        richTooltipContainerShape = shape;
                    }
                    if ((i3 & 64) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i4 = (-29360129) & i4;
                    }
                    if (i11 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    } else {
                        fM1742getLevel0D9Ej5fM = f2;
                    }
                    if (i13 != 0) {
                        long j11111111111118 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        function11 = function5;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        j4 = j11111111111118;
                    } else {
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        Function2 function1111116 = function5;
                        fM2085getContainerElevationD9Ej5fM = f3;
                        long j11111111111119 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        j4 = j11111111111119;
                        function11 = function1111116;
                    }
                } else {
                    if (i20 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i5 != 0) {
                        function5 = null;
                    }
                    if (i7 == 0) {
                    }
                    if ((i3 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i4 &= -57345;
                    } else {
                        jM6129getUnspecifiedMYxV2XQ = j2;
                    }
                    if (i9 != 0) {
                        fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                    }
                    if ((i3 & 32) != 0) {
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        i4 &= -3670017;
                    } else {
                        richTooltipContainerShape = shape;
                    }
                    if ((i3 & 64) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i4 = (-29360129) & i4;
                    }
                    if (i11 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    } else {
                        fM1742getLevel0D9Ej5fM = f2;
                    }
                    if (i13 != 0) {
                        long j111111111111110 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        function11 = function5;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        j4 = j111111111111110;
                    } else {
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        Function2 function1111117 = function5;
                        fM2085getContainerElevationD9Ej5fM = f3;
                        long j111111111111111 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        j4 = j111111111111111;
                        function11 = function1111117;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                }
                long j111111111111112 = j4;
                float f1116 = f7;
                Shape shape1110 = richTooltipContainerShape;
                TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f1116, shape1110, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function8 = function10;
                f4 = f8;
                function7 = function11;
                shape2 = shape1110;
                f5 = fM2085getContainerElevationD9Ej5fM;
                modifier3 = modifier4;
                richTooltipColors3 = richTooltipColors4;
                f6 = f1116;
                j3 = j111111111111112;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                shape2 = shape;
                f4 = f2;
                modifier3 = modifier2;
                function7 = function5;
                f5 = f3;
                f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                richTooltipColors3 = richTooltipColors2;
                function8 = function6;
                j3 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                    public final Object invoke(Object obj, Object obj2) {
                        return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 3072;
        function6 = function3;
        if ((i & 24576) == 0) {
            if ((i3 & 8) == 0) {
                j2 = j;
                if (composerStartRestartGroup.changed(j2)) {
                }
                i4 |= i21;
            } else {
                j2 = j;
            }
            i4 |= i21;
        } else {
            j2 = j;
        }
        i9 = i3 & 16;
        if (i9 != 0) {
            i4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            fM1273getRichTooltipMaxWidthD9Ej5fM = f;
        } else {
            fM1273getRichTooltipMaxWidthD9Ej5fM = f;
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(fM1273getRichTooltipMaxWidthD9Ej5fM)) {
                    i10 = 131072;
                } else {
                    i10 = 65536;
                }
                i4 |= i10;
            }
        }
        if ((i & 1572864) != 0) {
            if ((i3 & 32) == 0) {
                i19 = 524288;
            } else {
                i19 = 524288;
            }
            i4 |= i19;
        }
        if ((i & 12582912) == 0) {
            richTooltipColors2 = richTooltipColors;
            if ((i3 & 64) == 0) {
                i18 = 4194304;
            } else {
                i18 = 4194304;
            }
            i4 |= i18;
        } else {
            richTooltipColors2 = richTooltipColors;
        }
        i11 = i3 & 128;
        if (i11 != 0) {
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changed(f2)) {
                    i12 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i12 = 33554432;
                }
                i4 |= i12;
            }
            i13 = i3 & 256;
            if (i13 != 0) {
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i14 = 536870912;
                    } else {
                        i14 = 268435456;
                    }
                    i4 |= i14;
                }
                if ((i3 & 512) != 0) {
                    i15 = i2 | 6;
                } else if ((i2 & 6) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i16 = 4;
                    } else {
                        i16 = 2;
                    }
                    i15 = i2 | i16;
                } else {
                    i15 = i2;
                }
                if ((i4 & 306783379) == 306783378) {
                    z = true;
                } else {
                    z = true;
                }
                if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) != 0) {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j111111111111113 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j111111111111113;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function1111118 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j111111111111114 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j111111111111114;
                            function11 = function1111118;
                        }
                    } else {
                        if (i20 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i5 != 0) {
                            function5 = null;
                        }
                        if (i7 == 0) {
                        }
                        if ((i3 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i4 &= -57345;
                        } else {
                            jM6129getUnspecifiedMYxV2XQ = j2;
                        }
                        if (i9 != 0) {
                            fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                        }
                        if ((i3 & 32) != 0) {
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            i4 &= -3670017;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        if ((i3 & 64) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i4 = (-29360129) & i4;
                        }
                        if (i11 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        } else {
                            fM1742getLevel0D9Ej5fM = f2;
                        }
                        if (i13 != 0) {
                            long j111111111111115 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            function11 = function5;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            j4 = j111111111111115;
                        } else {
                            i17 = i4;
                            function10 = function9;
                            f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                            Function2 function1111119 = function5;
                            fM2085getContainerElevationD9Ej5fM = f3;
                            long j111111111111116 = jM6129getUnspecifiedMYxV2XQ;
                            f8 = fM1742getLevel0D9Ej5fM;
                            richTooltipColors4 = richTooltipColors2;
                            j4 = j111111111111116;
                            function11 = function1111119;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                    }
                    long j111111111111117 = j4;
                    float f1117 = f7;
                    Shape shape1111 = richTooltipContainerShape;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f1117, shape1111, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function10;
                    f4 = f8;
                    function7 = function11;
                    shape2 = shape1111;
                    f5 = fM2085getContainerElevationD9Ej5fM;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    f6 = f1117;
                    j3 = j111111111111117;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    shape2 = shape;
                    f4 = f2;
                    modifier3 = modifier2;
                    function7 = function5;
                    f5 = f3;
                    f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                    richTooltipColors3 = richTooltipColors2;
                    function8 = function6;
                    j3 = j2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 805306368;
            if ((i3 & 512) != 0) {
                i15 = i2 | 6;
            } else if ((i2 & 6) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i16 = 4;
                } else {
                    i16 = 2;
                }
                i15 = i2 | i16;
            } else {
                i15 = i2;
            }
            if ((i4 & 306783379) == 306783378) {
                z = true;
            } else {
                z = true;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i20 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i5 != 0) {
                        function5 = null;
                    }
                    if (i7 == 0) {
                    }
                    if ((i3 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i4 &= -57345;
                    } else {
                        jM6129getUnspecifiedMYxV2XQ = j2;
                    }
                    if (i9 != 0) {
                        fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                    }
                    if ((i3 & 32) != 0) {
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        i4 &= -3670017;
                    } else {
                        richTooltipContainerShape = shape;
                    }
                    if ((i3 & 64) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i4 = (-29360129) & i4;
                    }
                    if (i11 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    } else {
                        fM1742getLevel0D9Ej5fM = f2;
                    }
                    if (i13 != 0) {
                        long j111111111111118 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        function11 = function5;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        j4 = j111111111111118;
                    } else {
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        Function2 function11111110 = function5;
                        fM2085getContainerElevationD9Ej5fM = f3;
                        long j111111111111119 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        j4 = j111111111111119;
                        function11 = function11111110;
                    }
                } else {
                    if (i20 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i5 != 0) {
                        function5 = null;
                    }
                    if (i7 == 0) {
                    }
                    if ((i3 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i4 &= -57345;
                    } else {
                        jM6129getUnspecifiedMYxV2XQ = j2;
                    }
                    if (i9 != 0) {
                        fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                    }
                    if ((i3 & 32) != 0) {
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        i4 &= -3670017;
                    } else {
                        richTooltipContainerShape = shape;
                    }
                    if ((i3 & 64) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i4 = (-29360129) & i4;
                    }
                    if (i11 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    } else {
                        fM1742getLevel0D9Ej5fM = f2;
                    }
                    if (i13 != 0) {
                        long j1111111111111110 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        function11 = function5;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        j4 = j1111111111111110;
                    } else {
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        Function2 function11111111 = function5;
                        fM2085getContainerElevationD9Ej5fM = f3;
                        long j1111111111111111 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        j4 = j1111111111111111;
                        function11 = function11111111;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                }
                long j1111111111111112 = j4;
                float f1118 = f7;
                Shape shape1112 = richTooltipContainerShape;
                TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f1118, shape1112, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function8 = function10;
                f4 = f8;
                function7 = function11;
                shape2 = shape1112;
                f5 = fM2085getContainerElevationD9Ej5fM;
                modifier3 = modifier4;
                richTooltipColors3 = richTooltipColors4;
                f6 = f1118;
                j3 = j1111111111111112;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                shape2 = shape;
                f4 = f2;
                modifier3 = modifier2;
                function7 = function5;
                f5 = f3;
                f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                richTooltipColors3 = richTooltipColors2;
                function8 = function6;
                j3 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                    public final Object invoke(Object obj, Object obj2) {
                        return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 100663296;
        i13 = i3 & 256;
        if (i13 != 0) {
            if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changed(f3)) {
                    i14 = 536870912;
                } else {
                    i14 = 268435456;
                }
                i4 |= i14;
            }
            if ((i3 & 512) != 0) {
                i15 = i2 | 6;
            } else if ((i2 & 6) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i16 = 4;
                } else {
                    i16 = 2;
                }
                i15 = i2 | i16;
            } else {
                i15 = i2;
            }
            if ((i4 & 306783379) == 306783378) {
                z = true;
            } else {
                z = true;
            }
            if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) != 0) {
                    if (i20 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i5 != 0) {
                        function5 = null;
                    }
                    if (i7 == 0) {
                    }
                    if ((i3 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i4 &= -57345;
                    } else {
                        jM6129getUnspecifiedMYxV2XQ = j2;
                    }
                    if (i9 != 0) {
                        fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                    }
                    if ((i3 & 32) != 0) {
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        i4 &= -3670017;
                    } else {
                        richTooltipContainerShape = shape;
                    }
                    if ((i3 & 64) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i4 = (-29360129) & i4;
                    }
                    if (i11 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    } else {
                        fM1742getLevel0D9Ej5fM = f2;
                    }
                    if (i13 != 0) {
                        long j1111111111111113 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        function11 = function5;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        j4 = j1111111111111113;
                    } else {
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        Function2 function11111112 = function5;
                        fM2085getContainerElevationD9Ej5fM = f3;
                        long j1111111111111114 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        j4 = j1111111111111114;
                        function11 = function11111112;
                    }
                } else {
                    if (i20 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i5 != 0) {
                        function5 = null;
                    }
                    if (i7 == 0) {
                    }
                    if ((i3 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i4 &= -57345;
                    } else {
                        jM6129getUnspecifiedMYxV2XQ = j2;
                    }
                    if (i9 != 0) {
                        fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                    }
                    if ((i3 & 32) != 0) {
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        i4 &= -3670017;
                    } else {
                        richTooltipContainerShape = shape;
                    }
                    if ((i3 & 64) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i4 = (-29360129) & i4;
                    }
                    if (i11 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    } else {
                        fM1742getLevel0D9Ej5fM = f2;
                    }
                    if (i13 != 0) {
                        long j1111111111111115 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        function11 = function5;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        j4 = j1111111111111115;
                    } else {
                        i17 = i4;
                        function10 = function9;
                        f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                        Function2 function11111113 = function5;
                        fM2085getContainerElevationD9Ej5fM = f3;
                        long j1111111111111116 = jM6129getUnspecifiedMYxV2XQ;
                        f8 = fM1742getLevel0D9Ej5fM;
                        richTooltipColors4 = richTooltipColors2;
                        j4 = j1111111111111116;
                        function11 = function11111113;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
                }
                long j1111111111111117 = j4;
                float f1119 = f7;
                Shape shape1113 = richTooltipContainerShape;
                TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f1119, shape1113, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function8 = function10;
                f4 = f8;
                function7 = function11;
                shape2 = shape1113;
                f5 = fM2085getContainerElevationD9Ej5fM;
                modifier3 = modifier4;
                richTooltipColors3 = richTooltipColors4;
                f6 = f1119;
                j3 = j1111111111111117;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                shape2 = shape;
                f4 = f2;
                modifier3 = modifier2;
                function7 = function5;
                f5 = f3;
                f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                richTooltipColors3 = richTooltipColors2;
                function8 = function6;
                j3 = j2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                    public final Object invoke(Object obj, Object obj2) {
                        return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 805306368;
        if ((i3 & 512) != 0) {
            i15 = i2 | 6;
        } else if ((i2 & 6) == 0) {
            if (composerStartRestartGroup.changedInstance(function4)) {
                i16 = 4;
            } else {
                i16 = 2;
            }
            i15 = i2 | i16;
        } else {
            i15 = i2;
        }
        if ((i4 & 306783379) == 306783378) {
            z = true;
        } else {
            z = true;
        }
        if (composerStartRestartGroup.shouldExecute(z, i4 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) != 0) {
                if (i20 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i5 != 0) {
                    function5 = null;
                }
                if (i7 == 0) {
                }
                if ((i3 & 8) != 0) {
                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                    i4 &= -57345;
                } else {
                    jM6129getUnspecifiedMYxV2XQ = j2;
                }
                if (i9 != 0) {
                    fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                }
                if ((i3 & 32) != 0) {
                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                    i4 &= -3670017;
                } else {
                    richTooltipContainerShape = shape;
                }
                if ((i3 & 64) != 0) {
                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                    i4 = (-29360129) & i4;
                }
                if (i11 != 0) {
                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                } else {
                    fM1742getLevel0D9Ej5fM = f2;
                }
                if (i13 != 0) {
                    long j1111111111111118 = jM6129getUnspecifiedMYxV2XQ;
                    f8 = fM1742getLevel0D9Ej5fM;
                    richTooltipColors4 = richTooltipColors2;
                    function11 = function5;
                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                    i17 = i4;
                    function10 = function9;
                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                    j4 = j1111111111111118;
                } else {
                    i17 = i4;
                    function10 = function9;
                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                    Function2 function11111114 = function5;
                    fM2085getContainerElevationD9Ej5fM = f3;
                    long j1111111111111119 = jM6129getUnspecifiedMYxV2XQ;
                    f8 = fM1742getLevel0D9Ej5fM;
                    richTooltipColors4 = richTooltipColors2;
                    j4 = j1111111111111119;
                    function11 = function11111114;
                }
            } else {
                if (i20 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i5 != 0) {
                    function5 = null;
                }
                if (i7 == 0) {
                }
                if ((i3 & 8) != 0) {
                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                    i4 &= -57345;
                } else {
                    jM6129getUnspecifiedMYxV2XQ = j2;
                }
                if (i9 != 0) {
                    fM1273getRichTooltipMaxWidthD9Ej5fM = TooltipDefaults.INSTANCE.m1273getRichTooltipMaxWidthD9Ej5fM();
                }
                if ((i3 & 32) != 0) {
                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                    i4 &= -3670017;
                } else {
                    richTooltipContainerShape = shape;
                }
                if ((i3 & 64) != 0) {
                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                    i4 = (-29360129) & i4;
                }
                if (i11 != 0) {
                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                } else {
                    fM1742getLevel0D9Ej5fM = f2;
                }
                if (i13 != 0) {
                    long j11111111111111110 = jM6129getUnspecifiedMYxV2XQ;
                    f8 = fM1742getLevel0D9Ej5fM;
                    richTooltipColors4 = richTooltipColors2;
                    function11 = function5;
                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                    i17 = i4;
                    function10 = function9;
                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                    j4 = j11111111111111110;
                } else {
                    i17 = i4;
                    function10 = function9;
                    f7 = fM1273getRichTooltipMaxWidthD9Ej5fM;
                    Function2 function11111115 = function5;
                    fM2085getContainerElevationD9Ej5fM = f3;
                    long j11111111111111111 = jM6129getUnspecifiedMYxV2XQ;
                    f8 = fM1742getLevel0D9Ej5fM;
                    richTooltipColors4 = richTooltipColors2;
                    j4 = j11111111111111111;
                    function11 = function11111115;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-905938553, i17, i15, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:201)");
            }
            long j11111111111111112 = j4;
            float f11110 = f7;
            Shape shape1114 = richTooltipContainerShape;
            TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function11, function10, TooltipDefaults.INSTANCE.m1270caretShapeEaSLcWc(j4), f11110, shape1114, richTooltipColors4, f8, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, i17 & 2147426302, i15 & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function8 = function10;
            f4 = f8;
            function7 = function11;
            shape2 = shape1114;
            f5 = fM2085getContainerElevationD9Ej5fM;
            modifier3 = modifier4;
            richTooltipColors3 = richTooltipColors4;
            f6 = f11110;
            j3 = j11111111111111112;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            shape2 = shape;
            f4 = f2;
            modifier3 = modifier2;
            function7 = function5;
            f5 = f3;
            f6 = fM1273getRichTooltipMaxWidthD9Ej5fM;
            richTooltipColors3 = richTooltipColors2;
            function8 = function6;
            j3 = j2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ghe
                public final Object invoke(Object obj, Object obj2) {
                    return Tooltip_androidKt.c(tooltipScope, modifier3, function7, function8, j3, f6, shape2, richTooltipColors3, f4, f5, function4, i, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(TooltipScope tooltipScope, Modifier modifier, long j, Shape shape, long j2, long j3, float f, float f2, Function2 function2, int i, int i2, Composer composer, int i3) {
        PlainTooltip(tooltipScope, modifier, j, shape, j2, j3, f, f2, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit b(TooltipScope tooltipScope, Modifier modifier, Function2 function2, Function2 function3, long j, Shape shape, RichTooltipColors richTooltipColors, float f, float f2, Function2 function4, int i, int i2, Composer composer, int i3) {
        RichTooltip(tooltipScope, modifier, function2, function3, j, shape, richTooltipColors, f, f2, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit c(TooltipScope tooltipScope, Modifier modifier, Function2 function2, Function2 function3, long j, float f, Shape shape, RichTooltipColors richTooltipColors, float f2, float f3, Function2 function4, int i, int i2, int i3, Composer composer, int i4) {
        RichTooltip(tooltipScope, modifier, function2, function3, j, f, shape, richTooltipColors, f2, f3, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    public static Unit d(TooltipScope tooltipScope, Modifier modifier, long j, float f, Shape shape, long j2, long j3, float f2, float f3, Function2 function2, int i, int i2, Composer composer, int i3) {
        PlainTooltip(tooltipScope, modifier, j, f, shape, j2, j3, f2, f3, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:103:0x011a  */
    /* JADX WARN: Code duplicated, block: B:104:0x011c  */
    /* JADX WARN: Code duplicated, block: B:107:0x0125  */
    /* JADX WARN: Code duplicated, block: B:109:0x0132  */
    /* JADX WARN: Code duplicated, block: B:126:0x0161 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:127:0x0163  */
    /* JADX WARN: Code duplicated, block: B:130:0x016a  */
    /* JADX WARN: Code duplicated, block: B:133:0x0177  */
    /* JADX WARN: Code duplicated, block: B:136:0x0184  */
    /* JADX WARN: Code duplicated, block: B:139:0x0190  */
    /* JADX WARN: Code duplicated, block: B:142:0x019c  */
    /* JADX WARN: Code duplicated, block: B:143:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:145:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:146:0x01b0  */
    /* JADX WARN: Code duplicated, block: B:149:0x01c5  */
    /* JADX WARN: Code duplicated, block: B:152:0x0218  */
    /* JADX WARN: Code duplicated, block: B:154:0x0224  */
    /* JADX WARN: Code duplicated, block: B:157:0x0235  */
    /* JADX WARN: Code duplicated, block: B:159:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:29:0x004f  */
    /* JADX WARN: Code duplicated, block: B:31:0x0053  */
    /* JADX WARN: Code duplicated, block: B:33:0x005b  */
    /* JADX WARN: Code duplicated, block: B:34:0x005e  */
    /* JADX WARN: Code duplicated, block: B:37:0x0064  */
    /* JADX WARN: Code duplicated, block: B:40:0x006a  */
    /* JADX WARN: Code duplicated, block: B:42:0x006e  */
    /* JADX WARN: Code duplicated, block: B:44:0x0076  */
    /* JADX WARN: Code duplicated, block: B:45:0x0079  */
    /* JADX WARN: Code duplicated, block: B:48:0x007f  */
    /* JADX WARN: Code duplicated, block: B:51:0x0085  */
    /* JADX WARN: Code duplicated, block: B:53:0x0089  */
    /* JADX WARN: Code duplicated, block: B:55:0x0091  */
    /* JADX WARN: Code duplicated, block: B:56:0x0094  */
    /* JADX WARN: Code duplicated, block: B:59:0x009a  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a1  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:72:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:77:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d0  */
    /* JADX WARN: Code duplicated, block: B:82:0x00da  */
    /* JADX WARN: Code duplicated, block: B:83:0x00df  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:87:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:88:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:92:0x00f8  */
    /* JADX WARN: Code duplicated, block: B:94:0x00fd  */
    /* JADX WARN: Code duplicated, block: B:96:0x0101  */
    /* JADX WARN: Code duplicated, block: B:98:0x0109  */
    /* JADX WARN: Code duplicated, block: B:99:0x010c  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with maxWidth parameter.")
    public static final /* synthetic */ void PlainTooltip(final TooltipScope tooltipScope, Modifier modifier, long j, Shape shape, long j2, long j3, float f, float f2, final Function2 function2, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        long jM6129getUnspecifiedMYxV2XQ;
        Shape plainTooltipContainerShape;
        long plainTooltipContentColor;
        long plainTooltipContainerColor;
        int i4;
        float f3;
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z;
        final float f4;
        final long j4;
        final long j5;
        final float f5;
        final Modifier modifier3;
        final Shape shape2;
        final long j6;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        float fM6022constructorimpl;
        int i9;
        long j7;
        Shape shape3;
        long j8;
        float fM6022constructorimpl2;
        float f6;
        Modifier modifier4;
        long j9;
        long j10;
        int i10;
        Composer composerStartRestartGroup = composer.startRestartGroup(2114904198);
        if ((Integer.MIN_VALUE & i2) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = ((i & 8) == 0 ? composerStartRestartGroup.changed(tooltipScope) : composerStartRestartGroup.changedInstance(tooltipScope) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i11 = i2 & 1;
        if (i11 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                if ((i2 & 2) == 0) {
                    jM6129getUnspecifiedMYxV2XQ = j;
                    int i12 = composerStartRestartGroup.changed(jM6129getUnspecifiedMYxV2XQ) ? 256 : 128;
                    i3 |= i12;
                } else {
                    jM6129getUnspecifiedMYxV2XQ = j;
                }
                i3 |= i12;
            } else {
                jM6129getUnspecifiedMYxV2XQ = j;
            }
            if ((i & 3072) == 0) {
                if ((i2 & 4) == 0) {
                    plainTooltipContainerShape = shape;
                    int i13 = composerStartRestartGroup.changed(plainTooltipContainerShape) ? 2048 : 1024;
                    i3 |= i13;
                } else {
                    plainTooltipContainerShape = shape;
                }
                i3 |= i13;
            } else {
                plainTooltipContainerShape = shape;
            }
            if ((i & 24576) == 0) {
                if ((i2 & 8) == 0) {
                    plainTooltipContentColor = j2;
                    int i14 = composerStartRestartGroup.changed(plainTooltipContentColor) ? 16384 : 8192;
                    i3 |= i14;
                } else {
                    plainTooltipContentColor = j2;
                }
                i3 |= i14;
            } else {
                plainTooltipContentColor = j2;
            }
            if ((196608 & i) == 0) {
                plainTooltipContainerColor = j3;
                if ((i2 & 16) == 0 || !composerStartRestartGroup.changed(plainTooltipContainerColor)) {
                    i10 = 65536;
                } else {
                    i10 = 131072;
                }
                i3 |= i10;
            } else {
                plainTooltipContainerColor = j3;
            }
            i4 = i2 & 32;
            if (i4 != 0) {
                i3 |= 1572864;
                f3 = f;
            } else {
                f3 = f;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(f3)) {
                        i5 = 1048576;
                    } else {
                        i5 = 524288;
                    }
                    i3 |= i5;
                }
            }
            i6 = i2 & 64;
            if (i6 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(f2)) {
                    i7 = 8388608;
                } else {
                    i7 = 4194304;
                }
                i3 |= i7;
            }
            if ((i2 & 128) != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i8 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i8 = 33554432;
                    }
                    i3 |= i8;
                }
                if ((i3 & 38347923) != 38347922) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                        composerStartRestartGroup.skipToGroupEnd();
                        if ((i2 & 2) != 0) {
                            i3 &= -897;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -7169;
                        }
                        if ((i2 & 8) != 0) {
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                        }
                        i9 = i3;
                        modifier4 = modifier2;
                        shape3 = plainTooltipContainerShape;
                        j10 = plainTooltipContentColor;
                        fM6022constructorimpl2 = f2;
                        f6 = f3;
                    } else {
                        if (i11 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if ((i2 & 2) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -897;
                        }
                        if ((i2 & 4) != 0) {
                            i3 &= -7169;
                            plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 8) != 0) {
                            plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                            i3 &= -458753;
                        }
                        if (i4 != 0) {
                            fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                        } else {
                            fM6022constructorimpl = f3;
                        }
                        if (i6 != 0) {
                            shape3 = plainTooltipContainerShape;
                            j10 = plainTooltipContentColor;
                            fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                            f6 = fM6022constructorimpl;
                            i9 = i3;
                            modifier4 = modifier2;
                        } else {
                            i9 = i3;
                            j7 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = plainTooltipContainerShape;
                            j8 = plainTooltipContainerColor;
                            long j11 = plainTooltipContentColor;
                            fM6022constructorimpl2 = f2;
                            f6 = fM6022constructorimpl;
                            modifier4 = modifier2;
                            j9 = j11;
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(2114904198, i9, -1, "androidx.compose.material3.PlainTooltipAndroid (Tooltip.android.kt:61)");
                        }
                        TooltipDefaults tooltipDefaults = TooltipDefaults.INSTANCE;
                        long j12 = j7;
                        Shape shapeM1270caretShapeEaSLcWc = tooltipDefaults.m1270caretShapeEaSLcWc(j12);
                        float fM1272getPlainTooltipMaxWidthD9Ej5fM = tooltipDefaults.m1272getPlainTooltipMaxWidthD9Ej5fM();
                        int i15 = (i9 & 14) | 3072 | (i9 & 112);
                        int i16 = i9 << 3;
                        TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, modifier4, shapeM1270caretShapeEaSLcWc, fM1272getPlainTooltipMaxWidthD9Ej5fM, shape3, j9, j8, f6, fM6022constructorimpl2, function2, composerStartRestartGroup, (i16 & 1879048192) | i15 | (i16 & 57344) | (i16 & 458752) | (i16 & 3670016) | (i16 & 29360128) | (i16 & 234881024), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        f4 = fM6022constructorimpl2;
                        f5 = f6;
                        j6 = j8;
                        j5 = j9;
                        shape2 = shape3;
                        j4 = j12;
                    }
                    j7 = jM6129getUnspecifiedMYxV2XQ;
                    j9 = j10;
                    j8 = plainTooltipContainerColor;
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(2114904198, i9, -1, "androidx.compose.material3.PlainTooltipAndroid (Tooltip.android.kt:61)");
                    }
                    TooltipDefaults tooltipDefaults2 = TooltipDefaults.INSTANCE;
                    long j13 = j7;
                    Shape shapeM1270caretShapeEaSLcWc2 = tooltipDefaults2.m1270caretShapeEaSLcWc(j13);
                    float fM1272getPlainTooltipMaxWidthD9Ej5fM2 = tooltipDefaults2.m1272getPlainTooltipMaxWidthD9Ej5fM();
                    int i17 = (i9 & 14) | 3072 | (i9 & 112);
                    int i18 = i9 << 3;
                    TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, modifier4, shapeM1270caretShapeEaSLcWc2, fM1272getPlainTooltipMaxWidthD9Ej5fM2, shape3, j9, j8, f6, fM6022constructorimpl2, function2, composerStartRestartGroup, (i18 & 1879048192) | i17 | (i18 & 57344) | (i18 & 458752) | (i18 & 3670016) | (i18 & 29360128) | (i18 & 234881024), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    f4 = fM6022constructorimpl2;
                    f5 = f6;
                    j6 = j8;
                    j5 = j9;
                    shape2 = shape3;
                    j4 = j13;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    f4 = f2;
                    j4 = jM6129getUnspecifiedMYxV2XQ;
                    j5 = plainTooltipContentColor;
                    f5 = f3;
                    modifier3 = modifier2;
                    shape2 = plainTooltipContainerShape;
                    j6 = plainTooltipContainerColor;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ihe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.a(tooltipScope, modifier3, j4, shape2, j5, j6, f5, f4, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i3 & 38347923) != 38347922) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 2) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i3 &= -897;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -7169;
                        plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 8) != 0) {
                        plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    }
                    if ((i2 & 16) != 0) {
                        plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    }
                    if (i4 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f3;
                    }
                    if (i6 != 0) {
                        shape3 = plainTooltipContainerShape;
                        j10 = plainTooltipContentColor;
                        fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                        f6 = fM6022constructorimpl;
                        i9 = i3;
                        modifier4 = modifier2;
                        j7 = jM6129getUnspecifiedMYxV2XQ;
                        j9 = j10;
                        j8 = plainTooltipContainerColor;
                    } else {
                        i9 = i3;
                        j7 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = plainTooltipContainerShape;
                        j8 = plainTooltipContainerColor;
                        long j14 = plainTooltipContentColor;
                        fM6022constructorimpl2 = f2;
                        f6 = fM6022constructorimpl;
                        modifier4 = modifier2;
                        j9 = j14;
                    }
                } else {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 2) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i3 &= -897;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -7169;
                        plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 8) != 0) {
                        plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    }
                    if ((i2 & 16) != 0) {
                        plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    }
                    if (i4 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f3;
                    }
                    if (i6 != 0) {
                        shape3 = plainTooltipContainerShape;
                        j10 = plainTooltipContentColor;
                        fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                        f6 = fM6022constructorimpl;
                        i9 = i3;
                        modifier4 = modifier2;
                        j7 = jM6129getUnspecifiedMYxV2XQ;
                        j9 = j10;
                        j8 = plainTooltipContainerColor;
                    } else {
                        i9 = i3;
                        j7 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = plainTooltipContainerShape;
                        j8 = plainTooltipContainerColor;
                        long j15 = plainTooltipContentColor;
                        fM6022constructorimpl2 = f2;
                        f6 = fM6022constructorimpl;
                        modifier4 = modifier2;
                        j9 = j15;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2114904198, i9, -1, "androidx.compose.material3.PlainTooltipAndroid (Tooltip.android.kt:61)");
                }
                TooltipDefaults tooltipDefaults3 = TooltipDefaults.INSTANCE;
                long j16 = j7;
                Shape shapeM1270caretShapeEaSLcWc3 = tooltipDefaults3.m1270caretShapeEaSLcWc(j16);
                float fM1272getPlainTooltipMaxWidthD9Ej5fM3 = tooltipDefaults3.m1272getPlainTooltipMaxWidthD9Ej5fM();
                int i19 = (i9 & 14) | 3072 | (i9 & 112);
                int i110 = i9 << 3;
                TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, modifier4, shapeM1270caretShapeEaSLcWc3, fM1272getPlainTooltipMaxWidthD9Ej5fM3, shape3, j9, j8, f6, fM6022constructorimpl2, function2, composerStartRestartGroup, (i110 & 1879048192) | i19 | (i110 & 57344) | (i110 & 458752) | (i110 & 3670016) | (i110 & 29360128) | (i110 & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                f4 = fM6022constructorimpl2;
                f5 = f6;
                j6 = j8;
                j5 = j9;
                shape2 = shape3;
                j4 = j16;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                f4 = f2;
                j4 = jM6129getUnspecifiedMYxV2XQ;
                j5 = plainTooltipContentColor;
                f5 = f3;
                modifier3 = modifier2;
                shape2 = plainTooltipContainerShape;
                j6 = plainTooltipContainerColor;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ihe
                    public final Object invoke(Object obj, Object obj2) {
                        return Tooltip_androidKt.a(tooltipScope, modifier3, j4, shape2, j5, j6, f5, f4, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            if ((i2 & 2) == 0) {
                jM6129getUnspecifiedMYxV2XQ = j;
                if (composerStartRestartGroup.changed(jM6129getUnspecifiedMYxV2XQ)) {
                }
                i3 |= i12;
            } else {
                jM6129getUnspecifiedMYxV2XQ = j;
            }
            i3 |= i12;
        } else {
            jM6129getUnspecifiedMYxV2XQ = j;
        }
        if ((i & 3072) == 0) {
            if ((i2 & 4) == 0) {
                plainTooltipContainerShape = shape;
                if (composerStartRestartGroup.changed(plainTooltipContainerShape)) {
                }
                i3 |= i13;
            } else {
                plainTooltipContainerShape = shape;
            }
            i3 |= i13;
        } else {
            plainTooltipContainerShape = shape;
        }
        if ((i & 24576) == 0) {
            if ((i2 & 8) == 0) {
                plainTooltipContentColor = j2;
                if (composerStartRestartGroup.changed(plainTooltipContentColor)) {
                }
                i3 |= i14;
            } else {
                plainTooltipContentColor = j2;
            }
            i3 |= i14;
        } else {
            plainTooltipContentColor = j2;
        }
        if ((196608 & i) == 0) {
            plainTooltipContainerColor = j3;
            if ((i2 & 16) == 0) {
                i10 = 65536;
            } else {
                i10 = 65536;
            }
            i3 |= i10;
        } else {
            plainTooltipContainerColor = j3;
        }
        i4 = i2 & 32;
        if (i4 != 0) {
            i3 |= 1572864;
            f3 = f;
        } else {
            f3 = f;
            if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changed(f3)) {
                    i5 = 1048576;
                } else {
                    i5 = 524288;
                }
                i3 |= i5;
            }
        }
        i6 = i2 & 64;
        if (i6 != 0) {
            i3 |= 12582912;
        } else if ((i & 12582912) == 0) {
            if (composerStartRestartGroup.changed(f2)) {
                i7 = 8388608;
            } else {
                i7 = 4194304;
            }
            i3 |= i7;
        }
        if ((i2 & 128) != 0) {
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i8 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i8 = 33554432;
                }
                i3 |= i8;
            }
            if ((i3 & 38347923) != 38347922) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 2) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i3 &= -897;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -7169;
                        plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 8) != 0) {
                        plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    }
                    if ((i2 & 16) != 0) {
                        plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    }
                    if (i4 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f3;
                    }
                    if (i6 != 0) {
                        shape3 = plainTooltipContainerShape;
                        j10 = plainTooltipContentColor;
                        fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                        f6 = fM6022constructorimpl;
                        i9 = i3;
                        modifier4 = modifier2;
                        j7 = jM6129getUnspecifiedMYxV2XQ;
                        j9 = j10;
                        j8 = plainTooltipContainerColor;
                    } else {
                        i9 = i3;
                        j7 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = plainTooltipContainerShape;
                        j8 = plainTooltipContainerColor;
                        long j17 = plainTooltipContentColor;
                        fM6022constructorimpl2 = f2;
                        f6 = fM6022constructorimpl;
                        modifier4 = modifier2;
                        j9 = j17;
                    }
                } else {
                    if (i11 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if ((i2 & 2) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i3 &= -897;
                    }
                    if ((i2 & 4) != 0) {
                        i3 &= -7169;
                        plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 8) != 0) {
                        plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                        i3 &= -57345;
                    }
                    if ((i2 & 16) != 0) {
                        plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                        i3 &= -458753;
                    }
                    if (i4 != 0) {
                        fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                    } else {
                        fM6022constructorimpl = f3;
                    }
                    if (i6 != 0) {
                        shape3 = plainTooltipContainerShape;
                        j10 = plainTooltipContentColor;
                        fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                        f6 = fM6022constructorimpl;
                        i9 = i3;
                        modifier4 = modifier2;
                        j7 = jM6129getUnspecifiedMYxV2XQ;
                        j9 = j10;
                        j8 = plainTooltipContainerColor;
                    } else {
                        i9 = i3;
                        j7 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = plainTooltipContainerShape;
                        j8 = plainTooltipContainerColor;
                        long j18 = plainTooltipContentColor;
                        fM6022constructorimpl2 = f2;
                        f6 = fM6022constructorimpl;
                        modifier4 = modifier2;
                        j9 = j18;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2114904198, i9, -1, "androidx.compose.material3.PlainTooltipAndroid (Tooltip.android.kt:61)");
                }
                TooltipDefaults tooltipDefaults4 = TooltipDefaults.INSTANCE;
                long j19 = j7;
                Shape shapeM1270caretShapeEaSLcWc4 = tooltipDefaults4.m1270caretShapeEaSLcWc(j19);
                float fM1272getPlainTooltipMaxWidthD9Ej5fM4 = tooltipDefaults4.m1272getPlainTooltipMaxWidthD9Ej5fM();
                int i111 = (i9 & 14) | 3072 | (i9 & 112);
                int i112 = i9 << 3;
                TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, modifier4, shapeM1270caretShapeEaSLcWc4, fM1272getPlainTooltipMaxWidthD9Ej5fM4, shape3, j9, j8, f6, fM6022constructorimpl2, function2, composerStartRestartGroup, (i112 & 1879048192) | i111 | (i112 & 57344) | (i112 & 458752) | (i112 & 3670016) | (i112 & 29360128) | (i112 & 234881024), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                f4 = fM6022constructorimpl2;
                f5 = f6;
                j6 = j8;
                j5 = j9;
                shape2 = shape3;
                j4 = j19;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                f4 = f2;
                j4 = jM6129getUnspecifiedMYxV2XQ;
                j5 = plainTooltipContentColor;
                f5 = f3;
                modifier3 = modifier2;
                shape2 = plainTooltipContainerShape;
                j6 = plainTooltipContainerColor;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ihe
                    public final Object invoke(Object obj, Object obj2) {
                        return Tooltip_androidKt.a(tooltipScope, modifier3, j4, shape2, j5, j6, f5, f4, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 100663296;
        if ((i3 & 38347923) != 38347922) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0) {
                if (i11 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 2) != 0) {
                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                    i3 &= -897;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -7169;
                    plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 8) != 0) {
                    plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                    i3 &= -57345;
                }
                if ((i2 & 16) != 0) {
                    plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                    i3 &= -458753;
                }
                if (i4 != 0) {
                    fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                } else {
                    fM6022constructorimpl = f3;
                }
                if (i6 != 0) {
                    shape3 = plainTooltipContainerShape;
                    j10 = plainTooltipContentColor;
                    fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                    f6 = fM6022constructorimpl;
                    i9 = i3;
                    modifier4 = modifier2;
                    j7 = jM6129getUnspecifiedMYxV2XQ;
                    j9 = j10;
                    j8 = plainTooltipContainerColor;
                } else {
                    i9 = i3;
                    j7 = jM6129getUnspecifiedMYxV2XQ;
                    shape3 = plainTooltipContainerShape;
                    j8 = plainTooltipContainerColor;
                    long j110 = plainTooltipContentColor;
                    fM6022constructorimpl2 = f2;
                    f6 = fM6022constructorimpl;
                    modifier4 = modifier2;
                    j9 = j110;
                }
            } else {
                if (i11 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i2 & 2) != 0) {
                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                    i3 &= -897;
                }
                if ((i2 & 4) != 0) {
                    i3 &= -7169;
                    plainTooltipContainerShape = TooltipDefaults.INSTANCE.getPlainTooltipContainerShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 8) != 0) {
                    plainTooltipContentColor = TooltipDefaults.INSTANCE.getPlainTooltipContentColor(composerStartRestartGroup, 6);
                    i3 &= -57345;
                }
                if ((i2 & 16) != 0) {
                    plainTooltipContainerColor = TooltipDefaults.INSTANCE.getPlainTooltipContainerColor(composerStartRestartGroup, 6);
                    i3 &= -458753;
                }
                if (i4 != 0) {
                    fM6022constructorimpl = Dp.m6022constructorimpl(0.0f);
                } else {
                    fM6022constructorimpl = f3;
                }
                if (i6 != 0) {
                    shape3 = plainTooltipContainerShape;
                    j10 = plainTooltipContentColor;
                    fM6022constructorimpl2 = Dp.m6022constructorimpl(0.0f);
                    f6 = fM6022constructorimpl;
                    i9 = i3;
                    modifier4 = modifier2;
                    j7 = jM6129getUnspecifiedMYxV2XQ;
                    j9 = j10;
                    j8 = plainTooltipContainerColor;
                } else {
                    i9 = i3;
                    j7 = jM6129getUnspecifiedMYxV2XQ;
                    shape3 = plainTooltipContainerShape;
                    j8 = plainTooltipContainerColor;
                    long j111 = plainTooltipContentColor;
                    fM6022constructorimpl2 = f2;
                    f6 = fM6022constructorimpl;
                    modifier4 = modifier2;
                    j9 = j111;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2114904198, i9, -1, "androidx.compose.material3.PlainTooltipAndroid (Tooltip.android.kt:61)");
            }
            TooltipDefaults tooltipDefaults5 = TooltipDefaults.INSTANCE;
            long j112 = j7;
            Shape shapeM1270caretShapeEaSLcWc5 = tooltipDefaults5.m1270caretShapeEaSLcWc(j112);
            float fM1272getPlainTooltipMaxWidthD9Ej5fM5 = tooltipDefaults5.m1272getPlainTooltipMaxWidthD9Ej5fM();
            int i113 = (i9 & 14) | 3072 | (i9 & 112);
            int i114 = i9 << 3;
            TooltipKt.m1279PlainTooltipgv3ox5I(tooltipScope, modifier4, shapeM1270caretShapeEaSLcWc5, fM1272getPlainTooltipMaxWidthD9Ej5fM5, shape3, j9, j8, f6, fM6022constructorimpl2, function2, composerStartRestartGroup, (i114 & 1879048192) | i113 | (i114 & 57344) | (i114 & 458752) | (i114 & 3670016) | (i114 & 29360128) | (i114 & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            f4 = fM6022constructorimpl2;
            f5 = f6;
            j6 = j8;
            j5 = j9;
            shape2 = shape3;
            j4 = j112;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            f4 = f2;
            j4 = jM6129getUnspecifiedMYxV2XQ;
            j5 = plainTooltipContentColor;
            f5 = f3;
            modifier3 = modifier2;
            shape2 = plainTooltipContainerShape;
            j6 = plainTooltipContainerColor;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ihe
                public final Object invoke(Object obj, Object obj2) {
                    return Tooltip_androidKt.a(tooltipScope, modifier3, j4, shape2, j5, j6, f5, f4, function2, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:103:0x011e  */
    /* JADX WARN: Code duplicated, block: B:105:0x0123  */
    /* JADX WARN: Code duplicated, block: B:107:0x0127  */
    /* JADX WARN: Code duplicated, block: B:109:0x012f  */
    /* JADX WARN: Code duplicated, block: B:110:0x0132  */
    /* JADX WARN: Code duplicated, block: B:114:0x0140  */
    /* JADX WARN: Code duplicated, block: B:115:0x0142  */
    /* JADX WARN: Code duplicated, block: B:118:0x014b  */
    /* JADX WARN: Code duplicated, block: B:120:0x015b  */
    /* JADX WARN: Code duplicated, block: B:132:0x0176 A[PHI: r2 r3 r5 r7 r9 r10 r12 r14
      0x0176: PHI (r2v10 float) = (r2v5 float), (r2v2 float), (r2v2 float) binds: [B:151:0x01bd, B:130:0x0173, B:131:0x0175] A[DONT_GENERATE, DONT_INLINE]
      0x0176: PHI (r3v40 int) = (r3v33 int), (r3v43 int), (r3v44 int) binds: [B:151:0x01bd, B:130:0x0173, B:131:0x0175] A[DONT_GENERATE, DONT_INLINE]
      0x0176: PHI (r5v9 androidx.compose.ui.Modifier) = (r5v5 androidx.compose.ui.Modifier), (r5v2 androidx.compose.ui.Modifier), (r5v2 androidx.compose.ui.Modifier) binds: [B:151:0x01bd, B:130:0x0173, B:131:0x0175] A[DONT_GENERATE, DONT_INLINE]
      0x0176: PHI (r7v10 kotlin.jvm.functions.Function2) = (r7v5 kotlin.jvm.functions.Function2), (r7v2 kotlin.jvm.functions.Function2), (r7v2 kotlin.jvm.functions.Function2) binds: [B:151:0x01bd, B:130:0x0173, B:131:0x0175] A[DONT_GENERATE, DONT_INLINE]
      0x0176: PHI (r9v10 kotlin.jvm.functions.Function2) = (r9v5 kotlin.jvm.functions.Function2), (r9v2 kotlin.jvm.functions.Function2), (r9v2 kotlin.jvm.functions.Function2) binds: [B:151:0x01bd, B:130:0x0173, B:131:0x0175] A[DONT_GENERATE, DONT_INLINE]
      0x0176: PHI (r10v26 androidx.compose.material3.RichTooltipColors) = 
      (r10v10 androidx.compose.material3.RichTooltipColors)
      (r10v7 androidx.compose.material3.RichTooltipColors)
      (r10v7 androidx.compose.material3.RichTooltipColors)
     binds: [B:151:0x01bd, B:130:0x0173, B:131:0x0175] A[DONT_GENERATE, DONT_INLINE]
      0x0176: PHI (r12v8 long) = (r12v3 long), (r12v1 long), (r12v1 long) binds: [B:151:0x01bd, B:130:0x0173, B:131:0x0175] A[DONT_GENERATE, DONT_INLINE]
      0x0176: PHI (r14v12 androidx.compose.ui.graphics.Shape) = 
      (r14v4 androidx.compose.ui.graphics.Shape)
      (r14v2 androidx.compose.ui.graphics.Shape)
      (r14v2 androidx.compose.ui.graphics.Shape)
     binds: [B:151:0x01bd, B:130:0x0173, B:131:0x0175] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:133:0x0181 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:134:0x0183  */
    /* JADX WARN: Code duplicated, block: B:137:0x0189  */
    /* JADX WARN: Code duplicated, block: B:139:0x018c  */
    /* JADX WARN: Code duplicated, block: B:142:0x0191  */
    /* JADX WARN: Code duplicated, block: B:145:0x019e  */
    /* JADX WARN: Code duplicated, block: B:148:0x01ab  */
    /* JADX WARN: Code duplicated, block: B:150:0x01b6  */
    /* JADX WARN: Code duplicated, block: B:152:0x01bf  */
    /* JADX WARN: Code duplicated, block: B:155:0x01d8  */
    /* JADX WARN: Code duplicated, block: B:158:0x0228  */
    /* JADX WARN: Code duplicated, block: B:160:0x0235  */
    /* JADX WARN: Code duplicated, block: B:163:0x0247  */
    /* JADX WARN: Code duplicated, block: B:165:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:29:0x004f  */
    /* JADX WARN: Code duplicated, block: B:31:0x0054  */
    /* JADX WARN: Code duplicated, block: B:33:0x0058  */
    /* JADX WARN: Code duplicated, block: B:35:0x0060  */
    /* JADX WARN: Code duplicated, block: B:36:0x0063  */
    /* JADX WARN: Code duplicated, block: B:40:0x006a  */
    /* JADX WARN: Code duplicated, block: B:42:0x006f  */
    /* JADX WARN: Code duplicated, block: B:44:0x0073  */
    /* JADX WARN: Code duplicated, block: B:46:0x007b  */
    /* JADX WARN: Code duplicated, block: B:47:0x007e  */
    /* JADX WARN: Code duplicated, block: B:51:0x0085  */
    /* JADX WARN: Code duplicated, block: B:53:0x008b  */
    /* JADX WARN: Code duplicated, block: B:56:0x0094  */
    /* JADX WARN: Code duplicated, block: B:58:0x0098  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:63:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:65:0x00ac  */
    /* JADX WARN: Code duplicated, block: B:66:0x00af  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b6  */
    /* JADX WARN: Code duplicated, block: B:72:0x00be  */
    /* JADX WARN: Code duplicated, block: B:74:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:77:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:82:0x00de  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:85:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:87:0x00ef  */
    /* JADX WARN: Code duplicated, block: B:88:0x00f2  */
    /* JADX WARN: Code duplicated, block: B:92:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:94:0x0103  */
    /* JADX WARN: Code duplicated, block: B:96:0x0107  */
    /* JADX WARN: Code duplicated, block: B:98:0x0111  */
    /* JADX WARN: Code duplicated, block: B:99:0x0114  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use overload with maxWidth parameter.")
    public static final /* synthetic */ void RichTooltip(final TooltipScope tooltipScope, Modifier modifier, Function2 function2, Function2 function3, long j, Shape shape, RichTooltipColors richTooltipColors, float f, float f2, final Function2 function4, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        Function2 function5;
        int i5;
        int i6;
        Function2 function6;
        int i7;
        long jM6129getUnspecifiedMYxV2XQ;
        Shape richTooltipContainerShape;
        RichTooltipColors richTooltipColors2;
        int i8;
        float fM1742getLevel0D9Ej5fM;
        int i9;
        int i10;
        int i11;
        int i12;
        boolean z;
        final Function2 function7;
        final Function2 function8;
        final RichTooltipColors richTooltipColors3;
        final Shape shape2;
        final float f3;
        final float f4;
        final Modifier modifier3;
        final long j2;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        float fM2085getContainerElevationD9Ej5fM;
        int i13;
        Function2 function9;
        float f5;
        Modifier modifier4;
        Function2 function10;
        RichTooltipColors richTooltipColors4;
        long j3;
        Shape shape3;
        int i14;
        int i15;
        Composer composerStartRestartGroup = composer.startRestartGroup(-244908363);
        if ((Integer.MIN_VALUE & i2) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = ((i & 8) == 0 ? composerStartRestartGroup.changed(tooltipScope) : composerStartRestartGroup.changedInstance(tooltipScope) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i16 = i2 & 1;
        if (i16 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i4 = i2 & 2;
            if (i4 != 0) {
                if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                    function5 = function2;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i5 = 256;
                    } else {
                        i5 = 128;
                    }
                    i3 |= i5;
                }
                i6 = i2 & 4;
                if (i6 != 0) {
                    if ((i & 3072) == 0) {
                        function6 = function3;
                        if (composerStartRestartGroup.changedInstance(function6)) {
                            i7 = 2048;
                        } else {
                            i7 = 1024;
                        }
                        i3 |= i7;
                    }
                    if ((i & 24576) == 0) {
                        jM6129getUnspecifiedMYxV2XQ = j;
                        if ((i2 & 8) == 0 || !composerStartRestartGroup.changed(jM6129getUnspecifiedMYxV2XQ)) {
                            i15 = 8192;
                        } else {
                            i15 = 16384;
                        }
                        i3 |= i15;
                    } else {
                        jM6129getUnspecifiedMYxV2XQ = j;
                    }
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if ((i2 & 16) == 0) {
                            richTooltipContainerShape = shape;
                            int i17 = composerStartRestartGroup.changed(richTooltipContainerShape) ? 131072 : 65536;
                            i3 |= i17;
                        } else {
                            richTooltipContainerShape = shape;
                        }
                        i3 |= i17;
                    } else {
                        richTooltipContainerShape = shape;
                    }
                    if ((i & 1572864) == 0) {
                        richTooltipColors2 = richTooltipColors;
                        if ((i2 & 32) == 0 || !composerStartRestartGroup.changed(richTooltipColors2)) {
                            i14 = 524288;
                        } else {
                            i14 = 1048576;
                        }
                        i3 |= i14;
                    } else {
                        richTooltipColors2 = richTooltipColors;
                    }
                    i8 = i2 & 64;
                    if (i8 != 0) {
                        i3 |= 12582912;
                        fM1742getLevel0D9Ej5fM = f;
                    } else {
                        fM1742getLevel0D9Ej5fM = f;
                        if ((i & 12582912) == 0) {
                            if (composerStartRestartGroup.changed(fM1742getLevel0D9Ej5fM)) {
                                i9 = 8388608;
                            } else {
                                i9 = 4194304;
                            }
                            i3 |= i9;
                        }
                    }
                    i10 = i2 & 128;
                    if (i10 != 0) {
                        if ((i & 100663296) == 0) {
                            if (composerStartRestartGroup.changed(f2)) {
                                i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                            } else {
                                i11 = 33554432;
                            }
                            i3 |= i11;
                        }
                        if ((i2 & 256) != 0) {
                            if ((i & 805306368) == 0) {
                                if (composerStartRestartGroup.changedInstance(function4)) {
                                    i12 = 536870912;
                                } else {
                                    i12 = 268435456;
                                }
                                i3 |= i12;
                            }
                            if ((i3 & 306783379) != 306783378) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                                composerStartRestartGroup.startDefaults();
                                if ((i & 1) == 0 && !composerStartRestartGroup.getDefaultsInvalid()) {
                                    composerStartRestartGroup.skipToGroupEnd();
                                    if ((i2 & 8) != 0) {
                                        i3 &= -57345;
                                    }
                                    if ((i2 & 16) != 0) {
                                        i3 &= -458753;
                                    }
                                    if ((i2 & 32) != 0) {
                                        i3 &= -3670017;
                                    }
                                } else {
                                    if (i16 != 0) {
                                        modifier2 = Modifier.INSTANCE;
                                    }
                                    if (i4 != 0) {
                                        function5 = null;
                                    }
                                    if (i6 != 0) {
                                        function6 = null;
                                    }
                                    if ((i2 & 8) != 0) {
                                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                        i3 &= -57345;
                                    }
                                    if ((i2 & 16) != 0) {
                                        i3 &= -458753;
                                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                    }
                                    if ((i2 & 32) != 0) {
                                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                        i3 = (-3670017) & i3;
                                    }
                                    if (i8 != 0) {
                                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                    }
                                    if (i10 != 0) {
                                        Function2 function11 = function6;
                                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                        i13 = i3;
                                        function9 = function11;
                                        f5 = fM1742getLevel0D9Ej5fM;
                                        modifier4 = modifier2;
                                        function10 = function5;
                                        richTooltipColors4 = richTooltipColors2;
                                        j3 = jM6129getUnspecifiedMYxV2XQ;
                                        shape3 = richTooltipContainerShape;
                                    }
                                    composerStartRestartGroup.endDefaults();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                                    }
                                    TooltipDefaults tooltipDefaults = TooltipDefaults.INSTANCE;
                                    long j4 = j3;
                                    int i18 = i13 << 3;
                                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults.m1270caretShapeEaSLcWc(j4), tooltipDefaults.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i18 & 3670016) | (i18 & 29360128) | (i18 & 234881024) | (i18 & 1879048192), (i13 >> 27) & 14, 0);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                    }
                                    function8 = function9;
                                    f3 = fM2085getContainerElevationD9Ej5fM;
                                    function7 = function10;
                                    f4 = f5;
                                    modifier3 = modifier4;
                                    richTooltipColors3 = richTooltipColors4;
                                    shape2 = shape3;
                                    j2 = j4;
                                }
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                                composerStartRestartGroup.endDefaults();
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                                }
                                TooltipDefaults tooltipDefaults2 = TooltipDefaults.INSTANCE;
                                long j5 = j3;
                                int i19 = i13 << 3;
                                TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults2.m1270caretShapeEaSLcWc(j5), tooltipDefaults2.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i19 & 3670016) | (i19 & 29360128) | (i19 & 234881024) | (i19 & 1879048192), (i13 >> 27) & 14, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                function8 = function9;
                                f3 = fM2085getContainerElevationD9Ej5fM;
                                function7 = function10;
                                f4 = f5;
                                modifier3 = modifier4;
                                richTooltipColors3 = richTooltipColors4;
                                shape2 = shape3;
                                j2 = j5;
                            } else {
                                composerStartRestartGroup.skipToGroupEnd();
                                function7 = function5;
                                function8 = function6;
                                richTooltipColors3 = richTooltipColors2;
                                shape2 = richTooltipContainerShape;
                                f3 = f2;
                                f4 = fM1742getLevel0D9Ej5fM;
                                modifier3 = modifier2;
                                j2 = jM6129getUnspecifiedMYxV2XQ;
                            }
                            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                                    public final Object invoke(Object obj, Object obj2) {
                                        return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i3 |= 805306368;
                        if ((i3 & 306783379) != 306783378) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) == 0) {
                                if (i16 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    function5 = null;
                                }
                                if (i6 != 0) {
                                    function6 = null;
                                }
                                if ((i2 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i3 &= -57345;
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -458753;
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                }
                                if ((i2 & 32) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i3 = (-3670017) & i3;
                                }
                                if (i8 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                }
                                if (i10 != 0) {
                                    Function2 function12 = function6;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i13 = i3;
                                    function9 = function12;
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                } else {
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    i13 = i3;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    function9 = function6;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                    fM2085getContainerElevationD9Ej5fM = f2;
                                }
                            } else {
                                if (i16 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    function5 = null;
                                }
                                if (i6 != 0) {
                                    function6 = null;
                                }
                                if ((i2 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i3 &= -57345;
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -458753;
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                }
                                if ((i2 & 32) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i3 = (-3670017) & i3;
                                }
                                if (i8 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                }
                                if (i10 != 0) {
                                    Function2 function13 = function6;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i13 = i3;
                                    function9 = function13;
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                } else {
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    i13 = i3;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    function9 = function6;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                    fM2085getContainerElevationD9Ej5fM = f2;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                            }
                            TooltipDefaults tooltipDefaults3 = TooltipDefaults.INSTANCE;
                            long j6 = j3;
                            int i110 = i13 << 3;
                            TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults3.m1270caretShapeEaSLcWc(j6), tooltipDefaults3.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i110 & 3670016) | (i110 & 29360128) | (i110 & 234881024) | (i110 & 1879048192), (i13 >> 27) & 14, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function8 = function9;
                            f3 = fM2085getContainerElevationD9Ej5fM;
                            function7 = function10;
                            f4 = f5;
                            modifier3 = modifier4;
                            richTooltipColors3 = richTooltipColors4;
                            shape2 = shape3;
                            j2 = j6;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            function7 = function5;
                            function8 = function6;
                            richTooltipColors3 = richTooltipColors2;
                            shape2 = richTooltipContainerShape;
                            f3 = f2;
                            f4 = fM1742getLevel0D9Ej5fM;
                            modifier3 = modifier2;
                            j2 = jM6129getUnspecifiedMYxV2XQ;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                                public final Object invoke(Object obj, Object obj2) {
                                    return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 100663296;
                    if ((i2 & 256) != 0) {
                        if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i12 = 536870912;
                            } else {
                                i12 = 268435456;
                            }
                            i3 |= i12;
                        }
                        if ((i3 & 306783379) != 306783378) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) == 0) {
                                if (i16 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    function5 = null;
                                }
                                if (i6 != 0) {
                                    function6 = null;
                                }
                                if ((i2 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i3 &= -57345;
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -458753;
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                }
                                if ((i2 & 32) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i3 = (-3670017) & i3;
                                }
                                if (i8 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                }
                                if (i10 != 0) {
                                    Function2 function14 = function6;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i13 = i3;
                                    function9 = function14;
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                } else {
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    i13 = i3;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    function9 = function6;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                    fM2085getContainerElevationD9Ej5fM = f2;
                                }
                            } else {
                                if (i16 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    function5 = null;
                                }
                                if (i6 != 0) {
                                    function6 = null;
                                }
                                if ((i2 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i3 &= -57345;
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -458753;
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                }
                                if ((i2 & 32) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i3 = (-3670017) & i3;
                                }
                                if (i8 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                }
                                if (i10 != 0) {
                                    Function2 function15 = function6;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i13 = i3;
                                    function9 = function15;
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                } else {
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    i13 = i3;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    function9 = function6;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                    fM2085getContainerElevationD9Ej5fM = f2;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                            }
                            TooltipDefaults tooltipDefaults4 = TooltipDefaults.INSTANCE;
                            long j7 = j3;
                            int i111 = i13 << 3;
                            TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults4.m1270caretShapeEaSLcWc(j7), tooltipDefaults4.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i111 & 3670016) | (i111 & 29360128) | (i111 & 234881024) | (i111 & 1879048192), (i13 >> 27) & 14, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function8 = function9;
                            f3 = fM2085getContainerElevationD9Ej5fM;
                            function7 = function10;
                            f4 = f5;
                            modifier3 = modifier4;
                            richTooltipColors3 = richTooltipColors4;
                            shape2 = shape3;
                            j2 = j7;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            function7 = function5;
                            function8 = function6;
                            richTooltipColors3 = richTooltipColors2;
                            shape2 = richTooltipContainerShape;
                            f3 = f2;
                            f4 = fM1742getLevel0D9Ej5fM;
                            modifier3 = modifier2;
                            j2 = jM6129getUnspecifiedMYxV2XQ;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                                public final Object invoke(Object obj, Object obj2) {
                                    return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 805306368;
                    if ((i3 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function16 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function16;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        } else {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function17 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function17;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                        }
                        TooltipDefaults tooltipDefaults5 = TooltipDefaults.INSTANCE;
                        long j8 = j3;
                        int i112 = i13 << 3;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults5.m1270caretShapeEaSLcWc(j8), tooltipDefaults5.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i112 & 3670016) | (i112 & 29360128) | (i112 & 234881024) | (i112 & 1879048192), (i13 >> 27) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function9;
                        f3 = fM2085getContainerElevationD9Ej5fM;
                        function7 = function10;
                        f4 = f5;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        shape2 = shape3;
                        j2 = j8;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function7 = function5;
                        function8 = function6;
                        richTooltipColors3 = richTooltipColors2;
                        shape2 = richTooltipContainerShape;
                        f3 = f2;
                        f4 = fM1742getLevel0D9Ej5fM;
                        modifier3 = modifier2;
                        j2 = jM6129getUnspecifiedMYxV2XQ;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 3072;
                function6 = function3;
                if ((i & 24576) == 0) {
                    jM6129getUnspecifiedMYxV2XQ = j;
                    if ((i2 & 8) == 0) {
                        i15 = 8192;
                    } else {
                        i15 = 8192;
                    }
                    i3 |= i15;
                } else {
                    jM6129getUnspecifiedMYxV2XQ = j;
                }
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if ((i2 & 16) == 0) {
                        richTooltipContainerShape = shape;
                        if (composerStartRestartGroup.changed(richTooltipContainerShape)) {
                        }
                        i3 |= i17;
                    } else {
                        richTooltipContainerShape = shape;
                    }
                    i3 |= i17;
                } else {
                    richTooltipContainerShape = shape;
                }
                if ((i & 1572864) == 0) {
                    richTooltipColors2 = richTooltipColors;
                    if ((i2 & 32) == 0) {
                        i14 = 524288;
                    } else {
                        i14 = 524288;
                    }
                    i3 |= i14;
                } else {
                    richTooltipColors2 = richTooltipColors;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    i3 |= 12582912;
                    fM1742getLevel0D9Ej5fM = f;
                } else {
                    fM1742getLevel0D9Ej5fM = f;
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(fM1742getLevel0D9Ej5fM)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(f2)) {
                            i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i11 = 33554432;
                        }
                        i3 |= i11;
                    }
                    if ((i2 & 256) != 0) {
                        if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i12 = 536870912;
                            } else {
                                i12 = 268435456;
                            }
                            i3 |= i12;
                        }
                        if ((i3 & 306783379) != 306783378) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) == 0) {
                                if (i16 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    function5 = null;
                                }
                                if (i6 != 0) {
                                    function6 = null;
                                }
                                if ((i2 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i3 &= -57345;
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -458753;
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                }
                                if ((i2 & 32) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i3 = (-3670017) & i3;
                                }
                                if (i8 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                }
                                if (i10 != 0) {
                                    Function2 function18 = function6;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i13 = i3;
                                    function9 = function18;
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                } else {
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    i13 = i3;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    function9 = function6;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                    fM2085getContainerElevationD9Ej5fM = f2;
                                }
                            } else {
                                if (i16 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    function5 = null;
                                }
                                if (i6 != 0) {
                                    function6 = null;
                                }
                                if ((i2 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i3 &= -57345;
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -458753;
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                }
                                if ((i2 & 32) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i3 = (-3670017) & i3;
                                }
                                if (i8 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                }
                                if (i10 != 0) {
                                    Function2 function19 = function6;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i13 = i3;
                                    function9 = function19;
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                } else {
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    i13 = i3;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    function9 = function6;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                    fM2085getContainerElevationD9Ej5fM = f2;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                            }
                            TooltipDefaults tooltipDefaults6 = TooltipDefaults.INSTANCE;
                            long j9 = j3;
                            int i113 = i13 << 3;
                            TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults6.m1270caretShapeEaSLcWc(j9), tooltipDefaults6.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i113 & 3670016) | (i113 & 29360128) | (i113 & 234881024) | (i113 & 1879048192), (i13 >> 27) & 14, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function8 = function9;
                            f3 = fM2085getContainerElevationD9Ej5fM;
                            function7 = function10;
                            f4 = f5;
                            modifier3 = modifier4;
                            richTooltipColors3 = richTooltipColors4;
                            shape2 = shape3;
                            j2 = j9;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            function7 = function5;
                            function8 = function6;
                            richTooltipColors3 = richTooltipColors2;
                            shape2 = richTooltipContainerShape;
                            f3 = f2;
                            f4 = fM1742getLevel0D9Ej5fM;
                            modifier3 = modifier2;
                            j2 = jM6129getUnspecifiedMYxV2XQ;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                                public final Object invoke(Object obj, Object obj2) {
                                    return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 805306368;
                    if ((i3 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function110 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function110;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        } else {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function111 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function111;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                        }
                        TooltipDefaults tooltipDefaults7 = TooltipDefaults.INSTANCE;
                        long j10 = j3;
                        int i114 = i13 << 3;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults7.m1270caretShapeEaSLcWc(j10), tooltipDefaults7.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i114 & 3670016) | (i114 & 29360128) | (i114 & 234881024) | (i114 & 1879048192), (i13 >> 27) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function9;
                        f3 = fM2085getContainerElevationD9Ej5fM;
                        function7 = function10;
                        f4 = f5;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        shape2 = shape3;
                        j2 = j10;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function7 = function5;
                        function8 = function6;
                        richTooltipColors3 = richTooltipColors2;
                        shape2 = richTooltipContainerShape;
                        f3 = f2;
                        f4 = fM1742getLevel0D9Ej5fM;
                        modifier3 = modifier2;
                        j2 = jM6129getUnspecifiedMYxV2XQ;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i2 & 256) != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i12 = 536870912;
                        } else {
                            i12 = 268435456;
                        }
                        i3 |= i12;
                    }
                    if ((i3 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function112 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function112;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        } else {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function113 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function113;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                        }
                        TooltipDefaults tooltipDefaults8 = TooltipDefaults.INSTANCE;
                        long j11 = j3;
                        int i115 = i13 << 3;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults8.m1270caretShapeEaSLcWc(j11), tooltipDefaults8.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i115 & 3670016) | (i115 & 29360128) | (i115 & 234881024) | (i115 & 1879048192), (i13 >> 27) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function9;
                        f3 = fM2085getContainerElevationD9Ej5fM;
                        function7 = function10;
                        f4 = f5;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        shape2 = shape3;
                        j2 = j11;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function7 = function5;
                        function8 = function6;
                        richTooltipColors3 = richTooltipColors2;
                        shape2 = richTooltipContainerShape;
                        f3 = f2;
                        f4 = fM1742getLevel0D9Ej5fM;
                        modifier3 = modifier2;
                        j2 = jM6129getUnspecifiedMYxV2XQ;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 805306368;
                if ((i3 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function114 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function114;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    } else {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function115 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function115;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                    }
                    TooltipDefaults tooltipDefaults9 = TooltipDefaults.INSTANCE;
                    long j12 = j3;
                    int i116 = i13 << 3;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults9.m1270caretShapeEaSLcWc(j12), tooltipDefaults9.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i116 & 3670016) | (i116 & 29360128) | (i116 & 234881024) | (i116 & 1879048192), (i13 >> 27) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function9;
                    f3 = fM2085getContainerElevationD9Ej5fM;
                    function7 = function10;
                    f4 = f5;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    shape2 = shape3;
                    j2 = j12;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function7 = function5;
                    function8 = function6;
                    richTooltipColors3 = richTooltipColors2;
                    shape2 = richTooltipContainerShape;
                    f3 = f2;
                    f4 = fM1742getLevel0D9Ej5fM;
                    modifier3 = modifier2;
                    j2 = jM6129getUnspecifiedMYxV2XQ;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
            function5 = function2;
            i6 = i2 & 4;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    function6 = function3;
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i & 24576) == 0) {
                    jM6129getUnspecifiedMYxV2XQ = j;
                    if ((i2 & 8) == 0) {
                        i15 = 8192;
                    } else {
                        i15 = 8192;
                    }
                    i3 |= i15;
                } else {
                    jM6129getUnspecifiedMYxV2XQ = j;
                }
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if ((i2 & 16) == 0) {
                        richTooltipContainerShape = shape;
                        if (composerStartRestartGroup.changed(richTooltipContainerShape)) {
                        }
                        i3 |= i17;
                    } else {
                        richTooltipContainerShape = shape;
                    }
                    i3 |= i17;
                } else {
                    richTooltipContainerShape = shape;
                }
                if ((i & 1572864) == 0) {
                    richTooltipColors2 = richTooltipColors;
                    if ((i2 & 32) == 0) {
                        i14 = 524288;
                    } else {
                        i14 = 524288;
                    }
                    i3 |= i14;
                } else {
                    richTooltipColors2 = richTooltipColors;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    i3 |= 12582912;
                    fM1742getLevel0D9Ej5fM = f;
                } else {
                    fM1742getLevel0D9Ej5fM = f;
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(fM1742getLevel0D9Ej5fM)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(f2)) {
                            i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i11 = 33554432;
                        }
                        i3 |= i11;
                    }
                    if ((i2 & 256) != 0) {
                        if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i12 = 536870912;
                            } else {
                                i12 = 268435456;
                            }
                            i3 |= i12;
                        }
                        if ((i3 & 306783379) != 306783378) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) == 0) {
                                if (i16 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    function5 = null;
                                }
                                if (i6 != 0) {
                                    function6 = null;
                                }
                                if ((i2 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i3 &= -57345;
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -458753;
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                }
                                if ((i2 & 32) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i3 = (-3670017) & i3;
                                }
                                if (i8 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                }
                                if (i10 != 0) {
                                    Function2 function116 = function6;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i13 = i3;
                                    function9 = function116;
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                } else {
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    i13 = i3;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    function9 = function6;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                    fM2085getContainerElevationD9Ej5fM = f2;
                                }
                            } else {
                                if (i16 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    function5 = null;
                                }
                                if (i6 != 0) {
                                    function6 = null;
                                }
                                if ((i2 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i3 &= -57345;
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -458753;
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                }
                                if ((i2 & 32) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i3 = (-3670017) & i3;
                                }
                                if (i8 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                }
                                if (i10 != 0) {
                                    Function2 function117 = function6;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i13 = i3;
                                    function9 = function117;
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                } else {
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    i13 = i3;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    function9 = function6;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                    fM2085getContainerElevationD9Ej5fM = f2;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                            }
                            TooltipDefaults tooltipDefaults10 = TooltipDefaults.INSTANCE;
                            long j13 = j3;
                            int i117 = i13 << 3;
                            TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults10.m1270caretShapeEaSLcWc(j13), tooltipDefaults10.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i117 & 3670016) | (i117 & 29360128) | (i117 & 234881024) | (i117 & 1879048192), (i13 >> 27) & 14, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function8 = function9;
                            f3 = fM2085getContainerElevationD9Ej5fM;
                            function7 = function10;
                            f4 = f5;
                            modifier3 = modifier4;
                            richTooltipColors3 = richTooltipColors4;
                            shape2 = shape3;
                            j2 = j13;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            function7 = function5;
                            function8 = function6;
                            richTooltipColors3 = richTooltipColors2;
                            shape2 = richTooltipContainerShape;
                            f3 = f2;
                            f4 = fM1742getLevel0D9Ej5fM;
                            modifier3 = modifier2;
                            j2 = jM6129getUnspecifiedMYxV2XQ;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                                public final Object invoke(Object obj, Object obj2) {
                                    return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 805306368;
                    if ((i3 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function118 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function118;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        } else {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function119 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function119;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                        }
                        TooltipDefaults tooltipDefaults11 = TooltipDefaults.INSTANCE;
                        long j14 = j3;
                        int i118 = i13 << 3;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults11.m1270caretShapeEaSLcWc(j14), tooltipDefaults11.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i118 & 3670016) | (i118 & 29360128) | (i118 & 234881024) | (i118 & 1879048192), (i13 >> 27) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function9;
                        f3 = fM2085getContainerElevationD9Ej5fM;
                        function7 = function10;
                        f4 = f5;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        shape2 = shape3;
                        j2 = j14;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function7 = function5;
                        function8 = function6;
                        richTooltipColors3 = richTooltipColors2;
                        shape2 = richTooltipContainerShape;
                        f3 = f2;
                        f4 = fM1742getLevel0D9Ej5fM;
                        modifier3 = modifier2;
                        j2 = jM6129getUnspecifiedMYxV2XQ;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i2 & 256) != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i12 = 536870912;
                        } else {
                            i12 = 268435456;
                        }
                        i3 |= i12;
                    }
                    if ((i3 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function1110 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function1110;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        } else {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function1111 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function1111;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                        }
                        TooltipDefaults tooltipDefaults12 = TooltipDefaults.INSTANCE;
                        long j15 = j3;
                        int i119 = i13 << 3;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults12.m1270caretShapeEaSLcWc(j15), tooltipDefaults12.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i119 & 3670016) | (i119 & 29360128) | (i119 & 234881024) | (i119 & 1879048192), (i13 >> 27) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function9;
                        f3 = fM2085getContainerElevationD9Ej5fM;
                        function7 = function10;
                        f4 = f5;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        shape2 = shape3;
                        j2 = j15;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function7 = function5;
                        function8 = function6;
                        richTooltipColors3 = richTooltipColors2;
                        shape2 = richTooltipContainerShape;
                        f3 = f2;
                        f4 = fM1742getLevel0D9Ej5fM;
                        modifier3 = modifier2;
                        j2 = jM6129getUnspecifiedMYxV2XQ;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 805306368;
                if ((i3 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function1112 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function1112;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    } else {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function1113 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function1113;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                    }
                    TooltipDefaults tooltipDefaults13 = TooltipDefaults.INSTANCE;
                    long j16 = j3;
                    int i1110 = i13 << 3;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults13.m1270caretShapeEaSLcWc(j16), tooltipDefaults13.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i1110 & 3670016) | (i1110 & 29360128) | (i1110 & 234881024) | (i1110 & 1879048192), (i13 >> 27) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function9;
                    f3 = fM2085getContainerElevationD9Ej5fM;
                    function7 = function10;
                    f4 = f5;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    shape2 = shape3;
                    j2 = j16;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function7 = function5;
                    function8 = function6;
                    richTooltipColors3 = richTooltipColors2;
                    shape2 = richTooltipContainerShape;
                    f3 = f2;
                    f4 = fM1742getLevel0D9Ej5fM;
                    modifier3 = modifier2;
                    j2 = jM6129getUnspecifiedMYxV2XQ;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            function6 = function3;
            if ((i & 24576) == 0) {
                jM6129getUnspecifiedMYxV2XQ = j;
                if ((i2 & 8) == 0) {
                    i15 = 8192;
                } else {
                    i15 = 8192;
                }
                i3 |= i15;
            } else {
                jM6129getUnspecifiedMYxV2XQ = j;
            }
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if ((i2 & 16) == 0) {
                    richTooltipContainerShape = shape;
                    if (composerStartRestartGroup.changed(richTooltipContainerShape)) {
                    }
                    i3 |= i17;
                } else {
                    richTooltipContainerShape = shape;
                }
                i3 |= i17;
            } else {
                richTooltipContainerShape = shape;
            }
            if ((i & 1572864) == 0) {
                richTooltipColors2 = richTooltipColors;
                if ((i2 & 32) == 0) {
                    i14 = 524288;
                } else {
                    i14 = 524288;
                }
                i3 |= i14;
            } else {
                richTooltipColors2 = richTooltipColors;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 12582912;
                fM1742getLevel0D9Ej5fM = f;
            } else {
                fM1742getLevel0D9Ej5fM = f;
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(fM1742getLevel0D9Ej5fM)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f2)) {
                        i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i2 & 256) != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i12 = 536870912;
                        } else {
                            i12 = 268435456;
                        }
                        i3 |= i12;
                    }
                    if ((i3 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function1114 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function1114;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        } else {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function1115 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function1115;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                        }
                        TooltipDefaults tooltipDefaults14 = TooltipDefaults.INSTANCE;
                        long j17 = j3;
                        int i1111 = i13 << 3;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults14.m1270caretShapeEaSLcWc(j17), tooltipDefaults14.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i1111 & 3670016) | (i1111 & 29360128) | (i1111 & 234881024) | (i1111 & 1879048192), (i13 >> 27) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function9;
                        f3 = fM2085getContainerElevationD9Ej5fM;
                        function7 = function10;
                        f4 = f5;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        shape2 = shape3;
                        j2 = j17;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function7 = function5;
                        function8 = function6;
                        richTooltipColors3 = richTooltipColors2;
                        shape2 = richTooltipContainerShape;
                        f3 = f2;
                        f4 = fM1742getLevel0D9Ej5fM;
                        modifier3 = modifier2;
                        j2 = jM6129getUnspecifiedMYxV2XQ;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 805306368;
                if ((i3 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function1116 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function1116;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    } else {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function1117 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function1117;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                    }
                    TooltipDefaults tooltipDefaults15 = TooltipDefaults.INSTANCE;
                    long j18 = j3;
                    int i1112 = i13 << 3;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults15.m1270caretShapeEaSLcWc(j18), tooltipDefaults15.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i1112 & 3670016) | (i1112 & 29360128) | (i1112 & 234881024) | (i1112 & 1879048192), (i13 >> 27) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function9;
                    f3 = fM2085getContainerElevationD9Ej5fM;
                    function7 = function10;
                    f4 = f5;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    shape2 = shape3;
                    j2 = j18;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function7 = function5;
                    function8 = function6;
                    richTooltipColors3 = richTooltipColors2;
                    shape2 = richTooltipContainerShape;
                    f3 = f2;
                    f4 = fM1742getLevel0D9Ej5fM;
                    modifier3 = modifier2;
                    j2 = jM6129getUnspecifiedMYxV2XQ;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i2 & 256) != 0) {
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i12 = 536870912;
                    } else {
                        i12 = 268435456;
                    }
                    i3 |= i12;
                }
                if ((i3 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function1118 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function1118;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    } else {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function1119 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function1119;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                    }
                    TooltipDefaults tooltipDefaults16 = TooltipDefaults.INSTANCE;
                    long j19 = j3;
                    int i1113 = i13 << 3;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults16.m1270caretShapeEaSLcWc(j19), tooltipDefaults16.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i1113 & 3670016) | (i1113 & 29360128) | (i1113 & 234881024) | (i1113 & 1879048192), (i13 >> 27) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function9;
                    f3 = fM2085getContainerElevationD9Ej5fM;
                    function7 = function10;
                    f4 = f5;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    shape2 = shape3;
                    j2 = j19;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function7 = function5;
                    function8 = function6;
                    richTooltipColors3 = richTooltipColors2;
                    shape2 = richTooltipContainerShape;
                    f3 = f2;
                    f4 = fM1742getLevel0D9Ej5fM;
                    modifier3 = modifier2;
                    j2 = jM6129getUnspecifiedMYxV2XQ;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 805306368;
            if ((i3 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i16 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        function5 = null;
                    }
                    if (i6 != 0) {
                        function6 = null;
                    }
                    if ((i2 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i3 &= -57345;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -458753;
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i3 = (-3670017) & i3;
                    }
                    if (i8 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    }
                    if (i10 != 0) {
                        Function2 function11110 = function6;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i13 = i3;
                        function9 = function11110;
                        f5 = fM1742getLevel0D9Ej5fM;
                        modifier4 = modifier2;
                        function10 = function5;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                    } else {
                        f5 = fM1742getLevel0D9Ej5fM;
                        i13 = i3;
                        modifier4 = modifier2;
                        function10 = function5;
                        function9 = function6;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                        fM2085getContainerElevationD9Ej5fM = f2;
                    }
                } else {
                    if (i16 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        function5 = null;
                    }
                    if (i6 != 0) {
                        function6 = null;
                    }
                    if ((i2 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i3 &= -57345;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -458753;
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i3 = (-3670017) & i3;
                    }
                    if (i8 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    }
                    if (i10 != 0) {
                        Function2 function11111 = function6;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i13 = i3;
                        function9 = function11111;
                        f5 = fM1742getLevel0D9Ej5fM;
                        modifier4 = modifier2;
                        function10 = function5;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                    } else {
                        f5 = fM1742getLevel0D9Ej5fM;
                        i13 = i3;
                        modifier4 = modifier2;
                        function10 = function5;
                        function9 = function6;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                        fM2085getContainerElevationD9Ej5fM = f2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                }
                TooltipDefaults tooltipDefaults17 = TooltipDefaults.INSTANCE;
                long j110 = j3;
                int i1114 = i13 << 3;
                TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults17.m1270caretShapeEaSLcWc(j110), tooltipDefaults17.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i1114 & 3670016) | (i1114 & 29360128) | (i1114 & 234881024) | (i1114 & 1879048192), (i13 >> 27) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function8 = function9;
                f3 = fM2085getContainerElevationD9Ej5fM;
                function7 = function10;
                f4 = f5;
                modifier3 = modifier4;
                richTooltipColors3 = richTooltipColors4;
                shape2 = shape3;
                j2 = j110;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function7 = function5;
                function8 = function6;
                richTooltipColors3 = richTooltipColors2;
                shape2 = richTooltipContainerShape;
                f3 = f2;
                f4 = fM1742getLevel0D9Ej5fM;
                modifier3 = modifier2;
                j2 = jM6129getUnspecifiedMYxV2XQ;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                    public final Object invoke(Object obj, Object obj2) {
                        return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        i4 = i2 & 2;
        if (i4 != 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                function5 = function2;
                if (composerStartRestartGroup.changedInstance(function5)) {
                    i5 = 256;
                } else {
                    i5 = 128;
                }
                i3 |= i5;
            }
            i6 = i2 & 4;
            if (i6 != 0) {
                if ((i & 3072) == 0) {
                    function6 = function3;
                    if (composerStartRestartGroup.changedInstance(function6)) {
                        i7 = 2048;
                    } else {
                        i7 = 1024;
                    }
                    i3 |= i7;
                }
                if ((i & 24576) == 0) {
                    jM6129getUnspecifiedMYxV2XQ = j;
                    if ((i2 & 8) == 0) {
                        i15 = 8192;
                    } else {
                        i15 = 8192;
                    }
                    i3 |= i15;
                } else {
                    jM6129getUnspecifiedMYxV2XQ = j;
                }
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if ((i2 & 16) == 0) {
                        richTooltipContainerShape = shape;
                        if (composerStartRestartGroup.changed(richTooltipContainerShape)) {
                        }
                        i3 |= i17;
                    } else {
                        richTooltipContainerShape = shape;
                    }
                    i3 |= i17;
                } else {
                    richTooltipContainerShape = shape;
                }
                if ((i & 1572864) == 0) {
                    richTooltipColors2 = richTooltipColors;
                    if ((i2 & 32) == 0) {
                        i14 = 524288;
                    } else {
                        i14 = 524288;
                    }
                    i3 |= i14;
                } else {
                    richTooltipColors2 = richTooltipColors;
                }
                i8 = i2 & 64;
                if (i8 != 0) {
                    i3 |= 12582912;
                    fM1742getLevel0D9Ej5fM = f;
                } else {
                    fM1742getLevel0D9Ej5fM = f;
                    if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(fM1742getLevel0D9Ej5fM)) {
                            i9 = 8388608;
                        } else {
                            i9 = 4194304;
                        }
                        i3 |= i9;
                    }
                }
                i10 = i2 & 128;
                if (i10 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changed(f2)) {
                            i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                        } else {
                            i11 = 33554432;
                        }
                        i3 |= i11;
                    }
                    if ((i2 & 256) != 0) {
                        if ((i & 805306368) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i12 = 536870912;
                            } else {
                                i12 = 268435456;
                            }
                            i3 |= i12;
                        }
                        if ((i3 & 306783379) != 306783378) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                            composerStartRestartGroup.startDefaults();
                            if ((i & 1) == 0) {
                                if (i16 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    function5 = null;
                                }
                                if (i6 != 0) {
                                    function6 = null;
                                }
                                if ((i2 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i3 &= -57345;
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -458753;
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                }
                                if ((i2 & 32) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i3 = (-3670017) & i3;
                                }
                                if (i8 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                }
                                if (i10 != 0) {
                                    Function2 function11112 = function6;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i13 = i3;
                                    function9 = function11112;
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                } else {
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    i13 = i3;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    function9 = function6;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                    fM2085getContainerElevationD9Ej5fM = f2;
                                }
                            } else {
                                if (i16 != 0) {
                                    modifier2 = Modifier.INSTANCE;
                                }
                                if (i4 != 0) {
                                    function5 = null;
                                }
                                if (i6 != 0) {
                                    function6 = null;
                                }
                                if ((i2 & 8) != 0) {
                                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                    i3 &= -57345;
                                }
                                if ((i2 & 16) != 0) {
                                    i3 &= -458753;
                                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                                }
                                if ((i2 & 32) != 0) {
                                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                    i3 = (-3670017) & i3;
                                }
                                if (i8 != 0) {
                                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                                }
                                if (i10 != 0) {
                                    Function2 function11113 = function6;
                                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                    i13 = i3;
                                    function9 = function11113;
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                } else {
                                    f5 = fM1742getLevel0D9Ej5fM;
                                    i13 = i3;
                                    modifier4 = modifier2;
                                    function10 = function5;
                                    function9 = function6;
                                    richTooltipColors4 = richTooltipColors2;
                                    j3 = jM6129getUnspecifiedMYxV2XQ;
                                    shape3 = richTooltipContainerShape;
                                    fM2085getContainerElevationD9Ej5fM = f2;
                                }
                            }
                            composerStartRestartGroup.endDefaults();
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                            }
                            TooltipDefaults tooltipDefaults18 = TooltipDefaults.INSTANCE;
                            long j111 = j3;
                            int i1115 = i13 << 3;
                            TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults18.m1270caretShapeEaSLcWc(j111), tooltipDefaults18.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i1115 & 3670016) | (i1115 & 29360128) | (i1115 & 234881024) | (i1115 & 1879048192), (i13 >> 27) & 14, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function8 = function9;
                            f3 = fM2085getContainerElevationD9Ej5fM;
                            function7 = function10;
                            f4 = f5;
                            modifier3 = modifier4;
                            richTooltipColors3 = richTooltipColors4;
                            shape2 = shape3;
                            j2 = j111;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            function7 = function5;
                            function8 = function6;
                            richTooltipColors3 = richTooltipColors2;
                            shape2 = richTooltipContainerShape;
                            f3 = f2;
                            f4 = fM1742getLevel0D9Ej5fM;
                            modifier3 = modifier2;
                            j2 = jM6129getUnspecifiedMYxV2XQ;
                        }
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                                public final Object invoke(Object obj, Object obj2) {
                                    return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 805306368;
                    if ((i3 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function11114 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function11114;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        } else {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function11115 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function11115;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                        }
                        TooltipDefaults tooltipDefaults19 = TooltipDefaults.INSTANCE;
                        long j112 = j3;
                        int i1116 = i13 << 3;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults19.m1270caretShapeEaSLcWc(j112), tooltipDefaults19.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i1116 & 3670016) | (i1116 & 29360128) | (i1116 & 234881024) | (i1116 & 1879048192), (i13 >> 27) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function9;
                        f3 = fM2085getContainerElevationD9Ej5fM;
                        function7 = function10;
                        f4 = f5;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        shape2 = shape3;
                        j2 = j112;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function7 = function5;
                        function8 = function6;
                        richTooltipColors3 = richTooltipColors2;
                        shape2 = richTooltipContainerShape;
                        f3 = f2;
                        f4 = fM1742getLevel0D9Ej5fM;
                        modifier3 = modifier2;
                        j2 = jM6129getUnspecifiedMYxV2XQ;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i2 & 256) != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i12 = 536870912;
                        } else {
                            i12 = 268435456;
                        }
                        i3 |= i12;
                    }
                    if ((i3 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function11116 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function11116;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        } else {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function11117 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function11117;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                        }
                        TooltipDefaults tooltipDefaults110 = TooltipDefaults.INSTANCE;
                        long j113 = j3;
                        int i1117 = i13 << 3;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults110.m1270caretShapeEaSLcWc(j113), tooltipDefaults110.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i1117 & 3670016) | (i1117 & 29360128) | (i1117 & 234881024) | (i1117 & 1879048192), (i13 >> 27) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function9;
                        f3 = fM2085getContainerElevationD9Ej5fM;
                        function7 = function10;
                        f4 = f5;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        shape2 = shape3;
                        j2 = j113;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function7 = function5;
                        function8 = function6;
                        richTooltipColors3 = richTooltipColors2;
                        shape2 = richTooltipContainerShape;
                        f3 = f2;
                        f4 = fM1742getLevel0D9Ej5fM;
                        modifier3 = modifier2;
                        j2 = jM6129getUnspecifiedMYxV2XQ;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 805306368;
                if ((i3 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function11118 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function11118;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    } else {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function11119 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function11119;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                    }
                    TooltipDefaults tooltipDefaults111 = TooltipDefaults.INSTANCE;
                    long j114 = j3;
                    int i1118 = i13 << 3;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults111.m1270caretShapeEaSLcWc(j114), tooltipDefaults111.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i1118 & 3670016) | (i1118 & 29360128) | (i1118 & 234881024) | (i1118 & 1879048192), (i13 >> 27) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function9;
                    f3 = fM2085getContainerElevationD9Ej5fM;
                    function7 = function10;
                    f4 = f5;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    shape2 = shape3;
                    j2 = j114;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function7 = function5;
                    function8 = function6;
                    richTooltipColors3 = richTooltipColors2;
                    shape2 = richTooltipContainerShape;
                    f3 = f2;
                    f4 = fM1742getLevel0D9Ej5fM;
                    modifier3 = modifier2;
                    j2 = jM6129getUnspecifiedMYxV2XQ;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            function6 = function3;
            if ((i & 24576) == 0) {
                jM6129getUnspecifiedMYxV2XQ = j;
                if ((i2 & 8) == 0) {
                    i15 = 8192;
                } else {
                    i15 = 8192;
                }
                i3 |= i15;
            } else {
                jM6129getUnspecifiedMYxV2XQ = j;
            }
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if ((i2 & 16) == 0) {
                    richTooltipContainerShape = shape;
                    if (composerStartRestartGroup.changed(richTooltipContainerShape)) {
                    }
                    i3 |= i17;
                } else {
                    richTooltipContainerShape = shape;
                }
                i3 |= i17;
            } else {
                richTooltipContainerShape = shape;
            }
            if ((i & 1572864) == 0) {
                richTooltipColors2 = richTooltipColors;
                if ((i2 & 32) == 0) {
                    i14 = 524288;
                } else {
                    i14 = 524288;
                }
                i3 |= i14;
            } else {
                richTooltipColors2 = richTooltipColors;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 12582912;
                fM1742getLevel0D9Ej5fM = f;
            } else {
                fM1742getLevel0D9Ej5fM = f;
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(fM1742getLevel0D9Ej5fM)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f2)) {
                        i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i2 & 256) != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i12 = 536870912;
                        } else {
                            i12 = 268435456;
                        }
                        i3 |= i12;
                    }
                    if ((i3 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function111110 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function111110;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        } else {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function111111 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function111111;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                        }
                        TooltipDefaults tooltipDefaults112 = TooltipDefaults.INSTANCE;
                        long j115 = j3;
                        int i1119 = i13 << 3;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults112.m1270caretShapeEaSLcWc(j115), tooltipDefaults112.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i1119 & 3670016) | (i1119 & 29360128) | (i1119 & 234881024) | (i1119 & 1879048192), (i13 >> 27) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function9;
                        f3 = fM2085getContainerElevationD9Ej5fM;
                        function7 = function10;
                        f4 = f5;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        shape2 = shape3;
                        j2 = j115;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function7 = function5;
                        function8 = function6;
                        richTooltipColors3 = richTooltipColors2;
                        shape2 = richTooltipContainerShape;
                        f3 = f2;
                        f4 = fM1742getLevel0D9Ej5fM;
                        modifier3 = modifier2;
                        j2 = jM6129getUnspecifiedMYxV2XQ;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 805306368;
                if ((i3 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function111112 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function111112;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    } else {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function111113 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function111113;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                    }
                    TooltipDefaults tooltipDefaults113 = TooltipDefaults.INSTANCE;
                    long j116 = j3;
                    int i11110 = i13 << 3;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults113.m1270caretShapeEaSLcWc(j116), tooltipDefaults113.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i11110 & 3670016) | (i11110 & 29360128) | (i11110 & 234881024) | (i11110 & 1879048192), (i13 >> 27) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function9;
                    f3 = fM2085getContainerElevationD9Ej5fM;
                    function7 = function10;
                    f4 = f5;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    shape2 = shape3;
                    j2 = j116;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function7 = function5;
                    function8 = function6;
                    richTooltipColors3 = richTooltipColors2;
                    shape2 = richTooltipContainerShape;
                    f3 = f2;
                    f4 = fM1742getLevel0D9Ej5fM;
                    modifier3 = modifier2;
                    j2 = jM6129getUnspecifiedMYxV2XQ;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i2 & 256) != 0) {
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i12 = 536870912;
                    } else {
                        i12 = 268435456;
                    }
                    i3 |= i12;
                }
                if ((i3 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function111114 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function111114;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    } else {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function111115 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function111115;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                    }
                    TooltipDefaults tooltipDefaults114 = TooltipDefaults.INSTANCE;
                    long j117 = j3;
                    int i11111 = i13 << 3;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults114.m1270caretShapeEaSLcWc(j117), tooltipDefaults114.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i11111 & 3670016) | (i11111 & 29360128) | (i11111 & 234881024) | (i11111 & 1879048192), (i13 >> 27) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function9;
                    f3 = fM2085getContainerElevationD9Ej5fM;
                    function7 = function10;
                    f4 = f5;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    shape2 = shape3;
                    j2 = j117;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function7 = function5;
                    function8 = function6;
                    richTooltipColors3 = richTooltipColors2;
                    shape2 = richTooltipContainerShape;
                    f3 = f2;
                    f4 = fM1742getLevel0D9Ej5fM;
                    modifier3 = modifier2;
                    j2 = jM6129getUnspecifiedMYxV2XQ;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 805306368;
            if ((i3 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i16 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        function5 = null;
                    }
                    if (i6 != 0) {
                        function6 = null;
                    }
                    if ((i2 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i3 &= -57345;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -458753;
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i3 = (-3670017) & i3;
                    }
                    if (i8 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    }
                    if (i10 != 0) {
                        Function2 function111116 = function6;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i13 = i3;
                        function9 = function111116;
                        f5 = fM1742getLevel0D9Ej5fM;
                        modifier4 = modifier2;
                        function10 = function5;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                    } else {
                        f5 = fM1742getLevel0D9Ej5fM;
                        i13 = i3;
                        modifier4 = modifier2;
                        function10 = function5;
                        function9 = function6;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                        fM2085getContainerElevationD9Ej5fM = f2;
                    }
                } else {
                    if (i16 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        function5 = null;
                    }
                    if (i6 != 0) {
                        function6 = null;
                    }
                    if ((i2 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i3 &= -57345;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -458753;
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i3 = (-3670017) & i3;
                    }
                    if (i8 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    }
                    if (i10 != 0) {
                        Function2 function111117 = function6;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i13 = i3;
                        function9 = function111117;
                        f5 = fM1742getLevel0D9Ej5fM;
                        modifier4 = modifier2;
                        function10 = function5;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                    } else {
                        f5 = fM1742getLevel0D9Ej5fM;
                        i13 = i3;
                        modifier4 = modifier2;
                        function10 = function5;
                        function9 = function6;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                        fM2085getContainerElevationD9Ej5fM = f2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                }
                TooltipDefaults tooltipDefaults115 = TooltipDefaults.INSTANCE;
                long j118 = j3;
                int i11112 = i13 << 3;
                TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults115.m1270caretShapeEaSLcWc(j118), tooltipDefaults115.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i11112 & 3670016) | (i11112 & 29360128) | (i11112 & 234881024) | (i11112 & 1879048192), (i13 >> 27) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function8 = function9;
                f3 = fM2085getContainerElevationD9Ej5fM;
                function7 = function10;
                f4 = f5;
                modifier3 = modifier4;
                richTooltipColors3 = richTooltipColors4;
                shape2 = shape3;
                j2 = j118;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function7 = function5;
                function8 = function6;
                richTooltipColors3 = richTooltipColors2;
                shape2 = richTooltipContainerShape;
                f3 = f2;
                f4 = fM1742getLevel0D9Ej5fM;
                modifier3 = modifier2;
                j2 = jM6129getUnspecifiedMYxV2XQ;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                    public final Object invoke(Object obj, Object obj2) {
                        return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        function5 = function2;
        i6 = i2 & 4;
        if (i6 != 0) {
            if ((i & 3072) == 0) {
                function6 = function3;
                if (composerStartRestartGroup.changedInstance(function6)) {
                    i7 = 2048;
                } else {
                    i7 = 1024;
                }
                i3 |= i7;
            }
            if ((i & 24576) == 0) {
                jM6129getUnspecifiedMYxV2XQ = j;
                if ((i2 & 8) == 0) {
                    i15 = 8192;
                } else {
                    i15 = 8192;
                }
                i3 |= i15;
            } else {
                jM6129getUnspecifiedMYxV2XQ = j;
            }
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if ((i2 & 16) == 0) {
                    richTooltipContainerShape = shape;
                    if (composerStartRestartGroup.changed(richTooltipContainerShape)) {
                    }
                    i3 |= i17;
                } else {
                    richTooltipContainerShape = shape;
                }
                i3 |= i17;
            } else {
                richTooltipContainerShape = shape;
            }
            if ((i & 1572864) == 0) {
                richTooltipColors2 = richTooltipColors;
                if ((i2 & 32) == 0) {
                    i14 = 524288;
                } else {
                    i14 = 524288;
                }
                i3 |= i14;
            } else {
                richTooltipColors2 = richTooltipColors;
            }
            i8 = i2 & 64;
            if (i8 != 0) {
                i3 |= 12582912;
                fM1742getLevel0D9Ej5fM = f;
            } else {
                fM1742getLevel0D9Ej5fM = f;
                if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(fM1742getLevel0D9Ej5fM)) {
                        i9 = 8388608;
                    } else {
                        i9 = 4194304;
                    }
                    i3 |= i9;
                }
            }
            i10 = i2 & 128;
            if (i10 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changed(f2)) {
                        i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    } else {
                        i11 = 33554432;
                    }
                    i3 |= i11;
                }
                if ((i2 & 256) != 0) {
                    if ((i & 805306368) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i12 = 536870912;
                        } else {
                            i12 = 268435456;
                        }
                        i3 |= i12;
                    }
                    if ((i3 & 306783379) != 306783378) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                        composerStartRestartGroup.startDefaults();
                        if ((i & 1) == 0) {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function111118 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function111118;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        } else {
                            if (i16 != 0) {
                                modifier2 = Modifier.INSTANCE;
                            }
                            if (i4 != 0) {
                                function5 = null;
                            }
                            if (i6 != 0) {
                                function6 = null;
                            }
                            if ((i2 & 8) != 0) {
                                jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                                i3 &= -57345;
                            }
                            if ((i2 & 16) != 0) {
                                i3 &= -458753;
                                richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                            }
                            if ((i2 & 32) != 0) {
                                richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                                i3 = (-3670017) & i3;
                            }
                            if (i8 != 0) {
                                fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                            }
                            if (i10 != 0) {
                                Function2 function111119 = function6;
                                fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                                i13 = i3;
                                function9 = function111119;
                                f5 = fM1742getLevel0D9Ej5fM;
                                modifier4 = modifier2;
                                function10 = function5;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                            } else {
                                f5 = fM1742getLevel0D9Ej5fM;
                                i13 = i3;
                                modifier4 = modifier2;
                                function10 = function5;
                                function9 = function6;
                                richTooltipColors4 = richTooltipColors2;
                                j3 = jM6129getUnspecifiedMYxV2XQ;
                                shape3 = richTooltipContainerShape;
                                fM2085getContainerElevationD9Ej5fM = f2;
                            }
                        }
                        composerStartRestartGroup.endDefaults();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                        }
                        TooltipDefaults tooltipDefaults116 = TooltipDefaults.INSTANCE;
                        long j119 = j3;
                        int i11113 = i13 << 3;
                        TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults116.m1270caretShapeEaSLcWc(j119), tooltipDefaults116.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i11113 & 3670016) | (i11113 & 29360128) | (i11113 & 234881024) | (i11113 & 1879048192), (i13 >> 27) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function8 = function9;
                        f3 = fM2085getContainerElevationD9Ej5fM;
                        function7 = function10;
                        f4 = f5;
                        modifier3 = modifier4;
                        richTooltipColors3 = richTooltipColors4;
                        shape2 = shape3;
                        j2 = j119;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function7 = function5;
                        function8 = function6;
                        richTooltipColors3 = richTooltipColors2;
                        shape2 = richTooltipContainerShape;
                        f3 = f2;
                        f4 = fM1742getLevel0D9Ej5fM;
                        modifier3 = modifier2;
                        j2 = jM6129getUnspecifiedMYxV2XQ;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                            public final Object invoke(Object obj, Object obj2) {
                                return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 805306368;
                if ((i3 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function1111110 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function1111110;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    } else {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function1111111 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function1111111;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                    }
                    TooltipDefaults tooltipDefaults117 = TooltipDefaults.INSTANCE;
                    long j1110 = j3;
                    int i11114 = i13 << 3;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults117.m1270caretShapeEaSLcWc(j1110), tooltipDefaults117.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i11114 & 3670016) | (i11114 & 29360128) | (i11114 & 234881024) | (i11114 & 1879048192), (i13 >> 27) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function9;
                    f3 = fM2085getContainerElevationD9Ej5fM;
                    function7 = function10;
                    f4 = f5;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    shape2 = shape3;
                    j2 = j1110;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function7 = function5;
                    function8 = function6;
                    richTooltipColors3 = richTooltipColors2;
                    shape2 = richTooltipContainerShape;
                    f3 = f2;
                    f4 = fM1742getLevel0D9Ej5fM;
                    modifier3 = modifier2;
                    j2 = jM6129getUnspecifiedMYxV2XQ;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i2 & 256) != 0) {
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i12 = 536870912;
                    } else {
                        i12 = 268435456;
                    }
                    i3 |= i12;
                }
                if ((i3 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function1111112 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function1111112;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    } else {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function1111113 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function1111113;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                    }
                    TooltipDefaults tooltipDefaults118 = TooltipDefaults.INSTANCE;
                    long j1111 = j3;
                    int i11115 = i13 << 3;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults118.m1270caretShapeEaSLcWc(j1111), tooltipDefaults118.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i11115 & 3670016) | (i11115 & 29360128) | (i11115 & 234881024) | (i11115 & 1879048192), (i13 >> 27) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function9;
                    f3 = fM2085getContainerElevationD9Ej5fM;
                    function7 = function10;
                    f4 = f5;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    shape2 = shape3;
                    j2 = j1111;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function7 = function5;
                    function8 = function6;
                    richTooltipColors3 = richTooltipColors2;
                    shape2 = richTooltipContainerShape;
                    f3 = f2;
                    f4 = fM1742getLevel0D9Ej5fM;
                    modifier3 = modifier2;
                    j2 = jM6129getUnspecifiedMYxV2XQ;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 805306368;
            if ((i3 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i16 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        function5 = null;
                    }
                    if (i6 != 0) {
                        function6 = null;
                    }
                    if ((i2 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i3 &= -57345;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -458753;
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i3 = (-3670017) & i3;
                    }
                    if (i8 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    }
                    if (i10 != 0) {
                        Function2 function1111114 = function6;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i13 = i3;
                        function9 = function1111114;
                        f5 = fM1742getLevel0D9Ej5fM;
                        modifier4 = modifier2;
                        function10 = function5;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                    } else {
                        f5 = fM1742getLevel0D9Ej5fM;
                        i13 = i3;
                        modifier4 = modifier2;
                        function10 = function5;
                        function9 = function6;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                        fM2085getContainerElevationD9Ej5fM = f2;
                    }
                } else {
                    if (i16 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        function5 = null;
                    }
                    if (i6 != 0) {
                        function6 = null;
                    }
                    if ((i2 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i3 &= -57345;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -458753;
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i3 = (-3670017) & i3;
                    }
                    if (i8 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    }
                    if (i10 != 0) {
                        Function2 function1111115 = function6;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i13 = i3;
                        function9 = function1111115;
                        f5 = fM1742getLevel0D9Ej5fM;
                        modifier4 = modifier2;
                        function10 = function5;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                    } else {
                        f5 = fM1742getLevel0D9Ej5fM;
                        i13 = i3;
                        modifier4 = modifier2;
                        function10 = function5;
                        function9 = function6;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                        fM2085getContainerElevationD9Ej5fM = f2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                }
                TooltipDefaults tooltipDefaults119 = TooltipDefaults.INSTANCE;
                long j1112 = j3;
                int i11116 = i13 << 3;
                TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults119.m1270caretShapeEaSLcWc(j1112), tooltipDefaults119.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i11116 & 3670016) | (i11116 & 29360128) | (i11116 & 234881024) | (i11116 & 1879048192), (i13 >> 27) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function8 = function9;
                f3 = fM2085getContainerElevationD9Ej5fM;
                function7 = function10;
                f4 = f5;
                modifier3 = modifier4;
                richTooltipColors3 = richTooltipColors4;
                shape2 = shape3;
                j2 = j1112;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function7 = function5;
                function8 = function6;
                richTooltipColors3 = richTooltipColors2;
                shape2 = richTooltipContainerShape;
                f3 = f2;
                f4 = fM1742getLevel0D9Ej5fM;
                modifier3 = modifier2;
                j2 = jM6129getUnspecifiedMYxV2XQ;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                    public final Object invoke(Object obj, Object obj2) {
                        return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        function6 = function3;
        if ((i & 24576) == 0) {
            jM6129getUnspecifiedMYxV2XQ = j;
            if ((i2 & 8) == 0) {
                i15 = 8192;
            } else {
                i15 = 8192;
            }
            i3 |= i15;
        } else {
            jM6129getUnspecifiedMYxV2XQ = j;
        }
        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i2 & 16) == 0) {
                richTooltipContainerShape = shape;
                if (composerStartRestartGroup.changed(richTooltipContainerShape)) {
                }
                i3 |= i17;
            } else {
                richTooltipContainerShape = shape;
            }
            i3 |= i17;
        } else {
            richTooltipContainerShape = shape;
        }
        if ((i & 1572864) == 0) {
            richTooltipColors2 = richTooltipColors;
            if ((i2 & 32) == 0) {
                i14 = 524288;
            } else {
                i14 = 524288;
            }
            i3 |= i14;
        } else {
            richTooltipColors2 = richTooltipColors;
        }
        i8 = i2 & 64;
        if (i8 != 0) {
            i3 |= 12582912;
            fM1742getLevel0D9Ej5fM = f;
        } else {
            fM1742getLevel0D9Ej5fM = f;
            if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changed(fM1742getLevel0D9Ej5fM)) {
                    i9 = 8388608;
                } else {
                    i9 = 4194304;
                }
                i3 |= i9;
            }
        }
        i10 = i2 & 128;
        if (i10 != 0) {
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changed(f2)) {
                    i11 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                } else {
                    i11 = 33554432;
                }
                i3 |= i11;
            }
            if ((i2 & 256) != 0) {
                if ((i & 805306368) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i12 = 536870912;
                    } else {
                        i12 = 268435456;
                    }
                    i3 |= i12;
                }
                if ((i3 & 306783379) != 306783378) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                    composerStartRestartGroup.startDefaults();
                    if ((i & 1) == 0) {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function1111116 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function1111116;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    } else {
                        if (i16 != 0) {
                            modifier2 = Modifier.INSTANCE;
                        }
                        if (i4 != 0) {
                            function5 = null;
                        }
                        if (i6 != 0) {
                            function6 = null;
                        }
                        if ((i2 & 8) != 0) {
                            jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                            i3 &= -57345;
                        }
                        if ((i2 & 16) != 0) {
                            i3 &= -458753;
                            richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                        }
                        if ((i2 & 32) != 0) {
                            richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                            i3 = (-3670017) & i3;
                        }
                        if (i8 != 0) {
                            fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                        }
                        if (i10 != 0) {
                            Function2 function1111117 = function6;
                            fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                            i13 = i3;
                            function9 = function1111117;
                            f5 = fM1742getLevel0D9Ej5fM;
                            modifier4 = modifier2;
                            function10 = function5;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                        } else {
                            f5 = fM1742getLevel0D9Ej5fM;
                            i13 = i3;
                            modifier4 = modifier2;
                            function10 = function5;
                            function9 = function6;
                            richTooltipColors4 = richTooltipColors2;
                            j3 = jM6129getUnspecifiedMYxV2XQ;
                            shape3 = richTooltipContainerShape;
                            fM2085getContainerElevationD9Ej5fM = f2;
                        }
                    }
                    composerStartRestartGroup.endDefaults();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                    }
                    TooltipDefaults tooltipDefaults1110 = TooltipDefaults.INSTANCE;
                    long j1113 = j3;
                    int i11117 = i13 << 3;
                    TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults1110.m1270caretShapeEaSLcWc(j1113), tooltipDefaults1110.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i11117 & 3670016) | (i11117 & 29360128) | (i11117 & 234881024) | (i11117 & 1879048192), (i13 >> 27) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function8 = function9;
                    f3 = fM2085getContainerElevationD9Ej5fM;
                    function7 = function10;
                    f4 = f5;
                    modifier3 = modifier4;
                    richTooltipColors3 = richTooltipColors4;
                    shape2 = shape3;
                    j2 = j1113;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function7 = function5;
                    function8 = function6;
                    richTooltipColors3 = richTooltipColors2;
                    shape2 = richTooltipContainerShape;
                    f3 = f2;
                    f4 = fM1742getLevel0D9Ej5fM;
                    modifier3 = modifier2;
                    j2 = jM6129getUnspecifiedMYxV2XQ;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                        public final Object invoke(Object obj, Object obj2) {
                            return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 805306368;
            if ((i3 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i16 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        function5 = null;
                    }
                    if (i6 != 0) {
                        function6 = null;
                    }
                    if ((i2 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i3 &= -57345;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -458753;
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i3 = (-3670017) & i3;
                    }
                    if (i8 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    }
                    if (i10 != 0) {
                        Function2 function1111118 = function6;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i13 = i3;
                        function9 = function1111118;
                        f5 = fM1742getLevel0D9Ej5fM;
                        modifier4 = modifier2;
                        function10 = function5;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                    } else {
                        f5 = fM1742getLevel0D9Ej5fM;
                        i13 = i3;
                        modifier4 = modifier2;
                        function10 = function5;
                        function9 = function6;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                        fM2085getContainerElevationD9Ej5fM = f2;
                    }
                } else {
                    if (i16 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        function5 = null;
                    }
                    if (i6 != 0) {
                        function6 = null;
                    }
                    if ((i2 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i3 &= -57345;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -458753;
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i3 = (-3670017) & i3;
                    }
                    if (i8 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    }
                    if (i10 != 0) {
                        Function2 function1111119 = function6;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i13 = i3;
                        function9 = function1111119;
                        f5 = fM1742getLevel0D9Ej5fM;
                        modifier4 = modifier2;
                        function10 = function5;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                    } else {
                        f5 = fM1742getLevel0D9Ej5fM;
                        i13 = i3;
                        modifier4 = modifier2;
                        function10 = function5;
                        function9 = function6;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                        fM2085getContainerElevationD9Ej5fM = f2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                }
                TooltipDefaults tooltipDefaults1111 = TooltipDefaults.INSTANCE;
                long j1114 = j3;
                int i11118 = i13 << 3;
                TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults1111.m1270caretShapeEaSLcWc(j1114), tooltipDefaults1111.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i11118 & 3670016) | (i11118 & 29360128) | (i11118 & 234881024) | (i11118 & 1879048192), (i13 >> 27) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function8 = function9;
                f3 = fM2085getContainerElevationD9Ej5fM;
                function7 = function10;
                f4 = f5;
                modifier3 = modifier4;
                richTooltipColors3 = richTooltipColors4;
                shape2 = shape3;
                j2 = j1114;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function7 = function5;
                function8 = function6;
                richTooltipColors3 = richTooltipColors2;
                shape2 = richTooltipContainerShape;
                f3 = f2;
                f4 = fM1742getLevel0D9Ej5fM;
                modifier3 = modifier2;
                j2 = jM6129getUnspecifiedMYxV2XQ;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                    public final Object invoke(Object obj, Object obj2) {
                        return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 100663296;
        if ((i2 & 256) != 0) {
            if ((i & 805306368) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i12 = 536870912;
                } else {
                    i12 = 268435456;
                }
                i3 |= i12;
            }
            if ((i3 & 306783379) != 306783378) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                composerStartRestartGroup.startDefaults();
                if ((i & 1) == 0) {
                    if (i16 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        function5 = null;
                    }
                    if (i6 != 0) {
                        function6 = null;
                    }
                    if ((i2 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i3 &= -57345;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -458753;
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i3 = (-3670017) & i3;
                    }
                    if (i8 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    }
                    if (i10 != 0) {
                        Function2 function11111110 = function6;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i13 = i3;
                        function9 = function11111110;
                        f5 = fM1742getLevel0D9Ej5fM;
                        modifier4 = modifier2;
                        function10 = function5;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                    } else {
                        f5 = fM1742getLevel0D9Ej5fM;
                        i13 = i3;
                        modifier4 = modifier2;
                        function10 = function5;
                        function9 = function6;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                        fM2085getContainerElevationD9Ej5fM = f2;
                    }
                } else {
                    if (i16 != 0) {
                        modifier2 = Modifier.INSTANCE;
                    }
                    if (i4 != 0) {
                        function5 = null;
                    }
                    if (i6 != 0) {
                        function6 = null;
                    }
                    if ((i2 & 8) != 0) {
                        jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                        i3 &= -57345;
                    }
                    if ((i2 & 16) != 0) {
                        i3 &= -458753;
                        richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                    }
                    if ((i2 & 32) != 0) {
                        richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                        i3 = (-3670017) & i3;
                    }
                    if (i8 != 0) {
                        fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                    }
                    if (i10 != 0) {
                        Function2 function11111111 = function6;
                        fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                        i13 = i3;
                        function9 = function11111111;
                        f5 = fM1742getLevel0D9Ej5fM;
                        modifier4 = modifier2;
                        function10 = function5;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                    } else {
                        f5 = fM1742getLevel0D9Ej5fM;
                        i13 = i3;
                        modifier4 = modifier2;
                        function10 = function5;
                        function9 = function6;
                        richTooltipColors4 = richTooltipColors2;
                        j3 = jM6129getUnspecifiedMYxV2XQ;
                        shape3 = richTooltipContainerShape;
                        fM2085getContainerElevationD9Ej5fM = f2;
                    }
                }
                composerStartRestartGroup.endDefaults();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
                }
                TooltipDefaults tooltipDefaults1112 = TooltipDefaults.INSTANCE;
                long j1115 = j3;
                int i11119 = i13 << 3;
                TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults1112.m1270caretShapeEaSLcWc(j1115), tooltipDefaults1112.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i11119 & 3670016) | (i11119 & 29360128) | (i11119 & 234881024) | (i11119 & 1879048192), (i13 >> 27) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function8 = function9;
                f3 = fM2085getContainerElevationD9Ej5fM;
                function7 = function10;
                f4 = f5;
                modifier3 = modifier4;
                richTooltipColors3 = richTooltipColors4;
                shape2 = shape3;
                j2 = j1115;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function7 = function5;
                function8 = function6;
                richTooltipColors3 = richTooltipColors2;
                shape2 = richTooltipContainerShape;
                f3 = f2;
                f4 = fM1742getLevel0D9Ej5fM;
                modifier3 = modifier2;
                j2 = jM6129getUnspecifiedMYxV2XQ;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                    public final Object invoke(Object obj, Object obj2) {
                        return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 805306368;
        if ((i3 & 306783379) != 306783378) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            composerStartRestartGroup.startDefaults();
            if ((i & 1) == 0) {
                if (i16 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    function5 = null;
                }
                if (i6 != 0) {
                    function6 = null;
                }
                if ((i2 & 8) != 0) {
                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                    i3 &= -57345;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -458753;
                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 32) != 0) {
                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                    i3 = (-3670017) & i3;
                }
                if (i8 != 0) {
                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                }
                if (i10 != 0) {
                    Function2 function11111112 = function6;
                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                    i13 = i3;
                    function9 = function11111112;
                    f5 = fM1742getLevel0D9Ej5fM;
                    modifier4 = modifier2;
                    function10 = function5;
                    richTooltipColors4 = richTooltipColors2;
                    j3 = jM6129getUnspecifiedMYxV2XQ;
                    shape3 = richTooltipContainerShape;
                } else {
                    f5 = fM1742getLevel0D9Ej5fM;
                    i13 = i3;
                    modifier4 = modifier2;
                    function10 = function5;
                    function9 = function6;
                    richTooltipColors4 = richTooltipColors2;
                    j3 = jM6129getUnspecifiedMYxV2XQ;
                    shape3 = richTooltipContainerShape;
                    fM2085getContainerElevationD9Ej5fM = f2;
                }
            } else {
                if (i16 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 != 0) {
                    function5 = null;
                }
                if (i6 != 0) {
                    function6 = null;
                }
                if ((i2 & 8) != 0) {
                    jM6129getUnspecifiedMYxV2XQ = DpSize.INSTANCE.m6129getUnspecifiedMYxV2XQ();
                    i3 &= -57345;
                }
                if ((i2 & 16) != 0) {
                    i3 &= -458753;
                    richTooltipContainerShape = TooltipDefaults.INSTANCE.getRichTooltipContainerShape(composerStartRestartGroup, 6);
                }
                if ((i2 & 32) != 0) {
                    richTooltipColors2 = TooltipDefaults.INSTANCE.richTooltipColors(composerStartRestartGroup, 6);
                    i3 = (-3670017) & i3;
                }
                if (i8 != 0) {
                    fM1742getLevel0D9Ej5fM = ElevationTokens.INSTANCE.m1742getLevel0D9Ej5fM();
                }
                if (i10 != 0) {
                    Function2 function11111113 = function6;
                    fM2085getContainerElevationD9Ej5fM = RichTooltipTokens.INSTANCE.m2085getContainerElevationD9Ej5fM();
                    i13 = i3;
                    function9 = function11111113;
                    f5 = fM1742getLevel0D9Ej5fM;
                    modifier4 = modifier2;
                    function10 = function5;
                    richTooltipColors4 = richTooltipColors2;
                    j3 = jM6129getUnspecifiedMYxV2XQ;
                    shape3 = richTooltipContainerShape;
                } else {
                    f5 = fM1742getLevel0D9Ej5fM;
                    i13 = i3;
                    modifier4 = modifier2;
                    function10 = function5;
                    function9 = function6;
                    richTooltipColors4 = richTooltipColors2;
                    j3 = jM6129getUnspecifiedMYxV2XQ;
                    shape3 = richTooltipContainerShape;
                    fM2085getContainerElevationD9Ej5fM = f2;
                }
            }
            composerStartRestartGroup.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-244908363, i13, -1, "androidx.compose.material3.RichTooltipAndroid (Tooltip.android.kt:154)");
            }
            TooltipDefaults tooltipDefaults1113 = TooltipDefaults.INSTANCE;
            long j1116 = j3;
            int i111110 = i13 << 3;
            TooltipKt.m1281RichTooltipEkvW5A0(tooltipScope, modifier4, function10, function9, tooltipDefaults1113.m1270caretShapeEaSLcWc(j1116), tooltipDefaults1113.m1273getRichTooltipMaxWidthD9Ej5fM(), shape3, richTooltipColors4, f5, fM2085getContainerElevationD9Ej5fM, function4, composerStartRestartGroup, (i13 & 14) | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE | (i13 & 112) | (i13 & 896) | (i13 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i111110 & 3670016) | (i111110 & 29360128) | (i111110 & 234881024) | (i111110 & 1879048192), (i13 >> 27) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function8 = function9;
            f3 = fM2085getContainerElevationD9Ej5fM;
            function7 = function10;
            f4 = f5;
            modifier3 = modifier4;
            richTooltipColors3 = richTooltipColors4;
            shape2 = shape3;
            j2 = j1116;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            function7 = function5;
            function8 = function6;
            richTooltipColors3 = richTooltipColors2;
            shape2 = richTooltipContainerShape;
            f3 = f2;
            f4 = fM1742getLevel0D9Ej5fM;
            modifier3 = modifier2;
            j2 = jM6129getUnspecifiedMYxV2XQ;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: hhe
                public final Object invoke(Object obj, Object obj2) {
                    return Tooltip_androidKt.b(tooltipScope, modifier3, function7, function8, j2, shape2, richTooltipColors3, f4, f3, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
