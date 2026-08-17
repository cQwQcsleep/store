package androidx.compose.foundation.layout;

import androidx.compose.foundation.layout.ContextualFlowLayoutKt;
import androidx.compose.foundation.layout.FlowLineInfo;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import com.intellij.util.io.IOUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u008e\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u000f21\u0010\u0010\u001a-\u0012\u0004\u0012\u00020\u0012\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u0017H\u0007¢\u0006\u0002\u0010\u0018\u001a\u008e\u0001\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u001d21\u0010\u0010\u001a-\u0012\u0004\u0012\u00020\u001e\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0016¢\u0006\u0002\b\u0017H\u0007¢\u0006\u0002\u0010\u001f\u001a\u00ad\u0001\u0010 \u001a\u0014\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0\u00112\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0002\u001a\u00020\u00032\u0017\u0010'\u001a\u0013\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00010)¢\u0006\u0002\b\u00160(2;\u0010*\u001a7\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110+¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0016H\u0001¢\u0006\u0002\u0010-\u001a\u00ad\u0001\u0010.\u001a\u0014\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020#0\u00112\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010$\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010%\u001a\u00020&2\u0006\u0010\u0002\u001a\u00020\u00032\u0017\u0010'\u001a\u0013\u0012\u000f\u0012\r\u0012\u0004\u0012\u00020\u00010)¢\u0006\u0002\b\u00160(2;\u0010*\u001a7\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110+¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(,\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0016H\u0001¢\u0006\u0002\u0010/¨\u00060"}, d2 = {"ContextualFlowRow", "", "itemCount", "", "modifier", "Landroidx/compose/ui/Modifier;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "itemVerticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "maxItemsInEachRow", "maxLines", "overflow", "Landroidx/compose/foundation/layout/ContextualFlowRowOverflow;", "content", "Lkotlin/Function2;", "Landroidx/compose/foundation/layout/ContextualFlowRowScope;", "Lkotlin/ParameterName;", "name", "index", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(ILandroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Vertical;IILandroidx/compose/foundation/layout/ContextualFlowRowOverflow;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "ContextualFlowColumn", "itemHorizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "maxItemsInEachColumn", "Landroidx/compose/foundation/layout/ContextualFlowColumnOverflow;", "Landroidx/compose/foundation/layout/ContextualFlowColumnScope;", "(ILandroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/ui/Alignment$Horizontal;IILandroidx/compose/foundation/layout/ContextualFlowColumnOverflow;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;II)V", "contextualRowMeasurementHelper", "Landroidx/compose/ui/layout/SubcomposeMeasureScope;", "Landroidx/compose/ui/unit/Constraints;", "Landroidx/compose/ui/layout/MeasureResult;", "maxItemsInMainAxis", "overflowState", "Landroidx/compose/foundation/layout/FlowLayoutOverflowState;", "overflowComposables", "", "Lkotlin/Function0;", "getComposable", "Landroidx/compose/foundation/layout/FlowLineInfo;", "info", "(Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Vertical;IILandroidx/compose/foundation/layout/FlowLayoutOverflowState;ILjava/util/List;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "contextualColumnMeasureHelper", "(Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/ui/Alignment$Horizontal;IILandroidx/compose/foundation/layout/FlowLayoutOverflowState;ILjava/util/List;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function2;", "foundation-layout"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ContextualFlowLayoutKt {
    /* JADX WARN: Code duplicated, block: B:100:0x011d  */
    /* JADX WARN: Code duplicated, block: B:101:0x0125  */
    /* JADX WARN: Code duplicated, block: B:103:0x0128  */
    /* JADX WARN: Code duplicated, block: B:104:0x0134  */
    /* JADX WARN: Code duplicated, block: B:106:0x0139  */
    /* JADX WARN: Code duplicated, block: B:109:0x0145  */
    /* JADX WARN: Code duplicated, block: B:110:0x0147  */
    /* JADX WARN: Code duplicated, block: B:112:0x014b  */
    /* JADX WARN: Code duplicated, block: B:114:0x0151  */
    /* JADX WARN: Code duplicated, block: B:116:0x0156  */
    /* JADX WARN: Code duplicated, block: B:117:0x015d  */
    /* JADX WARN: Code duplicated, block: B:120:0x0165  */
    /* JADX WARN: Code duplicated, block: B:123:0x0172  */
    /* JADX WARN: Code duplicated, block: B:124:0x0174  */
    /* JADX WARN: Code duplicated, block: B:127:0x017b  */
    /* JADX WARN: Code duplicated, block: B:129:0x0183  */
    /* JADX WARN: Code duplicated, block: B:132:0x0190  */
    /* JADX WARN: Code duplicated, block: B:133:0x0192  */
    /* JADX WARN: Code duplicated, block: B:136:0x0199  */
    /* JADX WARN: Code duplicated, block: B:138:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:141:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:144:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:147:0x0210  */
    /* JADX WARN: Code duplicated, block: B:149:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:23:0x0042  */
    /* JADX WARN: Code duplicated, block: B:25:0x0047  */
    /* JADX WARN: Code duplicated, block: B:27:0x004b  */
    /* JADX WARN: Code duplicated, block: B:29:0x0053  */
    /* JADX WARN: Code duplicated, block: B:30:0x0056  */
    /* JADX WARN: Code duplicated, block: B:34:0x005d  */
    /* JADX WARN: Code duplicated, block: B:36:0x0062  */
    /* JADX WARN: Code duplicated, block: B:38:0x0066  */
    /* JADX WARN: Code duplicated, block: B:40:0x006e  */
    /* JADX WARN: Code duplicated, block: B:41:0x0071  */
    /* JADX WARN: Code duplicated, block: B:45:0x0078  */
    /* JADX WARN: Code duplicated, block: B:47:0x007d  */
    /* JADX WARN: Code duplicated, block: B:49:0x0081  */
    /* JADX WARN: Code duplicated, block: B:51:0x0089  */
    /* JADX WARN: Code duplicated, block: B:52:0x008c  */
    /* JADX WARN: Code duplicated, block: B:56:0x0095  */
    /* JADX WARN: Code duplicated, block: B:57:0x009a  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:66:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:69:0x00be  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:77:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:79:0x00de  */
    /* JADX WARN: Code duplicated, block: B:81:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:86:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:88:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:89:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:93:0x010b  */
    /* JADX WARN: Code duplicated, block: B:94:0x010d  */
    /* JADX WARN: Code duplicated, block: B:97:0x0116 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:98:0x0118  */
    @Deprecated(message = "ContextualFlowLayouts are no longer maintained")
    public static final void ContextualFlowColumn(final int i, Modifier modifier, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, Alignment.Horizontal horizontal2, int i2, int i3, ContextualFlowColumnOverflow contextualFlowColumnOverflow, final Function4<? super ContextualFlowColumnScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer composer, final int i4, final int i5) {
        int i6;
        Modifier modifier2;
        int i7;
        Arrangement.Vertical vertical2;
        int i8;
        int i9;
        int i10;
        int i11;
        Alignment.Horizontal start;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        boolean z;
        final Arrangement.Horizontal horizontal3;
        final int i19;
        final ContextualFlowColumnOverflow contextualFlowColumnOverflow2;
        final Arrangement.Vertical vertical3;
        final int i20;
        final Modifier modifier3;
        final Alignment.Horizontal horizontal4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Arrangement.Vertical top;
        int i21;
        Arrangement.Horizontal start2;
        int i22;
        int i23;
        ContextualFlowColumnOverflow clip;
        int i24;
        boolean z2;
        Object objRememberedValue;
        FlowLayoutOverflowState flowLayoutOverflowState;
        boolean z3;
        Object objRememberedValue2;
        Object obj;
        int i25;
        Composer composerStartRestartGroup = composer.startRestartGroup(1986851536);
        if ((i4 & 6) == 0) {
            i6 = (composerStartRestartGroup.changed(i) ? 4 : 2) | i4;
        } else {
            i6 = i4;
        }
        int i26 = i5 & 2;
        if (i26 == 0) {
            if ((i4 & 48) == 0) {
                modifier2 = modifier;
                i6 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i7 = i5 & 4;
            if (i7 != 0) {
                if ((i4 & 384) == 0) {
                    vertical2 = vertical;
                    if (composerStartRestartGroup.changed(vertical2)) {
                        i8 = 256;
                    } else {
                        i8 = 128;
                    }
                    i6 |= i8;
                }
                i9 = i5 & 8;
                if (i9 != 0) {
                    if ((i4 & 3072) == 0) {
                        if (composerStartRestartGroup.changed(horizontal)) {
                            i10 = 2048;
                        } else {
                            i10 = 1024;
                        }
                        i6 |= i10;
                    }
                    i11 = i5 & 16;
                    if (i11 != 0) {
                        if ((i4 & 24576) == 0) {
                            start = horizontal2;
                            if (composerStartRestartGroup.changed(start)) {
                                i12 = 16384;
                            } else {
                                i12 = 8192;
                            }
                            i6 |= i12;
                        }
                        i13 = i5 & 32;
                        if (i13 != 0) {
                            i6 |= 196608;
                        } else if ((i4 & 196608) == 0) {
                            if (composerStartRestartGroup.changed(i2)) {
                                i14 = 131072;
                            } else {
                                i14 = 65536;
                            }
                            i6 |= i14;
                        }
                        i15 = i5 & 64;
                        if (i15 != 0) {
                            i6 |= 1572864;
                        } else if ((i4 & 1572864) == 0) {
                            if (composerStartRestartGroup.changed(i3)) {
                                i16 = IOUtil.MiB;
                            } else {
                                i16 = 524288;
                            }
                            i6 |= i16;
                        }
                        i17 = i5 & 128;
                        if (i17 != 0) {
                            i6 |= 12582912;
                        } else if ((i4 & 12582912) == 0) {
                            if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                                i18 = 8388608;
                            } else {
                                i18 = 4194304;
                            }
                            i6 |= i18;
                        }
                        if ((i4 & 100663296) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i25 = 67108864;
                            } else {
                                i25 = 33554432;
                            }
                            i6 |= i25;
                        }
                        if ((i6 & 38347923) != 38347922) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                            if (i26 != 0) {
                                modifier2 = Modifier.Companion;
                            }
                            if (i7 != 0) {
                                top = Arrangement.INSTANCE.getTop();
                            } else {
                                top = vertical2;
                            }
                            if (i9 != 0) {
                                start2 = Arrangement.INSTANCE.getStart();
                                i21 = i11;
                            } else {
                                i21 = i11;
                                start2 = horizontal;
                            }
                            if (i21 != 0) {
                                start = Alignment.Companion.getStart();
                            }
                            if (i13 != 0) {
                                i22 = Integer.MAX_VALUE;
                            } else {
                                i22 = i2;
                            }
                            if (i15 != 0) {
                                i23 = Integer.MAX_VALUE;
                            } else {
                                i23 = i3;
                            }
                            if (i17 != 0) {
                                clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                            } else {
                                clip = contextualFlowColumnOverflow;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1986851536, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:154)");
                            }
                            i24 = 29360128 & i6;
                            if (i24 == 8388608) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
                                objRememberedValue = clip.createOverflowState$foundation_layout();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                            if (i24 == 8388608) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z3 || objRememberedValue2 == Composer.Companion.getEmpty()) {
                                obj = objRememberedValue2;
                                ArrayList arrayList = new ArrayList();
                                clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList);
                                composerStartRestartGroup.updateRememberedValue(arrayList);
                                obj = arrayList;
                            }
                            obj = objRememberedValue2;
                            int i27 = i6 >> 6;
                            SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(top, start2, start, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(620176540, true, new Function4() { // from class: aw2
                                public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                                    return ContextualFlowLayoutKt.b(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i27 & 57344) | (i27 & 14) | 100663296 | (i27 & 112) | (i27 & 896) | (i27 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            contextualFlowColumnOverflow2 = clip;
                            vertical3 = top;
                            horizontal3 = start2;
                            i19 = i22;
                            i20 = i23;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            horizontal3 = horizontal;
                            i19 = i2;
                            contextualFlowColumnOverflow2 = contextualFlowColumnOverflow;
                            vertical3 = vertical2;
                            i20 = i3;
                        }
                        modifier3 = modifier2;
                        horizontal4 = start;
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bw2
                                public final Object invoke(Object obj2, Object obj3) {
                                    return ContextualFlowLayoutKt.d(i, modifier3, vertical3, horizontal3, horizontal4, i19, i20, contextualFlowColumnOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            });
                        }
                    }
                    i6 |= 24576;
                    start = horizontal2;
                    i13 = i5 & 32;
                    if (i13 != 0) {
                        i6 |= 196608;
                    } else if ((i4 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i6 |= i14;
                    }
                    i15 = i5 & 64;
                    if (i15 != 0) {
                        i6 |= 1572864;
                    } else if ((i4 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(i3)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i6 |= i16;
                    }
                    i17 = i5 & 128;
                    if (i17 != 0) {
                        i6 |= 12582912;
                    } else if ((i4 & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                            i18 = 8388608;
                        } else {
                            i18 = 4194304;
                        }
                        i6 |= i18;
                    }
                    if ((i4 & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i25 = 67108864;
                        } else {
                            i25 = 33554432;
                        }
                        i6 |= i25;
                    }
                    if ((i6 & 38347923) != 38347922) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                        if (i26 != 0) {
                            modifier2 = Modifier.Companion;
                        }
                        if (i7 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical2;
                        }
                        if (i9 != 0) {
                            start2 = Arrangement.INSTANCE.getStart();
                            i21 = i11;
                        } else {
                            i21 = i11;
                            start2 = horizontal;
                        }
                        if (i21 != 0) {
                            start = Alignment.Companion.getStart();
                        }
                        if (i13 != 0) {
                            i22 = Integer.MAX_VALUE;
                        } else {
                            i22 = i2;
                        }
                        if (i15 != 0) {
                            i23 = Integer.MAX_VALUE;
                        } else {
                            i23 = i3;
                        }
                        if (i17 != 0) {
                            clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1986851536, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:154)");
                        }
                        i24 = 29360128 & i6;
                        if (i24 == 8388608) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        if (i24 == 8388608) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z3) {
                            obj = objRememberedValue2;
                            ArrayList arrayList2 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList2);
                            composerStartRestartGroup.updateRememberedValue(arrayList2);
                            obj = arrayList2;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList3 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList3);
                            composerStartRestartGroup.updateRememberedValue(arrayList3);
                            obj = arrayList3;
                        }
                        obj = objRememberedValue2;
                        int i28 = i6 >> 6;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(top, start2, start, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(620176540, true, new Function4() { // from class: aw2
                            public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                                return ContextualFlowLayoutKt.b(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i28 & 57344) | (i28 & 14) | 100663296 | (i28 & 112) | (i28 & 896) | (i28 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowColumnOverflow2 = clip;
                        vertical3 = top;
                        horizontal3 = start2;
                        i19 = i22;
                        i20 = i23;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        horizontal3 = horizontal;
                        i19 = i2;
                        contextualFlowColumnOverflow2 = contextualFlowColumnOverflow;
                        vertical3 = vertical2;
                        i20 = i3;
                    }
                    modifier3 = modifier2;
                    horizontal4 = start;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bw2
                            public final Object invoke(Object obj2, Object obj3) {
                                return ContextualFlowLayoutKt.d(i, modifier3, vertical3, horizontal3, horizontal4, i19, i20, contextualFlowColumnOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i6 |= 3072;
                i11 = i5 & 16;
                if (i11 != 0) {
                    if ((i4 & 24576) == 0) {
                        start = horizontal2;
                        if (composerStartRestartGroup.changed(start)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i6 |= i12;
                    }
                    i13 = i5 & 32;
                    if (i13 != 0) {
                        i6 |= 196608;
                    } else if ((i4 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i6 |= i14;
                    }
                    i15 = i5 & 64;
                    if (i15 != 0) {
                        i6 |= 1572864;
                    } else if ((i4 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(i3)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i6 |= i16;
                    }
                    i17 = i5 & 128;
                    if (i17 != 0) {
                        i6 |= 12582912;
                    } else if ((i4 & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                            i18 = 8388608;
                        } else {
                            i18 = 4194304;
                        }
                        i6 |= i18;
                    }
                    if ((i4 & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i25 = 67108864;
                        } else {
                            i25 = 33554432;
                        }
                        i6 |= i25;
                    }
                    if ((i6 & 38347923) != 38347922) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                        if (i26 != 0) {
                            modifier2 = Modifier.Companion;
                        }
                        if (i7 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical2;
                        }
                        if (i9 != 0) {
                            start2 = Arrangement.INSTANCE.getStart();
                            i21 = i11;
                        } else {
                            i21 = i11;
                            start2 = horizontal;
                        }
                        if (i21 != 0) {
                            start = Alignment.Companion.getStart();
                        }
                        if (i13 != 0) {
                            i22 = Integer.MAX_VALUE;
                        } else {
                            i22 = i2;
                        }
                        if (i15 != 0) {
                            i23 = Integer.MAX_VALUE;
                        } else {
                            i23 = i3;
                        }
                        if (i17 != 0) {
                            clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1986851536, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:154)");
                        }
                        i24 = 29360128 & i6;
                        if (i24 == 8388608) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        if (i24 == 8388608) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z3) {
                            obj = objRememberedValue2;
                            ArrayList arrayList4 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList4);
                            composerStartRestartGroup.updateRememberedValue(arrayList4);
                            obj = arrayList4;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList5 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList5);
                            composerStartRestartGroup.updateRememberedValue(arrayList5);
                            obj = arrayList5;
                        }
                        obj = objRememberedValue2;
                        int i29 = i6 >> 6;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(top, start2, start, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(620176540, true, new Function4() { // from class: aw2
                            public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                                return ContextualFlowLayoutKt.b(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i29 & 57344) | (i29 & 14) | 100663296 | (i29 & 112) | (i29 & 896) | (i29 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowColumnOverflow2 = clip;
                        vertical3 = top;
                        horizontal3 = start2;
                        i19 = i22;
                        i20 = i23;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        horizontal3 = horizontal;
                        i19 = i2;
                        contextualFlowColumnOverflow2 = contextualFlowColumnOverflow;
                        vertical3 = vertical2;
                        i20 = i3;
                    }
                    modifier3 = modifier2;
                    horizontal4 = start;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bw2
                            public final Object invoke(Object obj2, Object obj3) {
                                return ContextualFlowLayoutKt.d(i, modifier3, vertical3, horizontal3, horizontal4, i19, i20, contextualFlowColumnOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i6 |= 24576;
                start = horizontal2;
                i13 = i5 & 32;
                if (i13 != 0) {
                    i6 |= 196608;
                } else if ((i4 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i6 |= i14;
                }
                i15 = i5 & 64;
                if (i15 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i6 |= i16;
                }
                i17 = i5 & 128;
                if (i17 != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((i4 & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i25 = 67108864;
                    } else {
                        i25 = 33554432;
                    }
                    i6 |= i25;
                }
                if ((i6 & 38347923) != 38347922) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                    if (i26 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i7 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i9 != 0) {
                        start2 = Arrangement.INSTANCE.getStart();
                        i21 = i11;
                    } else {
                        i21 = i11;
                        start2 = horizontal;
                    }
                    if (i21 != 0) {
                        start = Alignment.Companion.getStart();
                    }
                    if (i13 != 0) {
                        i22 = Integer.MAX_VALUE;
                    } else {
                        i22 = i2;
                    }
                    if (i15 != 0) {
                        i23 = Integer.MAX_VALUE;
                    } else {
                        i23 = i3;
                    }
                    if (i17 != 0) {
                        clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1986851536, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:154)");
                    }
                    i24 = 29360128 & i6;
                    if (i24 == 8388608) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    if (i24 == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z3) {
                        obj = objRememberedValue2;
                        ArrayList arrayList6 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList6);
                        composerStartRestartGroup.updateRememberedValue(arrayList6);
                        obj = arrayList6;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList7 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList7);
                        composerStartRestartGroup.updateRememberedValue(arrayList7);
                        obj = arrayList7;
                    }
                    obj = objRememberedValue2;
                    int i210 = i6 >> 6;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(top, start2, start, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(620176540, true, new Function4() { // from class: aw2
                        public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                            return ContextualFlowLayoutKt.b(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i210 & 57344) | (i210 & 14) | 100663296 | (i210 & 112) | (i210 & 896) | (i210 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowColumnOverflow2 = clip;
                    vertical3 = top;
                    horizontal3 = start2;
                    i19 = i22;
                    i20 = i23;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    horizontal3 = horizontal;
                    i19 = i2;
                    contextualFlowColumnOverflow2 = contextualFlowColumnOverflow;
                    vertical3 = vertical2;
                    i20 = i3;
                }
                modifier3 = modifier2;
                horizontal4 = start;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bw2
                        public final Object invoke(Object obj2, Object obj3) {
                            return ContextualFlowLayoutKt.d(i, modifier3, vertical3, horizontal3, horizontal4, i19, i20, contextualFlowColumnOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i6 |= 384;
            vertical2 = vertical;
            i9 = i5 & 8;
            if (i9 != 0) {
                if ((i4 & 3072) == 0) {
                    if (composerStartRestartGroup.changed(horizontal)) {
                        i10 = 2048;
                    } else {
                        i10 = 1024;
                    }
                    i6 |= i10;
                }
                i11 = i5 & 16;
                if (i11 != 0) {
                    if ((i4 & 24576) == 0) {
                        start = horizontal2;
                        if (composerStartRestartGroup.changed(start)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i6 |= i12;
                    }
                    i13 = i5 & 32;
                    if (i13 != 0) {
                        i6 |= 196608;
                    } else if ((i4 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i6 |= i14;
                    }
                    i15 = i5 & 64;
                    if (i15 != 0) {
                        i6 |= 1572864;
                    } else if ((i4 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(i3)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i6 |= i16;
                    }
                    i17 = i5 & 128;
                    if (i17 != 0) {
                        i6 |= 12582912;
                    } else if ((i4 & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                            i18 = 8388608;
                        } else {
                            i18 = 4194304;
                        }
                        i6 |= i18;
                    }
                    if ((i4 & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i25 = 67108864;
                        } else {
                            i25 = 33554432;
                        }
                        i6 |= i25;
                    }
                    if ((i6 & 38347923) != 38347922) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                        if (i26 != 0) {
                            modifier2 = Modifier.Companion;
                        }
                        if (i7 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical2;
                        }
                        if (i9 != 0) {
                            start2 = Arrangement.INSTANCE.getStart();
                            i21 = i11;
                        } else {
                            i21 = i11;
                            start2 = horizontal;
                        }
                        if (i21 != 0) {
                            start = Alignment.Companion.getStart();
                        }
                        if (i13 != 0) {
                            i22 = Integer.MAX_VALUE;
                        } else {
                            i22 = i2;
                        }
                        if (i15 != 0) {
                            i23 = Integer.MAX_VALUE;
                        } else {
                            i23 = i3;
                        }
                        if (i17 != 0) {
                            clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1986851536, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:154)");
                        }
                        i24 = 29360128 & i6;
                        if (i24 == 8388608) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        if (i24 == 8388608) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z3) {
                            obj = objRememberedValue2;
                            ArrayList arrayList8 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList8);
                            composerStartRestartGroup.updateRememberedValue(arrayList8);
                            obj = arrayList8;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList9 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList9);
                            composerStartRestartGroup.updateRememberedValue(arrayList9);
                            obj = arrayList9;
                        }
                        obj = objRememberedValue2;
                        int i211 = i6 >> 6;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(top, start2, start, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(620176540, true, new Function4() { // from class: aw2
                            public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                                return ContextualFlowLayoutKt.b(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i211 & 57344) | (i211 & 14) | 100663296 | (i211 & 112) | (i211 & 896) | (i211 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowColumnOverflow2 = clip;
                        vertical3 = top;
                        horizontal3 = start2;
                        i19 = i22;
                        i20 = i23;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        horizontal3 = horizontal;
                        i19 = i2;
                        contextualFlowColumnOverflow2 = contextualFlowColumnOverflow;
                        vertical3 = vertical2;
                        i20 = i3;
                    }
                    modifier3 = modifier2;
                    horizontal4 = start;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bw2
                            public final Object invoke(Object obj2, Object obj3) {
                                return ContextualFlowLayoutKt.d(i, modifier3, vertical3, horizontal3, horizontal4, i19, i20, contextualFlowColumnOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i6 |= 24576;
                start = horizontal2;
                i13 = i5 & 32;
                if (i13 != 0) {
                    i6 |= 196608;
                } else if ((i4 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i6 |= i14;
                }
                i15 = i5 & 64;
                if (i15 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i6 |= i16;
                }
                i17 = i5 & 128;
                if (i17 != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((i4 & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i25 = 67108864;
                    } else {
                        i25 = 33554432;
                    }
                    i6 |= i25;
                }
                if ((i6 & 38347923) != 38347922) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                    if (i26 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i7 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i9 != 0) {
                        start2 = Arrangement.INSTANCE.getStart();
                        i21 = i11;
                    } else {
                        i21 = i11;
                        start2 = horizontal;
                    }
                    if (i21 != 0) {
                        start = Alignment.Companion.getStart();
                    }
                    if (i13 != 0) {
                        i22 = Integer.MAX_VALUE;
                    } else {
                        i22 = i2;
                    }
                    if (i15 != 0) {
                        i23 = Integer.MAX_VALUE;
                    } else {
                        i23 = i3;
                    }
                    if (i17 != 0) {
                        clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1986851536, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:154)");
                    }
                    i24 = 29360128 & i6;
                    if (i24 == 8388608) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    if (i24 == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z3) {
                        obj = objRememberedValue2;
                        ArrayList arrayList10 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList10);
                        composerStartRestartGroup.updateRememberedValue(arrayList10);
                        obj = arrayList10;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList11);
                        composerStartRestartGroup.updateRememberedValue(arrayList11);
                        obj = arrayList11;
                    }
                    obj = objRememberedValue2;
                    int i212 = i6 >> 6;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(top, start2, start, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(620176540, true, new Function4() { // from class: aw2
                        public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                            return ContextualFlowLayoutKt.b(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i212 & 57344) | (i212 & 14) | 100663296 | (i212 & 112) | (i212 & 896) | (i212 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowColumnOverflow2 = clip;
                    vertical3 = top;
                    horizontal3 = start2;
                    i19 = i22;
                    i20 = i23;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    horizontal3 = horizontal;
                    i19 = i2;
                    contextualFlowColumnOverflow2 = contextualFlowColumnOverflow;
                    vertical3 = vertical2;
                    i20 = i3;
                }
                modifier3 = modifier2;
                horizontal4 = start;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bw2
                        public final Object invoke(Object obj2, Object obj3) {
                            return ContextualFlowLayoutKt.d(i, modifier3, vertical3, horizontal3, horizontal4, i19, i20, contextualFlowColumnOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i6 |= 3072;
            i11 = i5 & 16;
            if (i11 != 0) {
                if ((i4 & 24576) == 0) {
                    start = horizontal2;
                    if (composerStartRestartGroup.changed(start)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i6 |= i12;
                }
                i13 = i5 & 32;
                if (i13 != 0) {
                    i6 |= 196608;
                } else if ((i4 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i6 |= i14;
                }
                i15 = i5 & 64;
                if (i15 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i6 |= i16;
                }
                i17 = i5 & 128;
                if (i17 != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((i4 & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i25 = 67108864;
                    } else {
                        i25 = 33554432;
                    }
                    i6 |= i25;
                }
                if ((i6 & 38347923) != 38347922) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                    if (i26 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i7 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i9 != 0) {
                        start2 = Arrangement.INSTANCE.getStart();
                        i21 = i11;
                    } else {
                        i21 = i11;
                        start2 = horizontal;
                    }
                    if (i21 != 0) {
                        start = Alignment.Companion.getStart();
                    }
                    if (i13 != 0) {
                        i22 = Integer.MAX_VALUE;
                    } else {
                        i22 = i2;
                    }
                    if (i15 != 0) {
                        i23 = Integer.MAX_VALUE;
                    } else {
                        i23 = i3;
                    }
                    if (i17 != 0) {
                        clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1986851536, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:154)");
                    }
                    i24 = 29360128 & i6;
                    if (i24 == 8388608) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    if (i24 == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z3) {
                        obj = objRememberedValue2;
                        ArrayList arrayList12 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList12);
                        composerStartRestartGroup.updateRememberedValue(arrayList12);
                        obj = arrayList12;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList13 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList13);
                        composerStartRestartGroup.updateRememberedValue(arrayList13);
                        obj = arrayList13;
                    }
                    obj = objRememberedValue2;
                    int i213 = i6 >> 6;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(top, start2, start, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(620176540, true, new Function4() { // from class: aw2
                        public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                            return ContextualFlowLayoutKt.b(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i213 & 57344) | (i213 & 14) | 100663296 | (i213 & 112) | (i213 & 896) | (i213 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowColumnOverflow2 = clip;
                    vertical3 = top;
                    horizontal3 = start2;
                    i19 = i22;
                    i20 = i23;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    horizontal3 = horizontal;
                    i19 = i2;
                    contextualFlowColumnOverflow2 = contextualFlowColumnOverflow;
                    vertical3 = vertical2;
                    i20 = i3;
                }
                modifier3 = modifier2;
                horizontal4 = start;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bw2
                        public final Object invoke(Object obj2, Object obj3) {
                            return ContextualFlowLayoutKt.d(i, modifier3, vertical3, horizontal3, horizontal4, i19, i20, contextualFlowColumnOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i6 |= 24576;
            start = horizontal2;
            i13 = i5 & 32;
            if (i13 != 0) {
                i6 |= 196608;
            } else if ((i4 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i6 |= i14;
            }
            i15 = i5 & 64;
            if (i15 != 0) {
                i6 |= 1572864;
            } else if ((i4 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(i3)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i6 |= i16;
            }
            i17 = i5 & 128;
            if (i17 != 0) {
                i6 |= 12582912;
            } else if ((i4 & 12582912) == 0) {
                if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                    i18 = 8388608;
                } else {
                    i18 = 4194304;
                }
                i6 |= i18;
            }
            if ((i4 & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i25 = 67108864;
                } else {
                    i25 = 33554432;
                }
                i6 |= i25;
            }
            if ((i6 & 38347923) != 38347922) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                if (i26 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i7 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical2;
                }
                if (i9 != 0) {
                    start2 = Arrangement.INSTANCE.getStart();
                    i21 = i11;
                } else {
                    i21 = i11;
                    start2 = horizontal;
                }
                if (i21 != 0) {
                    start = Alignment.Companion.getStart();
                }
                if (i13 != 0) {
                    i22 = Integer.MAX_VALUE;
                } else {
                    i22 = i2;
                }
                if (i15 != 0) {
                    i23 = Integer.MAX_VALUE;
                } else {
                    i23 = i3;
                }
                if (i17 != 0) {
                    clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1986851536, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:154)");
                }
                i24 = 29360128 & i6;
                if (i24 == 8388608) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                if (i24 == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z3) {
                    obj = objRememberedValue2;
                    ArrayList arrayList14 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList14);
                    composerStartRestartGroup.updateRememberedValue(arrayList14);
                    obj = arrayList14;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList15 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList15);
                    composerStartRestartGroup.updateRememberedValue(arrayList15);
                    obj = arrayList15;
                }
                obj = objRememberedValue2;
                int i214 = i6 >> 6;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(top, start2, start, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(620176540, true, new Function4() { // from class: aw2
                    public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                        return ContextualFlowLayoutKt.b(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i214 & 57344) | (i214 & 14) | 100663296 | (i214 & 112) | (i214 & 896) | (i214 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowColumnOverflow2 = clip;
                vertical3 = top;
                horizontal3 = start2;
                i19 = i22;
                i20 = i23;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                horizontal3 = horizontal;
                i19 = i2;
                contextualFlowColumnOverflow2 = contextualFlowColumnOverflow;
                vertical3 = vertical2;
                i20 = i3;
            }
            modifier3 = modifier2;
            horizontal4 = start;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bw2
                    public final Object invoke(Object obj2, Object obj3) {
                        return ContextualFlowLayoutKt.d(i, modifier3, vertical3, horizontal3, horizontal4, i19, i20, contextualFlowColumnOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i6 |= 48;
        modifier2 = modifier;
        i7 = i5 & 4;
        if (i7 != 0) {
            if ((i4 & 384) == 0) {
                vertical2 = vertical;
                if (composerStartRestartGroup.changed(vertical2)) {
                    i8 = 256;
                } else {
                    i8 = 128;
                }
                i6 |= i8;
            }
            i9 = i5 & 8;
            if (i9 != 0) {
                if ((i4 & 3072) == 0) {
                    if (composerStartRestartGroup.changed(horizontal)) {
                        i10 = 2048;
                    } else {
                        i10 = 1024;
                    }
                    i6 |= i10;
                }
                i11 = i5 & 16;
                if (i11 != 0) {
                    if ((i4 & 24576) == 0) {
                        start = horizontal2;
                        if (composerStartRestartGroup.changed(start)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i6 |= i12;
                    }
                    i13 = i5 & 32;
                    if (i13 != 0) {
                        i6 |= 196608;
                    } else if ((i4 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i6 |= i14;
                    }
                    i15 = i5 & 64;
                    if (i15 != 0) {
                        i6 |= 1572864;
                    } else if ((i4 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(i3)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i6 |= i16;
                    }
                    i17 = i5 & 128;
                    if (i17 != 0) {
                        i6 |= 12582912;
                    } else if ((i4 & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                            i18 = 8388608;
                        } else {
                            i18 = 4194304;
                        }
                        i6 |= i18;
                    }
                    if ((i4 & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i25 = 67108864;
                        } else {
                            i25 = 33554432;
                        }
                        i6 |= i25;
                    }
                    if ((i6 & 38347923) != 38347922) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                        if (i26 != 0) {
                            modifier2 = Modifier.Companion;
                        }
                        if (i7 != 0) {
                            top = Arrangement.INSTANCE.getTop();
                        } else {
                            top = vertical2;
                        }
                        if (i9 != 0) {
                            start2 = Arrangement.INSTANCE.getStart();
                            i21 = i11;
                        } else {
                            i21 = i11;
                            start2 = horizontal;
                        }
                        if (i21 != 0) {
                            start = Alignment.Companion.getStart();
                        }
                        if (i13 != 0) {
                            i22 = Integer.MAX_VALUE;
                        } else {
                            i22 = i2;
                        }
                        if (i15 != 0) {
                            i23 = Integer.MAX_VALUE;
                        } else {
                            i23 = i3;
                        }
                        if (i17 != 0) {
                            clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowColumnOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1986851536, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:154)");
                        }
                        i24 = 29360128 & i6;
                        if (i24 == 8388608) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        if (i24 == 8388608) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z3) {
                            obj = objRememberedValue2;
                            ArrayList arrayList16 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList16);
                            composerStartRestartGroup.updateRememberedValue(arrayList16);
                            obj = arrayList16;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList17 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList17);
                            composerStartRestartGroup.updateRememberedValue(arrayList17);
                            obj = arrayList17;
                        }
                        obj = objRememberedValue2;
                        int i215 = i6 >> 6;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(top, start2, start, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(620176540, true, new Function4() { // from class: aw2
                            public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                                return ContextualFlowLayoutKt.b(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i215 & 57344) | (i215 & 14) | 100663296 | (i215 & 112) | (i215 & 896) | (i215 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowColumnOverflow2 = clip;
                        vertical3 = top;
                        horizontal3 = start2;
                        i19 = i22;
                        i20 = i23;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        horizontal3 = horizontal;
                        i19 = i2;
                        contextualFlowColumnOverflow2 = contextualFlowColumnOverflow;
                        vertical3 = vertical2;
                        i20 = i3;
                    }
                    modifier3 = modifier2;
                    horizontal4 = start;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bw2
                            public final Object invoke(Object obj2, Object obj3) {
                                return ContextualFlowLayoutKt.d(i, modifier3, vertical3, horizontal3, horizontal4, i19, i20, contextualFlowColumnOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i6 |= 24576;
                start = horizontal2;
                i13 = i5 & 32;
                if (i13 != 0) {
                    i6 |= 196608;
                } else if ((i4 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i6 |= i14;
                }
                i15 = i5 & 64;
                if (i15 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i6 |= i16;
                }
                i17 = i5 & 128;
                if (i17 != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((i4 & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i25 = 67108864;
                    } else {
                        i25 = 33554432;
                    }
                    i6 |= i25;
                }
                if ((i6 & 38347923) != 38347922) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                    if (i26 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i7 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i9 != 0) {
                        start2 = Arrangement.INSTANCE.getStart();
                        i21 = i11;
                    } else {
                        i21 = i11;
                        start2 = horizontal;
                    }
                    if (i21 != 0) {
                        start = Alignment.Companion.getStart();
                    }
                    if (i13 != 0) {
                        i22 = Integer.MAX_VALUE;
                    } else {
                        i22 = i2;
                    }
                    if (i15 != 0) {
                        i23 = Integer.MAX_VALUE;
                    } else {
                        i23 = i3;
                    }
                    if (i17 != 0) {
                        clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1986851536, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:154)");
                    }
                    i24 = 29360128 & i6;
                    if (i24 == 8388608) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    if (i24 == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z3) {
                        obj = objRememberedValue2;
                        ArrayList arrayList18 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList18);
                        composerStartRestartGroup.updateRememberedValue(arrayList18);
                        obj = arrayList18;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList19 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList19);
                        composerStartRestartGroup.updateRememberedValue(arrayList19);
                        obj = arrayList19;
                    }
                    obj = objRememberedValue2;
                    int i216 = i6 >> 6;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(top, start2, start, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(620176540, true, new Function4() { // from class: aw2
                        public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                            return ContextualFlowLayoutKt.b(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i216 & 57344) | (i216 & 14) | 100663296 | (i216 & 112) | (i216 & 896) | (i216 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowColumnOverflow2 = clip;
                    vertical3 = top;
                    horizontal3 = start2;
                    i19 = i22;
                    i20 = i23;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    horizontal3 = horizontal;
                    i19 = i2;
                    contextualFlowColumnOverflow2 = contextualFlowColumnOverflow;
                    vertical3 = vertical2;
                    i20 = i3;
                }
                modifier3 = modifier2;
                horizontal4 = start;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bw2
                        public final Object invoke(Object obj2, Object obj3) {
                            return ContextualFlowLayoutKt.d(i, modifier3, vertical3, horizontal3, horizontal4, i19, i20, contextualFlowColumnOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i6 |= 3072;
            i11 = i5 & 16;
            if (i11 != 0) {
                if ((i4 & 24576) == 0) {
                    start = horizontal2;
                    if (composerStartRestartGroup.changed(start)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i6 |= i12;
                }
                i13 = i5 & 32;
                if (i13 != 0) {
                    i6 |= 196608;
                } else if ((i4 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i6 |= i14;
                }
                i15 = i5 & 64;
                if (i15 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i6 |= i16;
                }
                i17 = i5 & 128;
                if (i17 != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((i4 & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i25 = 67108864;
                    } else {
                        i25 = 33554432;
                    }
                    i6 |= i25;
                }
                if ((i6 & 38347923) != 38347922) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                    if (i26 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i7 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i9 != 0) {
                        start2 = Arrangement.INSTANCE.getStart();
                        i21 = i11;
                    } else {
                        i21 = i11;
                        start2 = horizontal;
                    }
                    if (i21 != 0) {
                        start = Alignment.Companion.getStart();
                    }
                    if (i13 != 0) {
                        i22 = Integer.MAX_VALUE;
                    } else {
                        i22 = i2;
                    }
                    if (i15 != 0) {
                        i23 = Integer.MAX_VALUE;
                    } else {
                        i23 = i3;
                    }
                    if (i17 != 0) {
                        clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1986851536, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:154)");
                    }
                    i24 = 29360128 & i6;
                    if (i24 == 8388608) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    if (i24 == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z3) {
                        obj = objRememberedValue2;
                        ArrayList arrayList110 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList110);
                        composerStartRestartGroup.updateRememberedValue(arrayList110);
                        obj = arrayList110;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList111 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList111);
                        composerStartRestartGroup.updateRememberedValue(arrayList111);
                        obj = arrayList111;
                    }
                    obj = objRememberedValue2;
                    int i217 = i6 >> 6;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(top, start2, start, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(620176540, true, new Function4() { // from class: aw2
                        public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                            return ContextualFlowLayoutKt.b(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i217 & 57344) | (i217 & 14) | 100663296 | (i217 & 112) | (i217 & 896) | (i217 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowColumnOverflow2 = clip;
                    vertical3 = top;
                    horizontal3 = start2;
                    i19 = i22;
                    i20 = i23;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    horizontal3 = horizontal;
                    i19 = i2;
                    contextualFlowColumnOverflow2 = contextualFlowColumnOverflow;
                    vertical3 = vertical2;
                    i20 = i3;
                }
                modifier3 = modifier2;
                horizontal4 = start;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bw2
                        public final Object invoke(Object obj2, Object obj3) {
                            return ContextualFlowLayoutKt.d(i, modifier3, vertical3, horizontal3, horizontal4, i19, i20, contextualFlowColumnOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i6 |= 24576;
            start = horizontal2;
            i13 = i5 & 32;
            if (i13 != 0) {
                i6 |= 196608;
            } else if ((i4 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i6 |= i14;
            }
            i15 = i5 & 64;
            if (i15 != 0) {
                i6 |= 1572864;
            } else if ((i4 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(i3)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i6 |= i16;
            }
            i17 = i5 & 128;
            if (i17 != 0) {
                i6 |= 12582912;
            } else if ((i4 & 12582912) == 0) {
                if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                    i18 = 8388608;
                } else {
                    i18 = 4194304;
                }
                i6 |= i18;
            }
            if ((i4 & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i25 = 67108864;
                } else {
                    i25 = 33554432;
                }
                i6 |= i25;
            }
            if ((i6 & 38347923) != 38347922) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                if (i26 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i7 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical2;
                }
                if (i9 != 0) {
                    start2 = Arrangement.INSTANCE.getStart();
                    i21 = i11;
                } else {
                    i21 = i11;
                    start2 = horizontal;
                }
                if (i21 != 0) {
                    start = Alignment.Companion.getStart();
                }
                if (i13 != 0) {
                    i22 = Integer.MAX_VALUE;
                } else {
                    i22 = i2;
                }
                if (i15 != 0) {
                    i23 = Integer.MAX_VALUE;
                } else {
                    i23 = i3;
                }
                if (i17 != 0) {
                    clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1986851536, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:154)");
                }
                i24 = 29360128 & i6;
                if (i24 == 8388608) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                if (i24 == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z3) {
                    obj = objRememberedValue2;
                    ArrayList arrayList112 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList112);
                    composerStartRestartGroup.updateRememberedValue(arrayList112);
                    obj = arrayList112;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList113 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList113);
                    composerStartRestartGroup.updateRememberedValue(arrayList113);
                    obj = arrayList113;
                }
                obj = objRememberedValue2;
                int i218 = i6 >> 6;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(top, start2, start, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(620176540, true, new Function4() { // from class: aw2
                    public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                        return ContextualFlowLayoutKt.b(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i218 & 57344) | (i218 & 14) | 100663296 | (i218 & 112) | (i218 & 896) | (i218 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowColumnOverflow2 = clip;
                vertical3 = top;
                horizontal3 = start2;
                i19 = i22;
                i20 = i23;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                horizontal3 = horizontal;
                i19 = i2;
                contextualFlowColumnOverflow2 = contextualFlowColumnOverflow;
                vertical3 = vertical2;
                i20 = i3;
            }
            modifier3 = modifier2;
            horizontal4 = start;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bw2
                    public final Object invoke(Object obj2, Object obj3) {
                        return ContextualFlowLayoutKt.d(i, modifier3, vertical3, horizontal3, horizontal4, i19, i20, contextualFlowColumnOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i6 |= 384;
        vertical2 = vertical;
        i9 = i5 & 8;
        if (i9 != 0) {
            if ((i4 & 3072) == 0) {
                if (composerStartRestartGroup.changed(horizontal)) {
                    i10 = 2048;
                } else {
                    i10 = 1024;
                }
                i6 |= i10;
            }
            i11 = i5 & 16;
            if (i11 != 0) {
                if ((i4 & 24576) == 0) {
                    start = horizontal2;
                    if (composerStartRestartGroup.changed(start)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i6 |= i12;
                }
                i13 = i5 & 32;
                if (i13 != 0) {
                    i6 |= 196608;
                } else if ((i4 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i6 |= i14;
                }
                i15 = i5 & 64;
                if (i15 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i6 |= i16;
                }
                i17 = i5 & 128;
                if (i17 != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((i4 & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i25 = 67108864;
                    } else {
                        i25 = 33554432;
                    }
                    i6 |= i25;
                }
                if ((i6 & 38347923) != 38347922) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                    if (i26 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i7 != 0) {
                        top = Arrangement.INSTANCE.getTop();
                    } else {
                        top = vertical2;
                    }
                    if (i9 != 0) {
                        start2 = Arrangement.INSTANCE.getStart();
                        i21 = i11;
                    } else {
                        i21 = i11;
                        start2 = horizontal;
                    }
                    if (i21 != 0) {
                        start = Alignment.Companion.getStart();
                    }
                    if (i13 != 0) {
                        i22 = Integer.MAX_VALUE;
                    } else {
                        i22 = i2;
                    }
                    if (i15 != 0) {
                        i23 = Integer.MAX_VALUE;
                    } else {
                        i23 = i3;
                    }
                    if (i17 != 0) {
                        clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowColumnOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1986851536, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:154)");
                    }
                    i24 = 29360128 & i6;
                    if (i24 == 8388608) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    if (i24 == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z3) {
                        obj = objRememberedValue2;
                        ArrayList arrayList114 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList114);
                        composerStartRestartGroup.updateRememberedValue(arrayList114);
                        obj = arrayList114;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList115 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList115);
                        composerStartRestartGroup.updateRememberedValue(arrayList115);
                        obj = arrayList115;
                    }
                    obj = objRememberedValue2;
                    int i219 = i6 >> 6;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(top, start2, start, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(620176540, true, new Function4() { // from class: aw2
                        public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                            return ContextualFlowLayoutKt.b(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i219 & 57344) | (i219 & 14) | 100663296 | (i219 & 112) | (i219 & 896) | (i219 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowColumnOverflow2 = clip;
                    vertical3 = top;
                    horizontal3 = start2;
                    i19 = i22;
                    i20 = i23;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    horizontal3 = horizontal;
                    i19 = i2;
                    contextualFlowColumnOverflow2 = contextualFlowColumnOverflow;
                    vertical3 = vertical2;
                    i20 = i3;
                }
                modifier3 = modifier2;
                horizontal4 = start;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bw2
                        public final Object invoke(Object obj2, Object obj3) {
                            return ContextualFlowLayoutKt.d(i, modifier3, vertical3, horizontal3, horizontal4, i19, i20, contextualFlowColumnOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i6 |= 24576;
            start = horizontal2;
            i13 = i5 & 32;
            if (i13 != 0) {
                i6 |= 196608;
            } else if ((i4 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i6 |= i14;
            }
            i15 = i5 & 64;
            if (i15 != 0) {
                i6 |= 1572864;
            } else if ((i4 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(i3)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i6 |= i16;
            }
            i17 = i5 & 128;
            if (i17 != 0) {
                i6 |= 12582912;
            } else if ((i4 & 12582912) == 0) {
                if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                    i18 = 8388608;
                } else {
                    i18 = 4194304;
                }
                i6 |= i18;
            }
            if ((i4 & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i25 = 67108864;
                } else {
                    i25 = 33554432;
                }
                i6 |= i25;
            }
            if ((i6 & 38347923) != 38347922) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                if (i26 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i7 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical2;
                }
                if (i9 != 0) {
                    start2 = Arrangement.INSTANCE.getStart();
                    i21 = i11;
                } else {
                    i21 = i11;
                    start2 = horizontal;
                }
                if (i21 != 0) {
                    start = Alignment.Companion.getStart();
                }
                if (i13 != 0) {
                    i22 = Integer.MAX_VALUE;
                } else {
                    i22 = i2;
                }
                if (i15 != 0) {
                    i23 = Integer.MAX_VALUE;
                } else {
                    i23 = i3;
                }
                if (i17 != 0) {
                    clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1986851536, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:154)");
                }
                i24 = 29360128 & i6;
                if (i24 == 8388608) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                if (i24 == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z3) {
                    obj = objRememberedValue2;
                    ArrayList arrayList116 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList116);
                    composerStartRestartGroup.updateRememberedValue(arrayList116);
                    obj = arrayList116;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList117 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList117);
                    composerStartRestartGroup.updateRememberedValue(arrayList117);
                    obj = arrayList117;
                }
                obj = objRememberedValue2;
                int i2110 = i6 >> 6;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(top, start2, start, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(620176540, true, new Function4() { // from class: aw2
                    public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                        return ContextualFlowLayoutKt.b(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2110 & 57344) | (i2110 & 14) | 100663296 | (i2110 & 112) | (i2110 & 896) | (i2110 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowColumnOverflow2 = clip;
                vertical3 = top;
                horizontal3 = start2;
                i19 = i22;
                i20 = i23;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                horizontal3 = horizontal;
                i19 = i2;
                contextualFlowColumnOverflow2 = contextualFlowColumnOverflow;
                vertical3 = vertical2;
                i20 = i3;
            }
            modifier3 = modifier2;
            horizontal4 = start;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bw2
                    public final Object invoke(Object obj2, Object obj3) {
                        return ContextualFlowLayoutKt.d(i, modifier3, vertical3, horizontal3, horizontal4, i19, i20, contextualFlowColumnOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i6 |= 3072;
        i11 = i5 & 16;
        if (i11 != 0) {
            if ((i4 & 24576) == 0) {
                start = horizontal2;
                if (composerStartRestartGroup.changed(start)) {
                    i12 = 16384;
                } else {
                    i12 = 8192;
                }
                i6 |= i12;
            }
            i13 = i5 & 32;
            if (i13 != 0) {
                i6 |= 196608;
            } else if ((i4 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i6 |= i14;
            }
            i15 = i5 & 64;
            if (i15 != 0) {
                i6 |= 1572864;
            } else if ((i4 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(i3)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i6 |= i16;
            }
            i17 = i5 & 128;
            if (i17 != 0) {
                i6 |= 12582912;
            } else if ((i4 & 12582912) == 0) {
                if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                    i18 = 8388608;
                } else {
                    i18 = 4194304;
                }
                i6 |= i18;
            }
            if ((i4 & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i25 = 67108864;
                } else {
                    i25 = 33554432;
                }
                i6 |= i25;
            }
            if ((i6 & 38347923) != 38347922) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                if (i26 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i7 != 0) {
                    top = Arrangement.INSTANCE.getTop();
                } else {
                    top = vertical2;
                }
                if (i9 != 0) {
                    start2 = Arrangement.INSTANCE.getStart();
                    i21 = i11;
                } else {
                    i21 = i11;
                    start2 = horizontal;
                }
                if (i21 != 0) {
                    start = Alignment.Companion.getStart();
                }
                if (i13 != 0) {
                    i22 = Integer.MAX_VALUE;
                } else {
                    i22 = i2;
                }
                if (i15 != 0) {
                    i23 = Integer.MAX_VALUE;
                } else {
                    i23 = i3;
                }
                if (i17 != 0) {
                    clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowColumnOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1986851536, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:154)");
                }
                i24 = 29360128 & i6;
                if (i24 == 8388608) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                if (i24 == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z3) {
                    obj = objRememberedValue2;
                    ArrayList arrayList118 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList118);
                    composerStartRestartGroup.updateRememberedValue(arrayList118);
                    obj = arrayList118;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList119 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList119);
                    composerStartRestartGroup.updateRememberedValue(arrayList119);
                    obj = arrayList119;
                }
                obj = objRememberedValue2;
                int i2111 = i6 >> 6;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(top, start2, start, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(620176540, true, new Function4() { // from class: aw2
                    public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                        return ContextualFlowLayoutKt.b(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2111 & 57344) | (i2111 & 14) | 100663296 | (i2111 & 112) | (i2111 & 896) | (i2111 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowColumnOverflow2 = clip;
                vertical3 = top;
                horizontal3 = start2;
                i19 = i22;
                i20 = i23;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                horizontal3 = horizontal;
                i19 = i2;
                contextualFlowColumnOverflow2 = contextualFlowColumnOverflow;
                vertical3 = vertical2;
                i20 = i3;
            }
            modifier3 = modifier2;
            horizontal4 = start;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bw2
                    public final Object invoke(Object obj2, Object obj3) {
                        return ContextualFlowLayoutKt.d(i, modifier3, vertical3, horizontal3, horizontal4, i19, i20, contextualFlowColumnOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i6 |= 24576;
        start = horizontal2;
        i13 = i5 & 32;
        if (i13 != 0) {
            i6 |= 196608;
        } else if ((i4 & 196608) == 0) {
            if (composerStartRestartGroup.changed(i2)) {
                i14 = 131072;
            } else {
                i14 = 65536;
            }
            i6 |= i14;
        }
        i15 = i5 & 64;
        if (i15 != 0) {
            i6 |= 1572864;
        } else if ((i4 & 1572864) == 0) {
            if (composerStartRestartGroup.changed(i3)) {
                i16 = IOUtil.MiB;
            } else {
                i16 = 524288;
            }
            i6 |= i16;
        }
        i17 = i5 & 128;
        if (i17 != 0) {
            i6 |= 12582912;
        } else if ((i4 & 12582912) == 0) {
            if (composerStartRestartGroup.changed(contextualFlowColumnOverflow)) {
                i18 = 8388608;
            } else {
                i18 = 4194304;
            }
            i6 |= i18;
        }
        if ((i4 & 100663296) == 0) {
            if (composerStartRestartGroup.changedInstance(function4)) {
                i25 = 67108864;
            } else {
                i25 = 33554432;
            }
            i6 |= i25;
        }
        if ((i6 & 38347923) != 38347922) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
            if (i26 != 0) {
                modifier2 = Modifier.Companion;
            }
            if (i7 != 0) {
                top = Arrangement.INSTANCE.getTop();
            } else {
                top = vertical2;
            }
            if (i9 != 0) {
                start2 = Arrangement.INSTANCE.getStart();
                i21 = i11;
            } else {
                i21 = i11;
                start2 = horizontal;
            }
            if (i21 != 0) {
                start = Alignment.Companion.getStart();
            }
            if (i13 != 0) {
                i22 = Integer.MAX_VALUE;
            } else {
                i22 = i2;
            }
            if (i15 != 0) {
                i23 = Integer.MAX_VALUE;
            } else {
                i23 = i3;
            }
            if (i17 != 0) {
                clip = ContextualFlowColumnOverflow.INSTANCE.getClip();
            } else {
                clip = contextualFlowColumnOverflow;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1986851536, i6, -1, "androidx.compose.foundation.layout.ContextualFlowColumn (ContextualFlowLayout.kt:154)");
            }
            i24 = 29360128 & i6;
            if (i24 == 8388608) {
                z2 = true;
            } else {
                z2 = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2) {
                objRememberedValue = clip.createOverflowState$foundation_layout();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = clip.createOverflowState$foundation_layout();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
            if (i24 == 8388608) {
                z3 = true;
            } else {
                z3 = false;
            }
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z3) {
                obj = objRememberedValue2;
                ArrayList arrayList1110 = new ArrayList();
                clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList1110);
                composerStartRestartGroup.updateRememberedValue(arrayList1110);
                obj = arrayList1110;
            } else {
                obj = objRememberedValue2;
                ArrayList arrayList1111 = new ArrayList();
                clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList1111);
                composerStartRestartGroup.updateRememberedValue(arrayList1111);
                obj = arrayList1111;
            }
            obj = objRememberedValue2;
            int i2112 = i6 >> 6;
            SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualColumnMeasureHelper(top, start2, start, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(620176540, true, new Function4() { // from class: aw2
                public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                    return ContextualFlowLayoutKt.b(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2112 & 57344) | (i2112 & 14) | 100663296 | (i2112 & 112) | (i2112 & 896) | (i2112 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            contextualFlowColumnOverflow2 = clip;
            vertical3 = top;
            horizontal3 = start2;
            i19 = i22;
            i20 = i23;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            horizontal3 = horizontal;
            i19 = i2;
            contextualFlowColumnOverflow2 = contextualFlowColumnOverflow;
            vertical3 = vertical2;
            i20 = i3;
        }
        modifier3 = modifier2;
        horizontal4 = start;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: bw2
                public final Object invoke(Object obj2, Object obj3) {
                    return ContextualFlowLayoutKt.d(i, modifier3, vertical3, horizontal3, horizontal4, i19, i20, contextualFlowColumnOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x011d  */
    /* JADX WARN: Code duplicated, block: B:101:0x0125  */
    /* JADX WARN: Code duplicated, block: B:103:0x0128  */
    /* JADX WARN: Code duplicated, block: B:104:0x0134  */
    /* JADX WARN: Code duplicated, block: B:106:0x0139  */
    /* JADX WARN: Code duplicated, block: B:109:0x0145  */
    /* JADX WARN: Code duplicated, block: B:110:0x0147  */
    /* JADX WARN: Code duplicated, block: B:112:0x014b  */
    /* JADX WARN: Code duplicated, block: B:114:0x0151  */
    /* JADX WARN: Code duplicated, block: B:116:0x0156  */
    /* JADX WARN: Code duplicated, block: B:117:0x015d  */
    /* JADX WARN: Code duplicated, block: B:120:0x0165  */
    /* JADX WARN: Code duplicated, block: B:123:0x0172  */
    /* JADX WARN: Code duplicated, block: B:124:0x0174  */
    /* JADX WARN: Code duplicated, block: B:127:0x017b  */
    /* JADX WARN: Code duplicated, block: B:129:0x0183  */
    /* JADX WARN: Code duplicated, block: B:132:0x0190  */
    /* JADX WARN: Code duplicated, block: B:133:0x0192  */
    /* JADX WARN: Code duplicated, block: B:136:0x0199  */
    /* JADX WARN: Code duplicated, block: B:138:0x01a1  */
    /* JADX WARN: Code duplicated, block: B:141:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:144:0x01fd  */
    /* JADX WARN: Code duplicated, block: B:147:0x0210  */
    /* JADX WARN: Code duplicated, block: B:149:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:23:0x0042  */
    /* JADX WARN: Code duplicated, block: B:25:0x0047  */
    /* JADX WARN: Code duplicated, block: B:27:0x004b  */
    /* JADX WARN: Code duplicated, block: B:29:0x0053  */
    /* JADX WARN: Code duplicated, block: B:30:0x0056  */
    /* JADX WARN: Code duplicated, block: B:34:0x005d  */
    /* JADX WARN: Code duplicated, block: B:36:0x0062  */
    /* JADX WARN: Code duplicated, block: B:38:0x0066  */
    /* JADX WARN: Code duplicated, block: B:40:0x006e  */
    /* JADX WARN: Code duplicated, block: B:41:0x0071  */
    /* JADX WARN: Code duplicated, block: B:45:0x0078  */
    /* JADX WARN: Code duplicated, block: B:47:0x007d  */
    /* JADX WARN: Code duplicated, block: B:49:0x0081  */
    /* JADX WARN: Code duplicated, block: B:51:0x0089  */
    /* JADX WARN: Code duplicated, block: B:52:0x008c  */
    /* JADX WARN: Code duplicated, block: B:56:0x0095  */
    /* JADX WARN: Code duplicated, block: B:57:0x009a  */
    /* JADX WARN: Code duplicated, block: B:59:0x00a0  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a9  */
    /* JADX WARN: Code duplicated, block: B:66:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b8  */
    /* JADX WARN: Code duplicated, block: B:69:0x00be  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c7  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:77:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:79:0x00de  */
    /* JADX WARN: Code duplicated, block: B:81:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:82:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:86:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:88:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:89:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:93:0x010b  */
    /* JADX WARN: Code duplicated, block: B:94:0x010d  */
    /* JADX WARN: Code duplicated, block: B:97:0x0116 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:98:0x0118  */
    @Deprecated(message = "ContextualFlowLayouts are no longer maintained")
    public static final void ContextualFlowRow(final int i, Modifier modifier, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, Alignment.Vertical vertical2, int i2, int i3, ContextualFlowRowOverflow contextualFlowRowOverflow, final Function4<? super ContextualFlowRowScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer composer, final int i4, final int i5) {
        int i6;
        Modifier modifier2;
        int i7;
        Arrangement.Horizontal horizontal2;
        int i8;
        int i9;
        int i10;
        int i11;
        Alignment.Vertical top;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        int i17;
        int i18;
        boolean z;
        final Arrangement.Vertical vertical3;
        final int i19;
        final ContextualFlowRowOverflow contextualFlowRowOverflow2;
        final Arrangement.Horizontal horizontal3;
        final int i20;
        final Modifier modifier3;
        final Alignment.Vertical vertical4;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Arrangement.Horizontal start;
        int i21;
        Arrangement.Vertical top2;
        int i22;
        int i23;
        ContextualFlowRowOverflow clip;
        int i24;
        boolean z2;
        Object objRememberedValue;
        FlowLayoutOverflowState flowLayoutOverflowState;
        boolean z3;
        Object objRememberedValue2;
        Object obj;
        int i25;
        Composer composerStartRestartGroup = composer.startRestartGroup(-294153140);
        if ((i4 & 6) == 0) {
            i6 = (composerStartRestartGroup.changed(i) ? 4 : 2) | i4;
        } else {
            i6 = i4;
        }
        int i26 = i5 & 2;
        if (i26 == 0) {
            if ((i4 & 48) == 0) {
                modifier2 = modifier;
                i6 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i7 = i5 & 4;
            if (i7 != 0) {
                if ((i4 & 384) == 0) {
                    horizontal2 = horizontal;
                    if (composerStartRestartGroup.changed(horizontal2)) {
                        i8 = 256;
                    } else {
                        i8 = 128;
                    }
                    i6 |= i8;
                }
                i9 = i5 & 8;
                if (i9 != 0) {
                    if ((i4 & 3072) == 0) {
                        if (composerStartRestartGroup.changed(vertical)) {
                            i10 = 2048;
                        } else {
                            i10 = 1024;
                        }
                        i6 |= i10;
                    }
                    i11 = i5 & 16;
                    if (i11 != 0) {
                        if ((i4 & 24576) == 0) {
                            top = vertical2;
                            if (composerStartRestartGroup.changed(top)) {
                                i12 = 16384;
                            } else {
                                i12 = 8192;
                            }
                            i6 |= i12;
                        }
                        i13 = i5 & 32;
                        if (i13 != 0) {
                            i6 |= 196608;
                        } else if ((i4 & 196608) == 0) {
                            if (composerStartRestartGroup.changed(i2)) {
                                i14 = 131072;
                            } else {
                                i14 = 65536;
                            }
                            i6 |= i14;
                        }
                        i15 = i5 & 64;
                        if (i15 != 0) {
                            i6 |= 1572864;
                        } else if ((i4 & 1572864) == 0) {
                            if (composerStartRestartGroup.changed(i3)) {
                                i16 = IOUtil.MiB;
                            } else {
                                i16 = 524288;
                            }
                            i6 |= i16;
                        }
                        i17 = i5 & 128;
                        if (i17 != 0) {
                            i6 |= 12582912;
                        } else if ((i4 & 12582912) == 0) {
                            if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                                i18 = 8388608;
                            } else {
                                i18 = 4194304;
                            }
                            i6 |= i18;
                        }
                        if ((i4 & 100663296) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i25 = 67108864;
                            } else {
                                i25 = 33554432;
                            }
                            i6 |= i25;
                        }
                        if ((i6 & 38347923) != 38347922) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                            if (i26 != 0) {
                                modifier2 = Modifier.Companion;
                            }
                            if (i7 != 0) {
                                start = Arrangement.INSTANCE.getStart();
                            } else {
                                start = horizontal2;
                            }
                            if (i9 != 0) {
                                top2 = Arrangement.INSTANCE.getTop();
                                i21 = i11;
                            } else {
                                i21 = i11;
                                top2 = vertical;
                            }
                            if (i21 != 0) {
                                top = Alignment.Companion.getTop();
                            }
                            if (i13 != 0) {
                                i22 = Integer.MAX_VALUE;
                            } else {
                                i22 = i2;
                            }
                            if (i15 != 0) {
                                i23 = Integer.MAX_VALUE;
                            } else {
                                i23 = i3;
                            }
                            if (i17 != 0) {
                                clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                            } else {
                                clip = contextualFlowRowOverflow;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-294153140, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:79)");
                            }
                            i24 = 29360128 & i6;
                            if (i24 == 8388608) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
                                objRememberedValue = clip.createOverflowState$foundation_layout();
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                            if (i24 == 8388608) {
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z3 || objRememberedValue2 == Composer.Companion.getEmpty()) {
                                obj = objRememberedValue2;
                                ArrayList arrayList = new ArrayList();
                                clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList);
                                composerStartRestartGroup.updateRememberedValue(arrayList);
                                obj = arrayList;
                            }
                            obj = objRememberedValue2;
                            int i27 = i6 >> 6;
                            SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(start, top2, top, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-1677845586, true, new Function4() { // from class: yv2
                                public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                                    return ContextualFlowLayoutKt.c(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                                }
                            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i27 & 57344) | (i27 & 14) | 100663296 | (i27 & 112) | (i27 & 896) | (i27 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            contextualFlowRowOverflow2 = clip;
                            horizontal3 = start;
                            vertical3 = top2;
                            i19 = i22;
                            i20 = i23;
                        } else {
                            composerStartRestartGroup.skipToGroupEnd();
                            vertical3 = vertical;
                            i19 = i2;
                            contextualFlowRowOverflow2 = contextualFlowRowOverflow;
                            horizontal3 = horizontal2;
                            i20 = i3;
                        }
                        modifier3 = modifier2;
                        vertical4 = top;
                        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zv2
                                public final Object invoke(Object obj2, Object obj3) {
                                    return ContextualFlowLayoutKt.a(i, modifier3, horizontal3, vertical3, vertical4, i19, i20, contextualFlowRowOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                                }
                            });
                        }
                    }
                    i6 |= 24576;
                    top = vertical2;
                    i13 = i5 & 32;
                    if (i13 != 0) {
                        i6 |= 196608;
                    } else if ((i4 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i6 |= i14;
                    }
                    i15 = i5 & 64;
                    if (i15 != 0) {
                        i6 |= 1572864;
                    } else if ((i4 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(i3)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i6 |= i16;
                    }
                    i17 = i5 & 128;
                    if (i17 != 0) {
                        i6 |= 12582912;
                    } else if ((i4 & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                            i18 = 8388608;
                        } else {
                            i18 = 4194304;
                        }
                        i6 |= i18;
                    }
                    if ((i4 & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i25 = 67108864;
                        } else {
                            i25 = 33554432;
                        }
                        i6 |= i25;
                    }
                    if ((i6 & 38347923) != 38347922) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                        if (i26 != 0) {
                            modifier2 = Modifier.Companion;
                        }
                        if (i7 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if (i9 != 0) {
                            top2 = Arrangement.INSTANCE.getTop();
                            i21 = i11;
                        } else {
                            i21 = i11;
                            top2 = vertical;
                        }
                        if (i21 != 0) {
                            top = Alignment.Companion.getTop();
                        }
                        if (i13 != 0) {
                            i22 = Integer.MAX_VALUE;
                        } else {
                            i22 = i2;
                        }
                        if (i15 != 0) {
                            i23 = Integer.MAX_VALUE;
                        } else {
                            i23 = i3;
                        }
                        if (i17 != 0) {
                            clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-294153140, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:79)");
                        }
                        i24 = 29360128 & i6;
                        if (i24 == 8388608) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        if (i24 == 8388608) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z3) {
                            obj = objRememberedValue2;
                            ArrayList arrayList2 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList2);
                            composerStartRestartGroup.updateRememberedValue(arrayList2);
                            obj = arrayList2;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList3 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList3);
                            composerStartRestartGroup.updateRememberedValue(arrayList3);
                            obj = arrayList3;
                        }
                        obj = objRememberedValue2;
                        int i28 = i6 >> 6;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(start, top2, top, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-1677845586, true, new Function4() { // from class: yv2
                            public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                                return ContextualFlowLayoutKt.c(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i28 & 57344) | (i28 & 14) | 100663296 | (i28 & 112) | (i28 & 896) | (i28 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowRowOverflow2 = clip;
                        horizontal3 = start;
                        vertical3 = top2;
                        i19 = i22;
                        i20 = i23;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        vertical3 = vertical;
                        i19 = i2;
                        contextualFlowRowOverflow2 = contextualFlowRowOverflow;
                        horizontal3 = horizontal2;
                        i20 = i3;
                    }
                    modifier3 = modifier2;
                    vertical4 = top;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zv2
                            public final Object invoke(Object obj2, Object obj3) {
                                return ContextualFlowLayoutKt.a(i, modifier3, horizontal3, vertical3, vertical4, i19, i20, contextualFlowRowOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i6 |= 3072;
                i11 = i5 & 16;
                if (i11 != 0) {
                    if ((i4 & 24576) == 0) {
                        top = vertical2;
                        if (composerStartRestartGroup.changed(top)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i6 |= i12;
                    }
                    i13 = i5 & 32;
                    if (i13 != 0) {
                        i6 |= 196608;
                    } else if ((i4 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i6 |= i14;
                    }
                    i15 = i5 & 64;
                    if (i15 != 0) {
                        i6 |= 1572864;
                    } else if ((i4 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(i3)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i6 |= i16;
                    }
                    i17 = i5 & 128;
                    if (i17 != 0) {
                        i6 |= 12582912;
                    } else if ((i4 & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                            i18 = 8388608;
                        } else {
                            i18 = 4194304;
                        }
                        i6 |= i18;
                    }
                    if ((i4 & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i25 = 67108864;
                        } else {
                            i25 = 33554432;
                        }
                        i6 |= i25;
                    }
                    if ((i6 & 38347923) != 38347922) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                        if (i26 != 0) {
                            modifier2 = Modifier.Companion;
                        }
                        if (i7 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if (i9 != 0) {
                            top2 = Arrangement.INSTANCE.getTop();
                            i21 = i11;
                        } else {
                            i21 = i11;
                            top2 = vertical;
                        }
                        if (i21 != 0) {
                            top = Alignment.Companion.getTop();
                        }
                        if (i13 != 0) {
                            i22 = Integer.MAX_VALUE;
                        } else {
                            i22 = i2;
                        }
                        if (i15 != 0) {
                            i23 = Integer.MAX_VALUE;
                        } else {
                            i23 = i3;
                        }
                        if (i17 != 0) {
                            clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-294153140, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:79)");
                        }
                        i24 = 29360128 & i6;
                        if (i24 == 8388608) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        if (i24 == 8388608) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z3) {
                            obj = objRememberedValue2;
                            ArrayList arrayList4 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList4);
                            composerStartRestartGroup.updateRememberedValue(arrayList4);
                            obj = arrayList4;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList5 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList5);
                            composerStartRestartGroup.updateRememberedValue(arrayList5);
                            obj = arrayList5;
                        }
                        obj = objRememberedValue2;
                        int i29 = i6 >> 6;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(start, top2, top, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-1677845586, true, new Function4() { // from class: yv2
                            public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                                return ContextualFlowLayoutKt.c(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i29 & 57344) | (i29 & 14) | 100663296 | (i29 & 112) | (i29 & 896) | (i29 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowRowOverflow2 = clip;
                        horizontal3 = start;
                        vertical3 = top2;
                        i19 = i22;
                        i20 = i23;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        vertical3 = vertical;
                        i19 = i2;
                        contextualFlowRowOverflow2 = contextualFlowRowOverflow;
                        horizontal3 = horizontal2;
                        i20 = i3;
                    }
                    modifier3 = modifier2;
                    vertical4 = top;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zv2
                            public final Object invoke(Object obj2, Object obj3) {
                                return ContextualFlowLayoutKt.a(i, modifier3, horizontal3, vertical3, vertical4, i19, i20, contextualFlowRowOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i6 |= 24576;
                top = vertical2;
                i13 = i5 & 32;
                if (i13 != 0) {
                    i6 |= 196608;
                } else if ((i4 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i6 |= i14;
                }
                i15 = i5 & 64;
                if (i15 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i6 |= i16;
                }
                i17 = i5 & 128;
                if (i17 != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((i4 & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i25 = 67108864;
                    } else {
                        i25 = 33554432;
                    }
                    i6 |= i25;
                }
                if ((i6 & 38347923) != 38347922) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                    if (i26 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i7 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i9 != 0) {
                        top2 = Arrangement.INSTANCE.getTop();
                        i21 = i11;
                    } else {
                        i21 = i11;
                        top2 = vertical;
                    }
                    if (i21 != 0) {
                        top = Alignment.Companion.getTop();
                    }
                    if (i13 != 0) {
                        i22 = Integer.MAX_VALUE;
                    } else {
                        i22 = i2;
                    }
                    if (i15 != 0) {
                        i23 = Integer.MAX_VALUE;
                    } else {
                        i23 = i3;
                    }
                    if (i17 != 0) {
                        clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-294153140, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:79)");
                    }
                    i24 = 29360128 & i6;
                    if (i24 == 8388608) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    if (i24 == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z3) {
                        obj = objRememberedValue2;
                        ArrayList arrayList6 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList6);
                        composerStartRestartGroup.updateRememberedValue(arrayList6);
                        obj = arrayList6;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList7 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList7);
                        composerStartRestartGroup.updateRememberedValue(arrayList7);
                        obj = arrayList7;
                    }
                    obj = objRememberedValue2;
                    int i210 = i6 >> 6;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(start, top2, top, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-1677845586, true, new Function4() { // from class: yv2
                        public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                            return ContextualFlowLayoutKt.c(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i210 & 57344) | (i210 & 14) | 100663296 | (i210 & 112) | (i210 & 896) | (i210 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowRowOverflow2 = clip;
                    horizontal3 = start;
                    vertical3 = top2;
                    i19 = i22;
                    i20 = i23;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    vertical3 = vertical;
                    i19 = i2;
                    contextualFlowRowOverflow2 = contextualFlowRowOverflow;
                    horizontal3 = horizontal2;
                    i20 = i3;
                }
                modifier3 = modifier2;
                vertical4 = top;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zv2
                        public final Object invoke(Object obj2, Object obj3) {
                            return ContextualFlowLayoutKt.a(i, modifier3, horizontal3, vertical3, vertical4, i19, i20, contextualFlowRowOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i6 |= 384;
            horizontal2 = horizontal;
            i9 = i5 & 8;
            if (i9 != 0) {
                if ((i4 & 3072) == 0) {
                    if (composerStartRestartGroup.changed(vertical)) {
                        i10 = 2048;
                    } else {
                        i10 = 1024;
                    }
                    i6 |= i10;
                }
                i11 = i5 & 16;
                if (i11 != 0) {
                    if ((i4 & 24576) == 0) {
                        top = vertical2;
                        if (composerStartRestartGroup.changed(top)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i6 |= i12;
                    }
                    i13 = i5 & 32;
                    if (i13 != 0) {
                        i6 |= 196608;
                    } else if ((i4 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i6 |= i14;
                    }
                    i15 = i5 & 64;
                    if (i15 != 0) {
                        i6 |= 1572864;
                    } else if ((i4 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(i3)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i6 |= i16;
                    }
                    i17 = i5 & 128;
                    if (i17 != 0) {
                        i6 |= 12582912;
                    } else if ((i4 & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                            i18 = 8388608;
                        } else {
                            i18 = 4194304;
                        }
                        i6 |= i18;
                    }
                    if ((i4 & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i25 = 67108864;
                        } else {
                            i25 = 33554432;
                        }
                        i6 |= i25;
                    }
                    if ((i6 & 38347923) != 38347922) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                        if (i26 != 0) {
                            modifier2 = Modifier.Companion;
                        }
                        if (i7 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if (i9 != 0) {
                            top2 = Arrangement.INSTANCE.getTop();
                            i21 = i11;
                        } else {
                            i21 = i11;
                            top2 = vertical;
                        }
                        if (i21 != 0) {
                            top = Alignment.Companion.getTop();
                        }
                        if (i13 != 0) {
                            i22 = Integer.MAX_VALUE;
                        } else {
                            i22 = i2;
                        }
                        if (i15 != 0) {
                            i23 = Integer.MAX_VALUE;
                        } else {
                            i23 = i3;
                        }
                        if (i17 != 0) {
                            clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-294153140, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:79)");
                        }
                        i24 = 29360128 & i6;
                        if (i24 == 8388608) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        if (i24 == 8388608) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z3) {
                            obj = objRememberedValue2;
                            ArrayList arrayList8 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList8);
                            composerStartRestartGroup.updateRememberedValue(arrayList8);
                            obj = arrayList8;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList9 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList9);
                            composerStartRestartGroup.updateRememberedValue(arrayList9);
                            obj = arrayList9;
                        }
                        obj = objRememberedValue2;
                        int i211 = i6 >> 6;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(start, top2, top, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-1677845586, true, new Function4() { // from class: yv2
                            public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                                return ContextualFlowLayoutKt.c(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i211 & 57344) | (i211 & 14) | 100663296 | (i211 & 112) | (i211 & 896) | (i211 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowRowOverflow2 = clip;
                        horizontal3 = start;
                        vertical3 = top2;
                        i19 = i22;
                        i20 = i23;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        vertical3 = vertical;
                        i19 = i2;
                        contextualFlowRowOverflow2 = contextualFlowRowOverflow;
                        horizontal3 = horizontal2;
                        i20 = i3;
                    }
                    modifier3 = modifier2;
                    vertical4 = top;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zv2
                            public final Object invoke(Object obj2, Object obj3) {
                                return ContextualFlowLayoutKt.a(i, modifier3, horizontal3, vertical3, vertical4, i19, i20, contextualFlowRowOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i6 |= 24576;
                top = vertical2;
                i13 = i5 & 32;
                if (i13 != 0) {
                    i6 |= 196608;
                } else if ((i4 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i6 |= i14;
                }
                i15 = i5 & 64;
                if (i15 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i6 |= i16;
                }
                i17 = i5 & 128;
                if (i17 != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((i4 & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i25 = 67108864;
                    } else {
                        i25 = 33554432;
                    }
                    i6 |= i25;
                }
                if ((i6 & 38347923) != 38347922) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                    if (i26 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i7 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i9 != 0) {
                        top2 = Arrangement.INSTANCE.getTop();
                        i21 = i11;
                    } else {
                        i21 = i11;
                        top2 = vertical;
                    }
                    if (i21 != 0) {
                        top = Alignment.Companion.getTop();
                    }
                    if (i13 != 0) {
                        i22 = Integer.MAX_VALUE;
                    } else {
                        i22 = i2;
                    }
                    if (i15 != 0) {
                        i23 = Integer.MAX_VALUE;
                    } else {
                        i23 = i3;
                    }
                    if (i17 != 0) {
                        clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-294153140, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:79)");
                    }
                    i24 = 29360128 & i6;
                    if (i24 == 8388608) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    if (i24 == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z3) {
                        obj = objRememberedValue2;
                        ArrayList arrayList10 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList10);
                        composerStartRestartGroup.updateRememberedValue(arrayList10);
                        obj = arrayList10;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList11 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList11);
                        composerStartRestartGroup.updateRememberedValue(arrayList11);
                        obj = arrayList11;
                    }
                    obj = objRememberedValue2;
                    int i212 = i6 >> 6;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(start, top2, top, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-1677845586, true, new Function4() { // from class: yv2
                        public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                            return ContextualFlowLayoutKt.c(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i212 & 57344) | (i212 & 14) | 100663296 | (i212 & 112) | (i212 & 896) | (i212 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowRowOverflow2 = clip;
                    horizontal3 = start;
                    vertical3 = top2;
                    i19 = i22;
                    i20 = i23;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    vertical3 = vertical;
                    i19 = i2;
                    contextualFlowRowOverflow2 = contextualFlowRowOverflow;
                    horizontal3 = horizontal2;
                    i20 = i3;
                }
                modifier3 = modifier2;
                vertical4 = top;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zv2
                        public final Object invoke(Object obj2, Object obj3) {
                            return ContextualFlowLayoutKt.a(i, modifier3, horizontal3, vertical3, vertical4, i19, i20, contextualFlowRowOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i6 |= 3072;
            i11 = i5 & 16;
            if (i11 != 0) {
                if ((i4 & 24576) == 0) {
                    top = vertical2;
                    if (composerStartRestartGroup.changed(top)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i6 |= i12;
                }
                i13 = i5 & 32;
                if (i13 != 0) {
                    i6 |= 196608;
                } else if ((i4 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i6 |= i14;
                }
                i15 = i5 & 64;
                if (i15 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i6 |= i16;
                }
                i17 = i5 & 128;
                if (i17 != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((i4 & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i25 = 67108864;
                    } else {
                        i25 = 33554432;
                    }
                    i6 |= i25;
                }
                if ((i6 & 38347923) != 38347922) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                    if (i26 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i7 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i9 != 0) {
                        top2 = Arrangement.INSTANCE.getTop();
                        i21 = i11;
                    } else {
                        i21 = i11;
                        top2 = vertical;
                    }
                    if (i21 != 0) {
                        top = Alignment.Companion.getTop();
                    }
                    if (i13 != 0) {
                        i22 = Integer.MAX_VALUE;
                    } else {
                        i22 = i2;
                    }
                    if (i15 != 0) {
                        i23 = Integer.MAX_VALUE;
                    } else {
                        i23 = i3;
                    }
                    if (i17 != 0) {
                        clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-294153140, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:79)");
                    }
                    i24 = 29360128 & i6;
                    if (i24 == 8388608) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    if (i24 == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z3) {
                        obj = objRememberedValue2;
                        ArrayList arrayList12 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList12);
                        composerStartRestartGroup.updateRememberedValue(arrayList12);
                        obj = arrayList12;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList13 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList13);
                        composerStartRestartGroup.updateRememberedValue(arrayList13);
                        obj = arrayList13;
                    }
                    obj = objRememberedValue2;
                    int i213 = i6 >> 6;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(start, top2, top, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-1677845586, true, new Function4() { // from class: yv2
                        public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                            return ContextualFlowLayoutKt.c(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i213 & 57344) | (i213 & 14) | 100663296 | (i213 & 112) | (i213 & 896) | (i213 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowRowOverflow2 = clip;
                    horizontal3 = start;
                    vertical3 = top2;
                    i19 = i22;
                    i20 = i23;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    vertical3 = vertical;
                    i19 = i2;
                    contextualFlowRowOverflow2 = contextualFlowRowOverflow;
                    horizontal3 = horizontal2;
                    i20 = i3;
                }
                modifier3 = modifier2;
                vertical4 = top;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zv2
                        public final Object invoke(Object obj2, Object obj3) {
                            return ContextualFlowLayoutKt.a(i, modifier3, horizontal3, vertical3, vertical4, i19, i20, contextualFlowRowOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i6 |= 24576;
            top = vertical2;
            i13 = i5 & 32;
            if (i13 != 0) {
                i6 |= 196608;
            } else if ((i4 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i6 |= i14;
            }
            i15 = i5 & 64;
            if (i15 != 0) {
                i6 |= 1572864;
            } else if ((i4 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(i3)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i6 |= i16;
            }
            i17 = i5 & 128;
            if (i17 != 0) {
                i6 |= 12582912;
            } else if ((i4 & 12582912) == 0) {
                if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                    i18 = 8388608;
                } else {
                    i18 = 4194304;
                }
                i6 |= i18;
            }
            if ((i4 & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i25 = 67108864;
                } else {
                    i25 = 33554432;
                }
                i6 |= i25;
            }
            if ((i6 & 38347923) != 38347922) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                if (i26 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i7 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal2;
                }
                if (i9 != 0) {
                    top2 = Arrangement.INSTANCE.getTop();
                    i21 = i11;
                } else {
                    i21 = i11;
                    top2 = vertical;
                }
                if (i21 != 0) {
                    top = Alignment.Companion.getTop();
                }
                if (i13 != 0) {
                    i22 = Integer.MAX_VALUE;
                } else {
                    i22 = i2;
                }
                if (i15 != 0) {
                    i23 = Integer.MAX_VALUE;
                } else {
                    i23 = i3;
                }
                if (i17 != 0) {
                    clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-294153140, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:79)");
                }
                i24 = 29360128 & i6;
                if (i24 == 8388608) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                if (i24 == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z3) {
                    obj = objRememberedValue2;
                    ArrayList arrayList14 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList14);
                    composerStartRestartGroup.updateRememberedValue(arrayList14);
                    obj = arrayList14;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList15 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList15);
                    composerStartRestartGroup.updateRememberedValue(arrayList15);
                    obj = arrayList15;
                }
                obj = objRememberedValue2;
                int i214 = i6 >> 6;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(start, top2, top, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-1677845586, true, new Function4() { // from class: yv2
                    public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                        return ContextualFlowLayoutKt.c(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i214 & 57344) | (i214 & 14) | 100663296 | (i214 & 112) | (i214 & 896) | (i214 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowRowOverflow2 = clip;
                horizontal3 = start;
                vertical3 = top2;
                i19 = i22;
                i20 = i23;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                vertical3 = vertical;
                i19 = i2;
                contextualFlowRowOverflow2 = contextualFlowRowOverflow;
                horizontal3 = horizontal2;
                i20 = i3;
            }
            modifier3 = modifier2;
            vertical4 = top;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zv2
                    public final Object invoke(Object obj2, Object obj3) {
                        return ContextualFlowLayoutKt.a(i, modifier3, horizontal3, vertical3, vertical4, i19, i20, contextualFlowRowOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i6 |= 48;
        modifier2 = modifier;
        i7 = i5 & 4;
        if (i7 != 0) {
            if ((i4 & 384) == 0) {
                horizontal2 = horizontal;
                if (composerStartRestartGroup.changed(horizontal2)) {
                    i8 = 256;
                } else {
                    i8 = 128;
                }
                i6 |= i8;
            }
            i9 = i5 & 8;
            if (i9 != 0) {
                if ((i4 & 3072) == 0) {
                    if (composerStartRestartGroup.changed(vertical)) {
                        i10 = 2048;
                    } else {
                        i10 = 1024;
                    }
                    i6 |= i10;
                }
                i11 = i5 & 16;
                if (i11 != 0) {
                    if ((i4 & 24576) == 0) {
                        top = vertical2;
                        if (composerStartRestartGroup.changed(top)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i6 |= i12;
                    }
                    i13 = i5 & 32;
                    if (i13 != 0) {
                        i6 |= 196608;
                    } else if ((i4 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i6 |= i14;
                    }
                    i15 = i5 & 64;
                    if (i15 != 0) {
                        i6 |= 1572864;
                    } else if ((i4 & 1572864) == 0) {
                        if (composerStartRestartGroup.changed(i3)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i6 |= i16;
                    }
                    i17 = i5 & 128;
                    if (i17 != 0) {
                        i6 |= 12582912;
                    } else if ((i4 & 12582912) == 0) {
                        if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                            i18 = 8388608;
                        } else {
                            i18 = 4194304;
                        }
                        i6 |= i18;
                    }
                    if ((i4 & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i25 = 67108864;
                        } else {
                            i25 = 33554432;
                        }
                        i6 |= i25;
                    }
                    if ((i6 & 38347923) != 38347922) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                        if (i26 != 0) {
                            modifier2 = Modifier.Companion;
                        }
                        if (i7 != 0) {
                            start = Arrangement.INSTANCE.getStart();
                        } else {
                            start = horizontal2;
                        }
                        if (i9 != 0) {
                            top2 = Arrangement.INSTANCE.getTop();
                            i21 = i11;
                        } else {
                            i21 = i11;
                            top2 = vertical;
                        }
                        if (i21 != 0) {
                            top = Alignment.Companion.getTop();
                        }
                        if (i13 != 0) {
                            i22 = Integer.MAX_VALUE;
                        } else {
                            i22 = i2;
                        }
                        if (i15 != 0) {
                            i23 = Integer.MAX_VALUE;
                        } else {
                            i23 = i3;
                        }
                        if (i17 != 0) {
                            clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                        } else {
                            clip = contextualFlowRowOverflow;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-294153140, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:79)");
                        }
                        i24 = 29360128 & i6;
                        if (i24 == 8388608) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (z2) {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        } else {
                            objRememberedValue = clip.createOverflowState$foundation_layout();
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                        if (i24 == 8388608) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z3) {
                            obj = objRememberedValue2;
                            ArrayList arrayList16 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList16);
                            composerStartRestartGroup.updateRememberedValue(arrayList16);
                            obj = arrayList16;
                        } else {
                            obj = objRememberedValue2;
                            ArrayList arrayList17 = new ArrayList();
                            clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList17);
                            composerStartRestartGroup.updateRememberedValue(arrayList17);
                            obj = arrayList17;
                        }
                        obj = objRememberedValue2;
                        int i215 = i6 >> 6;
                        SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(start, top2, top, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-1677845586, true, new Function4() { // from class: yv2
                            public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                                return ContextualFlowLayoutKt.c(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, (i215 & 57344) | (i215 & 14) | 100663296 | (i215 & 112) | (i215 & 896) | (i215 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        contextualFlowRowOverflow2 = clip;
                        horizontal3 = start;
                        vertical3 = top2;
                        i19 = i22;
                        i20 = i23;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        vertical3 = vertical;
                        i19 = i2;
                        contextualFlowRowOverflow2 = contextualFlowRowOverflow;
                        horizontal3 = horizontal2;
                        i20 = i3;
                    }
                    modifier3 = modifier2;
                    vertical4 = top;
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zv2
                            public final Object invoke(Object obj2, Object obj3) {
                                return ContextualFlowLayoutKt.a(i, modifier3, horizontal3, vertical3, vertical4, i19, i20, contextualFlowRowOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                            }
                        });
                    }
                }
                i6 |= 24576;
                top = vertical2;
                i13 = i5 & 32;
                if (i13 != 0) {
                    i6 |= 196608;
                } else if ((i4 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i6 |= i14;
                }
                i15 = i5 & 64;
                if (i15 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i6 |= i16;
                }
                i17 = i5 & 128;
                if (i17 != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((i4 & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i25 = 67108864;
                    } else {
                        i25 = 33554432;
                    }
                    i6 |= i25;
                }
                if ((i6 & 38347923) != 38347922) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                    if (i26 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i7 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i9 != 0) {
                        top2 = Arrangement.INSTANCE.getTop();
                        i21 = i11;
                    } else {
                        i21 = i11;
                        top2 = vertical;
                    }
                    if (i21 != 0) {
                        top = Alignment.Companion.getTop();
                    }
                    if (i13 != 0) {
                        i22 = Integer.MAX_VALUE;
                    } else {
                        i22 = i2;
                    }
                    if (i15 != 0) {
                        i23 = Integer.MAX_VALUE;
                    } else {
                        i23 = i3;
                    }
                    if (i17 != 0) {
                        clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-294153140, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:79)");
                    }
                    i24 = 29360128 & i6;
                    if (i24 == 8388608) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    if (i24 == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z3) {
                        obj = objRememberedValue2;
                        ArrayList arrayList18 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList18);
                        composerStartRestartGroup.updateRememberedValue(arrayList18);
                        obj = arrayList18;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList19 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList19);
                        composerStartRestartGroup.updateRememberedValue(arrayList19);
                        obj = arrayList19;
                    }
                    obj = objRememberedValue2;
                    int i216 = i6 >> 6;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(start, top2, top, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-1677845586, true, new Function4() { // from class: yv2
                        public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                            return ContextualFlowLayoutKt.c(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i216 & 57344) | (i216 & 14) | 100663296 | (i216 & 112) | (i216 & 896) | (i216 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowRowOverflow2 = clip;
                    horizontal3 = start;
                    vertical3 = top2;
                    i19 = i22;
                    i20 = i23;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    vertical3 = vertical;
                    i19 = i2;
                    contextualFlowRowOverflow2 = contextualFlowRowOverflow;
                    horizontal3 = horizontal2;
                    i20 = i3;
                }
                modifier3 = modifier2;
                vertical4 = top;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zv2
                        public final Object invoke(Object obj2, Object obj3) {
                            return ContextualFlowLayoutKt.a(i, modifier3, horizontal3, vertical3, vertical4, i19, i20, contextualFlowRowOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i6 |= 3072;
            i11 = i5 & 16;
            if (i11 != 0) {
                if ((i4 & 24576) == 0) {
                    top = vertical2;
                    if (composerStartRestartGroup.changed(top)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i6 |= i12;
                }
                i13 = i5 & 32;
                if (i13 != 0) {
                    i6 |= 196608;
                } else if ((i4 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i6 |= i14;
                }
                i15 = i5 & 64;
                if (i15 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i6 |= i16;
                }
                i17 = i5 & 128;
                if (i17 != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((i4 & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i25 = 67108864;
                    } else {
                        i25 = 33554432;
                    }
                    i6 |= i25;
                }
                if ((i6 & 38347923) != 38347922) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                    if (i26 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i7 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i9 != 0) {
                        top2 = Arrangement.INSTANCE.getTop();
                        i21 = i11;
                    } else {
                        i21 = i11;
                        top2 = vertical;
                    }
                    if (i21 != 0) {
                        top = Alignment.Companion.getTop();
                    }
                    if (i13 != 0) {
                        i22 = Integer.MAX_VALUE;
                    } else {
                        i22 = i2;
                    }
                    if (i15 != 0) {
                        i23 = Integer.MAX_VALUE;
                    } else {
                        i23 = i3;
                    }
                    if (i17 != 0) {
                        clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-294153140, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:79)");
                    }
                    i24 = 29360128 & i6;
                    if (i24 == 8388608) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    if (i24 == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z3) {
                        obj = objRememberedValue2;
                        ArrayList arrayList110 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList110);
                        composerStartRestartGroup.updateRememberedValue(arrayList110);
                        obj = arrayList110;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList111 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList111);
                        composerStartRestartGroup.updateRememberedValue(arrayList111);
                        obj = arrayList111;
                    }
                    obj = objRememberedValue2;
                    int i217 = i6 >> 6;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(start, top2, top, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-1677845586, true, new Function4() { // from class: yv2
                        public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                            return ContextualFlowLayoutKt.c(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i217 & 57344) | (i217 & 14) | 100663296 | (i217 & 112) | (i217 & 896) | (i217 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowRowOverflow2 = clip;
                    horizontal3 = start;
                    vertical3 = top2;
                    i19 = i22;
                    i20 = i23;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    vertical3 = vertical;
                    i19 = i2;
                    contextualFlowRowOverflow2 = contextualFlowRowOverflow;
                    horizontal3 = horizontal2;
                    i20 = i3;
                }
                modifier3 = modifier2;
                vertical4 = top;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zv2
                        public final Object invoke(Object obj2, Object obj3) {
                            return ContextualFlowLayoutKt.a(i, modifier3, horizontal3, vertical3, vertical4, i19, i20, contextualFlowRowOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i6 |= 24576;
            top = vertical2;
            i13 = i5 & 32;
            if (i13 != 0) {
                i6 |= 196608;
            } else if ((i4 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i6 |= i14;
            }
            i15 = i5 & 64;
            if (i15 != 0) {
                i6 |= 1572864;
            } else if ((i4 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(i3)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i6 |= i16;
            }
            i17 = i5 & 128;
            if (i17 != 0) {
                i6 |= 12582912;
            } else if ((i4 & 12582912) == 0) {
                if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                    i18 = 8388608;
                } else {
                    i18 = 4194304;
                }
                i6 |= i18;
            }
            if ((i4 & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i25 = 67108864;
                } else {
                    i25 = 33554432;
                }
                i6 |= i25;
            }
            if ((i6 & 38347923) != 38347922) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                if (i26 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i7 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal2;
                }
                if (i9 != 0) {
                    top2 = Arrangement.INSTANCE.getTop();
                    i21 = i11;
                } else {
                    i21 = i11;
                    top2 = vertical;
                }
                if (i21 != 0) {
                    top = Alignment.Companion.getTop();
                }
                if (i13 != 0) {
                    i22 = Integer.MAX_VALUE;
                } else {
                    i22 = i2;
                }
                if (i15 != 0) {
                    i23 = Integer.MAX_VALUE;
                } else {
                    i23 = i3;
                }
                if (i17 != 0) {
                    clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-294153140, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:79)");
                }
                i24 = 29360128 & i6;
                if (i24 == 8388608) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                if (i24 == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z3) {
                    obj = objRememberedValue2;
                    ArrayList arrayList112 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList112);
                    composerStartRestartGroup.updateRememberedValue(arrayList112);
                    obj = arrayList112;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList113 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList113);
                    composerStartRestartGroup.updateRememberedValue(arrayList113);
                    obj = arrayList113;
                }
                obj = objRememberedValue2;
                int i218 = i6 >> 6;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(start, top2, top, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-1677845586, true, new Function4() { // from class: yv2
                    public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                        return ContextualFlowLayoutKt.c(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i218 & 57344) | (i218 & 14) | 100663296 | (i218 & 112) | (i218 & 896) | (i218 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowRowOverflow2 = clip;
                horizontal3 = start;
                vertical3 = top2;
                i19 = i22;
                i20 = i23;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                vertical3 = vertical;
                i19 = i2;
                contextualFlowRowOverflow2 = contextualFlowRowOverflow;
                horizontal3 = horizontal2;
                i20 = i3;
            }
            modifier3 = modifier2;
            vertical4 = top;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zv2
                    public final Object invoke(Object obj2, Object obj3) {
                        return ContextualFlowLayoutKt.a(i, modifier3, horizontal3, vertical3, vertical4, i19, i20, contextualFlowRowOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i6 |= 384;
        horizontal2 = horizontal;
        i9 = i5 & 8;
        if (i9 != 0) {
            if ((i4 & 3072) == 0) {
                if (composerStartRestartGroup.changed(vertical)) {
                    i10 = 2048;
                } else {
                    i10 = 1024;
                }
                i6 |= i10;
            }
            i11 = i5 & 16;
            if (i11 != 0) {
                if ((i4 & 24576) == 0) {
                    top = vertical2;
                    if (composerStartRestartGroup.changed(top)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i6 |= i12;
                }
                i13 = i5 & 32;
                if (i13 != 0) {
                    i6 |= 196608;
                } else if ((i4 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i6 |= i14;
                }
                i15 = i5 & 64;
                if (i15 != 0) {
                    i6 |= 1572864;
                } else if ((i4 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(i3)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i6 |= i16;
                }
                i17 = i5 & 128;
                if (i17 != 0) {
                    i6 |= 12582912;
                } else if ((i4 & 12582912) == 0) {
                    if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                        i18 = 8388608;
                    } else {
                        i18 = 4194304;
                    }
                    i6 |= i18;
                }
                if ((i4 & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i25 = 67108864;
                    } else {
                        i25 = 33554432;
                    }
                    i6 |= i25;
                }
                if ((i6 & 38347923) != 38347922) {
                    z = true;
                } else {
                    z = false;
                }
                if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                    if (i26 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i7 != 0) {
                        start = Arrangement.INSTANCE.getStart();
                    } else {
                        start = horizontal2;
                    }
                    if (i9 != 0) {
                        top2 = Arrangement.INSTANCE.getTop();
                        i21 = i11;
                    } else {
                        i21 = i11;
                        top2 = vertical;
                    }
                    if (i21 != 0) {
                        top = Alignment.Companion.getTop();
                    }
                    if (i13 != 0) {
                        i22 = Integer.MAX_VALUE;
                    } else {
                        i22 = i2;
                    }
                    if (i15 != 0) {
                        i23 = Integer.MAX_VALUE;
                    } else {
                        i23 = i3;
                    }
                    if (i17 != 0) {
                        clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                    } else {
                        clip = contextualFlowRowOverflow;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-294153140, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:79)");
                    }
                    i24 = 29360128 & i6;
                    if (i24 == 8388608) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (z2) {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = clip.createOverflowState$foundation_layout();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                    if (i24 == 8388608) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z3) {
                        obj = objRememberedValue2;
                        ArrayList arrayList114 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList114);
                        composerStartRestartGroup.updateRememberedValue(arrayList114);
                        obj = arrayList114;
                    } else {
                        obj = objRememberedValue2;
                        ArrayList arrayList115 = new ArrayList();
                        clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList115);
                        composerStartRestartGroup.updateRememberedValue(arrayList115);
                        obj = arrayList115;
                    }
                    obj = objRememberedValue2;
                    int i219 = i6 >> 6;
                    SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(start, top2, top, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-1677845586, true, new Function4() { // from class: yv2
                        public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                            return ContextualFlowLayoutKt.c(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, (i219 & 57344) | (i219 & 14) | 100663296 | (i219 & 112) | (i219 & 896) | (i219 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    contextualFlowRowOverflow2 = clip;
                    horizontal3 = start;
                    vertical3 = top2;
                    i19 = i22;
                    i20 = i23;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    vertical3 = vertical;
                    i19 = i2;
                    contextualFlowRowOverflow2 = contextualFlowRowOverflow;
                    horizontal3 = horizontal2;
                    i20 = i3;
                }
                modifier3 = modifier2;
                vertical4 = top;
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zv2
                        public final Object invoke(Object obj2, Object obj3) {
                            return ContextualFlowLayoutKt.a(i, modifier3, horizontal3, vertical3, vertical4, i19, i20, contextualFlowRowOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                        }
                    });
                }
            }
            i6 |= 24576;
            top = vertical2;
            i13 = i5 & 32;
            if (i13 != 0) {
                i6 |= 196608;
            } else if ((i4 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i6 |= i14;
            }
            i15 = i5 & 64;
            if (i15 != 0) {
                i6 |= 1572864;
            } else if ((i4 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(i3)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i6 |= i16;
            }
            i17 = i5 & 128;
            if (i17 != 0) {
                i6 |= 12582912;
            } else if ((i4 & 12582912) == 0) {
                if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                    i18 = 8388608;
                } else {
                    i18 = 4194304;
                }
                i6 |= i18;
            }
            if ((i4 & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i25 = 67108864;
                } else {
                    i25 = 33554432;
                }
                i6 |= i25;
            }
            if ((i6 & 38347923) != 38347922) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                if (i26 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i7 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal2;
                }
                if (i9 != 0) {
                    top2 = Arrangement.INSTANCE.getTop();
                    i21 = i11;
                } else {
                    i21 = i11;
                    top2 = vertical;
                }
                if (i21 != 0) {
                    top = Alignment.Companion.getTop();
                }
                if (i13 != 0) {
                    i22 = Integer.MAX_VALUE;
                } else {
                    i22 = i2;
                }
                if (i15 != 0) {
                    i23 = Integer.MAX_VALUE;
                } else {
                    i23 = i3;
                }
                if (i17 != 0) {
                    clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-294153140, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:79)");
                }
                i24 = 29360128 & i6;
                if (i24 == 8388608) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                if (i24 == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z3) {
                    obj = objRememberedValue2;
                    ArrayList arrayList116 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList116);
                    composerStartRestartGroup.updateRememberedValue(arrayList116);
                    obj = arrayList116;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList117 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList117);
                    composerStartRestartGroup.updateRememberedValue(arrayList117);
                    obj = arrayList117;
                }
                obj = objRememberedValue2;
                int i2110 = i6 >> 6;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(start, top2, top, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-1677845586, true, new Function4() { // from class: yv2
                    public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                        return ContextualFlowLayoutKt.c(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2110 & 57344) | (i2110 & 14) | 100663296 | (i2110 & 112) | (i2110 & 896) | (i2110 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowRowOverflow2 = clip;
                horizontal3 = start;
                vertical3 = top2;
                i19 = i22;
                i20 = i23;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                vertical3 = vertical;
                i19 = i2;
                contextualFlowRowOverflow2 = contextualFlowRowOverflow;
                horizontal3 = horizontal2;
                i20 = i3;
            }
            modifier3 = modifier2;
            vertical4 = top;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zv2
                    public final Object invoke(Object obj2, Object obj3) {
                        return ContextualFlowLayoutKt.a(i, modifier3, horizontal3, vertical3, vertical4, i19, i20, contextualFlowRowOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i6 |= 3072;
        i11 = i5 & 16;
        if (i11 != 0) {
            if ((i4 & 24576) == 0) {
                top = vertical2;
                if (composerStartRestartGroup.changed(top)) {
                    i12 = 16384;
                } else {
                    i12 = 8192;
                }
                i6 |= i12;
            }
            i13 = i5 & 32;
            if (i13 != 0) {
                i6 |= 196608;
            } else if ((i4 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i6 |= i14;
            }
            i15 = i5 & 64;
            if (i15 != 0) {
                i6 |= 1572864;
            } else if ((i4 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(i3)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i6 |= i16;
            }
            i17 = i5 & 128;
            if (i17 != 0) {
                i6 |= 12582912;
            } else if ((i4 & 12582912) == 0) {
                if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                    i18 = 8388608;
                } else {
                    i18 = 4194304;
                }
                i6 |= i18;
            }
            if ((i4 & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i25 = 67108864;
                } else {
                    i25 = 33554432;
                }
                i6 |= i25;
            }
            if ((i6 & 38347923) != 38347922) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
                if (i26 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i7 != 0) {
                    start = Arrangement.INSTANCE.getStart();
                } else {
                    start = horizontal2;
                }
                if (i9 != 0) {
                    top2 = Arrangement.INSTANCE.getTop();
                    i21 = i11;
                } else {
                    i21 = i11;
                    top2 = vertical;
                }
                if (i21 != 0) {
                    top = Alignment.Companion.getTop();
                }
                if (i13 != 0) {
                    i22 = Integer.MAX_VALUE;
                } else {
                    i22 = i2;
                }
                if (i15 != 0) {
                    i23 = Integer.MAX_VALUE;
                } else {
                    i23 = i3;
                }
                if (i17 != 0) {
                    clip = ContextualFlowRowOverflow.INSTANCE.getClip();
                } else {
                    clip = contextualFlowRowOverflow;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-294153140, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:79)");
                }
                i24 = 29360128 & i6;
                if (i24 == 8388608) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (z2) {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = clip.createOverflowState$foundation_layout();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
                if (i24 == 8388608) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z3) {
                    obj = objRememberedValue2;
                    ArrayList arrayList118 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList118);
                    composerStartRestartGroup.updateRememberedValue(arrayList118);
                    obj = arrayList118;
                } else {
                    obj = objRememberedValue2;
                    ArrayList arrayList119 = new ArrayList();
                    clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList119);
                    composerStartRestartGroup.updateRememberedValue(arrayList119);
                    obj = arrayList119;
                }
                obj = objRememberedValue2;
                int i2111 = i6 >> 6;
                SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(start, top2, top, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-1677845586, true, new Function4() { // from class: yv2
                    public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                        return ContextualFlowLayoutKt.c(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2111 & 57344) | (i2111 & 14) | 100663296 | (i2111 & 112) | (i2111 & 896) | (i2111 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                contextualFlowRowOverflow2 = clip;
                horizontal3 = start;
                vertical3 = top2;
                i19 = i22;
                i20 = i23;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                vertical3 = vertical;
                i19 = i2;
                contextualFlowRowOverflow2 = contextualFlowRowOverflow;
                horizontal3 = horizontal2;
                i20 = i3;
            }
            modifier3 = modifier2;
            vertical4 = top;
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zv2
                    public final Object invoke(Object obj2, Object obj3) {
                        return ContextualFlowLayoutKt.a(i, modifier3, horizontal3, vertical3, vertical4, i19, i20, contextualFlowRowOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                    }
                });
            }
        }
        i6 |= 24576;
        top = vertical2;
        i13 = i5 & 32;
        if (i13 != 0) {
            i6 |= 196608;
        } else if ((i4 & 196608) == 0) {
            if (composerStartRestartGroup.changed(i2)) {
                i14 = 131072;
            } else {
                i14 = 65536;
            }
            i6 |= i14;
        }
        i15 = i5 & 64;
        if (i15 != 0) {
            i6 |= 1572864;
        } else if ((i4 & 1572864) == 0) {
            if (composerStartRestartGroup.changed(i3)) {
                i16 = IOUtil.MiB;
            } else {
                i16 = 524288;
            }
            i6 |= i16;
        }
        i17 = i5 & 128;
        if (i17 != 0) {
            i6 |= 12582912;
        } else if ((i4 & 12582912) == 0) {
            if (composerStartRestartGroup.changed(contextualFlowRowOverflow)) {
                i18 = 8388608;
            } else {
                i18 = 4194304;
            }
            i6 |= i18;
        }
        if ((i4 & 100663296) == 0) {
            if (composerStartRestartGroup.changedInstance(function4)) {
                i25 = 67108864;
            } else {
                i25 = 33554432;
            }
            i6 |= i25;
        }
        if ((i6 & 38347923) != 38347922) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i6 & 1)) {
            if (i26 != 0) {
                modifier2 = Modifier.Companion;
            }
            if (i7 != 0) {
                start = Arrangement.INSTANCE.getStart();
            } else {
                start = horizontal2;
            }
            if (i9 != 0) {
                top2 = Arrangement.INSTANCE.getTop();
                i21 = i11;
            } else {
                i21 = i11;
                top2 = vertical;
            }
            if (i21 != 0) {
                top = Alignment.Companion.getTop();
            }
            if (i13 != 0) {
                i22 = Integer.MAX_VALUE;
            } else {
                i22 = i2;
            }
            if (i15 != 0) {
                i23 = Integer.MAX_VALUE;
            } else {
                i23 = i3;
            }
            if (i17 != 0) {
                clip = ContextualFlowRowOverflow.INSTANCE.getClip();
            } else {
                clip = contextualFlowRowOverflow;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-294153140, i6, -1, "androidx.compose.foundation.layout.ContextualFlowRow (ContextualFlowLayout.kt:79)");
            }
            i24 = 29360128 & i6;
            if (i24 == 8388608) {
                z2 = true;
            } else {
                z2 = false;
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2) {
                objRememberedValue = clip.createOverflowState$foundation_layout();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = clip.createOverflowState$foundation_layout();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            flowLayoutOverflowState = (FlowLayoutOverflowState) objRememberedValue;
            if (i24 == 8388608) {
                z3 = true;
            } else {
                z3 = false;
            }
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z3) {
                obj = objRememberedValue2;
                ArrayList arrayList1110 = new ArrayList();
                clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList1110);
                composerStartRestartGroup.updateRememberedValue(arrayList1110);
                obj = arrayList1110;
            } else {
                obj = objRememberedValue2;
                ArrayList arrayList1111 = new ArrayList();
                clip.addOverflowComposables$foundation_layout(flowLayoutOverflowState, arrayList1111);
                composerStartRestartGroup.updateRememberedValue(arrayList1111);
                obj = arrayList1111;
            }
            obj = objRememberedValue2;
            int i2112 = i6 >> 6;
            SubcomposeLayoutKt.SubcomposeLayout(modifier2, contextualRowMeasurementHelper(start, top2, top, i22, i23, flowLayoutOverflowState, i, (List) obj, ComposableLambdaKt.rememberComposableLambda(-1677845586, true, new Function4() { // from class: yv2
                public final Object invoke(Object obj2, Object obj3, Object obj4, Object obj5) {
                    return ContextualFlowLayoutKt.c(function4, ((Integer) obj2).intValue(), (FlowLineInfo) obj3, (Composer) obj4, ((Integer) obj5).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2112 & 57344) | (i2112 & 14) | 100663296 | (i2112 & 112) | (i2112 & 896) | (i2112 & 7168) | ((i6 << 18) & 3670016)), composerStartRestartGroup, (i6 >> 3) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            contextualFlowRowOverflow2 = clip;
            horizontal3 = start;
            vertical3 = top2;
            i19 = i22;
            i20 = i23;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            vertical3 = vertical;
            i19 = i2;
            contextualFlowRowOverflow2 = contextualFlowRowOverflow;
            horizontal3 = horizontal2;
            i20 = i3;
        }
        modifier3 = modifier2;
        vertical4 = top;
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: zv2
                public final Object invoke(Object obj2, Object obj3) {
                    return ContextualFlowLayoutKt.a(i, modifier3, horizontal3, vertical3, vertical4, i19, i20, contextualFlowRowOverflow2, function4, i4, i5, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    public static Unit a(int i, Modifier modifier, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, Alignment.Vertical vertical2, int i2, int i3, ContextualFlowRowOverflow contextualFlowRowOverflow, Function4 function4, int i4, int i5, Composer composer, int i6) {
        ContextualFlowRow(i, modifier, horizontal, vertical, vertical2, i2, i3, contextualFlowRowOverflow, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
        return Unit.INSTANCE;
    }

    public static Unit b(Function4 function4, int i, FlowLineInfo flowLineInfo, Composer composer, int i2) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(620176540, i2, -1, "androidx.compose.foundation.layout.ContextualFlowColumn.<anonymous> (ContextualFlowLayout.kt:173)");
        }
        function4.invoke(new ContextualFlowColumnScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxCrossAxisSize(), flowLineInfo.getMaxMainAxisSize(), null), Integer.valueOf(i), composer, Integer.valueOf((i2 << 3) & 112));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit c(Function4 function4, int i, FlowLineInfo flowLineInfo, Composer composer, int i2) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1677845586, i2, -1, "androidx.compose.foundation.layout.ContextualFlowRow.<anonymous> (ContextualFlowLayout.kt:98)");
        }
        function4.invoke(new ContextualFlowRowScopeImpl(flowLineInfo.getLineIndex(), flowLineInfo.getPositionInLine(), flowLineInfo.getMaxMainAxisSize(), flowLineInfo.getMaxCrossAxisSize(), null), Integer.valueOf(i), composer, Integer.valueOf((i2 << 3) & 112));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:37:0x0072 A[PHI: r4
      0x0072: PHI (r4v16 int) = (r4v14 int), (r4v17 int) binds: [B:36:0x0070, B:32:0x0069] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:63:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:68:0x00da  */
    /* JADX WARN: Code duplicated, block: B:71:0x0107  */
    public static final Function2<SubcomposeMeasureScope, Constraints, MeasureResult> contextualColumnMeasureHelper(Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, Alignment.Horizontal horizontal2, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState, int i3, List<? extends Function2<? super Composer, ? super Integer, Unit>> list, Function4<? super Integer, ? super FlowLineInfo, ? super Composer, ? super Integer, Unit> function4, Composer composer, int i4) {
        int i5;
        boolean z;
        boolean z2;
        boolean z3;
        Object objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-676633639, i4, -1, "androidx.compose.foundation.layout.contextualColumnMeasureHelper (ContextualFlowLayout.kt:424)");
        }
        boolean z4 = ((((i4 & 14) ^ 6) > 4 && composer.changed(vertical)) || (i4 & 6) == 4) | ((((i4 & 112) ^ 48) > 32 && composer.changed(horizontal)) || (i4 & 48) == 32) | ((((i4 & 896) ^ 384) > 256 && composer.changed(horizontal2)) || (i4 & 384) == 256);
        if (((i4 & 7168) ^ 3072) > 2048) {
            i5 = i;
            if (composer.changed(i5)) {
                z = true;
            }
            boolean zChanged = z4 | z | ((((57344 & i4) ^ 24576) <= 16384 && composer.changed(i2)) || (i4 & 24576) == 16384) | composer.changed(flowLayoutOverflowState) | ((((3670016 & i4) ^ 1572864) <= 1048576 && composer.changed(i3)) || (i4 & 1572864) == 1048576);
            if (((234881024 & i4) ^ 100663296) > 67108864 || !composer.changed(function4)) {
                z2 = (i4 & 100663296) == 67108864;
            }
            z3 = zChanged | z2;
            objRememberedValue = composer.rememberedValue();
            if (z3 || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new FlowMeasureLazyPolicy(false, horizontal, vertical, vertical.getSpacing(), CrossAxisAlignment.INSTANCE.horizontal$foundation_layout(horizontal2), horizontal.getSpacing(), i3, i2, i5, flowLayoutOverflowState, list, function4, null).getMeasurePolicy();
                composer.updateRememberedValue(objRememberedValue);
            }
            Function2<SubcomposeMeasureScope, Constraints, MeasureResult> function2 = (Function2) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return function2;
        }
        i5 = i;
        if ((i4 & 3072) == 2048) {
            z = true;
        } else {
            z = false;
        }
        boolean zChanged2 = z4 | z | ((((57344 & i4) ^ 24576) <= 16384 && composer.changed(i2)) || (i4 & 24576) == 16384) | composer.changed(flowLayoutOverflowState) | ((((3670016 & i4) ^ 1572864) <= 1048576 && composer.changed(i3)) || (i4 & 1572864) == 1048576);
        if (((234881024 & i4) ^ 100663296) > 67108864) {
        }
        if ((i4 & 100663296) == 67108864) {
        }
        z3 = zChanged2 | z2;
        objRememberedValue = composer.rememberedValue();
        if (z3) {
            objRememberedValue = new FlowMeasureLazyPolicy(false, horizontal, vertical, vertical.getSpacing(), CrossAxisAlignment.INSTANCE.horizontal$foundation_layout(horizontal2), horizontal.getSpacing(), i3, i2, i5, flowLayoutOverflowState, list, function4, null).getMeasurePolicy();
            composer.updateRememberedValue(objRememberedValue);
        } else {
            objRememberedValue = new FlowMeasureLazyPolicy(false, horizontal, vertical, vertical.getSpacing(), CrossAxisAlignment.INSTANCE.horizontal$foundation_layout(horizontal2), horizontal.getSpacing(), i3, i2, i5, flowLayoutOverflowState, list, function4, null).getMeasurePolicy();
            composer.updateRememberedValue(objRememberedValue);
        }
        Function2<SubcomposeMeasureScope, Constraints, MeasureResult> function3 = (Function2) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return function3;
    }

    /* JADX WARN: Code duplicated, block: B:37:0x0072 A[PHI: r4
      0x0072: PHI (r4v16 int) = (r4v14 int), (r4v17 int) binds: [B:36:0x0070, B:32:0x0069] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:63:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:68:0x00da  */
    /* JADX WARN: Code duplicated, block: B:71:0x0107  */
    public static final Function2<SubcomposeMeasureScope, Constraints, MeasureResult> contextualRowMeasurementHelper(Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, Alignment.Vertical vertical2, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState, int i3, List<? extends Function2<? super Composer, ? super Integer, Unit>> list, Function4<? super Integer, ? super FlowLineInfo, ? super Composer, ? super Integer, Unit> function4, Composer composer, int i4) {
        int i5;
        boolean z;
        boolean z2;
        boolean z3;
        Object objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(962906403, i4, -1, "androidx.compose.foundation.layout.contextualRowMeasurementHelper (ContextualFlowLayout.kt:384)");
        }
        boolean z4 = ((((i4 & 14) ^ 6) > 4 && composer.changed(horizontal)) || (i4 & 6) == 4) | ((((i4 & 112) ^ 48) > 32 && composer.changed(vertical)) || (i4 & 48) == 32) | ((((i4 & 896) ^ 384) > 256 && composer.changed(vertical2)) || (i4 & 384) == 256);
        if (((i4 & 7168) ^ 3072) > 2048) {
            i5 = i;
            if (composer.changed(i5)) {
                z = true;
            }
            boolean zChanged = z4 | z | ((((57344 & i4) ^ 24576) <= 16384 && composer.changed(i2)) || (i4 & 24576) == 16384) | composer.changed(flowLayoutOverflowState) | ((((3670016 & i4) ^ 1572864) <= 1048576 && composer.changed(i3)) || (i4 & 1572864) == 1048576);
            if (((234881024 & i4) ^ 100663296) > 67108864 || !composer.changed(function4)) {
                z2 = (i4 & 100663296) == 67108864;
            }
            z3 = zChanged | z2;
            objRememberedValue = composer.rememberedValue();
            if (z3 || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new FlowMeasureLazyPolicy(true, horizontal, vertical, horizontal.getSpacing(), CrossAxisAlignment.INSTANCE.vertical$foundation_layout(vertical2), vertical.getSpacing(), i3, i2, i5, flowLayoutOverflowState, list, function4, null).getMeasurePolicy();
                composer.updateRememberedValue(objRememberedValue);
            }
            Function2<SubcomposeMeasureScope, Constraints, MeasureResult> function2 = (Function2) objRememberedValue;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            return function2;
        }
        i5 = i;
        if ((i4 & 3072) == 2048) {
            z = true;
        } else {
            z = false;
        }
        boolean zChanged2 = z4 | z | ((((57344 & i4) ^ 24576) <= 16384 && composer.changed(i2)) || (i4 & 24576) == 16384) | composer.changed(flowLayoutOverflowState) | ((((3670016 & i4) ^ 1572864) <= 1048576 && composer.changed(i3)) || (i4 & 1572864) == 1048576);
        if (((234881024 & i4) ^ 100663296) > 67108864) {
        }
        if ((i4 & 100663296) == 67108864) {
        }
        z3 = zChanged2 | z2;
        objRememberedValue = composer.rememberedValue();
        if (z3) {
            objRememberedValue = new FlowMeasureLazyPolicy(true, horizontal, vertical, horizontal.getSpacing(), CrossAxisAlignment.INSTANCE.vertical$foundation_layout(vertical2), vertical.getSpacing(), i3, i2, i5, flowLayoutOverflowState, list, function4, null).getMeasurePolicy();
            composer.updateRememberedValue(objRememberedValue);
        } else {
            objRememberedValue = new FlowMeasureLazyPolicy(true, horizontal, vertical, horizontal.getSpacing(), CrossAxisAlignment.INSTANCE.vertical$foundation_layout(vertical2), vertical.getSpacing(), i3, i2, i5, flowLayoutOverflowState, list, function4, null).getMeasurePolicy();
            composer.updateRememberedValue(objRememberedValue);
        }
        Function2<SubcomposeMeasureScope, Constraints, MeasureResult> function3 = (Function2) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return function3;
    }

    public static Unit d(int i, Modifier modifier, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, Alignment.Horizontal horizontal2, int i2, int i3, ContextualFlowColumnOverflow contextualFlowColumnOverflow, Function4 function4, int i4, int i5, Composer composer, int i6) {
        ContextualFlowColumn(i, modifier, vertical, horizontal, horizontal2, i2, i3, contextualFlowColumnOverflow, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i4 | 1), i5);
        return Unit.INSTANCE;
    }
}
