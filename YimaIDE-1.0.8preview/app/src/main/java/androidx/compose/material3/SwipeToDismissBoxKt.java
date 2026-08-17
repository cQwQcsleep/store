package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.gestures.AnchoredDraggableDefaults;
import androidx.compose.foundation.gestures.AnchoredDraggableKt;
import androidx.compose.foundation.gestures.AnchoredDraggableState;
import androidx.compose.foundation.gestures.DraggableAnchorsConfig;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.material3.SwipeToDismissBoxKt;
import androidx.compose.material3.SwipeToDismissBoxValue;
import androidx.compose.material3.internal.DraggableAnchorsKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a<\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032#\b\u0002\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¢\u0006\u0002\u0010\n\u001aR\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0014\b\u0002\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\f0\u00052#\b\u0002\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¢\u0006\u0002\u0010\r\u001a\u008f\u0001\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00012\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000f0\u0005¢\u0006\u0002\b\u0013¢\u0006\u0002\b\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\f2\b\b\u0002\u0010\u0019\u001a\u00020\f2\u0014\b\u0002\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u000f0\u00052\u001c\u0010\u001b\u001a\u0018\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000f0\u0005¢\u0006\u0002\b\u0013¢\u0006\u0002\b\u0014H\u0007¢\u0006\u0002\u0010\u001c\u001ay\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00012\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000f0\u0005¢\u0006\u0002\b\u0013¢\u0006\u0002\b\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\f2\b\b\u0002\u0010\u0018\u001a\u00020\f2\b\b\u0002\u0010\u0019\u001a\u00020\f2\u001c\u0010\u001b\u001a\u0018\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000f0\u0005¢\u0006\u0002\b\u0013¢\u0006\u0002\b\u0014H\u0007¢\u0006\u0002\u0010\u001d\"\u0010\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010 ¨\u0006!"}, d2 = {"rememberSwipeToDismissBoxState", "Landroidx/compose/material3/SwipeToDismissBoxState;", "initialValue", "Landroidx/compose/material3/SwipeToDismissBoxValue;", "positionalThreshold", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "totalDistance", "(Landroidx/compose/material3/SwipeToDismissBoxValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SwipeToDismissBoxState;", "confirmValueChange", "", "(Landroidx/compose/material3/SwipeToDismissBoxValue;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SwipeToDismissBoxState;", "SwipeToDismissBox", "", "state", "backgroundContent", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "enableDismissFromStartToEnd", "enableDismissFromEndToStart", "gesturesEnabled", "onDismiss", "content", "(Landroidx/compose/material3/SwipeToDismissBoxState;Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;ZZZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/material3/SwipeToDismissBoxState;Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;ZZZLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DismissVelocityThreshold", "Landroidx/compose/ui/unit/Dp;", "F", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SwipeToDismissBoxKt {
    private static final float DismissVelocityThreshold = Dp.m6022constructorimpl(125.0f);

    /* JADX WARN: Code duplicated, block: B:100:0x0111  */
    /* JADX WARN: Code duplicated, block: B:102:0x0115  */
    /* JADX WARN: Code duplicated, block: B:103:0x0117  */
    /* JADX WARN: Code duplicated, block: B:105:0x011a  */
    /* JADX WARN: Code duplicated, block: B:107:0x0126  */
    /* JADX WARN: Code duplicated, block: B:109:0x0131  */
    /* JADX WARN: Code duplicated, block: B:112:0x0138  */
    /* JADX WARN: Code duplicated, block: B:115:0x0149  */
    /* JADX WARN: Code duplicated, block: B:118:0x0154  */
    /* JADX WARN: Code duplicated, block: B:121:0x015d  */
    /* JADX WARN: Code duplicated, block: B:122:0x0183  */
    /* JADX WARN: Code duplicated, block: B:125:0x01c2  */
    /* JADX WARN: Code duplicated, block: B:128:0x01ce  */
    /* JADX WARN: Code duplicated, block: B:129:0x01d2  */
    /* JADX WARN: Code duplicated, block: B:132:0x01f1  */
    /* JADX WARN: Code duplicated, block: B:134:0x01ff  */
    /* JADX WARN: Code duplicated, block: B:137:0x0249  */
    /* JADX WARN: Code duplicated, block: B:140:0x0255  */
    /* JADX WARN: Code duplicated, block: B:141:0x0259  */
    /* JADX WARN: Code duplicated, block: B:144:0x027a  */
    /* JADX WARN: Code duplicated, block: B:146:0x0288  */
    /* JADX WARN: Code duplicated, block: B:149:0x02b9  */
    /* JADX WARN: Code duplicated, block: B:150:0x02bb  */
    /* JADX WARN: Code duplicated, block: B:153:0x02c4  */
    /* JADX WARN: Code duplicated, block: B:154:0x02c6  */
    /* JADX WARN: Code duplicated, block: B:157:0x02d3  */
    /* JADX WARN: Code duplicated, block: B:159:0x02db  */
    /* JADX WARN: Code duplicated, block: B:162:0x0310  */
    /* JADX WARN: Code duplicated, block: B:165:0x031c  */
    /* JADX WARN: Code duplicated, block: B:166:0x0320  */
    /* JADX WARN: Code duplicated, block: B:169:0x033f  */
    /* JADX WARN: Code duplicated, block: B:171:0x034d  */
    /* JADX WARN: Code duplicated, block: B:174:0x0384  */
    /* JADX WARN: Code duplicated, block: B:175:0x0386  */
    /* JADX WARN: Code duplicated, block: B:178:0x038e  */
    /* JADX WARN: Code duplicated, block: B:180:0x0396  */
    /* JADX WARN: Code duplicated, block: B:183:0x03ae  */
    /* JADX WARN: Code duplicated, block: B:185:0x03b9  */
    /* JADX WARN: Code duplicated, block: B:188:0x03c7  */
    /* JADX WARN: Code duplicated, block: B:190:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0060  */
    /* JADX WARN: Code duplicated, block: B:38:0x0065  */
    /* JADX WARN: Code duplicated, block: B:40:0x0069  */
    /* JADX WARN: Code duplicated, block: B:42:0x0071  */
    /* JADX WARN: Code duplicated, block: B:43:0x0074  */
    /* JADX WARN: Code duplicated, block: B:47:0x007b  */
    /* JADX WARN: Code duplicated, block: B:49:0x0080  */
    /* JADX WARN: Code duplicated, block: B:51:0x0084  */
    /* JADX WARN: Code duplicated, block: B:53:0x008c  */
    /* JADX WARN: Code duplicated, block: B:54:0x008f  */
    /* JADX WARN: Code duplicated, block: B:58:0x0099  */
    /* JADX WARN: Code duplicated, block: B:59:0x009e  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:63:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ad  */
    /* JADX WARN: Code duplicated, block: B:68:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:69:0x00bc  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:73:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:74:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:81:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:88:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:89:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:92:0x00ff A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:93:0x0101  */
    /* JADX WARN: Code duplicated, block: B:94:0x0106  */
    /* JADX WARN: Code duplicated, block: B:96:0x010a  */
    /* JADX WARN: Code duplicated, block: B:97:0x010c  */
    /* JADX WARN: Code duplicated, block: B:99:0x010f  */
    public static final void SwipeToDismissBox(final SwipeToDismissBoxState swipeToDismissBoxState, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, boolean z, boolean z2, boolean z3, Function1<? super SwipeToDismissBoxValue, Unit> function1, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function4, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        int i4;
        boolean z4;
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z5;
        int i9;
        int i10;
        final Function1<? super SwipeToDismissBoxValue, Unit> function2;
        int i11;
        int i12;
        boolean z6;
        final Modifier modifier3;
        final boolean z7;
        final boolean z8;
        final boolean z9;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        final boolean z10;
        final boolean z11;
        boolean z12;
        Function1<? super SwipeToDismissBoxValue, Unit> function5;
        boolean z13;
        boolean z14;
        FlingBehavior flingBehavior;
        int currentCompositeKeyHash;
        Function0<ComposeUiNode> constructor;
        Composer composerM2388constructorimpl;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash;
        int currentCompositeKeyHash2;
        Function0<ComposeUiNode> constructor2;
        Composer composerM2388constructorimpl2;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2;
        boolean z15;
        boolean z16;
        boolean zChangedInstance;
        Object objRememberedValue;
        int currentCompositeKeyHash3;
        Function0<ComposeUiNode> constructor3;
        Composer composerM2388constructorimpl3;
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3;
        boolean z17;
        boolean z18;
        Object objRememberedValue2;
        Object objRememberedValue3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-741495334);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(swipeToDismissBoxState) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function3) ? 32 : 16;
        }
        int i13 = i2 & 4;
        if (i13 == 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    z4 = z;
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
                        if (composerStartRestartGroup.changed(z2)) {
                            i7 = 16384;
                        } else {
                            i7 = 8192;
                        }
                        i3 |= i7;
                    }
                    i8 = i2 & 32;
                    if (i8 != 0) {
                        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                        z5 = z3;
                    } else {
                        z5 = z3;
                        if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                            if (composerStartRestartGroup.changed(z5)) {
                                i9 = 131072;
                            } else {
                                i9 = 65536;
                            }
                            i3 |= i9;
                        }
                    }
                    i10 = i2 & 64;
                    if (i10 != 0) {
                        i3 |= 1572864;
                        function2 = function1;
                    } else {
                        function2 = function1;
                        if ((i & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function2)) {
                                i11 = 1048576;
                            } else {
                                i11 = 524288;
                            }
                            i3 |= i11;
                        }
                    }
                    if ((i2 & 128) != 0) {
                        i3 |= 12582912;
                    } else if ((i & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i12 = 8388608;
                        } else {
                            i12 = 4194304;
                        }
                        i3 |= i12;
                    }
                    if ((4793491 & i3) != 4793490) {
                        z6 = true;
                    } else {
                        z6 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                        if (i13 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            z10 = true;
                        } else {
                            z10 = z4;
                        }
                        if (i6 != 0) {
                            z11 = true;
                        } else {
                            z11 = z2;
                        }
                        if (i8 != 0) {
                            z12 = true;
                        } else {
                            z12 = z5;
                        }
                        if (i10 != 0) {
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue3 = new Function1() { // from class: jvd
                                    public final Object invoke(Object obj) {
                                        return SwipeToDismissBoxKt.d((SwipeToDismissBoxValue) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            function5 = (Function1) objRememberedValue3;
                        } else {
                            function5 = function2;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-741495334, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:313)");
                        }
                        AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material3 = swipeToDismissBoxState.getAnchoredDraggableState$material3();
                        Orientation orientation = Orientation.Horizontal;
                        if (z12 || swipeToDismissBoxState.getSettledValue() != SwipeToDismissBoxValue.Settled) {
                            z13 = false;
                        } else {
                            z13 = true;
                        }
                        if (swipeToDismissBoxState.getUseFlingBehavior$material3()) {
                            composerStartRestartGroup.startReplaceGroup(387581105);
                            z14 = true;
                            FlingBehavior flingBehavior2 = AnchoredDraggableDefaults.INSTANCE.flingBehavior(swipeToDismissBoxState.getAnchoredDraggableState$material3(), swipeToDismissBoxState.getPositionalThreshold$material3(), (AnimationSpec) null, composerStartRestartGroup, AnchoredDraggableDefaults.$stable << 9, 4);
                            composerStartRestartGroup.endReplaceGroup();
                            flingBehavior = flingBehavior2;
                        } else {
                            z14 = true;
                            composerStartRestartGroup.startReplaceGroup(-869685853);
                            composerStartRestartGroup.endReplaceGroup();
                            flingBehavior = null;
                        }
                        Modifier modifierAnchoredDraggable$default = AnchoredDraggableKt.anchoredDraggable$default(modifier4, anchoredDraggableState$material3, orientation, z13, (MutableInteractionSource) null, (OverscrollEffect) null, flingBehavior, 24, (Object) null);
                        Alignment.Companion companion = Alignment.INSTANCE;
                        MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(companion.getTopStart(), z14);
                        currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierAnchoredDraggable$default);
                        ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
                        boolean z19 = z12;
                        constructor = companion2.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                        setCompositeKeyHash = companion2.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        Modifier.Companion companion3 = Modifier.INSTANCE;
                        Modifier modifierMatchParentSize = boxScopeInstance.matchParentSize(companion3);
                        int i14 = (i3 << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE;
                        Arrangement arrangement = Arrangement.INSTANCE;
                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(arrangement.getStart(), companion.getTop(), composerStartRestartGroup, 0);
                        currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierMatchParentSize);
                        constructor2 = companion2.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor2);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
                        Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap2, companion2.getSetResolvedCompositionLocals());
                        setCompositeKeyHash2 = companion2.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                            composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                            composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier2, companion2.getSetModifier());
                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                        function3.invoke(rowScopeInstance, composerStartRestartGroup, Integer.valueOf(((i14 >> 6) & 112) | 6));
                        composerStartRestartGroup.endNode();
                        AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material4 = swipeToDismissBoxState.getAnchoredDraggableState$material3();
                        if ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) {
                            z15 = true;
                        } else {
                            z15 = false;
                        }
                        if ((57344 & i3) == 16384) {
                            z16 = true;
                        } else {
                            z16 = false;
                        }
                        zChangedInstance = z16 | z15 | composerStartRestartGroup.changedInstance(swipeToDismissBoxState);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function2() { // from class: kvd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SwipeToDismissBoxKt.c(swipeToDismissBoxState, z10, z11, (IntSize) obj, (Constraints) obj2);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        Modifier modifierDraggableAnchorsV2 = DraggableAnchorsKt.draggableAnchorsV2(companion3, anchoredDraggableState$material4, orientation, (Function2) objRememberedValue);
                        int i15 = (i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE;
                        MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(arrangement.getStart(), companion.getTop(), composerStartRestartGroup, 0);
                        currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                        CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierDraggableAnchorsV2);
                        constructor3 = companion2.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor3);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        composerM2388constructorimpl3 = Updater.m2388constructorimpl(composerStartRestartGroup);
                        Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicyRowMeasurePolicy2, companion2.getSetMeasurePolicy());
                        Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap3, companion2.getSetResolvedCompositionLocals());
                        setCompositeKeyHash3 = companion2.getSetCompositeKeyHash();
                        if (composerM2388constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                            composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                            composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                        }
                        Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier3, companion2.getSetModifier());
                        function4.invoke(rowScopeInstance, composerStartRestartGroup, Integer.valueOf(((i15 >> 6) & 112) | 6));
                        composerStartRestartGroup.endNode();
                        composerStartRestartGroup.endNode();
                        SwipeToDismissBoxValue settledValue = swipeToDismissBoxState.getSettledValue();
                        boolean zChangedInstance2 = composerStartRestartGroup.changedInstance(swipeToDismissBoxState);
                        if ((3670016 & i3) == 1048576) {
                            z17 = true;
                        } else {
                            z17 = false;
                        }
                        z18 = zChangedInstance2 | z17;
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z18 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = new SwipeToDismissBoxKt$SwipeToDismissBox$3$1(swipeToDismissBoxState, function5, null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        EffectsKt.LaunchedEffect(settledValue, function5, (Function2) objRememberedValue2, composerStartRestartGroup, (i3 >> 15) & 112);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        z9 = z11;
                        function2 = function5;
                        modifier3 = modifier4;
                        z8 = z19;
                        z7 = z10;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        modifier3 = modifier2;
                        z7 = z4;
                        z8 = z5;
                        z9 = z2;
                    }
                    scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lvd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwipeToDismissBoxKt.e(swipeToDismissBoxState, function3, modifier3, z7, z9, z8, function2, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                i8 = i2 & 32;
                if (i8 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    z5 = z3;
                } else {
                    z5 = z3;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(z5)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                }
                i10 = i2 & 64;
                if (i10 != 0) {
                    i3 |= 1572864;
                    function2 = function1;
                } else {
                    function2 = function1;
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i11 = 1048576;
                        } else {
                            i11 = 524288;
                        }
                        i3 |= i11;
                    }
                }
                if ((i2 & 128) != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i12 = 8388608;
                    } else {
                        i12 = 4194304;
                    }
                    i3 |= i12;
                }
                if ((4793491 & i3) != 4793490) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    if (i13 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z10 = true;
                    } else {
                        z10 = z4;
                    }
                    if (i6 != 0) {
                        z11 = true;
                    } else {
                        z11 = z2;
                    }
                    if (i8 != 0) {
                        z12 = true;
                    } else {
                        z12 = z5;
                    }
                    if (i10 != 0) {
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new Function1() { // from class: jvd
                                public final Object invoke(Object obj) {
                                    return SwipeToDismissBoxKt.d((SwipeToDismissBoxValue) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        function5 = (Function1) objRememberedValue3;
                    } else {
                        function5 = function2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-741495334, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:313)");
                    }
                    AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material5 = swipeToDismissBoxState.getAnchoredDraggableState$material3();
                    Orientation orientation2 = Orientation.Horizontal;
                    if (z12) {
                        z13 = false;
                    } else {
                        z13 = false;
                    }
                    if (swipeToDismissBoxState.getUseFlingBehavior$material3()) {
                        composerStartRestartGroup.startReplaceGroup(387581105);
                        z14 = true;
                        FlingBehavior flingBehavior3 = AnchoredDraggableDefaults.INSTANCE.flingBehavior(swipeToDismissBoxState.getAnchoredDraggableState$material3(), swipeToDismissBoxState.getPositionalThreshold$material3(), (AnimationSpec) null, composerStartRestartGroup, AnchoredDraggableDefaults.$stable << 9, 4);
                        composerStartRestartGroup.endReplaceGroup();
                        flingBehavior = flingBehavior3;
                    } else {
                        z14 = true;
                        composerStartRestartGroup.startReplaceGroup(-869685853);
                        composerStartRestartGroup.endReplaceGroup();
                        flingBehavior = null;
                    }
                    Modifier modifierAnchoredDraggable$default2 = AnchoredDraggableKt.anchoredDraggable$default(modifier4, anchoredDraggableState$material5, orientation2, z13, (MutableInteractionSource) null, (OverscrollEffect) null, flingBehavior, 24, (Object) null);
                    Alignment.Companion companion4 = Alignment.INSTANCE;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(companion4.getTopStart(), z14);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierAnchoredDraggable$default2);
                    ComposeUiNode.Companion companion5 = ComposeUiNode.INSTANCE;
                    boolean z110 = z12;
                    constructor = companion5.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy2, companion5.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap4, companion5.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion5.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier4, companion5.getSetModifier());
                    BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                    Modifier.Companion companion6 = Modifier.INSTANCE;
                    Modifier modifierMatchParentSize2 = boxScopeInstance2.matchParentSize(companion6);
                    int i16 = (i3 << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE;
                    Arrangement arrangement2 = Arrangement.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(arrangement2.getStart(), companion4.getTop(), composerStartRestartGroup, 0);
                    currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierMatchParentSize2);
                    constructor2 = companion5.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor2);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyRowMeasurePolicy3, companion5.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap5, companion5.getSetResolvedCompositionLocals());
                    setCompositeKeyHash2 = companion5.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl2.getInserting()) {
                        composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                        composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                    } else {
                        composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                        composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier5, companion5.getSetModifier());
                    RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                    function3.invoke(rowScopeInstance2, composerStartRestartGroup, Integer.valueOf(((i16 >> 6) & 112) | 6));
                    composerStartRestartGroup.endNode();
                    AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material6 = swipeToDismissBoxState.getAnchoredDraggableState$material3();
                    if ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) {
                        z15 = true;
                    } else {
                        z15 = false;
                    }
                    if ((57344 & i3) == 16384) {
                        z16 = true;
                    } else {
                        z16 = false;
                    }
                    zChangedInstance = z16 | z15 | composerStartRestartGroup.changedInstance(swipeToDismissBoxState);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance) {
                        objRememberedValue = new Function2() { // from class: kvd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwipeToDismissBoxKt.c(swipeToDismissBoxState, z10, z11, (IntSize) obj, (Constraints) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function2() { // from class: kvd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwipeToDismissBoxKt.c(swipeToDismissBoxState, z10, z11, (IntSize) obj, (Constraints) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Modifier modifierDraggableAnchorsV3 = DraggableAnchorsKt.draggableAnchorsV2(companion6, anchoredDraggableState$material6, orientation2, (Function2) objRememberedValue);
                    int i17 = (i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE;
                    MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(arrangement2.getStart(), companion4.getTop(), composerStartRestartGroup, 0);
                    currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierDraggableAnchorsV3);
                    constructor3 = companion5.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor3);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl3 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicyRowMeasurePolicy4, companion5.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap6, companion5.getSetResolvedCompositionLocals());
                    setCompositeKeyHash3 = companion5.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl3.getInserting()) {
                        composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    } else {
                        composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier6, companion5.getSetModifier());
                    function4.invoke(rowScopeInstance2, composerStartRestartGroup, Integer.valueOf(((i17 >> 6) & 112) | 6));
                    composerStartRestartGroup.endNode();
                    composerStartRestartGroup.endNode();
                    SwipeToDismissBoxValue settledValue2 = swipeToDismissBoxState.getSettledValue();
                    boolean zChangedInstance3 = composerStartRestartGroup.changedInstance(swipeToDismissBoxState);
                    if ((3670016 & i3) == 1048576) {
                        z17 = true;
                    } else {
                        z17 = false;
                    }
                    z18 = zChangedInstance3 | z17;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z18) {
                        objRememberedValue2 = new SwipeToDismissBoxKt$SwipeToDismissBox$3$1(swipeToDismissBoxState, function5, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new SwipeToDismissBoxKt$SwipeToDismissBox$3$1(swipeToDismissBoxState, function5, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    EffectsKt.LaunchedEffect(settledValue2, function5, (Function2) objRememberedValue2, composerStartRestartGroup, (i3 >> 15) & 112);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z9 = z11;
                    function2 = function5;
                    modifier3 = modifier4;
                    z8 = z110;
                    z7 = z10;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z7 = z4;
                    z8 = z5;
                    z9 = z2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lvd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.e(swipeToDismissBoxState, function3, modifier3, z7, z9, z8, function2, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z4 = z;
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 32;
                if (i8 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    z5 = z3;
                } else {
                    z5 = z3;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(z5)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                }
                i10 = i2 & 64;
                if (i10 != 0) {
                    i3 |= 1572864;
                    function2 = function1;
                } else {
                    function2 = function1;
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i11 = 1048576;
                        } else {
                            i11 = 524288;
                        }
                        i3 |= i11;
                    }
                }
                if ((i2 & 128) != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i12 = 8388608;
                    } else {
                        i12 = 4194304;
                    }
                    i3 |= i12;
                }
                if ((4793491 & i3) != 4793490) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    if (i13 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z10 = true;
                    } else {
                        z10 = z4;
                    }
                    if (i6 != 0) {
                        z11 = true;
                    } else {
                        z11 = z2;
                    }
                    if (i8 != 0) {
                        z12 = true;
                    } else {
                        z12 = z5;
                    }
                    if (i10 != 0) {
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new Function1() { // from class: jvd
                                public final Object invoke(Object obj) {
                                    return SwipeToDismissBoxKt.d((SwipeToDismissBoxValue) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        function5 = (Function1) objRememberedValue3;
                    } else {
                        function5 = function2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-741495334, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:313)");
                    }
                    AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material7 = swipeToDismissBoxState.getAnchoredDraggableState$material3();
                    Orientation orientation3 = Orientation.Horizontal;
                    if (z12) {
                        z13 = false;
                    } else {
                        z13 = false;
                    }
                    if (swipeToDismissBoxState.getUseFlingBehavior$material3()) {
                        composerStartRestartGroup.startReplaceGroup(387581105);
                        z14 = true;
                        FlingBehavior flingBehavior4 = AnchoredDraggableDefaults.INSTANCE.flingBehavior(swipeToDismissBoxState.getAnchoredDraggableState$material3(), swipeToDismissBoxState.getPositionalThreshold$material3(), (AnimationSpec) null, composerStartRestartGroup, AnchoredDraggableDefaults.$stable << 9, 4);
                        composerStartRestartGroup.endReplaceGroup();
                        flingBehavior = flingBehavior4;
                    } else {
                        z14 = true;
                        composerStartRestartGroup.startReplaceGroup(-869685853);
                        composerStartRestartGroup.endReplaceGroup();
                        flingBehavior = null;
                    }
                    Modifier modifierAnchoredDraggable$default3 = AnchoredDraggableKt.anchoredDraggable$default(modifier4, anchoredDraggableState$material7, orientation3, z13, (MutableInteractionSource) null, (OverscrollEffect) null, flingBehavior, 24, (Object) null);
                    Alignment.Companion companion7 = Alignment.INSTANCE;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(companion7.getTopStart(), z14);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierAnchoredDraggable$default3);
                    ComposeUiNode.Companion companion8 = ComposeUiNode.INSTANCE;
                    boolean z111 = z12;
                    constructor = companion8.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy3, companion8.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap7, companion8.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion8.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier7, companion8.getSetModifier());
                    BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                    Modifier.Companion companion9 = Modifier.INSTANCE;
                    Modifier modifierMatchParentSize3 = boxScopeInstance3.matchParentSize(companion9);
                    int i18 = (i3 << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE;
                    Arrangement arrangement3 = Arrangement.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy5 = RowKt.rowMeasurePolicy(arrangement3.getStart(), companion7.getTop(), composerStartRestartGroup, 0);
                    currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierMatchParentSize3);
                    constructor2 = companion8.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor2);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyRowMeasurePolicy5, companion8.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap8, companion8.getSetResolvedCompositionLocals());
                    setCompositeKeyHash2 = companion8.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl2.getInserting()) {
                        composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                        composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                    } else {
                        composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                        composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier8, companion8.getSetModifier());
                    RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                    function3.invoke(rowScopeInstance3, composerStartRestartGroup, Integer.valueOf(((i18 >> 6) & 112) | 6));
                    composerStartRestartGroup.endNode();
                    AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material8 = swipeToDismissBoxState.getAnchoredDraggableState$material3();
                    if ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) {
                        z15 = true;
                    } else {
                        z15 = false;
                    }
                    if ((57344 & i3) == 16384) {
                        z16 = true;
                    } else {
                        z16 = false;
                    }
                    zChangedInstance = z16 | z15 | composerStartRestartGroup.changedInstance(swipeToDismissBoxState);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance) {
                        objRememberedValue = new Function2() { // from class: kvd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwipeToDismissBoxKt.c(swipeToDismissBoxState, z10, z11, (IntSize) obj, (Constraints) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function2() { // from class: kvd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwipeToDismissBoxKt.c(swipeToDismissBoxState, z10, z11, (IntSize) obj, (Constraints) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Modifier modifierDraggableAnchorsV4 = DraggableAnchorsKt.draggableAnchorsV2(companion9, anchoredDraggableState$material8, orientation3, (Function2) objRememberedValue);
                    int i19 = (i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE;
                    MeasurePolicy measurePolicyRowMeasurePolicy6 = RowKt.rowMeasurePolicy(arrangement3.getStart(), companion7.getTop(), composerStartRestartGroup, 0);
                    currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap9 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierDraggableAnchorsV4);
                    constructor3 = companion8.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor3);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl3 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicyRowMeasurePolicy6, companion8.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap9, companion8.getSetResolvedCompositionLocals());
                    setCompositeKeyHash3 = companion8.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl3.getInserting()) {
                        composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    } else {
                        composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier9, companion8.getSetModifier());
                    function4.invoke(rowScopeInstance3, composerStartRestartGroup, Integer.valueOf(((i19 >> 6) & 112) | 6));
                    composerStartRestartGroup.endNode();
                    composerStartRestartGroup.endNode();
                    SwipeToDismissBoxValue settledValue3 = swipeToDismissBoxState.getSettledValue();
                    boolean zChangedInstance4 = composerStartRestartGroup.changedInstance(swipeToDismissBoxState);
                    if ((3670016 & i3) == 1048576) {
                        z17 = true;
                    } else {
                        z17 = false;
                    }
                    z18 = zChangedInstance4 | z17;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z18) {
                        objRememberedValue2 = new SwipeToDismissBoxKt$SwipeToDismissBox$3$1(swipeToDismissBoxState, function5, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new SwipeToDismissBoxKt$SwipeToDismissBox$3$1(swipeToDismissBoxState, function5, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    EffectsKt.LaunchedEffect(settledValue3, function5, (Function2) objRememberedValue2, composerStartRestartGroup, (i3 >> 15) & 112);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z9 = z11;
                    function2 = function5;
                    modifier3 = modifier4;
                    z8 = z111;
                    z7 = z10;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z7 = z4;
                    z8 = z5;
                    z9 = z2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lvd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.e(swipeToDismissBoxState, function3, modifier3, z7, z9, z8, function2, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            i8 = i2 & 32;
            if (i8 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                z5 = z3;
            } else {
                z5 = z3;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(z5)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
            }
            i10 = i2 & 64;
            if (i10 != 0) {
                i3 |= 1572864;
                function2 = function1;
            } else {
                function2 = function1;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i11 = 1048576;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
            }
            if ((i2 & 128) != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i12 = 8388608;
                } else {
                    i12 = 4194304;
                }
                i3 |= i12;
            }
            if ((4793491 & i3) != 4793490) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                if (i13 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    z10 = true;
                } else {
                    z10 = z4;
                }
                if (i6 != 0) {
                    z11 = true;
                } else {
                    z11 = z2;
                }
                if (i8 != 0) {
                    z12 = true;
                } else {
                    z12 = z5;
                }
                if (i10 != 0) {
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = new Function1() { // from class: jvd
                            public final Object invoke(Object obj) {
                                return SwipeToDismissBoxKt.d((SwipeToDismissBoxValue) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    function5 = (Function1) objRememberedValue3;
                } else {
                    function5 = function2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-741495334, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:313)");
                }
                AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material9 = swipeToDismissBoxState.getAnchoredDraggableState$material3();
                Orientation orientation4 = Orientation.Horizontal;
                if (z12) {
                    z13 = false;
                } else {
                    z13 = false;
                }
                if (swipeToDismissBoxState.getUseFlingBehavior$material3()) {
                    composerStartRestartGroup.startReplaceGroup(387581105);
                    z14 = true;
                    FlingBehavior flingBehavior5 = AnchoredDraggableDefaults.INSTANCE.flingBehavior(swipeToDismissBoxState.getAnchoredDraggableState$material3(), swipeToDismissBoxState.getPositionalThreshold$material3(), (AnimationSpec) null, composerStartRestartGroup, AnchoredDraggableDefaults.$stable << 9, 4);
                    composerStartRestartGroup.endReplaceGroup();
                    flingBehavior = flingBehavior5;
                } else {
                    z14 = true;
                    composerStartRestartGroup.startReplaceGroup(-869685853);
                    composerStartRestartGroup.endReplaceGroup();
                    flingBehavior = null;
                }
                Modifier modifierAnchoredDraggable$default4 = AnchoredDraggableKt.anchoredDraggable$default(modifier4, anchoredDraggableState$material9, orientation4, z13, (MutableInteractionSource) null, (OverscrollEffect) null, flingBehavior, 24, (Object) null);
                Alignment.Companion companion10 = Alignment.INSTANCE;
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(companion10.getTopStart(), z14);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap10 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierAnchoredDraggable$default4);
                ComposeUiNode.Companion companion11 = ComposeUiNode.INSTANCE;
                boolean z112 = z12;
                constructor = companion11.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy4, companion11.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap10, companion11.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion11.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier10, companion11.getSetModifier());
                BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
                Modifier.Companion companion12 = Modifier.INSTANCE;
                Modifier modifierMatchParentSize4 = boxScopeInstance4.matchParentSize(companion12);
                int i110 = (i3 << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE;
                Arrangement arrangement4 = Arrangement.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy7 = RowKt.rowMeasurePolicy(arrangement4.getStart(), companion10.getTop(), composerStartRestartGroup, 0);
                currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap11 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier11 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierMatchParentSize4);
                constructor2 = companion11.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyRowMeasurePolicy7, companion11.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap11, companion11.getSetResolvedCompositionLocals());
                setCompositeKeyHash2 = companion11.getSetCompositeKeyHash();
                if (composerM2388constructorimpl2.getInserting()) {
                    composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                } else {
                    composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier11, companion11.getSetModifier());
                RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
                function3.invoke(rowScopeInstance4, composerStartRestartGroup, Integer.valueOf(((i110 >> 6) & 112) | 6));
                composerStartRestartGroup.endNode();
                AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material10 = swipeToDismissBoxState.getAnchoredDraggableState$material3();
                if ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) {
                    z15 = true;
                } else {
                    z15 = false;
                }
                if ((57344 & i3) == 16384) {
                    z16 = true;
                } else {
                    z16 = false;
                }
                zChangedInstance = z16 | z15 | composerStartRestartGroup.changedInstance(swipeToDismissBoxState);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    objRememberedValue = new Function2() { // from class: kvd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.c(swipeToDismissBoxState, z10, z11, (IntSize) obj, (Constraints) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function2() { // from class: kvd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.c(swipeToDismissBoxState, z10, z11, (IntSize) obj, (Constraints) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Modifier modifierDraggableAnchorsV5 = DraggableAnchorsKt.draggableAnchorsV2(companion12, anchoredDraggableState$material10, orientation4, (Function2) objRememberedValue);
                int i111 = (i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE;
                MeasurePolicy measurePolicyRowMeasurePolicy8 = RowKt.rowMeasurePolicy(arrangement4.getStart(), companion10.getTop(), composerStartRestartGroup, 0);
                currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap12 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier12 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierDraggableAnchorsV5);
                constructor3 = companion11.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl3 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicyRowMeasurePolicy8, companion11.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap12, companion11.getSetResolvedCompositionLocals());
                setCompositeKeyHash3 = companion11.getSetCompositeKeyHash();
                if (composerM2388constructorimpl3.getInserting()) {
                    composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                } else {
                    composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier12, companion11.getSetModifier());
                function4.invoke(rowScopeInstance4, composerStartRestartGroup, Integer.valueOf(((i111 >> 6) & 112) | 6));
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endNode();
                SwipeToDismissBoxValue settledValue4 = swipeToDismissBoxState.getSettledValue();
                boolean zChangedInstance5 = composerStartRestartGroup.changedInstance(swipeToDismissBoxState);
                if ((3670016 & i3) == 1048576) {
                    z17 = true;
                } else {
                    z17 = false;
                }
                z18 = zChangedInstance5 | z17;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z18) {
                    objRememberedValue2 = new SwipeToDismissBoxKt$SwipeToDismissBox$3$1(swipeToDismissBoxState, function5, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new SwipeToDismissBoxKt$SwipeToDismissBox$3$1(swipeToDismissBoxState, function5, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                EffectsKt.LaunchedEffect(settledValue4, function5, (Function2) objRememberedValue2, composerStartRestartGroup, (i3 >> 15) & 112);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z9 = z11;
                function2 = function5;
                modifier3 = modifier4;
                z8 = z112;
                z7 = z10;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                z7 = z4;
                z8 = z5;
                z9 = z2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lvd
                    public final Object invoke(Object obj, Object obj2) {
                        return SwipeToDismissBoxKt.e(swipeToDismissBoxState, function3, modifier3, z7, z9, z8, function2, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        modifier2 = modifier;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                z4 = z;
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
                    if (composerStartRestartGroup.changed(z2)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 32;
                if (i8 != 0) {
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    z5 = z3;
                } else {
                    z5 = z3;
                    if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                        if (composerStartRestartGroup.changed(z5)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                }
                i10 = i2 & 64;
                if (i10 != 0) {
                    i3 |= 1572864;
                    function2 = function1;
                } else {
                    function2 = function1;
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i11 = 1048576;
                        } else {
                            i11 = 524288;
                        }
                        i3 |= i11;
                    }
                }
                if ((i2 & 128) != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i12 = 8388608;
                    } else {
                        i12 = 4194304;
                    }
                    i3 |= i12;
                }
                if ((4793491 & i3) != 4793490) {
                    z6 = true;
                } else {
                    z6 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                    if (i13 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z10 = true;
                    } else {
                        z10 = z4;
                    }
                    if (i6 != 0) {
                        z11 = true;
                    } else {
                        z11 = z2;
                    }
                    if (i8 != 0) {
                        z12 = true;
                    } else {
                        z12 = z5;
                    }
                    if (i10 != 0) {
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = new Function1() { // from class: jvd
                                public final Object invoke(Object obj) {
                                    return SwipeToDismissBoxKt.d((SwipeToDismissBoxValue) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        function5 = (Function1) objRememberedValue3;
                    } else {
                        function5 = function2;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-741495334, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:313)");
                    }
                    AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material11 = swipeToDismissBoxState.getAnchoredDraggableState$material3();
                    Orientation orientation5 = Orientation.Horizontal;
                    if (z12) {
                        z13 = false;
                    } else {
                        z13 = false;
                    }
                    if (swipeToDismissBoxState.getUseFlingBehavior$material3()) {
                        composerStartRestartGroup.startReplaceGroup(387581105);
                        z14 = true;
                        FlingBehavior flingBehavior6 = AnchoredDraggableDefaults.INSTANCE.flingBehavior(swipeToDismissBoxState.getAnchoredDraggableState$material3(), swipeToDismissBoxState.getPositionalThreshold$material3(), (AnimationSpec) null, composerStartRestartGroup, AnchoredDraggableDefaults.$stable << 9, 4);
                        composerStartRestartGroup.endReplaceGroup();
                        flingBehavior = flingBehavior6;
                    } else {
                        z14 = true;
                        composerStartRestartGroup.startReplaceGroup(-869685853);
                        composerStartRestartGroup.endReplaceGroup();
                        flingBehavior = null;
                    }
                    Modifier modifierAnchoredDraggable$default5 = AnchoredDraggableKt.anchoredDraggable$default(modifier4, anchoredDraggableState$material11, orientation5, z13, (MutableInteractionSource) null, (OverscrollEffect) null, flingBehavior, 24, (Object) null);
                    Alignment.Companion companion13 = Alignment.INSTANCE;
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy5 = BoxKt.maybeCachedBoxMeasurePolicy(companion13.getTopStart(), z14);
                    currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap13 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier13 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierAnchoredDraggable$default5);
                    ComposeUiNode.Companion companion14 = ComposeUiNode.INSTANCE;
                    boolean z113 = z12;
                    constructor = companion14.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy5, companion14.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap13, companion14.getSetResolvedCompositionLocals());
                    setCompositeKeyHash = companion14.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl.getInserting()) {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    } else {
                        composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                        composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier13, companion14.getSetModifier());
                    BoxScopeInstance boxScopeInstance5 = BoxScopeInstance.INSTANCE;
                    Modifier.Companion companion15 = Modifier.INSTANCE;
                    Modifier modifierMatchParentSize5 = boxScopeInstance5.matchParentSize(companion15);
                    int i112 = (i3 << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE;
                    Arrangement arrangement5 = Arrangement.INSTANCE;
                    MeasurePolicy measurePolicyRowMeasurePolicy9 = RowKt.rowMeasurePolicy(arrangement5.getStart(), companion13.getTop(), composerStartRestartGroup, 0);
                    currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap14 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier14 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierMatchParentSize5);
                    constructor2 = companion14.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor2);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyRowMeasurePolicy9, companion14.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap14, companion14.getSetResolvedCompositionLocals());
                    setCompositeKeyHash2 = companion14.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl2.getInserting()) {
                        composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                        composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                    } else {
                        composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                        composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier14, companion14.getSetModifier());
                    RowScopeInstance rowScopeInstance5 = RowScopeInstance.INSTANCE;
                    function3.invoke(rowScopeInstance5, composerStartRestartGroup, Integer.valueOf(((i112 >> 6) & 112) | 6));
                    composerStartRestartGroup.endNode();
                    AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material12 = swipeToDismissBoxState.getAnchoredDraggableState$material3();
                    if ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) {
                        z15 = true;
                    } else {
                        z15 = false;
                    }
                    if ((57344 & i3) == 16384) {
                        z16 = true;
                    } else {
                        z16 = false;
                    }
                    zChangedInstance = z16 | z15 | composerStartRestartGroup.changedInstance(swipeToDismissBoxState);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (zChangedInstance) {
                        objRememberedValue = new Function2() { // from class: kvd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwipeToDismissBoxKt.c(swipeToDismissBoxState, z10, z11, (IntSize) obj, (Constraints) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    } else {
                        objRememberedValue = new Function2() { // from class: kvd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwipeToDismissBoxKt.c(swipeToDismissBoxState, z10, z11, (IntSize) obj, (Constraints) obj2);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Modifier modifierDraggableAnchorsV6 = DraggableAnchorsKt.draggableAnchorsV2(companion15, anchoredDraggableState$material12, orientation5, (Function2) objRememberedValue);
                    int i113 = (i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE;
                    MeasurePolicy measurePolicyRowMeasurePolicy10 = RowKt.rowMeasurePolicy(arrangement5.getStart(), companion13.getTop(), composerStartRestartGroup, 0);
                    currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                    CompositionLocalMap currentCompositionLocalMap15 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier15 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierDraggableAnchorsV6);
                    constructor3 = companion14.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor3);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    composerM2388constructorimpl3 = Updater.m2388constructorimpl(composerStartRestartGroup);
                    Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicyRowMeasurePolicy10, companion14.getSetMeasurePolicy());
                    Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap15, companion14.getSetResolvedCompositionLocals());
                    setCompositeKeyHash3 = companion14.getSetCompositeKeyHash();
                    if (composerM2388constructorimpl3.getInserting()) {
                        composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    } else {
                        composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                        composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                    }
                    Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier15, companion14.getSetModifier());
                    function4.invoke(rowScopeInstance5, composerStartRestartGroup, Integer.valueOf(((i113 >> 6) & 112) | 6));
                    composerStartRestartGroup.endNode();
                    composerStartRestartGroup.endNode();
                    SwipeToDismissBoxValue settledValue5 = swipeToDismissBoxState.getSettledValue();
                    boolean zChangedInstance6 = composerStartRestartGroup.changedInstance(swipeToDismissBoxState);
                    if ((3670016 & i3) == 1048576) {
                        z17 = true;
                    } else {
                        z17 = false;
                    }
                    z18 = zChangedInstance6 | z17;
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z18) {
                        objRememberedValue2 = new SwipeToDismissBoxKt$SwipeToDismissBox$3$1(swipeToDismissBoxState, function5, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new SwipeToDismissBoxKt$SwipeToDismissBox$3$1(swipeToDismissBoxState, function5, null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    EffectsKt.LaunchedEffect(settledValue5, function5, (Function2) objRememberedValue2, composerStartRestartGroup, (i3 >> 15) & 112);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    z9 = z11;
                    function2 = function5;
                    modifier3 = modifier4;
                    z8 = z113;
                    z7 = z10;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    modifier3 = modifier2;
                    z7 = z4;
                    z8 = z5;
                    z9 = z2;
                }
                scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lvd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.e(swipeToDismissBoxState, function3, modifier3, z7, z9, z8, function2, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            i8 = i2 & 32;
            if (i8 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                z5 = z3;
            } else {
                z5 = z3;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(z5)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
            }
            i10 = i2 & 64;
            if (i10 != 0) {
                i3 |= 1572864;
                function2 = function1;
            } else {
                function2 = function1;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i11 = 1048576;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
            }
            if ((i2 & 128) != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i12 = 8388608;
                } else {
                    i12 = 4194304;
                }
                i3 |= i12;
            }
            if ((4793491 & i3) != 4793490) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                if (i13 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    z10 = true;
                } else {
                    z10 = z4;
                }
                if (i6 != 0) {
                    z11 = true;
                } else {
                    z11 = z2;
                }
                if (i8 != 0) {
                    z12 = true;
                } else {
                    z12 = z5;
                }
                if (i10 != 0) {
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = new Function1() { // from class: jvd
                            public final Object invoke(Object obj) {
                                return SwipeToDismissBoxKt.d((SwipeToDismissBoxValue) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    function5 = (Function1) objRememberedValue3;
                } else {
                    function5 = function2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-741495334, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:313)");
                }
                AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material13 = swipeToDismissBoxState.getAnchoredDraggableState$material3();
                Orientation orientation6 = Orientation.Horizontal;
                if (z12) {
                    z13 = false;
                } else {
                    z13 = false;
                }
                if (swipeToDismissBoxState.getUseFlingBehavior$material3()) {
                    composerStartRestartGroup.startReplaceGroup(387581105);
                    z14 = true;
                    FlingBehavior flingBehavior7 = AnchoredDraggableDefaults.INSTANCE.flingBehavior(swipeToDismissBoxState.getAnchoredDraggableState$material3(), swipeToDismissBoxState.getPositionalThreshold$material3(), (AnimationSpec) null, composerStartRestartGroup, AnchoredDraggableDefaults.$stable << 9, 4);
                    composerStartRestartGroup.endReplaceGroup();
                    flingBehavior = flingBehavior7;
                } else {
                    z14 = true;
                    composerStartRestartGroup.startReplaceGroup(-869685853);
                    composerStartRestartGroup.endReplaceGroup();
                    flingBehavior = null;
                }
                Modifier modifierAnchoredDraggable$default6 = AnchoredDraggableKt.anchoredDraggable$default(modifier4, anchoredDraggableState$material13, orientation6, z13, (MutableInteractionSource) null, (OverscrollEffect) null, flingBehavior, 24, (Object) null);
                Alignment.Companion companion16 = Alignment.INSTANCE;
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy6 = BoxKt.maybeCachedBoxMeasurePolicy(companion16.getTopStart(), z14);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap16 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier16 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierAnchoredDraggable$default6);
                ComposeUiNode.Companion companion17 = ComposeUiNode.INSTANCE;
                boolean z114 = z12;
                constructor = companion17.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy6, companion17.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap16, companion17.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion17.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier16, companion17.getSetModifier());
                BoxScopeInstance boxScopeInstance6 = BoxScopeInstance.INSTANCE;
                Modifier.Companion companion18 = Modifier.INSTANCE;
                Modifier modifierMatchParentSize6 = boxScopeInstance6.matchParentSize(companion18);
                int i114 = (i3 << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE;
                Arrangement arrangement6 = Arrangement.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy11 = RowKt.rowMeasurePolicy(arrangement6.getStart(), companion16.getTop(), composerStartRestartGroup, 0);
                currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap17 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier17 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierMatchParentSize6);
                constructor2 = companion17.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyRowMeasurePolicy11, companion17.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap17, companion17.getSetResolvedCompositionLocals());
                setCompositeKeyHash2 = companion17.getSetCompositeKeyHash();
                if (composerM2388constructorimpl2.getInserting()) {
                    composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                } else {
                    composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier17, companion17.getSetModifier());
                RowScopeInstance rowScopeInstance6 = RowScopeInstance.INSTANCE;
                function3.invoke(rowScopeInstance6, composerStartRestartGroup, Integer.valueOf(((i114 >> 6) & 112) | 6));
                composerStartRestartGroup.endNode();
                AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material14 = swipeToDismissBoxState.getAnchoredDraggableState$material3();
                if ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) {
                    z15 = true;
                } else {
                    z15 = false;
                }
                if ((57344 & i3) == 16384) {
                    z16 = true;
                } else {
                    z16 = false;
                }
                zChangedInstance = z16 | z15 | composerStartRestartGroup.changedInstance(swipeToDismissBoxState);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    objRememberedValue = new Function2() { // from class: kvd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.c(swipeToDismissBoxState, z10, z11, (IntSize) obj, (Constraints) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function2() { // from class: kvd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.c(swipeToDismissBoxState, z10, z11, (IntSize) obj, (Constraints) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Modifier modifierDraggableAnchorsV7 = DraggableAnchorsKt.draggableAnchorsV2(companion18, anchoredDraggableState$material14, orientation6, (Function2) objRememberedValue);
                int i115 = (i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE;
                MeasurePolicy measurePolicyRowMeasurePolicy12 = RowKt.rowMeasurePolicy(arrangement6.getStart(), companion16.getTop(), composerStartRestartGroup, 0);
                currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap18 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier18 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierDraggableAnchorsV7);
                constructor3 = companion17.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl3 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicyRowMeasurePolicy12, companion17.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap18, companion17.getSetResolvedCompositionLocals());
                setCompositeKeyHash3 = companion17.getSetCompositeKeyHash();
                if (composerM2388constructorimpl3.getInserting()) {
                    composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                } else {
                    composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier18, companion17.getSetModifier());
                function4.invoke(rowScopeInstance6, composerStartRestartGroup, Integer.valueOf(((i115 >> 6) & 112) | 6));
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endNode();
                SwipeToDismissBoxValue settledValue6 = swipeToDismissBoxState.getSettledValue();
                boolean zChangedInstance7 = composerStartRestartGroup.changedInstance(swipeToDismissBoxState);
                if ((3670016 & i3) == 1048576) {
                    z17 = true;
                } else {
                    z17 = false;
                }
                z18 = zChangedInstance7 | z17;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z18) {
                    objRememberedValue2 = new SwipeToDismissBoxKt$SwipeToDismissBox$3$1(swipeToDismissBoxState, function5, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new SwipeToDismissBoxKt$SwipeToDismissBox$3$1(swipeToDismissBoxState, function5, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                EffectsKt.LaunchedEffect(settledValue6, function5, (Function2) objRememberedValue2, composerStartRestartGroup, (i3 >> 15) & 112);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z9 = z11;
                function2 = function5;
                modifier3 = modifier4;
                z8 = z114;
                z7 = z10;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                z7 = z4;
                z8 = z5;
                z9 = z2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lvd
                    public final Object invoke(Object obj, Object obj2) {
                        return SwipeToDismissBoxKt.e(swipeToDismissBoxState, function3, modifier3, z7, z9, z8, function2, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z4 = z;
        i6 = i2 & 16;
        if (i6 != 0) {
            if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changed(z2)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i3 |= i7;
            }
            i8 = i2 & 32;
            if (i8 != 0) {
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                z5 = z3;
            } else {
                z5 = z3;
                if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                    if (composerStartRestartGroup.changed(z5)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
            }
            i10 = i2 & 64;
            if (i10 != 0) {
                i3 |= 1572864;
                function2 = function1;
            } else {
                function2 = function1;
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i11 = 1048576;
                    } else {
                        i11 = 524288;
                    }
                    i3 |= i11;
                }
            }
            if ((i2 & 128) != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i12 = 8388608;
                } else {
                    i12 = 4194304;
                }
                i3 |= i12;
            }
            if ((4793491 & i3) != 4793490) {
                z6 = true;
            } else {
                z6 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
                if (i13 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    z10 = true;
                } else {
                    z10 = z4;
                }
                if (i6 != 0) {
                    z11 = true;
                } else {
                    z11 = z2;
                }
                if (i8 != 0) {
                    z12 = true;
                } else {
                    z12 = z5;
                }
                if (i10 != 0) {
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue3 = new Function1() { // from class: jvd
                            public final Object invoke(Object obj) {
                                return SwipeToDismissBoxKt.d((SwipeToDismissBoxValue) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    function5 = (Function1) objRememberedValue3;
                } else {
                    function5 = function2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-741495334, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:313)");
                }
                AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material15 = swipeToDismissBoxState.getAnchoredDraggableState$material3();
                Orientation orientation7 = Orientation.Horizontal;
                if (z12) {
                    z13 = false;
                } else {
                    z13 = false;
                }
                if (swipeToDismissBoxState.getUseFlingBehavior$material3()) {
                    composerStartRestartGroup.startReplaceGroup(387581105);
                    z14 = true;
                    FlingBehavior flingBehavior8 = AnchoredDraggableDefaults.INSTANCE.flingBehavior(swipeToDismissBoxState.getAnchoredDraggableState$material3(), swipeToDismissBoxState.getPositionalThreshold$material3(), (AnimationSpec) null, composerStartRestartGroup, AnchoredDraggableDefaults.$stable << 9, 4);
                    composerStartRestartGroup.endReplaceGroup();
                    flingBehavior = flingBehavior8;
                } else {
                    z14 = true;
                    composerStartRestartGroup.startReplaceGroup(-869685853);
                    composerStartRestartGroup.endReplaceGroup();
                    flingBehavior = null;
                }
                Modifier modifierAnchoredDraggable$default7 = AnchoredDraggableKt.anchoredDraggable$default(modifier4, anchoredDraggableState$material15, orientation7, z13, (MutableInteractionSource) null, (OverscrollEffect) null, flingBehavior, 24, (Object) null);
                Alignment.Companion companion19 = Alignment.INSTANCE;
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy7 = BoxKt.maybeCachedBoxMeasurePolicy(companion19.getTopStart(), z14);
                currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap19 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier19 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierAnchoredDraggable$default7);
                ComposeUiNode.Companion companion110 = ComposeUiNode.INSTANCE;
                boolean z115 = z12;
                constructor = companion110.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy7, companion110.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap19, companion110.getSetResolvedCompositionLocals());
                setCompositeKeyHash = companion110.getSetCompositeKeyHash();
                if (composerM2388constructorimpl.getInserting()) {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                } else {
                    composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier19, companion110.getSetModifier());
                BoxScopeInstance boxScopeInstance7 = BoxScopeInstance.INSTANCE;
                Modifier.Companion companion111 = Modifier.INSTANCE;
                Modifier modifierMatchParentSize7 = boxScopeInstance7.matchParentSize(companion111);
                int i116 = (i3 << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE;
                Arrangement arrangement7 = Arrangement.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy13 = RowKt.rowMeasurePolicy(arrangement7.getStart(), companion19.getTop(), composerStartRestartGroup, 0);
                currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap110 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier110 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierMatchParentSize7);
                constructor2 = companion110.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyRowMeasurePolicy13, companion110.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap110, companion110.getSetResolvedCompositionLocals());
                setCompositeKeyHash2 = companion110.getSetCompositeKeyHash();
                if (composerM2388constructorimpl2.getInserting()) {
                    composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                } else {
                    composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                    composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                }
                Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier110, companion110.getSetModifier());
                RowScopeInstance rowScopeInstance7 = RowScopeInstance.INSTANCE;
                function3.invoke(rowScopeInstance7, composerStartRestartGroup, Integer.valueOf(((i116 >> 6) & 112) | 6));
                composerStartRestartGroup.endNode();
                AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material16 = swipeToDismissBoxState.getAnchoredDraggableState$material3();
                if ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) {
                    z15 = true;
                } else {
                    z15 = false;
                }
                if ((57344 & i3) == 16384) {
                    z16 = true;
                } else {
                    z16 = false;
                }
                zChangedInstance = z16 | z15 | composerStartRestartGroup.changedInstance(swipeToDismissBoxState);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (zChangedInstance) {
                    objRememberedValue = new Function2() { // from class: kvd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.c(swipeToDismissBoxState, z10, z11, (IntSize) obj, (Constraints) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                } else {
                    objRememberedValue = new Function2() { // from class: kvd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.c(swipeToDismissBoxState, z10, z11, (IntSize) obj, (Constraints) obj2);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Modifier modifierDraggableAnchorsV8 = DraggableAnchorsKt.draggableAnchorsV2(companion111, anchoredDraggableState$material16, orientation7, (Function2) objRememberedValue);
                int i117 = (i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE;
                MeasurePolicy measurePolicyRowMeasurePolicy14 = RowKt.rowMeasurePolicy(arrangement7.getStart(), companion19.getTop(), composerStartRestartGroup, 0);
                currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
                CompositionLocalMap currentCompositionLocalMap111 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier111 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierDraggableAnchorsV8);
                constructor3 = companion110.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor3);
                } else {
                    composerStartRestartGroup.useNode();
                }
                composerM2388constructorimpl3 = Updater.m2388constructorimpl(composerStartRestartGroup);
                Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicyRowMeasurePolicy14, companion110.getSetMeasurePolicy());
                Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap111, companion110.getSetResolvedCompositionLocals());
                setCompositeKeyHash3 = companion110.getSetCompositeKeyHash();
                if (composerM2388constructorimpl3.getInserting()) {
                    composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                } else {
                    composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                    composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                }
                Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier111, companion110.getSetModifier());
                function4.invoke(rowScopeInstance7, composerStartRestartGroup, Integer.valueOf(((i117 >> 6) & 112) | 6));
                composerStartRestartGroup.endNode();
                composerStartRestartGroup.endNode();
                SwipeToDismissBoxValue settledValue7 = swipeToDismissBoxState.getSettledValue();
                boolean zChangedInstance8 = composerStartRestartGroup.changedInstance(swipeToDismissBoxState);
                if ((3670016 & i3) == 1048576) {
                    z17 = true;
                } else {
                    z17 = false;
                }
                z18 = zChangedInstance8 | z17;
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z18) {
                    objRememberedValue2 = new SwipeToDismissBoxKt$SwipeToDismissBox$3$1(swipeToDismissBoxState, function5, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new SwipeToDismissBoxKt$SwipeToDismissBox$3$1(swipeToDismissBoxState, function5, null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                EffectsKt.LaunchedEffect(settledValue7, function5, (Function2) objRememberedValue2, composerStartRestartGroup, (i3 >> 15) & 112);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                z9 = z11;
                function2 = function5;
                modifier3 = modifier4;
                z8 = z115;
                z7 = z10;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
                z7 = z4;
                z8 = z5;
                z9 = z2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lvd
                    public final Object invoke(Object obj, Object obj2) {
                        return SwipeToDismissBoxKt.e(swipeToDismissBoxState, function3, modifier3, z7, z9, z8, function2, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        i8 = i2 & 32;
        if (i8 != 0) {
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            z5 = z3;
        } else {
            z5 = z3;
            if ((i & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
                if (composerStartRestartGroup.changed(z5)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i3 |= i9;
            }
        }
        i10 = i2 & 64;
        if (i10 != 0) {
            i3 |= 1572864;
            function2 = function1;
        } else {
            function2 = function1;
            if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i11 = 1048576;
                } else {
                    i11 = 524288;
                }
                i3 |= i11;
            }
        }
        if ((i2 & 128) != 0) {
            i3 |= 12582912;
        } else if ((i & 12582912) == 0) {
            if (composerStartRestartGroup.changedInstance(function4)) {
                i12 = 8388608;
            } else {
                i12 = 4194304;
            }
            i3 |= i12;
        }
        if ((4793491 & i3) != 4793490) {
            z6 = true;
        } else {
            z6 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z6, i3 & 1)) {
            if (i13 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i4 != 0) {
                z10 = true;
            } else {
                z10 = z4;
            }
            if (i6 != 0) {
                z11 = true;
            } else {
                z11 = z2;
            }
            if (i8 != 0) {
                z12 = true;
            } else {
                z12 = z5;
            }
            if (i10 != 0) {
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue3 = new Function1() { // from class: jvd
                        public final Object invoke(Object obj) {
                            return SwipeToDismissBoxKt.d((SwipeToDismissBoxValue) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                function5 = (Function1) objRememberedValue3;
            } else {
                function5 = function2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-741495334, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:313)");
            }
            AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material17 = swipeToDismissBoxState.getAnchoredDraggableState$material3();
            Orientation orientation8 = Orientation.Horizontal;
            if (z12) {
                z13 = false;
            } else {
                z13 = false;
            }
            if (swipeToDismissBoxState.getUseFlingBehavior$material3()) {
                composerStartRestartGroup.startReplaceGroup(387581105);
                z14 = true;
                FlingBehavior flingBehavior9 = AnchoredDraggableDefaults.INSTANCE.flingBehavior(swipeToDismissBoxState.getAnchoredDraggableState$material3(), swipeToDismissBoxState.getPositionalThreshold$material3(), (AnimationSpec) null, composerStartRestartGroup, AnchoredDraggableDefaults.$stable << 9, 4);
                composerStartRestartGroup.endReplaceGroup();
                flingBehavior = flingBehavior9;
            } else {
                z14 = true;
                composerStartRestartGroup.startReplaceGroup(-869685853);
                composerStartRestartGroup.endReplaceGroup();
                flingBehavior = null;
            }
            Modifier modifierAnchoredDraggable$default8 = AnchoredDraggableKt.anchoredDraggable$default(modifier4, anchoredDraggableState$material17, orientation8, z13, (MutableInteractionSource) null, (OverscrollEffect) null, flingBehavior, 24, (Object) null);
            Alignment.Companion companion112 = Alignment.INSTANCE;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy8 = BoxKt.maybeCachedBoxMeasurePolicy(companion112.getTopStart(), z14);
            currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap112 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier112 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierAnchoredDraggable$default8);
            ComposeUiNode.Companion companion113 = ComposeUiNode.INSTANCE;
            boolean z116 = z12;
            constructor = companion113.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyMaybeCachedBoxMeasurePolicy8, companion113.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap112, companion113.getSetResolvedCompositionLocals());
            setCompositeKeyHash = companion113.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting()) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            } else {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier112, companion113.getSetModifier());
            BoxScopeInstance boxScopeInstance8 = BoxScopeInstance.INSTANCE;
            Modifier.Companion companion114 = Modifier.INSTANCE;
            Modifier modifierMatchParentSize8 = boxScopeInstance8.matchParentSize(companion114);
            int i118 = (i3 << 6) & V4Signature.MAX_SIGNING_INFOS_SIZE;
            Arrangement arrangement8 = Arrangement.INSTANCE;
            MeasurePolicy measurePolicyRowMeasurePolicy15 = RowKt.rowMeasurePolicy(arrangement8.getStart(), companion112.getTop(), composerStartRestartGroup, 0);
            currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap113 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier113 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierMatchParentSize8);
            constructor2 = companion113.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM2388constructorimpl2 = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl2, measurePolicyRowMeasurePolicy15, companion113.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl2, currentCompositionLocalMap113, companion113.getSetResolvedCompositionLocals());
            setCompositeKeyHash2 = companion113.getSetCompositeKeyHash();
            if (composerM2388constructorimpl2.getInserting()) {
                composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            } else {
                composerM2388constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                composerM2388constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
            }
            Updater.m2396setimpl(composerM2388constructorimpl2, modifierMaterializeModifier113, companion113.getSetModifier());
            RowScopeInstance rowScopeInstance8 = RowScopeInstance.INSTANCE;
            function3.invoke(rowScopeInstance8, composerStartRestartGroup, Integer.valueOf(((i118 >> 6) & 112) | 6));
            composerStartRestartGroup.endNode();
            AnchoredDraggableState<SwipeToDismissBoxValue> anchoredDraggableState$material18 = swipeToDismissBoxState.getAnchoredDraggableState$material3();
            if ((i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) == 2048) {
                z15 = true;
            } else {
                z15 = false;
            }
            if ((57344 & i3) == 16384) {
                z16 = true;
            } else {
                z16 = false;
            }
            zChangedInstance = z16 | z15 | composerStartRestartGroup.changedInstance(swipeToDismissBoxState);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChangedInstance) {
                objRememberedValue = new Function2() { // from class: kvd
                    public final Object invoke(Object obj, Object obj2) {
                        return SwipeToDismissBoxKt.c(swipeToDismissBoxState, z10, z11, (IntSize) obj, (Constraints) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            } else {
                objRememberedValue = new Function2() { // from class: kvd
                    public final Object invoke(Object obj, Object obj2) {
                        return SwipeToDismissBoxKt.c(swipeToDismissBoxState, z10, z11, (IntSize) obj, (Constraints) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifierDraggableAnchorsV9 = DraggableAnchorsKt.draggableAnchorsV2(companion114, anchoredDraggableState$material18, orientation8, (Function2) objRememberedValue);
            int i119 = (i3 >> 12) & V4Signature.MAX_SIGNING_INFOS_SIZE;
            MeasurePolicy measurePolicyRowMeasurePolicy16 = RowKt.rowMeasurePolicy(arrangement8.getStart(), companion112.getTop(), composerStartRestartGroup, 0);
            currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap114 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier114 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierDraggableAnchorsV9);
            constructor3 = companion113.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor3);
            } else {
                composerStartRestartGroup.useNode();
            }
            composerM2388constructorimpl3 = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl3, measurePolicyRowMeasurePolicy16, companion113.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl3, currentCompositionLocalMap114, companion113.getSetResolvedCompositionLocals());
            setCompositeKeyHash3 = companion113.getSetCompositeKeyHash();
            if (composerM2388constructorimpl3.getInserting()) {
                composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            } else {
                composerM2388constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                composerM2388constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
            }
            Updater.m2396setimpl(composerM2388constructorimpl3, modifierMaterializeModifier114, companion113.getSetModifier());
            function4.invoke(rowScopeInstance8, composerStartRestartGroup, Integer.valueOf(((i119 >> 6) & 112) | 6));
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endNode();
            SwipeToDismissBoxValue settledValue8 = swipeToDismissBoxState.getSettledValue();
            boolean zChangedInstance9 = composerStartRestartGroup.changedInstance(swipeToDismissBoxState);
            if ((3670016 & i3) == 1048576) {
                z17 = true;
            } else {
                z17 = false;
            }
            z18 = zChangedInstance9 | z17;
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z18) {
                objRememberedValue2 = new SwipeToDismissBoxKt$SwipeToDismissBox$3$1(swipeToDismissBoxState, function5, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = new SwipeToDismissBoxKt$SwipeToDismissBox$3$1(swipeToDismissBoxState, function5, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            EffectsKt.LaunchedEffect(settledValue8, function5, (Function2) objRememberedValue2, composerStartRestartGroup, (i3 >> 15) & 112);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z9 = z11;
            function2 = function5;
            modifier3 = modifier4;
            z8 = z116;
            z7 = z10;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
            z7 = z4;
            z8 = z5;
            z9 = z2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: lvd
                public final Object invoke(Object obj, Object obj2) {
                    return SwipeToDismissBoxKt.e(swipeToDismissBoxState, function3, modifier3, z7, z9, z8, function2, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static boolean a(SwipeToDismissBoxValue swipeToDismissBoxValue) {
        return true;
    }

    public static Unit b(IntSize intSize, boolean z, boolean z2, DraggableAnchorsConfig draggableAnchorsConfig) {
        float fM6197unboximpl = (int) (intSize.m6197unboximpl() >> 32);
        draggableAnchorsConfig.at(SwipeToDismissBoxValue.Settled, 0.0f);
        if (z) {
            draggableAnchorsConfig.at(SwipeToDismissBoxValue.StartToEnd, fM6197unboximpl);
        }
        if (z2) {
            draggableAnchorsConfig.at(SwipeToDismissBoxValue.EndToStart, -fM6197unboximpl);
        }
        return Unit.INSTANCE;
    }

    public static Pair c(SwipeToDismissBoxState swipeToDismissBoxState, final boolean z, final boolean z2, final IntSize intSize, Constraints constraints) {
        return TuplesKt.to(AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: mvd
            public final Object invoke(Object obj) {
                return SwipeToDismissBoxKt.b(intSize, z, z2, (DraggableAnchorsConfig) obj);
            }
        }), swipeToDismissBoxState.getTargetValue());
    }

    public static Unit d(SwipeToDismissBoxValue swipeToDismissBoxValue) {
        return Unit.INSTANCE;
    }

    public static Unit e(SwipeToDismissBoxState swipeToDismissBoxState, Function3 function3, Modifier modifier, boolean z, boolean z2, boolean z3, Function1 function1, Function3 function4, int i, int i2, Composer composer, int i3) {
        SwipeToDismissBox(swipeToDismissBoxState, function3, modifier, z, z2, z3, function1, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static SwipeToDismissBoxState f(SwipeToDismissBoxValue swipeToDismissBoxValue, Function1 function1) {
        return new SwipeToDismissBoxState(swipeToDismissBoxValue, function1);
    }

    public static Unit g(SwipeToDismissBoxValue swipeToDismissBoxValue) {
        return Unit.INSTANCE;
    }

    public static SwipeToDismissBoxState h(SwipeToDismissBoxValue swipeToDismissBoxValue, Density density, Function1 function1, Function1 function2) {
        return new SwipeToDismissBoxState(swipeToDismissBoxValue, density, function1, function2);
    }

    public static Unit i(SwipeToDismissBoxState swipeToDismissBoxState, Function3 function3, Modifier modifier, boolean z, boolean z2, boolean z3, Function3 function4, int i, int i2, Composer composer, int i3) {
        SwipeToDismissBox(swipeToDismissBoxState, function3, modifier, z, z2, z3, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = DraggableAnchorsKt.ConfirmValueChangeDeprecated, replaceWith = @ReplaceWith(expression = "rememberSwipeToDismissBoxState(initialValue, positionalThreshold)", imports = {}))
    public static final SwipeToDismissBoxState rememberSwipeToDismissBoxState(final SwipeToDismissBoxValue swipeToDismissBoxValue, final Function1<? super SwipeToDismissBoxValue, Boolean> function1, final Function1<? super Float, Float> function2, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            swipeToDismissBoxValue = SwipeToDismissBoxValue.Settled;
        }
        if ((i2 & 2) != 0) {
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: fvd
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(SwipeToDismissBoxKt.a((SwipeToDismissBoxValue) obj));
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            function1 = (Function1) objRememberedValue;
        }
        if ((i2 & 4) != 0) {
            function2 = SwipeToDismissBoxDefaults.INSTANCE.getPositionalThreshold(composer, 6);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-246335487, i, -1, "androidx.compose.material3.rememberSwipeToDismissBoxState (SwipeToDismissBox.kt:273)");
        }
        final Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
        Object[] objArr = new Object[0];
        Saver<SwipeToDismissBoxState, SwipeToDismissBoxValue> Saver = SwipeToDismissBoxState.INSTANCE.Saver(function1, function2, density);
        boolean z = true;
        boolean zChanged = (((6 ^ (i & 14)) > 4 && composer.changed(swipeToDismissBoxValue.ordinal())) || (i & 6) == 4) | composer.changed(density) | ((((i & 112) ^ 48) > 32 && composer.changed(function1)) || (i & 48) == 32);
        if ((((i & 896) ^ AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) <= 256 || !composer.changed(function2)) && (i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) != 256) {
            z = false;
        }
        boolean z2 = zChanged | z;
        Object objRememberedValue2 = composer.rememberedValue();
        if (z2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new Function0() { // from class: gvd
                public final Object invoke() {
                    return SwipeToDismissBoxKt.h(swipeToDismissBoxValue, density, function1, function2);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        SwipeToDismissBoxState swipeToDismissBoxState = (SwipeToDismissBoxState) RememberSaveableKt.m2564rememberSaveable(objArr, (Saver) Saver, (Function0) objRememberedValue2, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return swipeToDismissBoxState;
    }

    public static final SwipeToDismissBoxState rememberSwipeToDismissBoxState(final SwipeToDismissBoxValue swipeToDismissBoxValue, final Function1<? super Float, Float> function1, Composer composer, int i, int i2) {
        if ((i2 & 1) != 0) {
            swipeToDismissBoxValue = SwipeToDismissBoxValue.Settled;
        }
        if ((i2 & 2) != 0) {
            function1 = SwipeToDismissBoxDefaults.INSTANCE.getPositionalThreshold(composer, 6);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-470572556, i, -1, "androidx.compose.material3.rememberSwipeToDismissBoxState (SwipeToDismissBox.kt:243)");
        }
        Object[] objArr = new Object[0];
        Saver<SwipeToDismissBoxState, SwipeToDismissBoxValue> Saver = SwipeToDismissBoxState.INSTANCE.Saver(function1);
        boolean z = true;
        boolean z2 = ((6 ^ (i & 14)) > 4 && composer.changed(swipeToDismissBoxValue.ordinal())) || (i & 6) == 4;
        if ((((i & 112) ^ 48) <= 32 || !composer.changed(function1)) && (i & 48) != 32) {
            z = false;
        }
        boolean z3 = z2 | z;
        Object objRememberedValue = composer.rememberedValue();
        if (z3 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: nvd
                public final Object invoke() {
                    return SwipeToDismissBoxKt.f(swipeToDismissBoxValue, function1);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        SwipeToDismissBoxState swipeToDismissBoxState = (SwipeToDismissBoxState) RememberSaveableKt.m2564rememberSaveable(objArr, (Saver) Saver, (Function0) objRememberedValue, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return swipeToDismissBoxState;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x011b  */
    /* JADX WARN: Code duplicated, block: B:105:0x0152  */
    /* JADX WARN: Code duplicated, block: B:107:0x015a  */
    /* JADX WARN: Code duplicated, block: B:110:0x016b  */
    /* JADX WARN: Code duplicated, block: B:112:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:36:0x0060  */
    /* JADX WARN: Code duplicated, block: B:38:0x0065  */
    /* JADX WARN: Code duplicated, block: B:40:0x0069  */
    /* JADX WARN: Code duplicated, block: B:42:0x0071  */
    /* JADX WARN: Code duplicated, block: B:43:0x0074  */
    /* JADX WARN: Code duplicated, block: B:47:0x007b  */
    /* JADX WARN: Code duplicated, block: B:49:0x0080  */
    /* JADX WARN: Code duplicated, block: B:51:0x0084  */
    /* JADX WARN: Code duplicated, block: B:53:0x008c  */
    /* JADX WARN: Code duplicated, block: B:54:0x008f  */
    /* JADX WARN: Code duplicated, block: B:58:0x0098  */
    /* JADX WARN: Code duplicated, block: B:60:0x009c  */
    /* JADX WARN: Code duplicated, block: B:62:0x009f  */
    /* JADX WARN: Code duplicated, block: B:64:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:65:0x00aa  */
    /* JADX WARN: Code duplicated, block: B:69:0x00b3  */
    /* JADX WARN: Code duplicated, block: B:71:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:73:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:75:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:76:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:80:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:81:0x00db  */
    /* JADX WARN: Code duplicated, block: B:84:0x00e4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:85:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:86:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:89:0x00ee  */
    /* JADX WARN: Code duplicated, block: B:90:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:92:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:93:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:95:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:96:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:99:0x0106  */
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use updated signature with onDismissed parameter.")
    public static final /* synthetic */ void SwipeToDismissBox(final SwipeToDismissBoxState swipeToDismissBoxState, final Function3 function3, Modifier modifier, boolean z, boolean z2, boolean z3, final Function3 function4, Composer composer, final int i, final int i2) {
        SwipeToDismissBoxState swipeToDismissBoxState2;
        int i3;
        Function3 function5;
        Modifier modifier2;
        int i4;
        boolean z4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        boolean z5;
        Composer composer2;
        final boolean z6;
        final Modifier modifier3;
        final boolean z7;
        final boolean z8;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        boolean z9;
        boolean z10;
        boolean z11;
        Object objRememberedValue;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1807005299);
        if ((i2 & 1) != 0) {
            i3 = i | 6;
            swipeToDismissBoxState2 = swipeToDismissBoxState;
        } else {
            swipeToDismissBoxState2 = swipeToDismissBoxState;
            if ((i & 6) == 0) {
                i3 = (composerStartRestartGroup.changedInstance(swipeToDismissBoxState2) ? 4 : 2) | i;
            } else {
                i3 = i;
            }
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
            function5 = function3;
        } else {
            function5 = function3;
            if ((i & 48) == 0) {
                i3 |= composerStartRestartGroup.changedInstance(function5) ? 32 : 16;
            }
        }
        int i11 = i2 & 4;
        if (i11 == 0) {
            if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : 128;
            }
            i4 = i2 & 8;
            if (i4 != 0) {
                if ((i & 3072) == 0) {
                    z4 = z;
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
                        if (composerStartRestartGroup.changed(z2)) {
                            i7 = 16384;
                        } else {
                            i7 = 8192;
                        }
                        i3 |= i7;
                    }
                    i8 = i2 & 32;
                    if (i8 != 0) {
                        if ((196608 & i) == 0) {
                            if (composerStartRestartGroup.changed(z3)) {
                                i9 = 131072;
                            } else {
                                i9 = 65536;
                            }
                            i3 |= i9;
                        }
                        if ((i2 & 64) != 0) {
                            if ((i & 1572864) == 0) {
                                if (composerStartRestartGroup.changedInstance(function4)) {
                                    i10 = 1048576;
                                } else {
                                    i10 = 524288;
                                }
                                i3 |= i10;
                            }
                            if ((i3 & 599187) != 599186) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                                if (i11 != 0) {
                                    modifier4 = Modifier.INSTANCE;
                                } else {
                                    modifier4 = modifier2;
                                }
                                if (i4 != 0) {
                                    z9 = true;
                                } else {
                                    z9 = z4;
                                }
                                if (i6 != 0) {
                                    z10 = true;
                                } else {
                                    z10 = z2;
                                }
                                if (i8 != 0) {
                                    z11 = true;
                                } else {
                                    z11 = z3;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                                }
                                objRememberedValue = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                    objRememberedValue = new Function1() { // from class: hvd
                                        public final Object invoke(Object obj) {
                                            return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                                }
                                composer2 = composerStartRestartGroup;
                                SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                                modifier3 = modifier4;
                                z7 = z9;
                                z6 = z10;
                                z8 = z11;
                            } else {
                                composer2 = composerStartRestartGroup;
                                composer2.skipToGroupEnd();
                                z6 = z2;
                                modifier3 = modifier2;
                                z7 = z4;
                                z8 = z3;
                            }
                            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                            if (scopeUpdateScopeEndRestartGroup != null) {
                                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                                    public final Object invoke(Object obj, Object obj2) {
                                        return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                    }
                                });
                            }
                        }
                        i3 |= 1572864;
                        if ((i3 & 599187) != 599186) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                            if (i11 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i4 != 0) {
                                z9 = true;
                            } else {
                                z9 = z4;
                            }
                            if (i6 != 0) {
                                z10 = true;
                            } else {
                                z10 = z2;
                            }
                            if (i8 != 0) {
                                z11 = true;
                            } else {
                                z11 = z3;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function1() { // from class: hvd
                                    public final Object invoke(Object obj) {
                                        return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composer2 = composerStartRestartGroup;
                            SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            z7 = z9;
                            z6 = z10;
                            z8 = z11;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            z6 = z2;
                            modifier3 = modifier2;
                            z7 = z4;
                            z8 = z3;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                    if ((i2 & 64) != 0) {
                        if ((i & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i10 = 1048576;
                            } else {
                                i10 = 524288;
                            }
                            i3 |= i10;
                        }
                        if ((i3 & 599187) != 599186) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                            if (i11 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i4 != 0) {
                                z9 = true;
                            } else {
                                z9 = z4;
                            }
                            if (i6 != 0) {
                                z10 = true;
                            } else {
                                z10 = z2;
                            }
                            if (i8 != 0) {
                                z11 = true;
                            } else {
                                z11 = z3;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function1() { // from class: hvd
                                    public final Object invoke(Object obj) {
                                        return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composer2 = composerStartRestartGroup;
                            SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            z7 = z9;
                            z6 = z10;
                            z8 = z11;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            z6 = z2;
                            modifier3 = modifier2;
                            z7 = z4;
                            z8 = z3;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 1572864;
                    if ((i3 & 599187) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                        if (i11 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            z9 = true;
                        } else {
                            z9 = z4;
                        }
                        if (i6 != 0) {
                            z10 = true;
                        } else {
                            z10 = z2;
                        }
                        if (i8 != 0) {
                            z11 = true;
                        } else {
                            z11 = z3;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function1() { // from class: hvd
                                public final Object invoke(Object obj) {
                                    return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composer2 = composerStartRestartGroup;
                        SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        z7 = z9;
                        z6 = z10;
                        z8 = z11;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        z6 = z2;
                        modifier3 = modifier2;
                        z7 = z4;
                        z8 = z3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 24576;
                i8 = i2 & 32;
                if (i8 != 0) {
                    if ((196608 & i) == 0) {
                        if (composerStartRestartGroup.changed(z3)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                    if ((i2 & 64) != 0) {
                        if ((i & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i10 = 1048576;
                            } else {
                                i10 = 524288;
                            }
                            i3 |= i10;
                        }
                        if ((i3 & 599187) != 599186) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                            if (i11 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i4 != 0) {
                                z9 = true;
                            } else {
                                z9 = z4;
                            }
                            if (i6 != 0) {
                                z10 = true;
                            } else {
                                z10 = z2;
                            }
                            if (i8 != 0) {
                                z11 = true;
                            } else {
                                z11 = z3;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function1() { // from class: hvd
                                    public final Object invoke(Object obj) {
                                        return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composer2 = composerStartRestartGroup;
                            SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            z7 = z9;
                            z6 = z10;
                            z8 = z11;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            z6 = z2;
                            modifier3 = modifier2;
                            z7 = z4;
                            z8 = z3;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 1572864;
                    if ((i3 & 599187) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                        if (i11 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            z9 = true;
                        } else {
                            z9 = z4;
                        }
                        if (i6 != 0) {
                            z10 = true;
                        } else {
                            z10 = z2;
                        }
                        if (i8 != 0) {
                            z11 = true;
                        } else {
                            z11 = z3;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function1() { // from class: hvd
                                public final Object invoke(Object obj) {
                                    return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composer2 = composerStartRestartGroup;
                        SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        z7 = z9;
                        z6 = z10;
                        z8 = z11;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        z6 = z2;
                        modifier3 = modifier2;
                        z7 = z4;
                        z8 = z3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                if ((i2 & 64) != 0) {
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i10 = 1048576;
                        } else {
                            i10 = 524288;
                        }
                        i3 |= i10;
                    }
                    if ((i3 & 599187) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                        if (i11 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            z9 = true;
                        } else {
                            z9 = z4;
                        }
                        if (i6 != 0) {
                            z10 = true;
                        } else {
                            z10 = z2;
                        }
                        if (i8 != 0) {
                            z11 = true;
                        } else {
                            z11 = z3;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function1() { // from class: hvd
                                public final Object invoke(Object obj) {
                                    return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composer2 = composerStartRestartGroup;
                        SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        z7 = z9;
                        z6 = z10;
                        z8 = z11;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        z6 = z2;
                        modifier3 = modifier2;
                        z7 = z4;
                        z8 = z3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    if (i11 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z9 = true;
                    } else {
                        z9 = z4;
                    }
                    if (i6 != 0) {
                        z10 = true;
                    } else {
                        z10 = z2;
                    }
                    if (i8 != 0) {
                        z11 = true;
                    } else {
                        z11 = z3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function1() { // from class: hvd
                            public final Object invoke(Object obj) {
                                return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composer2 = composerStartRestartGroup;
                    SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    z7 = z9;
                    z6 = z10;
                    z8 = z11;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z6 = z2;
                    modifier3 = modifier2;
                    z7 = z4;
                    z8 = z3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 3072;
            z4 = z;
            i6 = i2 & 16;
            if (i6 != 0) {
                if ((i & 24576) == 0) {
                    if (composerStartRestartGroup.changed(z2)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 32;
                if (i8 != 0) {
                    if ((196608 & i) == 0) {
                        if (composerStartRestartGroup.changed(z3)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                    if ((i2 & 64) != 0) {
                        if ((i & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i10 = 1048576;
                            } else {
                                i10 = 524288;
                            }
                            i3 |= i10;
                        }
                        if ((i3 & 599187) != 599186) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                            if (i11 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i4 != 0) {
                                z9 = true;
                            } else {
                                z9 = z4;
                            }
                            if (i6 != 0) {
                                z10 = true;
                            } else {
                                z10 = z2;
                            }
                            if (i8 != 0) {
                                z11 = true;
                            } else {
                                z11 = z3;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function1() { // from class: hvd
                                    public final Object invoke(Object obj) {
                                        return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composer2 = composerStartRestartGroup;
                            SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            z7 = z9;
                            z6 = z10;
                            z8 = z11;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            z6 = z2;
                            modifier3 = modifier2;
                            z7 = z4;
                            z8 = z3;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 1572864;
                    if ((i3 & 599187) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                        if (i11 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            z9 = true;
                        } else {
                            z9 = z4;
                        }
                        if (i6 != 0) {
                            z10 = true;
                        } else {
                            z10 = z2;
                        }
                        if (i8 != 0) {
                            z11 = true;
                        } else {
                            z11 = z3;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function1() { // from class: hvd
                                public final Object invoke(Object obj) {
                                    return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composer2 = composerStartRestartGroup;
                        SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        z7 = z9;
                        z6 = z10;
                        z8 = z11;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        z6 = z2;
                        modifier3 = modifier2;
                        z7 = z4;
                        z8 = z3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                if ((i2 & 64) != 0) {
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i10 = 1048576;
                        } else {
                            i10 = 524288;
                        }
                        i3 |= i10;
                    }
                    if ((i3 & 599187) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                        if (i11 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            z9 = true;
                        } else {
                            z9 = z4;
                        }
                        if (i6 != 0) {
                            z10 = true;
                        } else {
                            z10 = z2;
                        }
                        if (i8 != 0) {
                            z11 = true;
                        } else {
                            z11 = z3;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function1() { // from class: hvd
                                public final Object invoke(Object obj) {
                                    return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composer2 = composerStartRestartGroup;
                        SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        z7 = z9;
                        z6 = z10;
                        z8 = z11;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        z6 = z2;
                        modifier3 = modifier2;
                        z7 = z4;
                        z8 = z3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    if (i11 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z9 = true;
                    } else {
                        z9 = z4;
                    }
                    if (i6 != 0) {
                        z10 = true;
                    } else {
                        z10 = z2;
                    }
                    if (i8 != 0) {
                        z11 = true;
                    } else {
                        z11 = z3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function1() { // from class: hvd
                            public final Object invoke(Object obj) {
                                return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composer2 = composerStartRestartGroup;
                    SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    z7 = z9;
                    z6 = z10;
                    z8 = z11;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z6 = z2;
                    modifier3 = modifier2;
                    z7 = z4;
                    z8 = z3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            i8 = i2 & 32;
            if (i8 != 0) {
                if ((196608 & i) == 0) {
                    if (composerStartRestartGroup.changed(z3)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
                if ((i2 & 64) != 0) {
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i10 = 1048576;
                        } else {
                            i10 = 524288;
                        }
                        i3 |= i10;
                    }
                    if ((i3 & 599187) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                        if (i11 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            z9 = true;
                        } else {
                            z9 = z4;
                        }
                        if (i6 != 0) {
                            z10 = true;
                        } else {
                            z10 = z2;
                        }
                        if (i8 != 0) {
                            z11 = true;
                        } else {
                            z11 = z3;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function1() { // from class: hvd
                                public final Object invoke(Object obj) {
                                    return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composer2 = composerStartRestartGroup;
                        SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        z7 = z9;
                        z6 = z10;
                        z8 = z11;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        z6 = z2;
                        modifier3 = modifier2;
                        z7 = z4;
                        z8 = z3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    if (i11 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z9 = true;
                    } else {
                        z9 = z4;
                    }
                    if (i6 != 0) {
                        z10 = true;
                    } else {
                        z10 = z2;
                    }
                    if (i8 != 0) {
                        z11 = true;
                    } else {
                        z11 = z3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function1() { // from class: hvd
                            public final Object invoke(Object obj) {
                                return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composer2 = composerStartRestartGroup;
                    SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    z7 = z9;
                    z6 = z10;
                    z8 = z11;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z6 = z2;
                    modifier3 = modifier2;
                    z7 = z4;
                    z8 = z3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            if ((i2 & 64) != 0) {
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i10 = 1048576;
                    } else {
                        i10 = 524288;
                    }
                    i3 |= i10;
                }
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    if (i11 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z9 = true;
                    } else {
                        z9 = z4;
                    }
                    if (i6 != 0) {
                        z10 = true;
                    } else {
                        z10 = z2;
                    }
                    if (i8 != 0) {
                        z11 = true;
                    } else {
                        z11 = z3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function1() { // from class: hvd
                            public final Object invoke(Object obj) {
                                return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composer2 = composerStartRestartGroup;
                    SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    z7 = z9;
                    z6 = z10;
                    z8 = z11;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z6 = z2;
                    modifier3 = modifier2;
                    z7 = z4;
                    z8 = z3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            if ((i3 & 599187) != 599186) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                if (i11 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    z9 = true;
                } else {
                    z9 = z4;
                }
                if (i6 != 0) {
                    z10 = true;
                } else {
                    z10 = z2;
                }
                if (i8 != 0) {
                    z11 = true;
                } else {
                    z11 = z3;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: hvd
                        public final Object invoke(Object obj) {
                            return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composer2 = composerStartRestartGroup;
                SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                z7 = z9;
                z6 = z10;
                z8 = z11;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                z6 = z2;
                modifier3 = modifier2;
                z7 = z4;
                z8 = z3;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                    public final Object invoke(Object obj, Object obj2) {
                        return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP;
        modifier2 = modifier;
        i4 = i2 & 8;
        if (i4 != 0) {
            if ((i & 3072) == 0) {
                z4 = z;
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
                    if (composerStartRestartGroup.changed(z2)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 32;
                if (i8 != 0) {
                    if ((196608 & i) == 0) {
                        if (composerStartRestartGroup.changed(z3)) {
                            i9 = 131072;
                        } else {
                            i9 = 65536;
                        }
                        i3 |= i9;
                    }
                    if ((i2 & 64) != 0) {
                        if ((i & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function4)) {
                                i10 = 1048576;
                            } else {
                                i10 = 524288;
                            }
                            i3 |= i10;
                        }
                        if ((i3 & 599187) != 599186) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                            if (i11 != 0) {
                                modifier4 = Modifier.INSTANCE;
                            } else {
                                modifier4 = modifier2;
                            }
                            if (i4 != 0) {
                                z9 = true;
                            } else {
                                z9 = z4;
                            }
                            if (i6 != 0) {
                                z10 = true;
                            } else {
                                z10 = z2;
                            }
                            if (i8 != 0) {
                                z11 = true;
                            } else {
                                z11 = z3;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                objRememberedValue = new Function1() { // from class: hvd
                                    public final Object invoke(Object obj) {
                                        return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            composer2 = composerStartRestartGroup;
                            SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            modifier3 = modifier4;
                            z7 = z9;
                            z6 = z10;
                            z8 = z11;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            z6 = z2;
                            modifier3 = modifier2;
                            z7 = z4;
                            z8 = z3;
                        }
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                                public final Object invoke(Object obj, Object obj2) {
                                    return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i3 |= 1572864;
                    if ((i3 & 599187) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                        if (i11 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            z9 = true;
                        } else {
                            z9 = z4;
                        }
                        if (i6 != 0) {
                            z10 = true;
                        } else {
                            z10 = z2;
                        }
                        if (i8 != 0) {
                            z11 = true;
                        } else {
                            z11 = z3;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function1() { // from class: hvd
                                public final Object invoke(Object obj) {
                                    return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composer2 = composerStartRestartGroup;
                        SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        z7 = z9;
                        z6 = z10;
                        z8 = z11;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        z6 = z2;
                        modifier3 = modifier2;
                        z7 = z4;
                        z8 = z3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
                if ((i2 & 64) != 0) {
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i10 = 1048576;
                        } else {
                            i10 = 524288;
                        }
                        i3 |= i10;
                    }
                    if ((i3 & 599187) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                        if (i11 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            z9 = true;
                        } else {
                            z9 = z4;
                        }
                        if (i6 != 0) {
                            z10 = true;
                        } else {
                            z10 = z2;
                        }
                        if (i8 != 0) {
                            z11 = true;
                        } else {
                            z11 = z3;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function1() { // from class: hvd
                                public final Object invoke(Object obj) {
                                    return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composer2 = composerStartRestartGroup;
                        SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        z7 = z9;
                        z6 = z10;
                        z8 = z11;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        z6 = z2;
                        modifier3 = modifier2;
                        z7 = z4;
                        z8 = z3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    if (i11 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z9 = true;
                    } else {
                        z9 = z4;
                    }
                    if (i6 != 0) {
                        z10 = true;
                    } else {
                        z10 = z2;
                    }
                    if (i8 != 0) {
                        z11 = true;
                    } else {
                        z11 = z3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function1() { // from class: hvd
                            public final Object invoke(Object obj) {
                                return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composer2 = composerStartRestartGroup;
                    SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    z7 = z9;
                    z6 = z10;
                    z8 = z11;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z6 = z2;
                    modifier3 = modifier2;
                    z7 = z4;
                    z8 = z3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 24576;
            i8 = i2 & 32;
            if (i8 != 0) {
                if ((196608 & i) == 0) {
                    if (composerStartRestartGroup.changed(z3)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
                if ((i2 & 64) != 0) {
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i10 = 1048576;
                        } else {
                            i10 = 524288;
                        }
                        i3 |= i10;
                    }
                    if ((i3 & 599187) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                        if (i11 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            z9 = true;
                        } else {
                            z9 = z4;
                        }
                        if (i6 != 0) {
                            z10 = true;
                        } else {
                            z10 = z2;
                        }
                        if (i8 != 0) {
                            z11 = true;
                        } else {
                            z11 = z3;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function1() { // from class: hvd
                                public final Object invoke(Object obj) {
                                    return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composer2 = composerStartRestartGroup;
                        SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        z7 = z9;
                        z6 = z10;
                        z8 = z11;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        z6 = z2;
                        modifier3 = modifier2;
                        z7 = z4;
                        z8 = z3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    if (i11 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z9 = true;
                    } else {
                        z9 = z4;
                    }
                    if (i6 != 0) {
                        z10 = true;
                    } else {
                        z10 = z2;
                    }
                    if (i8 != 0) {
                        z11 = true;
                    } else {
                        z11 = z3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function1() { // from class: hvd
                            public final Object invoke(Object obj) {
                                return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composer2 = composerStartRestartGroup;
                    SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    z7 = z9;
                    z6 = z10;
                    z8 = z11;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z6 = z2;
                    modifier3 = modifier2;
                    z7 = z4;
                    z8 = z3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            if ((i2 & 64) != 0) {
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i10 = 1048576;
                    } else {
                        i10 = 524288;
                    }
                    i3 |= i10;
                }
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    if (i11 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z9 = true;
                    } else {
                        z9 = z4;
                    }
                    if (i6 != 0) {
                        z10 = true;
                    } else {
                        z10 = z2;
                    }
                    if (i8 != 0) {
                        z11 = true;
                    } else {
                        z11 = z3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function1() { // from class: hvd
                            public final Object invoke(Object obj) {
                                return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composer2 = composerStartRestartGroup;
                    SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    z7 = z9;
                    z6 = z10;
                    z8 = z11;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z6 = z2;
                    modifier3 = modifier2;
                    z7 = z4;
                    z8 = z3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            if ((i3 & 599187) != 599186) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                if (i11 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    z9 = true;
                } else {
                    z9 = z4;
                }
                if (i6 != 0) {
                    z10 = true;
                } else {
                    z10 = z2;
                }
                if (i8 != 0) {
                    z11 = true;
                } else {
                    z11 = z3;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: hvd
                        public final Object invoke(Object obj) {
                            return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composer2 = composerStartRestartGroup;
                SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                z7 = z9;
                z6 = z10;
                z8 = z11;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                z6 = z2;
                modifier3 = modifier2;
                z7 = z4;
                z8 = z3;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                    public final Object invoke(Object obj, Object obj2) {
                        return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 3072;
        z4 = z;
        i6 = i2 & 16;
        if (i6 != 0) {
            if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changed(z2)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i3 |= i7;
            }
            i8 = i2 & 32;
            if (i8 != 0) {
                if ((196608 & i) == 0) {
                    if (composerStartRestartGroup.changed(z3)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i3 |= i9;
                }
                if ((i2 & 64) != 0) {
                    if ((i & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i10 = 1048576;
                        } else {
                            i10 = 524288;
                        }
                        i3 |= i10;
                    }
                    if ((i3 & 599187) != 599186) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                        if (i11 != 0) {
                            modifier4 = Modifier.INSTANCE;
                        } else {
                            modifier4 = modifier2;
                        }
                        if (i4 != 0) {
                            z9 = true;
                        } else {
                            z9 = z4;
                        }
                        if (i6 != 0) {
                            z10 = true;
                        } else {
                            z10 = z2;
                        }
                        if (i8 != 0) {
                            z11 = true;
                        } else {
                            z11 = z3;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue = new Function1() { // from class: hvd
                                public final Object invoke(Object obj) {
                                    return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        composer2 = composerStartRestartGroup;
                        SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        modifier3 = modifier4;
                        z7 = z9;
                        z6 = z10;
                        z8 = z11;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        z6 = z2;
                        modifier3 = modifier2;
                        z7 = z4;
                        z8 = z3;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                            public final Object invoke(Object obj, Object obj2) {
                                return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 1572864;
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    if (i11 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z9 = true;
                    } else {
                        z9 = z4;
                    }
                    if (i6 != 0) {
                        z10 = true;
                    } else {
                        z10 = z2;
                    }
                    if (i8 != 0) {
                        z11 = true;
                    } else {
                        z11 = z3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function1() { // from class: hvd
                            public final Object invoke(Object obj) {
                                return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composer2 = composerStartRestartGroup;
                    SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    z7 = z9;
                    z6 = z10;
                    z8 = z11;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z6 = z2;
                    modifier3 = modifier2;
                    z7 = z4;
                    z8 = z3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            if ((i2 & 64) != 0) {
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i10 = 1048576;
                    } else {
                        i10 = 524288;
                    }
                    i3 |= i10;
                }
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    if (i11 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z9 = true;
                    } else {
                        z9 = z4;
                    }
                    if (i6 != 0) {
                        z10 = true;
                    } else {
                        z10 = z2;
                    }
                    if (i8 != 0) {
                        z11 = true;
                    } else {
                        z11 = z3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function1() { // from class: hvd
                            public final Object invoke(Object obj) {
                                return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composer2 = composerStartRestartGroup;
                    SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    z7 = z9;
                    z6 = z10;
                    z8 = z11;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z6 = z2;
                    modifier3 = modifier2;
                    z7 = z4;
                    z8 = z3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            if ((i3 & 599187) != 599186) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                if (i11 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    z9 = true;
                } else {
                    z9 = z4;
                }
                if (i6 != 0) {
                    z10 = true;
                } else {
                    z10 = z2;
                }
                if (i8 != 0) {
                    z11 = true;
                } else {
                    z11 = z3;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: hvd
                        public final Object invoke(Object obj) {
                            return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composer2 = composerStartRestartGroup;
                SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                z7 = z9;
                z6 = z10;
                z8 = z11;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                z6 = z2;
                modifier3 = modifier2;
                z7 = z4;
                z8 = z3;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                    public final Object invoke(Object obj, Object obj2) {
                        return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        i8 = i2 & 32;
        if (i8 != 0) {
            if ((196608 & i) == 0) {
                if (composerStartRestartGroup.changed(z3)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i3 |= i9;
            }
            if ((i2 & 64) != 0) {
                if ((i & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i10 = 1048576;
                    } else {
                        i10 = 524288;
                    }
                    i3 |= i10;
                }
                if ((i3 & 599187) != 599186) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                    if (i11 != 0) {
                        modifier4 = Modifier.INSTANCE;
                    } else {
                        modifier4 = modifier2;
                    }
                    if (i4 != 0) {
                        z9 = true;
                    } else {
                        z9 = z4;
                    }
                    if (i6 != 0) {
                        z10 = true;
                    } else {
                        z10 = z2;
                    }
                    if (i8 != 0) {
                        z11 = true;
                    } else {
                        z11 = z3;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                        objRememberedValue = new Function1() { // from class: hvd
                            public final Object invoke(Object obj) {
                                return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    composer2 = composerStartRestartGroup;
                    SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    modifier3 = modifier4;
                    z7 = z9;
                    z6 = z10;
                    z8 = z11;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z6 = z2;
                    modifier3 = modifier2;
                    z7 = z4;
                    z8 = z3;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                        public final Object invoke(Object obj, Object obj2) {
                            return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            if ((i3 & 599187) != 599186) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                if (i11 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    z9 = true;
                } else {
                    z9 = z4;
                }
                if (i6 != 0) {
                    z10 = true;
                } else {
                    z10 = z2;
                }
                if (i8 != 0) {
                    z11 = true;
                } else {
                    z11 = z3;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: hvd
                        public final Object invoke(Object obj) {
                            return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composer2 = composerStartRestartGroup;
                SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                z7 = z9;
                z6 = z10;
                z8 = z11;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                z6 = z2;
                modifier3 = modifier2;
                z7 = z4;
                z8 = z3;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                    public final Object invoke(Object obj, Object obj2) {
                        return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        if ((i2 & 64) != 0) {
            if ((i & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i10 = 1048576;
                } else {
                    i10 = 524288;
                }
                i3 |= i10;
            }
            if ((i3 & 599187) != 599186) {
                z5 = true;
            } else {
                z5 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
                if (i11 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if (i4 != 0) {
                    z9 = true;
                } else {
                    z9 = z4;
                }
                if (i6 != 0) {
                    z10 = true;
                } else {
                    z10 = z2;
                }
                if (i8 != 0) {
                    z11 = true;
                } else {
                    z11 = z3;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: hvd
                        public final Object invoke(Object obj) {
                            return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                composer2 = composerStartRestartGroup;
                SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
                z7 = z9;
                z6 = z10;
                z8 = z11;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                z6 = z2;
                modifier3 = modifier2;
                z7 = z4;
                z8 = z3;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                    public final Object invoke(Object obj, Object obj2) {
                        return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 1572864;
        if ((i3 & 599187) != 599186) {
            z5 = true;
        } else {
            z5 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z5, i3 & 1)) {
            if (i11 != 0) {
                modifier4 = Modifier.INSTANCE;
            } else {
                modifier4 = modifier2;
            }
            if (i4 != 0) {
                z9 = true;
            } else {
                z9 = z4;
            }
            if (i6 != 0) {
                z10 = true;
            } else {
                z10 = z2;
            }
            if (i8 != 0) {
                z11 = true;
            } else {
                z11 = z3;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1807005299, i3, -1, "androidx.compose.material3.SwipeToDismissBox (SwipeToDismissBox.kt:381)");
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: hvd
                    public final Object invoke(Object obj) {
                        return SwipeToDismissBoxKt.g((SwipeToDismissBoxValue) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composer2 = composerStartRestartGroup;
            SwipeToDismissBox(swipeToDismissBoxState2, function5, modifier4, z9, z10, z11, (Function1) objRememberedValue, function4, composer2, (i3 & 14) | 1572864 | (i3 & 112) | (i3 & 896) | (i3 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (57344 & i3) | (458752 & i3) | ((i3 << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            z7 = z9;
            z6 = z10;
            z8 = z11;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            z6 = z2;
            modifier3 = modifier2;
            z7 = z4;
            z8 = z3;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ivd
                public final Object invoke(Object obj, Object obj2) {
                    return SwipeToDismissBoxKt.i(swipeToDismissBoxState, function3, modifier3, z7, z6, z8, function4, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
